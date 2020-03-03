package com.xiaomi.mishopsdk.plugin;

import android.content.Context;
import com.xiaomi.mishopsdk.ShopApp;
import com.xiaomi.mishopsdk.plugin.lib.PluginInfo;
import com.xiaomi.mishopsdk.util.AndroidUtil;
import com.xiaomi.mishopsdk.util.Log;
import com.xiaomi.shop2.util.Device;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

class PluginInfoCache {
    private static final String INFO_DIR = "plugin_sync_info";
    private static final String TAG = "PluginInfoCache";
    public static long mLatestSyncTime;
    private static PluginInfoCache sInstance;
    private File mCachPath;
    private ConcurrentHashMap<String, PluginInfo> sMemCache = new ConcurrentHashMap<>();

    protected PluginInfoCache(Context context) {
        this.mCachPath = context.getDir(INFO_DIR, 0);
        this.mCachPath.mkdirs();
    }

    public static synchronized PluginInfoCache getInstance(Context context) {
        PluginInfoCache pluginInfoCache;
        synchronized (PluginInfoCache.class) {
            if (sInstance == null) {
                sInstance = new PluginInfoCache(context);
            }
            pluginInfoCache = sInstance;
        }
        return pluginInfoCache;
    }

    public PluginInfo getPluginInfo(Context context, String str) {
        PluginInfo pluginInfoFromMem = getPluginInfoFromMem(str);
        if (pluginInfoFromMem != null) {
            return pluginInfoFromMem;
        }
        PluginInfo pluginInfoFromDisk = getPluginInfoFromDisk(context, str);
        if (pluginInfoFromDisk != null) {
            this.sMemCache.put(str, pluginInfoFromDisk);
        }
        return pluginInfoFromDisk;
    }

    public void deletePluginsIncompatible() {
        int i;
        try {
            for (File file : this.mCachPath.listFiles()) {
                String name = file.getName();
                PluginInfo pluginInfoFromDisk = getPluginInfoFromDisk(ShopApp.instance, name);
                if (pluginInfoFromDisk != null) {
                    try {
                        i = Integer.valueOf(pluginInfoFromDisk.parentVersion).intValue();
                    } catch (NumberFormatException e) {
                        Log.e(TAG, "Integer.valueOf failed.info.parentVersion=%s.", pluginInfoFromDisk.parentVersion, e);
                        i = 0;
                    }
                    if (i < Device.MIN_SHOP_SDK_VERSION) {
                        Log.d(TAG, "find an incompatible plugin,id:%S, parentVersion:%s, MIN_SHOP_SDK_VERSION:%s.", name, Integer.valueOf(i), Integer.valueOf(Device.MIN_SHOP_SDK_VERSION));
                        file.delete();
                    }
                }
            }
        } catch (Exception e2) {
            Log.e(TAG, "deletePluginsIncompatible failed.", (Object) e2);
        }
    }

