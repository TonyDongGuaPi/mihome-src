package com.xiaomi.smarthome.core.server.internal.plugin.util;

import android.content.Context;
import android.os.Environment;
import android.os.Parcel;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class FileUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f14698a = "FileUtils";

    public static String a(String str) {
        String str2 = "";
        File file = new File(str);
        if (file.isFile()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    str2 = str2 + readLine + "\n";
                }
                fileInputStream.close();
            } catch (FileNotFoundException | IOException unused) {
            }
        }
        return str2;
    }

    public static boolean a(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return false;
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(str));
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(str2);
            bufferedWriter.close();
            outputStreamWriter.close();
            fileOutputStream.close();
            return true;
        } catch (IOException e) {
            LogUtil.b(f14698a, e.toString());
            return false;
        }
    }

    public static byte[] b(String str) {
        return a(new File(str));
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0030 A[SYNTHETIC, Splitter:B:19:0x0030] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0042 A[SYNTHETIC, Splitter:B:26:0x0042] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] a(java.io.File r3) {
        /*
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x0023, all -> 0x0020 }
            r1.<init>(r3)     // Catch:{ Throwable -> 0x0023, all -> 0x0020 }
            int r3 = r1.available()     // Catch:{ Throwable -> 0x001e }
            byte[] r3 = new byte[r3]     // Catch:{ Throwable -> 0x001e }
            r1.read(r3)     // Catch:{ Throwable -> 0x001e }
            r1.close()     // Catch:{ Throwable -> 0x0013 }
            goto L_0x001d
        L_0x0013:
            r0 = move-exception
            java.lang.String r1 = "FileUtils"
            java.lang.String r0 = android.util.Log.getStackTraceString(r0)
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.b(r1, r0)
        L_0x001d:
            return r3
        L_0x001e:
            r3 = move-exception
            goto L_0x0025
        L_0x0020:
            r3 = move-exception
            r1 = r0
            goto L_0x0040
        L_0x0023:
            r3 = move-exception
            r1 = r0
        L_0x0025:
            java.lang.String r2 = "FileUtils"
            java.lang.String r3 = android.util.Log.getStackTraceString(r3)     // Catch:{ all -> 0x003f }
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.b(r2, r3)     // Catch:{ all -> 0x003f }
            if (r1 == 0) goto L_0x003e
            r1.close()     // Catch:{ Throwable -> 0x0034 }
            goto L_0x003e
        L_0x0034:
            r3 = move-exception
            java.lang.String r1 = "FileUtils"
            java.lang.String r3 = android.util.Log.getStackTraceString(r3)
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.b(r1, r3)
        L_0x003e:
            return r0
        L_0x003f:
            r3 = move-exception
        L_0x0040:
            if (r1 == 0) goto L_0x0050
            r1.close()     // Catch:{ Throwable -> 0x0046 }
            goto L_0x0050
        L_0x0046:
            r0 = move-exception
            java.lang.String r0 = android.util.Log.getStackTraceString(r0)
            java.lang.String r1 = "FileUtils"
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.b(r1, r0)
        L_0x0050:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils.a(java.io.File):byte[]");
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0031 A[SYNTHETIC, Splitter:B:22:0x0031] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0042 A[SYNTHETIC, Splitter:B:28:0x0042] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(java.io.File r2, byte[] r3) {
        /*
            r0 = 0
            c((java.io.File) r2)     // Catch:{ Throwable -> 0x0024 }
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ Throwable -> 0x0024 }
            r1.<init>(r2)     // Catch:{ Throwable -> 0x0024 }
            r1.write(r3)     // Catch:{ Throwable -> 0x001f, all -> 0x001c }
            r2 = 1
            r1.close()     // Catch:{ Throwable -> 0x0011 }
            goto L_0x001b
        L_0x0011:
            r3 = move-exception
            java.lang.String r0 = "FileUtils"
            java.lang.String r3 = android.util.Log.getStackTraceString(r3)
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.b(r0, r3)
        L_0x001b:
            return r2
        L_0x001c:
            r2 = move-exception
            r0 = r1
            goto L_0x0040
        L_0x001f:
            r2 = move-exception
            r0 = r1
            goto L_0x0025
        L_0x0022:
            r2 = move-exception
            goto L_0x0040
        L_0x0024:
            r2 = move-exception
        L_0x0025:
            java.lang.String r3 = "FileUtils"
            java.lang.String r2 = android.util.Log.getStackTraceString(r2)     // Catch:{ all -> 0x0022 }
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.b(r3, r2)     // Catch:{ all -> 0x0022 }
            r2 = 0
            if (r0 == 0) goto L_0x003f
            r0.close()     // Catch:{ Throwable -> 0x0035 }
            goto L_0x003f
        L_0x0035:
            r3 = move-exception
            java.lang.String r0 = "FileUtils"
            java.lang.String r3 = android.util.Log.getStackTraceString(r3)
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.b(r0, r3)
        L_0x003f:
            return r2
        L_0x0040:
            if (r0 == 0) goto L_0x0050
            r0.close()     // Catch:{ Throwable -> 0x0046 }
            goto L_0x0050
        L_0x0046:
            r3 = move-exception
            java.lang.String r3 = android.util.Log.getStackTraceString(r3)
            java.lang.String r0 = "FileUtils"
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.b(r0, r3)
        L_0x0050:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils.a(java.io.File, byte[]):boolean");
    }

    public static Parcel b(File file) {
        Parcel a2 = a(a(file));
        if (a2 == null) {
            file.delete();
        }
        return a2;
    }

    public static Parcel a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        try {
            Parcel obtain = Parcel.obtain();
            obtain.setDataCapacity(bArr.length);
            obtain.unmarshall(bArr, 0, bArr.length);
            obtain.setDataPosition(0);
            return obtain;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void a(Context context, String str, String str2) throws IOException {
        InputStream open = context.getResources().getAssets().open(str);
        FileOutputStream fileOutputStream = new FileOutputStream(c(str2));
        byte[] bArr = new byte[2048];
        while (true) {
            int read = open.read(bArr);
            if (read != -1) {
                fileOutputStream.write(bArr, 0, read);
            } else {
                fileOutputStream.flush();
                open.close();
                fileOutputStream.close();
                return;
            }
        }
    }

    public static boolean b(String str, String str2) {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(str));
            FileOutputStream fileOutputStream = new FileOutputStream(c(str2));
            byte[] bArr = new byte[2048];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read != -1) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.flush();
                    fileInputStream.close();
                    fileOutputStream.close();
                    return true;
                }
            }
        } catch (IOException unused) {
            return false;
        }
    }

    public static File c(String str) {
        File file = new File(str);
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        try {
            file.createNewFile();
        } catch (IOException unused) {
        }
        return file;
    }

    public static boolean d(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        File file = new File(str);
        if (!file.exists() || file.isDirectory() || !file.delete()) {
            return false;
        }
        return true;
    }

    public static void e(String str) {
        File[] listFiles;
        if (!TextUtils.isEmpty(str)) {
            File file = new File(str);
            if (file.exists()) {
                if (file.isDirectory() && (listFiles = file.listFiles()) != null && listFiles.length > 0) {
                    for (File file2 : file.listFiles()) {
                        if (file2 != null && file2.exists()) {
                            e(file2.getAbsolutePath());
                        }
                    }
                }
                file.delete();
            }
        }
    }

    public static void c(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            File file = new File(str);
            if (file.exists()) {
                if (file.isDirectory()) {
                    new File(str2).mkdirs();
                    File[] listFiles = file.listFiles();
                    if (listFiles != null && listFiles.length > 0) {
                        for (File file2 : file.listFiles()) {
                            if (file2 != null && file2.exists()) {
                                c(file2.getAbsolutePath(), new File(str2, file2.getName()).getAbsolutePath());
                            }
                        }
                    }
                    file.delete();
                    return;
                }
                file.renameTo(new File(str2));
            }
        }
    }

    public static boolean f(String str) {
        return !TextUtils.isEmpty(str) && new File(str).exists();
    }

    public static long g(String str) {
        if (!TextUtils.isEmpty(str)) {
            return new File(str).lastModified();
        }
        return 0;
    }

    public static void h(String str) {
        File parentFile;
        if (!TextUtils.isEmpty(str) && (parentFile = new File(str).getParentFile()) != null && !parentFile.exists()) {
            parentFile.mkdirs();
        }
    }

    public static File i(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return c(new File(str));
    }

    public static File c(File file) {
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException unused) {
        }
        return file;
    }

    public static void a(File[] fileArr, long j, Comparator<File> comparator) {
        long j2 = 0;
        for (File length : fileArr) {
            j2 += length.length();
        }
        if (j2 >= j) {
            List asList = Arrays.asList(fileArr);
            if (comparator == null) {
                comparator = new Comparator<File>() {
                    /* renamed from: a */
                    public int compare(File file, File file2) {
                        return (int) (file2.length() - file.length());
                    }
                };
            }
            Collections.sort(asList, comparator);
            Iterator it = asList.iterator();
            while (j2 > j && it.hasNext()) {
                File file = (File) it.next();
                j2 -= file.length();
                j(file.getPath());
            }
        }
    }

    public static boolean j(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        File file = new File(str);
        if (file.isFile()) {
            if (!file.exists() || !file.delete()) {
                return false;
            }
            return true;
        } else if (!file.isDirectory()) {
            return false;
        } else {
            e(str);
            return true;
        }
    }

    public static void k(String str) {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public static String a(Context context, String str) {
        try {
            InputStream open = context.getAssets().open(str);
            byte[] bArr = new byte[open.available()];
            open.read(bArr);
            open.close();
            return new String(bArr, "UTF-8");
        } catch (IOException e) {
            LogUtil.b(f14698a, Log.getStackTraceString(e));
            return "";
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:2|3|8) */
    /* JADX WARNING: Code restructure failed: missing block: B:3:?, code lost:
        r0 = java.io.File.separator + "data" + java.io.File.separator + "data" + java.io.File.separator + r2.getPackageName() + java.io.File.separator + "files";
        new java.io.File(r0).mkdirs();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0045, code lost:
        r2 = r2.getDir("files", 0);
        r2.mkdirs();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:?, code lost:
        return r2.getPath();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:?, code lost:
        return r0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:2:0x0009 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(android.content.Context r2) {
        /*
            java.io.File r0 = r2.getFilesDir()     // Catch:{ Throwable -> 0x0009 }
            java.lang.String r0 = r0.getPath()     // Catch:{ Throwable -> 0x0009 }
            goto L_0x0053
        L_0x0009:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0045 }
            r0.<init>()     // Catch:{ Throwable -> 0x0045 }
            java.lang.String r1 = java.io.File.separator     // Catch:{ Throwable -> 0x0045 }
            r0.append(r1)     // Catch:{ Throwable -> 0x0045 }
            java.lang.String r1 = "data"
            r0.append(r1)     // Catch:{ Throwable -> 0x0045 }
            java.lang.String r1 = java.io.File.separator     // Catch:{ Throwable -> 0x0045 }
            r0.append(r1)     // Catch:{ Throwable -> 0x0045 }
            java.lang.String r1 = "data"
            r0.append(r1)     // Catch:{ Throwable -> 0x0045 }
            java.lang.String r1 = java.io.File.separator     // Catch:{ Throwable -> 0x0045 }
            r0.append(r1)     // Catch:{ Throwable -> 0x0045 }
            java.lang.String r1 = r2.getPackageName()     // Catch:{ Throwable -> 0x0045 }
            r0.append(r1)     // Catch:{ Throwable -> 0x0045 }
            java.lang.String r1 = java.io.File.separator     // Catch:{ Throwable -> 0x0045 }
            r0.append(r1)     // Catch:{ Throwable -> 0x0045 }
            java.lang.String r1 = "files"
            r0.append(r1)     // Catch:{ Throwable -> 0x0045 }
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x0045 }
            java.io.File r1 = new java.io.File     // Catch:{ Throwable -> 0x0045 }
            r1.<init>(r0)     // Catch:{ Throwable -> 0x0045 }
            r1.mkdirs()     // Catch:{ Throwable -> 0x0045 }
            goto L_0x0053
        L_0x0045:
            java.lang.String r0 = "files"
            r1 = 0
            java.io.File r2 = r2.getDir(r0, r1)
            r2.mkdirs()
            java.lang.String r0 = r2.getPath()
        L_0x0053:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils.a(android.content.Context):java.lang.String");
    }

    public static long b(Context context) {
        return new File(a(context)).getUsableSpace();
    }

    public static File b(Context context, String str) {
        File externalFilesDir = context.getExternalFilesDir(Environment.DIRECTORY_MOVIES);
        if (externalFilesDir == null) {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            externalFilesDir = new File(externalStorageDirectory, "Android/data/" + context.getPackageName() + "/files/" + Environment.DIRECTORY_MOVIES);
        }
        return new File(externalFilesDir + "/" + str);
    }
}
