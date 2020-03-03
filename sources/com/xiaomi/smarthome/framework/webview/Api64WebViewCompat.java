package com.xiaomi.smarthome.framework.webview;

import android.content.Context;
import android.os.Build;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import java.io.File;

public final class Api64WebViewCompat {

    /* renamed from: a  reason: collision with root package name */
    private static final String f17839a = "WebViewChromiumPrefs";
    private static final String b = "webview";
    private static final String c = "GPUCache";

    public static void a() {
        if (Build.VERSION.SDK_INT >= 24) {
            try {
                Context appContext = SHApplication.getAppContext();
                appContext.getSharedPreferences(f17839a, 0).edit().clear().apply();
                a(new File(appContext.getDir("webview", 0) + File.separator + c));
            } catch (Exception e) {
                a(e.getMessage());
            }
        }
    }

    private static void a(File file) {
        if (file.isDirectory()) {
            for (File a2 : file.listFiles()) {
                a(a2);
            }
        }
        a("delete isSuccessDelete: " + file.delete() + " fileName: " + file);
    }

    private static void a(String str) {
        LogUtilGrey.a("Api64WebViewCompat", str);
    }
}
