package com.xiaomi.youpin.youpin_common;

import android.text.TextUtils;
import com.alipay.sdk.util.i;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.accountsdk.hasheddeviceidlib.HashedDeviceIdUtil;
import com.xiaomi.youpin.common.AppIdManager;
import com.xiaomi.youpin.common.util.AppInfo;
import com.xiaomi.youpin.log.LogUtils;
import com.xiaomi.youpin.youpin_constants.YPStoreConstant;

public class UserAgent {

    /* renamed from: a  reason: collision with root package name */
    static String f23793a = null;
    static String b = null;
    static String c = null;
    static String d = "";
    static String e = "";
    static String f = "";
    static String g = "";
    static String h = "";
    static String i = "";
    static String j = "";

    static void a() {
        b = "";
        c = "";
    }

    public static void a(String str, String str2, String str3) {
        i = str;
        g = str2;
        h = str3;
        a();
    }

    public static void a(String str, String str2) {
        e = str;
        f = str2;
        a();
    }

    public static String b() {
        if (TextUtils.isEmpty(f23793a)) {
            String property = System.getProperty("http.agent");
            LogUtils.d("http.agent:", property);
            f23793a = property;
        }
        LogUtils.d("getDefaultUserAgent:", f23793a);
        return f23793a;
    }

    public static void a(String str) {
        d = str;
        a();
    }

    static void c() {
        if (TextUtils.isEmpty(j)) {
            j = new HashedDeviceIdUtil(AppInfo.a()).getHashedDeviceIdNoThrow();
        }
        String str = ((((((((((((((((("" + " MIOTWeex/") + i) + " (" + e + i.b + AppInfo.f()) + i.b + AppIdManager.a().b()) + i.b + h + ";A;") + AppIdManager.a().c()) + i.b + f) + i.b + j) + Operators.BRACKET_END_STR) + " MIOTStore/") + YPStoreConstant.RN_SDK_VERSION) + " (" + e + i.b + AppInfo.f()) + i.b + AppIdManager.a().b()) + i.b + d + ";A;") + AppIdManager.a().c()) + i.b + f) + i.b + j) + Operators.BRACKET_END_STR;
        c = str;
        b = b(b() + str);
        LogUtils.d("tUserAgentRN:", b);
    }

    public static String d() {
        if (TextUtils.isEmpty(b)) {
            c();
        }
        return b;
    }

    public static String e() {
        return d();
    }

    public static String f() {
        d();
        return c;
    }

    public static String b(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            if (charAt > 31 && charAt < 127) {
                sb.append(charAt);
            }
        }
        return sb.toString();
    }
}
