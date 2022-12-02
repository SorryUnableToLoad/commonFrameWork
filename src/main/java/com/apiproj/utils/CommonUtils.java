package com.apiproj.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import junit.framework.Assert;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author nishanth.t
 *
 */
public class CommonUtils {

    private static final String CHAR_LIST =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private static final String NO_LIST =
            "1234567890";
    private static final int RANDOM_STRING_LENGTH = 10;
    private static final String ALGORITHM = "AES";
    private static final String KEY = "1Hbfh667adfDEJ78";
    static Logger logger = LoggerFactory.getLogger(CommonUtils.class);

    private CommonUtils() {
        //do nothing
    }

    /**
     * This method generate key for encryption & decryption
     *
     * @return
     * @
     */
    private static Key generateKey() {
        logger.debug("Generating the key for the encryption and the decryption");
        final Key key = new SecretKeySpec(KEY.getBytes(), ALGORITHM);
        logger.debug("The Generated Key is : " + key);
        return key;
    }

    /**
     * This method generates random string
     *
     * @return
     */
    public static String generateRandomString() {
        return generateRandomString(RANDOM_STRING_LENGTH);
    }

    /**
     * This method generates random string
     *
     * @return
     */
    public static String generateRandomString(final int length) {
        StringBuilder randStr = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = getRandomNumber();
            char ch = CHAR_LIST.charAt(number);
            randStr.append(ch);
        }
        return randStr.toString();
    }

    /**
     * This method generates random numbers
     *
     * @return
     */
    public static String generateRandomNumbers(final int length) {
        StringBuilder randStr = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomInt;
            Random randomGenerator = new Random();
            randomInt = randomGenerator.nextInt(NO_LIST.length());
            char ch = NO_LIST.charAt(randomInt);
            randStr.append(ch);
        }
        return randStr.toString();
    }

    /**
     * This method generates random numbers
     *
     * @return int
     */
    private static int getRandomNumber() {
        int randomInt;
        Random randomGenerator = new Random();
        randomInt = randomGenerator.nextInt(CHAR_LIST.length());
        if (randomInt - 1 == -1) {
            logger.debug("The Random number is : " + randomInt);
            return randomInt;
        } else {
            return randomInt - 1;
        }
    }

    /**
     * This method asserts 2 JsonArray irrespective of order
     */

    public static boolean assertEqualsNoOrder(final JSONArray firstElement, final JSONArray secondElement) {
        Assert.assertEquals(firstElement.length(), secondElement.length());

        List<String> firstList = new ArrayList<>();
        List<String> secondList = new ArrayList<>();

        for (int count = 0; count < firstElement.length(); count++) {
            firstList.add(firstElement.get(count).toString());
            secondList.add(secondElement.get(count).toString());
        }

        return secondList.containsAll(firstList);
    }

    /**
     * This method asserts 2 StringArray irrespective of order
     */

    public static boolean assertEqualsNoOrder(final String[] firstArray, final String[] secondArray) {
        Assert.assertEquals(firstArray.length, secondArray.length);

        List<String> firstList = Arrays.asList(firstArray);
        List<String> secondList = Arrays.asList(secondArray);
        return secondList.containsAll(firstList);
    }

    /**
     * This method decrypts the value
     *
     * @param value
     * @return
     */
  /*  public static String decrypt(final String value) {
        if (value == null) {
            return null;
        }
        try {
            logger.debug("Decrypting the text : " + value);
            Key key = new SecretKeySpec(KEY.getBytes(), ALGORITHM) {
            };
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decryptedValue64 = new BASE64Decoder().decodeBuffer(value);
            byte[] decryptedByteValue = cipher.doFinal(decryptedValue64);
            return new String(decryptedByteValue, "utf-8");
        } catch (Exception error) {
            logger.error("The error occurred during decryption is : " + error);
            error.printStackTrace();
        }
        return null;
    }*/

    /**
     * This method encrypts the value
     *
     * @param value
     * @return
     * @
     */
