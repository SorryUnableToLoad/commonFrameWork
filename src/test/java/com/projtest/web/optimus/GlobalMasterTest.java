package com.projtest.web.optimus;

import com.proj.common.base.WebSetUp;
import com.proj.pages.optimus.HomePage;
import com.proj.pages.optimus.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GlobalMasterTest extends WebSetUp {
    @Test
    void testGlobalMaster() throws InterruptedException {
        /*String successMessageDisplayedForCityName = new LoginPage()
                .loginToApplication("surajkumar.n@captainfresh.in", "CF&Suraj&Naganuri")
                .navigateToCities().clickOnCreateCity()
                .setCityName("11City")
                .clickOnSubmit()
                .isSuccessMessageDisplayedForCityName();

        Assert.assertEquals(successMessageDisplayedForCityName,"City Created Successfully!");*/


        /*******************
         * Create an City *
         *******************/
        String successMessageDisplayedForCityName= new HomePage()
                .navigateToCities()
                .clickOnCreateCity()
                .setCityName("11City")
                .clickOnSubmit()
                .isSuccessMessageDisplayedForCityName();

        Assert.assertEquals(successMessageDisplayedForCityName,"City Created Successfully!");


        /*******************
         * Create an State *
         *******************/
        String successMessageDisplayedForStateName = new HomePage()
                .navigateToStates()
                .clickOnCreateState()
                .setStateCode("11State")
                .setStateCode("11")
                .isSuccessMessageDisplayedForStateName();

        Assert.assertEquals(successMessageDisplayedForStateName,"Country Created Successfully!");


        /*********************
         * Create an Country *
         *********************/
        String successMessageDisplayedForCountryName = new HomePage()
                .navigateToCountries()
                .clickOnCreateCountry()
                .setCountryName("11Country")
                .clickOnSubmit()
                .isErrorMessageDisplayedForCountryName();

        Assert.assertEquals(successMessageDisplayedForCountryName,"Country Created Successfully!");

    }
}
