package com.xiaomi.smarthome.framework.plugin.rn.jsc;

import android.content.Context;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JSCManager {

    /* renamed from: a  reason: collision with root package name */
    private static final JSCManager f17283a = new JSCManager();
    /* access modifiers changed from: private */
    public List<ExecutorService> b = new CopyOnWriteArrayList();
    /* access modifiers changed from: private */
    public List<JSCRunner> c = new CopyOnWriteArrayList();

    public static final JSCManager a() {
        return f17283a;
    }

    public static void a(ReactApplicationContext reactApplicationContext) {
        SoLoader.init((Context) reactApplicationContext, false);
        ReactBridge.staticInit();
    }

    private class JSCRunner {

        /* renamed from: a  reason: collision with root package name */
        Reference<ReactApplicationContext> f17291a;
        JSC b;
        ExecutorService c;

        private JSCRunner() {
        }

        /* access modifiers changed from: package-private */
        public ReactApplicationContext a() {
            if (this.f17291a == null) {
                return null;
            }
            return this.f17291a.get();
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
            this.f17291a = null;
            if (this.c != null) {
                JSCManager.this.b.add(this.c);
                this.c = null;
            }
        }
    }

    private JSCManager() {
    }

    private ExecutorService c() {
        if (!this.b.isEmpty()) {
            return this.b.remove(0);
        }
        if (this.c.size() < 3) {
            return Executors.newSingleThreadExecutor();
        }
        ArrayList<JSCRunner> arrayList = new ArrayList<>();
        int size = this.c.size() - 2;
        for (int i = 0; i < size; i++) {
            final JSCRunner jSCRunner = this.c.get(i);
            ExecutorService executorService = jSCRunner.c;
            if (executorService != null) {
                jSCRunner.c = null;
                this.b.add(executorService);
                executorService.execute(new Runnable() {
                    public void run() {
                        jSCRunner.b();
                    }
                });
            }
            arrayList.add(jSCRunner);
        }
        for (JSCRunner remove : arrayList) {
            this.c.remove(remove);
        }
        return this.b.remove(0);
    }

    public void a(ReactApplicationContext reactApplicationContext, String str, String str2, Callback callback) {
        ExecutorService c2 = c();
        final ReactApplicationContext reactApplicationContext2 = reactApplicationContext;
        final String str3 = str;
        final Callback callback2 = callback;
        final ExecutorService executorService = c2;
        final String str4 = str2;
        c2.execute(new Runnable() {
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
                    jSCRunner.c = executorService;
                    jSCRunner.f17291a = new WeakReference(reactApplicationContext2);
                    try {
                        jSCRunner.b = new JSCV60(reactApplicationContext2);
                        jSCRunner.b.a(reactApplicationContext2, str, str4);
                        if (jSCRunner.b.b()) {
                            JSCManager.this.c.add(jSCRunner);
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
                    JSCManager.this.b.add(executorService);
                }
            }
        });
    }

    private JSCRunner b(String str) {
        for (final JSCRunner next : this.c) {
            if (next.b != null && next.b.a().equals(str)) {
                if (next.a() != null) {
                    return next;
                }
                next.c.execute(new Runnable() {
                    public void run() {
                        next.b();
                    }
                });
                this.c.remove(next);
                return null;
            }
        }
        return null;
    }

    public void a(String str, String str2, String str3, Callback callback) {
        final JSCRunner b2 = b(str);
        if (b2 == null || b2.b == null) {
            callback.invoke(false, "jsContext is destroyed", "destroyed");
            return;
        }
        final String str4 = str2;
        final String str5 = str3;
        final Callback callback2 = callback;
        new Thread(new Runnable() {
            public void run() {
                RnPluginLog.a("Thread:  " + Thread.currentThread().getName());
                b2.b.a(str4, str5, (JSCallback) new JSCallback() {
                    public void a(String str) {
                        RnPluginLog.a("jsc execute onSuccess:  " + Thread.currentThread().getName());
                        callback2.invoke(true, str);
                    }

                    public void b(String str) {
                        RnPluginLog.a("jsc execute onFailure:  " + Thread.currentThread().getName());
                        callback2.invoke(false, str);
                    }
                });
            }
        }).start();
    }

    public void a(String str) {
        final JSCRunner b2 = b(str);
        if (b2 != null) {
            b2.c.execute(new Runnable() {
                public void run() {
                    RnPluginLog.a("jsc remove:  " + Thread.currentThread().getName());
                    if (b2 != null) {
                        b2.b();
                        JSCManager.this.c.remove(b2);
                    }
                }
            });
        }
    }

    public void b() {
        ArrayList<JSCRunner> arrayList = new ArrayList<>();
        arrayList.addAll(this.c);
        this.c.clear();
        for (final JSCRunner jSCRunner : arrayList) {
            if (jSCRunner.c != null) {
                jSCRunner.c.execute(new Runnable() {
                    public void run() {
                        jSCRunner.b();
                    }
                });
            }
        }
    }
}
