package com.proj.utils;

import com.proj.config.FrameworkConfig;
import com.proj.driver.DriverManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CommonMethods {
    public WebDriver driver;
    long timeOutInSeconds = 10;

    public CommonMethods(WebDriver driver) {
        this.driver = DriverManager.getCurrentDriver();
    }

    public void waitForElementToBeClickable(WebElement element) {//Check webElement or mobileElement
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForPresenceOfElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public boolean isElementExists(List<WebElement> elements) {
        return elements.isEmpty();
    }

    //This is time efficient, no List is needed for element
    public boolean isElementExistsNew(WebElement element) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeOutInSeconds));
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds)).until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        } finally {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(FrameworkConfig.waitTime));
        }
    }

    public void clickBackButton() {
        driver.navigate().back();
    }

    public void clickOnElement(WebElement element) {
        waitForElementToBeClickable(element);
        element.click();
    }

    public void enterText(WebElement element, String text) {
        waitForPresenceOfElement(element);
        element.sendKeys(text);
    }

    public boolean verifyIsElementDisplayed(WebElement element) {
        waitForPresenceOfElement(element);
        return element.isDisplayed();
    }

    //Only Android methods
    public void scrollUsingUIAutomatorByTextAndClick(String visibleText) {
        ((AndroidDriver) driver).findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"" + visibleText + "\").instance(0))")).click();
    }

    public void scrollUsingUIAutomatorByText(String visibleText) {
        ((AndroidDriver) driver).findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"" + visibleText + "\").instance(0))"));
    }

    /*public void findElementByUiAutomatorAndCLick(String text) {
        ((AndroidDriver) driver).findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"))").click();
    }*/
}
