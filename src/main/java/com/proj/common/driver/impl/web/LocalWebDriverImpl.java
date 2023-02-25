package com.proj.common.driver.impl.web;

import com.proj.common.driver.IWebDriver;
import com.proj.common.driver.entity.WebDriverData;
import com.proj.common.driver.factory.web.local.LocalDriverFactory;
import org.openqa.selenium.WebDriver;

public class LocalWebDriverImpl implements IWebDriver {

    @Override
    public WebDriver getDriver(WebDriverData driverData) {
        return LocalDriverFactory.getDriver(driverData.getBrowserType());
    }
}
