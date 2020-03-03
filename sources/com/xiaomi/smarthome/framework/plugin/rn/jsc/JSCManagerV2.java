package com.xiaomi.smarthome.framework.plugin.rn.jsc;

import android.content.Context;
import android.text.TextUtils;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactBridge;
import com.facebook.soloader.SoLoader;
import com.mi.global.bbs.utils.ConnectionHelper;
import com.xiaomi.smarthome.framework.plugin.FileUtils;
import com.xiaomi.smarthome.framework.plugin.rn.utils.RnPluginLog;
import com.xiaomi.smarthome.library.common.network.NetworkUtils;
import java.io.File;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class JSCManagerV2 {

    /* renamed from: a  reason: collision with root package name */
    private static final JSCManagerV2 f17292a = new JSCManagerV2();
    /* access modifiers changed from: private */
    public List<JSCRunner> b = new CopyOnWriteArrayList();

    public static final JSCManagerV2 a() {
        return f17292a;
    }

    public static void a(ReactApplicationContext reactApplicationContext) {
        SoLoader.init((Context) reactApplicationContext, false);
        ReactBridge.staticInit();
    }

    private class JSCRunner {

        /* renamed from: a  reason: collision with root package name */
        Reference<ReactApplicationContext> f17296a;
        JSC b;

        private JSCRunner() {
        }

        /* access modifiers changed from: package-private */
        public ReactApplicationContext a() {
            if (this.f17296a == null) {
                return null;
            }
            return this.f17296a.get();
        }

        /* access modifiers changed from: package-private */
        public void b() {
            JSC jsc = this.b;
            this.b = null;
            if (jsc != null) {
                try {
                    jsc.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            this.f17296a = null;
        }
    }

    private JSCManagerV2() {
    }

    public String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        if (str.startsWith("file:///")) {
            return str.substring("file:///".length() - 1, str.length());
        }
        if (str.startsWith("file://")) {
            return str.substring("file://".length() - 1, str.length());
        }
        if (str.startsWith("file:/")) {
            return str.substring("file:/".length() - 1, str.length());
        }
        if (!str.startsWith("file:")) {
            return str;
        }
        return File.separator + str.substring("file:".length(), str.length());
    }

    public void a(ReactApplicationContext reactApplicationContext, String str, String str2, Callback callback) {
        if (!TextUtils.isEmpty(str)) {
            str = a(str);
        }
        final String str3 = str;
        final ReactApplicationContext reactApplicationContext2 = reactApplicationContext;
        final Callback callback2 = callback;
        final String str4 = str2;
        new Thread(new Runnable() {
            /* access modifiers changed from: package-private */
            public boolean a(String str) {
                return NetworkUtils.a(reactApplicationContext2, str3, new File(str), (NetworkUtils.OnDownloadProgress) null, false, false).b == 3;
            }

            public void run() {
                String str;
                boolean z = str3.startsWith(ConnectionHelper.HTTP_PREFIX) || str3.startsWith("https://");
                if (z) {
                    str = new File(reactApplicationContext2.getExternalCacheDir(), "temp.jx").getAbsolutePath();
                    FileUtils.d(str);
                } else {
                    str = str3;
                }
                if (!z || a(str)) {
                    JSCRunner jSCRunner = new JSCRunner();
                    jSCRunner.f17296a = new WeakReference(reactApplicationContext2);
                    try {
                        jSCRunner.b = new JSCV60(reactApplicationContext2);
                        jSCRunner.b.a(reactApplicationContext2, str, str4);
                        if (jSCRunner.b.b()) {
                            JSCManagerV2.this.b.add(jSCRunner);
                            callback2.invoke(true, jSCRunner.b.a());
                            return;
                        }
                        jSCRunner.b();
                        callback2.invoke(false, "failed to load jx");
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        jSCRunner.b();
                        Callback callback = callback2;
                        callback.invoke(false, "failed to load jx:" + e2.getMessage());
                    }
                } else {
                    callback2.invoke(false, "failed to download jx");
                }
            }
        }).start();
    }

    private JSCRunner c(String str) {
        for (final JSCRunner next : this.b) {
            if (next.b != null && next.b.a().equals(str)) {
                if (next.a() != null) {
                    return next;
                }
                new Thread(new Runnable() {
                    public void run() {
                        next.b();
                        JSCManagerV2.this.b.remove(next);
                    }
                }).start();
                return null;
            }
        }
        return null;
    }

    public void a(String str, String str2, String str3, final Callback callback) {
        JSCRunner c = c(str);
        if (c == null || c.b == null) {
            callback.invoke(false, "jsContext is destroyed", "destroyed");
            return;
        }
        RnPluginLog.a("execute threadName:  " + Thread.currentThread().getName());
        c.b.a(str2, str3, (JSCallback) new JSCallback() {
            public void a(String str) {
                RnPluginLog.a("jsc execute onSuccess threadName:  " + Thread.currentThread().getName());
                callback.invoke(true, str);
            }

            public void b(String str) {
                RnPluginLog.b("jsc execute onFailure threadName:  " + Thread.currentThread().getName());
                callback.invoke(false, str);
            }
        });
    }

    public void b(String str) {
        JSCRunner c = c(str);
        if (c != null) {
            RnPluginLog.a("jsc remove:  " + Thread.currentThread().getName());
            if (c != null) {
                c.b();
                this.b.remove(c);
            }
        }
    }

    public void b() {
        ArrayList<JSCRunner> arrayList = new ArrayList<>();
        arrayList.addAll(this.b);
        this.b.clear();
        for (JSCRunner b2 : arrayList) {
            b2.b();
        }
    }
}
