package com.proj.drivermanager;

import com.proj.drivermanager.mobile.IMobileDriver;
import com.proj.drivermanager.mobile.local.LocalMobileDriverImpl;
import com.proj.drivermanager.mobile.remote.RemoteMobileDriverImpl;
import com.proj.drivermanager.web.IWebDriver;
import com.proj.drivermanager.web.local.LocalWebDriverImpl;
import com.proj.drivermanager.web.remote.RemoteWebDriverImpl;
import com.proj.enums.RunModeType;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public class DriverFactory {
    private DriverFactory(){}

    private static final Map<RunModeType, Supplier<IWebDriver>> WEB = new EnumMap<>(RunModeType.class);
    private static final Map<RunModeType, Supplier<IMobileDriver>> MOBILE = new EnumMap<>(RunModeType.class);

    static{
        WEB.put(RunModeType.LOCAL, LocalWebDriverImpl::new);
        WEB.put(RunModeType.REMOTE, RemoteWebDriverImpl::new);
        MOBILE.put(RunModeType.LOCAL, LocalMobileDriverImpl::new);
        MOBILE.put(RunModeType.REMOTE, RemoteMobileDriverImpl::new);
    }

    public static IWebDriver getDriverForWeb(RunModeType runModeType){
        return WEB.get(runModeType).get();
    }
    public static IMobileDriver getDriverForMobile(RunModeType runModeType){
        return MOBILE.get(runModeType).get();
    }
}

