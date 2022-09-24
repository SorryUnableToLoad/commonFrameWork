package com.proj.utils;

import com.proj.config.FrameworkConfig;
import com.proj.driver.DriverManager;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Consumer;

public class PageActionsHelper {

    private PageActionsHelper() {
    }

    public static void clickOnElement(By bylocator) {
        DriverManager.getCurrentDriver().findElement(bylocator).click();
    }
    //====================================================================================================//
    public static void waitAndClick(By bylocator) {
        explicitlyWaitForClick(bylocator);
    }
    public static void explicitlyWaitForClick(By bylocator) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getCurrentDriver(), Duration.ofSeconds(FrameworkConfig.waitTime));
        wait.until(ExpectedConditions.presenceOfElementLocated(bylocator)).click();
    }
    //====================================================================================================//
    public static void waitForElement(By bylocator) {
        explicitlyWaitForElement(bylocator);
    }
    public static void explicitlyWaitForElement(By bylocator) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getCurrentDriver(), Duration.ofSeconds(FrameworkConfig.waitTime));
        wait.until(ExpectedConditions.presenceOfElementLocated(bylocator));
    }
    public static void waitAndClick(MobileElement element) {
        explicitlyWait(element);
        element.click();
    }
    private static void explicitlyWait(MobileElement textview_views) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getCurrentDriver(), Duration.ofSeconds(FrameworkConfig.waitTime));
        wait.until(ExpectedConditions.visibilityOf(textview_views));
    }

    //====================================================================================================//
    public static void sendValue(By bylocator, String value) {
        DriverManager.getCurrentDriver().findElement(bylocator).sendKeys(value);
    }
    public static String getValue(By bylocator){
        return DriverManager.getCurrentDriver().findElement(bylocator).getText();
    }
    //====================================================================================================//
    public static void select(By bylocator, Consumer<Select> consumer) {
        consumer.accept(new Select(DriverManager.getCurrentDriver().findElement(bylocator)));
    }
    //====================================================================================================//
    public static int getSize(By bylocator){
        return DriverManager.getCurrentDriver().findElements(bylocator).size();
    }
    //====================================================================================================//
    public static WebElement chooseAProduct(String productName){
        return DriverManager.getCurrentDriver().findElement(By.xpath("//android.widget.ImageView[@content-desc='" + productName + "']"));
    }
    //====================================================================================================//

    //====================================================================================================//

    //====================================================================================================//

    //====================================================================================================//


}
