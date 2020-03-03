package com.xiaomi.jr.verification.sdk;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.xiaomi.jr.hybrid.HybridCallbackManager;
import com.xiaomi.jr.hybrid.NativeInterface;
import com.xiaomi.miot.support.monitor.core.tasks.TaskConfig;

public class WBH5FaceVerifySDK {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11063a = "NETWORK_NONE";
    private static final String b = "NETWORK_WIFI";
    private static final String c = "NETWORK_2G";
    private static final String d = "NETWORK_3G";
    private static final String e = "NETWORK_4G";
    private static final String f = "NETWORK_MOBILE";
    private static final int g = 17;
    private static WBH5FaceVerifySDK j;
    private ValueCallback<Uri> h;
    private ValueCallback<Uri[]> i;

    private static String a(Context context) {
        NetworkInfo activeNetworkInfo;
        NetworkInfo.State state;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isAvailable()) {
            return f11063a;
        }
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
        if (networkInfo != null && (state = networkInfo.getState()) != null && (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING)) {
            return b;
        }
        NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(0);
        if (networkInfo2 == null) {
            return f11063a;
        }
        NetworkInfo.State state2 = networkInfo2.getState();
        String subtypeName = networkInfo2.getSubtypeName();
        if (state2 == null) {
            return f11063a;
        }
        if (state2 != NetworkInfo.State.CONNECTED && state2 != NetworkInfo.State.CONNECTING) {
            return f11063a;
        }
        switch (activeNetworkInfo.getSubtype()) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return c;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
                return d;
            case 13:
                return e;
            default:
                return (subtypeName.equalsIgnoreCase("TD-SCDMA") || subtypeName.equalsIgnoreCase("WCDMA") || subtypeName.equalsIgnoreCase("CDMA2000")) ? d : f;
        }
    }

    public static synchronized WBH5FaceVerifySDK a() {
        WBH5FaceVerifySDK wBH5FaceVerifySDK;
        synchronized (WBH5FaceVerifySDK.class) {
            if (j == null) {
                j = new WBH5FaceVerifySDK();
            }
            wBH5FaceVerifySDK = j;
        }
        return wBH5FaceVerifySDK;
    }

    private WBH5FaceVerifySDK() {
    }

    public void a(WebView webView, Context context) {
        if (webView != null) {
            WebSettings settings = webView.getSettings();
            settings.setJavaScriptEnabled(true);
            settings.setTextZoom(100);
            settings.setAllowFileAccess(true);
            settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
            settings.setSupportZoom(true);
            settings.setBuiltInZoomControls(true);
            settings.setUseWideViewPort(true);
            settings.setSupportMultipleWindows(false);
            settings.setLoadWithOverviewMode(true);
            settings.setAppCacheEnabled(true);
            settings.setDatabaseEnabled(true);
            settings.setDomStorageEnabled(true);
            settings.setGeolocationEnabled(true);
            settings.setAppCacheMaxSize(Long.MAX_VALUE);
            settings.setAppCachePath(context.getDir("appcache", 0).getPath());
            settings.setDatabasePath(context.getDir(TaskConfig.v, 0).getPath());
            settings.setGeolocationDatabasePath(context.getDir("geolocation", 0).getPath());
            settings.setPluginState(WebSettings.PluginState.ON_DEMAND);
            settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
            if (Build.VERSION.SDK_INT >= 16) {
                settings.setAllowUniversalAccessFromFileURLs(true);
            }
            if (Build.VERSION.SDK_INT >= 11) {
                webView.removeJavascriptInterface("searchBoxJavaBridge_");
            }
            String userAgentString = settings.getUserAgentString();
            try {
                settings.setUserAgentString(userAgentString + ";webank/h5face;webank/1.0;netType:" + a(context) + ";appVersion:" + context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode + ";packageName:" + context.getPackageName());
            } catch (PackageManager.NameNotFoundException e2) {
                settings.setUserAgentString(userAgentString + ";webank/h5face;webank/1.0");
                e2.printStackTrace();
            }
        }
    }

    public void a(int i2, Intent intent) {
        if (this.h != null || this.i != null) {
            Uri data = (intent == null || i2 != -1) ? null : intent.getData();
            Uri[] uriArr = data == null ? null : new Uri[]{data};
            if (this.i != null) {
                this.i.onReceiveValue(uriArr);
                b((ValueCallback<Uri[]>) null);
                return;
            }
            this.h.onReceiveValue(data);
            a((ValueCallback<Uri>) null);
        }
    }

    public boolean a(ValueCallback<Uri> valueCallback, String str, Fragment fragment) {
        a(valueCallback);
        a(fragment);
        return true;
    }

    @TargetApi(21)
    public boolean a(WebView webView, ValueCallback<Uri[]> valueCallback, Fragment fragment, WebChromeClient.FileChooserParams fileChooserParams) {
        Log.d("faceVerify", "accept is " + fileChooserParams.getAcceptTypes()[0] + "---url---" + webView.getUrl());
        b(valueCallback);
        a(fragment);
        return true;
    }

    private void a(Fragment fragment) {
        try {
            Intent intent = new Intent("android.media.action.VIDEO_CAPTURE");
            intent.putExtra("android.intent.extra.videoQuality", 1);
            intent.addFlags(1);
            intent.putExtra("android.intent.extras.CAMERA_FACING", 1);
            AnonymousClass1 r1 = new NativeInterface.Callback() {
                public void a(Object... objArr) {
                    super.a(objArr);
                    WBH5FaceVerifySDK.this.a(objArr[0].intValue(), objArr[1]);
                }
            };
            HybridCallbackManager.a((NativeInterface.Callback) r1);
            fragment.startActivityForResult(intent, r1.a());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void a(ValueCallback<Uri> valueCallback) {
        this.h = valueCallback;
    }

    private void b(ValueCallback<Uri[]> valueCallback) {
        this.i = valueCallback;
    }
}
