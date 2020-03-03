package com.xiaomi.push;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.service.f;

class eq implements ev {
    eq() {
    }

    private void a(Activity activity, Intent intent) {
        String stringExtra = intent.getStringExtra("awake_info");
        if (!TextUtils.isEmpty(stringExtra)) {
            String b = en.b(stringExtra);
            if (!TextUtils.isEmpty(b)) {
                eo.a(activity.getApplicationContext(), b, 1007, "play with activity successfully");
                return;
            }
        }
        eo.a(activity.getApplicationContext(), "activity", 1008, "B get incorrect message");
    }

    private void b(Context context, er erVar) {
        String a2 = erVar.a();
        String b = erVar.b();
        String d = erVar.d();
        int e = erVar.e();
        if (context == null || TextUtils.isEmpty(a2) || TextUtils.isEmpty(b) || TextUtils.isEmpty(d)) {
            if (TextUtils.isEmpty(d)) {
                eo.a(context, "activity", 1008, "argument error");
            } else {
                eo.a(context, d, 1008, "argument error");
            }
        } else if (!f.b(context, a2, b)) {
            eo.a(context, d, 1003, "B is not ready");
        } else {
            eo.a(context, d, 1002, "B is ready");
            eo.a(context, d, 1004, "A is ready");
            Intent intent = new Intent(b);
            intent.setPackage(a2);
            intent.putExtra("awake_info", en.a(d));
            intent.addFlags(276824064);
            intent.setAction(b);
            if (e == 1) {
                try {
                    if (!es.b(context)) {
                        eo.a(context, d, 1008, "A not in foreground");
                        return;
                    }
                } catch (Exception e2) {
                    b.a((Throwable) e2);
                    eo.a(context, d, 1008, "A meet a exception when help B's activity");
                    return;
                }
            }
            context.startActivity(intent);
            eo.a(context, d, 1005, "A is successful");
            eo.a(context, d, 1006, "The job is finished");
        }
    }

    public void a(Context context, Intent intent, String str) {
        if (context == null || !(context instanceof Activity) || intent == null) {
            eo.a(context, "activity", 1008, "B receive incorrect message");
        } else {
            a((Activity) context, intent);
        }
    }

    public void a(Context context, er erVar) {
        if (erVar != null) {
            b(context, erVar);
        } else {
            eo.a(context, "activity", 1008, "A receive incorrect message");
        }
    }
}
