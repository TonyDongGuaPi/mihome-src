package com.xiaomi.mobilestats.common;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.xiaomi.accountsdk.service.DeviceInfoResult;
import java.io.File;
import java.lang.reflect.Method;
import java.util.UUID;

public class DeviceUtil {
    private static String A;
    private Context mContext;

    private String a() {
        return new UUID((long) ("" + Settings.Secure.getString(this.mContext.getContentResolver(), DeviceInfoResult.BUNDLE_KEY_ANDROID_ID)).hashCode(), ((long) ("" + i(this.mContext)).hashCode()) | (((long) ("" + h(this.mContext)).hashCode()) << 32)).toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String a(java.io.File r5) {
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
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mobilestats.common.DeviceUtil.a(java.io.File):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x001d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void b(java.io.File r3) {
        /*
            r2 = this;
            r0 = 0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ all -> 0x0019 }
            r1.<init>(r3)     // Catch:{ all -> 0x0019 }
            java.lang.String r3 = r2.a()     // Catch:{ all -> 0x0017 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mobilestats.common.DeviceUtil.b(java.io.File):void");
    }

    public static DeviceUtil getInstance() {
        return c.B;
    }

    private static String h(Context context) {
        String str;
        String str2;
        Exception e;
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        try {
            str = telephonyManager.getDeviceId();
            try {
                if (!TextUtils.isEmpty(str) && str.startsWith("86")) {
                    return str;
                }
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                Method declaredMethod = telephonyManager.getClass().getDeclaredMethod("getImei", new Class[]{Integer.TYPE});
                declaredMethod.setAccessible(true);
                str2 = (String) declaredMethod.invoke(telephonyManager, new Object[]{0});
                return str2;
            }
        } catch (Exception e3) {
            Exception exc = e3;
            str = "000000000000000";
            e = exc;
            e.printStackTrace();
            Method declaredMethod2 = telephonyManager.getClass().getDeclaredMethod("getImei", new Class[]{Integer.TYPE});
            declaredMethod2.setAccessible(true);
            str2 = (String) declaredMethod2.invoke(telephonyManager, new Object[]{0});
            return str2;
        }
        try {
            Method declaredMethod22 = telephonyManager.getClass().getDeclaredMethod("getImei", new Class[]{Integer.TYPE});
            declaredMethod22.setAccessible(true);
            str2 = (String) declaredMethod22.invoke(telephonyManager, new Object[]{0});
            if (!TextUtils.isEmpty(str2) && str2.startsWith("86")) {
                return str2;
            }
        } catch (Exception e4) {
            e4.printStackTrace();
        }
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                for (int i = 0; i < telephonyManager.getPhoneCount(); i++) {
                    String deviceId = telephonyManager.getDeviceId(i);
                    if (!TextUtils.isEmpty(deviceId) && deviceId.startsWith("86")) {
                        return deviceId;
                    }
                }
            }
        } catch (Exception e5) {
            e5.printStackTrace();
        }
        return TextUtils.isEmpty(str) ? "000000000000000" : str;
    }

    private static String i(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getSimSerialNumber();
        } catch (Exception e) {
            e.printStackTrace();
            return "000000000000000";
        }
    }

    public synchronized String getUniqueId(Context context) {
        init(context);
        if (A == null) {
            File file = new File(this.mContext.getFilesDir(), "UNIQUEID");
            try {
                if (!file.exists()) {
                    b(file);
                }
                A = a(file);
            } catch (Exception unused) {
                return "no_id";
            }
        }
        if (A == null) {
            A = "";
        }
        return A;
    }

    public void init(Context context) {
        if (context != null) {
            this.mContext = context.getApplicationContext();
        }
    }
}
