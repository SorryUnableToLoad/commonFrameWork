package com.proj.converters;

import com.proj.enums.WebCloudType;
import org.aeonbits.owner.Converter;

import java.lang.reflect.Method;

public class StringToWebCloudTypeConverter implements Converter<WebCloudType> {

    @Override
    public WebCloudType convert(Method method, String webCloudName) {

        return WebCloudType.valueOf(webCloudName.toUpperCase());
    }
}
