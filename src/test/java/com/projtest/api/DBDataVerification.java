package com.projtest.api;

import com.proj.utils.DatabaseUtils;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class DBDataVerification {
    @Test
    void test2() throws SQLException {
        DatabaseUtils.connectToDB();
        String query = "select * from businesses";
        String data = DatabaseUtils.getTheDataFromDatabaseAndVerify(query, "TEstCode", 2);
        System.out.println(data);
        DatabaseUtils.disConnectToDB();
    }
}
