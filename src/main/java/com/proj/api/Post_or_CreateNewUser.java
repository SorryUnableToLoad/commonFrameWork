package com.proj.api;

import com.proj.api.pojos.UserDetails;
import com.proj.config.factory.ApiConfigFactory;
import com.proj.utils.apiutils.RequestBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Post_or_CreateNewUser {
    private Post_or_CreateNewUser() {
    }

    public static Response post_or_CreateNewUser(UserDetails userDetails) {
        RequestSpecification requestSpecification = RequestBuilder.requestForPostCall()
                .body(userDetails);
        //ExtentReport.logRequest(requestSpecification);

        Response response = requestSpecification
                .post(ApiConfigFactory.getConfig().postUserEndPoint());
        return response;
    }
}
