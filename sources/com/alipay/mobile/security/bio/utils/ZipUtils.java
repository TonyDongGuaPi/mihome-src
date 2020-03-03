package com.alipay.mobile.security.bio.utils;

import java.io.File;

public class ZipUtils {
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: java.util.zip.ZipFile} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: java.io.InputStream} */
    /* JADX WARNING: type inference failed for: r1v0, types: [java.util.zip.ZipFile] */
    /* JADX WARNING: type inference failed for: r1v2 */
    /* JADX WARNING: type inference failed for: r1v4 */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0151, code lost:
        r10 = th;
        r1 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0153, code lost:
        r10 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0154, code lost:
        r0 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:65:0x014c */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0144 A[SYNTHETIC, Splitter:B:59:0x0144] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0149 A[SYNTHETIC, Splitter:B:63:0x0149] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0151 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:3:0x0009] */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x017f A[SYNTHETIC, Splitter:B:80:0x017f] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void unzip(java.lang.String r10, java.lang.String r11) {
        /*
            delAllFile(r10)
            r0 = 0
            java.util.zip.ZipFile r1 = new java.util.zip.ZipFile     // Catch:{ IOException -> 0x0159 }
            r1.<init>(r10)     // Catch:{ IOException -> 0x0159 }
            java.util.Enumeration r10 = r1.entries()     // Catch:{ IOException -> 0x0153, all -> 0x0151 }
            java.io.File r2 = new java.io.File     // Catch:{ IOException -> 0x0153, all -> 0x0151 }
            r2.<init>(r11)     // Catch:{ IOException -> 0x0153, all -> 0x0151 }
            boolean r3 = r2.exists()     // Catch:{ IOException -> 0x0153, all -> 0x0151 }
            if (r3 == 0) goto L_0x001f
            java.lang.String r3 = r2.toString()     // Catch:{ IOException -> 0x0153, all -> 0x0151 }
            delAllFile(r3)     // Catch:{ IOException -> 0x0153, all -> 0x0151 }
        L_0x001f:
            java.lang.String r3 = "unzip mkdirs"
            com.alipay.mobile.security.bio.utils.BioLog.i(r3)     // Catch:{ IOException -> 0x0153, all -> 0x0151 }
            r2.mkdirs()     // Catch:{ IOException -> 0x0153, all -> 0x0151 }
        L_0x0028:
            boolean r2 = r10.hasMoreElements()     // Catch:{ IOException -> 0x0153, all -> 0x0151 }
            if (r2 == 0) goto L_0x014d
            java.lang.Object r2 = r10.nextElement()     // Catch:{ IOException -> 0x0153, all -> 0x0151 }
            java.util.zip.ZipEntry r2 = (java.util.zip.ZipEntry) r2     // Catch:{ IOException -> 0x0153, all -> 0x0151 }
            java.lang.String r3 = r2.getName()     // Catch:{ IOException -> 0x0153, all -> 0x0151 }
            boolean r4 = r2.isDirectory()     // Catch:{ IOException -> 0x011c, all -> 0x0119 }
            r5 = 0
            if (r4 == 0) goto L_0x006d
            java.lang.String r2 = r2.getName()     // Catch:{ IOException -> 0x011c, all -> 0x0119 }
            int r3 = r2.length()     // Catch:{ IOException -> 0x011c, all -> 0x0119 }
            int r3 = r3 + -1
            java.lang.String r2 = r2.substring(r5, r3)     // Catch:{ IOException -> 0x011c, all -> 0x0119 }
            java.io.File r3 = new java.io.File     // Catch:{ IOException -> 0x011c, all -> 0x0119 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x011c, all -> 0x0119 }
            r4.<init>()     // Catch:{ IOException -> 0x011c, all -> 0x0119 }
            r4.append(r11)     // Catch:{ IOException -> 0x011c, all -> 0x0119 }
            java.lang.String r5 = java.io.File.separator     // Catch:{ IOException -> 0x011c, all -> 0x0119 }
            r4.append(r5)     // Catch:{ IOException -> 0x011c, all -> 0x0119 }
            r4.append(r2)     // Catch:{ IOException -> 0x011c, all -> 0x0119 }
            java.lang.String r2 = r4.toString()     // Catch:{ IOException -> 0x011c, all -> 0x0119 }
            r3.<init>(r2)     // Catch:{ IOException -> 0x011c, all -> 0x0119 }
            r3.mkdirs()     // Catch:{ IOException -> 0x011c, all -> 0x0119 }
            r2 = r0
            r4 = r2
            goto L_0x0101
        L_0x006d:
            java.lang.String r4 = "\\"
            int r4 = r3.lastIndexOf(r4)     // Catch:{ IOException -> 0x011c, all -> 0x0119 }
            r6 = -1
            if (r4 == r6) goto L_0x0096
            java.io.File r7 = new java.io.File     // Catch:{ IOException -> 0x011c, all -> 0x0119 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x011c, all -> 0x0119 }
            r8.<init>()     // Catch:{ IOException -> 0x011c, all -> 0x0119 }
            r8.append(r11)     // Catch:{ IOException -> 0x011c, all -> 0x0119 }
            java.lang.String r9 = java.io.File.separator     // Catch:{ IOException -> 0x011c, all -> 0x0119 }
            r8.append(r9)     // Catch:{ IOException -> 0x011c, all -> 0x0119 }
            java.lang.String r4 = r3.substring(r5, r4)     // Catch:{ IOException -> 0x011c, all -> 0x0119 }
            r8.append(r4)     // Catch:{ IOException -> 0x011c, all -> 0x0119 }
            java.lang.String r4 = r8.toString()     // Catch:{ IOException -> 0x011c, all -> 0x0119 }
            r7.<init>(r4)     // Catch:{ IOException -> 0x011c, all -> 0x0119 }
            r7.mkdirs()     // Catch:{ IOException -> 0x011c, all -> 0x0119 }
        L_0x0096:
            java.lang.String r4 = "/"
            int r4 = r3.lastIndexOf(r4)     // Catch:{ IOException -> 0x011c, all -> 0x0119 }
            if (r4 == r6) goto L_0x00be
            java.io.File r7 = new java.io.File     // Catch:{ IOException -> 0x011c, all -> 0x0119 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x011c, all -> 0x0119 }
            r8.<init>()     // Catch:{ IOException -> 0x011c, all -> 0x0119 }
            r8.append(r11)     // Catch:{ IOException -> 0x011c, all -> 0x0119 }
            java.lang.String r9 = java.io.File.separator     // Catch:{ IOException -> 0x011c, all -> 0x0119 }
            r8.append(r9)     // Catch:{ IOException -> 0x011c, all -> 0x0119 }
            java.lang.String r3 = r3.substring(r5, r4)     // Catch:{ IOException -> 0x011c, all -> 0x0119 }
            r8.append(r3)     // Catch:{ IOException -> 0x011c, all -> 0x0119 }
            java.lang.String r3 = r8.toString()     // Catch:{ IOException -> 0x011c, all -> 0x0119 }
            r7.<init>(r3)     // Catch:{ IOException -> 0x011c, all -> 0x0119 }
            r7.mkdirs()     // Catch:{ IOException -> 0x011c, all -> 0x0119 }
        L_0x00be:
            java.io.File r3 = new java.io.File     // Catch:{ IOException -> 0x011c, all -> 0x0119 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x011c, all -> 0x0119 }
            r4.<init>()     // Catch:{ IOException -> 0x011c, all -> 0x0119 }
            r4.append(r11)     // Catch:{ IOException -> 0x011c, all -> 0x0119 }
            java.lang.String r7 = java.io.File.separator     // Catch:{ IOException -> 0x011c, all -> 0x0119 }
            r4.append(r7)     // Catch:{ IOException -> 0x011c, all -> 0x0119 }
            java.lang.String r7 = r2.getName()     // Catch:{ IOException -> 0x011c, all -> 0x0119 }
            r4.append(r7)     // Catch:{ IOException -> 0x011c, all -> 0x0119 }
            java.lang.String r4 = r4.toString()     // Catch:{ IOException -> 0x011c, all -> 0x0119 }
            r3.<init>(r4)     // Catch:{ IOException -> 0x011c, all -> 0x0119 }
            boolean r4 = r3.exists()     // Catch:{ IOException -> 0x011c, all -> 0x0119 }
            if (r4 == 0) goto L_0x00e4
            r3.delete()     // Catch:{ IOException -> 0x011c, all -> 0x0119 }
        L_0x00e4:
            r3.createNewFile()     // Catch:{ IOException -> 0x011c, all -> 0x0119 }
            java.io.InputStream r2 = r1.getInputStream(r2)     // Catch:{ IOException -> 0x011c, all -> 0x0119 }
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0115, all -> 0x0111 }
            r4.<init>(r3)     // Catch:{ IOException -> 0x0115, all -> 0x0111 }
            r3 = 1024(0x400, float:1.435E-42)
            byte[] r3 = new byte[r3]     // Catch:{ IOException -> 0x010f, all -> 0x010d }
        L_0x00f4:
            int r7 = r2.read(r3)     // Catch:{ IOException -> 0x010f, all -> 0x010d }
            if (r7 == r6) goto L_0x00fe
            r4.write(r3, r5, r7)     // Catch:{ IOException -> 0x010f, all -> 0x010d }
            goto L_0x00f4
        L_0x00fe:
            r4.flush()     // Catch:{ IOException -> 0x010f, all -> 0x010d }
        L_0x0101:
            if (r2 == 0) goto L_0x0106
            r2.close()     // Catch:{ IOException -> 0x0106, all -> 0x0151 }
        L_0x0106:
            if (r4 == 0) goto L_0x0028
            r4.close()     // Catch:{ IOException -> 0x0028, all -> 0x0151 }
            goto L_0x0028
        L_0x010d:
            r10 = move-exception
            goto L_0x0113
        L_0x010f:
            r10 = move-exception
            goto L_0x0117
        L_0x0111:
            r10 = move-exception
            r4 = r0
        L_0x0113:
            r0 = r2
            goto L_0x0142
        L_0x0115:
            r10 = move-exception
            r4 = r0
        L_0x0117:
            r0 = r2
            goto L_0x011e
        L_0x0119:
            r10 = move-exception
            r4 = r0
            goto L_0x0142
        L_0x011c:
            r10 = move-exception
            r4 = r0
        L_0x011e:
            java.lang.String r11 = r10.toString()     // Catch:{ all -> 0x0141 }
            com.alipay.mobile.security.bio.utils.BioLog.e((java.lang.String) r11)     // Catch:{ all -> 0x0141 }
            java.io.IOException r11 = new java.io.IOException     // Catch:{ all -> 0x0141 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0141 }
            r2.<init>()     // Catch:{ all -> 0x0141 }
            java.lang.String r3 = "解压失败："
            r2.append(r3)     // Catch:{ all -> 0x0141 }
            java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x0141 }
            r2.append(r10)     // Catch:{ all -> 0x0141 }
            java.lang.String r10 = r2.toString()     // Catch:{ all -> 0x0141 }
            r11.<init>(r10)     // Catch:{ all -> 0x0141 }
            throw r11     // Catch:{ all -> 0x0141 }
        L_0x0141:
            r10 = move-exception
        L_0x0142:
            if (r0 == 0) goto L_0x0147
            r0.close()     // Catch:{ IOException -> 0x0147, all -> 0x0151 }
        L_0x0147:
            if (r4 == 0) goto L_0x014c
            r4.close()     // Catch:{ IOException -> 0x014c, all -> 0x0151 }
        L_0x014c:
            throw r10     // Catch:{ IOException -> 0x0153, all -> 0x0151 }
        L_0x014d:
            r1.close()     // Catch:{ IOException -> 0x0150 }
        L_0x0150:
            return
        L_0x0151:
            r10 = move-exception
            goto L_0x017d
        L_0x0153:
            r10 = move-exception
            r0 = r1
            goto L_0x015a
        L_0x0156:
            r10 = move-exception
            r1 = r0
            goto L_0x017d
        L_0x0159:
            r10 = move-exception
        L_0x015a:
            java.lang.String r11 = r10.toString()     // Catch:{ all -> 0x0156 }
            com.alipay.mobile.security.bio.utils.BioLog.e((java.lang.String) r11)     // Catch:{ all -> 0x0156 }
            java.io.IOException r11 = new java.io.IOException     // Catch:{ all -> 0x0156 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0156 }
            r1.<init>()     // Catch:{ all -> 0x0156 }
            java.lang.String r2 = "解压失败："
            r1.append(r2)     // Catch:{ all -> 0x0156 }
            java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x0156 }
            r1.append(r10)     // Catch:{ all -> 0x0156 }
            java.lang.String r10 = r1.toString()     // Catch:{ all -> 0x0156 }
            r11.<init>(r10)     // Catch:{ all -> 0x0156 }
            throw r11     // Catch:{ all -> 0x0156 }
        L_0x017d:
            if (r1 == 0) goto L_0x0182
            r1.close()     // Catch:{ IOException -> 0x0182 }
        L_0x0182:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.mobile.security.bio.utils.ZipUtils.unzip(java.lang.String, java.lang.String):void");
    }

    public void delFile(String str) {
        try {
            new File(str.toString()).delete();
        } catch (Exception unused) {
        }
    }

    public static void delFolder(String str) {
        try {
            delAllFile(str);
            new File(str.toString()).delete();
        } catch (Exception unused) {
        }
    }

    public static void delAllFile(String str) {
        File file;
        File file2 = new File(str);
        if (file2.exists() && file2.isDirectory()) {
            String[] list = file2.list();
            for (int i = 0; i < list.length; i++) {
                if (str.endsWith(File.separator)) {
                    file = new File(str + list[i]);
                } else {
                    file = new File(str + File.separator + list[i]);
                }
                if (file.isFile()) {
                    file.delete();
                }
                if (file.isDirectory()) {
                    delAllFile(str + "/" + list[i]);
                    delFolder(str + "/" + list[i]);
                }
            }
        }
    }
}
