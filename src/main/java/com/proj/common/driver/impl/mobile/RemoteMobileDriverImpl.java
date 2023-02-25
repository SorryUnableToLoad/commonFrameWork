package com.proj.common.driver.impl.mobile;

import com.proj.common.driver.IMobileDriver;
import com.proj.common.driver.entity.MobileDriverData;
import com.proj.common.driver.factory.mobile.remote.RemoteMobileDriverFactory;
import io.appium.java_client.AppiumDriver;

public class RemoteMobileDriverImpl implements IMobileDriver {

    @Override
    public AppiumDriver getDriver(MobileDriverData driverData) {
        return RemoteMobileDriverFactory.getDriver(driverData.getMobileRemoteModeType()
                , driverData.getMobilePlatformType());
    }
}
