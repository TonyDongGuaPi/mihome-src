package com.xiaomi.smarthome.newui.card;

import java.util.concurrent.ThreadFactory;

/* renamed from: com.xiaomi.smarthome.newui.card.-$$Lambda$SpecCache$xzM8rgb5BQYxS3dM6_dCGJInh-g  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$SpecCache$xzM8rgb5BQYxS3dM6_dCGJInhg implements ThreadFactory {
    public static final /* synthetic */ $$Lambda$SpecCache$xzM8rgb5BQYxS3dM6_dCGJInhg INSTANCE = new $$Lambda$SpecCache$xzM8rgb5BQYxS3dM6_dCGJInhg();

    private /* synthetic */ $$Lambda$SpecCache$xzM8rgb5BQYxS3dM6_dCGJInhg() {
    }

    public final Thread newThread(Runnable runnable) {
        return SpecCache.b(runnable);
    }
}
