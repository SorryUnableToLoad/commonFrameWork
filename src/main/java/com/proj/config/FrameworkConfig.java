package com.proj.config;

import com.proj.converters.*;
import com.proj.enums.MobileCloudType;
import com.proj.enums.RunModeType;
import com.proj.enums.WebBrowserType;
import com.proj.enums.WebCloudType;
import org.aeonbits.owner.Config;

import java.net.URL;

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

    @DefaultValue("staging")
    String environment();

    @Key("${environment}.webUrl")
    String webUrl();


    @DefaultValue("CHROME")
    @Key("webBrowser")
    @ConverterClass(StringToWebBrowserTypeConverter.class)
    WebBrowserType webBrowser();

    @Key("webRunMode")
    @ConverterClass(StringToRunModeTypeConverter.class)
    RunModeType webRunMode();

    @Key("webCloudMode")
    @ConverterClass(StringToWebCloudTypeConverter.class)
    WebCloudType webCloudMode();




    @Key("mobileRunMode")
    @ConverterClass(StringToRunModeTypeConverter.class)
    RunModeType mobileRunMode();

    @Key("mobileCloudMode")
    @ConverterClass(StringToMobileCloudTypeConverter.class)
    MobileCloudType mobileCloudMode();

    @ConverterClass(StringToURLConverter.class)
    @DefaultValue("http://127.0.0.1:4723/wd/hub")
    URL localAppiumServerURL();


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
