package javaPrectice.consumer_interface;

import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class Use_Case_Test_2 {
    private static void selectValueFromDropDown(WebElement element, String textorvalueorindex, String strategy) {
        Select select = new Select(element);
        if (strategy.equalsIgnoreCase("text")) {
            select.selectByVisibleText(textorvalueorindex);
        } else if (strategy.equalsIgnoreCase("value")) {
            select.selectByValue(textorvalueorindex);
        } else if (strategy.equalsIgnoreCase("index")) {
            select.selectByIndex(Integer.parseInt(textorvalueorindex));
        } else {
            System.out.println("Invalid strategy");
        }
    }

    private static void selectFromDropDown(Consumer<Select> consumer, WebElement element) {
        Select select = new Select(element);
        consumer.accept(select);
    }

    @Test
    void testRunner() {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://demoqa.com/select-menu");

        WebElement oldSelectMenu = driver.findElement(By.id("oldSelectMenu"));

        selectValueFromDropDown(oldSelectMenu, "Magenta", "text");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);

        selectValueFromDropDown(oldSelectMenu, "1", "value");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);

        selectValueFromDropDown(oldSelectMenu, "3", "index");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);

        driver.quit();

    }

    @Test
    void testRunner1() {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://demoqa.com/select-menu");

        WebElement oldSelectMenu = driver.findElement(By.id("oldSelectMenu"));

        selectFromDropDown(e -> e.selectByVisibleText("Magenta"), oldSelectMenu);
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);

        selectFromDropDown(e -> e.selectByValue("1"), oldSelectMenu);
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);

        selectFromDropDown(e -> e.selectByIndex(3), oldSelectMenu);
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);

        List<WebElement> multiOptionsDropDown = new Select(driver.findElement(By.id("cars"))).getOptions();

        multiOptionsDropDown.forEach(e -> e.click());
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);

        multiOptionsDropDown.stream().skip(2).forEach(e -> e.click());
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);


        driver.quit();
    }
}
