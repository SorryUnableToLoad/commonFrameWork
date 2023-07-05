package com.proj.pages.optimus;

import com.proj.common.driver.DriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.proj.utils.UI_Utils.ActionHelper.clickOnElement;
import static com.proj.utils.UI_Utils.ActionHelper.sendKeys;

public class LoginPage {
    /**
     * This constructor is used to initialize elements using pageFactory
     */
    public LoginPage() {
        WebDriver driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button")
    WebElement LOGIN_BUTTON;
    @FindBy(id = "identifierId")
    WebElement USER_MAIL_ID_TEXT_BOX;
    @FindBy(xpath = "//div[@id='identifierNext']/div/button")
    WebElement MAIL_ID_NEXT_BUTTON;
    @FindBy(name = "password")
    WebElement USER_PASSWORD_TEXT_BOX;
    @FindBy(xpath = "//div[@id='passwordNext']/div/button")
    WebElement USER_PASSWORD_NEXT_BUTTON;

    private LoginPage clickOnLoginButton() {
        clickOnElement(LOGIN_BUTTON);
        return this;
    }

    private LoginPage clickOnContinueWithGoogle() {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) DriverManager.getDriver();
        /*WebElement element = (WebElement) javascriptExecutor.executeScript("return document.querySelector('div#supertokens-root')" +
                ".shadowRoot.querySelector('div')" +
                ".querySelector('div').querySelectorAll('div')[2].querySelector('span')" +
                ".querySelector('button')");*/
        WebElement element = (WebElement) javascriptExecutor.executeScript("return document.querySelector('div#supertokens-root')" +
                ".shadowRoot.querySelector('button')");
        javascriptExecutor.executeScript("arguments[0].click();", element);
        return this;
    }

    public LoginPage login() throws InterruptedException {
        clickOnElement(LOGIN_BUTTON);
        Thread.sleep(5000);
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) DriverManager.getDriver();
        WebElement element = (WebElement) javascriptExecutor.executeScript("return document.querySelector('div#supertokens-root')" +
                ".shadowRoot.querySelector('button')");
        javascriptExecutor.executeScript("arguments[0].click();", element);
        Thread.sleep(5000);
        return this;
    }

    private LoginPage setUserMailIdTextBox(String userMailId) {
        sendKeys(USER_MAIL_ID_TEXT_BOX, userMailId);
        return this;
    }

    private LoginPage clickMailIdNextButton() {
        clickOnElement(MAIL_ID_NEXT_BUTTON);
        return this;
    }

    private LoginPage setMailIdPasswordTextBox(String userPassword) {
        sendKeys(USER_PASSWORD_TEXT_BOX, userPassword);
        return this;
    }

    private HomePage clickPasswordNextButton() {
        clickOnElement(USER_PASSWORD_NEXT_BUTTON);
        return new HomePage();
    }

    public HomePage loginToApplication(String userMailId, String userPassword) {
        return clickOnLoginButton()
                .clickOnContinueWithGoogle()
                .setUserMailIdTextBox(userMailId)
                .clickMailIdNextButton()
                .setMailIdPasswordTextBox(userPassword)
                .clickPasswordNextButton();
    }
}
