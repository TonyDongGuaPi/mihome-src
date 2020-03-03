package org.cybergarage.upnp;

import org.cybergarage.http.HTTPStatus;

public class UPnPStatus {
    public static final int ACTION_FAILED = 501;
    public static final int INVALID_ACTION = 401;
    public static final int INVALID_ARGS = 402;
    public static final int INVALID_VAR = 404;
    public static final int OUT_OF_SYNC = 403;
    public static final int PRECONDITION_FAILED = 412;
    private int code;
    private String description;

    public static final String code2String(int i) {
        if (i == 412) {
            return "Precondition Failed";
        }
        if (i == 501) {
            return "Action Failed";
        }
        switch (i) {
            case 401:
                return "Invalid Action";
            case 402:
                return "Invalid Args";
            case 403:
                return "Out of Sync";
            case 404:
                return "Invalid Var";
            default:
                return HTTPStatus.code2String(i);
        }
    }

    public UPnPStatus() {
        setCode(0);
        setDescription("");
    }

    public UPnPStatus(int i, String str) {
        setCode(i);
        setDescription(str);
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }
}
