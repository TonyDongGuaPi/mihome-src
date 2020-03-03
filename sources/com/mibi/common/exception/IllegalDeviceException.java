package com.mibi.common.exception;

import com.mibi.common.R;

public class IllegalDeviceException extends PaymentException {
    public int getErrorCode() {
        return 5;
    }

    public String getIdentifier() {
        return "ID";
    }

    public IllegalDeviceException() {
    }

    public IllegalDeviceException(Throwable th) {
        super(th);
    }

    public IllegalDeviceException(String str) {
        super(str);
    }

    public int getErrorSummaryRes() {
        return R.string.mibi_error_invalid_device;
    }
}
