package com.tencent.smtt.sdk;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.sdk.TbsListener;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.j;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.xiaomi.smarthome.homeroom.HomeManager;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.nio.channels.FileLock;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class am {

    /* renamed from: a  reason: collision with root package name */
    public static ThreadLocal<Integer> f9142a = new an();
    static boolean b = false;
    static final FileFilter c = new ao();
    private static am d;
    private static final ReentrantLock i = new ReentrantLock();
    private static final Lock j = new ReentrantLock();
    private static FileLock l = null;
    private static Handler m = null;
    private static final Long[][] n = {new Long[]{44006L, 39094008L}, new Long[]{44005L, 39094008L}, new Long[]{43910L, 38917816L}, new Long[]{44027L, 39094008L}, new Long[]{44028L, 39094008L}, new Long[]{44029L, 39094008L}, new Long[]{44030L, 39094008L}, new Long[]{44032L, 39094008L}, new Long[]{44033L, 39094008L}, new Long[]{44034L, 39094008L}, new Long[]{43909L, 38917816L}};
    private static int o = 0;
    private static boolean p = false;
    private int e = 0;
    private FileLock f;
    private FileOutputStream g;
    private boolean h = false;
    private boolean k = false;

    private am() {
        if (m == null) {
            m = new ap(this, al.a().getLooper());
        }
    }

    private void A(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--generateNewTbsCoreFromUnzip");
        if (!z(context)) {
            TbsLog.i("TbsInstaller", "get rename fileLock#4 ## failed!");
            return;
        }
        try {
            C(context);
            D(context);
            if (!TbsShareManager.isThirdPartyApp(context)) {
                TbsShareManager.a(context);
            }
            ai.a(context).a(0);
            ai.a(context).b(0);
            ai.a(context).d(0);
            ai.a(context).a("incrupdate_retry_num", 0);
            ai.a(context).b(0, 3);
            ai.a(context).a("");
            ai.a(context).c(-1);
            if (!TbsShareManager.isThirdPartyApp(context)) {
                int i2 = TbsDownloadConfig.getInstance(context).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DECOUPLECOREVERSION, 0);
                if (i2 <= 0 || i2 == a().h(context) || i2 != a().i(context)) {
                    TbsLog.i("TbsInstaller", "TbsInstaller--generateNewTbsCoreFromUnzip #1 deCoupleCoreVersion is " + i2 + " getTbsCoreShareDecoupleCoreVersion is " + a().h(context) + " getTbsCoreInstalledVerInNolock is " + a().i(context));
                } else {
                    n(context);
                }
            }
            if (TbsShareManager.isThirdPartyApp(context)) {
                TbsShareManager.writeCoreInfoForThirdPartyApp(context, m(context), true);
            }
            f9142a.set(0);
            o = 0;
        } catch (Throwable th) {
            th.printStackTrace();
            TbsLogReport a2 = TbsLogReport.a(context);
            a2.a((int) TbsListener.ErrorCode.RENAME_EXCEPTION, "exception when renameing from unzip:" + th.toString());
            TbsLog.e("TbsInstaller", "TbsInstaller--generateNewTbsCoreFromUnzip Exception", true);
        }
        y(context);
    }

    private void B(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--generateNewTbsCoreFromCopy");
        if (!z(context)) {
            TbsLog.i("TbsInstaller", "get rename fileLock#4 ## failed!");
            return;
        }
        try {
            C(context);
            E(context);
            TbsShareManager.a(context);
            ai.a(context).a(0, 3);
            if (!TbsShareManager.isThirdPartyApp(context)) {
                int i2 = TbsDownloadConfig.getInstance(context).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DECOUPLECOREVERSION, 0);
                if (i2 <= 0 || i2 == a().h(context) || i2 != a().i(context)) {
                    TbsLog.i("TbsInstaller", "TbsInstaller--generateNewTbsCoreFromCopy #1 deCoupleCoreVersion is " + i2 + " getTbsCoreShareDecoupleCoreVersion is " + a().h(context) + " getTbsCoreInstalledVerInNolock is " + a().i(context));
                } else {
                    n(context);
                }
            }
            f9142a.set(0);
        } catch (Exception e2) {
            e2.printStackTrace();
            TbsLogReport a2 = TbsLogReport.a(context);
            a2.a((int) TbsListener.ErrorCode.RENAME_EXCEPTION, "exception when renameing from copy:" + e2.toString());
        }
        y(context);
    }

    private void C(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--deleteOldCore");
        j.a(q(context), false);
    }

    private void D(Context context) {
        TbsLogReport a2;
        int i2;
        TbsLog.i("TbsInstaller", "TbsInstaller--renameShareDir");
        File t = t(context);
        File q = q(context);
        if (t != null && q != null) {
            boolean renameTo = t.renameTo(q);
            if (context != null && "com.tencent.mm".equals(context.getApplicationContext().getApplicationInfo().packageName)) {
                if (renameTo) {
                    a2 = TbsLogReport.a(context);
                    i2 = TbsListener.ErrorCode.RENAME_SUCCESS;
                } else {
                    a2 = TbsLogReport.a(context);
                    i2 = TbsListener.ErrorCode.RENAME_FAIL;
                }
                a2.a(i2, " ");
            }
            f(context, false);
        }
    }

    private void E(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--renameTbsCoreCopyDir");
        File v = v(context);
        File q = q(context);
        if (v != null && q != null) {
            v.renameTo(q);
            f(context, false);
        }
    }

    private void F(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--clearNewTbsCore");
        File t = t(context);
        if (t != null) {
            j.a(t, false);
        }
        ai.a(context).b(0, 5);
        ai.a(context).c(-1);
        QbSdk.a(context, "TbsInstaller::clearNewTbsCore forceSysWebViewInner!");
    }

    static synchronized am a() {
        am amVar;
        synchronized (am.class) {
            if (d == null) {
                synchronized (am.class) {
                    if (d == null) {
                        d = new am();
                    }
                }
            }
            amVar = d;
        }
        return amVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x008b A[SYNTHETIC, Splitter:B:28:0x008b] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0095 A[SYNTHETIC, Splitter:B:33:0x0095] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00a1 A[SYNTHETIC, Splitter:B:39:0x00a1] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00ab A[SYNTHETIC, Splitter:B:44:0x00ab] */
    /* JADX WARNING: Removed duplicated region for block: B:50:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(int r5, java.lang.String r6, android.content.Context r7) {
        /*
            r4 = this;
            java.io.File r5 = new java.io.File
            r5.<init>(r6)
            r5.delete()
            java.lang.String r5 = "TbsInstaller"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Local tbs apk("
            r0.append(r1)
            r0.append(r6)
            java.lang.String r6 = ") is deleted!"
            r0.append(r6)
            java.lang.String r6 = r0.toString()
            r0 = 1
            com.tencent.smtt.utils.TbsLog.i(r5, r6, r0)
            java.lang.String r5 = "tbs"
            r6 = 0
            java.io.File r5 = r7.getDir(r5, r6)
            java.io.File r6 = new java.io.File
            java.lang.String r7 = "core_unzip_tmp"
            r6.<init>(r5, r7)
            boolean r5 = r6.canRead()
            if (r5 == 0) goto L_0x00b4
            java.io.File r5 = new java.io.File
            java.lang.String r7 = "tbs.conf"
            r5.<init>(r6, r7)
            java.util.Properties r6 = new java.util.Properties
            r6.<init>()
            r7 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x0084, all -> 0x0081 }
            r1.<init>(r5)     // Catch:{ Throwable -> 0x0084, all -> 0x0081 }
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ Throwable -> 0x0084, all -> 0x0081 }
            r2.<init>(r1)     // Catch:{ Throwable -> 0x0084, all -> 0x0081 }
            r6.load(r2)     // Catch:{ Throwable -> 0x007f }
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ Throwable -> 0x007f }
            r1.<init>(r5)     // Catch:{ Throwable -> 0x007f }
            java.io.BufferedOutputStream r5 = new java.io.BufferedOutputStream     // Catch:{ Throwable -> 0x007f }
            r5.<init>(r1)     // Catch:{ Throwable -> 0x007f }
            java.lang.String r1 = "tbs_local_installation"
            java.lang.String r3 = "true"
            r6.setProperty(r1, r3)     // Catch:{ Throwable -> 0x007c, all -> 0x0079 }
            r6.store(r5, r7)     // Catch:{ Throwable -> 0x007c, all -> 0x0079 }
            java.lang.String r6 = "TbsInstaller"
            java.lang.String r7 = "TBS_LOCAL_INSTALLATION is set!"
            com.tencent.smtt.utils.TbsLog.i(r6, r7, r0)     // Catch:{ Throwable -> 0x007c, all -> 0x0079 }
            r5.close()     // Catch:{ IOException -> 0x0071 }
            goto L_0x0075
        L_0x0071:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0075:
            r2.close()     // Catch:{ IOException -> 0x0099 }
            goto L_0x00b4
        L_0x0079:
            r6 = move-exception
            r7 = r5
            goto L_0x009f
        L_0x007c:
            r6 = move-exception
            r7 = r5
            goto L_0x0086
        L_0x007f:
            r6 = move-exception
            goto L_0x0086
        L_0x0081:
            r6 = move-exception
            r2 = r7
            goto L_0x009f
        L_0x0084:
            r6 = move-exception
            r2 = r7
        L_0x0086:
            r6.printStackTrace()     // Catch:{ all -> 0x009e }
            if (r7 == 0) goto L_0x0093
            r7.close()     // Catch:{ IOException -> 0x008f }
            goto L_0x0093
        L_0x008f:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0093:
            if (r2 == 0) goto L_0x00b4
            r2.close()     // Catch:{ IOException -> 0x0099 }
            goto L_0x00b4
        L_0x0099:
            r5 = move-exception
            r5.printStackTrace()
            goto L_0x00b4
        L_0x009e:
            r6 = move-exception
        L_0x009f:
            if (r7 == 0) goto L_0x00a9
            r7.close()     // Catch:{ IOException -> 0x00a5 }
            goto L_0x00a9
        L_0x00a5:
            r5 = move-exception
            r5.printStackTrace()
        L_0x00a9:
            if (r2 == 0) goto L_0x00b3
            r2.close()     // Catch:{ IOException -> 0x00af }
            goto L_0x00b3
        L_0x00af:
            r5 = move-exception
            r5.printStackTrace()
        L_0x00b3:
            throw r6
        L_0x00b4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.am.a(int, java.lang.String, android.content.Context):void");
    }

    public static void a(Context context) {
        String str;
        String str2;
        if (!x(context)) {
            if (d(context, "core_unzip_tmp")) {
                TbsCoreLoadStat.getInstance().a(context, 417, new Throwable("TMP_TBS_UNZIP_FOLDER_NAME"));
                str = "TbsInstaller";
                str2 = "TbsInstaller-UploadIfTempCoreExistConfError INFO_TEMP_CORE_EXIST_CONF_ERROR TMP_TBS_UNZIP_FOLDER_NAME";
            } else if (d(context, "core_share_backup_tmp")) {
                TbsCoreLoadStat.getInstance().a(context, 417, new Throwable("TMP_BACKUP_TBSCORE_FOLDER_NAME"));
                str = "TbsInstaller";
                str2 = "TbsInstaller-UploadIfTempCoreExistConfError INFO_TEMP_CORE_EXIST_CONF_ERROR TMP_BACKUP_TBSCORE_FOLDER_NAME";
            } else if (d(context, "core_copy_tmp")) {
                TbsCoreLoadStat.getInstance().a(context, 417, new Throwable("TMP_TBS_COPY_FOLDER_NAME"));
                str = "TbsInstaller";
                str2 = "TbsInstaller-UploadIfTempCoreExistConfError INFO_TEMP_CORE_EXIST_CONF_ERROR TMP_TBS_COPY_FOLDER_NAME";
            } else {
                return;
            }
            TbsLog.e(str, str2);
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x0301, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x0302, code lost:
        r3 = r0;
        r16 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x0307, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x0308, code lost:
        r4 = r0;
        r5 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:?, code lost:
        r16.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x0315, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:?, code lost:
        r0.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:189:?, code lost:
        r16.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:190:0x04d6, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:193:?, code lost:
        r0.printStackTrace();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x0301 A[ExcHandler: all (r0v9 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:91:0x02c1] */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x0311 A[SYNTHETIC, Splitter:B:122:0x0311] */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x031d A[Catch:{ Exception -> 0x0504 }] */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x03f1 A[ADDED_TO_REGION, Catch:{ Exception -> 0x0504 }] */
    /* JADX WARNING: Removed duplicated region for block: B:166:0x043b A[Catch:{ Exception -> 0x0504 }] */
    /* JADX WARNING: Removed duplicated region for block: B:167:0x043e A[Catch:{ Exception -> 0x0504 }] */
    /* JADX WARNING: Removed duplicated region for block: B:171:0x0451 A[Catch:{ Exception -> 0x0504 }] */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x045b A[Catch:{ Exception -> 0x0504 }] */
    /* JADX WARNING: Removed duplicated region for block: B:176:0x0487 A[Catch:{ Exception -> 0x0504 }] */
    /* JADX WARNING: Removed duplicated region for block: B:177:0x0490 A[Catch:{ Exception -> 0x0504 }] */
    /* JADX WARNING: Removed duplicated region for block: B:188:0x04d2 A[SYNTHETIC, Splitter:B:188:0x04d2] */
    @android.annotation.TargetApi(11)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(android.content.Context r22, android.content.Context r23, int r24) {
        /*
            r21 = this;
            r1 = r21
            r2 = r23
            r3 = r24
            com.tencent.smtt.sdk.TbsDownloadConfig r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r22)
            r5 = -524(0xfffffffffffffdf4, float:NaN)
            r4.setInstallInterruptCode(r5)
            boolean r4 = r1.c(r2)
            if (r4 == 0) goto L_0x0016
            return
        L_0x0016:
            java.lang.String r4 = "TbsInstaller"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "TbsInstaller-copyTbsCoreInThread start!  tbsCoreTargetVer is "
            r5.append(r6)
            r5.append(r3)
            java.lang.String r5 = r5.toString()
            com.tencent.smtt.utils.TbsLog.i(r4, r5)
            int r4 = android.os.Build.VERSION.SDK_INT
            r5 = 4
            r6 = 11
            r7 = 0
            if (r4 < r6) goto L_0x003b
            java.lang.String r4 = "tbs_preloadx5_check_cfg_file"
            android.content.SharedPreferences r4 = r2.getSharedPreferences(r4, r5)
            goto L_0x0041
        L_0x003b:
            java.lang.String r4 = "tbs_preloadx5_check_cfg_file"
            android.content.SharedPreferences r4 = r2.getSharedPreferences(r4, r7)
        L_0x0041:
            java.lang.String r8 = "tbs_precheck_disable_version"
            r9 = -1
            int r4 = r4.getInt(r8, r9)
            if (r4 != r3) goto L_0x006f
            java.lang.String r2 = "TbsInstaller"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "TbsInstaller-copyTbsCoreInThread -- version:"
            r4.append(r5)
            r4.append(r3)
            java.lang.String r3 = " is disabled by preload_x5_check!"
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            com.tencent.smtt.utils.TbsLog.e(r2, r3)
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r22)
            r3 = -525(0xfffffffffffffdf3, float:NaN)
        L_0x006b:
            r2.setInstallInterruptCode(r3)
            return
        L_0x006f:
            boolean r4 = r1.w(r2)
            if (r4 != 0) goto L_0x007c
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r22)
            r3 = -526(0xfffffffffffffdf2, float:NaN)
            goto L_0x006b
        L_0x007c:
            java.util.concurrent.locks.Lock r4 = j
            boolean r4 = r4.tryLock()
            java.lang.String r8 = "TbsInstaller"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "TbsInstaller-copyTbsCoreInThread #1 locked is "
            r10.append(r11)
            r10.append(r4)
            java.lang.String r10 = r10.toString()
            com.tencent.smtt.utils.TbsLog.i(r8, r10)
            if (r4 == 0) goto L_0x059f
            java.util.concurrent.locks.ReentrantLock r4 = i
            r4.lock()
            com.tencent.smtt.sdk.ai r8 = com.tencent.smtt.sdk.ai.a((android.content.Context) r23)     // Catch:{ Exception -> 0x0542 }
            java.lang.String r10 = "copy_core_ver"
            int r8 = r8.c((java.lang.String) r10)     // Catch:{ Exception -> 0x0542 }
            com.tencent.smtt.sdk.ai r10 = com.tencent.smtt.sdk.ai.a((android.content.Context) r23)     // Catch:{ Exception -> 0x0542 }
            java.lang.String r11 = "copy_status"
            int r10 = r10.b((java.lang.String) r11)     // Catch:{ Exception -> 0x0542 }
            r11 = -528(0xfffffffffffffdf0, float:NaN)
            r12 = 220(0xdc, float:3.08E-43)
            if (r8 != r3) goto L_0x00d3
            com.tencent.smtt.sdk.TbsListener r3 = com.tencent.smtt.sdk.QbSdk.m     // Catch:{ Exception -> 0x0542 }
            r3.onInstallFinish(r12)     // Catch:{ Exception -> 0x0542 }
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r22)     // Catch:{ Exception -> 0x0542 }
            r3.setInstallInterruptCode(r11)     // Catch:{ Exception -> 0x0542 }
            java.util.concurrent.locks.ReentrantLock r2 = i
            r2.unlock()
            java.util.concurrent.locks.Lock r2 = j
            r2.unlock()
            r21.b()
            return
        L_0x00d3:
            int r13 = r1.i(r2)     // Catch:{ Exception -> 0x0542 }
            java.lang.String r14 = "TbsInstaller"
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0542 }
            r15.<init>()     // Catch:{ Exception -> 0x0542 }
            java.lang.String r4 = "TbsInstaller-copyTbsCoreInThread tbsCoreInstalledVer="
            r15.append(r4)     // Catch:{ Exception -> 0x0542 }
            r15.append(r13)     // Catch:{ Exception -> 0x0542 }
            java.lang.String r4 = r15.toString()     // Catch:{ Exception -> 0x0542 }
            com.tencent.smtt.utils.TbsLog.i(r14, r4)     // Catch:{ Exception -> 0x0542 }
            if (r13 != r3) goto L_0x011f
            com.tencent.smtt.sdk.TbsListener r3 = com.tencent.smtt.sdk.QbSdk.m     // Catch:{ Exception -> 0x0542 }
            r3.onInstallFinish(r12)     // Catch:{ Exception -> 0x0542 }
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r22)     // Catch:{ Exception -> 0x0542 }
            r3.setInstallInterruptCode(r11)     // Catch:{ Exception -> 0x0542 }
            java.lang.String r3 = "TbsInstaller"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0542 }
            r4.<init>()     // Catch:{ Exception -> 0x0542 }
            java.lang.String r5 = "TbsInstaller-copyTbsCoreInThread return have same version is "
            r4.append(r5)     // Catch:{ Exception -> 0x0542 }
            r4.append(r13)     // Catch:{ Exception -> 0x0542 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x0542 }
            com.tencent.smtt.utils.TbsLog.i(r3, r4)     // Catch:{ Exception -> 0x0542 }
            java.util.concurrent.locks.ReentrantLock r2 = i
            r2.unlock()
            java.util.concurrent.locks.Lock r2 = j
            r2.unlock()
            r21.b()
            return
        L_0x011f:
            com.tencent.smtt.sdk.ai r4 = com.tencent.smtt.sdk.ai.a((android.content.Context) r23)     // Catch:{ Exception -> 0x0542 }
            int r4 = r4.b()     // Catch:{ Exception -> 0x0542 }
            if (r4 <= 0) goto L_0x012b
            if (r3 > r4) goto L_0x012f
        L_0x012b:
            if (r8 <= 0) goto L_0x0132
            if (r3 <= r8) goto L_0x0132
        L_0x012f:
            r1.o(r2)     // Catch:{ Exception -> 0x0542 }
        L_0x0132:
            r4 = 3
            r8 = 1
            if (r10 != r4) goto L_0x014a
            if (r13 <= 0) goto L_0x014a
            if (r3 > r13) goto L_0x013f
            r4 = 88888888(0x54c5638, float:9.60787E-36)
            if (r3 != r4) goto L_0x014a
        L_0x013f:
            r1.o(r2)     // Catch:{ Exception -> 0x0542 }
            java.lang.String r4 = "TbsInstaller"
            java.lang.String r10 = "TbsInstaller-copyTbsCoreInThread -- update TBS....."
            com.tencent.smtt.utils.TbsLog.i(r4, r10, r8)     // Catch:{ Exception -> 0x0542 }
            r10 = -1
        L_0x014a:
            boolean r4 = com.tencent.smtt.utils.j.b((android.content.Context) r23)     // Catch:{ Exception -> 0x0542 }
            if (r4 != 0) goto L_0x0195
            long r3 = com.tencent.smtt.utils.v.a()     // Catch:{ Exception -> 0x0542 }
            com.tencent.smtt.sdk.TbsDownloadConfig r5 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r23)     // Catch:{ Exception -> 0x0542 }
            long r5 = r5.getDownloadMinFreeSpace()     // Catch:{ Exception -> 0x0542 }
            com.tencent.smtt.sdk.TbsDownloadConfig r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r22)     // Catch:{ Exception -> 0x0542 }
            r10 = -529(0xfffffffffffffdef, float:NaN)
            r8.setInstallInterruptCode(r10)     // Catch:{ Exception -> 0x0542 }
            com.tencent.smtt.sdk.TbsLogReport r8 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r23)     // Catch:{ Exception -> 0x0542 }
            r10 = 210(0xd2, float:2.94E-43)
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0542 }
            r11.<init>()     // Catch:{ Exception -> 0x0542 }
            java.lang.String r12 = "rom is not enough when copying tbs core! curAvailROM="
            r11.append(r12)     // Catch:{ Exception -> 0x0542 }
            r11.append(r3)     // Catch:{ Exception -> 0x0542 }
            java.lang.String r3 = ",minReqRom="
            r11.append(r3)     // Catch:{ Exception -> 0x0542 }
            r11.append(r5)     // Catch:{ Exception -> 0x0542 }
            java.lang.String r3 = r11.toString()     // Catch:{ Exception -> 0x0542 }
            r8.a((int) r10, (java.lang.String) r3)     // Catch:{ Exception -> 0x0542 }
            java.util.concurrent.locks.ReentrantLock r2 = i
            r2.unlock()
            java.util.concurrent.locks.Lock r2 = j
            r2.unlock()
            r21.b()
            return
        L_0x0195:
            if (r10 <= 0) goto L_0x01da
            boolean r4 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r23)     // Catch:{ Exception -> 0x0542 }
            if (r4 != 0) goto L_0x01b2
            com.tencent.smtt.sdk.TbsDownloadConfig r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r23)     // Catch:{ Exception -> 0x0542 }
            android.content.SharedPreferences r4 = r4.mPreferences     // Catch:{ Exception -> 0x0542 }
            java.lang.String r11 = "tbs_downloaddecouplecore"
            int r4 = r4.getInt(r11, r7)     // Catch:{ Exception -> 0x0542 }
            if (r4 != r8) goto L_0x01b2
            int r4 = r1.h(r2)     // Catch:{ Exception -> 0x0542 }
            if (r3 == r4) goto L_0x01b2
            goto L_0x01da
        L_0x01b2:
            java.lang.String r3 = "TbsInstaller"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0542 }
            r4.<init>()     // Catch:{ Exception -> 0x0542 }
            java.lang.String r5 = "TbsInstaller-copyTbsCoreInThread return have copied is "
            r4.append(r5)     // Catch:{ Exception -> 0x0542 }
            int r5 = r1.h(r2)     // Catch:{ Exception -> 0x0542 }
            r4.append(r5)     // Catch:{ Exception -> 0x0542 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x0542 }
            com.tencent.smtt.utils.TbsLog.i(r3, r4)     // Catch:{ Exception -> 0x0542 }
            java.util.concurrent.locks.ReentrantLock r2 = i
            r2.unlock()
            java.util.concurrent.locks.Lock r2 = j
            r2.unlock()
            r21.b()
            return
        L_0x01da:
            r4 = 2
            if (r10 != 0) goto L_0x0215
            com.tencent.smtt.sdk.ai r10 = com.tencent.smtt.sdk.ai.a((android.content.Context) r23)     // Catch:{ Exception -> 0x0542 }
            java.lang.String r11 = "copy_retry_num"
            int r10 = r10.c((java.lang.String) r11)     // Catch:{ Exception -> 0x0542 }
            if (r10 <= r4) goto L_0x020b
            com.tencent.smtt.sdk.TbsLogReport r3 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r23)     // Catch:{ Exception -> 0x0542 }
            r4 = 211(0xd3, float:2.96E-43)
            java.lang.String r5 = "exceed copy retry num!"
            r3.a((int) r4, (java.lang.String) r5)     // Catch:{ Exception -> 0x0542 }
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r22)     // Catch:{ Exception -> 0x0542 }
            r4 = -530(0xfffffffffffffdee, float:NaN)
            r3.setInstallInterruptCode(r4)     // Catch:{ Exception -> 0x0542 }
            java.util.concurrent.locks.ReentrantLock r2 = i
            r2.unlock()
            java.util.concurrent.locks.Lock r2 = j
            r2.unlock()
            r21.b()
            return
        L_0x020b:
            com.tencent.smtt.sdk.ai r11 = com.tencent.smtt.sdk.ai.a((android.content.Context) r23)     // Catch:{ Exception -> 0x0542 }
            java.lang.String r13 = "copy_retry_num"
            int r10 = r10 + r8
            r11.a((java.lang.String) r13, (int) r10)     // Catch:{ Exception -> 0x0542 }
        L_0x0215:
            java.io.File r10 = r21.q(r22)     // Catch:{ Exception -> 0x0542 }
            boolean r11 = com.tencent.smtt.sdk.TbsShareManager.isThirdPartyApp(r23)     // Catch:{ Exception -> 0x0542 }
            if (r11 != 0) goto L_0x0232
            com.tencent.smtt.sdk.TbsDownloadConfig r11 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r23)     // Catch:{ Exception -> 0x0542 }
            android.content.SharedPreferences r11 = r11.mPreferences     // Catch:{ Exception -> 0x0542 }
            java.lang.String r13 = "tbs_downloaddecouplecore"
            int r11 = r11.getInt(r13, r7)     // Catch:{ Exception -> 0x0542 }
            if (r11 != r8) goto L_0x0232
            java.io.File r11 = r1.p(r2)     // Catch:{ Exception -> 0x0542 }
            goto L_0x0236
        L_0x0232:
            java.io.File r11 = r1.v(r2)     // Catch:{ Exception -> 0x0542 }
        L_0x0236:
            r13 = 213(0xd5, float:2.98E-43)
            if (r10 == 0) goto L_0x0507
            if (r11 == 0) goto L_0x0507
            com.tencent.smtt.sdk.ai r14 = com.tencent.smtt.sdk.ai.a((android.content.Context) r23)     // Catch:{ Exception -> 0x0504 }
            r14.a((int) r3, (int) r7)     // Catch:{ Exception -> 0x0504 }
            com.tencent.smtt.utils.u r14 = new com.tencent.smtt.utils.u     // Catch:{ Exception -> 0x0504 }
            r14.<init>()     // Catch:{ Exception -> 0x0504 }
            r14.a(r10)     // Catch:{ Exception -> 0x0504 }
            long r17 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x0504 }
            com.tencent.smtt.sdk.TbsDownloadConfig r15 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r22)     // Catch:{ Exception -> 0x0504 }
            r9 = -551(0xfffffffffffffdd9, float:NaN)
            r15.setInstallInterruptCode(r9)     // Catch:{ Exception -> 0x0504 }
            java.io.FileFilter r9 = c     // Catch:{ Exception -> 0x0504 }
            boolean r9 = com.tencent.smtt.utils.j.a((java.io.File) r10, (java.io.File) r11, (java.io.FileFilter) r9)     // Catch:{ Exception -> 0x0504 }
            com.tencent.smtt.sdk.TbsDownloadConfig r15 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r23)     // Catch:{ Exception -> 0x0504 }
            android.content.SharedPreferences r15 = r15.mPreferences     // Catch:{ Exception -> 0x0504 }
            java.lang.String r4 = "tbs_downloaddecouplecore"
            int r4 = r15.getInt(r4, r7)     // Catch:{ Exception -> 0x0504 }
            if (r4 != r8) goto L_0x026f
            com.tencent.smtt.sdk.TbsShareManager.b(r23)     // Catch:{ Exception -> 0x0504 }
        L_0x026f:
            java.lang.String r4 = "TbsInstaller"
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0504 }
            r15.<init>()     // Catch:{ Exception -> 0x0504 }
            java.lang.String r7 = "TbsInstaller-copyTbsCoreInThread time="
            r15.append(r7)     // Catch:{ Exception -> 0x0504 }
            long r19 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x0504 }
            r7 = 0
            long r5 = r19 - r17
            r15.append(r5)     // Catch:{ Exception -> 0x0504 }
            java.lang.String r5 = r15.toString()     // Catch:{ Exception -> 0x0504 }
            com.tencent.smtt.utils.TbsLog.i(r4, r5)     // Catch:{ Exception -> 0x0504 }
            if (r9 == 0) goto L_0x04dc
            r14.b(r10)     // Catch:{ Exception -> 0x0504 }
            boolean r4 = r14.a()     // Catch:{ Exception -> 0x0504 }
            if (r4 != 0) goto L_0x02c1
            java.lang.String r3 = "TbsInstaller"
            java.lang.String r4 = "TbsInstaller-copyTbsCoreInThread copy-verify fail!"
            com.tencent.smtt.utils.TbsLog.i(r3, r4)     // Catch:{ Exception -> 0x0504 }
            com.tencent.smtt.utils.j.a((java.io.File) r11, (boolean) r8)     // Catch:{ Exception -> 0x0504 }
            com.tencent.smtt.sdk.TbsLogReport r3 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r23)     // Catch:{ Exception -> 0x0504 }
            java.lang.String r4 = "TbsCopy-Verify fail after copying tbs core!"
            r3.a((int) r13, (java.lang.String) r4)     // Catch:{ Exception -> 0x0504 }
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r22)     // Catch:{ Exception -> 0x0504 }
            r4 = -531(0xfffffffffffffded, float:NaN)
            r3.setInstallInterruptCode(r4)     // Catch:{ Exception -> 0x0504 }
            java.util.concurrent.locks.ReentrantLock r2 = i
            r2.unlock()
            java.util.concurrent.locks.Lock r2 = j
            r2.unlock()
            r21.b()
            return
        L_0x02c1:
            java.io.File r4 = new java.io.File     // Catch:{ Exception -> 0x0307, all -> 0x0301 }
            java.lang.String r5 = "1"
            r4.<init>(r11, r5)     // Catch:{ Exception -> 0x0307, all -> 0x0301 }
            java.util.Properties r5 = new java.util.Properties     // Catch:{ Exception -> 0x0307, all -> 0x0301 }
            r5.<init>()     // Catch:{ Exception -> 0x0307, all -> 0x0301 }
            boolean r6 = r4.exists()     // Catch:{ Exception -> 0x02fe, all -> 0x0301 }
            if (r6 == 0) goto L_0x02ef
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch:{ Exception -> 0x02fe, all -> 0x0301 }
            r6.<init>(r4)     // Catch:{ Exception -> 0x02fe, all -> 0x0301 }
            java.io.BufferedInputStream r4 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x02fe, all -> 0x0301 }
            r4.<init>(r6)     // Catch:{ Exception -> 0x02fe, all -> 0x0301 }
            r5.load(r4)     // Catch:{ Exception -> 0x02ea, all -> 0x02e4 }
            r16 = r4
            r4 = 1
            goto L_0x02f2
        L_0x02e4:
            r0 = move-exception
            r3 = r0
            r16 = r4
            goto L_0x04d0
        L_0x02ea:
            r0 = move-exception
            r16 = r4
            r4 = r0
            goto L_0x030c
        L_0x02ef:
            r4 = 0
            r16 = 0
        L_0x02f2:
            if (r16 == 0) goto L_0x031b
            r16.close()     // Catch:{ IOException -> 0x02f8 }
            goto L_0x031b
        L_0x02f8:
            r0 = move-exception
            r6 = r0
            r6.printStackTrace()     // Catch:{ Exception -> 0x0504 }
            goto L_0x031b
        L_0x02fe:
            r0 = move-exception
            r4 = r0
            goto L_0x030a
        L_0x0301:
            r0 = move-exception
            r3 = r0
            r16 = 0
            goto L_0x04d0
        L_0x0307:
            r0 = move-exception
            r4 = r0
            r5 = 0
        L_0x030a:
            r16 = 0
        L_0x030c:
            r4.printStackTrace()     // Catch:{ all -> 0x04ce }
            if (r16 == 0) goto L_0x031a
            r16.close()     // Catch:{ IOException -> 0x0315 }
            goto L_0x031a
        L_0x0315:
            r0 = move-exception
            r4 = r0
            r4.printStackTrace()     // Catch:{ Exception -> 0x0504 }
        L_0x031a:
            r4 = 1
        L_0x031b:
            if (r4 == 0) goto L_0x03d8
            java.io.File[] r6 = r11.listFiles()     // Catch:{ Exception -> 0x0504 }
            com.tencent.smtt.sdk.TbsDownloadConfig r7 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r22)     // Catch:{ Exception -> 0x0504 }
            r9 = -552(0xfffffffffffffdd8, float:NaN)
            r7.setInstallInterruptCode(r9)     // Catch:{ Exception -> 0x0504 }
            r7 = 0
        L_0x032b:
            int r9 = r6.length     // Catch:{ Exception -> 0x0504 }
            if (r7 >= r9) goto L_0x03d8
            r9 = r6[r7]     // Catch:{ Exception -> 0x0504 }
            java.lang.String r10 = "1"
            java.lang.String r14 = r9.getName()     // Catch:{ Exception -> 0x0504 }
            boolean r10 = r10.equals(r14)     // Catch:{ Exception -> 0x0504 }
            if (r10 != 0) goto L_0x03d4
            java.lang.String r10 = r9.getName()     // Catch:{ Exception -> 0x0504 }
            java.lang.String r14 = ".dex"
            boolean r10 = r10.endsWith(r14)     // Catch:{ Exception -> 0x0504 }
            if (r10 != 0) goto L_0x03d4
            java.lang.String r10 = "tbs.conf"
            java.lang.String r14 = r9.getName()     // Catch:{ Exception -> 0x0504 }
            boolean r10 = r10.equals(r14)     // Catch:{ Exception -> 0x0504 }
            if (r10 != 0) goto L_0x03d4
            boolean r10 = r9.isDirectory()     // Catch:{ Exception -> 0x0504 }
            if (r10 != 0) goto L_0x03d4
            java.lang.String r10 = r9.getName()     // Catch:{ Exception -> 0x0504 }
            java.lang.String r14 = ".prof"
            boolean r10 = r10.endsWith(r14)     // Catch:{ Exception -> 0x0504 }
            if (r10 == 0) goto L_0x0367
            goto L_0x03d4
        L_0x0367:
            java.lang.String r10 = com.tencent.smtt.utils.a.a(r9)     // Catch:{ Exception -> 0x0504 }
            java.lang.String r14 = r9.getName()     // Catch:{ Exception -> 0x0504 }
            java.lang.String r15 = ""
            java.lang.String r14 = r5.getProperty(r14, r15)     // Catch:{ Exception -> 0x0504 }
            java.lang.String r15 = ""
            boolean r15 = r14.equals(r15)     // Catch:{ Exception -> 0x0504 }
            if (r15 != 0) goto L_0x03a3
            boolean r15 = r10.equals(r14)     // Catch:{ Exception -> 0x0504 }
            if (r15 == 0) goto L_0x03a3
            java.lang.String r10 = "TbsInstaller"
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0504 }
            r14.<init>()     // Catch:{ Exception -> 0x0504 }
            java.lang.String r15 = "md5_check_success for ("
            r14.append(r15)     // Catch:{ Exception -> 0x0504 }
            java.lang.String r9 = r9.getName()     // Catch:{ Exception -> 0x0504 }
            r14.append(r9)     // Catch:{ Exception -> 0x0504 }
            java.lang.String r9 = ")"
            r14.append(r9)     // Catch:{ Exception -> 0x0504 }
            java.lang.String r9 = r14.toString()     // Catch:{ Exception -> 0x0504 }
            com.tencent.smtt.utils.TbsLog.i(r10, r9)     // Catch:{ Exception -> 0x0504 }
            goto L_0x03d4
        L_0x03a3:
            java.lang.String r5 = "TbsInstaller"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0504 }
            r6.<init>()     // Catch:{ Exception -> 0x0504 }
            java.lang.String r7 = "md5_check_failure for ("
            r6.append(r7)     // Catch:{ Exception -> 0x0504 }
            java.lang.String r7 = r9.getName()     // Catch:{ Exception -> 0x0504 }
            r6.append(r7)     // Catch:{ Exception -> 0x0504 }
            java.lang.String r7 = ")"
            r6.append(r7)     // Catch:{ Exception -> 0x0504 }
            java.lang.String r7 = " targetMd5:"
            r6.append(r7)     // Catch:{ Exception -> 0x0504 }
            r6.append(r14)     // Catch:{ Exception -> 0x0504 }
            java.lang.String r7 = ", realMd5:"
            r6.append(r7)     // Catch:{ Exception -> 0x0504 }
            r6.append(r10)     // Catch:{ Exception -> 0x0504 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x0504 }
            com.tencent.smtt.utils.TbsLog.e(r5, r6)     // Catch:{ Exception -> 0x0504 }
            r5 = 0
            goto L_0x03d9
        L_0x03d4:
            int r7 = r7 + 1
            goto L_0x032b
        L_0x03d8:
            r5 = 1
        L_0x03d9:
            java.lang.String r6 = "TbsInstaller"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0504 }
            r7.<init>()     // Catch:{ Exception -> 0x0504 }
            java.lang.String r9 = "copyTbsCoreInThread - md5_check_success:"
            r7.append(r9)     // Catch:{ Exception -> 0x0504 }
            r7.append(r5)     // Catch:{ Exception -> 0x0504 }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x0504 }
            com.tencent.smtt.utils.TbsLog.i(r6, r7)     // Catch:{ Exception -> 0x0504 }
            if (r4 == 0) goto L_0x041d
            if (r5 != 0) goto L_0x041d
            java.lang.String r3 = "TbsInstaller"
            java.lang.String r4 = "copyTbsCoreInThread - md5 incorrect -> delete destTmpDir!"
            com.tencent.smtt.utils.TbsLog.e(r3, r4)     // Catch:{ Exception -> 0x0504 }
            com.tencent.smtt.utils.j.a((java.io.File) r11, (boolean) r8)     // Catch:{ Exception -> 0x0504 }
            com.tencent.smtt.sdk.TbsLogReport r3 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r23)     // Catch:{ Exception -> 0x0504 }
            java.lang.String r4 = "TbsCopy-Verify md5 fail after copying tbs core!"
            r3.a((int) r13, (java.lang.String) r4)     // Catch:{ Exception -> 0x0504 }
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r22)     // Catch:{ Exception -> 0x0504 }
            r4 = -532(0xfffffffffffffdec, float:NaN)
            r3.setInstallInterruptCode(r4)     // Catch:{ Exception -> 0x0504 }
            java.util.concurrent.locks.ReentrantLock r2 = i
            r2.unlock()
            java.util.concurrent.locks.Lock r2 = j
            r2.unlock()
            r21.b()
            return
        L_0x041d:
            java.lang.String r4 = "TbsInstaller"
            java.lang.String r5 = "TbsInstaller-copyTbsCoreInThread success!"
            com.tencent.smtt.utils.TbsLog.i(r4, r5)     // Catch:{ Exception -> 0x0504 }
            r1.f(r2, r8)     // Catch:{ Exception -> 0x0504 }
            java.io.File r4 = com.tencent.smtt.sdk.ag.b((android.content.Context) r22)     // Catch:{ Exception -> 0x0504 }
            if (r4 == 0) goto L_0x0446
            boolean r5 = r4.exists()     // Catch:{ Exception -> 0x0504 }
            if (r5 == 0) goto L_0x0446
            java.io.File r5 = new java.io.File     // Catch:{ Exception -> 0x0504 }
            boolean r6 = com.tencent.smtt.sdk.TbsDownloader.getOverSea(r23)     // Catch:{ Exception -> 0x0504 }
            if (r6 == 0) goto L_0x043e
            java.lang.String r6 = "x5.oversea.tbs.org"
            goto L_0x0440
        L_0x043e:
            java.lang.String r6 = "x5.tbs.org"
        L_0x0440:
            r5.<init>(r4, r6)     // Catch:{ Exception -> 0x0504 }
            com.tencent.smtt.sdk.ag.a((java.io.File) r5, (android.content.Context) r2)     // Catch:{ Exception -> 0x0504 }
        L_0x0446:
            com.tencent.smtt.sdk.ai r4 = com.tencent.smtt.sdk.ai.a((android.content.Context) r23)     // Catch:{ Exception -> 0x0504 }
            r4.a((int) r3, (int) r8)     // Catch:{ Exception -> 0x0504 }
            boolean r4 = r1.k     // Catch:{ Exception -> 0x0504 }
            if (r4 == 0) goto L_0x045b
            com.tencent.smtt.sdk.TbsLogReport r4 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r23)     // Catch:{ Exception -> 0x0504 }
            java.lang.String r5 = "continueInstallWithout core success"
        L_0x0457:
            r4.a((int) r12, (java.lang.String) r5)     // Catch:{ Exception -> 0x0504 }
            goto L_0x0462
        L_0x045b:
            com.tencent.smtt.sdk.TbsLogReport r4 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r23)     // Catch:{ Exception -> 0x0504 }
            java.lang.String r5 = "success"
            goto L_0x0457
        L_0x0462:
            com.tencent.smtt.sdk.TbsDownloadConfig r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r22)     // Catch:{ Exception -> 0x0504 }
            r5 = -533(0xfffffffffffffdeb, float:NaN)
            r4.setInstallInterruptCode(r5)     // Catch:{ Exception -> 0x0504 }
            java.lang.String r4 = "TbsInstaller"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0504 }
            r5.<init>()     // Catch:{ Exception -> 0x0504 }
            java.lang.String r6 = "TbsInstaller-copyTbsCoreInThread success -- version:"
            r5.append(r6)     // Catch:{ Exception -> 0x0504 }
            r5.append(r3)     // Catch:{ Exception -> 0x0504 }
            java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x0504 }
            com.tencent.smtt.utils.TbsLog.i(r4, r5)     // Catch:{ Exception -> 0x0504 }
            int r4 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x0504 }
            r5 = 11
            if (r4 < r5) goto L_0x0490
            java.lang.String r4 = "tbs_preloadx5_check_cfg_file"
            r5 = 4
            android.content.SharedPreferences r4 = r2.getSharedPreferences(r4, r5)     // Catch:{ Exception -> 0x0504 }
            r5 = 0
            goto L_0x0497
        L_0x0490:
            java.lang.String r4 = "tbs_preloadx5_check_cfg_file"
            r5 = 0
            android.content.SharedPreferences r4 = r2.getSharedPreferences(r4, r5)     // Catch:{ Exception -> 0x0504 }
        L_0x0497:
            android.content.SharedPreferences$Editor r4 = r4.edit()     // Catch:{ Throwable -> 0x04ae }
            java.lang.String r6 = "tbs_preload_x5_counter"
            r4.putInt(r6, r5)     // Catch:{ Throwable -> 0x04ae }
            java.lang.String r6 = "tbs_preload_x5_recorder"
            r4.putInt(r6, r5)     // Catch:{ Throwable -> 0x04ae }
            java.lang.String r5 = "tbs_preload_x5_version"
            r4.putInt(r5, r3)     // Catch:{ Throwable -> 0x04ae }
            r4.commit()     // Catch:{ Throwable -> 0x04ae }
            goto L_0x04ca
        L_0x04ae:
            r0 = move-exception
            r3 = r0
            java.lang.String r4 = "TbsInstaller"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0504 }
            r5.<init>()     // Catch:{ Exception -> 0x0504 }
            java.lang.String r6 = "Init tbs_preload_x5_counter#2 exception:"
            r5.append(r6)     // Catch:{ Exception -> 0x0504 }
            java.lang.String r3 = android.util.Log.getStackTraceString(r3)     // Catch:{ Exception -> 0x0504 }
            r5.append(r3)     // Catch:{ Exception -> 0x0504 }
            java.lang.String r3 = r5.toString()     // Catch:{ Exception -> 0x0504 }
            com.tencent.smtt.utils.TbsLog.e(r4, r3)     // Catch:{ Exception -> 0x0504 }
        L_0x04ca:
            com.tencent.smtt.utils.v.a(r23)     // Catch:{ Exception -> 0x0504 }
            goto L_0x0531
        L_0x04ce:
            r0 = move-exception
            r3 = r0
        L_0x04d0:
            if (r16 == 0) goto L_0x04db
            r16.close()     // Catch:{ IOException -> 0x04d6 }
            goto L_0x04db
        L_0x04d6:
            r0 = move-exception
            r4 = r0
            r4.printStackTrace()     // Catch:{ Exception -> 0x0504 }
        L_0x04db:
            throw r3     // Catch:{ Exception -> 0x0504 }
        L_0x04dc:
            java.lang.String r4 = "TbsInstaller"
            java.lang.String r5 = "TbsInstaller-copyTbsCoreInThread fail!"
            com.tencent.smtt.utils.TbsLog.i(r4, r5)     // Catch:{ Exception -> 0x0504 }
            com.tencent.smtt.sdk.ai r4 = com.tencent.smtt.sdk.ai.a((android.content.Context) r23)     // Catch:{ Exception -> 0x0504 }
            r5 = 2
            r4.a((int) r3, (int) r5)     // Catch:{ Exception -> 0x0504 }
            r3 = 0
            com.tencent.smtt.utils.j.a((java.io.File) r11, (boolean) r3)     // Catch:{ Exception -> 0x0504 }
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r22)     // Catch:{ Exception -> 0x0504 }
            r4 = -534(0xfffffffffffffdea, float:NaN)
            r3.setInstallInterruptCode(r4)     // Catch:{ Exception -> 0x0504 }
            com.tencent.smtt.sdk.TbsLogReport r3 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r23)     // Catch:{ Exception -> 0x0504 }
            r4 = 212(0xd4, float:2.97E-43)
            java.lang.String r5 = "copy fail!"
            r3.a((int) r4, (java.lang.String) r5)     // Catch:{ Exception -> 0x0504 }
            goto L_0x0531
        L_0x0504:
            r0 = move-exception
            r3 = r0
            goto L_0x0545
        L_0x0507:
            if (r10 != 0) goto L_0x051b
            com.tencent.smtt.sdk.TbsLogReport r3 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r23)     // Catch:{ Exception -> 0x0504 }
            java.lang.String r4 = "src-dir is null when copying tbs core!"
            r3.a((int) r13, (java.lang.String) r4)     // Catch:{ Exception -> 0x0504 }
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r22)     // Catch:{ Exception -> 0x0504 }
            r4 = -535(0xfffffffffffffde9, float:NaN)
            r3.setInstallInterruptCode(r4)     // Catch:{ Exception -> 0x0504 }
        L_0x051b:
            if (r11 != 0) goto L_0x0531
            com.tencent.smtt.sdk.TbsLogReport r3 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r23)     // Catch:{ Exception -> 0x0504 }
            r4 = 214(0xd6, float:3.0E-43)
            java.lang.String r5 = "dst-dir is null when copying tbs core!"
            r3.a((int) r4, (java.lang.String) r5)     // Catch:{ Exception -> 0x0504 }
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r22)     // Catch:{ Exception -> 0x0504 }
            r4 = -536(0xfffffffffffffde8, float:NaN)
            r3.setInstallInterruptCode(r4)     // Catch:{ Exception -> 0x0504 }
        L_0x0531:
            java.util.concurrent.locks.ReentrantLock r2 = i
            r2.unlock()
            java.util.concurrent.locks.Lock r2 = j
            r2.unlock()
            r21.b()
            goto L_0x05ab
        L_0x053f:
            r0 = move-exception
            r2 = r0
            goto L_0x0591
        L_0x0542:
            r0 = move-exception
            r3 = r0
            r11 = 0
        L_0x0545:
            com.tencent.smtt.sdk.TbsLogReport r4 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r23)     // Catch:{ all -> 0x053f }
            r5 = 215(0xd7, float:3.01E-43)
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x053f }
            r4.a((int) r5, (java.lang.String) r3)     // Catch:{ all -> 0x053f }
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r22)     // Catch:{ all -> 0x053f }
            r4 = -537(0xfffffffffffffde7, float:NaN)
            r3.setInstallInterruptCode(r4)     // Catch:{ all -> 0x053f }
            r3 = 0
            com.tencent.smtt.utils.j.a((java.io.File) r11, (boolean) r3)     // Catch:{ Exception -> 0x0568 }
            com.tencent.smtt.sdk.ai r2 = com.tencent.smtt.sdk.ai.a((android.content.Context) r23)     // Catch:{ Exception -> 0x0568 }
            r4 = -1
            r2.a((int) r3, (int) r4)     // Catch:{ Exception -> 0x0568 }
            goto L_0x0531
        L_0x0568:
            r0 = move-exception
            r2 = r0
            java.lang.String r3 = "TbsInstaller"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x053f }
            r4.<init>()     // Catch:{ all -> 0x053f }
            java.lang.String r5 = "[TbsInstaller-copyTbsCoreInThread] delete dstTmpDir throws exception:"
            r4.append(r5)     // Catch:{ all -> 0x053f }
            java.lang.String r5 = r2.getMessage()     // Catch:{ all -> 0x053f }
            r4.append(r5)     // Catch:{ all -> 0x053f }
            java.lang.String r5 = ","
            r4.append(r5)     // Catch:{ all -> 0x053f }
            java.lang.Throwable r2 = r2.getCause()     // Catch:{ all -> 0x053f }
            r4.append(r2)     // Catch:{ all -> 0x053f }
            java.lang.String r2 = r4.toString()     // Catch:{ all -> 0x053f }
            com.tencent.smtt.utils.TbsLog.e(r3, r2)     // Catch:{ all -> 0x053f }
            goto L_0x0531
        L_0x0591:
            java.util.concurrent.locks.ReentrantLock r3 = i
            r3.unlock()
            java.util.concurrent.locks.Lock r3 = j
            r3.unlock()
            r21.b()
            throw r2
        L_0x059f:
            r21.b()
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r22)
            r3 = -538(0xfffffffffffffde6, float:NaN)
            r2.setInstallInterruptCode(r3)
        L_0x05ab:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.am.a(android.content.Context, android.content.Context, int):void");
    }

    private boolean a(Context context, File file) {
        return a(context, file, false);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0178, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:?, code lost:
        com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r10).setInstallInterruptCode(-523);
        com.tencent.smtt.sdk.TbsLogReport.a(r10).a(206, (java.lang.Throwable) r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0192, code lost:
        r0 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:?, code lost:
        com.tencent.smtt.utils.j.b(r2);
        com.tencent.smtt.utils.TbsLog.e("TbsInstaller", "copyFileIfChanged -- delete tmpTbsCoreUnzipDir#2! exist:" + r2.exists());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x01b5, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x01b6, code lost:
        com.tencent.smtt.utils.TbsLog.e("TbsInstaller", "copyFileIfChanged -- delete tmpTbsCoreUnzipDir#2! exception:" + android.util.Log.getStackTraceString(r10));
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0178 A[ExcHandler: IOException (r11v1 'e' java.io.IOException A[CUSTOM_DECLARE]), Splitter:B:24:0x008f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean a(android.content.Context r10, java.io.File r11, boolean r12) {
        /*
            r9 = this;
            java.lang.String r0 = "TbsInstaller"
            java.lang.String r1 = "TbsInstaller-unzipTbs start"
            com.tencent.smtt.utils.TbsLog.i(r0, r1)
            boolean r0 = com.tencent.smtt.utils.j.c((java.io.File) r11)
            r1 = 0
            if (r0 != 0) goto L_0x0023
            com.tencent.smtt.sdk.TbsLogReport r11 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r10)
            r12 = 204(0xcc, float:2.86E-43)
            java.lang.String r0 = "apk is invalid!"
            r11.a((int) r12, (java.lang.String) r0)
            com.tencent.smtt.sdk.TbsDownloadConfig r10 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r10)
            r11 = -520(0xfffffffffffffdf8, float:NaN)
        L_0x001f:
            r10.setInstallInterruptCode(r11)
            return r1
        L_0x0023:
            r0 = 1
            java.lang.String r2 = "tbs"
            java.io.File r2 = r10.getDir(r2, r1)     // Catch:{ Throwable -> 0x0053 }
            if (r12 == 0) goto L_0x0034
            java.io.File r3 = new java.io.File     // Catch:{ Throwable -> 0x0053 }
            java.lang.String r4 = "core_share_decouple"
            r3.<init>(r2, r4)     // Catch:{ Throwable -> 0x0053 }
            goto L_0x003b
        L_0x0034:
            java.io.File r3 = new java.io.File     // Catch:{ Throwable -> 0x0053 }
            java.lang.String r4 = "core_unzip_tmp"
            r3.<init>(r2, r4)     // Catch:{ Throwable -> 0x0053 }
        L_0x003b:
            boolean r2 = r3.exists()     // Catch:{ Throwable -> 0x0053 }
            if (r2 == 0) goto L_0x006e
            com.tencent.smtt.sdk.TbsDownloadConfig r2 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r10)     // Catch:{ Throwable -> 0x0053 }
            android.content.SharedPreferences r2 = r2.mPreferences     // Catch:{ Throwable -> 0x0053 }
            java.lang.String r4 = "tbs_downloaddecouplecore"
            int r2 = r2.getInt(r4, r1)     // Catch:{ Throwable -> 0x0053 }
            if (r2 == r0) goto L_0x006e
            com.tencent.smtt.utils.j.b((java.io.File) r3)     // Catch:{ Throwable -> 0x0053 }
            goto L_0x006e
        L_0x0053:
            r2 = move-exception
            java.lang.String r3 = "TbsInstaller"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "TbsInstaller-unzipTbs -- delete unzip folder if exists exception"
            r4.append(r5)
            java.lang.String r2 = android.util.Log.getStackTraceString(r2)
            r4.append(r2)
            java.lang.String r2 = r4.toString()
            com.tencent.smtt.utils.TbsLog.e(r3, r2)
        L_0x006e:
            if (r12 == 0) goto L_0x0075
            java.io.File r2 = r9.u(r10)
            goto L_0x0079
        L_0x0075:
            java.io.File r2 = r9.t(r10)
        L_0x0079:
            if (r2 != 0) goto L_0x008d
            com.tencent.smtt.sdk.TbsLogReport r11 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r10)
            r12 = 205(0xcd, float:2.87E-43)
            java.lang.String r0 = "tmp unzip dir is null!"
            r11.a((int) r12, (java.lang.String) r0)
            com.tencent.smtt.sdk.TbsDownloadConfig r10 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r10)
            r11 = -521(0xfffffffffffffdf7, float:NaN)
            goto L_0x001f
        L_0x008d:
            r3 = -523(0xfffffffffffffdf5, float:NaN)
            com.tencent.smtt.utils.j.a((java.io.File) r2)     // Catch:{ IOException -> 0x0178, Exception -> 0x0118 }
            if (r12 == 0) goto L_0x0097
            com.tencent.smtt.utils.j.a((java.io.File) r2, (boolean) r0)     // Catch:{ IOException -> 0x0178, Exception -> 0x0118 }
        L_0x0097:
            boolean r11 = com.tencent.smtt.utils.j.a((java.io.File) r11, (java.io.File) r2)     // Catch:{ IOException -> 0x0178, Exception -> 0x0118 }
            if (r11 == 0) goto L_0x00a1
            boolean r11 = r9.a((java.io.File) r2, (android.content.Context) r10)     // Catch:{ IOException -> 0x0178, Exception -> 0x0118 }
        L_0x00a1:
            if (r12 == 0) goto L_0x00d2
            java.lang.String[] r4 = r2.list()     // Catch:{ IOException -> 0x0178, Exception -> 0x0118 }
            r5 = 0
        L_0x00a8:
            int r6 = r4.length     // Catch:{ IOException -> 0x0178, Exception -> 0x0118 }
            if (r5 >= r6) goto L_0x00c4
            java.io.File r6 = new java.io.File     // Catch:{ IOException -> 0x0178, Exception -> 0x0118 }
            r7 = r4[r5]     // Catch:{ IOException -> 0x0178, Exception -> 0x0118 }
            r6.<init>(r2, r7)     // Catch:{ IOException -> 0x0178, Exception -> 0x0118 }
            java.lang.String r7 = r6.getName()     // Catch:{ IOException -> 0x0178, Exception -> 0x0118 }
            java.lang.String r8 = ".dex"
            boolean r7 = r7.endsWith(r8)     // Catch:{ IOException -> 0x0178, Exception -> 0x0118 }
            if (r7 == 0) goto L_0x00c1
            r6.delete()     // Catch:{ IOException -> 0x0178, Exception -> 0x0118 }
        L_0x00c1:
            int r5 = r5 + 1
            goto L_0x00a8
        L_0x00c4:
            java.io.File r4 = s(r10)     // Catch:{ Exception -> 0x00d2, IOException -> 0x0178 }
            java.io.File r5 = new java.io.File     // Catch:{ Exception -> 0x00d2, IOException -> 0x0178 }
            java.lang.String r6 = "x5.tbs"
            r5.<init>(r4, r6)     // Catch:{ Exception -> 0x00d2, IOException -> 0x0178 }
            r5.delete()     // Catch:{ Exception -> 0x00d2, IOException -> 0x0178 }
        L_0x00d2:
            if (r11 != 0) goto L_0x00fb
            com.tencent.smtt.utils.j.b((java.io.File) r2)     // Catch:{ IOException -> 0x0178, Exception -> 0x0118 }
            com.tencent.smtt.sdk.TbsDownloadConfig r12 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r10)     // Catch:{ IOException -> 0x0178, Exception -> 0x0118 }
            r4 = -522(0xfffffffffffffdf6, float:NaN)
            r12.setInstallInterruptCode(r4)     // Catch:{ IOException -> 0x0178, Exception -> 0x0118 }
            java.lang.String r12 = "TbsInstaller"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0178, Exception -> 0x0118 }
            r4.<init>()     // Catch:{ IOException -> 0x0178, Exception -> 0x0118 }
            java.lang.String r5 = "copyFileIfChanged -- delete tmpTbsCoreUnzipDir#1! exist:"
            r4.append(r5)     // Catch:{ IOException -> 0x0178, Exception -> 0x0118 }
            boolean r5 = r2.exists()     // Catch:{ IOException -> 0x0178, Exception -> 0x0118 }
            r4.append(r5)     // Catch:{ IOException -> 0x0178, Exception -> 0x0118 }
            java.lang.String r4 = r4.toString()     // Catch:{ IOException -> 0x0178, Exception -> 0x0118 }
            com.tencent.smtt.utils.TbsLog.e(r12, r4)     // Catch:{ IOException -> 0x0178, Exception -> 0x0118 }
            goto L_0x010d
        L_0x00fb:
            r9.f(r10, r0)     // Catch:{ IOException -> 0x0178, Exception -> 0x0118 }
            if (r12 == 0) goto L_0x010d
            java.io.File r12 = r9.p(r10)     // Catch:{ IOException -> 0x0178, Exception -> 0x0118 }
            com.tencent.smtt.utils.j.a((java.io.File) r12, (boolean) r0)     // Catch:{ IOException -> 0x0178, Exception -> 0x0118 }
            r2.renameTo(r12)     // Catch:{ IOException -> 0x0178, Exception -> 0x0118 }
            com.tencent.smtt.sdk.TbsShareManager.b(r10)     // Catch:{ IOException -> 0x0178, Exception -> 0x0118 }
        L_0x010d:
            java.lang.String r10 = "TbsInstaller"
            java.lang.String r12 = "TbsInstaller-unzipTbs done"
            com.tencent.smtt.utils.TbsLog.i(r10, r12)
            return r11
        L_0x0115:
            r10 = move-exception
            goto L_0x01d8
        L_0x0118:
            r11 = move-exception
            com.tencent.smtt.sdk.TbsDownloadConfig r12 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r10)     // Catch:{ all -> 0x0115 }
            r12.setInstallInterruptCode(r3)     // Catch:{ all -> 0x0115 }
            com.tencent.smtt.sdk.TbsLogReport r10 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r10)     // Catch:{ all -> 0x0115 }
            r12 = 207(0xcf, float:2.9E-43)
            r10.a((int) r12, (java.lang.Throwable) r11)     // Catch:{ all -> 0x0115 }
            if (r2 == 0) goto L_0x0132
            boolean r10 = r2.exists()     // Catch:{ all -> 0x0115 }
            if (r10 == 0) goto L_0x0132
            goto L_0x0133
        L_0x0132:
            r0 = 0
        L_0x0133:
            if (r0 == 0) goto L_0x0170
            if (r2 == 0) goto L_0x0170
            com.tencent.smtt.utils.j.b((java.io.File) r2)     // Catch:{ Throwable -> 0x0155 }
            java.lang.String r10 = "TbsInstaller"
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0155 }
            r11.<init>()     // Catch:{ Throwable -> 0x0155 }
            java.lang.String r12 = "copyFileIfChanged -- delete tmpTbsCoreUnzipDir#2! exist:"
            r11.append(r12)     // Catch:{ Throwable -> 0x0155 }
            boolean r12 = r2.exists()     // Catch:{ Throwable -> 0x0155 }
            r11.append(r12)     // Catch:{ Throwable -> 0x0155 }
            java.lang.String r11 = r11.toString()     // Catch:{ Throwable -> 0x0155 }
            com.tencent.smtt.utils.TbsLog.e(r10, r11)     // Catch:{ Throwable -> 0x0155 }
            goto L_0x0170
        L_0x0155:
            r10 = move-exception
            java.lang.String r11 = "TbsInstaller"
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r0 = "copyFileIfChanged -- delete tmpTbsCoreUnzipDir#2! exception:"
            r12.append(r0)
            java.lang.String r10 = android.util.Log.getStackTraceString(r10)
            r12.append(r10)
            java.lang.String r10 = r12.toString()
            com.tencent.smtt.utils.TbsLog.e(r11, r10)
        L_0x0170:
            java.lang.String r10 = "TbsInstaller"
            java.lang.String r11 = "TbsInstaller-unzipTbs done"
            com.tencent.smtt.utils.TbsLog.i(r10, r11)
            return r1
        L_0x0178:
            r11 = move-exception
            com.tencent.smtt.sdk.TbsDownloadConfig r12 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r10)     // Catch:{ all -> 0x0115 }
            r12.setInstallInterruptCode(r3)     // Catch:{ all -> 0x0115 }
            com.tencent.smtt.sdk.TbsLogReport r10 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r10)     // Catch:{ all -> 0x0115 }
            r12 = 206(0xce, float:2.89E-43)
            r10.a((int) r12, (java.lang.Throwable) r11)     // Catch:{ all -> 0x0115 }
            if (r2 == 0) goto L_0x0192
            boolean r10 = r2.exists()     // Catch:{ all -> 0x0115 }
            if (r10 == 0) goto L_0x0192
            goto L_0x0193
        L_0x0192:
            r0 = 0
        L_0x0193:
            if (r0 == 0) goto L_0x01d0
            if (r2 == 0) goto L_0x01d0
            com.tencent.smtt.utils.j.b((java.io.File) r2)     // Catch:{ Throwable -> 0x01b5 }
            java.lang.String r10 = "TbsInstaller"
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x01b5 }
            r11.<init>()     // Catch:{ Throwable -> 0x01b5 }
            java.lang.String r12 = "copyFileIfChanged -- delete tmpTbsCoreUnzipDir#2! exist:"
            r11.append(r12)     // Catch:{ Throwable -> 0x01b5 }
            boolean r12 = r2.exists()     // Catch:{ Throwable -> 0x01b5 }
            r11.append(r12)     // Catch:{ Throwable -> 0x01b5 }
            java.lang.String r11 = r11.toString()     // Catch:{ Throwable -> 0x01b5 }
            com.tencent.smtt.utils.TbsLog.e(r10, r11)     // Catch:{ Throwable -> 0x01b5 }
            goto L_0x01d0
        L_0x01b5:
            r10 = move-exception
            java.lang.String r11 = "TbsInstaller"
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r0 = "copyFileIfChanged -- delete tmpTbsCoreUnzipDir#2! exception:"
            r12.append(r0)
            java.lang.String r10 = android.util.Log.getStackTraceString(r10)
            r12.append(r10)
            java.lang.String r10 = r12.toString()
            com.tencent.smtt.utils.TbsLog.e(r11, r10)
        L_0x01d0:
            java.lang.String r10 = "TbsInstaller"
            java.lang.String r11 = "TbsInstaller-unzipTbs done"
            com.tencent.smtt.utils.TbsLog.i(r10, r11)
            return r1
        L_0x01d8:
            java.lang.String r11 = "TbsInstaller"
            java.lang.String r12 = "TbsInstaller-unzipTbs done"
            com.tencent.smtt.utils.TbsLog.i(r11, r12)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.am.a(android.content.Context, java.io.File, boolean):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0064 A[SYNTHETIC, Splitter:B:27:0x0064] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0085  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0150 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0164 A[SYNTHETIC, Splitter:B:63:0x0164] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean a(java.io.File r10, android.content.Context r11) {
        /*
            r9 = this;
            java.lang.String r0 = "TbsInstaller"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "finalCheckForTbsCoreValidity - "
            r1.append(r2)
            r1.append(r10)
            java.lang.String r2 = ", "
            r1.append(r2)
            r1.append(r11)
            java.lang.String r11 = r1.toString()
            com.tencent.smtt.utils.TbsLog.i(r0, r11)
            r11 = 0
            r0 = 0
            r1 = 1
            java.io.File r2 = new java.io.File     // Catch:{ Exception -> 0x005d }
            java.lang.String r3 = "1"
            r2.<init>(r10, r3)     // Catch:{ Exception -> 0x005d }
            java.util.Properties r3 = new java.util.Properties     // Catch:{ Exception -> 0x005d }
            r3.<init>()     // Catch:{ Exception -> 0x005d }
            boolean r4 = r2.exists()     // Catch:{ Exception -> 0x0058 }
            if (r4 == 0) goto L_0x004c
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0058 }
            r4.<init>(r2)     // Catch:{ Exception -> 0x0058 }
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x0058 }
            r2.<init>(r4)     // Catch:{ Exception -> 0x0058 }
            r3.load(r2)     // Catch:{ Exception -> 0x0047, all -> 0x0043 }
            r11 = r2
            r2 = 1
            goto L_0x004d
        L_0x0043:
            r10 = move-exception
            r11 = r2
            goto L_0x0162
        L_0x0047:
            r11 = move-exception
            r8 = r2
            r2 = r11
            r11 = r8
            goto L_0x005f
        L_0x004c:
            r2 = 0
        L_0x004d:
            if (r11 == 0) goto L_0x006d
            r11.close()     // Catch:{ IOException -> 0x0053 }
            goto L_0x006d
        L_0x0053:
            r11 = move-exception
            r11.printStackTrace()
            goto L_0x006d
        L_0x0058:
            r2 = move-exception
            goto L_0x005f
        L_0x005a:
            r10 = move-exception
            goto L_0x0162
        L_0x005d:
            r2 = move-exception
            r3 = r11
        L_0x005f:
            r2.printStackTrace()     // Catch:{ all -> 0x005a }
            if (r11 == 0) goto L_0x006c
            r11.close()     // Catch:{ IOException -> 0x0068 }
            goto L_0x006c
        L_0x0068:
            r11 = move-exception
            r11.printStackTrace()
        L_0x006c:
            r2 = 1
        L_0x006d:
            java.lang.String r11 = "TbsInstaller"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "finalCheckForTbsCoreValidity - need_check:"
            r4.append(r5)
            r4.append(r2)
            java.lang.String r4 = r4.toString()
            com.tencent.smtt.utils.TbsLog.i(r11, r4)
            if (r2 == 0) goto L_0x0137
            java.io.File[] r10 = r10.listFiles()
            r11 = 0
        L_0x008a:
            int r4 = r10.length
            if (r11 >= r4) goto L_0x0137
            r4 = r10[r11]
            java.lang.String r5 = "1"
            java.lang.String r6 = r4.getName()
            boolean r5 = r5.equals(r6)
            if (r5 != 0) goto L_0x0133
            java.lang.String r5 = r4.getName()
            java.lang.String r6 = ".dex"
            boolean r5 = r5.endsWith(r6)
            if (r5 != 0) goto L_0x0133
            java.lang.String r5 = "tbs.conf"
            java.lang.String r6 = r4.getName()
            boolean r5 = r5.equals(r6)
            if (r5 != 0) goto L_0x0133
            boolean r5 = r4.isDirectory()
            if (r5 != 0) goto L_0x0133
            java.lang.String r5 = r4.getName()
            java.lang.String r6 = ".prof"
            boolean r5 = r5.endsWith(r6)
            if (r5 == 0) goto L_0x00c6
            goto L_0x0133
        L_0x00c6:
            java.lang.String r5 = com.tencent.smtt.utils.a.a(r4)
            java.lang.String r6 = r4.getName()
            java.lang.String r7 = ""
            java.lang.String r6 = r3.getProperty(r6, r7)
            java.lang.String r7 = ""
            boolean r7 = r6.equals(r7)
            if (r7 != 0) goto L_0x0102
            boolean r7 = r6.equals(r5)
            if (r7 == 0) goto L_0x0102
            java.lang.String r5 = "TbsInstaller"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "md5_check_success for ("
            r6.append(r7)
            java.lang.String r4 = r4.getName()
            r6.append(r4)
            java.lang.String r4 = ")"
            r6.append(r4)
            java.lang.String r4 = r6.toString()
            com.tencent.smtt.utils.TbsLog.i(r5, r4)
            goto L_0x0133
        L_0x0102:
            java.lang.String r10 = "TbsInstaller"
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r3 = "md5_check_failure for ("
            r11.append(r3)
            java.lang.String r3 = r4.getName()
            r11.append(r3)
            java.lang.String r3 = ")"
            r11.append(r3)
            java.lang.String r3 = " targetMd5:"
            r11.append(r3)
            r11.append(r6)
            java.lang.String r3 = ", realMd5:"
            r11.append(r3)
            r11.append(r5)
            java.lang.String r11 = r11.toString()
            com.tencent.smtt.utils.TbsLog.e(r10, r11)
            r10 = 0
            goto L_0x0138
        L_0x0133:
            int r11 = r11 + 1
            goto L_0x008a
        L_0x0137:
            r10 = 1
        L_0x0138:
            java.lang.String r11 = "TbsInstaller"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "finalCheckForTbsCoreValidity - md5_check_success:"
            r3.append(r4)
            r3.append(r10)
            java.lang.String r3 = r3.toString()
            com.tencent.smtt.utils.TbsLog.i(r11, r3)
            if (r2 == 0) goto L_0x015a
            if (r10 != 0) goto L_0x015a
            java.lang.String r10 = "TbsInstaller"
            java.lang.String r11 = "finalCheckForTbsCoreValidity - Verify failed after unzipping!"
            com.tencent.smtt.utils.TbsLog.e(r10, r11)
            return r0
        L_0x015a:
            java.lang.String r10 = "TbsInstaller"
            java.lang.String r11 = "finalCheckForTbsCoreValidity success!"
            com.tencent.smtt.utils.TbsLog.i(r10, r11)
            return r1
        L_0x0162:
            if (r11 == 0) goto L_0x016c
            r11.close()     // Catch:{ IOException -> 0x0168 }
            goto L_0x016c
        L_0x0168:
            r11 = move-exception
            r11.printStackTrace()
        L_0x016c:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.am.a(java.io.File, android.content.Context):boolean");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x04a4  */
    @android.annotation.TargetApi(11)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(android.content.Context r17, java.lang.String r18, int r19) {
        /*
            r16 = this;
            r1 = r16
            r2 = r17
            r3 = r18
            r4 = r19
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)
            r5 = -501(0xfffffffffffffe0b, float:NaN)
            r0.setInstallInterruptCode(r5)
            boolean r0 = r16.c(r17)
            r5 = 1
            if (r0 == 0) goto L_0x0029
            java.lang.String r0 = "TbsInstaller"
            java.lang.String r3 = "isTbsLocalInstalled --> no installation!"
            com.tencent.smtt.utils.TbsLog.i(r0, r3, r5)
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)
            r2 = -502(0xfffffffffffffe0a, float:NaN)
            r0.setInstallInterruptCode(r2)
            return
        L_0x0029:
            java.lang.String r0 = "TbsInstaller"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "TbsInstaller-installTbsCoreInThread tbsApkPath="
            r6.append(r7)
            r6.append(r3)
            java.lang.String r6 = r6.toString()
            com.tencent.smtt.utils.TbsLog.i(r0, r6)
            java.lang.String r0 = "TbsInstaller"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "TbsInstaller-installTbsCoreInThread tbsCoreTargetVer="
            r6.append(r7)
            r6.append(r4)
            java.lang.String r6 = r6.toString()
            com.tencent.smtt.utils.TbsLog.i(r0, r6)
            java.lang.String r0 = "TbsInstaller"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "TbsInstaller-continueInstallTbsCore currentProcessName="
            r6.append(r7)
            android.content.pm.ApplicationInfo r7 = r17.getApplicationInfo()
            java.lang.String r7 = r7.processName
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            com.tencent.smtt.utils.TbsLog.i(r0, r6)
            java.lang.String r0 = "TbsInstaller"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "TbsInstaller-installTbsCoreInThread currentProcessId="
            r6.append(r7)
            int r7 = android.os.Process.myPid()
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            com.tencent.smtt.utils.TbsLog.i(r0, r6)
            java.lang.String r0 = "TbsInstaller"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "TbsInstaller-installTbsCoreInThread currentThreadName="
            r6.append(r7)
            java.lang.Thread r7 = java.lang.Thread.currentThread()
            java.lang.String r7 = r7.getName()
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            com.tencent.smtt.utils.TbsLog.i(r0, r6)
            int r0 = android.os.Build.VERSION.SDK_INT
            r6 = 4
            r7 = 11
            r8 = 0
            if (r0 < r7) goto L_0x00b8
            java.lang.String r0 = "tbs_preloadx5_check_cfg_file"
            android.content.SharedPreferences r0 = r2.getSharedPreferences(r0, r6)
            goto L_0x00be
        L_0x00b8:
            java.lang.String r0 = "tbs_preloadx5_check_cfg_file"
            android.content.SharedPreferences r0 = r2.getSharedPreferences(r0, r8)
        L_0x00be:
            java.lang.String r9 = "tbs_precheck_disable_version"
            r10 = -1
            int r0 = r0.getInt(r9, r10)
            if (r0 != r4) goto L_0x00ec
            java.lang.String r0 = "TbsInstaller"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r5 = "TbsInstaller-installTbsCoreInThread -- version:"
            r3.append(r5)
            r3.append(r4)
            java.lang.String r4 = " is disabled by preload_x5_check!"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            com.tencent.smtt.utils.TbsLog.e(r0, r3)
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)
            r2 = -503(0xfffffffffffffe09, float:NaN)
            r0.setInstallInterruptCode(r2)
            return
        L_0x00ec:
            boolean r0 = com.tencent.smtt.utils.j.b((android.content.Context) r17)
            if (r0 != 0) goto L_0x012a
            long r3 = com.tencent.smtt.utils.v.a()
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)
            long r5 = r0.getDownloadMinFreeSpace()
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)
            r7 = -504(0xfffffffffffffe08, float:NaN)
            r0.setInstallInterruptCode(r7)
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r17)
            r2 = 210(0xd2, float:2.94E-43)
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "rom is not enough when installing tbs core! curAvailROM="
            r7.append(r8)
            r7.append(r3)
            java.lang.String r3 = ",minReqRom="
            r7.append(r3)
            r7.append(r5)
            java.lang.String r3 = r7.toString()
            r0.a((int) r2, (java.lang.String) r3)
            return
        L_0x012a:
            boolean r0 = r16.w(r17)
            if (r0 != 0) goto L_0x013a
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)
            r2 = -505(0xfffffffffffffe07, float:NaN)
            r0.setInstallInterruptCode(r2)
            return
        L_0x013a:
            java.util.concurrent.locks.Lock r0 = j
            boolean r0 = r0.tryLock()
            java.lang.String r9 = "TbsInstaller"
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r12 = "TbsInstaller-installTbsCoreInThread locked ="
            r11.append(r12)
            r11.append(r0)
            java.lang.String r11 = r11.toString()
            com.tencent.smtt.utils.TbsLog.i(r9, r11)
            if (r0 == 0) goto L_0x064a
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)
            r9 = -507(0xfffffffffffffe05, float:NaN)
            r0.setInstallInterruptCode(r9)
            java.util.concurrent.locks.ReentrantLock r0 = i
            r0.lock()
            com.tencent.smtt.sdk.ai r0 = com.tencent.smtt.sdk.ai.a((android.content.Context) r17)     // Catch:{ all -> 0x063b }
            java.lang.String r9 = "copy_core_ver"
            int r0 = r0.c((java.lang.String) r9)     // Catch:{ all -> 0x063b }
            com.tencent.smtt.sdk.ai r9 = com.tencent.smtt.sdk.ai.a((android.content.Context) r17)     // Catch:{ all -> 0x063b }
            int r9 = r9.b()     // Catch:{ all -> 0x063b }
            java.lang.String r11 = "TbsInstaller"
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x063b }
            r12.<init>()     // Catch:{ all -> 0x063b }
            java.lang.String r13 = "TbsInstaller-installTbsCoreInThread tbsCoreCopyVer ="
            r12.append(r13)     // Catch:{ all -> 0x063b }
            r12.append(r0)     // Catch:{ all -> 0x063b }
            java.lang.String r12 = r12.toString()     // Catch:{ all -> 0x063b }
            com.tencent.smtt.utils.TbsLog.i(r11, r12)     // Catch:{ all -> 0x063b }
            java.lang.String r11 = "TbsInstaller"
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x063b }
            r12.<init>()     // Catch:{ all -> 0x063b }
            java.lang.String r13 = "TbsInstaller-installTbsCoreInThread tbsCoreInstallVer ="
            r12.append(r13)     // Catch:{ all -> 0x063b }
            r12.append(r9)     // Catch:{ all -> 0x063b }
            java.lang.String r12 = r12.toString()     // Catch:{ all -> 0x063b }
            com.tencent.smtt.utils.TbsLog.i(r11, r12)     // Catch:{ all -> 0x063b }
            java.lang.String r11 = "TbsInstaller"
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x063b }
            r12.<init>()     // Catch:{ all -> 0x063b }
            java.lang.String r13 = "TbsInstaller-installTbsCoreInThread tbsCoreTargetVer ="
            r12.append(r13)     // Catch:{ all -> 0x063b }
            r12.append(r4)     // Catch:{ all -> 0x063b }
            java.lang.String r12 = r12.toString()     // Catch:{ all -> 0x063b }
            com.tencent.smtt.utils.TbsLog.i(r11, r12)     // Catch:{ all -> 0x063b }
            if (r9 <= 0) goto L_0x01be
            if (r4 > r9) goto L_0x01c2
        L_0x01be:
            if (r0 <= 0) goto L_0x01c5
            if (r4 <= r0) goto L_0x01c5
        L_0x01c2:
            r16.o(r17)     // Catch:{ all -> 0x063b }
        L_0x01c5:
            com.tencent.smtt.sdk.ai r0 = com.tencent.smtt.sdk.ai.a((android.content.Context) r17)     // Catch:{ all -> 0x063b }
            int r0 = r0.c()     // Catch:{ all -> 0x063b }
            int r9 = r16.i(r17)     // Catch:{ all -> 0x063b }
            java.lang.String r11 = "TbsInstaller"
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x063b }
            r12.<init>()     // Catch:{ all -> 0x063b }
            java.lang.String r13 = "TbsInstaller-installTbsCoreInThread installStatus1="
            r12.append(r13)     // Catch:{ all -> 0x063b }
            r12.append(r0)     // Catch:{ all -> 0x063b }
            java.lang.String r12 = r12.toString()     // Catch:{ all -> 0x063b }
            com.tencent.smtt.utils.TbsLog.i(r11, r12)     // Catch:{ all -> 0x063b }
            java.lang.String r11 = "TbsInstaller"
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x063b }
            r12.<init>()     // Catch:{ all -> 0x063b }
            java.lang.String r13 = "TbsInstaller-installTbsCoreInThread tbsCoreInstalledVer="
            r12.append(r13)     // Catch:{ all -> 0x063b }
            r12.append(r9)     // Catch:{ all -> 0x063b }
            java.lang.String r12 = r12.toString()     // Catch:{ all -> 0x063b }
            com.tencent.smtt.utils.TbsLog.i(r11, r12)     // Catch:{ all -> 0x063b }
            r11 = 88888888(0x54c5638, float:9.60787E-36)
            r12 = 2
            if (r0 < 0) goto L_0x020e
            if (r0 >= r12) goto L_0x020e
            java.lang.String r9 = "TbsInstaller"
            java.lang.String r10 = "TbsInstaller-installTbsCoreInThread -- retry....."
            com.tencent.smtt.utils.TbsLog.i(r9, r10, r5)     // Catch:{ all -> 0x063b }
            r9 = 1
            goto L_0x0223
        L_0x020e:
            r13 = 3
            if (r0 != r13) goto L_0x0222
            if (r9 <= 0) goto L_0x0222
            if (r4 > r9) goto L_0x0217
            if (r4 != r11) goto L_0x0222
        L_0x0217:
            r16.o(r17)     // Catch:{ all -> 0x063b }
            java.lang.String r0 = "TbsInstaller"
            java.lang.String r9 = "TbsInstaller-installTbsCoreInThread -- update TBS....."
            com.tencent.smtt.utils.TbsLog.i(r0, r9, r5)     // Catch:{ all -> 0x063b }
            r0 = -1
        L_0x0222:
            r9 = 0
        L_0x0223:
            com.tencent.smtt.sdk.TbsDownloadConfig r10 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)     // Catch:{ all -> 0x063b }
            r13 = -508(0xfffffffffffffe04, float:NaN)
            r10.setInstallInterruptCode(r13)     // Catch:{ all -> 0x063b }
            java.lang.String r10 = "TbsInstaller"
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ all -> 0x063b }
            r13.<init>()     // Catch:{ all -> 0x063b }
            java.lang.String r14 = "TbsInstaller-installTbsCoreInThread installStatus2="
            r13.append(r14)     // Catch:{ all -> 0x063b }
            r13.append(r0)     // Catch:{ all -> 0x063b }
            java.lang.String r13 = r13.toString()     // Catch:{ all -> 0x063b }
            com.tencent.smtt.utils.TbsLog.i(r10, r13)     // Catch:{ all -> 0x063b }
            r10 = -511(0xfffffffffffffe01, float:NaN)
            r13 = 202(0xca, float:2.83E-43)
            r14 = 10
            if (r0 >= r5) goto L_0x04c1
            java.lang.String r15 = "TbsInstaller"
            java.lang.String r11 = "STEP 2/2 begin installation....."
            com.tencent.smtt.utils.TbsLog.i(r15, r11, r5)     // Catch:{ all -> 0x063b }
            com.tencent.smtt.sdk.TbsDownloadConfig r11 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)     // Catch:{ all -> 0x063b }
            r15 = -509(0xfffffffffffffe03, float:NaN)
            r11.setInstallInterruptCode(r15)     // Catch:{ all -> 0x063b }
            if (r9 == 0) goto L_0x0295
            com.tencent.smtt.sdk.ai r11 = com.tencent.smtt.sdk.ai.a((android.content.Context) r17)     // Catch:{ all -> 0x063b }
            java.lang.String r15 = "unzip_retry_num"
            int r11 = r11.c((java.lang.String) r15)     // Catch:{ all -> 0x063b }
            if (r11 <= r14) goto L_0x028d
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r17)     // Catch:{ all -> 0x063b }
            r3 = 201(0xc9, float:2.82E-43)
            java.lang.String r4 = "exceed unzip retry num!"
            r0.a((int) r3, (java.lang.String) r4)     // Catch:{ all -> 0x063b }
            r16.F(r17)     // Catch:{ all -> 0x063b }
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)     // Catch:{ all -> 0x063b }
            r2 = -510(0xfffffffffffffe02, float:NaN)
            r0.setInstallInterruptCode(r2)     // Catch:{ all -> 0x063b }
            java.util.concurrent.locks.ReentrantLock r0 = i
            r0.unlock()
            java.util.concurrent.locks.Lock r0 = j
            r0.unlock()
            r16.b()
            return
        L_0x028d:
            com.tencent.smtt.sdk.ai r15 = com.tencent.smtt.sdk.ai.a((android.content.Context) r17)     // Catch:{ all -> 0x063b }
            int r11 = r11 + r5
            r15.b((int) r11)     // Catch:{ all -> 0x063b }
        L_0x0295:
            if (r3 != 0) goto L_0x02c1
            com.tencent.smtt.sdk.ai r11 = com.tencent.smtt.sdk.ai.a((android.content.Context) r17)     // Catch:{ all -> 0x063b }
            java.lang.String r15 = "install_apk_path"
            java.lang.String r11 = r11.d((java.lang.String) r15)     // Catch:{ all -> 0x063b }
            if (r11 != 0) goto L_0x02c2
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r17)     // Catch:{ all -> 0x063b }
            java.lang.String r3 = "apk path is null!"
            r0.a((int) r13, (java.lang.String) r3)     // Catch:{ all -> 0x063b }
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)     // Catch:{ all -> 0x063b }
            r0.setInstallInterruptCode(r10)     // Catch:{ all -> 0x063b }
            java.util.concurrent.locks.ReentrantLock r0 = i
            r0.unlock()
            java.util.concurrent.locks.Lock r0 = j
            r0.unlock()
            r16.b()
            return
        L_0x02c1:
            r11 = r3
        L_0x02c2:
            java.lang.String r10 = "TbsInstaller"
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ all -> 0x063b }
            r13.<init>()     // Catch:{ all -> 0x063b }
            java.lang.String r15 = "TbsInstaller-installTbsCoreInThread apkPath ="
            r13.append(r15)     // Catch:{ all -> 0x063b }
            r13.append(r11)     // Catch:{ all -> 0x063b }
            java.lang.String r13 = r13.toString()     // Catch:{ all -> 0x063b }
            com.tencent.smtt.utils.TbsLog.i(r10, r13)     // Catch:{ all -> 0x063b }
            int r10 = r1.b((android.content.Context) r2, (java.lang.String) r11)     // Catch:{ all -> 0x063b }
            if (r10 != 0) goto L_0x0300
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)     // Catch:{ all -> 0x063b }
            r3 = -512(0xfffffffffffffe00, float:NaN)
            r0.setInstallInterruptCode(r3)     // Catch:{ all -> 0x063b }
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r17)     // Catch:{ all -> 0x063b }
            r2 = 203(0xcb, float:2.84E-43)
            java.lang.String r3 = "apk version is 0!"
            r0.a((int) r2, (java.lang.String) r3)     // Catch:{ all -> 0x063b }
            java.util.concurrent.locks.ReentrantLock r0 = i
            r0.unlock()
            java.util.concurrent.locks.Lock r0 = j
            r0.unlock()
            r16.b()
            return
        L_0x0300:
            com.tencent.smtt.sdk.ai r13 = com.tencent.smtt.sdk.ai.a((android.content.Context) r17)     // Catch:{ all -> 0x063b }
            java.lang.String r15 = "install_apk_path"
            r13.a((java.lang.String) r15, (java.lang.String) r11)     // Catch:{ all -> 0x063b }
            com.tencent.smtt.sdk.ai r13 = com.tencent.smtt.sdk.ai.a((android.content.Context) r17)     // Catch:{ all -> 0x063b }
            r13.b(r10, r8)     // Catch:{ all -> 0x063b }
            com.tencent.smtt.sdk.TbsDownloadConfig r13 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)     // Catch:{ all -> 0x063b }
            r15 = -548(0xfffffffffffffddc, float:NaN)
            r13.setInstallInterruptCode(r15)     // Catch:{ all -> 0x063b }
            com.tencent.smtt.sdk.TbsDownloadConfig r13 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)     // Catch:{ all -> 0x063b }
            android.content.SharedPreferences r13 = r13.mPreferences     // Catch:{ all -> 0x063b }
            java.lang.String r15 = "tbs_downloaddecouplecore"
            int r13 = r13.getInt(r15, r8)     // Catch:{ all -> 0x063b }
            r15 = 207(0xcf, float:2.9E-43)
            if (r13 != r5) goto L_0x034d
            java.io.File r13 = new java.io.File     // Catch:{ all -> 0x063b }
            r13.<init>(r11)     // Catch:{ all -> 0x063b }
            boolean r11 = r1.a((android.content.Context) r2, (java.io.File) r13, (boolean) r5)     // Catch:{ all -> 0x063b }
            if (r11 != 0) goto L_0x036f
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r17)     // Catch:{ all -> 0x063b }
            java.lang.String r2 = "unzipTbsApk failed"
            com.tencent.smtt.sdk.TbsLogReport$EventType r3 = com.tencent.smtt.sdk.TbsLogReport.EventType.TYPE_INSTALL_DECOUPLE     // Catch:{ all -> 0x063b }
            r0.a((int) r15, (java.lang.String) r2, (com.tencent.smtt.sdk.TbsLogReport.EventType) r3)     // Catch:{ all -> 0x063b }
            java.util.concurrent.locks.ReentrantLock r0 = i
            r0.unlock()
            java.util.concurrent.locks.Lock r0 = j
            r0.unlock()
            r16.b()
            return
        L_0x034d:
            java.io.File r13 = new java.io.File     // Catch:{ all -> 0x063b }
            r13.<init>(r11)     // Catch:{ all -> 0x063b }
            boolean r11 = r1.a((android.content.Context) r2, (java.io.File) r13)     // Catch:{ all -> 0x063b }
            if (r11 != 0) goto L_0x036f
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r17)     // Catch:{ all -> 0x063b }
            java.lang.String r2 = "unzipTbsApk failed"
            r0.a((int) r15, (java.lang.String) r2)     // Catch:{ all -> 0x063b }
            java.util.concurrent.locks.ReentrantLock r0 = i
            r0.unlock()
            java.util.concurrent.locks.Lock r0 = j
            r0.unlock()
            r16.b()
            return
        L_0x036f:
            if (r9 == 0) goto L_0x03d3
            com.tencent.smtt.sdk.ai r11 = com.tencent.smtt.sdk.ai.a((android.content.Context) r17)     // Catch:{ all -> 0x063b }
            java.lang.String r13 = "unlzma_status"
            int r11 = r11.b((java.lang.String) r13)     // Catch:{ all -> 0x063b }
            r13 = 5
            if (r11 <= r13) goto L_0x03cb
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r17)     // Catch:{ all -> 0x063b }
            r3 = 223(0xdf, float:3.12E-43)
            java.lang.String r4 = "exceed unlzma retry num!"
            r0.a((int) r3, (java.lang.String) r4)     // Catch:{ all -> 0x063b }
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)     // Catch:{ all -> 0x063b }
            r3 = -553(0xfffffffffffffdd7, float:NaN)
            r0.setInstallInterruptCode(r3)     // Catch:{ all -> 0x063b }
            r16.F(r17)     // Catch:{ all -> 0x063b }
            com.tencent.smtt.sdk.ag.c((android.content.Context) r17)     // Catch:{ all -> 0x063b }
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)     // Catch:{ all -> 0x063b }
            java.util.Map<java.lang.String, java.lang.Object> r0 = r0.f9091a     // Catch:{ all -> 0x063b }
            java.lang.String r3 = "tbs_needdownload"
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r5)     // Catch:{ all -> 0x063b }
            r0.put(r3, r4)     // Catch:{ all -> 0x063b }
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)     // Catch:{ all -> 0x063b }
            java.util.Map<java.lang.String, java.lang.Object> r0 = r0.f9091a     // Catch:{ all -> 0x063b }
            java.lang.String r3 = "request_full_package"
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r5)     // Catch:{ all -> 0x063b }
            r0.put(r3, r4)     // Catch:{ all -> 0x063b }
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)     // Catch:{ all -> 0x063b }
            r0.commit()     // Catch:{ all -> 0x063b }
            java.util.concurrent.locks.ReentrantLock r0 = i
            r0.unlock()
            java.util.concurrent.locks.Lock r0 = j
            r0.unlock()
            r16.b()
            return
        L_0x03cb:
            com.tencent.smtt.sdk.ai r13 = com.tencent.smtt.sdk.ai.a((android.content.Context) r17)     // Catch:{ all -> 0x063b }
            int r11 = r11 + r5
            r13.d((int) r11)     // Catch:{ all -> 0x063b }
        L_0x03d3:
            java.lang.String r11 = "TbsInstaller"
            java.lang.String r13 = "unlzma begin"
            com.tencent.smtt.utils.TbsLog.i(r11, r13)     // Catch:{ all -> 0x063b }
            com.tencent.smtt.sdk.TbsDownloadConfig r11 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance()     // Catch:{ all -> 0x063b }
            android.content.SharedPreferences r11 = r11.mPreferences     // Catch:{ all -> 0x063b }
            java.lang.String r13 = "tbs_responsecode"
            int r11 = r11.getInt(r13, r8)     // Catch:{ all -> 0x063b }
            int r13 = r16.i(r17)     // Catch:{ all -> 0x063b }
            if (r13 == 0) goto L_0x04b2
            java.lang.String r13 = "can_unlzma"
            r15 = 0
            java.lang.Object r13 = com.tencent.smtt.sdk.QbSdk.a((android.content.Context) r2, (java.lang.String) r13, (android.os.Bundle) r15)     // Catch:{ all -> 0x063b }
            if (r13 == 0) goto L_0x0400
            boolean r15 = r13 instanceof java.lang.Boolean     // Catch:{ all -> 0x063b }
            if (r15 == 0) goto L_0x0400
            java.lang.Boolean r13 = (java.lang.Boolean) r13     // Catch:{ all -> 0x063b }
            boolean r13 = r13.booleanValue()     // Catch:{ all -> 0x063b }
            goto L_0x0401
        L_0x0400:
            r13 = 0
        L_0x0401:
            if (r13 == 0) goto L_0x04b2
            android.os.Bundle r13 = new android.os.Bundle     // Catch:{ all -> 0x063b }
            r13.<init>()     // Catch:{ all -> 0x063b }
            java.lang.String r15 = "responseCode"
            r13.putInt(r15, r11)     // Catch:{ all -> 0x063b }
            com.tencent.smtt.sdk.TbsDownloadConfig r11 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)     // Catch:{ all -> 0x063b }
            android.content.SharedPreferences r11 = r11.mPreferences     // Catch:{ all -> 0x063b }
            java.lang.String r15 = "tbs_downloaddecouplecore"
            int r11 = r11.getInt(r15, r8)     // Catch:{ all -> 0x063b }
            if (r11 != r5) goto L_0x0429
            java.lang.String r11 = "unzip_temp_path"
            java.io.File r15 = r16.p(r17)     // Catch:{ all -> 0x063b }
            java.lang.String r15 = r15.getAbsolutePath()     // Catch:{ all -> 0x063b }
        L_0x0425:
            r13.putString(r11, r15)     // Catch:{ all -> 0x063b }
            goto L_0x0434
        L_0x0429:
            java.lang.String r11 = "unzip_temp_path"
            java.io.File r15 = r16.t(r17)     // Catch:{ all -> 0x063b }
            java.lang.String r15 = r15.getAbsolutePath()     // Catch:{ all -> 0x063b }
            goto L_0x0425
        L_0x0434:
            java.lang.String r11 = "unlzma"
            java.lang.Object r11 = com.tencent.smtt.sdk.QbSdk.a((android.content.Context) r2, (java.lang.String) r11, (android.os.Bundle) r13)     // Catch:{ all -> 0x063b }
            r13 = 222(0xde, float:3.11E-43)
            if (r11 != 0) goto L_0x044f
            java.lang.String r11 = "TbsInstaller"
            java.lang.String r15 = "unlzma return null"
            com.tencent.smtt.utils.TbsLog.i(r11, r15)     // Catch:{ all -> 0x063b }
            com.tencent.smtt.sdk.TbsLogReport r11 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r17)     // Catch:{ all -> 0x063b }
            java.lang.String r15 = "unlzma is null"
        L_0x044b:
            r11.a((int) r13, (java.lang.String) r15)     // Catch:{ all -> 0x063b }
            goto L_0x04a1
        L_0x044f:
            boolean r15 = r11 instanceof java.lang.Boolean     // Catch:{ all -> 0x063b }
            if (r15 == 0) goto L_0x0471
            java.lang.Boolean r11 = (java.lang.Boolean) r11     // Catch:{ all -> 0x063b }
            boolean r11 = r11.booleanValue()     // Catch:{ all -> 0x063b }
            if (r11 == 0) goto L_0x0463
            java.lang.String r11 = "TbsInstaller"
            java.lang.String r13 = "unlzma success"
            com.tencent.smtt.utils.TbsLog.i(r11, r13)     // Catch:{ all -> 0x063b }
            goto L_0x0475
        L_0x0463:
            java.lang.String r11 = "TbsInstaller"
            java.lang.String r15 = "unlzma return false"
            com.tencent.smtt.utils.TbsLog.i(r11, r15)     // Catch:{ all -> 0x063b }
            com.tencent.smtt.sdk.TbsLogReport r11 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r17)     // Catch:{ all -> 0x063b }
            java.lang.String r15 = "unlzma return false"
            goto L_0x044b
        L_0x0471:
            boolean r15 = r11 instanceof android.os.Bundle     // Catch:{ all -> 0x063b }
            if (r15 == 0) goto L_0x0477
        L_0x0475:
            r11 = 1
            goto L_0x04a2
        L_0x0477:
            boolean r15 = r11 instanceof java.lang.Throwable     // Catch:{ all -> 0x063b }
            if (r15 == 0) goto L_0x04a1
            java.lang.String r15 = "TbsInstaller"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x063b }
            r6.<init>()     // Catch:{ all -> 0x063b }
            java.lang.String r7 = "unlzma failure because Throwable"
            r6.append(r7)     // Catch:{ all -> 0x063b }
            r7 = r11
            java.lang.Throwable r7 = (java.lang.Throwable) r7     // Catch:{ all -> 0x063b }
            java.lang.String r7 = android.util.Log.getStackTraceString(r7)     // Catch:{ all -> 0x063b }
            r6.append(r7)     // Catch:{ all -> 0x063b }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x063b }
            com.tencent.smtt.utils.TbsLog.i(r15, r6)     // Catch:{ all -> 0x063b }
            com.tencent.smtt.sdk.TbsLogReport r6 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r17)     // Catch:{ all -> 0x063b }
            java.lang.Throwable r11 = (java.lang.Throwable) r11     // Catch:{ all -> 0x063b }
            r6.a((int) r13, (java.lang.Throwable) r11)     // Catch:{ all -> 0x063b }
        L_0x04a1:
            r11 = 0
        L_0x04a2:
            if (r11 != 0) goto L_0x04b2
            java.util.concurrent.locks.ReentrantLock r0 = i
            r0.unlock()
            java.util.concurrent.locks.Lock r0 = j
            r0.unlock()
            r16.b()
            return
        L_0x04b2:
            java.lang.String r6 = "TbsInstaller"
            java.lang.String r7 = "unlzma finished"
            com.tencent.smtt.utils.TbsLog.i(r6, r7)     // Catch:{ all -> 0x063b }
            com.tencent.smtt.sdk.ai r6 = com.tencent.smtt.sdk.ai.a((android.content.Context) r17)     // Catch:{ all -> 0x063b }
            r6.b(r10, r5)     // Catch:{ all -> 0x063b }
            goto L_0x0505
        L_0x04c1:
            com.tencent.smtt.sdk.TbsDownloadConfig r6 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)     // Catch:{ all -> 0x063b }
            android.content.SharedPreferences r6 = r6.mPreferences     // Catch:{ all -> 0x063b }
            java.lang.String r7 = "tbs_downloaddecouplecore"
            int r6 = r6.getInt(r7, r8)     // Catch:{ all -> 0x063b }
            if (r6 != r5) goto L_0x0504
            if (r3 != 0) goto L_0x04fb
            com.tencent.smtt.sdk.ai r6 = com.tencent.smtt.sdk.ai.a((android.content.Context) r17)     // Catch:{ all -> 0x063b }
            java.lang.String r7 = "install_apk_path"
            java.lang.String r6 = r6.d((java.lang.String) r7)     // Catch:{ all -> 0x063b }
            if (r6 != 0) goto L_0x04fc
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r17)     // Catch:{ all -> 0x063b }
            java.lang.String r3 = "apk path is null!"
            r0.a((int) r13, (java.lang.String) r3)     // Catch:{ all -> 0x063b }
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)     // Catch:{ all -> 0x063b }
            r0.setInstallInterruptCode(r10)     // Catch:{ all -> 0x063b }
            java.util.concurrent.locks.ReentrantLock r0 = i
            r0.unlock()
            java.util.concurrent.locks.Lock r0 = j
            r0.unlock()
            r16.b()
            return
        L_0x04fb:
            r6 = r3
        L_0x04fc:
            java.io.File r7 = new java.io.File     // Catch:{ all -> 0x063b }
            r7.<init>(r6)     // Catch:{ all -> 0x063b }
            r1.a((android.content.Context) r2, (java.io.File) r7, (boolean) r5)     // Catch:{ all -> 0x063b }
        L_0x0504:
            r10 = 0
        L_0x0505:
            r6 = 200(0xc8, float:2.8E-43)
            if (r0 >= r12) goto L_0x0629
            if (r9 == 0) goto L_0x0544
            com.tencent.smtt.sdk.ai r0 = com.tencent.smtt.sdk.ai.a((android.content.Context) r17)     // Catch:{ all -> 0x063b }
            java.lang.String r7 = "dexopt_retry_num"
            int r0 = r0.c((java.lang.String) r7)     // Catch:{ all -> 0x063b }
            if (r0 <= r14) goto L_0x053c
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r17)     // Catch:{ all -> 0x063b }
            r3 = 208(0xd0, float:2.91E-43)
            java.lang.String r4 = "exceed dexopt retry num!"
            r0.a((int) r3, (java.lang.String) r4)     // Catch:{ all -> 0x063b }
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)     // Catch:{ all -> 0x063b }
            r3 = -514(0xfffffffffffffdfe, float:NaN)
            r0.setInstallInterruptCode(r3)     // Catch:{ all -> 0x063b }
            r16.F(r17)     // Catch:{ all -> 0x063b }
            java.util.concurrent.locks.ReentrantLock r0 = i
            r0.unlock()
            java.util.concurrent.locks.Lock r0 = j
            r0.unlock()
            r16.b()
            return
        L_0x053c:
            com.tencent.smtt.sdk.ai r7 = com.tencent.smtt.sdk.ai.a((android.content.Context) r17)     // Catch:{ all -> 0x063b }
            int r0 = r0 + r5
            r7.a((int) r0)     // Catch:{ all -> 0x063b }
        L_0x0544:
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)     // Catch:{ all -> 0x063b }
            r7 = -549(0xfffffffffffffddb, float:NaN)
            r0.setInstallInterruptCode(r7)     // Catch:{ all -> 0x063b }
            boolean r0 = r1.d((android.content.Context) r2, (int) r8)     // Catch:{ all -> 0x063b }
            if (r0 != 0) goto L_0x056a
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)     // Catch:{ all -> 0x063b }
            r2 = -515(0xfffffffffffffdfd, float:NaN)
            r0.setInstallInterruptCode(r2)     // Catch:{ all -> 0x063b }
            java.util.concurrent.locks.ReentrantLock r0 = i
            r0.unlock()
            java.util.concurrent.locks.Lock r0 = j
            r0.unlock()
            r16.b()
            return
        L_0x056a:
            com.tencent.smtt.sdk.ai r0 = com.tencent.smtt.sdk.ai.a((android.content.Context) r17)     // Catch:{ all -> 0x063b }
            r0.b(r10, r12)     // Catch:{ all -> 0x063b }
            java.lang.String r0 = "TbsInstaller"
            java.lang.String r7 = "STEP 2/2 installation completed! you can restart!"
            com.tencent.smtt.utils.TbsLog.i(r0, r7, r5)     // Catch:{ all -> 0x063b }
            java.lang.String r0 = "TbsInstaller"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x063b }
            r7.<init>()     // Catch:{ all -> 0x063b }
            java.lang.String r9 = "STEP 2/2 installation completed! you can restart! version:"
            r7.append(r9)     // Catch:{ all -> 0x063b }
            r7.append(r4)     // Catch:{ all -> 0x063b }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x063b }
            com.tencent.smtt.utils.TbsLog.i(r0, r7)     // Catch:{ all -> 0x063b }
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)     // Catch:{ all -> 0x063b }
            r7 = -516(0xfffffffffffffdfc, float:NaN)
            r0.setInstallInterruptCode(r7)     // Catch:{ all -> 0x063b }
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x063b }
            r7 = 11
            if (r0 < r7) goto L_0x05a5
            java.lang.String r0 = "tbs_preloadx5_check_cfg_file"
            r7 = 4
            android.content.SharedPreferences r0 = r2.getSharedPreferences(r0, r7)     // Catch:{ all -> 0x063b }
            goto L_0x05ab
        L_0x05a5:
            java.lang.String r0 = "tbs_preloadx5_check_cfg_file"
            android.content.SharedPreferences r0 = r2.getSharedPreferences(r0, r8)     // Catch:{ all -> 0x063b }
        L_0x05ab:
            android.content.SharedPreferences$Editor r0 = r0.edit()     // Catch:{ Throwable -> 0x05ce }
            java.lang.String r7 = "tbs_preload_x5_counter"
            r0.putInt(r7, r8)     // Catch:{ Throwable -> 0x05ce }
            java.lang.String r7 = "tbs_preload_x5_recorder"
            r0.putInt(r7, r8)     // Catch:{ Throwable -> 0x05ce }
            java.lang.String r7 = "tbs_preload_x5_version"
            r0.putInt(r7, r4)     // Catch:{ Throwable -> 0x05ce }
            r0.commit()     // Catch:{ Throwable -> 0x05ce }
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)     // Catch:{ Throwable -> 0x05ce }
            r7 = -517(0xfffffffffffffdfb, float:NaN)
            r0.setInstallInterruptCode(r7)     // Catch:{ Throwable -> 0x05ce }
        L_0x05ca:
            r7 = 88888888(0x54c5638, float:9.60787E-36)
            goto L_0x05f3
        L_0x05ce:
            r0 = move-exception
            java.lang.String r7 = "TbsInstaller"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x063b }
            r8.<init>()     // Catch:{ all -> 0x063b }
            java.lang.String r9 = "Init tbs_preload_x5_counter#1 exception:"
            r8.append(r9)     // Catch:{ all -> 0x063b }
            java.lang.String r0 = android.util.Log.getStackTraceString(r0)     // Catch:{ all -> 0x063b }
            r8.append(r0)     // Catch:{ all -> 0x063b }
            java.lang.String r0 = r8.toString()     // Catch:{ all -> 0x063b }
            com.tencent.smtt.utils.TbsLog.e(r7, r0)     // Catch:{ all -> 0x063b }
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)     // Catch:{ all -> 0x063b }
            r7 = -518(0xfffffffffffffdfa, float:NaN)
            r0.setInstallInterruptCode(r7)     // Catch:{ all -> 0x063b }
            goto L_0x05ca
        L_0x05f3:
            if (r4 != r7) goto L_0x05f8
            r1.a((int) r4, (java.lang.String) r3, (android.content.Context) r2)     // Catch:{ all -> 0x063b }
        L_0x05f8:
            boolean r0 = r1.k     // Catch:{ all -> 0x063b }
            r3 = 221(0xdd, float:3.1E-43)
            if (r0 == 0) goto L_0x0615
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r17)     // Catch:{ all -> 0x063b }
            com.tencent.smtt.sdk.ai r2 = com.tencent.smtt.sdk.ai.a((android.content.Context) r17)     // Catch:{ all -> 0x063b }
            int r2 = r2.d()     // Catch:{ all -> 0x063b }
            if (r2 != r5) goto L_0x060d
            goto L_0x060f
        L_0x060d:
            r3 = 200(0xc8, float:2.8E-43)
        L_0x060f:
            java.lang.String r2 = "continueInstallWithout core success"
        L_0x0611:
            r0.a((int) r3, (java.lang.String) r2)     // Catch:{ all -> 0x063b }
            goto L_0x0630
        L_0x0615:
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r17)     // Catch:{ all -> 0x063b }
            com.tencent.smtt.sdk.ai r2 = com.tencent.smtt.sdk.ai.a((android.content.Context) r17)     // Catch:{ all -> 0x063b }
            int r2 = r2.d()     // Catch:{ all -> 0x063b }
            if (r2 != r5) goto L_0x0624
            goto L_0x0626
        L_0x0624:
            r3 = 200(0xc8, float:2.8E-43)
        L_0x0626:
            java.lang.String r2 = "success"
            goto L_0x0611
        L_0x0629:
            if (r0 != r12) goto L_0x0630
            com.tencent.smtt.sdk.TbsListener r0 = com.tencent.smtt.sdk.QbSdk.m     // Catch:{ all -> 0x063b }
            r0.onInstallFinish(r6)     // Catch:{ all -> 0x063b }
        L_0x0630:
            java.util.concurrent.locks.ReentrantLock r0 = i
            r0.unlock()
            java.util.concurrent.locks.Lock r0 = j
            r0.unlock()
            goto L_0x0653
        L_0x063b:
            r0 = move-exception
            java.util.concurrent.locks.ReentrantLock r2 = i
            r2.unlock()
            java.util.concurrent.locks.Lock r2 = j
            r2.unlock()
            r16.b()
            throw r0
        L_0x064a:
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r17)
            r2 = -519(0xfffffffffffffdf9, float:NaN)
            r0.setInstallInterruptCode(r2)
        L_0x0653:
            r16.b()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.am.b(android.content.Context, java.lang.String, int):void");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|(3:8|9|10)|11|12|(2:14|15)|20|16) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0028 */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x002f A[Catch:{ Exception -> 0x0060 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean b(android.content.Context r10, java.io.File r11) {
        /*
            r9 = this;
            r0 = 0
            com.tencent.smtt.sdk.au r1 = new com.tencent.smtt.sdk.au     // Catch:{ Exception -> 0x0060 }
            r1.<init>(r9)     // Catch:{ Exception -> 0x0060 }
            java.io.File[] r1 = r11.listFiles(r1)     // Catch:{ Exception -> 0x0060 }
            int r2 = r1.length     // Catch:{ Exception -> 0x0060 }
            int r3 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x0060 }
            r4 = 16
            if (r3 >= r4) goto L_0x0028
            java.lang.String r3 = r10.getPackageName()     // Catch:{ Exception -> 0x0060 }
            if (r3 == 0) goto L_0x0028
            java.lang.String r3 = r10.getPackageName()     // Catch:{ Exception -> 0x0060 }
            java.lang.String r4 = "com.tencent.tbs"
            boolean r3 = r3.equalsIgnoreCase(r4)     // Catch:{ Exception -> 0x0060 }
            if (r3 == 0) goto L_0x0028
            r3 = 5000(0x1388, double:2.4703E-320)
            java.lang.Thread.sleep(r3)     // Catch:{ Exception -> 0x0028 }
        L_0x0028:
            java.lang.ClassLoader r3 = r10.getClassLoader()     // Catch:{ Exception -> 0x0060 }
            r4 = 0
        L_0x002d:
            if (r4 >= r2) goto L_0x005e
            java.lang.String r5 = "TbsInstaller"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0060 }
            r6.<init>()     // Catch:{ Exception -> 0x0060 }
            java.lang.String r7 = "jarFile: "
            r6.append(r7)     // Catch:{ Exception -> 0x0060 }
            r7 = r1[r4]     // Catch:{ Exception -> 0x0060 }
            java.lang.String r7 = r7.getAbsolutePath()     // Catch:{ Exception -> 0x0060 }
            r6.append(r7)     // Catch:{ Exception -> 0x0060 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x0060 }
            com.tencent.smtt.utils.TbsLog.i(r5, r6)     // Catch:{ Exception -> 0x0060 }
            dalvik.system.DexClassLoader r5 = new dalvik.system.DexClassLoader     // Catch:{ Exception -> 0x0060 }
            r6 = r1[r4]     // Catch:{ Exception -> 0x0060 }
            java.lang.String r6 = r6.getAbsolutePath()     // Catch:{ Exception -> 0x0060 }
            java.lang.String r7 = r11.getAbsolutePath()     // Catch:{ Exception -> 0x0060 }
            r8 = 0
            r5.<init>(r6, r7, r8, r3)     // Catch:{ Exception -> 0x0060 }
            int r4 = r4 + 1
            goto L_0x002d
        L_0x005e:
            r10 = 1
            return r10
        L_0x0060:
            r11 = move-exception
            r11.printStackTrace()
            com.tencent.smtt.sdk.TbsLogReport r10 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r10)
            r1 = 209(0xd1, float:2.93E-43)
            java.lang.String r11 = r11.toString()
            r10.a((int) r1, (java.lang.String) r11)
            java.lang.String r10 = "TbsInstaller"
            java.lang.String r11 = "TbsInstaller-doTbsDexOpt done"
            com.tencent.smtt.utils.TbsLog.i(r10, r11)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.am.b(android.content.Context, java.io.File):boolean");
    }

    private boolean c(Context context, File file) {
        try {
            File file2 = new File(file, "tbs_sdk_extension_dex.jar");
            File file3 = new File(file, "tbs_sdk_extension_dex.dex");
            new DexClassLoader(file2.getAbsolutePath(), file.getAbsolutePath(), (String) null, context.getClassLoader());
            String a2 = g.a(context, file3.getAbsolutePath());
            if (TextUtils.isEmpty(a2)) {
                TbsLogReport.a(context).a((int) TbsListener.ErrorCode.DEXOAT_EXCEPTION, "can not find oat command");
                return false;
            }
            for (File file4 : file.listFiles(new av(this))) {
                String substring = file4.getName().substring(0, file4.getName().length() - 4);
                Runtime.getRuntime().exec("/system/bin/dex2oat " + a2.replaceAll("tbs_sdk_extension_dex", substring) + " --dex-location=" + a().q(context) + File.separator + substring + ".jar").waitFor();
            }
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            TbsLogReport.a(context).a((int) TbsListener.ErrorCode.DEXOAT_EXCEPTION, (Throwable) e2);
            return false;
        }
    }

    private synchronized boolean c(Context context, boolean z) {
        boolean z2;
        TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromCopy");
        z2 = false;
        try {
            if (!w(context)) {
                return false;
            }
            boolean tryLock = i.tryLock();
            TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromCopy Locked =" + tryLock);
            if (tryLock) {
                int b2 = ai.a(context).b("copy_status");
                int a2 = a(false, context);
                TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromCopy copyStatus =" + b2);
                TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromCopy tbsCoreInstalledVer =" + a2);
                if (b2 == 1) {
                    if (a2 == 0) {
                        TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromCopy tbsCoreInstalledVer = 0", true);
                    } else if (z) {
                        TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromCopy tbsCoreInstalledVer != 0", true);
                    }
                    B(context);
                    z2 = true;
                }
                i.unlock();
            }
            b();
        } catch (Throwable th) {
            TbsLogReport.a(context).a(215, th.toString());
            QbSdk.a(context, "TbsInstaller::enableTbsCoreFromCopy exception:" + Log.getStackTraceString(th));
        }
        return z2;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x005f A[Catch:{ Exception -> 0x00b0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0061 A[Catch:{ Exception -> 0x00b0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x007d A[Catch:{ Exception -> 0x00b0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0085 A[Catch:{ Exception -> 0x00b0 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean d(android.content.Context r7, int r8) {
        /*
            r6 = this;
            java.lang.String r0 = "TbsInstaller"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "TbsInstaller-doTbsDexOpt start - dirMode: "
            r1.append(r2)
            r1.append(r8)
            java.lang.String r1 = r1.toString()
            com.tencent.smtt.utils.TbsLog.i(r0, r1)
            r0 = 1
            r1 = 0
            switch(r8) {
                case 0: goto L_0x0029;
                case 1: goto L_0x0024;
                case 2: goto L_0x001f;
                default: goto L_0x001b;
            }
        L_0x001b:
            java.lang.String r2 = "TbsInstaller"
            goto L_0x009b
        L_0x001f:
            java.io.File r8 = r6.q(r7)     // Catch:{ Exception -> 0x00b0 }
            goto L_0x003c
        L_0x0024:
            java.io.File r8 = r6.v(r7)     // Catch:{ Exception -> 0x00b0 }
            goto L_0x003c
        L_0x0029:
            com.tencent.smtt.sdk.TbsDownloadConfig r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)     // Catch:{ Exception -> 0x00b0 }
            android.content.SharedPreferences r8 = r8.mPreferences     // Catch:{ Exception -> 0x00b0 }
            java.lang.String r2 = "tbs_downloaddecouplecore"
            int r8 = r8.getInt(r2, r1)     // Catch:{ Exception -> 0x00b0 }
            if (r8 != r0) goto L_0x0038
            return r0
        L_0x0038:
            java.io.File r8 = r6.t(r7)     // Catch:{ Exception -> 0x00b0 }
        L_0x003c:
            java.lang.String r2 = "java.vm.version"
            java.lang.String r2 = java.lang.System.getProperty(r2)     // Catch:{ Throwable -> 0x004e }
            if (r2 == 0) goto L_0x0058
            java.lang.String r3 = "2"
            boolean r2 = r2.startsWith(r3)     // Catch:{ Throwable -> 0x004e }
            if (r2 == 0) goto L_0x0058
            r2 = 1
            goto L_0x0059
        L_0x004e:
            r2 = move-exception
            com.tencent.smtt.sdk.TbsLogReport r3 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r7)     // Catch:{ Exception -> 0x00b0 }
            r4 = 226(0xe2, float:3.17E-43)
            r3.a((int) r4, (java.lang.Throwable) r2)     // Catch:{ Exception -> 0x00b0 }
        L_0x0058:
            r2 = 0
        L_0x0059:
            int r3 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x00b0 }
            r4 = 23
            if (r3 != r4) goto L_0x0061
            r3 = 1
            goto L_0x0062
        L_0x0061:
            r3 = 0
        L_0x0062:
            com.tencent.smtt.sdk.TbsDownloadConfig r4 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r7)     // Catch:{ Exception -> 0x00b0 }
            android.content.SharedPreferences r4 = r4.mPreferences     // Catch:{ Exception -> 0x00b0 }
            java.lang.String r5 = "tbs_stop_preoat"
            boolean r4 = r4.getBoolean(r5, r1)     // Catch:{ Exception -> 0x00b0 }
            if (r2 == 0) goto L_0x0075
            if (r3 == 0) goto L_0x0075
            if (r4 != 0) goto L_0x0075
            r1 = 1
        L_0x0075:
            if (r1 == 0) goto L_0x0085
            boolean r1 = r6.c((android.content.Context) r7, (java.io.File) r8)     // Catch:{ Exception -> 0x00b0 }
            if (r1 == 0) goto L_0x0085
            java.lang.String r8 = "TbsInstaller"
            java.lang.String r1 = "doTbsDexOpt -- doDexoatForArtVm"
            com.tencent.smtt.utils.TbsLog.i(r8, r1)     // Catch:{ Exception -> 0x00b0 }
            return r0
        L_0x0085:
            if (r2 == 0) goto L_0x008f
            java.lang.String r8 = "TbsInstaller"
            java.lang.String r1 = "doTbsDexOpt -- is ART mode, skip!"
            com.tencent.smtt.utils.TbsLog.i(r8, r1)     // Catch:{ Exception -> 0x00b0 }
            goto L_0x00c1
        L_0x008f:
            java.lang.String r1 = "TbsInstaller"
            java.lang.String r2 = "doTbsDexOpt -- doDexoptForDavlikVM"
            com.tencent.smtt.utils.TbsLog.i(r1, r2)     // Catch:{ Exception -> 0x00b0 }
            boolean r8 = r6.b((android.content.Context) r7, (java.io.File) r8)     // Catch:{ Exception -> 0x00b0 }
            return r8
        L_0x009b:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00b0 }
            r3.<init>()     // Catch:{ Exception -> 0x00b0 }
            java.lang.String r4 = "doDexoptOrDexoat mode error: "
            r3.append(r4)     // Catch:{ Exception -> 0x00b0 }
            r3.append(r8)     // Catch:{ Exception -> 0x00b0 }
            java.lang.String r8 = r3.toString()     // Catch:{ Exception -> 0x00b0 }
            com.tencent.smtt.utils.TbsLog.e(r2, r8)     // Catch:{ Exception -> 0x00b0 }
            return r1
        L_0x00b0:
            r8 = move-exception
            r8.printStackTrace()
            com.tencent.smtt.sdk.TbsLogReport r7 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r7)
            r1 = 209(0xd1, float:2.93E-43)
            java.lang.String r8 = r8.toString()
            r7.a((int) r1, (java.lang.String) r8)
        L_0x00c1:
            java.lang.String r7 = "TbsInstaller"
            java.lang.String r8 = "TbsInstaller-doTbsDexOpt done"
            com.tencent.smtt.utils.TbsLog.i(r7, r8)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.am.d(android.content.Context, int):boolean");
    }

    private static boolean d(Context context, String str) {
        String str2;
        String str3;
        File file = new File(context.getDir("tbs", 0), str);
        if (!file.exists()) {
            str2 = "TbsInstaller";
            str3 = "TbsInstaller-isPrepareTbsCore, #1";
        } else if (!new File(file, "tbs.conf").exists()) {
            str2 = "TbsInstaller";
            str3 = "TbsInstaller-isPrepareTbsCore, #2";
        } else {
            TbsLog.i("TbsInstaller", "TbsInstaller-isPrepareTbsCore, #3");
            return true;
        }
        TbsLog.i(str2, str3);
        return false;
    }

    private synchronized boolean d(Context context, boolean z) {
        boolean z2;
        if (context != null) {
            try {
                if ("com.tencent.mm".equals(context.getApplicationContext().getApplicationInfo().packageName)) {
                    TbsLogReport.a(context).a((int) TbsListener.ErrorCode.INSTALL_FROM_UNZIP, " ");
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromUnzip canRenameTmpDir =" + z);
        z2 = false;
        try {
            if (!w(context)) {
                return false;
            }
            boolean tryLock = i.tryLock();
            TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromUnzip locked=" + tryLock);
            if (tryLock) {
                int c2 = ai.a(context).c();
                TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromUnzip installStatus=" + c2);
                int a2 = a(false, context);
                if (c2 == 2) {
                    if (a2 == 0) {
                        TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromUnzip tbsCoreInstalledVer = 0", false);
                    } else if (z) {
                        TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromUnzip tbsCoreInstalledVer != 0", false);
                    }
                    A(context);
                    z2 = true;
                }
                i.unlock();
            }
            b();
        } catch (Exception e2) {
            QbSdk.a(context, "TbsInstaller::enableTbsCoreFromUnzip Exception: " + e2);
            e2.printStackTrace();
        } catch (Throwable th2) {
            i.unlock();
            throw th2;
        }
        return z2;
    }

    private Context e(Context context, int i2) {
        Context a2;
        TbsLog.i("TbsInstaller", "TbsInstaller--getTbsCoreHostContext tbsCoreTargetVer=" + i2);
        if (i2 <= 0) {
            return null;
        }
        String[] coreProviderAppList = TbsShareManager.getCoreProviderAppList();
        for (int i3 = 0; i3 < coreProviderAppList.length; i3++) {
            if (!context.getPackageName().equalsIgnoreCase(coreProviderAppList[i3]) && e(context, coreProviderAppList[i3]) && (a2 = a(context, coreProviderAppList[i3])) != null) {
                if (!f(a2)) {
                    TbsLog.e("TbsInstaller", "TbsInstaller--getTbsCoreHostContext " + coreProviderAppList[i3] + " illegal signature go on next");
                } else {
                    int i4 = i(a2);
                    TbsLog.i("TbsInstaller", "TbsInstaller-getTbsCoreHostContext hostTbsCoreVer=" + i4);
                    if (i4 != 0 && i4 == i2) {
                        TbsLog.i("TbsInstaller", "TbsInstaller-getTbsCoreHostContext targetApp=" + coreProviderAppList[i3]);
                        return a2;
                    }
                }
            }
        }
        return null;
    }

    private boolean e(Context context, String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException unused) {
            packageInfo = null;
        }
        return packageInfo != null;
    }

    private synchronized boolean e(Context context, boolean z) {
        return false;
    }

    private void f(Context context, boolean z) {
        if (context == null) {
            TbsLogReport.a(context).a(225, "setTmpFolderCoreToRead context is null");
            return;
        }
        try {
            File file = new File(context.getDir("tbs", 0), "tmp_folder_core_to_read.conf");
            if (!z) {
                j.b(file);
            } else if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e2) {
            TbsLogReport a2 = TbsLogReport.a(context);
            a2.a(225, "setTmpFolderCoreToRead Exception message is " + e2.getMessage() + " Exception cause is " + e2.getCause());
        }
    }

    static File s(Context context) {
        File file = new File(context.getDir("tbs", 0), "core_private");
        if (file.isDirectory() || file.mkdir()) {
            return file;
        }
        return null;
    }

    private static boolean x(Context context) {
        String str;
        String str2;
        if (context == null) {
            str = "TbsInstaller";
            str2 = "TbsInstaller-getTmpFolderCoreToRead, #1";
        } else {
            try {
                if (new File(context.getDir("tbs", 0), "tmp_folder_core_to_read.conf").exists()) {
                    TbsLog.i("TbsInstaller", "TbsInstaller-getTmpFolderCoreToRead, #2");
                    return true;
                }
                TbsLog.i("TbsInstaller", "TbsInstaller-getTmpFolderCoreToRead, #3");
                return false;
            } catch (Exception unused) {
                str = "TbsInstaller";
                str2 = "TbsInstaller-getTmpFolderCoreToRead, #4";
            }
        }
        TbsLog.i(str, str2);
        return true;
    }

    private void y(Context context) {
        boolean z;
        try {
            z = TbsDownloadConfig.getInstance().getTbsCoreLoadRenameFileLockEnable();
        } catch (Throwable unused) {
            z = true;
        }
        if (z && l != null) {
            j.a(context, l);
        }
    }

    private boolean z(Context context) {
        boolean z;
        try {
            z = TbsDownloadConfig.getInstance().getTbsCoreLoadRenameFileLockEnable();
        } catch (Throwable unused) {
            z = true;
        }
        l = !z ? bt.a().b(context) : j.f(context);
        return l != null;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0049 A[SYNTHETIC, Splitter:B:28:0x0049] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x004f A[SYNTHETIC, Splitter:B:34:0x004f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int a(java.lang.String r5) {
        /*
            r4 = this;
            r0 = 0
            if (r5 != 0) goto L_0x0004
            return r0
        L_0x0004:
            r1 = 0
            java.io.File r2 = new java.io.File     // Catch:{ Exception -> 0x004d, all -> 0x0046 }
            r2.<init>(r5)     // Catch:{ Exception -> 0x004d, all -> 0x0046 }
            java.io.File r5 = new java.io.File     // Catch:{ Exception -> 0x004d, all -> 0x0046 }
            java.lang.String r3 = "tbs.conf"
            r5.<init>(r2, r3)     // Catch:{ Exception -> 0x004d, all -> 0x0046 }
            boolean r2 = r5.exists()     // Catch:{ Exception -> 0x004d, all -> 0x0046 }
            if (r2 != 0) goto L_0x0018
            return r0
        L_0x0018:
            java.util.Properties r2 = new java.util.Properties     // Catch:{ Exception -> 0x004d, all -> 0x0046 }
            r2.<init>()     // Catch:{ Exception -> 0x004d, all -> 0x0046 }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ Exception -> 0x004d, all -> 0x0046 }
            r3.<init>(r5)     // Catch:{ Exception -> 0x004d, all -> 0x0046 }
            java.io.BufferedInputStream r5 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x004d, all -> 0x0046 }
            r5.<init>(r3)     // Catch:{ Exception -> 0x004d, all -> 0x0046 }
            r2.load(r5)     // Catch:{ Exception -> 0x0044, all -> 0x0041 }
            r5.close()     // Catch:{ Exception -> 0x0044, all -> 0x0041 }
            java.lang.String r1 = "tbs_core_version"
            java.lang.String r1 = r2.getProperty(r1)     // Catch:{ Exception -> 0x0044, all -> 0x0041 }
            if (r1 != 0) goto L_0x0039
            r5.close()     // Catch:{ IOException -> 0x0038 }
        L_0x0038:
            return r0
        L_0x0039:
            int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ Exception -> 0x0044, all -> 0x0041 }
            r5.close()     // Catch:{ IOException -> 0x0040 }
        L_0x0040:
            return r1
        L_0x0041:
            r0 = move-exception
            r1 = r5
            goto L_0x0047
        L_0x0044:
            r1 = r5
            goto L_0x004d
        L_0x0046:
            r0 = move-exception
        L_0x0047:
            if (r1 == 0) goto L_0x004c
            r1.close()     // Catch:{ IOException -> 0x004c }
        L_0x004c:
            throw r0
        L_0x004d:
            if (r1 == 0) goto L_0x0052
            r1.close()     // Catch:{ IOException -> 0x0052 }
        L_0x0052:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.am.a(java.lang.String):int");
    }

    public int a(boolean z, Context context) {
        if (z || f9142a.get().intValue() <= 0) {
            f9142a.set(Integer.valueOf(i(context)));
        }
        return f9142a.get().intValue();
    }

    /* access modifiers changed from: package-private */
    public Context a(Context context, String str) {
        try {
            return context.createPackageContext(str, 2);
        } catch (Exception unused) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Context context, Bundle bundle) {
        if (bundle != null && context != null) {
            Object[] objArr = {context, bundle};
            Message message = new Message();
            message.what = 3;
            message.obj = objArr;
            m.sendMessage(message);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Context context, String str, int i2) {
        TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCore tbsApkPath=" + str);
        TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCore tbsCoreTargetVer=" + i2);
        TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore currentProcessName=" + context.getApplicationInfo().processName);
        TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCore currentProcessId=" + Process.myPid());
        TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCore currentThreadName=" + Thread.currentThread().getName());
        Object[] objArr = {context, str, Integer.valueOf(i2)};
        Message message = new Message();
        message.what = 1;
        message.obj = objArr;
        m.sendMessage(message);
    }

    /* access modifiers changed from: package-private */
    public void a(Context context, boolean z) {
        int i2;
        int i3;
        int i4;
        String str;
        int i5;
        boolean z2 = true;
        if (z) {
            this.k = true;
        }
        TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore currentProcessName=" + context.getApplicationInfo().processName);
        TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore currentProcessId=" + Process.myPid());
        TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore currentThreadName=" + Thread.currentThread().getName());
        if (w(context)) {
            if (i.tryLock()) {
                try {
                    i2 = ai.a(context).c();
                    i5 = ai.a(context).b();
                    str = ai.a(context).d("install_apk_path");
                    i4 = ai.a(context).c("copy_core_ver");
                    i3 = ai.a(context).b("copy_status");
                } finally {
                    i.unlock();
                }
            } else {
                str = null;
                i2 = -1;
                i5 = 0;
                i4 = 0;
                i3 = -1;
            }
            b();
            TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore installStatus=" + i2);
            TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore tbsCoreInstallVer=" + i5);
            TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore tbsApkPath=" + str);
            TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore tbsCoreCopyVer=" + i4);
            TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore copyStatus=" + i3);
            if (TbsShareManager.isThirdPartyApp(context)) {
                b(context, TbsShareManager.a(context, false));
                return;
            }
            int i6 = TbsDownloadConfig.getInstance(context).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_RESPONSECODE, 0);
            if (!(i6 == 1 || i6 == 2 || i6 == 4)) {
                z2 = false;
            }
            if (!z2 && i6 != 0) {
                Bundle bundle = new Bundle();
                bundle.putInt(HomeManager.v, 10001);
                a(context, bundle);
            }
            if (i2 > -1 && i2 < 2) {
                a(context, str, i5);
            }
            if (i3 == 0) {
                a(context, i4);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(Context context, int i2) {
        if (TbsDownloader.getOverSea(context)) {
            return false;
        }
        TbsLog.i("TbsInstaller", "TbsInstaller-installLocalTbsCore targetTbsCoreVer=" + i2);
        TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore currentProcessName=" + context.getApplicationInfo().processName);
        TbsLog.i("TbsInstaller", "TbsInstaller-installLocalTbsCore currentProcessId=" + Process.myPid());
        TbsLog.i("TbsInstaller", "TbsInstaller-installLocalTbsCore currentThreadName=" + Thread.currentThread().getName());
        Context e2 = e(context, i2);
        if (e2 != null) {
            Object[] objArr = {e2, context, Integer.valueOf(i2)};
            Message message = new Message();
            message.what = 2;
            message.obj = objArr;
            m.sendMessage(message);
            return true;
        }
        TbsLog.i("TbsInstaller", "TbsInstaller--installLocalTbsCore copy from null");
        return false;
    }

    public synchronized boolean a(Context context, Context context2) {
        TbsLog.i("TbsInstaller", "TbsInstaller--quickDexOptForThirdPartyApp");
        if (p) {
            return true;
        }
        p = true;
        new aq(this, context2, context).start();
        return true;
    }

    public boolean a(Context context, File[] fileArr) {
        return false;
    }

    /* access modifiers changed from: package-private */
    public int b(Context context, String str) {
        PackageInfo packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(str, 0);
        if (packageArchiveInfo != null) {
            return packageArchiveInfo.versionCode;
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public File b(Context context, Context context2) {
        File file = new File(context2.getDir("tbs", 0), "core_share");
        if (file.isDirectory() || ((context != null && TbsShareManager.isThirdPartyApp(context)) || file.mkdir())) {
            return file;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public synchronized void b() {
        int i2 = this.e;
        this.e = i2 - 1;
        if (i2 <= 1) {
            if (this.h) {
                TbsLog.i("TbsInstaller", "releaseTbsInstallingFileLock without skip");
                j.a(this.f, this.g);
                this.h = false;
                return;
            }
        }
        TbsLog.i("TbsInstaller", "releaseTbsInstallingFileLock with skip");
    }

    public void b(Context context) {
        f(context, true);
        ai.a(context).b(h(context), 2);
    }

    /* access modifiers changed from: package-private */
    public void b(Context context, int i2) {
        int i3;
        TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreForThirdPartyApp");
        if (i2 > 0 && (i3 = i(context)) != i2) {
            Context e2 = TbsShareManager.e(context);
            if (e2 != null || TbsShareManager.getHostCorePathAppDefined() != null) {
                TbsLog.i("TbsInstaller", "TbsInstaller--quickDexOptForThirdPartyApp hostContext != null");
                a(context, e2);
            } else if (i3 <= 0) {
                TbsLog.i("TbsInstaller", "TbsInstaller--installTbsCoreForThirdPartyApp hostContext == null");
                QbSdk.a(context, "TbsInstaller::installTbsCoreForThirdPartyApp forceSysWebViewInner #2");
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x0305  */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x034c  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00cb A[Catch:{ Exception -> 0x0283, all -> 0x0280 }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0142 A[SYNTHETIC, Splitter:B:47:0x0142] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x01e2  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x021f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(android.content.Context r14, android.os.Bundle r15) {
        /*
            r13 = this;
            boolean r0 = r13.c(r14)
            if (r0 == 0) goto L_0x0010
            com.tencent.smtt.sdk.TbsDownloadConfig r14 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r14)
            r15 = -539(0xfffffffffffffde5, float:NaN)
        L_0x000c:
            r14.setInstallInterruptCode(r15)
            return
        L_0x0010:
            java.lang.String r0 = "TbsInstaller"
            java.lang.String r1 = "TbsInstaller-installLocalTesCoreExInThread"
            com.tencent.smtt.utils.TbsLog.i(r0, r1)
            if (r15 == 0) goto L_0x0398
            if (r14 != 0) goto L_0x001d
            goto L_0x0398
        L_0x001d:
            boolean r0 = com.tencent.smtt.utils.j.b((android.content.Context) r14)
            if (r0 != 0) goto L_0x0058
            long r0 = com.tencent.smtt.utils.v.a()
            com.tencent.smtt.sdk.TbsDownloadConfig r15 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r14)
            long r2 = r15.getDownloadMinFreeSpace()
            com.tencent.smtt.sdk.TbsLogReport r15 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r14)
            r4 = 210(0xd2, float:2.94E-43)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "rom is not enough when patching tbs core! curAvailROM="
            r5.append(r6)
            r5.append(r0)
            java.lang.String r0 = ",minReqRom="
            r5.append(r0)
            r5.append(r2)
            java.lang.String r0 = r5.toString()
            r15.a((int) r4, (java.lang.String) r0)
            com.tencent.smtt.sdk.TbsDownloadConfig r14 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r14)
            r15 = -540(0xfffffffffffffde4, float:NaN)
            goto L_0x000c
        L_0x0058:
            boolean r0 = r13.w(r14)
            if (r0 != 0) goto L_0x0065
            com.tencent.smtt.sdk.TbsDownloadConfig r14 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r14)
            r15 = -541(0xfffffffffffffde3, float:NaN)
            goto L_0x000c
        L_0x0065:
            java.util.concurrent.locks.Lock r0 = j
            boolean r0 = r0.tryLock()
            java.lang.String r1 = "TbsInstaller"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "TbsInstaller-installLocalTesCoreExInThread locked="
            r2.append(r3)
            r2.append(r0)
            java.lang.String r2 = r2.toString()
            com.tencent.smtt.utils.TbsLog.i(r1, r2)
            if (r0 == 0) goto L_0x038c
            r0 = 0
            r1 = -1
            r2 = -544(0xfffffffffffffde0, float:NaN)
            r3 = 217(0xd9, float:3.04E-43)
            r4 = -546(0xfffffffffffffdde, float:NaN)
            r5 = 2
            r6 = 0
            r7 = 1
            com.tencent.smtt.sdk.QbSdk.setTBSInstallingStatus(r7)     // Catch:{ Exception -> 0x0283, all -> 0x0280 }
            int r8 = r13.i(r14)     // Catch:{ Exception -> 0x0283, all -> 0x0280 }
            if (r8 <= 0) goto L_0x026a
            com.tencent.smtt.sdk.ai r8 = com.tencent.smtt.sdk.ai.a((android.content.Context) r14)     // Catch:{ Exception -> 0x0283, all -> 0x0280 }
            int r8 = r8.d()     // Catch:{ Exception -> 0x0283, all -> 0x0280 }
            if (r8 != r7) goto L_0x00a3
            goto L_0x026a
        L_0x00a3:
            com.tencent.smtt.sdk.TbsDownloadConfig r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r14)     // Catch:{ Exception -> 0x0283, all -> 0x0280 }
            android.content.SharedPreferences r8 = r8.mPreferences     // Catch:{ Exception -> 0x0283, all -> 0x0280 }
            java.lang.String r9 = "tbs_responsecode"
            int r8 = r8.getInt(r9, r6)     // Catch:{ Exception -> 0x0283, all -> 0x0280 }
            if (r8 == r7) goto L_0x00b9
            if (r8 == r5) goto L_0x00b9
            r9 = 4
            if (r8 != r9) goto L_0x00b7
            goto L_0x00b9
        L_0x00b7:
            r9 = 0
            goto L_0x00ba
        L_0x00b9:
            r9 = 1
        L_0x00ba:
            if (r9 != 0) goto L_0x01d6
            if (r8 == 0) goto L_0x01d6
            com.tencent.smtt.sdk.ai r8 = com.tencent.smtt.sdk.ai.a((android.content.Context) r14)     // Catch:{ Exception -> 0x0283, all -> 0x0280 }
            java.lang.String r9 = "incrupdate_retry_num"
            int r8 = r8.c((java.lang.String) r9)     // Catch:{ Exception -> 0x0283, all -> 0x0280 }
            r9 = 5
            if (r8 <= r9) goto L_0x0142
            java.lang.String r8 = "TbsInstaller"
            java.lang.String r9 = "TbsInstaller-installLocalTesCoreExInThread exceed incrupdate num"
            com.tencent.smtt.utils.TbsLog.i(r8, r9)     // Catch:{ Exception -> 0x0283, all -> 0x0280 }
            java.lang.String r8 = "old_apk_location"
            java.lang.String r8 = r15.getString(r8)     // Catch:{ Exception -> 0x0283, all -> 0x0280 }
            java.lang.String r9 = "new_apk_location"
            java.lang.String r9 = r15.getString(r9)     // Catch:{ Exception -> 0x0283, all -> 0x0280 }
            java.lang.String r10 = "diff_file_location"
            java.lang.String r15 = r15.getString(r10)     // Catch:{ Exception -> 0x0283, all -> 0x0280 }
            boolean r10 = android.text.TextUtils.isEmpty(r8)     // Catch:{ Exception -> 0x0283, all -> 0x0280 }
            if (r10 != 0) goto L_0x00f2
            java.io.File r10 = new java.io.File     // Catch:{ Exception -> 0x0283, all -> 0x0280 }
            r10.<init>(r8)     // Catch:{ Exception -> 0x0283, all -> 0x0280 }
            com.tencent.smtt.utils.j.b((java.io.File) r10)     // Catch:{ Exception -> 0x0283, all -> 0x0280 }
        L_0x00f2:
            boolean r8 = android.text.TextUtils.isEmpty(r9)     // Catch:{ Exception -> 0x0283, all -> 0x0280 }
            if (r8 != 0) goto L_0x0100
            java.io.File r8 = new java.io.File     // Catch:{ Exception -> 0x0283, all -> 0x0280 }
            r8.<init>(r9)     // Catch:{ Exception -> 0x0283, all -> 0x0280 }
            com.tencent.smtt.utils.j.b((java.io.File) r8)     // Catch:{ Exception -> 0x0283, all -> 0x0280 }
        L_0x0100:
            boolean r8 = android.text.TextUtils.isEmpty(r15)     // Catch:{ Exception -> 0x0283, all -> 0x0280 }
            if (r8 != 0) goto L_0x010e
            java.io.File r8 = new java.io.File     // Catch:{ Exception -> 0x0283, all -> 0x0280 }
            r8.<init>(r15)     // Catch:{ Exception -> 0x0283, all -> 0x0280 }
            com.tencent.smtt.utils.j.b((java.io.File) r8)     // Catch:{ Exception -> 0x0283, all -> 0x0280 }
        L_0x010e:
            com.tencent.smtt.sdk.TbsDownloadConfig r15 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r14)     // Catch:{ Exception -> 0x0283, all -> 0x0280 }
            java.util.Map<java.lang.String, java.lang.Object> r15 = r15.f9091a     // Catch:{ Exception -> 0x0283, all -> 0x0280 }
            java.lang.String r8 = "tbs_needdownload"
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r7)     // Catch:{ Exception -> 0x0283, all -> 0x0280 }
            r15.put(r8, r9)     // Catch:{ Exception -> 0x0283, all -> 0x0280 }
            com.tencent.smtt.sdk.TbsDownloadConfig r15 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r14)     // Catch:{ Exception -> 0x0283, all -> 0x0280 }
            r15.commit()     // Catch:{ Exception -> 0x0283, all -> 0x0280 }
            com.tencent.smtt.sdk.TbsLogReport r15 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r14)     // Catch:{ Exception -> 0x0283, all -> 0x0280 }
            r8 = 224(0xe0, float:3.14E-43)
            java.lang.String r9 = "incrUpdate exceed retry max num"
            r15.a((int) r8, (java.lang.String) r9)     // Catch:{ Exception -> 0x0283, all -> 0x0280 }
            java.util.concurrent.locks.Lock r14 = j
            r14.unlock()
            r13.b()
            java.lang.String r14 = "TbsInstaller"
            java.lang.String r15 = "TbsInstaller-installLocalTesCoreExInThread PATCH_NONEEDPATCH"
            com.tencent.smtt.utils.TbsLog.i(r14, r15)
            com.tencent.smtt.sdk.QbSdk.setTBSInstallingStatus(r6)
            return
        L_0x0142:
            com.tencent.smtt.sdk.ai r9 = com.tencent.smtt.sdk.ai.a((android.content.Context) r14)     // Catch:{ Exception -> 0x0283, all -> 0x0280 }
            java.lang.String r10 = "incrupdate_retry_num"
            int r8 = r8 + r7
            r9.a((java.lang.String) r10, (int) r8)     // Catch:{ Exception -> 0x0283, all -> 0x0280 }
            java.io.File r8 = s(r14)     // Catch:{ Exception -> 0x0283, all -> 0x0280 }
            if (r8 == 0) goto L_0x01d6
            java.io.File r9 = new java.io.File     // Catch:{ Exception -> 0x0283, all -> 0x0280 }
            java.lang.String r10 = "x5.tbs"
            r9.<init>(r8, r10)     // Catch:{ Exception -> 0x0283, all -> 0x0280 }
            boolean r8 = r9.exists()     // Catch:{ Exception -> 0x0283, all -> 0x0280 }
            if (r8 == 0) goto L_0x01d6
            com.tencent.smtt.sdk.TbsDownloadConfig r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r14)     // Catch:{ Exception -> 0x0283, all -> 0x0280 }
            r9 = -550(0xfffffffffffffdda, float:NaN)
            r8.setInstallInterruptCode(r9)     // Catch:{ Exception -> 0x0283, all -> 0x0280 }
            android.os.Bundle r8 = com.tencent.smtt.sdk.QbSdk.a((android.content.Context) r14, (android.os.Bundle) r15)     // Catch:{ Exception -> 0x0283, all -> 0x0280 }
            r0 = 228(0xe4, float:3.2E-43)
            if (r8 != 0) goto L_0x0199
            com.tencent.smtt.sdk.TbsLogReport r9 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r14)     // Catch:{ Exception -> 0x0194, all -> 0x0190 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0194, all -> 0x0190 }
            r10.<init>()     // Catch:{ Exception -> 0x0194, all -> 0x0190 }
            java.lang.String r11 = "result null : "
            r10.append(r11)     // Catch:{ Exception -> 0x0194, all -> 0x0190 }
            java.lang.String r11 = "new_core_ver"
            int r15 = r15.getInt(r11)     // Catch:{ Exception -> 0x0194, all -> 0x0190 }
            r10.append(r15)     // Catch:{ Exception -> 0x0194, all -> 0x0190 }
            java.lang.String r15 = r10.toString()     // Catch:{ Exception -> 0x0194, all -> 0x0190 }
            r9.a((int) r0, (java.lang.String) r15)     // Catch:{ Exception -> 0x0194, all -> 0x0190 }
            r9 = 1
            goto L_0x01d8
        L_0x0190:
            r15 = move-exception
            r0 = r8
            goto L_0x02f8
        L_0x0194:
            r15 = move-exception
            r0 = r8
            r9 = 1
            goto L_0x0285
        L_0x0199:
            java.lang.String r9 = "patch_result"
            int r9 = r8.getInt(r9)     // Catch:{ Exception -> 0x01d2, all -> 0x01ce }
            com.tencent.smtt.sdk.TbsLogReport r10 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r14)     // Catch:{ Exception -> 0x01ca, all -> 0x01c6 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01ca, all -> 0x01c6 }
            r11.<init>()     // Catch:{ Exception -> 0x01ca, all -> 0x01c6 }
            java.lang.String r12 = "result "
            r11.append(r12)     // Catch:{ Exception -> 0x01ca, all -> 0x01c6 }
            r11.append(r9)     // Catch:{ Exception -> 0x01ca, all -> 0x01c6 }
            java.lang.String r12 = " : "
            r11.append(r12)     // Catch:{ Exception -> 0x01ca, all -> 0x01c6 }
            java.lang.String r12 = "new_core_ver"
            int r15 = r15.getInt(r12)     // Catch:{ Exception -> 0x01ca, all -> 0x01c6 }
            r11.append(r15)     // Catch:{ Exception -> 0x01ca, all -> 0x01c6 }
            java.lang.String r15 = r11.toString()     // Catch:{ Exception -> 0x01ca, all -> 0x01c6 }
            r10.a((int) r0, (java.lang.String) r15)     // Catch:{ Exception -> 0x01ca, all -> 0x01c6 }
            goto L_0x01d8
        L_0x01c6:
            r15 = move-exception
            r0 = r8
            goto L_0x02fb
        L_0x01ca:
            r15 = move-exception
            r0 = r8
            goto L_0x0285
        L_0x01ce:
            r15 = move-exception
            r0 = r8
            goto L_0x0281
        L_0x01d2:
            r15 = move-exception
            r0 = r8
            goto L_0x0284
        L_0x01d6:
            r8 = r0
            r9 = 2
        L_0x01d8:
            java.util.concurrent.locks.Lock r15 = j
            r15.unlock()
            r13.b()
            if (r9 != 0) goto L_0x021f
            java.lang.String r15 = "TbsInstaller"
            java.lang.String r0 = "TbsInstaller-installLocalTesCoreExInThread PATCH_SUCCESS"
            com.tencent.smtt.utils.TbsLog.i(r15, r0)
            com.tencent.smtt.sdk.ai r15 = com.tencent.smtt.sdk.ai.a((android.content.Context) r14)
            java.lang.String r0 = "incrupdate_retry_num"
            r15.a((java.lang.String) r0, (int) r6)
            com.tencent.smtt.sdk.TbsDownloadConfig r15 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r14)
            r15.setInstallInterruptCode(r2)
            com.tencent.smtt.sdk.ai r15 = com.tencent.smtt.sdk.ai.a((android.content.Context) r14)
            r15.b(r6, r1)
            com.tencent.smtt.sdk.ai r15 = com.tencent.smtt.sdk.ai.a((android.content.Context) r14)
            r15.c((int) r7)
            java.lang.String r15 = "apk_path"
            java.lang.String r15 = r8.getString(r15)
            java.io.File r0 = new java.io.File
            r0.<init>(r15)
            com.tencent.smtt.sdk.ag.a((java.io.File) r0, (android.content.Context) r14)
            java.lang.String r0 = "tbs_core_ver"
            int r0 = r8.getInt(r0)
            r13.b(r14, r15, r0)
            goto L_0x0265
        L_0x021f:
            if (r9 != r5) goto L_0x0229
            java.lang.String r14 = "TbsInstaller"
            java.lang.String r15 = "TbsInstaller-installLocalTesCoreExInThread PATCH_NONEEDPATCH"
            com.tencent.smtt.utils.TbsLog.i(r14, r15)
            goto L_0x0265
        L_0x0229:
            com.tencent.smtt.sdk.TbsDownloadConfig r15 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r14)
            r15.setInstallInterruptCode(r4)
            java.lang.String r15 = "TbsInstaller"
            java.lang.String r0 = "TbsInstaller-installLocalTesCoreExInThread PATCH_FAIL"
            com.tencent.smtt.utils.TbsLog.i(r15, r0)
            com.tencent.smtt.sdk.TbsDownloadConfig r15 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r14)
            java.util.Map<java.lang.String, java.lang.Object> r15 = r15.f9091a
            java.lang.String r0 = "tbs_needdownload"
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r7)
            r15.put(r0, r1)
            com.tencent.smtt.sdk.TbsDownloadConfig r15 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r14)
            r15.commit()
            com.tencent.smtt.sdk.TbsLogReport r14 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r14)
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            java.lang.String r0 = "incrUpdate fail! patch ret="
            r15.append(r0)
            r15.append(r9)
        L_0x025e:
            java.lang.String r15 = r15.toString()
            r14.a((int) r3, (java.lang.String) r15)
        L_0x0265:
            com.tencent.smtt.sdk.QbSdk.setTBSInstallingStatus(r6)
            goto L_0x0398
        L_0x026a:
            com.tencent.smtt.sdk.QbSdk.setTBSInstallingStatus(r6)     // Catch:{ Exception -> 0x0283, all -> 0x0280 }
            java.util.concurrent.locks.Lock r14 = j
            r14.unlock()
            r13.b()
            java.lang.String r14 = "TbsInstaller"
            java.lang.String r15 = "TbsInstaller-installLocalTesCoreExInThread PATCH_NONEEDPATCH"
            com.tencent.smtt.utils.TbsLog.i(r14, r15)
            com.tencent.smtt.sdk.QbSdk.setTBSInstallingStatus(r6)
            return
        L_0x0280:
            r15 = move-exception
        L_0x0281:
            r9 = 2
            goto L_0x02fb
        L_0x0283:
            r15 = move-exception
        L_0x0284:
            r9 = 2
        L_0x0285:
            java.lang.String r8 = "TbsInstaller"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x02fa }
            r10.<init>()     // Catch:{ all -> 0x02fa }
            java.lang.String r11 = "installLocalTbsCoreExInThread exception:"
            r10.append(r11)     // Catch:{ all -> 0x02fa }
            java.lang.String r11 = android.util.Log.getStackTraceString(r15)     // Catch:{ all -> 0x02fa }
            r10.append(r11)     // Catch:{ all -> 0x02fa }
            java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x02fa }
            com.tencent.smtt.utils.TbsLog.i(r8, r10)     // Catch:{ all -> 0x02fa }
            r15.printStackTrace()     // Catch:{ all -> 0x02fa }
            com.tencent.smtt.sdk.TbsDownloadConfig r8 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r14)     // Catch:{ all -> 0x02f7 }
            r9 = -543(0xfffffffffffffde1, float:NaN)
            r8.setInstallInterruptCode(r9)     // Catch:{ all -> 0x02f7 }
            com.tencent.smtt.sdk.TbsLogReport r8 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r14)     // Catch:{ all -> 0x02f7 }
            r9 = 218(0xda, float:3.05E-43)
            java.lang.String r15 = r15.toString()     // Catch:{ all -> 0x02f7 }
            r8.a((int) r9, (java.lang.String) r15)     // Catch:{ all -> 0x02f7 }
            java.util.concurrent.locks.Lock r15 = j
            r15.unlock()
            r13.b()
            com.tencent.smtt.sdk.TbsDownloadConfig r15 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r14)
            r15.setInstallInterruptCode(r4)
            java.lang.String r15 = "TbsInstaller"
            java.lang.String r0 = "TbsInstaller-installLocalTesCoreExInThread PATCH_FAIL"
            com.tencent.smtt.utils.TbsLog.i(r15, r0)
            com.tencent.smtt.sdk.TbsDownloadConfig r15 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r14)
            java.util.Map<java.lang.String, java.lang.Object> r15 = r15.f9091a
            java.lang.String r0 = "tbs_needdownload"
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r7)
            r15.put(r0, r1)
            com.tencent.smtt.sdk.TbsDownloadConfig r15 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r14)
            r15.commit()
            com.tencent.smtt.sdk.TbsLogReport r14 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r14)
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            java.lang.String r0 = "incrUpdate fail! patch ret="
            r15.append(r0)
            r15.append(r7)
            goto L_0x025e
        L_0x02f7:
            r15 = move-exception
        L_0x02f8:
            r9 = 1
            goto L_0x02fb
        L_0x02fa:
            r15 = move-exception
        L_0x02fb:
            java.util.concurrent.locks.Lock r8 = j
            r8.unlock()
            r13.b()
            if (r9 == 0) goto L_0x034c
            if (r9 != r5) goto L_0x030f
            java.lang.String r14 = "TbsInstaller"
            java.lang.String r0 = "TbsInstaller-installLocalTesCoreExInThread PATCH_NONEEDPATCH"
            com.tencent.smtt.utils.TbsLog.i(r14, r0)
            goto L_0x0388
        L_0x030f:
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r14)
            r0.setInstallInterruptCode(r4)
            java.lang.String r0 = "TbsInstaller"
            java.lang.String r1 = "TbsInstaller-installLocalTesCoreExInThread PATCH_FAIL"
            com.tencent.smtt.utils.TbsLog.i(r0, r1)
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r14)
            java.util.Map<java.lang.String, java.lang.Object> r0 = r0.f9091a
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r7)
            java.lang.String r2 = "tbs_needdownload"
            r0.put(r2, r1)
            com.tencent.smtt.sdk.TbsDownloadConfig r0 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r14)
            r0.commit()
            com.tencent.smtt.sdk.TbsLogReport r14 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r14)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "incrUpdate fail! patch ret="
            r0.append(r1)
            r0.append(r9)
            java.lang.String r0 = r0.toString()
            r14.a((int) r3, (java.lang.String) r0)
            goto L_0x0388
        L_0x034c:
            java.lang.String r3 = "TbsInstaller"
            java.lang.String r4 = "TbsInstaller-installLocalTesCoreExInThread PATCH_SUCCESS"
            com.tencent.smtt.utils.TbsLog.i(r3, r4)
            com.tencent.smtt.sdk.ai r3 = com.tencent.smtt.sdk.ai.a((android.content.Context) r14)
            java.lang.String r4 = "incrupdate_retry_num"
            r3.a((java.lang.String) r4, (int) r6)
            com.tencent.smtt.sdk.TbsDownloadConfig r3 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r14)
            r3.setInstallInterruptCode(r2)
            com.tencent.smtt.sdk.ai r2 = com.tencent.smtt.sdk.ai.a((android.content.Context) r14)
            r2.b(r6, r1)
            com.tencent.smtt.sdk.ai r1 = com.tencent.smtt.sdk.ai.a((android.content.Context) r14)
            r1.c((int) r7)
            java.lang.String r1 = "apk_path"
            java.lang.String r1 = r0.getString(r1)
            java.io.File r2 = new java.io.File
            r2.<init>(r1)
            com.tencent.smtt.sdk.ag.a((java.io.File) r2, (android.content.Context) r14)
            java.lang.String r2 = "tbs_core_ver"
            int r0 = r0.getInt(r2)
            r13.b(r14, r1, r0)
        L_0x0388:
            com.tencent.smtt.sdk.QbSdk.setTBSInstallingStatus(r6)
            throw r15
        L_0x038c:
            com.tencent.smtt.sdk.TbsDownloadConfig r14 = com.tencent.smtt.sdk.TbsDownloadConfig.getInstance(r14)
            r15 = -547(0xfffffffffffffddd, float:NaN)
            r14.setInstallInterruptCode(r15)
            r13.b()
        L_0x0398:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.am.b(android.content.Context, android.os.Bundle):void");
    }

    /* access modifiers changed from: package-private */
    public void b(Context context, boolean z) {
        String str;
        String str2;
        File u;
        if (!QbSdk.b) {
            if (Build.VERSION.SDK_INT < 8) {
                TbsLog.e("TbsInstaller", "android version < 2.1 no need install X5 core", true);
                return;
            }
            try {
                if (!TbsShareManager.isThirdPartyApp(context) && (u = u(context)) != null && u.exists()) {
                    j.a(u, false);
                    new File(s(context), "x5.tbs").delete();
                }
            } catch (Throwable unused) {
            }
            if (x(context)) {
                if (d(context, "core_unzip_tmp") && d(context, z)) {
                    str = "TbsInstaller";
                    str2 = "TbsInstaller-installTbsCoreIfNeeded, enableTbsCoreFromUnzip!!";
                } else if (d(context, "core_share_backup_tmp") && e(context, z)) {
                    str = "TbsInstaller";
                    str2 = "TbsInstaller-installTbsCoreIfNeeded, enableTbsCoreFromBackup!!";
                } else if (d(context, "core_copy_tmp") && c(context, z)) {
                    str = "TbsInstaller";
                    str2 = "TbsInstaller-installTbsCoreIfNeeded, enableTbsCoreFromCopy!!";
                } else {
                    TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreIfNeeded, error !!", true);
                    return;
                }
                TbsLog.i(str, str2, true);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public File c(Context context, Context context2) {
        File file = new File(context2.getDir("tbs", 0), "core_share_decouple");
        if (file.isDirectory() || ((context != null && TbsShareManager.isThirdPartyApp(context)) || file.mkdir())) {
            return file;
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x003d A[SYNTHETIC, Splitter:B:18:0x003d] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0044 A[SYNTHETIC, Splitter:B:26:0x0044] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String c(android.content.Context r4, java.lang.String r5) {
        /*
            r3 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r5)
            r1 = 0
            if (r0 == 0) goto L_0x0008
            return r1
        L_0x0008:
            java.io.File r4 = r3.q(r4)     // Catch:{ Exception -> 0x0041, all -> 0x003a }
            java.io.File r0 = new java.io.File     // Catch:{ Exception -> 0x0041, all -> 0x003a }
            java.lang.String r2 = "tbs.conf"
            r0.<init>(r4, r2)     // Catch:{ Exception -> 0x0041, all -> 0x003a }
            boolean r4 = r0.exists()     // Catch:{ Exception -> 0x0041, all -> 0x003a }
            if (r4 != 0) goto L_0x001a
            return r1
        L_0x001a:
            java.util.Properties r4 = new java.util.Properties     // Catch:{ Exception -> 0x0041, all -> 0x003a }
            r4.<init>()     // Catch:{ Exception -> 0x0041, all -> 0x003a }
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0041, all -> 0x003a }
            r2.<init>(r0)     // Catch:{ Exception -> 0x0041, all -> 0x003a }
            java.io.BufferedInputStream r0 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x0041, all -> 0x003a }
            r0.<init>(r2)     // Catch:{ Exception -> 0x0041, all -> 0x003a }
            r4.load(r0)     // Catch:{ Exception -> 0x0042, all -> 0x0037 }
            r0.close()     // Catch:{ Exception -> 0x0042, all -> 0x0037 }
            java.lang.String r4 = r4.getProperty(r5)     // Catch:{ Exception -> 0x0042, all -> 0x0037 }
            r0.close()     // Catch:{ IOException -> 0x0036 }
        L_0x0036:
            return r4
        L_0x0037:
            r4 = move-exception
            r1 = r0
            goto L_0x003b
        L_0x003a:
            r4 = move-exception
        L_0x003b:
            if (r1 == 0) goto L_0x0040
            r1.close()     // Catch:{ IOException -> 0x0040 }
        L_0x0040:
            throw r4
        L_0x0041:
            r0 = r1
        L_0x0042:
            if (r0 == 0) goto L_0x0047
            r0.close()     // Catch:{ IOException -> 0x0047 }
        L_0x0047:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.am.c(android.content.Context, java.lang.String):java.lang.String");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004c, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004d, code lost:
        r2 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0079, code lost:
        r10 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0092, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0093, code lost:
        r0.printStackTrace();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0079 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:6:0x0023] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0088 A[SYNTHETIC, Splitter:B:32:0x0088] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x008e A[SYNTHETIC, Splitter:B:36:0x008e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean c(android.content.Context r10) {
        /*
            r9 = this;
            java.io.File r10 = r9.q(r10)
            java.io.File r0 = new java.io.File
            java.lang.String r1 = "tbs.conf"
            r0.<init>(r10, r1)
            boolean r10 = r0.exists()
            r1 = 0
            if (r10 != 0) goto L_0x0013
            return r1
        L_0x0013:
            java.util.Properties r10 = new java.util.Properties
            r10.<init>()
            r2 = 0
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x0081 }
            r3.<init>(r0)     // Catch:{ Throwable -> 0x0081 }
            java.io.BufferedInputStream r4 = new java.io.BufferedInputStream     // Catch:{ Throwable -> 0x0081 }
            r4.<init>(r3)     // Catch:{ Throwable -> 0x0081 }
            r10.load(r4)     // Catch:{ Throwable -> 0x007b, all -> 0x0079 }
            java.lang.String r2 = "tbs_local_installation"
            java.lang.String r3 = "false"
            java.lang.String r10 = r10.getProperty(r2, r3)     // Catch:{ Throwable -> 0x007b, all -> 0x0079 }
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r10)     // Catch:{ Throwable -> 0x007b, all -> 0x0079 }
            boolean r10 = r10.booleanValue()     // Catch:{ Throwable -> 0x007b, all -> 0x0079 }
            r2 = 1
            if (r10 == 0) goto L_0x004f
            long r5 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x004c, all -> 0x0079 }
            long r7 = r0.lastModified()     // Catch:{ Throwable -> 0x004c, all -> 0x0079 }
            r0 = 0
            long r5 = r5 - r7
            r7 = 259200000(0xf731400, double:1.280618154E-315)
            int r0 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r0 <= 0) goto L_0x004f
            r1 = 1
            goto L_0x004f
        L_0x004c:
            r0 = move-exception
            r2 = r4
            goto L_0x0083
        L_0x004f:
            java.lang.String r0 = "TbsInstaller"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x004c, all -> 0x0079 }
            r3.<init>()     // Catch:{ Throwable -> 0x004c, all -> 0x0079 }
            java.lang.String r5 = "TBS_LOCAL_INSTALLATION is:"
            r3.append(r5)     // Catch:{ Throwable -> 0x004c, all -> 0x0079 }
            r3.append(r10)     // Catch:{ Throwable -> 0x004c, all -> 0x0079 }
            java.lang.String r5 = " expired="
            r3.append(r5)     // Catch:{ Throwable -> 0x004c, all -> 0x0079 }
            r3.append(r1)     // Catch:{ Throwable -> 0x004c, all -> 0x0079 }
            java.lang.String r3 = r3.toString()     // Catch:{ Throwable -> 0x004c, all -> 0x0079 }
            com.tencent.smtt.utils.TbsLog.i(r0, r3)     // Catch:{ Throwable -> 0x004c, all -> 0x0079 }
            r0 = r1 ^ 1
            r10 = r10 & r0
            r4.close()     // Catch:{ IOException -> 0x0074 }
            goto L_0x008b
        L_0x0074:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x008b
        L_0x0079:
            r10 = move-exception
            goto L_0x008c
        L_0x007b:
            r0 = move-exception
            r2 = r4
            goto L_0x0082
        L_0x007e:
            r10 = move-exception
            r4 = r2
            goto L_0x008c
        L_0x0081:
            r0 = move-exception
        L_0x0082:
            r10 = 0
        L_0x0083:
            r0.printStackTrace()     // Catch:{ all -> 0x007e }
            if (r2 == 0) goto L_0x008b
            r2.close()     // Catch:{ IOException -> 0x0074 }
        L_0x008b:
            return r10
        L_0x008c:
            if (r4 == 0) goto L_0x0096
            r4.close()     // Catch:{ IOException -> 0x0092 }
            goto L_0x0096
        L_0x0092:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0096:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.am.c(android.content.Context):boolean");
    }

    /* access modifiers changed from: package-private */
    public boolean c(Context context, int i2) {
        File file;
        String str;
        String str2;
        try {
            boolean isThirdPartyApp = TbsShareManager.isThirdPartyApp(context);
            if (!isThirdPartyApp) {
                file = q(context);
            } else if (TbsShareManager.j(context)) {
                file = new File(TbsShareManager.c(context));
                if (file.getAbsolutePath().contains(TbsConfig.APP_DEMO)) {
                    return true;
                }
            } else {
                TbsLog.e("TbsInstaller", "321");
                return false;
            }
            if (file != null) {
                Long[][] lArr = n;
                int length = lArr.length;
                int i3 = 0;
                while (true) {
                    if (i3 >= length) {
                        break;
                    }
                    Long[] lArr2 = lArr[i3];
                    if (i2 == lArr2[0].intValue()) {
                        File file2 = new File(file, "libmttwebview.so");
                        if (!file2.exists() || file2.length() != lArr2[1].longValue()) {
                            if (!isThirdPartyApp) {
                                j.b(context.getDir("tbs", 0));
                            }
                            f9142a.set(0);
                            str = "TbsInstaller";
                            str2 = "322";
                        } else {
                            TbsLog.d("TbsInstaller", "check so success: " + i2 + "; file: " + file2);
                        }
                    } else {
                        i3++;
                    }
                }
                return true;
            }
            str = "TbsInstaller";
            str2 = "323";
            TbsLog.e(str, str2);
            return false;
        } catch (Throwable th) {
            TbsLog.e("TbsInstaller", "ISTBSCORELEGAL exception getMessage is " + th.getMessage());
            TbsLog.e("TbsInstaller", "ISTBSCORELEGAL exception getCause is " + th.getCause());
            return false;
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:14|15|(2:22|23)|(2:26|27)|28|29) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x0035 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:28:0x004c */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0044 A[SYNTHETIC, Splitter:B:22:0x0044] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0049 A[SYNTHETIC, Splitter:B:26:0x0049] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0050 A[SYNTHETIC, Splitter:B:34:0x0050] */
    /* JADX WARNING: Removed duplicated region for block: B:40:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void d(android.content.Context r6) {
        /*
            r5 = this;
            java.io.File r6 = r5.q(r6)     // Catch:{  }
            java.io.File r0 = new java.io.File     // Catch:{  }
            java.lang.String r1 = "tbs.conf"
            r0.<init>(r6, r1)     // Catch:{  }
            java.util.Properties r6 = new java.util.Properties     // Catch:{  }
            r6.<init>()     // Catch:{  }
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x004d, all -> 0x0040 }
            r2.<init>(r0)     // Catch:{ Throwable -> 0x004d, all -> 0x0040 }
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch:{ Throwable -> 0x004d, all -> 0x0040 }
            r3.<init>(r2)     // Catch:{ Throwable -> 0x004d, all -> 0x0040 }
            r6.load(r3)     // Catch:{ Throwable -> 0x004e, all -> 0x003e }
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ Throwable -> 0x004e, all -> 0x003e }
            r2.<init>(r0)     // Catch:{ Throwable -> 0x004e, all -> 0x003e }
            java.io.BufferedOutputStream r0 = new java.io.BufferedOutputStream     // Catch:{ Throwable -> 0x004e, all -> 0x003e }
            r0.<init>(r2)     // Catch:{ Throwable -> 0x004e, all -> 0x003e }
            java.lang.String r2 = "tbs_local_installation"
            java.lang.String r4 = "false"
            r6.setProperty(r2, r4)     // Catch:{ Throwable -> 0x003c, all -> 0x0039 }
            r6.store(r0, r1)     // Catch:{ Throwable -> 0x003c, all -> 0x0039 }
            r0.close()     // Catch:{ IOException -> 0x0035 }
        L_0x0035:
            r3.close()     // Catch:{ Throwable -> 0x0056 }
            goto L_0x0056
        L_0x0039:
            r6 = move-exception
            r1 = r0
            goto L_0x0042
        L_0x003c:
            r1 = r0
            goto L_0x004e
        L_0x003e:
            r6 = move-exception
            goto L_0x0042
        L_0x0040:
            r6 = move-exception
            r3 = r1
        L_0x0042:
            if (r1 == 0) goto L_0x0047
            r1.close()     // Catch:{ IOException -> 0x0047 }
        L_0x0047:
            if (r3 == 0) goto L_0x004c
            r3.close()     // Catch:{ IOException -> 0x004c }
        L_0x004c:
            throw r6     // Catch:{  }
        L_0x004d:
            r3 = r1
        L_0x004e:
            if (r1 == 0) goto L_0x0053
            r1.close()     // Catch:{ IOException -> 0x0053 }
        L_0x0053:
            if (r3 == 0) goto L_0x0056
            goto L_0x0035
        L_0x0056:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.am.d(android.content.Context):void");
    }

    public boolean e(Context context) {
        try {
            File file = new File(j.a(context, 4), "x5.tbs.decouple");
            File u = a().u(context);
            j.a(u);
            j.a(u, true);
            j.a(file, u);
            String[] list = u.list();
            for (String file2 : list) {
                File file3 = new File(u, file2);
                if (file3.getName().endsWith(ShareConstants.w)) {
                    file3.delete();
                }
            }
            a().f(context, true);
            File p2 = p(context);
            j.a(p2, true);
            u.renameTo(p2);
            TbsShareManager.b(context);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean f(Context context) {
        if (TbsShareManager.getHostCorePathAppDefined() != null) {
            return true;
        }
        try {
            Signature signature = context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures[0];
            if (context.getPackageName().equals(TbsConfig.APP_QB)) {
                if (!signature.toCharsString().equals("3082023f308201a8a00302010202044c46914a300d06092a864886f70d01010505003064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f301e170d3130303732313036313835305a170d3430303731333036313835305a3064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f30819f300d06092a864886f70d010101050003818d0030818902818100c209077044bd0d63ea00ede5b839914cabcc912a87f0f8b390877e0f7a2583f0d5933443c40431c35a4433bc4c965800141961adc44c9625b1d321385221fd097e5bdc2f44a1840d643ab59dc070cf6c4b4b4d98bed5cbb8046e0a7078ae134da107cdf2bfc9b440fe5cb2f7549b44b73202cc6f7c2c55b8cfb0d333a021f01f0203010001300d06092a864886f70d010105050003818100b007db9922774ef4ccfee81ba514a8d57c410257e7a2eba64bfa17c9e690da08106d32f637ac41fbc9f205176c71bde238c872c3ee2f8313502bee44c80288ea4ef377a6f2cdfe4d3653c145c4acfedbfbadea23b559d41980cc3cdd35d79a68240693739aabf5c5ed26148756cf88264226de394c8a24ac35b712b120d4d23a")) {
                    return false;
                }
            } else if (context.getPackageName().equals("com.tencent.mm")) {
                if (!signature.toCharsString().equals("308202eb30820254a00302010202044d36f7a4300d06092a864886f70d01010505003081b9310b300906035504061302383631123010060355040813094775616e67646f6e673111300f060355040713085368656e7a68656e31353033060355040a132c54656e63656e7420546563686e6f6c6f6779285368656e7a68656e2920436f6d70616e79204c696d69746564313a3038060355040b133154656e63656e74204775616e677a686f7520526573656172636820616e6420446576656c6f706d656e742043656e7465723110300e0603550403130754656e63656e74301e170d3131303131393134333933325a170d3431303131313134333933325a3081b9310b300906035504061302383631123010060355040813094775616e67646f6e673111300f060355040713085368656e7a68656e31353033060355040a132c54656e63656e7420546563686e6f6c6f6779285368656e7a68656e2920436f6d70616e79204c696d69746564313a3038060355040b133154656e63656e74204775616e677a686f7520526573656172636820616e6420446576656c6f706d656e742043656e7465723110300e0603550403130754656e63656e7430819f300d06092a864886f70d010101050003818d0030818902818100c05f34b231b083fb1323670bfbe7bdab40c0c0a6efc87ef2072a1ff0d60cc67c8edb0d0847f210bea6cbfaa241be70c86daf56be08b723c859e52428a064555d80db448cdcacc1aea2501eba06f8bad12a4fa49d85cacd7abeb68945a5cb5e061629b52e3254c373550ee4e40cb7c8ae6f7a8151ccd8df582d446f39ae0c5e930203010001300d06092a864886f70d0101050500038181009c8d9d7f2f908c42081b4c764c377109a8b2c70582422125ce545842d5f520aea69550b6bd8bfd94e987b75a3077eb04ad341f481aac266e89d3864456e69fba13df018acdc168b9a19dfd7ad9d9cc6f6ace57c746515f71234df3a053e33ba93ece5cd0fc15f3e389a3f365588a9fcb439e069d3629cd7732a13fff7b891499")) {
                    return false;
                }
            } else if (context.getPackageName().equals(TbsConfig.APP_QQ)) {
                if (!signature.toCharsString().equals("30820253308201bca00302010202044bbb0361300d06092a864886f70d0101050500306d310e300c060355040613054368696e61310f300d06035504080c06e58c97e4baac310f300d06035504070c06e58c97e4baac310f300d060355040a0c06e885bee8aeaf311b3019060355040b0c12e697a0e7babfe4b89ae58aa1e7b3bbe7bb9f310b30090603550403130251513020170d3130303430363039343831375a180f32323834303132303039343831375a306d310e300c060355040613054368696e61310f300d06035504080c06e58c97e4baac310f300d06035504070c06e58c97e4baac310f300d060355040a0c06e885bee8aeaf311b3019060355040b0c12e697a0e7babfe4b89ae58aa1e7b3bbe7bb9f310b300906035504031302515130819f300d06092a864886f70d010101050003818d0030818902818100a15e9756216f694c5915e0b529095254367c4e64faeff07ae13488d946615a58ddc31a415f717d019edc6d30b9603d3e2a7b3de0ab7e0cf52dfee39373bc472fa997027d798d59f81d525a69ecf156e885fd1e2790924386b2230cc90e3b7adc95603ddcf4c40bdc72f22db0f216a99c371d3bf89cba6578c60699e8a0d536950203010001300d06092a864886f70d01010505000381810094a9b80e80691645dd42d6611775a855f71bcd4d77cb60a8e29404035a5e00b21bcc5d4a562482126bd91b6b0e50709377ceb9ef8c2efd12cc8b16afd9a159f350bb270b14204ff065d843832720702e28b41491fbc3a205f5f2f42526d67f17614d8a974de6487b2c866efede3b4e49a0f916baa3c1336fd2ee1b1629652049")) {
                    return false;
                }
            } else if (context.getPackageName().equals(TbsConfig.APP_DEMO)) {
                if (!signature.toCharsString().equals("3082023f308201a8a00302010202044c46914a300d06092a864886f70d01010505003064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f301e170d3130303732313036313835305a170d3430303731333036313835305a3064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f30819f300d06092a864886f70d010101050003818d0030818902818100c209077044bd0d63ea00ede5b839914cabcc912a87f0f8b390877e0f7a2583f0d5933443c40431c35a4433bc4c965800141961adc44c9625b1d321385221fd097e5bdc2f44a1840d643ab59dc070cf6c4b4b4d98bed5cbb8046e0a7078ae134da107cdf2bfc9b440fe5cb2f7549b44b73202cc6f7c2c55b8cfb0d333a021f01f0203010001300d06092a864886f70d010105050003818100b007db9922774ef4ccfee81ba514a8d57c410257e7a2eba64bfa17c9e690da08106d32f637ac41fbc9f205176c71bde238c872c3ee2f8313502bee44c80288ea4ef377a6f2cdfe4d3653c145c4acfedbfbadea23b559d41980cc3cdd35d79a68240693739aabf5c5ed26148756cf88264226de394c8a24ac35b712b120d4d23a")) {
                    return false;
                }
            } else if (!context.getPackageName().equals(TbsConfig.APP_QZONE)) {
                return !context.getPackageName().equals("com.tencent.qqpimsecure") || signature.toCharsString().equals("30820239308201a2a00302010202044c96f48f300d06092a864886f70d01010505003060310b300906035504061302434e310b300906035504081302474431123010060355040713094775616e677a686f753110300e060355040a130754656e63656e74310b3009060355040b130233473111300f0603550403130857696c736f6e57753020170d3130303932303035343334335a180f32303635303632333035343334335a3060310b300906035504061302434e310b300906035504081302474431123010060355040713094775616e677a686f753110300e060355040a130754656e63656e74310b3009060355040b130233473111300f0603550403130857696c736f6e577530819f300d06092a864886f70d010101050003818d0030818902818100b56e79dbb1185a79e52d792bb3d0bb3da8010d9b87da92ec69f7dc5ad66ab6bfdff2a6a1ed285dd2358f28b72a468be7c10a2ce30c4c27323ed4edcc936080e5bedc2cbbca0b7e879c08a631182793f44bb3ea284179b263410c298e5f6831032c9702ba4a74e2ccfc9ef857f12201451602fc8e774ac59d6398511586c83d1d0203010001300d06092a864886f70d0101050500038181002475615bb65b8d8786b890535802948840387d06b1692ff3ea47ef4c435719ba1865b81e6bfa6293ce31747c3cd6b34595b485cc1563fd90107ba5845c28b95c79138f0dec288940395bc10f92f2b69d8dc410999deb38900974ce9984b678030edfba8816582f56160d87e38641288d8588d2a31e20b89f223d788dd35cc9c8");
            } else {
                if (!signature.toCharsString().equals("308202ad30820216a00302010202044c26cea2300d06092a864886f70d010105050030819a310b3009060355040613023836311530130603550408130c4265696a696e672043697479311530130603550407130c4265696a696e67204369747931263024060355040a131d515a6f6e65205465616d206f662054656e63656e7420436f6d70616e7931183016060355040b130f54656e63656e7420436f6d70616e79311b301906035504031312416e64726f696420515a6f6e65205465616d301e170d3130303632373034303830325a170d3335303632313034303830325a30819a310b3009060355040613023836311530130603550408130c4265696a696e672043697479311530130603550407130c4265696a696e67204369747931263024060355040a131d515a6f6e65205465616d206f662054656e63656e7420436f6d70616e7931183016060355040b130f54656e63656e7420436f6d70616e79311b301906035504031312416e64726f696420515a6f6e65205465616d30819f300d06092a864886f70d010101050003818d003081890281810082d6aca037a9843fbbe88b6dd19f36e9c24ce174c1b398f3a529e2a7fe02de99c27539602c026edf96ad8d43df32a85458bca1e6fbf11958658a7d6751a1d9b782bf43a8c19bd1c06bdbfd94c0516326ae3cf638ac42bb470580e340c46e6f306a772c1ef98f10a559edf867f3f31fe492808776b7bd953b2cba2d2b2d66a44f0203010001300d06092a864886f70d0101050500038181006003b04a8a8c5be9650f350cda6896e57dd13e6e83e7f891fc70f6a3c2eaf75cfa4fc998365deabbd1b9092159edf4b90df5702a0d101f8840b5d4586eb92a1c3cd19d95fbc1c2ac956309eda8eef3944baf08c4a49d3b9b3ffb06bc13dab94ecb5b8eb74e8789aa0ba21cb567f538bbc59c2a11e6919924a24272eb79251677")) {
                    return false;
                }
            }
        } catch (Exception unused) {
            TbsLog.i("TbsInstaller", "TbsInstaller-installLocalTbsCore getPackageInfo fail");
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x005b A[SYNTHETIC, Splitter:B:25:0x005b] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0061 A[SYNTHETIC, Splitter:B:31:0x0061] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int g(android.content.Context r6) {
        /*
            r5 = this;
            r0 = 0
            r1 = 0
            java.io.File r6 = r5.t(r6)     // Catch:{ Exception -> 0x005f, all -> 0x0058 }
            java.lang.String r2 = "TbsInstaller"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x005f, all -> 0x0058 }
            r3.<init>()     // Catch:{ Exception -> 0x005f, all -> 0x0058 }
            java.lang.String r4 = "TbsInstaller--getTmpTbsCoreVersionUnzipDir  tbsShareDir is "
            r3.append(r4)     // Catch:{ Exception -> 0x005f, all -> 0x0058 }
            r3.append(r6)     // Catch:{ Exception -> 0x005f, all -> 0x0058 }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x005f, all -> 0x0058 }
            com.tencent.smtt.utils.TbsLog.i(r2, r3)     // Catch:{ Exception -> 0x005f, all -> 0x0058 }
            java.io.File r2 = new java.io.File     // Catch:{ Exception -> 0x005f, all -> 0x0058 }
            java.lang.String r3 = "tbs.conf"
            r2.<init>(r6, r3)     // Catch:{ Exception -> 0x005f, all -> 0x0058 }
            boolean r6 = r2.exists()     // Catch:{ Exception -> 0x005f, all -> 0x0058 }
            if (r6 != 0) goto L_0x002a
            return r0
        L_0x002a:
            java.util.Properties r6 = new java.util.Properties     // Catch:{ Exception -> 0x005f, all -> 0x0058 }
            r6.<init>()     // Catch:{ Exception -> 0x005f, all -> 0x0058 }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ Exception -> 0x005f, all -> 0x0058 }
            r3.<init>(r2)     // Catch:{ Exception -> 0x005f, all -> 0x0058 }
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x005f, all -> 0x0058 }
            r2.<init>(r3)     // Catch:{ Exception -> 0x005f, all -> 0x0058 }
            r6.load(r2)     // Catch:{ Exception -> 0x0056, all -> 0x0053 }
            r2.close()     // Catch:{ Exception -> 0x0056, all -> 0x0053 }
            java.lang.String r1 = "tbs_core_version"
            java.lang.String r6 = r6.getProperty(r1)     // Catch:{ Exception -> 0x0056, all -> 0x0053 }
            if (r6 != 0) goto L_0x004b
            r2.close()     // Catch:{ IOException -> 0x004a }
        L_0x004a:
            return r0
        L_0x004b:
            int r6 = java.lang.Integer.parseInt(r6)     // Catch:{ Exception -> 0x0056, all -> 0x0053 }
            r2.close()     // Catch:{ IOException -> 0x0052 }
        L_0x0052:
            return r6
        L_0x0053:
            r6 = move-exception
            r1 = r2
            goto L_0x0059
        L_0x0056:
            r1 = r2
            goto L_0x005f
        L_0x0058:
            r6 = move-exception
        L_0x0059:
            if (r1 == 0) goto L_0x005e
            r1.close()     // Catch:{ IOException -> 0x005e }
        L_0x005e:
            throw r6
        L_0x005f:
            if (r1 == 0) goto L_0x0064
            r1.close()     // Catch:{ IOException -> 0x0064 }
        L_0x0064:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.am.g(android.content.Context):int");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0045 A[SYNTHETIC, Splitter:B:25:0x0045] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x004b A[SYNTHETIC, Splitter:B:31:0x004b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int h(android.content.Context r5) {
        /*
            r4 = this;
            r0 = 0
            r1 = 0
            java.io.File r5 = r4.p(r5)     // Catch:{ Exception -> 0x0049, all -> 0x0042 }
            java.io.File r2 = new java.io.File     // Catch:{ Exception -> 0x0049, all -> 0x0042 }
            java.lang.String r3 = "tbs.conf"
            r2.<init>(r5, r3)     // Catch:{ Exception -> 0x0049, all -> 0x0042 }
            boolean r5 = r2.exists()     // Catch:{ Exception -> 0x0049, all -> 0x0042 }
            if (r5 != 0) goto L_0x0014
            return r0
        L_0x0014:
            java.util.Properties r5 = new java.util.Properties     // Catch:{ Exception -> 0x0049, all -> 0x0042 }
            r5.<init>()     // Catch:{ Exception -> 0x0049, all -> 0x0042 }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0049, all -> 0x0042 }
            r3.<init>(r2)     // Catch:{ Exception -> 0x0049, all -> 0x0042 }
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x0049, all -> 0x0042 }
            r2.<init>(r3)     // Catch:{ Exception -> 0x0049, all -> 0x0042 }
            r5.load(r2)     // Catch:{ Exception -> 0x0040, all -> 0x003d }
            r2.close()     // Catch:{ Exception -> 0x0040, all -> 0x003d }
            java.lang.String r1 = "tbs_core_version"
            java.lang.String r5 = r5.getProperty(r1)     // Catch:{ Exception -> 0x0040, all -> 0x003d }
            if (r5 != 0) goto L_0x0035
            r2.close()     // Catch:{ IOException -> 0x0034 }
        L_0x0034:
            return r0
        L_0x0035:
            int r5 = java.lang.Integer.parseInt(r5)     // Catch:{ Exception -> 0x0040, all -> 0x003d }
            r2.close()     // Catch:{ IOException -> 0x003c }
        L_0x003c:
            return r5
        L_0x003d:
            r5 = move-exception
            r1 = r2
            goto L_0x0043
        L_0x0040:
            r1 = r2
            goto L_0x0049
        L_0x0042:
            r5 = move-exception
        L_0x0043:
            if (r1 == 0) goto L_0x0048
            r1.close()     // Catch:{ IOException -> 0x0048 }
        L_0x0048:
            throw r5
        L_0x0049:
            if (r1 == 0) goto L_0x004e
            r1.close()     // Catch:{ IOException -> 0x004e }
        L_0x004e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.am.h(android.content.Context):int");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00a0 A[SYNTHETIC, Splitter:B:33:0x00a0] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00c2 A[SYNTHETIC, Splitter:B:39:0x00c2] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int i(android.content.Context r6) {
        /*
            r5 = this;
            r0 = 0
            r1 = 0
            java.io.File r6 = r5.q(r6)     // Catch:{ Exception -> 0x0083 }
            java.io.File r2 = new java.io.File     // Catch:{ Exception -> 0x0083 }
            java.lang.String r3 = "tbs.conf"
            r2.<init>(r6, r3)     // Catch:{ Exception -> 0x0083 }
            boolean r6 = r2.exists()     // Catch:{ Exception -> 0x0083 }
            if (r6 != 0) goto L_0x0014
            return r0
        L_0x0014:
            java.util.Properties r6 = new java.util.Properties     // Catch:{ Exception -> 0x0083 }
            r6.<init>()     // Catch:{ Exception -> 0x0083 }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0083 }
            r3.<init>(r2)     // Catch:{ Exception -> 0x0083 }
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x0083 }
            r2.<init>(r3)     // Catch:{ Exception -> 0x0083 }
            r6.load(r2)     // Catch:{ Exception -> 0x007e, all -> 0x007b }
            r2.close()     // Catch:{ Exception -> 0x007e, all -> 0x007b }
            java.lang.String r1 = "tbs_core_version"
            java.lang.String r6 = r6.getProperty(r1)     // Catch:{ Exception -> 0x007e, all -> 0x007b }
            if (r6 != 0) goto L_0x0051
            r2.close()     // Catch:{ IOException -> 0x0035 }
            goto L_0x0050
        L_0x0035:
            r6 = move-exception
            java.lang.String r1 = "TbsInstaller"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "TbsInstaller--getTbsCoreInstalledVerInNolock IOException="
            r2.append(r3)
            java.lang.String r6 = r6.toString()
            r2.append(r6)
            java.lang.String r6 = r2.toString()
            com.tencent.smtt.utils.TbsLog.i(r1, r6)
        L_0x0050:
            return r0
        L_0x0051:
            int r6 = java.lang.Integer.parseInt(r6)     // Catch:{ Exception -> 0x007e, all -> 0x007b }
            int r1 = o     // Catch:{ Exception -> 0x007e, all -> 0x007b }
            if (r1 != 0) goto L_0x005b
            o = r6     // Catch:{ Exception -> 0x007e, all -> 0x007b }
        L_0x005b:
            r2.close()     // Catch:{ IOException -> 0x005f }
            goto L_0x007a
        L_0x005f:
            r0 = move-exception
            java.lang.String r1 = "TbsInstaller"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "TbsInstaller--getTbsCoreInstalledVerInNolock IOException="
            r2.append(r3)
            java.lang.String r0 = r0.toString()
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            com.tencent.smtt.utils.TbsLog.i(r1, r0)
        L_0x007a:
            return r6
        L_0x007b:
            r6 = move-exception
            r1 = r2
            goto L_0x00c0
        L_0x007e:
            r6 = move-exception
            r1 = r2
            goto L_0x0084
        L_0x0081:
            r6 = move-exception
            goto L_0x00c0
        L_0x0083:
            r6 = move-exception
        L_0x0084:
            java.lang.String r2 = "TbsInstaller"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0081 }
            r3.<init>()     // Catch:{ all -> 0x0081 }
            java.lang.String r4 = "TbsInstaller--getTbsCoreInstalledVerInNolock Exception="
            r3.append(r4)     // Catch:{ all -> 0x0081 }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x0081 }
            r3.append(r6)     // Catch:{ all -> 0x0081 }
            java.lang.String r6 = r3.toString()     // Catch:{ all -> 0x0081 }
            com.tencent.smtt.utils.TbsLog.i(r2, r6)     // Catch:{ all -> 0x0081 }
            if (r1 == 0) goto L_0x00bf
            r1.close()     // Catch:{ IOException -> 0x00a4 }
            goto L_0x00bf
        L_0x00a4:
            r6 = move-exception
            java.lang.String r1 = "TbsInstaller"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "TbsInstaller--getTbsCoreInstalledVerInNolock IOException="
            r2.append(r3)
            java.lang.String r6 = r6.toString()
            r2.append(r6)
            java.lang.String r6 = r2.toString()
            com.tencent.smtt.utils.TbsLog.i(r1, r6)
        L_0x00bf:
            return r0
        L_0x00c0:
            if (r1 == 0) goto L_0x00e1
            r1.close()     // Catch:{ IOException -> 0x00c6 }
            goto L_0x00e1
        L_0x00c6:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "TbsInstaller--getTbsCoreInstalledVerInNolock IOException="
            r1.append(r2)
            java.lang.String r0 = r0.toString()
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            java.lang.String r1 = "TbsInstaller"
            com.tencent.smtt.utils.TbsLog.i(r1, r0)
        L_0x00e1:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.am.i(android.content.Context):int");
    }

    /* access modifiers changed from: package-private */
    public int j(Context context) {
        return o != 0 ? o : i(context);
    }

    /* access modifiers changed from: package-private */
    public void k(Context context) {
        if (o == 0) {
            o = i(context);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean l(Context context) {
        return new File(q(context), "tbs.conf").exists();
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x014d A[SYNTHETIC, Splitter:B:58:0x014d] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0174 A[Catch:{ Throwable -> 0x017a }] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0197 A[SYNTHETIC, Splitter:B:71:0x0197] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x01be A[Catch:{ Throwable -> 0x01c4 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int m(android.content.Context r6) {
        /*
            r5 = this;
            boolean r0 = r5.w(r6)
            if (r0 != 0) goto L_0x0008
            r6 = -1
            return r6
        L_0x0008:
            java.util.concurrent.locks.ReentrantLock r0 = i
            boolean r0 = r0.tryLock()
            java.lang.String r1 = "TbsInstaller"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "TbsInstaller--getTbsCoreInstalledVer locked="
            r2.append(r3)
            r2.append(r0)
            java.lang.String r2 = r2.toString()
            com.tencent.smtt.utils.TbsLog.i(r1, r2)
            r1 = 0
            if (r0 == 0) goto L_0x01df
            r0 = 0
            java.io.File r6 = r5.q(r6)     // Catch:{ Exception -> 0x0130 }
            java.io.File r2 = new java.io.File     // Catch:{ Exception -> 0x0130 }
            java.lang.String r3 = "tbs.conf"
            r2.<init>(r6, r3)     // Catch:{ Exception -> 0x0130 }
            boolean r6 = r2.exists()     // Catch:{ Exception -> 0x0130 }
            if (r6 != 0) goto L_0x0062
            java.util.concurrent.locks.ReentrantLock r6 = i     // Catch:{ Throwable -> 0x0047 }
            boolean r6 = r6.isHeldByCurrentThread()     // Catch:{ Throwable -> 0x0047 }
            if (r6 == 0) goto L_0x005e
            java.util.concurrent.locks.ReentrantLock r6 = i     // Catch:{ Throwable -> 0x0047 }
            r6.unlock()     // Catch:{ Throwable -> 0x0047 }
            goto L_0x005e
        L_0x0047:
            r6 = move-exception
            java.lang.String r0 = "TbsInstaller"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "TbsRenameLock.unlock exception: "
            r2.append(r3)
            r2.append(r6)
            java.lang.String r6 = r2.toString()
            com.tencent.smtt.utils.TbsLog.e(r0, r6)
        L_0x005e:
            r5.b()
            return r1
        L_0x0062:
            java.util.Properties r6 = new java.util.Properties     // Catch:{ Exception -> 0x0130 }
            r6.<init>()     // Catch:{ Exception -> 0x0130 }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0130 }
            r3.<init>(r2)     // Catch:{ Exception -> 0x0130 }
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x0130 }
            r2.<init>(r3)     // Catch:{ Exception -> 0x0130 }
            r6.load(r2)     // Catch:{ Exception -> 0x012b, all -> 0x0128 }
            r2.close()     // Catch:{ Exception -> 0x012b, all -> 0x0128 }
            java.lang.String r0 = "tbs_core_version"
            java.lang.String r6 = r6.getProperty(r0)     // Catch:{ Exception -> 0x012b, all -> 0x0128 }
            if (r6 != 0) goto L_0x00c7
            r2.close()     // Catch:{ IOException -> 0x0083 }
            goto L_0x009e
        L_0x0083:
            r6 = move-exception
            java.lang.String r0 = "TbsInstaller"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "TbsInstaller--getTbsCoreInstalledVer IOException="
            r2.append(r3)
            java.lang.String r6 = r6.toString()
            r2.append(r6)
            java.lang.String r6 = r2.toString()
            com.tencent.smtt.utils.TbsLog.i(r0, r6)
        L_0x009e:
            java.util.concurrent.locks.ReentrantLock r6 = i     // Catch:{ Throwable -> 0x00ac }
            boolean r6 = r6.isHeldByCurrentThread()     // Catch:{ Throwable -> 0x00ac }
            if (r6 == 0) goto L_0x00c3
            java.util.concurrent.locks.ReentrantLock r6 = i     // Catch:{ Throwable -> 0x00ac }
            r6.unlock()     // Catch:{ Throwable -> 0x00ac }
            goto L_0x00c3
        L_0x00ac:
            r6 = move-exception
            java.lang.String r0 = "TbsInstaller"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "TbsRenameLock.unlock exception: "
            r2.append(r3)
            r2.append(r6)
            java.lang.String r6 = r2.toString()
            com.tencent.smtt.utils.TbsLog.e(r0, r6)
        L_0x00c3:
            r5.b()
            return r1
        L_0x00c7:
            java.lang.ThreadLocal<java.lang.Integer> r0 = f9142a     // Catch:{ Exception -> 0x012b, all -> 0x0128 }
            int r6 = java.lang.Integer.parseInt(r6)     // Catch:{ Exception -> 0x012b, all -> 0x0128 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ Exception -> 0x012b, all -> 0x0128 }
            r0.set(r6)     // Catch:{ Exception -> 0x012b, all -> 0x0128 }
            java.lang.ThreadLocal<java.lang.Integer> r6 = f9142a     // Catch:{ Exception -> 0x012b, all -> 0x0128 }
            java.lang.Object r6 = r6.get()     // Catch:{ Exception -> 0x012b, all -> 0x0128 }
            java.lang.Integer r6 = (java.lang.Integer) r6     // Catch:{ Exception -> 0x012b, all -> 0x0128 }
            int r6 = r6.intValue()     // Catch:{ Exception -> 0x012b, all -> 0x0128 }
            r2.close()     // Catch:{ IOException -> 0x00e4 }
            goto L_0x00ff
        L_0x00e4:
            r0 = move-exception
            java.lang.String r1 = "TbsInstaller"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "TbsInstaller--getTbsCoreInstalledVer IOException="
            r2.append(r3)
            java.lang.String r0 = r0.toString()
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            com.tencent.smtt.utils.TbsLog.i(r1, r0)
        L_0x00ff:
            java.util.concurrent.locks.ReentrantLock r0 = i     // Catch:{ Throwable -> 0x010d }
            boolean r0 = r0.isHeldByCurrentThread()     // Catch:{ Throwable -> 0x010d }
            if (r0 == 0) goto L_0x0124
            java.util.concurrent.locks.ReentrantLock r0 = i     // Catch:{ Throwable -> 0x010d }
            r0.unlock()     // Catch:{ Throwable -> 0x010d }
            goto L_0x0124
        L_0x010d:
            r0 = move-exception
            java.lang.String r1 = "TbsInstaller"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "TbsRenameLock.unlock exception: "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            com.tencent.smtt.utils.TbsLog.e(r1, r0)
        L_0x0124:
            r5.b()
            return r6
        L_0x0128:
            r6 = move-exception
            r0 = r2
            goto L_0x0195
        L_0x012b:
            r6 = move-exception
            r0 = r2
            goto L_0x0131
        L_0x012e:
            r6 = move-exception
            goto L_0x0195
        L_0x0130:
            r6 = move-exception
        L_0x0131:
            java.lang.String r2 = "TbsInstaller"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x012e }
            r3.<init>()     // Catch:{ all -> 0x012e }
            java.lang.String r4 = "TbsInstaller--getTbsCoreInstalledVer Exception="
            r3.append(r4)     // Catch:{ all -> 0x012e }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x012e }
            r3.append(r6)     // Catch:{ all -> 0x012e }
            java.lang.String r6 = r3.toString()     // Catch:{ all -> 0x012e }
            com.tencent.smtt.utils.TbsLog.i(r2, r6)     // Catch:{ all -> 0x012e }
            if (r0 == 0) goto L_0x016c
            r0.close()     // Catch:{ IOException -> 0x0151 }
            goto L_0x016c
        L_0x0151:
            r6 = move-exception
            java.lang.String r0 = "TbsInstaller"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "TbsInstaller--getTbsCoreInstalledVer IOException="
            r2.append(r3)
            java.lang.String r6 = r6.toString()
            r2.append(r6)
            java.lang.String r6 = r2.toString()
            com.tencent.smtt.utils.TbsLog.i(r0, r6)
        L_0x016c:
            java.util.concurrent.locks.ReentrantLock r6 = i     // Catch:{ Throwable -> 0x017a }
            boolean r6 = r6.isHeldByCurrentThread()     // Catch:{ Throwable -> 0x017a }
            if (r6 == 0) goto L_0x0191
            java.util.concurrent.locks.ReentrantLock r6 = i     // Catch:{ Throwable -> 0x017a }
            r6.unlock()     // Catch:{ Throwable -> 0x017a }
            goto L_0x0191
        L_0x017a:
            r6 = move-exception
            java.lang.String r0 = "TbsInstaller"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "TbsRenameLock.unlock exception: "
            r2.append(r3)
            r2.append(r6)
            java.lang.String r6 = r2.toString()
            com.tencent.smtt.utils.TbsLog.e(r0, r6)
        L_0x0191:
            r5.b()
            return r1
        L_0x0195:
            if (r0 == 0) goto L_0x01b6
            r0.close()     // Catch:{ IOException -> 0x019b }
            goto L_0x01b6
        L_0x019b:
            r0 = move-exception
            java.lang.String r1 = "TbsInstaller"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "TbsInstaller--getTbsCoreInstalledVer IOException="
            r2.append(r3)
            java.lang.String r0 = r0.toString()
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            com.tencent.smtt.utils.TbsLog.i(r1, r0)
        L_0x01b6:
            java.util.concurrent.locks.ReentrantLock r0 = i     // Catch:{ Throwable -> 0x01c4 }
            boolean r0 = r0.isHeldByCurrentThread()     // Catch:{ Throwable -> 0x01c4 }
            if (r0 == 0) goto L_0x01db
            java.util.concurrent.locks.ReentrantLock r0 = i     // Catch:{ Throwable -> 0x01c4 }
            r0.unlock()     // Catch:{ Throwable -> 0x01c4 }
            goto L_0x01db
        L_0x01c4:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "TbsRenameLock.unlock exception: "
            r1.append(r2)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            java.lang.String r1 = "TbsInstaller"
            com.tencent.smtt.utils.TbsLog.e(r1, r0)
        L_0x01db:
            r5.b()
            throw r6
        L_0x01df:
            r5.b()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.am.m(android.content.Context):int");
    }

    public boolean n(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--coreShareCopyToDecouple #0");
        File q = q(context);
        File p2 = p(context);
        try {
            j.a(p2, true);
            j.a(q, p2, (FileFilter) new at(this));
            TbsShareManager.b(context);
            TbsLog.i("TbsInstaller", "TbsInstaller--coreShareCopyToDecouple success!!!");
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public void o(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--cleanStatusAndTmpDir");
        ai.a(context).a(0);
        ai.a(context).b(0);
        ai.a(context).d(0);
        ai.a(context).a("incrupdate_retry_num", 0);
        if (TbsDownloadConfig.getInstance(context).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DOWNLOADDECOUPLECORE, 0) != 1) {
            ai.a(context).b(0, -1);
            ai.a(context).a("");
            ai.a(context).a("copy_retry_num", 0);
            ai.a(context).c(-1);
            ai.a(context).a(0, -1);
            j.a(t(context), true);
            j.a(v(context), true);
        }
    }

    /* access modifiers changed from: package-private */
    public File p(Context context) {
        File file = new File(context.getDir("tbs", 0), "core_share_decouple");
        if (file.isDirectory() || file.mkdir()) {
            return file;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public File q(Context context) {
        return b((Context) null, context);
    }

    /* access modifiers changed from: package-private */
    public File r(Context context) {
        File file = new File(context.getDir("tbs", 0), "share");
        if (file.isDirectory() || file.mkdir()) {
            return file;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public File t(Context context) {
        File file = new File(context.getDir("tbs", 0), "core_unzip_tmp");
        if (file.isDirectory() || file.mkdir()) {
            return file;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public File u(Context context) {
        File file = new File(context.getDir("tbs", 0), "core_unzip_tmp_decouple");
        if (file.isDirectory() || file.mkdir()) {
            return file;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public File v(Context context) {
        File file = new File(context.getDir("tbs", 0), "core_copy_tmp");
        if (file.isDirectory() || file.mkdir()) {
            return file;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean w(Context context) {
        this.e++;
        if (this.h) {
            TbsLog.i("TbsInstaller", "getTbsInstallingFileLock success,is cached= true");
            return true;
        }
        this.g = j.b(context, true, "tbslock.txt");
        if (this.g == null) {
            return false;
        }
        this.f = j.a(context, this.g);
        if (this.f == null) {
            return false;
        }
        TbsLog.i("TbsInstaller", "getTbsInstallingFileLock success,is cached= false");
        this.h = true;
        return true;
    }
}
