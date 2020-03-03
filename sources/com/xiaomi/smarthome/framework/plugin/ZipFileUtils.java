package com.xiaomi.smarthome.framework.plugin;

import java.io.File;
import java.io.IOException;

public class ZipFileUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final int f17198a = 4096;

    public interface TranslateName {
        String a(String str);
    }

    public static boolean a(String str, String str2, TranslateName translateName) {
        return b(str, str2, (String) null, translateName);
    }

    public static boolean a(String str, String str2) {
        return b(str, str2, (String) null, (TranslateName) null);
    }

    @Deprecated
    private static void a(String str, String str2, UnzipLibraryRunnable.UnzipCallBack unzipCallBack) {
        new UnzipLibraryRunnable(str, str2, unzipCallBack).run();
    }

    @Deprecated
    private static void a(String str, String str2, String str3) {
        new UnzipLibraryRunnable(str, str2, str3, (UnzipLibraryRunnable.UnzipCallBack) null).run();
    }

    @Deprecated
    private static void a(String str, String str2, String str3, UnzipLibraryRunnable.UnzipCallBack unzipCallBack) {
        new UnzipLibraryRunnable(str, str2, str3, unzipCallBack).run();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00a4 A[SYNTHETIC, Splitter:B:42:0x00a4] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00af A[SYNTHETIC, Splitter:B:48:0x00af] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00bb A[SYNTHETIC, Splitter:B:57:0x00bb] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean b(java.lang.String r9, java.lang.String r10, java.lang.String r11, com.xiaomi.smarthome.framework.plugin.ZipFileUtils.TranslateName r12) {
        /*
            r0 = 0
            r1 = 0
            java.util.zip.ZipFile r2 = new java.util.zip.ZipFile     // Catch:{ FileNotFoundException -> 0x00b8, Throwable -> 0x009e }
            r2.<init>(r9)     // Catch:{ FileNotFoundException -> 0x00b8, Throwable -> 0x009e }
            java.util.Enumeration r9 = r2.entries()     // Catch:{ FileNotFoundException -> 0x00b9, Throwable -> 0x0098, all -> 0x0096 }
            r1 = 4096(0x1000, float:5.74E-42)
            byte[] r3 = new byte[r1]     // Catch:{ FileNotFoundException -> 0x00b9, Throwable -> 0x0098, all -> 0x0096 }
        L_0x000f:
            boolean r4 = r9.hasMoreElements()     // Catch:{ FileNotFoundException -> 0x00b9, Throwable -> 0x0098, all -> 0x0096 }
            if (r4 == 0) goto L_0x008c
            java.lang.Object r4 = r9.nextElement()     // Catch:{ FileNotFoundException -> 0x00b9, Throwable -> 0x0098, all -> 0x0096 }
            java.util.zip.ZipEntry r4 = (java.util.zip.ZipEntry) r4     // Catch:{ FileNotFoundException -> 0x00b9, Throwable -> 0x0098, all -> 0x0096 }
            boolean r5 = r4.isDirectory()     // Catch:{ FileNotFoundException -> 0x00b9, Throwable -> 0x0098, all -> 0x0096 }
            if (r5 == 0) goto L_0x0022
            goto L_0x000f
        L_0x0022:
            java.lang.String r5 = r4.getName()     // Catch:{ FileNotFoundException -> 0x00b9, Throwable -> 0x0098, all -> 0x0096 }
            r6 = -1
            if (r5 == 0) goto L_0x0039
            java.lang.String r7 = "\\"
            int r7 = r5.indexOf(r7)     // Catch:{ FileNotFoundException -> 0x00b9, Throwable -> 0x0098, all -> 0x0096 }
            if (r7 == r6) goto L_0x0039
            java.lang.String r7 = "\\\\"
            java.lang.String r8 = java.io.File.separator     // Catch:{ FileNotFoundException -> 0x00b9, Throwable -> 0x0098, all -> 0x0096 }
            java.lang.String r5 = r5.replaceAll(r7, r8)     // Catch:{ FileNotFoundException -> 0x00b9, Throwable -> 0x0098, all -> 0x0096 }
        L_0x0039:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x00b9, Throwable -> 0x0098, all -> 0x0096 }
            r7.<init>()     // Catch:{ FileNotFoundException -> 0x00b9, Throwable -> 0x0098, all -> 0x0096 }
            r7.append(r10)     // Catch:{ FileNotFoundException -> 0x00b9, Throwable -> 0x0098, all -> 0x0096 }
            java.lang.String r8 = java.io.File.separator     // Catch:{ FileNotFoundException -> 0x00b9, Throwable -> 0x0098, all -> 0x0096 }
            r7.append(r8)     // Catch:{ FileNotFoundException -> 0x00b9, Throwable -> 0x0098, all -> 0x0096 }
            if (r12 != 0) goto L_0x0049
            goto L_0x004d
        L_0x0049:
            java.lang.String r5 = r12.a(r5)     // Catch:{ FileNotFoundException -> 0x00b9, Throwable -> 0x0098, all -> 0x0096 }
        L_0x004d:
            r7.append(r5)     // Catch:{ FileNotFoundException -> 0x00b9, Throwable -> 0x0098, all -> 0x0096 }
            java.lang.String r5 = r7.toString()     // Catch:{ FileNotFoundException -> 0x00b9, Throwable -> 0x0098, all -> 0x0096 }
            boolean r7 = android.text.TextUtils.isEmpty(r11)     // Catch:{ FileNotFoundException -> 0x00b9, Throwable -> 0x0098, all -> 0x0096 }
            if (r7 != 0) goto L_0x0061
            int r7 = r5.indexOf(r11)     // Catch:{ FileNotFoundException -> 0x00b9, Throwable -> 0x0098, all -> 0x0096 }
            if (r7 != r6) goto L_0x0061
            goto L_0x000f
        L_0x0061:
            java.io.File r5 = a(r5)     // Catch:{ FileNotFoundException -> 0x00b9, Throwable -> 0x0098, all -> 0x0096 }
            java.io.BufferedOutputStream r7 = new java.io.BufferedOutputStream     // Catch:{ FileNotFoundException -> 0x00b9, Throwable -> 0x0098, all -> 0x0096 }
            java.io.FileOutputStream r8 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x00b9, Throwable -> 0x0098, all -> 0x0096 }
            r8.<init>(r5)     // Catch:{ FileNotFoundException -> 0x00b9, Throwable -> 0x0098, all -> 0x0096 }
            r7.<init>(r8)     // Catch:{ FileNotFoundException -> 0x00b9, Throwable -> 0x0098, all -> 0x0096 }
            java.io.BufferedInputStream r5 = new java.io.BufferedInputStream     // Catch:{ FileNotFoundException -> 0x00b9, Throwable -> 0x0098, all -> 0x0096 }
            java.io.InputStream r4 = r2.getInputStream(r4)     // Catch:{ FileNotFoundException -> 0x00b9, Throwable -> 0x0098, all -> 0x0096 }
            r5.<init>(r4)     // Catch:{ FileNotFoundException -> 0x00b9, Throwable -> 0x0098, all -> 0x0096 }
        L_0x0078:
            int r4 = r5.read(r3, r0, r1)     // Catch:{ FileNotFoundException -> 0x00b9, Throwable -> 0x0098, all -> 0x0096 }
            if (r4 == r6) goto L_0x0082
            r7.write(r3, r0, r4)     // Catch:{ FileNotFoundException -> 0x00b9, Throwable -> 0x0098, all -> 0x0096 }
            goto L_0x0078
        L_0x0082:
            r7.flush()     // Catch:{ FileNotFoundException -> 0x00b9, Throwable -> 0x0098, all -> 0x0096 }
            r5.close()     // Catch:{ FileNotFoundException -> 0x00b9, Throwable -> 0x0098, all -> 0x0096 }
            r7.close()     // Catch:{ FileNotFoundException -> 0x00b9, Throwable -> 0x0098, all -> 0x0096 }
            goto L_0x000f
        L_0x008c:
            r2.close()     // Catch:{ Exception -> 0x0090 }
            goto L_0x0094
        L_0x0090:
            r9 = move-exception
            r9.printStackTrace()
        L_0x0094:
            r9 = 1
            return r9
        L_0x0096:
            r9 = move-exception
            goto L_0x00ad
        L_0x0098:
            r9 = move-exception
            r1 = r2
            goto L_0x009f
        L_0x009b:
            r9 = move-exception
            r2 = r1
            goto L_0x00ad
        L_0x009e:
            r9 = move-exception
        L_0x009f:
            r9.printStackTrace()     // Catch:{ all -> 0x009b }
            if (r1 == 0) goto L_0x00ac
            r1.close()     // Catch:{ Exception -> 0x00a8 }
            goto L_0x00ac
        L_0x00a8:
            r9 = move-exception
            r9.printStackTrace()
        L_0x00ac:
            return r0
        L_0x00ad:
            if (r2 == 0) goto L_0x00b7
            r2.close()     // Catch:{ Exception -> 0x00b3 }
            goto L_0x00b7
        L_0x00b3:
            r10 = move-exception
            r10.printStackTrace()
        L_0x00b7:
            throw r9
        L_0x00b8:
            r2 = r1
        L_0x00b9:
            if (r2 == 0) goto L_0x00c3
            r2.close()     // Catch:{ Exception -> 0x00bf }
            goto L_0x00c3
        L_0x00bf:
            r9 = move-exception
            r9.printStackTrace()
        L_0x00c3:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.plugin.ZipFileUtils.b(java.lang.String, java.lang.String, java.lang.String, com.xiaomi.smarthome.framework.plugin.ZipFileUtils$TranslateName):boolean");
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

    public static class UnzipLibraryRunnable implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        private String f17199a;
        private String b;
        private String c;
        private UnzipCallBack d;

        public interface UnzipCallBack {
            void a();

            void b();
        }

        public UnzipLibraryRunnable(String str, String str2, String str3, UnzipCallBack unzipCallBack) {
            this.f17199a = str;
            this.b = str2;
            this.c = str3;
            this.d = unzipCallBack;
        }

        public UnzipLibraryRunnable(String str, String str2, UnzipCallBack unzipCallBack) {
            this(str, str2, (String) null, unzipCallBack);
        }

        public void run() {
            if (ZipFileUtils.b(this.f17199a, this.b, this.c, (TranslateName) null)) {
                if (this.d != null) {
                    this.d.a();
                }
            } else if (this.d != null) {
                this.d.b();
            }
        }
    }
}
