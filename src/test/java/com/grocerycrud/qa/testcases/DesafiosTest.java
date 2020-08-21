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
public class DesafiosTest {

    private TestBase testBase;
    private HomePage homePage;
    private AddPage addPage;

    @BeforeTest
    public void setUpTestCase(ITestContext testCaseContext){
        testBase = new TestBase();
        testBase.initialize(testCaseContext);
    }

    @BeforeMethod
    public void setUpTest(Method testMethod){
        testBase.initWebDriver();
        testBase.startTestReport(testMethod.getAnnotation(Test.class).testName());
    }

    @Test(testName = "Desafio 1 - Cadastrar novo cliente com sucesso")
    public void cadastrarNovoClienteComSucesso() {
        homePage = new HomePage();
        addPage = new AddPage();

        homePage.selectBootstrapVs("Bootstrap V4 Theme")
                .addNewCustomer();
		addPage.fillInputName("Teste Sicredi")
                .fillInputLastName("Teste")
                .fillInputContactFirstName("Felipe Peiter dos Santos")
                .fillInputPhone("51 9999-9999")
                .fillInputAddressLine1("Av Assis Brasil, 3970")
                .fillInputAddressLine2("Torre D")
                .fillInputCity("Porto Alegre")
                .fillInputState("RS")
                .fillInputPostalCode("91000-000")
                .fillInputCountry("Brasil")
                .selectOptionFromEmployeer("Fixter")
                .fillCreditLimit("200")
                .saveForm();
        Assert.assertEquals(addPage.validateRegistrationReturnMessage(),"Your data has been successfully stored into the database. Edit Customer or Go back to list");
    }

    @Test(testName = "Desafio 2 - Remover cadastro da lista de clientes com sucesso")
	public void removerCadastroCliente(){
        cadastrarNovoClienteComSucesso();

        homePage = new HomePage();
        addPage = new AddPage();

        addPage.goBackToCustomersList(homePage);
        homePage.filterCustomersByName("Teste Sicredi")
                .deleteCustomerFiltered();
        Assert.assertEquals(homePage.returnMessageWhenDelete(),"Your data has been successfully deleted from the database.");
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
        TestBase.driver.quit();
    }

    @AfterTest
    public void tearDownTestCase(){
        testBase.finalizeReport();
    }
}
