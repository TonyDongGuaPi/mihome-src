package com.tencent.smtt.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.sdk.TbsDownloader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.util.zip.ZipEntry;

@SuppressLint({"NewApi"})
public class j {

    /* renamed from: a  reason: collision with root package name */
    public static String f9209a = null;
    public static final a b = new l();
    private static final int c = "lib/".length();
    private static RandomAccessFile d = null;

    public interface a {
        boolean a(File file, File file2);
    }

    public interface b {
        boolean a(InputStream inputStream, ZipEntry zipEntry, String str);
    }

    public static File a(Context context, boolean z, String str) {
        String d2 = z ? d(context) : c(context);
        if (d2 == null) {
            return null;
        }
        File file = new File(d2);
        if (!file.exists()) {
            file.mkdirs();
        }
        if (!file.canWrite()) {
            return null;
        }
        File file2 = new File(file, str);
        if (!file2.exists()) {
            try {
                file2.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return file2;
    }

    public static String a(Context context, int i) {
        return a(context, context.getApplicationInfo().packageName, i, true);
    }

    private static String a(Context context, String str) {
        if (context == null) {
            return "";
        }
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            context = applicationContext;
        }
        try {
            return context.getExternalFilesDir(str).getAbsolutePath();
        } catch (Throwable th) {
            th.printStackTrace();
            try {
                return Environment.getExternalStorageDirectory() + File.separator + com.alipay.android.phone.a.a.a.f813a + File.separator + "data" + File.separator + context.getApplicationInfo().packageName + File.separator + "files" + File.separator + str;
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }

    public static String a(Context context, String str, int i, boolean z) {
        if (context == null) {
            return "";
        }
        String str2 = "";
        try {
            str2 = Environment.getExternalStorageDirectory() + File.separator;
        } catch (Exception e) {
            e.printStackTrace();
        }
        switch (i) {
            case 1:
                if (str2.equals("")) {
                    return str2;
                }
                return str2 + "tencent" + File.separator + "tbs" + File.separator + str;
            case 2:
                if (str2.equals("")) {
                    return str2;
                }
                return str2 + "tbs" + File.separator + "backup" + File.separator + str;
            case 3:
                if (str2.equals("")) {
                    return str2;
                }
                return str2 + "tencent" + File.separator + "tbs" + File.separator + "backup" + File.separator + str;
            case 4:
                if (str2.equals("")) {
                    return a(context, "backup");
                }
                String str3 = str2 + "tencent" + File.separator + "tbs" + File.separator + "backup" + File.separator + str;
                if (!z) {
                    return str3;
                }
                File file = new File(str3);
                if (file.exists() && file.canWrite()) {
                    return str3;
                }
                if (file.exists()) {
                    return a(context, "backup");
                }
                file.mkdirs();
                return !file.canWrite() ? a(context, "backup") : str3;
            case 5:
                if (str2.equals("")) {
                    return str2;
                }
                return str2 + "tencent" + File.separator + "tbs" + File.separator + str;
            case 6:
                if (f9209a != null) {
                    return f9209a;
                }
                f9209a = a(context, "tbslog");
                return f9209a;
            default:
                return "";
        }
    }

    public static FileLock a(Context context, FileOutputStream fileOutputStream) {
        if (fileOutputStream == null) {
            return null;
        }
        try {
            FileLock tryLock = fileOutputStream.getChannel().tryLock();
            if (tryLock.isValid()) {
                return tryLock;
            }
            return null;
        } catch (OverlappingFileLockException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static synchronized void a(Context context, FileLock fileLock) {
        synchronized (j.class) {
            FileChannel channel = fileLock.channel();
            if (channel != null && channel.isOpen()) {
                try {
                    fileLock.release();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return;
    }

    public static void a(File file, boolean z) {
        if (file != null && file.exists()) {
            if (file.isFile()) {
                file.delete();
                return;
            }
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File a2 : listFiles) {
                    a(a2, z);
                }
                if (!z) {
                    file.delete();
                }
            }
        }
    }

    public static void a(File file, boolean z, String str) {
        if (file != null && file.exists()) {
            if (file.isFile()) {
                file.delete();
                return;
            }
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    if (!file2.getName().equals(str)) {
                        a(file2, z);
                    }
                }
                if (!z) {
                    file.delete();
                }
            }
        }
    }

    public static void a(FileLock fileLock, FileOutputStream fileOutputStream) {
        if (fileLock != null) {
            try {
                FileChannel channel = fileLock.channel();
                if (channel != null && channel.isOpen()) {
                    fileLock.release();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (fileOutputStream != null) {
            try {
                fileOutputStream.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static boolean a(Context context) {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        return context != null && context.getApplicationContext().checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }

    public static boolean a(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists() && file.isDirectory()) {
            return true;
        }
        b(file);
        return file.mkdirs();
    }

    public static boolean a(File file, File file2) {
        return a(file.getPath(), file2.getPath());
    }

    public static boolean a(File file, File file2, FileFilter fileFilter) {
        return a(file, file2, fileFilter, b);
    }

    public static boolean a(File file, File file2, FileFilter fileFilter, a aVar) {
        if (file == null || file2 == null || !file.exists()) {
            return false;
        }
        if (file.isFile()) {
            return b(file, file2, fileFilter, aVar);
        }
        File[] listFiles = file.listFiles(fileFilter);
        if (listFiles == null) {
            return false;
        }
        boolean z = true;
        for (File file3 : listFiles) {
            if (!a(file3, new File(file2, file3.getName()), fileFilter)) {
                z = false;
            }
        }
        return z;
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x008d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean a(java.lang.String r5, long r6, long r8, long r10) {
        /*
            java.io.File r8 = new java.io.File
            r8.<init>(r5)
            long r0 = r8.length()
            int r5 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            r9 = 1
            if (r5 == 0) goto L_0x0031
            java.lang.String r5 = "FileHelper"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "file size doesn't match: "
            r10.append(r11)
            long r0 = r8.length()
            r10.append(r0)
            java.lang.String r8 = " vs "
            r10.append(r8)
            r10.append(r6)
            java.lang.String r6 = r10.toString()
            com.tencent.smtt.utils.TbsLog.e(r5, r6)
            return r9
        L_0x0031:
            r5 = 0
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch:{ all -> 0x0087 }
            r6.<init>(r8)     // Catch:{ all -> 0x0087 }
            java.util.zip.CRC32 r5 = new java.util.zip.CRC32     // Catch:{ all -> 0x0085 }
            r5.<init>()     // Catch:{ all -> 0x0085 }
            r7 = 8192(0x2000, float:1.14794E-41)
            byte[] r7 = new byte[r7]     // Catch:{ all -> 0x0085 }
        L_0x0040:
            int r0 = r6.read(r7)     // Catch:{ all -> 0x0085 }
            r1 = 0
            if (r0 <= 0) goto L_0x004b
            r5.update(r7, r1, r0)     // Catch:{ all -> 0x0085 }
            goto L_0x0040
        L_0x004b:
            long r2 = r5.getValue()     // Catch:{ all -> 0x0085 }
            java.lang.String r5 = "FileHelper"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x0085 }
            r7.<init>()     // Catch:{ all -> 0x0085 }
            java.lang.String r0 = ""
            r7.append(r0)     // Catch:{ all -> 0x0085 }
            java.lang.String r8 = r8.getName()     // Catch:{ all -> 0x0085 }
            r7.append(r8)     // Catch:{ all -> 0x0085 }
            java.lang.String r8 = ": crc = "
            r7.append(r8)     // Catch:{ all -> 0x0085 }
            r7.append(r2)     // Catch:{ all -> 0x0085 }
            java.lang.String r8 = ", zipCrc = "
            r7.append(r8)     // Catch:{ all -> 0x0085 }
            r7.append(r10)     // Catch:{ all -> 0x0085 }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x0085 }
            com.tencent.smtt.utils.TbsLog.i(r5, r7)     // Catch:{ all -> 0x0085 }
            int r5 = (r2 > r10 ? 1 : (r2 == r10 ? 0 : -1))
            if (r5 == 0) goto L_0x0081
            r6.close()
            return r9
        L_0x0081:
            r6.close()
            return r1
        L_0x0085:
            r5 = move-exception
            goto L_0x008b
        L_0x0087:
            r6 = move-exception
            r4 = r6
            r6 = r5
            r5 = r4
        L_0x008b:
            if (r6 == 0) goto L_0x0090
            r6.close()
        L_0x0090:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.j.a(java.lang.String, long, long, long):boolean");
    }

    @SuppressLint({"InlinedApi"})
    public static boolean a(String str, String str2) {
        return a(str, str2, Build.CPU_ABI, Build.VERSION.SDK_INT >= 8 ? Build.CPU_ABI2 : null, p.a("ro.product.cpu.upgradeabi", "armeabi"));
    }

    /* JADX WARNING: Removed duplicated region for block: B:63:0x00d9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean a(java.lang.String r11, java.lang.String r12, java.lang.String r13, java.lang.String r14, com.tencent.smtt.utils.j.b r15) {
        /*
            r0 = 0
            java.util.zip.ZipFile r1 = new java.util.zip.ZipFile     // Catch:{ all -> 0x00d5 }
            r1.<init>(r11)     // Catch:{ all -> 0x00d5 }
            java.util.Enumeration r11 = r1.entries()     // Catch:{ all -> 0x00d3 }
            r0 = 1
            r2 = 0
            r3 = 0
            r4 = 0
        L_0x000e:
            boolean r5 = r11.hasMoreElements()     // Catch:{ all -> 0x00d3 }
            if (r5 == 0) goto L_0x00cf
            java.lang.Object r5 = r11.nextElement()     // Catch:{ all -> 0x00d3 }
            java.util.zip.ZipEntry r5 = (java.util.zip.ZipEntry) r5     // Catch:{ all -> 0x00d3 }
            java.lang.String r6 = r5.getName()     // Catch:{ all -> 0x00d3 }
            if (r6 != 0) goto L_0x0021
            goto L_0x000e
        L_0x0021:
            java.lang.String r7 = "../"
            boolean r7 = r6.contains(r7)     // Catch:{ all -> 0x00d3 }
            if (r7 == 0) goto L_0x002a
            goto L_0x000e
        L_0x002a:
            java.lang.String r7 = "lib/"
            boolean r7 = r6.startsWith(r7)     // Catch:{ all -> 0x00d3 }
            if (r7 != 0) goto L_0x003b
            java.lang.String r7 = "assets/"
            boolean r7 = r6.startsWith(r7)     // Catch:{ all -> 0x00d3 }
            if (r7 != 0) goto L_0x003b
            goto L_0x000e
        L_0x003b:
            r7 = 47
            int r8 = r6.lastIndexOf(r7)     // Catch:{ all -> 0x00d3 }
            java.lang.String r8 = r6.substring(r8)     // Catch:{ all -> 0x00d3 }
            java.lang.String r9 = ".so"
            boolean r9 = r8.endsWith(r9)     // Catch:{ all -> 0x00d3 }
            if (r9 == 0) goto L_0x00aa
            int r9 = c     // Catch:{ all -> 0x00d3 }
            int r10 = r12.length()     // Catch:{ all -> 0x00d3 }
            boolean r9 = r6.regionMatches(r9, r12, r2, r10)     // Catch:{ all -> 0x00d3 }
            if (r9 == 0) goto L_0x0068
            int r9 = c     // Catch:{ all -> 0x00d3 }
            int r10 = r12.length()     // Catch:{ all -> 0x00d3 }
            int r9 = r9 + r10
            char r9 = r6.charAt(r9)     // Catch:{ all -> 0x00d3 }
            if (r9 != r7) goto L_0x0068
            r3 = 1
            goto L_0x00aa
        L_0x0068:
            if (r13 == 0) goto L_0x0089
            int r9 = c     // Catch:{ all -> 0x00d3 }
            int r10 = r13.length()     // Catch:{ all -> 0x00d3 }
            boolean r9 = r6.regionMatches(r9, r13, r2, r10)     // Catch:{ all -> 0x00d3 }
            if (r9 == 0) goto L_0x0089
            int r9 = c     // Catch:{ all -> 0x00d3 }
            int r10 = r13.length()     // Catch:{ all -> 0x00d3 }
            int r9 = r9 + r10
            char r9 = r6.charAt(r9)     // Catch:{ all -> 0x00d3 }
            if (r9 != r7) goto L_0x0089
            if (r3 == 0) goto L_0x0087
            r4 = 1
            goto L_0x000e
        L_0x0087:
            r4 = 1
            goto L_0x00aa
        L_0x0089:
            if (r14 == 0) goto L_0x000e
            int r9 = c     // Catch:{ all -> 0x00d3 }
            int r10 = r14.length()     // Catch:{ all -> 0x00d3 }
            boolean r9 = r6.regionMatches(r9, r14, r2, r10)     // Catch:{ all -> 0x00d3 }
            if (r9 == 0) goto L_0x000e
            int r9 = c     // Catch:{ all -> 0x00d3 }
            int r10 = r14.length()     // Catch:{ all -> 0x00d3 }
            int r9 = r9 + r10
            char r6 = r6.charAt(r9)     // Catch:{ all -> 0x00d3 }
            if (r6 != r7) goto L_0x000e
            if (r3 != 0) goto L_0x000e
            if (r4 == 0) goto L_0x00aa
            goto L_0x000e
        L_0x00aa:
            java.io.InputStream r6 = r1.getInputStream(r5)     // Catch:{ all -> 0x00d3 }
            java.lang.String r7 = r8.substring(r0)     // Catch:{ all -> 0x00c8 }
            boolean r5 = r15.a(r6, r5, r7)     // Catch:{ all -> 0x00c8 }
            if (r5 != 0) goto L_0x00c1
            if (r6 == 0) goto L_0x00bd
            r6.close()     // Catch:{ all -> 0x00d3 }
        L_0x00bd:
            r1.close()
            return r2
        L_0x00c1:
            if (r6 == 0) goto L_0x000e
            r6.close()     // Catch:{ all -> 0x00d3 }
            goto L_0x000e
        L_0x00c8:
            r11 = move-exception
            if (r6 == 0) goto L_0x00ce
            r6.close()     // Catch:{ all -> 0x00d3 }
        L_0x00ce:
            throw r11     // Catch:{ all -> 0x00d3 }
        L_0x00cf:
            r1.close()
            return r0
        L_0x00d3:
            r11 = move-exception
            goto L_0x00d7
        L_0x00d5:
            r11 = move-exception
            r1 = r0
        L_0x00d7:
            if (r1 == 0) goto L_0x00dc
            r1.close()
        L_0x00dc:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.j.a(java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.tencent.smtt.utils.j$b):boolean");
    }

    private static boolean a(String str, String str2, String str3, String str4, String str5) {
        return a(str, str3, str4, str5, (b) new k(str2));
    }

    public static FileOutputStream b(Context context, boolean z, String str) {
        File a2 = a(context, z, str);
        if (a2 == null) {
            return null;
        }
        try {
            return new FileOutputStream(a2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void b(File file) {
        a(file, false);
    }

    public static boolean b(Context context) {
        long a2 = v.a();
        boolean z = a2 >= TbsDownloadConfig.getInstance(context).getDownloadMinFreeSpace();
        if (!z) {
            TbsLog.e(TbsDownloader.LOGTAG, "[TbsApkDwonloader.hasEnoughFreeSpace] freeSpace too small,  freeSpace = " + a2);
        }
        return z;
    }

    public static boolean b(File file, File file2) {
        return a(file, file2, (FileFilter) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:54:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0099  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean b(java.io.File r11, java.io.File r12, java.io.FileFilter r13, com.tencent.smtt.utils.j.a r14) {
        /*
            r0 = 0
            if (r11 == 0) goto L_0x009d
            if (r12 != 0) goto L_0x0007
            goto L_0x009d
        L_0x0007:
            if (r13 == 0) goto L_0x0010
            boolean r13 = r13.accept(r11)
            if (r13 != 0) goto L_0x0010
            return r0
        L_0x0010:
            r13 = 0
            boolean r1 = r11.exists()     // Catch:{ all -> 0x0090 }
            if (r1 == 0) goto L_0x008f
            boolean r1 = r11.isFile()     // Catch:{ all -> 0x0090 }
            if (r1 != 0) goto L_0x001f
            goto L_0x008f
        L_0x001f:
            boolean r1 = r12.exists()     // Catch:{ all -> 0x0090 }
            r2 = 1
            if (r1 == 0) goto L_0x0032
            if (r14 == 0) goto L_0x002f
            boolean r14 = r14.a(r11, r12)     // Catch:{ all -> 0x0090 }
            if (r14 == 0) goto L_0x002f
            return r2
        L_0x002f:
            b((java.io.File) r12)     // Catch:{ all -> 0x0090 }
        L_0x0032:
            java.io.File r14 = r12.getParentFile()     // Catch:{ all -> 0x0090 }
            boolean r1 = r14.isFile()     // Catch:{ all -> 0x0090 }
            if (r1 == 0) goto L_0x003f
            b((java.io.File) r14)     // Catch:{ all -> 0x0090 }
        L_0x003f:
            boolean r1 = r14.exists()     // Catch:{ all -> 0x0090 }
            if (r1 != 0) goto L_0x004c
            boolean r14 = r14.mkdirs()     // Catch:{ all -> 0x0090 }
            if (r14 != 0) goto L_0x004c
            return r0
        L_0x004c:
            java.io.FileInputStream r14 = new java.io.FileInputStream     // Catch:{ all -> 0x0090 }
            r14.<init>(r11)     // Catch:{ all -> 0x0090 }
            java.nio.channels.FileChannel r11 = r14.getChannel()     // Catch:{ all -> 0x0090 }
            java.io.FileOutputStream r14 = new java.io.FileOutputStream     // Catch:{ all -> 0x008a }
            r14.<init>(r12)     // Catch:{ all -> 0x008a }
            java.nio.channels.FileChannel r14 = r14.getChannel()     // Catch:{ all -> 0x008a }
            long r9 = r11.size()     // Catch:{ all -> 0x0088 }
            r5 = 0
            r3 = r14
            r4 = r11
            r7 = r9
            long r3 = r3.transferFrom(r4, r5, r7)     // Catch:{ all -> 0x0088 }
            int r13 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1))
            if (r13 == 0) goto L_0x007d
            b((java.io.File) r12)     // Catch:{ all -> 0x0088 }
            if (r11 == 0) goto L_0x0077
            r11.close()
        L_0x0077:
            if (r14 == 0) goto L_0x007c
            r14.close()
        L_0x007c:
            return r0
        L_0x007d:
            if (r11 == 0) goto L_0x0082
            r11.close()
        L_0x0082:
            if (r14 == 0) goto L_0x0087
            r14.close()
        L_0x0087:
            return r2
        L_0x0088:
            r12 = move-exception
            goto L_0x008c
        L_0x008a:
            r12 = move-exception
            r14 = r13
        L_0x008c:
            r13 = r11
            r11 = r12
            goto L_0x0092
        L_0x008f:
            return r0
        L_0x0090:
            r11 = move-exception
            r14 = r13
        L_0x0092:
            if (r13 == 0) goto L_0x0097
            r13.close()
        L_0x0097:
            if (r14 == 0) goto L_0x009c
            r14.close()
        L_0x009c:
            throw r11
        L_0x009d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.j.b(java.io.File, java.io.File, java.io.FileFilter, com.tencent.smtt.utils.j$a):boolean");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00aa  */
    @android.annotation.SuppressLint({"NewApi"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean b(java.io.InputStream r9, java.util.zip.ZipEntry r10, java.lang.String r11, java.lang.String r12) {
        /*
            java.io.File r0 = new java.io.File
            r0.<init>(r11)
            a((java.io.File) r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r11)
            java.lang.String r11 = java.io.File.separator
            r0.append(r11)
            r0.append(r12)
            java.lang.String r11 = r0.toString()
            java.io.File r12 = new java.io.File
            r12.<init>(r11)
            r0 = 0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x008d }
            r1.<init>(r12)     // Catch:{ IOException -> 0x008d }
            r0 = 8192(0x2000, float:1.14794E-41)
            byte[] r0 = new byte[r0]     // Catch:{ IOException -> 0x0088, all -> 0x0085 }
        L_0x002b:
            int r2 = r9.read(r0)     // Catch:{ IOException -> 0x0088, all -> 0x0085 }
            r8 = 0
            if (r2 <= 0) goto L_0x0036
            r1.write(r0, r8, r2)     // Catch:{ IOException -> 0x0088, all -> 0x0085 }
            goto L_0x002b
        L_0x0036:
            r1.close()
            long r2 = r10.getSize()
            long r4 = r10.getTime()
            long r6 = r10.getCrc()
            r1 = r11
            boolean r9 = a((java.lang.String) r1, (long) r2, (long) r4, (long) r6)
            if (r9 == 0) goto L_0x0063
            java.lang.String r9 = "FileHelper"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r12 = "file is different: "
            r10.append(r12)
            r10.append(r11)
            java.lang.String r10 = r10.toString()
            com.tencent.smtt.utils.TbsLog.e(r9, r10)
            return r8
        L_0x0063:
            long r9 = r10.getTime()
            boolean r9 = r12.setLastModified(r9)
            if (r9 != 0) goto L_0x0083
            java.lang.String r9 = "FileHelper"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "Couldn't set time for dst file "
            r10.append(r11)
            r10.append(r12)
            java.lang.String r10 = r10.toString()
            com.tencent.smtt.utils.TbsLog.e(r9, r10)
        L_0x0083:
            r9 = 1
            return r9
        L_0x0085:
            r9 = move-exception
            r0 = r1
            goto L_0x00a8
        L_0x0088:
            r9 = move-exception
            r0 = r1
            goto L_0x008e
        L_0x008b:
            r9 = move-exception
            goto L_0x00a8
        L_0x008d:
            r9 = move-exception
        L_0x008e:
            b((java.io.File) r12)     // Catch:{ all -> 0x008b }
            java.io.IOException r10 = new java.io.IOException     // Catch:{ all -> 0x008b }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x008b }
            r11.<init>()     // Catch:{ all -> 0x008b }
            java.lang.String r1 = "Couldn't write dst file "
            r11.append(r1)     // Catch:{ all -> 0x008b }
            r11.append(r12)     // Catch:{ all -> 0x008b }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x008b }
            r10.<init>(r11, r9)     // Catch:{ all -> 0x008b }
            throw r10     // Catch:{ all -> 0x008b }
        L_0x00a8:
            if (r0 == 0) goto L_0x00ad
            r0.close()
        L_0x00ad:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.j.b(java.io.InputStream, java.util.zip.ZipEntry, java.lang.String, java.lang.String):boolean");
    }

    public static String c(Context context) {
        return Environment.getExternalStorageDirectory() + File.separator + "tbs" + File.separator + "file_locks";
    }

    public static boolean c(File file) {
        return file != null && file.exists() && file.isFile() && file.length() > 0;
    }

    public static FileOutputStream d(File file) {
        if (!file.exists()) {
            File parentFile = file.getParentFile();
            if (parentFile != null && !parentFile.exists() && !parentFile.mkdirs()) {
                throw new IOException("File '" + file + "' could not be created");
            }
        } else if (file.isDirectory()) {
            throw new IOException("File '" + file + "' exists but is a directory");
        } else if (!file.canWrite()) {
            throw new IOException("File '" + file + "' cannot be written to");
        }
        return new FileOutputStream(file);
    }

    static String d(Context context) {
        File file = new File(context.getDir("tbs", 0), "core_private");
        if (file.isDirectory() || file.mkdir()) {
            return file.getAbsolutePath();
        }
        return null;
    }

    public static FileLock e(Context context) {
        boolean z;
        try {
            z = TbsDownloadConfig.getInstance().getTbsCoreLoadRenameFileLockEnable();
        } catch (Throwable unused) {
            z = true;
        }
        if (!z) {
            FileOutputStream b2 = b(context, true, "tbs_rename_lock");
            if (b2 == null) {
                return null;
            }
            return a(context, b2);
        }
        try {
            d = new RandomAccessFile(a(context, true, "tbs_rename_lock").getAbsolutePath(), "r");
            return d.getChannel().tryLock(0, Long.MAX_VALUE, true);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static FileLock f(Context context) {
        try {
            d = new RandomAccessFile(a(context, true, "tbs_rename_lock").getAbsolutePath(), "rw");
            return d.getChannel().tryLock(0, Long.MAX_VALUE, false);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }
}
