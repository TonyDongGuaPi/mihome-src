package com.mibi.common.exception;

import com.mibi.common.R;

public class AccountThrottingException extends AccountException {
    public int getErrorCode() {
        return 15;
    }

    public int getErrorSummaryRes() {
        return R.string.mibi_error_account_throtting;
    }
}
