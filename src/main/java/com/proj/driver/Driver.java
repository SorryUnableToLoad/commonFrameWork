package com.proj.driver;

import com.proj.driver.entity.MobileDriverData;
import com.proj.driver.entity.WebDriverData;
import com.proj.driver.factory.DriverFactory;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

import static com.proj.config.FrameworkConfigFactory.getConfig;

/**
 * This class is created for initialize the webDriver and quit the driver.
 */
public final class Driver {
    private Driver() {
    }

    /**
     * This method is intialize the driver based on user data for web application testing.
     * Description : set the browser type,cloud mode and run mode in config file
     * webBrowser -> chrome, firefox
     * webCloudMode -> selenium-grid, selenoid, browserstack
     * webRunMode -> local, cloud
     */
    public static void initDriverForWeb() {
        if (Objects.isNull(DriverManager.getDriver())) {
            WebDriverData driverData = new WebDriverData(getConfig().webBrowser(),
                    getConfig().webCloudMode());
            WebDriver driver = DriverFactory
                    .getDriverForWeb(getConfig().webRunMode())
                    .getDriver(driverData);
            DriverManager.getInstance().setDriver(driver);
            loadURL();
        }
    }

    /**
     * Thsi is method is load the url of application
     * Description : url can be dev environment,beta environment, staging environment
     */
    public static void loadURL() {
        DriverManager.getDriver().manage().window().maximize();
        DriverManager.getDriver().get(getConfig().webUrl());
        // DriverManager.getDriver().get("https://www.amazon.in/");
    }

    /**
     * This method is intialize the driver based on user data for mobile application testing.
     * Description : set the browser type,cloud mode and run mode in config file
     * mobile-platform -> android, ios
     * mobileCloudMode -> browserstack
     * mobileRunMode -> local, cloud
     */
    public static void initDriverForMobile() {
        //MobileDriverData driverData = new MobileDriverData(MobilePlatformType.ANDROID, getConfig().mobileCloudMode());
        MobileDriverData driverData=new MobileDriverData(getConfig().mobileOs()
                ,getConfig().mobileCloudMode());
        WebDriver driver = DriverFactory
                .getDriverForMobile(getConfig().mobileRunMode())
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
