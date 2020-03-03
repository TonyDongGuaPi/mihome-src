package com.mi.mistatistic.sdk.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.mi.mistatistic.sdk.MiStatInterface;
import com.taobao.weex.annotation.JSMethod;

public class GaUtil {

    /* renamed from: a  reason: collision with root package name */
    public static final String f7373a = "GaClientId";

    /* JADX WARNING: Removed duplicated region for block: B:38:0x004f A[SYNTHETIC, Splitter:B:38:0x004f] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0056 A[SYNTHETIC, Splitter:B:44:0x0056] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x005d A[SYNTHETIC, Splitter:B:52:0x005d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(android.content.Context r6) {
        /*
            r0 = 0
            java.lang.String r1 = "gaClientId"
            java.io.FileInputStream r1 = r6.openFileInput(r1)     // Catch:{ FileNotFoundException -> 0x005a, IOException -> 0x0047, all -> 0x0044 }
            r2 = 36
            byte[] r3 = new byte[r2]     // Catch:{ FileNotFoundException -> 0x005b, IOException -> 0x0048 }
            r4 = 0
            int r2 = r1.read(r3, r4, r2)     // Catch:{ FileNotFoundException -> 0x005b, IOException -> 0x0048 }
            int r5 = r1.available()     // Catch:{ FileNotFoundException -> 0x005b, IOException -> 0x0048 }
            if (r5 <= 0) goto L_0x0024
            r1.close()     // Catch:{ FileNotFoundException -> 0x005b, IOException -> 0x0048 }
            java.lang.String r2 = "gaClientId"
            r6.deleteFile(r2)     // Catch:{ FileNotFoundException -> 0x005b, IOException -> 0x0048 }
            if (r1 == 0) goto L_0x0023
            r1.close()     // Catch:{ IOException -> 0x0023 }
        L_0x0023:
            return r0
        L_0x0024:
            r5 = 14
            if (r2 < r5) goto L_0x0036
            r1.close()     // Catch:{ FileNotFoundException -> 0x005b, IOException -> 0x0048 }
            java.lang.String r5 = new java.lang.String     // Catch:{ FileNotFoundException -> 0x005b, IOException -> 0x0048 }
            r5.<init>(r3, r4, r2)     // Catch:{ FileNotFoundException -> 0x005b, IOException -> 0x0048 }
            if (r1 == 0) goto L_0x0035
            r1.close()     // Catch:{ IOException -> 0x0035 }
        L_0x0035:
            return r5
        L_0x0036:
            r1.close()     // Catch:{ FileNotFoundException -> 0x005b, IOException -> 0x0048 }
            java.lang.String r2 = "gaClientId"
            r6.deleteFile(r2)     // Catch:{ FileNotFoundException -> 0x005b, IOException -> 0x0048 }
            if (r1 == 0) goto L_0x0043
            r1.close()     // Catch:{ IOException -> 0x0043 }
        L_0x0043:
            return r0
        L_0x0044:
            r6 = move-exception
            r1 = r0
            goto L_0x0054
        L_0x0047:
            r1 = r0
        L_0x0048:
            java.lang.String r2 = "gaClientId"
            r6.deleteFile(r2)     // Catch:{ all -> 0x0053 }
            if (r1 == 0) goto L_0x0052
            r1.close()     // Catch:{ IOException -> 0x0052 }
        L_0x0052:
            return r0
        L_0x0053:
            r6 = move-exception
        L_0x0054:
            if (r1 == 0) goto L_0x0059
            r1.close()     // Catch:{ IOException -> 0x0059 }
        L_0x0059:
            throw r6
        L_0x005a:
            r1 = r0
        L_0x005b:
            if (r1 == 0) goto L_0x0060
            r1.close()     // Catch:{ IOException -> 0x0060 }
        L_0x0060:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.mistatistic.sdk.util.GaUtil.a(android.content.Context):java.lang.String");
    }

    public static void a(Context context, String str, String str2) {
        String str3 = JSMethod.NOT_SET + str2;
        if (!a(context, f7373a + str3, false)) {
            String a2 = a(context);
            if (!TextUtils.isEmpty(a2)) {
                MiStatInterface.a(f7373a + str3, str, "gaClientId", a2);
                b(context, f7373a + str3, true);
            }
        }
    }

    public static boolean a(Context context, String str, boolean z) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return defaultSharedPreferences != null ? defaultSharedPreferences.getBoolean(str, z) : z;
    }

    public static void b(Context context, String str, boolean z) {
        SharedPreferences.Editor edit;
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (defaultSharedPreferences != null && (edit = defaultSharedPreferences.edit()) != null) {
            edit.putBoolean(str, z);
            edit.apply();
        }
    }
}