    /* access modifiers changed from: protected */
    public PluginInfo getPluginInfoFromMem(String str) {
        if (this.sMemCache.containsKey(str)) {
            return this.sMemCache.get(str);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public synchronized void initAllPluginInfos() {
        AndroidUtil.sPluginQueue.postRunnable(new Runnable() {
            public void run() {
                PluginInfoCache.this.createCachedPlugin();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void createCachedPlugin() {
        File[] listFiles;
        if (ShopApp.instance != null) {
            if (this.mCachPath == null) {
                this.mCachPath = ShopApp.instance.getDir(INFO_DIR, 0);
                this.mCachPath.mkdirs();
            }
            if (this.sMemCache.isEmpty() && (listFiles = this.mCachPath.listFiles()) != null) {
                for (File name : listFiles) {
                    String name2 = name.getName();
                    PluginInfo pluginInfoFromDisk = getPluginInfoFromDisk(ShopApp.instance, name2);
                    if (!(name2 == null || pluginInfoFromDisk == null)) {
                        this.sMemCache.put(name2, pluginInfoFromDisk);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public ArrayList<PluginInfo> getAllPluginInfosFromDisk() {
        ArrayList<PluginInfo> arrayList = new ArrayList<>();
        if (this.mCachPath == null) {
            this.mCachPath = ShopApp.instance.getDir(INFO_DIR, 0);
            this.mCachPath.mkdirs();
        }
        File[] listFiles = this.mCachPath.listFiles();
        if (listFiles == null) {
            return arrayList;
        }
        for (File name : listFiles) {
            arrayList.add(getPluginInfoFromDisk(ShopApp.instance, name.getName()));
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public ConcurrentHashMap<String, PluginInfo> getAllPluginCached() {
        if (this.sMemCache == null || this.sMemCache.isEmpty()) {
            createCachedPlugin();
        }
        return this.sMemCache;
    }

    /* access modifiers changed from: protected */
    public void deleteAllPluginInfos() {
        if (this.sMemCache != null) {
            this.sMemCache.clear();
        }
        if (this.mCachPath != null && this.mCachPath.exists()) {
            this.mCachPath.delete();
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x006e, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.xiaomi.mishopsdk.plugin.lib.PluginInfo getPluginInfoFromDisk(android.content.Context r4, java.lang.String r5) {
        /*
            r3 = this;
            monitor-enter(r3)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x006f }
            r0.<init>()     // Catch:{ all -> 0x006f }
            java.io.File r1 = r3.mCachPath     // Catch:{ all -> 0x006f }
            java.lang.String r1 = r1.getPath()     // Catch:{ all -> 0x006f }
            r0.append(r1)     // Catch:{ all -> 0x006f }
            java.lang.String r1 = "/"
            r0.append(r1)     // Catch:{ all -> 0x006f }
            r0.append(r5)     // Catch:{ all -> 0x006f }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x006f }
            java.io.File r1 = new java.io.File     // Catch:{ all -> 0x006f }
            r1.<init>(r0)     // Catch:{ all -> 0x006f }
            boolean r5 = android.text.TextUtils.isEmpty(r5)     // Catch:{ all -> 0x006f }
            r0 = 0
            if (r5 != 0) goto L_0x006d
            if (r4 == 0) goto L_0x006d
            boolean r4 = r1.exists()     // Catch:{ all -> 0x006f }
            if (r4 != 0) goto L_0x0030
            goto L_0x006d
        L_0x0030:
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0057, all -> 0x0052 }
            r4.<init>(r1)     // Catch:{ Exception -> 0x0057, all -> 0x0052 }
            java.io.ObjectInputStream r5 = new java.io.ObjectInputStream     // Catch:{ Exception -> 0x004f, all -> 0x004a }
            r5.<init>(r4)     // Catch:{ Exception -> 0x004f, all -> 0x004a }
            java.lang.Object r1 = r5.readObject()     // Catch:{ Exception -> 0x0048 }
            com.xiaomi.mishopsdk.plugin.lib.PluginInfo r1 = (com.xiaomi.mishopsdk.plugin.lib.PluginInfo) r1     // Catch:{ Exception -> 0x0048 }
            com.xiaomi.mishopsdk.util.FileUtil.closeQuietly(r5)     // Catch:{ all -> 0x006f }
            com.xiaomi.mishopsdk.util.FileUtil.closeQuietly(r4)     // Catch:{ all -> 0x006f }
            r0 = r1
            goto L_0x0063
        L_0x0048:
            r1 = move-exception
            goto L_0x005a
        L_0x004a:
            r5 = move-exception
            r2 = r0
            r0 = r5
            r5 = r2
            goto L_0x0066
        L_0x004f:
            r1 = move-exception
            r5 = r0
            goto L_0x005a
        L_0x0052:
            r4 = move-exception
            r5 = r0
            r0 = r4
            r4 = r5
            goto L_0x0066
        L_0x0057:
            r1 = move-exception
            r4 = r0
            r5 = r4
        L_0x005a:
            r1.printStackTrace()     // Catch:{ all -> 0x0065 }
            com.xiaomi.mishopsdk.util.FileUtil.closeQuietly(r5)     // Catch:{ all -> 0x006f }
            com.xiaomi.mishopsdk.util.FileUtil.closeQuietly(r4)     // Catch:{ all -> 0x006f }
        L_0x0063:
            monitor-exit(r3)
            return r0
        L_0x0065:
            r0 = move-exception
        L_0x0066:
            com.xiaomi.mishopsdk.util.FileUtil.closeQuietly(r5)     // Catch:{ all -> 0x006f }
            com.xiaomi.mishopsdk.util.FileUtil.closeQuietly(r4)     // Catch:{ all -> 0x006f }
            throw r0     // Catch:{ all -> 0x006f }
        L_0x006d:
            monitor-exit(r3)
            return r0
        L_0x006f:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mishopsdk.plugin.PluginInfoCache.getPluginInfoFromDisk(android.content.Context, java.lang.String):com.xiaomi.mishopsdk.plugin.lib.PluginInfo");
    }

    public boolean isPluginInfoExist(Context context, String str, PluginInfo pluginInfo) {
        PluginInfo pluginInfo2 = getPluginInfo(context, str);
        return pluginInfo2 != null && pluginInfo2.checkVersion(pluginInfo) && pluginInfo2.checkMd5(pluginInfo) && pluginInfo2.priority == pluginInfo.priority;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:43:0x006f, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean updatePluginInfo(android.content.Context r7, java.lang.String r8, com.xiaomi.mishopsdk.plugin.lib.PluginInfo r9) {
        /*
            r6 = this;
            monitor-enter(r6)
            boolean r0 = android.text.TextUtils.isEmpty(r8)     // Catch:{ all -> 0x0070 }
            r1 = 0
            if (r0 != 0) goto L_0x006e
            if (r9 != 0) goto L_0x000c
            goto L_0x006e
        L_0x000c:
            boolean r7 = r6.isPluginInfoExist(r7, r8, r9)     // Catch:{ all -> 0x0070 }
            r0 = 1
            if (r7 == 0) goto L_0x0015
            monitor-exit(r6)
            return r0
        L_0x0015:
            java.util.concurrent.ConcurrentHashMap<java.lang.String, com.xiaomi.mishopsdk.plugin.lib.PluginInfo> r7 = r6.sMemCache     // Catch:{ all -> 0x0070 }
            r7.put(r8, r9)     // Catch:{ all -> 0x0070 }
            r7 = 0
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x0058, all -> 0x0055 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0058, all -> 0x0055 }
            r3.<init>()     // Catch:{ Exception -> 0x0058, all -> 0x0055 }
            java.io.File r4 = r6.mCachPath     // Catch:{ Exception -> 0x0058, all -> 0x0055 }
            java.lang.String r4 = r4.getPath()     // Catch:{ Exception -> 0x0058, all -> 0x0055 }
            r3.append(r4)     // Catch:{ Exception -> 0x0058, all -> 0x0055 }
            java.lang.String r4 = "/"
            r3.append(r4)     // Catch:{ Exception -> 0x0058, all -> 0x0055 }
            r3.append(r8)     // Catch:{ Exception -> 0x0058, all -> 0x0055 }
            java.lang.String r8 = r3.toString()     // Catch:{ Exception -> 0x0058, all -> 0x0055 }
            r2.<init>(r8)     // Catch:{ Exception -> 0x0058, all -> 0x0055 }
            java.io.ObjectOutputStream r8 = new java.io.ObjectOutputStream     // Catch:{ Exception -> 0x0053 }
            r8.<init>(r2)     // Catch:{ Exception -> 0x0053 }
            r8.writeObject(r9)     // Catch:{ Exception -> 0x004e, all -> 0x0049 }
            com.xiaomi.mishopsdk.util.FileUtil.closeQuietly(r8)     // Catch:{ all -> 0x0070 }
            com.xiaomi.mishopsdk.util.FileUtil.closeQuietly(r2)     // Catch:{ all -> 0x0070 }
            goto L_0x0064
        L_0x0049:
            r7 = move-exception
            r5 = r8
            r8 = r7
            r7 = r5
            goto L_0x0067
        L_0x004e:
            r7 = move-exception
            r5 = r8
            r8 = r7
            r7 = r5
            goto L_0x005a
        L_0x0053:
            r8 = move-exception
            goto L_0x005a
        L_0x0055:
            r8 = move-exception
            r2 = r7
            goto L_0x0067
        L_0x0058:
            r8 = move-exception
            r2 = r7
        L_0x005a:
            r8.printStackTrace()     // Catch:{ all -> 0x0066 }
            com.xiaomi.mishopsdk.util.FileUtil.closeQuietly(r7)     // Catch:{ all -> 0x0070 }
            com.xiaomi.mishopsdk.util.FileUtil.closeQuietly(r2)     // Catch:{ all -> 0x0070 }
            r0 = 0
        L_0x0064:
            monitor-exit(r6)
            return r0
        L_0x0066:
            r8 = move-exception
        L_0x0067:
            com.xiaomi.mishopsdk.util.FileUtil.closeQuietly(r7)     // Catch:{ all -> 0x0070 }
            com.xiaomi.mishopsdk.util.FileUtil.closeQuietly(r2)     // Catch:{ all -> 0x0070 }
            throw r8     // Catch:{ all -> 0x0070 }
        L_0x006e:
            monitor-exit(r6)
            return r1
        L_0x0070:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mishopsdk.plugin.PluginInfoCache.updatePluginInfo(android.content.Context, java.lang.String, com.xiaomi.mishopsdk.plugin.lib.PluginInfo):boolean");
    }

    public synchronized boolean deletePluginInfo(Context context, String str) {
        File file = new File(this.mCachPath.getPath() + "/" + str);
        if (!file.exists()) {
            return false;
        }
        return file.delete();
    }
}
