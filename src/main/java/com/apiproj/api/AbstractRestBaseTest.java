package com.apiproj.api;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.path.xml.XmlPath;
import com.apiproj.utils.UtilClass;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.testng.Assert;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

/**
 *
 * @author nishanth.t
 *
 */

public class AbstractRestBaseTest {
    protected static String sBaseUrl;
    protected static RestCallEngine sRestCallEngine;
    protected static Response sResponse;
    protected static Map<String, Response> sResponses;
    protected static JsonPath sJsonPath;
    protected static Map<String, JsonPath> sJsonResponses;
    protected static XmlPath sXmlPath;
    protected static Map<String, XmlPath> sXmlResponses;
    protected static Throwable failure;
    protected static Verify verify;
    protected static String testDataDir;
    protected static Level sLogLevel = Level.FINEST;
    // TODO move this to Util class?
    protected static final String LOG_LEVEL_PROP = "test.loglevel";

    public static UtilClass takeLog = new UtilClass();

    /**
     * Gets the base URL for the REST API end point back using the passed in
     * values
     *
     * @param host
     *            the host where REST API end point is
     * @param port
     *            the port where the REST API end point is running
     * @return the base URL for the REST API end point
     */
    protected static String getBaseURL(String baseurl) {
        String sBaseUrl = "http://" + baseurl;
        return sBaseUrl;
    }

    /**
     * Gets the base URL for the REST API end point back using system property
     * values list.hostname for the host, list.port for the port.
     *
     * @return the base URL for the REST API end point
     */
    protected static String getBaseURL() {
        return sBaseUrl;
    }

    /**
     * Gets the host information for the REST API end point.
     *
     * @return the host
     */
    protected static String getHost() {
        return sBaseUrl;
    }

    protected static void setTestDataDir() {
        testDataDir = testDataDir;
    }

    /**
     * Initializes references to base uri for the rest api endpoint, and then
     * creates the restCallEngine using this rest endpoint information.
     */
    protected static void init(String baseURL, String userName, String password) {
        resetFailure();
        sBaseUrl = getBaseURL(baseURL);
        sRestCallEngine = new RestCallEngine(sBaseUrl, createRestClient(userName, password));
        verify = new Verify();
        setTestDataDir();
    }

    /**
     * Store the response object in the class
     *
     * @param iResponse
     *            the response object to store
     */
    protected static void storeResponse(Response iResponse) {
        sResponse = iResponse;
    }

    /**
     * Get the stored response object back from the class
     *
     * @return the response object stored in the class
     */
    protected static Response getResponse() {
        return sResponse;
    }

    /**
     * Gets the response object for the specified test case name value
     *
     * @param key
     *            the test case name that the response is stored under
     * @return the response object for the specified test case name
     */
    protected static Response getResponse(String key) {
        return sResponses.get(key);
    }

    /**
     * Gets the response object for the specified test case
     *
     * @param restTestCase
     *            the test case object containing the name the response is
     *            stored under
     * @return the response object for the specified test case
     */
    protected static Response getResponse(CfAPI restTestCase) {
        if (restTestCase.isNameSet()) {
            return sResponses.get(restTestCase.getName());
        } else {
            return sResponse;
        }
    }

    /**
     * Stores the response object in the class (can store more than one in the
     * class at a time)
     *
     * @param key
     *            the name to store the response under
     * @param response
     *            the response object to store
     */
    protected static void storeResponse(String key, Response response) {
        if (sResponses == null) {
            sResponses = new HashMap<String, Response>();
        }
        sResponses.put(key, response);
    }

    /**
     * Store the response object in the class (can store more than one in the
     * class at a time)
     *
     * @param restTestCase
     *            the test case object that contains the name to store the
     *            response under
     * @param curResponse
     *            the response to be stored
     */
    protected static void storeResponse(CfAPI restTestCase, Response curResponse) {
        if (restTestCase.isNameSet()) {
            storeResponse(restTestCase.getName(), curResponse);
        } else {
            sResponse = curResponse;
        }
    }

    /**
     * Store the response body in xml format in the class (can store more than
     * one in class at a time)
     *
     * @param restTestCase
     *            the test case object containing the name to store the xml
     *            object under
     * @param curResponse
     *            the response object to store the body as an xml object for
     */
    protected static void storeXml(CfAPI restTestCase, Response curResponse) {
        if (restTestCase.getResponseType() != null) {
            if (restTestCase.getResponseType().equals(MediaType.APPLICATION_XML_TYPE)) {
                // String curXml = curResponse.asString();
                // XmlPath curXmlPath = new XmlPath(curXml);
                XmlPath curXmlPath = convertToXmlPath(curResponse);
                if (restTestCase.isNameSet()) {
                    storeXml(restTestCase.getName(), curXmlPath);
                } else {
                    sXmlPath = curXmlPath;
                }
            }
        }
    }

