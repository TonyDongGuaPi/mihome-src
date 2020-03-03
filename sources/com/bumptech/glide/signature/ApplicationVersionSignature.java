package com.bumptech.glide.signature;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import com.bumptech.glide.load.Key;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class ApplicationVersionSignature {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5091a = "AppVersionSignature";
    private static final ConcurrentMap<String, Key> b = new ConcurrentHashMap();

    @NonNull
    public static Key a(@NonNull Context context) {
        String packageName = context.getPackageName();
        Key key = (Key) b.get(packageName);
        if (key != null) {
            return key;
        }
        Key b2 = b(context);
        Key putIfAbsent = b.putIfAbsent(packageName, b2);
        return putIfAbsent == null ? b2 : putIfAbsent;
    }

    @VisibleForTesting
    static void a() {
        b.clear();
    }

    @NonNull
    private static Key b(@NonNull Context context) {
        return new ObjectKey(a(c(context)));
    }

    @NonNull
    private static String a(@Nullable PackageInfo packageInfo) {
        if (packageInfo != null) {
            return String.valueOf(packageInfo.versionCode);
        }
        return UUID.randomUUID().toString();
    }

    @Nullable
    private static PackageInfo c(@NonNull Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(f5091a, "Cannot resolve info for" + context.getPackageName(), e);
            return null;
        }
    }

    private ApplicationVersionSignature() {
    }
}
