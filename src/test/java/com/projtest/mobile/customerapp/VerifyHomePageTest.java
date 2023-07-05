package com.projtest.mobile.customerapp;

import com.proj.common.base.MobileSetUp;
import com.proj.pages.mobile.b2b.SelectShop.SelectShop;
import com.proj.pages.mobile.customerapp.home.HomePageRepo;
import org.testng.annotations.Test;

public class VerifyHomePageTest extends MobileSetUp {
    @Test
    public void verifyHomePageTest() {
        SelectShop.changeStore();
        HomePageRepo.validateHomepageIsDisplayed();
        System.out.println("Test Completed");
    }
}
