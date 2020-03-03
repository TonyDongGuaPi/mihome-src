package com.xiaomi.jr.mipay.common;

import android.content.Context;
import android.support.annotation.NonNull;
import com.xiaomi.jr.mipay.common.http.MipayApi;
import com.xiaomi.jr.mipay.common.http.MipayHttpManager;

public class MipayManager {

    /* renamed from: a  reason: collision with root package name */
    private static Context f1447a;
    private static MipayApi b;

    public static void a(@NonNull Context context) {
        f1447a = context.getApplicationContext();
        MipayHttpManager.a(f1447a);
        b = (MipayApi) MipayHttpManager.a().a(MipayApi.class);
    }

    public static Context a() {
        return f1447a;
    }

    public static MipayApi b() {
        return b;
    }
}
