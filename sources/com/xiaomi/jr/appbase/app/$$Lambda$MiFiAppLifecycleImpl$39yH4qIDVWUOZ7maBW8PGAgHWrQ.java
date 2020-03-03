package com.xiaomi.jr.appbase.app;

import android.app.Activity;
import android.os.Bundle;
import com.xiaomi.jr.appbase.MiFiActivityManager;
import com.xiaomi.jr.common.lifecycle.Interceptor;

/* renamed from: com.xiaomi.jr.appbase.app.-$$Lambda$MiFiAppLifecycleImpl$39yH4qIDVWUOZ7maBW8PGAgHWrQ  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$MiFiAppLifecycleImpl$39yH4qIDVWUOZ7maBW8PGAgHWrQ implements Interceptor {
    public static final /* synthetic */ $$Lambda$MiFiAppLifecycleImpl$39yH4qIDVWUOZ7maBW8PGAgHWrQ INSTANCE = new $$Lambda$MiFiAppLifecycleImpl$39yH4qIDVWUOZ7maBW8PGAgHWrQ();

    private /* synthetic */ $$Lambda$MiFiAppLifecycleImpl$39yH4qIDVWUOZ7maBW8PGAgHWrQ() {
    }

    public final boolean process(Activity activity, Bundle bundle) {
        return MiFiActivityManager.a().a(activity);
    }
}
