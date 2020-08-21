package com.grocerycrud.qa.testcases;

import com.grocerycrud.qa.base.TestBase;
import com.grocerycrud.qa.pages.AddPage;
import com.grocerycrud.qa.pages.HomePage;
import com.grocerycrud.qa.util.TestListener;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;

@Listeners(TestListener.class)
public class DesafiosSegundaOpcaoTest {

    private TestBase testBase;
    private DesafiosTest desafiosTest;

    @BeforeTest
    public void setUpTestCase(ITestContext testCaseContext){
        testBase = new TestBase();
        testBase.initialize(testCaseContext);
        desafiosTest = new DesafiosTest();
    }

    @BeforeMethod
    public void setUpTest(Method testMethod){
        testBase.startTestReport(testMethod.getAnnotation(Test.class).testName());
    }

    @Test(testName = "Desafio 2 (Segunda opcao) - Cadastrar um novo cliente e remove-lo da lista de clientes com sucesso")
    public void cadastrarERemoverCliente(){
        desafiosTest.cadastrarNovoClienteComSucesso();
        desafiosTest.removerCadastroCliente();
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
        TestBase.driver.quit();
        testBase.finalizeReport();
    }
}
