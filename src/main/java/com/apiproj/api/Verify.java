package com.apiproj.api;

import com.apiproj.utils.UtilClass;
import junit.framework.Assert;

import java.util.*;

/**
 *
 * @author nishanth.t
 *
 */

public class Verify {
    protected List<String> m_ErrorList;

    public static UtilClass takeLog = new UtilClass();

    public Verify() {
        m_ErrorList = new ArrayList<String>();
    }

    public void verifyTrue(String _passMessage, String _failMessage, boolean rc) {
        if (rc) {
            System.out.println(_passMessage);
            takeLog.utilList.add(_passMessage);
        } else {
            System.out.println(_failMessage);
            takeLog.utilList.add(_failMessage);
            m_ErrorList.add(_failMessage);
        }
    }

    public void verifyTrue(String _failMessage, boolean rc) {
        verifyTrue("", _failMessage, rc);
    }

    public void verifyFalse(String _passMessage, String _failMessage, boolean rc) {
        verifyTrue(_passMessage, _failMessage, !rc);
    }

    public void verifyFalse(String _failMessage, boolean rc) {
        verifyTrue("", _failMessage, !rc);
    }

    public <T> void verifyEquals(String _passMessage, String _failMessage, T exp, T act) {
        if (exp.equals(act)) {
            System.out.println(_passMessage);
            takeLog.utilList.add(_passMessage);
        } else {
            _failMessage = _failMessage + "  Expected:  " + exp.toString() + "  Actual:  " + act.toString();
            System.out.println(_failMessage);
            takeLog.utilList.add(_failMessage);
            m_ErrorList.add(_failMessage);
        }
    }

    public <T> void verifyEquals(String _failMessage, T exp, T act) {
        verifyEquals("", _failMessage, exp, act);
    }

    public String getErrorsAsString() {
        String errors = "";
        for (int i = 0; i < m_ErrorList.size(); i++) {
            errors = errors + m_ErrorList.get(i) + "\n";
        }
        return errors;
    }

    public void clearErrors() {
        m_ErrorList.clear();
    }

    public void assertErrors() {
        if (m_ErrorList.isEmpty()) {
            System.out.println("The error list was empty");
            takeLog.utilList.add("The error list was empty");
        } else {
            String errors = getErrorsAsString();
            clearErrors();
            Assert.fail(errors);
        }

    }

    public void verifyMaps(List<Map<String, String>> mapList, Map<String, String> expMap) {

        Iterator<String> iterator = expMap.keySet().iterator();
        while (iterator.hasNext()) {
            boolean found = false;
            String key = iterator.next();
            String value = expMap.get(key);
            for (int i = 0; i < mapList.size(); i++) {
                if (mapList.get(i).get("name").equals(key)) {
                    found = true;
                    String actVal = mapList.get(i).get("value");
                    if (value == null) {
                        if (actVal != null) {
                            m_ErrorList.add("The value for '" + key + "' was '" + actVal + "' not the expected: '"
                                    + value + "'");
                        }
                    } else if (!value.equals(actVal)) {
                        m_ErrorList.add(
                                "The value for '" + key + "' was '" + actVal + "' not the expected: '" + value + "'");
                    }
                }
            }
            if (!found) {
                m_ErrorList.add("Could not find '" + key + "' in the result.");
            }
        }
    }

    public void verifyLinkTemplate(String _failMessage, String actLink, String expURL, String[] expParam) {
        String[] actString = actLink.split("\\?");
        boolean b1 = actString[0].equals(expURL);
        String[] actParam = actString[1].split("\\&");
        Arrays.sort(expParam);
        Arrays.sort(actParam);
        boolean b2 = Arrays.equals(expParam, actParam);
        if (!(b1 && b2)) {
            System.out.println(_failMessage);
            takeLog.utilList.add(_failMessage);
            m_ErrorList.add("The value was '" + actLink + "' not the expected: '" + expURL + "'");
        }
    }

    public void verifyCapabilities(String _failMessage, String actCapabilities, String[] expCapabilities) {
        String[] act = actCapabilities.split(" ");
        Arrays.sort(act);
        boolean b = Arrays.equals(act, expCapabilities);
        if (!b) {
            System.out.println(_failMessage);
            takeLog.utilList.add(_failMessage);
            m_ErrorList.add("The value was '" + actCapabilities + "' not the expected: '"
                    + Arrays.toString(expCapabilities) + "'");
        }
    }
}
