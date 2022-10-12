package com.proj.driver.impl.web;

import com.proj.driver.entity.WebDriverData;
import com.proj.driver.factory.web.local.LocalDriverFactory;
import com.proj.driver.IWebDriver;
import org.openqa.selenium.WebDriver;

public class LocalWebDriverImpl implements IWebDriver {

    @Override
    public WebDriver getDriver(WebDriverData driverData) {
        return LocalDriverFactory.getDriver(driverData.getBrowserType());
    }
}
