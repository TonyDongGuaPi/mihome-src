package com.mibi.common.exception;

import com.mibi.common.R;
import org.cybergarage.http.HTTP;

public class ServiceTokenExpiredException extends PaymentException {
    public int getErrorCode() {
        return 6;
    }

    public String getIdentifier() {
        return HTTP.ST;
    }

    public int getErrorSummaryRes() {
        return R.string.mibi_error_server_summary;
    }
}
