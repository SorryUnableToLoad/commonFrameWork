package com.proj.utils.UI_Utils;

import com.proj.common.base.MobileSetUp;
import com.proj.common.driver.DriverManager;
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

import static com.proj.common.config.factory.FrameworkConfigFactory.getConfig;


/**
 * This class contains generic methods
 *
 * @author surajkumarnaganuri
 */
public class ActionHelper extends MobileSetUp {

    static AppiumDriver driver = (AppiumDriver) DriverManager.getDriver();
    static Dimension size = DriverManager.getDriver().manage().window().getSize();
    static int center = size.getWidth() * 50 / 100;
    static int startX = size.getWidth() * 90 / 100;
    static int endX = size.getWidth() * 10 / 100;
    static int startY = size.getHeight() * 60 / 100;
    static int endY = size.getHeight() * 10 / 100;

    private ActionHelper() {

    }
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    /*protected void sendKeys(MobileElement element,String value) {
        explicitlyWait(element);
        LogStatus.pass("Screenshot below", TestUtils.pullScreenshotPath());
        element.sendKeys(value);
        LogStatus.pass(value + " is entered in to the "+ element);
    }protected void click(MobileElement element) {
        explicitlyWait(element);
        LogStatus.pass("Screenshot below", TestUtils.pullScreenshotPath());
        element.click();
        LogStatus.pass("Clicking is successfull on "+ element);
    }*/

    /**
     * This method is used to wait and click on the element
     *
     * @param element
     */
    public static void clickOnElement(MobileElement element) {
        waitForElementToBeClickableAndClick(element);
        logger.info(element + " Clicked on element");
    }

    /**
     * This method is used to wait and click on the element
     *
     * @param by
     */
    public static void clickOnElement(By by) {
        waitForElementToBeClickableAndClick(by);
        logger.info(by + " Clicked on element");
    }

