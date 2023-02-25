package com.proj.common.listeners.extentreport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.restassured.http.Header;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReport {
    public static String timeStamp = new SimpleDateFormat(("ddMMMyyyy hh:mm:ss")).format(new Date());
    private static ExtentReports report;
    private static ExtentTest test;

    private ExtentReport() {
    }

    public static void initReport() {
        File file = new File("./extentreports/HTML" + " " + timeStamp);
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(file);
        extentSparkReporter.config().setTheme(Theme.DARK);
        extentSparkReporter.config().setDocumentTitle("Customer App Project");
        extentSparkReporter.config().setReportName("Regression Testing");
        report = new ExtentReports();
        report.attachReporter(extentSparkReporter);
        report.setSystemInfo("PlatForm", "Android");
        report.setSystemInfo("Build Name", "-----");
        report.setSystemInfo("Reporter Name", "Suraj N");
    }

    public static void tearDownReport() {
        report.flush();
    }

    public static void createTest(String testCaseName) {
        test = report.createTest(testCaseName);
        ExtentManager.setExtent(test);
    }

    public static void addAuthor(String[] authors) {
        for (String author : authors) {
            ExtentManager.getTest().assignAuthor(author);
        }
    }

    public static void addCategory(String[] categories) {
        for (String category : categories) {
            ExtentManager.getTest().assignCategory(category);
        }

    }

    public static void logRequest(RequestSpecification requestSpecification) {
        QueryableRequestSpecification query = SpecificationQuerier.query(requestSpecification);
        //test.log(Status.INFO, "Request Body Details");
        ExtentManager.getTest().log(Status.INFO, "Request Body Details");
        if (query == null) {
            ExtentManager.getTest().pass(" Doesn't Have Request Body");

        } else {
            ExtentManager.getTest().pass(MarkupHelper.createCodeBlock(query.getBody(), CodeLanguage.JSON));
            for (Header header : query.getHeaders()) {
                //test.log(Status.INFO, header.getName() + ":" + header.getValue());
                ExtentManager.getTest().log(Status.INFO, header.getName() + ":" + header.getValue());
            }
        }
    }

    public static void logResponse(String response) {
        //test.log(Status.INFO, "Response Body Details");
        ExtentManager.getTest().log(Status.INFO, "Response Body Details");
        ExtentManager.getTest().pass(MarkupHelper.createCodeBlock(response, CodeLanguage.JSON));
    }
}
