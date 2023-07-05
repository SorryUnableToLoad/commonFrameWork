package com.projtest.web.fwsanity;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class ShadowElementTest {
    @Test
    public void testShadowElement() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("chrome://settings/appearance");
        /*WebElement element = driver.findElement(By.xpath("//settings-ui")).getShadowRoot()
                .findElement(By.cssSelector("settings-main#main")).getShadowRoot()//id//
                .findElement(By.cssSelector("settings-basic-page.cr-centered-card-container")).getShadowRoot()//class//
                .findElement(By.cssSelector("settings-section[section=appearance]")).getShadowRoot()//attribute//
                .findElement(By.tagName("settings-appearance-pages"))
                .findElement(By.cssSelector("settings-toggle-button[label='Show Home button']")).getShadowRoot()
                .findElement(By.cssSelector("cr-toggle#control"));*/

        JavascriptExecutor js= (JavascriptExecutor)driver;
        js.executeScript("document.querySelector(\"body > settings-ui\")" +
                ".shadowRoot.querySelector(\"#main\")" +
                ".shadowRoot.querySelector(\"settings-basic-page\")" +
                ".shadowRoot.querySelector(\"#basicPage > settings-section:nth-child(13) > settings-appearance-page\")" +
                ".shadowRoot.querySelector(\"#pages > div > settings-toggle-button:nth-child(3)\")" +
                ".shadowRoot.querySelector(\"#control\")");

        /*Thread.sleep(5000);
        element.click();*/


    }
}
