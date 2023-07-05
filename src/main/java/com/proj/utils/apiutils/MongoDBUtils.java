package com.proj.utils.apiutils;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class MongoDBUtils {

    public static MongoClient mongoClient;
    public static MongoDatabase database;
    public MongoCollection<Document> collection;

    /**
     * @param mongoPrefix Prefix
     * @param userName DB username
     * @param password DB password
     * @param urlAtSymbol url starting with @ symbol
     * @param dbName database name
     * @param collectionName collection name in the database
     * @return this method returns MongoClient
     */
    public MongoClient mongodbOpenConnection(String mongoPrefix, String userName, String password, String urlAtSymbol, String dbName, String collectionName){
        try{
            String completeUrl = mongoPrefix + "://" + userName + ":" + password + urlAtSymbol;
            mongoClient = MongoClients.create(completeUrl);
            database = mongoClient.getDatabase(dbName);
            changeCollection(collectionName);
        }catch (Exception e){
            LoggerUtils.info("Connection to mongodb failed");
            e.printStackTrace();;
        }
        return mongoClient;
    }

    /**
     * Use this method to close the connection to mongoDB
     */
    public void mongodbCloseConnection() {
        if (mongoClient != null){
            try{
                mongoClient.close();
                System.out.println("DB connection closed");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param collectionName Any collection name in specific database
     * This method changes the collection to provided value
     */
    public void changeCollection(String collectionName){
        if(mongoClient == null){
            LoggerUtils.info("Please open connection to mongodbClient and then call changeCollection method");
            System.exit(0);
        }
        collection = database.getCollection(collectionName);
        LoggerUtils.info("Using collection: " + collectionName);
    }

    /**
     * This method will turn off the unnecessary logging messages from mongoDB
     */
    public void turnOffMongodbLogger(){
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger rootLogger = loggerContext.getLogger("org.mongodb.driver");
        rootLogger.setLevel(Level.OFF);
    }

    /**
     * @param operation provide values like eq, ne, gt, gte, lt, lte, regex.
     * @param fieldName field name in the mongo object
     * @param value value of the field name
     * This method filters the data based on the condition provided.
     * @return returns Bson filter
     */
    public Bson filterBy(String operation, String fieldName, String value){
        Bson filter = null;
        switch (operation){
            case "eq":
                if(fieldName == "_id") filter = Filters.eq(fieldName, new ObjectId(value));
                else filter = Filters.eq(fieldName,value);
                break;
            case "ne":
                if(fieldName == "_id") filter = Filters.ne(fieldName, new ObjectId(value));
                else filter = Filters.ne(fieldName,value);
                break;
            case "regex":
                if(fieldName == "_id") {System.err.println("regex cannot take _id as input"); System.exit(0);}
                else filter = Filters.regex(fieldName,value);
                break;
            case "gt":
                if(fieldName == "_id") filter = Filters.gt(fieldName, new ObjectId(value));
                else filter = Filters.gt(fieldName,value);
                break;
            case "gte":
                if(fieldName == "_id") filter = Filters.gte(fieldName, new ObjectId(value));
                else filter = Filters.gte(fieldName,value);
                break;
            case "lt":
                if(fieldName == "_id") filter = Filters.lt(fieldName, new ObjectId(value));
                else filter = Filters.lt(fieldName,value);
                break;
            case "lte":
                if(fieldName == "_id") filter = Filters.lte(fieldName, new ObjectId(value));
                else filter = Filters.lte(fieldName,value);
                break;
            default:
                System.err.println("Please provide valid filter operation");
                System.exit(0);
        }
        return filter;
    }

    /**
     * @param jsonStringFilter Provide input string in json format, for ex: "{\"abc.xyz\":{$lt:10}}", "{\"name\":\"Raghav\"}"
     * @return returns Bson filter
     */
    public Bson filterBy(String jsonStringFilter){
        return Document.parse(jsonStringFilter);
    }

    /**
     * @param filter1 Provide Bson filter as input value 1
     * @param filter2 Provide Bson filter as input value 2
     * @return returns Bson filter
     * This method takes 2 bson filters as input, uses AND operation to filter the data.
     */
    public Bson andFilter(Bson filter1, Bson filter2){
        return Filters.and(filter1, filter2);
    }

    /**
     * @param filter1 Provide Bson filter as input value 1
     * @param filter2 Provide Bson filter as input value 2
     * @return returns Bson filter
     * This method takes 2 bson filters as input, uses OR operation to filter the data.
     */
    public Bson orFilter(Bson filter1, Bson filter2){
        return Filters.or(filter1, filter2);
    }

    /**
     * @param fieldName field name in the mongo object
     * @param values list of values for particular field
     * @return returns Bson filter
     * This method takes multiples values to filter the data.
     */
    public Bson inFilter(String fieldName, String[] values){
        return Filters.in(fieldName, values);
    }

    /**
     * @param filter input bson filter
     * @return returns list of Documents filtered through bson filter
     */
    public List<Document> getDataFromFilter(Bson filter){
        MongoCursor<Document> cursor = null;
        List<Document> lst = new ArrayList<>();
        try{
            cursor = collection.find(filter).iterator();
            while (cursor.hasNext()) lst.add(cursor.next());
        }catch (Exception e){
            System.err.println("Please open connection to MongoDB OR provide valid filter");
            e.printStackTrace();
        } finally {
            if(cursor!=null) cursor.close();
        }
        if(lst.isEmpty()) System.err.println("There is no data to display, please check provided filter/query: "+ filter.toString());//chl how to handle runtime exceptions
        return lst;
    }

    /**
     * @param doc Single mongoDB document
     * @param fieldName any key from the provided document in "SingleStrin" or "str1.str2" format
     * @return returns the filtered value as a String
     */
    public String getSingleValueFromDocument(Document doc, String fieldName){
        String[] val = fieldName.split("\\.");
        String s = null;
        try{
            if(val.length == 1) s = doc.get(fieldName).toString();
            else if(val.length == 2) s = doc.get(val[0], Document.class).get(val[1]).toString();
            else System.err.println("Please provide key only in singleString or str1.str2 format");
        }catch (Exception e){
            System.err.println("Please provide valid doc/key: " +fieldName);
            e.printStackTrace();
        }
        return s;
    }
}