package com.xiaomi.mishopsdk.util;

import android.text.TextUtils;
import android.util.Base64;
import com.coloros.mcssdk.c.a;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Coder {
    public static final String AES_CBC = "AES/CBC/PKCS5Padding";
    public static final String AES_ECB = "AES/ECB/PKCS5Padding";
    private static final String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    public static final String encodeMD5(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            return byteArrayToString(instance.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static final String encodeMD5(File file) {
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
                return byteArrayToString(instance.digest());
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

    private static String byteArrayToString(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte byteToHexString : bArr) {
            stringBuffer.append(byteToHexString(byteToHexString));
        }
        return stringBuffer.toString();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: byte} */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=int, for r3v0, types: [int, byte] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String byteToHexString(int r3) {
        /*
            if (r3 >= 0) goto L_0x0004
            int r3 = r3 + 256
        L_0x0004:
            int r0 = r3 / 16
            int r3 = r3 % 16
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String[] r2 = hexDigits
            r0 = r2[r0]
            r1.append(r0)
            java.lang.String[] r0 = hexDigits
            r3 = r0[r3]
            r1.append(r3)
            java.lang.String r3 = r1.toString()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mishopsdk.util.Coder.byteToHexString(byte):java.lang.String");
    }

    public static final String encodeSHA(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA");
            instance.update(str.getBytes());
            return byteArrayToString(instance.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static final byte[] encodeSHABytes(String str) {
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

    public static final String encodeBase64(String str) {
        return Base64.encodeToString(str.getBytes(), 2);
    }

    public static final String encodeBase64(byte[] bArr) {
        return Base64.encodeToString(bArr, 2);
    }

    public static final byte[] encodeBase64Bytes(String str) {
        return Base64.encode(str.getBytes(), 2);
    }

    public static final String decodeBase64(String str) {
        return new String(Base64.decode(str, 0));
    }

    public static final byte[] decodeBase64Bytes(String str) {
        return Base64.decode(str, 0);
    }

    public static final String encryptAES_CBC(String str, String str2) {
        byte[] decodeBase64Bytes;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || (decodeBase64Bytes = decodeBase64Bytes(str2)) == null || decodeBase64Bytes.length != 16) {
            return null;
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(decodeBase64Bytes, a.b);
        try {
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(1, secretKeySpec, new IvParameterSpec("0102030405060708".getBytes()));
            return encodeBase64(instance.doFinal(str.getBytes()));
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

    public static final String decryptAES_CBC(String str, String str2) {
        byte[] decodeBase64Bytes;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || (decodeBase64Bytes = decodeBase64Bytes(str2)) == null || decodeBase64Bytes.length != 16) {
            return null;
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(decodeBase64Bytes, a.b);
        try {
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(2, secretKeySpec, new IvParameterSpec("0102030405060708".getBytes()));
            byte[] decodeBase64Bytes2 = decodeBase64Bytes(str);
            if (decodeBase64Bytes2 == null) {
                return null;
            }
            return new String(instance.doFinal(decodeBase64Bytes2));
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

    public static String encrypt_AES_ECB(String str, String str2) {
        byte[] bArr;
        String str3 = "";
        if (str2.length() != 16) {
            if (str2.length() > 16) {
                str2 = str2.substring(0, 16);
            } else {
                for (int i = 0; i < 16 - str2.length(); i++) {
                    str3 = str2 + "0";
                }
                str2 = str3;
            }
        }
        try {
            Key generateKey = generateKey(str2);
            Cipher instance = Cipher.getInstance("AES/ECB/PKCS5Padding");
            instance.init(1, generateKey);
            bArr = instance.doFinal(str.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            bArr = null;
        }
        return new String(org.apache.commons.codec.binary.Base64.encodeBase64(bArr));
    }

    private static Key generateKey(String str) throws Exception {
        try {
            return new SecretKeySpec(str.getBytes(), a.b);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static final String decryptAES_ECB(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || str2.length() != 16) {
            return null;
        }
        try {
            Key generateKey = generateKey(str2);
            Cipher instance = Cipher.getInstance("AES/ECB/PKCS5Padding");
            instance.init(2, generateKey);
            byte[] decodeBase64 = org.apache.commons.codec.binary.Base64.decodeBase64(str.getBytes());
            if (decodeBase64 == null) {
                return null;
            }
            return new String(instance.doFinal(decodeBase64));
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
        } catch (Exception unused7) {
            return null;
        }
    }
}
