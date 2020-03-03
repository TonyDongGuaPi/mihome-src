package com.xiaomi.jr.mipay.codepay;

import com.xiaomi.jr.deeplink.DeeplinkConfig;
import com.xiaomi.jr.deeplink.DeeplinkPolicy;

/* renamed from: com.xiaomi.jr.mipay.codepay.-$$Lambda$CodePayManager$iTZptB6O0z1wykJB5lduW_hEBMo  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$CodePayManager$iTZptB6O0z1wykJB5lduW_hEBMo implements DeeplinkConfig.RouteBuilder {
    public static final /* synthetic */ $$Lambda$CodePayManager$iTZptB6O0z1wykJB5lduW_hEBMo INSTANCE = new $$Lambda$CodePayManager$iTZptB6O0z1wykJB5lduW_hEBMo();

    private /* synthetic */ $$Lambda$CodePayManager$iTZptB6O0z1wykJB5lduW_hEBMo() {
    }

    public final DeeplinkPolicy.Target build(String str) {
        return CodePayManager.a(str);
    }
}
