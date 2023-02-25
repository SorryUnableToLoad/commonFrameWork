package com.proj.pages.loginandlogout;

import com.proj.common.driver.DriverManager;
import com.proj.utils.UI_Utils.ActionHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage {
    AppiumDriver driver;
    /**
     * Identifing the mobile elements by using annotations
     */
    @AndroidFindBy(xpath = "//android.view.View[@content-desc='Login/Signup']")
    private MobileElement LoginPage;
    @AndroidFindBy(xpath = "//android.view.View[@content-desc='Please enter your mobile number']")
    private MobileElement enterMobileNumberText;
    @AndroidFindBy(xpath = "//android.widget.EditText[@class='android.widget.EditText']")
    private MobileElement mobileNumberInputBox;
    @AndroidFindBy(xpath = "//android.view.View[@content-desc='Enter OTP']")
    private MobileElement otpPage;
    @AndroidFindBy(id = "com.google.android.gms:id/positive_button")
    private MobileElement otpAllowButton;

    /**
     * This constructor is used to initialize elements using pageFactory
     */
    public LoginPage() {
        driver = (AppiumDriver) DriverManager.getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    /**
     * This method is used to login to the application
     *
     * @param mobileNumber
     */
    public void loginToApplication(String mobileNumber) {
        String login = ActionHelper.getAttribute(LoginPage, "content-desc");
        Assert.assertEquals(login, "Login/Signup");
        String enter_your_mobile_number = ActionHelper.getAttribute(enterMobileNumberText, "content-desc");
        Assert.assertEquals(enter_your_mobile_number, "Please enter your mobile number");
        System.out.println(enter_your_mobile_number);
        ActionHelper.clickOnElement(mobileNumberInputBox);
        ActionHelper.waitAndSendKeys(mobileNumberInputBox, mobileNumber);
        //ActionHelper.waitForPresenceOfElement(otpPage);
        String otppage = ActionHelper.getAttribute(otpPage, "content-desc");
        Assert.assertEquals(otppage, "Enter OTP");
        //clickOnElement(otpAllowButton);
    }
}
