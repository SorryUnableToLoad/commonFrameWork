package com.proj.pages.homepage;

import org.openqa.selenium.By;

import static com.proj.utils.PageActionsHelper.clickOnElement;

public class HomePage {

    public HomePage() {
    }

    // Section//
    //-----------------------------------------------------------//

    //-----------------------------------------------------------//

    // Bottom Navigational Bar //
    //-----------------------------------------------------------//
    public static final By HOME_PAGE_BUTTON = By.id("Home");
    public static final By CATEGORIES_PAGE_BUTTON = By.id("Categories");
    public static final By MYCART_PAGE_BUTTON = By.id("My cart");
    public static final By ACCOUNT_PAGE_BUTTON = By.id("Account");

    public void clickOnHomePageButton() {
        clickOnElement(HOME_PAGE_BUTTON);
    }

    public void clickOnCategoriesPageButton() {
        clickOnElement(CATEGORIES_PAGE_BUTTON);
    }

    public void clickOnMyCartPageButton() {
        clickOnElement(MYCART_PAGE_BUTTON);
    }

    public void clickOnAccountPageButton() {
        clickOnElement(ACCOUNT_PAGE_BUTTON);
    }
    //-----------------------------------------------------------//

    // Recent Orders Section //
    //-----------------------------------------------------------//
    public static final By recentOrderCards = By.className("android.view.View");
    public static final By VIEW_ALL_BUTTON_OF_RECENT_CARDS = By.xpath("(//android.view.View[@content-desc=\"View All \"])[2]");

    public void clickOnRecentOrderCards() {
        clickOnElement(recentOrderCards);
    }

    public void clickOnViewAllButtonOfRecentCards() {
        clickOnElement(VIEW_ALL_BUTTON_OF_RECENT_CARDS);
    }


    //-----------------------------------------------------------//

    // OutStandingAmount Section //
    //-----------------------------------------------------------//
    public static final By ACCOUNT_STATEMENT_BUTTON = By.id("ACCOUNT STATEMENT");
    public static final By PAY_BUTTON = By.id("PAY");

    public void clickOnAccountStatementButton() {
        clickOnElement(ACCOUNT_STATEMENT_BUTTON);
    }

    public void clickOnPayButton() {
        clickOnElement(PAY_BUTTON);
    }
    //-----------------------------------------------------------//


}
