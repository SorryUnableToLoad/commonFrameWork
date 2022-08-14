package com.proj.drivermanager;

import com.proj.drivermanager.mobile.MobileDriverData;
import com.proj.drivermanager.web.WebDriverData;
import com.proj.enums.MobilePlatformType;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

import static com.proj.config.ConfigFactory.getConfig;

public final class Driver {
    private Driver() {
    }

    public static void initDriverForWeb() {
        if (Objects.isNull(DriverManager.getDriver())) {
            WebDriverData driverData = new WebDriverData(getConfig().webBrowser(), getConfig().webCloudMode());
            WebDriver driver = DriverFactory.getDriverForWeb(getConfig().webRunMode()).getDriver(driverData);
            DriverManager.setDriver(driver);
            loadURL();
        }
    }

    public static void loadURL() {
        DriverManager.getDriver().manage().window().maximize();
        //DriverManager.getDriver().get(getConfig().webUrl());
        DriverManager.getDriver().get("https://www.amazon.in/");
    }

    public static void initDriverForMobile() {
        MobileDriverData driverData = new MobileDriverData(MobilePlatformType.ANDROID, getConfig().mobileCloudMode());
        WebDriver driver = DriverFactory.getDriverForMobile(getConfig().mobileRunMode()).getDriver(driverData);
        DriverManager.setDriver(driver);
    }

    public static void quitDriver() {
        if (Objects.nonNull(DriverManager.getDriver())) {
            DriverManager.getDriver().quit();
            DriverManager.unload();
        }
    }
}
