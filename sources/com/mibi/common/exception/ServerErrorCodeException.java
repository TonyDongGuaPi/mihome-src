package com.mibi.common.exception;

import com.mibi.common.R;

public class ServerErrorCodeException extends PaymentException {
    private Object mResult;
    private int mServerErrorCode;
    private String mServerErrorDesc;

    public int getErrorCode() {
        return 6;
    }

    public String getIdentifier() {
        return "SC";
    }

    public ServerErrorCodeException(int i, String str, Object obj) {
        this.mServerErrorCode = i;
        this.mServerErrorDesc = str;
        this.mResult = obj;
    }

    public int getErrorSummaryRes() {
        return R.string.mibi_error_server_summary;
    }

    public int getServerErrorCode() {
        return this.mServerErrorCode;
    }

    public String getServerErrorDesc() {
        return this.mServerErrorDesc;
    }

    public Object getResult() {
        return this.mResult;
    }
}
