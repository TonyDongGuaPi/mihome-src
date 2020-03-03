package com.xiaomi.youpin.common.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import com.xiaomi.smarthome.download.Downloads;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public final class FileUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f23252a = System.getProperty("line.separator");
    private static final char[] b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public interface OnReplaceListener {
        boolean a();
    }

    private FileUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static File a(String str) {
        if (z(str)) {
            return null;
        }
        return new File(str);
    }

    public static boolean b(String str) {
        return a(a(str));
    }

    public static boolean a(File file) {
        return file != null && file.exists();
    }

    public static boolean a(String str, String str2) {
        return a(a(str), str2);
    }

    public static boolean a(File file, String str) {
        if (file == null || !file.exists() || z(str)) {
            return false;
        }
        if (str.equals(file.getName())) {
            return true;
        }
        File file2 = new File(file.getParent() + File.separator + str);
        if (file2.exists() || !file.renameTo(file2)) {
            return false;
        }
        return true;
    }

    public static boolean c(String str) {
        return b(a(str));
    }

    public static boolean b(File file) {
        return file != null && file.exists() && file.isDirectory();
    }

    public static boolean d(String str) {
        return c(a(str));
    }

    public static boolean c(File file) {
        return file != null && file.exists() && file.isFile();
    }

    public static boolean e(String str) {
        return d(a(str));
    }

    public static boolean d(File file) {
        return file != null && (!file.exists() ? file.mkdirs() : file.isDirectory());
    }

    public static boolean f(String str) {
        return e(a(str));
    }

    public static boolean e(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            return file.isFile();
        }
        if (!d(file.getParentFile())) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean g(String str) {
        return f(a(str));
    }

    public static boolean f(File file) {
        if (file == null) {
            return false;
        }
        if ((file.exists() && !file.delete()) || !d(file.getParentFile())) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean b(String str, String str2) {
        return a(a(str), a(str2));
    }

    public static boolean a(String str, String str2, OnReplaceListener onReplaceListener) {
        return a(a(str), a(str2), onReplaceListener);
    }

    public static boolean a(File file, File file2) {
        return a(file, file2, false);
    }

    public static boolean a(File file, File file2, OnReplaceListener onReplaceListener) {
        return a(file, file2, onReplaceListener, false);
    }

    public static boolean c(String str, String str2) {
        return b(a(str), a(str2));
    }

    public static boolean b(String str, String str2, OnReplaceListener onReplaceListener) {
        return b(a(str), a(str2), onReplaceListener);
    }

    public static boolean b(File file, File file2) {
        return b(file, file2, false);
    }

    public static boolean b(File file, File file2, OnReplaceListener onReplaceListener) {
        return b(file, file2, onReplaceListener, false);
    }

    public static boolean d(String str, String str2) {
        return c(a(str), a(str2));
    }

    public static boolean c(String str, String str2, OnReplaceListener onReplaceListener) {
        return c(a(str), a(str2), onReplaceListener);
    }

    public static boolean c(File file, File file2) {
        return a(file, file2, true);
    }

    public static boolean c(File file, File file2, OnReplaceListener onReplaceListener) {
        return a(file, file2, onReplaceListener, true);
    }

    public static boolean e(String str, String str2) {
        return d(a(str), a(str2));
    }

    public static boolean d(String str, String str2, OnReplaceListener onReplaceListener) {
        return d(a(str), a(str2), onReplaceListener);
    }

    public static boolean d(File file, File file2) {
        return b(file, file2, true);
    }

    public static boolean d(File file, File file2, OnReplaceListener onReplaceListener) {
        return b(file, file2, onReplaceListener, true);
    }

    private static boolean a(File file, File file2, boolean z) {
        return a(file, file2, new OnReplaceListener() {
            public boolean a() {
                return true;
            }
        }, z);
    }

    private static boolean a(File file, File file2, OnReplaceListener onReplaceListener, boolean z) {
        if (file == null || file2 == null) {
            return false;
        }
        String str = file2.getPath() + File.separator;
        if (str.contains(file.getPath() + File.separator) || !file.exists() || !file.isDirectory()) {
            return false;
        }
        if (file2.exists()) {
            if (onReplaceListener != null && !onReplaceListener.a()) {
                return true;
            }
            if (!i(file2)) {
                return false;
            }
        }
        if (!d(file2)) {
            return false;
        }
        for (File file3 : file.listFiles()) {
            File file4 = new File(str + file3.getName());
            if (file3.isFile()) {
                if (!b(file3, file4, onReplaceListener, z)) {
                    return false;
                }
            } else if (file3.isDirectory() && !a(file3, file4, onReplaceListener, z)) {
                return false;
            }
        }
        if (!z || g(file)) {
            return true;
        }
        return false;
    }

    private static boolean b(File file, File file2, boolean z) {
        return b(file, file2, new OnReplaceListener() {
            public boolean a() {
                return true;
            }
        }, z);
    }

    private static boolean b(File file, File file2, OnReplaceListener onReplaceListener, boolean z) {
        if (file == null || file2 == null || file.equals(file2) || !file.exists() || !file.isFile()) {
            return false;
        }
        if (file2.exists()) {
            if (onReplaceListener != null && !onReplaceListener.a()) {
                return true;
            }
            if (!file2.delete()) {
                return false;
            }
        }
        if (!d(file2.getParentFile())) {
            return false;
        }
        try {
            if (!FileIOUtils.a(file2, (InputStream) new FileInputStream(file), false)) {
                return false;
            }
            if (!z || h(file)) {
                return true;
            }
            return false;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean h(String str) {
        return g(a(str));
    }

    public static boolean g(File file) {
        if (file == null) {
            return false;
        }
        if (!file.exists()) {
            return true;
        }
        if (!file.isDirectory()) {
            return false;
        }
        File[] listFiles = file.listFiles();
        if (!(listFiles == null || listFiles.length == 0)) {
            for (File file2 : listFiles) {
                if (file2.isFile()) {
                    if (!file2.delete()) {
                        return false;
                    }
                } else if (file2.isDirectory() && !g(file2)) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    public static boolean i(String str) {
        return h(a(str));
    }

    public static boolean h(File file) {
        return file != null && (!file.exists() || (file.isFile() && file.delete()));
    }

    public static boolean j(String str) {
        return i(a(str));
    }

    public static boolean i(File file) {
        return a(file, (FileFilter) new FileFilter() {
            public boolean accept(File file) {
                return true;
            }
        });
    }

    public static boolean k(String str) {
        return j(a(str));
    }

    public static boolean j(File file) {
        return a(file, (FileFilter) new FileFilter() {
            public boolean accept(File file) {
                return file.isFile();
            }
        });
    }

    public static boolean a(String str, FileFilter fileFilter) {
        return a(a(str), fileFilter);
    }

    public static boolean a(File file, FileFilter fileFilter) {
        if (file == null) {
            return false;
        }
        if (!file.exists()) {
            return true;
        }
        if (!file.isDirectory()) {
            return false;
        }
        File[] listFiles = file.listFiles();
        if (!(listFiles == null || listFiles.length == 0)) {
            for (File file2 : listFiles) {
                if (fileFilter.accept(file2)) {
                    if (file2.isFile()) {
                        if (!file2.delete()) {
                            return false;
                        }
                    } else if (file2.isDirectory() && !g(file2)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static List<File> l(String str) {
        return a(str, false);
    }

    public static List<File> k(File file) {
        return a(file, false);
    }

    public static List<File> a(String str, boolean z) {
        return a(a(str), z);
    }

    public static List<File> a(File file, boolean z) {
        return a(file, (FileFilter) new FileFilter() {
            public boolean accept(File file) {
                return true;
            }
        }, z);
    }

    public static List<File> b(String str, FileFilter fileFilter) {
        return a(a(str), fileFilter, false);
    }

    public static List<File> b(File file, FileFilter fileFilter) {
        return a(file, fileFilter, false);
    }

    public static List<File> a(String str, FileFilter fileFilter, boolean z) {
        return a(a(str), fileFilter, z);
    }

    public static List<File> a(File file, FileFilter fileFilter, boolean z) {
        if (!b(file)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        File[] listFiles = file.listFiles();
        if (!(listFiles == null || listFiles.length == 0)) {
            for (File file2 : listFiles) {
                if (fileFilter.accept(file2)) {
                    arrayList.add(file2);
                }
                if (z && file2.isDirectory()) {
                    arrayList.addAll(a(file2, fileFilter, true));
                }
            }
        }
        return arrayList;
    }

    public static long m(String str) {
        return l(a(str));
    }

    public static long l(File file) {
        if (file == null) {
            return -1;
        }
        return file.lastModified();
    }

    public static String n(String str) {
        return m(a(str));
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x002d A[SYNTHETIC, Splitter:B:19:0x002d] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x004e A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0053 A[SYNTHETIC, Splitter:B:35:0x0053] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m(java.io.File r3) {
        /*
            r0 = 0
            java.io.BufferedInputStream r1 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x0027 }
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0027 }
            r2.<init>(r3)     // Catch:{ IOException -> 0x0027 }
            r1.<init>(r2)     // Catch:{ IOException -> 0x0027 }
            int r3 = r1.read()     // Catch:{ IOException -> 0x0022, all -> 0x001f }
            int r3 = r3 << 8
            int r0 = r1.read()     // Catch:{ IOException -> 0x0022, all -> 0x001f }
            int r3 = r3 + r0
            r1.close()     // Catch:{ IOException -> 0x001a }
            goto L_0x0036
        L_0x001a:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0036
        L_0x001f:
            r3 = move-exception
            r0 = r1
            goto L_0x0051
        L_0x0022:
            r3 = move-exception
            r0 = r1
            goto L_0x0028
        L_0x0025:
            r3 = move-exception
            goto L_0x0051
        L_0x0027:
            r3 = move-exception
        L_0x0028:
            r3.printStackTrace()     // Catch:{ all -> 0x0025 }
            if (r0 == 0) goto L_0x0035
            r0.close()     // Catch:{ IOException -> 0x0031 }
            goto L_0x0035
        L_0x0031:
            r3 = move-exception
            r3.printStackTrace()
        L_0x0035:
            r3 = 0
        L_0x0036:
            r0 = 61371(0xefbb, float:8.5999E-41)
            if (r3 == r0) goto L_0x004e
            r0 = 65279(0xfeff, float:9.1475E-41)
            if (r3 == r0) goto L_0x004b
            r0 = 65534(0xfffe, float:9.1833E-41)
            if (r3 == r0) goto L_0x0048
            java.lang.String r3 = "GBK"
            return r3
        L_0x0048:
            java.lang.String r3 = "Unicode"
            return r3
        L_0x004b:
            java.lang.String r3 = "UTF-16BE"
            return r3
        L_0x004e:
            java.lang.String r3 = "UTF-8"
            return r3
        L_0x0051:
            if (r0 == 0) goto L_0x005b
            r0.close()     // Catch:{ IOException -> 0x0057 }
            goto L_0x005b
        L_0x0057:
            r0 = move-exception
            r0.printStackTrace()
        L_0x005b:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.common.util.FileUtils.m(java.io.File):java.lang.String");
    }

    public static int o(String str) {
        return n(a(str));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0031, code lost:
        r9 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0032, code lost:
        r1 = r2;
        r0 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0057, code lost:
        r9 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x006f, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0070, code lost:
        r0.printStackTrace();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0057 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:4:0x000e] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0065 A[SYNTHETIC, Splitter:B:45:0x0065] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x006b A[SYNTHETIC, Splitter:B:49:0x006b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int n(java.io.File r9) {
        /*
            r0 = 1
            r1 = 0
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x005f }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ IOException -> 0x005f }
            r3.<init>(r9)     // Catch:{ IOException -> 0x005f }
            r2.<init>(r3)     // Catch:{ IOException -> 0x005f }
            r9 = 1024(0x400, float:1.435E-42)
            byte[] r1 = new byte[r9]     // Catch:{ IOException -> 0x0059, all -> 0x0057 }
            java.lang.String r3 = f23252a     // Catch:{ IOException -> 0x0059, all -> 0x0057 }
            java.lang.String r4 = "\n"
            boolean r3 = r3.endsWith(r4)     // Catch:{ IOException -> 0x0059, all -> 0x0057 }
            r4 = -1
            r5 = 0
            if (r3 == 0) goto L_0x0037
        L_0x001c:
            int r3 = r2.read(r1, r5, r9)     // Catch:{ IOException -> 0x0059, all -> 0x0057 }
            if (r3 == r4) goto L_0x004e
            r6 = r0
            r0 = 0
        L_0x0024:
            if (r0 >= r3) goto L_0x0035
            byte r7 = r1[r0]     // Catch:{ IOException -> 0x0031, all -> 0x0057 }
            r8 = 10
            if (r7 != r8) goto L_0x002e
            int r6 = r6 + 1
        L_0x002e:
            int r0 = r0 + 1
            goto L_0x0024
        L_0x0031:
            r9 = move-exception
            r1 = r2
            r0 = r6
            goto L_0x0060
        L_0x0035:
            r0 = r6
            goto L_0x001c
        L_0x0037:
            int r3 = r2.read(r1, r5, r9)     // Catch:{ IOException -> 0x0059, all -> 0x0057 }
            if (r3 == r4) goto L_0x004e
            r6 = r0
            r0 = 0
        L_0x003f:
            if (r0 >= r3) goto L_0x004c
            byte r7 = r1[r0]     // Catch:{ IOException -> 0x0031, all -> 0x0057 }
            r8 = 13
            if (r7 != r8) goto L_0x0049
            int r6 = r6 + 1
        L_0x0049:
            int r0 = r0 + 1
            goto L_0x003f
        L_0x004c:
            r0 = r6
            goto L_0x0037
        L_0x004e:
            r2.close()     // Catch:{ IOException -> 0x0052 }
            goto L_0x0068
        L_0x0052:
            r9 = move-exception
            r9.printStackTrace()
            goto L_0x0068
        L_0x0057:
            r9 = move-exception
            goto L_0x0069
        L_0x0059:
            r9 = move-exception
            r1 = r2
            goto L_0x0060
        L_0x005c:
            r9 = move-exception
            r2 = r1
            goto L_0x0069
        L_0x005f:
            r9 = move-exception
        L_0x0060:
            r9.printStackTrace()     // Catch:{ all -> 0x005c }
            if (r1 == 0) goto L_0x0068
            r1.close()     // Catch:{ IOException -> 0x0052 }
        L_0x0068:
            return r0
        L_0x0069:
            if (r2 == 0) goto L_0x0073
            r2.close()     // Catch:{ IOException -> 0x006f }
            goto L_0x0073
        L_0x006f:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0073:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.common.util.FileUtils.n(java.io.File):int");
    }

    public static String p(String str) {
        return o(a(str));
    }

    public static String o(File file) {
        long q = q(file);
        if (q == -1) {
            return "";
        }
        return a(q);
    }

    public static String q(String str) {
        long s = s(str);
        if (s == -1) {
            return "";
        }
        return a(s);
    }

    public static String p(File file) {
        long r = r(file);
        if (r == -1) {
            return "";
        }
        return a(r);
    }

    public static long r(String str) {
        return q(a(str));
    }

    public static long q(File file) {
        long j;
        if (!b(file)) {
            return -1;
        }
        long j2 = 0;
        File[] listFiles = file.listFiles();
        if (!(listFiles == null || listFiles.length == 0)) {
            for (File file2 : listFiles) {
                if (file2.isDirectory()) {
                    j = q(file2);
                } else {
                    j = file2.length();
                }
                j2 += j;
            }
        }
        return j2;
    }

    public static long s(String str) {
        if (str.matches("[a-zA-z]+://[^\\s]*")) {
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                httpURLConnection.setRequestProperty("Accept-Encoding", "identity");
                httpURLConnection.connect();
                if (httpURLConnection.getResponseCode() == 200) {
                    return (long) httpURLConnection.getContentLength();
                }
                return -1;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return r(a(str));
    }

    public static long r(File file) {
        if (!c(file)) {
            return -1;
        }
        return file.length();
    }

    public static String t(String str) {
        return s(z(str) ? null : new File(str));
    }

    public static String s(File file) {
        return a(t(file));
    }

    public static byte[] u(String str) {
        return t(a(str));
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x003a A[SYNTHETIC, Splitter:B:23:0x003a] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0047 A[SYNTHETIC, Splitter:B:31:0x0047] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] t(java.io.File r3) {
        /*
            r0 = 0
            if (r3 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ IOException | NoSuchAlgorithmException -> 0x0033, all -> 0x0031 }
            r1.<init>(r3)     // Catch:{ IOException | NoSuchAlgorithmException -> 0x0033, all -> 0x0031 }
            java.lang.String r3 = "MD5"
            java.security.MessageDigest r3 = java.security.MessageDigest.getInstance(r3)     // Catch:{ IOException | NoSuchAlgorithmException -> 0x0033, all -> 0x0031 }
            java.security.DigestInputStream r2 = new java.security.DigestInputStream     // Catch:{ IOException | NoSuchAlgorithmException -> 0x0033, all -> 0x0031 }
            r2.<init>(r1, r3)     // Catch:{ IOException | NoSuchAlgorithmException -> 0x0033, all -> 0x0031 }
            r3 = 262144(0x40000, float:3.67342E-40)
            byte[] r3 = new byte[r3]     // Catch:{ IOException | NoSuchAlgorithmException -> 0x002f }
        L_0x0018:
            int r1 = r2.read(r3)     // Catch:{ IOException | NoSuchAlgorithmException -> 0x002f }
            if (r1 > 0) goto L_0x0018
            java.security.MessageDigest r3 = r2.getMessageDigest()     // Catch:{ IOException | NoSuchAlgorithmException -> 0x002f }
            byte[] r3 = r3.digest()     // Catch:{ IOException | NoSuchAlgorithmException -> 0x002f }
            r2.close()     // Catch:{ IOException -> 0x002a }
            goto L_0x002e
        L_0x002a:
            r0 = move-exception
            r0.printStackTrace()
        L_0x002e:
            return r3
        L_0x002f:
            r3 = move-exception
            goto L_0x0035
        L_0x0031:
            r3 = move-exception
            goto L_0x0045
        L_0x0033:
            r3 = move-exception
            r2 = r0
        L_0x0035:
            r3.printStackTrace()     // Catch:{ all -> 0x0043 }
            if (r2 == 0) goto L_0x0042
            r2.close()     // Catch:{ IOException -> 0x003e }
            goto L_0x0042
        L_0x003e:
            r3 = move-exception
            r3.printStackTrace()
        L_0x0042:
            return r0
        L_0x0043:
            r3 = move-exception
            r0 = r2
        L_0x0045:
            if (r0 == 0) goto L_0x004f
            r0.close()     // Catch:{ IOException -> 0x004b }
            goto L_0x004f
        L_0x004b:
            r0 = move-exception
            r0.printStackTrace()
        L_0x004f:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.common.util.FileUtils.t(java.io.File):byte[]");
    }

    public static String u(File file) {
        return file == null ? "" : v(file.getAbsolutePath());
    }

    public static String v(String str) {
        int lastIndexOf;
        if (!z(str) && (lastIndexOf = str.lastIndexOf(File.separator)) != -1) {
            return str.substring(0, lastIndexOf + 1);
        }
        return "";
    }

    public static String v(File file) {
        return file == null ? "" : w(file.getAbsolutePath());
    }

    public static String w(String str) {
        if (z(str)) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(File.separator);
        return lastIndexOf == -1 ? str : str.substring(lastIndexOf + 1);
    }

    public static String w(File file) {
        return file == null ? "" : x(file.getPath());
    }

    public static String x(String str) {
        if (z(str)) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(46);
        int lastIndexOf2 = str.lastIndexOf(File.separator);
        if (lastIndexOf2 == -1) {
            return lastIndexOf == -1 ? str : str.substring(0, lastIndexOf);
        }
        if (lastIndexOf == -1 || lastIndexOf2 > lastIndexOf) {
            return str.substring(lastIndexOf2 + 1);
        }
        return str.substring(lastIndexOf2 + 1, lastIndexOf);
    }

    public static String x(File file) {
        return file == null ? "" : y(file.getPath());
    }

    public static String y(String str) {
        if (z(str)) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(46);
        return (lastIndexOf == -1 || str.lastIndexOf(File.separator) >= lastIndexOf) ? "" : str.substring(lastIndexOf + 1);
    }

    private static String a(byte[] bArr) {
        int length;
        if (bArr == null || (length = bArr.length) <= 0) {
            return "";
        }
        char[] cArr = new char[(length << 1)];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i + 1;
            cArr[i] = b[(bArr[i2] >>> 4) & 15];
            i = i3 + 1;
            cArr[i3] = b[bArr[i2] & 15];
        }
        return new String(cArr);
    }

    @SuppressLint({"DefaultLocale"})
    private static String a(long j) {
        if (j < 0) {
            return "shouldn't be less than zero!";
        }
        if (j < 1024) {
            return String.format("%.3fB", new Object[]{Double.valueOf((double) j)});
        } else if (j < 1048576) {
            double d = (double) j;
            Double.isNaN(d);
            return String.format("%.3fKB", new Object[]{Double.valueOf(d / 1024.0d)});
        } else if (j < 1073741824) {
            double d2 = (double) j;
            Double.isNaN(d2);
            return String.format("%.3fMB", new Object[]{Double.valueOf(d2 / 1048576.0d)});
        } else {
            double d3 = (double) j;
            Double.isNaN(d3);
            return String.format("%.3fGB", new Object[]{Double.valueOf(d3 / 1.073741824E9d)});
        }
    }

    private static boolean z(String str) {
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

    public static String a(Context context, Uri uri) {
        if (Build.VERSION.SDK_INT >= 19) {
            return KKImagePathUtils.a(context, uri);
        }
        return b(context, uri);
    }

    public static String b(Context context, Uri uri) {
        String[] strArr = {Downloads._DATA};
        Cursor query = context.getContentResolver().query(uri, strArr, (String) null, (String[]) null, (String) null);
        if (query == null) {
            return null;
        }
        query.moveToFirst();
        String string = query.getString(query.getColumnIndex(strArr[0]));
        query.close();
        return string;
    }
}
