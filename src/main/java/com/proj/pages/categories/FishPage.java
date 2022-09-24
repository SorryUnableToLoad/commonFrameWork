package com.proj.pages.categories;

import org.openqa.selenium.By;

import static com.proj.utils.PageActionsHelper.clickOnElement;

public class FishPage {

    public FishPage() {
    }

    public final By ALL = By.id("All");

    public final void clickOnAll() {
        clickOnElement(ALL);
    }

    public static final By FRESHWATER = By.id("Freshwater");

    public static void clickOnFreshWater() {
        clickOnElement(FRESHWATER);
    }

    public final By SEAWATER = By.id("Seawater");

    public void clickOnSeaWater() {
        clickOnElement(SEAWATER);
    }

    public final By BENGALI = By.id("Bengali");

    public void clickOnBengali() {
        clickOnElement(BENGALI);
    }

    public final By PRAWNS = By.id("Prawns");

    public void clickOnPrawns() {
        clickOnElement(PRAWNS);
    }

    public final By CRABS = By.id("Crabs");

    public void clickOnCrabs() {
        clickOnElement(CRABS);
    }

    public final By PROCESSED = By.id("Processed");

    public void clickOnProcessed() {
        clickOnElement(PROCESSED);
    }

    public final By OTHERS = By.id("Others");

    public void clickOnOthers() {
        clickOnElement(OTHERS);
    }


    public static final By BLUE_CRAB = By.id("Blue crab");

    public static void clickOnBlueCrab() {
        clickOnElement(BLUE_CRAB);
    }

    public static final By ADD_BUTTON = By.xpath("(//android.view.View[@content-desc=\"Add\"])[1]");

    public static void clickOnAdd() {
        clickOnElement(ADD_BUTTON);
    }

    public static final By CROSS_Button = By.xpath("hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View[2]");

    public static void clickOnCrossButton() {
        clickOnElement(ADD_BUTTON);
    }


}
