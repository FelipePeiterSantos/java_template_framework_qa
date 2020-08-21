package com.grocerycrud.qa.util;

import com.grocerycrud.qa.base.TestBase;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    public void onTestFailure(ITestResult iTestResult) {
        TestBase.logPrint(iTestResult.getThrowable().getMessage());
    }

    public void onTestStart(ITestResult iTestResult) {
        //Unused method
    }

    public void onTestSuccess(ITestResult iTestResult) {
        //Unused method
    }

    public void onTestSkipped(ITestResult iTestResult) {
        //Unused method
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        //Unused method
    }

    public void onStart(ITestContext iTestContext) {
        //Unused method
    }

    public void onFinish(ITestContext iTestContext) {
        //Unused method
    }
}
