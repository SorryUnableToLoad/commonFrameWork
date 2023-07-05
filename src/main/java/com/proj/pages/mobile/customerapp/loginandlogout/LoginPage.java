package com.proj.pages.mobile.customerapp.loginandlogout;

import com.proj.common.driver.DriverManager;
import com.proj.utils.UI_Utils.ActionHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage {
    AppiumDriver driver;

    /**
     * This constructor is used to initialize elements using pageFactory
     */
    public LoginPage() {
        driver = (AppiumDriver) DriverManager.getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    /**
     * Identifying the mobile elements by using annotations
     */
    @AndroidFindBy(xpath = "//android.view.View[@content-desc='Login/Signup']")
    private MobileElement LoginPage;
    @AndroidFindBy(xpath = "//android.widget.EditText[@class='android.widget.EditText']")
    private MobileElement mobileNumberInputBox;
    @AndroidFindBy(xpath = "//android.view.View[@content-desc='Enter OTP']")
    private MobileElement otpPage;
    @AndroidFindBy(id = "com.google.android.gms:id/positive_button")
    private MobileElement otpAllowButton;


    private static final By LOGIN_PAGE = MobileBy.AccessibilityId("Login/Signup");
    private static final By LOGIN_PAGE_TEXT = MobileBy.xpath("//android.view.View[@content-desc='Login/Signup']");
    private static final By MOBILE_NUMBER_TEXT = By.xpath("//android.view.View[@content-desc='Please enter your mobile number']");
    private static final By MOBILE_NUMBER_INPUT_BOX = MobileBy.className("android.widget.EditText");
    private static final By OTP_PAGE = MobileBy.AccessibilityId("Enter OTP");
    private static final By OTP_PAGE_TEXT = By.xpath("//android.view.View[@content-desc='Enter OTP']");
    private static final By ALLOW_BUTTON = MobileBy.id("com.google.android.gms:id/positive_button");


    /**
     * This method is used to log in to the application
     *
     * @param mobileNumber
     */
    public void loginToApplication(String mobileNumber) {
        Assert.assertEquals(ActionHelper.verifyIsElementDisplayed(LOGIN_PAGE_TEXT), true, "Login page is not displayed");
        Assert.assertEquals(ActionHelper.verifyIsElementDisplayed(MOBILE_NUMBER_TEXT), true, "Please enter your mobile number text is displayed");
        ActionHelper.clickOnElement(MOBILE_NUMBER_INPUT_BOX);
        ActionHelper.sendKeys(MOBILE_NUMBER_INPUT_BOX, mobileNumber);
        ActionHelper.waitForPresenceOfElement(OTP_PAGE);
        Assert.assertEquals(ActionHelper.verifyIsElementDisplayed(OTP_PAGE_TEXT), true, "OTP page is not displayed");

        //clickOnElement(ALLOW_BUTTON);
    }
}
