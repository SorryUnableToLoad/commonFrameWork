package com.proj.base;

import com.proj.driver.Driver;
import com.proj.driver.DriverManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import static com.proj.config.factory.FrameworkConfigFactory.getConfig;

/**
 * This class contains basic configuration methods to execute every TestScripts
 *
 * @author surajkumarnaganuri
 */
public class WebSetUp {
    public static final Logger logger = LoggerFactory.getLogger(WebSetUp.class);

    /**
     * This method consist of before testScript execution configuration functionalities
     */
    @BeforeClass
    public void setUp() {
        Driver.initDriverForWeb();
        DriverManager.getDriver().manage().timeouts().implicitlyWait(getConfig().waitTime(), TimeUnit.SECONDS);
        Reporter.log("========== Successfully Initiated for Web Test ==========", true);
        logger.info("========== Successfully Initiated for Web Test ==========");
    }

    /**
     * This method consist of after testScript execution configuration functionalities
     */
    @AfterClass
    public void tearDown() {
        Driver.quitDriver();
        Reporter.log("========== Successfully Terminated The Instance of Running Test Script ==========", true);
        logger.info("========== Successfully Terminated The Instance of Running Test Script ==========");
    }
}
