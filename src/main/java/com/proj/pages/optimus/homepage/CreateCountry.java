package com.proj.pages.optimus.homepage;

import com.proj.common.driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.proj.common.base.WebSetUp.logger;
import static com.proj.utils.UI_Utils.ActionHelper.clickOnElement;
import static com.proj.utils.UI_Utils.ActionHelper.sendKeys;

public class CreateCountry {
    
    public CreateCountry() {
        WebDriver driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[text()='Create Country']")
    WebElement createCountry;
    @FindBy(id = "name")
    WebElement countryNameTextBox;
    @FindBy(xpath = "//span[text()='Submit']")
    WebElement submitButton;
    @FindBy(xpath = "//div[@class='ant-notification-notice-description']")
    WebElement successMessageForCountryName;
    @FindBy(xpath = "//div[@class='ant-notification-notice-message']")
    WebElement errorMessageForCountryName;

    public CreateCountry clickOnCreateCountry() {
        clickOnElement(createCountry);
        return this;
    }

    public CreateCountry setCountryName(String countryName) {
        clickOnElement(countryNameTextBox);
        sendKeys(countryNameTextBox, countryName);
        return this;
    }

    public CreateCountry clickOnSubmit() {
        clickOnElement(submitButton);
        return this;
    }

    public String isErrorMessageDisplayedForCountryName() {
        String errorMessageForCountryNameText = errorMessageForCountryName.getText();
        logger.info("Error message for create city : " + errorMessageForCountryNameText);
        return errorMessageForCountryNameText;
    }

    public String isSuccessMessageDisplayedForCountryName() {
        String successMessageForCountryNameText = successMessageForCountryName.getText();
        logger.info("Success message for create city : " + successMessageForCountryNameText);
        return successMessageForCountryNameText;
    }

}
