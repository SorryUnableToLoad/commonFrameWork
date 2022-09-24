package com.proj.pages.trial;

import com.proj.utils.CommonMethods;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LoginPageDemo extends CommonMethods {

    LoginPageObjectRepo loginPageObjectRepo=new LoginPageObjectRepo();
    public LoginPageDemo(WebDriver driver){
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), loginPageObjectRepo);
    }

   public void login(){
        clickOnElement(loginPageObjectRepo.mobileNumberInputBox);
        enterText(loginPageObjectRepo.mobileNumberInputBox, "7019020148");
        clickOnElement(loginPageObjectRepo.otpAllowButton);
   }
}
