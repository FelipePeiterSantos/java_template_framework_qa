package com.grocerycrud.qa.testcases;

import com.grocerycrud.qa.base.TestBase;
import com.grocerycrud.qa.controllers.ApiController;
import com.grocerycrud.qa.util.TestListener;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;

@Listeners(TestListener.class)
public class ApiTest {

    private TestBase testBase;
    private ApiController api;

    @BeforeTest
    public void setUpTestCase(ITestContext testCaseContext){
        testBase = new TestBase();
        testBase.initialize(testCaseContext);
    }

    @BeforeMethod
    public void setUpTest(Method testMethod){
        testBase.startTestReport(testMethod.getAnnotation(Test.class).testName());
        api = new ApiController();
    }

    @Test(testName = "RestAssured Test - GET")
    public void test() {
        api.setUri("http://ip.jsontest.com/")
                .get()
                .printResponseOnConsole("/GET Response: ");
    }

    @Test(testName = "RestAssured Test - POST")
    public void test1() {
        api.setUri("http://ip.jsontest.com/")
                .addHeader("Content-Type","application/json")
                .setBody("{\"property\" : [\"Sites\"], \"report_type\" : [\"ALL\"]}")
                .post()
                .printResponseOnConsole("/POST Response: ");
    }

    @AfterMethod
    public void tearDownTest(ITestResult result) {
        switch (result.getStatus()){
            case ITestResult.SUCCESS:
                testBase.log(LogStatus.PASS,"Test passed!");
                break;
            case ITestResult.FAILURE:
                testBase.log(LogStatus.FAIL,"Test failed!");
                break;
            case ITestResult.SKIP:
                testBase.log(LogStatus.SKIP,"Test failed!");
                break;
            default:
                testBase.log(LogStatus.INFO,"Test finished with status ["+result.getStatus()+"]");
                break;
        }
        testBase.endTestReport();
    }

    @AfterTest
    public void tearDownTestCase(){
        testBase.finalizeReport();
    }
}
