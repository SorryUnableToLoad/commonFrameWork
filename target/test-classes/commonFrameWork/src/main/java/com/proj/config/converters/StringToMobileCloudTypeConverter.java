package com.proj.config.converters;

import com.proj.config.enums.MobileCloudType;
import org.aeonbits.owner.Converter;

import java.lang.reflect.Method;

public class StringToMobileCloudTypeConverter implements Converter<MobileCloudType> {

    @Override
    public MobileCloudType convert(Method method, String mobileCloudName) {

        return MobileCloudType.valueOf(mobileCloudName.toUpperCase());
    }
}
