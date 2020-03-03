package com.xiaomi.shop2.util;

import android.app.ActivityManager;
import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import com.xiaomi.accountsdk.service.DeviceInfoResult;
import com.xiaomi.mishopsdk.ShopApp;
import com.xiaomi.mishopsdk.util.AndroidUtil;
import com.xiaomi.mishopsdk.util.Coder;
import com.xiaomi.mishopsdk.util.Installation;
import com.xiaomi.mobilestats.StatService;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class DeviceUtil {
    private static final String TAG = "DeviceUtil";
    private static final String UNIQUEID = "UNIQUEID";
    private static String sCurrentInstructionSet;
    private static String sID;
    private Context mContext;

    public static native String getDSidNative();

    public static native String getDTokenNative();

    private static class SingletonHolder {
        /* access modifiers changed from: private */
        public static DeviceUtil INSTANCE = new DeviceUtil();

        private SingletonHolder() {
        }
    }

    public static DeviceUtil getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void preInit(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public void init() {
        AndroidUtil.sStageQueue.postRunnable(new Runnable() {
            public void run() {
                DeviceUtil.this.getUniqueId();
            }
        });
    }

    private String generateUniqueId() {
        return new UUID((long) ("" + Settings.Secure.getString(this.mContext.getContentResolver(), DeviceInfoResult.BUNDLE_KEY_ANDROID_ID)).hashCode(), ((long) ("" + Device.SIM_SERIAL_NO).hashCode()) | (((long) ("" + Device.IMEI).hashCode()) << 32)).toString();
    }

    private String getDeviceId() {
        String str = Device.IMEI;
        if (!TextUtils.isEmpty(str)) {
            return Coder.encodeMD5(str);
        }
        String string = Settings.Secure.getString(this.mContext.getContentResolver(), DeviceInfoResult.BUNDLE_KEY_ANDROID_ID);
        if (string != null) {
            return string;
        }
        return Installation.id(this.mContext);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0039, code lost:
        return getDeviceId();
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x0034 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.lang.String getUniqueId() {
        /*
            r3 = this;
            monitor-enter(r3)
            java.lang.String r0 = sID     // Catch:{ all -> 0x003e }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x003e }
            if (r0 == 0) goto L_0x003a
            java.io.File r0 = new java.io.File     // Catch:{ all -> 0x003e }
            android.content.Context r1 = r3.mContext     // Catch:{ all -> 0x003e }
            java.io.File r1 = r1.getFilesDir()     // Catch:{ all -> 0x003e }
            java.lang.String r2 = "UNIQUEID"
            r0.<init>(r1, r2)     // Catch:{ all -> 0x003e }
            boolean r1 = r0.exists()     // Catch:{ Exception -> 0x0034 }
            if (r1 == 0) goto L_0x0022
            java.lang.String r1 = r3.readInstallationFile(r0)     // Catch:{ Exception -> 0x0034 }
            sID = r1     // Catch:{ Exception -> 0x0034 }
        L_0x0022:
            java.lang.String r1 = sID     // Catch:{ Exception -> 0x0034 }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Exception -> 0x0034 }
            if (r1 == 0) goto L_0x003a
            r3.writeInstallationFile(r0)     // Catch:{ Exception -> 0x0034 }
            java.lang.String r0 = r3.readInstallationFile(r0)     // Catch:{ Exception -> 0x0034 }
            sID = r0     // Catch:{ Exception -> 0x0034 }
            goto L_0x003a
        L_0x0034:
            java.lang.String r0 = r3.getDeviceId()     // Catch:{ all -> 0x003e }
            monitor-exit(r3)
            return r0
        L_0x003a:
            java.lang.String r0 = sID     // Catch:{ all -> 0x003e }
            monitor-exit(r3)
            return r0
        L_0x003e:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.shop2.util.DeviceUtil.getUniqueId():java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String readInstallationFile(java.io.File r5) throws java.io.IOException {
        /*
            r4 = this;
            r0 = 0
            java.io.RandomAccessFile r1 = new java.io.RandomAccessFile     // Catch:{ all -> 0x001f }
            java.lang.String r2 = "r"
            r1.<init>(r5, r2)     // Catch:{ all -> 0x001f }
            long r2 = r1.length()     // Catch:{ all -> 0x001d }
            int r5 = (int) r2     // Catch:{ all -> 0x001d }
            byte[] r5 = new byte[r5]     // Catch:{ all -> 0x001d }
            r1.readFully(r5)     // Catch:{ all -> 0x001d }
            java.lang.String r0 = new java.lang.String     // Catch:{ all -> 0x001d }
            java.lang.String r2 = "UTF-8"
            r0.<init>(r5, r2)     // Catch:{ all -> 0x001d }
            r1.close()
            return r0
        L_0x001d:
            r5 = move-exception
            goto L_0x0021
        L_0x001f:
            r5 = move-exception
            r1 = r0
        L_0x0021:
            if (r1 == 0) goto L_0x0026
            r1.close()
        L_0x0026:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.shop2.util.DeviceUtil.readInstallationFile(java.io.File):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x001d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void writeInstallationFile(java.io.File r3) throws java.io.IOException {
        /*
            r2 = this;
            r0 = 0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ all -> 0x0019 }
            r1.<init>(r3)     // Catch:{ all -> 0x0019 }
            java.lang.String r3 = r2.generateUniqueId()     // Catch:{ all -> 0x0017 }
            java.lang.String r0 = "UTF-8"
            byte[] r3 = r3.getBytes(r0)     // Catch:{ all -> 0x0017 }
            r1.write(r3)     // Catch:{ all -> 0x0017 }
            r1.close()
            return
        L_0x0017:
            r3 = move-exception
            goto L_0x001b
        L_0x0019:
            r3 = move-exception
            r1 = r0
        L_0x001b:
            if (r1 == 0) goto L_0x0020
            r1.close()
        L_0x0020:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.shop2.util.DeviceUtil.writeInstallationFile(java.io.File):void");
    }

    public static String getProcessName(Context context, int i) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo next : runningAppProcesses) {
            if (next.pid == i) {
                return next.processName;
            }
        }
        return null;
    }

    public static String getMetaData(String str) {
        if (ShopApp.instance == null) {
            return "";
        }
        try {
            return ShopApp.instance.getPackageManager().getApplicationInfo(ShopApp.instance.getPackageName(), 128).metaData.getString(str);
        } catch (Exception unused) {
            return "";
        }
    }

    public static String getDToken() {
        try {
            return getDTokenNative();
        } catch (Throwable th) {
            Exception exc = new Exception(th.getMessage());
            exc.setStackTrace(th.getStackTrace());
            StatService.onError(ShopApp.instance, exc, new HashMap<String, String>() {
                {
                    put("getDTokenNativeFailed", "getDToken");
                }
            });
            return "";
        }
    }

    public static String getDSid() {
        try {
            return getDSidNative();
        } catch (Throwable th) {
            Exception exc = new Exception(th.getMessage());
            exc.setStackTrace(th.getStackTrace());
            StatService.onError(ShopApp.instance, exc, new HashMap<String, String>() {
                {
                    put("getDSidNativeFailed", "getDSidNative");
                }
            });
            return "";
        }
    }

    public static String getCurrentInstructionSet() {
        if (sCurrentInstructionSet != null) {
            return sCurrentInstructionSet;
        }
        try {
            sCurrentInstructionSet = (String) Class.forName("dalvik.system.VMRuntime").getDeclaredMethod("getCurrentInstructionSet", new Class[0]).invoke((Object) null, new Object[0]);
        } catch (Exception unused) {
            sCurrentInstructionSet = "";
        }
        return sCurrentInstructionSet;
    }
}
