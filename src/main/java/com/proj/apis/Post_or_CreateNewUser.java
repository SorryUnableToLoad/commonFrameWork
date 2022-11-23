package com.proj.apis;

import com.proj.apis.pojos.UserDetails;
import com.proj.config.factory.ApiConfigFactory;
import com.proj.utils.RequestBuilder;
import io.restassured.response.Response;

public class Post_or_CreateNewUser {
    private Post_or_CreateNewUser() {
    }

    public static Response post_or_CreateNewUser(UserDetails userDetails) {
        return RequestBuilder.requestForPostCall()
                .body(userDetails)
                .post(ApiConfigFactory.getConfig().postUserEndPoint());
    }
}
