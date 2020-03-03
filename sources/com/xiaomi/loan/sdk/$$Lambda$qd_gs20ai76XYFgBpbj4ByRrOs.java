package com.xiaomi.loan.sdk;

import com.xiaomi.jr.appbase.utils.WebUtils;
import com.xiaomi.jr.deeplink.DeeplinkPolicy;

/* renamed from: com.xiaomi.loan.sdk.-$$Lambda$qd_gs20ai76XYFgB-pbj4ByRrOs  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$qd_gs20ai76XYFgBpbj4ByRrOs implements DeeplinkPolicy.DeeplinkMatcher {
    public static final /* synthetic */ $$Lambda$qd_gs20ai76XYFgBpbj4ByRrOs INSTANCE = new $$Lambda$qd_gs20ai76XYFgBpbj4ByRrOs();

    private /* synthetic */ $$Lambda$qd_gs20ai76XYFgBpbj4ByRrOs() {
    }

    public final boolean match(String str) {
        return WebUtils.g(str);
    }
}
