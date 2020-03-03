package com.xiaomi.smarthome.frame.file;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import com.xiaomi.smarthome.library.common.util.IOUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class FileUtils {
    public static String a(String str) {
        File file = new File(str);
        if (!file.isFile()) {
            return "";
        }
        try {
            return a((InputStream) new FileInputStream(file));
        } catch (FileNotFoundException | IOException unused) {
            return "";
        }
    }

    public static final String a(InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        if (inputStream != null) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
                sb.append("\n");
            }
            inputStream.close();
        }
        return sb.toString();
    }

    public static byte[] b(String str) {
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            byte[] bArr = new byte[fileInputStream.available()];
            try {
                fileInputStream.read(bArr);
                fileInputStream.close();
                return bArr;
            } catch (FileNotFoundException | IOException unused) {
                return bArr;
            }
        } catch (FileNotFoundException | IOException unused2) {
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

    public static boolean a(InputStream inputStream, String str, String str2, String str3) {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(c(str));
            try {
                if (!TextUtils.isEmpty(str2)) {
                    fileOutputStream.write(str2.getBytes());
                }
                if (inputStream != null) {
                    byte[] bArr = new byte[2048];
                    while (true) {
                        int read = inputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                    inputStream.close();
                }
                if (!TextUtils.isEmpty(str3)) {
                    fileOutputStream.write(str3.getBytes());
                }
                fileOutputStream.flush();
                fileOutputStream.close();
                IOUtils.a(inputStream);
                IOUtils.a((OutputStream) fileOutputStream);
                return true;
            } catch (IOException unused) {
                IOUtils.a(inputStream);
                IOUtils.a((OutputStream) fileOutputStream);
                return false;
            } catch (Throwable th) {
                th = th;
                IOUtils.a(inputStream);
                IOUtils.a((OutputStream) fileOutputStream);
                throw th;
            }
        } catch (IOException unused2) {
            fileOutputStream = null;
            IOUtils.a(inputStream);
            IOUtils.a((OutputStream) fileOutputStream);
            return false;
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream = null;
            IOUtils.a(inputStream);
            IOUtils.a((OutputStream) fileOutputStream);
            throw th;
        }
    }

    public static boolean a(String str, String str2) {
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
        File file = new File(str);
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
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static boolean a() {
        return Environment.getExternalStorageState().equals("removed");
    }

    public static boolean b() {
        return !Environment.getExternalStorageState().equals("mounted");
    }

    public static boolean c() {
        return d() <= 102400;
    }

    public static long d() {
        if (b()) {
            return 0;
        }
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return ((long) statFs.getBlockSize()) * (((long) statFs.getAvailableBlocks()) - 4);
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x003a A[SYNTHETIC, Splitter:B:23:0x003a] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0045 A[SYNTHETIC, Splitter:B:29:0x0045] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean b(java.lang.String r2, java.lang.String r3) {
        /*
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch:{ Exception -> 0x0033 }
            r1.<init>(r3)     // Catch:{ Exception -> 0x0033 }
            boolean r3 = r1.exists()     // Catch:{ Exception -> 0x0033 }
            if (r3 == 0) goto L_0x0010
            r1.delete()     // Catch:{ Exception -> 0x0033 }
            goto L_0x0016
        L_0x0010:
            r1.mkdirs()     // Catch:{ Exception -> 0x0033 }
            r1.createNewFile()     // Catch:{ Exception -> 0x0033 }
        L_0x0016:
            java.io.PrintWriter r3 = new java.io.PrintWriter     // Catch:{ Exception -> 0x0033 }
            r3.<init>(r1)     // Catch:{ Exception -> 0x0033 }
            r3.write(r2)     // Catch:{ Exception -> 0x002e, all -> 0x002b }
            r3.flush()     // Catch:{ Exception -> 0x002e, all -> 0x002b }
            r3.close()     // Catch:{ Exception -> 0x0025 }
            goto L_0x0029
        L_0x0025:
            r2 = move-exception
            r2.printStackTrace()
        L_0x0029:
            r2 = 1
            return r2
        L_0x002b:
            r2 = move-exception
            r0 = r3
            goto L_0x0043
        L_0x002e:
            r2 = move-exception
            r0 = r3
            goto L_0x0034
        L_0x0031:
            r2 = move-exception
            goto L_0x0043
        L_0x0033:
            r2 = move-exception
        L_0x0034:
            r2.printStackTrace()     // Catch:{ all -> 0x0031 }
            r2 = 0
            if (r0 == 0) goto L_0x0042
            r0.close()     // Catch:{ Exception -> 0x003e }
            goto L_0x0042
        L_0x003e:
            r3 = move-exception
            r3.printStackTrace()
        L_0x0042:
            return r2
        L_0x0043:
            if (r0 == 0) goto L_0x004d
            r0.close()     // Catch:{ Exception -> 0x0049 }
            goto L_0x004d
        L_0x0049:
            r3 = move-exception
            r3.printStackTrace()
        L_0x004d:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.frame.file.FileUtils.b(java.lang.String, java.lang.String):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002a, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002c, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002d, code lost:
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003a, code lost:
        r1.close();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x002a A[ExcHandler: all (th java.lang.Throwable), Splitter:B:5:0x0012] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0040  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String l(java.lang.String r4) throws java.io.IOException {
        /*
            java.io.File r0 = new java.io.File
            r0.<init>(r4)
            boolean r4 = r0.exists()
            r1 = 0
            if (r4 != 0) goto L_0x000d
            return r1
        L_0x000d:
            java.io.FileReader r4 = new java.io.FileReader     // Catch:{ Exception -> 0x0033 }
            r4.<init>(r0)     // Catch:{ Exception -> 0x0033 }
            long r2 = r0.length()     // Catch:{ Exception -> 0x002c, all -> 0x002a }
            int r0 = (int) r2     // Catch:{ Exception -> 0x002c, all -> 0x002a }
            char[] r0 = new char[r0]     // Catch:{ Exception -> 0x002c, all -> 0x002a }
            r4.read(r0)     // Catch:{ Exception -> 0x002c, all -> 0x002a }
            java.lang.String r2 = new java.lang.String     // Catch:{ Exception -> 0x002c, all -> 0x002a }
            r2.<init>(r0)     // Catch:{ Exception -> 0x002c, all -> 0x002a }
            r4.close()     // Catch:{ Exception -> 0x0028, all -> 0x002a }
            r4.close()
            goto L_0x003d
        L_0x0028:
            r0 = move-exception
            goto L_0x002e
        L_0x002a:
            r0 = move-exception
            goto L_0x003e
        L_0x002c:
            r0 = move-exception
            r2 = r1
        L_0x002e:
            r1 = r4
            goto L_0x0035
        L_0x0030:
            r0 = move-exception
            r4 = r1
            goto L_0x003e
        L_0x0033:
            r0 = move-exception
            r2 = r1
        L_0x0035:
            r0.printStackTrace()     // Catch:{ all -> 0x0030 }
            if (r1 == 0) goto L_0x003d
            r1.close()
        L_0x003d:
            return r2
        L_0x003e:
            if (r4 == 0) goto L_0x0043
            r4.close()
        L_0x0043:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.frame.file.FileUtils.l(java.lang.String):java.lang.String");
    }
}
