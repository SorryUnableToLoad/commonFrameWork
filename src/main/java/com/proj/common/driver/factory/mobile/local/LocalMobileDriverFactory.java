package com.proj.common.driver.factory.mobile.local;

import com.proj.common.driver.enums.MobilePlatformType;
import com.proj.common.driver.manager.mobile.local.AndroidManager;
import com.proj.common.driver.manager.mobile.local.IosManager;
import io.appium.java_client.AppiumDriver;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public class LocalMobileDriverFactory {
    private static final Map<MobilePlatformType, Supplier<AppiumDriver>> MAP
            = new EnumMap<>(MobilePlatformType.class);

    static {
        MAP.put(MobilePlatformType.ANDROID, AndroidManager::getDriver);
        MAP.put(MobilePlatformType.IOS, IosManager::getDriver);
    }

    private LocalMobileDriverFactory() {
    }

    public static AppiumDriver getDriver(MobilePlatformType mobilePlatformType) {
        return MAP.get(mobilePlatformType).get();
    }
}