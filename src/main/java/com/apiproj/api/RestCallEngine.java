package com.apiproj.api;

import com.apiproj.utils.UtilClass;
import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * This class can be used to generate REST API calls, and get responses back
 *
 * @author nishanth.t
 */
public class RestCallEngine {
    protected String mBaseUri;
    protected Client mClient;
    protected WebTarget target;

    public static UtilClass takeLog = new UtilClass();

    public RestCallEngine(String baseUri, Client client) {
        super();
        mBaseUri = baseUri;
        mClient = client;
    }

    /**
     * @return the baseUri
     */
    public String getBaseUri() {
        return mBaseUri;
    }

    public Client getClient() {
        return mClient;
    }

    /**
     * Makes a rest call based on the information in the restTestCase object
     *
     * @param restTestCase the object containing the details of the rest call
     * @return The response from the REST API call
     */
    public Response callRest(CfAPI restTestCase) {
        System.out.println("*********************");
        takeLog.utilList.add("*********************");
        Invocation.Builder request = buildRequest(restTestCase, buildWebTarget(restTestCase));
        Response response = callRest(restTestCase, request);
        System.out.println("*********************");
        takeLog.utilList.add("*********************");
        return response;
    }


    public void downLoad(CfAPI restTestCase,String fileName) {
        System.out.println("*********************");
        takeLog.utilList.add("*********************");
        Invocation.Builder request = buildRequest(restTestCase, buildWebTarget(restTestCase));
        downloadResponse(request,fileName);
        System.out.println("*********************");
        takeLog.utilList.add("*********************");

    }


    private WebTarget buildWebTarget(CfAPI restTestCase) {
        System.out.println("Rest Call Base URI: " + mBaseUri);
        takeLog.utilList.add("Rest Call Base URI: " + mBaseUri);
        target = mClient.target(mBaseUri);
        target = setAuthentication(restTestCase, target);
        System.out.println("Rest Call URI: " + restTestCase.getUri());
        takeLog.utilList.add("Rest Call URI: " + restTestCase.getUri());
        target = target.path(restTestCase.getUri());
        target = setParameters(restTestCase, target);
        return target;
    }

    private Invocation.Builder buildRequest(CfAPI restTestCase, WebTarget target) {
        Invocation.Builder request = setRequestContentType(restTestCase, target);
        request = setHeaders(restTestCase, request);
        request = setResponseContentType(restTestCase, request);
        request = setCookie(restTestCase, request);
        return request;
    }

    private Invocation.Builder setCookie(CfAPI restTestCase, Invocation.Builder reqSpec) {
        final List<Cookie> cookieList = restTestCase.getCookies();
        for (Cookie cookie : cookieList) {
            System.out.println("Adding cookie - " + cookie);
            takeLog.utilList.add("Adding cookie - " + cookie);
            reqSpec = reqSpec.cookie(cookie);
        }
        return reqSpec;
    }

