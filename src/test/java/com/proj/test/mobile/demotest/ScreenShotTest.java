package com.proj.test.mobile.demotest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class ScreenShotTest {
    private final static String APP_ACTIVITY_NAME = ".MainActivity";
    //AppiumDriver driver = DriverManager.getDriver();
    //public AppiumDriver driver=null;
    public WebDriver driver;

    @BeforeSuite
    public WebDriver getDriver() throws MalformedURLException, InterruptedException {
        if (driver == null) {
            bf();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }
        return driver;
    }

    public void bf() throws InterruptedException, MalformedURLException {
       /* DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, APP_ACTIVITY_NAME);
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, getConfig().apkPackageName());
        desiredCapabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/app_prod_6_oct_2022.apk");
        desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, "true");
        desiredCapabilities.setCapability(MobileCapabilityType.FULL_RESET, "false");
        //desiredCapabilities.setCapability(MobileCapabilityType.APP,System.getProperty("user.dir")+"/src/test/resources/apkbuilds/android-app.apk");
        new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
        */
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.amazon.in/");
        Thread.sleep(5000);

    }

    @AfterClass
    public void af() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }

    @Test
    public void screenShotTest(Method name) throws IOException, InterruptedException {
        Thread.sleep(5000);

        System.out.println(name.getName());

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File("./screenshots/" + name.getName() + ".png"));
    }

    public AppiumDriver<MobileElement> getMobileDriver(AppiumDriver driver) throws MalformedURLException, InterruptedException {
        if (driver == null) getDriver();
        return driver;
    }
}
