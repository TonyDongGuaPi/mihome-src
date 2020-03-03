package com.xiaomi.push;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.service.f;

class ez implements ev {
    ez() {
    }

    private void a(Service service, Intent intent) {
        if ("com.xiaomi.mipush.sdk.WAKEUP".equals(intent.getAction())) {
            String stringExtra = intent.getStringExtra("waker_pkgname");
            String stringExtra2 = intent.getStringExtra("awake_info");
            if (TextUtils.isEmpty(stringExtra)) {
                eo.a(service.getApplicationContext(), "service", 1007, "old version message");
            } else if (!TextUtils.isEmpty(stringExtra2)) {
                String b = en.b(stringExtra2);
                if (!TextUtils.isEmpty(b)) {
                    eo.a(service.getApplicationContext(), b, 1007, "old version message ");
                } else {
                    eo.a(service.getApplicationContext(), "service", 1008, "B get a incorrect message");
                }
            } else {
                eo.a(service.getApplicationContext(), stringExtra, 1007, "play with service ");
            }
        }
    }

    private void a(Context context, String str, String str2, String str3) {
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            if (TextUtils.isEmpty(str3)) {
                eo.a(context, "service", 1008, "argument error");
            } else {
                eo.a(context, str3, 1008, "argument error");
            }
        } else if (!f.a(context, str)) {
            eo.a(context, str3, 1003, "B is not ready");
        } else {
            eo.a(context, str3, 1002, "B is ready");
            eo.a(context, str3, 1004, "A is ready");
            try {
                Intent intent = new Intent();
                intent.setClassName(str, str2);
                intent.setAction("com.xiaomi.mipush.sdk.WAKEUP");
                intent.putExtra("waker_pkgname", context.getPackageName());
                intent.putExtra("awake_info", en.a(str3));
                if (context.startService(intent) != null) {
                    eo.a(context, str3, 1005, "A is successful");
                    eo.a(context, str3, 1006, "The job is finished");
                    return;
                }
                eo.a(context, str3, 1008, "A is fail to help B's service");
            } catch (Exception e) {
                b.a((Throwable) e);
                eo.a(context, str3, 1008, "A meet a exception when help B's service");
            }
        }
    }

    public void a(Context context, Intent intent, String str) {
        if (context != null && (context instanceof Service)) {
            a((Service) context, intent);
        }
    }

    public void a(Context context, er erVar) {
        if (erVar != null) {
            a(context, erVar.a(), erVar.c(), erVar.d());
        }
    }
}
