package com.proj.drivermanager;

import com.proj.config.ConfigFactory;
import com.proj.enums.CloudType;

public class RemoteDriverFactory {
    private RemoteDriverFactory() {
    }

    public static void getDriver() {
        CloudType cloudType = ConfigFactory.getConfig().cloudType();
        if (cloudType == CloudType.BROWSERSTACK) {

        } else if (cloudType == CloudType.SAUCELABS) {

        } else if (cloudType == CloudType.SELENIUMGRID) {

        } else {

        }

    }
}
