package com.xiaomi.youpin.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

public final class FileIOUtils {

    /* renamed from: a  reason: collision with root package name */
    private static int f23251a = 8192;

    private FileIOUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static boolean a(String str, InputStream inputStream) {
        return a(f(str), inputStream, false);
    }

    public static boolean a(String str, InputStream inputStream, boolean z) {
        return a(f(str), inputStream, z);
    }

    public static boolean a(File file, InputStream inputStream) {
        return a(file, inputStream, false);
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x004e A[SYNTHETIC, Splitter:B:34:0x004e] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0061 A[SYNTHETIC, Splitter:B:44:0x0061] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(java.io.File r4, java.io.InputStream r5, boolean r6) {
        /*
            boolean r0 = f((java.io.File) r4)
            r1 = 0
            if (r0 == 0) goto L_0x006a
            if (r5 != 0) goto L_0x000a
            goto L_0x006a
        L_0x000a:
            r0 = 0
            java.io.BufferedOutputStream r2 = new java.io.BufferedOutputStream     // Catch:{ IOException -> 0x0040 }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0040 }
            r3.<init>(r4, r6)     // Catch:{ IOException -> 0x0040 }
            r2.<init>(r3)     // Catch:{ IOException -> 0x0040 }
            int r4 = f23251a     // Catch:{ IOException -> 0x003b, all -> 0x0038 }
            byte[] r4 = new byte[r4]     // Catch:{ IOException -> 0x003b, all -> 0x0038 }
        L_0x0019:
            int r6 = f23251a     // Catch:{ IOException -> 0x003b, all -> 0x0038 }
            int r6 = r5.read(r4, r1, r6)     // Catch:{ IOException -> 0x003b, all -> 0x0038 }
            r0 = -1
            if (r6 == r0) goto L_0x0026
            r2.write(r4, r1, r6)     // Catch:{ IOException -> 0x003b, all -> 0x0038 }
            goto L_0x0019
        L_0x0026:
            r4 = 1
            r5.close()     // Catch:{ IOException -> 0x002b }
            goto L_0x002f
        L_0x002b:
            r5 = move-exception
            r5.printStackTrace()
        L_0x002f:
            r2.close()     // Catch:{ IOException -> 0x0033 }
            goto L_0x0037
        L_0x0033:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0037:
            return r4
        L_0x0038:
            r4 = move-exception
            r0 = r2
            goto L_0x0057
        L_0x003b:
            r4 = move-exception
            r0 = r2
            goto L_0x0041
        L_0x003e:
            r4 = move-exception
            goto L_0x0057
        L_0x0040:
            r4 = move-exception
        L_0x0041:
            r4.printStackTrace()     // Catch:{ all -> 0x003e }
            r5.close()     // Catch:{ IOException -> 0x0048 }
            goto L_0x004c
        L_0x0048:
            r4 = move-exception
            r4.printStackTrace()
        L_0x004c:
            if (r0 == 0) goto L_0x0056
            r0.close()     // Catch:{ IOException -> 0x0052 }
            goto L_0x0056
        L_0x0052:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0056:
            return r1
        L_0x0057:
            r5.close()     // Catch:{ IOException -> 0x005b }
            goto L_0x005f
        L_0x005b:
            r5 = move-exception
            r5.printStackTrace()
        L_0x005f:
            if (r0 == 0) goto L_0x0069
            r0.close()     // Catch:{ IOException -> 0x0065 }
            goto L_0x0069
        L_0x0065:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0069:
            throw r4
        L_0x006a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.common.util.FileIOUtils.a(java.io.File, java.io.InputStream, boolean):boolean");
    }

    public static boolean a(String str, byte[] bArr) {
        return a(f(str), bArr, false);
    }

    public static boolean a(String str, byte[] bArr, boolean z) {
        return a(f(str), bArr, z);
    }

    public static boolean a(File file, byte[] bArr) {
        return a(file, bArr, false);
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0030 A[SYNTHETIC, Splitter:B:24:0x0030] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x003b A[SYNTHETIC, Splitter:B:30:0x003b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(java.io.File r4, byte[] r5, boolean r6) {
        /*
            r0 = 0
            if (r5 == 0) goto L_0x0044
            boolean r1 = f((java.io.File) r4)
            if (r1 != 0) goto L_0x000a
            goto L_0x0044
        L_0x000a:
            r1 = 0
            java.io.BufferedOutputStream r2 = new java.io.BufferedOutputStream     // Catch:{ IOException -> 0x002a }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x002a }
            r3.<init>(r4, r6)     // Catch:{ IOException -> 0x002a }
            r2.<init>(r3)     // Catch:{ IOException -> 0x002a }
            r2.write(r5)     // Catch:{ IOException -> 0x0025, all -> 0x0022 }
            r4 = 1
            r2.close()     // Catch:{ IOException -> 0x001d }
            goto L_0x0021
        L_0x001d:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0021:
            return r4
        L_0x0022:
            r4 = move-exception
            r1 = r2
            goto L_0x0039
        L_0x0025:
            r4 = move-exception
            r1 = r2
            goto L_0x002b
        L_0x0028:
            r4 = move-exception
            goto L_0x0039
        L_0x002a:
            r4 = move-exception
        L_0x002b:
            r4.printStackTrace()     // Catch:{ all -> 0x0028 }
            if (r1 == 0) goto L_0x0038
            r1.close()     // Catch:{ IOException -> 0x0034 }
            goto L_0x0038
        L_0x0034:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0038:
            return r0
        L_0x0039:
            if (r1 == 0) goto L_0x0043
            r1.close()     // Catch:{ IOException -> 0x003f }
            goto L_0x0043
        L_0x003f:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0043:
            throw r4
        L_0x0044:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.common.util.FileIOUtils.a(java.io.File, byte[], boolean):boolean");
    }

    public static boolean b(String str, byte[] bArr, boolean z) {
        return a(f(str), bArr, false, z);
    }

    public static boolean a(String str, byte[] bArr, boolean z, boolean z2) {
        return a(f(str), bArr, z, z2);
    }

    public static boolean b(File file, byte[] bArr, boolean z) {
        return a(file, bArr, false, z);
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x003b A[SYNTHETIC, Splitter:B:25:0x003b] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0046 A[SYNTHETIC, Splitter:B:31:0x0046] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(java.io.File r3, byte[] r4, boolean r5, boolean r6) {
        /*
            r0 = 0
            if (r4 != 0) goto L_0x0004
            return r0
        L_0x0004:
            r1 = 0
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0035 }
            r2.<init>(r3, r5)     // Catch:{ IOException -> 0x0035 }
            java.nio.channels.FileChannel r3 = r2.getChannel()     // Catch:{ IOException -> 0x0035 }
            long r1 = r3.size()     // Catch:{ IOException -> 0x002f, all -> 0x002d }
            r3.position(r1)     // Catch:{ IOException -> 0x002f, all -> 0x002d }
            java.nio.ByteBuffer r4 = java.nio.ByteBuffer.wrap(r4)     // Catch:{ IOException -> 0x002f, all -> 0x002d }
            r3.write(r4)     // Catch:{ IOException -> 0x002f, all -> 0x002d }
            r4 = 1
            if (r6 == 0) goto L_0x0022
            r3.force(r4)     // Catch:{ IOException -> 0x002f, all -> 0x002d }
        L_0x0022:
            if (r3 == 0) goto L_0x002c
            r3.close()     // Catch:{ IOException -> 0x0028 }
            goto L_0x002c
        L_0x0028:
            r3 = move-exception
            r3.printStackTrace()
        L_0x002c:
            return r4
        L_0x002d:
            r4 = move-exception
            goto L_0x0044
        L_0x002f:
            r4 = move-exception
            r1 = r3
            goto L_0x0036
        L_0x0032:
            r4 = move-exception
            r3 = r1
            goto L_0x0044
        L_0x0035:
            r4 = move-exception
        L_0x0036:
            r4.printStackTrace()     // Catch:{ all -> 0x0032 }
            if (r1 == 0) goto L_0x0043
            r1.close()     // Catch:{ IOException -> 0x003f }
            goto L_0x0043
        L_0x003f:
            r3 = move-exception
            r3.printStackTrace()
        L_0x0043:
            return r0
        L_0x0044:
            if (r3 == 0) goto L_0x004e
            r3.close()     // Catch:{ IOException -> 0x004a }
            goto L_0x004e
        L_0x004a:
            r3 = move-exception
            r3.printStackTrace()
        L_0x004e:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.common.util.FileIOUtils.a(java.io.File, byte[], boolean, boolean):boolean");
    }

    public static boolean c(String str, byte[] bArr, boolean z) {
        return b(str, bArr, false, z);
    }

    public static boolean b(String str, byte[] bArr, boolean z, boolean z2) {
        return b(f(str), bArr, z, z2);
    }

    public static boolean c(File file, byte[] bArr, boolean z) {
        return b(file, bArr, false, z);
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0043 A[SYNTHETIC, Splitter:B:27:0x0043] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x004e A[SYNTHETIC, Splitter:B:33:0x004e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean b(java.io.File r9, byte[] r10, boolean r11, boolean r12) {
        /*
            r0 = 0
            if (r10 == 0) goto L_0x0057
            boolean r1 = f((java.io.File) r9)
            if (r1 != 0) goto L_0x000a
            goto L_0x0057
        L_0x000a:
            r1 = 0
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x003d }
            r2.<init>(r9, r11)     // Catch:{ IOException -> 0x003d }
            java.nio.channels.FileChannel r9 = r2.getChannel()     // Catch:{ IOException -> 0x003d }
            java.nio.channels.FileChannel$MapMode r4 = java.nio.channels.FileChannel.MapMode.READ_WRITE     // Catch:{ IOException -> 0x0037, all -> 0x0035 }
            long r5 = r9.size()     // Catch:{ IOException -> 0x0037, all -> 0x0035 }
            int r11 = r10.length     // Catch:{ IOException -> 0x0037, all -> 0x0035 }
            long r7 = (long) r11     // Catch:{ IOException -> 0x0037, all -> 0x0035 }
            r3 = r9
            java.nio.MappedByteBuffer r11 = r3.map(r4, r5, r7)     // Catch:{ IOException -> 0x0037, all -> 0x0035 }
            r11.put(r10)     // Catch:{ IOException -> 0x0037, all -> 0x0035 }
            if (r12 == 0) goto L_0x0029
            r11.force()     // Catch:{ IOException -> 0x0037, all -> 0x0035 }
        L_0x0029:
            r10 = 1
            if (r9 == 0) goto L_0x0034
            r9.close()     // Catch:{ IOException -> 0x0030 }
            goto L_0x0034
        L_0x0030:
            r9 = move-exception
            r9.printStackTrace()
        L_0x0034:
            return r10
        L_0x0035:
            r10 = move-exception
            goto L_0x004c
        L_0x0037:
            r10 = move-exception
            r1 = r9
            goto L_0x003e
        L_0x003a:
            r10 = move-exception
            r9 = r1
            goto L_0x004c
        L_0x003d:
            r10 = move-exception
        L_0x003e:
            r10.printStackTrace()     // Catch:{ all -> 0x003a }
            if (r1 == 0) goto L_0x004b
            r1.close()     // Catch:{ IOException -> 0x0047 }
            goto L_0x004b
        L_0x0047:
            r9 = move-exception
            r9.printStackTrace()
        L_0x004b:
            return r0
        L_0x004c:
            if (r9 == 0) goto L_0x0056
            r9.close()     // Catch:{ IOException -> 0x0052 }
            goto L_0x0056
        L_0x0052:
            r9 = move-exception
            r9.printStackTrace()
        L_0x0056:
            throw r10
        L_0x0057:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.common.util.FileIOUtils.b(java.io.File, byte[], boolean, boolean):boolean");
    }

    public static boolean a(String str, String str2) {
        return a(f(str), str2, false);
    }

    public static boolean a(String str, String str2, boolean z) {
        return a(f(str), str2, z);
    }

    public static boolean a(File file, String str) {
        return a(file, str, false);
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0033 A[SYNTHETIC, Splitter:B:26:0x0033] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x003e A[SYNTHETIC, Splitter:B:32:0x003e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(java.io.File r4, java.lang.String r5, boolean r6) {
        /*
            r0 = 0
            if (r4 == 0) goto L_0x0047
            if (r5 != 0) goto L_0x0006
            goto L_0x0047
        L_0x0006:
            boolean r1 = f((java.io.File) r4)
            if (r1 != 0) goto L_0x000d
            return r0
        L_0x000d:
            r1 = 0
            java.io.BufferedWriter r2 = new java.io.BufferedWriter     // Catch:{ IOException -> 0x002d }
            java.io.FileWriter r3 = new java.io.FileWriter     // Catch:{ IOException -> 0x002d }
            r3.<init>(r4, r6)     // Catch:{ IOException -> 0x002d }
            r2.<init>(r3)     // Catch:{ IOException -> 0x002d }
            r2.write(r5)     // Catch:{ IOException -> 0x0028, all -> 0x0025 }
            r4 = 1
            r2.close()     // Catch:{ IOException -> 0x0020 }
            goto L_0x0024
        L_0x0020:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0024:
            return r4
        L_0x0025:
            r4 = move-exception
            r1 = r2
            goto L_0x003c
        L_0x0028:
            r4 = move-exception
            r1 = r2
            goto L_0x002e
        L_0x002b:
            r4 = move-exception
            goto L_0x003c
        L_0x002d:
            r4 = move-exception
        L_0x002e:
            r4.printStackTrace()     // Catch:{ all -> 0x002b }
            if (r1 == 0) goto L_0x003b
            r1.close()     // Catch:{ IOException -> 0x0037 }
            goto L_0x003b
        L_0x0037:
            r4 = move-exception
            r4.printStackTrace()
        L_0x003b:
            return r0
        L_0x003c:
            if (r1 == 0) goto L_0x0046
            r1.close()     // Catch:{ IOException -> 0x0042 }
            goto L_0x0046
        L_0x0042:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0046:
            throw r4
        L_0x0047:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.common.util.FileIOUtils.a(java.io.File, java.lang.String, boolean):boolean");
    }

    public static List<String> a(String str) {
        return b(f(str), (String) null);
    }

    public static List<String> b(String str, String str2) {
        return b(f(str), str2);
    }

    public static List<String> a(File file) {
        return a(file, 0, Integer.MAX_VALUE, (String) null);
    }

    public static List<String> b(File file, String str) {
        return a(file, 0, Integer.MAX_VALUE, str);
    }

    public static List<String> a(String str, int i, int i2) {
        return a(f(str), i, i2, (String) null);
    }

    public static List<String> a(String str, int i, int i2, String str2) {
        return a(f(str), i, i2, str2);
    }

    public static List<String> a(File file, int i, int i2) {
        return a(file, i, i2, (String) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0060 A[SYNTHETIC, Splitter:B:33:0x0060] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x006d A[SYNTHETIC, Splitter:B:41:0x006d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.List<java.lang.String> a(java.io.File r6, int r7, int r8, java.lang.String r9) {
        /*
            boolean r0 = h((java.io.File) r6)
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            if (r7 <= r8) goto L_0x000b
            return r1
        L_0x000b:
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ IOException -> 0x0059, all -> 0x0057 }
            r0.<init>()     // Catch:{ IOException -> 0x0059, all -> 0x0057 }
            boolean r2 = h((java.lang.String) r9)     // Catch:{ IOException -> 0x0059, all -> 0x0057 }
            r3 = 1
            if (r2 == 0) goto L_0x0027
            java.io.BufferedReader r9 = new java.io.BufferedReader     // Catch:{ IOException -> 0x0059, all -> 0x0057 }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x0059, all -> 0x0057 }
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0059, all -> 0x0057 }
            r4.<init>(r6)     // Catch:{ IOException -> 0x0059, all -> 0x0057 }
            r2.<init>(r4)     // Catch:{ IOException -> 0x0059, all -> 0x0057 }
            r9.<init>(r2)     // Catch:{ IOException -> 0x0059, all -> 0x0057 }
            goto L_0x0037
        L_0x0027:
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ IOException -> 0x0059, all -> 0x0057 }
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x0059, all -> 0x0057 }
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0059, all -> 0x0057 }
            r5.<init>(r6)     // Catch:{ IOException -> 0x0059, all -> 0x0057 }
            r4.<init>(r5, r9)     // Catch:{ IOException -> 0x0059, all -> 0x0057 }
            r2.<init>(r4)     // Catch:{ IOException -> 0x0059, all -> 0x0057 }
            r9 = r2
        L_0x0037:
            java.lang.String r6 = r9.readLine()     // Catch:{ IOException -> 0x0055 }
            if (r6 == 0) goto L_0x004a
            if (r3 <= r8) goto L_0x0040
            goto L_0x004a
        L_0x0040:
            if (r7 > r3) goto L_0x0047
            if (r3 > r8) goto L_0x0047
            r0.add(r6)     // Catch:{ IOException -> 0x0055 }
        L_0x0047:
            int r3 = r3 + 1
            goto L_0x0037
        L_0x004a:
            if (r9 == 0) goto L_0x0054
            r9.close()     // Catch:{ IOException -> 0x0050 }
            goto L_0x0054
        L_0x0050:
            r6 = move-exception
            r6.printStackTrace()
        L_0x0054:
            return r0
        L_0x0055:
            r6 = move-exception
            goto L_0x005b
        L_0x0057:
            r6 = move-exception
            goto L_0x006b
        L_0x0059:
            r6 = move-exception
            r9 = r1
        L_0x005b:
            r6.printStackTrace()     // Catch:{ all -> 0x0069 }
            if (r9 == 0) goto L_0x0068
            r9.close()     // Catch:{ IOException -> 0x0064 }
            goto L_0x0068
        L_0x0064:
            r6 = move-exception
            r6.printStackTrace()
        L_0x0068:
            return r1
        L_0x0069:
            r6 = move-exception
            r1 = r9
        L_0x006b:
            if (r1 == 0) goto L_0x0075
            r1.close()     // Catch:{ IOException -> 0x0071 }
            goto L_0x0075
        L_0x0071:
            r7 = move-exception
            r7.printStackTrace()
        L_0x0075:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.common.util.FileIOUtils.a(java.io.File, int, int, java.lang.String):java.util.List");
    }

    public static String b(String str) {
        return c(f(str), (String) null);
    }

    public static String c(String str, String str2) {
        return c(f(str), str2);
    }

    public static String b(File file) {
        return c(file, (String) null);
    }

    public static String c(File file, String str) {
        byte[] c = c(file);
        if (c == null) {
            return null;
        }
        if (h(str)) {
            return new String(c);
        }
        try {
            return new String(c, str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static byte[] c(String str) {
        return c(f(str));
    }

    public static byte[] c(File file) {
        if (!h(file)) {
            return null;
        }
        try {
            return a((InputStream) new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] d(String str) {
        return d(f(str));
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x003d A[SYNTHETIC, Splitter:B:24:0x003d] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0049 A[SYNTHETIC, Splitter:B:31:0x0049] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] d(java.io.File r4) {
        /*
            boolean r0 = h((java.io.File) r4)
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            java.io.RandomAccessFile r0 = new java.io.RandomAccessFile     // Catch:{ IOException -> 0x0036, all -> 0x0033 }
            java.lang.String r2 = "r"
            r0.<init>(r4, r2)     // Catch:{ IOException -> 0x0036, all -> 0x0033 }
            java.nio.channels.FileChannel r4 = r0.getChannel()     // Catch:{ IOException -> 0x0036, all -> 0x0033 }
            long r2 = r4.size()     // Catch:{ IOException -> 0x0031 }
            int r0 = (int) r2     // Catch:{ IOException -> 0x0031 }
            java.nio.ByteBuffer r0 = java.nio.ByteBuffer.allocate(r0)     // Catch:{ IOException -> 0x0031 }
        L_0x001c:
            int r2 = r4.read(r0)     // Catch:{ IOException -> 0x0031 }
            if (r2 > 0) goto L_0x001c
            byte[] r0 = r0.array()     // Catch:{ IOException -> 0x0031 }
            if (r4 == 0) goto L_0x0030
            r4.close()     // Catch:{ IOException -> 0x002c }
            goto L_0x0030
        L_0x002c:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0030:
            return r0
        L_0x0031:
            r0 = move-exception
            goto L_0x0038
        L_0x0033:
            r0 = move-exception
            r4 = r1
            goto L_0x0047
        L_0x0036:
            r0 = move-exception
            r4 = r1
        L_0x0038:
            r0.printStackTrace()     // Catch:{ all -> 0x0046 }
            if (r4 == 0) goto L_0x0045
            r4.close()     // Catch:{ IOException -> 0x0041 }
            goto L_0x0045
        L_0x0041:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0045:
            return r1
        L_0x0046:
            r0 = move-exception
        L_0x0047:
            if (r4 == 0) goto L_0x0051
            r4.close()     // Catch:{ IOException -> 0x004d }
            goto L_0x0051
        L_0x004d:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0051:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.common.util.FileIOUtils.d(java.io.File):byte[]");
    }

    public static byte[] e(String str) {
        return e(f(str));
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0043 A[SYNTHETIC, Splitter:B:21:0x0043] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x004f A[SYNTHETIC, Splitter:B:28:0x004f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] e(java.io.File r9) {
        /*
            boolean r0 = h((java.io.File) r9)
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            java.io.RandomAccessFile r0 = new java.io.RandomAccessFile     // Catch:{ IOException -> 0x003c, all -> 0x0039 }
            java.lang.String r2 = "r"
            r0.<init>(r9, r2)     // Catch:{ IOException -> 0x003c, all -> 0x0039 }
            java.nio.channels.FileChannel r9 = r0.getChannel()     // Catch:{ IOException -> 0x003c, all -> 0x0039 }
            long r2 = r9.size()     // Catch:{ IOException -> 0x0037 }
            int r0 = (int) r2     // Catch:{ IOException -> 0x0037 }
            java.nio.channels.FileChannel$MapMode r4 = java.nio.channels.FileChannel.MapMode.READ_ONLY     // Catch:{ IOException -> 0x0037 }
            r5 = 0
            long r7 = (long) r0     // Catch:{ IOException -> 0x0037 }
            r3 = r9
            java.nio.MappedByteBuffer r2 = r3.map(r4, r5, r7)     // Catch:{ IOException -> 0x0037 }
            java.nio.MappedByteBuffer r2 = r2.load()     // Catch:{ IOException -> 0x0037 }
            byte[] r3 = new byte[r0]     // Catch:{ IOException -> 0x0037 }
            r4 = 0
            r2.get(r3, r4, r0)     // Catch:{ IOException -> 0x0037 }
            if (r9 == 0) goto L_0x0036
            r9.close()     // Catch:{ IOException -> 0x0032 }
            goto L_0x0036
        L_0x0032:
            r9 = move-exception
            r9.printStackTrace()
        L_0x0036:
            return r3
        L_0x0037:
            r0 = move-exception
            goto L_0x003e
        L_0x0039:
            r0 = move-exception
            r9 = r1
            goto L_0x004d
        L_0x003c:
            r0 = move-exception
            r9 = r1
        L_0x003e:
            r0.printStackTrace()     // Catch:{ all -> 0x004c }
            if (r9 == 0) goto L_0x004b
            r9.close()     // Catch:{ IOException -> 0x0047 }
            goto L_0x004b
        L_0x0047:
            r9 = move-exception
            r9.printStackTrace()
        L_0x004b:
            return r1
        L_0x004c:
            r0 = move-exception
        L_0x004d:
            if (r9 == 0) goto L_0x0057
            r9.close()     // Catch:{ IOException -> 0x0053 }
            goto L_0x0057
        L_0x0053:
            r9 = move-exception
            r9.printStackTrace()
        L_0x0057:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.common.util.FileIOUtils.e(java.io.File):byte[]");
    }

    public static void a(int i) {
        f23251a = i;
    }

    private static File f(String str) {
        if (h(str)) {
            return null;
        }
        return new File(str);
    }

    private static boolean g(String str) {
        return f(f(str));
    }

    private static boolean f(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            return file.isFile();
        }
        if (!g(file.getParentFile())) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean g(File file) {
        return file != null && (!file.exists() ? file.mkdirs() : file.isDirectory());
    }

    private static boolean h(File file) {
        return file != null && file.exists();
    }

    private static boolean h(String str) {
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

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0046 A[SYNTHETIC, Splitter:B:32:0x0046] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x005a A[SYNTHETIC, Splitter:B:43:0x005a] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] a(java.io.InputStream r7) {
        /*
            r0 = 0
            if (r7 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x0037, all -> 0x0032 }
            r1.<init>()     // Catch:{ IOException -> 0x0037, all -> 0x0032 }
            int r2 = f23251a     // Catch:{ IOException -> 0x0030 }
            byte[] r2 = new byte[r2]     // Catch:{ IOException -> 0x0030 }
        L_0x000d:
            int r3 = f23251a     // Catch:{ IOException -> 0x0030 }
            r4 = 0
            int r3 = r7.read(r2, r4, r3)     // Catch:{ IOException -> 0x0030 }
            r5 = -1
            if (r3 == r5) goto L_0x001b
            r1.write(r2, r4, r3)     // Catch:{ IOException -> 0x0030 }
            goto L_0x000d
        L_0x001b:
            byte[] r2 = r1.toByteArray()     // Catch:{ IOException -> 0x0030 }
            r7.close()     // Catch:{ IOException -> 0x0023 }
            goto L_0x0027
        L_0x0023:
            r7 = move-exception
            r7.printStackTrace()
        L_0x0027:
            r1.close()     // Catch:{ IOException -> 0x002b }
            goto L_0x002f
        L_0x002b:
            r7 = move-exception
            r7.printStackTrace()
        L_0x002f:
            return r2
        L_0x0030:
            r2 = move-exception
            goto L_0x0039
        L_0x0032:
            r1 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
            goto L_0x0050
        L_0x0037:
            r2 = move-exception
            r1 = r0
        L_0x0039:
            r2.printStackTrace()     // Catch:{ all -> 0x004f }
            r7.close()     // Catch:{ IOException -> 0x0040 }
            goto L_0x0044
        L_0x0040:
            r7 = move-exception
            r7.printStackTrace()
        L_0x0044:
            if (r1 == 0) goto L_0x004e
            r1.close()     // Catch:{ IOException -> 0x004a }
            goto L_0x004e
        L_0x004a:
            r7 = move-exception
            r7.printStackTrace()
        L_0x004e:
            return r0
        L_0x004f:
            r0 = move-exception
        L_0x0050:
            r7.close()     // Catch:{ IOException -> 0x0054 }
            goto L_0x0058
        L_0x0054:
            r7 = move-exception
            r7.printStackTrace()
        L_0x0058:
            if (r1 == 0) goto L_0x0062
            r1.close()     // Catch:{ IOException -> 0x005e }
            goto L_0x0062
        L_0x005e:
            r7 = move-exception
            r7.printStackTrace()
        L_0x0062:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.common.util.FileIOUtils.a(java.io.InputStream):byte[]");
    }
}
