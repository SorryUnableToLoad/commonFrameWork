package com.proj.config.converters;

import com.proj.config.enums.WebBrowserType;
import org.aeonbits.owner.Converter;

import java.lang.reflect.Method;

public class StringToWebBrowserTypeConverter implements Converter<WebBrowserType> {
    @Override
    public WebBrowserType convert(Method method, String webBrowserName) {

        return WebBrowserType.valueOf(webBrowserName.toUpperCase());
        /*Map<String, WebBrowserType> stringBrowserTypeMap = Map
                .of("CHROME", WebBrowserType.CHROME,
                        "FIREFOX", WebBrowserType.FIREFOX);
        return stringBrowserTypeMap.getOrDefault(webBrowserName.toUpperCase(), WebBrowserType.CHROME);
*/
    }
}
