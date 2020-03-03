package com.mibi.common.data;

import android.support.media.ExifInterface;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Base64InputStream;
import android.util.Base64OutputStream;
import com.coloros.mcssdk.c.a;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import miuipub.reflect.Field;

public class Coder {

    /* renamed from: a  reason: collision with root package name */
    public static final String f7505a = "AES/CBC/PKCS5Padding";
    private static final String[] b = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
    private static final String[] c = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", Field.b, Field.c, Field.h, ExifInterface.LONGITUDE_EAST, Field.g};

    public static final byte[] a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            return instance.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static final String b(String str) {
        byte[] a2 = a(str);
        if (a2 != null) {
            return a(a2);
        }
        return null;
    }

    public static final String a(File file) {
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
                return a(instance.digest());
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

    public static String a(byte[] bArr) {
        return a(bArr, false);
    }

    public static String a(byte[] bArr, boolean z) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte a2 : bArr) {
            stringBuffer.append(a(a2, z));
        }
        return stringBuffer.toString();
    }

    public static String a(byte b2) {
        return a(b2, false);
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
        throw new UnsupportedOperationException("Method not decompiled: com.mibi.common.data.Coder.a(byte, boolean):java.lang.String");
    }

    public static final String c(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA");
            instance.update(str.getBytes());
            return a(instance.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static final byte[] d(String str) {
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

    public static final String e(String str) {
        return Base64.encodeToString(str.getBytes(), 2);
    }

    public static final String b(byte[] bArr) {
        return Base64.encodeToString(bArr, 2);
    }

    public static final byte[] f(String str) {
        return Base64.encode(str.getBytes(), 2);
    }

    public static final String g(String str) {
        return new String(Base64.decode(str, 2));
    }

    public static final byte[] h(String str) {
        return Base64.decode(str, 2);
    }

    public static final OutputStream a(OutputStream outputStream) {
        return new Base64OutputStream(outputStream, 2);
    }

    public static final InputStream a(InputStream inputStream) {
        return new Base64InputStream(inputStream, 2);
    }

    public static final String a(String str, String str2) {
        byte[] h;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || (h = h(str2)) == null || h.length != 16) {
            return null;
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(h, a.b);
        try {
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(1, secretKeySpec, new IvParameterSpec("0102030405060708".getBytes()));
            return b(instance.doFinal(str.getBytes()));
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

    public static final String b(String str, String str2) {
        byte[] h;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || (h = h(str2)) == null || h.length != 16) {
            return null;
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(h, a.b);
        try {
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(2, secretKeySpec, new IvParameterSpec("0102030405060708".getBytes()));
            byte[] h2 = h(str);
            if (h2 == null) {
                return null;
            }
            return new String(instance.doFinal(h2));
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

    /* JADX WARNING: Removed duplicated region for block: B:102:0x00f5 A[SYNTHETIC, Splitter:B:102:0x00f5] */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x00fa A[Catch:{ Exception -> 0x0103 }] */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x00ff A[Catch:{ Exception -> 0x0103 }] */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x010c A[SYNTHETIC, Splitter:B:115:0x010c] */
    /* JADX WARNING: Removed duplicated region for block: B:118:0x0111 A[Catch:{ Exception -> 0x011a }] */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x0116 A[Catch:{ Exception -> 0x011a }] */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x0123 A[SYNTHETIC, Splitter:B:128:0x0123] */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x0128 A[Catch:{ Exception -> 0x0131 }] */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x012d A[Catch:{ Exception -> 0x0131 }] */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x013a A[SYNTHETIC, Splitter:B:141:0x013a] */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x013f A[Catch:{ Exception -> 0x0148 }] */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x0144 A[Catch:{ Exception -> 0x0148 }] */
    /* JADX WARNING: Removed duplicated region for block: B:151:0x014c A[SYNTHETIC, Splitter:B:151:0x014c] */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x0151 A[Catch:{ Exception -> 0x015a }] */
    /* JADX WARNING: Removed duplicated region for block: B:156:0x0156 A[Catch:{ Exception -> 0x015a }] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x00c7 A[SYNTHETIC, Splitter:B:76:0x00c7] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x00cc A[Catch:{ Exception -> 0x00d5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x00d1 A[Catch:{ Exception -> 0x00d5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x00de A[SYNTHETIC, Splitter:B:89:0x00de] */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x00e3 A[Catch:{ Exception -> 0x00ec }] */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x00e8 A[Catch:{ Exception -> 0x00ec }] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:99:0x00f0=Splitter:B:99:0x00f0, B:73:0x00c2=Splitter:B:73:0x00c2, B:138:0x0135=Splitter:B:138:0x0135, B:112:0x0107=Splitter:B:112:0x0107, B:86:0x00d9=Splitter:B:86:0x00d9, B:125:0x011e=Splitter:B:125:0x011e} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(java.lang.String r5, java.lang.String r6, java.lang.String r7) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r7)
            r1 = 0
            if (r0 != 0) goto L_0x015d
            boolean r0 = android.text.TextUtils.isEmpty(r5)
            if (r0 != 0) goto L_0x015d
            boolean r0 = android.text.TextUtils.isEmpty(r6)
            if (r0 == 0) goto L_0x0015
            goto L_0x015d
        L_0x0015:
            byte[] r7 = h(r7)
            if (r7 == 0) goto L_0x015c
            int r0 = r7.length
            r2 = 16
            if (r0 == r2) goto L_0x0022
            goto L_0x015c
        L_0x0022:
            r0 = 0
            javax.crypto.spec.SecretKeySpec r2 = new javax.crypto.spec.SecretKeySpec     // Catch:{ InvalidKeyException -> 0x0132, InvalidAlgorithmParameterException -> 0x011b, NoSuchAlgorithmException -> 0x0104, NoSuchPaddingException -> 0x00ed, FileNotFoundException -> 0x00d6, IOException -> 0x00bf, all -> 0x00ba }
            java.lang.String r3 = "AES"
            r2.<init>(r7, r3)     // Catch:{ InvalidKeyException -> 0x0132, InvalidAlgorithmParameterException -> 0x011b, NoSuchAlgorithmException -> 0x0104, NoSuchPaddingException -> 0x00ed, FileNotFoundException -> 0x00d6, IOException -> 0x00bf, all -> 0x00ba }
            java.lang.String r7 = "AES/CBC/PKCS5Padding"
            javax.crypto.Cipher r7 = javax.crypto.Cipher.getInstance(r7)     // Catch:{ InvalidKeyException -> 0x0132, InvalidAlgorithmParameterException -> 0x011b, NoSuchAlgorithmException -> 0x0104, NoSuchPaddingException -> 0x00ed, FileNotFoundException -> 0x00d6, IOException -> 0x00bf, all -> 0x00ba }
            javax.crypto.spec.IvParameterSpec r3 = new javax.crypto.spec.IvParameterSpec     // Catch:{ InvalidKeyException -> 0x0132, InvalidAlgorithmParameterException -> 0x011b, NoSuchAlgorithmException -> 0x0104, NoSuchPaddingException -> 0x00ed, FileNotFoundException -> 0x00d6, IOException -> 0x00bf, all -> 0x00ba }
            java.lang.String r4 = "0102030405060708"
            byte[] r4 = r4.getBytes()     // Catch:{ InvalidKeyException -> 0x0132, InvalidAlgorithmParameterException -> 0x011b, NoSuchAlgorithmException -> 0x0104, NoSuchPaddingException -> 0x00ed, FileNotFoundException -> 0x00d6, IOException -> 0x00bf, all -> 0x00ba }
            r3.<init>(r4)     // Catch:{ InvalidKeyException -> 0x0132, InvalidAlgorithmParameterException -> 0x011b, NoSuchAlgorithmException -> 0x0104, NoSuchPaddingException -> 0x00ed, FileNotFoundException -> 0x00d6, IOException -> 0x00bf, all -> 0x00ba }
            r4 = 1
            r7.init(r4, r2, r3)     // Catch:{ InvalidKeyException -> 0x0132, InvalidAlgorithmParameterException -> 0x011b, NoSuchAlgorithmException -> 0x0104, NoSuchPaddingException -> 0x00ed, FileNotFoundException -> 0x00d6, IOException -> 0x00bf, all -> 0x00ba }
            r2 = 1024(0x400, float:1.435E-42)
            byte[] r2 = new byte[r2]     // Catch:{ InvalidKeyException -> 0x0132, InvalidAlgorithmParameterException -> 0x011b, NoSuchAlgorithmException -> 0x0104, NoSuchPaddingException -> 0x00ed, FileNotFoundException -> 0x00d6, IOException -> 0x00bf, all -> 0x00ba }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ InvalidKeyException -> 0x0132, InvalidAlgorithmParameterException -> 0x011b, NoSuchAlgorithmException -> 0x0104, NoSuchPaddingException -> 0x00ed, FileNotFoundException -> 0x00d6, IOException -> 0x00bf, all -> 0x00ba }
            r3.<init>(r5)     // Catch:{ InvalidKeyException -> 0x0132, InvalidAlgorithmParameterException -> 0x011b, NoSuchAlgorithmException -> 0x0104, NoSuchPaddingException -> 0x00ed, FileNotFoundException -> 0x00d6, IOException -> 0x00bf, all -> 0x00ba }
            java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch:{ InvalidKeyException -> 0x00b4, InvalidAlgorithmParameterException -> 0x00ae, NoSuchAlgorithmException -> 0x00a8, NoSuchPaddingException -> 0x00a3, FileNotFoundException -> 0x009e, IOException -> 0x0099, all -> 0x0093 }
            r5.<init>(r6)     // Catch:{ InvalidKeyException -> 0x00b4, InvalidAlgorithmParameterException -> 0x00ae, NoSuchAlgorithmException -> 0x00a8, NoSuchPaddingException -> 0x00a3, FileNotFoundException -> 0x009e, IOException -> 0x0099, all -> 0x0093 }
            javax.crypto.CipherInputStream r6 = new javax.crypto.CipherInputStream     // Catch:{ InvalidKeyException -> 0x0090, InvalidAlgorithmParameterException -> 0x008d, NoSuchAlgorithmException -> 0x008a, NoSuchPaddingException -> 0x0087, FileNotFoundException -> 0x0084, IOException -> 0x0081, all -> 0x007e }
            r6.<init>(r3, r7)     // Catch:{ InvalidKeyException -> 0x0090, InvalidAlgorithmParameterException -> 0x008d, NoSuchAlgorithmException -> 0x008a, NoSuchPaddingException -> 0x0087, FileNotFoundException -> 0x0084, IOException -> 0x0081, all -> 0x007e }
        L_0x0052:
            int r7 = r6.read(r2)     // Catch:{ InvalidKeyException -> 0x007b, InvalidAlgorithmParameterException -> 0x0078, NoSuchAlgorithmException -> 0x0076, NoSuchPaddingException -> 0x0074, FileNotFoundException -> 0x0072, IOException -> 0x0070, all -> 0x006e }
            r0 = -1
            if (r7 == r0) goto L_0x0060
            r5.write(r2, r1, r7)     // Catch:{ InvalidKeyException -> 0x007b, InvalidAlgorithmParameterException -> 0x0078, NoSuchAlgorithmException -> 0x0076, NoSuchPaddingException -> 0x0074, FileNotFoundException -> 0x0072, IOException -> 0x0070, all -> 0x006e }
            r5.flush()     // Catch:{ InvalidKeyException -> 0x007b, InvalidAlgorithmParameterException -> 0x0078, NoSuchAlgorithmException -> 0x0076, NoSuchPaddingException -> 0x0074, FileNotFoundException -> 0x0072, IOException -> 0x0070, all -> 0x006e }
            goto L_0x0052
        L_0x0060:
            r5.flush()     // Catch:{ InvalidKeyException -> 0x007b, InvalidAlgorithmParameterException -> 0x0078, NoSuchAlgorithmException -> 0x0076, NoSuchPaddingException -> 0x0074, FileNotFoundException -> 0x0072, IOException -> 0x0070, all -> 0x006e }
            r3.close()     // Catch:{ Exception -> 0x006d }
            r5.close()     // Catch:{ Exception -> 0x006d }
            r6.close()     // Catch:{ Exception -> 0x006d }
            return r4
        L_0x006d:
            return r1
        L_0x006e:
            r7 = move-exception
            goto L_0x0096
        L_0x0070:
            r7 = move-exception
            goto L_0x009c
        L_0x0072:
            r7 = move-exception
            goto L_0x00a1
        L_0x0074:
            r7 = move-exception
            goto L_0x00a6
        L_0x0076:
            r7 = move-exception
            goto L_0x00ab
        L_0x0078:
            r7 = move-exception
            goto L_0x00b1
        L_0x007b:
            r7 = move-exception
            goto L_0x00b7
        L_0x007e:
            r7 = move-exception
            r6 = r0
            goto L_0x0096
        L_0x0081:
            r7 = move-exception
            r6 = r0
            goto L_0x009c
        L_0x0084:
            r7 = move-exception
            r6 = r0
            goto L_0x00a1
        L_0x0087:
            r7 = move-exception
            r6 = r0
            goto L_0x00a6
        L_0x008a:
            r7 = move-exception
            r6 = r0
            goto L_0x00ab
        L_0x008d:
            r7 = move-exception
            r6 = r0
            goto L_0x00b1
        L_0x0090:
            r7 = move-exception
            r6 = r0
            goto L_0x00b7
        L_0x0093:
            r7 = move-exception
            r5 = r0
            r6 = r5
        L_0x0096:
            r0 = r3
            goto L_0x014a
        L_0x0099:
            r7 = move-exception
            r5 = r0
            r6 = r5
        L_0x009c:
            r0 = r3
            goto L_0x00c2
        L_0x009e:
            r7 = move-exception
            r5 = r0
            r6 = r5
        L_0x00a1:
            r0 = r3
            goto L_0x00d9
        L_0x00a3:
            r7 = move-exception
            r5 = r0
            r6 = r5
        L_0x00a6:
            r0 = r3
            goto L_0x00f0
        L_0x00a8:
            r7 = move-exception
            r5 = r0
            r6 = r5
        L_0x00ab:
            r0 = r3
            goto L_0x0107
        L_0x00ae:
            r7 = move-exception
            r5 = r0
            r6 = r5
        L_0x00b1:
            r0 = r3
            goto L_0x011e
        L_0x00b4:
            r7 = move-exception
            r5 = r0
            r6 = r5
        L_0x00b7:
            r0 = r3
            goto L_0x0135
        L_0x00ba:
            r7 = move-exception
            r5 = r0
            r6 = r5
            goto L_0x014a
        L_0x00bf:
            r7 = move-exception
            r5 = r0
            r6 = r5
        L_0x00c2:
            r7.printStackTrace()     // Catch:{ all -> 0x0149 }
            if (r0 == 0) goto L_0x00ca
            r0.close()     // Catch:{ Exception -> 0x00d5 }
        L_0x00ca:
            if (r5 == 0) goto L_0x00cf
            r5.close()     // Catch:{ Exception -> 0x00d5 }
        L_0x00cf:
            if (r6 == 0) goto L_0x00d5
            r6.close()     // Catch:{ Exception -> 0x00d5 }
        L_0x00d5:
            return r1
        L_0x00d6:
            r7 = move-exception
            r5 = r0
            r6 = r5
        L_0x00d9:
            r7.printStackTrace()     // Catch:{ all -> 0x0149 }
            if (r0 == 0) goto L_0x00e1
            r0.close()     // Catch:{ Exception -> 0x00ec }
        L_0x00e1:
            if (r5 == 0) goto L_0x00e6
            r5.close()     // Catch:{ Exception -> 0x00ec }
        L_0x00e6:
            if (r6 == 0) goto L_0x00ec
            r6.close()     // Catch:{ Exception -> 0x00ec }
        L_0x00ec:
            return r1
        L_0x00ed:
            r7 = move-exception
            r5 = r0
            r6 = r5
        L_0x00f0:
            r7.printStackTrace()     // Catch:{ all -> 0x0149 }
            if (r0 == 0) goto L_0x00f8
            r0.close()     // Catch:{ Exception -> 0x0103 }
        L_0x00f8:
            if (r5 == 0) goto L_0x00fd
            r5.close()     // Catch:{ Exception -> 0x0103 }
        L_0x00fd:
            if (r6 == 0) goto L_0x0103
            r6.close()     // Catch:{ Exception -> 0x0103 }
        L_0x0103:
            return r1
        L_0x0104:
            r7 = move-exception
            r5 = r0
            r6 = r5
        L_0x0107:
            r7.printStackTrace()     // Catch:{ all -> 0x0149 }
            if (r0 == 0) goto L_0x010f
            r0.close()     // Catch:{ Exception -> 0x011a }
        L_0x010f:
            if (r5 == 0) goto L_0x0114
            r5.close()     // Catch:{ Exception -> 0x011a }
        L_0x0114:
            if (r6 == 0) goto L_0x011a
            r6.close()     // Catch:{ Exception -> 0x011a }
        L_0x011a:
            return r1
        L_0x011b:
            r7 = move-exception
            r5 = r0
            r6 = r5
        L_0x011e:
            r7.printStackTrace()     // Catch:{ all -> 0x0149 }
            if (r0 == 0) goto L_0x0126
            r0.close()     // Catch:{ Exception -> 0x0131 }
        L_0x0126:
            if (r5 == 0) goto L_0x012b
            r5.close()     // Catch:{ Exception -> 0x0131 }
        L_0x012b:
            if (r6 == 0) goto L_0x0131
            r6.close()     // Catch:{ Exception -> 0x0131 }
        L_0x0131:
            return r1
        L_0x0132:
            r7 = move-exception
            r5 = r0
            r6 = r5
        L_0x0135:
            r7.printStackTrace()     // Catch:{ all -> 0x0149 }
            if (r0 == 0) goto L_0x013d
            r0.close()     // Catch:{ Exception -> 0x0148 }
        L_0x013d:
            if (r5 == 0) goto L_0x0142
            r5.close()     // Catch:{ Exception -> 0x0148 }
        L_0x0142:
            if (r6 == 0) goto L_0x0148
            r6.close()     // Catch:{ Exception -> 0x0148 }
        L_0x0148:
            return r1
        L_0x0149:
            r7 = move-exception
        L_0x014a:
            if (r0 == 0) goto L_0x014f
            r0.close()     // Catch:{ Exception -> 0x015a }
        L_0x014f:
            if (r5 == 0) goto L_0x0154
            r5.close()     // Catch:{ Exception -> 0x015a }
        L_0x0154:
            if (r6 == 0) goto L_0x015b
            r6.close()     // Catch:{ Exception -> 0x015a }
            goto L_0x015b
        L_0x015a:
            return r1
        L_0x015b:
            throw r7
        L_0x015c:
            return r1
        L_0x015d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mibi.common.data.Coder.a(java.lang.String, java.lang.String, java.lang.String):boolean");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: javax.crypto.CipherOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: javax.crypto.CipherOutputStream} */
    /* JADX WARNING: type inference failed for: r0v4 */
    /* JADX WARNING: type inference failed for: r0v7, types: [java.io.FileOutputStream] */
    /* JADX WARNING: type inference failed for: r0v8 */
    /* JADX WARNING: type inference failed for: r0v9 */
    /* JADX WARNING: type inference failed for: r0v11 */
    /* JADX WARNING: type inference failed for: r0v12 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00c3 A[SYNTHETIC, Splitter:B:59:0x00c3] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00cb A[Catch:{ Exception -> 0x00c7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00d0 A[Catch:{ Exception -> 0x00c7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x00db A[SYNTHETIC, Splitter:B:71:0x00db] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x00e3 A[Catch:{ Exception -> 0x00df }] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x00e8 A[Catch:{ Exception -> 0x00df }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean b(java.lang.String r5, java.lang.String r6, java.lang.String r7) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r7)
            r1 = 0
            if (r0 != 0) goto L_0x00f2
            boolean r0 = android.text.TextUtils.isEmpty(r5)
            if (r0 != 0) goto L_0x00f2
            boolean r0 = android.text.TextUtils.isEmpty(r6)
            if (r0 == 0) goto L_0x0015
            goto L_0x00f2
        L_0x0015:
            byte[] r7 = h(r7)
            if (r7 == 0) goto L_0x00f1
            int r0 = r7.length
            r2 = 16
            if (r0 == r2) goto L_0x0022
            goto L_0x00f1
        L_0x0022:
            r0 = 0
            java.io.File r2 = new java.io.File     // Catch:{ Exception -> 0x00bb, all -> 0x00b7 }
            r2.<init>(r5)     // Catch:{ Exception -> 0x00bb, all -> 0x00b7 }
            java.io.File r5 = new java.io.File     // Catch:{ Exception -> 0x00bb, all -> 0x00b7 }
            r5.<init>(r6)     // Catch:{ Exception -> 0x00bb, all -> 0x00b7 }
            boolean r6 = r2.exists()     // Catch:{ Exception -> 0x00bb, all -> 0x00b7 }
            if (r6 == 0) goto L_0x009c
            boolean r6 = r2.isFile()     // Catch:{ Exception -> 0x00bb, all -> 0x00b7 }
            if (r6 == 0) goto L_0x009c
            java.io.File r6 = r5.getParentFile()     // Catch:{ Exception -> 0x00bb, all -> 0x00b7 }
            boolean r6 = r6.exists()     // Catch:{ Exception -> 0x00bb, all -> 0x00b7 }
            if (r6 != 0) goto L_0x004a
            java.io.File r6 = r5.getParentFile()     // Catch:{ Exception -> 0x00bb, all -> 0x00b7 }
            r6.mkdirs()     // Catch:{ Exception -> 0x00bb, all -> 0x00b7 }
        L_0x004a:
            r5.createNewFile()     // Catch:{ Exception -> 0x00bb, all -> 0x00b7 }
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch:{ Exception -> 0x00bb, all -> 0x00b7 }
            r6.<init>(r2)     // Catch:{ Exception -> 0x00bb, all -> 0x00b7 }
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x0099, all -> 0x0096 }
            r2.<init>(r5)     // Catch:{ Exception -> 0x0099, all -> 0x0096 }
            javax.crypto.spec.SecretKeySpec r5 = new javax.crypto.spec.SecretKeySpec     // Catch:{ Exception -> 0x0094 }
            java.lang.String r3 = "AES"
            r5.<init>(r7, r3)     // Catch:{ Exception -> 0x0094 }
            javax.crypto.spec.IvParameterSpec r7 = new javax.crypto.spec.IvParameterSpec     // Catch:{ Exception -> 0x0094 }
            java.lang.String r3 = "0102030405060708"
            byte[] r3 = r3.getBytes()     // Catch:{ Exception -> 0x0094 }
            r7.<init>(r3)     // Catch:{ Exception -> 0x0094 }
            java.lang.String r3 = "AES/CBC/PKCS5Padding"
            javax.crypto.Cipher r3 = javax.crypto.Cipher.getInstance(r3)     // Catch:{ Exception -> 0x0094 }
            r4 = 2
            r3.init(r4, r5, r7)     // Catch:{ Exception -> 0x0094 }
            javax.crypto.CipherOutputStream r5 = new javax.crypto.CipherOutputStream     // Catch:{ Exception -> 0x0094 }
            r5.<init>(r2, r3)     // Catch:{ Exception -> 0x0094 }
            r7 = 1024(0x400, float:1.435E-42)
            byte[] r7 = new byte[r7]     // Catch:{ Exception -> 0x0090, all -> 0x008c }
        L_0x007c:
            int r0 = r6.read(r7)     // Catch:{ Exception -> 0x0090, all -> 0x008c }
            r3 = -1
            if (r0 == r3) goto L_0x008a
            r5.write(r7, r1, r0)     // Catch:{ Exception -> 0x0090, all -> 0x008c }
            r5.flush()     // Catch:{ Exception -> 0x0090, all -> 0x008c }
            goto L_0x007c
        L_0x008a:
            r0 = r2
            goto L_0x009e
        L_0x008c:
            r7 = move-exception
            r0 = r5
            r5 = r7
            goto L_0x00d9
        L_0x0090:
            r7 = move-exception
            r0 = r5
            r5 = r7
            goto L_0x00be
        L_0x0094:
            r5 = move-exception
            goto L_0x00be
        L_0x0096:
            r5 = move-exception
            r2 = r0
            goto L_0x00d9
        L_0x0099:
            r5 = move-exception
            r2 = r0
            goto L_0x00be
        L_0x009c:
            r5 = r0
            r6 = r5
        L_0x009e:
            if (r5 == 0) goto L_0x00a6
            r5.close()     // Catch:{ Exception -> 0x00a4 }
            goto L_0x00a6
        L_0x00a4:
            r5 = move-exception
            goto L_0x00b1
        L_0x00a6:
            if (r0 == 0) goto L_0x00ab
            r0.close()     // Catch:{ Exception -> 0x00a4 }
        L_0x00ab:
            if (r6 == 0) goto L_0x00b5
            r6.close()     // Catch:{ Exception -> 0x00a4 }
            goto L_0x00b5
        L_0x00b1:
            r5.printStackTrace()
            return r1
        L_0x00b5:
            r5 = 1
            return r5
        L_0x00b7:
            r5 = move-exception
            r6 = r0
            r2 = r6
            goto L_0x00d9
        L_0x00bb:
            r5 = move-exception
            r6 = r0
            r2 = r6
        L_0x00be:
            r5.printStackTrace()     // Catch:{ all -> 0x00d8 }
            if (r0 == 0) goto L_0x00c9
            r0.close()     // Catch:{ Exception -> 0x00c7 }
            goto L_0x00c9
        L_0x00c7:
            r5 = move-exception
            goto L_0x00d4
        L_0x00c9:
            if (r2 == 0) goto L_0x00ce
            r2.close()     // Catch:{ Exception -> 0x00c7 }
        L_0x00ce:
            if (r6 == 0) goto L_0x00d7
            r6.close()     // Catch:{ Exception -> 0x00c7 }
            goto L_0x00d7
        L_0x00d4:
            r5.printStackTrace()
        L_0x00d7:
            return r1
        L_0x00d8:
            r5 = move-exception
        L_0x00d9:
            if (r0 == 0) goto L_0x00e1
            r0.close()     // Catch:{ Exception -> 0x00df }
            goto L_0x00e1
        L_0x00df:
            r5 = move-exception
            goto L_0x00ec
        L_0x00e1:
            if (r2 == 0) goto L_0x00e6
            r2.close()     // Catch:{ Exception -> 0x00df }
        L_0x00e6:
            if (r6 == 0) goto L_0x00f0
            r6.close()     // Catch:{ Exception -> 0x00df }
            goto L_0x00f0
        L_0x00ec:
            r5.printStackTrace()
            return r1
        L_0x00f0:
            throw r5
        L_0x00f1:
            return r1
        L_0x00f2:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mibi.common.data.Coder.b(java.lang.String, java.lang.String, java.lang.String):boolean");
    }
}
