package com.proj.config.converters;

import com.proj.config.enums.RunModeType;
import org.aeonbits.owner.Converter;

import java.lang.reflect.Method;

public class StringToRunModeTypeConverter implements Converter<RunModeType> {

    @Override
    public RunModeType convert(Method method, String runModeName) {
        return RunModeType.valueOf(runModeName.toUpperCase());
    }
}
