package com.proj.pages.loginpage;

import org.openqa.selenium.By;

import static com.proj.utils.PageActionsHelper.*;

public class LoginPage {
    private static final By LOGIN_PAGE = By.xpath("//android.view.View[@content-desc=\"Login/Signup\"]");
    private static final By NUMBER_TEXT_BOX = By.className("android.widget.EditText");
    private static final By OTP_ALLOW_BUTTON = By.id("com.google.android.gms:id/positive_button");

    public void loginToApplication() {
        getValue(LOGIN_PAGE);
        clickOnElement(NUMBER_TEXT_BOX);
        sendValue(NUMBER_TEXT_BOX,"7019020148");
        waitAndClick(OTP_ALLOW_BUTTON);
    }
}
