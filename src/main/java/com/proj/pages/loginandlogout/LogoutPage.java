package com.proj.pages.loginandlogout;

import com.proj.utils.UI_Utils.ActionHelper;
import org.openqa.selenium.By;
import org.testng.Assert;

public class LogoutPage {

    /**
     * Identifying the mobile elements by using By class
     */
    private static final By account_page_button = By.xpath("//android.view.View[@content-desc='Account']");
    private static final By logout_option = By.xpath("//android.view.View[@content-desc='Logout']");
    private static final By logout_popup = By.xpath("//android.widget.ImageView[@content-desc='Are you sure you want to logout?']");
    private static final By logout_button = By.xpath("//android.widget.Button[@content-desc='Logout']");
    private static final By cancel_button = By.xpath("//android.widget.Button[@content-desc='Logout']");

    /**
     * This method is used to log out to the application
     */
    public void logoutToApplication() {
        ActionHelper.clickOnElement(account_page_button);
        ActionHelper.performScrollBottomToTop();
        ActionHelper.scrollToSpecificElementAndClick(logout_option);
        String logOutPopUp = ActionHelper.getAttribute(logout_popup);
        Assert.assertEquals(logOutPopUp, "Are you sure you want to logout?");
        ActionHelper.clickOnElement(logout_button);
    }
}
