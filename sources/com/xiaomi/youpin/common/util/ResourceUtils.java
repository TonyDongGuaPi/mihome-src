package com.xiaomi.youpin.common.util;

import android.support.annotation.RawRes;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

public final class ResourceUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final int f23267a = 8192;

    private ResourceUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static boolean a(String str, String str2) {
        try {
            String[] list = Utils.a().getAssets().list(str);
            if (list.length <= 0) {
                return a(str2, Utils.a().getAssets().open(str), false);
            }
            boolean z = true;
            for (String str3 : list) {
                z &= a(str + "/" + str3, str2 + "/" + str3);
            }
            return z;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String a(String str) {
        return b(str, (String) null);
    }

    public static String b(String str, String str2) {
        try {
            byte[] a2 = a(Utils.a().getAssets().open(str));
            if (a2 == null) {
                return null;
            }
            if (d(str2)) {
                return new String(a2);
            }
            try {
                return new String(a2, str2);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return "";
            }
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static List<String> b(String str) {
        return c(str, (String) null);
    }

    public static List<String> c(String str, String str2) {
        try {
            return a(Utils.a().getResources().getAssets().open(str), str2);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean a(@RawRes int i, String str) {
        return a(str, Utils.a().getResources().openRawResource(i), false);
    }

    public static String a(@RawRes int i) {
        return b(i, (String) null);
    }

    public static String b(@RawRes int i, String str) {
        byte[] a2 = a(Utils.a().getResources().openRawResource(i));
        if (a2 == null) {
            return null;
        }
        if (d(str)) {
            return new String(a2);
        }
        try {
            return new String(a2, str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static List<String> b(@RawRes int i) {
        return c(i, (String) null);
    }

    public static List<String> c(@RawRes int i, String str) {
        return a(Utils.a().getResources().openRawResource(i), str);
    }

    private static boolean a(String str, InputStream inputStream, boolean z) {
        return a(c(str), inputStream, z);
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x004c A[SYNTHETIC, Splitter:B:35:0x004c] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x005f A[SYNTHETIC, Splitter:B:45:0x005f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean a(java.io.File r4, java.io.InputStream r5, boolean r6) {
        /*
            boolean r0 = a((java.io.File) r4)
            r1 = 0
            if (r0 == 0) goto L_0x0068
            if (r5 != 0) goto L_0x000a
            goto L_0x0068
        L_0x000a:
            r0 = 0
            java.io.BufferedOutputStream r2 = new java.io.BufferedOutputStream     // Catch:{ IOException -> 0x003e }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x003e }
            r3.<init>(r4, r6)     // Catch:{ IOException -> 0x003e }
            r2.<init>(r3)     // Catch:{ IOException -> 0x003e }
            r4 = 8192(0x2000, float:1.14794E-41)
            byte[] r6 = new byte[r4]     // Catch:{ IOException -> 0x0039, all -> 0x0036 }
        L_0x0019:
            int r0 = r5.read(r6, r1, r4)     // Catch:{ IOException -> 0x0039, all -> 0x0036 }
            r3 = -1
            if (r0 == r3) goto L_0x0024
            r2.write(r6, r1, r0)     // Catch:{ IOException -> 0x0039, all -> 0x0036 }
            goto L_0x0019
        L_0x0024:
            r4 = 1
            r5.close()     // Catch:{ IOException -> 0x0029 }
            goto L_0x002d
        L_0x0029:
            r5 = move-exception
            r5.printStackTrace()
        L_0x002d:
            r2.close()     // Catch:{ IOException -> 0x0031 }
            goto L_0x0035
        L_0x0031:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0035:
            return r4
        L_0x0036:
            r4 = move-exception
            r0 = r2
            goto L_0x0055
        L_0x0039:
            r4 = move-exception
            r0 = r2
            goto L_0x003f
        L_0x003c:
            r4 = move-exception
            goto L_0x0055
        L_0x003e:
            r4 = move-exception
        L_0x003f:
            r4.printStackTrace()     // Catch:{ all -> 0x003c }
            r5.close()     // Catch:{ IOException -> 0x0046 }
            goto L_0x004a
        L_0x0046:
            r4 = move-exception
            r4.printStackTrace()
        L_0x004a:
            if (r0 == 0) goto L_0x0054
            r0.close()     // Catch:{ IOException -> 0x0050 }
            goto L_0x0054
        L_0x0050:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0054:
            return r1
        L_0x0055:
            r5.close()     // Catch:{ IOException -> 0x0059 }
            goto L_0x005d
        L_0x0059:
            r5 = move-exception
            r5.printStackTrace()
        L_0x005d:
            if (r0 == 0) goto L_0x0067
            r0.close()     // Catch:{ IOException -> 0x0063 }
            goto L_0x0067
        L_0x0063:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0067:
            throw r4
        L_0x0068:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.common.util.ResourceUtils.a(java.io.File, java.io.InputStream, boolean):boolean");
    }

    private static File c(String str) {
        if (d(str)) {
            return null;
        }
        return new File(str);
    }

    private static boolean a(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            return file.isFile();
        }
        if (!b(file.getParentFile())) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean b(File file) {
        return file != null && (!file.exists() ? file.mkdirs() : file.isDirectory());
    }

    private static boolean d(String str) {
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

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0044 A[SYNTHETIC, Splitter:B:33:0x0044] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0058 A[SYNTHETIC, Splitter:B:44:0x0058] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] a(java.io.InputStream r8) {
        /*
            r0 = 0
            if (r8 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x0035, all -> 0x0030 }
            r1.<init>()     // Catch:{ IOException -> 0x0035, all -> 0x0030 }
            r2 = 8192(0x2000, float:1.14794E-41)
            byte[] r3 = new byte[r2]     // Catch:{ IOException -> 0x002e }
        L_0x000d:
            r4 = 0
            int r5 = r8.read(r3, r4, r2)     // Catch:{ IOException -> 0x002e }
            r6 = -1
            if (r5 == r6) goto L_0x0019
            r1.write(r3, r4, r5)     // Catch:{ IOException -> 0x002e }
            goto L_0x000d
        L_0x0019:
            byte[] r2 = r1.toByteArray()     // Catch:{ IOException -> 0x002e }
            r8.close()     // Catch:{ IOException -> 0x0021 }
            goto L_0x0025
        L_0x0021:
            r8 = move-exception
            r8.printStackTrace()
        L_0x0025:
            r1.close()     // Catch:{ IOException -> 0x0029 }
            goto L_0x002d
        L_0x0029:
            r8 = move-exception
            r8.printStackTrace()
        L_0x002d:
            return r2
        L_0x002e:
            r2 = move-exception
            goto L_0x0037
        L_0x0030:
            r1 = move-exception
            r7 = r1
            r1 = r0
            r0 = r7
            goto L_0x004e
        L_0x0035:
            r2 = move-exception
            r1 = r0
        L_0x0037:
            r2.printStackTrace()     // Catch:{ all -> 0x004d }
            r8.close()     // Catch:{ IOException -> 0x003e }
            goto L_0x0042
        L_0x003e:
            r8 = move-exception
            r8.printStackTrace()
        L_0x0042:
            if (r1 == 0) goto L_0x004c
            r1.close()     // Catch:{ IOException -> 0x0048 }
            goto L_0x004c
        L_0x0048:
            r8 = move-exception
            r8.printStackTrace()
        L_0x004c:
            return r0
        L_0x004d:
            r0 = move-exception
        L_0x004e:
            r8.close()     // Catch:{ IOException -> 0x0052 }
            goto L_0x0056
        L_0x0052:
            r8 = move-exception
            r8.printStackTrace()
        L_0x0056:
            if (r1 == 0) goto L_0x0060
            r1.close()     // Catch:{ IOException -> 0x005c }
            goto L_0x0060
        L_0x005c:
            r8 = move-exception
            r8.printStackTrace()
        L_0x0060:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.common.util.ResourceUtils.a(java.io.InputStream):byte[]");
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0042 A[SYNTHETIC, Splitter:B:24:0x0042] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x004f A[SYNTHETIC, Splitter:B:32:0x004f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.List<java.lang.String> a(java.io.InputStream r4, java.lang.String r5) {
        /*
            r0 = 0
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ IOException -> 0x003b, all -> 0x0039 }
            r1.<init>()     // Catch:{ IOException -> 0x003b, all -> 0x0039 }
            boolean r2 = d(r5)     // Catch:{ IOException -> 0x003b, all -> 0x0039 }
            if (r2 == 0) goto L_0x0017
            java.io.BufferedReader r5 = new java.io.BufferedReader     // Catch:{ IOException -> 0x003b, all -> 0x0039 }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x003b, all -> 0x0039 }
            r2.<init>(r4)     // Catch:{ IOException -> 0x003b, all -> 0x0039 }
            r5.<init>(r2)     // Catch:{ IOException -> 0x003b, all -> 0x0039 }
            goto L_0x0022
        L_0x0017:
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ IOException -> 0x003b, all -> 0x0039 }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x003b, all -> 0x0039 }
            r3.<init>(r4, r5)     // Catch:{ IOException -> 0x003b, all -> 0x0039 }
            r2.<init>(r3)     // Catch:{ IOException -> 0x003b, all -> 0x0039 }
            r5 = r2
        L_0x0022:
            java.lang.String r4 = r5.readLine()     // Catch:{ IOException -> 0x0037 }
            if (r4 == 0) goto L_0x002c
            r1.add(r4)     // Catch:{ IOException -> 0x0037 }
            goto L_0x0022
        L_0x002c:
            if (r5 == 0) goto L_0x0036
            r5.close()     // Catch:{ IOException -> 0x0032 }
            goto L_0x0036
        L_0x0032:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0036:
            return r1
        L_0x0037:
            r4 = move-exception
            goto L_0x003d
        L_0x0039:
            r4 = move-exception
            goto L_0x004d
        L_0x003b:
            r4 = move-exception
            r5 = r0
        L_0x003d:
            r4.printStackTrace()     // Catch:{ all -> 0x004b }
            if (r5 == 0) goto L_0x004a
            r5.close()     // Catch:{ IOException -> 0x0046 }
            goto L_0x004a
        L_0x0046:
            r4 = move-exception
            r4.printStackTrace()
        L_0x004a:
            return r0
        L_0x004b:
            r4 = move-exception
            r0 = r5
        L_0x004d:
            if (r0 == 0) goto L_0x0057
            r0.close()     // Catch:{ IOException -> 0x0053 }
            goto L_0x0057
        L_0x0053:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0057:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.common.util.ResourceUtils.a(java.io.InputStream, java.lang.String):java.util.List");
    }
}
