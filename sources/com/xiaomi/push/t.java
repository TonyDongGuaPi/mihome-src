package com.xiaomi.push;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.alipay.android.phone.a.a.a;
import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.channel.commonutils.logger.b;

public class t {

    /* renamed from: a  reason: collision with root package name */
    private static Context f12946a;
    private static String b;

    public static Context a() {
        return f12946a;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:13|14|15) */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0029, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002a, code lost:
        com.xiaomi.channel.commonutils.logger.b.a(java.lang.String.format("loadClass fail hasContext= %s, errMsg = %s", new java.lang.Object[]{java.lang.Boolean.valueOf(r2), r5.getLocalizedMessage()}));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0049, code lost:
        throw new java.lang.ClassNotFoundException("loadClass fail ", r5);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0024 */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0024 A[SYNTHETIC, Splitter:B:13:0x0024] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Class<?> a(android.content.Context r5, java.lang.String r6) {
        /*
            if (r6 == 0) goto L_0x004a
            java.lang.String r0 = r6.trim()
            int r0 = r0.length()
            if (r0 == 0) goto L_0x004a
            r0 = 0
            r1 = 1
            if (r5 == 0) goto L_0x0012
            r2 = 1
            goto L_0x0013
        L_0x0012:
            r2 = 0
        L_0x0013:
            if (r2 == 0) goto L_0x0024
            int r3 = android.os.Build.VERSION.SDK_INT
            r4 = 29
            if (r3 < r4) goto L_0x0024
            java.lang.ClassLoader r5 = r5.getClassLoader()     // Catch:{ ClassNotFoundException -> 0x0024 }
            java.lang.Class r5 = r5.loadClass(r6)     // Catch:{ ClassNotFoundException -> 0x0024 }
            return r5
        L_0x0024:
            java.lang.Class r5 = java.lang.Class.forName(r6)     // Catch:{ ClassNotFoundException -> 0x0029 }
            return r5
        L_0x0029:
            r5 = move-exception
            r6 = 2
            java.lang.Object[] r6 = new java.lang.Object[r6]
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)
            r6[r0] = r2
            java.lang.String r0 = r5.getLocalizedMessage()
            r6[r1] = r0
            java.lang.String r0 = "loadClass fail hasContext= %s, errMsg = %s"
            java.lang.String r6 = java.lang.String.format(r0, r6)
            com.xiaomi.channel.commonutils.logger.b.a((java.lang.String) r6)
            java.lang.ClassNotFoundException r6 = new java.lang.ClassNotFoundException
            java.lang.String r0 = "loadClass fail "
            r6.<init>(r0, r5)
            throw r6
        L_0x004a:
            java.lang.ClassNotFoundException r5 = new java.lang.ClassNotFoundException
            java.lang.String r6 = "class is empty"
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.t.a(android.content.Context, java.lang.String):java.lang.Class");
    }

    public static void a(Context context) {
        f12946a = context.getApplicationContext();
    }

    public static int b() {
        try {
            Class<?> a2 = a((Context) null, "miui.os.Build");
            if (a2.getField("IS_STABLE_VERSION").getBoolean((Object) null)) {
                return 3;
            }
            return a2.getField("IS_DEVELOPMENT_VERSION").getBoolean((Object) null) ? 2 : 1;
        } catch (Exception unused) {
            return 0;
        }
    }

    public static boolean b(Context context) {
        try {
            return (context.getApplicationInfo().flags & 2) != 0;
        } catch (Exception e) {
            b.a((Throwable) e);
            return false;
        }
    }

    public static String c(Context context) {
        if (l.b()) {
            return "";
        }
        String str = (String) ba.a("com.xiaomi.xmsf.helper.MIIDAccountHelper", "getMIID", context);
        return TextUtils.isEmpty(str) ? "0" : str;
    }

    public static boolean c() {
        return TextUtils.equals((String) ba.a("android.os.SystemProperties", "get", "sys.boot_completed"), "1");
    }

    public static boolean d() {
        try {
            return a((Context) null, "miui.os.Build").getField("IS_GLOBAL_BUILD").getBoolean(false);
        } catch (ClassNotFoundException unused) {
            b.d("miui.os.Build ClassNotFound");
            return false;
        } catch (Exception e) {
            b.a((Throwable) e);
            return false;
        }
    }

    public static synchronized String e() {
        String str;
        synchronized (t.class) {
            if (b != null) {
                String str2 = b;
                return str2;
            }
            String str3 = Build.VERSION.INCREMENTAL;
            if (b() <= 0) {
                str = f();
                if (TextUtils.isEmpty(str)) {
                    str = g();
                    if (TextUtils.isEmpty(str)) {
                        str = h();
                        if (TextUtils.isEmpty(str)) {
                            str3 = String.valueOf(s.a("ro.product.brand", a.f813a) + JSMethod.NOT_SET + str3);
                        }
                    }
                }
                b = str;
                return str;
            }
            str = str3;
            b = str;
            return str;
        }
    }

    private static String f() {
        b = s.a("ro.build.version.emui", "");
        return b;
    }

    private static String g() {
        String a2 = s.a("ro.build.version.opporom", "");
        if (!TextUtils.isEmpty(a2) && !a2.startsWith("ColorOS_")) {
            b = "ColorOS_" + a2;
        }
        return b;
    }

    private static String h() {
        String a2 = s.a("ro.vivo.os.version", "");
        if (!TextUtils.isEmpty(a2) && !a2.startsWith("FuntouchOS_")) {
            b = "FuntouchOS_" + a2;
        }
        return b;
    }
}
