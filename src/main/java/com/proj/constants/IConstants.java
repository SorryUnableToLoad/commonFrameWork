package com.proj.constants;

public interface IConstants {
    long responseTime = 6000l;

    //----------file paths----------//
    String RESPONSE_TXTPATH = System.getProperty("user.dir")+"/output/responses/";
    String REQUEST_JSON_PATH = System.getProperty("user.dir")+"/test/resources/customerPreferences.json";


    //----------pre-requiremnts like base uri and Oauth----------//
    String BASEURI = "https://dapi.dev.captainfresh.in/phoenix";
    String USERNAME = "";
    String CLIENT_SECRET = "";
    String GRANT_TYPE = "";
    String CLIENT_ID = "";
    String PASSWORD = "";


    //----------End Points----------//

    //business module
    String GET_USER="/users";

    String GET_BUSINESS_BY_ID = "/customers/{id}";
    String GET_BUSINESS_PREFERENCES="/customers/{id}/details";
    String POST_CUSTOMER_PREFERENCES = "/customers/preference";

}
