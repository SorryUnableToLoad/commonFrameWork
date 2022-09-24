package com.proj.driver.impl.mobile;

import com.proj.driver.IMobileDriver;
import com.proj.driver.entity.MobileDriverData;
import com.proj.driver.factory.mobile.local.LocalMobileDriverFactory;
import org.openqa.selenium.WebDriver;

public class LocalMobileDriverImpl implements IMobileDriver {

    @Override
    public WebDriver getDriver(MobileDriverData driverData) {
        return LocalMobileDriverFactory.getDriver(driverData.getMobilePlatformType());
    }
}
