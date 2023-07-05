package com.proj.pages.optimus.homepage;

import com.proj.common.driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.proj.common.base.WebSetUp.logger;
import static com.proj.utils.UI_Utils.ActionHelper.clickOnElement;
import static com.proj.utils.UI_Utils.ActionHelper.sendKeys;

public class CreateState {

    public CreateState() {
        WebDriver driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[text()='Create State']")
    WebElement createState;
    @FindBy(id = "name")
    WebElement stateNameTextBox;
    @FindBy(id = "code")
    WebElement codeTextBox;
    @FindBy(xpath = "//span[text()='Submit']")
    WebElement submitButton;
    @FindBy(xpath = "//div[@class='ant-notification-notice-description']")
    WebElement successMessageForStateName;
    @FindBy(xpath = "//div[@class='ant-notification-notice-message']")
    WebElement errorMessageForStateName;

    public CreateState clickOnCreateState() {
        clickOnElement(createState);
        return this;
    }

    public CreateState setStateName(String stateName) {
    clickOnElement(stateNameTextBox);
    sendKeys(stateNameTextBox,stateName);
    return this;
    }
    public CreateState setStateCode(String stateCode) {
        clickOnElement(codeTextBox);
        sendKeys(codeTextBox,stateCode);
        return this;
    }
    public CreateState clickOnSubmit(){
        clickOnElement(submitButton);
        return this;
    }
    public String isErrorMessageDisplayedForStateName() {
        String errorMessageForCityNameText = errorMessageForStateName.getText();
        logger.info("Error message for create state : "+errorMessageForCityNameText);
        return errorMessageForCityNameText;
    }

    public String isSuccessMessageDisplayedForStateName() {
        String successMessageForCityNameText = successMessageForStateName.getText();
        System.out.println("Error message for create state : "+successMessageForCityNameText);
        return successMessageForCityNameText;
    }
}