    private static void waitForElementToBeClickableAndClick(MobileElement element) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), getConfig().waitTime());
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    private static void waitForElementToBeClickableAndClick(By by) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), getConfig().waitTime());
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    //===================================================================================================================================================================//
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

    /**
     * This method is used for send the text input from user
     *
     * @param element
     * @param text
     */
    public static void waitAndSendKeys(MobileElement element, String text) {
        waitForPresenceOfElement(element);
        element.sendKeys(text);
    }

    /**
     * This method is used for send the text input from user
     *
     * @param by
     * @param text
     */
    public static void waitAndSendKeys(By by, String text) {
        waitForPresenceOfElement(by);
        DriverManager.getDriver().findElement(by).sendKeys(text);
    }

    /**
     * This method is used for get the text from element
     *
     * @param element
     */
    public static String gatText(MobileElement element) {
        waitForPresenceOfElement(element);
        return element.getText();
    }

    /**
     * This method is used for get the text from element
     *
     * @param by
     */
    public static String getText(By by) {
        waitForPresenceOfElement(by);
        return DriverManager.getDriver().findElement(by).getText();
    }

    /**
     * This method is used for get the attribute value from element
     *
     * @param element
     */
    public static String getAttribute(MobileElement element, String attributeName) {
        waitForPresenceOfElement(element);
        String attributeValue = element.getAttribute(attributeName);
        return attributeValue;
    }

    /**
     * This method is used for get the attribute value from element
     *
     * @param by
     */
    public static String getAttribute(By by) {
        waitForPresenceOfElement(by);
        return DriverManager.getDriver().findElement(by).getTagName();
    }

    /**
     * This method is used for get the attribute value from element
     *
     * @param by
     * @param attributeFunction
     * @return
     */
    public static String getAttribute(By by, Function<WebElement, String> attributeFunction) {
        waitForPresenceOfElement(by);
        return attributeFunction.apply(DriverManager.getDriver().findElement(by));
    }

    /**
     * This method is used for get the count of element
     *
     * @param by
     * @return
     */
    public static int getSize(By by) {
        waitForPresenceOfElement(by);
        return DriverManager.getDriver().findElements(by).size();
    }

    /**
     * This method is used to verify the element is displayed or not
     *
     * @param mobileElement
     * @return
     */
    public static boolean verifyIsElementDisplayed(MobileElement mobileElement) {
        waitForPresenceOfElement(mobileElement);
        return mobileElement.isDisplayed();
    }

    /**
     * This method is used to verify the element is displayed or not
     *
     * @param by
     * @return
     */
    public static boolean verifyIsElementDisplayed(By by) {
        waitForPresenceOfElement(by);
        return DriverManager.getDriver().findElement(by).isDisplayed();
    }

    /**
     * This method is used to verify the element is displayed or not
     *
     * @param by
     * @param elementPredicate
     * @return
     */
    public static boolean verifyIsElementPresent(By by, Predicate<WebElement> elementPredicate) {
        waitForPresenceOfElement(by);
        return elementPredicate.test(DriverManager.getDriver().findElement(by));
    }

    /**
     * This method is used to select the element
     *
     * @param by
     * @param consumer
     */
    public static void select(By by, Consumer<Select> consumer) {
        waitForPresenceOfElement(by);
        consumer.accept(new Select(DriverManager.getDriver().findElement(by)));
    }

    /**
     * This method is used to wait for the visibility of element
     *
     * @param mobileElement
     */
    private static void waitForPresenceOfElement(MobileElement mobileElement) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), getConfig().waitTime());
        wait.until(ExpectedConditions.visibilityOf(mobileElement));
    }

    /**
     * This method is used to wait for the presence of element
     *
     * @param by
     */
    private static void waitForPresenceOfElement(By by) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), getConfig().waitTime());
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
    //===================================================================================================================================================================//
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

    /**
     * This method is used for click on android and ios elements
     *
     * @param androidBy
     * @param iosBy
     */
    public static void clickOnElement(By androidBy, By iosBy) {
        //wait strategy
        By byBasedOnMobilePlatform = getByBasedOnMobilePlatform(androidBy, iosBy);
        DriverManager.getDriver().findElement(byBasedOnMobilePlatform).click();
    }

    /**
     * This method is used for scroll the page.
     *
     * @param by
     */
    public static void performScroll(By by) {
        String previousPageSource = "";
        while (isElementNotEnabled(by) && isNotEndOfPage(previousPageSource)) {
            previousPageSource = DriverManager.getDriver().getPageSource();
            scrollForMobile();
            logger.info(by + " Successfully Scroll Did");
        }
    }

    /**
     * This method is used for scroll the page.
     *
     * @param element
     */
    public static void performScroll(WebElement element) {
        String previousPageSource = "";
        while (isElementNotEnabled(element) && isNotEndOfPage(previousPageSource)) {
            previousPageSource = DriverManager.getDriver().getPageSource();
            scrollForMobile();
            logger.info(element + " Successfully Scroll Did");
        }
    }

    /**
     * This method is used for scroll to the specific element and click on it.
     *
     * @param by
     */
    public static void performScrollAndClick(By by) {
        String previousPageSource = "";
        while (isElementNotEnabled(by) && isNotEndOfPage(previousPageSource)) {
            previousPageSource = DriverManager.getDriver().getPageSource();
            scrollForMobile();
            DriverManager.getDriver().findElement(by).click();
            logger.info(by + " Successfully Scrolled And Clicked on element");
        }
    }

    /**
     * This method is used for scrolling to the element and click on it.
     *
     * @param element
     */
    public static void performScrollAndClick(WebElement element) {
        String previousPageSource = "";
        while (isElementNotEnabled(element) && isNotEndOfPage(previousPageSource)) {
            previousPageSource = DriverManager.getDriver().getPageSource();
            scrollForMobile();
            element.click();
            logger.info(element + " Successfully Scrolled And Clicked on element");
        }
    }

    /**
     * This method is used for scroll to the specific mobile element and click on it.
     *
     * @param by
     */
    public static void scrollToSpecificElementAndClick(By by) {
        while (DriverManager.getDriver().findElements(by).isEmpty()) {
            performScrollBottomToTop();
        }
        if (!DriverManager.getDriver().findElements(by).isEmpty()) {
            DriverManager.getDriver().findElement(by).click();
            logger.info(by + " Successfully Scrolled And Clicked on element");
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
            return elements.get(0).getAttribute("scrollable").equalsIgnoreCase("false");
        }
        return true;
    }

    public static void scrollForMobile() {
        if (isAndroid()) performScrollForAndroid();
        else performScrollForIOS();
    }

    private static void performScrollForAndroid() {
        new AndroidTouchAction((PerformsTouchActions) DriverManager.getDriver()).press(PointOption.point(center, startY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).moveTo(PointOption.point(center, endY)).release().perform();
    }

    private static void performScrollForIOS() {
        new IOSTouchAction((PerformsTouchActions) DriverManager.getDriver()).press(PointOption.point(center, startY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).moveTo(PointOption.point(center, endY)).release().perform();
    }
    //===================================================================================================================================================================//
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

    /**
     * This method is performed bottom to top swipe action
     */
    public static void performScrollBottomToTop() {
        new TouchAction((PerformsTouchActions) DriverManager.getDriver()).press(PointOption.point(center, startY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).moveTo(PointOption.point(center, endY)).release().perform();
    }

    /**
     * This method is performed bottom to top swipe action
     * based on int argument i.e number of times
     */
    public static void performScrollBottomToTop(int Times) {
        for (int initTime = 0; initTime < Times; initTime++) {
            new TouchAction((PerformsTouchActions) DriverManager.getDriver()).press(PointOption.point(center, startY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).moveTo(PointOption.point(center, endY)).release().perform();
        }
    }

    /**
     * This method is performed top to bottom swipe action
     */
    public static void performScrollTopToBottom() {
        new TouchAction((PerformsTouchActions) DriverManager.getDriver()).press(PointOption.point(center, endY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).moveTo(PointOption.point(center, startY)).release().perform();
    }

    /**
     * This method is performed top to bottom swipe action
     * based on int argument i.e number of times
     */
    public static void performScrollTopToBottom(int Times) {
        for (int initTime = 0; initTime < Times; initTime++) {
            new TouchAction((PerformsTouchActions) DriverManager.getDriver()).press(PointOption.point(center, endY)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).moveTo(PointOption.point(center, startY)).release().perform();
        }
    }

    /**
     * This method is performed left to right swipe action
     */
    public static void performScrollLeftToRight() {
        new TouchAction((PerformsTouchActions) DriverManager.getDriver()).press(PointOption.point(endX, center)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).moveTo(PointOption.point(startX, center)).release().perform();
    }

    /**
     * This method is performed left to right swipe action
     * based on int argument i.e number of times
     */
    public static void performScrollLeftToRight(int Times) {
        for (int initTime = 0; initTime < Times; initTime++) {
            new TouchAction((PerformsTouchActions) DriverManager.getDriver()).press(PointOption.point(endX, center)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).moveTo(PointOption.point(startX, center)).release().perform();
        }
    }

    /**
     * This method is performed right to left swipe action
     */
    public static void performScrollRightToLeft() {
        new TouchAction((PerformsTouchActions) DriverManager.getDriver()).press(PointOption.point(startX, center)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).moveTo(PointOption.point(endX, center)).release().perform();
    }

    /**
     * This method is performed right to left swipe action
     * based on int argument i.e number of times
     */
    public static void performScrollRightToLeft(int Times) {
        new TouchAction((PerformsTouchActions) DriverManager.getDriver()).press(PointOption.point(startX, center)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).moveTo(PointOption.point(endX, center)).release().perform();
    }

    /**
     * This method is used for tap on element
     *
     * @param mobileElement
     */
    public static void tapOnElement(MobileElement mobileElement) {
        new TouchAction((PerformsTouchActions) DriverManager.getDriver()).tap(TapOptions.tapOptions().withElement(ElementOption.element(mobileElement))).perform();
    }

    /**
     * This method is used for tap on element
     * by passing argument as x and y coordinates
     *
     * @param x
     * @param y
     */
    public static void tapOnElementByCoordinates(int x, int y) {
        new TouchAction((PerformsTouchActions) DriverManager.getDriver()).tap(PointOption.point(x, y)).perform();
    }

    /**
     * This method is used for long-press on element
     *
     * @param mobileElement
     * @param milliSeconds
     */
    public void longPress(MobileElement mobileElement, int milliSeconds) {
        new TouchAction((PerformsTouchActions) DriverManager.getDriver()).longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(mobileElement))).waitAction(WaitOptions.waitOptions(Duration.ofMillis(milliSeconds))).perform();
    }

    /**
     * This method is used to drag and drop the element
     * by passing the source element and the target element
     *
     * @param sourceElement
     * @param targetElement
     */
    public static void dragAndDrop(MobileElement sourceElement, MobileElement targetElement) {
        new TouchAction((PerformsTouchActions) DriverManager.getDriver()).tap(TapOptions.tapOptions().withElement(ElementOption.element(sourceElement))).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500))).moveTo(ElementOption.element(targetElement)).release().perform();
    }
    //=================================================================================================================================================================//

    public static WebElement chooseAProduct(String productName) {
        return DriverManager.getDriver().findElement(By.xpath("//android.widget.ImageView[@content-desc='" + productName + "']"));
    }
}