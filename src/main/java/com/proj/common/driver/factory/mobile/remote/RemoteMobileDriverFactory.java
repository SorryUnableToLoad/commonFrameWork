package com.proj.common.driver.factory.mobile.remote;

import com.proj.common.driver.enums.MobileCloudType;
import com.proj.common.driver.enums.MobilePlatformType;
import io.appium.java_client.AppiumDriver;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

public class RemoteMobileDriverFactory {
    private static final Map<MobileCloudType, Function<MobilePlatformType, AppiumDriver>> MAP =
            new EnumMap<>(MobileCloudType.class);

    static {
        MAP.put(MobileCloudType.BROWSERSTACK, BrowserStackMobileFactory::getDriver);
        MAP.put(MobileCloudType.SAUCELABS, SauceLabsMobileFactory::getDriver);
    }

    private RemoteMobileDriverFactory() {
    }

    public static AppiumDriver getDriver(MobileCloudType mobileCloudType, MobilePlatformType mobilePlatformType) {
        return MAP.get(mobileCloudType).apply(mobilePlatformType);
    }
}
