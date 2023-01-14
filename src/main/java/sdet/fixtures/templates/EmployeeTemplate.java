package sdet.fixtures.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.github.javafaker.Faker;
import sdet.fixtures.pojo.Address;
import sdet.fixtures.pojo.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmployeeTemplate implements TemplateLoader {

    private Object[] getFirstName() {
        Faker faker = new Faker();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(faker.name().firstName());
        }
        return list.toArray();
    }

    @Override
    public void load() {
        //Address validAddress = Fixture.from(Address.class).gimme("aValid");
        Fixture.of(Employee.class).addTemplate("valid", new Rule() {{
            add("id", uniqueRandom(101, 999));
            add("fname", random(getFirstName()));
            add("isFTE", random(true, false));
            add("roles", uniqueRandom(Arrays.asList("tester"), Arrays.asList("QA")));
            // add("address", random(new Address("2nd street", "mangalore")));
            add("address", one(Address.class, "valid"));
        }});
       /* Fixture.of(Employee.class).addTemplate("inValid", new Rule() {{
            add("id", uniqueRandom(101, 999));
            add("fname", random("suraj", "sukumar", "sudarshan", "mahesh"));
            add("isFTE", random(true, false));
            add("roles", uniqueRandom(Arrays.asList("tester"), Arrays.asList("QA")));
            add("address", random(new Address("2nd street", "mangalore")));
        }});
        Fixture.of(Employee.class).addTemplate("invalidId").inherits("valid", new Rule() {{
            add("id", uniqueRandom(11, 99));
        }});
        Fixture.of(Employee.class).addTemplate("invalidName").inherits("valid", new Rule() {{
            add("fname", random("sur-aj", "suku/mar", "sudar]shan", "mah[]esh"));
        }});
*/
        Fixture.of(Address.class).addTemplate("valid", new Rule() {{
            add("street", random("street1", "street3"));
            add("city", random("city1", "city3"));
        }});
    }
}