    /**
     * Store the response body in xml format in the class (can store more than
     * one in class at a time)
     *
     * @param key
     *            the name to store the xml object as
     * @param curXmlPath
     *            the xml object to store
     */
    protected static void storeXml(String key, XmlPath curXmlPath) {
        if (sXmlResponses == null) {
            sXmlResponses = new HashMap<String, XmlPath>();
        }
        sXmlResponses.put(key, curXmlPath);
    }

    /**
     * Store the response body in xml format in the class (can store more than
     * one in class at a time)
     *
     * @param key
     *            the name to store the xml object as
     * @param curResponse
     *            the response object containing the body to store as an xml
     *            object
     */
    protected static void storeXml(String key, Response curResponse) {
        // String curXml = curResponse.asString();
        // XmlPath curXmlPath = new XmlPath(curXml);
        XmlPath curXmlPath = convertToXmlPath(curResponse);
        storeXml(key, curXmlPath);
    }

    /**
     * Converts the response string into an xml Path object
     *
     * @param curResponse
     *            the response object containing the rest response
     * @return the XmlPath object based on the value in the response object
     */
    protected static XmlPath convertToXmlPath(Response curResponse) {
        String xmlString = "";
        XmlPath xmlPath;
        try {
            xmlString = curResponse.readEntity(String.class);
            xmlPath = new XmlPath(xmlString);
            if (sLogLevel.intValue() <= Level.FINE.intValue())
                xmlPath.prettyPrint();
            return xmlPath;
        } catch (Throwable t) {
            throw new RuntimeException("Failed to convert to XmlPath object, Response Code:  " + curResponse.getStatus()
                    + "  Error:  " + t.getMessage() + " XML:  " + xmlString);
        }
    }

    /**
     * Gets the response body as string if it is not null, or returns the string
     * null
     *
     * @param curResponse
     *            the Rest Assured response object
     * @return the string of the body of the rest assured response body.
     */
    protected static String getResponseAsString(Response curResponse) {
        try {
            return curResponse.readEntity(String.class);
        } catch (Throwable t) {
            return "null";
        }
    }

    /**
     * Gets the stored xml body from a response that was stored earlier.
     *
     * @return the xml body value that was stored earlier
     */
    protected static XmlPath getXml() {
        return sXmlPath;
    }

    /**
     * Gets the stored xml body from a response that was stored earlier for the
     * specified test name
     *
     * @param key
     *            the name of the test, or value that the xml was stored as
     * @return the stored xml body from a response that was stored earlier for
     *         the specified test name
     */
    protected static XmlPath getXml(String key) {
        return sXmlResponses.get(key);
    }

    /**
     * Gets the stored xml body from a response that was stored earlier for the
     * specified test
     *
     * @param restTestCase
     *            the test case object containing the name that the xml object
     *            was stored under
     * @return the stored xml body from a response that was stored earlier for
     *         the specified test
     */
    protected static XmlPath getXml(CfAPI restTestCase) {
        if (restTestCase.isNameSet()) {
            return getXml(restTestCase.getName());
        } else {
            return getXml();
        }
    }

    /**
     * Store the response body in json format in the class (can store more than
     * one in class at at time)
     *
     * @param restTestCase
     *            the test case object containing the name to store the json
     *            object under
     * @param curResponse
     *            the response object containing the body to store as an json
     *            object
     */
    protected static void storeJson(CfAPI restTestCase, Response curResponse) {
        if (restTestCase.getResponseType() != null) {
            if (!restTestCase.getResponseType().equals(MediaType.APPLICATION_JSON_TYPE)) {
                return;
            }
        }
        // String curJson = curResponse.asString();
        // JsonPath curJsonPath = new JsonPath(curJson);
        JsonPath curJsonPath = convertToJsonPath(curResponse);
        if (restTestCase.isNameSet()) {
            storeJson(restTestCase.getName(), curJsonPath);
        } else {
            sJsonPath = curJsonPath;
        }
    }

    /**
     * Store the response body in json format in the class (can store more than
     * one in class at at time)
     *
     * @param key
     *            the name to store the json object as
     * @param curJsonPath
     *            the json object to store
     */
    protected static void storeJson(String key, JsonPath curJsonPath) {
        if (sJsonResponses == null) {
            sJsonResponses = new HashMap<String, JsonPath>();
        }
        sJsonResponses.put(key, curJsonPath);
    }

