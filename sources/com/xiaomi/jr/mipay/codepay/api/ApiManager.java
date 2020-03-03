package com.xiaomi.jr.mipay.codepay.api;

import com.xiaomi.jr.http.MifiHttpManager;
import com.xiaomi.jr.mipay.common.http.MipayHttpManager;

public class ApiManager {

    /* renamed from: a  reason: collision with root package name */
    private static volatile CodePayApi f10886a;
    private static volatile MifiApi b;

    public static CodePayApi a() {
        if (f10886a == null) {
            synchronized (ApiManager.class) {
                if (f10886a == null) {
                    f10886a = (CodePayApi) MipayHttpManager.a().a(CodePayApi.class);
                }
            }
        }
        return f10886a;
    }

    public static MifiApi b() {
        if (b == null) {
            synchronized (ApiManager.class) {
                if (b == null) {
                    b = (MifiApi) MifiHttpManager.a().a(MifiApi.class);
                }
            }
        }
        return b;
    }
}
