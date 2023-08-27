package com.projtest.databasequery;

import com.proj.utils.apiutils.PostgresDBUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBTest {

    private static final String DATABASE_URL = "jdbc:postgresql://dev-customer-app.cmswamgmg8xz.us-east-1.rds.amazonaws.com/promotion-dev";
    private static final String DATABASE_USERNAME = "postgres";
    private static final String DATABASE_PASSWORD = "ncf.dev.2019";
    PostgresDBUtils pdb = new PostgresDBUtils();

    @BeforeMethod
    void setup() throws Exception {
        pdb.connectPostgresDB(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

    }

    @AfterMethod
    void teardown() throws Exception {
        pdb.disconnectPostgresDB();
    }

    @Test
    public void test() throws SQLException, ClassNotFoundException {

        ResultSet result = pdb.buildQuery("select id,name from media where name='promobanner.png'");



        while (result.next()) {
            System.out.println(result.getString("id")+"\t"+result.getString("name"));
        }






       /* while (result.next()) {
            System.out.println(result.getString("name"));
            System.out.println(result.getString(1) + "\t" + result.getString(3) + "\t" +
                    result.getString(4) + "\t" + result.getString(6) + "\t" + result.getString(12));
            if (result.getString(1).equals(projectid)) {
                Assert.assertEquals(result.getString(1), projectid);
                Assert.assertEquals(result.getString(2).equals(createdby), true);
                Assert.assertEquals(result.getString(3).equals(createdon), true);
                Assert.assertEquals(result.getString(4).equals(projectname), true);
                Assert.assertEquals(result.getString(5).equals(status), true);
                System.out.println("verified");
            }
        }*/

    }
}
