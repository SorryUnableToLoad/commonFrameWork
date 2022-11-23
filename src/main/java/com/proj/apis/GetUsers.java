package com.proj.apis;

import com.proj.config.factory.ApiConfigFactory;
import com.proj.utils.RequestBuilder;
import io.restassured.response.Response;

public final class GetUsers {
    private GetUsers() {
    }

    public static Response getUser(int pageNumber) {
        return RequestBuilder.requestForGetCall()
                .queryParam("page", pageNumber)
                .get(ApiConfigFactory.getConfig().listUserEndPoint());

    }

}
