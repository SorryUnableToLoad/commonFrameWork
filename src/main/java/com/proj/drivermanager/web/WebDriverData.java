package com.proj.drivermanager.web;

import com.proj.enums.WebBrowserType;
import com.proj.enums.WebCloudType;


public class WebDriverData {
    private WebBrowserType browserType;
    private WebCloudType cloudType;

    public WebDriverData(WebBrowserType browserType, WebCloudType cloudType) {
        this.browserType = browserType;
        this.cloudType = cloudType;
    }

    public WebBrowserType getBrowserType() {
        return this.browserType;
    }

    public WebCloudType getCloudType() {
        return this.cloudType;
    }
}
