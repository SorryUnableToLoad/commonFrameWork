package com.proj.config;

import com.proj.config.converters.StringToBrowserTypeConverter;
import com.proj.config.enums.BrowserType;
import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        //look for maven system property
        "system:properties",
        // look for jenkins environment property
        "system:env",
        //look for local directory properties file
        "file:${user.dir}/src/test/resources/config.properties"
})
public interface FrameworkConfig extends Config {

    @ConverterClass(StringToBrowserTypeConverter.class)
    BrowserType browser();
}
