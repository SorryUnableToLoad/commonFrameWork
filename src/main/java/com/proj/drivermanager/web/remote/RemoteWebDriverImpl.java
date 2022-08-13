package com.proj.drivermanager.web.remote;

import com.proj.drivermanager.web.IWebDriver;
import com.proj.drivermanager.web.WebDriverData;
import org.openqa.selenium.WebDriver;

public class RemoteWebDriverImpl implements IWebDriver {
    @Override
    public WebDriver getDriver(WebDriverData driverData) {
        return RemoteWebDriverFactory.getDriver(driverData.getBrowserRemoteModeType()
                ,driverData.getBrowserType());
    }
}
