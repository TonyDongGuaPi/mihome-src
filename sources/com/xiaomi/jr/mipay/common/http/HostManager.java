package com.xiaomi.jr.mipay.common.http;

import com.xiaomi.jr.common.utils.MifiHostsUtils;
import com.xiaomi.jr.http.SimpleHttpRequest;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class HostManager {

    /* renamed from: a  reason: collision with root package name */
    public static String f10942a = b;
    private static String b = MifiHostsUtils.c("https://api.pay.xiaomi.com/");
    private static String c = MifiHostsUtils.c("https://m.pay.xiaomi.com/");
    private static boolean d;

    public static synchronized void a() {
        synchronized (HostManager.class) {
            if (!d) {
                SimpleHttpRequest.Response b2 = SimpleHttpRequest.b(b + "host", (Map<String, String>) null);
                if (!(b2 == null || !b2.f10820a || b2.b == null)) {
                    try {
                        String string = new JSONObject(b2.b).getString("apiHost");
                        if (string != null && !string.endsWith("/")) {
                            string = string + "/";
                        }
                        b = string;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                d = true;
            }
        }
    }

    public static String b() {
        a();
        return b;
    }

    public static String a(String str) {
        return c + str;
    }
}
