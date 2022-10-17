package sdet.fixtures;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import sdet.fixtures.pojo.Address;
import sdet.fixtures.pojo.Employee;

import java.util.Arrays;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        Employee employee = new Employee(11, "suraj", true, new Address("1st street", "delhi"), Arrays.asList("manager"));
        System.out.println(employee);

        Fixture.of(Employee.class).addTemplate("valid",new Rule(){{
            add("id",uniqueRandom(101,999));
            add("fname",random("suraj","sukumar","sudarshan","mahesh"));
            add("isFTE",random(true,false));
            add("roles",uniqueRandom(Arrays.asList("tester"),Arrays.asList("QA")));
            add("address",random(new Address("2nd street","mangalore")));
        }});

        List<Employee> valid = Fixture.from(Employee.class).gimme(3,"valid");
        //System.out.println(valid);
        valid.forEach(System.out::println);

    }
}
