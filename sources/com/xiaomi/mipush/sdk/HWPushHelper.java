package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.hms.support.api.push.PushReceiver;
import com.xiaomi.channel.commonutils.logger.b;
import org.json.JSONArray;
import org.json.JSONObject;

public class HWPushHelper {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f11507a = false;

    public static synchronized void a(Context context) {
        synchronized (HWPushHelper.class) {
            context.getSharedPreferences("mipush_extra", 0).edit().putLong("last_connect_time", System.currentTimeMillis()).commit();
        }
    }

    public static void a(Context context, String str) {
        String str2 = "";
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONArray jSONArray = new JSONArray(str);
                if (jSONArray.length() > 0) {
                    int i = 0;
                    while (true) {
                        if (i >= jSONArray.length()) {
                            break;
                        }
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        if (jSONObject.has(PushReceiver.BOUND_KEY.pushMsgKey)) {
                            str2 = jSONObject.getString(PushReceiver.BOUND_KEY.pushMsgKey);
                            break;
                        }
                        i++;
                    }
                }
            } catch (Exception e) {
                b.d(e.toString());
            }
        }
        PushMessageReceiver c = h.c(context);
        if (c != null) {
            MiPushMessage a2 = h.a(str2);
            if (!a2.getExtra().containsKey("notify_effect")) {
                c.onNotificationMessageClicked(context, a2);
            }
        }
    }

    public static void a(Intent intent) {
        h.a(intent);
    }

    public static void a(String str, int i) {
        h.a(str, i);
    }

    public static void a(boolean z) {
        f11507a = z;
    }

    public static boolean a() {
        return f11507a;
    }

    public static void b(Context context, String str) {
        String str2 = "";
        try {
            if (!TextUtils.isEmpty(str)) {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("content")) {
                    str2 = jSONObject.getString("content");
                }
            }
        } catch (Exception e) {
            b.d(e.toString());
        }
        PushMessageReceiver c = h.c(context);
        if (c != null) {
            c.onReceivePassThroughMessage(context, h.a(str2));
        }
    }

    public static synchronized boolean b(Context context) {
        boolean z;
        synchronized (HWPushHelper.class) {
            z = false;
            if (Math.abs(System.currentTimeMillis() - context.getSharedPreferences("mipush_extra", 0).getLong("last_connect_time", -1)) > 5000) {
                z = true;
            }
        }
        return z;
    }

    public static synchronized void c(Context context) {
        synchronized (HWPushHelper.class) {
            context.getSharedPreferences("mipush_extra", 0).edit().putLong("last_get_token_time", System.currentTimeMillis()).commit();
        }
    }

    public static void c(Context context, String str) {
        h.a(context, d.ASSEMBLE_PUSH_HUAWEI, str);
    }

    public static synchronized boolean d(Context context) {
        boolean z;
        synchronized (HWPushHelper.class) {
            z = false;
            if (Math.abs(System.currentTimeMillis() - context.getSharedPreferences("mipush_extra", 0).getLong("last_get_token_time", -1)) > 172800000) {
                z = true;
            }
        }
        return z;
    }

    public static boolean e(Context context) {
        String a2 = h.a(d.ASSEMBLE_PUSH_HUAWEI);
        if (TextUtils.isEmpty(a2)) {
            return false;
        }
        String a3 = h.a(context, a2);
        String a4 = am.a(context).a(bb.UPLOAD_HUAWEI_TOKEN);
        return !TextUtils.isEmpty(a3) && !TextUtils.isEmpty(a4) && "synced".equals(a4);
    }

    public static boolean f(Context context) {
        return MiPushClient.q(context);
    }

    public static void g(Context context) {
        AbstractPushManager c = e.a(context).c(d.ASSEMBLE_PUSH_HUAWEI);
        if (c != null) {
            c.a();
        }
    }

    public static boolean h(Context context) {
        return h.b(context);
    }
}
