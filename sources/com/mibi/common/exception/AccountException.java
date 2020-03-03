package com.mibi.common.exception;

import com.mibi.common.R;

public class AccountException extends PaymentException {
    public int getErrorCode() {
        return 5;
    }

    public String getIdentifier() {
        return "AT";
    }

    public AccountException() {
    }

    public AccountException(Throwable th) {
        super(th);
    }

    public AccountException(String str) {
        super(str);
    }

    public int getErrorSummaryRes() {
        return R.string.mibi_error_auth_summary;
    }
}
