package com.xiaomi.jr.common.utils;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.os.StatFs;
import android.util.Base64;
import com.google.android.exoplayer2.C;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.Set;

public class FileUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1414a = "FileUtils";

    public static boolean a(String str, String str2) {
        return a(new File(str), new File(str2));
    }

    private static boolean a(File file, File file2) {
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(file);
            boolean a2 = a(file2, (InputStream) fileInputStream);
            fileInputStream.close();
            return a2;
        } catch (IOException unused) {
            return false;
        } catch (Throwable th) {
            fileInputStream.close();
            throw th;
        }
    }

    public static byte[] a(String str) {
        byte[] bArr = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            byte[] a2 = a((InputStream) fileInputStream);
            try {
                fileInputStream.close();
                return a2;
            } catch (IOException e) {
                IOException iOException = e;
                bArr = a2;
                e = iOException;
            }
        } catch (IOException e2) {
            e = e2;
            e.printStackTrace();
            return bArr;
        }
    }

    public static byte[] a(InputStream inputStream) {
        ByteArrayOutputStream b = b(inputStream);
        if (b != null) {
            return b.toByteArray();
        }
        return null;
    }

    private static ByteArrayOutputStream b(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[4096];
        while (true) {
            try {
                int read = inputStream.read(bArr);
                if (read > 0) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    try {
                        break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e2) {
                e2.printStackTrace();
                try {
                    byteArrayOutputStream.close();
                    return null;
                } catch (IOException e3) {
                    e3.printStackTrace();
                    return null;
                }
            } catch (Throwable th) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
                throw th;
            }
        }
        byteArrayOutputStream.close();
        return byteArrayOutputStream;
    }

    public static boolean b(String str) {
        return b(new File(str));
    }

    private static boolean b(File file) {
        if (file.exists() && !file.delete()) {
            return false;
        }
        if (!file.getParentFile().exists()) {
            return file.getParentFile().mkdirs();
        }
        return true;
    }

    public static void a(File file) {
        if (file.isFile()) {
            file.delete();
        } else if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles == null || listFiles.length == 0) {
                file.delete();
                return;
            }
            for (File a2 : listFiles) {
                a(a2);
            }
            file.delete();
        }
    }

    public static void a(File file, Set set) {
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles == null || listFiles.length == 0) {
                file.delete();
                return;
            }
            for (File file2 : listFiles) {
                if (set != null) {
                    try {
                        if (set.contains(file2.getCanonicalPath())) {
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                a(file2);
            }
            file.delete();
        }
    }

    public static String a(Context context, String str) {
        try {
            return a(context) + File.separator + str;
        } catch (NullPointerException unused) {
            return null;
        }
    }

    public static String a(Context context) {
        File filesDir = context.getFilesDir();
        if (filesDir == null) {
            return null;
        }
        try {
            return filesDir.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean b(String str, String str2) {
        return a(str, str2, false);
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0031 A[SYNTHETIC, Splitter:B:24:0x0031] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x003b A[SYNTHETIC, Splitter:B:30:0x003b] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0041 A[SYNTHETIC, Splitter:B:34:0x0041] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:27:0x0036=Splitter:B:27:0x0036, B:21:0x002c=Splitter:B:21:0x002c} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean a(java.lang.String r4, java.lang.String r5, boolean r6) {
        /*
            boolean r0 = b((java.lang.String) r4)
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            r0 = 0
            java.io.BufferedWriter r2 = new java.io.BufferedWriter     // Catch:{ FileNotFoundException -> 0x0035, IOException -> 0x002b }
            java.io.FileWriter r3 = new java.io.FileWriter     // Catch:{ FileNotFoundException -> 0x0035, IOException -> 0x002b }
            r3.<init>(r4, r6)     // Catch:{ FileNotFoundException -> 0x0035, IOException -> 0x002b }
            r2.<init>(r3)     // Catch:{ FileNotFoundException -> 0x0035, IOException -> 0x002b }
            r2.write(r5)     // Catch:{ FileNotFoundException -> 0x0026, IOException -> 0x0023, all -> 0x0020 }
            r1 = 1
            r2.close()     // Catch:{ IOException -> 0x001b }
            goto L_0x003e
        L_0x001b:
            r4 = move-exception
            r4.printStackTrace()
            goto L_0x003e
        L_0x0020:
            r4 = move-exception
            r0 = r2
            goto L_0x003f
        L_0x0023:
            r4 = move-exception
            r0 = r2
            goto L_0x002c
        L_0x0026:
            r4 = move-exception
            r0 = r2
            goto L_0x0036
        L_0x0029:
            r4 = move-exception
            goto L_0x003f
        L_0x002b:
            r4 = move-exception
        L_0x002c:
            r4.printStackTrace()     // Catch:{ all -> 0x0029 }
            if (r0 == 0) goto L_0x003e
            r0.close()     // Catch:{ IOException -> 0x001b }
            goto L_0x003e
        L_0x0035:
            r4 = move-exception
        L_0x0036:
            r4.printStackTrace()     // Catch:{ all -> 0x0029 }
            if (r0 == 0) goto L_0x003e
            r0.close()     // Catch:{ IOException -> 0x001b }
        L_0x003e:
            return r1
        L_0x003f:
            if (r0 == 0) goto L_0x0049
            r0.close()     // Catch:{ IOException -> 0x0045 }
            goto L_0x0049
        L_0x0045:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0049:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.jr.common.utils.FileUtils.a(java.lang.String, java.lang.String, boolean):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x002c A[SYNTHETIC, Splitter:B:25:0x002c] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0036 A[SYNTHETIC, Splitter:B:31:0x0036] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0041 A[SYNTHETIC, Splitter:B:37:0x0041] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:28:0x0031=Splitter:B:28:0x0031, B:22:0x0027=Splitter:B:22:0x0027} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(java.lang.String r3, byte[] r4) {
        /*
            boolean r0 = b((java.lang.String) r3)
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            r0 = 0
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x0030, IOException -> 0x0026 }
            r2.<init>(r3)     // Catch:{ FileNotFoundException -> 0x0030, IOException -> 0x0026 }
            r2.write(r4)     // Catch:{ FileNotFoundException -> 0x0021, IOException -> 0x001e, all -> 0x001b }
            r3 = 1
            r2.close()     // Catch:{ IOException -> 0x0016 }
            goto L_0x001a
        L_0x0016:
            r4 = move-exception
            r4.printStackTrace()
        L_0x001a:
            return r3
        L_0x001b:
            r3 = move-exception
            r0 = r2
            goto L_0x003f
        L_0x001e:
            r3 = move-exception
            r0 = r2
            goto L_0x0027
        L_0x0021:
            r3 = move-exception
            r0 = r2
            goto L_0x0031
        L_0x0024:
            r3 = move-exception
            goto L_0x003f
        L_0x0026:
            r3 = move-exception
        L_0x0027:
            r3.printStackTrace()     // Catch:{ all -> 0x0024 }
            if (r0 == 0) goto L_0x003e
            r0.close()     // Catch:{ IOException -> 0x003a }
            goto L_0x003e
        L_0x0030:
            r3 = move-exception
        L_0x0031:
            r3.printStackTrace()     // Catch:{ all -> 0x0024 }
            if (r0 == 0) goto L_0x003e
            r0.close()     // Catch:{ IOException -> 0x003a }
            goto L_0x003e
        L_0x003a:
            r3 = move-exception
            r3.printStackTrace()
        L_0x003e:
            return r1
        L_0x003f:
            if (r0 == 0) goto L_0x0049
            r0.close()     // Catch:{ IOException -> 0x0045 }
            goto L_0x0049
        L_0x0045:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0049:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.jr.common.utils.FileUtils.a(java.lang.String, byte[]):boolean");
    }

    public static boolean a(File file, InputStream inputStream) {
        if (!b(file)) {
            return false;
        }
        FileOutputStream fileOutputStream = null;
        try {
            FileOutputStream fileOutputStream2 = new FileOutputStream(file);
            try {
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read > 0) {
                        fileOutputStream2.write(bArr, 0, read);
                    } else {
                        Utils.a((Closeable) fileOutputStream2);
                        Utils.a((Closeable) inputStream);
                        return true;
                    }
                }
            } catch (IOException e) {
                e = e;
                fileOutputStream = fileOutputStream2;
                try {
                    e.printStackTrace();
                    Utils.a((Closeable) fileOutputStream);
                    Utils.a((Closeable) inputStream);
                    return false;
                } catch (Throwable th) {
                    th = th;
                    Utils.a((Closeable) fileOutputStream);
                    Utils.a((Closeable) inputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = fileOutputStream2;
                Utils.a((Closeable) fileOutputStream);
                Utils.a((Closeable) inputStream);
                throw th;
            }
        } catch (IOException e2) {
            e = e2;
            e.printStackTrace();
            Utils.a((Closeable) fileOutputStream);
            Utils.a((Closeable) inputStream);
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x001e A[SYNTHETIC, Splitter:B:17:0x001e] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x002b A[SYNTHETIC, Splitter:B:25:0x002b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String c(java.lang.String r2) {
        /*
            r0 = 0
            java.io.FileReader r1 = new java.io.FileReader     // Catch:{ FileNotFoundException -> 0x0017, all -> 0x0015 }
            r1.<init>(r2)     // Catch:{ FileNotFoundException -> 0x0017, all -> 0x0015 }
            java.lang.String r2 = a((java.io.Reader) r1)     // Catch:{ FileNotFoundException -> 0x0013 }
            r1.close()     // Catch:{ IOException -> 0x000e }
            goto L_0x0012
        L_0x000e:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0012:
            return r2
        L_0x0013:
            r2 = move-exception
            goto L_0x0019
        L_0x0015:
            r2 = move-exception
            goto L_0x0029
        L_0x0017:
            r2 = move-exception
            r1 = r0
        L_0x0019:
            r2.printStackTrace()     // Catch:{ all -> 0x0027 }
            if (r1 == 0) goto L_0x0026
            r1.close()     // Catch:{ IOException -> 0x0022 }
            goto L_0x0026
        L_0x0022:
            r2 = move-exception
            r2.printStackTrace()
        L_0x0026:
            return r0
        L_0x0027:
            r2 = move-exception
            r0 = r1
        L_0x0029:
            if (r0 == 0) goto L_0x0033
            r0.close()     // Catch:{ IOException -> 0x002f }
            goto L_0x0033
        L_0x002f:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0033:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.jr.common.utils.FileUtils.c(java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0026 A[SYNTHETIC, Splitter:B:17:0x0026] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0033 A[SYNTHETIC, Splitter:B:25:0x0033] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String b(android.content.Context r1, java.lang.String r2) {
        /*
            r0 = 0
            android.content.res.AssetManager r1 = r1.getAssets()     // Catch:{ IOException -> 0x001f, all -> 0x001d }
            java.io.InputStream r1 = r1.open(r2)     // Catch:{ IOException -> 0x001f, all -> 0x001d }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x001f, all -> 0x001d }
            r2.<init>(r1)     // Catch:{ IOException -> 0x001f, all -> 0x001d }
            java.lang.String r1 = a((java.io.Reader) r2)     // Catch:{ IOException -> 0x001b }
            r2.close()     // Catch:{ IOException -> 0x0016 }
            goto L_0x001a
        L_0x0016:
            r2 = move-exception
            r2.printStackTrace()
        L_0x001a:
            return r1
        L_0x001b:
            r1 = move-exception
            goto L_0x0021
        L_0x001d:
            r1 = move-exception
            goto L_0x0031
        L_0x001f:
            r1 = move-exception
            r2 = r0
        L_0x0021:
            r1.printStackTrace()     // Catch:{ all -> 0x002f }
            if (r2 == 0) goto L_0x002e
            r2.close()     // Catch:{ IOException -> 0x002a }
            goto L_0x002e
        L_0x002a:
            r1 = move-exception
            r1.printStackTrace()
        L_0x002e:
            return r0
        L_0x002f:
            r1 = move-exception
            r0 = r2
        L_0x0031:
            if (r0 == 0) goto L_0x003b
            r0.close()     // Catch:{ IOException -> 0x0037 }
            goto L_0x003b
        L_0x0037:
            r2 = move-exception
            r2.printStackTrace()
        L_0x003b:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.jr.common.utils.FileUtils.b(android.content.Context, java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x002c A[SYNTHETIC, Splitter:B:20:0x002c] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0039 A[SYNTHETIC, Splitter:B:28:0x0039] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0044  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x004c A[SYNTHETIC, Splitter:B:37:0x004c] */
    /* JADX WARNING: Removed duplicated region for block: B:43:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:25:0x0034=Splitter:B:25:0x0034, B:17:0x0027=Splitter:B:17:0x0027} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String a(java.io.Reader r3) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r1 = 0
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ FileNotFoundException -> 0x0032, IOException -> 0x0025, all -> 0x0022 }
            r2.<init>(r3)     // Catch:{ FileNotFoundException -> 0x0032, IOException -> 0x0025, all -> 0x0022 }
        L_0x000b:
            java.lang.String r3 = r2.readLine()     // Catch:{ FileNotFoundException -> 0x0020, IOException -> 0x001e }
            if (r3 == 0) goto L_0x0015
            r0.append(r3)     // Catch:{ FileNotFoundException -> 0x0020, IOException -> 0x001e }
            goto L_0x000b
        L_0x0015:
            r2.close()     // Catch:{ IOException -> 0x0019 }
            goto L_0x0042
        L_0x0019:
            r3 = move-exception
            r3.printStackTrace()
            goto L_0x0042
        L_0x001e:
            r3 = move-exception
            goto L_0x0027
        L_0x0020:
            r3 = move-exception
            goto L_0x0034
        L_0x0022:
            r3 = move-exception
            r2 = r1
            goto L_0x004a
        L_0x0025:
            r3 = move-exception
            r2 = r1
        L_0x0027:
            r3.printStackTrace()     // Catch:{ all -> 0x0049 }
            if (r2 == 0) goto L_0x0041
            r2.close()     // Catch:{ IOException -> 0x0030 }
            goto L_0x0041
        L_0x0030:
            r3 = move-exception
            goto L_0x003e
        L_0x0032:
            r3 = move-exception
            r2 = r1
        L_0x0034:
            r3.printStackTrace()     // Catch:{ all -> 0x0049 }
            if (r2 == 0) goto L_0x0041
            r2.close()     // Catch:{ IOException -> 0x003d }
            goto L_0x0041
        L_0x003d:
            r3 = move-exception
        L_0x003e:
            r3.printStackTrace()
        L_0x0041:
            r0 = r1
        L_0x0042:
            if (r0 == 0) goto L_0x0048
            java.lang.String r1 = r0.toString()
        L_0x0048:
            return r1
        L_0x0049:
            r3 = move-exception
        L_0x004a:
            if (r2 == 0) goto L_0x0054
            r2.close()     // Catch:{ IOException -> 0x0050 }
            goto L_0x0054
        L_0x0050:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0054:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.jr.common.utils.FileUtils.a(java.io.Reader):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0038 A[SYNTHETIC, Splitter:B:19:0x0038] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0043 A[SYNTHETIC, Splitter:B:26:0x0043] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x004f A[SYNTHETIC, Splitter:B:33:0x004f] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:16:0x0033=Splitter:B:16:0x0033, B:23:0x003e=Splitter:B:23:0x003e} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.io.ByteArrayInputStream d(java.lang.String r4) {
        /*
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch:{ FileNotFoundException -> 0x003c, IOException -> 0x0031, all -> 0x002c }
            r1.<init>(r4)     // Catch:{ FileNotFoundException -> 0x003c, IOException -> 0x0031, all -> 0x002c }
            java.io.BufferedInputStream r4 = new java.io.BufferedInputStream     // Catch:{ FileNotFoundException -> 0x003c, IOException -> 0x0031, all -> 0x002c }
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x003c, IOException -> 0x0031, all -> 0x002c }
            r2.<init>(r1)     // Catch:{ FileNotFoundException -> 0x003c, IOException -> 0x0031, all -> 0x002c }
            r4.<init>(r2)     // Catch:{ FileNotFoundException -> 0x003c, IOException -> 0x0031, all -> 0x002c }
            long r1 = r1.length()     // Catch:{ FileNotFoundException -> 0x002a, IOException -> 0x0028 }
            int r1 = (int) r1     // Catch:{ FileNotFoundException -> 0x002a, IOException -> 0x0028 }
            byte[] r1 = new byte[r1]     // Catch:{ FileNotFoundException -> 0x002a, IOException -> 0x0028 }
            r4.read(r1)     // Catch:{ FileNotFoundException -> 0x002a, IOException -> 0x0028 }
            java.io.ByteArrayInputStream r2 = new java.io.ByteArrayInputStream     // Catch:{ FileNotFoundException -> 0x002a, IOException -> 0x0028 }
            r2.<init>(r1)     // Catch:{ FileNotFoundException -> 0x002a, IOException -> 0x0028 }
            r4.close()     // Catch:{ IOException -> 0x0023 }
            goto L_0x0027
        L_0x0023:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0027:
            return r2
        L_0x0028:
            r1 = move-exception
            goto L_0x0033
        L_0x002a:
            r1 = move-exception
            goto L_0x003e
        L_0x002c:
            r4 = move-exception
            r3 = r0
            r0 = r4
            r4 = r3
            goto L_0x004d
        L_0x0031:
            r1 = move-exception
            r4 = r0
        L_0x0033:
            r1.printStackTrace()     // Catch:{ all -> 0x004c }
            if (r4 == 0) goto L_0x004b
            r4.close()     // Catch:{ IOException -> 0x0047 }
            goto L_0x004b
        L_0x003c:
            r1 = move-exception
            r4 = r0
        L_0x003e:
            r1.printStackTrace()     // Catch:{ all -> 0x004c }
            if (r4 == 0) goto L_0x004b
            r4.close()     // Catch:{ IOException -> 0x0047 }
            goto L_0x004b
        L_0x0047:
            r4 = move-exception
            r4.printStackTrace()
        L_0x004b:
            return r0
        L_0x004c:
            r0 = move-exception
        L_0x004d:
            if (r4 == 0) goto L_0x0057
            r4.close()     // Catch:{ IOException -> 0x0053 }
            goto L_0x0057
        L_0x0053:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0057:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.jr.common.utils.FileUtils.d(java.lang.String):java.io.ByteArrayInputStream");
    }

    public static ParcelFileDescriptor e(String str) {
        File file = new File(str);
        if (!file.exists()) {
            return null;
        }
        try {
            return ParcelFileDescriptor.open(file, C.ENCODING_PCM_MU_LAW);
        } catch (FileNotFoundException unused) {
            MifiLog.e(f1414a, "Can NOT open file. filePath - " + str);
            return null;
        }
    }

    public static ParcelFileDescriptor a(Context context, Uri uri) {
        try {
            return context.getContentResolver().openFileDescriptor(uri, "r");
        } catch (Exception unused) {
            MifiLog.e(f1414a, "Can NOT open file. contentUri - " + uri.toString());
            return null;
        }
    }

    public static String f(String str) {
        FileInputStream fileInputStream;
        int i;
        File file = new File(str);
        FileInputStream fileInputStream2 = null;
        if (!file.isFile()) {
            return null;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            byte[] bArr = new byte[1024];
            fileInputStream = new FileInputStream(file);
            while (true) {
                try {
                    int read = fileInputStream.read(bArr, 0, 1024);
                    if (read == -1) {
                        break;
                    }
                    instance.update(bArr, 0, read);
                } catch (Exception e) {
                    e = e;
                    try {
                        e.printStackTrace();
                        Utils.a((Closeable) fileInputStream);
                        return null;
                    } catch (Throwable th) {
                        th = th;
                        fileInputStream2 = fileInputStream;
                        Utils.a((Closeable) fileInputStream2);
                        throw th;
                    }
                }
            }
            byte[] digest = instance.digest();
            Utils.a((Closeable) fileInputStream);
            if (digest == null) {
                return null;
            }
            StringBuilder sb = new StringBuilder(digest.length * 2);
            for (byte b : digest) {
                byte b2 = b & 255;
                if (b2 < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(b2));
            }
            return sb.toString();
        } catch (Exception e2) {
            e = e2;
            fileInputStream = null;
            e.printStackTrace();
            Utils.a((Closeable) fileInputStream);
            return null;
        } catch (Throwable th2) {
            th = th2;
            Utils.a((Closeable) fileInputStream2);
            throw th;
        }
    }

    public static String g(String str) {
        FileInputStream fileInputStream;
        File file = new File(str);
        if (!file.isFile()) {
            return null;
        }
        try {
            fileInputStream = new FileInputStream(file);
            try {
                byte[] bArr = new byte[fileInputStream.available()];
                String encodeToString = Base64.encodeToString(bArr, 0, fileInputStream.read(bArr), 0);
                Utils.a((Closeable) fileInputStream);
                return encodeToString;
            } catch (Exception e) {
                e = e;
                try {
                    e.printStackTrace();
                    Utils.a((Closeable) fileInputStream);
                    return null;
                } catch (Throwable th) {
                    th = th;
                    Utils.a((Closeable) fileInputStream);
                    throw th;
                }
            }
        } catch (Exception e2) {
            e = e2;
            fileInputStream = null;
            e.printStackTrace();
            Utils.a((Closeable) fileInputStream);
            return null;
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
            Utils.a((Closeable) fileInputStream);
            throw th;
        }
    }

    public static FileSystemInfo h(String str) {
        StatFs statFs = new StatFs(str);
        FileSystemInfo fileSystemInfo = new FileSystemInfo();
        if (Build.VERSION.SDK_INT >= 18) {
            fileSystemInfo.f1413a = statFs.getTotalBytes();
            fileSystemInfo.b = statFs.getFreeBytes();
        } else {
            fileSystemInfo.f1413a = ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
            fileSystemInfo.b = (long) (statFs.getFreeBlocks() * statFs.getBlockSize());
        }
        return fileSystemInfo;
    }
}
