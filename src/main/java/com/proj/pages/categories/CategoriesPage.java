package com.proj.pages.categories;

import org.openqa.selenium.By;

import static com.proj.utils.PageActionsHelper.clickOnElement;

public class CategoriesPage {

    public CategoriesPage() {

    }

    public static final By ALL_CATEGORIES = By.id("All Categories");
    public static final By FISH_CATEGORIES = By.id("Fish Freshwater, Seawater, Bengali, Prawns, Crabs, Processed, Others");
    public static final By CHICKEN_CATEGORIES = By.ById.id("Chicken Others");
    public static final By MUTTON = By.id("Mutton Others");
    public static final By EGGS = By.id("Eggs Eggs");
    public static final By OTHERS = By.id("Processed Others");

    public static final By ADD_SKU_BUTTON=By.xpath("(//android.view.View[@content-desc=\"Add\"])[1]");

    public void clickOnAllCategories() {
        clickOnElement(ALL_CATEGORIES);
    }

    public static void clickOnFishCategories() {
        clickOnElement(FISH_CATEGORIES);
    }

    public void clickOnChickenCategories() {
        clickOnElement(CHICKEN_CATEGORIES);
    }

    public void clickOnMuttonCategories() {
        clickOnElement(MUTTON);
    }

    public void clickOnEggsCategories() {
        clickOnElement(EGGS);
    }

    public void clickOnOthersCategories() {
        clickOnElement(OTHERS);
    }
    public void clickOnAddSKUButton() {
        clickOnElement(ADD_SKU_BUTTON);
    }

}
