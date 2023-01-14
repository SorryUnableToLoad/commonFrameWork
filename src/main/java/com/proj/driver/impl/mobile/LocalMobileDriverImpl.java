package com.proj.driver.impl.mobile;

import com.proj.driver.IMobileDriver;
import com.proj.driver.entity.MobileDriverData;
import com.proj.driver.factory.mobile.local.LocalMobileDriverFactory;
import io.appium.java_client.AppiumDriver;

public class LocalMobileDriverImpl implements IMobileDriver {

    @Override
    public AppiumDriver getDriver(MobileDriverData driverData) {
        return LocalMobileDriverFactory.getDriver(driverData.getMobilePlatformType());
    }
}
