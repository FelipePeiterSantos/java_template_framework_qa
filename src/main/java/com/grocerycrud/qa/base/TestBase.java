package com.grocerycrud.qa.base;

import com.grocerycrud.qa.util.TestUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class TestBase {
	public static WebDriver driver;
	public static ExtentReports extentReports;
	public static ExtentTest extentTest;

	public Properties prop;
	public Logger logger;

	public void initialize(ITestContext testContext) {
		initProperties();
		initReporter(testContext.getName());
	}

	public void initProperties(){
		try(FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+ "/src/test/resources/config.properties")){
			prop = new Properties();
			prop.load(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initReporter(String runnerName){
		extentReports = new ExtentReports(System.getProperty("user.dir") + "/target/" + runnerName+ ".html", true);
		logger = Logger.getLogger(TestBase.class.getName());
	}

	public void initWebDriver(){
		String browser = prop.getProperty("browser");

		if ("chrome".equals(browser)) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if ("firefox".equals(browser)) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else {
			throw new AssertionError("Navegador informado nao possui suporte");
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

		driver.get(prop.getProperty("url"));

	}

	public void startTestReport(String testName){
		extentTest = extentReports.startTest(testName);
	}
	public void endTestReport(){
		extentReports.endTest(extentTest);
	}

	public void finalizeReport(){
		extentReports.flush();
		extentReports.close();
	}

	public static void logPrint(String log){
		extentTest.log(LogStatus.FAIL,log+extentTest.addScreenCapture(TestUtil.takeScreenshot()));
	}

	public void log(LogStatus status, String log){
		logger.info(log);
		extentTest.log(status,log);
	}

	public WebElement waitForElement(WebElement element, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	public Boolean waitForElementVanish(By inputFilterName, int timeoutInSeconds) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.pollingEvery(1,TimeUnit.NANOSECONDS)
				.withTimeout(3, TimeUnit.SECONDS);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(inputFilterName));
		} catch (TimeoutException | NoSuchElementException e) {
			return true;
		}
		wait = new FluentWait<WebDriver>(driver)
				.pollingEvery(1,TimeUnit.NANOSECONDS)
				.withTimeout(timeoutInSeconds, TimeUnit.SECONDS);

		return wait.until(ExpectedConditions.invisibilityOfElementLocated(inputFilterName));
	}
}
