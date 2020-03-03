package com.mibi.common.exception;

import com.mibi.common.R;

public class PasswordErrorException extends AccountException {
    public static final int PASS_WORD_ERROR = 3;

    public int getErrorCode() {
        return 3;
    }

    public String getIdentifier() {
        return "PW";
    }

    public PasswordErrorException() {
    }

    public PasswordErrorException(Throwable th) {
        super(th);
    }

    public PasswordErrorException(String str) {
        super(str);
    }

    public int getErrorSummaryRes() {
        return R.string.mibi_password_error;
    }
}
