package com.proj.drivermanager.web.local;

import com.proj.drivermanager.web.IWebDriver;
import com.proj.drivermanager.web.WebDriverData;
import org.openqa.selenium.WebDriver;

public class LocalWebDriverImpl implements IWebDriver {

    @Override
    public WebDriver getDriver(WebDriverData driverData) {
        return LocalDriverFactory.getDriver(driverData.getBrowserType());
    }
}
