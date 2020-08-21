package com.grocerycrud.qa.pages;

import com.grocerycrud.qa.base.TestBase;
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

    @FindBy(css = "#report-success")
    WebElement returnMsgWhenSaving;

    @FindBy(linkText = "Go back to list")
    WebElement linkGoBackToList;

    public AddPage() {
        PageFactory.initElements(driver, this);
    }

    public AddPage fillInputName(String name) {
        waitForElement(inputName,15).sendKeys(name);
        return this;
    }

    public AddPage fillInputLastName(String lastName) {
        inputLastName.sendKeys(lastName);
        return this;
    }

    public AddPage fillInputContactFirstName(String contactFirstName) {
        inputContactFirstName.sendKeys(contactFirstName);
        return this;
    }

    public AddPage fillInputPhone(String phone) {
        inputPhone.sendKeys(phone);
        return this;
    }

    public AddPage fillInputAddressLine1(String addressLine1) {
        inputAddressLine1.sendKeys(addressLine1);
        return this;
    }

    public AddPage fillInputAddressLine2(String addressLine2) {
        inputAddressLine2.sendKeys(addressLine2);
        return this;
    }

    public AddPage fillInputCity(String city) {
        inputCity.sendKeys(city);
        return this;
    }

    public AddPage fillInputState(String state) {
        inputState.sendKeys(state);
        return this;
    }

    public AddPage fillInputPostalCode(String postalCode) {
        inputPostalCode.sendKeys(postalCode);
        return this;
    }

    public AddPage fillInputCountry(String country) {
        inputCountry.sendKeys(country);
        return this;
    }

    public AddPage selectOptionFromEmployeer(String fromEmployeer) {
        setClickFromEmployeer(fromEmployeer);
        return this;
    }

    public AddPage fillCreditLimit(String creditLimit) {
        inputCreditLimit.sendKeys(creditLimit);
        return this;
    }

    public AddPage saveForm() {
        btnSave.click();
        return this;
    }

    private void setClickFromEmployeer(String fromEmployeer) {
        clickFromEmployeer.click();
        inputSearchFromEmployeer.sendKeys(fromEmployeer+Keys.ENTER);
    }

    public String validateRegistrationReturnMessage() {
        return waitForElement(returnMsgWhenSaving, 10).getText();
    }

    public void goBackToCustomersList(HomePage homePage) {
        linkGoBackToList.click();
        waitForElement(homePage.btnAddCustomer,10);
    }
}
