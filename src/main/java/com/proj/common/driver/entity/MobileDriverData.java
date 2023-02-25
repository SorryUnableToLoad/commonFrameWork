package com.proj.common.driver.entity;

import com.proj.common.driver.enums.MobileCloudType;
import com.proj.common.driver.enums.MobilePlatformType;

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
