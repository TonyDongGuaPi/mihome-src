package com.xiaomi.jr.appbase.app;

import android.app.Activity;
import android.os.Bundle;
import com.xiaomi.jr.appbase.MiFiActivityManager;
import com.xiaomi.jr.common.lifecycle.Interceptor;

/* renamed from: com.xiaomi.jr.appbase.app.-$$Lambda$MiFiAppLifecycleImpl$JzAlzqe6dK5tskDVieTM3I27pUI  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$MiFiAppLifecycleImpl$JzAlzqe6dK5tskDVieTM3I27pUI implements Interceptor {
    public static final /* synthetic */ $$Lambda$MiFiAppLifecycleImpl$JzAlzqe6dK5tskDVieTM3I27pUI INSTANCE = new $$Lambda$MiFiAppLifecycleImpl$JzAlzqe6dK5tskDVieTM3I27pUI();

    private /* synthetic */ $$Lambda$MiFiAppLifecycleImpl$JzAlzqe6dK5tskDVieTM3I27pUI() {
    }

    public final boolean process(Activity activity, Bundle bundle) {
        return MiFiActivityManager.a().b(activity);
    }
}
