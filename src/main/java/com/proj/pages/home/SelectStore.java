package com.proj.pages.home;

import org.openqa.selenium.By;

import static com.proj.utils.ActionHelper.*;

public class SelectStore {
    public SelectStore() {
    }

    private static final By change_store = By.xpath("//android.view.View[@content-desc='Hello!']/following-sibling::android.view.View[1]");
    private static final By STORE_LIST = By.xpath("//android.view.View[@content-desc='Select Shop']/following-sibling::android.view.View[2]/android.view.View/android.view.View");
    private static final By test_customer_1 = By.xpath("//android.view.View[@content-desc=\"Test Customer 1\n" + "#123/2, brigade rd, ashok nagar bangalore 560025, Bengaluru, Karnataka, India, 560025\"]");

    private static final By APP_TEST_CUSTOMER_3 = By.xpath("//android.view.View[@content-desc='App Test Customer 3\n" + "Mira Road, Mumbai, Mumbai, Maharashtra, India, 401107']");
    private static final By APP_TEST_CUSTOMER_4 = By.xpath("//android.view.View[@content-desc='App Test Customer 4\n" + "Xxxxx, Xxxx, Coimbatore, Tamil Nadu, India, 641006']");

    public static void changeStore(String store) {
        store = null;
        switch (store) {
            case "App_Test_Customer_3":
                clickOnElement(change_store);
                scrollToSpecificElementAndClick(APP_TEST_CUSTOMER_3);
                break;
            case "App_Test_Customer_4":
                clickOnElement(change_store);
                scrollToSpecificElementAndClick(APP_TEST_CUSTOMER_4);
                break;
            default:
                clickOnElement(change_store);
                scrollToSpecificElementAndClick(test_customer_1);
        }
    }

    public void changeStore() {
        clickOnElement(change_store);
        performScrollBottomToTop();
        scrollToSpecificElementAndClick(test_customer_1);
    }
}
