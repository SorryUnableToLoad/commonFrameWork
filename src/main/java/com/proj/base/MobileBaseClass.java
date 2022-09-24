package com.proj.base;

import com.proj.driver.Driver;
import com.proj.pages.loginpage.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * This class contains basic configuration methods to execute every TestScripts
 * @author surajkumarnaganuri
 */
public class MobileBaseClass {
WebDriver driver;
    /**
     * This method consist of before Suite configuration functionalities
     */
    @BeforeClass
    public void setUp(){
         Driver.initDriverForMobile();
    }


    @BeforeMethod(enabled = false)
    public void loginToApplication() {
        LoginPage lp=new LoginPage();
        lp.loginToApplication();
        Reporter.log("Successfully login to the application",true);
    }
    @AfterMethod(enabled = false)
    public void logoutToApplication(){

        Reporter.log("Successfully logout to the application",true);
    }

    /**
     * This method consist of after method configuration functionalities
     */
    @AfterClass(enabled = false)
    public void tearDown(){
        Driver.quitDriver();
    }
}
