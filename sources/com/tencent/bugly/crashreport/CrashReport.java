package com.tencent.bugly.crashreport;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.CrashModule;
import com.tencent.bugly.a;
import com.tencent.bugly.b;
import com.tencent.bugly.crashreport.crash.BuglyBroadcastRecevier;
import com.tencent.bugly.crashreport.crash.c;
import com.tencent.bugly.crashreport.crash.h5.H5JavaScriptInterface;
import com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler;
import com.tencent.bugly.proguard.q;
import com.tencent.bugly.proguard.w;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CrashReport {

    /* renamed from: a  reason: collision with root package name */
    private static Context f8981a;

    public static class CrashHandleCallback extends BuglyStrategy.a {
    }

    public interface WebViewInterface {
        void addJavascriptInterface(H5JavaScriptInterface h5JavaScriptInterface, String str);

        CharSequence getContentDescription();

        String getUrl();

        void loadUrl(String str);

        void setJavaScriptEnabled(boolean z);
    }

    public static void enableBugly(boolean z) {
        b.f8980a = z;
    }

    public static void initCrashReport(Context context) {
        f8981a = context;
        b.a((a) CrashModule.getInstance());
        b.a(context);
    }

    public static void initCrashReport(Context context, UserStrategy userStrategy) {
        f8981a = context;
        b.a((a) CrashModule.getInstance());
        b.a(context, userStrategy);
    }

    public static void initCrashReport(Context context, String str, boolean z) {
        if (context != null) {
            f8981a = context;
            b.a((a) CrashModule.getInstance());
            b.a(context, str, z, (BuglyStrategy) null);
        }
    }

    public static void initCrashReport(Context context, String str, boolean z, UserStrategy userStrategy) {
        if (context != null) {
            f8981a = context;
            b.a((a) CrashModule.getInstance());
            b.a(context, str, z, userStrategy);
        }
    }

    public static String getBuglyVersion(Context context) {
        if (context == null) {
            x.d("Please call with context.", new Object[0]);
            return "unknown";
        }
        com.tencent.bugly.crashreport.common.info.a.a(context);
        return com.tencent.bugly.crashreport.common.info.a.c();
    }

    public static void testJavaCrash() {
        if (!b.f8980a) {
            Log.w(x.f9062a, "Can not test Java crash because bugly is disable.");
        } else if (!CrashModule.hasInitialized()) {
            Log.e(x.f9062a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            com.tencent.bugly.crashreport.common.info.a b = com.tencent.bugly.crashreport.common.info.a.b();
            if (b != null) {
                b.b(24096);
            }
            throw new RuntimeException("This Crash create for Test! You can go to Bugly see more detail!");
        }
    }

    public static void testNativeCrash() {
        if (!b.f8980a) {
            Log.w(x.f9062a, "Can not test native crash because bugly is disable.");
        } else if (!CrashModule.hasInitialized()) {
            Log.e(x.f9062a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            x.a("start to create a native crash for test!", new Object[0]);
            c.a().j();
        }
    }

    public static void testANRCrash() {
        if (!b.f8980a) {
            Log.w(x.f9062a, "Can not test ANR crash because bugly is disable.");
        } else if (!CrashModule.hasInitialized()) {
            Log.e(x.f9062a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            x.a("start to create a anr crash for test!", new Object[0]);
            c.a().k();
        }
    }

    public static void postCatchedException(Throwable th) {
        postCatchedException(th, Thread.currentThread(), false);
    }

    public static void postCatchedException(Throwable th, Thread thread) {
        postCatchedException(th, thread, false);
    }

    public static void postCatchedException(Throwable th, Thread thread, boolean z) {
        if (!b.f8980a) {
            Log.w(x.f9062a, "Can not post crash caught because bugly is disable.");
        } else if (!CrashModule.hasInitialized()) {
            Log.e(x.f9062a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else if (th == null) {
            x.d("throwable is null, just return", new Object[0]);
        } else {
            if (thread == null) {
                thread = Thread.currentThread();
            }
            c.a().a(thread, th, false, (String) null, (byte[]) null, z);
        }
    }

    public static void closeNativeReport() {
        if (!b.f8980a) {
            Log.w(x.f9062a, "Can not close native report because bugly is disable.");
        } else if (!CrashModule.hasInitialized()) {
            Log.e(x.f9062a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            c.a().f();
        }
    }

    public static void startCrashReport() {
        if (!b.f8980a) {
            Log.w(x.f9062a, "Can not start crash report because bugly is disable.");
        } else if (!CrashModule.hasInitialized()) {
            Log.w(x.f9062a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            c.a().c();
        }
    }

    public static void closeCrashReport() {
        if (!b.f8980a) {
            Log.w(x.f9062a, "Can not close crash report because bugly is disable.");
        } else if (!CrashModule.hasInitialized()) {
            Log.w(x.f9062a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            c.a().d();
        }
    }

    public static void closeBugly() {
        if (!b.f8980a) {
            Log.w(x.f9062a, "Can not close bugly because bugly is disable.");
        } else if (!CrashModule.hasInitialized()) {
            Log.w(x.f9062a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else if (f8981a != null) {
            BuglyBroadcastRecevier instance = BuglyBroadcastRecevier.getInstance();
            if (instance != null) {
                instance.unregister(f8981a);
            }
            closeCrashReport();
            com.tencent.bugly.crashreport.biz.b.a(f8981a);
            w a2 = w.a();
            if (a2 != null) {
                a2.b();
            }
        }
    }

    public static void setUserSceneTag(Context context, int i) {
        if (!b.f8980a) {
            Log.w(x.f9062a, "Can not set tag caught because bugly is disable.");
        } else if (context == null) {
            Log.e(x.f9062a, "setTag args context should not be null");
        } else {
            if (i <= 0) {
                x.d("setTag args tagId should > 0", new Object[0]);
            }
            com.tencent.bugly.crashreport.common.info.a.a(context).a(i);
            x.b("[param] set user scene tag: %d", Integer.valueOf(i));
        }
    }

    public static int getUserSceneTagId(Context context) {
        if (!b.f8980a) {
            Log.w(x.f9062a, "Can not get user scene tag because bugly is disable.");
            return -1;
        } else if (context != null) {
            return com.tencent.bugly.crashreport.common.info.a.a(context).F();
        } else {
            Log.e(x.f9062a, "getUserSceneTagId args context should not be null");
            return -1;
        }
    }

    public static String getUserData(Context context, String str) {
        if (!b.f8980a) {
            Log.w(x.f9062a, "Can not get user data because bugly is disable.");
            return "unknown";
        } else if (context == null) {
            Log.e(x.f9062a, "getUserDataValue args context should not be null");
            return "unknown";
        } else if (z.a(str)) {
            return null;
        } else {
            return com.tencent.bugly.crashreport.common.info.a.a(context).g(str);
        }
    }

    public static void putUserData(Context context, String str, String str2) {
        if (!b.f8980a) {
            Log.w(x.f9062a, "Can not put user data because bugly is disable.");
        } else if (context == null) {
            Log.w(x.f9062a, "putUserData args context should not be null");
        } else if (str == null) {
            str;
            x.d("putUserData args key should not be null or empty", new Object[0]);
        } else if (str2 == null) {
            str2;
            x.d("putUserData args value should not be null", new Object[0]);
        } else if (!str.matches("[a-zA-Z[0-9]]+")) {
            x.d("putUserData args key should match [a-zA-Z[0-9]]+  {" + str + "}", new Object[0]);
        } else {
            if (str2.length() > 200) {
                x.d("user data value length over limit %d, it will be cutted!", 200);
                str2 = str2.substring(0, 200);
            }
            com.tencent.bugly.crashreport.common.info.a a2 = com.tencent.bugly.crashreport.common.info.a.a(context);
            if (a2.C().contains(str)) {
                NativeCrashHandler instance = NativeCrashHandler.getInstance();
                if (instance != null) {
                    instance.putKeyValueToNative(str, str2);
                }
                com.tencent.bugly.crashreport.common.info.a.a(context).b(str, str2);
                x.c("replace KV %s %s", str, str2);
            } else if (a2.B() >= 10) {
                x.d("user data size is over limit %d, it will be cutted!", 10);
            } else {
                if (str.length() > 50) {
                    x.d("user data key length over limit %d , will drop this new key %s", 50, str);
                    str = str.substring(0, 50);
                }
                NativeCrashHandler instance2 = NativeCrashHandler.getInstance();
                if (instance2 != null) {
                    instance2.putKeyValueToNative(str, str2);
                }
                com.tencent.bugly.crashreport.common.info.a.a(context).b(str, str2);
                x.b("[param] set user data: %s - %s", str, str2);
            }
        }
    }

    public static String removeUserData(Context context, String str) {
        if (!b.f8980a) {
            Log.w(x.f9062a, "Can not remove user data because bugly is disable.");
            return "unknown";
        } else if (context == null) {
            Log.e(x.f9062a, "removeUserData args context should not be null");
            return "unknown";
        } else if (z.a(str)) {
            return null;
        } else {
            x.b("[param] remove user data: %s", str);
            return com.tencent.bugly.crashreport.common.info.a.a(context).f(str);
        }
    }

    public static Set<String> getAllUserDataKeys(Context context) {
        if (!b.f8980a) {
            Log.w(x.f9062a, "Can not get all keys of user data because bugly is disable.");
            return new HashSet();
        } else if (context != null) {
            return com.tencent.bugly.crashreport.common.info.a.a(context).C();
        } else {
            Log.e(x.f9062a, "getAllUserDataKeys args context should not be null");
            return new HashSet();
        }
    }

    public static int getUserDatasSize(Context context) {
        if (!b.f8980a) {
            Log.w(x.f9062a, "Can not get size of user data because bugly is disable.");
            return -1;
        } else if (context != null) {
            return com.tencent.bugly.crashreport.common.info.a.a(context).B();
        } else {
            Log.e(x.f9062a, "getUserDatasSize args context should not be null");
            return -1;
        }
    }

    public static String getAppID() {
        if (!b.f8980a) {
            Log.w(x.f9062a, "Can not get App ID because bugly is disable.");
            return "unknown";
        } else if (CrashModule.hasInitialized()) {
            return com.tencent.bugly.crashreport.common.info.a.a(f8981a).f();
        } else {
            Log.e(x.f9062a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return "unknown";
        }
    }

    public static void setUserId(String str) {
        if (!b.f8980a) {
            Log.w(x.f9062a, "Can not set user ID because bugly is disable.");
        } else if (!CrashModule.hasInitialized()) {
            Log.e(x.f9062a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            setUserId(f8981a, str);
        }
    }

    public static void setUserId(Context context, String str) {
        if (!b.f8980a) {
            Log.w(x.f9062a, "Can not set user ID because bugly is disable.");
        } else if (context == null) {
            Log.e(x.f9062a, "Context should not be null when bugly has not been initialed!");
        } else if (str == null) {
            x.d("userId should not be null", new Object[0]);
        } else {
            if (str.length() > 100) {
                String substring = str.substring(0, 100);
                x.d("userId %s length is over limit %d substring to %s", str, 100, substring);
                str = substring;
            }
            if (!str.equals(com.tencent.bugly.crashreport.common.info.a.a(context).g())) {
                com.tencent.bugly.crashreport.common.info.a.a(context).b(str);
                x.b("[user] set userId : %s", str);
                NativeCrashHandler instance = NativeCrashHandler.getInstance();
                if (instance != null) {
                    instance.setNativeUserId(str);
                }
                if (CrashModule.hasInitialized()) {
                    com.tencent.bugly.crashreport.biz.b.a();
                }
            }
        }
    }

    public static String getUserId() {
        if (!b.f8980a) {
            Log.w(x.f9062a, "Can not get user ID because bugly is disable.");
            return "unknown";
        } else if (CrashModule.hasInitialized()) {
            return com.tencent.bugly.crashreport.common.info.a.a(f8981a).g();
        } else {
            Log.e(x.f9062a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return "unknown";
        }
    }

    public static String getAppVer() {
        if (!b.f8980a) {
            Log.w(x.f9062a, "Can not get app version because bugly is disable.");
            return "unknown";
        } else if (CrashModule.hasInitialized()) {
            return com.tencent.bugly.crashreport.common.info.a.a(f8981a).j;
        } else {
            Log.e(x.f9062a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return "unknown";
        }
    }

    public static String getAppChannel() {
        if (!b.f8980a) {
            Log.w(x.f9062a, "Can not get App channel because bugly is disable.");
            return "unknown";
        } else if (CrashModule.hasInitialized()) {
            return com.tencent.bugly.crashreport.common.info.a.a(f8981a).l;
        } else {
            Log.e(x.f9062a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return "unknown";
        }
    }

    public static void setContext(Context context) {
        f8981a = context;
    }

    public static boolean isLastSessionCrash() {
        if (!b.f8980a) {
            Log.w(x.f9062a, "The info 'isLastSessionCrash' is not accurate because bugly is disable.");
            return false;
        } else if (CrashModule.hasInitialized()) {
            return c.a().b();
        } else {
            Log.e(x.f9062a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return false;
        }
    }

    public static void setSdkExtraData(Context context, String str, String str2) {
        if (!b.f8980a) {
            Log.w(x.f9062a, "Can not put SDK extra data because bugly is disable.");
        } else if (context != null && !z.a(str) && !z.a(str2)) {
            com.tencent.bugly.crashreport.common.info.a.a(context).a(str, str2);
        }
    }

    public static Map<String, String> getSdkExtraData() {
        if (!b.f8980a) {
            Log.w(x.f9062a, "Can not get SDK extra data because bugly is disable.");
            return new HashMap();
        } else if (CrashModule.hasInitialized()) {
            return com.tencent.bugly.crashreport.common.info.a.a(f8981a).A;
        } else {
            Log.e(x.f9062a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return null;
        }
    }

    public static Map<String, String> getSdkExtraData(Context context) {
        if (!b.f8980a) {
            Log.w(x.f9062a, "Can not get SDK extra data because bugly is disable.");
            return new HashMap();
        } else if (context != null) {
            return com.tencent.bugly.crashreport.common.info.a.a(context).A;
        } else {
            x.d("Context should not be null.", new Object[0]);
            return null;
        }
    }

    private static void putSdkData(Context context, String str, String str2) {
        if (context != null && !z.a(str) && !z.a(str2)) {
            String replace = str.replace("[a-zA-Z[0-9]]+", "");
            if (replace.length() > 100) {
                Log.w(x.f9062a, String.format("putSdkData key length over limit %d, will be cutted.", new Object[]{50}));
                replace = replace.substring(0, 50);
            }
            if (str2.length() > 500) {
                Log.w(x.f9062a, String.format("putSdkData value length over limit %d, will be cutted!", new Object[]{200}));
                str2 = str2.substring(0, 200);
            }
            com.tencent.bugly.crashreport.common.info.a.a(context).c(replace, str2);
            x.b(String.format("[param] putSdkData data: %s - %s", new Object[]{replace, str2}), new Object[0]);
        }
    }

    public static void setIsAppForeground(Context context, boolean z) {
        if (!b.f8980a) {
            Log.w(x.f9062a, "Can not set 'isAppForeground' because bugly is disable.");
        } else if (context == null) {
            x.d("Context should not be null.", new Object[0]);
        } else {
            if (z) {
                x.c("App is in foreground.", new Object[0]);
            } else {
                x.c("App is in background.", new Object[0]);
            }
            com.tencent.bugly.crashreport.common.info.a.a(context).a(z);
        }
    }

    public static void setIsDevelopmentDevice(Context context, boolean z) {
        if (!b.f8980a) {
            Log.w(x.f9062a, "Can not set 'isDevelopmentDevice' because bugly is disable.");
        } else if (context == null) {
            x.d("Context should not be null.", new Object[0]);
        } else {
            if (z) {
                x.c("This is a development device.", new Object[0]);
            } else {
                x.c("This is not a development device.", new Object[0]);
            }
            com.tencent.bugly.crashreport.common.info.a.a(context).y = z;
        }
    }

    public static void setSessionIntervalMills(long j) {
        if (!b.f8980a) {
            Log.w(x.f9062a, "Can not set 'SessionIntervalMills' because bugly is disable.");
        } else {
            com.tencent.bugly.crashreport.biz.b.a(j);
        }
    }

    public static void setAppVersion(Context context, String str) {
        if (!b.f8980a) {
            Log.w(x.f9062a, "Can not set App version because bugly is disable.");
        } else if (context == null) {
            Log.w(x.f9062a, "setAppVersion args context should not be null");
        } else if (str == null) {
            Log.w(x.f9062a, "App version is null, will not set");
        } else {
            com.tencent.bugly.crashreport.common.info.a.a(context).j = str;
            NativeCrashHandler instance = NativeCrashHandler.getInstance();
            if (instance != null) {
                instance.setNativeAppVersion(str);
            }
        }
    }

    public static void setAppChannel(Context context, String str) {
        if (!b.f8980a) {
            Log.w(x.f9062a, "Can not set App channel because Bugly is disable.");
        } else if (context == null) {
            Log.w(x.f9062a, "setAppChannel args context should not be null");
        } else if (str == null) {
            Log.w(x.f9062a, "App channel is null, will not set");
        } else {
            com.tencent.bugly.crashreport.common.info.a.a(context).l = str;
            NativeCrashHandler instance = NativeCrashHandler.getInstance();
            if (instance != null) {
                instance.setNativeAppChannel(str);
            }
        }
    }

    public static void setAppPackage(Context context, String str) {
        if (!b.f8980a) {
            Log.w(x.f9062a, "Can not set App package because bugly is disable.");
        } else if (context == null) {
            Log.w(x.f9062a, "setAppPackage args context should not be null");
        } else if (str == null) {
            Log.w(x.f9062a, "App package is null, will not set");
        } else {
            com.tencent.bugly.crashreport.common.info.a.a(context).c = str;
            NativeCrashHandler instance = NativeCrashHandler.getInstance();
            if (instance != null) {
                instance.setNativeAppPackage(str);
            }
        }
    }

    public static void setCrashFilter(String str) {
        if (!b.f8980a) {
            Log.w(x.f9062a, "Can not set App package because bugly is disable.");
            return;
        }
        String str2 = x.f9062a;
        Log.w(str2, "Set crash stack filter: " + str);
        c.m = str;
    }

    public static void setCrashRegularFilter(String str) {
        if (!b.f8980a) {
            Log.w(x.f9062a, "Can not set App package because bugly is disable.");
            return;
        }
        String str2 = x.f9062a;
        Log.w(str2, "Set crash stack filter: " + str);
        c.n = str;
    }

    public static void setBuglyDbName(String str) {
        if (!b.f8980a) {
            Log.w(x.f9062a, "Can not set DB name because bugly is disable.");
            return;
        }
        String str2 = x.f9062a;
        Log.i(str2, "Set Bugly DB name: " + str);
        q.f9053a = str;
    }

    public static void setAuditEnable(Context context, boolean z) {
        if (!b.f8980a) {
            Log.w(x.f9062a, "Can not set App package because bugly is disable.");
        } else if (context == null) {
            Log.w(x.f9062a, "setAppPackage args context should not be null");
        } else {
            String str = x.f9062a;
            Log.i(str, "Set audit enable: " + z);
            com.tencent.bugly.crashreport.common.info.a.a(context).B = z;
        }
    }

    public static boolean setJavascriptMonitor(WebView webView, boolean z) {
        return setJavascriptMonitor(webView, z, false);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public static boolean setJavascriptMonitor(final WebView webView, boolean z, boolean z2) {
        if (webView != null) {
            return setJavascriptMonitor((WebViewInterface) new WebViewInterface() {
                public final String getUrl() {
                    return webView.getUrl();
                }

                public final void setJavaScriptEnabled(boolean z) {
                    WebSettings settings = webView.getSettings();
                    if (!settings.getJavaScriptEnabled()) {
                        settings.setJavaScriptEnabled(true);
                    }
                }

                public final void loadUrl(String str) {
                    webView.loadUrl(str);
                }

                public final void addJavascriptInterface(H5JavaScriptInterface h5JavaScriptInterface, String str) {
                    webView.addJavascriptInterface(h5JavaScriptInterface, str);
                }

                public final CharSequence getContentDescription() {
                    return webView.getContentDescription();
                }
            }, z, z2);
        }
        Log.w(x.f9062a, "WebView is null.");
        return false;
    }

    public static boolean setJavascriptMonitor(WebViewInterface webViewInterface, boolean z) {
        return setJavascriptMonitor(webViewInterface, z, false);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public static boolean setJavascriptMonitor(WebViewInterface webViewInterface, boolean z, boolean z2) {
        if (webViewInterface == null) {
            Log.w(x.f9062a, "WebViewInterface is null.");
            return false;
        } else if (!CrashModule.hasInitialized()) {
            x.e("CrashReport has not been initialed! please to call method 'initCrashReport' first!", new Object[0]);
            return false;
        } else {
            x.a("Set Javascript exception monitor of webview.", new Object[0]);
            if (!b.f8980a) {
                Log.w(x.f9062a, "Can not set JavaScript monitor because bugly is disable.");
                return false;
            }
            x.c("URL of webview is %s", webViewInterface.getUrl());
            if (z2 || Build.VERSION.SDK_INT >= 19) {
                x.a("Enable the javascript needed by webview monitor.", new Object[0]);
                webViewInterface.setJavaScriptEnabled(true);
                H5JavaScriptInterface instance = H5JavaScriptInterface.getInstance(webViewInterface);
                if (instance != null) {
                    x.a("Add a secure javascript interface to the webview.", new Object[0]);
                    webViewInterface.addJavascriptInterface(instance, "exceptionUploader");
                }
                if (z) {
                    x.a("Inject bugly.js(v%s) to the webview.", com.tencent.bugly.crashreport.crash.h5.b.b());
                    String a2 = com.tencent.bugly.crashreport.crash.h5.b.a();
                    if (a2 == null) {
                        x.e("Failed to inject Bugly.js.", com.tencent.bugly.crashreport.crash.h5.b.b());
                        return false;
                    }
                    webViewInterface.loadUrl("javascript:" + a2);
                }
                return true;
            }
            x.e("This interface is only available for Android 4.4 or later.", new Object[0]);
            return false;
        }
    }

    public static class UserStrategy extends BuglyStrategy {

        /* renamed from: a  reason: collision with root package name */
        private CrashHandleCallback f8983a;

        public UserStrategy(Context context) {
        }

        public synchronized CrashHandleCallback getCrashHandleCallback() {
            return this.f8983a;
        }

        public synchronized void setCrashHandleCallback(CrashHandleCallback crashHandleCallback) {
            this.f8983a = crashHandleCallback;
        }
    }
}
