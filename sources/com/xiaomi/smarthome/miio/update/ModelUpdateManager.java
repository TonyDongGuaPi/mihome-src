package com.xiaomi.smarthome.miio.update;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.core.entity.plugin.PluginUpdateInfo;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.update.api.UpdateApi;
import com.xiaomi.smarthome.library.common.util.PreferenceUtils;
import com.xiaomi.smarthome.miio.Miio;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class ModelUpdateManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f19975a = "com.xiaomi.smarthome.miio.update.ModelUpdateManager";
    public static final String b = "model_ignore_version_code_prefix_";
    public static final String c = "plugin_ignore_version_code_prefix_";
    public static final int d = 1;
    public static final int e = 2;
    public static final int f = 3;
    public static final int g = 4;
    public static final int h = 5;
    public static final int i = 0;
    public static final int j = 1;
    static ModelUpdateManager k = null;
    private static final int n = 1;
    private static final int o = 10;
    private static final int t = 200;
    List<ModelUpdateInfo> l;
    /* access modifiers changed from: private */
    public int m;
    /* access modifiers changed from: private */
    public ExecutorService p;
    private final LinkedList<Runnable> q;
    private int r;
    private Thread s;

    public interface CheckModelUpdateCallBack {
        void a(int i);

        void a(List<ModelUpdateInfo> list);
    }

    public interface IgnoreStateCallback {
        void a(boolean z);
    }

    public void a(final IgnoreStateCallback ignoreStateCallback) {
        new Thread(new Runnable() {
            public void run() {
                boolean a2 = ModelUpdateManager.this.a();
                if (ignoreStateCallback != null) {
                    ignoreStateCallback.a(a2);
                }
            }
        }).start();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0047, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0049, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean a() {
        /*
            r5 = this;
            monitor-enter(r5)
            java.util.List<com.xiaomi.smarthome.miio.update.ModelUpdateInfo> r0 = r5.l     // Catch:{ all -> 0x004a }
            r1 = 0
            if (r0 == 0) goto L_0x0048
            java.util.List<com.xiaomi.smarthome.miio.update.ModelUpdateInfo> r0 = r5.l     // Catch:{ all -> 0x004a }
            int r0 = r0.size()     // Catch:{ all -> 0x004a }
            if (r0 > 0) goto L_0x000f
            goto L_0x0048
        L_0x000f:
            r0 = 0
        L_0x0010:
            java.util.List<com.xiaomi.smarthome.miio.update.ModelUpdateInfo> r2 = r5.l     // Catch:{ all -> 0x004a }
            int r2 = r2.size()     // Catch:{ all -> 0x004a }
            if (r0 >= r2) goto L_0x0045
            java.util.List<com.xiaomi.smarthome.miio.update.ModelUpdateInfo> r2 = r5.l     // Catch:{ all -> 0x004a }
            java.lang.Object r2 = r2.get(r0)     // Catch:{ all -> 0x004a }
            com.xiaomi.smarthome.miio.update.ModelUpdateInfo r2 = (com.xiaomi.smarthome.miio.update.ModelUpdateInfo) r2     // Catch:{ all -> 0x004a }
            boolean r3 = r2.h     // Catch:{ all -> 0x004a }
            if (r3 == 0) goto L_0x0025
            goto L_0x0042
        L_0x0025:
            com.xiaomi.smarthome.device.SmartHomeDeviceManager r3 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()     // Catch:{ Exception -> 0x0042 }
            java.lang.String r4 = r2.b     // Catch:{ Exception -> 0x0042 }
            com.xiaomi.smarthome.device.Device r3 = r3.b((java.lang.String) r4)     // Catch:{ Exception -> 0x0042 }
            if (r3 != 0) goto L_0x0032
            goto L_0x0042
        L_0x0032:
            boolean r3 = r3.isOnline     // Catch:{ Exception -> 0x0042 }
            if (r3 != 0) goto L_0x0037
            goto L_0x0042
        L_0x0037:
            java.lang.String r3 = r2.b     // Catch:{ Exception -> 0x0042 }
            java.lang.String r2 = r2.g     // Catch:{ Exception -> 0x0042 }
            boolean r2 = r5.b(r3, r2)     // Catch:{ Exception -> 0x0042 }
            if (r2 != 0) goto L_0x0042
            goto L_0x0046
        L_0x0042:
            int r0 = r0 + 1
            goto L_0x0010
        L_0x0045:
            r1 = 1
        L_0x0046:
            monitor-exit(r5)
            return r1
        L_0x0048:
            monitor-exit(r5)
            return r1
        L_0x004a:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.miio.update.ModelUpdateManager.a():boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0043, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void b() {
        /*
            r4 = this;
            monitor-enter(r4)
            java.util.List<com.xiaomi.smarthome.miio.update.ModelUpdateInfo> r0 = r4.l     // Catch:{ all -> 0x0044 }
            if (r0 == 0) goto L_0x0042
            java.util.List<com.xiaomi.smarthome.miio.update.ModelUpdateInfo> r0 = r4.l     // Catch:{ all -> 0x0044 }
            int r0 = r0.size()     // Catch:{ all -> 0x0044 }
            if (r0 > 0) goto L_0x000e
            goto L_0x0042
        L_0x000e:
            r0 = 0
        L_0x000f:
            java.util.List<com.xiaomi.smarthome.miio.update.ModelUpdateInfo> r1 = r4.l     // Catch:{ all -> 0x0044 }
            int r1 = r1.size()     // Catch:{ all -> 0x0044 }
            if (r0 >= r1) goto L_0x0040
            java.util.List<com.xiaomi.smarthome.miio.update.ModelUpdateInfo> r1 = r4.l     // Catch:{ all -> 0x0044 }
            java.lang.Object r1 = r1.get(r0)     // Catch:{ all -> 0x0044 }
            com.xiaomi.smarthome.miio.update.ModelUpdateInfo r1 = (com.xiaomi.smarthome.miio.update.ModelUpdateInfo) r1     // Catch:{ all -> 0x0044 }
            boolean r2 = r1.h     // Catch:{ all -> 0x0044 }
            if (r2 == 0) goto L_0x0024
            goto L_0x003d
        L_0x0024:
            com.xiaomi.smarthome.device.SmartHomeDeviceManager r2 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()     // Catch:{ Exception -> 0x003d }
            java.lang.String r3 = r1.b     // Catch:{ Exception -> 0x003d }
            com.xiaomi.smarthome.device.Device r2 = r2.b((java.lang.String) r3)     // Catch:{ Exception -> 0x003d }
            if (r2 != 0) goto L_0x0031
            goto L_0x003d
        L_0x0031:
            boolean r2 = r2.isOnline     // Catch:{ Exception -> 0x003d }
            if (r2 != 0) goto L_0x0036
            goto L_0x003d
        L_0x0036:
            java.lang.String r2 = r1.b     // Catch:{ Exception -> 0x003d }
            java.lang.String r1 = r1.g     // Catch:{ Exception -> 0x003d }
            r4.a((java.lang.String) r2, (java.lang.String) r1)     // Catch:{ Exception -> 0x003d }
        L_0x003d:
            int r0 = r0 + 1
            goto L_0x000f
        L_0x0040:
            monitor-exit(r4)
            return
        L_0x0042:
            monitor-exit(r4)
            return
        L_0x0044:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.miio.update.ModelUpdateManager.b():void");
    }

    public synchronized void c() {
        PluginUpdateInfo j2;
        List<PluginRecord> O = CoreApi.a().O();
        if (O != null) {
            int i2 = 0;
            while (i2 < O.size()) {
                try {
                    PluginRecord pluginRecord = O.get(i2);
                    if (pluginRecord.l() && pluginRecord.n() && (j2 = pluginRecord.j()) != null) {
                        String str = f19975a;
                        Miio.b(str, "setAllPluginUpdateIgnore" + pluginRecord.o() + "******" + j2.e());
                        Context appContext = SHApplication.getAppContext();
                        StringBuilder sb = new StringBuilder();
                        sb.append(c);
                        sb.append(pluginRecord.o());
                        String sb2 = sb.toString();
                        PreferenceUtils.b(appContext, sb2, "" + j2.e());
                    }
                    i2++;
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        } else {
            return;
        }
        return;
    }

    public boolean a(String str, int i2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Context appContext = SHApplication.getAppContext();
        String a2 = PreferenceUtils.a(appContext, c + str, (String) null);
        String str2 = f19975a;
        Miio.b(str2, "isPluginUpdateIgnore" + str + "****** ignoreVersion   " + a2 + "   currVersion" + i2);
        if (TextUtils.isEmpty(a2)) {
            return false;
        }
        if (a2.equals("" + i2)) {
            return true;
        }
        return false;
    }

    public void a(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            Context appContext = SHApplication.getAppContext();
            PreferenceUtils.b(appContext, b + str, str2);
        }
    }

    public boolean b(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Context appContext = SHApplication.getAppContext();
        String a2 = PreferenceUtils.a(appContext, b + str, (String) null);
        if (TextUtils.isEmpty(a2) || !a2.equals(str2)) {
            return false;
        }
        return true;
    }

    public static ModelUpdateManager d() {
        if (k == null) {
            k = new ModelUpdateManager(0, 1);
        }
        return k;
    }

    ModelUpdateManager(int i2, int i3) {
        this.r = i2 == 0 ? 0 : 1;
        i3 = i3 < 1 ? 1 : i3;
        this.m = i3 <= 10 ? i3 : 10;
        this.q = new LinkedList<>();
    }

    public synchronized List<ModelUpdateInfo> e() {
        return this.l;
    }

    public synchronized void a(final CheckModelUpdateCallBack checkModelUpdateCallBack) {
        UpdateApi.a().d(SHApplication.getAppContext(), (List<String>) null, new AsyncCallback<List<ModelUpdateInfo>, Error>() {
            /* renamed from: a */
            public void onSuccess(final List<ModelUpdateInfo> list) {
                String str = ModelUpdateManager.f19975a;
                Log.d(str, "onSuccess,result=" + list);
                if (checkModelUpdateCallBack != null) {
                    SmartHomeDeviceManager.a().a((SmartHomeDeviceManager.IsDeviceReadyCallback) new SmartHomeDeviceManager.IsDeviceReadyCallback() {
                        public void onDeviceReady(List<Device> list) {
                            ModelUpdateManager.this.l = list;
                            checkModelUpdateCallBack.a(ModelUpdateManager.this.l);
                        }
                    });
                }
            }

            public void onFailure(Error error) {
                String str = ModelUpdateManager.f19975a;
                Log.d(str, "onFailure,result=" + error.a());
                if (checkModelUpdateCallBack != null) {
                    checkModelUpdateCallBack.a(error.a());
                }
            }
        });
    }

    public void a(Runnable runnable) {
        synchronized (this.q) {
            String str = f19975a;
            Log.d(str, "add task: " + runnable);
            this.q.addLast(runnable);
        }
    }

    /* access modifiers changed from: private */
    public Runnable h() {
        synchronized (this.q) {
            if (this.q.size() <= 0) {
                return null;
            }
            Runnable runnable = (Runnable) (this.r == 0 ? this.q.removeFirst() : this.q.removeLast());
            String str = f19975a;
            Log.d(str, "remove task: " + runnable);
            return runnable;
        }
    }

    public void f() {
        if (this.s == null) {
            this.s = new Thread(new PoolRunnable());
            this.s.start();
        }
    }

    public void g() {
        if (this.s != null) {
            this.s.interrupt();
            this.s = null;
        }
    }

    private class PoolRunnable implements Runnable {
        private PoolRunnable() {
        }

        /* JADX INFO: finally extract failed */
        /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x004e */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r2 = this;
                java.lang.String r0 = com.xiaomi.smarthome.miio.update.ModelUpdateManager.f19975a
                java.lang.String r1 = "开始轮询"
                android.util.Log.d(r0, r1)
                com.xiaomi.smarthome.miio.update.ModelUpdateManager r0 = com.xiaomi.smarthome.miio.update.ModelUpdateManager.this
                java.util.concurrent.ExecutorService r0 = r0.p
                if (r0 == 0) goto L_0x0027
                com.xiaomi.smarthome.miio.update.ModelUpdateManager r0 = com.xiaomi.smarthome.miio.update.ModelUpdateManager.this
                java.util.concurrent.ExecutorService r0 = r0.p
                boolean r0 = r0.isShutdown()
                if (r0 != 0) goto L_0x0027
                com.xiaomi.smarthome.miio.update.ModelUpdateManager r0 = com.xiaomi.smarthome.miio.update.ModelUpdateManager.this
                java.util.concurrent.ExecutorService r0 = r0.p
                boolean r0 = r0.isTerminated()
                if (r0 == 0) goto L_0x0036
            L_0x0027:
                com.xiaomi.smarthome.miio.update.ModelUpdateManager r0 = com.xiaomi.smarthome.miio.update.ModelUpdateManager.this
                com.xiaomi.smarthome.miio.update.ModelUpdateManager r1 = com.xiaomi.smarthome.miio.update.ModelUpdateManager.this
                int r1 = r1.m
                java.util.concurrent.ExecutorService r1 = java.util.concurrent.Executors.newFixedThreadPool(r1)
                java.util.concurrent.ExecutorService unused = r0.p = r1
            L_0x0036:
                java.lang.Thread r0 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0071 }
                boolean r0 = r0.isInterrupted()     // Catch:{ all -> 0x0071 }
                if (r0 != 0) goto L_0x0060
                com.xiaomi.smarthome.miio.update.ModelUpdateManager r0 = com.xiaomi.smarthome.miio.update.ModelUpdateManager.this     // Catch:{ all -> 0x0071 }
                java.lang.Runnable r0 = r0.h()     // Catch:{ all -> 0x0071 }
                if (r0 != 0) goto L_0x0056
                r0 = 200(0xc8, double:9.9E-322)
                java.lang.Thread.sleep(r0)     // Catch:{ InterruptedException -> 0x004e }
                goto L_0x0036
            L_0x004e:
                java.lang.Thread r0 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0071 }
                r0.interrupt()     // Catch:{ all -> 0x0071 }
                goto L_0x0036
            L_0x0056:
                com.xiaomi.smarthome.miio.update.ModelUpdateManager r1 = com.xiaomi.smarthome.miio.update.ModelUpdateManager.this     // Catch:{ all -> 0x0071 }
                java.util.concurrent.ExecutorService r1 = r1.p     // Catch:{ all -> 0x0071 }
                r1.execute(r0)     // Catch:{ all -> 0x0071 }
                goto L_0x0036
            L_0x0060:
                com.xiaomi.smarthome.miio.update.ModelUpdateManager r0 = com.xiaomi.smarthome.miio.update.ModelUpdateManager.this
                java.util.concurrent.ExecutorService r0 = r0.p
                r0.shutdown()
                java.lang.String r0 = com.xiaomi.smarthome.miio.update.ModelUpdateManager.f19975a
                java.lang.String r1 = "结束轮询"
                android.util.Log.d(r0, r1)
                return
            L_0x0071:
                r0 = move-exception
                com.xiaomi.smarthome.miio.update.ModelUpdateManager r1 = com.xiaomi.smarthome.miio.update.ModelUpdateManager.this
                java.util.concurrent.ExecutorService r1 = r1.p
                r1.shutdown()
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.miio.update.ModelUpdateManager.PoolRunnable.run():void");
        }
    }
}
