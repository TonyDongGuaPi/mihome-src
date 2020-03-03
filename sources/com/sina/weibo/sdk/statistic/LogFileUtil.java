package com.sina.weibo.sdk.statistic;

import android.os.Environment;
import android.text.TextUtils;
import com.sina.weibo.sdk.utils.MD5;
import java.io.File;

class LogFileUtil {

    /* renamed from: a  reason: collision with root package name */
    public static final String f8831a = "app_logs";
    private static final String b = "/sina/weibo/.applogs/";
    private static final String c = ".txt";

    LogFileUtil() {
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return d(str);
    }

    public static String b(String str) {
        String str2 = "";
        if (LogReport.a() != null) {
            str2 = MD5.a(LogReport.a()) + "/";
        }
        return a() + b + str2 + str + ".txt";
    }

    private static String a() {
        File externalStorageDirectory = Environment.getExternalStorageState().equals("mounted") ? Environment.getExternalStorageDirectory() : null;
        if (externalStorageDirectory != null) {
            return externalStorageDirectory.toString();
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0050, code lost:
        if (r4 != null) goto L_0x0052;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x005a, code lost:
        if (r4 != null) goto L_0x0052;
     */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0064 A[SYNTHETIC, Splitter:B:37:0x0064] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:30:0x0057=Splitter:B:30:0x0057, B:24:0x004d=Splitter:B:24:0x004d} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String d(java.lang.String r4) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r4)
            if (r0 == 0) goto L_0x0009
            java.lang.String r4 = ""
            return r4
        L_0x0009:
            java.io.File r0 = new java.io.File
            r0.<init>(r4)
            boolean r4 = r0.isFile()
            if (r4 == 0) goto L_0x0068
            boolean r4 = r0.exists()
            if (r4 != 0) goto L_0x001b
            goto L_0x0068
        L_0x001b:
            r4 = 0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            long r2 = r0.length()
            int r2 = (int) r2
            r1.<init>(r2)
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ IOException -> 0x0056, OutOfMemoryError -> 0x004c }
            java.io.FileReader r3 = new java.io.FileReader     // Catch:{ IOException -> 0x0056, OutOfMemoryError -> 0x004c }
            r3.<init>(r0)     // Catch:{ IOException -> 0x0056, OutOfMemoryError -> 0x004c }
            r2.<init>(r3)     // Catch:{ IOException -> 0x0056, OutOfMemoryError -> 0x004c }
        L_0x0030:
            java.lang.String r4 = r2.readLine()     // Catch:{ IOException -> 0x0046, OutOfMemoryError -> 0x0042, all -> 0x003e }
            if (r4 == 0) goto L_0x003a
            r1.append(r4)     // Catch:{ IOException -> 0x0046, OutOfMemoryError -> 0x0042, all -> 0x003e }
            goto L_0x0030
        L_0x003a:
            r2.close()     // Catch:{ IOException -> 0x005d }
            goto L_0x005d
        L_0x003e:
            r4 = move-exception
            r0 = r4
            r4 = r2
            goto L_0x0062
        L_0x0042:
            r4 = move-exception
            r0 = r4
            r4 = r2
            goto L_0x004d
        L_0x0046:
            r4 = move-exception
            r0 = r4
            r4 = r2
            goto L_0x0057
        L_0x004a:
            r0 = move-exception
            goto L_0x0062
        L_0x004c:
            r0 = move-exception
        L_0x004d:
            r0.printStackTrace()     // Catch:{ all -> 0x004a }
            if (r4 == 0) goto L_0x005d
        L_0x0052:
            r4.close()     // Catch:{ IOException -> 0x005d }
            goto L_0x005d
        L_0x0056:
            r0 = move-exception
        L_0x0057:
            r0.printStackTrace()     // Catch:{ all -> 0x004a }
            if (r4 == 0) goto L_0x005d
            goto L_0x0052
        L_0x005d:
            java.lang.String r4 = r1.toString()
            return r4
        L_0x0062:
            if (r4 == 0) goto L_0x0067
            r4.close()     // Catch:{ IOException -> 0x0067 }
        L_0x0067:
            throw r0
        L_0x0068:
            java.lang.String r4 = ""
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sina.weibo.sdk.statistic.LogFileUtil.d(java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00c8, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00b4 A[SYNTHETIC, Splitter:B:49:0x00b4] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00bf A[SYNTHETIC, Splitter:B:57:0x00bf] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void a(java.lang.String r8, java.lang.String r9, boolean r10) {
        /*
            java.lang.Class<com.sina.weibo.sdk.statistic.LogFileUtil> r0 = com.sina.weibo.sdk.statistic.LogFileUtil.class
            monitor-enter(r0)
            boolean r1 = android.text.TextUtils.isEmpty(r8)     // Catch:{ all -> 0x00c9 }
            if (r1 == 0) goto L_0x000b
            monitor-exit(r0)
            return
        L_0x000b:
            java.lang.String r1 = "WBAgent"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x00c9 }
            r2.<init>()     // Catch:{ all -> 0x00c9 }
            java.lang.String r3 = "filePath:"
            r2.append(r3)     // Catch:{ all -> 0x00c9 }
            r2.append(r8)     // Catch:{ all -> 0x00c9 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x00c9 }
            com.sina.weibo.sdk.utils.LogUtil.b(r1, r2)     // Catch:{ all -> 0x00c9 }
            if (r9 == 0) goto L_0x00c7
            int r1 = r9.length()     // Catch:{ all -> 0x00c9 }
            if (r1 != 0) goto L_0x002b
            goto L_0x00c7
        L_0x002b:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00c9 }
            r1.<init>(r9)     // Catch:{ all -> 0x00c9 }
            r9 = 0
            char r2 = r1.charAt(r9)     // Catch:{ all -> 0x00c9 }
            r3 = 91
            r4 = 1
            if (r2 != r3) goto L_0x003f
            java.lang.String r2 = ""
            r1.replace(r9, r4, r2)     // Catch:{ all -> 0x00c9 }
        L_0x003f:
            int r2 = r1.length()     // Catch:{ all -> 0x00c9 }
            int r2 = r2 - r4
            char r2 = r1.charAt(r2)     // Catch:{ all -> 0x00c9 }
            r3 = 44
            if (r2 == r3) goto L_0x005a
            int r2 = r1.length()     // Catch:{ all -> 0x00c9 }
            int r2 = r2 - r4
            int r3 = r1.length()     // Catch:{ all -> 0x00c9 }
            java.lang.String r4 = ","
            r1.replace(r2, r3, r4)     // Catch:{ all -> 0x00c9 }
        L_0x005a:
            java.io.File r2 = new java.io.File     // Catch:{ all -> 0x00c9 }
            r2.<init>(r8)     // Catch:{ all -> 0x00c9 }
            r8 = 0
            java.io.File r3 = r2.getParentFile()     // Catch:{ IOException -> 0x00bd, all -> 0x00b1 }
            boolean r4 = r3.exists()     // Catch:{ IOException -> 0x00bd, all -> 0x00b1 }
            if (r4 != 0) goto L_0x006d
            r3.mkdirs()     // Catch:{ IOException -> 0x00bd, all -> 0x00b1 }
        L_0x006d:
            boolean r3 = r2.exists()     // Catch:{ IOException -> 0x00bd, all -> 0x00b1 }
            if (r3 != 0) goto L_0x0077
            r2.createNewFile()     // Catch:{ IOException -> 0x00bd, all -> 0x00b1 }
            goto L_0x0093
        L_0x0077:
            long r3 = r2.lastModified()     // Catch:{ IOException -> 0x00bd, all -> 0x00b1 }
            r5 = 0
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 <= 0) goto L_0x0093
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ IOException -> 0x00bd, all -> 0x00b1 }
            long r5 = r2.lastModified()     // Catch:{ IOException -> 0x00bd, all -> 0x00b1 }
            r7 = 0
            long r3 = r3 - r5
            r5 = 86400000(0x5265c00, double:4.2687272E-316)
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 <= 0) goto L_0x0093
            goto L_0x0094
        L_0x0093:
            r9 = r10
        L_0x0094:
            java.io.FileWriter r10 = new java.io.FileWriter     // Catch:{ IOException -> 0x00bd, all -> 0x00b1 }
            r10.<init>(r2, r9)     // Catch:{ IOException -> 0x00bd, all -> 0x00b1 }
            java.lang.String r8 = r1.toString()     // Catch:{ IOException -> 0x00af, all -> 0x00ac }
            r10.write(r8)     // Catch:{ IOException -> 0x00af, all -> 0x00ac }
            r10.flush()     // Catch:{ IOException -> 0x00af, all -> 0x00ac }
            r10.close()     // Catch:{ IOException -> 0x00a7 }
            goto L_0x00c5
        L_0x00a7:
            r8 = move-exception
        L_0x00a8:
            r8.printStackTrace()     // Catch:{ all -> 0x00c9 }
            goto L_0x00c5
        L_0x00ac:
            r9 = move-exception
            r8 = r10
            goto L_0x00b2
        L_0x00af:
            r8 = r10
            goto L_0x00bd
        L_0x00b1:
            r9 = move-exception
        L_0x00b2:
            if (r8 == 0) goto L_0x00bc
            r8.close()     // Catch:{ IOException -> 0x00b8 }
            goto L_0x00bc
        L_0x00b8:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ all -> 0x00c9 }
        L_0x00bc:
            throw r9     // Catch:{ all -> 0x00c9 }
        L_0x00bd:
            if (r8 == 0) goto L_0x00c5
            r8.close()     // Catch:{ IOException -> 0x00c3 }
            goto L_0x00c5
        L_0x00c3:
            r8 = move-exception
            goto L_0x00a8
        L_0x00c5:
            monitor-exit(r0)
            return
        L_0x00c7:
            monitor-exit(r0)
            return
        L_0x00c9:
            r8 = move-exception
            monitor-exit(r0)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sina.weibo.sdk.statistic.LogFileUtil.a(java.lang.String, java.lang.String, boolean):void");
    }

    public static boolean c(String str) {
        File file = new File(str);
        if (!file.exists() || !file.isFile()) {
            return false;
        }
        file.delete();
        return true;
    }
}
