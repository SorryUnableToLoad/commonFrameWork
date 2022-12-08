package com.projtest.base;

import com.proj.driver.Driver;
import com.projtest.LogbackHello;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class WebSetUp {
    public static final Logger logger = LoggerFactory.getLogger(WebSetUp.class);

    @BeforeClass
    public void setUp(){
        Driver.initDriverForWeb();
        logger.info("Initiated for web set up");

    }


    @AfterClass
    public void tearDown(){
        Driver.quitDriver();
        logger.info("web tear down completed");
    }
}
