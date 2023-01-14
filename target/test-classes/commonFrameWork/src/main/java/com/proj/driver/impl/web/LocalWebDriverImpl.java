package com.proj.driver.impl.web;

import com.proj.driver.IWebDriver;
import com.proj.driver.entity.WebDriverData;
import com.proj.driver.factory.web.local.LocalDriverFactory;
import org.openqa.selenium.WebDriver;

public class LocalWebDriverImpl implements IWebDriver {

    @Override
    public WebDriver getDriver(WebDriverData driverData) {
        return LocalDriverFactory.getDriver(driverData.getBrowserType());
    }
}
