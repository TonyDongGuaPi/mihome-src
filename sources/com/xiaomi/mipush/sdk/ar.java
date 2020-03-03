package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.mipush.sdk.k;
import com.xiaomi.push.ba;

public class ar {
    public static AbstractPushManager a(Context context, d dVar) {
        return b(context, dVar);
    }

    private static AbstractPushManager b(Context context, d dVar) {
        k.a a2 = k.a(dVar);
        if (a2 == null || TextUtils.isEmpty(a2.f11557a) || TextUtils.isEmpty(a2.b)) {
            return null;
        }
        return (AbstractPushManager) ba.a(a2.f11557a, a2.b, context);
    }
}
