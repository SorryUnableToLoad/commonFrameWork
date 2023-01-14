package com.proj.config.converters;

import com.proj.config.enums.WebCloudType;
import org.aeonbits.owner.Converter;

import java.lang.reflect.Method;

public class StringToWebCloudTypeConverter implements Converter<WebCloudType> {

    @Override
    public WebCloudType convert(Method method, String webCloudName) {

        return WebCloudType.valueOf(webCloudName.toUpperCase());
    }
}
