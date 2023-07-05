package com.proj.pages.optimus.sidemenubar;

public enum SubModuleType {
    CUSTOMER("Customer"),
    CITIES("Cities"),
    STATES("States"),
    COUNTRIES("Countries"),
    LANGUAGES("Languages"),
    OPERATION_CENTERS("Operation Center"),
    CUSTOMER_ADDRESS("Customer Address"),
    CUSTOMER_BUSINESS_TYPE("Customer Business Type"),
    CUSTOMER_CREDIT_LIMIT("Customer Credit Limits"),
    CUSTOMER_PREFERENCE("Customer Preference"),
    SEGMENT_CREDIT_LIMIT("Segment Credit Limits"),
    CUSTOMER_USER("Customer User"),
    EMPLOYEE("Employee"),
    CATEGORIES("Categories"),
    SUB_CATEGORIES("Sub Categories"),
    SPECIES("Species"),
    SPECS("Specs"),
    FORMS("Forms"),
    PROCESSING_LEVELS("Processing Levels"),
    SKUS("SKUs"),
    VERTICALS("Verticals"),
    CHANNELS("Channels"),
    SEGMENTS("Segments"),
    ALL_SEGMENTS_SKUS("All Segment SKUs"),



    OTHER("Other");


    private final String SubModuleName;

    public String getSubModuleName() {
        return SubModuleName;
    }

    SubModuleType(String SubModuleName) {
        this.SubModuleName = SubModuleName;
    }
}
