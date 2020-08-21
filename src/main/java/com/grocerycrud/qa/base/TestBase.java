package com.grocerycrud.qa.base;

import com.grocerycrud.qa.util.TestUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;

	private static ExtentReports extent;
	private static ExtentTest test;

	public TestBase(){
		try {
			prop = new Properties();
			FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+ "/src/test/resources/config.properties");
			prop.load(fileInputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void initReporter(ITestContext testContext){
		extent = new ExtentReports(System.getProperty("user.dir") + "/target/" + testContext.getName()+ ".html", true);
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
			logFail("Navegador informado nao possui suporte");
			Assert.assertTrue(false,"Navegador informado nao possui suporte");
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
		
	}
	public void startTestReport(Method testMethod){
		test = extent.startTest(testMethod.getAnnotation(Test.class).testName());
	}
	public void endTestReport(){
		extent.endTest(test);
	}

	public void finalizeReport(){
		extent.flush();
		extent.close();
	}

	public void logInfo(String log){
		System.out.println(log);
		test.log(LogStatus.INFO,log);
	}

	public void logPass(String log){
		System.out.println(log);
		test.log(LogStatus.PASS,log);
	}

	public void logFail(String log){
		System.out.println(log);
		test.log(LogStatus.FAIL,log);
	}

	public void logPrint(String log){
		logPrint(LogStatus.INFO,log);
	}
	public void logPrint(LogStatus status, String log){
		System.out.println(log);
		test.log(status,log+test.addScreenCapture(TestUtil.takeScreenshot()));
	}

	public static void wait(int timeout){
		try {
			Thread.sleep(timeout*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public WebElement waitForElement(WebElement element, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
		return wait.until(ExpectedConditions.visibilityOf(element));
	}
}
