package com.grocerycrud.qa.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.grocerycrud.qa.base.TestBase;

public class TestUtil {
	private TestBase testBase;

	public static final long PAGE_LOAD_TIMEOUT = 20;
	public static final long IMPLICIT_WAIT = 5;

	public TestUtil(TestBase testBase){
		this.testBase = testBase;
	}

	public String takeScreenshot()  {
		File scrFile = ((TakesScreenshot) testBase.driver).getScreenshotAs(OutputType.FILE);
		String reportFolder = System.getProperty("user.dir")+"/target/";
		String screenshotFolder = "imgs/";
		String screenshotName = + System.currentTimeMillis() + ".png";
		if(!Files.exists(Paths.get(reportFolder+screenshotFolder))){
			try {
				Files.createDirectory(Paths.get(reportFolder+screenshotFolder));
			} catch (IOException e) {
				throw new AssertionError(e);
			}
		}
		try {
			FileUtils.copyFile(scrFile, new File(reportFolder+screenshotFolder+screenshotName));
		} catch (IOException e) {
			throw new AssertionError(e);
		}
		return "./"+screenshotFolder+screenshotName;
	}
}
