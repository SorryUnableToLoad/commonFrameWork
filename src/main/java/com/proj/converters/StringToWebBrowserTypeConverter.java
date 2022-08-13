package com.proj.converters;

import com.proj.enums.WebBrowserType;
import org.aeonbits.owner.Converter;

import java.lang.reflect.Method;
import java.util.Map;

public class StringToWebBrowserTypeConverter implements Converter<WebBrowserType> {

    @Override
    public WebBrowserType convert(Method method, String webBrowserName) {

        //return BrowserType.valueOf(browserName.toUpperCase());
        Map<String, WebBrowserType> stringBrowserTypeMap = Map
                .of("CHROME", WebBrowserType.CHROME,
                        "FIREFOX", WebBrowserType.FIREFOX);
        return stringBrowserTypeMap.getOrDefault(webBrowserName.toUpperCase(), WebBrowserType.CHROME);

    }
}
