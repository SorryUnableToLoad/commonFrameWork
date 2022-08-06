package com.proj.drivermanager.web.remote;

import com.proj.drivermanager.web.remote.browserstack.BrowserStackFactory;
import com.proj.drivermanager.web.remote.seleniumgrid.SeleniumGridFactory;
import com.proj.drivermanager.web.remote.selenoid.SelenoidFactory;
import com.proj.enums.BrowserType;
import com.proj.enums.CloudType;
import org.openqa.selenium.WebDriver;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

public class RemoteDriverFactory {
    private RemoteDriverFactory() {
    }
    /*public static WebDriver getDriver(CloudType cloudType, BrowserType browserType) {
        if (cloudType == CloudType.BROWSERSTACK) {
            return BrowserStackFactory.getDriver(browserType);
        } else if (cloudType == CloudType.SELENIUMGRID) {
            return SeleniumGridFactory.getDriver(browserType);
        }else if (cloudType == CloudType.SELENOID) {
            return SelenoidFactory.getDriver(browserType);
        }
        return null;
    }*/

    private static final Map<CloudType, Function<BrowserType,WebDriver>> MAP=new EnumMap<>(CloudType.class);
    //private static final Function<BrowserType,WebDriver>SELENIUMGRID=browserType -> SelenoidFactory.getDriver();
    private static final Function<BrowserType,WebDriver>SELENIUMGRID=SeleniumGridFactory::getDriver;
    private static final Function<BrowserType,WebDriver>SELENOID=SelenoidFactory::getDriver;
    private static final Function<BrowserType,WebDriver>BROWSERSTACK=BrowserStackFactory::getDriver;

    static {
        MAP.put(CloudType.SELENIUMGRID, SELENIUMGRID);
        MAP.put(CloudType.SELENOID, SELENOID);
        MAP.put(CloudType.BROWSERSTACK, BROWSERSTACK);
    }
    public static WebDriver getDriver(CloudType cloudType,BrowserType browserType){
        return MAP.get(cloudType).apply(browserType);
    }
}
