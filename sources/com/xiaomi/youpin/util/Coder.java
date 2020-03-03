package com.xiaomi.youpin.util;

import android.text.TextUtils;
import android.util.Base64;
import com.coloros.mcssdk.c.a;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Coder {

    /* renamed from: a  reason: collision with root package name */
    public static final String f23763a = "AES/CBC/PKCS5Padding";
    private static final String[] b = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            return b(instance.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String a(File file) {
        byte[] bArr = new byte[1024];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                MessageDigest instance = MessageDigest.getInstance("MD5");
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read > 0) {
                        instance.update(bArr, 0, read);
                    } else {
                        try {
                            break;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                fileInputStream.close();
                return b(instance.digest());
            } catch (NoSuchAlgorithmException e2) {
                e2.printStackTrace();
                try {
                    fileInputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
                return null;
            } catch (IOException e4) {
                e4.printStackTrace();
                try {
                    fileInputStream.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
                return null;
            } catch (Throwable th) {
                try {
                    fileInputStream.close();
                } catch (IOException e6) {
                    e6.printStackTrace();
                }
                throw th;
            }
        } catch (FileNotFoundException e7) {
            e7.printStackTrace();
            return null;
        }
    }

    private static String b(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte a2 : bArr) {
            stringBuffer.append(a(a2));
        }
        return stringBuffer.toString();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: byte} */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=int, for r3v0, types: [int, byte] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String a(int r3) {
        /*
            if (r3 >= 0) goto L_0x0004
            int r3 = r3 + 256
        L_0x0004:
            int r0 = r3 / 16
            int r3 = r3 % 16
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String[] r2 = b
            r0 = r2[r0]
            r1.append(r0)
            java.lang.String[] r0 = b
            r3 = r0[r3]
            r1.append(r3)
            java.lang.String r3 = r1.toString()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.util.Coder.a(byte):java.lang.String");
    }

    public static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA");
            instance.update(str.getBytes());
            return b(instance.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] c(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA");
            instance.update(str.getBytes());
            return instance.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String d(String str) {
        return Base64.encodeToString(str.getBytes(), 2);
    }

    public static String a(byte[] bArr) {
        return Base64.encodeToString(bArr, 2);
    }

    public static byte[] e(String str) {
        return Base64.encode(str.getBytes(), 2);
    }

    public static String f(String str) {
        return new String(Base64.decode(str, 0));
    }

    public static byte[] g(String str) {
        return Base64.decode(str, 0);
    }

    public static String a(String str, String str2) {
        byte[] g;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || (g = g(str2)) == null || g.length != 16) {
            return null;
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(g, a.b);
        try {
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(1, secretKeySpec, new IvParameterSpec("0102030405060708".getBytes()));
            return a(instance.doFinal(str.getBytes()));
        } catch (NoSuchAlgorithmException unused) {
            return null;
        } catch (NoSuchPaddingException unused2) {
            return null;
        } catch (InvalidKeyException unused3) {
            return null;
        } catch (InvalidAlgorithmParameterException unused4) {
            return null;
        } catch (IllegalBlockSizeException unused5) {
            return null;
        } catch (BadPaddingException unused6) {
            return null;
        }
    }

    public static String b(String str, String str2) {
        byte[] g;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || (g = g(str2)) == null || g.length != 16) {
            return null;
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(g, a.b);
        try {
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(2, secretKeySpec, new IvParameterSpec("0102030405060708".getBytes()));
            byte[] g2 = g(str);
            if (g2 == null) {
                return null;
            }
            return new String(instance.doFinal(g2));
        } catch (NoSuchAlgorithmException unused) {
            return null;
        } catch (NoSuchPaddingException unused2) {
            return null;
        } catch (InvalidKeyException unused3) {
            return null;
        } catch (InvalidAlgorithmParameterException unused4) {
            return null;
        } catch (IllegalBlockSizeException unused5) {
            return null;
        } catch (BadPaddingException unused6) {
            return null;
        }
    }
}
