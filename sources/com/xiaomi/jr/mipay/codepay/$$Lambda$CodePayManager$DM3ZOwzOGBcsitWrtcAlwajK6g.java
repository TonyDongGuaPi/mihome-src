package com.xiaomi.jr.mipay.codepay;

import com.xiaomi.jr.deeplink.DeeplinkPolicy;
import com.xiaomi.jr.mipay.codepay.ui.CodePayDialogActivity;

/* renamed from: com.xiaomi.jr.mipay.codepay.-$$Lambda$CodePayManager$DM3ZOwzOGBcsitWrtcAlwaj-K6g  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$CodePayManager$DM3ZOwzOGBcsitWrtcAlwajK6g implements DeeplinkPolicy.DeeplinkMatcher {
    public static final /* synthetic */ $$Lambda$CodePayManager$DM3ZOwzOGBcsitWrtcAlwajK6g INSTANCE = new $$Lambda$CodePayManager$DM3ZOwzOGBcsitWrtcAlwajK6g();

    private /* synthetic */ $$Lambda$CodePayManager$DM3ZOwzOGBcsitWrtcAlwajK6g() {
    }

    public final boolean match(String str) {
        return str.startsWith(CodePayDialogActivity.DEEPLINK_PREFIX);
    }
}
