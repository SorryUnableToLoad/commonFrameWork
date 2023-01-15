package com.projtest.api.assertwrapper;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;


public class ResponseAssert {
    private SoftAssert softAssert;
    static Response response;

    private ResponseAssert() {
        softAssert = new SoftAssert();
    }

    public static ResponseAssert asserThat(Response response1) {
        response = response1;
        return new ResponseAssert();
    }

    public ResponseAssert statusCodeIs(int expectedStatusCode) {
        softAssert.assertEquals(response.statusCode(), expectedStatusCode, "Status Code Is Not Equal as...");
        return this;
    }

    public ResponseAssert hasContentType(String expectedContentType) {
        softAssert.assertEquals(response.contentType(), expectedContentType, "ContentType Is Not Equal as...");
        return this;
    }

    public ResponseAssert hasKeyWithValue(String key, String value) {
        softAssert.assertEquals(response.jsonPath().getString(key), value, "Body Kay Value Is Not Matched as...");
        return this;
    }

    public ResponseAssert hasHeaderKeyAndValue(String headerKey, String expectedHeaderValue) {
        softAssert.assertEquals(response.header(headerKey), expectedHeaderValue, "Header Key Value Is Not Matched as...");
        return this;
    }

    public ResponseAssert responseTimeIsLessThan(long expectedTimeInMilliSeconds) {
        response.then().time(Matchers.lessThan(expectedTimeInMilliSeconds), TimeUnit.MILLISECONDS);
        //softAssert.assertEquals(response.time(), expectedTimeInMilliSeconds, "Response Time More Than Expected Value...");
        return this;
    }

    public ResponseAssert canBeDeserializedTo(Class clazz) {
        response.as(clazz);
        return this;
    }

    public void assertAll() {
        softAssert.assertAll();
    }
}
