package com.xiaomi.jr.mipay.common.util;

import android.support.media.ExifInterface;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Base64InputStream;
import android.util.Base64OutputStream;
import com.coloros.mcssdk.c.a;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.common.utils.Utils;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import miuipub.reflect.Field;

public class Coder {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10952a = "Coder";
    private static final String[] b = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
    private static final String[] c = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", Field.b, Field.c, Field.h, ExifInterface.LONGITUDE_EAST, Field.g};
    private static final byte[] d = new byte[0];
    private static final String e = "AES/CBC/PKCS5Padding";
    private static final byte[] f = "0102030405060708".getBytes();

    private Coder() {
    }

    public static String a(byte[] bArr) {
        return a(bArr, false);
    }

    public static String a(byte[] bArr, boolean z) {
        StringBuilder sb = new StringBuilder();
        for (byte a2 : bArr) {
            sb.append(a(a2, z));
        }
        return sb.toString();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v8, resolved type: byte} */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=int, for r2v0, types: [int, byte] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(int r2, boolean r3) {
        /*
            if (r2 >= 0) goto L_0x0004
            int r2 = r2 + 256
        L_0x0004:
            int r0 = r2 / 16
            int r2 = r2 % 16
            if (r3 == 0) goto L_0x0022
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String[] r1 = c
            r0 = r1[r0]
            r3.append(r0)
            java.lang.String[] r0 = c
            r2 = r0[r2]
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            return r2
        L_0x0022:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String[] r1 = b
            r0 = r1[r0]
            r3.append(r0)
            java.lang.String[] r0 = b
            r2 = r0[r2]
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.jr.mipay.common.util.Coder.a(byte, boolean):java.lang.String");
    }

    public static final String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA");
            instance.update(str.getBytes());
            return a(instance.digest());
        } catch (NoSuchAlgorithmException e2) {
            throw new IllegalStateException(e2);
        }
    }

    public static final byte[] b(String str) {
        if (TextUtils.isEmpty(str)) {
            return d;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA");
            instance.update(str.getBytes());
            return instance.digest();
        } catch (NoSuchAlgorithmException e2) {
            throw new IllegalStateException(e2);
        }
    }

    public static final String c(String str) {
        return Base64.encodeToString(str.getBytes(), 2);
    }

    public static final String b(byte[] bArr) {
        return Base64.encodeToString(bArr, 2);
    }

    public static final byte[] d(String str) {
        return Base64.encode(str.getBytes(), 2);
    }

    public static final String e(String str) {
        return new String(Base64.decode(str, 0));
    }

    public static final byte[] f(String str) {
        return Base64.decode(str, 0);
    }

    public static final OutputStream a(OutputStream outputStream) {
        return new Base64OutputStream(outputStream, 2);
    }

    public static final InputStream a(InputStream inputStream) {
        return new Base64InputStream(inputStream, 0);
    }

    public static final String a(String str, String str2) {
        byte[] f2;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || (f2 = f(str2)) == null || f2.length != 16) {
            return null;
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(f2, a.b);
        try {
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(1, secretKeySpec, new IvParameterSpec(f));
            return b(instance.doFinal(str.getBytes()));
        } catch (Exception e2) {
            MifiLog.b(f10952a, "encrypt AES failed. security=" + str2, e2);
            return null;
        }
    }

    public static final String b(String str, String str2) {
        byte[] f2;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || (f2 = f(str2)) == null || f2.length != 16) {
            return null;
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(f2, a.b);
        try {
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(2, secretKeySpec, new IvParameterSpec(f));
            byte[] f3 = f(str);
            if (f3 == null) {
                return null;
            }
            return new String(instance.doFinal(f3));
        } catch (Exception e2) {
            MifiLog.b(f10952a, "decrypt AES failed. security=" + str2, e2);
            return null;
        }
    }

    public static boolean a(String str, String str2, String str3) {
        byte[] f2;
        CipherInputStream cipherInputStream;
        FileOutputStream fileOutputStream;
        if (TextUtils.isEmpty(str3) || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || (f2 = f(str3)) == null || f2.length != 16) {
            return false;
        }
        FileInputStream fileInputStream = null;
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(f2, a.b);
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(1, secretKeySpec, new IvParameterSpec(f));
            byte[] bArr = new byte[1024];
            FileInputStream fileInputStream2 = new FileInputStream(str);
            try {
                fileOutputStream = new FileOutputStream(str2);
            } catch (Exception e2) {
                e = e2;
                fileOutputStream = null;
                cipherInputStream = null;
                fileInputStream = fileInputStream2;
                try {
                    MifiLog.b(f10952a, "error happened while encrypting file", e);
                    Utils.a((Closeable) fileInputStream);
                    Utils.a((Closeable) fileInputStream);
                    Utils.a((Closeable) fileOutputStream);
                    Utils.a((Closeable) cipherInputStream);
                    return false;
                } catch (Throwable th) {
                    th = th;
                    Utils.a((Closeable) fileInputStream);
                    Utils.a((Closeable) fileInputStream);
                    Utils.a((Closeable) fileOutputStream);
                    Utils.a((Closeable) cipherInputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = null;
                cipherInputStream = null;
                fileInputStream = fileInputStream2;
                Utils.a((Closeable) fileInputStream);
                Utils.a((Closeable) fileInputStream);
                Utils.a((Closeable) fileOutputStream);
                Utils.a((Closeable) cipherInputStream);
                throw th;
            }
            try {
                cipherInputStream = new CipherInputStream(fileInputStream2, instance);
                while (true) {
                    try {
                        int read = cipherInputStream.read(bArr);
                        if (read != -1) {
                            fileOutputStream.write(bArr, 0, read);
                            fileOutputStream.flush();
                        } else {
                            fileOutputStream.flush();
                            Utils.a((Closeable) fileInputStream2);
                            Utils.a((Closeable) fileInputStream2);
                            Utils.a((Closeable) fileOutputStream);
                            Utils.a((Closeable) cipherInputStream);
                            return true;
                        }
                    } catch (Exception e3) {
                        e = e3;
                        fileInputStream = fileInputStream2;
                        MifiLog.b(f10952a, "error happened while encrypting file", e);
                        Utils.a((Closeable) fileInputStream);
                        Utils.a((Closeable) fileInputStream);
                        Utils.a((Closeable) fileOutputStream);
                        Utils.a((Closeable) cipherInputStream);
                        return false;
                    } catch (Throwable th3) {
                        th = th3;
                        fileInputStream = fileInputStream2;
                        Utils.a((Closeable) fileInputStream);
                        Utils.a((Closeable) fileInputStream);
                        Utils.a((Closeable) fileOutputStream);
                        Utils.a((Closeable) cipherInputStream);
                        throw th;
                    }
                }
            } catch (Exception e4) {
                e = e4;
                cipherInputStream = null;
                fileInputStream = fileInputStream2;
                MifiLog.b(f10952a, "error happened while encrypting file", e);
                Utils.a((Closeable) fileInputStream);
                Utils.a((Closeable) fileInputStream);
                Utils.a((Closeable) fileOutputStream);
                Utils.a((Closeable) cipherInputStream);
                return false;
            } catch (Throwable th4) {
                th = th4;
                cipherInputStream = null;
                fileInputStream = fileInputStream2;
                Utils.a((Closeable) fileInputStream);
                Utils.a((Closeable) fileInputStream);
                Utils.a((Closeable) fileOutputStream);
                Utils.a((Closeable) cipherInputStream);
                throw th;
            }
        } catch (Exception e5) {
            e = e5;
            fileOutputStream = null;
            cipherInputStream = null;
            MifiLog.b(f10952a, "error happened while encrypting file", e);
            Utils.a((Closeable) fileInputStream);
            Utils.a((Closeable) fileInputStream);
            Utils.a((Closeable) fileOutputStream);
            Utils.a((Closeable) cipherInputStream);
            return false;
        } catch (Throwable th5) {
            th = th5;
            fileOutputStream = null;
            cipherInputStream = null;
            Utils.a((Closeable) fileInputStream);
            Utils.a((Closeable) fileInputStream);
            Utils.a((Closeable) fileOutputStream);
            Utils.a((Closeable) cipherInputStream);
            throw th;
        }
    }

    public static boolean b(String str, String str2, String str3) {
        byte[] f2;
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        CipherOutputStream cipherOutputStream;
        FileInputStream fileInputStream2;
        CipherOutputStream cipherOutputStream2;
        if (TextUtils.isEmpty(str3) || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || (f2 = f(str3)) == null || f2.length != 16) {
            return false;
        }
        Closeable closeable = null;
        try {
            File file = new File(str);
            File file2 = new File(str2);
            if (!file.exists() || !file.isFile()) {
                cipherOutputStream2 = null;
                fileInputStream2 = null;
            } else {
                if (!file2.getParentFile().exists()) {
                    file2.getParentFile().mkdirs();
                }
                file2.createNewFile();
                FileInputStream fileInputStream3 = new FileInputStream(file);
                try {
                    fileOutputStream = new FileOutputStream(file2);
                    try {
                        SecretKeySpec secretKeySpec = new SecretKeySpec(f2, a.b);
                        IvParameterSpec ivParameterSpec = new IvParameterSpec("0102030405060708".getBytes());
                        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
                        instance.init(2, secretKeySpec, ivParameterSpec);
                        cipherOutputStream2 = new CipherOutputStream(fileOutputStream, instance);
                    } catch (Exception e2) {
                        e = e2;
                        closeable = fileInputStream3;
                        cipherOutputStream = null;
                        try {
                            MifiLog.b(f10952a, "error happened while decrypting file", e);
                            Utils.a(closeable);
                            Utils.a((Closeable) fileOutputStream);
                            Utils.a((Closeable) cipherOutputStream);
                            return false;
                        } catch (Throwable th) {
                            th = th;
                            Closeable closeable2 = closeable;
                            closeable = cipherOutputStream;
                            fileInputStream = closeable2;
                            Utils.a(fileInputStream);
                            Utils.a((Closeable) fileOutputStream);
                            Utils.a(closeable);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        fileInputStream = fileInputStream3;
                        Utils.a(fileInputStream);
                        Utils.a((Closeable) fileOutputStream);
                        Utils.a(closeable);
                        throw th;
                    }
                    try {
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int read = fileInputStream3.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            cipherOutputStream2.write(bArr, 0, read);
                            cipherOutputStream2.flush();
                        }
                        closeable = fileOutputStream;
                        fileInputStream2 = fileInputStream3;
                    } catch (Exception e3) {
                        closeable = fileInputStream3;
                        cipherOutputStream = cipherOutputStream2;
                        e = e3;
                        MifiLog.b(f10952a, "error happened while decrypting file", e);
                        Utils.a(closeable);
                        Utils.a((Closeable) fileOutputStream);
                        Utils.a((Closeable) cipherOutputStream);
                        return false;
                    } catch (Throwable th3) {
                        closeable = cipherOutputStream2;
                        th = th3;
                        fileInputStream = fileInputStream3;
                        Utils.a(fileInputStream);
                        Utils.a((Closeable) fileOutputStream);
                        Utils.a(closeable);
                        throw th;
                    }
                } catch (Exception e4) {
                    e = e4;
                    fileOutputStream = null;
                    closeable = fileInputStream3;
                    cipherOutputStream = null;
                    MifiLog.b(f10952a, "error happened while decrypting file", e);
                    Utils.a(closeable);
                    Utils.a((Closeable) fileOutputStream);
                    Utils.a((Closeable) cipherOutputStream);
                    return false;
                } catch (Throwable th4) {
                    th = th4;
                    fileOutputStream = null;
                    fileInputStream = fileInputStream3;
                    Utils.a(fileInputStream);
                    Utils.a((Closeable) fileOutputStream);
                    Utils.a(closeable);
                    throw th;
                }
            }
            Utils.a((Closeable) fileInputStream2);
            Utils.a(closeable);
            Utils.a((Closeable) cipherOutputStream2);
            return true;
        } catch (Exception e5) {
            e = e5;
            cipherOutputStream = null;
            fileOutputStream = null;
            MifiLog.b(f10952a, "error happened while decrypting file", e);
            Utils.a(closeable);
            Utils.a((Closeable) fileOutputStream);
            Utils.a((Closeable) cipherOutputStream);
            return false;
        } catch (Throwable th5) {
            th = th5;
            fileInputStream = null;
            fileOutputStream = null;
            Utils.a(fileInputStream);
            Utils.a((Closeable) fileOutputStream);
            Utils.a(closeable);
            throw th;
        }
    }

    public static String a() {
        try {
            KeyGenerator instance = KeyGenerator.getInstance(a.b);
            instance.init(128);
            return b(instance.generateKey().getEncoded());
        } catch (NoSuchAlgorithmException e2) {
            MifiLog.b(f10952a, "generate aes key failed", e2);
            return null;
        }
    }
}
