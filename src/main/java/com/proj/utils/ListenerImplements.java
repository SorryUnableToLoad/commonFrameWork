package com.proj.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import lombok.SneakyThrows;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.proj.utils.ScreenshotUtils.takeScreenShot;

public class ListenerImplements implements ITestListener {
    public ListenerImplements() {
    }

    public String timeStamp = new SimpleDateFormat(("yyyyMMdd hh_mm_ss")).format(new Date());
    public ExtentReports extentReports;
    public ExtentTest test;

    @Override
    public void onStart(ITestContext context) {
        File file = new File("./reports/HTML" + " " + timeStamp);
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(file);
        extentSparkReporter.config().setTheme(Theme.DARK);
        extentSparkReporter.config().setDocumentTitle("Customer App Project");
        extentSparkReporter.config().setReportName("Regression Test");
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        extentReports.setSystemInfo("PlatForm", "Android");
        extentReports.setSystemInfo("Build Name", "-----");
        extentReports.setSystemInfo("Reporter Name", "Suraj N");
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = extentReports.createTest(result.getMethod().getMethodName() + " ").assignAuthor("Suraj");
        test.log(Status.INFO, result.getMethod().getMethodName() + " Executed");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, result.getMethod().getMethodName() + "is Pass");
    }

    @SneakyThrows
    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, result.getMethod().getMethodName() + "is failed");
        test.log(Status.FAIL, result.getThrowable());

        String testCaseName = result.getMethod().getMethodName();
        try {
            String path = takeScreenShot();
            test.fail(testCaseName,MediaEntityBuilder.createScreenCaptureFromBase64String(path).build());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //  String screenShotAs = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);
        // test.fail(result.getMethod().getMethodName(), MediaEntityBuilder.createScreenCaptureFromBase64String(screenShotAs).build());

        /*===========================================================================================================*/

        /*File source = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "/screenshots/" + result.getMethod().getMethodName() + " " + timeStamp + ".png";
        File finalDestination = new File(destination);
        try {
            FileUtils.copyFile(source, finalDestination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        test.addScreenCaptureFromPath(destination, result.getMethod().getMethodName());*/

        /*===========================================================================================================*/

        /*EventFiringWebDriver efwd = new EventFiringWebDriver(DriverManager.getDriver());
        String res = result.getName();
        File src = efwd.getScreenshotAs(OutputType.FILE);
         try {
        String dest = System.getProperty("user.dir") + "/screenshots/" + res + ".png";
        File finaldest = new File(dest);
            FileUtils.copyFile(src, finaldest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        test.addScreenCaptureFromPath(dest, result.getMethod().getMethodName());*/
       // String path = takeScreenShot();
        //test.addScreenCaptureFromPath(screenshotPath).fail(MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());

       /* File f = new File(screenshotPath);
        FileInputStream fis = new FileInputStream(f);
        byte[] bytes = new byte[(int) f.length()];
        fis.read(bytes);
        String base64Img = new String(encodeBase64(bytes), StandardCharsets.UTF_8);*/

       /* byte[] file = FileUtils.readFileToByteArray(new File(path));
        String base64Img = encodeBase64String(file);
        test.addScreenCaptureFromPath(path).fail(MediaEntityBuilder.createScreenCaptureFromBase64String(base64Img).build());*/
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.log(Status.SKIP, result.getMethod().getMethodName() + "is Skipped");
        test.log(Status.SKIP, result.getThrowable());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
    }
}

