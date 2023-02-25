package com.proj.utils.etc;

import com.proj.common.driver.DriverManager;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CommonMethods {
    AndroidDriver driver = (AndroidDriver) DriverManager.getDriver();
    long timeOutInSeconds = 10;

    public boolean isElementExists(List<WebElement> elements) {
        return elements.isEmpty();
    }

    //This is time efficient, no List is needed for element
    public boolean isElementExistsNew(WebElement element) {
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeOutInSeconds));
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        try {
            //new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds)).until(ExpectedConditions.visibilityOf(element));
            new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.visibilityOf(element));

            return true;
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        } finally {
            // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(getConfig().waitTime()));
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        }
    }

    public void clickBackButton() {
        driver.navigate().back();
    }

    public void scrollByUiAutomator(String textContains, String part_text) {
        //driver.findElementByAndroidUIAutomator("new UiSelector(new UiSelector().scrollIntoView(AN(\"AV\"))");
        //driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().textContains(\"part_text\"))");

    }

    //Only Android methods
    public void scrollUsingUIAutomatorByTextAndClick(String visibleText) {
        ((AndroidDriver) driver).findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"" + visibleText + "\").instance(0))")).click();
    }

    public void scrollUsingUIAutomatorByText(String visibleText) {
        ((AndroidDriver) driver).findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"" + visibleText + "\").instance(0))"));
    }


    public void findElementByUiAutomatorAndCLick(String text) {
        ((AndroidDriver) driver).findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"))").click();
    }
}
