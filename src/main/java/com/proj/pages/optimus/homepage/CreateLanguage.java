package com.proj.pages.optimus.homepage;

import com.proj.common.driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.proj.common.base.WebSetUp.logger;
import static com.proj.utils.UI_Utils.ActionHelper.clickOnElement;
import static com.proj.utils.UI_Utils.ActionHelper.sendKeys;

public class CreateLanguage {


    public CreateLanguage() {
        WebDriver driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[text()='Create language']")
    WebElement createLanguage;
    @FindBy(id = "name")
    WebElement languageNameTextBox;
    @FindBy(id = "regionalName")
    WebElement regionalNameTextBox;
    @FindBy(id = "code")
    WebElement codeTextBox;
    @FindBy(xpath = "//span[text()='Submit']")
    WebElement submitButton;
    @FindBy(xpath = "//div[@class='ant-notification-notice-description']")
    WebElement successMessageForLanguageName;
    @FindBy(xpath = "//div[@class='ant-notification-notice-message']")
    WebElement errorMessageForLanguageName;

    public CreateLanguage clickOnCreateLanguage() {
        clickOnElement(createLanguage);
        return this;
    }

    public CreateLanguage setLanguageName(String languageName) {
        clickOnElement(languageNameTextBox);
        sendKeys(languageNameTextBox, languageName);
        return this;
    }

    public CreateLanguage setRegionalName(String regionalName) {
        clickOnElement(regionalNameTextBox);
        sendKeys(regionalNameTextBox, regionalName);
        return this;
    }

    public CreateLanguage setLanguageCode(String languageCode) {
        clickOnElement(codeTextBox);
        sendKeys(codeTextBox, languageCode);
        return this;
    }

    public CreateLanguage clickOnSubmit() {
        clickOnElement(submitButton);
        return this;
    }

    public String isErrorMessageDisplayedForLanguageName() {
        String errorMessageForLanguageNameText = errorMessageForLanguageName.getText();
        logger.info("Error message for create language : " + errorMessageForLanguageNameText);
        return errorMessageForLanguageNameText;
    }

    public String isSuccessMessageDisplayedForLanguageName() {
        String successMessageForLanguageNameText = successMessageForLanguageName.getText();
        System.out.println("Error message for create language : " + successMessageForLanguageNameText);
        return successMessageForLanguageNameText;
    }

}
