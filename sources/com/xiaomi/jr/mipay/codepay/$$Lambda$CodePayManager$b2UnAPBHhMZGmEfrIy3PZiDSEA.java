package com.xiaomi.jr.mipay.codepay;

import com.xiaomi.jr.common.utils.MifiHostsUtils;
import com.xiaomi.jr.deeplink.DeeplinkPolicy;
import com.xiaomi.jr.mipay.codepay.ui.CodePayActivity;

/* renamed from: com.xiaomi.jr.mipay.codepay.-$$Lambda$CodePayManager$b2UnAPBHhMZGmEfrIy3PZiDS-EA  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$CodePayManager$b2UnAPBHhMZGmEfrIy3PZiDSEA implements DeeplinkPolicy.DeeplinkMatcher {
    public static final /* synthetic */ $$Lambda$CodePayManager$b2UnAPBHhMZGmEfrIy3PZiDSEA INSTANCE = new $$Lambda$CodePayManager$b2UnAPBHhMZGmEfrIy3PZiDSEA();

    private /* synthetic */ $$Lambda$CodePayManager$b2UnAPBHhMZGmEfrIy3PZiDSEA() {
    }

    public final boolean match(String str) {
        return str.startsWith(MifiHostsUtils.h(CodePayActivity.DEEPLINK_PREFIX));
    }
}
