package com.xiaomi.youpin.common.util;

import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public final class ZipUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final int f23288a = 8192;

    private ZipUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static boolean a(Collection<String> collection, String str) throws IOException {
        return a(collection, str, (String) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0042  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(java.util.Collection<java.lang.String> r4, java.lang.String r5, java.lang.String r6) throws java.io.IOException {
        /*
            r0 = 0
            if (r4 == 0) goto L_0x0049
            if (r5 != 0) goto L_0x0006
            goto L_0x0049
        L_0x0006:
            r1 = 0
            java.util.zip.ZipOutputStream r2 = new java.util.zip.ZipOutputStream     // Catch:{ all -> 0x003e }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ all -> 0x003e }
            r3.<init>(r5)     // Catch:{ all -> 0x003e }
            r2.<init>(r3)     // Catch:{ all -> 0x003e }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ all -> 0x003c }
        L_0x0015:
            boolean r5 = r4.hasNext()     // Catch:{ all -> 0x003c }
            if (r5 == 0) goto L_0x0034
            java.lang.Object r5 = r4.next()     // Catch:{ all -> 0x003c }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ all -> 0x003c }
            java.io.File r5 = c((java.lang.String) r5)     // Catch:{ all -> 0x003c }
            java.lang.String r1 = ""
            boolean r5 = a(r5, r1, r2, r6)     // Catch:{ all -> 0x003c }
            if (r5 != 0) goto L_0x0015
            r2.finish()
            r2.close()
            return r0
        L_0x0034:
            r4 = 1
            r2.finish()
            r2.close()
            return r4
        L_0x003c:
            r4 = move-exception
            goto L_0x0040
        L_0x003e:
            r4 = move-exception
            r2 = r1
        L_0x0040:
            if (r2 == 0) goto L_0x0048
            r2.finish()
            r2.close()
        L_0x0048:
            throw r4
        L_0x0049:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.common.util.ZipUtils.a(java.util.Collection, java.lang.String, java.lang.String):boolean");
    }

    public static boolean a(Collection<File> collection, File file) throws IOException {
        return a(collection, file, (String) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x003e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(java.util.Collection<java.io.File> r4, java.io.File r5, java.lang.String r6) throws java.io.IOException {
        /*
            r0 = 0
            if (r4 == 0) goto L_0x0045
            if (r5 != 0) goto L_0x0006
            goto L_0x0045
        L_0x0006:
            r1 = 0
            java.util.zip.ZipOutputStream r2 = new java.util.zip.ZipOutputStream     // Catch:{ all -> 0x003a }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ all -> 0x003a }
            r3.<init>(r5)     // Catch:{ all -> 0x003a }
            r2.<init>(r3)     // Catch:{ all -> 0x003a }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ all -> 0x0038 }
        L_0x0015:
            boolean r5 = r4.hasNext()     // Catch:{ all -> 0x0038 }
            if (r5 == 0) goto L_0x0030
            java.lang.Object r5 = r4.next()     // Catch:{ all -> 0x0038 }
            java.io.File r5 = (java.io.File) r5     // Catch:{ all -> 0x0038 }
            java.lang.String r1 = ""
            boolean r5 = a(r5, r1, r2, r6)     // Catch:{ all -> 0x0038 }
            if (r5 != 0) goto L_0x0015
            r2.finish()
            r2.close()
            return r0
        L_0x0030:
            r4 = 1
            r2.finish()
            r2.close()
            return r4
        L_0x0038:
            r4 = move-exception
            goto L_0x003c
        L_0x003a:
            r4 = move-exception
            r2 = r1
        L_0x003c:
            if (r2 == 0) goto L_0x0044
            r2.finish()
            r2.close()
        L_0x0044:
            throw r4
        L_0x0045:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.common.util.ZipUtils.a(java.util.Collection, java.io.File, java.lang.String):boolean");
    }

    public static boolean a(String str, String str2) throws IOException {
        return a(c(str), c(str2), (String) null);
    }

    public static boolean a(String str, String str2, String str3) throws IOException {
        return a(c(str), c(str2), str3);
    }

    public static boolean a(File file, File file2) throws IOException {
        return a(file, file2, (String) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0020  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(java.io.File r3, java.io.File r4, java.lang.String r5) throws java.io.IOException {
        /*
            if (r3 == 0) goto L_0x0024
            if (r4 != 0) goto L_0x0005
            goto L_0x0024
        L_0x0005:
            r0 = 0
            java.util.zip.ZipOutputStream r1 = new java.util.zip.ZipOutputStream     // Catch:{ all -> 0x001d }
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ all -> 0x001d }
            r2.<init>(r4)     // Catch:{ all -> 0x001d }
            r1.<init>(r2)     // Catch:{ all -> 0x001d }
            java.lang.String r4 = ""
            boolean r3 = a(r3, r4, r1, r5)     // Catch:{ all -> 0x001a }
            r1.close()
            return r3
        L_0x001a:
            r3 = move-exception
            r0 = r1
            goto L_0x001e
        L_0x001d:
            r3 = move-exception
        L_0x001e:
            if (r0 == 0) goto L_0x0023
            r0.close()
        L_0x0023:
            throw r3
        L_0x0024:
            r3 = 0
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.common.util.ZipUtils.a(java.io.File, java.io.File, java.lang.String):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0095  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean a(java.io.File r4, java.lang.String r5, java.util.zip.ZipOutputStream r6, java.lang.String r7) throws java.io.IOException {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r5)
            boolean r5 = d((java.lang.String) r5)
            if (r5 == 0) goto L_0x0011
            java.lang.String r5 = ""
            goto L_0x0013
        L_0x0011:
            java.lang.String r5 = java.io.File.separator
        L_0x0013:
            r0.append(r5)
            java.lang.String r5 = r4.getName()
            r0.append(r5)
            java.lang.String r5 = r0.toString()
            boolean r0 = r4.isDirectory()
            r1 = 0
            if (r0 == 0) goto L_0x0062
            java.io.File[] r4 = r4.listFiles()
            if (r4 == 0) goto L_0x0042
            int r0 = r4.length
            if (r0 > 0) goto L_0x0032
            goto L_0x0042
        L_0x0032:
            int r0 = r4.length
            r2 = 0
        L_0x0034:
            if (r2 >= r0) goto L_0x008d
            r3 = r4[r2]
            boolean r3 = a(r3, r5, r6, r7)
            if (r3 != 0) goto L_0x003f
            return r1
        L_0x003f:
            int r2 = r2 + 1
            goto L_0x0034
        L_0x0042:
            java.util.zip.ZipEntry r4 = new java.util.zip.ZipEntry
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r5)
            r5 = 47
            r0.append(r5)
            java.lang.String r5 = r0.toString()
            r4.<init>(r5)
            r4.setComment(r7)
            r6.putNextEntry(r4)
            r6.closeEntry()
            goto L_0x008d
        L_0x0062:
            r0 = 0
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ all -> 0x0091 }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ all -> 0x0091 }
            r3.<init>(r4)     // Catch:{ all -> 0x0091 }
            r2.<init>(r3)     // Catch:{ all -> 0x0091 }
            java.util.zip.ZipEntry r4 = new java.util.zip.ZipEntry     // Catch:{ all -> 0x008f }
            r4.<init>(r5)     // Catch:{ all -> 0x008f }
            r4.setComment(r7)     // Catch:{ all -> 0x008f }
            r6.putNextEntry(r4)     // Catch:{ all -> 0x008f }
            r4 = 8192(0x2000, float:1.14794E-41)
            byte[] r5 = new byte[r4]     // Catch:{ all -> 0x008f }
        L_0x007c:
            int r7 = r2.read(r5, r1, r4)     // Catch:{ all -> 0x008f }
            r0 = -1
            if (r7 == r0) goto L_0x0087
            r6.write(r5, r1, r7)     // Catch:{ all -> 0x008f }
            goto L_0x007c
        L_0x0087:
            r6.closeEntry()     // Catch:{ all -> 0x008f }
            r2.close()
        L_0x008d:
            r4 = 1
            return r4
        L_0x008f:
            r4 = move-exception
            goto L_0x0093
        L_0x0091:
            r4 = move-exception
            r2 = r0
        L_0x0093:
            if (r2 == 0) goto L_0x0098
            r2.close()
        L_0x0098:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.common.util.ZipUtils.a(java.io.File, java.lang.String, java.util.zip.ZipOutputStream, java.lang.String):boolean");
    }

    public static List<File> b(String str, String str2) throws IOException {
        return b(str, str2, (String) null);
    }

    public static List<File> b(File file, File file2) throws IOException {
        return b(file, file2, (String) null);
    }

    public static List<File> b(String str, String str2, String str3) throws IOException {
        return b(c(str), c(str2), str3);
    }

    public static List<File> b(File file, File file2, String str) throws IOException {
        if (file == null || file2 == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ZipFile zipFile = new ZipFile(file);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        if (d(str)) {
            while (entries.hasMoreElements()) {
                ZipEntry zipEntry = (ZipEntry) entries.nextElement();
                String name = zipEntry.getName();
                if (name.contains("../")) {
                    Log.e("ZipUtils", "it's dangerous!");
                    return arrayList;
                } else if (!a(file2, arrayList, zipFile, zipEntry, name)) {
                    return arrayList;
                }
            }
        } else {
            while (entries.hasMoreElements()) {
                ZipEntry zipEntry2 = (ZipEntry) entries.nextElement();
                String name2 = zipEntry2.getName();
                if (name2.contains("../")) {
                    Log.e("ZipUtils", "it's dangerous!");
                    return arrayList;
                } else if (name2.contains(str) && !a(file2, arrayList, zipFile, zipEntry2, name2)) {
                    return arrayList;
                }
            }
        }
        return arrayList;
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x006b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean a(java.io.File r1, java.util.List<java.io.File> r2, java.util.zip.ZipFile r3, java.util.zip.ZipEntry r4, java.lang.String r5) throws java.io.IOException {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r1)
            java.lang.String r1 = java.io.File.separator
            r0.append(r1)
            r0.append(r5)
            java.lang.String r1 = r0.toString()
            java.io.File r5 = new java.io.File
            r5.<init>(r1)
            r2.add(r5)
            boolean r1 = r4.isDirectory()
            r2 = 0
            if (r1 == 0) goto L_0x002a
            boolean r1 = c((java.io.File) r5)
            if (r1 != 0) goto L_0x005a
            return r2
        L_0x002a:
            boolean r1 = d((java.io.File) r5)
            if (r1 != 0) goto L_0x0031
            return r2
        L_0x0031:
            r1 = 0
            java.io.BufferedInputStream r0 = new java.io.BufferedInputStream     // Catch:{ all -> 0x0062 }
            java.io.InputStream r3 = r3.getInputStream(r4)     // Catch:{ all -> 0x0062 }
            r0.<init>(r3)     // Catch:{ all -> 0x0062 }
            java.io.BufferedOutputStream r3 = new java.io.BufferedOutputStream     // Catch:{ all -> 0x0060 }
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch:{ all -> 0x0060 }
            r4.<init>(r5)     // Catch:{ all -> 0x0060 }
            r3.<init>(r4)     // Catch:{ all -> 0x0060 }
            r1 = 8192(0x2000, float:1.14794E-41)
            byte[] r1 = new byte[r1]     // Catch:{ all -> 0x005c }
        L_0x0049:
            int r4 = r0.read(r1)     // Catch:{ all -> 0x005c }
            r5 = -1
            if (r4 == r5) goto L_0x0054
            r3.write(r1, r2, r4)     // Catch:{ all -> 0x005c }
            goto L_0x0049
        L_0x0054:
            r0.close()
            r3.close()
        L_0x005a:
            r1 = 1
            return r1
        L_0x005c:
            r1 = move-exception
            r2 = r1
            r1 = r3
            goto L_0x0064
        L_0x0060:
            r2 = move-exception
            goto L_0x0064
        L_0x0062:
            r2 = move-exception
            r0 = r1
        L_0x0064:
            if (r0 == 0) goto L_0x0069
            r0.close()
        L_0x0069:
            if (r1 == 0) goto L_0x006e
            r1.close()
        L_0x006e:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.common.util.ZipUtils.a(java.io.File, java.util.List, java.util.zip.ZipFile, java.util.zip.ZipEntry, java.lang.String):boolean");
    }

    public static List<String> a(String str) throws IOException {
        return a(c(str));
    }

    public static List<String> a(File file) throws IOException {
        if (file == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Enumeration<? extends ZipEntry> entries = new ZipFile(file).entries();
        while (entries.hasMoreElements()) {
            arrayList.add(((ZipEntry) entries.nextElement()).getName());
        }
        return arrayList;
    }

    public static List<String> b(String str) throws IOException {
        return b(c(str));
    }

    public static List<String> b(File file) throws IOException {
        if (file == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Enumeration<? extends ZipEntry> entries = new ZipFile(file).entries();
        while (entries.hasMoreElements()) {
            arrayList.add(((ZipEntry) entries.nextElement()).getComment());
        }
        return arrayList;
    }

    private static boolean c(File file) {
        return file != null && (!file.exists() ? file.mkdirs() : file.isDirectory());
    }

    private static boolean d(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            return file.isFile();
        }
        if (!c(file.getParentFile())) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static File c(String str) {
        if (d(str)) {
            return null;
        }
        return new File(str);
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
}
