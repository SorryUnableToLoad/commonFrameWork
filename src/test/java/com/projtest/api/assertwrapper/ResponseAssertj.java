package com.projtest.api.assertwrapper;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.SoftAssertions;

import java.util.function.Predicate;

public class ResponseAssertj extends AbstractAssert<ResponseAssertj, Response> {

    private SoftAssertions softAssertions;

    private ResponseAssertj(Response response, Class<?> selfType) {
        super(response, selfType);
        softAssertions = new SoftAssertions();
    }

    public static ResponseAssertj assertThat(Response response) {
        return new ResponseAssertj(response, ResponseAssertj.class);
    }

    public ResponseAssertj statusCodeIs(int expectedStatusCode) {
        softAssertions.assertThat(actual.statusCode())
                //.withFailMessage(()->"Status Code Is Not as expected")
                .as("Status code is not as expected " + expectedStatusCode)
                .isEqualTo(expectedStatusCode);
        return this;
    }

    public ResponseAssertj hasContentType(ContentType contentType) {
        softAssertions.assertThat(actual.contentType())
                .as("Content type is not as excepted " + contentType)
                .isEqualTo(contentType);
        return this;
    }

    public ResponseAssertj hasContentType(String contentType) {
        softAssertions.assertThat(actual.contentType())
                .as("Content type is not as excepted " + contentType)
                .isEqualTo(contentType);
        return this;
    }

    public ResponseAssertj hasKeyWithValue(String key, String value) {
        softAssertions.assertThat(actual.getBody().jsonPath().getString(key))
                .as("Body key value is not as excepted " + value)
                .isEqualTo(value);
        return this;
    }

    public ResponseAssertj hasKeyWithValue(Predicate<Response> responsePredicate) {
        softAssertions.assertThat(responsePredicate).accepts();
        return this;
    }

    public ResponseAssertj hasHeaderKeyAndValue(String headerKey, String expectedHeaderValue) {
        softAssertions.assertThat(actual.header(headerKey))
                .as("Header key value is Not expected " + expectedHeaderValue)
                .isEqualTo(expectedHeaderValue);
        return this;
    }

    public ResponseAssertj responseTimeIsLessThan(long expectedTimeInMilliSeconds) {
        softAssertions.assertThat(actual.time())
                .as("Response time is more than expected " + expectedTimeInMilliSeconds)
                .isLessThanOrEqualTo(expectedTimeInMilliSeconds);
        return this;
    }

    public ResponseAssertj canBeDeserializedTo(Class clazz) {
        softAssertions.assertThatCode(() -> actual.as(clazz))
                .doesNotThrowAnyException();
        return this;
    }

    public void assertAll() {
        softAssertions.assertAll();
    }
}
