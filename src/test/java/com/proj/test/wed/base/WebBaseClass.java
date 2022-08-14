package com.proj.test.wed.base;

import com.proj.drivermanager.Driver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.*;

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
