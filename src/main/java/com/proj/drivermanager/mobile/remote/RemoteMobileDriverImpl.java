package com.proj.drivermanager.mobile.remote;

import com.proj.drivermanager.mobile.IMobileDriver;
import com.proj.drivermanager.mobile.MobileDriverData;
import org.openqa.selenium.WebDriver;

public class RemoteMobileDriverImpl implements IMobileDriver {

    @Override
    public WebDriver getDriver(MobileDriverData driverData) {
        return RemoteMobileDriverFactory.getDriver(driverData.getMobileRemoteModeType()
                ,driverData.getMobilePlatformType());
    }
}
