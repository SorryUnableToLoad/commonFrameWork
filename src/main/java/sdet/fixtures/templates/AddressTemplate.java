package sdet.fixtures.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import sdet.fixtures.pojo.Address;

public class AddressTemplate implements TemplateLoader {
    @Override
    public void load() {
        Fixture.of(Address.class).addTemplate("avalid",new Rule(){{
            add("street",random("street1","street3"));
            add("city",random("city1","city3"));
        }});

    }
}
