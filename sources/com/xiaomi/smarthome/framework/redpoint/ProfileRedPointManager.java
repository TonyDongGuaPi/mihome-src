package com.xiaomi.smarthome.framework.redpoint;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.core.entity.plugin.PluginUpdateInfo;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.library.common.util.PreferenceUtils;
import com.xiaomi.smarthome.miio.Miio;
import com.xiaomi.smarthome.miio.update.AppUpdateManger;
import com.xiaomi.smarthome.miio.update.ModelUpdateInfo;
import java.util.List;

public class ProfileRedPointManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f17677a = "setting_main_model_update_ignore_prefix";
    public static final String b = "setting_main_app_update_ignore_prefix";
    public static final String c = "setting_main_plugin_update_ignore_prefix";
    public static final String d = "setting_main_new_msg_timestamp";
    public static final String e = "message_center_new_msg_timestamp";
    private static final String g = "ProfileRedPointManager";
    private static ProfileRedPointManager h;
    List<ModelUpdateInfo> f;
    private long i = 0;

    public interface IgnoreStateCallback {
        void a(boolean z);
    }

    public boolean b() {
        return false;
    }

    public static ProfileRedPointManager a() {
        if (h == null) {
            synchronized (ProfileRedPointManager.class) {
                h = new ProfileRedPointManager();
            }
        }
        return h;
    }

    public long c() {
        return this.i;
    }

    public void a(long j) {
        Miio.b(g, "serverDiff" + j);
        this.i = j;
    }

    public void a(List<ModelUpdateInfo> list, final IgnoreStateCallback ignoreStateCallback) {
        this.f = list;
        SHApplication.getThreadExecutor().submit(new Runnable() {
            public void run() {
                boolean a2 = ProfileRedPointManager.this.i();
                if (ignoreStateCallback != null) {
                    ignoreStateCallback.a(a2);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0047, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0049, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean i() {
        /*
            r5 = this;
            monitor-enter(r5)
            java.util.List<com.xiaomi.smarthome.miio.update.ModelUpdateInfo> r0 = r5.f     // Catch:{ all -> 0x004a }
            r1 = 0
            if (r0 == 0) goto L_0x0048
            java.util.List<com.xiaomi.smarthome.miio.update.ModelUpdateInfo> r0 = r5.f     // Catch:{ all -> 0x004a }
            int r0 = r0.size()     // Catch:{ all -> 0x004a }
            if (r0 > 0) goto L_0x000f
            goto L_0x0048
        L_0x000f:
            r0 = 0
        L_0x0010:
            java.util.List<com.xiaomi.smarthome.miio.update.ModelUpdateInfo> r2 = r5.f     // Catch:{ all -> 0x004a }
            int r2 = r2.size()     // Catch:{ all -> 0x004a }
            if (r0 >= r2) goto L_0x0045
            java.util.List<com.xiaomi.smarthome.miio.update.ModelUpdateInfo> r2 = r5.f     // Catch:{ all -> 0x004a }
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
            boolean r2 = r5.a((java.lang.String) r3, (java.lang.String) r2)     // Catch:{ Exception -> 0x0042 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.redpoint.ProfileRedPointManager.i():boolean");
    }

    private boolean a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Context appContext = SHApplication.getAppContext();
        String a2 = PreferenceUtils.a(appContext, f17677a + str, (String) null);
        if (TextUtils.isEmpty(a2) || !a2.equals(str2)) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0043, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void d() {
        /*
            r4 = this;
            monitor-enter(r4)
            java.util.List<com.xiaomi.smarthome.miio.update.ModelUpdateInfo> r0 = r4.f     // Catch:{ all -> 0x0044 }
            if (r0 == 0) goto L_0x0042
            java.util.List<com.xiaomi.smarthome.miio.update.ModelUpdateInfo> r0 = r4.f     // Catch:{ all -> 0x0044 }
            int r0 = r0.size()     // Catch:{ all -> 0x0044 }
            if (r0 > 0) goto L_0x000e
            goto L_0x0042
        L_0x000e:
            r0 = 0
        L_0x000f:
            java.util.List<com.xiaomi.smarthome.miio.update.ModelUpdateInfo> r1 = r4.f     // Catch:{ all -> 0x0044 }
            int r1 = r1.size()     // Catch:{ all -> 0x0044 }
            if (r0 >= r1) goto L_0x0040
            java.util.List<com.xiaomi.smarthome.miio.update.ModelUpdateInfo> r1 = r4.f     // Catch:{ all -> 0x0044 }
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
            r4.b(r2, r1)     // Catch:{ Exception -> 0x003d }
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
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.redpoint.ProfileRedPointManager.d():void");
    }

    private void b(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            Context appContext = SHApplication.getAppContext();
            PreferenceUtils.b(appContext, f17677a + str, str2);
        }
    }

    public synchronized void e() {
        List<PluginRecord> O = CoreApi.a().O();
        int i2 = 0;
        while (i2 < O.size()) {
            try {
                PluginRecord pluginRecord = O.get(i2);
                if (pluginRecord.l() && pluginRecord.n()) {
                    PluginUpdateInfo j = pluginRecord.j();
                    Miio.b(g, "setAllPluginUpdateIgnore" + pluginRecord.o() + "******" + j.e());
                    if (j != null) {
                        PreferenceUtils.b(SHApplication.getAppContext(), c + pluginRecord.o(), "" + j.e());
                    }
                }
                i2++;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return;
    }

    public boolean a(List<PluginRecord> list) {
        PluginUpdateInfo j;
        boolean z = true;
        for (int i2 = 0; i2 < list.size(); i2++) {
            PluginRecord pluginRecord = list.get(i2);
            if (pluginRecord.l() && pluginRecord.n() && (j = pluginRecord.j()) != null) {
                z &= a(pluginRecord.o(), j.e());
            }
        }
        return z;
    }

    private boolean a(String str, int i2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Context appContext = SHApplication.getAppContext();
        String a2 = PreferenceUtils.a(appContext, c + str, (String) null);
        Miio.b(g, "isPluginUpdateIgnore" + str + "****** ignoreVersion   " + a2 + "   currVersion" + i2);
        if (TextUtils.isEmpty(a2)) {
            return false;
        }
        if (a2.equals("" + i2)) {
            return true;
        }
        return false;
    }

    public boolean f() {
        if (h() && PreferenceUtils.c(SHApplication.getAppContext(), b, 0) == AppUpdateManger.g()) {
            return true;
        }
        return false;
    }

    public synchronized void g() {
        PreferenceUtils.a(SHApplication.getAppContext(), b, AppUpdateManger.g());
    }

    public boolean h() {
        return AppUpdateManger.d();
    }
}
