package com.proj.driver.factory.mobile.remote;


import com.proj.config.enums.MobilePlatformType;
import com.proj.driver.manager.mobile.remote.saucelabs.SauceLabsAndroidManager;
import com.proj.driver.manager.mobile.remote.saucelabs.SauceLabsIosManager;
import io.appium.java_client.AppiumDriver;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public final class SauceLabsMobileFactory {

    private SauceLabsMobileFactory(){}

    private static final Map<MobilePlatformType, Supplier<AppiumDriver>> MAP =
            new EnumMap<>(MobilePlatformType.class);

    static {
        MAP.put(MobilePlatformType.ANDROID, SauceLabsAndroidManager::getDriver);
        MAP.put(MobilePlatformType.IOS, SauceLabsIosManager::getDriver);
    }

    public static AppiumDriver getDriver(MobilePlatformType mobilePlatformType){
        return MAP.get(mobilePlatformType).get();
    }
}
