package com.mipay.common.data;

import android.content.Context;
import android.text.TextUtils;
import com.mipay.common.data.e;

public class f {
    public static final e a(Context context, String str) {
        return a(context, str, false);
    }

    public static final e a(Context context, String str, boolean z) {
        e eVar = new e(str);
        if (z) {
            a(context, eVar);
        }
        return eVar;
    }

    private static final void a(Context context, e eVar) {
        e.b d = eVar.d();
        if (TextUtils.isEmpty(b.e)) {
            b.a(context);
        }
        d.a("la", (Object) b.a());
        d.a("co", (Object) b.b());
        d.a("uuid", (Object) b.b);
        d.a("package", (Object) b.e);
        d.a("apkSign", (Object) b.f);
        d.a("version", (Object) b.c);
        d.a("versionCode", (Object) Integer.valueOf(b.d));
        d.a("networkType", (Object) Integer.valueOf(b.c(context)));
        d.a("networkMeter", (Object) Boolean.valueOf(b.b(context)));
    }
}
