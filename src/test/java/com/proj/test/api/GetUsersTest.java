package com.proj.test.api;

import com.proj.apis.GetUsers;
import com.proj.constants.IConstants;
import com.proj.test.base.APISetUp;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class GetUsersTest extends APISetUp {
    @Test
    void getUserByPageNumber() {

        Response response = GetUsers.getUser(2);
        response.then()
                .assertThat()
                .statusCode(200)
                .time(Matchers.lessThan(IConstants.responseTime))
                .contentType(ContentType.JSON);
        response.prettyPrint();

    }
}