    /**
     * Store the response body in json format in the class (can store more than
     * one in class at at time)
     *
     * @param key
     *            the name to store the json object as
     * @param curResponse
     *            the response object containing the body to store as an json
     *            object
     */
    protected static void storeJson(String key, Response curResponse) {
        // String curJson = curResponse.asString();
        // JsonPath curJsonPath = new JsonPath(curJson);
        JsonPath curJsonPath = convertToJsonPath(curResponse);
        storeJson(key, curJsonPath);
    }

    /**
     * Converts the response payload into a JsonPath object
     *
     * @param curResponse
     *            the response containing the response payload
     * @return a JsonPath object of the json payload from the response object
     */
    protected static JsonPath convertToJsonPath(Response curResponse) {
        String curJson = "";
        JsonPath curJsonPath;
        try {
            curJson = curResponse.readEntity(String.class);
            curJsonPath = new JsonPath(curJson);
            if (sLogLevel.intValue() <= Level.FINE.intValue())
                curJsonPath.prettyPrint();
            return curJsonPath;
        } catch (Throwable t) {
            throw new RuntimeException("Failed to convert to JsonPath object, Response Code:  "
                    + curResponse.getStatus() + "  Error:  " + t.getMessage() + " JSON:  " + curJson);
        }
    }

    /**
     * Gets a previously stored json object from a rest response
     *
     * @return a previously stored json object from a rest response
     */
    protected static JsonPath getJson() {
        return sJsonPath;
    }

    /**
     * Gets a previously stored json object from a rest response stored under
     * the specified name
     *
     * @param key
     *            the name where the json object is stored
     * @return a previously stored json object from a rest response stored under
     *         the specified name
     */
    protected static JsonPath getJson(String key) {
        return sJsonResponses.get(key);
    }

    /**
     * Gets a previously stored json object from a rest response stored under
     * the specified test case
     *
     * @param restTestCase
     *            the test case object with the name that the json result is
     *            stored undere
     * @return a previously stored json object from a rest response stored under
     *         the specified test case
     */
    protected static JsonPath getJson(CfAPI restTestCase) {
        if (restTestCase.isNameSet()) {
            return getJson(restTestCase.getName());
        } else {
            return getJson();
        }
    }

    /**
     *
     * @param testCaseName
     * @return
     */
    protected static CfAPI getTestCase(String testCaseName) {
        return new CfAPI();
    }

    /**
     * Performs a rest call based on the information in the test case object and
     * returns the response
     *
     * @param restTestCase
     * @return the response object from the rest call
     */
    public static Response callRest(CfAPI restTestCase) {
        return callRest(restTestCase, false);
    }

    /**
     * Performs a rest call based on the information in the test case object and
     * stores the response in the class if specified, or returns the response
     * object if not.
     *
     * @param restTestCase
     *            The test case that contains the information about how the
     *            result should be returned, and the name of the test for
     *            storing the response
     * @param storeResponse
     *            true to store the response objects in the class for later use.
     * @return the response object from the rest call
     */
    public static Response callRest(CfAPI restTestCase, boolean storeResponse) {
        sResponse = null;
        sJsonPath = null;
        sXmlPath = null;
        Response curResponse = sRestCallEngine.callRest(restTestCase);
        if (storeResponse) {
            storeResponseAndResponseBody(restTestCase, curResponse);
        }
        return curResponse;
    }

    /**
     * Stores the response object, and the body as the type of object specified
     * by the rest test call object.
     *
     * @param restTestCall
     *            the rest test call object that contains the name the response
     *            will be stored as
     * @param curResponse
     *            the response object to be stored.
     */
    protected static void storeResponseAndResponseBody(CfAPI restTestCall, Response curResponse) {
        storeResponse(restTestCall, curResponse);
        storeXml(restTestCall, curResponse);
        storeJson(restTestCall, curResponse);
    }

    /**
     * Performs a rest call based on the information in the test case object,
     * checks to see if the status code for the rest call is the expected value
     *
     * @param restTestCall
     *            The test case that contains the information about how the
     *            result should be returned, and the name of the test for
     *            storing the response
     * @param storeResponse
     *            true to store the response objects in the class for later use.
     * @return the response object from the rest call
     */
    protected static Response callRestAndCheckStatusCode(CfAPI restTestCall, int expectedCode) {
        return callRestAndCheckStatusCode(restTestCall, expectedCode, false);
    }

