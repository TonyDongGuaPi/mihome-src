package com.xiaomi.push;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.b;
import java.util.HashMap;

public class eo {
    public static void a(Context context, String str, int i, String str2) {
        ai.a(context).a((Runnable) new ep(context, str, i, str2));
    }

    private static void a(Context context, HashMap<String, String> hashMap) {
        ew a2 = es.a(context).a();
        if (a2 != null) {
            a2.a(context, hashMap);
        }
    }

    private static void b(Context context, HashMap<String, String> hashMap) {
        ew a2 = es.a(context).a();
        if (a2 != null) {
            a2.c(context, hashMap);
        }
    }

    /* access modifiers changed from: private */
    public static void c(Context context, String str, int i, String str2) {
        if (context != null && !TextUtils.isEmpty(str)) {
            try {
                HashMap hashMap = new HashMap();
                hashMap.put("awake_info", str);
                hashMap.put("event_type", String.valueOf(i));
                hashMap.put("description", str2);
                switch (es.a(context).d()) {
                    case 1:
                        a(context, hashMap);
                        break;
                    case 2:
                        break;
                    case 3:
                        a(context, hashMap);
                        break;
                }
                c(context, hashMap);
                b(context, hashMap);
            } catch (Exception e) {
                b.a((Throwable) e);
            }
        }
    }

    private static void c(Context context, HashMap<String, String> hashMap) {
        ew a2 = es.a(context).a();
        if (a2 != null) {
            a2.b(context, hashMap);
        }
    }
}
