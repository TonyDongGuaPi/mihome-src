package com.xiaomi.mistatistic.sdk.controller;

import android.annotation.TargetApi;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.xiaomi.mistatistic.sdk.controller.d;

public class e {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static String f12022a;
    private static String b;

    public String a() {
        if (f12022a != null) {
            return f12022a;
        }
        d.a().a((d.a) new a(c.a()));
        return null;
    }

    private static class a implements d.a {

        /* renamed from: a  reason: collision with root package name */
        private Context f12023a;

        public a(Context context) {
            this.f12023a = context;
        }

        public void a() {
            String a2 = k.a(this.f12023a, "device_id", "");
            if (TextUtils.isEmpty(a2)) {
                String unused = e.f12022a = e.a(this.f12023a);
                k.b(this.f12023a, "device_id", e.f12022a);
                h.b("DeviceIdHolder", "persisted deviceId " + e.f12022a);
                return;
            }
            String unused2 = e.f12022a = a2;
        }
    }

    public static String a(Context context) {
        if (TextUtils.isEmpty(f12022a)) {
            String b2 = b(context);
            String c = q.c(context);
            String a2 = q.a();
            String c2 = q.c(b2 + c + a2);
            h.b("DeviceIdHolder", String.format("deviceId %s, %s, %s, %s", new Object[]{c2, b2, c, a2}));
            return c2;
        }
        String str = f12022a;
        h.b("DeviceIdHolder", String.format("cached deviceId %s", new Object[]{str}));
        return str;
    }

    @TargetApi(9)
    public static String b(Context context) {
        try {
            if (TextUtils.isEmpty(b)) {
                b = k.a(context, "imei", "");
                if (TextUtils.isEmpty(b)) {
                    if (context.getPackageManager().checkPermission("android.permission.READ_PHONE_STATE", context.getPackageName()) == 0) {
                        b = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
                        k.b(context, "imei", b);
                    } else {
                        h.d("cannot get READ_PHONE_STATE permission");
                    }
                }
            }
        } catch (Throwable th) {
            h.a("getImei exception:", th);
        }
        if (TextUtils.isEmpty(b)) {
            h.c("Imei is empty");
        }
        return b;
    }
}
