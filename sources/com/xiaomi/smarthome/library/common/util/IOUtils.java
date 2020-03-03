package com.xiaomi.smarthome.library.common.util;

import android.database.Cursor;
import android.text.TextUtils;
import com.xiaomi.smarthome.download.Constants;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class IOUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final String[] f18674a = {"jpg", "png", "bmp", "gif", "webp"};
    private static final int b = 1024;

    public static void a(ZipOutputStream zipOutputStream, File file, String str, FileFilter fileFilter) throws IOException {
        FileInputStream fileInputStream;
        File[] fileArr;
        String str2;
        if (str == null) {
            str = "";
        }
        FileInputStream fileInputStream2 = null;
        try {
            if (file.isDirectory()) {
                if (fileFilter != null) {
                    fileArr = file.listFiles(fileFilter);
                } else {
                    fileArr = file.listFiles();
                }
                zipOutputStream.putNextEntry(new ZipEntry(str + File.separator));
                if (TextUtils.isEmpty(str)) {
                    str2 = "";
                } else {
                    str2 = str + File.separator;
                }
                for (File file2 : fileArr) {
                    a(zipOutputStream, file2, str2 + file2.getName(), (FileFilter) null);
                }
                File[] listFiles = file.listFiles(new FileFilter() {
                    public boolean accept(File file) {
                        return file.isDirectory();
                    }
                });
                if (listFiles != null) {
                    for (File file3 : listFiles) {
                        a(zipOutputStream, file3, str2 + File.separator + file3.getName(), fileFilter);
                    }
                }
                fileInputStream = null;
            } else {
                if (!TextUtils.isEmpty(str)) {
                    zipOutputStream.putNextEntry(new ZipEntry(str));
                } else {
                    Date date = new Date();
                    zipOutputStream.putNextEntry(new ZipEntry(String.valueOf(date.getTime()) + Constants.n));
                }
                fileInputStream = new FileInputStream(file);
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        zipOutputStream.write(bArr, 0, read);
                    }
                } catch (IOException unused) {
                    fileInputStream2 = fileInputStream;
                    a((InputStream) fileInputStream2);
                } catch (Throwable th) {
                    th = th;
                    fileInputStream2 = fileInputStream;
                    a((InputStream) fileInputStream2);
                    throw th;
                }
            }
            a((InputStream) fileInputStream);
        } catch (IOException unused2) {
            a((InputStream) fileInputStream2);
        } catch (Throwable th2) {
            th = th2;
            a((InputStream) fileInputStream2);
            throw th;
        }
    }

    public static boolean a(List<File> list, String str) {
        ZipOutputStream zipOutputStream = null;
        try {
            ZipOutputStream zipOutputStream2 = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(str)));
            try {
                byte[] bArr = new byte[2048];
                for (File next : list) {
                    if (next.isDirectory()) {
                        for (File file : next.listFiles()) {
                            zipOutputStream2.putNextEntry(new ZipEntry(next.getName() + "/" + file.getName()));
                            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file), 2048);
                            while (true) {
                                int read = bufferedInputStream.read(bArr, 0, 2048);
                                if (read == -1) {
                                    break;
                                }
                                zipOutputStream2.write(bArr, 0, read);
                            }
                            bufferedInputStream.close();
                        }
                    } else {
                        BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(next), 2048);
                        zipOutputStream2.putNextEntry(new ZipEntry(next.getName()));
                        while (true) {
                            int read2 = bufferedInputStream2.read(bArr, 0, 2048);
                            if (read2 == -1) {
                                break;
                            }
                            zipOutputStream2.write(bArr, 0, read2);
                        }
                        bufferedInputStream2.close();
                    }
                }
                zipOutputStream2.close();
                a((OutputStream) zipOutputStream2);
                return true;
            } catch (Exception e) {
                e = e;
                zipOutputStream = zipOutputStream2;
                try {
                    e.printStackTrace();
                    a((OutputStream) zipOutputStream);
                    return false;
                } catch (Throwable th) {
                    th = th;
                    zipOutputStream2 = zipOutputStream;
                    a((OutputStream) zipOutputStream2);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                a((OutputStream) zipOutputStream2);
                throw th;
            }
        } catch (Exception e2) {
            e = e2;
            e.printStackTrace();
            a((OutputStream) zipOutputStream);
            return false;
        }
    }

    public static void b(ZipOutputStream zipOutputStream, File file, String str, FileFilter fileFilter) throws IOException {
        FileInputStream fileInputStream;
        File[] fileArr;
        String str2;
        if (str == null) {
            str = "";
        }
        FileInputStream fileInputStream2 = null;
        try {
            if (file.isDirectory()) {
                if (fileFilter != null) {
                    fileArr = file.listFiles(fileFilter);
                } else {
                    fileArr = file.listFiles();
                }
                zipOutputStream.putNextEntry(new ZipEntry(str + File.separator));
                if (TextUtils.isEmpty(str)) {
                    str2 = "";
                } else {
                    str2 = str + File.separator;
                }
                for (File file2 : fileArr) {
                    a(zipOutputStream, file2, str2 + file2.getName(), (FileFilter) null);
                }
                fileInputStream = null;
            } else {
                if (!TextUtils.isEmpty(str)) {
                    zipOutputStream.putNextEntry(new ZipEntry(str));
                } else {
                    Date date = new Date();
                    zipOutputStream.putNextEntry(new ZipEntry(String.valueOf(date.getTime()) + Constants.n));
                }
                fileInputStream = new FileInputStream(file);
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        zipOutputStream.write(bArr, 0, read);
                    }
                } catch (IOException unused) {
                    fileInputStream2 = fileInputStream;
                    a((InputStream) fileInputStream2);
                } catch (Throwable th) {
                    th = th;
                    fileInputStream2 = fileInputStream;
                    a((InputStream) fileInputStream2);
                    throw th;
                }
            }
            a((InputStream) fileInputStream);
        } catch (IOException unused2) {
            a((InputStream) fileInputStream2);
        } catch (Throwable th2) {
            th = th2;
            a((InputStream) fileInputStream2);
            throw th;
        }
    }

    public static void a(ZipOutputStream zipOutputStream, String str, InputStream inputStream) {
        try {
            if (!TextUtils.isEmpty(str)) {
                zipOutputStream.putNextEntry(new ZipEntry(str));
            } else {
                Date date = new Date();
                zipOutputStream.putNextEntry(new ZipEntry(String.valueOf(date.getTime()) + Constants.n));
            }
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read != -1) {
                    zipOutputStream.write(bArr, 0, read);
                } else {
                    return;
                }
            }
        } catch (IOException unused) {
        }
    }

    public static void a(Reader reader) {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException unused) {
            }
        }
    }

    public static void a(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused) {
            }
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:1|2|3|4|6) */
    /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0005 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(java.io.OutputStream r0) {
        /*
            if (r0 == 0) goto L_0x0008
            r0.flush()     // Catch:{ Exception -> 0x0005 }
        L_0x0005:
            r0.close()     // Catch:{ Exception -> 0x0008 }
        L_0x0008:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.library.common.util.IOUtils.a(java.io.OutputStream):void");
    }

    public static void a(Cursor cursor) {
        if (cursor != null) {
            cursor.close();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x003e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(java.io.File r3, java.io.File r4) throws java.io.IOException {
        /*
            java.lang.String r0 = r3.getAbsolutePath()
            java.lang.String r1 = r4.getAbsolutePath()
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x000f
            return
        L_0x000f:
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ all -> 0x0035 }
            r1.<init>(r3)     // Catch:{ all -> 0x0035 }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ all -> 0x0033 }
            r3.<init>(r4)     // Catch:{ all -> 0x0033 }
            r4 = 1024(0x400, float:1.435E-42)
            byte[] r4 = new byte[r4]     // Catch:{ all -> 0x0030 }
        L_0x001e:
            int r0 = r1.read(r4)     // Catch:{ all -> 0x0030 }
            if (r0 < 0) goto L_0x0029
            r2 = 0
            r3.write(r4, r2, r0)     // Catch:{ all -> 0x0030 }
            goto L_0x001e
        L_0x0029:
            r1.close()
            r3.close()
            return
        L_0x0030:
            r4 = move-exception
            r0 = r3
            goto L_0x0037
        L_0x0033:
            r4 = move-exception
            goto L_0x0037
        L_0x0035:
            r4 = move-exception
            r1 = r0
        L_0x0037:
            if (r1 == 0) goto L_0x003c
            r1.close()
        L_0x003c:
            if (r0 == 0) goto L_0x0041
            r0.close()
        L_0x0041:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.library.common.util.IOUtils.a(java.io.File, java.io.File):void");
    }

    public static boolean a(String str, String str2) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            return false;
        }
        if (!str2.endsWith("/")) {
            str2 = str2 + "/";
        }
        try {
            ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(str)));
            byte[] bArr = new byte[4096];
            while (true) {
                ZipEntry nextEntry = zipInputStream.getNextEntry();
                if (nextEntry != null) {
                    String name = nextEntry.getName();
                    File file = new File(str2 + name);
                    if (!name.endsWith("/")) {
                        File file2 = new File(file.getParent());
                        if (!file2.exists() || !file2.isDirectory()) {
                            file2.mkdirs();
                            a(file2);
                        }
                        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file), 4096);
                        while (true) {
                            int read = zipInputStream.read(bArr, 0, 4096);
                            if (read == -1) {
                                break;
                            }
                            bufferedOutputStream.write(bArr, 0, read);
                        }
                        bufferedOutputStream.flush();
                        bufferedOutputStream.close();
                    }
                } else {
                    zipInputStream.close();
                    return true;
                }
            }
        } catch (IOException unused) {
            return false;
        }
    }

    public static void a(File file) {
        File file2 = new File(file, ".nomedia");
        if (!file2.exists() || !file2.isFile()) {
            try {
                file2.createNewFile();
            } catch (IOException unused) {
            }
        }
    }

    public static byte[] a(String str) throws NoSuchAlgorithmException, IOException {
        MessageDigest instance = MessageDigest.getInstance("SHA1");
        FileInputStream fileInputStream = new FileInputStream(new File(str));
        byte[] bArr = new byte[4096];
        while (true) {
            int read = fileInputStream.read(bArr);
            if (read == -1) {
                return instance.digest();
            }
            instance.update(bArr, 0, read);
        }
    }

    public static void b(File file) {
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length > 0) {
                for (File file2 : listFiles) {
                    if (file2.isFile()) {
                        file2.delete();
                    } else {
                        b(file2);
                    }
                }
            }
            file.delete();
        }
    }

    public static String b(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        return lastIndexOf > 0 ? str.substring(lastIndexOf + 1) : "";
    }

    public static boolean c(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        for (String equalsIgnoreCase : f18674a) {
            if (equalsIgnoreCase.equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }
}
