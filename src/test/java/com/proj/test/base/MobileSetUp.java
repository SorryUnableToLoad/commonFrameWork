package com.proj.test.base;

import com.proj.driver.Driver;
import com.proj.driver.DriverManager;
import com.proj.pages.initializepages.InitializePages;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

import static com.proj.config.FrameworkConfigFactory.getConfig;

/**
 * This class contains basic configuration methods to execute every TestScripts
 *
 * @author surajkumarnaganuri
 */
public class MobileSetUp {

    /**
     * This method consist of before method configuration functionalities
     */
    @BeforeSuite
    public void setUp() {
        Driver.initDriverForMobile();
        DriverManager.getDriver().manage().timeouts().implicitlyWait(getConfig().waitTime(), TimeUnit.SECONDS);
        Reporter.log("Set Up Done", true);
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
     * This method consist of after method configuration functionalities
     */
    @AfterSuite
    public void tearDown() {
        Driver.quitDriver();
        Reporter.log("Teat Down", true);
    }
}
