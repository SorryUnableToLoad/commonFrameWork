package com.proj.api;

import com.proj.api.pojos.UserDetails;
import com.proj.common.config.factory.ApiConfigFactory;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public final class ReqResApis {
    private ReqResApis() {
    }

    private static final String LIST_USERS = ApiConfigFactory.getConfig().listUserEndPoint();
    private static final String POST_USER = ApiConfigFactory.getConfig().postUserEndPoint();

    public static Response getUser(int pageNumber) {
        RequestSpecification requestSpecification = BaseRequestSpecification.getDefaultRequestSpec()
                .queryParam("page", pageNumber);
        //ExtentReport.logRequest(requestSpecification);
        return requestSpecification.get(LIST_USERS);
    }

    public static Response post_or_CreateNewUser(UserDetails userDetails) {
        RequestSpecification requestSpecification = BaseRequestSpecification.getDefaultRequestSpec()
                .body(userDetails);
        //ExtentReport.logRequest(requestSpecification);
        return requestSpecification.post(POST_USER);
    }

    /*private static final String LIST_USERS_ENDPOINT = ApiConfigFactory.getConfig().listUserEndPoint();
    private static final String POST_USERS_ENDPOINT = ApiConfigFactory.getConfig().postUserEndPoint();

    public static Response getUsers() {
        RequestSpecification requestSpecification = BaseRequestSpecification.getDefaultRequestSpec()
                .queryParam("page", 2);
        System.out.println("================================================================");
        return requestSpecification.get(LIST_USERS_ENDPOINT);
    }

    public static Response postUsers(UserDetails userDetails) {
        RequestSpecification requestSpecification = BaseRequestSpecification.getDefaultRequestSpec()
                .body(userDetails);
        System.out.println("================================================================");
        return requestSpecification.post(POST_USERS_ENDPOINT);
    }*/
}
