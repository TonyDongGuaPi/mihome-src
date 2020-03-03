package com.xiaomi.push.service;

import android.content.Context;
import com.xiaomi.mipush.sdk.Constants;
import com.xiaomi.push.g;
import com.xiaomi.push.service.am;
import com.xiaomi.push.t;
import com.xiaomi.stat.c.c;
import java.util.Locale;

public class k {

    /* renamed from: a  reason: collision with root package name */
    public final int f12930a;

    /* renamed from: a  reason: collision with other field name */
    public final String f346a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final String f;

    public k(String str, String str2, String str3, String str4, String str5, String str6, int i) {
        this.f346a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
        this.f = str6;
        this.f12930a = i;
    }

    public static boolean a() {
        try {
            return t.a((Context) null, "miui.os.Build").getField("IS_ALPHA_BUILD").getBoolean((Object) null);
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean a(Context context) {
        return c.f23036a.equals(context.getPackageName()) && a();
    }

    private static boolean b(Context context) {
        return context.getPackageName().equals(c.f23036a);
    }

    public am.b a(XMPushService xMPushService) {
        am.b bVar = new am.b(xMPushService);
        a(bVar, xMPushService, xMPushService.b(), "c");
        return bVar;
    }

    public am.b a(am.b bVar, Context context, d dVar, String str) {
        bVar.f290a = context.getPackageName();
        bVar.f293b = this.f346a;
        bVar.h = this.c;
        bVar.c = this.b;
        bVar.g = "5";
        bVar.d = "XMPUSH-PASS";
        bVar.f292a = false;
        String str2 = "";
        if (b(context)) {
            str2 = g.b(context);
        }
        bVar.e = String.format("%1$s:%2$s,%3$s:%4$s,%5$s:%6$s:%7$s:%8$s,%9$s:%10$s,%11$s:%12$s", new Object[]{"sdk_ver", 39, "cpvn", "3_7_2", "cpvc", 30702, "aapn", str2, "country_code", a.a(context).b(), "region", a.a(context).a()});
        bVar.f = String.format("%1$s:%2$s,%3$s:%4$s,%5$s:%6$s,sync:1", new Object[]{"appid", b(context) ? "1000271" : this.d, "locale", Locale.getDefault().toString(), Constants.o, t.c(context)});
        if (a(context)) {
            bVar.f += String.format(",%1$s:%2$s", new Object[]{"ab", str});
        }
        bVar.f289a = dVar;
        return bVar;
    }
}
