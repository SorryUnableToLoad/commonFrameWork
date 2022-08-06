package com.proj.drivermanager;

import com.proj.config.ConfigFactory;
import com.proj.enums.BrowserType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public final class LocalDriverFactory {
    private LocalDriverFactory() {
    }

    public static WebDriver getDriver() {
        WebDriver driver = null;
        if (getBrowser() == BrowserType.CHROME) {
            driver = ChromeManager.getDriver();
        } else {
            driver = FirefoxManager.getDriver();
        }
        return driver;
    }

    private static BrowserType getBrowser() {
        return ConfigFactory.getConfig().browser();
    }

}