    /**
     * Takes in an existing Request Specification, and uses it to make a REST
     * API call, using the method and uri specified in the restTestCase object
     *
     * @param restTestCase the object containing the details of the rest call (method and
     *                     uri used)
     * @param reqSpec      an existing RequestSpecification object that will be used for
     *                     the REST API call
     * @return the response from the REST API call
     */
    protected Response callRest(CfAPI restTestCase, Invocation.Builder reqSpec) {
        try {
            RestMethod method = RestMethod.valueOf(restTestCase.getMethod());
            System.out.println("Method:  " + method.toString() + "\n");
            takeLog.utilList.add("Method:  " + method.toString() + "\n");

            switch (method) {
                case POST:
                    System.out.println("Payload:  " + restTestCase.getSource() + "\n");
                    takeLog.utilList.add("Payload:  " + restTestCase.getSource() + "\n");
                    return reqSpec.post(Entity.entity(restTestCase.getSource(), restTestCase.getSourceType()));
                case MULTIPARTPOST:
                    System.out.println("Payload:  " + "file upload" + "\n");
                    takeLog.utilList.add("Payload:  " + "file upload" + "\n");
                    return target.register(MultiPartFeature.class).request().headers(restTestCase.getHeaders()).accept(restTestCase.getResponseType())
                            .post(Entity.entity(restTestCase.getMultiPartSource(), restTestCase.getSourceType()));
                case GET:
                    return reqSpec.get();
                case MULTIPARTGET:
                    return target.request().accept(restTestCase.getResponseType()).get();
                case PUT:
                    System.out.println("Payload:  " + restTestCase.getSource() + "\n");
                    takeLog.utilList.add("Payload:  " + restTestCase.getSource() + "\n");
                    return reqSpec.put(Entity.entity(restTestCase.getSource(), restTestCase.getSourceType()));
                case DELETE:
                    return reqSpec.delete();
                // case PATCH:
                // {
                // reqSpec.header("X-HTTP-Method-Override", "PATCH");
                // return reqSpec.post(reqUri);
                // }
                default:
                    throw new RuntimeException("Unable to find the rest call method for '" + method.toString() + "'.");
            }
        } catch (Throwable t) {
            throw new RuntimeException(t.getMessage());
        }
    }

    protected void downloadResponse(Invocation.Builder reqSpec,String fileName) {
        try {
            Response resp=reqSpec.get();
            if(resp.getStatus() == Response.Status.OK.getStatusCode())
            {
                InputStream is = resp.readEntity(InputStream.class);
                fetchData(is,fileName);
                //fetchFeedAnotherWay(is) //use for Java 7
                IOUtils.closeQuietly(is);

            }
            else{
                throw new WebApplicationException("Http Call failed. response code is"+resp.getStatus()+". Error reported is"+resp.getStatusInfo());
            }
        }

        catch(Exception e){

        }

    }

