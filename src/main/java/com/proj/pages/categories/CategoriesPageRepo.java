package com.proj.pages.categories;

import com.proj.common.driver.DriverManager;
import com.proj.utils.UI_Utils.ActionHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class CategoriesPageRepo {
    public static final By categories_button = By.xpath("//android.view.View[@content-desc='Categories']");
    public static final By all_categories_page = By.xpath("//android.view.View[@content-desc='All Categories']");
    public static final By back_arrow_button = By.xpath("//android.view.View[@content-desc='All Categories']/android.widget.Button");
    public static final By search_icon = By.xpath("//android.view.View[@content-desc='All Categories']/android.view.View");
    public static final By categories_list = By.xpath("//android.view.View[@content-desc='All Categories']/following-sibling::android.view.View/android.view.View/android.widget.ImageView");
    public static final By cross_button = By.xpath("//android.view.View[@content-desc='Select item(s) to order']/following-sibling::android.view.View");
    @AndroidFindBy(accessibility = "Fish\n" +
            "Bengali, Freshwater, Seawater, Prawns, Crabs, Squids")
    public static MobileElement fish_category;
    @AndroidFindBy(accessibility = "Mutton\n" +
            "Live Animal, Carcass")
    public static MobileElement mutton_category;
    @AndroidFindBy(accessibility = "Chicken\n" +
            "With Bone, Boneless")
    public static MobileElement chicken_category;
    @AndroidFindBy(accessibility = "Eggs\n" +
            "White Eggs, Brown Eggs")
    public static MobileElement eggs_category;
    @AndroidFindBy(accessibility = "Back Button")
    public static MobileElement backButton;
    AppiumDriver driver;

    public CategoriesPageRepo() {
        driver = (AppiumDriver) DriverManager.getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public static WebElement select_level_1_Catgory(String level_1_category) {
        return DriverManager.getDriver().findElement(By.xpath("//android.widget.ImageView[@content-desc='" + level_1_category + "']"));
    }

    public static MobileElement selectproduct(String product_name) {
        return (MobileElement) DriverManager.getDriver().findElement(By.xpath("//android.widget.ImageView[@content-desc='" + product_name + "']"));
    }

    public static MobileElement select_sku(int index) {
        return (MobileElement) DriverManager.getDriver().findElement(By.xpath("//android.view.View[@content-desc='Select item(s) to order']/parent::android.view.View/android.widget.ScrollView/android.view.View[2]/android.view.View/android.view.View['" + index + "']"));
    }

    public static MobileElement add_sku(int index) {
        //return (MobileElement) DriverManager.getDriver().findElement(By.xpath("(//android.view.View[@content-desc='Add'])['"+number+"']"));
        return (MobileElement) DriverManager.getDriver().findElement(By.xpath("(//android.view.View[@content-desc='Select item(s) to order']/parent::android.view.View/android.widget.ScrollView/android.view.View[2]/android.view.View/android.view.View/android.view.View)['" + index + "']"));
    }

    public void add_items_to_cart() {
        ActionHelper.clickOnElement(categories_button);
        ActionHelper.clickOnElement(fish_category);
        ActionHelper.clickOnElement(selectproduct("Aar (Bengali)"));
        ActionHelper.clickOnElement(add_sku(1));
        ActionHelper.clickOnElement(cross_button);
    }
}