    /**
     * Performs a rest call based on the information in the test case object,
     * checks to see if the status code for the rest call is the expected value,
     * stores the response in the class if specified, or returns the response
     * object if not.
     *
     * @param restTestCall
     *            The test case that contains the information about how the
     *            result should be returned, and the name of the test for
     *            storing the response
     * @param expectedCode
     *            the status code that is expected for the specified rest call
     * @param storeResponse
     *            true to store the response objects in the class for later use.
     * @return the response object from the rest call
     */
    protected static Response callRestAndCheckStatusCode(CfAPI restTestCall, int expectedCode, boolean storeResponse) {
        Response response = callRest(restTestCall);
        Verify quickVerify = new Verify();
        if (response.getStatus() != expectedCode) {
            String responseBody = getErrorMessageFromResponseBody(restTestCall, response);
            String failMessage = "The response was not the expected value., Expected Response Code:  " + expectedCode
                    + ", Actual Response Code:  " + response.getStatus() + ", Response Body:  " + responseBody;
            quickVerify.verifyEquals(failMessage, expectedCode, response.getStatus());
            quickVerify.assertErrors();
        } else {
            if (storeResponse) {
                storeResponseAndResponseBody(restTestCall, response);
            }
        }
        return response;
    }

    /**
     * Gets the error message from the body of the response object
     *
     * @param restTestCall
     *            the rest test call object containing info about the response
     *            type
     * @param response
     *            the response for the rest call
     * @return the error message from the response object.
     */
    protected static String getErrorMessageFromResponseBody(CfAPI restTestCall, Response response) {
        String body = response.readEntity(String.class);
        String error = "";
        try {
            if (restTestCall.getResponseType() != null) {
                if (restTestCall.getResponseType().equals(MediaType.APPLICATION_XML_TYPE)) {
                    XmlPath xmlPath = new XmlPath(body);
                    error = xmlPath.getString("errorMessage");
                } else {
                    JsonPath jsonPath = new JsonPath(body);
                    error = jsonPath.getString("errorMessage");
                }
            } else {
                JsonPath jsonPath = new JsonPath(body);
                error = jsonPath.getString("errorMessage");
            }
        } catch (Throwable t) {
            error = body;
        }
        return error;
    }

    /**
     * Checks to see if the response code for the stored response is the
     * specified value
     *
     * @param expRespCode
     *            the expected response code
     * @return true if the response code is the expected value, false if not
     */
    protected static boolean isResponseCode(int expRespCode) {
        int actRespCode = getResponseCode();
        return expRespCode == actRespCode;
    }

    /**
     * Checks to see if the response code for the stored response is the
     * specified value
     *
     * @param key
     *            the value where the response is stored
     * @param expRespCode
     *            the expected response code
     * @return true if the response code is the expected value, false if not
     */
    protected static boolean isResponseCode(String key, int expRespCode) {
        int actRespCode = getResponseCode(key);
        return expRespCode == actRespCode;
    }

    /**
     * Gets the response code from the stored response object
     *
     * @return the response code of the stored response object
     */
    protected static int getResponseCode() {
        return getResponse().getStatus();
    }

    /**
     * Gets the response code for the stored response for the specified key
     *
     * @param key
     *            the key to get the response code from
     * @return the response code for the stored response for the specified key
     */
    protected static int getResponseCode(String key) {
        return getResponse(key).getStatus();
    }

    /**
     * Gets the response code for the stored response code for the specified
     * rest call
     *
     * @param restCall
     *            the rest call object tht the response is stored under
     * @return the response code for the stored response code of the specified
     *         rest call
     */
    protected static int getResponseCode(CfAPI restCall) {
        if (restCall.isNameSet()) {
            return getResponseCode(restCall.getName());
        } else {
            return getResponseCode();
        }
    }

    /**
     * Checks to see if the response code for the stored response is the
     * specified value
     *
     * @param restTestCase
     *            the test case object that contains the name where the response
     *            is stored
     * @param expRespCode
     *            the expected response code
     * @return true if the response code is the expected value, false if not
     */
    protected static boolean isResponseCode(CfAPI restTestCase, int expRespCode) {
        if (restTestCase.isNameSet()) {
            return isResponseCode(restTestCase.getName(), expRespCode);
        } else {
            return isResponseCode(expRespCode);
        }
    }

