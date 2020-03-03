package com.xiaomi.smarthome.framework.plugin.rn.utils;

import android.text.TextUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class RnPluginFileUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final int f17507a = 4096;

    public static boolean a(String str, String str2) {
        return c(str, str2, (String) null);
    }

    @Deprecated
    private static void a(String str, String str2, UnzipLibraryRunnable.UnzipCallBack unzipCallBack) {
        new UnzipLibraryRunnable(str, str2, unzipCallBack).run();
    }

    @Deprecated
    private static void b(String str, String str2, String str3) {
        new UnzipLibraryRunnable(str, str2, str3, (UnzipLibraryRunnable.UnzipCallBack) null).run();
    }

    @Deprecated
    private static void a(String str, String str2, String str3, UnzipLibraryRunnable.UnzipCallBack unzipCallBack) {
        new UnzipLibraryRunnable(str, str2, str3, unzipCallBack).run();
    }

    public static class UnzipLibraryRunnable implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        private String f17508a;
        private String b;
        private String c;
        private UnzipCallBack d;

        public interface UnzipCallBack {
            void a();

            void b();
        }

        public UnzipLibraryRunnable(String str, String str2, String str3, UnzipCallBack unzipCallBack) {
            this.f17508a = str;
            this.b = str2;
            this.c = str3;
            this.d = unzipCallBack;
        }

        public UnzipLibraryRunnable(String str, String str2, UnzipCallBack unzipCallBack) {
            this(str, str2, (String) null, unzipCallBack);
        }

        public void run() {
            if (RnPluginFileUtils.c(this.f17508a, this.b, this.c)) {
                if (this.d != null) {
                    this.d.a();
                }
            } else if (this.d != null) {
                this.d.b();
            }
        }
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
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00a9 A[SYNTHETIC, Splitter:B:42:0x00a9] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00b4 A[SYNTHETIC, Splitter:B:48:0x00b4] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00c0 A[SYNTHETIC, Splitter:B:57:0x00c0] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean c(java.lang.String r9, java.lang.String r10, java.lang.String r11) {
        /*
            r0 = 0
            r1 = 0
            java.util.zip.ZipFile r2 = new java.util.zip.ZipFile     // Catch:{ FileNotFoundException -> 0x00bd, Throwable -> 0x00a3 }
            r2.<init>(r9)     // Catch:{ FileNotFoundException -> 0x00bd, Throwable -> 0x00a3 }
            java.util.Enumeration r9 = r2.entries()     // Catch:{ FileNotFoundException -> 0x00be, Throwable -> 0x009d, all -> 0x009b }
            r1 = 4096(0x1000, float:5.74E-42)
            byte[] r3 = new byte[r1]     // Catch:{ FileNotFoundException -> 0x00be, Throwable -> 0x009d, all -> 0x009b }
        L_0x000f:
            boolean r4 = r9.hasMoreElements()     // Catch:{ FileNotFoundException -> 0x00be, Throwable -> 0x009d, all -> 0x009b }
            if (r4 == 0) goto L_0x0091
            java.lang.Object r4 = r9.nextElement()     // Catch:{ FileNotFoundException -> 0x00be, Throwable -> 0x009d, all -> 0x009b }
            java.util.zip.ZipEntry r4 = (java.util.zip.ZipEntry) r4     // Catch:{ FileNotFoundException -> 0x00be, Throwable -> 0x009d, all -> 0x009b }
            boolean r5 = r4.isDirectory()     // Catch:{ FileNotFoundException -> 0x00be, Throwable -> 0x009d, all -> 0x009b }
            if (r5 == 0) goto L_0x0022
            goto L_0x000f
        L_0x0022:
            java.lang.String r5 = r4.getName()     // Catch:{ FileNotFoundException -> 0x00be, Throwable -> 0x009d, all -> 0x009b }
            r6 = -1
            if (r5 == 0) goto L_0x0039
            java.lang.String r7 = "\\"
            int r7 = r5.indexOf(r7)     // Catch:{ FileNotFoundException -> 0x00be, Throwable -> 0x009d, all -> 0x009b }
            if (r7 == r6) goto L_0x0039
            java.lang.String r7 = "\\\\"
            java.lang.String r8 = java.io.File.separator     // Catch:{ FileNotFoundException -> 0x00be, Throwable -> 0x009d, all -> 0x009b }
            java.lang.String r5 = r5.replaceAll(r7, r8)     // Catch:{ FileNotFoundException -> 0x00be, Throwable -> 0x009d, all -> 0x009b }
        L_0x0039:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x00be, Throwable -> 0x009d, all -> 0x009b }
            r7.<init>()     // Catch:{ FileNotFoundException -> 0x00be, Throwable -> 0x009d, all -> 0x009b }
            r7.append(r10)     // Catch:{ FileNotFoundException -> 0x00be, Throwable -> 0x009d, all -> 0x009b }
            java.lang.String r8 = java.io.File.separator     // Catch:{ FileNotFoundException -> 0x00be, Throwable -> 0x009d, all -> 0x009b }
            r7.append(r8)     // Catch:{ FileNotFoundException -> 0x00be, Throwable -> 0x009d, all -> 0x009b }
            r7.append(r5)     // Catch:{ FileNotFoundException -> 0x00be, Throwable -> 0x009d, all -> 0x009b }
            java.lang.String r5 = r7.toString()     // Catch:{ FileNotFoundException -> 0x00be, Throwable -> 0x009d, all -> 0x009b }
            boolean r7 = c(r10, r5)     // Catch:{ FileNotFoundException -> 0x00be, Throwable -> 0x009d, all -> 0x009b }
            if (r7 == 0) goto L_0x008b
            boolean r7 = android.text.TextUtils.isEmpty(r11)     // Catch:{ FileNotFoundException -> 0x00be, Throwable -> 0x009d, all -> 0x009b }
            if (r7 != 0) goto L_0x0060
            int r7 = r5.indexOf(r11)     // Catch:{ FileNotFoundException -> 0x00be, Throwable -> 0x009d, all -> 0x009b }
            if (r7 != r6) goto L_0x0060
            goto L_0x000f
        L_0x0060:
            java.io.File r5 = a(r5)     // Catch:{ FileNotFoundException -> 0x00be, Throwable -> 0x009d, all -> 0x009b }
            java.io.BufferedOutputStream r7 = new java.io.BufferedOutputStream     // Catch:{ FileNotFoundException -> 0x00be, Throwable -> 0x009d, all -> 0x009b }
            java.io.FileOutputStream r8 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x00be, Throwable -> 0x009d, all -> 0x009b }
            r8.<init>(r5)     // Catch:{ FileNotFoundException -> 0x00be, Throwable -> 0x009d, all -> 0x009b }
            r7.<init>(r8)     // Catch:{ FileNotFoundException -> 0x00be, Throwable -> 0x009d, all -> 0x009b }
            java.io.BufferedInputStream r5 = new java.io.BufferedInputStream     // Catch:{ FileNotFoundException -> 0x00be, Throwable -> 0x009d, all -> 0x009b }
            java.io.InputStream r4 = r2.getInputStream(r4)     // Catch:{ FileNotFoundException -> 0x00be, Throwable -> 0x009d, all -> 0x009b }
            r5.<init>(r4)     // Catch:{ FileNotFoundException -> 0x00be, Throwable -> 0x009d, all -> 0x009b }
        L_0x0077:
            int r4 = r5.read(r3, r0, r1)     // Catch:{ FileNotFoundException -> 0x00be, Throwable -> 0x009d, all -> 0x009b }
            if (r4 == r6) goto L_0x0081
            r7.write(r3, r0, r4)     // Catch:{ FileNotFoundException -> 0x00be, Throwable -> 0x009d, all -> 0x009b }
            goto L_0x0077
        L_0x0081:
            r7.flush()     // Catch:{ FileNotFoundException -> 0x00be, Throwable -> 0x009d, all -> 0x009b }
            r5.close()     // Catch:{ FileNotFoundException -> 0x00be, Throwable -> 0x009d, all -> 0x009b }
            r7.close()     // Catch:{ FileNotFoundException -> 0x00be, Throwable -> 0x009d, all -> 0x009b }
            goto L_0x000f
        L_0x008b:
            java.io.FileNotFoundException r9 = new java.io.FileNotFoundException     // Catch:{ FileNotFoundException -> 0x00be, Throwable -> 0x009d, all -> 0x009b }
            r9.<init>()     // Catch:{ FileNotFoundException -> 0x00be, Throwable -> 0x009d, all -> 0x009b }
            throw r9     // Catch:{ FileNotFoundException -> 0x00be, Throwable -> 0x009d, all -> 0x009b }
        L_0x0091:
            r2.close()     // Catch:{ Exception -> 0x0095 }
            goto L_0x0099
        L_0x0095:
            r9 = move-exception
            r9.printStackTrace()
        L_0x0099:
            r9 = 1
            return r9
        L_0x009b:
            r9 = move-exception
            goto L_0x00b2
        L_0x009d:
            r9 = move-exception
            r1 = r2
            goto L_0x00a4
        L_0x00a0:
            r9 = move-exception
            r2 = r1
            goto L_0x00b2
        L_0x00a3:
            r9 = move-exception
        L_0x00a4:
            r9.printStackTrace()     // Catch:{ all -> 0x00a0 }
            if (r1 == 0) goto L_0x00b1
            r1.close()     // Catch:{ Exception -> 0x00ad }
            goto L_0x00b1
        L_0x00ad:
            r9 = move-exception
            r9.printStackTrace()
        L_0x00b1:
            return r0
        L_0x00b2:
            if (r2 == 0) goto L_0x00bc
            r2.close()     // Catch:{ Exception -> 0x00b8 }
            goto L_0x00bc
        L_0x00b8:
            r10 = move-exception
            r10.printStackTrace()
        L_0x00bc:
            throw r9
        L_0x00bd:
            r2 = r1
        L_0x00be:
            if (r2 == 0) goto L_0x00c8
            r2.close()     // Catch:{ Exception -> 0x00c4 }
            goto L_0x00c8
        L_0x00c4:
            r9 = move-exception
            r9.printStackTrace()
        L_0x00c8:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.plugin.rn.utils.RnPluginFileUtils.c(java.lang.String, java.lang.String, java.lang.String):boolean");
    }

    public static boolean b(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        return c(str, str + File.separator + str2);
    }

    public static boolean c(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            RnPluginLog.b("VerifyFile   fileAbsolutePathName=" + str2);
            return false;
        }
        String[] split = str2.split(File.separator);
        ArrayList arrayList = new ArrayList();
        for (String add : split) {
            arrayList.add(add);
        }
        Stack stack = new Stack();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if ("..".equals(arrayList.get(i))) {
                if (stack.size() <= 0) {
                    break;
                }
                stack.pop();
            } else if (!"".equals(arrayList.get(i))) {
                stack.push(arrayList.get(i));
            }
        }
        StringBuilder sb = new StringBuilder();
        int size2 = stack.size();
        for (int i2 = 0; i2 < size2; i2++) {
            if (i2 == size2 - 1) {
                sb.append((String) stack.get(i2));
            } else {
                sb.append((String) stack.get(i2));
                sb.append(File.separator);
            }
        }
        int length = File.separator.length();
        while (str.startsWith(File.separator)) {
            try {
                str = str.substring(length, str.length());
            } catch (Exception unused) {
            }
        }
        boolean startsWith = sb.toString().startsWith(str);
        RnPluginLog.a("VerifyFile   fileAbsolutePathName=" + str2);
        RnPluginLog.a("VerifyFile   resultFilePathName=" + sb.toString());
        RnPluginLog.a("VerifyFile   result=" + startsWith);
        return startsWith;
    }
}
