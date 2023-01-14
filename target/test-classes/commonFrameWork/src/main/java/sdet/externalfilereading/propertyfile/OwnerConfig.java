package sdet.externalfilereading.propertyfile;

import org.aeonbits.owner.Config;

import java.util.List;


@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"file:${user.dir}/src/test/resources/prop.properties"
})
public interface OwnerConfig extends Config {
    String browser();

    List favtools();

    String loves();
}