/*    public static String encrypt(final String value) {
        try {
            Key key = generateKey();
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedByteValue = cipher.doFinal(value.getBytes("utf-8"));
            return new BASE64Encoder().encode(encryptedByteValue);
        } catch (Exception error) {
            logger.error("The error occurred during encryption is : " + error);
        }
        return null;
    }*/

    /**
     * This method is used to convert the object to json object
     *
     * @param object
     * @return
     */
    public static JSONObject convertToJsonObject(final Object object) {
        JSONObject jsonObject = new JSONObject(object);
        logger.debug("The json object is : " + jsonObject);
        return jsonObject;
    }

    /**
     * This method is used to convert the object to String
     *
     * @param object
     * @return
     */
    public static String convertSerializableJsonToString(final Object object) {
        Gson gsonObj = new GsonBuilder().setPrettyPrinting().create();
        // converts object to json string
        return gsonObj.toJson(object);
    }

    /**
     * This method is used to convert the json to list
     *
     * @param json
     * @param fieldName
     * @return
     */
    public static List<JSONObject> convertToJsonArrayList(final JSONObject json, final String fieldName) {

        List<JSONObject> jsonObjectList = new ArrayList<>();
        for (int count = 0; count < json.getJSONArray(fieldName).length(); count++) {
            jsonObjectList.add((JSONObject) (json.getJSONArray(fieldName).get(count)));

        }
        return jsonObjectList;
    }

    /**
     * This method is used to convert the json to list
     *
     * @param json
     * @param fieldName
     * @return
     */
    public static List<String> convertToStringArrayList(final JSONObject json, final String fieldName) {

        List<String> stringList = new ArrayList<>();
        for (int count = 0; count < json.getJSONArray(fieldName).length(); count++) {
            stringList.add((String) (json.getJSONArray(fieldName).get(count)));

        }
        return stringList;
    }

    /**
     * returns current system time in epoch format
     *
     * @return
     */
    public static long getEpochTime() {
        return System.currentTimeMillis() / 1000L;
    }

    /**
     * returns current system time in defined time format
     *
     * @return
     */
    public static String getCurrentFixTime(final String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date dateobj = new Date();
        return dateFormat.format(dateobj);
    }

    /**
     * This method is used to sleep the thread
     *
     * @param milliseconds
     */
    public static void sleep(final long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (Exception error) {
            logger.error("The exception occurred is : " + error);
        }
    }

    /**
     * This method is used to encrypt the imei
     *
     * @param imei
     * @return
     */
   /* public static String getEncryptedIMEI(final String imei) {

        String key = "6YnGpe3GDjqh3zPq";
        String encrypted = null;

        byte[] crypted;
        try {
            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skey);
            crypted = cipher.doFinal(imei.getBytes());
            encrypted = Base64.getEncoder().encodeToString(crypted);

        } catch (Exception error) {
            logger.debug("The exception occurred in this method getEncryptedIMEI : " + error);
        }
        return encrypted;
    }*/

    public static long getEpochTimeBasedOnDateFormat(String time, String format) throws ParseException {
        Date date = new SimpleDateFormat(format, Locale.ENGLISH).parse(time);
        return date.toInstant().toEpochMilli();
    }


    public static String decryptOSN(final String crn, final String imei) {
        String decryptedCrn;
        int crnLength = crn.length();
        int[] crnIntVal = new int[crnLength];
        for (int i = 0; i < crnLength; i++) {
            crnIntVal[i] = crn.charAt(i) - '0';
        }
        int imeiLength = imei.length();
        int[] imeiIntArray = new int[imeiLength];
        for (int i = 0; i < imeiLength; i++) {
            imeiIntArray[i] = imei.charAt(i) - '0';
        }
        int lastCrnCharVal = crnIntVal[crnLength - 1];
        int rotateBy = 0;
        int[] rotateCrn = new int[3];
        for (int i = lastCrnCharVal; i < imeiLength; i++) {
            if (imeiIntArray[i] > 0) {
                rotateBy = imeiIntArray[i];
                break;
            }
        }
        for (int i = crnLength - 4, j = 0; i < crnLength - 1 && j < 3; i++, j++) {
            rotateCrn[j] = crnIntVal[i] - rotateBy;
            if (rotateCrn[j] < 0) {
                rotateCrn[j] = rotateCrn[j] + 10;
            }
        }
        String rotatedStr = Integer.toString(rotateCrn[0]) + Integer.toString(rotateCrn[1]) + Integer.toString(rotateCrn[2]);
        decryptedCrn = crn.substring(0, crnLength - 4).concat(rotatedStr).concat(crn.substring(crnLength - 1));
        return decryptedCrn;
    }

    public static String getMd5(String input) {
        if (input == null) {
            return "";
        }
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(input.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

    public static boolean isNullOrEmpty(String s) {
        return s == null ? true : s.isEmpty();
    }

}
