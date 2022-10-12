package com.proj.driver.impl.mobile;

import com.proj.driver.entity.MobileDriverData;
import com.proj.driver.IMobileDriver;
import com.proj.driver.factory.mobile.remote.RemoteMobileDriverFactory;
import io.appium.java_client.AppiumDriver;

public class RemoteMobileDriverImpl implements IMobileDriver {

    @Override
    public AppiumDriver getDriver(MobileDriverData driverData) {
        return RemoteMobileDriverFactory.getDriver(driverData.getMobileRemoteModeType()
                ,driverData.getMobilePlatformType());
    }
}