    /**
     * Checks to see if the specified path in the stored xml result is present
     *
     * @param expXmlPath
     *            the path to the element to check for in the xml (use format
     *            from http://groovy.codehaus.org/Updating+XML+with+XmlSlurper)
     * @return true if the path is present in the REST Response, false if it is
     *         not
     */
    protected static boolean isXmlPathPresent(String expXmlPath) {
        try {
            getXml().getString(expXmlPath);
            return true;
        } catch (Throwable t) {
            t.printStackTrace();
            return false;
        }
    }

    /**
     * Checks to see if the specified path in the stored xml result is present
     *
     * @param key
     *            the value that the xml results is stored under
     * @param expXmlPath
     *            the path to the element to check for in the xml (use format
     *            from http://groovy.codehaus.org/Updating+XML+with+XmlSlurper)
     * @return true if the path is present in the xml, false if it is not
     */
    protected static boolean isXmlPathPresent(String key, String expXmlPath) {
        try {
            String actXmlPath = getXml(key).getString(expXmlPath);
            if (actXmlPath != null) {
                return true;
            } else {
                return false;
            }
        } catch (Throwable t) {
            t.printStackTrace();
            return false;
        }
    }

    /**
     * Checks to see if the specified path in the stored xml result is present
     *
     * @param restTestCase
     *            the test case object containing the name that the xml result
     *            is stored under.
     * @param expXmlPath
     *            the path to the element to check for in the xml (use format
     *            from http://groovy.codehaus.org/Updating+XML+with+XmlSlurper)
     * @return true if the path is present in the xml, false if it is not
     */
    protected static boolean isXmlPathPresent(CfAPI restTestCase, String expXmlPath) {
        if (restTestCase.isNameSet()) {
            return isXmlPathPresent(restTestCase.getName(), expXmlPath);
        } else {
            return isXmlPathPresent(expXmlPath);
        }
    }

    /**
     * Checks to see if the specified path in the stored json result is present
     *
     * @param expJsonPath
     *            the path to the element to check for in the json (use format
     *            from
     *            http://rest-assured.googlecode.com/svn/tags/1.6.1/apidocs/
     *            com/jayway/restassured/path/json/JsonPath.html)
     * @return if the path is present in the json, false if it is not
     */
    protected static boolean isJsonPathPresent(String expJsonPath) {
        try {
            String actJsonPath = getJson().getString(expJsonPath);
            if (actJsonPath != null) {
                return true;
            } else {
                return false;
            }
        } catch (Throwable t) {
            t.printStackTrace();
            return false;
        }
    }

    /**
     * Checks to see if the specified path in the stored json result is present
     *
     * @param key
     *            the value that the json results is stored under
     * @param expJsonPath
     *            the path to the element to check for in the json (use format
     *            from
     *            http://rest-assured.googlecode.com/svn/tags/1.6.1/apidocs/
     *            com/jayway/restassured/path/json/JsonPath.html)
     * @return if the path is present in the json, false if it is not
     */
    protected static boolean isJsonPathPresent(String key, String expJsonPath) {
        try {
            String value = getJson(key).getString(expJsonPath);
            if (value != null)
                return true;
            return false;
        } catch (Throwable t) {
            t.printStackTrace();
            return false;
        }
    }

    /**
     * Checks to see if the specified path in the stored json result is present
     *
     * @param restTestCase
     *            the test case object containing the name that the json result
     *            is stored under.
     * @param expJsonPath
     *            the path to the element to check for in the json (use format
     *            from
     *            http://rest-assured.googlecode.com/svn/tags/1.6.1/apidocs/
     *            com/jayway/restassured/path/json/JsonPath.html)
     * @return if the path is present in the json, false if it is not
     */
    protected static boolean isJsonPathPresent(CfAPI restTestCase, String expJsonPath) {
        if (restTestCase.isNameSet()) {
            return isJsonPathPresent(restTestCase.getName(), expJsonPath);
        } else {
            return isJsonPathPresent(expJsonPath);
        }
    }

    /**
     * Gets the value from the element or attribute in the stored xml for the
     * path specified
     *
     * @param expXmlPath
     *            the path to the element or attribute to get the value from in
     *            the xml (use format from
     *            http://groovy.codehaus.org/Updating+XML+with+XmlSlurper)
     * @return the value from the element or attribute in the stored xml for the
     *         path specified
     */
    protected static String getXmlStringValue(String expXmlPath) {
        if (isXmlPathPresent(expXmlPath)) {
            String actVal = getXml().getString(expXmlPath);
            return actVal;
        } else {
            throw new RuntimeException("The xml path '" + expXmlPath + "' was not found in the result.");
        }
    }

