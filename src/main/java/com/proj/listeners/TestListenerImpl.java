package com.proj.listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.proj.annotations.FrameworkAnnotations;
import com.proj.extentreport.ExtentLogger;
import com.proj.extentreport.ExtentReport;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListenerImpl implements ITestListener {

    AndroidDriver driver;
    private ExtentTest test;
    @Override
    public void onStart(ITestContext context) {
        ExtentReport.initReport();
    }
    @Override
    public void onFinish(ITestContext context) {
        ExtentReport.tearDownReport();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentReport.createTest(result.getName());

        //find the author and category and then i need to call that extent report method
        String[] authors = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotations.class).author();
        ExtentReport.addAuthor(authors);

        String[] category = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotations.class).category();
        ExtentReport.addCategory(category);

        ExtentLogger.info(result.getMethod().getMethodName()+" IS EXECUTED");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentLogger.pass(result.getMethod().getMethodName()+" IS PASS");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentLogger.fail(result.getMethod().getMethodName()+" IS FAIL");
        ExtentLogger.fail(String.valueOf(result.getThrowable()));

        ExtentReport.takeScreenShot(result);
        //String screenShotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        //test.fail(result.getMethod().getMethodName(), MediaEntityBuilder.createScreenCaptureFromBase64String(screenShotAs).build());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentLogger.skipped(result.getMethod().getMethodName()+" IS SKIPPED");
        ExtentLogger.skipped(String.valueOf(result.getThrowable()));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
    }
}
