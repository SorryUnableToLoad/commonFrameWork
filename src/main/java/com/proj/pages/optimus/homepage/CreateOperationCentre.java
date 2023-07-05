package com.proj.pages.optimus.homepage;

import com.proj.common.driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateOperationCentre {
    public CreateOperationCentre() {
        WebDriver driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[text()='Create OperationCenter']")
    WebElement createOperationCenter;
    @FindBy(xpath = "//span[@title='Operation Center']")
    WebElement titleOfPage;
    @FindBy(id = "name")
    WebElement operationCenterNameTextBox;
    @FindBy(xpath = "//input[@id='capabilities']")
    WebElement selectCapabilities;
    @FindBy(xpath = "//div[@title='distribution']/div")
    WebElement distributionTypeCapabilities;
    @FindBy(xpath = "//div[@title='collection']/div")
    WebElement collectionTypeCapabilities;
    @FindBy(id = "zone")
    WebElement zoneType;
}
