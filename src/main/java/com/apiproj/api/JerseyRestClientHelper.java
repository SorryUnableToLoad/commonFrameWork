package com.apiproj.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.net.ConnectException;
import java.util.Map;

/**
 *
 * @author surajkumarn
 *
 */
public class JerseyRestClientHelper {

    static Logger logger = LoggerFactory.getLogger(JerseyRestClientHelper.class);
    private static int retryCounter = 0;

    private JerseyRestClientHelper() {
        //do nothing
    }

    /**
     * This method is used to call the API
     *
     * @param url
     * @param apiName
     * @param auth
     * @param headers
     * @param methodType
     * @param queryParam
     * @param payload
     * @return
     */
    public static ClientResponse callAPI(final String url, final String apiName,
                                         final Map<String, String> auth,
                                         final Map<String, Object> headers,
                                         final RestCallEngine.RestMethod methodType,
                                         final MultivaluedMap<String, String> queryParam,
                                         final String payload) {


        if (retryCounter < 3) {
            ClientResponse response = null;
            try {
                /*Creating the Jersey rest client instance*/
                Client client = Client.create();

                /*Checking whether the Auth is there*/
                if (auth != null && !auth.isEmpty()) {
                    /*Creating the Basic Auth HTTP Filter*/
                    HTTPBasicAuthFilter authentication = new HTTPBasicAuthFilter(auth.get("username"), auth.get("password"));
                    /*Adding it to the client*/
                    client.addFilter(authentication);
                }

                WebResource webResource;

                /*Checking whether the query param is not null for get methods*/
                if (queryParam != null) {
                    /*Assigning the url, api path and query parameter*/
                    webResource = client.resource(url + apiName).queryParams(queryParam);
                    logger.info(" The request url is : " + webResource);
                    logger.info(" The query parameters are : " + queryParam);
                }/*Post methods*/ else {
                    /*Assigning the url, api path*/
                    webResource = client.resource(url + apiName);
                    logger.info(" The request url is : " + webResource);
                }
                logger.info("The headers are : " + headers);

                /*Getting the instance builder from the resources*/
                WebResource.Builder builder = webResource.getRequestBuilder();

                if (headers != null) {
                    /*Iterating the header map*/
                    for (Map.Entry<String, Object> headersMapEntry : headers.entrySet()) {
                        /*Building the headers for the resource*/
                        builder = builder.header(headersMapEntry.getKey(), headersMapEntry.getValue());
                    }
                }

                switch (methodType) {
                    case GET:
                        /*Calling the get api*/
                        response = builder.get(ClientResponse.class);
                        break;
                    case POST:
                        if (payload != null) {
                            /*Printing the request string in json format*/
                            ObjectMapper mapper = new ObjectMapper();
                            Object json = mapper.readValue(payload, Object.class);
                            logger.info("The payload is : " + json);
                            //logger.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json));
                        }

                        /*Calling the post api*/
                        response = builder.post(ClientResponse.class, payload);
                        break;
                    case PUT:
                        if (payload != null) {
                            /*Printing the request string in json format*/
                            ObjectMapper mapper = new ObjectMapper();
                            Object json = mapper.readValue(payload, Object.class);
                            logger.info("The payload is : " + json);
                            //logger.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json));
                        }

                        /*Calling the pout api*/
                        response = builder.put(ClientResponse.class, payload);
                        break;
                    case DELETE:
                        response = builder.delete(ClientResponse.class);
                        break;
                    default:
                        break;
                }

                /*Checking whether the response code is not 200*/
                if (response.getStatus() != 200) {
                    if (response.getStatus() != 201) {
                        logger.error("Failed : HTTP error code : " + response.getStatus());
                    }
                }
                retryCounter = 0;
            } catch (ConnectException connectException) {
                retryCounter++;
                logger.info("Connection refused, trying again");
                callAPI(url, apiName, auth, headers, RestCallEngine.RestMethod.GET, queryParam, payload);
            } catch (IOException error) {
                logger.error("The exception occurred is in this method is : " + error);
                return null;
            }
            return response;
        } else {
            logger.error("The exception occurred ");
            retryCounter = 0;
            return null;
        }


    }

