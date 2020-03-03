package com.xiaomi.smarthome.core.server.internal.plugin.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipFileUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final int f14701a = 4096;

    public static boolean a(String str, String str2) {
        return d(str, str2, (String) null);
    }

    public static boolean a(ZipInputStream zipInputStream, String str) {
        while (true) {
            try {
                ZipEntry nextEntry = zipInputStream.getNextEntry();
                if (nextEntry == null) {
                    return true;
                }
                String name = nextEntry.getName();
                if (nextEntry.isDirectory()) {
                    String substring = name.substring(0, name.length() - 1);
                    new File(str + File.separator + substring).mkdirs();
                } else {
                    File file = new File(str + File.separator + name);
                    if (!file.exists()) {
                        file.getParentFile().mkdirs();
                        file.createNewFile();
                    }
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = zipInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                        fileOutputStream.flush();
                    }
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    public static boolean a(String str, String str2, String str3) {
        return d(str, str2, str3);
    }

    @Deprecated
    private static void a(String str, String str2, UnzipLibraryRunnable.UnzipCallBack unzipCallBack) {
        new UnzipLibraryRunnable(str, str2, unzipCallBack).run();
    }

    @Deprecated
    private static void c(String str, String str2, String str3) {
        new UnzipLibraryRunnable(str, str2, str3, (UnzipLibraryRunnable.UnzipCallBack) null).run();
    }

    @Deprecated
    private static void a(String str, String str2, String str3, UnzipLibraryRunnable.UnzipCallBack unzipCallBack) {
        new UnzipLibraryRunnable(str, str2, str3, unzipCallBack).run();
    }

    public static File a(String str) {
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

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0095 A[SYNTHETIC, Splitter:B:33:0x0095] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00a1 A[SYNTHETIC, Splitter:B:42:0x00a1] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00ad A[SYNTHETIC, Splitter:B:51:0x00ad] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean d(java.lang.String r9, java.lang.String r10, java.lang.String r11) {
        /*
            r0 = 0
            r1 = 0
            java.util.zip.ZipFile r2 = new java.util.zip.ZipFile     // Catch:{ FileNotFoundException -> 0x00aa, Exception -> 0x009e, all -> 0x0091 }
            r2.<init>(r9)     // Catch:{ FileNotFoundException -> 0x00aa, Exception -> 0x009e, all -> 0x0091 }
            java.util.Enumeration r9 = r2.entries()     // Catch:{ FileNotFoundException -> 0x00ab, Exception -> 0x009f, all -> 0x008f }
            r1 = 4096(0x1000, float:5.74E-42)
            byte[] r3 = new byte[r1]     // Catch:{ FileNotFoundException -> 0x00ab, Exception -> 0x009f, all -> 0x008f }
        L_0x000f:
            boolean r4 = r9.hasMoreElements()     // Catch:{ FileNotFoundException -> 0x00ab, Exception -> 0x009f, all -> 0x008f }
            if (r4 == 0) goto L_0x0085
            java.lang.Object r4 = r9.nextElement()     // Catch:{ FileNotFoundException -> 0x00ab, Exception -> 0x009f, all -> 0x008f }
            java.util.zip.ZipEntry r4 = (java.util.zip.ZipEntry) r4     // Catch:{ FileNotFoundException -> 0x00ab, Exception -> 0x009f, all -> 0x008f }
            boolean r5 = r4.isDirectory()     // Catch:{ FileNotFoundException -> 0x00ab, Exception -> 0x009f, all -> 0x008f }
            if (r5 == 0) goto L_0x0022
            goto L_0x000f
        L_0x0022:
            java.lang.String r5 = r4.getName()     // Catch:{ FileNotFoundException -> 0x00ab, Exception -> 0x009f, all -> 0x008f }
            r6 = -1
            if (r5 == 0) goto L_0x0039
            java.lang.String r7 = "\\"
            int r7 = r5.indexOf(r7)     // Catch:{ FileNotFoundException -> 0x00ab, Exception -> 0x009f, all -> 0x008f }
            if (r7 == r6) goto L_0x0039
            java.lang.String r7 = "\\\\"
            java.lang.String r8 = java.io.File.separator     // Catch:{ FileNotFoundException -> 0x00ab, Exception -> 0x009f, all -> 0x008f }
            java.lang.String r5 = r5.replaceAll(r7, r8)     // Catch:{ FileNotFoundException -> 0x00ab, Exception -> 0x009f, all -> 0x008f }
        L_0x0039:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x00ab, Exception -> 0x009f, all -> 0x008f }
            r7.<init>()     // Catch:{ FileNotFoundException -> 0x00ab, Exception -> 0x009f, all -> 0x008f }
            r7.append(r10)     // Catch:{ FileNotFoundException -> 0x00ab, Exception -> 0x009f, all -> 0x008f }
            java.lang.String r8 = java.io.File.separator     // Catch:{ FileNotFoundException -> 0x00ab, Exception -> 0x009f, all -> 0x008f }
            r7.append(r8)     // Catch:{ FileNotFoundException -> 0x00ab, Exception -> 0x009f, all -> 0x008f }
            r7.append(r5)     // Catch:{ FileNotFoundException -> 0x00ab, Exception -> 0x009f, all -> 0x008f }
            java.lang.String r5 = r7.toString()     // Catch:{ FileNotFoundException -> 0x00ab, Exception -> 0x009f, all -> 0x008f }
            boolean r7 = android.text.TextUtils.isEmpty(r11)     // Catch:{ FileNotFoundException -> 0x00ab, Exception -> 0x009f, all -> 0x008f }
            if (r7 != 0) goto L_0x005a
            int r7 = r5.indexOf(r11)     // Catch:{ FileNotFoundException -> 0x00ab, Exception -> 0x009f, all -> 0x008f }
            if (r7 != r6) goto L_0x005a
            goto L_0x000f
        L_0x005a:
            java.io.File r5 = a(r5)     // Catch:{ FileNotFoundException -> 0x00ab, Exception -> 0x009f, all -> 0x008f }
            java.io.BufferedOutputStream r7 = new java.io.BufferedOutputStream     // Catch:{ FileNotFoundException -> 0x00ab, Exception -> 0x009f, all -> 0x008f }
            java.io.FileOutputStream r8 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x00ab, Exception -> 0x009f, all -> 0x008f }
            r8.<init>(r5)     // Catch:{ FileNotFoundException -> 0x00ab, Exception -> 0x009f, all -> 0x008f }
            r7.<init>(r8)     // Catch:{ FileNotFoundException -> 0x00ab, Exception -> 0x009f, all -> 0x008f }
            java.io.BufferedInputStream r5 = new java.io.BufferedInputStream     // Catch:{ FileNotFoundException -> 0x00ab, Exception -> 0x009f, all -> 0x008f }
            java.io.InputStream r4 = r2.getInputStream(r4)     // Catch:{ FileNotFoundException -> 0x00ab, Exception -> 0x009f, all -> 0x008f }
            r5.<init>(r4)     // Catch:{ FileNotFoundException -> 0x00ab, Exception -> 0x009f, all -> 0x008f }
        L_0x0071:
            int r4 = r5.read(r3, r0, r1)     // Catch:{ FileNotFoundException -> 0x00ab, Exception -> 0x009f, all -> 0x008f }
            if (r4 == r6) goto L_0x007b
            r7.write(r3, r0, r4)     // Catch:{ FileNotFoundException -> 0x00ab, Exception -> 0x009f, all -> 0x008f }
            goto L_0x0071
        L_0x007b:
            r7.flush()     // Catch:{ FileNotFoundException -> 0x00ab, Exception -> 0x009f, all -> 0x008f }
            r5.close()     // Catch:{ FileNotFoundException -> 0x00ab, Exception -> 0x009f, all -> 0x008f }
            r7.close()     // Catch:{ FileNotFoundException -> 0x00ab, Exception -> 0x009f, all -> 0x008f }
            goto L_0x000f
        L_0x0085:
            r2.close()     // Catch:{ Exception -> 0x0089 }
            goto L_0x008d
        L_0x0089:
            r9 = move-exception
            r9.printStackTrace()
        L_0x008d:
            r9 = 1
            return r9
        L_0x008f:
            r9 = move-exception
            goto L_0x0093
        L_0x0091:
            r9 = move-exception
            r2 = r1
        L_0x0093:
            if (r2 == 0) goto L_0x009d
            r2.close()     // Catch:{ Exception -> 0x0099 }
            goto L_0x009d
        L_0x0099:
            r10 = move-exception
            r10.printStackTrace()
        L_0x009d:
            throw r9
        L_0x009e:
            r2 = r1
        L_0x009f:
            if (r2 == 0) goto L_0x00a9
            r2.close()     // Catch:{ Exception -> 0x00a5 }
            goto L_0x00a9
        L_0x00a5:
            r9 = move-exception
            r9.printStackTrace()
        L_0x00a9:
            return r0
        L_0x00aa:
            r2 = r1
        L_0x00ab:
            if (r2 == 0) goto L_0x00b5
            r2.close()     // Catch:{ Exception -> 0x00b1 }
            goto L_0x00b5
        L_0x00b1:
            r9 = move-exception
            r9.printStackTrace()
        L_0x00b5:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.internal.plugin.util.ZipFileUtils.d(java.lang.String, java.lang.String, java.lang.String):boolean");
    }

    public static class UnzipLibraryRunnable implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        private String f14702a;
        private String b;
        private String c;
        private UnzipCallBack d;

        public interface UnzipCallBack {
            void a();

            void b();
        }

        public UnzipLibraryRunnable(String str, String str2, String str3, UnzipCallBack unzipCallBack) {
            this.f14702a = str;
            this.b = str2;
            this.c = str3;
            this.d = unzipCallBack;
        }

        public UnzipLibraryRunnable(String str, String str2, UnzipCallBack unzipCallBack) {
            this(str, str2, (String) null, unzipCallBack);
        }

        public void run() {
            if (ZipFileUtils.d(this.f14702a, this.b, this.c)) {
                if (this.d != null) {
                    this.d.a();
                }
            } else if (this.d != null) {
                this.d.b();
            }
        }
    }
}
