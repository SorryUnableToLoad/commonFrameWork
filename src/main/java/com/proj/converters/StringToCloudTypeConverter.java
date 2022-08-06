package com.proj.converters;

import com.proj.enums.CloudType;
import org.aeonbits.owner.Converter;

import java.lang.reflect.Method;

public class StringToCloudTypeConverter implements Converter<CloudType> {

    @Override
    public CloudType convert(Method method, String cloudName) {
        return CloudType.valueOf(cloudName.toUpperCase());
    }
}
