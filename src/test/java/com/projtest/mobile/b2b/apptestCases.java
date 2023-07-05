package com.projtest.mobile.b2b;

import com.proj.common.base.MobileSetUp;
import com.proj.pages.mobile.b2b.initializepages.InitializePages;
import org.testng.annotations.Test;

public class apptestCases extends MobileSetUp {
    @Test(priority = 0)
    public void createNewOrder() {
        InitializePages pages = new InitializePages();
        pages.selectStore.changeStore();
        pages.categoriesPageRepo.add_items_to_cart();
        pages.myCartPageRepo.createNewOrder();
    }

    @Test(priority = 1)
    public void addToExistingOrder() {
        InitializePages pages = new InitializePages();
        pages.selectStore.changeStore();
        pages.categoriesPageRepo.add_items_to_cart();
        pages.myCartPageRepo.addToExistingOrder();
    }

    @Test(priority = 2)
    public void modifyOrder() {
        InitializePages pages = new InitializePages();
        pages.selectStore.changeStore();
        pages.myCartPageRepo.modifyOrder();
    }
}
