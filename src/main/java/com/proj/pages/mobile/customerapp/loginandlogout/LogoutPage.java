package com.proj.pages.mobile.customerapp.loginandlogout;

import com.proj.utils.UI_Utils.ActionHelper;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.proj.utils.UI_Utils.ActionHelper.clickOnElement;

public class LogoutPage {

    /**
     * Identifying the mobile elements by using By class
     */
    private static final By ACCOUNT_PAGE_BUTTON = MobileBy.AccessibilityId("Account");
    private static final By VIEW_HISTORY_TEXT = MobileBy.xpath("//android.view.View[@content-desc='View History']");
    private static final By LOGOUT_OPTION = MobileBy.xpath("//android.widget.ImageView[@content-desc='Logout']");
    private static final By LOGOUT_POPUP = By.xpath("//android.widget.ImageView[@content-desc='Are you sure you want to logout?']");
    private static final By LOGOUT_BUTTON = By.xpath("//android.widget.Button[@content-desc='Logout']");
    private static final By CANCEL_BUTTON = By.xpath("//android.widget.Button[@content-desc='Logout']");

    /**
     * This method is used to log out to the application
     */
    public void logoutToApplication() {
        clickOnElement(ACCOUNT_PAGE_BUTTON);
        Assert.assertEquals(ActionHelper.verifyIsElementDisplayed(VIEW_HISTORY_TEXT), true, "Account module is not displayed");
        //ActionHelper.scrollForMobile();
        ActionHelper.performScroll(LOGOUT_OPTION);
        ActionHelper.clickOnElement(LOGOUT_OPTION);
        Assert.assertEquals(ActionHelper.verifyIsElementDisplayed(LOGOUT_POPUP), true, "Logout popup is not displayed");
        ActionHelper.clickOnElement(LOGOUT_BUTTON);
    }
}
