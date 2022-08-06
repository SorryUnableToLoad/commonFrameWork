package com.proj.config;

import com.proj.converters.StringToBrowserTypeConverter;
import com.proj.converters.StringToURLConverter;
import com.proj.enums.BrowserType;
import com.proj.enums.CloudType;
import com.proj.enums.RunmodeType;
import org.aeonbits.owner.Config;

import java.net.URL;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        //look for maven system property
        "system:properties",
        // look for jenkins environment property
        "system:env",
        //look for local directory properties file
        "file:${user.dir}/src/test/resources/config.properties"})
public interface FrameworkConfig extends Config {

    @ConverterClass(StringToBrowserTypeConverter.class)
    BrowserType browser();

    @Key("runmode")
    RunmodeType runmodeType();

    @Key("cloud")
    CloudType cloudType();

    @Key("browserstackusername")
    String userName();

    @Key("browserstackautomatekey")
    String automateKay();

    @DefaultValue("https://${browserstackusername}:${browserstackautomatekey}@hub-cloud.browserstack.com/wd/hub")
    @ConverterClass(StringToURLConverter.class)
    URL browserStackURL();

    @ConverterClass(StringToURLConverter.class)
    @Key("seleniumgridurl")
    URL seleniumGridUrl();

    @ConverterClass(StringToURLConverter.class)
    @Key("selenoidurl")
    URL selenoidUrl();

}