    private void fetchData(InputStream is,String fileName){
        File downloadfile = new File(System.getProperty("user.dir")+"/"+fileName);
        byte[] byteArray;
        try {
            byteArray = IOUtils.toByteArray(is);
            FileOutputStream fos = new FileOutputStream(downloadfile);
            fos.write(byteArray);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    /**
     * Takes the information in the restTestCase object for the request content
     * type, and set it in the passed in reqSpec object. Valid values are
     * ANY,BINARY,HTML,JSON,TEXT,URLENC,XML
     *
     * @param restTestCase the object containing the details to store in the Request
     *                     Specification (can later be used for an API Call)
     * @param reqSpec      an existing RequestSpecification object that will modified
     *                     with the request content type
     * @return the RequestSpecification object modified with request content
     * type settings (can later be used for and API Call)
     */
    protected Invocation.Builder setRequestContentType(CfAPI restTestCase, WebTarget reqSpec) {
        if (restTestCase.getSourceType() != null) {
            System.out.println("Request Payload Type:  " + restTestCase.getSourceType().toString());
            takeLog.utilList.add("Request Payload Type:  " + restTestCase.getSourceType().toString());
            return reqSpec.request(restTestCase.getResponseType());
        }
        return reqSpec.request();
    }

    /**
     * Takes the information in the restTestCase object for the request content
     * type, and set it in the passed in reqSpec object. Valid values are
     * ANY,BINARY,HTML,JSON,TEXT,URLENC,XML
     *
     * @param restTestCase the object containing the details to store in the Request
     *                     Specification (can later be used for an API Call)
     * @param reqSpec      an existing RequestSpecification object that will modified
     *                     with the request content type
     * @return the RequestSpecification object modified with request content
     * type settings (can later be used for and API Call)
     */
    protected Invocation.Builder setResponseContentType(CfAPI restTestCase, Invocation.Builder reqSpec) {
        if (restTestCase.getResponseType() != null) {
            System.out.println("Response Type: " + restTestCase.getResponseType().toString());
            takeLog.utilList.add("Response Type: " + restTestCase.getResponseType().toString());
            reqSpec = reqSpec.accept(restTestCase.getResponseType());
            return reqSpec;
        }
        return reqSpec;
    }

    /**
     * Takes the information in the restTestCase object for the parameters to be
     * set, and set it in the passed in reqSpec object.
     *
     * @param restTestCase the object containing the details to store in the Request
     *                     Specification (can later be used for an API Call)
     * @param reqSpec      an existing RequestSpecification object that will modified
     *                     with the the parameters for the eventual REST API call
     * @return the RequestSpecification object modified with parameters (can
     * later be used for and API Call)
     */
    protected WebTarget setParameters(CfAPI restTestCase, WebTarget reqSpec) {
        if (restTestCase.getParameters() != null && !restTestCase.getParameters().isEmpty()) {
            System.out.println("Rest Call Parameters:");
            takeLog.utilList.add("Rest Call Parameters:");
            for (Map.Entry<String, String> param : restTestCase.getParameters().entrySet()) {
                System.out.println(param.getKey() + " = " + param.getValue());
                takeLog.utilList.add(param.getKey() + " = " + param.getValue());
                reqSpec = reqSpec.queryParam(param.getKey(), param.getValue());
            }
            return reqSpec;
        } else if (restTestCase.getMultiValuedParameters() != null && !restTestCase.getMultiValuedParameters().isEmpty()) {
            System.out.println("Rest Call Parameters:");
            for ( MultivaluedMap.Entry<String, List<String>> param : restTestCase.getMultiValuedParameters().entrySet()) {
                System.out.println((param.getKey() + " = " + param.getValue()));
                for (String value : param.getValue()) {
                    reqSpec = reqSpec.queryParam(param.getKey(), value);
                }
            }
            return reqSpec;
        }
        return reqSpec;
    }

    /**
     * Adds authentication spec information to the passed in reqSpec if the rest
     * call object has authentication info set.
     *
     * @param restTestCall the rest call object containing the auth information
     * @param reqSpec      the request specification to add auth information to
     * @return the request spec with the added auth specification if specified
     * in the rest call object.
     */
    protected WebTarget setAuthentication(CfAPI restTestCall, WebTarget reqSpec) {
        boolean isUserNameEmpty = (restTestCall.getUserName() == null || restTestCall.getUserName().isEmpty());
        boolean isPasswordEmpty = (restTestCall.getPassword() == null || restTestCall.getPassword().isEmpty());

        if (isUserNameEmpty || isPasswordEmpty) {
            System.out.println("Rest Call Using Deafult User/Password as per base url.");
            takeLog.utilList.add("Rest Call Using Deafult User/Password as per base url.");
            return reqSpec;
        } else {
            // TODO: implement other authtype as needed
            System.out.println(
                    "Rest Call User/Password: " + restTestCall.getUserName() + "/" + restTestCall.getPassword());
            takeLog.utilList.add(
                    "Rest Call User/Password: " + restTestCall.getUserName() + "/" + restTestCall.getPassword());
            HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(restTestCall.getUserName(),
                    restTestCall.getPassword());
            reqSpec = reqSpec.register(feature);
            return reqSpec;
        }
    }

    /**
     * Sets the header values for the rest call
     *
     * @param restTestCall the rest call object containing the information about what to
     *                     set in the headers
     * @param reqSpec      an existing request spec object where the headers will be set.
     * @return the request spec object with the header information set based on
     * what is defined in the rest test call object.
     */
    protected Invocation.Builder setHeaders(CfAPI restTestCall, Invocation.Builder reqSpec) {
        if (restTestCall.getHeaders() != null && !restTestCall.getHeaders().isEmpty()) {
            System.out.println("Headers:  " + restTestCall.getHeaders().toString());
            takeLog.utilList.add("Headers:  " + restTestCall.getHeaders().toString());
            reqSpec = reqSpec.headers(restTestCall.getHeaders());
        }
        return reqSpec;
    }

    /**
     * Enum of valid rest methods
     */
    public static enum RestMethod {
        POST, GET, PUT, DELETE, MULTIPARTPOST, MULTIPARTGET;
        // PATCH;
    }
}
