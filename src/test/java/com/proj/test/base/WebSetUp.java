package com.proj.test.base;

import com.proj.driver.Driver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class WebSetUp {

    @BeforeClass
    public void setUp(){
        Driver.initDriverForWeb();
    }


    @AfterClass
    public void tearDown(){
        Driver.quitDriver();
    }
}
