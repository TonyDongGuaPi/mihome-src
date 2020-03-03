package com.mibi.common.exception;

import com.mibi.common.R;

public class NoPrivacyRightException extends PaymentException {
    public int getErrorCode() {
        return 18;
    }

    public String getIdentifier() {
        return "NP";
    }

    public NoPrivacyRightException(Throwable th) {
        super(th);
    }

    public NoPrivacyRightException(String str) {
        super(str);
    }

    public NoPrivacyRightException(String str, Throwable th) {
        super(str, th);
    }

    public int getErrorSummaryRes() {
        return R.string.mibi_error_privacy_summary;
    }
}
