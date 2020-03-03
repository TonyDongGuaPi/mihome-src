package com.xiaomi.smarthome.operation.js_sdk.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.framework.log.LogUtil;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.internal.functions.Functions;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;

public class JsSdkUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f21130a = "JsSdkUtils";

    public static void c(WebView webView, String str) {
    }

    public static void d(WebView webView, String str) {
    }

    private JsSdkUtils() {
    }

    public static Observable<String> a(final WebView webView, String str) {
        if (webView == null) {
            return Observable.error((Throwable) new IllegalArgumentException("argumrnt webview is null"));
        }
        if (Build.VERSION.SDK_INT < 19) {
            return Observable.empty();
        }
        final String str2 = "javascript:" + str;
        return Observable.create(new ObservableOnSubscribe<String>() {
            public void subscribe(final ObservableEmitter<String> observableEmitter) throws Exception {
                webView.evaluateJavascript(str2, new ValueCallback<String>() {
                    /* renamed from: a */
                    public void onReceiveValue(String str) {
                        LogUtil.a(JsSdkUtils.f21130a, "callJsFuncByRx: " + str2 + " ;return value: " + str);
                        if (str.charAt(0) == '\"' && str.charAt(str.length() - 1) == '\"') {
                            str = str.substring(1, str.length() - 1);
                        }
                        observableEmitter.onNext(str);
                        observableEmitter.onComplete();
                    }
                });
            }
        }).subscribeOn(AndroidSchedulers.mainThread());
    }

    @SuppressLint({"CheckResult"})
    public static void b(WebView webView, String str) {
        a(webView, str).subscribe(Functions.emptyConsumer(), Functions.emptyConsumer());
    }

    public static Observable<String> a(WebView webView, String str, String... strArr) {
        StringBuilder sb = new StringBuilder(str);
        sb.append(Operators.BRACKET_START_STR);
        for (int i = 0; i < strArr.length; i++) {
            String str2 = strArr[i];
            sb.append("'");
            sb.append(str2);
            sb.append("'");
            if (i != strArr.length - 1) {
                sb.append(",");
            }
        }
        sb.append(Operators.BRACKET_END_STR);
        return a(webView, sb.toString());
    }

    @SuppressLint({"CheckResult"})
    public static void b(WebView webView, String str, String... strArr) {
        a(webView, str, strArr).subscribe(Functions.emptyConsumer(), Functions.emptyConsumer());
    }

    public static String a(Context context, int i) {
        try {
            Bitmap decodeResource = BitmapFactory.decodeResource(context.getResources(), i);
            String str = context.getFilesDir().getAbsolutePath() + File.separator + "default_share_icon.jpeg";
            decodeResource.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(str));
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static Bitmap a(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.b(f21130a, "base64ImageDecoder:  empty base64: ");
            return null;
        }
        try {
            byte[] decode = Base64.decode(str, 0);
            return BitmapFactory.decodeByteArray(decode, 0, decode.length);
        } catch (Exception e) {
            LogUtil.b(f21130a, "base64ImageDecoder: failed with base64: " + str + " ;message: " + e.getLocalizedMessage());
            return null;
        }
    }

    public static <T> T a(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    public static void a(Runnable runnable) {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            runnable.run();
        } else {
            SHApplication.getGlobalHandler().post(runnable);
        }
    }

    public static void a(Runnable runnable, long j) {
        SHApplication.getGlobalHandler().postDelayed(runnable, j);
    }

    public static String b(String str) {
        String[] split = str.split("\\.");
        int length = split.length;
        if (split.length < 2) {
            return str;
        }
        return split[length - 2] + "." + split[length - 1];
    }

    public static boolean c(String str) {
        LogUtil.a(f21130a, "isValidUrl: url: " + str);
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            Uri parse = Uri.parse(str);
            String scheme = parse.getScheme();
            if (TextUtils.equals("http", scheme) || TextUtils.equals("https", scheme)) {
                String host = parse.getHost();
                if (TextUtils.isEmpty(host)) {
                    LogUtil.a(f21130a, "isValidUrl: invalid url host: empty host");
                    return false;
                }
                List asList = Arrays.asList(new String[]{"mi.com", "xiaomiyoupin.com", "miui.com", "xiaomi.com"});
                String b = b(host);
                if (asList.contains(b)) {
                    return true;
                }
                LogUtil.a(f21130a, "isValidUrl : invalid url domain:" + b + ",only accept domain in the list: " + Arrays.deepToString(asList.toArray()));
                return false;
            }
            LogUtil.a(f21130a, "isValidUrl: invalid url scheme: " + str);
            return false;
        } catch (Exception e) {
            LogUtil.a(f21130a, "isValidUrl: " + Log.getStackTraceString(e));
            return false;
        }
    }
}
