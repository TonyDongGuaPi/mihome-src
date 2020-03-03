package com.loc;

import com.xiaomi.stat.d;
import java.net.URLConnection;

public final class bg {

    /* renamed from: a  reason: collision with root package name */
    public static int f6508a = 0;
    public static String b = "";
    private static bg c;

    public interface a {
        URLConnection a();
    }

    public static bg a() {
        if (c == null) {
            c = new bg();
        }
        return c;
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x007b A[Catch:{ t -> 0x00a6, Throwable -> 0x0099 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.loc.bk a(com.loc.bj r8, boolean r9) throws com.loc.t {
        /*
            if (r8 == 0) goto L_0x0091
            java.lang.String r0 = r8.c()     // Catch:{ t -> 0x00a6, Throwable -> 0x0099 }
            if (r0 == 0) goto L_0x0089
            java.lang.String r0 = ""
            java.lang.String r1 = r8.c()     // Catch:{ t -> 0x00a6, Throwable -> 0x0099 }
            boolean r0 = r0.equals(r1)     // Catch:{ t -> 0x00a6, Throwable -> 0x0099 }
            if (r0 != 0) goto L_0x0089
            java.net.Proxy r0 = r8.e     // Catch:{ t -> 0x00a6, Throwable -> 0x0099 }
            if (r0 != 0) goto L_0x001a
            r0 = 0
            goto L_0x001c
        L_0x001a:
            java.net.Proxy r0 = r8.e     // Catch:{ t -> 0x00a6, Throwable -> 0x0099 }
        L_0x001c:
            com.loc.bi r1 = new com.loc.bi     // Catch:{ t -> 0x00a6, Throwable -> 0x0099 }
            int r2 = r8.c     // Catch:{ t -> 0x00a6, Throwable -> 0x0099 }
            int r3 = r8.d     // Catch:{ t -> 0x00a6, Throwable -> 0x0099 }
            r1.<init>(r2, r3, r0, r9)     // Catch:{ t -> 0x00a6, Throwable -> 0x0099 }
            byte[] r9 = r8.d()     // Catch:{ t -> 0x00a6, Throwable -> 0x0099 }
            if (r9 == 0) goto L_0x0053
            int r9 = r9.length     // Catch:{ t -> 0x00a6, Throwable -> 0x0099 }
            if (r9 != 0) goto L_0x002f
            goto L_0x0053
        L_0x002f:
            java.util.Map r9 = r8.b()     // Catch:{ t -> 0x00a6, Throwable -> 0x0099 }
            if (r9 != 0) goto L_0x0036
            goto L_0x0053
        L_0x0036:
            java.lang.String r9 = com.loc.bi.a(r9)     // Catch:{ t -> 0x00a6, Throwable -> 0x0099 }
            java.lang.StringBuffer r0 = new java.lang.StringBuffer     // Catch:{ t -> 0x00a6, Throwable -> 0x0099 }
            r0.<init>()     // Catch:{ t -> 0x00a6, Throwable -> 0x0099 }
            java.lang.String r2 = r8.c()     // Catch:{ t -> 0x00a6, Throwable -> 0x0099 }
            r0.append(r2)     // Catch:{ t -> 0x00a6, Throwable -> 0x0099 }
            java.lang.String r2 = "?"
            r0.append(r2)     // Catch:{ t -> 0x00a6, Throwable -> 0x0099 }
            r0.append(r9)     // Catch:{ t -> 0x00a6, Throwable -> 0x0099 }
            java.lang.String r9 = r0.toString()     // Catch:{ t -> 0x00a6, Throwable -> 0x0099 }
            goto L_0x0057
        L_0x0053:
            java.lang.String r9 = r8.c()     // Catch:{ t -> 0x00a6, Throwable -> 0x0099 }
        L_0x0057:
            r2 = r9
            boolean r3 = r8.l()     // Catch:{ t -> 0x00a6, Throwable -> 0x0099 }
            java.lang.String r4 = r8.k()     // Catch:{ t -> 0x00a6, Throwable -> 0x0099 }
            java.util.Map r5 = r8.a()     // Catch:{ t -> 0x00a6, Throwable -> 0x0099 }
            byte[] r9 = r8.d()     // Catch:{ t -> 0x00a6, Throwable -> 0x0099 }
            if (r9 == 0) goto L_0x006d
            int r0 = r9.length     // Catch:{ t -> 0x00a6, Throwable -> 0x0099 }
            if (r0 != 0) goto L_0x007f
        L_0x006d:
            java.util.Map r0 = r8.b()     // Catch:{ t -> 0x00a6, Throwable -> 0x0099 }
            java.lang.String r0 = com.loc.bi.a(r0)     // Catch:{ t -> 0x00a6, Throwable -> 0x0099 }
            boolean r6 = android.text.TextUtils.isEmpty(r0)     // Catch:{ t -> 0x00a6, Throwable -> 0x0099 }
            if (r6 != 0) goto L_0x007f
            byte[] r9 = com.loc.ad.a((java.lang.String) r0)     // Catch:{ t -> 0x00a6, Throwable -> 0x0099 }
        L_0x007f:
            r6 = r9
            boolean r7 = r8.m()     // Catch:{ t -> 0x00a6, Throwable -> 0x0099 }
            com.loc.bk r8 = r1.a(r2, r3, r4, r5, r6, r7)     // Catch:{ t -> 0x00a6, Throwable -> 0x0099 }
            return r8
        L_0x0089:
            com.loc.t r8 = new com.loc.t     // Catch:{ t -> 0x00a6, Throwable -> 0x0099 }
            java.lang.String r9 = "request url is empty"
            r8.<init>(r9)     // Catch:{ t -> 0x00a6, Throwable -> 0x0099 }
            throw r8     // Catch:{ t -> 0x00a6, Throwable -> 0x0099 }
        L_0x0091:
            com.loc.t r8 = new com.loc.t     // Catch:{ t -> 0x00a6, Throwable -> 0x0099 }
            java.lang.String r9 = "requeust is null"
            r8.<init>(r9)     // Catch:{ t -> 0x00a6, Throwable -> 0x0099 }
            throw r8     // Catch:{ t -> 0x00a6, Throwable -> 0x0099 }
        L_0x0099:
            r8 = move-exception
            r8.printStackTrace()
            com.loc.t r8 = new com.loc.t
            java.lang.String r9 = "未知的错误"
            r8.<init>(r9)
            throw r8
        L_0x00a6:
            r8 = move-exception
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.bg.a(com.loc.bj, boolean):com.loc.bk");
    }

    public static byte[] a(bj bjVar) throws t {
        try {
            bk a2 = a(bjVar, true);
            if (a2 != null) {
                return a2.f6513a;
            }
            return null;
        } catch (t e) {
            throw e;
        } catch (Throwable unused) {
            throw new t("未知的错误");
        }
    }

    public static byte[] b(bj bjVar) throws t {
        try {
            bk a2 = a(bjVar, false);
            if (a2 != null) {
                return a2.f6513a;
            }
            return null;
        } catch (t e) {
            throw e;
        } catch (Throwable th) {
            an.a(th, d.L, "msp");
            throw new t("未知的错误");
        }
    }
}
