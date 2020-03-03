package com.xiaomi.push;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.service.f;

class ex implements ev {
    ex() {
    }

    private void a(Context context, String str) {
        String str2;
        String str3;
        try {
            if (TextUtils.isEmpty(str) || context == null) {
                str2 = "provider";
                str3 = "B get a incorrect message";
            } else {
                String[] split = str.split("/");
                if (split.length <= 0 || TextUtils.isEmpty(split[split.length - 1])) {
                    str2 = "provider";
                    str3 = "B get a incorrect message";
                } else {
                    String str4 = split[split.length - 1];
                    if (TextUtils.isEmpty(str4)) {
                        eo.a(context, "provider", 1008, "B get a incorrect message");
                        return;
                    }
                    String decode = Uri.decode(str4);
                    if (TextUtils.isEmpty(decode)) {
                        eo.a(context, "provider", 1008, "B get a incorrect message");
                        return;
                    }
                    String b = en.b(decode);
                    if (!TextUtils.isEmpty(b)) {
                        eo.a(context, b, 1007, "play with provider successfully");
                        return;
                    } else {
                        str2 = "provider";
                        str3 = "B get a incorrect message";
                    }
                }
            }
            eo.a(context, str2, 1008, str3);
        } catch (Exception e) {
            eo.a(context, "provider", 1008, "B meet a exception" + e.getMessage());
        }
    }

    private void b(Context context, er erVar) {
        String b = erVar.b();
        String d = erVar.d();
        int e = erVar.e();
        if (context == null || TextUtils.isEmpty(b) || TextUtils.isEmpty(d)) {
            if (TextUtils.isEmpty(d)) {
                eo.a(context, "provider", 1008, "argument error");
            } else {
                eo.a(context, d, 1008, "argument error");
            }
        } else if (!f.b(context, b)) {
            eo.a(context, d, 1003, "B is not ready");
        } else {
            eo.a(context, d, 1002, "B is ready");
            eo.a(context, d, 1004, "A is ready");
            String a2 = en.a(d);
            try {
                if (TextUtils.isEmpty(a2)) {
                    eo.a(context, d, 1008, "info is empty");
                } else if (e != 1 || es.b(context)) {
                    String type = context.getContentResolver().getType(en.a(b, a2));
                    if (TextUtils.isEmpty(type) || !"success".equals(type)) {
                        eo.a(context, d, 1008, "A is fail to help B's provider");
                        return;
                    }
                    eo.a(context, d, 1005, "A is successful");
                    eo.a(context, d, 1006, "The job is finished");
                } else {
                    eo.a(context, d, 1008, "A not in foreground");
                }
            } catch (Exception e2) {
                b.a((Throwable) e2);
                eo.a(context, d, 1008, "A meet a exception when help B's provider");
            }
        }
    }

    public void a(Context context, Intent intent, String str) {
        a(context, str);
    }

    public void a(Context context, er erVar) {
        if (erVar != null) {
            b(context, erVar);
        } else {
            eo.a(context, "provider", 1008, "A receive incorrect message");
        }
    }
}
