package com.amap.api.services.a;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.apache.commons.compress.compressors.CompressorStreamFactory;

public class bs {
    public static String a(Context context, String str, String str2) {
        try {
            String e = bp.e(context);
            return bw.a(e + ":" + str.substring(0, str.length() - 3) + ":" + str2);
        } catch (Throwable th) {
            ci.a(th, "CI", "Sco");
            return null;
        }
    }

    public static String a() {
        String str;
        Throwable th;
        try {
            str = String.valueOf(System.currentTimeMillis());
            String str2 = "1";
            try {
                if (!bp.a()) {
                    str2 = "0";
                }
                int length = str.length();
                return str.substring(0, length - 2) + str2 + str.substring(length - 1);
            } catch (Throwable th2) {
                th = th2;
                ci.a(th, "CI", "TS");
                return str;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            str = null;
            th = th4;
            ci.a(th, "CI", "TS");
            return str;
        }
    }

    public static byte[] a(Context context, byte[] bArr) throws CertificateException, InvalidKeySpecException, NoSuchAlgorithmException, NullPointerException, IOException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        return bu.a(bArr);
    }

    public static byte[] a(Context context, boolean z) {
        try {
            return b(context, b(context, z));
        } catch (Throwable th) {
            ci.a(th, "CI", CompressorStreamFactory.b);
            return null;
        }
    }

    public static String a(Context context) {
        try {
            return a(context, b(context, false));
        } catch (Throwable th) {
            ci.a(th, "CI", "gCX");
            return null;
        }
    }

