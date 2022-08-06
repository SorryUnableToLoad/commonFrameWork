package com.proj.converters;

import com.proj.enums.BrowserType;
import org.aeonbits.owner.Converter;

import java.lang.reflect.Method;
import java.util.Map;

public class StringToBrowserTypeConverter implements Converter<BrowserType> {

    @Override
    public BrowserType convert(Method method, String browserName) {

        //return BrowserType.valueOf(browserName.toUpperCase());
        Map<String, BrowserType> stringBrowserTypeMap = Map
                .of("CHROME", BrowserType.CHROME,
                        "FIREFOX", BrowserType.FIREFOX);
        return stringBrowserTypeMap.getOrDefault(browserName.toUpperCase(), BrowserType.CHROME);

    }
}
