package com.projtest.api;

import com.proj.annotations.FrameworkAnnotations;
import com.proj.apis.GetUsers;
import com.proj.utils.ResponseAssert;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class GetUsersTest {
    @Test
    @FrameworkAnnotations(author = "SurajkumarN")
    void getUserByPageNumber() {

        Response response = GetUsers.getUser(2);
        //System.out.println(response.then().log().all());


        /*response.then()
                .assertThat()
                .statusCode(200)
                .time(Matchers.lessThan(ApiConfigFactory.getConfig().responseTime()))
                .contentType(ContentType.JSON);*/
        //ExtentReport.logResponse(response.prettyPrint());

        /*Predicate<Response> responsePredicate=res->res.jsonPath()
                .getString("email")
                .equalsIgnoreCase("george.edwards@reqres.in");*/


        /*ResponseAssertj.assertThat(response)
                .statusCodeIs(2001)
                .hasKeyWithValue("page","2")
                .hasKeyWithValue(responsePredicate)
                .hasContentType(ContentType.JSON)
                .hasContentType("application/json; charset=utf-8")
                .assertAll();*/


        ResponseAssert.asserThat(response)
                .statusCodeIs(201)
                .responseTimeIsLessThan(1800l)
                .hasHeaderKeyAndValue("Content-Type","application/json")
                .hasContentType("application/json")
                .hasKeyWithValue("page","3")
                .assertAll();

    }
}
