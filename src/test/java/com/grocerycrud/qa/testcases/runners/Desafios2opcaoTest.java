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
public class Desafios2opcaoTest {

    public void setTestBase(TestBase testBase) {
        this.testBase = testBase;
    }

    private TestBase testBase;
    private DesafiosTest desafiosTest;

    @BeforeTest
    public void setUpTestCase(){
        if(testBase == null){
            testBase = new TestBase();
            testBase.initialization(this.getClass().getSimpleName());
        }
        desafiosTest = new DesafiosTest();
        desafiosTest.setTestBase(this.testBase);
        desafiosTest.setUpTestCase();
    }

    @BeforeMethod
    public void setUpTest(Method testMethod) {
        testBase.startTestReport(testMethod);
    }

    @Test(testName = "Desafio 2 (Segunda opcao) - Cadastrar um novo cliente e remove-lo da lista de clientes com sucesso")
	public void cadastrarERemoverCliente(){
        desafiosTest.cadastrarNovoClienteComSucesso();
        desafiosTest.removerCadastroCliente();
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
