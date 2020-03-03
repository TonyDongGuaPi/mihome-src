package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.google.android.gms.measurement.AppMeasurement;
import com.huawei.hms.support.api.push.PushReceiver;
import java.util.Map;

public class FCMPushHelper {
    public static void a() {
        MiTinyDataClient.a(h.b(d.ASSEMBLE_PUSH_FCM), AppMeasurement.FCM_ORIGIN, 1, "some fcm messages was deleted ");
    }

    public static void a(Context context) {
        h.a(context, d.ASSEMBLE_PUSH_FCM);
    }

    public static void a(Context context, String str) {
        h.a(context, d.ASSEMBLE_PUSH_FCM, str);
    }

    public static void a(Context context, Map<String, String> map) {
        PushMessageReceiver c;
        String str = map.get(PushReceiver.BOUND_KEY.pushMsgKey);
        if (!TextUtils.isEmpty(str) && (c = h.c(context)) != null) {
            c.onNotificationMessageArrived(context, h.a(str));
        }
    }

    public static void a(Intent intent) {
        h.a(intent);
    }

    public static void b(Context context, Map<String, String> map) {
        PushMessageReceiver c;
        String str = map.get(PushReceiver.BOUND_KEY.pushMsgKey);
        if (!TextUtils.isEmpty(str) && (c = h.c(context)) != null) {
            c.onReceivePassThroughMessage(context, h.a(str));
        }
    }

    public static boolean b(Context context) {
        return h.b(context, d.ASSEMBLE_PUSH_FCM) && MiPushClient.r(context);
    }
}