    /**
     * Gets the value from the element or attribute in the stored xml for the
     * path specified
     *
     * @param key
     *            the value that the xml results is stored under
     * @param expXmlPath
     *            the path to the element or attribute to get the value from in
     *            the xml (use format from
     *            http://groovy.codehaus.org/Updating+XML+with+XmlSlurper)
     * @return the value from the element or attribute in the stored xml for the
     *         path specified
     */
    protected static String getXmlStringValue(String key, String expXmlPath) {
        if (isXmlPathPresent(key, expXmlPath)) {
            String actVal = getXml(key).getString(expXmlPath);
            return actVal;
        } else {
            throw new RuntimeException("The xml path '" + expXmlPath + "' was not found in the result.");
        }
    }

    /**
     * Gets the value from the element or attribute in the stored xml for the
     * path specified
     *
     * @param restTestCase
     *            the test case object containing the name that the xml result
     *            is stored under.
     * @param expXmlPath
     *            the path to the element or attribute to get the value from in
     *            the xml (use format from
     *            http://groovy.codehaus.org/Updating+XML+with+XmlSlurper)
     * @return the value from the element or attribute in the stored xml for the
     *         path specified
     */
    protected static String getXmlStringValue(CfAPI restTestCase, String expXmlPath) {
        if (isXmlPathPresent(restTestCase, expXmlPath)) {
            String actVal = getXml(restTestCase).getString(expXmlPath);
            return actVal;
        } else {
            throw new RuntimeException("The xml path '" + expXmlPath + "' was not found in the result.");
        }
    }

    /**
     * Gets the value from the element or attribute in the stored json for the
     * path specified
     *
     * @param expJsonPath
     *            the path to the element to check for in the json (use format
     *            from
     *            http://rest-assured.googlecode.com/svn/tags/1.6.1/apidocs/
     *            com/jayway/restassured/path/json/JsonPath.html)
     * @return the value from the element or attribute in the stored json for
     *         the path specified
     */
    protected static String getJsonStringValue(String expJsonPath) {
        if (isJsonPathPresent(expJsonPath)) {
            String actVal = getJson().getString(expJsonPath);
            return actVal;
        } else {
            throw new RuntimeException("The Json path '" + expJsonPath + "' was not found in the result.");
        }
    }

    /**
     * Gets the value from the element or attribute in the stored json for the
     * path specified
     *
     * @param key
     *            the value that the json results is stored under
     * @param expJsonPath
     *            the path to the element to check for in the json (use format
     *            from
     *            http://rest-assured.googlecode.com/svn/tags/1.6.1/apidocs/
     *            com/jayway/restassured/path/json/JsonPath.html)
     * @return the value from the element or attribute in the stored json for
     *         the path specified
     */
    protected static String getJsonStringValue(String key, String expJsonPath) {
        if (isJsonPathPresent(key, expJsonPath)) {
            String actVal = getJson(key).getString(expJsonPath);
            return actVal;
        } else {
            throw new RuntimeException("The Json path '" + expJsonPath + "' was not found in the result.");
        }
    }

    /**
     * Gets the value from the element or attribute in the stored json for the
     * path specified
     *
     * @param restTestCase
     *            the test case object containing the name that the json result
     *            is stored under.
     * @param expJsonPath
     *            the path to the element to check for in the json (use format
     *            from
     *            http://rest-assured.googlecode.com/svn/tags/1.6.1/apidocs/
     *            com/jayway/restassured/path/json/JsonPath.html)
     * @return the value from the element or attribute in the stored json for
     *         the path specified
     */
    protected static String getJsonStringValue(CfAPI restTestCase, String expJsonPath) {
        if (isJsonPathPresent(restTestCase, expJsonPath)) {
            String actVal = getJson(restTestCase).getString(expJsonPath);
            return actVal;
        } else {
            throw new RuntimeException("The Json path '" + expJsonPath + "' was not found in the result.");
        }
    }

    /**
     * Checks to see if the value for the specified path to element or attribute
     * in the xml is the value passed in
     *
     * @param expXmlPath
     *            the path to the element or attribute to get the value from in
     *            the xml (use format from
     *            http://groovy.codehaus.org/Updating+XML+with+XmlSlurper)
     * @param expValue
     *            the value that you expect the element or attribute to be
     * @return true if the value for the specified path to element or attribute
     *         in the xml is the value passed in, false if not
     */
    protected static boolean isXmlValue(String expXmlPath, String expValue) {
        String actValue = getXmlStringValue(expXmlPath);
        return actValue.equals(expValue);
    }

