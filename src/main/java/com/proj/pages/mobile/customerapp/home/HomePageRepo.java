package com.proj.pages.mobile.customerapp.home;

import com.proj.utils.UI_Utils.ActionHelper;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.testng.Assert;

public class HomePageRepo {
    public HomePageRepo() {}
 /*
    AppiumDriver driver;

    public HomePageRepo() {
        driver = (AppiumDriver) DriverManager.getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

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
    //-----------------------------------------------------------//
    //Popular Products section
    //-----------------------------------------------------------//
    @AndroidFindBy(accessibility = "Popular Products ")
    public MobileElement popularProductList;
    //-----------------------------------------------------------//
    //Outstanding section
    //-----------------------------------------------------------//
    @AndroidFindBy(accessibility = "ACCOUNT STATEMENT")
    public MobileElement accountStatementButton;
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
   */

    private static final By HOME_PAGE=MobileBy.xpath("//android.view.View[@content-desc='Hello!']");
    public static void  validateHomepageIsDisplayed(){
        Assert.assertTrue(ActionHelper.verifyIsElementDisplayed(HOME_PAGE),"Home page is not displayed");
    }




}
