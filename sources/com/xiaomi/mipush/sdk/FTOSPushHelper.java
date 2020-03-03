package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import com.huawei.hms.support.api.push.PushReceiver;
import com.xiaomi.channel.commonutils.logger.b;
import java.util.Map;

public class FTOSPushHelper {

    /* renamed from: a  reason: collision with root package name */
    private static long f11506a = 0;
    private static volatile boolean b = false;

    public static void a(Context context, String str) {
        h.a(context, d.ASSEMBLE_PUSH_FTOS, str);
    }

    public static void a(Context context, Map<String, String> map) {
        PushMessageReceiver c;
        if (map != null && map.containsKey(PushReceiver.BOUND_KEY.pushMsgKey)) {
            String str = map.get(PushReceiver.BOUND_KEY.pushMsgKey);
            if (!TextUtils.isEmpty(str) && (c = h.c(context)) != null) {
                MiPushMessage a2 = h.a(str);
                if (!a2.getExtra().containsKey("notify_effect")) {
                    c.onNotificationMessageClicked(context, a2);
                }
            }
        }
    }

    public static void a(boolean z) {
        b = z;
    }

    public static boolean a() {
        return b;
    }

    public static boolean a(Context context) {
        return h.b(context);
    }

    public static void b(Context context) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (!a()) {
            return;
        }
        if (f11506a <= 0 || f11506a + 300000 <= elapsedRealtime) {
            f11506a = elapsedRealtime;
            c(context);
        }
    }

    private static void c(Context context) {
        AbstractPushManager c = e.a(context).c(d.ASSEMBLE_PUSH_FTOS);
        if (c != null) {
            b.a("ASSEMBLE_PUSH :  register fun touch os when network change!");
            c.a();
        }
    }
}
