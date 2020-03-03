package com.xiaomi.youpin.util;

import android.app.ActivityManager;
import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import com.xiaomi.accountsdk.service.DeviceInfoResult;
import java.util.List;
import java.util.UUID;

public class DeviceUtil {
    private static String b = null;
    private static final String c = "UNIQUEID";

    /* renamed from: a  reason: collision with root package name */
    private Context f23765a;

    public static native String getDSid();

    public static native String getDToken();

    static {
        System.loadLibrary("DToken");
    }

    private static class SingletonHolder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public static DeviceUtil f23766a = new DeviceUtil();

        private SingletonHolder() {
        }
    }

    public static DeviceUtil a() {
        return SingletonHolder.f23766a;
    }

    public void a(Context context) {
        this.f23765a = context.getApplicationContext();
    }

    private String d() {
        TelephonyManager telephonyManager = (TelephonyManager) this.f23765a.getSystemService("phone");
        return new UUID((long) ("" + Settings.Secure.getString(this.f23765a.getContentResolver(), DeviceInfoResult.BUNDLE_KEY_ANDROID_ID)).hashCode(), (((long) ("" + telephonyManager.getDeviceId()).hashCode()) << 32) | ((long) ("" + telephonyManager.getSimSerialNumber()).hashCode())).toString();
    }

    public String b() {
        TelephonyManager telephonyManager = (TelephonyManager) this.f23765a.getSystemService("phone");
        String deviceId = telephonyManager != null ? telephonyManager.getDeviceId() : null;
        if (deviceId != null) {
            return Coder.a(deviceId);
        }
        String string = Settings.Secure.getString(this.f23765a.getContentResolver(), DeviceInfoResult.BUNDLE_KEY_ANDROID_ID);
        if (string != null) {
            return string;
        }
        return Installation.a(this.f23765a);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0027, code lost:
        return b();
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0022 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.lang.String c() {
        /*
            r3 = this;
            monitor-enter(r3)
            java.lang.String r0 = b     // Catch:{ all -> 0x002c }
            if (r0 != 0) goto L_0x0028
            java.io.File r0 = new java.io.File     // Catch:{ all -> 0x002c }
            android.content.Context r1 = r3.f23765a     // Catch:{ all -> 0x002c }
            java.io.File r1 = r1.getFilesDir()     // Catch:{ all -> 0x002c }
            java.lang.String r2 = "UNIQUEID"
            r0.<init>(r1, r2)     // Catch:{ all -> 0x002c }
            boolean r1 = r0.exists()     // Catch:{ Exception -> 0x0022 }
            if (r1 != 0) goto L_0x001b
            r3.b(r0)     // Catch:{ Exception -> 0x0022 }
        L_0x001b:
            java.lang.String r0 = r3.a((java.io.File) r0)     // Catch:{ Exception -> 0x0022 }
            b = r0     // Catch:{ Exception -> 0x0022 }
            goto L_0x0028
        L_0x0022:
            java.lang.String r0 = r3.b()     // Catch:{ all -> 0x002c }
            monitor-exit(r3)
            return r0
        L_0x0028:
            java.lang.String r0 = b     // Catch:{ all -> 0x002c }
            monitor-exit(r3)
            return r0
        L_0x002c:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.util.DeviceUtil.c():java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String a(java.io.File r5) throws java.io.IOException {
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
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.util.DeviceUtil.a(java.io.File):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x001d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void b(java.io.File r3) throws java.io.IOException {
        /*
            r2 = this;
            r0 = 0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ all -> 0x0019 }
            r1.<init>(r3)     // Catch:{ all -> 0x0019 }
            java.lang.String r3 = r2.d()     // Catch:{ all -> 0x0017 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.util.DeviceUtil.b(java.io.File):void");
    }

    public static String a(Context context, int i) {
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
}
