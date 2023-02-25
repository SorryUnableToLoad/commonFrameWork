package com.proj.common.config.converters;

import com.proj.common.driver.enums.MobileCloudType;
import org.aeonbits.owner.Converter;

import java.lang.reflect.Method;

public class StringToMobileCloudTypeConverter implements Converter<MobileCloudType> {

    @Override
    public MobileCloudType convert(Method method, String mobileCloudName) {

        return MobileCloudType.valueOf(mobileCloudName.toUpperCase());
    }
}
