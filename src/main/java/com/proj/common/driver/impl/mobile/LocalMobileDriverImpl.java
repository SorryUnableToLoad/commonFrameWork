package com.proj.common.driver.impl.mobile;

import com.proj.common.driver.IMobileDriver;
import com.proj.common.driver.entity.MobileDriverData;
import com.proj.common.driver.factory.mobile.local.LocalMobileDriverFactory;
import io.appium.java_client.AppiumDriver;

public class LocalMobileDriverImpl implements IMobileDriver {

    @Override
    public AppiumDriver getDriver(MobileDriverData driverData) {
        return LocalMobileDriverFactory.getDriver(driverData.getMobilePlatformType());
    }
}
