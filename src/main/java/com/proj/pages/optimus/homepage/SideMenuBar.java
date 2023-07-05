package com.proj.pages.optimus.homepage;

import com.proj.pages.optimus.sidemenubar.ModuleType;
import com.proj.pages.optimus.sidemenubar.SubModuleType;
import org.openqa.selenium.By;

import static com.proj.utils.UI_Utils.ActionHelper.clickOnElement;

public class SideMenuBar {
    private static final String MODULE = "//span[text()='%s']";
    private static final String SUB_MODULE = "//a[text()='%s']";

    public SideMenuBar clickOnModule(ModuleType moduleType) {
        String xpath = String.format(MODULE, moduleType.getModuleName());
        clickOnElement(By.xpath(xpath));
        return this;
    }

    public SideMenuBar clickOnSubModule(SubModuleType subModuleType) {
        String xpath = String.format(SUB_MODULE, subModuleType.getSubModuleName());
        clickOnElement(By.xpath(xpath));
        return this;
    }
}
