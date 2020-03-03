package com.tencent.smtt.utils;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.alipay.sdk.sys.a;
import com.tencent.smtt.sdk.TbsConfig;
import com.tencent.smtt.sdk.WebView;

public class r {

    /* renamed from: a  reason: collision with root package name */
    private static String f9214a = null;
    private static String b = "GA";
    private static String c = "GE";
    private static String d = "9422";
    private static String e = "0";
    private static String f = "";
    private static boolean g = false;
    private static boolean h = false;
    private static boolean i = false;

    private static String a() {
        return " " + Build.MODEL.replaceAll("[ |\\/|\\_|\\&|\\|]", "") + " ";
    }

    public static String a(Context context) {
        if (!TextUtils.isEmpty(f9214a)) {
            return f9214a;
        }
        f9214a = a(context, String.valueOf(WebView.getTbsSDKVersion(context)), "0", b, c, d, e, f, g);
        return f9214a;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0093  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00a6  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00c5  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00c8  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00ea  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x010a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String a(android.content.Context r9, java.lang.String r10, java.lang.String r11, java.lang.String r12, java.lang.String r13, java.lang.String r14, java.lang.String r15, java.lang.String r16, boolean r17) {
        /*
            java.lang.String r1 = ""
            java.lang.String r2 = ""
            java.lang.String r3 = "PHONE"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            int r5 = b(r9)
            r0.append(r5)
            java.lang.String r5 = "*"
            r0.append(r5)
            int r5 = c(r9)
            r0.append(r5)
            java.lang.String r5 = r0.toString()
            android.content.Context r0 = r9.getApplicationContext()     // Catch:{ NameNotFoundException -> 0x004b }
            android.content.pm.ApplicationInfo r0 = r0.getApplicationInfo()     // Catch:{ NameNotFoundException -> 0x004b }
            android.content.pm.PackageManager r6 = r9.getPackageManager()     // Catch:{ NameNotFoundException -> 0x004b }
            java.lang.String r7 = r0.packageName     // Catch:{ NameNotFoundException -> 0x004b }
            r8 = 0
            android.content.pm.PackageInfo r6 = r6.getPackageInfo(r7, r8)     // Catch:{ NameNotFoundException -> 0x004b }
            java.lang.String r7 = r0.packageName     // Catch:{ NameNotFoundException -> 0x004b }
            boolean r0 = android.text.TextUtils.isEmpty(r16)     // Catch:{ NameNotFoundException -> 0x0049 }
            if (r0 != 0) goto L_0x0045
            r2 = r16
            goto L_0x0050
        L_0x0045:
            java.lang.String r0 = r6.versionName     // Catch:{ NameNotFoundException -> 0x0049 }
            r2 = r0
            goto L_0x0050
        L_0x0049:
            r0 = move-exception
            goto L_0x004d
        L_0x004b:
            r0 = move-exception
            r7 = r1
        L_0x004d:
            r0.printStackTrace()
        L_0x0050:
            java.lang.String r0 = a((java.lang.String) r7)
            java.lang.String r1 = "QB"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0061
            if (r17 == 0) goto L_0x0068
        L_0x005e:
            java.lang.String r3 = "PAD"
            goto L_0x0068
        L_0x0061:
            boolean r1 = d(r9)
            if (r1 == 0) goto L_0x0068
            goto L_0x005e
        L_0x0068:
            java.lang.String r1 = "QV"
            r4.append(r1)
            java.lang.String r1 = "="
            r4.append(r1)
            java.lang.String r1 = "3"
            r4.append(r1)
            java.lang.String r1 = "PL"
            java.lang.String r6 = "ADR"
            a(r4, r1, r6)
            java.lang.String r1 = "PR"
            a(r4, r1, r0)
            java.lang.String r0 = "PP"
            a(r4, r0, r7)
            java.lang.String r0 = "PPVN"
            a(r4, r0, r2)
            boolean r0 = android.text.TextUtils.isEmpty(r10)
            if (r0 != 0) goto L_0x0099
            java.lang.String r0 = "TBSVC"
            r1 = r10
            a(r4, r0, r10)
        L_0x0099:
            java.lang.String r0 = "CO"
            java.lang.String r1 = "SYS"
            a(r4, r0, r1)
            boolean r0 = android.text.TextUtils.isEmpty(r11)
            if (r0 != 0) goto L_0x00ac
            java.lang.String r0 = "COVC"
            r1 = r11
            a(r4, r0, r11)
        L_0x00ac:
            java.lang.String r0 = "PB"
            r1 = r13
            a(r4, r0, r13)
            java.lang.String r0 = "VE"
            r1 = r12
            a(r4, r0, r12)
            java.lang.String r0 = "DE"
            a(r4, r0, r3)
            java.lang.String r0 = "CHID"
            boolean r1 = android.text.TextUtils.isEmpty(r15)
            if (r1 == 0) goto L_0x00c8
            java.lang.String r1 = "0"
            goto L_0x00c9
        L_0x00c8:
            r1 = r15
        L_0x00c9:
            a(r4, r0, r1)
            java.lang.String r0 = "LCID"
            r1 = r14
            a(r4, r0, r14)
            java.lang.String r0 = a()
            java.lang.String r1 = new java.lang.String     // Catch:{ Exception -> 0x00e4 }
            java.lang.String r2 = "UTF-8"
            byte[] r2 = r0.getBytes(r2)     // Catch:{ Exception -> 0x00e4 }
            java.lang.String r3 = "ISO8859-1"
            r1.<init>(r2, r3)     // Catch:{ Exception -> 0x00e4 }
            r0 = r1
        L_0x00e4:
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L_0x00ef
            java.lang.String r1 = "MO"
            a(r4, r1, r0)
        L_0x00ef:
            java.lang.String r0 = "RL"
            a(r4, r0, r5)
            java.lang.String r0 = android.os.Build.VERSION.RELEASE
            java.lang.String r1 = new java.lang.String     // Catch:{ Exception -> 0x0104 }
            java.lang.String r2 = "UTF-8"
            byte[] r2 = r0.getBytes(r2)     // Catch:{ Exception -> 0x0104 }
            java.lang.String r3 = "ISO8859-1"
            r1.<init>(r2, r3)     // Catch:{ Exception -> 0x0104 }
            r0 = r1
        L_0x0104:
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L_0x010f
            java.lang.String r1 = "OS"
            a(r4, r1, r0)
        L_0x010f:
            java.lang.String r0 = "API"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            int r2 = android.os.Build.VERSION.SDK_INT
            r1.append(r2)
            java.lang.String r2 = ""
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            a(r4, r0, r1)
            java.lang.String r0 = r4.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.r.a(android.content.Context, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean):java.lang.String");
    }

    private static String a(String str) {
        return str.equals("com.tencent.mm") ? "WX" : str.equals(TbsConfig.APP_QQ) ? "QQ" : str.equals(TbsConfig.APP_QZONE) ? "QZ" : str.equals(TbsConfig.APP_QB) ? "QB" : "TRD";
    }

    private static void a(StringBuilder sb, String str, String str2) {
        sb.append(a.b);
        sb.append(str);
        sb.append("=");
        sb.append(str2);
    }

    private static int b(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        if (defaultDisplay != null) {
            return defaultDisplay.getWidth();
        }
        return -1;
    }

    private static int c(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        if (defaultDisplay != null) {
            return defaultDisplay.getHeight();
        }
        return -1;
    }

    private static boolean d(Context context) {
        if (h) {
            return i;
        }
        try {
            i = (Math.min(b(context), c(context)) * 160) / e(context) >= 700;
            h = true;
            return i;
        } catch (Throwable unused) {
            return false;
        }
    }

    private static int e(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        if (defaultDisplay == null) {
            return 160;
        }
        defaultDisplay.getMetrics(displayMetrics);
        return displayMetrics.densityDpi;
    }
}
