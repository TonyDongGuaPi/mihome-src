package com.amap.openapi;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.amap.location.common.HeaderConfig;
import com.amap.location.common.a;
import com.loc.fc;

public class bk {
    public static int a(fc fcVar, Context context) {
        a();
        try {
            int a2 = fcVar.a((CharSequence) context.getPackageName());
            int a3 = fcVar.a((CharSequence) HeaderConfig.b());
            int a4 = TextUtils.isEmpty(a.c(context)) ? Integer.MIN_VALUE : fcVar.a((CharSequence) a.c(context));
            String b = a.b(context);
            int a5 = TextUtils.isEmpty(b) ? Integer.MIN_VALUE : fcVar.a((CharSequence) b);
            String a6 = a.a(context);
            int a7 = TextUtils.isEmpty(a6) ? Integer.MIN_VALUE : fcVar.a((CharSequence) a6);
            String d = a.d(context);
            int a8 = TextUtils.isEmpty(d) ? Integer.MIN_VALUE : fcVar.a((CharSequence) d);
            String str = Build.BRAND;
            if (str != null && str.length() > 16) {
                str = str.substring(0, 16);
            }
            int a9 = TextUtils.isEmpty(str) ? Integer.MIN_VALUE : fcVar.a((CharSequence) str);
            String str2 = Build.MODEL;
            if (str2 != null && str2.length() > 16) {
                str2 = str2.substring(0, 16);
            }
            int a10 = TextUtils.isEmpty(str2) ? Integer.MIN_VALUE : fcVar.a((CharSequence) str2);
            int a11 = TextUtils.isEmpty(HeaderConfig.c()) ? Integer.MIN_VALUE : fcVar.a((CharSequence) HeaderConfig.c());
            int a12 = TextUtils.isEmpty(HeaderConfig.d()) ? Integer.MIN_VALUE : fcVar.a((CharSequence) HeaderConfig.d());
            bl.a(fcVar);
            bl.a(fcVar, HeaderConfig.a());
            bl.a(fcVar, a2);
            bl.b(fcVar, a3);
            bl.b(fcVar, (byte) a.d());
            bl.a(fcVar, a.e(context));
            if (a4 != Integer.MIN_VALUE) {
                bl.c(fcVar, a4);
            }
            if (a5 != Integer.MIN_VALUE) {
                bl.d(fcVar, a5);
            }
            if (a7 != Integer.MIN_VALUE) {
                bl.e(fcVar, a7);
            }
            if (a8 != Integer.MIN_VALUE) {
                bl.f(fcVar, a8);
            }
            if (a9 != Integer.MIN_VALUE) {
                bl.g(fcVar, a9);
            }
            if (a10 != Integer.MIN_VALUE) {
                bl.h(fcVar, a10);
            }
            if (a11 != Integer.MIN_VALUE) {
                bl.i(fcVar, a11);
            }
            if (a12 != Integer.MIN_VALUE) {
                bl.j(fcVar, a12);
            }
            return bl.b(fcVar);
        } catch (Error | Exception unused) {
            return 0;
        }
    }

    private static void a() {
        if (HeaderConfig.a() < 0 || TextUtils.isEmpty(HeaderConfig.b())) {
            throw new RuntimeException("必须在 HeaderBuildre 中，设置好 productId, productVerion" + HeaderConfig.a() + HeaderConfig.b() + "， 以及其他的值");
        }
    }
}
