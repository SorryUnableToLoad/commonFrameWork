package com.proj.driver.entity;

import com.proj.config.enums.MobileCloudType;
import com.proj.config.enums.MobilePlatformType;

public class MobileDriverData {
    private MobilePlatformType mobilePlatformType;
    private MobileCloudType mobileRemoteModeType;

    public MobileDriverData(MobilePlatformType mobilePlatformType, MobileCloudType mobileRemoteModeType) {
        this.mobilePlatformType = mobilePlatformType;
        this.mobileRemoteModeType = mobileRemoteModeType;
    }

    public MobilePlatformType getMobilePlatformType() {
        return this.mobilePlatformType;
    }

    public MobileCloudType getMobileRemoteModeType() {
        return this.mobileRemoteModeType;
    }
}
