package com.proj.apis;

import com.proj.constants.IConstants;
import com.proj.utils.RequestBuilder;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import static com.proj.config.FrameworkConfigFactory.getConfig;
import static io.restassured.RestAssured.given;

public final class GetUsers {
    private GetUsers() {
    }

    public static Response getUser(int pageNumber) {
        return RequestBuilder.requestForGetCall()
                .queryParam("page", pageNumber)
                .get(IConstants.GET_USER);
    }

}
