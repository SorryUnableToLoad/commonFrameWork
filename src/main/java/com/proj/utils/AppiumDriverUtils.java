package com.proj.utils;

import com.proj.driver.DriverManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.ios.IOSTouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.proj.config.factory.FrameworkConfigFactory.getConfig;

/**
 * This class contains generic methods
 *
 * @author surajkumarnaganuri
 */
public class AppiumDriverUtils {
    private AppiumDriverUtils() {
    }

    static AppiumDriver driver = (AppiumDriver) DriverManager.getDriver();
    static Dimension size = DriverManager.getDriver().manage().window().getSize();
    static int center = size.getHeight() / 2;
    static int endY = (int) (size.getHeight() * 0.25);
    static int startX = size.getWidth() * 10 / 100;
    static int endX = size.getWidth() * 90 / 100;

    /**
     * This method is performed bottom to top swipe action
     */
    public static void performScrollBottomToTop() {
        new AndroidTouchAction((PerformsTouchActions) DriverManager.getDriver()).press(PointOption.point(center, center)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(200))).moveTo(PointOption.point(center, endY)).release().perform();
    }

    /**
     * This method is performed bottom to top swipe action based on int argument i.e number of times
     */
    public static void swipeFromBottomToTop(int Times) {
        for (int initTime = 0; initTime < Times; initTime++) {
            new AndroidTouchAction((PerformsTouchActions) DriverManager.getDriver()).press(PointOption.point(center, center)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(200))).moveTo(PointOption.point(center, endY)).release().perform();
        }
    }

    /**
     * This method is performed top to bottom swipe action
     */
    public static void performScrollTopToBottom() {
        new AndroidTouchAction((PerformsTouchActions) DriverManager.getDriver()).press(PointOption.point(center, endY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(200))).moveTo(PointOption.point(center, center)).release().perform();
    }

    /**
     * This method is performed top to bottom swipe action based on int argument i.e number of times
     */
    public static void performScrollTopToBottom(int Times) {
        for (int initTime = 0; initTime < Times; initTime++) {
            new AndroidTouchAction((PerformsTouchActions) DriverManager.getDriver()).press(PointOption.point(center, endY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(200))).moveTo(PointOption.point(center, center)).release().perform();
        }
    }

    /**
     * This method is performed left to right swipe action
     */
    public static void performScrollLeftToRight() {
        new AndroidTouchAction((PerformsTouchActions) DriverManager.getDriver()).press(PointOption.point(center, startX)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(200))).moveTo(PointOption.point(center, center)).release().perform();
    }

    /**
     * This method is performed left to right swipe action based on int argument i.e number of times
     */
    public static void performScrollLeftToRight(int Times) {
        for (int initTime = 0; initTime < Times; initTime++) {
            new AndroidTouchAction((PerformsTouchActions) DriverManager.getDriver()).press(PointOption.point(center, startX)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(200))).moveTo(PointOption.point(center, center)).release().perform();
        }
    }

    /**
     * This method is performed right to left swipe action
     */
    public static void performScrollRightToLeft() {
        new AndroidTouchAction((PerformsTouchActions) DriverManager.getDriver()).press(PointOption.point(center, endX)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(200))).moveTo(PointOption.point(center, center)).release().perform();
    }

    /**
     * This method is performed right to left swipe action based on int argument i.e number of times
     */
    public static void swipeFromRightToLeft(int Times) {
        new AndroidTouchAction((PerformsTouchActions) DriverManager.getDriver()).press(PointOption.point(center, endX)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(200))).moveTo(PointOption.point(center, center)).release().perform();
    }

    /**
     * This method is used for scroll to the specific mobile element
     *
     * @param by
     */
    public static void scrollToSpecificElementAndClick(By by) {
        while (DriverManager.getDriver().findElements(by).isEmpty()) {
            performScrollBottomToTop();
        }
        if (!DriverManager.getDriver().findElements(by).isEmpty()) {
            DriverManager.getDriver().findElement(by).click();
        }
    }

    /**
     * This method is used for tap on element
     * by passing argument as mobileElement
     *
     * @param mobileElement
     */
    public static void tapOnElement(MobileElement mobileElement) {
        new TouchAction((PerformsTouchActions) DriverManager.getDriver()).tap(TapOptions.tapOptions().withElement(ElementOption.element(mobileElement))).perform();
    }

    public static void tapOnElementByCoordinates(int x, int y) {
        new TouchAction((PerformsTouchActions) DriverManager.getDriver()).tap(PointOption.point(x, y)).perform();
    }

    public void longPress(MobileElement mobileElement, int milliSeconds) {
        new TouchAction((PerformsTouchActions) DriverManager.getDriver()).longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(mobileElement))).waitAction(WaitOptions.waitOptions(Duration.ofMillis(milliSeconds))).perform();
    }

    public static void dragAndDrop(MobileElement sourceElement, MobileElement targetElement) {
        new TouchAction((PerformsTouchActions) DriverManager.getDriver()).tap(TapOptions.tapOptions().withElement(ElementOption.element(sourceElement))).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).moveTo(ElementOption.element(targetElement)).release().perform();
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    public static void waitAndSendKeys(MobileElement mobileElement, String text) {
        waitForPresenceOfElement(mobileElement);
        mobileElement.sendKeys(text);
    }

    public static void waitAndSendKeys(By by, String value) {
        waitForPresenceOfElement(by);
        DriverManager.getDriver().findElement(by).sendKeys(value);
    }

    public static String gatText(MobileElement mobileElement) {
        waitForPresenceOfElement(mobileElement);
        return mobileElement.getText();
    }

    public static String getText(By by) {
        waitForPresenceOfElement(by);
        return DriverManager.getDriver().findElement(by).getText();
    }

    public static String getAttribute(MobileElement mobileElement, String attributeName) {
        waitForPresenceOfElement(mobileElement);
        String attributeValue = mobileElement.getAttribute(attributeName);
        return attributeValue;
    }

    public static String getAttribute(By by) {
        waitForPresenceOfElement(by);
        return DriverManager.getDriver().findElement(by).getTagName();
    }

    public static String getAttribute(By by, Function<WebElement, String> attributeFunction) {
        waitForPresenceOfElement(by);
        return attributeFunction.apply(DriverManager.getDriver().findElement(by));
    }

    public static int getSize(By by) {
        waitForPresenceOfElement(by);
        return DriverManager.getDriver().findElements(by).size();
    }

    public static boolean verifyIsElementDisplayed(MobileElement mobileElement) {
        waitForPresenceOfElement(mobileElement);
        return mobileElement.isDisplayed();
    }

    public static boolean verifyIsElementDisplayed(By by) {
        waitForPresenceOfElement(by);
        return DriverManager.getDriver().findElement(by).isDisplayed();
    }

    public static boolean verifyIsElementPresent(By by, Predicate<WebElement> elementPredicate) {
        waitForPresenceOfElement(by);
        return elementPredicate.test(DriverManager.getDriver().findElement(by));
    }

    public static void select(By by, Consumer<Select> consumer) {
        waitForPresenceOfElement(by);
        consumer.accept(new Select(DriverManager.getDriver().findElement(by)));
    }

    public static void waitForPresenceOfElement(MobileElement mobileElement) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), getConfig().waitTime());
        wait.until(ExpectedConditions.visibilityOf(mobileElement));
    }

    public static void waitForPresenceOfElement(By by) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), getConfig().waitTime());
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    public static void clickOnElement(MobileElement element) {
        waitForElementToBeClickableAndClick(element);
    }

    public static void clickOnElement(By by) {
        waitForElementToBeClickableAndClick(by);
    }

    private static void waitForElementToBeClickableAndClick(MobileElement element) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), getConfig().waitTime());
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    private static void waitForElementToBeClickableAndClick(By by) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), getConfig().waitTime());
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

    public static void clickOnElement(By androidBy, By iosBy) {
        //wait strategy
        By byBasedOnMobilePlatform = getByBasedOnMobilePlatform(androidBy, iosBy);
        DriverManager.getDriver().findElement(byBasedOnMobilePlatform).click();
    }

    public static void performScroll(By by) {
        String previousPageSource = "";
        while (isElementNotEnabled(by) && isNotEndOfPage(previousPageSource)) {
            previousPageSource = DriverManager.getDriver().getPageSource();
            scrollForMobile();
        }
    }

    public static void performScroll(WebElement element) {
        String previousPageSource = "";
        while (isElementNotEnabled(element) && isNotEndOfPage(previousPageSource)) {
            previousPageSource = DriverManager.getDriver().getPageSource();
            scrollForMobile();
        }
    }

    private static boolean isAndroid() {
        return DriverManager.getDriver() instanceof AndroidDriver;
    }

    private static By getByBasedOnMobilePlatform(By androidBy, By iosBy) {
        return isAndroid() ? androidBy : iosBy;
    }

    private static boolean isNotEndOfPage(String previousPageSource) {
        return !previousPageSource.equals(DriverManager.getDriver().getPageSource());
    }

    private static boolean isElementNotEnabled(WebElement element) {
        try {
            return !element.isDisplayed();
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    private static boolean isElementNotEnabled(By by) {
        List<WebElement> elements = DriverManager.getDriver().findElements(by);
        if (isAndroid()) {
            return elements.isEmpty();
        }
        if (!elements.isEmpty()) {
            return elements.get(0).getAttribute("visible").equalsIgnoreCase("false");
        }
        return true;
    }

    private static void scrollForMobile() {
        Dimension size = DriverManager.getDriver().manage().window().getSize();
        int startX = size.getWidth() / 2;
        int endX = size.getWidth() / 2;
        int startY = size.getHeight() / 2;
        int endY = (int) (size.getHeight() * 0.25);

        if (isAndroid()) performScrollForAndroid(startX, startY, endX, endY);
        else performScrollForIOS(startX, startY, endX, endY);
    }

    private static void performScrollForAndroid(int startX, int startY, int endX, int endY) {
        new AndroidTouchAction((PerformsTouchActions) DriverManager.getDriver()).press(PointOption.point(startX, startY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(200))).moveTo(PointOption.point(endX, endY)).release().perform();
    }

    private static void performScrollForIOS(int startX, int startY, int endX, int endY) {
        new IOSTouchAction((PerformsTouchActions) DriverManager.getDriver()).press(PointOption.point(startX, startY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(200))).moveTo(PointOption.point(endX, endY)).release().perform();
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

    //====================================================================================================//


    //====================================================================================================//
    public static WebElement chooseAProduct(String productName) {
        return DriverManager.getDriver().findElement(By.xpath("//android.widget.ImageView[@content-desc='" + productName + "']"));
    }
}
