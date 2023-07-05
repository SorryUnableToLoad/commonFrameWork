package com.proj.pages.mobile.b2b.initializepages;

import com.proj.pages.mobile.b2b.account.AccountPageRepo;
import com.proj.pages.mobile.b2b.categories.CategoriesPageRepo;
import com.proj.pages.mobile.b2b.home.HomePageRepo;
import com.proj.pages.mobile.b2b.home.SelectStore;
import com.proj.pages.mobile.b2b.loginandlogout.LoginPage;
import com.proj.pages.mobile.b2b.loginandlogout.LogoutPage;
import com.proj.pages.mobile.b2b.mycart.MyCartPageRepo;

/**
 * This class is created for initilize the all pomrepository pages and use in scripts
 */
public class InitializePages {

    public LoginPage loginPage;
    public LogoutPage logoutPage;
    public HomePageRepo homePageRepo;
    public SelectStore selectStore;
    public CategoriesPageRepo categoriesPageRepo;
    public MyCartPageRepo myCartPageRepo;
    public AccountPageRepo accountPageRepo;

    public InitializePages() {
        loginPage = new LoginPage();
        logoutPage = new LogoutPage();
        homePageRepo = new HomePageRepo();
        selectStore = new SelectStore();
        categoriesPageRepo = new CategoriesPageRepo();
        myCartPageRepo = new MyCartPageRepo();
        accountPageRepo = new AccountPageRepo();
    }
}
