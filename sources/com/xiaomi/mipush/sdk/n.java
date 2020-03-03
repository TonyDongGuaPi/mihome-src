package com.xiaomi.mipush.sdk;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.ai;
import com.xiaomi.push.es;
import com.xiaomi.push.eu;
import com.xiaomi.push.ew;
import com.xiaomi.push.ht;
import com.xiaomi.push.hy;
import com.xiaomi.push.in;
import com.xiaomi.push.iy;
import com.xiaomi.push.iz;
import com.xiaomi.push.l;
import com.xiaomi.push.service.ah;
import com.xiaomi.push.service.ak;
import java.util.HashMap;
import java.util.Map;

public class n {
    public static void a(Context context, Intent intent, Uri uri) {
        es a2;
        eu euVar;
        if (context != null) {
            aw.a(context).a();
            if (es.a(context.getApplicationContext()).a() == null) {
                es.a(context.getApplicationContext()).a(b.a(context.getApplicationContext()).c(), context.getPackageName(), ah.a(context.getApplicationContext()).a(ht.AwakeInfoUploadWaySwitch.a(), 0), (ew) new c());
                ah.a(context).a((ah.a) new p(102, "awake online config", context));
            }
            if ((context instanceof Activity) && intent != null) {
                a2 = es.a(context.getApplicationContext());
                euVar = eu.ACTIVITY;
            } else if (!(context instanceof Service) || intent == null) {
                if (uri != null && !TextUtils.isEmpty(uri.toString())) {
                    es.a(context.getApplicationContext()).a(eu.PROVIDER, context, (Intent) null, uri.toString());
                    return;
                }
                return;
            } else if ("com.xiaomi.mipush.sdk.WAKEUP".equals(intent.getAction())) {
                a2 = es.a(context.getApplicationContext());
                euVar = eu.SERVICE_COMPONENT;
            } else {
                a2 = es.a(context.getApplicationContext());
                euVar = eu.SERVICE_ACTION;
            }
            a2.a(euVar, context, intent, (String) null);
        }
    }

    private static void a(Context context, in inVar) {
        boolean a2 = ah.a(context).a(ht.AwakeAppPingSwitch.a(), false);
        int a3 = ah.a(context).a(ht.AwakeAppPingFrequency.a(), 0);
        if (a3 >= 0 && a3 < 30) {
            b.c("aw_ping: frquency need > 30s.");
            a3 = 30;
        }
        if (a3 < 0) {
            a2 = false;
        }
        if (!l.a()) {
            a(context, inVar, a2, a3);
        } else if (a2) {
            ai.a(context.getApplicationContext()).a((ai.a) new o(inVar, context), a3);
        }
    }

    public static final <T extends iz<T, ?>> void a(Context context, T t, boolean z, int i) {
        byte[] a2 = iy.a(t);
        if (a2 == null) {
            b.a("send message fail, because msgBytes is null.");
            return;
        }
        Intent intent = new Intent();
        intent.setAction("action_help_ping");
        intent.putExtra("extra_help_ping_switch", z);
        intent.putExtra("extra_help_ping_frequency", i);
        intent.putExtra("mipush_payload", a2);
        intent.putExtra("com.xiaomi.mipush.MESSAGE_CACHE", true);
        aw.a(context).a(intent);
    }

    public static void a(Context context, String str) {
        b.a("aw_ping : send aw_ping cmd and content to push service from 3rd app");
        HashMap hashMap = new HashMap();
        hashMap.put("awake_info", str);
        hashMap.put("event_type", String.valueOf(9999));
        hashMap.put("description", "ping message");
        in inVar = new in();
        inVar.b(b.a(context).c());
        inVar.d(context.getPackageName());
        inVar.c(hy.AwakeAppResponse.f114a);
        inVar.a(ak.a());
        inVar.f177a = hashMap;
        a(context, inVar);
    }

    public static void a(Context context, String str, int i, String str2) {
        in inVar = new in();
        inVar.b(str);
        inVar.a((Map<String, String>) new HashMap());
        inVar.a().put("extra_aw_app_online_cmd", String.valueOf(i));
        inVar.a().put("extra_help_aw_info", str2);
        inVar.a(ak.a());
        byte[] a2 = iy.a(inVar);
        if (a2 == null) {
            b.a("send message fail, because msgBytes is null.");
            return;
        }
        Intent intent = new Intent();
        intent.setAction("action_aw_app_logic");
        intent.putExtra("mipush_payload", a2);
        aw.a(context).a(intent);
    }
}
