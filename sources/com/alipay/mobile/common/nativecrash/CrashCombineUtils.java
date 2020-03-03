package com.alipay.mobile.common.nativecrash;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.TimeZone;

public class CrashCombineUtils {
    public static final int DEFAULT_MAX_INFO_LEN = 1048576;
    public static final String TOMB = "CrashSDK";

    /* renamed from: a  reason: collision with root package name */
    private static final FlatComparator f972a = new FlatComparator();
    public static long crashTime;

    public static String getLatestTombAndDelOld(Context context) {
        File file;
        if (context == null) {
            Log.w("CrashCombineUtils", "getLatestTombAndDelOld context is null");
            return null;
        }
        try {
            file = new File(context.getApplicationInfo().dataDir + File.separator + NativeCrashHandler.FILE_PATH);
        } catch (Exception unused) {
            file = null;
        }
        if (file == null) {
            Log.w("CrashCombineUtils", "parent is null");
            return null;
        }
        File[] listFiles = file.listFiles();
        ArrayList arrayList = new ArrayList();
        for (File add : listFiles) {
            arrayList.add(add);
        }
        Collections.sort(arrayList, f972a);
        File file2 = null;
        for (int i = 0; i < arrayList.size(); i++) {
            File file3 = (File) arrayList.get(i);
            if (!(file3 == null || file3.getName() == null || !file3.getName().startsWith(TOMB))) {
                file2 = file3;
            }
        }
        if (file2 == null) {
            return null;
        }
        Log.w("CrashCombineUtils", "latestTombFile = " + file2.getName());
        String UserTrackReport = UserTrackReport(file2.getAbsolutePath(), (String) null);
        a(file2);
        deleteFileByPath(file2.getAbsolutePath());
        return UserTrackReport;
    }

    private static void a(File file) {
        try {
            crashTime = file.lastModified();
        } catch (Exception unused) {
        }
    }

    public static long getCrashTime() {
        return crashTime;
    }

    public static class FlatComparator implements Comparator<File> {
        public int compare(File file, File file2) {
            return (file.lastModified() + "").compareTo(file2.lastModified() + "");
        }
    }

    public static String UserTrackReport(String str, String str2) {
        if (str == null) {
            return str2;
        }
        File file = new File(str);
        if (!file.exists() || !file.isFile()) {
            return str2;
        }
        byte[] readFile = readFile(file, 0);
        Log.i("MiniDump", "read: " + readFile.length + " org: " + file.length());
        if (readFile.length > 0) {
            try {
                return new String(readFile, "UTF-8");
            } catch (Throwable unused) {
                return "error: byte to string, logType:" + str2;
            }
        } else {
            return "error: none byte, logType:" + str2;
        }
    }

    public static void writeFileSdcardFile(String str, String str2) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(str);
            fileOutputStream.write(str2.getBytes());
            fileOutputStream.close();
        } catch (Exception unused) {
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0032 A[SYNTHETIC, Splitter:B:20:0x0032] */
    /* JADX WARNING: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] readFile(java.io.File r4, int r5) {
        /*
            if (r5 > 0) goto L_0x0004
            r5 = 1048576(0x100000, float:1.469368E-39)
        L_0x0004:
            long r0 = r4.length()
            int r0 = (int) r0
            if (r0 <= r5) goto L_0x000c
            goto L_0x000d
        L_0x000c:
            r5 = r0
        L_0x000d:
            byte[] r0 = new byte[r5]
            r1 = 0
            r2 = 0
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ IOException -> 0x002d }
            r3.<init>(r4)     // Catch:{ IOException -> 0x002d }
            int r4 = r3.read(r0, r2, r5)     // Catch:{ IOException -> 0x002e }
            r3.close()     // Catch:{ IOException -> 0x002e }
            if (r4 <= 0) goto L_0x0028
            if (r4 >= r5) goto L_0x0028
            byte[] r5 = new byte[r4]     // Catch:{ IOException -> 0x002e }
            java.lang.System.arraycopy(r0, r2, r5, r2, r4)     // Catch:{ IOException -> 0x002e }
            r0 = r5
            goto L_0x0035
        L_0x0028:
            if (r4 == r5) goto L_0x0035
            byte[] r0 = new byte[r2]     // Catch:{ IOException -> 0x002e }
            goto L_0x0035
        L_0x002d:
            r3 = r1
        L_0x002e:
            byte[] r0 = new byte[r2]
            if (r3 == 0) goto L_0x0035
            r3.close()     // Catch:{ IOException -> 0x0035 }
        L_0x0035:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.mobile.common.nativecrash.CrashCombineUtils.readFile(java.io.File, int):byte[]");
    }

    public static String getFullTimeString(long j) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return simpleDateFormat.format(new Date(j));
    }

    public static void deleteFileByPath(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                File file = new File(str);
                if (file.exists() && file.isFile()) {
                    file.delete();
                }
            } catch (Exception unused) {
            }
        }
    }
}
