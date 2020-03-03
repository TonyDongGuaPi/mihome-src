package com.mibi.common.exception;

import com.mibi.common.R;

public class ResultException extends PaymentException {
    public int getErrorCode() {
        return 6;
    }

    public String getIdentifier() {
        return "RT";
    }

    public ResultException(Throwable th) {
        super(th);
    }

    public ResultException(String str) {
        super(str);
    }

    public ResultException(String str, Throwable th) {
        super(str, th);
    }

    public int getErrorSummaryRes() {
        return R.string.mibi_error_server_summary;
    }
}
