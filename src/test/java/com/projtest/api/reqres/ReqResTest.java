package com.projtest.api.reqres;

import com.proj.api.ReqResApis;
import com.proj.api.pojos.UserDetails;
import com.proj.api.pojos.UserResponseDetails;
import com.proj.common.base.APISetUp;
import com.projtest.api.assertwrapper.ResponseAssertj;
import com.projtest.api.reqres.testdata.UserTestData;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

class ReqResTest extends APISetUp {

    @Test
    public void GetListOfUserTest() {
        //Intention -> Make api call and get the response
        //AAA
        Response response = ReqResApis.getUser(2);

        response.then().log().all();
        logger.info("----------------------------------------------------------------");
        //Assertions
        int actualStatusCode = response.statusCode();
        Assertions.assertThat(actualStatusCode).isEqualTo(200);
    }

    @Test
    public void postNewUserTest() {

        UserDetails userDetails = UserTestData.getUserDetails();

        //sending request with user details payload
        Response response = ReqResApis.post_or_CreateNewUser(userDetails);

        response.then().log().all();
        logger.info("----------------------------------------------------------------");

        //Assertions
        Predicate<Response> predicate = res -> res.jsonPath()
                .getString("job")
                .equalsIgnoreCase("leader");

        ResponseAssertj.assertThat(response)
                .statusCodeIs(201)
                .canBeDeserializedTo(UserResponseDetails.class)
                .hasKeyWithValue("job", "leader")
                .hasKeyWithValue(predicate)
                .hasContentType("application/json; charset=utf-8")
                .assertAll();
    }
}