    /**
     * This method is used to call the get method without authentication and returns the response
     *
     * @param url
     * @param apiName
     * @param headers
     * @param queryParam
     * @return
     */
    public static ClientResponse callGetAPI(final String url, final String apiName,
                                            final Map<String, Object> headers,
                                            final MultivaluedMap<String, String> queryParam) {
        return callAPI(url, apiName, null, headers, RestCallEngine.RestMethod.GET, queryParam, null);
    }

    /**
     * This method is used to call the get method with authentication and returns the response
     *
     * @param url
     * @param apiName
     * @param auth
     * @param headers
     * @param queryParam
     * @return
     */
    public static ClientResponse callGetAPIWithAuth(final String url, final String apiName, final Map<String, String> auth,
                                                    final Map<String, Object> headers,
                                                    final MultivaluedMap<String, String> queryParam) {
        return callAPI(url, apiName, auth, headers, RestCallEngine.RestMethod.GET, queryParam, null);
    }

    /**
     * This method is used to call the post method without authentication and returns the response
     *
     * @param url
     * @param apiName
     * @param headers
     * @param payload
     * @return
     */
    public static ClientResponse callPostAPI(final String url, final String apiName,
                                             final Map<String, Object> headers,
                                             final String payload) {
        return callAPI(url, apiName, null, headers, RestCallEngine.RestMethod.POST, null, payload);
    }

    /**
     * This method is used to call the put method without authentication and returns the response
     *
     * @param url
     * @param apiName
     * @param headers
     * @param payload
     * @return
     */
    public static ClientResponse callPutAPI(final String url, final String apiName,
                                            final Map<String, Object> headers,
                                            final String payload) {
        return callAPI(url, apiName, null, headers, RestCallEngine.RestMethod.PUT, null, payload);
    }

    /**
     * This method is used to call the delete method without authentication and returns the response
     *
     * @param url
     * @param apiName
     * @param headers
     * @param payload
     * @return
     */
    public static ClientResponse callDeleteAPI(final String url, final String apiName,
                                               final Map<String, Object> headers,
                                               final MultivaluedMap<String, String> queryParam) {
        return callAPI(url, apiName, null, headers, RestCallEngine.RestMethod.DELETE, queryParam, null);
    }

    /**
     * This method is used to call the get method with authentication and returns the response
     *
     * @param url
     * @param apiName
     * @param auth
     * @param headers
     * @param payload
     * @return
     */
    public static ClientResponse callPostAPIWithAuth(final String url, final String apiName, final Map<String, String> auth,
                                                     final Map<String, Object> headers,
                                                     final String payload) {
        return callAPI(url, apiName, auth, headers, RestCallEngine.RestMethod.GET, null, payload);
    }

    /**
     * This method is used to call the post method with parameters
     *
     * @param url
     * @param apiName
     * @param headers
     * @param queryParam
     * @return
     */
    public static ClientResponse callPostAPIWithParams(String url, String apiName, Map<String, Object> headers, MultivaluedMap<String, String> queryParam) {
        return JerseyRestClientHelper.callAPI(url, apiName, null, headers, RestCallEngine.RestMethod.POST, queryParam, null);
    }

    public static String getPayloadFromResponse(final ClientResponse response) {

        try {
            /*Reading the response in the string format*/
            String jsonStringResponse = response.getEntity(String.class);

            /*Printing the response string in json format*/
            ObjectMapper mapper = new ObjectMapper();
            Object json = mapper.readValue(jsonStringResponse, Object.class);
            logger.info("The Response is : ");
            logger.info(jsonStringResponse);
            return jsonStringResponse;
        } catch (IOException error) {
            logger.error("The exception is : " + error);
            return null;
        }


    }

    public static LinkedTreeMap<?, ?> getResponseAsHashMap(String payload) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.serializeNulls().create();
        return gson.fromJson(payload, LinkedTreeMap.class);
    }

    public static <T> T getResponseAsClass(String payload, Class<T> tClass) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.serializeNulls().create();
        return gson.fromJson(payload, tClass);
    }

}

