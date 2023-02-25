package com.proj.common.driver;

import com.proj.common.driver.entity.WebDriverData;
import org.openqa.selenium.WebDriver;

public interface IWebDriver {
    WebDriver getDriver(WebDriverData driverData);
}