    /**
     * Checks to see if the value for the specified path to element or attribute
     * in the xml is the value passed in
     *
     * @param key
     *            the value that the xml results is stored under
     * @param expXmlPath
     *            the path to the element or attribute to get the value from in
     *            the xml (use format from
     *            http://groovy.codehaus.org/Updating+XML+with+XmlSlurper)
     * @param expValue
     *            the value that you expect the element or attribute to be
     * @return true if the value for the specified path to element or attribute
     *         in the xml is the value passed in, false if not
     */
    protected static boolean isXmlValue(String key, String expXmlPath, String expValue) {
        String actValue = getXmlStringValue(key, expXmlPath);
        return actValue.equals(expValue);
    }

    /**
     * Checks to see if the value for the specified path to element or attribute
     * in the xml is the value passed in
     *
     * @param restTestCase
     *            the test case object containing the name that the xml result
     *            is stored under.
     * @param expXmlPath
     *            the path to the element or attribute to get the value from in
     *            the xml (use format from
     *            http://groovy.codehaus.org/Updating+XML+with+XmlSlurper)
     * @param expValue
     *            the value that you expect the element or attribute to be
     * @return true if the value for the specified path to element or attribute
     *         in the xml is the value passed in, false if not
     */
    protected static boolean isXmlValue(CfAPI restTestCase, String expXmlPath, String expValue) {
        String actValue = getXmlStringValue(restTestCase, expXmlPath);
        return actValue.equals(expValue);
    }

    /**
     * Checks to see if the value for the specified path to element or attribute
     * in the json is the value passed in
     *
     * @param expJsonPath
     *            the path to the element or attribute to get the value for in
     *            the json (use format from
     *            http://rest-assured.googlecode.com/svn
     *            /tags/1.6.1/apidocs/com/jayway
     *            /restassured/path/json/JsonPath.html)
     * @param expValue
     *            the value that you expect the element or attribute to be
     * @return true if the value for the specified path to element or attribute
     *         in the json is the value passed in, false if not
     */
    protected static boolean isJsonValue(String expJsonPath, String expValue) {
        String actValue = getJsonStringValue(expJsonPath);
        return actValue.equals(expValue);
    }

    /**
     * Checks to see if the value for the specified path to element or attribute
     * in the json is the value passed in
     *
     * @param key
     *            the value that the json results is stored under
     * @param expJsonPath
     *            the path to the element or attribute to get the value for in
     *            the json (use format from
     *            http://rest-assured.googlecode.com/svn
     *            /tags/1.6.1/apidocs/com/jayway
     *            /restassured/path/json/JsonPath.html)
     * @param expValue
     *            the value that you expect the element or attribute to be
     * @return true if the value for the specified path to element or attribute
     *         in the json is the value passed in, false if not
     */
    protected static boolean isJsonValue(String key, String expJsonPath, String expValue) {
        String actValue = getJsonStringValue(key, expJsonPath);
        return actValue.equals(expValue);
    }

    /**
     * Checks to see if the value for the specified path to element or attribute
     * in the json is the value passed in
     *
     * @param restTestCase
     *            the test case object containing the name that the json result
     *            is stored under.
     * @param expJsonPath
     *            the path to the element or attribute to get the value for in
     *            the json (use format from
     *            http://rest-assured.googlecode.com/svn
     *            /tags/1.6.1/apidocs/com/jayway
     *            /restassured/path/json/JsonPath.html)
     * @param expValue
     *            the value that you expect the element or attribute to be
     * @return true if the value for the specified path to element or attribute
     *         in the json is the value passed in, false if not
     */
    protected static boolean isJsonValue(CfAPI restTestCase, String expJsonPath, String expValue) {
        String actValue = getJsonStringValue(restTestCase, expJsonPath);
        return actValue.equals(expValue);
    }

    /**
     * Gets current test case name
     *
     * @return method name
     */
    protected static String getMethodName() {
        return getMethodName(1);
    }

    /**
     * Gets current test case name
     *
     * @param depth
     *            how many calls between the method you want to get the name
     *            i.e. if the method name you are looking for is calling this
     *            method directly it would be 0, if it is called by another
     *            method in between it would be 1.
     * @return the name of the method.
     */
    protected static String getMethodName(int depth) {
        StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        return ste[depth + 2].getMethodName();
    }

