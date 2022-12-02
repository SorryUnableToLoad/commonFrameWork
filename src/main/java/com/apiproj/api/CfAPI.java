package com.apiproj.api;

import com.beust.jcommander.internal.Lists;
import com.apiproj.api.RestCallEngine.RestMethod;
import com.apiproj.utils.UtilClass;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author surajkumarn
 *
 */
public class CfAPI {
    protected String mName;
    protected String mUri;
    protected Object mSource;
    protected MediaType mSourceType;
    protected MediaType mResponseType;
    protected String mMethod;
    protected Map<String, String> mParameters;
    protected MultivaluedMap<String, String> mMultiValuedParameters;
    protected MultivaluedMap<String, Object> mHeaders;
    protected String userName;
    protected String password;
    protected FormDataMultiPart multiPart;
    protected FileDataBodyPart filePart;
    protected List<Cookie> cookies = Lists.newArrayList();
    public static UtilClass takeLog = new UtilClass();

    /**
     * Checks to see if the name field is set.
     *
     * @return true if empty, false if it is not
     */
    public boolean isNameSet() {
        return (getName() != null && !getName().isEmpty());
    }

    /**
     * Gets a WebcenterRest instance with detailed info;
     *
     * @param name
     *            Name of the test
     * @param sourceType
     *            Media type of the payload
     * @param source
     *            Payload content
     * @param responseType
     *            Media type of the accepted response
     * @param uri
     *            URI of the rest end point
     * @param method
     *            GET, POST, PUT or DELETE
     * @param userName
     *            user name
     * @param password
     *            user password
     * @return
     */
    public static CfAPI getTestCall(String name, MediaType sourceType, String source, MediaType responseType,
                                    String uri, String method, String userName, String password, Map<String, String> params,
                                    MultivaluedMap<String, Object> headers) {
        CfAPI rtc = new CfAPI();
        rtc.setName(name);
        rtc.setSourceType(sourceType);
        rtc.setSource(source);
        rtc.setResponseType(responseType);
        rtc.setUri(uri);
        rtc.setMethod(method);
        rtc.setUserName(userName);
        rtc.setPassword(password);
        rtc.setParameters(params);
        rtc.setHeaders(headers);
        return rtc;
    }

    /**
     *
     * @param name
     * @param sourceType
     * @param source Payload object
     * @param responseType
     * @param uri
     * @param method
     * @param userName
     * @param password
     * @param params
     * @param headers
     * @return
     */
    public static CfAPI getTestCall(String name, MediaType sourceType, Object source, MediaType responseType,
                                    String uri, String method, String userName, String password, Map<String, String> params,
                                    MultivaluedMap<String, Object> headers) {
        CfAPI rtc = new CfAPI();
        rtc.setName(name);
        rtc.setSourceType(sourceType);
        rtc.setSource(source);
        rtc.setResponseType(responseType);
        rtc.setUri(uri);
        rtc.setMethod(method);
        rtc.setUserName(userName);
        rtc.setPassword(password);
        rtc.setParameters(params);
        rtc.setHeaders(headers);
        return rtc;
    }

    public static CfAPI getMultiPartCall(String name, MediaType sourceType, String sourceFile, MediaType responseType,
                                         String uri, String method, String userName, String password, Map<String, String> params,
                                         MultivaluedMap<String, Object> headers) {
        CfAPI rtc = new CfAPI();
        rtc.setName(name);
        rtc.setSourceType(sourceType);
        rtc.setMultiPartSource(sourceFile);
        rtc.setResponseType(responseType);
        rtc.setUri(uri);
        rtc.setMethod(method);
        rtc.setUserName(userName);
        rtc.setPassword(password);
        rtc.setParameters(params);
        rtc.setHeaders(headers);
        return rtc;
    }

    public static CfAPI getMultiPartCall(String name, MediaType sourceType, Map<String, String> fileDataMap,
                                         MediaType responseType, String uri, String method, String userName, String password,
                                         Map<String, String> params, MultivaluedMap<String, Object> headers) {
        CfAPI rtc = new CfAPI();
        rtc.setName(name);
        rtc.setSourceType(sourceType);
        rtc.setMultiPartSource(fileDataMap);
        rtc.setResponseType(responseType);
        rtc.setUri(uri);
        rtc.setMethod(method);
        rtc.setUserName(userName);
        rtc.setPassword(password);
        rtc.setParameters(params);
        rtc.setHeaders(headers);
        return rtc;
    }

