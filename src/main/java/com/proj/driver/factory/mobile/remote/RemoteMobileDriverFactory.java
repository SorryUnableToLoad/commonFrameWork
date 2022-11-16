package com.proj.driver.factory.mobile.remote;

import com.proj.config.enums.MobileCloudType;
import com.proj.config.enums.MobilePlatformType;
import io.appium.java_client.AppiumDriver;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

public class RemoteMobileDriverFactory {
    private RemoteMobileDriverFactory() {
    }

    private static final Map<MobileCloudType, Function<MobilePlatformType, AppiumDriver>> MAP =
            new EnumMap<>(MobileCloudType.class);

    static {
        MAP.put(MobileCloudType.BROWSERSTACK, BrowserStackMobileFactory::getDriver);
        MAP.put(MobileCloudType.SAUCELABS, SauceLabsMobileFactory::getDriver);
    }

    public static AppiumDriver getDriver(MobileCloudType mobileCloudType, MobilePlatformType mobilePlatformType) {
        return MAP.get(mobileCloudType).apply(mobilePlatformType);
    }
}
