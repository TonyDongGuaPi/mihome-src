package com.tencent.tinker.loader.shareutil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.util.Log;
import com.google.code.microlog4android.format.PatternFormatter;
import com.tencent.tinker.loader.TinkerRuntimeException;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipFile;

public class SharePatchFileUtil {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1360a = "Tinker.PatchFileUtil";
    private static char[] b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', PatternFormatter.CATEGORY_CONVERSION_CHAR, PatternFormatter.DATE_CONVERSION_CHAR, 'e', 'f'};

    public static File a(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        if (applicationInfo == null) {
            return null;
        }
        return new File(applicationInfo.dataDir, ShareConstants.Y);
    }

    public static File b(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        if (applicationInfo == null) {
            return null;
        }
        return new File(applicationInfo.dataDir, ShareConstants.Z);
    }

    public static File c(Context context) {
        File b2 = b(context);
        if (b2 == null) {
            return null;
        }
        return new File(b2, ShareConstants.aa);
    }

    public static File a(String str) {
        return new File(str + "/" + ShareConstants.ab);
    }

    public static File b(String str) {
        return new File(str + "/" + ShareConstants.ac);
    }

    public static String c(String str) {
        if (str == null || str.length() != 32) {
            return null;
        }
        return ShareConstants.k + str.substring(0, 8);
    }

    public static String d(String str) {
        if (str == null || str.length() != 32) {
            return null;
        }
        return c(str) + ".apk";
    }

    public static boolean e(String str) {
        return str != null && str.length() == 32;
    }

