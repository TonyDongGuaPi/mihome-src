package com.mibi.common.exception;

import com.mibi.common.R;
import org.cybergarage.http.HTTP;

public class NetworkException extends PaymentException {
    public int getErrorCode() {
        return 3;
    }

    public String getIdentifier() {
        return HTTP.NT;
    }

    public NetworkException() {
    }

    public NetworkException(Throwable th) {
        super(th);
    }

    public NetworkException(String str) {
        super(str);
    }

    public NetworkException(String str, Throwable th) {
        super(str, th);
    }

    public int getErrorSummaryRes() {
        return R.string.mibi_error_network_summary;
    }
}
