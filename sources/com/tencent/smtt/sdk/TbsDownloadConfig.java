package com.tencent.smtt.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class TbsDownloadConfig {
    public static final int CMD_ID_DOWNLOAD_FILE = 101;
    public static final int CMD_ID_FILE_UPLOAD = 100;
    public static final long DEFAULT_RETRY_INTERVAL_SEC = 86400;
    public static final int ERROR_DOWNLOAD = 2;
    public static final int ERROR_INSTALL = 5;
    public static final int ERROR_LOAD = 6;
    public static final int ERROR_NONE = 1;
    public static final int ERROR_REPORTED = 0;
    public static final int ERROR_UNZIP = 4;
    public static final int ERROR_VERIFY = 3;
    private static TbsDownloadConfig b;

    /* renamed from: a  reason: collision with root package name */
    Map<String, Object> f9091a = new HashMap();
    private Context c;
    public SharedPreferences mPreferences;

    public interface TbsConfigKey {
        public static final String KEY_APP_METADATA = "app_metadata";
        public static final String KEY_APP_VERSIONCODE = "app_versioncode";
        public static final String KEY_APP_VERSIONCODE_FOR_SWITCH = "app_versioncode_for_switch";
        public static final String KEY_APP_VERSIONNAME = "app_versionname";
        public static final String KEY_BACKUPCORE_DELFILELIST = "backupcore_delfilelist";
        public static final String KEY_COUNT_REQUEST_FAIL_IN_24HOURS = "count_request_fail_in_24hours";
        public static final String KEY_DECOUPLECOREVERSION = "tbs_decouplecoreversion";
        public static final String KEY_DESkEY_TOKEN = "tbs_deskey_token";
        public static final String KEY_DEVICE_CPUABI = "device_cpuabi";
        public static final String KEY_DOWNLOADDECOUPLECORE = "tbs_downloaddecouplecore";
        public static final String KEY_DOWNLOADURL_LIST = "tbs_downloadurl_list";
        public static final String KEY_DOWNLOAD_FAILED_MAX_RETRYTIMES = "tbs_download_failed_max_retrytimes";
        public static final String KEY_DOWNLOAD_FAILED_RETRYTIMES = "tbs_download_failed_retrytimes";
        public static final String KEY_DOWNLOAD_INTERRUPT_CODE = "tbs_download_interrupt_code";
        public static final String KEY_DOWNLOAD_INTERRUPT_CODE_REASON = "tbs_download_interrupt_code_reason";
        public static final String KEY_DOWNLOAD_INTERRUPT_TIME = "tbs_download_interrupt_time";
        public static final String KEY_DOWNLOAD_MAXFLOW = "tbs_download_maxflow";
        public static final String KEY_DOWNLOAD_MIN_FREE_SPACE = "tbs_download_min_free_space";
        public static final String KEY_DOWNLOAD_SINGLE_TIMEOUT = "tbs_single_timeout";
        public static final String KEY_DOWNLOAD_SUCCESS_MAX_RETRYTIMES = "tbs_download_success_max_retrytimes";
        public static final String KEY_DOWNLOAD_SUCCESS_RETRYTIMES = "tbs_download_success_retrytimes";
        public static final String KEY_FULL_PACKAGE = "request_full_package";
        public static final String KEY_INSTALL_INTERRUPT_CODE = "tbs_install_interrupt_code";
        public static final String KEY_IS_OVERSEA = "is_oversea";
        public static final String KEY_LAST_CHECK = "last_check";
        public static final String KEY_LAST_DOWNLOAD_DECOUPLE_CORE = "last_download_decouple_core";
        public static final String KEY_LAST_REQUEST_SUCCESS = "last_request_success";
        public static final String KEY_LAST_THIRDAPP_SENDREQUEST_COREVERSION = "last_thirdapp_sendrequest_coreversion";
        public static final String KEY_NEEDDOWNLOAD = "tbs_needdownload";
        public static final String KEY_REQUEST_FAIL = "request_fail";
        public static final String KEY_RESPONSECODE = "tbs_responsecode";
        public static final String KEY_RETRY_INTERVAL = "retry_interval";
        public static final String KEY_STOP_PRE_OAT = "tbs_stop_preoat";
        public static final String KEY_SWITCH_BACKUPCORE_ENABLE = "switch_backupcore_enable";
        public static final String KEY_TBSAPKFILESIZE = "tbs_apkfilesize";
        public static final String KEY_TBSAPK_MD5 = "tbs_apk_md5";
        public static final String KEY_TBSDOWNLOADURL = "tbs_downloadurl";
        public static final String KEY_TBSDOWNLOAD_FLOW = "tbs_downloadflow";
        public static final String KEY_TBSDOWNLOAD_STARTTIME = "tbs_downloadstarttime";
        public static final String KEY_TBS_CORE_LOAD_RENAME_FILE_LOCK_ENABLE = "tbs_core_load_rename_file_lock_enable";
        public static final String KEY_TBS_DOWNLOAD_V = "tbs_download_version";
        public static final String KEY_TBS_DOWNLOAD_V_TYPE = "tbs_download_version_type";
        public static final String KEY_USE_BACKUP_VERSION = "use_backup_version";
        public static final String KEY_USE_BUGLY = "tbs_use_bugly";
    }

    private TbsDownloadConfig(Context context) {
        this.mPreferences = context.getSharedPreferences("tbs_download_config", 4);
        this.c = context.getApplicationContext();
        if (this.c == null) {
            this.c = context;
        }
    }

    public static synchronized TbsDownloadConfig getInstance() {
        TbsDownloadConfig tbsDownloadConfig;
        synchronized (TbsDownloadConfig.class) {
            tbsDownloadConfig = b;
        }
        return tbsDownloadConfig;
    }

    public static synchronized TbsDownloadConfig getInstance(Context context) {
        TbsDownloadConfig tbsDownloadConfig;
        synchronized (TbsDownloadConfig.class) {
            if (b == null) {
                b = new TbsDownloadConfig(context);
            }
            tbsDownloadConfig = b;
        }
        return tbsDownloadConfig;
    }

    public void clear() {
        try {
            this.f9091a.clear();
            SharedPreferences.Editor edit = this.mPreferences.edit();
            edit.clear();
            edit.commit();
        } catch (Exception unused) {
        }
    }

    public synchronized void commit() {
        try {
            SharedPreferences.Editor edit = this.mPreferences.edit();
            for (String next : this.f9091a.keySet()) {
                Object obj = this.f9091a.get(next);
                if (obj instanceof String) {
                    edit.putString(next, (String) obj);
                } else if (obj instanceof Boolean) {
                    edit.putBoolean(next, ((Boolean) obj).booleanValue());
                } else if (obj instanceof Long) {
                    edit.putLong(next, ((Long) obj).longValue());
                } else if (obj instanceof Integer) {
                    edit.putInt(next, ((Integer) obj).intValue());
                } else if (obj instanceof Float) {
                    edit.putFloat(next, ((Float) obj).floatValue());
                }
            }
            edit.commit();
            this.f9091a.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }

    public synchronized int getDownloadFailedMaxRetrytimes() {
        int i;
        i = this.mPreferences.getInt(TbsConfigKey.KEY_DOWNLOAD_FAILED_MAX_RETRYTIMES, 0);
        if (i == 0) {
            i = 100;
        }
        return i;
    }

    public synchronized int getDownloadInterruptCode() {
        int i;
        if (!this.mPreferences.contains(TbsConfigKey.KEY_DOWNLOAD_INTERRUPT_CODE)) {
            try {
                i = !new File(new File(this.c.getFilesDir(), "shared_prefs"), "tbs_download_config").exists() ? -97 : !this.mPreferences.contains(TbsConfigKey.KEY_NEEDDOWNLOAD) ? -96 : -101;
            } catch (Throwable unused) {
                i = -95;
            }
        } else {
            i = this.mPreferences.getInt(TbsConfigKey.KEY_DOWNLOAD_INTERRUPT_CODE, -99);
            if (i == -119 || i == -121) {
                i = this.mPreferences.getInt(TbsConfigKey.KEY_DOWNLOAD_INTERRUPT_CODE_REASON, -119);
            }
            if (System.currentTimeMillis() - this.mPreferences.getLong(TbsConfigKey.KEY_DOWNLOAD_INTERRUPT_TIME, 0) > 86400000) {
                i -= 98000;
            }
        }
        return (this.c == null || !TbsConfig.APP_QQ.equals(this.c.getApplicationInfo().packageName) || "CN".equals(Locale.getDefault().getCountry())) ? (i * 1000) + this.mPreferences.getInt(TbsConfigKey.KEY_INSTALL_INTERRUPT_CODE, -1) : -320;
    }

    public synchronized long getDownloadMaxflow() {
        int i;
        i = this.mPreferences.getInt(TbsConfigKey.KEY_DOWNLOAD_MAXFLOW, 0);
        if (i == 0) {
            i = 20;
        }
        return ((long) (i * 1024)) * 1024;
    }

    public synchronized long getDownloadMinFreeSpace() {
        int i;
        i = this.mPreferences.getInt(TbsConfigKey.KEY_DOWNLOAD_MIN_FREE_SPACE, 0);
        if (i == 0) {
            i = 0;
        }
        return ((long) (i * 1024)) * 1024;
    }

    public synchronized long getDownloadSingleTimeout() {
        long j;
        j = this.mPreferences.getLong(TbsConfigKey.KEY_DOWNLOAD_SINGLE_TIMEOUT, 0);
        if (j == 0) {
            j = 1200000;
        }
        return j;
    }

    public synchronized int getDownloadSuccessMaxRetrytimes() {
        int i;
        i = this.mPreferences.getInt(TbsConfigKey.KEY_DOWNLOAD_SUCCESS_MAX_RETRYTIMES, 0);
        if (i == 0) {
            i = 3;
        }
        return i;
    }

    public synchronized long getRetryInterval() {
        if (TbsDownloader.getRetryIntervalInSeconds() >= 0) {
            return TbsDownloader.getRetryIntervalInSeconds();
        }
        return this.mPreferences.getLong(TbsConfigKey.KEY_RETRY_INTERVAL, 86400);
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean getTbsCoreLoadRenameFileLockEnable() {
        /*
            r3 = this;
            monitor-enter(r3)
            r0 = 1
            android.content.SharedPreferences r1 = r3.mPreferences     // Catch:{ Exception -> 0x000f, all -> 0x000c }
            java.lang.String r2 = "tbs_core_load_rename_file_lock_enable"
            boolean r1 = r1.getBoolean(r2, r0)     // Catch:{ Exception -> 0x000f, all -> 0x000c }
            r0 = r1
            goto L_0x000f
        L_0x000c:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        L_0x000f:
            monitor-exit(r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsDownloadConfig.getTbsCoreLoadRenameFileLockEnable():boolean");
    }

    public synchronized boolean isOverSea() {
        return this.mPreferences.getBoolean(TbsConfigKey.KEY_IS_OVERSEA, false);
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setDownloadInterruptCode(int r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            android.content.SharedPreferences r0 = r3.mPreferences     // Catch:{ Exception -> 0x001c, all -> 0x0019 }
            android.content.SharedPreferences$Editor r0 = r0.edit()     // Catch:{ Exception -> 0x001c, all -> 0x0019 }
            java.lang.String r1 = "tbs_download_interrupt_code"
            r0.putInt(r1, r4)     // Catch:{ Exception -> 0x001c, all -> 0x0019 }
            java.lang.String r4 = "tbs_download_interrupt_time"
            long r1 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x001c, all -> 0x0019 }
            r0.putLong(r4, r1)     // Catch:{ Exception -> 0x001c, all -> 0x0019 }
            r0.commit()     // Catch:{ Exception -> 0x001c, all -> 0x0019 }
            goto L_0x001c
        L_0x0019:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        L_0x001c:
            monitor-exit(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsDownloadConfig.setDownloadInterruptCode(int):void");
    }

    public synchronized void setInstallInterruptCode(int i) {
        SharedPreferences.Editor edit = this.mPreferences.edit();
        edit.putInt(TbsConfigKey.KEY_INSTALL_INTERRUPT_CODE, i);
        edit.commit();
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setTbsCoreLoadRenameFileLockEnable(boolean r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            android.content.SharedPreferences r0 = r2.mPreferences     // Catch:{ Exception -> 0x0013, all -> 0x0010 }
            android.content.SharedPreferences$Editor r0 = r0.edit()     // Catch:{ Exception -> 0x0013, all -> 0x0010 }
            java.lang.String r1 = "tbs_core_load_rename_file_lock_enable"
            r0.putBoolean(r1, r3)     // Catch:{ Exception -> 0x0013, all -> 0x0010 }
            r0.commit()     // Catch:{ Exception -> 0x0013, all -> 0x0010 }
            goto L_0x0013
        L_0x0010:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        L_0x0013:
            monitor-exit(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsDownloadConfig.setTbsCoreLoadRenameFileLockEnable(boolean):void");
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void uploadDownloadInterruptCodeIfNeeded(android.content.Context r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            if (r6 == 0) goto L_0x00a6
            java.lang.String r0 = "com.tencent.mm"
            android.content.Context r1 = r6.getApplicationContext()     // Catch:{ Throwable -> 0x00a6, all -> 0x00a3 }
            android.content.pm.ApplicationInfo r1 = r1.getApplicationInfo()     // Catch:{ Throwable -> 0x00a6, all -> 0x00a3 }
            java.lang.String r1 = r1.packageName     // Catch:{ Throwable -> 0x00a6, all -> 0x00a3 }
            boolean r0 = r0.equals(r1)     // Catch:{ Throwable -> 0x00a6, all -> 0x00a3 }
            if (r0 == 0) goto L_0x00a6
            r0 = 1
            android.content.SharedPreferences r1 = r5.mPreferences     // Catch:{ Throwable -> 0x00a6, all -> 0x00a3 }
            java.lang.String r2 = "tbs_download_interrupt_code"
            boolean r1 = r1.contains(r2)     // Catch:{ Throwable -> 0x00a6, all -> 0x00a3 }
            if (r1 != 0) goto L_0x0050
            java.io.File r1 = new java.io.File     // Catch:{ Throwable -> 0x004d, all -> 0x00a3 }
            java.io.File r2 = new java.io.File     // Catch:{ Throwable -> 0x004d, all -> 0x00a3 }
            android.content.Context r3 = r5.c     // Catch:{ Throwable -> 0x004d, all -> 0x00a3 }
            java.io.File r3 = r3.getFilesDir()     // Catch:{ Throwable -> 0x004d, all -> 0x00a3 }
            java.lang.String r4 = "shared_prefs"
            r2.<init>(r3, r4)     // Catch:{ Throwable -> 0x004d, all -> 0x00a3 }
            java.lang.String r3 = "tbs_download_config"
            r1.<init>(r2, r3)     // Catch:{ Throwable -> 0x004d, all -> 0x00a3 }
            boolean r1 = r1.exists()     // Catch:{ Throwable -> 0x004d, all -> 0x00a3 }
            if (r1 != 0) goto L_0x003d
            r1 = -97
            goto L_0x0076
        L_0x003d:
            android.content.SharedPreferences r1 = r5.mPreferences     // Catch:{ Throwable -> 0x004d, all -> 0x00a3 }
            java.lang.String r2 = "tbs_needdownload"
            boolean r1 = r1.contains(r2)     // Catch:{ Throwable -> 0x004d, all -> 0x00a3 }
            if (r1 != 0) goto L_0x004a
            r1 = -96
            goto L_0x0076
        L_0x004a:
            r1 = -101(0xffffffffffffff9b, float:NaN)
            goto L_0x0076
        L_0x004d:
            r1 = -95
            goto L_0x0076
        L_0x0050:
            android.content.SharedPreferences r1 = r5.mPreferences     // Catch:{ Throwable -> 0x00a6, all -> 0x00a3 }
            java.lang.String r2 = "tbs_download_interrupt_code"
            r3 = -99
            int r1 = r1.getInt(r2, r3)     // Catch:{ Throwable -> 0x00a6, all -> 0x00a3 }
            r2 = -206(0xffffffffffffff32, float:NaN)
            if (r1 > r2) goto L_0x0063
            r2 = -219(0xffffffffffffff25, float:NaN)
            if (r1 < r2) goto L_0x0063
            goto L_0x0076
        L_0x0063:
            r2 = -302(0xfffffffffffffed2, float:NaN)
            if (r1 > r2) goto L_0x006c
            r2 = -316(0xfffffffffffffec4, float:NaN)
            if (r1 < r2) goto L_0x006c
            goto L_0x0076
        L_0x006c:
            r2 = -318(0xfffffffffffffec2, float:NaN)
            if (r1 > r2) goto L_0x0075
            r2 = -322(0xfffffffffffffebe, float:NaN)
            if (r1 < r2) goto L_0x0075
            goto L_0x0076
        L_0x0075:
            r0 = 0
        L_0x0076:
            if (r0 == 0) goto L_0x00a6
            com.tencent.smtt.sdk.TbsLogReport r0 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r6)     // Catch:{ Throwable -> 0x00a6, all -> 0x00a3 }
            com.tencent.smtt.sdk.TbsLogReport$TbsLogInfo r0 = r0.a()     // Catch:{ Throwable -> 0x00a6, all -> 0x00a3 }
            r2 = 128(0x80, float:1.794E-43)
            r0.setErrorCode(r2)     // Catch:{ Throwable -> 0x00a6, all -> 0x00a3 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00a6, all -> 0x00a3 }
            r2.<init>()     // Catch:{ Throwable -> 0x00a6, all -> 0x00a3 }
            java.lang.String r3 = " "
            r2.append(r3)     // Catch:{ Throwable -> 0x00a6, all -> 0x00a3 }
            r2.append(r1)     // Catch:{ Throwable -> 0x00a6, all -> 0x00a3 }
            java.lang.String r1 = r2.toString()     // Catch:{ Throwable -> 0x00a6, all -> 0x00a3 }
            r0.setFailDetail((java.lang.String) r1)     // Catch:{ Throwable -> 0x00a6, all -> 0x00a3 }
            com.tencent.smtt.sdk.TbsLogReport r6 = com.tencent.smtt.sdk.TbsLogReport.a((android.content.Context) r6)     // Catch:{ Throwable -> 0x00a6, all -> 0x00a3 }
            com.tencent.smtt.sdk.TbsLogReport$EventType r1 = com.tencent.smtt.sdk.TbsLogReport.EventType.TYPE_DOWNLOAD     // Catch:{ Throwable -> 0x00a6, all -> 0x00a3 }
            r6.a((com.tencent.smtt.sdk.TbsLogReport.EventType) r1, (com.tencent.smtt.sdk.TbsLogReport.TbsLogInfo) r0)     // Catch:{ Throwable -> 0x00a6, all -> 0x00a3 }
            goto L_0x00a6
        L_0x00a3:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        L_0x00a6:
            monitor-exit(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.TbsDownloadConfig.uploadDownloadInterruptCodeIfNeeded(android.content.Context):void");
    }
}
