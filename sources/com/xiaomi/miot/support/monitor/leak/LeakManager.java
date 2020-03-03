package com.xiaomi.miot.support.monitor.leak;

import android.content.Context;
import android.support.annotation.NonNull;

public final class LeakManager {

    /* renamed from: a  reason: collision with root package name */
    private static AndroidRefWatcherBuilder f11487a;

    @NonNull
    public static RefWatcher a(@NonNull Context context) {
        return b(context).a();
    }

    @NonNull
    public static AndroidRefWatcherBuilder b(@NonNull Context context) {
        if (f11487a == null) {
            f11487a = new AndroidRefWatcherBuilder(context);
        }
        return f11487a;
    }

    public static void a() {
        if (f11487a != null) {
            f11487a.c();
        }
    }

    public static void b() {
        if (f11487a != null) {
            f11487a.d();
        }
    }

    public static void c() {
        if (f11487a != null) {
            f11487a.e();
            f11487a = null;
        }
    }
}
