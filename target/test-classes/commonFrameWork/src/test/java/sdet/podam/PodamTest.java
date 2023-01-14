package sdet.podam;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;
import sdet.podam.pojo.Employee;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.io.File;
import java.io.IOException;


public class PodamTest {

    File jsonBody = new File(System.getProperty("user.dir") + "/src/test/resources" +
            "/employee.json");

    @Test
    public void test() {
        PodamFactoryImpl podamFactory = new PodamFactoryImpl();
        Employee employee = podamFactory.manufacturePojo(Employee.class);
        System.out.println(employee);
    }

    @Test
    public void test1() {
        PodamFactoryImpl podamFactory = new PodamFactoryImpl();
        Employee employee1 = podamFactory.manufacturePojo(Employee.class);
        employee1.setId(202);
        employee1.setIsFTE(false);
        System.out.println(employee1);
    }

    @Test
    public void test2() throws IOException {
        PodamFactoryImpl podamFactory = new PodamFactoryImpl();
        Employee employee2 = podamFactory.manufacturePojo(Employee.class);
        System.out.println(employee2);

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");

        Employee employee = new ObjectMapper().readValue(jsonBody, Employee.class);
        Employee employee3 = podamFactory.populatePojo(employee);
        employee3.setLastName("Naganuri");
        System.out.println(employee3);
    }
}
