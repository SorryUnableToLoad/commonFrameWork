package com.proj.common.driver.entity;

import com.proj.common.driver.enums.WebBrowserType;
import com.proj.common.driver.enums.WebCloudType;

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
