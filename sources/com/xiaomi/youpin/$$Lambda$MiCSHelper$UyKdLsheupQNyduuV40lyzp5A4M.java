package com.xiaomi.youpin;

import com.mics.core.MiCS;
import com.xiaomi.plugin.XmPluginHostApi;

/* renamed from: com.xiaomi.youpin.-$$Lambda$MiCSHelper$UyKdLsheupQNyduuV40lyzp5A4M  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$MiCSHelper$UyKdLsheupQNyduuV40lyzp5A4M implements MiCS.UrlDispatchInterceptor {
    public static final /* synthetic */ $$Lambda$MiCSHelper$UyKdLsheupQNyduuV40lyzp5A4M INSTANCE = new $$Lambda$MiCSHelper$UyKdLsheupQNyduuV40lyzp5A4M();

    private /* synthetic */ $$Lambda$MiCSHelper$UyKdLsheupQNyduuV40lyzp5A4M() {
    }

    public final void openUrl(String str) {
        XmPluginHostApi.instance().openUrl(str);
    }
}
