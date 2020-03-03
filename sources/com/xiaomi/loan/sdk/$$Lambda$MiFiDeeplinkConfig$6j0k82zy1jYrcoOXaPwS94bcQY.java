package com.xiaomi.loan.sdk;

import com.xiaomi.jr.appbase.utils.AppConstants;
import com.xiaomi.jr.common.utils.UrlUtils;
import com.xiaomi.jr.deeplink.DeeplinkPolicy;

/* renamed from: com.xiaomi.loan.sdk.-$$Lambda$MiFiDeeplinkConfig$6j0k82zy1jYrcoOXaPw-S94bcQY  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$MiFiDeeplinkConfig$6j0k82zy1jYrcoOXaPwS94bcQY implements DeeplinkPolicy.DeeplinkMatcher {
    public static final /* synthetic */ $$Lambda$MiFiDeeplinkConfig$6j0k82zy1jYrcoOXaPwS94bcQY INSTANCE = new $$Lambda$MiFiDeeplinkConfig$6j0k82zy1jYrcoOXaPwS94bcQY();

    private /* synthetic */ $$Lambda$MiFiDeeplinkConfig$6j0k82zy1jYrcoOXaPwS94bcQY() {
    }

    public final boolean match(String str) {
        return Boolean.parseBoolean(UrlUtils.b(str, AppConstants.h));
    }
}
