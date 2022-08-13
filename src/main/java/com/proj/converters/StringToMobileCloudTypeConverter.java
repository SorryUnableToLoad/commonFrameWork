package com.proj.converters;

import com.proj.enums.MobileCloudType;
import com.proj.enums.WebCloudType;
import org.aeonbits.owner.Converter;

import java.lang.reflect.Method;

public class StringToMobileCloudTypeConverter implements Converter<MobileCloudType> {

    @Override
    public MobileCloudType convert(Method method, String mobileCloudName) {

        return MobileCloudType.valueOf(mobileCloudName.toUpperCase());
    }
}
