package com.proj.common.driver.factory;

import com.proj.common.driver.enums.RunModeType;
import com.proj.common.driver.IMobileDriver;
import com.proj.common.driver.IWebDriver;
import com.proj.common.driver.impl.mobile.LocalMobileDriverImpl;
import com.proj.common.driver.impl.mobile.RemoteMobileDriverImpl;
import com.proj.common.driver.impl.web.LocalWebDriverImpl;
import com.proj.common.driver.impl.web.RemoteWebDriverImpl;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public class DriverFactory {
    private static final Map<RunModeType, Supplier<IWebDriver>> WEB = new EnumMap<>(RunModeType.class);
    private static final Map<RunModeType, Supplier<IMobileDriver>> MOBILE = new EnumMap<>(RunModeType.class);

    static {
        WEB.put(RunModeType.LOCAL, LocalWebDriverImpl::new);
        WEB.put(RunModeType.CLOUD, RemoteWebDriverImpl::new);
        MOBILE.put(RunModeType.LOCAL, LocalMobileDriverImpl::new);
        MOBILE.put(RunModeType.CLOUD, RemoteMobileDriverImpl::new);
    }

    private DriverFactory() {

    }

    public static IWebDriver getDriverForWeb(RunModeType runModeType) {
        return WEB.get(runModeType).get();
    }

    public static IMobileDriver getDriverForMobile(RunModeType runModeType) {
        return MOBILE.get(runModeType).get();
    }
}

