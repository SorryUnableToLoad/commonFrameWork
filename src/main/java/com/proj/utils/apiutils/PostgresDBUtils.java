package com.proj.utils.apiutils;

import com.proj.utils.LoggerUtils;

import java.sql.*;
import java.util.HashMap;


/**
 * @author nishanth.t
 */
public class PostgresDBUtils {

    public static Connection con = null;
    public static String combinedURL;

    /**
     * @author Raghavendra YT
     * @param host Host for the database
     * @param port port number
     * @param uname DB username
     * @param pass DB password
     * @param dbName database name
     */
    public Connection connectPostgresDB(String host, String port, String uname, String pass, String dbName) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        try {
            combinedURL = "jdbc:postgresql://" + host + port + dbName;

            con = DriverManager.getConnection(combinedURL, uname, pass);

            LoggerUtils.info("Connecting to: " + host + " with " + uname + " and " + pass);
            LoggerUtils.info("DB Connection is successful");

        }catch (SQLException e) {
            LoggerUtils.info(e.getMessage());
        }
        return con;
    }

    /**
     * @author Raghavendra YT
     * @param completeUrl Provide complete postgres url with db name
     * @param uname DB username
     * @param pass DB password
     */
    public Connection connectPostgresDB(String completeUrl, String uname, String pass) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");

        try {
            con = DriverManager.getConnection(completeUrl, uname, pass);
            LoggerUtils.info("Connecting to: " + completeUrl + " with " + uname + " and " + pass);
            LoggerUtils.info("DB Connection is successful");
        }catch (SQLException e) {
            LoggerUtils.info("Unable to Connect to: " + completeUrl + " with " + uname + " and " + pass);
            LoggerUtils.info("Invalid Url. Please provide Valid Url");
            LoggerUtils.info(e.getMessage());
        }
        return con;
    }


    public ResultSet buildQuery(String sqlQuery) throws SQLException {
        if(con == null) {
            LoggerUtils.info("Please connect to the Database before hitting the query");
            System.exit(0);
        }
        return con.createStatement().executeQuery(sqlQuery);
    }

    public void disconnectPostgresDB() throws SQLException {
        if (con != null){
            try{
                con.close();
                LoggerUtils.info("DB Connection Closed Successfully");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * @author Raghavendra YT
     * @param tableName Database table name
     * @param columnNames list of column names {id, name, etc..} OR {""} to display all columns
     * @param whereConditions list of conditions like {"columnName < 10", "columnName like 'xyz%'", "columnName in (10, 11)"} OR {""} for without conditions
     */
    public String generateSelectQuery(String tableName, String[] columnNames, String[] whereConditions){
        String columnName = "";
        String query = "SELECT ";
        if(columnNames.length!=0){
            for(int i=0;i<columnNames.length;i++){
                if(i != columnNames.length-1) columnName += columnNames[i] + ", ";
                else columnName += columnNames[i];
            }
            query+=columnName;
        }else query+="*";

        query+=" FROM " + tableName;

        if(whereConditions.length!=0){
            query+=  " WHERE ";
            for(int i=0;i<whereConditions.length;i++){
                if(i != whereConditions.length-1) query+= whereConditions[i] + " AND ";
                else query+= whereConditions[i];
            }
        }
        LoggerUtils.info("Generated Select Query: " + query);
        return query;
    }

    /**
     * @author Raghavendra YT
     * @param tableName Database table name
     * @param columnValues list of all column values {id, name, etc..}
     */
    public String generateInsertQuery(String tableName, String[] columnValues){
        String query = "INSERT into " + tableName + " values(";
        String colValue = "";
        if(columnValues.length!=0){
            for(int i=0;i<columnValues.length;i++){
                if(i != columnValues.length-1)
                    colValue += "'" +columnValues[i] + "', ";
                else colValue += "'"+columnValues[i] + "')";
            }
            query+=colValue;
        }
        LoggerUtils.info("Generated Insert Query: " + query);
        return query;
    }

    /**
     * @author Raghavendra YT
     * @param tableName Database table name
     * @param updateValueConditions single or list of update conditions like {"columnName = 'xyz'", "id = 1"}
     * @param whereConditions list of conditions like {"columnName < 10", "columnName like 'xyz%'", "columnName in (10, 11)"}
     */
    public String generateUpdateQuery(String tableName, String[] updateValueConditions, String[] whereConditions){
        String query = "UPDATE " + tableName + " SET ";
        if(updateValueConditions.length!=0) {
            for (int i = 0; i < updateValueConditions.length; i++) {
                if (i != updateValueConditions.length - 1)
                    query += updateValueConditions[i] + ", ";
                else query += updateValueConditions[i] + " ";
            }
        }

        if(whereConditions.length!=0){
            query+=  "WHERE ";
            for(int i=0;i<whereConditions.length;i++){
                if(i != whereConditions.length-1) query+= whereConditions[i] + " AND ";
                else query+= whereConditions[i];
            }
        }
        LoggerUtils.info("Generated Update Query: " + query);
        return query;
    }

    /**
     * Displays all row data, but returns only last row as HashMap
     *
     * @author Raghavendra YT
     * @param completeUrl Provide complete postgres url with db name
     * @param uname DB username
     * @param pass DB password
     * @param selectQuery Provide direct select sql query OR generate using generateSelectSqlQuery() method
     */
    public HashMap<String, String> getDataUsingSelect(String completeUrl, String uname, String pass, String selectQuery) throws SQLException, ClassNotFoundException {

        // connectPostgresDB(String completeUrl, String uname, String pass)
        connectPostgresDB(completeUrl, uname, pass);

        LoggerUtils.info("Query:: " + selectQuery);

        HashMap<String, String> selectedData = new HashMap<>();
        try{
            ResultSet rs = buildQuery(selectQuery);
            if (rs != null){
                ResultSetMetaData md = rs.getMetaData();
                while (rs.next()){
                    for(int i=1;i<=md.getColumnCount();i++){
                        LoggerUtils.info(md.getColumnName(i) + ":" + rs.getString(i) + "");
                        selectedData.put(md.getColumnName(i), rs.getString(i));
                    }

                }
                rs.close();
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        disconnectPostgresDB();
        return selectedData;
    }

    /**
     * @author Raghavendra YT
     * @param completeUrl Provide complete postgres url with db name
     * @param uname DB username
     * @param pass DB password
     * @param insertQuery Provide direct insert sql query OR generate using generateInsertSqlQuery() method
     */
    public void insertCompleteRowIntoTable(String completeUrl, String uname, String pass, String insertQuery) throws SQLException, ClassNotFoundException {
        connectPostgresDB(completeUrl, uname, pass);
        LoggerUtils.info("Query: " + insertQuery);
        try{
            PreparedStatement ps = con.prepareStatement(insertQuery);
            ps.execute();
            LoggerUtils.info("Row values are inserted: ");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        disconnectPostgresDB();
    }


    /**
     * @author Raghavendra YT
     * @param completeUrl Provide complete postgres url with db name
     * @param uname DB username
     * @param pass DB password
     * @param updateQuery Provide direct update sql query OR generate using generateUpdateSqlQuery() method
     */
    public void updateDataInExistingRow(String completeUrl, String uname, String pass, String updateQuery) throws SQLException, ClassNotFoundException {
        connectPostgresDB(completeUrl, uname, pass);
        LoggerUtils.info("Query: " + updateQuery);
        try{
            PreparedStatement ps = con.prepareStatement(updateQuery);
            ps.execute();
            LoggerUtils.info("Row values are updated: ");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        disconnectPostgresDB();
    }
}

