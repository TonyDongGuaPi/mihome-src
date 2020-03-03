package com.miui.tsmclient.seitsm.Exception;

public class SeiTSMApiException extends Exception {
    private int mErrorCode;

    public SeiTSMApiException(int i) {
        this(i, "");
    }

    public SeiTSMApiException(int i, String str) {
        super(str);
        this.mErrorCode = i;
    }

    public int getErrorCode() {
        return this.mErrorCode;
    }

    public String toString() {
        return super.toString() + ", errorCode: " + this.mErrorCode;
    }
}
