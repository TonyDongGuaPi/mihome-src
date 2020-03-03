package com.xiaomi.account.openauth;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

public class XiaomiOAuthResults {
    private static final String TAG = "XiaomiOAuthResults";
    private final Bundle contentBundle;
    private final Error errorResult;
    private final Success successResult;

    private XiaomiOAuthResults(Bundle bundle, Error error) {
        this.contentBundle = bundle;
        this.successResult = null;
        this.errorResult = error;
    }

    private XiaomiOAuthResults(Bundle bundle, Success success) {
        this.contentBundle = bundle;
        this.successResult = success;
        this.errorResult = null;
    }

    public Bundle getContentBundle() {
        return this.contentBundle;
    }

    public String getAccessToken() {
        if (this.successResult != null) {
            return this.successResult.accessToken;
        }
        return null;
    }

    public String getExpiresIn() {
        if (this.successResult != null) {
            return this.successResult.expiresIn;
        }
        return null;
    }

    public String getScopes() {
        if (this.successResult != null) {
            return this.successResult.scopes;
        }
        return null;
    }

    public String getState() {
        if (this.successResult != null) {
            return this.successResult.state;
        }
        return null;
    }

    public String getTokenType() {
        if (this.successResult != null) {
            return this.successResult.tokenType;
        }
        return null;
    }

    public String getMacKey() {
        if (this.successResult != null) {
            return this.successResult.macKey;
        }
        return null;
    }

    public String getMacAlgorithm() {
        if (this.successResult != null) {
            return this.successResult.macAlgorithm;
        }
        return null;
    }

    public String getCode() {
        if (this.successResult != null) {
            return this.successResult.code;
        }
        return null;
    }

    public int getErrorCode() {
        if (this.errorResult != null) {
            return this.errorResult.errorCode;
        }
        return 0;
    }

    public String getErrorMessage() {
        if (this.errorResult != null) {
            return this.errorResult.errorMessage;
        }
        return null;
    }

    public boolean hasError() {
        return this.errorResult != null;
    }

    private static class Success {
        public final String accessToken;
        public final String code;
        public final String expiresIn;
        public final String macAlgorithm;
        public final String macKey;
        public final String scopes;
        public final String state;
        public final String tokenType;

        public Success(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
            this.accessToken = str;
            this.expiresIn = str2;
            this.scopes = str3;
            this.state = str4;
            this.tokenType = str5;
            this.macKey = str6;
            this.macAlgorithm = str7;
            this.code = str8;
        }

        public String toString() {
            return "accessToken=" + this.accessToken + ",expiresIn=" + this.expiresIn + ",scope=" + this.scopes + ",state=" + this.state + ",tokenType=" + this.tokenType + ",macKey=" + this.macKey + ",macAlogorithm=" + this.macAlgorithm + ",code=" + this.code;
        }

        /* access modifiers changed from: private */
        public static Success parseBundle(Bundle bundle) {
            return new Success(XiaomiOAuthResults.getStringCompatibly(bundle, "access_token", "extra_access_token"), XiaomiOAuthResults.getIntOrStringCompatibly(bundle, "expires_in", "extra_expires_in"), XiaomiOAuthResults.getStringCompatibly(bundle, "scope", "extra_scope"), XiaomiOAuthResults.getStringCompatibly(bundle, "state", XiaomiOAuthConstants.EXTRA_STATE), XiaomiOAuthResults.getStringCompatibly(bundle, "token_type", "extra_token_type"), XiaomiOAuthResults.getStringCompatibly(bundle, "mac_key", "extra_mac_key"), XiaomiOAuthResults.getStringCompatibly(bundle, "mac_algorithm", "extra_mac_algorithm"), XiaomiOAuthResults.getStringCompatibly(bundle, "code", "extra_code"));
        }
    }

    public static class Error {
        public final int errorCode;
        public final String errorMessage;

        public Error(int i, String str) {
            this.errorCode = i;
            this.errorMessage = str;
        }

        public String toString() {
            return "errorCode=" + this.errorCode + ",errorMessage=" + this.errorMessage;
        }

        /* access modifiers changed from: private */
        public static Error parseBundle(Bundle bundle) {
            return new Error(XiaomiOAuthResults.getIntCompatibly(bundle, "extra_error_code", "error"), XiaomiOAuthResults.getStringCompatibly(bundle, "extra_error_description", "error_description"));
        }
    }

    /* access modifiers changed from: private */
    public static String getStringCompatibly(Bundle bundle, String str, String str2) {
        return bundle.containsKey(str) ? bundle.getString(str) : bundle.getString(str2);
    }

    /* access modifiers changed from: private */
    public static int getIntCompatibly(Bundle bundle, String str, String str2) {
        try {
            return Integer.parseInt(getIntOrStringCompatibly(bundle, str, str2));
        } catch (NumberFormatException unused) {
            Log.w(TAG, "error, return 0 instead:");
            return 0;
        }
    }

    /* access modifiers changed from: private */
    public static String getIntOrStringCompatibly(Bundle bundle, String str, String str2) {
        Object obj;
        int i = 0;
        String[] strArr = {str, str2};
        int length = strArr.length;
        while (i < length) {
            String str3 = strArr[i];
            if (TextUtils.isEmpty(str3) || !bundle.containsKey(str3) || (obj = bundle.get(str3)) == null) {
                i++;
            } else if (obj instanceof Integer) {
                return ((Integer) obj).toString();
            } else {
                return obj.toString();
            }
        }
        return null;
    }

    public String toString() {
        if (this.successResult != null) {
            return this.successResult.toString();
        }
        if (this.errorResult != null) {
            return this.errorResult.toString();
        }
        throw new IllegalStateException("should not be here.");
    }

    public static XiaomiOAuthResults parseBundle(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        if (getIntCompatibly(bundle, "extra_error_code", "error") != 0) {
            return new XiaomiOAuthResults(bundle, Error.parseBundle(bundle));
        }
        return new XiaomiOAuthResults(bundle, Success.parseBundle(bundle));
    }
}
