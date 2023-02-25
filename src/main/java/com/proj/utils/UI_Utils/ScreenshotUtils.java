package com.proj.utils.UI_Utils;

import com.proj.common.driver.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.IOException;

public class ScreenshotUtils {
    public static String takeScreenShot() throws IOException {
        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);
    }
}
