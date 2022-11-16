package com.proj.utils;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static com.proj.config.FrameworkConfigFactory.getConfig;
import static io.restassured.RestAssured.given;

public class RequestBuilder {
    private RequestBuilder(){}
    public static RequestSpecification requestForGetCall() {
        return given()
                .baseUri(getConfig().baseUri())
                .contentType(ContentType.JSON)
                .log()
                .all();
    }

    public static RequestSpecification requestForPostCall() {
        return requestForGetCall();
    }
}
