package com.proj.pages.mobile.customerapp.SelectShop;

import com.proj.utils.UI_Utils.ActionHelper;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.proj.utils.UI_Utils.ActionHelper.*;

public class SelectShop {
    public SelectShop() {
    }

    private static final By SELECT_SHOP_PAGE= MobileBy.AccessibilityId("Select Shop");
    private static final By CHANGE_STORE = MobileBy.xpath("//android.view.View[@content-desc=\"Hello!\"]/child::android.view.View[1]");

    private static final By LIST_OF_STORES = MobileBy.xpath("//android.view.View[@content-desc='Select Shop']/following-sibling::android.view.View[1]/android.view.View/android.view.View");
    private static final By APP_TEST_CUSTOMER = MobileBy.AccessibilityId("App /Test Customer\n" + "Xxxxx, Xxxx, Dummy2, Tamil Nadu, India, 641006");
    private static final By APP_TEST_CUSTOMER_0=MobileBy.AccessibilityId("App Test Customer 0\n" + "Address Line 1*, Address Line 2*, Dummy3, Karnataka, India, 560095\n" + "Please activate your shop to place order");
    private static final By APP_TEST_CUSTOMER_1 = MobileBy.AccessibilityId("App Test Customer 1\n" + "asdfgh, qwerty, Dummy3, Karnataka, India, 560025");
    private static final By APP_TEST_CUSTOMER_2 = MobileBy.AccessibilityId("App Test Customer 2\n" + "Mira Road, Mumbai, Dummy3, Maharashtra, India, 401107");

    private static final By SELECT_STORE_BUTTON = MobileBy.AccessibilityId("SELECT STORE");
    public static void changeStore(String storeName) {
        storeName = null;
        switch (storeName) {
            case "App_Test_Customer_0":
                clickOnElement(CHANGE_STORE);
                verifyIsElementDisplayed(SELECT_SHOP_PAGE);
                scrollToSpecificElementAndClick(APP_TEST_CUSTOMER_0);
                break;
            case "App_Test_Customer_1":
                clickOnElement(CHANGE_STORE);
                verifyIsElementDisplayed(SELECT_SHOP_PAGE);
                scrollToSpecificElementAndClick(APP_TEST_CUSTOMER_1);
                break;
            case "App_Test_Customer_2":
                clickOnElement(CHANGE_STORE);
                verifyIsElementDisplayed(SELECT_SHOP_PAGE);
                scrollToSpecificElementAndClick(APP_TEST_CUSTOMER_2);
                break;
            default:
                clickOnElement(CHANGE_STORE);
                verifyIsElementDisplayed(SELECT_SHOP_PAGE);
                scrollToSpecificElementAndClick(APP_TEST_CUSTOMER);
        }
    }

    public static void changeStore() {
        Assert.assertEquals(ActionHelper.verifyIsElementDisplayed(SELECT_SHOP_PAGE),true,"Select Shop Page is not displayed");
        ActionHelper.clickOnElement(APP_TEST_CUSTOMER);
        ActionHelper.clickOnElement(SELECT_STORE_BUTTON);
    }
}
