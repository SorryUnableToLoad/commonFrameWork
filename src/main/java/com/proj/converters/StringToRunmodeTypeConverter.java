package com.proj.converters;

import com.proj.enums.RunmodeType;
import org.aeonbits.owner.Converter;

import java.lang.reflect.Method;

public class StringToRunmodeTypeConverter implements Converter<RunmodeType> {

    @Override
    public RunmodeType convert(Method method, String runmodeName) {
        return RunmodeType.valueOf(runmodeName.toUpperCase());
    }
}
