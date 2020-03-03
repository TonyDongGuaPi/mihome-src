package com.miuipub.internal.hybrid.provider;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import com.miuipub.internal.hybrid.webkit.WebkitFactoryProvider;

public class WebViewFactory {

    /* renamed from: a  reason: collision with root package name */
    private static WebViewFactoryProvider f8273a = null;
    private static final Object b = new Object();
    private static final String c = "com.miui.sdk.hybrid.webview";

    public static WebViewFactoryProvider a(Context context) {
        synchronized (b) {
            if (f8273a != null) {
                WebViewFactoryProvider webViewFactoryProvider = f8273a;
                return webViewFactoryProvider;
            }
            String str = null;
            try {
                Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
                if (bundle != null) {
                    str = bundle.getString(c);
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            if (str != null) {
                try {
                    f8273a = (WebViewFactoryProvider) Class.forName(str, false, context.getClassLoader()).newInstance();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (f8273a == null) {
                f8273a = new WebkitFactoryProvider();
            }
            if (Log.isLoggable("hybrid", 3)) {
                Log.d("hybrid", "loaded provider:" + f8273a);
            }
            WebViewFactoryProvider webViewFactoryProvider2 = f8273a;
            return webViewFactoryProvider2;
        }
    }
}
