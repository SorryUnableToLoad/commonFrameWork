package com.proj.pages.account;

import com.proj.common.driver.DriverManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

public class AccountPageRepo {
    public final By account_button = By.xpath("//android.view.View[@content-desc='Account']");
    public final By view_history_section = By.xpath("//android.view.View[@content-desc='View History']");
    public final By order_history = By.xpath("//android.view.View[@content-desc='Orders']");
    public final By invoice_history = By.xpath("//android.view.View[@content-desc='Invoice']");
    public final By payment_history = By.xpath("//android.view.View[@content-desc='Payment']");
    public final By top_order_card = By.xpath("//android.view.View[@content-desc='Orders']/parent::android.view.View/android.view.View/android.view.View/android.view.View[1]");
    public final By view_details_button = By.xpath("(//android.widget.Button[@content-desc='VIEW DETAILS'])[1]");
    public final By reorder_button = By.xpath("//android.widget.Button[@content-desc='REORDER']");
    public final By modify_button = By.xpath("//android.widget.Button[@content-desc='MODIFY']");
    AppiumDriver driver;

    public AccountPageRepo() {
        driver = (AppiumDriver) DriverManager.getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public MobileElement orderCard(String delivery_date, String total_order_count, String order_id, String status, String order_date, String no_of_items, String total_qty, String total_amount) {

        return (MobileElement) DriverManager.getDriver().findElement(By.xpath("//android.view.View[@content-desc='Delivery Date: '" + delivery_date + "'\n" +
                "Total order '" + total_order_count + "'\n" +
                "Order ID: '" + order_id + "'\n" +
                "'" + status + "'\n" +
                "Order Date\n" +
                "'" + order_date + "'\n" +
                "No. of Items\n" +
                "'" + no_of_items + "' item\n" +
                "Total Quantity\n" +
                "'" + total_qty + "'.0 kgs\n" +
                "Total Amount\n" +
                " ₹ '" + total_amount + "'.00']"));
    }

}
