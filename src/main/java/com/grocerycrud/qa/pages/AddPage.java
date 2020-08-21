package com.grocerycrud.qa.pages;

import com.grocerycrud.qa.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddPage extends TestBase {

    @FindBy(id = "field-customerName")
    WebElement inputName;

    @FindBy(id = "field-contactLastName")
    WebElement inputLastName;

    @FindBy(id = "field-contactFirstName")
    WebElement inputContactFirstName;

    @FindBy(id = "field-phone")
    WebElement inputPhone;

    @FindBy(id = "field-addressLine1")
    WebElement inputAddressLine1;

    @FindBy(id = "field-addressLine2")
    WebElement inputAddressLine2;

    @FindBy(id = "field-city")
    WebElement inputCity;

    @FindBy(id = "field-state")
    WebElement inputState;

    @FindBy(id = "field-postalCode")
    WebElement inputPostalCode;

    @FindBy(id = "field-country")
    WebElement inputCountry;

    @FindBy(id = "field-creditLimit")
    WebElement inputCreditLimit;

    @FindBy(id = "form-button-save")
    WebElement btnSave;

    @FindBy(id = "field_salesRepEmployeeNumber_chosen")
    WebElement clickFromEmployeer;

    @FindBy(css = "#field_salesRepEmployeeNumber_chosen input")
    WebElement inputSearchFromEmployeer;

    @FindBy(id = "report-success")
    WebElement msgSuccess;

    @FindBy(id = "report-error")
    WebElement msgError;

    @FindBy(linkText = "Go back to list")
    WebElement linkGoBackToList;

    public AddPage() {
        PageFactory.initElements(driver, this);
    }

    public AddPage fillCustomerForm(String name, String lastName, String contactFirstName, String phone,
                                    String addressLine1, String addressLine2, String city, String state,
                                    String postalCode, String country, String fromEmployeer, String creditLimit) {
        waitForElement(inputName,5).sendKeys(name);
        logInfo("Informou no campo NAME o valor ["+name+"]");
        inputLastName.sendKeys(lastName);
        logInfo("Informou no campo LAST NAME o valor ["+lastName+"]");
        inputContactFirstName.sendKeys(contactFirstName);
        logInfo("Informou no campo CONTACT FIRST NAME o valor ["+contactFirstName+"]");
        inputPhone.sendKeys(phone);
        logInfo("Informou no campo PHONE o valor ["+phone+"]");
        inputAddressLine1.sendKeys(addressLine1);
        logInfo("Informou no campo ADDRESS LINE 1 o valor ["+addressLine1+"]");
        inputAddressLine2.sendKeys(addressLine2);
        logInfo("Informou no campo ADDRESS LINE 2 o valor ["+addressLine2+"]");
        inputCity.sendKeys(city);
        logInfo("Informou no campo CITY o valor ["+city+"]");
        inputState.sendKeys(state);
        logInfo("Informou no campo STATE o valor ["+state+"]");
        inputPostalCode.sendKeys(postalCode);
        logInfo("Informou no campo POSTALCODE o valor ["+postalCode+"]");
        inputCountry.sendKeys(country);
        logInfo("Informou no campo COUNTRY o valor ["+country+"]");
        setClickFromEmployeer(fromEmployeer);
        logInfo("Informou no campo FROM EMPLOYEER o valor ["+fromEmployeer+"]");
        inputCreditLimit.sendKeys(creditLimit);
        logInfo("Informou no campo CREDIT LIMIT o valor ["+creditLimit+"]");
        logPrint( "Informou todos os campos do formulario");
        return this;
    }

    public AddPage saveForm() {
        btnSave.click();
        logInfo("Clicou em salvar formulario");
        return this;
    }

    private void setClickFromEmployeer(String fromEmployeer) {
        clickFromEmployeer.click();
        inputSearchFromEmployeer.sendKeys(fromEmployeer+Keys.ENTER);
    }

    public boolean validateRegistrationReturnMessage(String expectedMsg) {
        String returnedMsg = returnMessageWhenSaving();
        boolean condition = returnedMsg.equals(expectedMsg);
        if(condition)
            logPrint(LogStatus.PASS,"Apresentou a seguinte mensagem ao cadastrar um novo consumidor:<br>"+returnedMsg);
        else
            logPrint(LogStatus.FAIL,"Apresentou mensagem diferente do esperado<br><b>Esperado:<b> "+expectedMsg+"<br><b>Retorno:<b> "+returnedMsg);
        return condition;
    }

    private String returnMessageWhenSaving() {
        for (int i = 0; i < 10; i++) {
            if(msgSuccess.isDisplayed())
                return msgSuccess.getText();
            else if(msgError.isDisplayed())
                return msgError.getText();
            else {
                wait(1);
            }
        }
        logFail("Nao retornou mensagem na tela ao salvar");
        return "";
    }

    public void goBackToCustomersList(HomePage homePage) {
        linkGoBackToList.click();
        waitForElement(homePage.btnAddCustomer,10);
        logPrint( "Voltou para a lista de consumidores");
    }
}
