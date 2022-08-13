package com.proj.drivermanager.web;

import com.proj.enums.WebBrowserType;
import com.proj.enums.WebCloudType;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class WebDriverData {
    private WebBrowserType browserType;
    private WebCloudType browserRemoteModeType;
}
