package com.proj.pages.mycart;

import org.openqa.selenium.By;

import static com.proj.utils.PageActionsHelper.waitAndClick;

public class MyCart {
    private static final By INCRESE_QTY= By.xpath("//android.view.View[@content-desc=\"1 - 2 kg ₹ 100.00 49 Kgs Left!\"]/android.view.View[3]");
    public static void clickOnIncrese(){
        waitAndClick(INCRESE_QTY);
    }

    private static final By PLACEORDR_BUTTON = By.id("PLACE ORDER");
    public static void clickOnPlaceOrder(){
        waitAndClick(PLACEORDR_BUTTON);
    }
}
