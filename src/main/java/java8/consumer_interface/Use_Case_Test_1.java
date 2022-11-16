package java8.consumer_interface;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;
import java.util.function.Consumer;

public class Use_Case_Test_1 {
    @Test
    void testRunner() {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://amazon.in");
        List<WebElement> elements = driver.findElements(By.xpath("//a"));

        /*for (int i=0;i<element.size();i++){
            System.out.println(element.get(i).getText());
        }*/
        elements.forEach(e -> System.out.println(e.getText()));
        System.out.println("************************************");

        Consumer<WebElement> elementConsumer = (element) -> System.out.println(element.getText());
        elements.forEach(elementConsumer);
        System.out.println("************************************");

        Consumer<WebElement> elementConsumer1 = (e) -> {
            if (!e.getText().isBlank()) {
                System.out.println(e.getText());
            }
        };
        elements.forEach(elementConsumer1);
        driver.quit();

    }
}
