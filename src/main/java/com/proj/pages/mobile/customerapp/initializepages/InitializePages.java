package com.proj.pages.mobile.customerapp.initializepages;

import com.proj.pages.mobile.customerapp.SelectShop.SelectShop;
import com.proj.pages.mobile.customerapp.account.AccountPageRepo;
import com.proj.pages.mobile.customerapp.categories.CategoriesPageRepo;
import com.proj.pages.mobile.customerapp.home.HomePageRepo;
import com.proj.pages.mobile.customerapp.loginandlogout.LoginPage;
import com.proj.pages.mobile.customerapp.loginandlogout.LogoutPage;
import com.proj.pages.mobile.customerapp.mycart.MyCartPageRepo;

/**
 * This class created for initialize the all repository pages and uses in test scripts.
 */
public class InitializePages {

    public LoginPage loginPage;
    public LogoutPage logoutPage;
    public HomePageRepo homePageRepo;
    public SelectShop selectShop;
    public CategoriesPageRepo categoriesPageRepo;
    public MyCartPageRepo myCartPageRepo;
    public AccountPageRepo accountPageRepo;

    public InitializePages() {
        loginPage = new LoginPage();
        logoutPage = new LogoutPage();
        homePageRepo = new HomePageRepo();
        selectShop = new SelectShop();
        categoriesPageRepo = new CategoriesPageRepo();
        myCartPageRepo = new MyCartPageRepo();
        accountPageRepo = new AccountPageRepo();
    }
}
