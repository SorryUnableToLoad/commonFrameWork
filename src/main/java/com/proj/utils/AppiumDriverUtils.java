package com.proj.utils;

import com.proj.base.MobileBaseClass;
import com.proj.config.FrameworkConfig;
import com.proj.driver.DriverManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * This class contains generic methods
 *
 * @author surajkumarnaganuri
 */
public class AppiumDriverUtils {
    AndroidDriver driver;

    TouchAction touchAction = new TouchAction(driver);

    public AppiumDriverUtils() {
        this.driver = driver;
    }

    Dimension dimension = driver.manage().window().getSize();
    int screenHeight = dimension.getHeight();
    int screenWidth = dimension.getWidth();

    int centerPoint = screenWidth * 50 / 100;
    int horizontalStartPoint = screenWidth * 90 / 100;
    int horizontalEndPoint = screenWidth * 10 / 100;
    int verticalStartPoint = screenHeight * 60 / 100;
    int verticalEndPoint = screenHeight * 10 / 100;

    /**
     * This method is perform left to right swipe action
     */
    public void swipeFromLeftToRight() {
        touchAction.press(PointOption.point(horizontalEndPoint, centerPoint))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(horizontalStartPoint, centerPoint))
                .release().perform();
    }

    /**
     * This method is perform left to right swipe action based on int argument i.e number of times
     */
    public void swipeFromLeftToRight(int Times) {
        for (int initTime = 0; initTime < Times; initTime++) {
            touchAction.press(PointOption.point(horizontalEndPoint, centerPoint))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                    .moveTo(PointOption.point(horizontalStartPoint, centerPoint))
                    .release().perform();
        }
    }

    /**
     * This method is perform right to left swipe action
     */
    public void swipeFromRightToLeft() {
        touchAction.press(PointOption.point(horizontalStartPoint, centerPoint))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(horizontalEndPoint, centerPoint))
                .release().perform();
    }

    /**
     * This method is perform right to left swipe action based on int argument i.e number of times
     */
    public void swipeFromRightToLeft(int Times) {
        for (int initTime = 0; initTime < Times; initTime++) {
            touchAction.press(PointOption.point(horizontalStartPoint, centerPoint))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                    .moveTo(PointOption.point(horizontalEndPoint, centerPoint))
                    .release().perform();
        }
    }

    /**
     * This method is perform bottom to top swipe action
     */
    public void swipeFromBottomToTop() {
        touchAction.press(PointOption.point(centerPoint, verticalStartPoint))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(centerPoint, verticalEndPoint))
                .release().perform();
    }

    /**
     * This method is perform bottom to top swipe action based on int argument i.e number of times
     */
    public void swipeFromBottomToTop(int Times) {
        for (int initTime = 0; initTime < Times; initTime++) {
            touchAction.press(PointOption.point(centerPoint, verticalStartPoint))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                    .moveTo(PointOption.point(centerPoint, verticalEndPoint))
                    .release().perform();
        }
    }

    /**
     * This method is perform top to bottom swipe action
     */
    public void swipeFromTopToBottom() {
        touchAction.press(PointOption.point(centerPoint, verticalEndPoint))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(centerPoint, verticalStartPoint))
                .release().perform();
    }

    /**
     * This method is perform top to bottom swipe action based on int argument i.e number of times
     */
    public void swipeFromTopToBottom(int Times) {
        for (int initTime = 0; initTime < Times; initTime++) {
            touchAction.press(PointOption.point(centerPoint, verticalEndPoint))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                    .moveTo(PointOption.point(centerPoint, verticalStartPoint))
                    .release().perform();
        }
    }

    /**
     * This method is uesd for scroll to the specific mobile element
     *
     * @param by
     */
    public void scrollToSpecificElement(By by) {
        while (driver.findElements(by).isEmpty()) {
            touchAction.press(PointOption.point(centerPoint, verticalStartPoint))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                    .moveTo(PointOption.point(centerPoint, verticalEndPoint))
                    .release().perform();
        }
        if (!driver.findElements(by).isEmpty()) {
            driver.findElement(by).click();
        }
    }

    /**
     * This method is uesd for scroll to the specific mobile element
     *
     * @param textContains
     * @param part_text
     */
    public void scrollByUiAutomator(String textContains, String part_text) {
        //driver.findElementByAndroidUIAutomator("new UiSelector(new UiSelector().scrollIntoView(AN(\"AV\"))");
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().textContains(\"part_text\"))");

    }

    /**
     * This method is used for tap on element
     * by passing argument as Mobileelement
     *
     * @param mobileElement
     */
    public void tapOnElement(WebElement mobileElement) {
        touchAction.tap(TapOptions.tapOptions()
                        .withElement(ElementOption.element(mobileElement)))
                .perform();
    }

    public void tapOnElementByCoordinates(int x, int y) {
        touchAction.tap(PointOption.point(x, y)).perform();
    }

    public void longPress(WebElement mobileElement, int seconds) {
        touchAction.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(mobileElement)))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(seconds)))
                .perform();
    }

    public void dragAndDrop(WebElement sourceElement, WebElement targetElement) {
        touchAction.tap(TapOptions.tapOptions().withElement(ElementOption.element(sourceElement)))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(ElementOption.element(targetElement))
                .release().perform();
    }

    public void waitForTheElement(WebElement mobileElement) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(FrameworkConfig.waitTime));
        wait.until(ExpectedConditions.elementToBeClickable(mobileElement));
    }
}
