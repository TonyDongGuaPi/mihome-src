package com.xiaomi.loan.sdk;

import android.content.Intent;
import com.xiaomi.jr.deeplink.CustomDeeplinkHandler;
import com.xiaomi.jr.deeplink.DeeplinkUtils;

/* renamed from: com.xiaomi.loan.sdk.-$$Lambda$MiFiDeeplinkConfig$EDfGDGMSJRVudB2Z8yQ-IfsG6-I  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$MiFiDeeplinkConfig$EDfGDGMSJRVudB2Z8yQIfsG6I implements CustomDeeplinkHandler.Handler {
    public static final /* synthetic */ $$Lambda$MiFiDeeplinkConfig$EDfGDGMSJRVudB2Z8yQIfsG6I INSTANCE = new $$Lambda$MiFiDeeplinkConfig$EDfGDGMSJRVudB2Z8yQIfsG6I();

    private /* synthetic */ $$Lambda$MiFiDeeplinkConfig$EDfGDGMSJRVudB2Z8yQIfsG6I() {
    }

    public final void handle(Object obj, Intent intent) {
        DeeplinkUtils.openExternalUrl(obj, intent.getStringExtra("url"), intent.getStringExtra("fallback"));
    }
}
