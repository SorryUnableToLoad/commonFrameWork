package com.proj.pages.optimus.sidemenubar;

public enum ModuleType {

    CUSTOMER_ONBOARDING("Customer Onboarding"),
    CATALOG("Catalog"),
    PRICING_AND_PROMOTION("Pricing & Promotions"),
    ORDER_AND_INDENT("Orders & Indent"),
    FINANCE("Finance"),
    LAST_MILE("Last Mile"),
    PURCHASE("Purchase"),
    VENDOR_ONBOARDING("Vendor Onboarding"),
    FIRST_MILE("First Mile"),
    CENTRAL("Central"),
    GLOBAL_MASTER("Global Master"),
    UPLOAD_AND_EXPORT("Upload & Export"),
    TASKER("Tasker"),




    OTHER("Other");



    private final String moduleName;

    public String getModuleName() {
        return moduleName;
    }

    ModuleType(String moduleName) {
        this.moduleName = moduleName;
    }
}
