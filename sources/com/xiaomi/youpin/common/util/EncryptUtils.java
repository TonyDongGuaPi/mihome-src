package com.xiaomi.youpin.common.util;

import android.util.Base64;
import com.alipay.mobile.security.bio.utils.DESCoder;
import com.coloros.mcssdk.c.a;
import com.miuipub.internal.hybrid.SignUtils;
import java.io.File;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class EncryptUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final char[] f23249a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    private EncryptUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static String a(String str) {
        return (str == null || str.length() == 0) ? "" : a(str.getBytes());
    }

    public static String a(byte[] bArr) {
        return o(b(bArr));
    }

    public static byte[] b(byte[] bArr) {
        return a(bArr, "MD2");
    }

    public static String b(String str) {
        return (str == null || str.length() == 0) ? "" : c(str.getBytes());
    }

    public static String a(String str, String str2) {
        if (str == null && str2 == null) {
            return "";
        }
        if (str2 == null) {
            return o(d(str.getBytes()));
        }
        if (str == null) {
            return o(d(str2.getBytes()));
        }
        return o(d((str + str2).getBytes()));
    }

    public static String c(byte[] bArr) {
        return o(d(bArr));
    }

    public static String a(byte[] bArr, byte[] bArr2) {
        if (bArr == null && bArr2 == null) {
            return "";
        }
        if (bArr2 == null) {
            return o(d(bArr));
        }
        if (bArr == null) {
            return o(d(bArr2));
        }
        byte[] bArr3 = new byte[(bArr.length + bArr2.length)];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return o(d(bArr3));
    }

    public static byte[] d(byte[] bArr) {
        return a(bArr, "MD5");
    }

    public static String c(String str) {
        return a(k(str) ? null : new File(str));
    }

    public static byte[] d(String str) {
        return b(k(str) ? null : new File(str));
    }

    public static String a(File file) {
        return o(b(file));
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x003b A[SYNTHETIC, Splitter:B:24:0x003b] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0047 A[SYNTHETIC, Splitter:B:31:0x0047] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] b(java.io.File r4) {
        /*
            r0 = 0
            if (r4 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ IOException | NoSuchAlgorithmException -> 0x0034, all -> 0x0031 }
            r1.<init>(r4)     // Catch:{ IOException | NoSuchAlgorithmException -> 0x0034, all -> 0x0031 }
            java.lang.String r4 = "MD5"
            java.security.MessageDigest r4 = java.security.MessageDigest.getInstance(r4)     // Catch:{ IOException | NoSuchAlgorithmException -> 0x002f }
            java.security.DigestInputStream r2 = new java.security.DigestInputStream     // Catch:{ IOException | NoSuchAlgorithmException -> 0x002f }
            r2.<init>(r1, r4)     // Catch:{ IOException | NoSuchAlgorithmException -> 0x002f }
            r4 = 262144(0x40000, float:3.67342E-40)
            byte[] r4 = new byte[r4]     // Catch:{ IOException | NoSuchAlgorithmException -> 0x002f }
        L_0x0018:
            int r3 = r2.read(r4)     // Catch:{ IOException | NoSuchAlgorithmException -> 0x002f }
            if (r3 > 0) goto L_0x0018
            java.security.MessageDigest r4 = r2.getMessageDigest()     // Catch:{ IOException | NoSuchAlgorithmException -> 0x002f }
            byte[] r4 = r4.digest()     // Catch:{ IOException | NoSuchAlgorithmException -> 0x002f }
            r1.close()     // Catch:{ IOException -> 0x002a }
            goto L_0x002e
        L_0x002a:
            r0 = move-exception
            r0.printStackTrace()
        L_0x002e:
            return r4
        L_0x002f:
            r4 = move-exception
            goto L_0x0036
        L_0x0031:
            r4 = move-exception
            r1 = r0
            goto L_0x0045
        L_0x0034:
            r4 = move-exception
            r1 = r0
        L_0x0036:
            r4.printStackTrace()     // Catch:{ all -> 0x0044 }
            if (r1 == 0) goto L_0x0043
            r1.close()     // Catch:{ IOException -> 0x003f }
            goto L_0x0043
        L_0x003f:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0043:
            return r0
        L_0x0044:
            r4 = move-exception
        L_0x0045:
            if (r1 == 0) goto L_0x004f
            r1.close()     // Catch:{ IOException -> 0x004b }
            goto L_0x004f
        L_0x004b:
            r0 = move-exception
            r0.printStackTrace()
        L_0x004f:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.common.util.EncryptUtils.b(java.io.File):byte[]");
    }

    public static String e(String str) {
        return (str == null || str.length() == 0) ? "" : e(str.getBytes());
    }

    public static String e(byte[] bArr) {
        return o(f(bArr));
    }

    public static byte[] f(byte[] bArr) {
        return a(bArr, "SHA1");
    }

    public static String f(String str) {
        return (str == null || str.length() == 0) ? "" : g(str.getBytes());
    }

    public static String g(byte[] bArr) {
        return o(h(bArr));
    }

    public static byte[] h(byte[] bArr) {
        return a(bArr, "SHA224");
    }

    public static String g(String str) {
        return (str == null || str.length() == 0) ? "" : i(str.getBytes());
    }

    public static String i(byte[] bArr) {
        return o(j(bArr));
    }

    public static byte[] j(byte[] bArr) {
        return a(bArr, "SHA256");
    }

    public static String h(String str) {
        return (str == null || str.length() == 0) ? "" : k(str.getBytes());
    }

    public static String k(byte[] bArr) {
        return o(l(bArr));
    }

    public static byte[] l(byte[] bArr) {
        return a(bArr, "SHA384");
    }

    public static String i(String str) {
        return (str == null || str.length() == 0) ? "" : m(str.getBytes());
    }

    public static String m(byte[] bArr) {
        return o(n(bArr));
    }

    public static byte[] n(byte[] bArr) {
        return a(bArr, "SHA512");
    }

    private static byte[] a(byte[] bArr, String str) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance(str);
            instance.update(bArr);
            return instance.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String b(String str, String str2) {
        return (str == null || str.length() == 0 || str2 == null || str2.length() == 0) ? "" : b(str.getBytes(), str2.getBytes());
    }

    public static String b(byte[] bArr, byte[] bArr2) {
        return o(c(bArr, bArr2));
    }

    public static byte[] c(byte[] bArr, byte[] bArr2) {
        return a(bArr, bArr2, "HmacMD5");
    }

    public static String c(String str, String str2) {
        return (str == null || str.length() == 0 || str2 == null || str2.length() == 0) ? "" : d(str.getBytes(), str2.getBytes());
    }

    public static String d(byte[] bArr, byte[] bArr2) {
        return o(e(bArr, bArr2));
    }

    public static byte[] e(byte[] bArr, byte[] bArr2) {
        return a(bArr, bArr2, "HmacSHA1");
    }

    public static String d(String str, String str2) {
        return (str == null || str.length() == 0 || str2 == null || str2.length() == 0) ? "" : f(str.getBytes(), str2.getBytes());
    }

    public static String f(byte[] bArr, byte[] bArr2) {
        return o(g(bArr, bArr2));
    }

    public static byte[] g(byte[] bArr, byte[] bArr2) {
        return a(bArr, bArr2, "HmacSHA224");
    }

    public static String e(String str, String str2) {
        return (str == null || str.length() == 0 || str2 == null || str2.length() == 0) ? "" : h(str.getBytes(), str2.getBytes());
    }

    public static String h(byte[] bArr, byte[] bArr2) {
        return o(i(bArr, bArr2));
    }

    public static byte[] i(byte[] bArr, byte[] bArr2) {
        return a(bArr, bArr2, "HmacSHA256");
    }

    public static String f(String str, String str2) {
        return (str == null || str.length() == 0 || str2 == null || str2.length() == 0) ? "" : j(str.getBytes(), str2.getBytes());
    }

    public static String j(byte[] bArr, byte[] bArr2) {
        return o(k(bArr, bArr2));
    }

    public static byte[] k(byte[] bArr, byte[] bArr2) {
        return a(bArr, bArr2, "HmacSHA384");
    }

    public static String g(String str, String str2) {
        return (str == null || str.length() == 0 || str2 == null || str2.length() == 0) ? "" : l(str.getBytes(), str2.getBytes());
    }

    public static String l(byte[] bArr, byte[] bArr2) {
        return o(m(bArr, bArr2));
    }

    public static byte[] m(byte[] bArr, byte[] bArr2) {
        return a(bArr, bArr2, "HmacSHA512");
    }

    private static byte[] a(byte[] bArr, byte[] bArr2, String str) {
        if (bArr == null || bArr.length == 0 || bArr2 == null || bArr2.length == 0) {
            return null;
        }
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, str);
            Mac instance = Mac.getInstance(str);
            instance.init(secretKeySpec);
            return instance.doFinal(bArr);
        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] a(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return p(c(bArr, bArr2, str, bArr3));
    }

    public static String b(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return o(c(bArr, bArr2, str, bArr3));
    }

    public static byte[] c(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return a(bArr, bArr2, "DES", str, bArr3, true);
    }

    public static byte[] d(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return e(q(bArr), bArr2, str, bArr3);
    }

    public static byte[] a(String str, byte[] bArr, String str2, byte[] bArr2) {
        return e(j(str), bArr, str2, bArr2);
    }

    public static byte[] e(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return a(bArr, bArr2, "DES", str, bArr3, false);
    }

    public static byte[] f(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return p(h(bArr, bArr2, str, bArr3));
    }

    public static String g(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return o(h(bArr, bArr2, str, bArr3));
    }

    public static byte[] h(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return a(bArr, bArr2, DESCoder.ALGORITHM, str, bArr3, true);
    }

    public static byte[] i(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return j(q(bArr), bArr2, str, bArr3);
    }

    public static byte[] b(String str, byte[] bArr, String str2, byte[] bArr2) {
        return j(j(str), bArr, str2, bArr2);
    }

    public static byte[] j(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return a(bArr, bArr2, DESCoder.ALGORITHM, str, bArr3, false);
    }

    public static byte[] k(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return p(m(bArr, bArr2, str, bArr3));
    }

    public static String l(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return o(m(bArr, bArr2, str, bArr3));
    }

    public static byte[] m(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return a(bArr, bArr2, a.b, str, bArr3, true);
    }

    public static byte[] n(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return o(q(bArr), bArr2, str, bArr3);
    }

    public static byte[] c(String str, byte[] bArr, String str2, byte[] bArr2) {
        return o(j(str), bArr, str2, bArr2);
    }

    public static byte[] o(byte[] bArr, byte[] bArr2, String str, byte[] bArr3) {
        return a(bArr, bArr2, a.b, str, bArr3, false);
    }

    private static byte[] a(byte[] bArr, byte[] bArr2, String str, String str2, byte[] bArr3, boolean z) {
        if (bArr == null || bArr.length == 0 || bArr2 == null || bArr2.length == 0) {
            return null;
        }
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, str);
            Cipher instance = Cipher.getInstance(str2);
            int i = 2;
            if (bArr3 != null) {
                if (bArr3.length != 0) {
                    IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr3);
                    if (z) {
                        i = 1;
                    }
                    instance.init(i, secretKeySpec, ivParameterSpec);
                    return instance.doFinal(bArr);
                }
            }
            if (z) {
                i = 1;
            }
            instance.init(i, secretKeySpec);
            return instance.doFinal(bArr);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static byte[] a(byte[] bArr, byte[] bArr2, boolean z, String str) {
        return p(c(bArr, bArr2, z, str));
    }

    public static String b(byte[] bArr, byte[] bArr2, boolean z, String str) {
        return o(c(bArr, bArr2, z, str));
    }

    public static byte[] c(byte[] bArr, byte[] bArr2, boolean z, String str) {
        return a(bArr, bArr2, z, str, true);
    }

    public static byte[] d(byte[] bArr, byte[] bArr2, boolean z, String str) {
        return e(q(bArr), bArr2, z, str);
    }

    public static byte[] a(String str, byte[] bArr, boolean z, String str2) {
        return e(j(str), bArr, z, str2);
    }

    public static byte[] e(byte[] bArr, byte[] bArr2, boolean z, String str) {
        return a(bArr, bArr2, z, str, false);
    }

    private static byte[] a(byte[] bArr, byte[] bArr2, boolean z, String str, boolean z2) {
        Key key;
        if (bArr == null || bArr.length == 0 || bArr2 == null || bArr2.length == 0) {
            return null;
        }
        if (z) {
            try {
                key = KeyFactory.getInstance(SignUtils.f8267a).generatePublic(new X509EncodedKeySpec(bArr2));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return null;
            } catch (NoSuchPaddingException e2) {
                e2.printStackTrace();
                return null;
            } catch (InvalidKeyException e3) {
                e3.printStackTrace();
                return null;
            } catch (BadPaddingException e4) {
                e4.printStackTrace();
                return null;
            } catch (IllegalBlockSizeException e5) {
                e5.printStackTrace();
                return null;
            } catch (InvalidKeySpecException e6) {
                e6.printStackTrace();
                return null;
            }
        } else {
            key = KeyFactory.getInstance(SignUtils.f8267a).generatePrivate(new PKCS8EncodedKeySpec(bArr2));
        }
        if (key == null) {
            return null;
        }
        Cipher instance = Cipher.getInstance(str);
        instance.init(z2 ? 1 : 2, key);
        int length = bArr.length;
        int i = z2 ? 117 : 128;
        int i2 = length / i;
        if (i2 <= 0) {
            return instance.doFinal(bArr);
        }
        byte[] bArr3 = new byte[i];
        byte[] bArr4 = new byte[0];
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            System.arraycopy(bArr, i3, bArr3, 0, i);
            bArr4 = n(bArr4, instance.doFinal(bArr3));
            i3 += i;
        }
        if (i3 == length) {
            return bArr4;
        }
        int i5 = length - i3;
        byte[] bArr5 = new byte[i5];
        System.arraycopy(bArr, i3, bArr5, 0, i5);
        return n(bArr4, instance.doFinal(bArr5));
    }

    private static byte[] n(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[(bArr.length + bArr2.length)];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    private static String o(byte[] bArr) {
        int length;
        if (bArr == null || (length = bArr.length) <= 0) {
            return "";
        }
        char[] cArr = new char[(length << 1)];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i + 1;
            cArr[i] = f23249a[(bArr[i2] >>> 4) & 15];
            i = i3 + 1;
            cArr[i3] = f23249a[bArr[i2] & 15];
        }
        return new String(cArr);
    }

    private static byte[] j(String str) {
        if (k(str)) {
            return null;
        }
        int length = str.length();
        if (length % 2 != 0) {
            str = "0" + str;
            length++;
        }
        char[] charArray = str.toUpperCase().toCharArray();
        byte[] bArr = new byte[(length >> 1)];
        for (int i = 0; i < length; i += 2) {
            bArr[i >> 1] = (byte) ((a(charArray[i]) << 4) | a(charArray[i + 1]));
        }
        return bArr;
    }

    private static int a(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'A' && c <= 'F') {
            return (c - 'A') + 10;
        }
        throw new IllegalArgumentException();
    }

    private static byte[] p(byte[] bArr) {
        return Base64.encode(bArr, 2);
    }

    private static byte[] q(byte[] bArr) {
        return Base64.decode(bArr, 2);
    }

    private static boolean k(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
