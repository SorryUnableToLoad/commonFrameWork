package com.proj.utils.apiutils;

import com.proj.config.factory.ApiConfigFactory;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RequestBuilder {
    private RequestBuilder() {
    }

    public static RequestSpecification requestForGetCall() {
        return given()
                .baseUri(ApiConfigFactory.getConfig().apibaseurl())
                .contentType(ContentType.JSON)
                .log()
                .all();
    }

    public static RequestSpecification requestForPostCall() {
        return requestForGetCall();
    }
}
