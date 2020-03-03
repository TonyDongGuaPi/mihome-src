package com.xiaomi.smarthome.core.server.internal.util;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.smarthome.core.server.internal.account.AccountManager;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.library.common.util.XMStringUtils;
import com.xiaomi.smarthome.library.commonapi.SystemApi;
import com.xiaomi.smarthome.library.crypto.SHA1Util;
import java.net.URLEncoder;

public class UserAgentUtil {

    /* renamed from: a  reason: collision with root package name */
    private static String f1512a = null;
    private static Object b = new Object();
    private static final String c = "";
    private static final String d = "SmartHome";

    private static boolean a(char c2) {
        return c2 > 31 && c2 < 127;
    }

    public static void a() {
        synchronized (b) {
            f1512a = null;
        }
    }

    public static String a(Context context) {
        synchronized (b) {
            if (f1512a == null) {
                boolean g = ServerCompact.g(context);
                String c2 = SystemApi.a().c(context);
                ServerBean a2 = ServerCompact.a(context);
                String str = a2 != null ? a2.b : "";
                StringBuilder sb = new StringBuilder();
                sb.append(SystemApi.a().d().replace("-", ""));
                sb.append("-");
                sb.append(SystemApi.a().f().replace("-", ""));
                sb.append("-");
                sb.append(SystemApi.a().d(context).replace("-", ""));
                sb.append("-");
                sb.append(SystemApi.a().h().replace("-", ""));
                sb.append("-");
                sb.append(SystemApi.a().g().replace("-", ""));
                sb.append("-");
                sb.append(SystemApi.a().j().replace("-", ""));
                sb.append("-");
                sb.append(SystemApi.a().a(context, g).replace("-", ""));
                sb.append("-");
                sb.append(str.replace("-", ""));
                sb.append("-");
                sb.append((g ? SHA1Util.b(c2) : XMStringUtils.d(c2)).replace("-", ""));
                sb.append("-");
                sb.append(XMStringUtils.d(AccountManager.a().m()));
                sb.append("-");
                sb.append("SmartHome");
                f1512a = a(sb.toString());
            }
        }
        return f1512a;
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (b(str)) {
            return str;
        }
        try {
            StringBuilder sb = new StringBuilder(str.length());
            int length = str.length();
            for (int i = 0; i < length; i++) {
                char charAt = str.charAt(i);
                if (!a(charAt)) {
                    sb.append(URLEncoder.encode(charAt + "", "UTF-8"));
                } else {
                    sb.append(charAt);
                }
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    public static boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!a(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
