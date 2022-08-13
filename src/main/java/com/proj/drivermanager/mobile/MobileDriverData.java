package com.proj.drivermanager.mobile;

import com.proj.enums.MobileCloudType;
import com.proj.enums.MobilePlatformType;
import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
@Getter
public class MobileDriverData {
    private MobilePlatformType mobilePlatformType;
    private MobileCloudType mobileRemoteModeType;
}
