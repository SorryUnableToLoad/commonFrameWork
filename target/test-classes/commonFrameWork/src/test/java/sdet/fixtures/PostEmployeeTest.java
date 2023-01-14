package sdet.fixtures;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import sdet.fixtures.pojo.Employee;

import java.util.List;

public class PostEmployeeTest {

    @BeforeClass
    public void setUp() {
        FixtureFactoryLoader.loadTemplates("sdet.fixtures");
    }

    @Test
    public void test() {
        List<Employee> valid = Fixture.from(Employee.class).gimme(10, "valid");
        valid.forEach(System.out::println);
    }



   /* @DataProvider(parallel = true)
    public static Object[][] getData() {
        Employee valid = Fixture.from(Employee.class).gimme("valid");
        Employee invalidId = Fixture.from(Employee.class).gimme("invalidId");
        Employee invalidName = Fixture.from(Employee.class).gimme("invalidName");
        return new Object[][]{
                {valid},
                {invalidId},
                {invalidName}
        };

    }

    @Test(dataProvider = "getData")
    public void postEmployeeTest(Employee employee) {
        Response response = given().contentType(ContentType.JSON)
                .baseUri("")
                .log()
                .all()
                .body(employee)
                .post("");

        response.then().assertThat().statusCode(201);
        System.out.println(employee);
    }*/

}