    /**
     * Gets a WebcenterRest instance with detailed info;
     *
     * @param name
     *            Name of the test
     * @param sourceType
     *            Media type of the payload
     * @param source
     *            Payload content
     * @param responseType
     *            Media type of the accepted response
     * @param uri
     *            URI of the rest end point
     * @param method
     *            GET, POST, PUT or DELETE
     * @param userName
     *            user name
     * @param password
     *            user password
     * @param params
     * 			  query parameters from MultiValuedMap
     * @return
     */
    public static CfAPI getMultiValuedTestCall(String name, MediaType sourceType, String source, MediaType responseType,
                                               String uri, String method, String userName, String password, MultivaluedMap<String, String> params,
                                               MultivaluedMap<String, Object> headers) {
        CfAPI rtc = new CfAPI();
        rtc.setName(name);
        rtc.setSourceType(sourceType);
        rtc.setSource(source);
        rtc.setResponseType(responseType);
        rtc.setUri(uri);
        rtc.setMethod(method);
        rtc.setUserName(userName);
        rtc.setPassword(password);
        rtc.setMultiValuedParameters(params);
        rtc.setHeaders(headers);
        return rtc;
    }

    public static CfAPI getTestCall(String name, MediaType sourceType, String source, MediaType responseType,
                                    String uri, String method, String userName, String password, Map<String, String> params,
                                    MultivaluedMap<String, Object> headers, List<Cookie> cookies) {
        CfAPI rtc = getTestCall(name, sourceType, source, responseType, uri, method, userName, password, params,
                headers);
        rtc.setCookies(cookies);
        return rtc;
    }

    /**
     * Gets a default test call with no payload, request and response type is
     * JSON
     *
     * @param name
     *            name of the test call
     * @param uri
     *            rest end point
     * @param method
     *            GET,POST,PUT,DELETE
     * @return
     */
    public static CfAPI getTestCall(String name, String uri, RestMethod method) {
        return getTestCall(name, MediaType.APPLICATION_JSON_TYPE, null, MediaType.APPLICATION_JSON_TYPE, uri,
                method.toString(), null, null, new HashMap<String, String>(), null);
    }

    public static CfAPI getTestCall(String name, String uri, RestMethod method, HashMap<String, String> parm) {
        return getTestCall(name, MediaType.APPLICATION_JSON_TYPE, null, MediaType.APPLICATION_JSON_TYPE, uri,
                method.toString(), null, null, parm, null);
    }

    /**
     * Gets a test call with payload, request and response type is JSON
     *
     * @param name
     *            name of the test call
     * @param source
     *            payload
     * @param uri
     *            rest end point
     * @param method
     *            GET, POST, PUT or DELETE
     * @return
     */
    public static CfAPI getTestCall(String name, String source, String uri, RestMethod method) {
        return getTestCall(name, MediaType.APPLICATION_JSON_TYPE, source, MediaType.APPLICATION_JSON_TYPE, uri,
                method.toString(), null, null, new HashMap<String, String>(), null);
    }

    /**
     * Gets a rest call with sepcified response and request payload type
     *
     * @param name
     *            name of the test call
     * @param sourceType
     *            request payload type
     * @param source
     *            payload content
     * @param responseType
     *            response type
     * @param uri
     *            rest end point
     * @param method
     *            GET, POST, PUT or DELETE
     * @return
     */
    public static CfAPI getTestCall(String name, MediaType sourceType, String source, MediaType responseType,
                                    String uri, RestMethod method) {
        return getTestCall(name, sourceType, source, responseType, uri, method.toString(), null, null,
                new HashMap<String, String>(), null);
    }

    /**
     * Gets a rest call with parameters
     *
     * @param name
     *            name of the rest call
     * @param uri
     *            rest end point
     * @param method
     *            GET, POST, PUT or DELETE
     * @param params
     *            request parameters
     * @return
     */
    public static CfAPI getTestCall(String name, String uri, RestMethod method, Map<String, String> params) {
        return getTestCall(name, MediaType.APPLICATION_JSON_TYPE, null, MediaType.APPLICATION_JSON_TYPE, uri,
                method.toString(), null, null, params, null);
    }

