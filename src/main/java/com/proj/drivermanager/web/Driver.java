package com.proj.drivermanager.web;

import com.proj.config.ConfigFactory;
import com.proj.drivermanager.web.local.LocalDriverFactory;
import org.openqa.selenium.WebDriver;

public class Driver {
    private Driver(){}
    static WebDriver driver = LocalDriverFactory.getDriver(ConfigFactory.getConfig().browser());

    public static void initDriver() {
        driver.get("https://google.co.in");
    }

    public static void quiteDriver() {
        driver.quit();
    }
}
