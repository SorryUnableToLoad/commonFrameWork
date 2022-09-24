package com.proj.driver.factory.mobile.remote;

import com.proj.driver.enums.MobileCloudType;
import com.proj.driver.enums.MobilePlatformType;
import org.openqa.selenium.WebDriver;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

public class RemoteMobileDriverFactory {
    private RemoteMobileDriverFactory() {
    }

    private static final Map<MobileCloudType, Function<MobilePlatformType, WebDriver>> MAP=new EnumMap<>(MobileCloudType.class);
    static {
        MAP.put(MobileCloudType.BROWSERSTACK, BrowserStackMobileFactory::getDriver);
        MAP.put(MobileCloudType.SAUCELABS, SauceLabsMobileFactory::getDriver);
    }
    public static WebDriver getDriver(MobileCloudType mobileCloudType,MobilePlatformType mobilePlatformType){
        return MAP.get(mobileCloudType).apply(mobilePlatformType);
    }
}
