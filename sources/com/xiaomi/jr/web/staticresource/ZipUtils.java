package com.xiaomi.jr.web.staticresource;

import com.xiaomi.jr.common.utils.Utils;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtils {
    public static boolean a(String str, String str2) {
        File[] listFiles;
        if (str != null) {
            File file = new File(str);
            if (!file.exists() || (listFiles = file.listFiles()) == null) {
                return false;
            }
            ArrayList arrayList = new ArrayList();
            for (File name : listFiles) {
                arrayList.add(name.getName());
            }
            return a((List<String>) arrayList, str, str2, (String) null);
        }
        return false;
    }

    private static boolean a(List<String> list, String str, String str2, String str3) {
        Collections.sort(list);
        try {
            String canonicalPath = new File(str).getCanonicalPath();
            ZipOutputStream zipOutputStream = null;
            try {
                ZipOutputStream zipOutputStream2 = new ZipOutputStream(new FileOutputStream(str2));
                try {
                    zipOutputStream2.setLevel(9);
                    for (String a2 : list) {
                        a(a2, canonicalPath, zipOutputStream2, str3);
                    }
                    Utils.a((Closeable) zipOutputStream2);
                    return true;
                } catch (Exception e) {
                    e = e;
                    zipOutputStream = zipOutputStream2;
                    try {
                        e.printStackTrace();
                        Utils.a((Closeable) zipOutputStream);
                        return false;
                    } catch (Throwable th) {
                        th = th;
                        zipOutputStream2 = zipOutputStream;
                        Utils.a((Closeable) zipOutputStream2);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    Utils.a((Closeable) zipOutputStream2);
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                Utils.a((Closeable) zipOutputStream);
                return false;
            }
        } catch (IOException e3) {
            e3.printStackTrace();
            return false;
        }
    }

    private static void a(String str, String str2, ZipOutputStream zipOutputStream, String str3) {
        String str4 = str2 + File.separator + str;
        File file = new File(str4);
        if (file.isDirectory()) {
            String[] list = file.list();
            if (list != null) {
                Arrays.sort(list);
                for (String str5 : list) {
                    a(str + File.separator + str5, str2, zipOutputStream, str3);
                }
                return;
            }
            return;
        }
        byte[] bArr = new byte[1024];
        FileInputStream fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(str4);
            try {
                if (str3 != null) {
                    str = str3 + File.separator + str;
                }
                ZipEntry zipEntry = new ZipEntry(str);
                zipEntry.setTime(0);
                zipOutputStream.putNextEntry(zipEntry);
                while (true) {
                    int read = fileInputStream2.read(bArr);
                    if (read > 0) {
                        zipOutputStream.write(bArr, 0, read);
                    } else {
                        Utils.a((Closeable) fileInputStream2);
                        return;
                    }
                }
            } catch (IOException e) {
                e = e;
                fileInputStream = fileInputStream2;
                try {
                    e.printStackTrace();
                    Utils.a((Closeable) fileInputStream);
                } catch (Throwable th) {
                    th = th;
                    fileInputStream2 = fileInputStream;
                    Utils.a((Closeable) fileInputStream2);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                Utils.a((Closeable) fileInputStream2);
                throw th;
            }
        } catch (IOException e2) {
            e = e2;
            e.printStackTrace();
            Utils.a((Closeable) fileInputStream);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v0, resolved type: java.util.zip.ZipFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v1, resolved type: java.util.zip.ZipFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v2, resolved type: java.util.zip.ZipFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v3, resolved type: java.io.BufferedOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v4, resolved type: java.util.zip.ZipFile} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0085 A[Catch:{ Exception -> 0x00a3, all -> 0x00a1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00af A[SYNTHETIC, Splitter:B:56:0x00af] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00ba A[SYNTHETIC, Splitter:B:62:0x00ba] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0012 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(java.io.File r12, java.io.File r13) {
        /*
            r0 = 0
            r1 = 0
            java.util.zip.ZipFile r2 = new java.util.zip.ZipFile     // Catch:{ Exception -> 0x00a9 }
            r2.<init>(r12)     // Catch:{ Exception -> 0x00a9 }
            java.io.File r12 = r13.getParentFile()     // Catch:{ Exception -> 0x00a3, all -> 0x00a1 }
            r12.mkdirs()     // Catch:{ Exception -> 0x00a3, all -> 0x00a1 }
            java.util.Enumeration r12 = r2.entries()     // Catch:{ Exception -> 0x00a3, all -> 0x00a1 }
        L_0x0012:
            boolean r3 = r12.hasMoreElements()     // Catch:{ Exception -> 0x00a3, all -> 0x00a1 }
            if (r3 == 0) goto L_0x0097
            java.lang.Object r3 = r12.nextElement()     // Catch:{ Exception -> 0x00a3, all -> 0x00a1 }
            java.util.zip.ZipEntry r3 = (java.util.zip.ZipEntry) r3     // Catch:{ Exception -> 0x00a3, all -> 0x00a1 }
            java.lang.String r4 = r3.getName()     // Catch:{ Exception -> 0x00a3, all -> 0x00a1 }
            java.io.File r5 = new java.io.File     // Catch:{ Exception -> 0x00a3, all -> 0x00a1 }
            r5.<init>(r13, r4)     // Catch:{ Exception -> 0x00a3, all -> 0x00a1 }
            java.io.File r6 = r5.getParentFile()     // Catch:{ Exception -> 0x00a3, all -> 0x00a1 }
            r6.mkdirs()     // Catch:{ Exception -> 0x00a3, all -> 0x00a1 }
            boolean r7 = r3.isDirectory()     // Catch:{ Exception -> 0x00a3, all -> 0x00a1 }
            if (r7 != 0) goto L_0x007a
            java.io.BufferedInputStream r7 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x0067, all -> 0x0064 }
            java.io.InputStream r3 = r2.getInputStream(r3)     // Catch:{ Exception -> 0x0067, all -> 0x0064 }
            r7.<init>(r3)     // Catch:{ Exception -> 0x0067, all -> 0x0064 }
            r3 = 1024(0x400, float:1.435E-42)
            byte[] r8 = new byte[r3]     // Catch:{ Exception -> 0x0061, all -> 0x005f }
            java.io.FileOutputStream r9 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x0061, all -> 0x005f }
            r9.<init>(r5)     // Catch:{ Exception -> 0x0061, all -> 0x005f }
            java.io.BufferedOutputStream r10 = new java.io.BufferedOutputStream     // Catch:{ Exception -> 0x0061, all -> 0x005f }
            r10.<init>(r9, r3)     // Catch:{ Exception -> 0x0061, all -> 0x005f }
        L_0x004b:
            int r9 = r7.read(r8, r0, r3)     // Catch:{ Exception -> 0x005d }
            r11 = -1
            if (r9 == r11) goto L_0x0056
            r10.write(r8, r0, r9)     // Catch:{ Exception -> 0x005d }
            goto L_0x004b
        L_0x0056:
            com.xiaomi.jr.common.utils.Utils.a((java.io.Closeable) r10)     // Catch:{ Exception -> 0x00a3, all -> 0x00a1 }
        L_0x0059:
            com.xiaomi.jr.common.utils.Utils.a((java.io.Closeable) r7)     // Catch:{ Exception -> 0x00a3, all -> 0x00a1 }
            goto L_0x007d
        L_0x005d:
            r3 = move-exception
            goto L_0x006a
        L_0x005f:
            r12 = move-exception
            goto L_0x0073
        L_0x0061:
            r3 = move-exception
            r10 = r1
            goto L_0x006a
        L_0x0064:
            r12 = move-exception
            r7 = r1
            goto L_0x0073
        L_0x0067:
            r3 = move-exception
            r7 = r1
            r10 = r7
        L_0x006a:
            r3.printStackTrace()     // Catch:{ all -> 0x0071 }
            com.xiaomi.jr.common.utils.Utils.a((java.io.Closeable) r10)     // Catch:{ Exception -> 0x00a3, all -> 0x00a1 }
            goto L_0x0059
        L_0x0071:
            r12 = move-exception
            r1 = r10
        L_0x0073:
            com.xiaomi.jr.common.utils.Utils.a((java.io.Closeable) r1)     // Catch:{ Exception -> 0x00a3, all -> 0x00a1 }
            com.xiaomi.jr.common.utils.Utils.a((java.io.Closeable) r7)     // Catch:{ Exception -> 0x00a3, all -> 0x00a1 }
            throw r12     // Catch:{ Exception -> 0x00a3, all -> 0x00a1 }
        L_0x007a:
            r5.mkdirs()     // Catch:{ Exception -> 0x00a3, all -> 0x00a1 }
        L_0x007d:
            java.lang.String r3 = ".zip"
            boolean r3 = r4.endsWith(r3)     // Catch:{ Exception -> 0x00a3, all -> 0x00a1 }
            if (r3 == 0) goto L_0x0012
            a((java.io.File) r5, (java.io.File) r6)     // Catch:{ Exception -> 0x00a3, all -> 0x00a1 }
            boolean r3 = r5.delete()     // Catch:{ Exception -> 0x00a3, all -> 0x00a1 }
            if (r3 != 0) goto L_0x0012
            java.io.PrintStream r3 = java.lang.System.out     // Catch:{ Exception -> 0x00a3, all -> 0x00a1 }
            java.lang.String r4 = "Could not delete zip"
            r3.println(r4)     // Catch:{ Exception -> 0x00a3, all -> 0x00a1 }
            goto L_0x0012
        L_0x0097:
            r2.close()     // Catch:{ IOException -> 0x009b }
            goto L_0x009f
        L_0x009b:
            r12 = move-exception
            r12.printStackTrace()
        L_0x009f:
            r12 = 1
            return r12
        L_0x00a1:
            r12 = move-exception
            goto L_0x00b8
        L_0x00a3:
            r12 = move-exception
            r1 = r2
            goto L_0x00aa
        L_0x00a6:
            r12 = move-exception
            r2 = r1
            goto L_0x00b8
        L_0x00a9:
            r12 = move-exception
        L_0x00aa:
            r12.printStackTrace()     // Catch:{ all -> 0x00a6 }
            if (r1 == 0) goto L_0x00b7
            r1.close()     // Catch:{ IOException -> 0x00b3 }
            goto L_0x00b7
        L_0x00b3:
            r12 = move-exception
            r12.printStackTrace()
        L_0x00b7:
            return r0
        L_0x00b8:
            if (r2 == 0) goto L_0x00c2
            r2.close()     // Catch:{ IOException -> 0x00be }
            goto L_0x00c2
        L_0x00be:
            r13 = move-exception
            r13.printStackTrace()
        L_0x00c2:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.jr.web.staticresource.ZipUtils.a(java.io.File, java.io.File):boolean");
    }
}
