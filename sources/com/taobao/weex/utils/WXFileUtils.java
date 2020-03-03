package com.taobao.weex.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXFileUtils {
    private static transient /* synthetic */ boolean[] $jacocoData;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(9051934571208743651L, "com/taobao/weex/utils/WXFileUtils", 160);
        $jacocoData = a2;
        return a2;
    }

    public WXFileUtils() {
        $jacocoInit()[0] = true;
    }

    public static String loadFileOrAsset(String str, Context context) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[1] = true;
        } else {
            $jacocoInit[2] = true;
            File file = new File(str);
            $jacocoInit[3] = true;
            if (file.exists()) {
                try {
                    $jacocoInit[4] = true;
                    FileInputStream fileInputStream = new FileInputStream(file);
                    $jacocoInit[5] = true;
                    String readStreamToString = readStreamToString(fileInputStream);
                    $jacocoInit[6] = true;
                    return readStreamToString;
                } catch (FileNotFoundException e) {
                    $jacocoInit[7] = true;
                    e.printStackTrace();
                    $jacocoInit[8] = true;
                }
            } else {
                String loadAsset = loadAsset(str, context);
                $jacocoInit[9] = true;
                return loadAsset;
            }
        }
        $jacocoInit[10] = true;
        return "";
    }

    public static String loadAsset(String str, Context context) {
        boolean[] $jacocoInit = $jacocoInit();
        if (context == null) {
            $jacocoInit[11] = true;
        } else if (TextUtils.isEmpty(str)) {
            $jacocoInit[12] = true;
        } else {
            try {
                $jacocoInit[14] = true;
                InputStream open = context.getAssets().open(str);
                $jacocoInit[15] = true;
                String readStreamToString = readStreamToString(open);
                $jacocoInit[16] = true;
                return readStreamToString;
            } catch (IOException e) {
                $jacocoInit[17] = true;
                e.printStackTrace();
                $jacocoInit[18] = true;
                return "";
            }
        }
        $jacocoInit[13] = true;
        return null;
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00b2 A[Catch:{  }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00d2 A[Catch:{ IOException -> 0x00e7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00d7 A[Catch:{ IOException -> 0x00e7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0102  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0107 A[Catch:{  }] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0127 A[Catch:{ IOException -> 0x013c }] */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x012c A[Catch:{ IOException -> 0x013c }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String readStreamToString(java.io.InputStream r7) {
        /*
            boolean[] r0 = $jacocoInit()
            r1 = 19
            r2 = 1
            r3 = 0
            r0[r1] = r2     // Catch:{ IOException -> 0x0096 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0096 }
            int r4 = r7.available()     // Catch:{ IOException -> 0x0096 }
            int r4 = r4 + 10
            r1.<init>(r4)     // Catch:{ IOException -> 0x0096 }
            r4 = 20
            r0[r4] = r2     // Catch:{ IOException -> 0x0096 }
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch:{ IOException -> 0x0096 }
            java.io.InputStreamReader r5 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x0096 }
            r5.<init>(r7)     // Catch:{ IOException -> 0x0096 }
            r4.<init>(r5)     // Catch:{ IOException -> 0x0096 }
            r3 = 4096(0x1000, float:5.74E-42)
            char[] r3 = new char[r3]     // Catch:{ IOException -> 0x0091, all -> 0x008e }
            r5 = 21
            r0[r5] = r2     // Catch:{ IOException -> 0x0091, all -> 0x008e }
        L_0x002b:
            int r5 = r4.read(r3)     // Catch:{ IOException -> 0x0091, all -> 0x008e }
            if (r5 <= 0) goto L_0x003e
            r6 = 22
            r0[r6] = r2     // Catch:{ IOException -> 0x0091, all -> 0x008e }
            r6 = 0
            r1.append(r3, r6, r5)     // Catch:{ IOException -> 0x0091, all -> 0x008e }
            r5 = 23
            r0[r5] = r2     // Catch:{ IOException -> 0x0091, all -> 0x008e }
            goto L_0x002b
        L_0x003e:
            java.lang.String r1 = r1.toString()     // Catch:{ IOException -> 0x0091, all -> 0x008e }
            r3 = 24
            r0[r3] = r2     // Catch:{ all -> 0x008e }
            r3 = 26
            r0[r3] = r2     // Catch:{ IOException -> 0x0056 }
            r4.close()     // Catch:{ IOException -> 0x0056 }
            r3 = 27
            r0[r3] = r2     // Catch:{ IOException -> 0x0056 }
            r3 = 28
            r0[r3] = r2
            goto L_0x0064
        L_0x0056:
            r3 = move-exception
            r4 = 29
            r0[r4] = r2
            java.lang.String r4 = "WXFileUtils loadAsset: "
            com.taobao.weex.utils.WXLogUtils.e((java.lang.String) r4, (java.lang.Throwable) r3)
            r3 = 30
            r0[r3] = r2     // Catch:{ IOException -> 0x007b }
        L_0x0064:
            if (r7 != 0) goto L_0x006b
            r7 = 31
            r0[r7] = r2     // Catch:{ IOException -> 0x007b }
            goto L_0x0076
        L_0x006b:
            r3 = 32
            r0[r3] = r2     // Catch:{ IOException -> 0x007b }
            r7.close()     // Catch:{ IOException -> 0x007b }
            r7 = 33
            r0[r7] = r2     // Catch:{ IOException -> 0x007b }
        L_0x0076:
            r7 = 34
            r0[r7] = r2
            goto L_0x0089
        L_0x007b:
            r7 = move-exception
            r3 = 35
            r0[r3] = r2
            java.lang.String r3 = "WXFileUtils loadAsset: "
            com.taobao.weex.utils.WXLogUtils.e((java.lang.String) r3, (java.lang.Throwable) r7)
            r7 = 36
            r0[r7] = r2
        L_0x0089:
            r7 = 37
            r0[r7] = r2
            return r1
        L_0x008e:
            r1 = move-exception
            r3 = r4
            goto L_0x00fc
        L_0x0091:
            r1 = move-exception
            r3 = r4
            goto L_0x0097
        L_0x0094:
            r1 = move-exception
            goto L_0x00fc
        L_0x0096:
            r1 = move-exception
        L_0x0097:
            r4 = 38
            r0[r4] = r2     // Catch:{ all -> 0x0094 }
            r1.printStackTrace()     // Catch:{ all -> 0x0094 }
            r4 = 39
            r0[r4] = r2     // Catch:{ all -> 0x0094 }
            java.lang.String r4 = ""
            com.taobao.weex.utils.WXLogUtils.e((java.lang.String) r4, (java.lang.Throwable) r1)     // Catch:{ all -> 0x0094 }
            r1 = 40
            r0[r1] = r2     // Catch:{ IOException -> 0x00c2 }
            if (r3 != 0) goto L_0x00b2
            r1 = 41
            r0[r1] = r2     // Catch:{  }
            goto L_0x00bd
        L_0x00b2:
            r1 = 42
            r0[r1] = r2     // Catch:{  }
            r3.close()     // Catch:{  }
            r1 = 43
            r0[r1] = r2     // Catch:{  }
        L_0x00bd:
            r1 = 44
            r0[r1] = r2
            goto L_0x00d0
        L_0x00c2:
            r1 = move-exception
            r3 = 45
            r0[r3] = r2
            java.lang.String r3 = "WXFileUtils loadAsset: "
            com.taobao.weex.utils.WXLogUtils.e((java.lang.String) r3, (java.lang.Throwable) r1)
            r1 = 46
            r0[r1] = r2     // Catch:{ IOException -> 0x00e7 }
        L_0x00d0:
            if (r7 != 0) goto L_0x00d7
            r7 = 47
            r0[r7] = r2     // Catch:{ IOException -> 0x00e7 }
            goto L_0x00e2
        L_0x00d7:
            r1 = 48
            r0[r1] = r2     // Catch:{ IOException -> 0x00e7 }
            r7.close()     // Catch:{ IOException -> 0x00e7 }
            r7 = 49
            r0[r7] = r2     // Catch:{ IOException -> 0x00e7 }
        L_0x00e2:
            r7 = 50
            r0[r7] = r2
            goto L_0x00f5
        L_0x00e7:
            r7 = move-exception
            r1 = 51
            r0[r1] = r2
            java.lang.String r1 = "WXFileUtils loadAsset: "
            com.taobao.weex.utils.WXLogUtils.e((java.lang.String) r1, (java.lang.Throwable) r7)
            r7 = 52
            r0[r7] = r2
        L_0x00f5:
            java.lang.String r7 = ""
            r1 = 67
            r0[r1] = r2
            return r7
        L_0x00fc:
            r4 = 53
            r0[r4] = r2     // Catch:{ IOException -> 0x0117 }
            if (r3 != 0) goto L_0x0107
            r3 = 54
            r0[r3] = r2     // Catch:{  }
            goto L_0x0112
        L_0x0107:
            r4 = 55
            r0[r4] = r2     // Catch:{  }
            r3.close()     // Catch:{  }
            r3 = 56
            r0[r3] = r2     // Catch:{  }
        L_0x0112:
            r3 = 57
            r0[r3] = r2
            goto L_0x0125
        L_0x0117:
            r3 = move-exception
            r4 = 58
            r0[r4] = r2
            java.lang.String r4 = "WXFileUtils loadAsset: "
            com.taobao.weex.utils.WXLogUtils.e((java.lang.String) r4, (java.lang.Throwable) r3)
            r3 = 59
            r0[r3] = r2     // Catch:{ IOException -> 0x013c }
        L_0x0125:
            if (r7 != 0) goto L_0x012c
            r7 = 60
            r0[r7] = r2     // Catch:{ IOException -> 0x013c }
            goto L_0x0137
        L_0x012c:
            r3 = 61
            r0[r3] = r2     // Catch:{ IOException -> 0x013c }
            r7.close()     // Catch:{ IOException -> 0x013c }
            r7 = 62
            r0[r7] = r2     // Catch:{ IOException -> 0x013c }
        L_0x0137:
            r7 = 63
            r0[r7] = r2
            goto L_0x014a
        L_0x013c:
            r7 = move-exception
            r3 = 64
            r0[r3] = r2
            java.lang.String r3 = "WXFileUtils loadAsset: "
            com.taobao.weex.utils.WXLogUtils.e((java.lang.String) r3, (java.lang.Throwable) r7)
            r7 = 65
            r0[r7] = r2
        L_0x014a:
            r7 = 66
            r0[r7] = r2
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.utils.WXFileUtils.readStreamToString(java.io.InputStream):java.lang.String");
    }

    public static byte[] readBytesFromAssets(String str, Context context) {
        boolean[] $jacocoInit = $jacocoInit();
        if (context == null) {
            $jacocoInit[68] = true;
        } else if (TextUtils.isEmpty(str)) {
            $jacocoInit[69] = true;
        } else {
            try {
                $jacocoInit[71] = true;
                InputStream open = context.getAssets().open(str);
                byte[] bArr = new byte[4096];
                $jacocoInit[72] = true;
                int read = open.read(bArr);
                byte[] bArr2 = new byte[read];
                $jacocoInit[73] = true;
                System.arraycopy(bArr, 0, bArr2, 0, read);
                $jacocoInit[74] = true;
                return bArr2;
            } catch (IOException e) {
                $jacocoInit[75] = true;
                e.printStackTrace();
                $jacocoInit[76] = true;
                return null;
            }
        }
        $jacocoInit[70] = true;
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0078  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x009c  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00a1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean saveFile(java.lang.String r4, byte[] r5, android.content.Context r6) {
        /*
            boolean[] r0 = $jacocoInit()
            boolean r1 = android.text.TextUtils.isEmpty(r4)
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L_0x0011
            r4 = 77
            r0[r4] = r3
            goto L_0x001e
        L_0x0011:
            if (r5 != 0) goto L_0x0018
            r4 = 78
            r0[r4] = r3
            goto L_0x001e
        L_0x0018:
            if (r6 != 0) goto L_0x0023
            r4 = 79
            r0[r4] = r3
        L_0x001e:
            r4 = 80
            r0[r4] = r3
            return r2
        L_0x0023:
            r6 = 0
            r1 = 81
            r0[r1] = r3     // Catch:{ Exception -> 0x0059 }
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x0059 }
            r1.<init>(r4)     // Catch:{ Exception -> 0x0059 }
            r4 = 82
            r0[r4] = r3     // Catch:{ Exception -> 0x0054, all -> 0x0051 }
            r1.write(r5)     // Catch:{ Exception -> 0x0054, all -> 0x0051 }
            r4 = 84
            r0[r4] = r3     // Catch:{ IOException -> 0x0040 }
            r1.close()     // Catch:{ IOException -> 0x0040 }
            r4 = 85
            r0[r4] = r3
            goto L_0x004c
        L_0x0040:
            r4 = move-exception
            r5 = 86
            r0[r5] = r3
            r4.printStackTrace()
            r4 = 87
            r0[r4] = r3
        L_0x004c:
            r4 = 88
            r0[r4] = r3
            return r3
        L_0x0051:
            r4 = move-exception
            r6 = r1
            goto L_0x009a
        L_0x0054:
            r4 = move-exception
            r6 = r1
            goto L_0x005a
        L_0x0057:
            r4 = move-exception
            goto L_0x009a
        L_0x0059:
            r4 = move-exception
        L_0x005a:
            r5 = 89
            r0[r5] = r3     // Catch:{ all -> 0x0057 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0057 }
            r5.<init>()     // Catch:{ all -> 0x0057 }
            java.lang.String r1 = "WXFileUtils saveFile: "
            r5.append(r1)     // Catch:{ all -> 0x0057 }
            java.lang.String r4 = com.taobao.weex.utils.WXLogUtils.getStackTrace(r4)     // Catch:{ all -> 0x0057 }
            r5.append(r4)     // Catch:{ all -> 0x0057 }
            java.lang.String r4 = r5.toString()     // Catch:{ all -> 0x0057 }
            com.taobao.weex.utils.WXLogUtils.e(r4)     // Catch:{ all -> 0x0057 }
            if (r6 != 0) goto L_0x007d
            r4 = 90
            r0[r4] = r3
            goto L_0x0095
        L_0x007d:
            r4 = 91
            r0[r4] = r3     // Catch:{ IOException -> 0x0089 }
            r6.close()     // Catch:{ IOException -> 0x0089 }
            r4 = 92
            r0[r4] = r3
            goto L_0x0095
        L_0x0089:
            r4 = move-exception
            r5 = 93
            r0[r5] = r3
            r4.printStackTrace()
            r4 = 94
            r0[r4] = r3
        L_0x0095:
            r4 = 101(0x65, float:1.42E-43)
            r0[r4] = r3
            return r2
        L_0x009a:
            if (r6 != 0) goto L_0x00a1
            r5 = 95
            r0[r5] = r3
            goto L_0x00b9
        L_0x00a1:
            r5 = 96
            r0[r5] = r3     // Catch:{ IOException -> 0x00ad }
            r6.close()     // Catch:{ IOException -> 0x00ad }
            r5 = 97
            r0[r5] = r3
            goto L_0x00b9
        L_0x00ad:
            r5 = move-exception
            r6 = 98
            r0[r6] = r3
            r5.printStackTrace()
            r5 = 99
            r0[r5] = r3
        L_0x00b9:
            r5 = 100
            r0[r5] = r3
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.utils.WXFileUtils.saveFile(java.lang.String, byte[], android.content.Context):boolean");
    }

    public static String md5(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (str != null) {
            try {
                $jacocoInit[102] = true;
                String md5 = md5(str.getBytes("UTF-8"));
                $jacocoInit[104] = true;
                return md5;
            } catch (UnsupportedEncodingException unused) {
                $jacocoInit[105] = true;
                return "";
            }
        } else {
            $jacocoInit[103] = true;
            return "";
        }
    }

    public static String md5(byte[] bArr) {
        boolean[] $jacocoInit = $jacocoInit();
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            $jacocoInit[106] = true;
            instance.update(bArr);
            $jacocoInit[107] = true;
            BigInteger bigInteger = new BigInteger(1, instance.digest());
            $jacocoInit[108] = true;
            String bigInteger2 = bigInteger.toString(16);
            $jacocoInit[109] = true;
            return bigInteger2;
        } catch (NoSuchAlgorithmException unused) {
            $jacocoInit[110] = true;
            return "";
        }
    }

    public static String base64Md5(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (str != null) {
            try {
                $jacocoInit[111] = true;
                String base64Md5 = base64Md5(str.getBytes("UTF-8"));
                $jacocoInit[113] = true;
                return base64Md5;
            } catch (UnsupportedEncodingException unused) {
                $jacocoInit[114] = true;
                return "";
            }
        } else {
            $jacocoInit[112] = true;
            return "";
        }
    }

    public static String base64Md5(byte[] bArr) {
        boolean[] $jacocoInit = $jacocoInit();
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            $jacocoInit[115] = true;
            instance.update(bArr);
            $jacocoInit[116] = true;
            String encodeToString = Base64.encodeToString(instance.digest(), 2);
            $jacocoInit[117] = true;
            return encodeToString;
        } catch (NoSuchAlgorithmException unused) {
            $jacocoInit[118] = true;
            return "";
        }
    }

    public static void extractSo(String str, String str2) throws IOException {
        boolean[] $jacocoInit = $jacocoInit();
        ZipFile zipFile = new ZipFile(str);
        $jacocoInit[119] = true;
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(str));
        $jacocoInit[120] = true;
        ZipInputStream zipInputStream = new ZipInputStream(bufferedInputStream);
        $jacocoInit[121] = true;
        while (true) {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            if (nextEntry != null) {
                $jacocoInit[122] = true;
                if (nextEntry.isDirectory()) {
                    $jacocoInit[123] = true;
                } else if (!nextEntry.getName().contains("lib/armeabi/")) {
                    $jacocoInit[124] = true;
                } else {
                    $jacocoInit[125] = true;
                    if (nextEntry.getName().contains("weex")) {
                        $jacocoInit[126] = true;
                    } else if (!nextEntry.getName().equals("libJavaScriptCore.so")) {
                        $jacocoInit[127] = true;
                    } else {
                        $jacocoInit[128] = true;
                    }
                    String[] split = nextEntry.getName().split("/");
                    String str3 = split[split.length - 1];
                    $jacocoInit[129] = true;
                    InputStream inputStream = zipFile.getInputStream(nextEntry);
                    byte[] bArr = new byte[1024];
                    $jacocoInit[130] = true;
                    File file = new File(str2 + "/" + str3);
                    $jacocoInit[131] = true;
                    if (!file.exists()) {
                        $jacocoInit[132] = true;
                    } else {
                        $jacocoInit[133] = true;
                        file.delete();
                        $jacocoInit[134] = true;
                    }
                    file.createNewFile();
                    $jacocoInit[135] = true;
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    $jacocoInit[136] = true;
                    while (inputStream.read(bArr) != -1) {
                        $jacocoInit[137] = true;
                        fileOutputStream.write(bArr);
                        $jacocoInit[138] = true;
                    }
                    fileOutputStream.close();
                    $jacocoInit[139] = true;
                }
            } else {
                zipInputStream.closeEntry();
                $jacocoInit[140] = true;
                return;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00a2  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00a7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void copyFile(java.io.File r7, java.io.File r8) {
        /*
            boolean[] r0 = $jacocoInit()
            r1 = 141(0x8d, float:1.98E-43)
            r2 = 0
            r3 = 1
            r0[r1] = r3     // Catch:{ Exception -> 0x0049 }
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0049 }
            r1.<init>(r7)     // Catch:{ Exception -> 0x0049 }
            r4 = 1024(0x400, float:1.435E-42)
            byte[] r4 = new byte[r4]     // Catch:{ Exception -> 0x0045 }
            r5 = 142(0x8e, float:1.99E-43)
            r0[r5] = r3     // Catch:{ Exception -> 0x0045 }
            java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x0045 }
            r5.<init>(r8)     // Catch:{ Exception -> 0x0045 }
            r2 = 143(0x8f, float:2.0E-43)
            r0[r2] = r3     // Catch:{ Exception -> 0x0043 }
        L_0x0020:
            int r2 = r1.read(r4)     // Catch:{ Exception -> 0x0043 }
            r6 = -1
            if (r2 == r6) goto L_0x0033
            r2 = 144(0x90, float:2.02E-43)
            r0[r2] = r3     // Catch:{ Exception -> 0x0043 }
            r5.write(r4)     // Catch:{ Exception -> 0x0043 }
            r2 = 145(0x91, float:2.03E-43)
            r0[r2] = r3     // Catch:{ Exception -> 0x0043 }
            goto L_0x0020
        L_0x0033:
            r1.close()     // Catch:{ Exception -> 0x0043 }
            r2 = 146(0x92, float:2.05E-43)
            r0[r2] = r3     // Catch:{ Exception -> 0x0043 }
            r5.close()     // Catch:{ Exception -> 0x0043 }
            r7 = 147(0x93, float:2.06E-43)
            r0[r7] = r3
            goto L_0x00bf
        L_0x0043:
            r2 = move-exception
            goto L_0x004d
        L_0x0045:
            r4 = move-exception
            r5 = r2
            r2 = r4
            goto L_0x004d
        L_0x0049:
            r1 = move-exception
            r5 = r2
            r2 = r1
            r1 = r5
        L_0x004d:
            r4 = 148(0x94, float:2.07E-43)
            r0[r4] = r3
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r6 = "copyFile "
            r4.append(r6)
            java.lang.String r2 = r2.getMessage()
            r4.append(r2)
            java.lang.String r2 = ": "
            r4.append(r2)
            java.lang.String r7 = r7.getAbsolutePath()
            r4.append(r7)
            java.lang.String r7 = ": "
            r4.append(r7)
            java.lang.String r7 = r8.getAbsolutePath()
            r4.append(r7)
            java.lang.String r7 = r4.toString()
            com.taobao.weex.utils.WXLogUtils.e(r7)
            if (r1 != 0) goto L_0x0088
            r7 = 149(0x95, float:2.09E-43)
            r0[r7] = r3
            goto L_0x00a0
        L_0x0088:
            r7 = 150(0x96, float:2.1E-43)
            r0[r7] = r3     // Catch:{ IOException -> 0x0094 }
            r1.close()     // Catch:{ IOException -> 0x0094 }
            r7 = 151(0x97, float:2.12E-43)
            r0[r7] = r3
            goto L_0x00a0
        L_0x0094:
            r7 = move-exception
            r8 = 152(0x98, float:2.13E-43)
            r0[r8] = r3
            r7.printStackTrace()
            r7 = 153(0x99, float:2.14E-43)
            r0[r7] = r3
        L_0x00a0:
            if (r5 != 0) goto L_0x00a7
            r7 = 154(0x9a, float:2.16E-43)
            r0[r7] = r3
            goto L_0x00bf
        L_0x00a7:
            r7 = 155(0x9b, float:2.17E-43)
            r0[r7] = r3     // Catch:{ IOException -> 0x00b3 }
            r5.close()     // Catch:{ IOException -> 0x00b3 }
            r7 = 156(0x9c, float:2.19E-43)
            r0[r7] = r3
            goto L_0x00bf
        L_0x00b3:
            r7 = move-exception
            r8 = 157(0x9d, float:2.2E-43)
            r0[r8] = r3
            r7.printStackTrace()
            r7 = 158(0x9e, float:2.21E-43)
            r0[r7] = r3
        L_0x00bf:
            r7 = 159(0x9f, float:2.23E-43)
            r0[r7] = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.weex.utils.WXFileUtils.copyFile(java.io.File, java.io.File):void");
    }
}
