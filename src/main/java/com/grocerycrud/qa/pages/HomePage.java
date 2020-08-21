package com.grocerycrud.qa.pages;

import com.grocerycrud.qa.base.TestBase;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.logging.Logger;

public class HomePage {
    public void setTestBase(TestBase testBase) {
        this.testBase = testBase;
    }

    private TestBase testBase;

    @FindBy(id = "switch-version-select")
    WebElement selectBootstrapVersion;

    @FindBy(xpath = "//a[contains(string(),'Add Customer')]")
    WebElement btnAddCustomer;

    @FindBy(name = "customerName")
    WebElement inputFilterName;

    @FindBy(xpath = "//button[contains(string(),'More')]")
    WebElement dropdownMore;

    @FindBy(xpath = "//div[contains(@class,'open')]//a[contains(string(),'Delete')]")
    WebElement btnDelete;

    @FindBy(className = "delete-confirmation-button")
    WebElement btnConfirmDelete;

    @FindBy(css = "div.alert p")
    WebElement popupAlert;

    public HomePage(TestBase testBase) {
        PageFactory.initElements(testBase.driver, this);
        this.testBase = testBase;
    }

    public HomePage selectBootstrapVs(String bootstrapVs) {
        new Select(selectBootstrapVersion).selectByVisibleText(bootstrapVs);
        testBase.logPrint( "Selecionou versao ["+bootstrapVs+"]");
        return this;
    }

    public HomePage addNewCustomer() {
        btnAddCustomer.click();
        testBase.logPrint( "Clicou em Add Customer");
        return this;
    }

    public HomePage filterCustomersByName(String customerName) {
        inputFilterName.sendKeys(customerName + Keys.ENTER);
        TestBase.wait(3);
        testBase.logPrint( "Filtrou os clientes por nome ["+customerName+"]");
        return this;
    }


    public void deleteCustomerFiltered() {
        dropdownMore.click();
        btnDelete.click();
        btnConfirmDelete.click();
        testBase.logPrint( "Deletou o cliente filtrado");
    }

    public boolean returnMessageWhenDelete(String expectedMsg) {
        String returnedMsg = testBase.waitForElement(popupAlert,5).getText();
        testBase.logPass("Apresentou a seguinte mensagem ao deletar cliente:<br>"+returnedMsg);
        return expectedMsg.equals(returnedMsg);
    }
}
