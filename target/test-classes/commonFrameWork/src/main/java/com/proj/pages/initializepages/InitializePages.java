package com.proj.pages.initializepages;

import com.proj.pages.account.AccountPageRepo;
import com.proj.pages.categories.CategoriesPageRepo;
import com.proj.pages.home.HomePageRepo;
import com.proj.pages.home.SelectStore;
import com.proj.pages.loginandlogout.LoginPage;
import com.proj.pages.loginandlogout.LogoutPage;
import com.proj.pages.mycart.MyCartPageRepo;

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
