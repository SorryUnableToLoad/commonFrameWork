package com.proj.pages.mycart;

import com.proj.driver.DriverManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import static com.proj.utils.ActionHelper.clickOnElement;
import static com.proj.utils.ActionHelper.getAttribute;

public class MyCartPageRepo {
    public static final By my_cart_button = By.xpath("//android.view.View[@content-desc='My cart']");
    public static final By place_order_button = By.xpath("//android.widget.Button[@content-desc='PLACE ORDER']");
    /*@AndroidFindBy(accessibility = "PLACE ORDER")
    public MobileElement place_order;*/
    public static final By add_to_existing_order_button = By.xpath("//android.widget.Button[@content-desc='ADD TO EXISTING ORDER']");
    /* @AndroidFindBy(accessibility = "ADD TO EXISTING ORDER")
     public static MobileElement add_to_existing_order;*/
    public static final By modify_order_button = By.xpath("//android.widget.Button[@content-desc='MODIFY ORDER']");
    /*@AndroidFindBy(accessibility = "MODIFY ORDER")
    public static MobileElement modify_order;*/
    public static final By modify_order_popup = By.xpath("//android.view.View[@content-desc='Are you sure you want to modify the order?']");
    public static final By modify_button = By.xpath("//android.widget.Button[@content-desc='MODIFY']");
    public static final By cancel_button = By.xpath("//android.widget.Button[@content-desc='CANCEL']");
    public static final By order_placed_successfully_page = By.xpath("//android.view.View[@content-desc='Order placed successfully']");
    @AndroidFindBy(accessibility = "HOME")
    public static MobileElement home_button;
    @AndroidFindBy(accessibility = "VIEW ORDERS")
    public static MobileElement view_orders_button;
    /*@AndroidFindBy(xpath = "//android.view.View[@content-desc='Order placed successfully']")
    public static MobileElement Order_placed_successfully;*/
    AppiumDriver driver;

    public MyCartPageRepo() {
        driver = (AppiumDriver) DriverManager.getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void createNewOrder() {
        clickOnElement(my_cart_button);
        clickOnElement(place_order_button);
        String sussesPage = getAttribute(order_placed_successfully_page);
        System.out.println(sussesPage);
        Assert.assertEquals(sussesPage, "Order placed successfully");
        clickOnElement(home_button);
    }

    public void addToExistingOrder() {
        clickOnElement(my_cart_button);
        clickOnElement(add_to_existing_order_button);
        String sussesPage = getAttribute(order_placed_successfully_page);
        System.out.println(sussesPage);
        Assert.assertEquals(sussesPage, "Order placed successfully");
        clickOnElement(home_button);
    }

    public void modifyOrder() {
        clickOnElement(modify_order_button);
        clickOnElement(modify_order_button);
        String popup = getAttribute(modify_order_popup);
        System.out.println(popup);
        Assert.assertEquals(popup, "Are you sure you want to modify the order?");
        clickOnElement(modify_button);
        String sussesPage = getAttribute(order_placed_successfully_page);
        System.out.println(sussesPage);
        Assert.assertEquals(sussesPage, "Order placed successfully");
        clickOnElement(home_button);
    }
}
