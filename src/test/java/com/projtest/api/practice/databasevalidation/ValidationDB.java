package com.projtest.api.practice.databasevalidation;

import com.proj.utils.apiutils.DatabaseUtils;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class ValidationDB {
    @Test
    public void DataVerificationInDataBaseTest() throws SQLException {
        DatabaseUtils.connectToDB();
        String query = "select * from businesses";
        String data = DatabaseUtils.getTheDataFromDatabaseAndVerify(query, "TEstCode", 2);
        System.out.println(data);
        DatabaseUtils.disConnectToDB();
    }
}
