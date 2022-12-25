package javaPrectice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.*;

public class TestAssignment {
    //without using java 8
    @Test
    public void testLinks() {
        //Chrome Driver Setup
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        //amazon url
        driver.get("https://amazon.in");
        //finding links
        List<WebElement> links = driver.findElements(By.xpath("//a"));
        //all links storing in array list
        List<String> listOfLinks = new ArrayList<>();
        for (WebElement link : links) {
            String temp = link.getText();
            if (!temp.isBlank()) {
                listOfLinks.add(temp);
            }
        }
        //removing duplicate
        HashSet<String> removingDuplicateLinks = new HashSet<>(listOfLinks);
        //storing without duplicate links in array
        ArrayList<String> withoutDuplicateLinks = new ArrayList<>(removingDuplicateLinks);
        Collections.sort(withoutDuplicateLinks);

        for (String withoutDuplicateLink : withoutDuplicateLinks) {
            if (withoutDuplicateLink.startsWith("C") || withoutDuplicateLink.startsWith("D")) {
                System.out.println(withoutDuplicateLink);
            }
        }
        driver.quit();
    }
    //with using java 8 concept
    @Test
    public void testLinks1() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://amazon.in");
        List<WebElement> links = driver.findElements(By.xpath("//a"));
        links.stream()
                .map(e -> e.getText())
                .filter(s -> !s.isBlank())
                .distinct()
                .sorted()
                .filter(s -> s.startsWith("C") || s.startsWith("D"))
                .forEach(s -> System.out.println(s));
        driver.quit();
    }
}
