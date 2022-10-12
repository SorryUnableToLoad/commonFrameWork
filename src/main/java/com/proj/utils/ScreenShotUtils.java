package com.proj.utils;

import com.proj.driver.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class ScreenShotUtils {
    public static String getScreenShot(String imagename) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) DriverManager.getDriver();
        String filepath = null;
        try {
            File f = ts.getScreenshotAs(OutputType.FILE);
            filepath = "./screenshots/" + imagename;
            FileUtils.copyFile(f, new File(filepath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filepath;
    }
}