    /**
     * Gets a rest call with parameters
     *
     * @param name
     *            name of the rest call
     * @param uri
     *            rest end point
     * @param method
     *            GET, POST, PUT or DELETE
     * @param params
     *            request parameters
     * @return
     */
    public static CfAPI getTestCall(String name, String uri, RestMethod method, String... params) {
        Map<String, String> paramMap = new HashMap<String, String>();
        for (String param : params) {
            String[] set = param.split("=");
            if (set.length > 1)
                paramMap.put(set[0], set[1]);
            else
                paramMap.put(set[0], null);
        }
        return getTestCall(name, uri, method, paramMap);
    }

    public static CfAPI getTestCall(String name, MediaType payloadType, String uri, RestMethod method,
                                    String... params) {
        Map<String, String> paramMap = new HashMap<String, String>();
        for (String param : params) {
            String[] set = param.split("=");
            if (set.length > 1)
                paramMap.put(set[0], set[1]);
            else
                paramMap.put(set[0], null);
        }
        return getTestCall(name, payloadType, null, payloadType, uri, method.toString(), null, null, paramMap, null);
    }

    /**
     * Gets a rest call with parameters
     *
     * @param name
     *            name of the rest call
     * @param uri
     *            rest end point
     * @param method
     *            GET, POST, PUT or DELETE
     * @param params
     *            request parameters
     * @return
     */

    public static CfAPI getMultiValuedTestCall(String name, String uri, RestMethod method, MultivaluedMap<String, String> params) {
        return getMultiValuedTestCall(name, MediaType.APPLICATION_JSON_TYPE, null, MediaType.APPLICATION_JSON_TYPE, uri,
                method.toString(), null, null, params, null);
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getUri() {
        return mUri;
    }

    public void setUri(String mUri) {
        this.mUri = mUri;
    }

    public Object getSource() {
        return mSource;
    }

    public FormDataMultiPart getMultiPartSource() {
        return multiPart;
    }

    public void setSource(Object mSource) {
        this.mSource = mSource;
    }

    public void setMultiPartSource(FormDataMultiPart formDataMultipart) {
        this.multiPart = formDataMultipart;
    }

    public void setMultiPartSource(String file) {
        this.filePart = new FileDataBodyPart("fileName", new File(file));
        this.multiPart = (FormDataMultiPart) new FormDataMultiPart().bodyPart(this.filePart);
    }

    public void setMultiPartSource(Map<String, String> fileDataMap) {
        FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
        for (String key : fileDataMap.keySet()) {
            FileDataBodyPart filePart = new FileDataBodyPart(key, new File(fileDataMap.get(key)));
            formDataMultiPart = (FormDataMultiPart) formDataMultiPart.bodyPart(filePart);
        }
        this.multiPart = formDataMultiPart;
    }

    public MediaType getSourceType() {
        return mSourceType;
    }

    public void setSourceType(MediaType mSourceType) {
        this.mSourceType = mSourceType;
    }

    public MediaType getResponseType() {
        return mResponseType;
    }

    public void setResponseType(MediaType mResponseType) {
        this.mResponseType = mResponseType;
    }

    public String getMethod() {
        return mMethod;
    }

    public void setMethod(String mMethod) {
        this.mMethod = mMethod;
    }

    public Map<String, String> getParameters() {
        return mParameters;
    }

    public MultivaluedMap<String, String> getMultiValuedParameters() {
        return mMultiValuedParameters;
    }

    public void setParameters(Map<String, String> mParameters) {
        this.mParameters = mParameters;
    }

    public void setMultiValuedParameters(MultivaluedMap<String, String> mParameters) {
        this.mMultiValuedParameters = mParameters;
    }

    public MultivaluedMap<String, Object> getHeaders() {
        return mHeaders;
    }

    public void setHeaders(MultivaluedMap<String, Object> mHeaders) {
        this.mHeaders = mHeaders;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Cookie> getCookies() {
        return cookies;
    }

    public void setCookies(List<Cookie> cookies) {
        this.cookies = cookies;
    }
}
