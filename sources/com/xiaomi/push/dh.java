package com.xiaomi.push;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.b;

public class dh {
    public static int a(Context context, int i) {
        int b = gz.b(context);
        if (-1 == b) {
            return -1;
        }
        return (i * (b == 0 ? 13 : 11)) / 10;
    }

    public static int a(ho hoVar) {
        return fc.b(hoVar.a());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002c, code lost:
        if (com.xiaomi.push.fi.a(r2) != -1) goto L_0x002e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0033, code lost:
        r2 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004b, code lost:
        if (com.xiaomi.push.fi.a(r2) != -1) goto L_0x002e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int a(com.xiaomi.push.iz r2, com.xiaomi.push.ho r3) {
        /*
            int[] r0 = com.xiaomi.push.di.f12689a
            int r1 = r3.ordinal()
            r0 = r0[r1]
            r1 = -1
            switch(r0) {
                case 1: goto L_0x00b5;
                case 2: goto L_0x00b5;
                case 3: goto L_0x00b5;
                case 4: goto L_0x00b5;
                case 5: goto L_0x00b5;
                case 6: goto L_0x00b5;
                case 7: goto L_0x00b5;
                case 8: goto L_0x00b5;
                case 9: goto L_0x00b5;
                case 10: goto L_0x00b5;
                case 11: goto L_0x0055;
                case 12: goto L_0x000e;
                default: goto L_0x000c;
            }
        L_0x000c:
            goto L_0x00bd
        L_0x000e:
            int r3 = r3.a()
            int r3 = com.xiaomi.push.fc.b((int) r3)
            if (r2 == 0) goto L_0x0053
            boolean r0 = r2 instanceof com.xiaomi.push.ij     // Catch:{ Exception -> 0x004e }
            if (r0 == 0) goto L_0x0037
            com.xiaomi.push.ij r2 = (com.xiaomi.push.ij) r2     // Catch:{ Exception -> 0x004e }
            java.lang.String r2 = r2.a()     // Catch:{ Exception -> 0x004e }
            boolean r0 = android.text.TextUtils.isEmpty(r2)     // Catch:{ Exception -> 0x004e }
            if (r0 != 0) goto L_0x0033
            int r0 = com.xiaomi.push.fi.a(r2)     // Catch:{ Exception -> 0x004e }
            if (r0 == r1) goto L_0x0033
        L_0x002e:
            int r2 = com.xiaomi.push.fi.a(r2)     // Catch:{ Exception -> 0x004e }
            goto L_0x0034
        L_0x0033:
            r2 = r3
        L_0x0034:
            r1 = r2
            goto L_0x00bd
        L_0x0037:
            boolean r0 = r2 instanceof com.xiaomi.push.ii     // Catch:{ Exception -> 0x004e }
            if (r0 == 0) goto L_0x0053
            com.xiaomi.push.ii r2 = (com.xiaomi.push.ii) r2     // Catch:{ Exception -> 0x004e }
            java.lang.String r2 = r2.a()     // Catch:{ Exception -> 0x004e }
            boolean r0 = android.text.TextUtils.isEmpty(r2)     // Catch:{ Exception -> 0x004e }
            if (r0 != 0) goto L_0x0053
            int r0 = com.xiaomi.push.fi.a(r2)     // Catch:{ Exception -> 0x004e }
            if (r0 == r1) goto L_0x0053
            goto L_0x002e
        L_0x004e:
            java.lang.String r2 = "PERF_ERROR : parse Command type error"
            com.xiaomi.channel.commonutils.logger.b.d(r2)
        L_0x0053:
            r1 = r3
            goto L_0x00bd
        L_0x0055:
            int r3 = r3.a()
            int r3 = com.xiaomi.push.fc.b((int) r3)
            if (r2 == 0) goto L_0x0053
            boolean r0 = r2 instanceof com.xiaomi.push.Cif     // Catch:{ Exception -> 0x00ae }
            if (r0 == 0) goto L_0x0080
            com.xiaomi.push.if r2 = (com.xiaomi.push.Cif) r2     // Catch:{ Exception -> 0x00ae }
            java.lang.String r2 = r2.d     // Catch:{ Exception -> 0x00ae }
            boolean r0 = android.text.TextUtils.isEmpty(r2)     // Catch:{ Exception -> 0x00ae }
            if (r0 != 0) goto L_0x0033
            com.xiaomi.push.hy r0 = com.xiaomi.push.fc.b((java.lang.String) r2)     // Catch:{ Exception -> 0x00ae }
            int r0 = com.xiaomi.push.fc.a((java.lang.Enum) r0)     // Catch:{ Exception -> 0x00ae }
            if (r0 == r1) goto L_0x0033
            com.xiaomi.push.hy r2 = com.xiaomi.push.fc.b((java.lang.String) r2)     // Catch:{ Exception -> 0x00ae }
            int r2 = com.xiaomi.push.fc.a((java.lang.Enum) r2)     // Catch:{ Exception -> 0x00ae }
            goto L_0x0034
        L_0x0080:
            boolean r0 = r2 instanceof com.xiaomi.push.in     // Catch:{ Exception -> 0x00ae }
            if (r0 == 0) goto L_0x0053
            com.xiaomi.push.in r2 = (com.xiaomi.push.in) r2     // Catch:{ Exception -> 0x00ae }
            java.lang.String r2 = r2.d     // Catch:{ Exception -> 0x00ae }
            boolean r0 = android.text.TextUtils.isEmpty(r2)     // Catch:{ Exception -> 0x00ae }
            if (r0 != 0) goto L_0x0053
            com.xiaomi.push.hy r0 = com.xiaomi.push.fc.b((java.lang.String) r2)     // Catch:{ Exception -> 0x00ae }
            int r0 = com.xiaomi.push.fc.a((java.lang.Enum) r0)     // Catch:{ Exception -> 0x00ae }
            if (r0 == r1) goto L_0x00a1
            com.xiaomi.push.hy r0 = com.xiaomi.push.fc.b((java.lang.String) r2)     // Catch:{ Exception -> 0x00ae }
            int r0 = com.xiaomi.push.fc.a((java.lang.Enum) r0)     // Catch:{ Exception -> 0x00ae }
            r3 = r0
        L_0x00a1:
            com.xiaomi.push.hy r0 = com.xiaomi.push.hy.UploadTinyData     // Catch:{ Exception -> 0x00ae }
            com.xiaomi.push.hy r2 = com.xiaomi.push.fc.b((java.lang.String) r2)     // Catch:{ Exception -> 0x00ae }
            boolean r2 = r0.equals(r2)     // Catch:{ Exception -> 0x00ae }
            if (r2 == 0) goto L_0x0053
            goto L_0x00bd
        L_0x00ae:
            r1 = r3
            java.lang.String r2 = "PERF_ERROR : parse Notification type error"
            com.xiaomi.channel.commonutils.logger.b.d(r2)
            goto L_0x00bd
        L_0x00b5:
            int r2 = r3.a()
            int r1 = com.xiaomi.push.fc.b((int) r2)
        L_0x00bd:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.dh.a(com.xiaomi.push.iz, com.xiaomi.push.ho):int");
    }

    public static void a(String str, Context context, int i, int i2) {
        if (i > 0 && i2 > 0) {
            int a2 = a(context, i2);
            if (i != fc.a((Enum) hy.UploadTinyData)) {
                fd.a(context.getApplicationContext()).a(str, i, 1, (long) a2);
            }
        }
    }

    public static void a(String str, Context context, ik ikVar, int i) {
        ho a2;
        if (context != null && ikVar != null && (a2 = ikVar.a()) != null) {
            int a3 = a(a2);
            if (i <= 0) {
                byte[] a4 = iy.a(ikVar);
                i = a4 != null ? a4.length : 0;
            }
            a(str, context, a3, i);
        }
    }

    public static void a(String str, Context context, iz izVar, ho hoVar, int i) {
        a(str, context, a(izVar, hoVar), i);
    }

    public static void a(String str, Context context, byte[] bArr) {
        if (context != null && bArr != null && bArr.length > 0) {
            ik ikVar = new ik();
            try {
                iy.a(ikVar, bArr);
                a(str, context, ikVar, bArr.length);
            } catch (je unused) {
                b.a("fail to convert bytes to container");
            }
        }
    }
}
