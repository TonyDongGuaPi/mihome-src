package com.xiaomi.youpin.mipay;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.xiaomi.jr.account.IAccountProvider;
import com.xiaomi.passport.servicetoken.ServiceTokenResult;

class ServiceTokenConverter {
    ServiceTokenConverter() {
    }

    static Bundle a(ServiceTokenResult serviceTokenResult) throws ConvertException {
        String str = serviceTokenResult.errorMessage;
        ServiceTokenResult.ErrorCode errorCode = serviceTokenResult.errorCode;
        if (errorCode == ServiceTokenResult.ErrorCode.ERROR_OLD_MIUI_ACCOUNT_MANAGER_PERMISSION_ISSUE) {
            throw new SecurityException(str + serviceTokenResult.errorStackTrace);
        } else if (errorCode == ServiceTokenResult.ErrorCode.ERROR_NONE) {
            Bundle bundle = new Bundle();
            bundle.putInt("errorCode", ServiceTokenResult.ErrorCode.ERROR_NONE.ordinal());
            bundle.putString("serviceToken", serviceTokenResult.serviceToken);
            bundle.putString("sid", serviceTokenResult.sid);
            bundle.putString("security", serviceTokenResult.security);
            bundle.putString(IAccountProvider.d, serviceTokenResult.slh);
            bundle.putString("ph", serviceTokenResult.ph);
            bundle.putString("cUserId", serviceTokenResult.cUserId);
            return bundle;
        } else if (errorCode == ServiceTokenResult.ErrorCode.ERROR_USER_INTERACTION_NEEDED) {
            Bundle bundle2 = new Bundle();
            bundle2.putInt("errorCode", ServiceTokenResult.ErrorCode.ERROR_USER_INTERACTION_NEEDED.ordinal());
            bundle2.putParcelable("intent", serviceTokenResult.intent);
            return bundle2;
        } else {
            if (str != null && str.matches("\\d#.*")) {
                try {
                    int indexOf = str.indexOf("#");
                    int intValue = Integer.valueOf(str.substring(0, indexOf)).intValue();
                    String substring = str.substring(indexOf + 1);
                    Bundle bundle3 = new Bundle();
                    bundle3.putInt("errorCode", intValue);
                    bundle3.putString("errorMessage", substring);
                    return bundle3;
                } catch (NumberFormatException unused) {
                }
            }
            throw new ConvertException(errorCode, str);
        }
    }

    static ServiceTokenResult a(Bundle bundle) {
        ServiceTokenResult.ErrorCode errorCode;
        if (bundle == null) {
            return new ServiceTokenResult.Builder((String) null).errorCode(ServiceTokenResult.ErrorCode.ERROR_UNKNOWN).build();
        }
        String string = bundle.getString("sid");
        if (bundle.containsKey("serviceToken")) {
            String string2 = bundle.getString("cUserId");
            String string3 = bundle.getString("serviceToken");
            String string4 = bundle.getString(IAccountProvider.d);
            String string5 = bundle.getString("ph");
            String string6 = bundle.getString("security");
            if (TextUtils.isEmpty(string3) || TextUtils.isEmpty(string2)) {
                return new ServiceTokenResult.Builder(string).errorCode(ServiceTokenResult.ErrorCode.ERROR_AUTHENTICATOR_ERROR).errorMessage("invalid auth token").build();
            }
            return new ServiceTokenResult.Builder(string).errorCode(ServiceTokenResult.ErrorCode.ERROR_NONE).serviceToken(string3).security(string6).slh(string4).ph(string5).peeked(false).build();
        }
        Intent intent = (Intent) bundle.getParcelable("intent");
        if (intent != null) {
            return new ServiceTokenResult.Builder(string).errorCode(ServiceTokenResult.ErrorCode.ERROR_USER_INTERACTION_NEEDED).intent(intent).build();
        }
        if (!bundle.containsKey("errorCode")) {
            return new ServiceTokenResult.Builder(string).errorCode(ServiceTokenResult.ErrorCode.ERROR_UNKNOWN).build();
        }
        int i = bundle.getInt("errorCode");
        String string7 = bundle.getString("errorMessage");
        if (i != 1) {
            switch (i) {
                case 3:
                    errorCode = ServiceTokenResult.ErrorCode.ERROR_IOERROR;
                    break;
                case 4:
                    errorCode = ServiceTokenResult.ErrorCode.ERROR_CANCELLED;
                    break;
                case 5:
                    errorCode = ServiceTokenResult.ErrorCode.ERROR_AUTHENTICATOR_ERROR;
                    break;
                case 6:
                    errorCode = ServiceTokenResult.ErrorCode.ERROR_AUTHENTICATOR_ERROR;
                    break;
                case 7:
                    errorCode = ServiceTokenResult.ErrorCode.ERROR_AUTHENTICATOR_ERROR;
                    break;
                case 8:
                    errorCode = ServiceTokenResult.ErrorCode.ERROR_AUTHENTICATOR_ERROR;
                    break;
                case 9:
                    errorCode = ServiceTokenResult.ErrorCode.ERROR_AUTHENTICATOR_ERROR;
                    break;
                default:
                    errorCode = ServiceTokenResult.ErrorCode.ERROR_UNKNOWN;
                    break;
            }
        } else {
            errorCode = ServiceTokenResult.ErrorCode.ERROR_REMOTE_EXCEPTION;
        }
        ServiceTokenResult.Builder errorCode2 = new ServiceTokenResult.Builder(string).errorCode(errorCode);
        return errorCode2.errorMessage(i + "#" + string7).build();
    }

    static class ConvertException extends Exception {
        final ServiceTokenResult.ErrorCode errorCode;
        final String errorMsg;

        private ConvertException(ServiceTokenResult.ErrorCode errorCode2, String str) {
            this.errorCode = errorCode2;
            this.errorMsg = str;
        }
    }
}
