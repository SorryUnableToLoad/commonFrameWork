package com.proj.test.mobile.base;

import com.proj.drivermanager.Driver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class MobileBaseClass {

    @BeforeMethod
    public void setUp(){
        Driver.initDriverForMobile();
    }

    @AfterMethod
    public void tearDown(){
        Driver.quitDriver();
    }
}
