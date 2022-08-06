package com.proj.drivermanager.web.remote;

import com.proj.drivermanager.web.remote.browserstack.BrowserStackFactory;
import com.proj.drivermanager.web.remote.seleniumgrid.SeleniumGridFactory;
import com.proj.drivermanager.web.remote.selenoid.SelenoidFactory;
import com.proj.enums.BrowserType;
import com.proj.enums.CloudType;
import org.openqa.selenium.WebDriver;

public class RemoteDriverFactory {
    private RemoteDriverFactory() {
    }

    public static WebDriver getDriver(CloudType cloudType, BrowserType browserType) {
        if (cloudType == CloudType.BROWSERSTACK) {
            return BrowserStackFactory.getDriver(browserType);
        } else if (cloudType == CloudType.SELENIUMGRID) {
            return SeleniumGridFactory.getDriver(browserType);
        }else if (cloudType == CloudType.SELENOID) {
            return SelenoidFactory.getDriver(browserType);
        }
        return null;
    }
}
