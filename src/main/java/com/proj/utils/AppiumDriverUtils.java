package com.proj.utils;

import com.proj.driver.DriverManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.function.Consumer;

import static com.proj.config.FrameworkConfigFactory.getConfig;

/**
 * This class contains generic methods
 *
 * @author surajkumarnaganuri
 */
public class AppiumDriverUtils {
    private AppiumDriverUtils() {
    }

    static AppiumDriver driver = (AppiumDriver) DriverManager.getDriver();
    static TouchAction touchAction = new TouchAction((PerformsTouchActions) DriverManager.getDriver());
    static Dimension dimension = DriverManager.getDriver().manage().window().getSize();
    static int screenHeight = dimension.getHeight();
    static int screenWidth = dimension.getWidth();

    static int centerPoint = screenWidth * 50 / 100;
    static int horizontalStartPoint = screenWidth * 90 / 100;
    static int horizontalEndPoint = screenWidth * 10 / 100;
    static int verticalStartPoint = screenHeight * 60 / 100;
    static int verticalEndPoint = screenHeight * 10 / 100;

    /**
     * This method is perform left to right swipe action
     */
    public static void swipeFromLeftToRight() {
        touchAction.press(PointOption.point(horizontalEndPoint, centerPoint))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(horizontalStartPoint, centerPoint))
                .release().perform();
    }

    /**
     * This method is perform left to right swipe action based on int argument i.e number of times
     */
    public static void swipeFromLeftToRight(int Times) {
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
    public static void swipeFromRightToLeft() {
        touchAction.press(PointOption.point(horizontalStartPoint, centerPoint))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(horizontalEndPoint, centerPoint))
                .release().perform();
    }

    /**
     * This method is perform right to left swipe action based on int argument i.e number of times
     */
    public static void swipeFromRightToLeft(int Times) {
        for (int initTime = 0; initTime < Times; initTime++) {
            touchAction.press(PointOption.point(horizontalStartPoint, centerPoint))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                    .moveTo(PointOption.point(horizontalEndPoint, centerPoint))
                    .release().perform();
        }
    }

    /**
     * This method is perform top to bottom swipe action
     */
    public static void swipeFromTopToBottom() {
        touchAction.press(PointOption.point(centerPoint, verticalEndPoint))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(centerPoint, verticalStartPoint))
                .release().perform();
    }

    /**
     * This method is perform top to bottom swipe action based on int argument i.e number of times
     */
    public static void swipeFromTopToBottom(int Times) {
        for (int initTime = 0; initTime < Times; initTime++) {
            touchAction.press(PointOption.point(centerPoint, verticalEndPoint))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                    .moveTo(PointOption.point(centerPoint, verticalStartPoint))
                    .release().perform();
        }
    }/**
     * This method is perform bottom to top swipe action
     */
    public static void swipeFromBottomToTop() {
        touchAction.press(PointOption.point(centerPoint, verticalStartPoint))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(centerPoint, verticalEndPoint))
                .release().perform();
    }

    /**
     * This method is perform bottom to top swipe action based on int argument i.e number of times
     */
    public static void swipeFromBottomToTop(int Times) {
        for (int initTime = 0; initTime < Times; initTime++) {
            touchAction.press(PointOption.point(centerPoint, verticalStartPoint))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                    .moveTo(PointOption.point(centerPoint, verticalEndPoint))
                    .release().perform();
        }
    }


    /**
     * This method is uesd for scroll to the specific mobile element
     *
     * @param by
     */
    public static void scrollToSpecificElementAndClick(By by) {
        while (driver.findElements(by).isEmpty()) {
            swipeFromBottomToTop();
        }
        if (!driver.findElements(by).isEmpty()) {
            driver.findElement(by).click();
        }
    }

    /**
     * This method is used for tap on element
     * by passing argument as Mobileelement
     *
     * @param mobileElement
     */
    public static void tapOnElement(MobileElement mobileElement) {
        touchAction.tap(TapOptions.tapOptions()
                        .withElement(ElementOption.element(mobileElement)))
                .perform();
    }

    public static void tapOnElementByCoordinates(int x, int y) {
        touchAction.tap(PointOption.point(x, y)).perform();
    }

    public void longPress(MobileElement mobileElement, int seconds) {
        touchAction.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(mobileElement)))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(seconds)))
                .perform();
    }

    public static void dragAndDrop(MobileElement sourceElement, MobileElement targetElement) {
        touchAction.tap(TapOptions.tapOptions().withElement(ElementOption.element(sourceElement)))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(ElementOption.element(targetElement))
                .release().perform();
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    public static void enterText(MobileElement mobileElement, String text) {
        waitForPresenceOfElement(mobileElement);
        mobileElement.sendKeys(text);
    }

    public static String gatText(MobileElement mobileElement) {
        waitForPresenceOfElement(mobileElement);
        return mobileElement.getText();
    }

    public static String getAttribute(MobileElement mobileElement, String attributeName) {
        waitForPresenceOfElement(mobileElement);
        String attributeValue = mobileElement.getAttribute(attributeName);
        return attributeValue;
    }

    public static boolean verifyIsElementDisplayed(MobileElement mobileElement) {
        waitForPresenceOfElement(mobileElement);
        return mobileElement.isDisplayed();
    }

    public static void waitForPresenceOfElement(MobileElement mobileElement) {
        WebDriverWait wait = new WebDriverWait(driver, getConfig().waitTime());
        wait.until(ExpectedConditions.visibilityOf(mobileElement));
    }

    public static void enterText(By byLocator, String value) {
        driver.findElement(byLocator).sendKeys(value);
    }

    public static String getText(By byLocator) {
        return driver.findElement(byLocator).getText();
    }

    public static String getAttribute(By byLocator) {
        return driver.findElement(byLocator).getTagName();
    }

    public static boolean verifyIsElementDisplayed(By bylocator) {
        return driver.findElement(bylocator).isDisplayed();
    }

    public static void waitForPresenceOfElement(By by) {
        WebDriverWait wait = new WebDriverWait(driver, getConfig().waitTime());
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    public static void clickOnElement(MobileElement element) {
        waitForElementToBeClickableAndCLick(element);
    }

    public static void waitForElementToBeClickableAndCLick(MobileElement element) {
        WebDriverWait wait = new WebDriverWait(driver, getConfig().waitTime());
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public static void clickOnElement(By byLocator) {
        waitForElementToBeClickableAndCLick(byLocator);
    }

    public static void waitForElementToBeClickableAndCLick(By byLocator) {
        WebDriverWait wait = new WebDriverWait(driver, getConfig().waitTime());
        wait.until(ExpectedConditions.elementToBeClickable(byLocator)).click();
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    public static void select(By byLocator, Consumer<Select> consumer) {
        consumer.accept(new Select(driver.findElement(byLocator)));
    }

    //====================================================================================================//
    public static int getSize(By byLocator) {
        return driver.findElements(byLocator).size();
    }

    //====================================================================================================//
    public static WebElement chooseAProduct(String productName) {
        return driver.findElement(By.xpath("//android.widget.ImageView[@content-desc='" + productName + "']"));
    }

}
