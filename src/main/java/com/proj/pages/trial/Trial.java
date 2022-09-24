package com.proj.pages.trial;

import com.proj.driver.DriverManager;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class Trial {

    public  Trial(){
        WebDriver driver = DriverManager.getCurrentDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }
    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Login/Signup\"]")
    public WebElement loginPage;

    @AndroidFindBy(className = "android.widget.EditText")
    public WebElement mobileNumberInputBox;

    @AndroidFindBy(id = "com.google.android.gms:id/positive_button")
    public WebElement otpAllowButton;

    public void login(){
        System.out.println(Trial.class);
        System.out.println(getClass());
        mobileNumberInputBox.click();
        mobileNumberInputBox.sendKeys("7019020148");
        otpAllowButton.click();


    }
}
