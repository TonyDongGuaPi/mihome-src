package com.mi.account.sdk.util;

import android.text.TextUtils;
import com.mi.util.ResourceUtil;

public final class Constants {

    /* renamed from: a  reason: collision with root package name */
    public static final String f6726a = "encrypted_user_id";

    public static class Prefence {
        public static final String PREF_KEY_ENCRYTION_USER_ID = "pref_key_user_ecryption_id";
        public static final String PREF_KEY_SPLASH_INFO = "pref_key_splash_info";
        public static final String PREF_MIUI_ACCOUNT_AVAILABLE = "pref_miui_account_available";
    }

    public static class Account {
        public static final String ACCOUNT_TYPE = "com.xiaomi";
        public static final String ACTION_LOGIN_ACCOUNTS_PRE_CHANGED = "android.accounts.LOGIN_ACCOUNTS_PRE_CHANGED";
        public static final String EXTRA_ACCOUNT = "extra_account";
        public static final String EXTRA_UPDATE_TYPE = "extra_update_type";
        public static final String PREF_C_UID = "pref_c_uid";
        public static final String PREF_EXTENDED_TOKEN = "pref_extended_token";
        public static final String PREF_LAST_REFRESH_SERVICETOKEN_TIME = "pref_last_refresh_serviceToken_time";
        public static final String PREF_LOGIN_SYSTEM = "pref_login_system";
        public static final String PREF_PASS_TOKEN = "pref_pass_token";
        public static final String PREF_SYSTEM_C_UID = "pref_system_c_uid";
        public static final String PREF_SYSTEM_EXTENDED_TOKEN = "pref_system_extended_token";
        public static final String PREF_SYSTEM_UID = "pref_system_uid";
        public static final String PREF_UID = "pref_uid";
        public static final int TYPE_ADD = 2;
        public static final int TYPE_REMOVE = 1;
        public static String accountSid = "";
        protected static Account instance;

        public static void init() {
            if (instance == null) {
                instance = new Account();
            }
        }

        public static Account getInstance() {
            return instance;
        }

        public String getServiceID() {
            if (!TextUtils.isEmpty(accountSid)) {
                return accountSid;
            }
            if (com.mi.util.Constants.b) {
                return ResourceUtil.a("account_sid_test");
            }
            return ResourceUtil.a("account_sid");
        }
    }
}
