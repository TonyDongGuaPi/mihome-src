package com.squareup.leakcanary;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

public final class LeakCanary {
    public static boolean a(@NonNull Context context) {
        return false;
    }

    @NonNull
    public static RefWatcher a(@NonNull Application application) {
        return RefWatcher.f8872a;
    }

    @NonNull
    public static RefWatcher a() {
        return RefWatcher.f8872a;
    }

    private LeakCanary() {
        throw new AssertionError();
    }
}
