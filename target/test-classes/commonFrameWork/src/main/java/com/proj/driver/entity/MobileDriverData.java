package com.proj.driver.entity;

import com.proj.config.enums.MobileCloudType;
import com.proj.config.enums.MobilePlatformType;

public class MobileDriverData {
    private MobilePlatformType mobilePlatformType;
    private MobileCloudType mobileCloudType;

    public MobileDriverData(MobilePlatformType mobilePlatformType, MobileCloudType mobileRemoteModeType) {
        this.mobilePlatformType = mobilePlatformType;
        this.mobileCloudType = mobileRemoteModeType;
    }

    public MobilePlatformType getMobilePlatformType() {
        return this.mobilePlatformType;
    }

    public MobileCloudType getMobileRemoteModeType() {
        return this.mobileCloudType;
    }
}
