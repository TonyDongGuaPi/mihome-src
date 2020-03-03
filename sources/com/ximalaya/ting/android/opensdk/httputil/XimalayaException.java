package com.ximalaya.ting.android.opensdk.httputil;

import java.util.HashMap;
import java.util.Map;

public class XimalayaException extends Exception {
    public static final int DHKEY_CREATE_FAIL = 1015;
    public static final int DHKEY_REQUEST_FAIL = 1016;
    public static final int ENCODE_ERROR = 1008;
    public static final int ERROR_DESC_EMPTY = 1009;
    public static final Map<Integer, String> ERR_MESSAGE_MAP = new HashMap<Integer, String>() {
        {
            put(1001, "request url is empty");
            put(1002, "exception occurs when caculate signature");
            put(1003, "Form encoded body must have at least one part");
            put(1004, "you must call #XiMaLaYa.init");
            put(1005, "get appkey error from AndroidManifest.xml metaData");
            put(1009, "parse data error");
            put(1010, "get accesstoken fail");
            put(1012, "request url parse error");
            put(1013, "token invalid");
            put(1014, "login need");
            put(1011, "http error");
            put(1015, "create dh ke error");
            put(1016, "create dh request fail");
        }
    };
    public static final int FORM_ENCODE_LAST_ONE = 1003;
    public static final int GET_SYSTEM_PARAMETER_ERROR = 1006;
    public static final int GET_TOKEN_FAIL = 1010;
    public static final int HTTP_REQUEST_ERROR = 1011;
    public static final int LOGIN_NEED = 1014;
    public static final int NOT_HAVE_APPKEY = 1005;
    public static final int NOT_INIT = 1004;
    public static final int PARSE_JSON_ERROR = 1007;
    public static final int REQUEST_URL_EMPTY = 1001;
    public static final int REQUEST_URL_PARSE_ERROR = 1012;
    public static final int SIGNATURE_ERR_BY_EMPTY = 1002;
    public static final int TOKEN_INVALID = 1013;
    private int mErrorCode;
    private String mErrorMessage;

    public XimalayaException(int i, String str) {
        this.mErrorCode = i;
        this.mErrorMessage = str;
    }

    public static final XimalayaException getExceptionByCode(int i) {
        return new XimalayaException(i, ERR_MESSAGE_MAP.get(Integer.valueOf(i)));
    }

    public int getErrorCode() {
        return this.mErrorCode;
    }

    public void setErrorCode(int i) {
        this.mErrorCode = i;
    }

    public String getErrorMessage() {
        return this.mErrorMessage;
    }

    public void setErrorMessage(String str) {
        this.mErrorMessage = str;
    }
}
