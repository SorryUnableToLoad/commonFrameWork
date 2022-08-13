package com.proj.drivermanager.mobile.remote;

import com.proj.enums.MobileCloudType;
import com.proj.enums.MobilePlatformType;
import org.openqa.selenium.WebDriver;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

public class RemoteMobileDriverFactory {
    private RemoteMobileDriverFactory() {
    }
    /*public static WebDriver getDriver(MobileCloudType cloudType, BrowserType browserType) {
        if (cloudType == MobileCloudType.BROWSERSTACK) {
            return BrowserStackFactory.getDriver(browserType);
        } else if (cloudType == MobileCloudType.SELENIUMGRID) {
            return SeleniumGridFactory.getDriver(browserType);
        }
        return null;
    }*/

    private static final Map<MobileCloudType, Function<MobilePlatformType, WebDriver>> MAP=new EnumMap<>(MobileCloudType.class);
    //private static final Function<BrowserType,WebDriver>SELENIUMGRID=browserType -> SelenoidFactory.getDriver();
    //private static final Function<BrowserType,WebDriver>SELENIUMGRID=SeleniumGridFactory::getDriver;
    //private static final Function<MobileCloudType,WebDriver>BROWSERSTACK=BrowserStackMobileFactory::getDriver;

    static {
       // MAP.put(MobileCloudType.SAUCELABS, SELENIUMGRID);
        MAP.put(MobileCloudType.BROWSERSTACK,BrowserStackMobileFactory::getDriver);
    }
    public static WebDriver getDriver(MobileCloudType mobileCloudType,MobilePlatformType mobilePlatformType){
        return MAP.get(mobileCloudType).apply(mobilePlatformType);
    }
}
