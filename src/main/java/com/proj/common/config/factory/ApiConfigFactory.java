package com.proj.common.config.factory;

import com.proj.common.config.ApiConfig;
import org.aeonbits.owner.ConfigCache;

public final class ApiConfigFactory {
    private ApiConfigFactory() {
    }

    public static ApiConfig getConfig() {
        //FrameworkConfig config = ConfigFactory.create(FrameworkConfig.class);
        //FrameworkConfig config = ConfigCache.getOrCreate(FrameworkConfig.class);
        return ConfigCache.getOrCreate(ApiConfig.class);
    }
}
