package com.sina.weibo.sdk.auth;

import com.sina.weibo.sdk.utils.WbAuthConstants;

public class WbConnectErrorMessage {
    private String errorCode = WbAuthConstants.k;
    private String errorMessage = WbAuthConstants.j;

    public WbConnectErrorMessage() {
    }

    public WbConnectErrorMessage(String str, String str2) {
        this.errorMessage = str;
        this.errorCode = str2;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorMessage(String str) {
        this.errorMessage = str;
    }

    public void setErrorCode(String str) {
        this.errorCode = str;
    }
}
