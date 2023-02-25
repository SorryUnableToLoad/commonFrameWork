package com.proj.common.config;
import com.proj.common.config.converters.*;
import com.proj.common.driver.enums.*;
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

//---------------------------------------------------------------------//

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

    @ConverterClass(StringToURLConverter.class)
    @Key("seleniumGridUrl")
    URL seleniumGridUrl();

    @ConverterClass(StringToURLConverter.class)
    @Key("selenoidUrl")
    URL selenoidUrl();

//---------------------------------------------------------------------//

    @DefaultValue("ANDROID")
    @Key("mobileOs")
    @ConverterClass(StringToMobileOsTypeConverter.class)
    MobilePlatformType mobileOs();

    @Key("mobileRunMode")
    @ConverterClass(StringToRunModeTypeConverter.class)
    RunModeType mobileRunMode();

    @Key("mobileCloudMode")
    @ConverterClass(StringToMobileCloudTypeConverter.class)
    MobileCloudType mobileCloudMode();

    @ConverterClass(StringToURLConverter.class)
    @DefaultValue("http://127.0.0.1:4723/wd/hub")
    URL localAppiumServerURL();


    @Key("browserStackUserName")
    String browserStackUserName();

    @Key("browserStackAutomateKey")
    String browserStackAutomateKey();

    @DefaultValue("https://${browserStackUserName}:${browserStackAutomateKey}@hub-cloud.browserstack.com/wd/hub")
    @ConverterClass(StringToURLConverter.class)
    URL browserStackURL();


    @Key("sauceLabsUsername")
    String sauceLabsUsername();

    @Key("sauceLabsKey")
    String key();

    @ConverterClass(StringToURLConverter.class)
    @Key("sauceLabsUrl")
    URL sauceLabsUrl();

    //---------------------------------------------------------------------//

    @Key("waitTime")
    int waitTime();

    @Key("apkPackageName")
    String apkPackageName();

}
