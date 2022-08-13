package com.proj.drivermanager.mobile.local;

import com.proj.drivermanager.mobile.IMobileDriver;
import com.proj.drivermanager.mobile.MobileDriverData;
import org.openqa.selenium.WebDriver;

public class LocalMobileDriverImpl implements IMobileDriver {

    @Override
    public WebDriver getDriver(MobileDriverData driverData) {
        return LocalMobileDriverFactory.getDriver(driverData.getMobilePlatformType());
    }
}
