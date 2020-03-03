package com.miui.tsmclient.util;

import android.content.Context;
import android.text.TextUtils;
import com.miui.tsmclient.database.ProviderAuthorities;
import java.io.File;
import java.lang.ref.WeakReference;

public class EnvironmentConfig {
    private static WeakReference<Context> mContextRef;
    private static String sClientId;
    private static boolean sIsLoginAuth = true;
    private static boolean sIsStaging = new File("/data/system/xiaomi_account_preview").exists();

    public static void initialize(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            sIsLoginAuth = false;
            sClientId = str;
            initialize(context);
            return;
        }
        throw new IllegalArgumentException("client id must be set!");
    }

    public static void initialize(Context context) {
        if (context != null) {
            mContextRef = new WeakReference<>(context);
            ProviderAuthorities.init(context);
            return;
        }
        throw new IllegalArgumentException("context can't be null!");
    }

    public static boolean isLoginAuth() {
        return sIsLoginAuth;
    }

    public static void setStaging(boolean z) {
        sIsStaging = z;
    }

    public static boolean isStaging() {
        return sIsStaging;
    }

    public static Context getContext() {
        if (mContextRef == null) {
            return null;
        }
        return (Context) mContextRef.get();
    }

    public static String getClientId() {
        return sClientId;
    }

    private EnvironmentConfig() {
    }
}
