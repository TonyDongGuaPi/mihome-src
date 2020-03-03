package org.cybergarage.http;

import java.util.StringTokenizer;
import org.cybergarage.util.Debug;

public class HTTPStatus {
    public static final int BAD_REQUEST = 400;
    public static final int CONTINUE = 100;
    public static final int INTERNAL_SERVER_ERROR = 500;
    public static final int INVALID_RANGE = 416;
    public static final int NOT_FOUND = 404;
    public static final int OK = 200;
    public static final int PARTIAL_CONTENT = 206;
    public static final int PRECONDITION_FAILED = 412;
    private String reasonPhrase = "";
    private int statusCode = 0;
    private String version = "";

    public static final String code2String(int i) {
        return i != 100 ? i != 200 ? i != 206 ? i != 400 ? i != 404 ? i != 412 ? i != 416 ? i != 500 ? "" : "Internal Server Error" : "Invalid Range" : "Precondition Failed" : "Not Found" : "Bad Request" : "Partial Content" : "OK" : "Continue";
    }

    public static final boolean isSuccessful(int i) {
        return 200 <= i && i < 300;
    }

    public HTTPStatus() {
        setVersion("");
        setStatusCode(0);
        setReasonPhrase("");
    }

    public HTTPStatus(String str, int i, String str2) {
        setVersion(str);
        setStatusCode(i);
        setReasonPhrase(str2);
    }

    public HTTPStatus(String str) {
        set(str);
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public void setStatusCode(int i) {
        this.statusCode = i;
    }

    public void setReasonPhrase(String str) {
        this.reasonPhrase = str;
    }

    public String getVersion() {
        return this.version;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getReasonPhrase() {
        return this.reasonPhrase;
    }

    public boolean isSuccessful() {
        return isSuccessful(getStatusCode());
    }

    public void set(String str) {
        int i;
        if (str == null) {
            setVersion("1.1");
            setStatusCode(500);
            setReasonPhrase(code2String(500));
            return;
        }
        try {
            StringTokenizer stringTokenizer = new StringTokenizer(str, " ");
            if (stringTokenizer.hasMoreTokens()) {
                setVersion(stringTokenizer.nextToken().trim());
                if (stringTokenizer.hasMoreTokens()) {
                    try {
                        i = Integer.parseInt(stringTokenizer.nextToken());
                    } catch (Exception unused) {
                        i = 0;
                    }
                    setStatusCode(i);
                    String str2 = "";
                    while (stringTokenizer.hasMoreTokens()) {
                        if (str2.length() >= 0) {
                            str2 = str2 + " ";
                        }
                        str2 = str2 + stringTokenizer.nextToken();
                    }
                    setReasonPhrase(str2.trim());
                }
            }
        } catch (Exception e) {
            Debug.warning(e);
        }
    }
}
