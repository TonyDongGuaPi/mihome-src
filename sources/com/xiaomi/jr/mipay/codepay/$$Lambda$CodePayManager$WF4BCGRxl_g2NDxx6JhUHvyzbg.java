package com.xiaomi.jr.mipay.codepay;

import com.xiaomi.jr.deeplink.DeeplinkConfig;
import com.xiaomi.jr.deeplink.DeeplinkPolicy;

/* renamed from: com.xiaomi.jr.mipay.codepay.-$$Lambda$CodePayManager$WF4BC-GRxl_g2NDxx6JhUHvyzbg  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$CodePayManager$WF4BCGRxl_g2NDxx6JhUHvyzbg implements DeeplinkConfig.RouteBuilder {
    public static final /* synthetic */ $$Lambda$CodePayManager$WF4BCGRxl_g2NDxx6JhUHvyzbg INSTANCE = new $$Lambda$CodePayManager$WF4BCGRxl_g2NDxx6JhUHvyzbg();

    private /* synthetic */ $$Lambda$CodePayManager$WF4BCGRxl_g2NDxx6JhUHvyzbg() {
    }

    public final DeeplinkPolicy.Target build(String str) {
        return CodePayManager.c(str);
    }
}