    public static String d(Context context) {
        BufferedReader bufferedReader;
        File c = c(context);
        if (!a(c)) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(c)));
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        stringBuffer.append(readLine);
                        stringBuffer.append("\n");
                    } else {
                        a((Object) bufferedReader);
                        return stringBuffer.toString();
                    }
                } catch (Exception e) {
                    e = e;
                    try {
                        Log.e(f1360a, "checkTinkerLastUncaughtCrash exception: " + e);
                        a((Object) bufferedReader);
                        return null;
                    } catch (Throwable th) {
                        th = th;
                        a((Object) bufferedReader);
                        throw th;
                    }
                }
            }
        } catch (Exception e2) {
            e = e2;
            bufferedReader = null;
            Log.e(f1360a, "checkTinkerLastUncaughtCrash exception: " + e);
            a((Object) bufferedReader);
            return null;
        } catch (Throwable th2) {
            th = th2;
            bufferedReader = null;
            a((Object) bufferedReader);
            throw th;
        }
    }

    @SuppressLint({"NewApi"})
    public static void a(Object obj) {
        if (obj != null) {
            if (obj instanceof Closeable) {
                try {
                    ((Closeable) obj).close();
                } catch (Throwable unused) {
                }
            } else if (Build.VERSION.SDK_INT >= 19 && (obj instanceof AutoCloseable)) {
                ((AutoCloseable) obj).close();
            } else if (obj instanceof ZipFile) {
                ((ZipFile) obj).close();
            } else {
                throw new IllegalArgumentException("obj: " + obj + " cannot be closed.");
            }
        }
    }

    public static final boolean a(File file) {
        return file != null && file.exists() && file.canRead() && file.isFile() && file.length() > 0;
    }

    public static final boolean b(File file) {
        boolean z = "vivo".equalsIgnoreCase(Build.MANUFACTURER) || "oppo".equalsIgnoreCase(Build.MANUFACTURER);
        boolean z2 = Build.VERSION.SDK_INT >= 29 || (Build.VERSION.SDK_INT >= 28 && Build.VERSION.PREVIEW_SDK_INT != 0) || ShareTinkerInternals.c();
        boolean z3 = !file.exists() || file.length() == 0;
        if ((z || z2) && z3) {
            return true;
        }
        return false;
    }

    public static long c(File file) {
        long j;
        long j2 = 0;
        if (file == null || !file.exists()) {
            return 0;
        }
        if (file.isFile()) {
            return file.length();
        }
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File file2 : listFiles) {
                if (file2.isDirectory()) {
                    j = c(file2);
                } else {
                    j = file2.length();
                }
                j2 += j;
            }
        }
        return j2;
    }

    public static final boolean d(File file) {
        if (file == null || !file.exists()) {
            return true;
        }
        Log.i(f1360a, "safeDeleteFile, try to delete path: " + file.getPath());
        boolean delete = file.delete();
        if (!delete) {
            Log.e(f1360a, "Failed to delete file, try to delete when exit. path: " + file.getPath());
            file.deleteOnExit();
        }
        return delete;
    }

    public static final boolean f(String str) {
        if (str == null) {
            return false;
        }
        return e(new File(str));
    }

    public static final boolean e(File file) {
        File[] listFiles;
        if (file == null || !file.exists()) {
            return false;
        }
        if (file.isFile()) {
            d(file);
            return true;
        } else if (!file.isDirectory() || (listFiles = file.listFiles()) == null) {
            return true;
        } else {
            for (File e : listFiles) {
                e(e);
            }
            d(file);
            return true;
        }
    }

    public static boolean a(File file, String str) {
        String f;
        if (str == null || (f = f(file)) == null) {
            return false;
        }
        return str.equals(f);
    }

    public static boolean g(String str) {
        if (str == null) {
            return false;
        }
        return str.endsWith(ShareConstants.w);
    }

    public static boolean b(File file, String str) {
        return a(file, ShareConstants.X, str);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: java.util.zip.ZipFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: java.util.zip.ZipFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: java.util.zip.ZipFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: java.util.zip.ZipFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: java.util.zip.ZipFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v7, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v8, resolved type: java.util.zip.ZipFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v9, resolved type: java.util.zip.ZipFile} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(java.io.File r8, java.lang.String r9, java.lang.String r10) {
        /*
            r0 = 0
            if (r8 == 0) goto L_0x00b1
            if (r10 == 0) goto L_0x00b1
            if (r9 != 0) goto L_0x0009
            goto L_0x00b1
        L_0x0009:
            java.lang.String r1 = ""
            java.lang.String r2 = r8.getName()
            boolean r2 = g((java.lang.String) r2)
            if (r2 == 0) goto L_0x001a
            java.lang.String r8 = f((java.io.File) r8)
            goto L_0x007d
        L_0x001a:
            r2 = 0
            java.util.zip.ZipFile r3 = new java.util.zip.ZipFile     // Catch:{ Throwable -> 0x008e }
            r3.<init>(r8)     // Catch:{ Throwable -> 0x008e }
            java.util.zip.ZipEntry r9 = r3.getEntry(r9)     // Catch:{ Throwable -> 0x0088, all -> 0x0086 }
            if (r9 != 0) goto L_0x0044
            java.lang.String r9 = "Tinker.PatchFileUtil"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0088, all -> 0x0086 }
            r10.<init>()     // Catch:{ Throwable -> 0x0088, all -> 0x0086 }
            java.lang.String r1 = "There's no entry named: classes.dex in "
            r10.append(r1)     // Catch:{ Throwable -> 0x0088, all -> 0x0086 }
            java.lang.String r1 = r8.getAbsolutePath()     // Catch:{ Throwable -> 0x0088, all -> 0x0086 }
            r10.append(r1)     // Catch:{ Throwable -> 0x0088, all -> 0x0086 }
            java.lang.String r10 = r10.toString()     // Catch:{ Throwable -> 0x0088, all -> 0x0086 }
            android.util.Log.e(r9, r10)     // Catch:{ Throwable -> 0x0088, all -> 0x0086 }
            a((java.util.zip.ZipFile) r3)
            return r0
        L_0x0044:
            java.io.InputStream r9 = r3.getInputStream(r9)     // Catch:{ Throwable -> 0x005b }
            java.lang.String r2 = a((java.io.InputStream) r9)     // Catch:{ Throwable -> 0x0054, all -> 0x0051 }
            a((java.lang.Object) r9)     // Catch:{ Throwable -> 0x0088, all -> 0x0086 }
            r8 = r2
            goto L_0x007a
        L_0x0051:
            r10 = move-exception
            r2 = r9
            goto L_0x0082
        L_0x0054:
            r2 = move-exception
            r7 = r2
            r2 = r9
            r9 = r7
            goto L_0x005c
        L_0x0059:
            r10 = move-exception
            goto L_0x0082
        L_0x005b:
            r9 = move-exception
        L_0x005c:
            java.lang.String r4 = "Tinker.PatchFileUtil"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0059 }
            r5.<init>()     // Catch:{ all -> 0x0059 }
            java.lang.String r6 = "exception occurred when get md5: "
            r5.append(r6)     // Catch:{ all -> 0x0059 }
            java.lang.String r6 = r8.getAbsolutePath()     // Catch:{ all -> 0x0059 }
            r5.append(r6)     // Catch:{ all -> 0x0059 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0059 }
            android.util.Log.e(r4, r5, r9)     // Catch:{ all -> 0x0059 }
            a((java.lang.Object) r2)     // Catch:{ Throwable -> 0x0088, all -> 0x0086 }
            r8 = r1
        L_0x007a:
            a((java.util.zip.ZipFile) r3)
        L_0x007d:
            boolean r8 = r10.equals(r8)
            return r8
        L_0x0082:
            a((java.lang.Object) r2)     // Catch:{ Throwable -> 0x0088, all -> 0x0086 }
            throw r10     // Catch:{ Throwable -> 0x0088, all -> 0x0086 }
        L_0x0086:
            r8 = move-exception
            goto L_0x00ad
        L_0x0088:
            r9 = move-exception
            r2 = r3
            goto L_0x008f
        L_0x008b:
            r8 = move-exception
            r3 = r2
            goto L_0x00ad
        L_0x008e:
            r9 = move-exception
        L_0x008f:
            java.lang.String r10 = "Tinker.PatchFileUtil"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x008b }
            r1.<init>()     // Catch:{ all -> 0x008b }
            java.lang.String r3 = "Bad dex jar file: "
            r1.append(r3)     // Catch:{ all -> 0x008b }
            java.lang.String r8 = r8.getAbsolutePath()     // Catch:{ all -> 0x008b }
            r1.append(r8)     // Catch:{ all -> 0x008b }
            java.lang.String r8 = r1.toString()     // Catch:{ all -> 0x008b }
            android.util.Log.e(r10, r8, r9)     // Catch:{ all -> 0x008b }
            a((java.util.zip.ZipFile) r2)
            return r0
        L_0x00ad:
            a((java.util.zip.ZipFile) r3)
            throw r8
        L_0x00b1:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.tinker.loader.shareutil.SharePatchFileUtil.a(java.io.File, java.lang.String, java.lang.String):boolean");
    }

    public static void a(File file, File file2) throws IOException {
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        if (a(file) && file2 != null && !file.getAbsolutePath().equals(file2.getAbsolutePath())) {
            File parentFile = file2.getParentFile();
            if (parentFile != null && !parentFile.exists()) {
                parentFile.mkdirs();
            }
            FileOutputStream fileOutputStream2 = null;
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    fileOutputStream = new FileOutputStream(file2, false);
                } catch (Throwable th) {
                    th = th;
                    a((Object) fileInputStream);
                    a((Object) fileOutputStream2);
                    throw th;
                }
                try {
                    byte[] bArr = new byte[16384];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read > 0) {
                            fileOutputStream.write(bArr, 0, read);
                        } else {
                            a((Object) fileInputStream);
                            a((Object) fileOutputStream);
                            return;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    fileOutputStream2 = fileOutputStream;
                    a((Object) fileInputStream);
                    a((Object) fileOutputStream2);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fileInputStream = null;
                a((Object) fileInputStream);
                a((Object) fileOutputStream2);
                throw th;
            }
        }
    }

    public static String a(JarFile jarFile, JarEntry jarEntry) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedInputStream bufferedInputStream = null;
        try {
            InputStream inputStream = jarFile.getInputStream(jarEntry);
            byte[] bArr = new byte[16384];
            BufferedInputStream bufferedInputStream2 = new BufferedInputStream(inputStream);
            while (true) {
                try {
                    int read = bufferedInputStream2.read(bArr);
                    if (read > 0) {
                        sb.append(new String(bArr, 0, read));
                    } else {
                        a((Object) bufferedInputStream2);
                        return sb.toString();
                    }
                } catch (Throwable th) {
                    th = th;
                    bufferedInputStream = bufferedInputStream2;
                    a((Object) bufferedInputStream);
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            a((Object) bufferedInputStream);
            throw th;
        }
    }

    public static final String a(InputStream inputStream) {
        int i;
        if (inputStream == null) {
            return null;
        }
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            MessageDigest instance = MessageDigest.getInstance("MD5");
            StringBuilder sb = new StringBuilder(32);
            byte[] bArr = new byte[102400];
            while (true) {
                int read = bufferedInputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                instance.update(bArr, 0, read);
            }
            byte[] digest = instance.digest();
            for (byte b2 : digest) {
                sb.append(Integer.toString((b2 & 255) + 256, 16).substring(1));
            }
            return sb.toString();
        } catch (Exception unused) {
            return null;
        }
    }

    public static String a(byte[] bArr) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(bArr);
            char[] cArr = new char[(r0 * 2)];
            int i = 0;
            for (byte b2 : instance.digest()) {
                int i2 = i + 1;
                cArr[i] = b[(b2 >>> 4) & 15];
                i = i2 + 1;
                cArr[i2] = b[b2 & 15];
            }
            return new String(cArr);
        } catch (Exception unused) {
            return null;
        }
    }

    public static String f(File file) {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        if (file == null || !file.exists()) {
            return null;
        }
        try {
            fileInputStream = new FileInputStream(file);
            try {
                String a2 = a((InputStream) fileInputStream);
                a((Object) fileInputStream);
                return a2;
            } catch (Exception unused) {
                a((Object) fileInputStream);
                return null;
            } catch (Throwable th) {
                th = th;
                fileInputStream2 = fileInputStream;
                a((Object) fileInputStream2);
                throw th;
            }
        } catch (Exception unused2) {
            fileInputStream = null;
            a((Object) fileInputStream);
            return null;
        } catch (Throwable th2) {
            th = th2;
            a((Object) fileInputStream2);
            throw th;
        }
    }

    public static String b(File file, File file2) {
        if (ShareTinkerInternals.d()) {
            try {
                String e = ShareTinkerInternals.e();
                File parentFile = file.getParentFile();
                String name = file.getName();
                int lastIndexOf = name.lastIndexOf(46);
                if (lastIndexOf > 0) {
                    name = name.substring(0, lastIndexOf);
                }
                return parentFile.getAbsolutePath() + "/oat/" + e + "/" + name + ShareConstants.z;
            } catch (Exception e2) {
                throw new TinkerRuntimeException("getCurrentInstructionSet fail:", e2);
            }
        } else {
            String name2 = file.getName();
            if (!name2.endsWith(ShareConstants.w)) {
                int lastIndexOf2 = name2.lastIndexOf(".");
                if (lastIndexOf2 < 0) {
                    name2 = name2 + ShareConstants.w;
                } else {
                    StringBuilder sb = new StringBuilder(lastIndexOf2 + 4);
                    sb.append(name2, 0, lastIndexOf2);
                    sb.append(ShareConstants.w);
                    name2 = sb.toString();
                }
            }
            return new File(file2, name2).getPath();
        }
    }

    public static void a(ZipFile zipFile) {
        if (zipFile != null) {
            try {
                zipFile.close();
            } catch (IOException e) {
                Log.w(f1360a, "Failed to close resource", e);
            }
        }
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:22:0x0032=Splitter:B:22:0x0032, B:28:0x003d=Splitter:B:28:0x003d} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean c(java.io.File r4, java.lang.String r5) {
        /*
            r0 = 0
            r1 = 0
            java.util.zip.ZipFile r2 = new java.util.zip.ZipFile     // Catch:{ Throwable -> 0x0049 }
            r2.<init>(r4)     // Catch:{ Throwable -> 0x0049 }
            java.lang.String r4 = "resources.arsc"
            java.util.zip.ZipEntry r4 = r2.getEntry(r4)     // Catch:{ Throwable -> 0x0044, all -> 0x0041 }
            if (r4 != 0) goto L_0x001a
            java.lang.String r4 = "Tinker.PatchFileUtil"
            java.lang.String r5 = "checkResourceArscMd5 resources.arsc not found"
            android.util.Log.i(r4, r5)     // Catch:{ Throwable -> 0x0044, all -> 0x0041 }
            a((java.util.zip.ZipFile) r2)
            return r0
        L_0x001a:
            java.io.InputStream r4 = r2.getInputStream(r4)     // Catch:{ all -> 0x003b }
            java.lang.String r1 = a((java.io.InputStream) r4)     // Catch:{ all -> 0x0039 }
            if (r1 == 0) goto L_0x0032
            boolean r5 = r1.equals(r5)     // Catch:{ all -> 0x0039 }
            if (r5 == 0) goto L_0x0032
            r5 = 1
            a((java.lang.Object) r4)     // Catch:{ Throwable -> 0x0044, all -> 0x0041 }
            a((java.util.zip.ZipFile) r2)
            return r5
        L_0x0032:
            a((java.lang.Object) r4)     // Catch:{ Throwable -> 0x0044, all -> 0x0041 }
            a((java.util.zip.ZipFile) r2)
            goto L_0x0067
        L_0x0039:
            r5 = move-exception
            goto L_0x003d
        L_0x003b:
            r5 = move-exception
            r4 = r1
        L_0x003d:
            a((java.lang.Object) r4)     // Catch:{ Throwable -> 0x0044, all -> 0x0041 }
            throw r5     // Catch:{ Throwable -> 0x0044, all -> 0x0041 }
        L_0x0041:
            r4 = move-exception
            r1 = r2
            goto L_0x0068
        L_0x0044:
            r4 = move-exception
            r1 = r2
            goto L_0x004a
        L_0x0047:
            r4 = move-exception
            goto L_0x0068
        L_0x0049:
            r4 = move-exception
        L_0x004a:
            java.lang.String r5 = "Tinker.PatchFileUtil"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0047 }
            r2.<init>()     // Catch:{ all -> 0x0047 }
            java.lang.String r3 = "checkResourceArscMd5 throwable:"
            r2.append(r3)     // Catch:{ all -> 0x0047 }
            java.lang.String r4 = r4.getMessage()     // Catch:{ all -> 0x0047 }
            r2.append(r4)     // Catch:{ all -> 0x0047 }
            java.lang.String r4 = r2.toString()     // Catch:{ all -> 0x0047 }
            android.util.Log.i(r5, r4)     // Catch:{ all -> 0x0047 }
            a((java.util.zip.ZipFile) r1)
        L_0x0067:
            return r0
        L_0x0068:
            a((java.util.zip.ZipFile) r1)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.tinker.loader.shareutil.SharePatchFileUtil.c(java.io.File, java.lang.String):boolean");
    }

    public static void g(File file) {
        if (file != null) {
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
        }
    }
}
