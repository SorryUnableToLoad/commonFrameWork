package com.proj.api;

import com.proj.common.config.factory.ApiConfigFactory;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public final class BaseRequestSpecification {
    private BaseRequestSpecification() {
    }
    private static final String REQRES_BASE_URI = ApiConfigFactory.getConfig().apiBaseUrl();
    public static RequestSpecification getDefaultRequestSpec() {
        return RestAssured
                .given()
                .baseUri(REQRES_BASE_URI)
                .contentType(ContentType.JSON)
                .log()
                .all();
    }
}
