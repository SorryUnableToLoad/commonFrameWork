package com.proj.pages.optimus.homepage;

import com.proj.common.driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.proj.utils.UI_Utils.ActionHelper.*;

public class CreateCity {

    public CreateCity() {
        WebDriver driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[text()='Create City']")
    WebElement createCity;
    @FindBy(id = "name")
    WebElement cityNameTextBox;
    @FindBy(xpath = "//span[text()='Submit']")
    WebElement submitButton;
    @FindBy(xpath = "//div[@class='ant-notification-notice-description']")
    WebElement successMessageForCityName;
    @FindBy(xpath = "//div[@class='ant-notification-notice-message']")
    WebElement errorMessageForCityName;

    public CreateCity clickOnCreateCity() {
        clickOnElement(createCity);
        return this;
    }

    public CreateCity setCityName(String cityName) {
        clickOnElement(cityNameTextBox);
        sendKeys(cityNameTextBox, cityName);
        return this;
    }

    public CreateCity clickOnSubmit() {
        clickOnElement(submitButton);
        return this;
    }

    public String isErrorMessageDisplayedForCityName() {
        String errorMessageForCityNameText = errorMessageForCityName.getText();
        logger.info("Error message for create city : " + errorMessageForCityNameText);
        return errorMessageForCityNameText;
    }

    public String isSuccessMessageDisplayedForCityName() {
        String successMessageForCityNameText = successMessageForCityName.getText();
        logger.info("Success message for create city : " + successMessageForCityNameText);
        return successMessageForCityNameText;
    }

}
