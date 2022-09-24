package com.proj.pages.trial;

import com.proj.driver.DriverManager;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPageObjectRepo {

    /**
     * Identifing the mobile elements by using annotations
     */
    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Login/Signup\"]")
    public WebElement loginPage;

    @AndroidFindBy(className = "android.widget.EditText")
    public WebElement mobileNumberInputBox;

    @AndroidFindBy(id = "com.google.android.gms:id/positive_button")
    public WebElement otpAllowButton;


}
