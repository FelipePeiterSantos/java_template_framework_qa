package com.grocerycrud.qa.testcases.runners;

import com.grocerycrud.qa.base.TestBase;
import com.grocerycrud.qa.pages.AddPage;
import com.grocerycrud.qa.pages.HomePage;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;

@Listeners
public class DesafiosTest {

    public void setTestBase(TestBase testBase) {
        this.testBase = testBase;
    }

    private TestBase testBase;
    private HomePage homePage;
    private AddPage addPage;


    @BeforeTest
    public void setUpTestCase(){
        if(testBase == null) {
            testBase = new TestBase();
            testBase.initialization(this.getClass().getSimpleName());
        }
        homePage = new HomePage(testBase);
        addPage = new AddPage(testBase);
    }

    @BeforeMethod
    public void setUpTest(Method testMethod) {
        testBase.startTestReport(testMethod);
    }

    @Test(testName = "Desafio 1 - Cadastrar novo cliente com sucesso", priority = 1)
    public void cadastrarNovoClienteComSucesso() {
        homePage.selectBootstrapVs("Bootstrap V4 Theme")
                .addNewCustomer();
		addPage.fillCustomerForm(
				"Teste Sicredi","Teste","Felipe Peiter dos Santos","51 9999-9999",
				"Av Assis Brasil, 3970","Torre D","Porto Alegre","RS",
				"91000-000","Brasil","Fixter","200")
				.saveForm();
		Assert.assertTrue(addPage.validateRegistrationReturnMessage("Your data has been successfully stored into the database. Edit Customer or Go back to list"),
                "Nao foi possivel realizar o cadastro do cliente");
    }

    @Test(testName = "Desafio 2 - Remover cadastro da lista de clientes com sucesso"
            , priority = 2, dependsOnMethods = {"cadastrarNovoClienteComSucesso"})
	public void removerCadastroCliente(){
        addPage.goBackToCustomersList(homePage);

        homePage.filterCustomersByName("Teste Sicredi")
                .deleteCustomerFiltered();
        Assert.assertTrue(homePage.returnMessageWhenDelete("Your data has been successfully deleted from the database."),
                "Nao foi possivel remover o cadastro do cliente");
	}

    @AfterMethod
    public void tearDownTest(ITestResult result) {
        switch (result.getStatus()){
            case ITestResult.FAILURE:
                testBase.logFail("Teste falhou");
                break;
            case ITestResult.SUCCESS:
                testBase.logPass("Teste obteve sucesso");
                break;
            default:
                testBase.logInfo("Teste finalizou");
                break;
        }
        testBase.endTestReport();
    }

    @AfterTest
    public void tearDownTestCase(){
        testBase.finalizeReport();
        testBase.driver.quit();
    }
}
