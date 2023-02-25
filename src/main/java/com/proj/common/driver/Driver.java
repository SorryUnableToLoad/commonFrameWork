package com.proj.common.driver;

import com.proj.common.config.factory.FrameworkConfigFactory;
import com.proj.common.driver.entity.MobileDriverData;
import com.proj.common.driver.entity.WebDriverData;
import com.proj.common.driver.factory.DriverFactory;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

/**
 * This class is created for initialize the webDriver and quit the driver.
 */
public final class Driver {
    private Driver() {
    }

    /**
     * This method is initialized the driver based on user data for web application testing.
     * Description : set the browser type,cloud mode and run mode in config file
     * webBrowser -> chrome, firefox
     * webCloudMode -> selenium-grid, selenoid, browserstack
     * webRunMode -> local, cloud
     */
    public static void initDriverForWeb() {
        if (Objects.isNull(DriverManager.getDriver())) {
            WebDriverData driverData = new WebDriverData(FrameworkConfigFactory.getConfig().webBrowser(),
                    FrameworkConfigFactory.getConfig().webCloudMode());
            WebDriver driver = DriverFactory
                    .getDriverForWeb(FrameworkConfigFactory.getConfig().webRunMode())
                    .getDriver(driverData);
            DriverManager.getInstance().setDriver(driver);
            loadURL();
        }
    }

    /**
     * This is method is load the url of application
     * Description : url can be dev environment,beta environment, staging environment
     */
    public static void loadURL() {
        DriverManager.getDriver().manage().window().maximize();
        DriverManager.getDriver().get(FrameworkConfigFactory.getConfig().webUrl());
        // DriverManager.getDriver().get("https://www.amazon.in/");
    }

    /**
     * This method is initialized the driver based on user data for mobile application testing.
     * Description : set the browser type,cloud mode and run mode in config file
     * mobile-platform -> android, ios
     * mobileCloudMode -> browserstack
     * mobileRunMode -> local, cloud
     */
    public static void initDriverForMobile() {
        //MobileDriverData driverData = new MobileDriverData(MobilePlatformType.ANDROID, getConfig().mobileCloudMode());
        MobileDriverData driverData = new MobileDriverData(FrameworkConfigFactory.getConfig().mobileOs()
                , FrameworkConfigFactory.getConfig().mobileCloudMode());
        WebDriver driver = DriverFactory
                .getDriverForMobile(FrameworkConfigFactory.getConfig().mobileRunMode())
                .getDriver(driverData);
        DriverManager.getInstance().setDriver(driver);
    }

    /**
     * This method is terminated the instance of running test script after completion.
     */
    public static void quitDriver() {
        if (Objects.nonNull(DriverManager.getDriver())) {
            DriverManager.getDriver().quit();
            DriverManager.unload();
        }
    }
}
