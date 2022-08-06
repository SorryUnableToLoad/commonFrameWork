package com.proj.drivermanager;

import org.openqa.selenium.WebDriver;

public class Driver {
    static WebDriver driver = LocalDriverFactory.getDriver();

    public static void initDriver() {
        driver.get("https://google.co.in");
    }

    public static void quiteDriver() {
        driver.quit();
    }
}
