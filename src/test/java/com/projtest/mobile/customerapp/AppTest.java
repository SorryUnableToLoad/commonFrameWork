package com.projtest.mobile.customerapp;

import com.proj.common.base.MobileSetUp;
import com.proj.pages.mobile.customerapp.initializepages.InitializePages;
import org.testng.annotations.Test;

public class AppTest extends MobileSetUp {
    @Test(priority = 0)
    public void createNewOrder() {
        InitializePages pages = new InitializePages();
        pages.selectShop.changeStore();
        pages.categoriesPageRepo.add_items_to_cart();
        pages.myCartPageRepo.createNewOrder();
    }
    @Test(priority = 1)
    public void addToExistingOrder() {
        InitializePages pages = new InitializePages();
        pages.selectShop.changeStore();
        pages.categoriesPageRepo.add_items_to_cart();
        pages.myCartPageRepo.addToExistingOrder();
    }
    @Test(priority = 2)
    public void modifyOrder() {
        InitializePages pages = new InitializePages();
        pages.selectShop.changeStore();
        pages.myCartPageRepo.modifyOrder();
    }
}
