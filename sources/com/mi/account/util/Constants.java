package com.mi.account.util;

import android.text.TextUtils;
import com.mi.util.ResourceUtil;

public final class Constants {

    public static class Prefence {
        public static final String PREF_KEY_CURRENT_TIME = "pref_key_Current_time";
        public static final String PREF_KEY_ENCRYTION_USER_ID = "pref_key_user_ecryption_id";
        public static final String PREF_KEY_INSIDE_BETA = "pref_key_inside_beta";
        public static final String PREF_KEY_MESSAGE_OVER_TIME = "pref_key_message_over_time";
        public static final String PREF_KEY_PRE_SPLASH_INFO = "pref_key_pre_splash_info";
        public static final String PREF_KEY_SIGNED_TIME = "pref_key_signed_time";
        public static final String PREF_KEY_SMS_WG = "pref_key_sms_wg";
        public static final String PREF_KEY_SPLASH_INFO = "pref_key_splash_info";
        public static final String PREF_KEY_USER_MAC_ADDRESS = "pref_key_user_mac_address";
        public static final String PREF_MIUI_ACCOUNT_AVAILABLE = "pref_miui_account_available";
        public static final String PREF_NOMORE_EULA = "pref_nomore_eula";
    }

    public static class Account {
        public static final String A = "/pass/config";
        public static final String B = "106571014010030";
        public static final String C = "1065507729555678";
        public static final String D = "10659057100335678";
        public static final String E = "+447786209730";
        public static final String F = "extra_service_url";
        public static final String G = "extra_username";
        public static final String H = "extra_account";
        public static final String I = "extra_reset_count";
        public static final String J = "extra_update_type";
        public static final String K = "android.settings.XIAOMI_ACCOUNT_SYNC_SETTINGS";
        public static final String L = "android.accounts.LOGIN_ACCOUNTS_PRE_CHANGED";
        public static final int M = 1;
        public static final int N = 2;
        public static final String O = "reg_sms";
        public static final String P = "reg_email";
        public static final String Q = "reg_type";
        public static final String R = "reg_email";
        public static final String S = "reg_phone";

        /* renamed from: a  reason: collision with root package name */
        public static String f6727a = "";
        protected static Account b = null;
        public static final String c = "com.xiaomi";
        public static final String d = "com.xiaomi.unactivated";
        public static final String e = "pref_asked_system";
        public static final String f = "pref_uid";
        public static final String g = "pref_c_uid";
        public static final String h = "pref_extended_token";
        public static final String i = "pref_pass_token";
        public static final String j = "pref_login_system";
        public static final String k = "pref_system_uid";
        public static final String l = "pref_system_c_uid";
        public static final String m = "pref_system_extended_token";
        public static final String n = "pref_user_names";
        public static final String o = "pref_last_refresh_serviceToken_time";
        public static final String p = ",";
        public static final String q = "https://www.account.xiaomi.com/pass";
        public static final String r = "https://account.xiaomi.com/pass";
        public static final String s = "http://api.account.xiaomi.com/pass";
        public static final String t = "https://www.account.xiaomi.com/pass/serviceLoginAuth";
        public static final String u = "https://www.account.xiaomi.com/pass/serviceLogin";
        public static final String v = "http://api.account.xiaomi.com/pass/activate/dev/%s/activating";
        public static final String w = "http://api.account.xiaomi.com/pass/user/full";
        public static final String x = "http://api.account.xiaomi.com/pass/user@id";
        public static final String y = "http://api.account.xiaomi.com/pass/sendActivateMessage";
        public static final String z = "https://www.account.xiaomi.com/pass/forgetPassword";

        public static void a() {
            if (b == null) {
                b = new Account();
            }
        }

        public static Account b() {
            return b;
        }

        public String c() {
            if (!TextUtils.isEmpty(f6727a)) {
                return f6727a;
            }
            if (com.mi.util.Constants.b) {
                return ResourceUtil.a("account_sid_test");
            }
            return ResourceUtil.a("account_sid");
        }
    }
}
