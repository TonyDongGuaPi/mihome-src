package com.alipay.sdk.sys;

import android.content.Context;
import com.alipay.sdk.app.statistic.a;
import com.alipay.sdk.data.c;
import com.ta.utdid2.device.UTDevice;
import java.io.File;

public class b {

    /* renamed from: a  reason: collision with root package name */
    private static b f1122a;
    private Context b;

    private b() {
    }

    public static b a() {
        if (f1122a == null) {
            f1122a = new b();
        }
        return f1122a;
    }

    public Context b() {
        return this.b;
    }

    public void a(Context context, c cVar) {
        this.b = context.getApplicationContext();
    }

    public c c() {
        return c.b();
    }

    public static boolean d() {
        for (String file : new String[]{"/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su", "/su/bin/su"}) {
            if (new File(file).exists()) {
                return true;
            }
        }
        return false;
    }

    public String e() {
        try {
            return UTDevice.getUtdid(this.b);
        } catch (Throwable th) {
            com.alipay.sdk.util.c.a(th);
            a.a(com.alipay.sdk.app.statistic.c.e, com.alipay.sdk.app.statistic.c.k, th);
            return "";
        }
    }
}
