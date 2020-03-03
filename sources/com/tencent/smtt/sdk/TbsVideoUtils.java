package com.tencent.smtt.sdk;

import android.content.Context;
import com.tencent.smtt.export.external.DexLoader;

public class TbsVideoUtils {

    /* renamed from: a  reason: collision with root package name */
    private static bf f9104a;

    private static void a(Context context) {
        synchronized (TbsVideoUtils.class) {
            if (f9104a == null) {
                o.a(true).a(context, false, false);
                bh a2 = o.a(true).a();
                DexLoader dexLoader = null;
                if (a2 != null) {
                    dexLoader = a2.b();
                }
                if (dexLoader != null) {
                    f9104a = new bf(dexLoader);
                }
            }
        }
    }

    public static void deleteVideoCache(Context context, String str) {
        a(context);
        if (f9104a != null) {
            f9104a.a(context, str);
        }
    }

    public static String getCurWDPDecodeType(Context context) {
        a(context);
        return f9104a != null ? f9104a.a(context) : "";
    }
}
