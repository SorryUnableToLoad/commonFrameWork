package com.proj.base;

import com.proj.driver.Driver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class WebBaseClass {

    @BeforeClass
    public void setUp(){
        Driver.initDriverForWeb();
    }


    @AfterClass
    public void tearDown(){
        Driver.quitDriver();
    }
}
