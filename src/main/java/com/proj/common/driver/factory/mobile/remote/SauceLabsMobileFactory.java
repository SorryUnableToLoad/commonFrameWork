package com.proj.common.driver.factory.mobile.remote;


import com.proj.common.driver.enums.MobilePlatformType;
import com.proj.common.driver.manager.mobile.remote.saucelabs.SauceLabsAndroidManager;
import com.proj.common.driver.manager.mobile.remote.saucelabs.SauceLabsIosManager;
import io.appium.java_client.AppiumDriver;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public final class SauceLabsMobileFactory {

    private static final Map<MobilePlatformType, Supplier<AppiumDriver>> MAP =
            new EnumMap<>(MobilePlatformType.class);

    static {
        MAP.put(MobilePlatformType.ANDROID, SauceLabsAndroidManager::getDriver);
        MAP.put(MobilePlatformType.IOS, SauceLabsIosManager::getDriver);
    }

    private SauceLabsMobileFactory() {
    }

    public static AppiumDriver getDriver(MobilePlatformType mobilePlatformType) {
        return MAP.get(mobilePlatformType).get();
    }
}
