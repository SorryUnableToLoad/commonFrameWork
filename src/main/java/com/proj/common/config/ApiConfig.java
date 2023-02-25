package com.proj.common.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        //look for maven system property
        "system:properties",
        // look for jenkins environment property
        "system:env",
        //look for local directory properties file
        "file:${user.dir}/src/test/resources/api-resources/api-config.properties"
})
public interface ApiConfig extends Config {

    @Key("responseTime")
    long responseTime();

    @Key("api.BaseUrl")
    String apiBaseUrl();

    @Key("list.users")
    String listUserEndPoint();

    @Key("create.users")
    String postUserEndPoint();



    @Key("api.Baseurl")
    String apiBaseurl();

    @Key("add_new_pet_store")
    String addNewPetStore();
}
