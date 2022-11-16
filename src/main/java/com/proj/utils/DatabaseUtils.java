package com.proj.utils;

import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DatabaseUtils {
    public static Connection connect;

    /**
     * This method will connect the database connection
     */
    public static void connectToDB() {
        try {
            Driver dbDriver = new Driver();
            DriverManager.registerDriver(dbDriver);
            connect = DriverManager.getConnection("jdbc:postgresql://dev-customer-app.cmswamgmg8xz.us-east-1.rds.amazonaws.com/phoenix-dev",
                    "postgres", "ncf.dev.2019");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Connected to DataBase Successfully");
    }

    /**
     * This method will disconnect the database connection
     *
     * @throws SQLException
     */
    public static void disConnectToDB() {
        try {
            connect.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Disconnected to DataBase Successfully");
    }

    /**
     * This methods returns all the data from database
     *
     * @param query
     * @return
     */
    public static ResultSet getAllData(String query) {
        ResultSet result = null;
        try {
            result = connect.createStatement().executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * This method will validate the data and return the data
     *
     * @param query
     * @param expectedData
     * @param columnIndex
     * @return
     * @throws SQLException
     */
    public static String getTheDataFromDatabaseAndVerify(String query, int columnIndex, String expectedData) throws SQLException {
        ResultSet result = connect.createStatement().executeQuery(query);
        String actualData = null;
        while (result.next()) {
            //System.out.println(result.getString(columnindex));
            if (result.getString(columnIndex).equals(expectedData)) {
                actualData = result.getString(columnIndex);
            } else {
                actualData = "data is not present";
            }
        }
        return actualData;

    }

    /**
     * This method will validate the data and return the data
     *
     * @param query
     * @param columnindex
     * @param expectedData
     * @return
     * @throws SQLException
     */
    public static String getTheDataFromDatabaseAndVerify(String query, String expectedData, int columnindex) throws SQLException {
        ResultSet result = connect.createStatement().executeQuery(query);
        boolean flag = false;
        String actualData = null;
        while (result.next()) {
            try {
                if (result.getString(columnindex).equals(expectedData)) {
                    //System.out.println(result.getString(columnindex));
                    flag = true;
                    actualData = result.getString(columnindex);
                    break;
                } else {
                    actualData = "Data Is Not Present";
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (flag) {
            System.out.println("Data is verified and present");
        } else {
            System.out.println("Data is not present");
        }
        return actualData;
    }
}
