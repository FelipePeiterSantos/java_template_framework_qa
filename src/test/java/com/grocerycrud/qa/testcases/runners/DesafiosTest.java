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
public class DesafiosTest extends TestBase {

    HomePage homePage;
    AddPage addPage;

    @BeforeTest
    public void setUpTestCase(ITestContext testCaseContext){
        initReporter(testCaseContext);
        initWebDriver();
        homePage = new HomePage();
        addPage = new AddPage();
    }

    @BeforeMethod
    public void setUpTest(Method testMethod) {
        startTestReport(testMethod);
    }

    @Test(testName = "Desafio 1 - Cadastrar novo consumidor com sucesso", priority = 1)
    public void cadastrarNovoConsumidorComSucesso() {
        homePage.selectBootstrapVs("Bootstrap V4 Theme")
                .addNewCustomer();
		addPage.fillCustomerForm(
				"Teste Sicredi","Teste","Felipe Peiter dos Santos","51 9999-9999",
				"Av Assis Brasil, 3970","Torre D","Porto Alegre","RS",
				"91000-000","Brasil","Fixter","200")
				.saveForm();
		Assert.assertTrue(addPage.validateRegistrationReturnMessage("Your data has been successfully stored into the database. Edit Customer or Go back to list"),
                "Nao foi possivel realizar o cadastro do consumidor");
    }

    @Test(testName = "Desafio 2 - Remover cadastro da lista de consumidores com sucesso"
            , priority = 2, dependsOnMethods = {"cadastrarNovoConsumidorComSucesso"})
	public void removerCadastroConsumidor(){
        addPage.goBackToCustomersList(homePage);

        homePage.filterCustomersByName("Teste Sicredi")
                .deleteCustomerFiltered();
        Assert.assertTrue(homePage.returnMessageWhenDelete("Your data has been successfully deleted from the database."),
                "Nao foi possivel remover o cadastro do consumidor");
	}

    @AfterMethod
    public void tearDownTest(ITestResult result) {
        switch (result.getStatus()){
            case ITestResult.FAILURE:
                logFail("Teste falhou");
                break;
            case ITestResult.SUCCESS:
                logPass("Teste obteve sucesso");
                break;
            default:
                logInfo("Teste finalizou");
                break;
        }
        endTestReport();
    }

    @AfterTest
    public void tearDownTestCase(){
        finalizeReport();
        driver.quit();
    }
}
