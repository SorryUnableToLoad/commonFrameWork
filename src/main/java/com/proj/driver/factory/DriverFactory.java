package com.proj.driver.factory;

import com.proj.config.enums.RunModeType;
import com.proj.driver.IMobileDriver;
import com.proj.driver.impl.mobile.LocalMobileDriverImpl;
import com.proj.driver.impl.mobile.RemoteMobileDriverImpl;
import com.proj.driver.IWebDriver;
import com.proj.driver.impl.web.LocalWebDriverImpl;
import com.proj.driver.impl.web.RemoteWebDriverImpl;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public class DriverFactory {
    private DriverFactory(){

    }

    private static final Map<RunModeType, Supplier<IWebDriver>> WEB = new EnumMap<>(RunModeType.class);
    private static final Map<RunModeType, Supplier<IMobileDriver>> MOBILE = new EnumMap<>(RunModeType.class);

    static{
        WEB.put(RunModeType.LOCAL, LocalWebDriverImpl::new);
        WEB.put(RunModeType.CLOUD, RemoteWebDriverImpl::new);
        MOBILE.put(RunModeType.LOCAL, LocalMobileDriverImpl::new);
        MOBILE.put(RunModeType.CLOUD, RemoteMobileDriverImpl::new);
    }

    public static IWebDriver getDriverForWeb(RunModeType runModeType){
        return WEB.get(runModeType).get();
    }
    public static IMobileDriver getDriverForMobile(RunModeType runModeType){
        return MOBILE.get(runModeType).get();
    }
}

