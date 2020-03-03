package com.loc;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import org.apache.commons.compress.compressors.CompressorStreamFactory;

public final class w {

    private static class a {

        /* renamed from: a  reason: collision with root package name */
        String f6647a;
        String b;
        String c;
        String d;
        String e;
        String f;
        String g;
        String h;
        String i;
        String j;
        String k;
        String l;
        String m;
        String n;
        String o;
        String p;
        String q;
        String r;
        String s;
        String t;
        String u;
        String v;
        String w;
        String x;
        String y;

        private a() {
        }

        /* synthetic */ a(byte b2) {
            this();
        }
    }

    public static String a() {
        String str;
        Throwable th;
        try {
            str = String.valueOf(System.currentTimeMillis());
            String str2 = "1";
            try {
                if (!u.a()) {
                    str2 = "0";
                }
                int length = str.length();
                return str.substring(0, length - 2) + str2 + str.substring(length - 1);
            } catch (Throwable th2) {
                th = th2;
                an.a(th, "CI", "TS");
                return str;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            str = null;
            th = th4;
            an.a(th, "CI", "TS");
            return str;
        }
    }

    public static String a(Context context, String str, String str2) {
        try {
            String e = u.e(context);
            return aa.b(e + ":" + str.substring(0, str.length() - 3) + ":" + str2);
        } catch (Throwable th) {
            an.a(th, "CI", "Sco");
            return null;
        }
    }

    private static void a(ByteArrayOutputStream byteArrayOutputStream, String str) {
        if (!TextUtils.isEmpty(str)) {
            ad.a(byteArrayOutputStream, str.getBytes().length > 255 ? -1 : (byte) str.getBytes().length, ad.a(str));
        } else {
            ad.a(byteArrayOutputStream, (byte) 0, new byte[0]);
        }
    }

    public static byte[] a(Context context, boolean z) {
        String str;
        try {
            a aVar = new a((byte) 0);
            aVar.f6647a = x.u(context);
            aVar.b = x.l(context);
            String g = x.g(context);
            if (g == null) {
                g = "";
            }
            aVar.c = g;
            aVar.d = u.c(context);
            aVar.e = Build.MODEL;
            aVar.f = Build.MANUFACTURER;
            aVar.g = Build.DEVICE;
            aVar.h = u.b(context);
            aVar.i = u.d(context);
            aVar.j = String.valueOf(Build.VERSION.SDK_INT);
            aVar.k = x.x(context);
            aVar.l = x.t(context);
            StringBuilder sb = new StringBuilder();
            sb.append(x.q(context));
            aVar.m = sb.toString();
            StringBuilder sb2 = new StringBuilder();
            sb2.append(x.p(context));
            aVar.n = sb2.toString();
            aVar.o = x.z(context);
            aVar.p = x.o(context);
            aVar.q = z ? "" : x.k(context);
            aVar.r = z ? "" : x.j(context);
            if (z) {
                aVar.s = "";
                str = "";
            } else {
                String[] m = x.m(context);
                aVar.s = m[0];
                str = m[1];
            }
            aVar.t = str;
            aVar.w = x.a();
            String a2 = x.a(context);
            if (TextUtils.isEmpty(a2)) {
                a2 = "";
            }
            aVar.x = a2;
            aVar.y = "aid=" + x.i(context) + "|serial=" + x.h(context) + "|storage=" + x.d() + "|ram=" + x.y(context) + "|arch=" + x.e();
            String b = x.b();
            if (!TextUtils.isEmpty(b)) {
                aVar.y += "|adiuExtras=" + b;
            }
            String a3 = x.a(context, ",");
            if (!TextUtils.isEmpty(a3)) {
                aVar.y += "|multiImeis=" + a3;
            }
            String w = x.w(context);
            if (!TextUtils.isEmpty(w)) {
                aVar.y += "|meid=" + w;
            }
            return a(aVar);
        } catch (Throwable th) {
            an.a(th, "CI", CompressorStreamFactory.b);
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x00cb A[SYNTHETIC, Splitter:B:22:0x00cb] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00d7 A[SYNTHETIC, Splitter:B:29:0x00d7] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] a(com.loc.w.a r7) {
        /*
            r0 = 0
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
            r1.<init>()     // Catch:{ Throwable -> 0x00c0, all -> 0x00bd }
            java.lang.String r2 = r7.f6647a     // Catch:{ Throwable -> 0x00bb }
            a((java.io.ByteArrayOutputStream) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x00bb }
            java.lang.String r2 = r7.b     // Catch:{ Throwable -> 0x00bb }
            a((java.io.ByteArrayOutputStream) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x00bb }
            java.lang.String r2 = r7.c     // Catch:{ Throwable -> 0x00bb }
            a((java.io.ByteArrayOutputStream) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x00bb }
            java.lang.String r2 = r7.d     // Catch:{ Throwable -> 0x00bb }
            a((java.io.ByteArrayOutputStream) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x00bb }
            java.lang.String r2 = r7.e     // Catch:{ Throwable -> 0x00bb }
            a((java.io.ByteArrayOutputStream) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x00bb }
            java.lang.String r2 = r7.f     // Catch:{ Throwable -> 0x00bb }
            a((java.io.ByteArrayOutputStream) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x00bb }
            java.lang.String r2 = r7.g     // Catch:{ Throwable -> 0x00bb }
            a((java.io.ByteArrayOutputStream) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x00bb }
            java.lang.String r2 = r7.h     // Catch:{ Throwable -> 0x00bb }
            a((java.io.ByteArrayOutputStream) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x00bb }
            java.lang.String r2 = r7.i     // Catch:{ Throwable -> 0x00bb }
            a((java.io.ByteArrayOutputStream) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x00bb }
            java.lang.String r2 = r7.j     // Catch:{ Throwable -> 0x00bb }
            a((java.io.ByteArrayOutputStream) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x00bb }
            java.lang.String r2 = r7.k     // Catch:{ Throwable -> 0x00bb }
            a((java.io.ByteArrayOutputStream) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x00bb }
            java.lang.String r2 = r7.l     // Catch:{ Throwable -> 0x00bb }
            a((java.io.ByteArrayOutputStream) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x00bb }
            java.lang.String r2 = r7.m     // Catch:{ Throwable -> 0x00bb }
            a((java.io.ByteArrayOutputStream) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x00bb }
            java.lang.String r2 = r7.n     // Catch:{ Throwable -> 0x00bb }
            a((java.io.ByteArrayOutputStream) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x00bb }
            java.lang.String r2 = r7.o     // Catch:{ Throwable -> 0x00bb }
            a((java.io.ByteArrayOutputStream) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x00bb }
            java.lang.String r2 = r7.p     // Catch:{ Throwable -> 0x00bb }
            a((java.io.ByteArrayOutputStream) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x00bb }
            java.lang.String r2 = r7.q     // Catch:{ Throwable -> 0x00bb }
            a((java.io.ByteArrayOutputStream) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x00bb }
            java.lang.String r2 = r7.r     // Catch:{ Throwable -> 0x00bb }
            a((java.io.ByteArrayOutputStream) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x00bb }
            java.lang.String r2 = r7.s     // Catch:{ Throwable -> 0x00bb }
            a((java.io.ByteArrayOutputStream) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x00bb }
            java.lang.String r2 = r7.t     // Catch:{ Throwable -> 0x00bb }
            a((java.io.ByteArrayOutputStream) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x00bb }
            java.lang.String r2 = r7.u     // Catch:{ Throwable -> 0x00bb }
            a((java.io.ByteArrayOutputStream) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x00bb }
            java.lang.String r2 = r7.v     // Catch:{ Throwable -> 0x00bb }
            a((java.io.ByteArrayOutputStream) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x00bb }
            java.lang.String r2 = r7.w     // Catch:{ Throwable -> 0x00bb }
            a((java.io.ByteArrayOutputStream) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x00bb }
            java.lang.String r2 = r7.x     // Catch:{ Throwable -> 0x00bb }
            a((java.io.ByteArrayOutputStream) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x00bb }
            java.lang.String r7 = r7.y     // Catch:{ Throwable -> 0x00bb }
            a((java.io.ByteArrayOutputStream) r1, (java.lang.String) r7)     // Catch:{ Throwable -> 0x00bb }
            byte[] r7 = r1.toByteArray()     // Catch:{ Throwable -> 0x00bb }
            byte[] r7 = com.loc.ad.b((byte[]) r7)     // Catch:{ Throwable -> 0x00bb }
            java.security.PublicKey r2 = com.loc.ad.d()     // Catch:{ Throwable -> 0x00bb }
            int r3 = r7.length     // Catch:{ Throwable -> 0x00bb }
            r4 = 117(0x75, float:1.64E-43)
            if (r3 <= r4) goto L_0x00ae
            byte[] r3 = new byte[r4]     // Catch:{ Throwable -> 0x00bb }
            r5 = 0
            java.lang.System.arraycopy(r7, r5, r3, r5, r4)     // Catch:{ Throwable -> 0x00bb }
            byte[] r2 = com.loc.y.a((byte[]) r3, (java.security.Key) r2)     // Catch:{ Throwable -> 0x00bb }
            int r3 = r7.length     // Catch:{ Throwable -> 0x00bb }
            r6 = 128(0x80, float:1.794E-43)
            int r3 = r3 + r6
            int r3 = r3 - r4
            byte[] r3 = new byte[r3]     // Catch:{ Throwable -> 0x00bb }
            java.lang.System.arraycopy(r2, r5, r3, r5, r6)     // Catch:{ Throwable -> 0x00bb }
            int r2 = r7.length     // Catch:{ Throwable -> 0x00bb }
            int r2 = r2 - r4
            java.lang.System.arraycopy(r7, r4, r3, r6, r2)     // Catch:{ Throwable -> 0x00bb }
            goto L_0x00b2
        L_0x00ae:
            byte[] r3 = com.loc.y.a((byte[]) r7, (java.security.Key) r2)     // Catch:{ Throwable -> 0x00bb }
        L_0x00b2:
            r1.close()     // Catch:{ Throwable -> 0x00b6 }
            goto L_0x00ba
        L_0x00b6:
            r7 = move-exception
            r7.printStackTrace()
        L_0x00ba:
            return r3
        L_0x00bb:
            r7 = move-exception
            goto L_0x00c2
        L_0x00bd:
            r7 = move-exception
            r1 = r0
            goto L_0x00d5
        L_0x00c0:
            r7 = move-exception
            r1 = r0
        L_0x00c2:
            java.lang.String r2 = "CI"
            java.lang.String r3 = "gzx"
            com.loc.an.a((java.lang.Throwable) r7, (java.lang.String) r2, (java.lang.String) r3)     // Catch:{ all -> 0x00d4 }
            if (r1 == 0) goto L_0x00d3
            r1.close()     // Catch:{ Throwable -> 0x00cf }
            goto L_0x00d3
        L_0x00cf:
            r7 = move-exception
            r7.printStackTrace()
        L_0x00d3:
            return r0
        L_0x00d4:
            r7 = move-exception
        L_0x00d5:
            if (r1 == 0) goto L_0x00df
            r1.close()     // Catch:{ Throwable -> 0x00db }
            goto L_0x00df
        L_0x00db:
            r0 = move-exception
            r0.printStackTrace()
        L_0x00df:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.w.a(com.loc.w$a):byte[]");
    }
}
