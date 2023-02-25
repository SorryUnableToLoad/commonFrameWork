package com.proj.common.config.factory;

import com.proj.common.config.FrameworkConfig;
import org.aeonbits.owner.ConfigCache;

public final class FrameworkConfigFactory {
    private FrameworkConfigFactory() {
    }

    public static FrameworkConfig getConfig() {
        //FrameworkConfig config = ConfigFactory.create(FrameworkConfig.class);
        //FrameworkConfig config = ConfigCache.getOrCreate(FrameworkConfig.class);
        return ConfigCache.getOrCreate(FrameworkConfig.class);
    }
}
