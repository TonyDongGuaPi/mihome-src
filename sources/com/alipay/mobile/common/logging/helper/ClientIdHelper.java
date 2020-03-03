package com.alipay.mobile.common.logging.helper;

import android.text.TextUtils;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class ClientIdHelper {
    private boolean a(byte b) {
        return (b >= 48 && b <= 57) || (b >= 97 && b <= 122) || (b >= 65 && b <= 90);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x005a, code lost:
        if (r4.startsWith(r5) == false) goto L_0x005e;
     */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0094  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String initClientId(android.content.Context r8) {
        /*
            r7 = this;
            com.alipay.mobile.common.logging.util.LoggingSPCache r0 = com.alipay.mobile.common.logging.util.LoggingSPCache.getInstance()
            java.lang.String r1 = "clientID"
            r2 = 0
            java.lang.String r0 = r0.getString(r1, r2)
            java.lang.String r1 = ""
            java.lang.String r2 = ""
            java.lang.String r3 = "android.permission.READ_PHONE_STATE"
            int r3 = r8.checkCallingOrSelfPermission(r3)     // Catch:{ Throwable -> 0x002d }
            if (r3 != 0) goto L_0x0035
            java.lang.String r3 = "phone"
            java.lang.Object r8 = r8.getSystemService(r3)     // Catch:{ Throwable -> 0x002d }
            android.telephony.TelephonyManager r8 = (android.telephony.TelephonyManager) r8     // Catch:{ Throwable -> 0x002d }
            java.lang.String r3 = r8.getDeviceId()     // Catch:{ Throwable -> 0x002d }
            java.lang.String r8 = r8.getSubscriberId()     // Catch:{ Throwable -> 0x002a }
            r2 = r8
            r1 = r3
            goto L_0x0035
        L_0x002a:
            r8 = move-exception
            r1 = r3
            goto L_0x002e
        L_0x002d:
            r8 = move-exception
        L_0x002e:
            java.lang.String r3 = "ClientIdHelper"
            java.lang.String r4 = "initClientId"
            android.util.Log.e(r3, r4, r8)
        L_0x0035:
            boolean r8 = r7.b(r0)
            if (r8 == 0) goto L_0x0094
            r8 = 0
            r3 = 15
            java.lang.String r4 = r0.substring(r8, r3)
            boolean r5 = r7.a((java.lang.String) r2)
            if (r5 == 0) goto L_0x005d
            java.lang.String r5 = r7.d(r2)
            int r6 = r5.length()
            if (r6 <= r3) goto L_0x0056
            java.lang.String r5 = r5.substring(r8, r3)
        L_0x0056:
            boolean r5 = r4.startsWith(r5)
            if (r5 != 0) goto L_0x005d
            goto L_0x005e
        L_0x005d:
            r2 = r4
        L_0x005e:
            int r4 = r0.length()
            int r4 = r4 - r3
            int r5 = r0.length()
            java.lang.String r0 = r0.substring(r4, r5)
            boolean r4 = r7.a((java.lang.String) r1)
            if (r4 == 0) goto L_0x0086
            java.lang.String r4 = r7.d(r1)
            int r5 = r4.length()
            if (r5 <= r3) goto L_0x007f
            java.lang.String r4 = r4.substring(r8, r3)
        L_0x007f:
            boolean r8 = r0.startsWith(r4)
            if (r8 != 0) goto L_0x0086
            r0 = r1
        L_0x0086:
            java.lang.String r8 = r7.a(r2, r0)
            com.alipay.mobile.common.logging.util.LoggingSPCache r0 = com.alipay.mobile.common.logging.util.LoggingSPCache.getInstance()
            java.lang.String r1 = "clientID"
            r0.putString(r1, r8)
            goto L_0x00b5
        L_0x0094:
            boolean r8 = r7.a((java.lang.String) r1)
            if (r8 != 0) goto L_0x009e
            java.lang.String r1 = r7.a()
        L_0x009e:
            boolean r8 = r7.a((java.lang.String) r2)
            if (r8 != 0) goto L_0x00a8
            java.lang.String r2 = r7.a()
        L_0x00a8:
            java.lang.String r8 = r7.a(r2, r1)
            com.alipay.mobile.common.logging.util.LoggingSPCache r0 = com.alipay.mobile.common.logging.util.LoggingSPCache.getInstance()
            java.lang.String r1 = "clientID"
            r0.putString(r1, r8)
        L_0x00b5:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.mobile.common.logging.helper.ClientIdHelper.initClientId(android.content.Context):java.lang.String");
    }

    private String a() {
        return new SimpleDateFormat("yyMMddHHmmssSSS", Locale.getDefault()).format(Long.valueOf(System.currentTimeMillis()));
    }

    private boolean a(String str) {
        if (str == null || str.trim().length() == 0) {
            return false;
        }
        String trim = str.trim();
        if (trim.equalsIgnoreCase("unknown") || trim.equalsIgnoreCase("null") || trim.matches("[0]+") || trim.length() <= 5) {
            return false;
        }
        return true;
    }

    private boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.matches("[[a-z][A-Z][0-9]]{15}\\|[[a-z][A-Z][0-9]]{15}");
    }

    private String a(String str, String str2) {
        return c(str) + "|" + c(str2);
    }

    private String c(String str) {
        if (!a(str)) {
            str = a();
        }
        return d((str + "123456789012345").substring(0, 15));
    }

    private String d(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        byte[] bytes = str.getBytes();
        for (int i = 0; i < bytes.length; i++) {
            if (!a(bytes[i])) {
                bytes[i] = 48;
            }
        }
        return new String(bytes);
    }
}
