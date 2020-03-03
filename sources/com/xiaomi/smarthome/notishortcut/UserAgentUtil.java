package com.xiaomi.smarthome.notishortcut;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.library.common.util.XMStringUtils;
import com.xiaomi.smarthome.library.commonapi.SystemApi;
import com.xiaomi.smarthome.library.crypto.SHA1Util;
import java.net.URLEncoder;

public class UserAgentUtil {

    /* renamed from: a  reason: collision with root package name */
    private static String f21023a;
    private static Object b = new Object();

    private static boolean a(char c) {
        return c > 31 && c < 127;
    }

    public static String a(Context context) {
        synchronized (b) {
            if (f21023a == null) {
                boolean g = ServerCompact.g(context);
                String c = SystemApi.a().c(context);
                StringBuilder sb = new StringBuilder();
                sb.append(SystemApi.a().d().replace("-", ""));
                sb.append("-");
                sb.append(SystemApi.a().f().replace("-", ""));
                sb.append("-");
                sb.append(SystemApi.a().d(context).replace("-", ""));
                sb.append("-");
                sb.append(SystemApi.a().g().replace("-", ""));
                sb.append("-");
                sb.append(SystemApi.a().j().replace("-", ""));
                sb.append("-");
                sb.append(SystemApi.a().a(context, g).replace("-", ""));
                sb.append("-");
                sb.append((g ? SHA1Util.b(c) : XMStringUtils.d(c)).replace("-", ""));
                sb.append("-");
                sb.append(AccountManager.a(context));
                f21023a = a(sb.toString());
            }
        }
        return f21023a;
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
