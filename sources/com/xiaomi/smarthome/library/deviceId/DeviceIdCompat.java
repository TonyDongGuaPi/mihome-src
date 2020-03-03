package com.xiaomi.smarthome.library.deviceId;

import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import com.xiaomi.accountsdk.service.DeviceInfoResult;

public class DeviceIdCompat {

    /* renamed from: a  reason: collision with root package name */
    private static String f19095a = "";
    private static final String b = "spfs_uuid";
    private static final String c = "spfs_uuid_key";
    private static final String d = "DeviceIdCompat";

    private DeviceIdCompat() {
    }

    public static String a(Context context) {
        if (a(f19095a)) {
            return f19095a;
        }
        if (context == null) {
            return "";
        }
        String a2 = IdentifierManager.a(context);
        if (!a(a2)) {
            a2 = b(context);
        }
        if (!a(a2)) {
            a2 = c(context);
        }
        f19095a = a2;
        return a2;
    }

    private static String b(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), DeviceInfoResult.BUNDLE_KEY_ANDROID_ID);
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x0035 */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0053 A[Catch:{ Exception -> 0x0075 }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0118 A[Catch:{ Exception -> 0x0120 }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x011d A[Catch:{ Exception -> 0x0120 }] */
    @android.annotation.SuppressLint({"MissingPermission"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String c(android.content.Context r6) {
        /*
            java.lang.String r0 = f19095a
            boolean r0 = a((java.lang.String) r0)
            if (r0 == 0) goto L_0x000b
            java.lang.String r6 = f19095a
            return r6
        L_0x000b:
            r0 = 0
            r1 = 28
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x0035 }
            if (r2 <= r1) goto L_0x0035
            java.lang.String r2 = "spfs_uuid"
            android.content.SharedPreferences r2 = r6.getSharedPreferences(r2, r0)     // Catch:{ Exception -> 0x0035 }
            java.lang.String r3 = "spfs_uuid_key"
            java.lang.String r4 = ""
            java.lang.String r2 = r2.getString(r3, r4)     // Catch:{ Exception -> 0x0035 }
            boolean r3 = a((java.lang.String) r2)     // Catch:{ Exception -> 0x0035 }
            if (r3 == 0) goto L_0x0035
            byte[] r2 = r2.getBytes()     // Catch:{ Exception -> 0x0035 }
            java.util.UUID r2 = java.util.UUID.nameUUIDFromBytes(r2)     // Catch:{ Exception -> 0x0035 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x0035 }
            f19095a = r2     // Catch:{ Exception -> 0x0035 }
            return r2
        L_0x0035:
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x0075 }
            if (r2 > r1) goto L_0x0075
            java.lang.String r1 = "android.permission.READ_PHONE_STATE"
            int r1 = android.support.v4.content.ContextCompat.checkSelfPermission(r6, r1)     // Catch:{ Exception -> 0x0075 }
            if (r1 != 0) goto L_0x0075
            java.lang.String r1 = "phone"
            java.lang.Object r1 = r6.getSystemService(r1)     // Catch:{ Exception -> 0x0075 }
            android.telephony.TelephonyManager r1 = (android.telephony.TelephonyManager) r1     // Catch:{ Exception -> 0x0075 }
            java.lang.String r1 = r1.getDeviceId()     // Catch:{ Exception -> 0x0075 }
            boolean r2 = a((java.lang.String) r1)     // Catch:{ Exception -> 0x0075 }
            if (r2 == 0) goto L_0x0075
            java.lang.String r2 = "spfs_uuid"
            android.content.SharedPreferences r6 = r6.getSharedPreferences(r2, r0)     // Catch:{ Exception -> 0x0075 }
            android.content.SharedPreferences$Editor r6 = r6.edit()     // Catch:{ Exception -> 0x0075 }
            java.lang.String r0 = "spfs_uuid_key"
            android.content.SharedPreferences$Editor r6 = r6.putString(r0, r1)     // Catch:{ Exception -> 0x0075 }
            r6.commit()     // Catch:{ Exception -> 0x0075 }
            byte[] r6 = r1.getBytes()     // Catch:{ Exception -> 0x0075 }
            java.util.UUID r6 = java.util.UUID.nameUUIDFromBytes(r6)     // Catch:{ Exception -> 0x0075 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x0075 }
            f19095a = r6     // Catch:{ Exception -> 0x0075 }
            return r6
        L_0x0075:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r0 = "35"
            r6.append(r0)
            java.lang.String r0 = android.os.Build.BOARD
            int r0 = r0.length()
            int r0 = r0 % 10
            r6.append(r0)
            java.lang.String r0 = android.os.Build.BRAND
            int r0 = r0.length()
            int r0 = r0 % 10
            r6.append(r0)
            java.lang.String r0 = android.os.Build.CPU_ABI
            int r0 = r0.length()
            int r0 = r0 % 10
            r6.append(r0)
            java.lang.String r0 = android.os.Build.DEVICE
            int r0 = r0.length()
            int r0 = r0 % 10
            r6.append(r0)
            java.lang.String r0 = android.os.Build.DISPLAY
            int r0 = r0.length()
            int r0 = r0 % 10
            r6.append(r0)
            java.lang.String r0 = android.os.Build.HOST
            int r0 = r0.length()
            int r0 = r0 % 10
            r6.append(r0)
            java.lang.String r0 = android.os.Build.ID
            int r0 = r0.length()
            int r0 = r0 % 10
            r6.append(r0)
            java.lang.String r0 = android.os.Build.MANUFACTURER
            int r0 = r0.length()
            int r0 = r0 % 10
            r6.append(r0)
            java.lang.String r0 = android.os.Build.MODEL
            int r0 = r0.length()
            int r0 = r0 % 10
            r6.append(r0)
            java.lang.String r0 = android.os.Build.PRODUCT
            int r0 = r0.length()
            int r0 = r0 % 10
            r6.append(r0)
            java.lang.String r0 = android.os.Build.TAGS
            int r0 = r0.length()
            int r0 = r0 % 10
            r6.append(r0)
            java.lang.String r0 = android.os.Build.TYPE
            int r0 = r0.length()
            int r0 = r0 % 10
            r6.append(r0)
            java.lang.String r0 = android.os.Build.USER
            int r0 = r0.length()
            int r0 = r0 % 10
            r6.append(r0)
            java.lang.String r6 = r6.toString()
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x0120 }
            r1 = 26
            if (r0 < r1) goto L_0x011d
            java.lang.String r0 = android.os.Build.getSerial()     // Catch:{ Exception -> 0x0120 }
            goto L_0x0122
        L_0x011d:
            java.lang.String r0 = android.os.Build.SERIAL     // Catch:{ Exception -> 0x0120 }
            goto L_0x0122
        L_0x0120:
            java.lang.String r0 = "serial"
        L_0x0122:
            java.util.UUID r1 = new java.util.UUID
            int r6 = r6.hashCode()
            long r2 = (long) r6
            int r6 = r0.hashCode()
            long r4 = (long) r6
            r1.<init>(r2, r4)
            java.lang.String r6 = r1.toString()
            f19095a = r6
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.library.deviceId.DeviceIdCompat.c(android.content.Context):java.lang.String");
    }

    private static boolean a(String str) {
        return !TextUtils.isEmpty(str) && !"unknow".equalsIgnoreCase(str) && !"null".equalsIgnoreCase(str);
    }
}
