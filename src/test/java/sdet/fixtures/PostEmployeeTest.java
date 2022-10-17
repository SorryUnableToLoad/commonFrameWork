package sdet.fixtures;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import sdet.fixtures.pojo.Address;
import sdet.fixtures.pojo.Employee;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class PostEmployeeTest {

    @BeforeSuite
    public void setUp(){
        FixtureFactoryLoader.loadTemplates("sdet.fixtures.templates");
    }
    @DataProvider(parallel = true)
    public static Object[][] getData() {
        Employee valid = Fixture.from(Employee.class).gimme("valid");
        Employee invalidid = Fixture.from(Employee.class).gimme("invalidid");
        Employee invalidfname=Fixture.from(Employee.class).gimme("invalidfname");
        return new Object[][]{
                {valid},
                {invalidid},
                {invalidfname}
        };

    }

    @Test(dataProvider = "getData")
    public void postEmployeeTest(Employee employee){
        Response response = given().contentType(ContentType.JSON)
                .baseUri("")
                .log()
                .all()
                .body(employee)
                .post("");
        
        response.then().assertThat().statusCode(201);
        System.out.println(employee);
    }
}
