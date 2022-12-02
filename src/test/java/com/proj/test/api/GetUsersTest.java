package com.proj.test.api;

import com.proj.annotations.FrameworkAnnotations;
import com.proj.apis.GetUsers;
import com.proj.config.factory.ApiConfigFactory;
import com.proj.listeners.extentreport.ExtentReport;
import com.proj.test.base.APISetUp;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class GetUsersTest extends APISetUp {
    @Test
    @FrameworkAnnotations(author = "SurajkumarN")
    void getUserByPageNumber() {

        Response response = GetUsers.getUser(2);

        response.then()
                .assertThat()
                .statusCode(200)
                .time(Matchers.lessThan(ApiConfigFactory.getConfig().responseTime()))
                .contentType(ContentType.JSON);
        ExtentReport.logResponse(response.prettyPrint());

    }
}
