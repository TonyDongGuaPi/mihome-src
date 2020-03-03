package com.tencent.smtt.utils;

import com.tencent.smtt.sdk.a.a;
import java.security.Provider;
import java.security.Security;
import java.util.Random;

public class o {

    /* renamed from: a  reason: collision with root package name */
    private static final char[] f9212a = "0123456789abcdef".toCharArray();
    private static o b;
    private String c = (this.e + String.valueOf(new Random().nextInt(89999999) + 10000000));
    private String d;
    private String e = String.valueOf(new Random().nextInt(89999999) + 10000000);

    private o() {
    }

    public static synchronized o a() {
        o oVar;
        synchronized (o.class) {
            if (b == null) {
                b = new o();
            }
            oVar = b;
        }
        return oVar;
    }

    private String b(byte[] bArr) {
        char[] cArr = new char[(bArr.length * 2)];
        for (int i = 0; i < bArr.length; i++) {
            byte b2 = bArr[i] & 255;
            int i2 = i * 2;
            cArr[i2] = f9212a[b2 >>> 4];
            cArr[i2 + 1] = f9212a[b2 & 15];
        }
        return new String(cArr);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:?, code lost:
        b();
        r0 = javax.crypto.Cipher.getInstance("RSA/ECB/NoPadding");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0015, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0016, code lost:
        r0.printStackTrace();
        r0 = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x000b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String a(java.lang.String r5) {
        /*
            r4 = this;
            byte[] r5 = r5.getBytes()
            java.lang.String r0 = "RSA/ECB/NoPadding"
            javax.crypto.Cipher r0 = javax.crypto.Cipher.getInstance(r0)     // Catch:{ Exception -> 0x000b }
            goto L_0x001a
        L_0x000b:
            r4.b()     // Catch:{ Exception -> 0x0015 }
            java.lang.String r0 = "RSA/ECB/NoPadding"
            javax.crypto.Cipher r0 = javax.crypto.Cipher.getInstance(r0)     // Catch:{ Exception -> 0x0015 }
            goto L_0x001a
        L_0x0015:
            r0 = move-exception
            r0.printStackTrace()
            r0 = 0
        L_0x001a:
            java.lang.String r1 = "MCwwDQYJKoZIhvcNAQEBBQADGwAwGAIRAMRB/Q0hTCD+XtnQhpQJefUCAwEAAQ=="
            byte[] r1 = r1.getBytes()
            java.security.spec.X509EncodedKeySpec r2 = new java.security.spec.X509EncodedKeySpec
            r3 = 0
            byte[] r1 = android.util.Base64.decode(r1, r3)
            r2.<init>(r1)
            java.lang.String r1 = "RSA"
            java.security.KeyFactory r1 = java.security.KeyFactory.getInstance(r1)
            java.security.PublicKey r1 = r1.generatePublic(r2)
            r2 = 1
            r0.init(r2, r1)
            byte[] r5 = r0.doFinal(r5)
            java.lang.String r5 = r4.b(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.o.a(java.lang.String):java.lang.String");
    }

    public byte[] a(byte[] bArr) {
        return a.a(this.e.getBytes(), bArr, 1);
    }

    public void b() {
        Security.addProvider((Provider) Class.forName("com.android.org.bouncycastle.jce.provider.BouncyCastleProvider", true, ClassLoader.getSystemClassLoader()).newInstance());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:?, code lost:
        b();
        r2 = javax.crypto.Cipher.getInstance("RSA/ECB/NoPadding");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001c, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001d, code lost:
        r2.printStackTrace();
        r2 = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0012 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String c() {
        /*
            r5 = this;
            java.lang.String r0 = r5.d
            if (r0 != 0) goto L_0x0049
            java.lang.String r0 = r5.c
            byte[] r0 = r0.getBytes()
            r1 = 0
            java.lang.String r2 = "RSA/ECB/NoPadding"
            javax.crypto.Cipher r2 = javax.crypto.Cipher.getInstance(r2)     // Catch:{ Exception -> 0x0012 }
            goto L_0x0021
        L_0x0012:
            r5.b()     // Catch:{ Exception -> 0x001c }
            java.lang.String r2 = "RSA/ECB/NoPadding"
            javax.crypto.Cipher r2 = javax.crypto.Cipher.getInstance(r2)     // Catch:{ Exception -> 0x001c }
            goto L_0x0021
        L_0x001c:
            r2 = move-exception
            r2.printStackTrace()
            r2 = r1
        L_0x0021:
            java.lang.String r1 = "MCwwDQYJKoZIhvcNAQEBBQADGwAwGAIRAMRB/Q0hTCD+XtnQhpQJefUCAwEAAQ=="
            byte[] r1 = r1.getBytes()
            java.security.spec.X509EncodedKeySpec r3 = new java.security.spec.X509EncodedKeySpec
            r4 = 0
            byte[] r1 = android.util.Base64.decode(r1, r4)
            r3.<init>(r1)
            java.lang.String r1 = "RSA"
            java.security.KeyFactory r1 = java.security.KeyFactory.getInstance(r1)
            java.security.PublicKey r1 = r1.generatePublic(r3)
            r3 = 1
            r2.init(r3, r1)
            byte[] r0 = r2.doFinal(r0)
            java.lang.String r0 = r5.b(r0)
            r5.d = r0
        L_0x0049:
            java.lang.String r0 = r5.d
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.o.c():java.lang.String");
    }
}