    /**
     * Gets the payload from an external file
     *
     * @param filePath
     * @return
     */
    protected static List<String> getPayloadFromFile(String filePath) {
        BufferedReader br;
        List<String> payload = new ArrayList<String>();
        try {
            String str = null;
            br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath))));
            while ((str = br.readLine()) != null) {
                payload.add(str);
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            takeLog.utilList.add(e.getMessage());
        }
        return payload;
    }

    /**
     * Checks to see if the failure object is set. Usage: Add try catch around
     * setup methods, and have the catch store the exception/throwable in the
     * failure object. Then call this method at the begining of every test
     * method that you want to have fail based on the failed setup method. This
     * will make sure that even when setup fails, that the number of test cases
     * reported will match the methods
     */
    protected static void checkForFailure() {
        if (failure != null) {
            Assert.assertFalse(false, failure.getMessage());
        }
    }

    /**
     * Used at the begining of a test method, it will report out the name of the
     * tests to the system out, and if specified will check to see if the setup
     * failed (seed checkForFailure)
     *
     * @param checkForFailure
     *            true if you want to check if the setup failed, false to skip
     *            the check and just report the name of the test.
     */
    protected static void initTestMethod(boolean checkForFailure) {
        initTestMethod(checkForFailure, 1);
    }

    /**
     * Used at the begining of a test method, it will report out the name of the
     * tests to the system out, and if specified will check to see if the setup
     * failed (seed checkForFailure)
     *
     * @param checkForFailure
     *            true if you want to check if the setup failed, false to skip
     *            the check and just report the name of the test.
     */
    protected static void initTestMethod(boolean checkForFailure, int depth) {
        verify.clearErrors();
        System.out.println("\n");
        takeLog.utilList.add("\n");
        System.out.println("TEST - " + getMethodName(depth + 1));
        takeLog.utilList.add("TEST - " + getMethodName(depth + 1));
        if (checkForFailure) {
            checkForFailure();
        }
    }

    /**
     * Used at the begining of atest method, it will report out the name of the
     * test to system out.
     */
    protected static void initTestMethod() {
        initTestMethod(true, 1);
    }

    /**
     * Gets the payload from an external file
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    protected static String getOnePayloadFromFile(String filePath) {
        BufferedReader br;
        StringBuffer payload = new StringBuffer();
        try {
            String str = null;
            br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath))));
            while ((str = br.readLine()) != null) {
                payload.append(str);
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            takeLog.utilList.add(e.getMessage());
        }
        return payload.toString();
    }

    /**
     * resets the failure object.
     */
    protected static void resetFailure() {
        failure = null;
    }

    /**
     * Initilizes a Jersey client with Basic Authentication
     *
     * @return Jersey rest client
     */
    public static Client createRestClient(String userName, String password) {
        // TODO: implemet other auth type
        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(userName, password);
        Client client = ClientBuilder.newClient();
        client.register(feature);
        return client;
    }

    /**
     * Logs the message to the system out if the specified value is greater or
     * equal to the log level set for the class
     *
     * @param level
     *            the log level above which the message will be logged.
     * @param message
     *            the message to log if the log level is met.
     */
    protected static void log(Level level, String message) {
        if (level.intValue() >= sLogLevel.intValue()) {
            System.out.println(message);
            takeLog.utilList.add(message);
        }
    }

    /**
     * Sets the log level of for this class
     *
     * @param level
     *            the level to set for the log level of this class
     */
    protected static void setLogLevel(Level level) {
        sLogLevel = level;
    }

    /**
     * Sets the log level for this class from a string
     *
     * @param logLevel
     *            the log level to set
     */
    protected static void setLogLevel(String logLevel) {
        Level level = Level.parse(logLevel);
        setLogLevel(level);
    }

    /**
     * Sets the log level for this class from a system property.
     */
    protected static void setLogLevel() {
        String logLevel = System.getProperty(LOG_LEVEL_PROP);
        if (logLevel != null && !logLevel.isEmpty())
            setLogLevel(logLevel);
    }

    protected static String getUpdatePayload(String payload, String testCall) {
        JsonPath json = getJson(testCall);
        payload = payload.replaceAll("@@SHORTID@@", getJsonStringValue(testCall, "shortId"))
                .replaceAll("@@ID@@", json.getString("id")).replaceAll("@@SECURITYID@@", json.getString("securityId"));
        payload = payload.replaceAll("@@MODIFIED@@", getJsonStringValue(testCall, "modified"))
                .replaceAll("@@MODIFIER@@", json.getString("modifier"));
        payload = payload.replaceAll("@@CREATED@@", getJsonStringValue(testCall, "created")).replaceAll("@@CREATOR@@",
                json.getString("creator"));
        payload = payload.replace("@@CURRENTVERSIONID@@", json.getString("currentVersionId"));
        return payload;
    }
}