    public static byte[] b(Context context, byte[] bArr) throws CertificateException, InvalidKeySpecException, NoSuchAlgorithmException, NullPointerException, IOException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        PublicKey d = bz.d();
        if (bArr.length <= 117) {
            return bu.a(bArr, (Key) d);
        }
        byte[] bArr2 = new byte[117];
        System.arraycopy(bArr, 0, bArr2, 0, 117);
        byte[] a2 = bu.a(bArr2, (Key) d);
        byte[] bArr3 = new byte[((bArr.length + 128) - 117)];
        System.arraycopy(a2, 0, bArr3, 0, 128);
        System.arraycopy(bArr, 117, bArr3, 128, bArr.length - 117);
        return bArr3;
    }

    private static String a(Context context, a aVar) {
        return bu.b(b(context, aVar));
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x00a0 A[SYNTHETIC, Splitter:B:19:0x00a0] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00ac A[SYNTHETIC, Splitter:B:26:0x00ac] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] b(android.content.Context r3, com.amap.api.services.a.bs.a r4) {
        /*
            r0 = 0
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ Throwable -> 0x0095, all -> 0x0092 }
            r1.<init>()     // Catch:{ Throwable -> 0x0095, all -> 0x0092 }
            java.lang.String r2 = r4.f4354a     // Catch:{ Throwable -> 0x0090 }
            a((java.io.ByteArrayOutputStream) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x0090 }
            java.lang.String r2 = r4.b     // Catch:{ Throwable -> 0x0090 }
            a((java.io.ByteArrayOutputStream) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x0090 }
            java.lang.String r2 = r4.c     // Catch:{ Throwable -> 0x0090 }
            a((java.io.ByteArrayOutputStream) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x0090 }
            java.lang.String r2 = r4.d     // Catch:{ Throwable -> 0x0090 }
            a((java.io.ByteArrayOutputStream) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x0090 }
            java.lang.String r2 = r4.e     // Catch:{ Throwable -> 0x0090 }
            a((java.io.ByteArrayOutputStream) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x0090 }
            java.lang.String r2 = r4.f     // Catch:{ Throwable -> 0x0090 }
            a((java.io.ByteArrayOutputStream) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x0090 }
            java.lang.String r2 = r4.g     // Catch:{ Throwable -> 0x0090 }
            a((java.io.ByteArrayOutputStream) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x0090 }
            java.lang.String r2 = r4.h     // Catch:{ Throwable -> 0x0090 }
            a((java.io.ByteArrayOutputStream) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x0090 }
            java.lang.String r2 = r4.i     // Catch:{ Throwable -> 0x0090 }
            a((java.io.ByteArrayOutputStream) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x0090 }
            java.lang.String r2 = r4.j     // Catch:{ Throwable -> 0x0090 }
            a((java.io.ByteArrayOutputStream) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x0090 }
            java.lang.String r2 = r4.k     // Catch:{ Throwable -> 0x0090 }
            a((java.io.ByteArrayOutputStream) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x0090 }
            java.lang.String r2 = r4.l     // Catch:{ Throwable -> 0x0090 }
            a((java.io.ByteArrayOutputStream) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x0090 }
            java.lang.String r2 = r4.m     // Catch:{ Throwable -> 0x0090 }
            a((java.io.ByteArrayOutputStream) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x0090 }
            java.lang.String r2 = r4.n     // Catch:{ Throwable -> 0x0090 }
            a((java.io.ByteArrayOutputStream) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x0090 }
            java.lang.String r2 = r4.o     // Catch:{ Throwable -> 0x0090 }
            a((java.io.ByteArrayOutputStream) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x0090 }
            java.lang.String r2 = r4.p     // Catch:{ Throwable -> 0x0090 }
            a((java.io.ByteArrayOutputStream) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x0090 }
            java.lang.String r2 = r4.q     // Catch:{ Throwable -> 0x0090 }
            a((java.io.ByteArrayOutputStream) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x0090 }
            java.lang.String r2 = r4.r     // Catch:{ Throwable -> 0x0090 }
            a((java.io.ByteArrayOutputStream) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x0090 }
            java.lang.String r2 = r4.s     // Catch:{ Throwable -> 0x0090 }
            a((java.io.ByteArrayOutputStream) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x0090 }
            java.lang.String r2 = r4.t     // Catch:{ Throwable -> 0x0090 }
            a((java.io.ByteArrayOutputStream) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x0090 }
            java.lang.String r2 = r4.u     // Catch:{ Throwable -> 0x0090 }
            a((java.io.ByteArrayOutputStream) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x0090 }
            java.lang.String r2 = r4.v     // Catch:{ Throwable -> 0x0090 }
            a((java.io.ByteArrayOutputStream) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x0090 }
            java.lang.String r2 = r4.w     // Catch:{ Throwable -> 0x0090 }
            a((java.io.ByteArrayOutputStream) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x0090 }
            java.lang.String r2 = r4.x     // Catch:{ Throwable -> 0x0090 }
            a((java.io.ByteArrayOutputStream) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x0090 }
            java.lang.String r4 = r4.y     // Catch:{ Throwable -> 0x0090 }
            a((java.io.ByteArrayOutputStream) r1, (java.lang.String) r4)     // Catch:{ Throwable -> 0x0090 }
            byte[] r3 = a((android.content.Context) r3, (java.io.ByteArrayOutputStream) r1)     // Catch:{ Throwable -> 0x0090 }
            r1.close()     // Catch:{ Throwable -> 0x008b }
            goto L_0x008f
        L_0x008b:
            r4 = move-exception
            r4.printStackTrace()
        L_0x008f:
            return r3
        L_0x0090:
            r3 = move-exception
            goto L_0x0097
        L_0x0092:
            r3 = move-exception
            r1 = r0
            goto L_0x00aa
        L_0x0095:
            r3 = move-exception
            r1 = r0
        L_0x0097:
            java.lang.String r4 = "CI"
            java.lang.String r2 = "gzx"
            com.amap.api.services.a.ci.a((java.lang.Throwable) r3, (java.lang.String) r4, (java.lang.String) r2)     // Catch:{ all -> 0x00a9 }
            if (r1 == 0) goto L_0x00a8
            r1.close()     // Catch:{ Throwable -> 0x00a4 }
            goto L_0x00a8
        L_0x00a4:
            r3 = move-exception
            r3.printStackTrace()
        L_0x00a8:
            return r0
        L_0x00a9:
            r3 = move-exception
        L_0x00aa:
            if (r1 == 0) goto L_0x00b4
            r1.close()     // Catch:{ Throwable -> 0x00b0 }
            goto L_0x00b4
        L_0x00b0:
            r4 = move-exception
            r4.printStackTrace()
        L_0x00b4:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.bs.b(android.content.Context, com.amap.api.services.a.bs$a):byte[]");
    }

    private static byte[] a(Context context, ByteArrayOutputStream byteArrayOutputStream) throws CertificateException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException {
        return b(context, bz.b(byteArrayOutputStream.toByteArray()));
    }

    public static void a(ByteArrayOutputStream byteArrayOutputStream, String str) {
        byte b;
        if (!TextUtils.isEmpty(str)) {
            if (str.getBytes().length > 255) {
                b = -1;
            } else {
                b = (byte) str.getBytes().length;
            }
            bz.a(byteArrayOutputStream, b, bz.a(str));
            return;
        }
        bz.a(byteArrayOutputStream, (byte) 0, new byte[0]);
    }

    private static a b(Context context, boolean z) {
        a aVar = new a();
        aVar.f4354a = bt.u(context);
        aVar.b = bt.m(context);
        String h = bt.h(context);
        if (h == null) {
            h = "";
        }
        aVar.c = h;
        aVar.d = bp.c(context);
        aVar.e = Build.MODEL;
        aVar.f = Build.MANUFACTURER;
        aVar.g = Build.DEVICE;
        aVar.h = bp.b(context);
        aVar.i = bp.d(context);
        aVar.j = String.valueOf(Build.VERSION.SDK_INT);
        aVar.k = bt.w(context);
        aVar.l = bt.t(context);
        aVar.m = bt.q(context) + "";
        aVar.n = bt.p(context) + "";
        aVar.o = bt.y(context);
        aVar.p = bt.o(context);
        if (z) {
            aVar.q = "";
        } else {
            aVar.q = bt.l(context);
        }
        if (z) {
            aVar.r = "";
        } else {
            aVar.r = bt.k(context);
        }
        if (z) {
            aVar.s = "";
            aVar.t = "";
        } else {
            String[] n = bt.n(context);
            aVar.s = n[0];
            aVar.t = n[1];
        }
        aVar.w = bt.a();
        String b = bt.b(context);
        if (!TextUtils.isEmpty(b)) {
            aVar.x = b;
        } else {
            aVar.x = "";
        }
        aVar.y = "aid=" + bt.j(context) + "|serial=" + bt.i(context) + "|storage=" + bt.c() + "|ram=" + bt.x(context) + "|arch=" + bt.d();
        String a2 = bt.a(context);
        if (!TextUtils.isEmpty(a2)) {
            aVar.y += "|adiuExtras=" + a2;
        }
        String a3 = bt.a(context, ",", true);
        if (!TextUtils.isEmpty(a3)) {
            aVar.y += "|multiImeis=" + a3;
        }
        String v = bt.v(context);
        if (!TextUtils.isEmpty(v)) {
            aVar.y += "|meid=" + v;
        }
        return aVar;
    }

    private static class a {

        /* renamed from: a  reason: collision with root package name */
        String f4354a;
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
    }
}
