package com.mibi.common.exception;

import com.mibi.common.R;

public class AccountChangedException extends AccountException {
    public int getErrorCode() {
        return 10;
    }

    public String getIdentifier() {
        return "AC";
    }

    public int getErrorSummaryRes() {
        return R.string.mibi_error_account_changed_summary;
    }
}
