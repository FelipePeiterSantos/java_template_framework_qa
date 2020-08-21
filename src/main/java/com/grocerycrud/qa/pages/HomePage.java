package com.grocerycrud.qa.pages;

import com.grocerycrud.qa.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class HomePage extends TestBase{

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

    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    public HomePage selectBootstrapVs(String bootstrapVs) {
        new Select(selectBootstrapVersion).selectByVisibleText(bootstrapVs);
        return this;
    }

    public HomePage addNewCustomer() {
        btnAddCustomer.click();
        return this;
    }

    public HomePage filterCustomersByName(String customerName) {
        inputFilterName.sendKeys(customerName + Keys.ENTER);
        waitForElementVanish(By.className("loading-opacity"),15);
        return this;
    }


    public void deleteCustomerFiltered() {
        dropdownMore.click();
        btnDelete.click();
        btnConfirmDelete.click();
    }

    public String returnMessageWhenDelete() {
        return waitForElement(popupAlert,5).getText();
    }
}
