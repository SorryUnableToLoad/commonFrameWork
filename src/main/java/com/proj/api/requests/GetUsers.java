package com.proj.api.requests;

import com.proj.config.factory.ApiConfigFactory;
import com.proj.utils.apiutils.RequestBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public final class GetUsers {
    private GetUsers() {
    }

    public static Response getUser(int pageNumber) {
        RequestSpecification requestSpecification = RequestBuilder.requestForGetCall()
                .queryParam("page", pageNumber);
        //ExtentReport.logRequest(requestSpecification);
        Response response = requestSpecification
                .get(ApiConfigFactory.getConfig().listUserEndPoint());
        return response;

    }

}
