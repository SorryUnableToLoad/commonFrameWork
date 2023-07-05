package com.proj.pages.optimus;

import com.proj.pages.optimus.homepage.*;
import com.proj.pages.optimus.sidemenubar.ModuleType;
import com.proj.pages.optimus.sidemenubar.SubModuleType;


public class HomePage {
    private final SideMenuBar sideBarMenuComponent;

    public HomePage() {
        this.sideBarMenuComponent = new SideMenuBar();
    }

    public CreateCity navigateToCities() {
        sideBarMenuComponent.clickOnModule(ModuleType.GLOBAL_MASTER)
                .clickOnSubModule(SubModuleType.CITIES);
        return new CreateCity();
    }
    public CreateState navigateToStates() {
        sideBarMenuComponent.clickOnModule(ModuleType.GLOBAL_MASTER)
                .clickOnSubModule(SubModuleType.STATES);
        return new CreateState();
    }
    public CreateCountry navigateToCountries() {
        sideBarMenuComponent.clickOnModule(ModuleType.GLOBAL_MASTER)
                .clickOnSubModule(SubModuleType.COUNTRIES);
        return new CreateCountry();
    }

    public CreateLanguage navigateToLanguage(){
        sideBarMenuComponent.clickOnModule(ModuleType.GLOBAL_MASTER)
                .clickOnSubModule(SubModuleType.LANGUAGES);
        return new CreateLanguage();
    }

    public CreateOperationCentre navigateToOperationCentre(){
        sideBarMenuComponent.clickOnModule(ModuleType.GLOBAL_MASTER)
                .clickOnSubModule(SubModuleType.OPERATION_CENTERS);
        return new CreateOperationCentre();

    }
}
