package com.proj.common.base;

import com.proj.common.config.factory.FrameworkConfigFactory;
import com.proj.common.driver.Driver;
import com.proj.common.driver.DriverManager;
import com.proj.pages.initializepages.InitializePages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

/**
 * This class contains basic configuration methods to execute every TestScripts
 *
 * @author surajkumarnaganuri
 */
public class MobileSetUp {
    public static final Logger logger = LoggerFactory.getLogger(MobileSetUp.class);

    /**
     * This method consist of before suite configuration functionalities
     */
    @BeforeSuite
    public void setUp() {
        Driver.initDriverForMobile();
        DriverManager.getDriver().manage().timeouts().implicitlyWait(FrameworkConfigFactory.getConfig().waitTime(), TimeUnit.SECONDS);
        Reporter.log("========== Successfully Initiated for Mobile Test ==========", true);
        logger.info("========== Successfully Initiated for Mobile Test ==========");
    }

    @BeforeClass(enabled = false)
    public void loginToApplication() {
        InitializePages pages = new InitializePages();
        pages.loginPage.loginToApplication("7019020148");
        Reporter.log("Successfully login to the application", true);
    }

    @AfterClass(enabled = false)
    public void logoutToApplication() {
        InitializePages pages = new InitializePages();
        pages.logoutPage.logoutToApplication();
        Reporter.log("Successfully logout to the application", true);
    }

    /**
     * This method consist of after suite configuration functionalities
     */
    @AfterSuite
    public void tearDown() {
        Driver.quitDriver();
        Reporter.log("========== Successfully Terminated The Instance of Running Test Script ==========", true);
        logger.info("========== Successfully Terminated The Instance of Running Test Script ==========");
    }
}
