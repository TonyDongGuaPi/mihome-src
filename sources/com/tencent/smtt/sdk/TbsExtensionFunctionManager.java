package com.tencent.smtt.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.smtt.export.external.DexLoader;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.q;
import java.io.File;
import java.util.Map;

public class TbsExtensionFunctionManager {
    public static final String BUGLY_SWITCH_FILE_NAME = "bugly_switch.txt";
    public static final String COOKIE_SWITCH_FILE_NAME = "cookie_switch.txt";
    public static final String DISABLE_GET_APK_VERSION_SWITCH_FILE_NAME = "disable_get_apk_version_switch.txt";
    public static final String SP_KEY_COOKIE_DB_VERSION = "cookie_db_version";
    public static final String SP_NAME_FOR_COOKIE = "cookie_compatiable";
    public static final int SWITCH_BYTE_COOKIE = 1;
    public static final int SWITCH_BYTE_DISABLE_GET_APK_VERSION = 2;
    public static final String USEX5_FILE_NAME = "usex5.txt";
    private static TbsExtensionFunctionManager b;

    /* renamed from: a  reason: collision with root package name */
    private boolean f9093a;

    private TbsExtensionFunctionManager() {
    }

    public static TbsExtensionFunctionManager getInstance() {
        if (b == null) {
            synchronized (TbsExtensionFunctionManager.class) {
                if (b == null) {
                    b = new TbsExtensionFunctionManager();
                }
            }
        }
        return b;
    }

    public synchronized boolean canUseFunction(Context context, String str) {
        File file;
        file = new File(context.getFilesDir(), str);
        return file.exists() && file.isFile();
    }

    public synchronized int getRomCookieDBVersion(Context context) {
        SharedPreferences sharedPreferences = Build.VERSION.SDK_INT >= 11 ? context.getSharedPreferences(SP_NAME_FOR_COOKIE, 4) : context.getSharedPreferences(SP_NAME_FOR_COOKIE, 0);
        if (sharedPreferences == null) {
            return -1;
        }
        return sharedPreferences.getInt(SP_KEY_COOKIE_DB_VERSION, -1);
    }

    public synchronized void initTbsBuglyIfNeed(Context context) {
        String str;
        if (!this.f9093a) {
            if (!canUseFunction(context, BUGLY_SWITCH_FILE_NAME)) {
                TbsLog.i("TbsExtensionFunMana", "bugly is forbiden!!");
                return;
            }
            if (TbsShareManager.isThirdPartyApp(context)) {
                str = TbsShareManager.c(context);
            } else {
                File q = am.a().q(context);
                if (q == null) {
                    TbsLog.i("TbsExtensionFunMana", "getTbsCoreShareDir is null");
                }
                if (q.listFiles() != null) {
                    if (q.listFiles().length > 0) {
                        str = q.getAbsolutePath();
                    }
                }
                TbsLog.i("TbsExtensionFunMana", "getTbsCoreShareDir is empty!");
                return;
            }
            if (TextUtils.isEmpty(str)) {
                TbsLog.i("TbsExtensionFunMana", "bugly init ,corePath is null");
                return;
            }
            File q2 = am.a().q(context);
            if (q2 == null) {
                TbsLog.i("TbsExtensionFunMana", "bugly init ,optDir is null");
                return;
            }
            File file = new File(str, "tbs_bugly_dex.jar");
            try {
                Context context2 = context;
                q.a(new DexLoader(file.getParent(), context2, new String[]{file.getAbsolutePath()}, q2.getAbsolutePath(), (Map<String, Object>) null).loadClass("com.tencent.smtt.tbs.bugly.TBSBuglyManager"), "initBugly", (Class<?>[]) new Class[]{Context.class, String.class, String.class, String.class}, context, str, String.valueOf(WebView.getTbsSDKVersion(context)), String.valueOf(WebView.getTbsCoreVersion(context)));
                this.f9093a = true;
                TbsLog.i("TbsExtensionFunMana", "initTbsBuglyIfNeed success!");
            } catch (Throwable th) {
                TbsLog.i("TbsExtensionFunMana", "bugly init ,try init bugly failed(need new core):" + Log.getStackTraceString(th));
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0063, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean setFunctionEnable(android.content.Context r4, java.lang.String r5, boolean r6) {
        /*
            r3 = this;
            monitor-enter(r3)
            r0 = 0
            if (r4 != 0) goto L_0x0006
            monitor-exit(r3)
            return r0
        L_0x0006:
            java.io.File r1 = new java.io.File     // Catch:{ all -> 0x0064 }
            java.io.File r4 = r4.getFilesDir()     // Catch:{ all -> 0x0064 }
            r1.<init>(r4, r5)     // Catch:{ all -> 0x0064 }
            r4 = 1
            if (r6 == 0) goto L_0x003c
            boolean r6 = r1.exists()     // Catch:{ all -> 0x0064 }
            if (r6 != 0) goto L_0x0062
            boolean r6 = r1.createNewFile()     // Catch:{ IOException -> 0x0020 }
            if (r6 == 0) goto L_0x0062
            monitor-exit(r3)
            return r4
        L_0x0020:
            r4 = move-exception
            java.lang.String r6 = "TbsExtensionFunMana"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0064 }
            r1.<init>()     // Catch:{ all -> 0x0064 }
            java.lang.String r2 = "setFunctionEnable,createNewFile fail:"
            r1.append(r2)     // Catch:{ all -> 0x0064 }
            r1.append(r5)     // Catch:{ all -> 0x0064 }
            java.lang.String r5 = r1.toString()     // Catch:{ all -> 0x0064 }
            com.tencent.smtt.utils.TbsLog.e(r6, r5)     // Catch:{ all -> 0x0064 }
            r4.printStackTrace()     // Catch:{ all -> 0x0064 }
            monitor-exit(r3)
            return r0
        L_0x003c:
            boolean r6 = r1.exists()     // Catch:{ all -> 0x0064 }
            if (r6 == 0) goto L_0x0062
            boolean r6 = r1.delete()     // Catch:{ all -> 0x0064 }
            if (r6 == 0) goto L_0x004a
            monitor-exit(r3)
            return r4
        L_0x004a:
            java.lang.String r4 = "TbsExtensionFunMana"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0064 }
            r6.<init>()     // Catch:{ all -> 0x0064 }
            java.lang.String r1 = "setFunctionEnable,file.delete fail:"
            r6.append(r1)     // Catch:{ all -> 0x0064 }
            r6.append(r5)     // Catch:{ all -> 0x0064 }
            java.lang.String r5 = r6.toString()     // Catch:{ all -> 0x0064 }
            com.tencent.smtt.utils.TbsLog.e(r4, r5)     // Catch:{ all -> 0x0064 }
            monitor-exit(r3)
            return r0
        L_0x0062:
            monitor-exit(r3)
            return r4
        L_0x0064:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsExtensionFunctionManager.setFunctionEnable(android.content.Context, java.lang.String, boolean):boolean");
    }
}
