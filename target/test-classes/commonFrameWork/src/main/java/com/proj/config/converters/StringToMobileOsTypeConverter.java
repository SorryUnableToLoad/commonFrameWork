package com.proj.config.converters;

import com.proj.config.enums.MobilePlatformType;
import org.aeonbits.owner.Converter;

import java.lang.reflect.Method;

public class StringToMobileOsTypeConverter implements Converter<MobilePlatformType> {
    @Override
    public MobilePlatformType convert(Method method, String mobileOsName) {

        return MobilePlatformType.valueOf(mobileOsName.toUpperCase());
        /*Map<String, MobilePlatformType> stringBrowserTypeMap = Map
                .of("ANDROID", MobilePlatformType.ANDROID,
                        "IOS", MobilePlatformType.IOS);
        return stringBrowserTypeMap.getOrDefault(mobileOsName.toUpperCase(), MobilePlatformType.ANDROID);
*/
    }
}
