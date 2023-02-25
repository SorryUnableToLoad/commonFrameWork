package com.proj.pages.home;

import com.proj.common.driver.DriverManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePageRepo {
    //bottom navigate bar elements
    //-----------------------------------------------------------//
    @AndroidFindBy(accessibility = "Home")
    public MobileElement homePageButton;
    @AndroidFindBy(accessibility = "Categories")
    public MobileElement categoriesPagebutton;
    @AndroidFindBy(accessibility = "My cart")
    public MobileElement myCartPageButtton;
    @AndroidFindBy(accessibility = "Account")
    public MobileElement accountPageButtton;
    @AndroidFindBy(accessibility = "Popular Products ")
    public MobileElement popularProductList;
    //Outstanding section
    //-----------------------------------------------------------//
    @AndroidFindBy(accessibility = "ACCOUNT STATEMENT")
    public MobileElement accountStateementButton;


    //-----------------------------------------------------------//

    //Popular Products section
    //-----------------------------------------------------------//
    @AndroidFindBy(accessibility = "PAY")
    public MobileElement payButton;


    //-----------------------------------------------------------//
    //recent Orders section
    //-----------------------------------------------------------//
    @AndroidFindBy(className = "android.view.View")
    public MobileElement recentOrderCards;
    @AndroidFindBy(xpath = "(//android.view.View[@content-desc=\"View All \"])[2]")
    public MobileElement viewAllButtonOfRecentOrders;
    //-----------------------------------------------------------//
    @AndroidFindBy(className = "android.view.View")
    public List<MobileElement> l1category;
    AppiumDriver driver;
    //-----------------------------------------------------------//

    public HomePageRepo() {
        driver = (AppiumDriver) DriverManager.getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }


}
