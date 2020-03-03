package com.xiaomi.youpin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mi.global.bbs.utils.Constants;
import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.plugin.Callback;
import com.xiaomi.plugin.JsonParserUtils;
import com.xiaomi.plugin.PackageRawInfo;
import com.xiaomi.plugin.Parser;
import com.xiaomi.plugin.ProgressCallback;
import com.xiaomi.plugin.XmPluginHostApi;
import com.xiaomi.plugin.XmPluginPackage;
import com.xiaomi.plugin.update.pojo.UpdateInfo;
import com.xiaomi.pluginhost.PluginRuntimeManager;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.youpin.log.LogUtils;
import com.xiaomi.youpin.pojo.PluginInfoLocal;
import com.xiaomi.youpin.utils.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.cybergarage.http.HTTP;

public class PluginManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f23162a = "PluginManager";
    static final String b = "/shopv3/update/apkinfonew?type=MijiaPlugin&level=";
    public static final String c = "action_plugin_update";
    public static final String d = "extra_plugin_package_name";
    static final String e = "plugin_pref";
    static final String f = "local_install_plugin";
    static final String g = "last_app_version";
    static final int h = 100;
    private static PluginManager s = null;
    private static Object t = new Object();
    private static final String x = ".apk";
    Handler i = new Handler(Looper.getMainLooper());
    HandlerThread j = new HandlerThread("plugin_work_thread");
    Handler k;
    PackageManager l = this.y.getPackageManager();
    SharedPreferences m;
    PluginInfoLocal n;
    int o;
    boolean p = false;
    long q = 0;
    boolean r = false;
    private String u = "youpin_plugin";
    private String v = (g() + File.separator + "youpin_plugin" + File.separator + Constants.TitleMenu.MENU_DOWNLOAD);
    private String w = (g() + File.separator + "youpin_plugin" + File.separator + "install" + File.separator + com.xiaomi.smarthome.core.server.internal.plugin.PluginManager.i);
    /* access modifiers changed from: private */
    public Context y = SHApplication.getAppContext();
    private String z = null;

    private PluginManager() {
        this.j.start();
        this.k = new PluginWorkHandler(this.j.getLooper());
    }

    private String g() {
        if (TextUtils.isEmpty(this.z)) {
            this.z = this.y.getFilesDir().getPath();
        }
        return this.z;
    }

    public static PluginManager a() {
        if (s == null) {
            synchronized (t) {
                if (s == null) {
                    s = new PluginManager();
                }
            }
        }
        return s;
    }

    public void b() {
        if (!this.r && XmPluginHostApi.instance() != null && !this.p && System.currentTimeMillis() - this.q >= 60000) {
            this.p = true;
            this.q = System.currentTimeMillis();
            XmPluginHostApi.instance().sendMijiaShopRequest("/shopv3/update/apkinfonew?type=MijiaPlugin&level=29", new JsonObject(), new Callback<Map<String, UpdateInfo>>() {
                /* renamed from: a */
                public void onCache(Map<String, UpdateInfo> map) {
                }

                /* renamed from: a */
                public void onSuccess(Map<String, UpdateInfo> map, boolean z) {
                    if (map != null) {
                        for (String next : map.keySet()) {
                            PluginManager.this.a(next, map.get(next));
                        }
                    }
                    PluginManager.this.p = false;
                }

                public void onFailure(int i, String str) {
                    PluginManager.this.p = false;
                }
            }, new Parser<Map<String, UpdateInfo>>() {
                /* renamed from: a */
                public Map<String, UpdateInfo> parse(JsonElement jsonElement) {
                    HashMap hashMap = new HashMap();
                    if (jsonElement != null) {
                        for (Map.Entry next : jsonElement.getAsJsonObject().entrySet()) {
                            UpdateInfo updateInfo = (UpdateInfo) JsonParserUtils.parse((JsonElement) ((JsonElement) next.getValue()).getAsJsonObject(), (String[]) null, UpdateInfo.class);
                            if (updateInfo != null) {
                                hashMap.put(next.getKey(), updateInfo);
                            }
                        }
                    }
                    return hashMap;
                }
            }, false);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(String str, UpdateInfo updateInfo) {
        XmPluginPackage c2;
        if (!TextUtils.isEmpty(str) && updateInfo != null && (c2 = PluginRuntimeManager.a().c(str)) != null) {
            if (c2 == null || a(updateInfo.getVersion(), c2.packageRawInfo.mVersionName) > 0) {
                LogUtils.d("PluginManager", "updatePlugin:" + str + HTTP.HEADER_LINE_DELIM + updateInfo.getVersion());
                a(str, updateInfo, false);
            }
        }
    }

    public int a(String str, String str2) {
        int i2;
        if (str == null && str2 == null) {
            return 0;
        }
        if (str == null) {
            return -1;
        }
        if (str2 == null) {
            return 1;
        }
        String[] split = str.split("[^a-zA-Z0-9]+");
        String[] split2 = str2.split("[^a-zA-Z0-9]+");
        int min = Math.min(split.length, split2.length);
        int i3 = 0;
        while (i3 <= min) {
            if (i3 == split.length) {
                if (i3 == split2.length) {
                    return 0;
                }
                return -1;
            } else if (i3 == split2.length) {
                return 1;
            } else {
                int i4 = Integer.MAX_VALUE;
                try {
                    i2 = Integer.parseInt(split[i3]);
                } catch (Exception unused) {
                    i2 = Integer.MAX_VALUE;
                }
                try {
                    i4 = Integer.parseInt(split2[i3]);
                } catch (Exception unused2) {
                }
                if (i2 != i4) {
                    return i2 - i4;
                }
                int compareTo = split[i3].compareTo(split2[i3]);
                if (compareTo != 0) {
                    return compareTo;
                }
                i3++;
            }
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public void a(String str, UpdateInfo updateInfo, boolean z2) {
        String str2 = this.v + File.separator + str + JSMethod.NOT_SET + updateInfo.getVersion() + ".apk";
        String url = updateInfo.getUrl();
        if (z2) {
            url = updateInfo.getBak_url();
        }
        if (!TextUtils.isEmpty(url)) {
            final String str3 = str2;
            final String str4 = str;
            final boolean z3 = z2;
            final UpdateInfo updateInfo2 = updateInfo;
            XmPluginHostApi.instance().downloadFile(url, str2, new ProgressCallback<Void>() {
                /* renamed from: a */
                public void onCache(Void voidR) {
                }

                public void onProgress(long j, long j2) {
                }

                /* renamed from: a */
                public void onSuccess(Void voidR, boolean z) {
                    if (PluginManager.this.a(str3, false)) {
                        Intent intent = new Intent(PluginManager.c);
                        intent.putExtra(PluginManager.d, str4);
                        LocalBroadcastManager.getInstance(PluginManager.this.y).sendBroadcast(intent);
                    }
                }

                public void onFailure(int i, String str) {
                    if (!z3) {
                        PluginManager.this.a(str4, updateInfo2, true);
                    }
                }
            });
        }
    }

    public SharedPreferences c() {
        if (this.m == null) {
            this.m = this.y.getSharedPreferences(e, 0);
        }
        return this.m;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x009a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void d() {
        /*
            r5 = this;
            monitor-enter(r5)
            r5.c()     // Catch:{ all -> 0x00c8 }
            android.content.SharedPreferences r0 = r5.m     // Catch:{ all -> 0x00c8 }
            java.lang.String r1 = "local_install_plugin"
            java.lang.String r2 = ""
            java.lang.String r0 = r0.getString(r1, r2)     // Catch:{ all -> 0x00c8 }
            java.lang.String r1 = "PluginManager"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x00c8 }
            r2.<init>()     // Catch:{ all -> 0x00c8 }
            java.lang.String r3 = "plugin info:"
            r2.append(r3)     // Catch:{ all -> 0x00c8 }
            r2.append(r0)     // Catch:{ all -> 0x00c8 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x00c8 }
            com.xiaomi.youpin.log.LogUtils.d((java.lang.String) r1, (java.lang.String) r2)     // Catch:{ all -> 0x00c8 }
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x00c8 }
            if (r1 != 0) goto L_0x0030
            com.xiaomi.youpin.pojo.PluginInfoLocal r0 = com.xiaomi.youpin.pojo.PluginInfoLocal.parse(r0)     // Catch:{ all -> 0x00c8 }
            r5.n = r0     // Catch:{ all -> 0x00c8 }
        L_0x0030:
            com.xiaomi.youpin.pojo.PluginInfoLocal r0 = r5.n     // Catch:{ all -> 0x00c8 }
            if (r0 != 0) goto L_0x003b
            com.xiaomi.youpin.pojo.PluginInfoLocal r0 = new com.xiaomi.youpin.pojo.PluginInfoLocal     // Catch:{ all -> 0x00c8 }
            r0.<init>()     // Catch:{ all -> 0x00c8 }
            r5.n = r0     // Catch:{ all -> 0x00c8 }
        L_0x003b:
            android.content.SharedPreferences r0 = r5.m     // Catch:{ all -> 0x00c8 }
            java.lang.String r1 = "last_app_version"
            r2 = -1
            int r0 = r0.getInt(r1, r2)     // Catch:{ all -> 0x00c8 }
            r5.o = r0     // Catch:{ all -> 0x00c8 }
            com.xiaomi.plugin.XmPluginHostApi r0 = com.xiaomi.plugin.XmPluginHostApi.instance()     // Catch:{ all -> 0x00c8 }
            int r0 = r0.getAppVersionCode()     // Catch:{ all -> 0x00c8 }
            int r1 = r5.o     // Catch:{ all -> 0x00c8 }
            if (r0 != r1) goto L_0x005b
            boolean r0 = r5.r     // Catch:{ all -> 0x00c8 }
            if (r0 == 0) goto L_0x0057
            goto L_0x005b
        L_0x0057:
            r5.f()     // Catch:{ all -> 0x00c8 }
            goto L_0x008e
        L_0x005b:
            r5.e()     // Catch:{ all -> 0x00c8 }
            com.xiaomi.plugin.XmPluginHostApi r0 = com.xiaomi.plugin.XmPluginHostApi.instance()     // Catch:{ all -> 0x00c8 }
            int r0 = r0.getAppVersionCode()     // Catch:{ all -> 0x00c8 }
            r5.o = r0     // Catch:{ all -> 0x00c8 }
            android.content.SharedPreferences r0 = r5.m     // Catch:{ all -> 0x00c8 }
            android.content.SharedPreferences$Editor r0 = r0.edit()     // Catch:{ all -> 0x00c8 }
            java.lang.String r1 = "last_app_version"
            int r2 = r5.o     // Catch:{ all -> 0x00c8 }
            android.content.SharedPreferences$Editor r0 = r0.putInt(r1, r2)     // Catch:{ all -> 0x00c8 }
            r0.apply()     // Catch:{ all -> 0x00c8 }
            android.content.SharedPreferences r0 = r5.m     // Catch:{ all -> 0x00c8 }
            android.content.SharedPreferences$Editor r0 = r0.edit()     // Catch:{ all -> 0x00c8 }
            java.lang.String r1 = "local_install_plugin"
            com.xiaomi.youpin.pojo.PluginInfoLocal r2 = r5.n     // Catch:{ all -> 0x00c8 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x00c8 }
            android.content.SharedPreferences$Editor r0 = r0.putString(r1, r2)     // Catch:{ all -> 0x00c8 }
            r0.apply()     // Catch:{ all -> 0x00c8 }
        L_0x008e:
            com.xiaomi.youpin.app_sdk.url_dispatch.UrlDispatchManger r0 = com.xiaomi.youpin.app_sdk.url_dispatch.UrlDispatchManger.a()     // Catch:{ all -> 0x00c8 }
            java.lang.String[] r0 = r0.b()     // Catch:{ all -> 0x00c8 }
            r1 = 0
        L_0x0097:
            int r2 = r0.length     // Catch:{ all -> 0x00c8 }
            if (r1 >= r2) goto L_0x00c6
            com.xiaomi.pluginhost.PluginRuntimeManager r2 = com.xiaomi.pluginhost.PluginRuntimeManager.a()     // Catch:{ all -> 0x00c8 }
            r3 = r0[r1]     // Catch:{ all -> 0x00c8 }
            com.xiaomi.plugin.XmPluginPackage r2 = r2.c(r3)     // Catch:{ all -> 0x00c8 }
            if (r2 != 0) goto L_0x00c3
            java.lang.String r2 = "PluginManager"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00c8 }
            r3.<init>()     // Catch:{ all -> 0x00c8 }
            java.lang.String r4 = "not found and reinstall:"
            r3.append(r4)     // Catch:{ all -> 0x00c8 }
            r4 = r0[r1]     // Catch:{ all -> 0x00c8 }
            r3.append(r4)     // Catch:{ all -> 0x00c8 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x00c8 }
            com.xiaomi.youpin.log.LogUtils.d((java.lang.String) r2, (java.lang.String) r3)     // Catch:{ all -> 0x00c8 }
            r2 = r0[r1]     // Catch:{ all -> 0x00c8 }
            r5.a((java.lang.String) r2)     // Catch:{ all -> 0x00c8 }
        L_0x00c3:
            int r1 = r1 + 1
            goto L_0x0097
        L_0x00c6:
            monitor-exit(r5)
            return
        L_0x00c8:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.PluginManager.d():void");
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        try {
            int lastIndexOf = str.lastIndexOf(46);
            if (lastIndexOf == -1) {
                return;
            }
            if (lastIndexOf != str.length() - 1) {
                String substring = str.substring(lastIndexOf + 1);
                String[] list = this.y.getAssets().list(this.u);
                if (list != null && list.length > 0) {
                    for (String str2 : list) {
                        if (str2.startsWith(substring)) {
                            b(this.u + File.separator + str2);
                        }
                    }
                }
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public void e() {
        try {
            FileUtils.e(this.w);
            String[] list = this.y.getAssets().list(this.u);
            if (list != null && list.length > 0) {
                for (String str : list) {
                    b(this.u + File.separator + str);
                }
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    /* access modifiers changed from: package-private */
    public void b(String str) {
        String str2 = this.y.getCacheDir() + File.separator + str;
        try {
            com.xiaomi.pluginhost.FileUtils.a(this.y, str, str2);
            a(str2, false);
            FileUtils.d(str2);
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public void f() {
        File file = new File(this.w);
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
                return;
            }
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length != 0) {
                for (File a2 : listFiles) {
                    a(a2);
                }
            }
        }
    }

    public void c(String str) {
        a(str, true);
    }

    public boolean a(String str, boolean z2) {
        if (!d(str)) {
            LogUtils.e("PluginManager", "checkPlugin failed");
            FileUtils.d(str);
            return false;
        } else if (!new File(str).exists()) {
            LogUtils.e("PluginManager", "plugin file not exist:" + str);
            return false;
        } else {
            PackageRawInfo a2 = PluginRuntimeManager.a(this.y, str);
            if (a2 == null) {
                LogUtils.e("PluginManager", "loadPackageInfo failed");
                return false;
            }
            a(a2);
            if (z2) {
                FileUtils.e(b(a2));
            }
            String c2 = c(a2);
            FileUtils.e(c2);
            FileUtils.i(c2);
            String d2 = d(a2);
            FileUtils.a(str, d2);
            PackageUtils.a(str, c2);
            LogUtils.d("PluginManager", "installPlugin success:" + str);
            this.n.addPluginInfo(d2, a2, z2);
            a(d2, a2);
            return true;
        }
    }

    public void a(File file) {
        int b2;
        File[] listFiles = file.listFiles();
        int i2 = 0;
        File file2 = null;
        if (listFiles != null && listFiles.length > 0) {
            if (listFiles.length == 1) {
                file2 = listFiles[0];
                i2 = b(file2);
            } else {
                int i3 = 0;
                int i4 = -1;
                for (int i5 = 0; i5 < listFiles.length; i5++) {
                    if (listFiles[i5].isDirectory() && (b2 = b(listFiles[i5])) > i3) {
                        i4 = i5;
                        i3 = b2;
                    }
                }
                while (i2 < listFiles.length) {
                    if (i2 != i4) {
                        FileUtils.e(listFiles[i2].getAbsolutePath());
                    }
                    i2++;
                }
                if (i4 >= 0) {
                    file2 = listFiles[i4];
                }
                i2 = i3;
            }
        }
        if (file2 != null) {
            String str = file2.getAbsolutePath() + File.separator + i2 + ".apk";
            PackageRawInfo a2 = PluginRuntimeManager.a(this.y, str);
            if (a2 == null) {
                LogUtils.e("PluginManager", "loadPackageInfo failed");
                return;
            }
            a2.mPackageId = i2;
            a(str, a2);
        }
    }

    /* access modifiers changed from: package-private */
    public int b(File file) {
        if (file == null) {
            return 0;
        }
        try {
            return Integer.valueOf(file.getName()).intValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    /* access modifiers changed from: package-private */
    public void a(String str, PackageRawInfo packageRawInfo) {
        LogUtils.d("PluginManager", "load plugin:" + str);
        PluginRuntimeManager.a().a(str, packageRawInfo);
    }

    /* access modifiers changed from: package-private */
    public void a(PackageRawInfo packageRawInfo) {
        if (packageRawInfo != null && packageRawInfo.mPackageId <= 0) {
            XmPluginPackage c2 = PluginRuntimeManager.a().c(packageRawInfo.mPackageName);
            packageRawInfo.mPackageId = packageRawInfo.mVersion;
            if (c2 != null) {
                packageRawInfo.mPackageId = c2.packageId + 1;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public String b(PackageRawInfo packageRawInfo) {
        return this.w + File.separator + packageRawInfo.mPackageName;
    }

    /* access modifiers changed from: package-private */
    public String c(PackageRawInfo packageRawInfo) {
        return b(packageRawInfo) + File.separator + packageRawInfo.mPackageId;
    }

    /* access modifiers changed from: package-private */
    public String d(PackageRawInfo packageRawInfo) {
        return c(packageRawInfo) + File.separator + packageRawInfo.mPackageId + ".apk";
    }

    /* access modifiers changed from: package-private */
    public boolean d(String str) {
        if (PackageUtils.a(this.l, "BB69E51C4A1CCDFFFADF7BD32B964235", str)) {
            return true;
        }
        LogUtils.e("PluginManager", "checkApkPackageSignature error");
        return false;
    }

    class PluginWorkHandler extends Handler {
        public PluginWorkHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            int i = message.what;
        }
    }
}
