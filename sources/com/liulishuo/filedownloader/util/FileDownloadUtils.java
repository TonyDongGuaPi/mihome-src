package com.liulishuo.filedownloader.util;

import android.app.ActivityManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import android.text.TextUtils;
import com.liulishuo.filedownloader.BuildConfig;
import com.liulishuo.filedownloader.connection.FileDownloadConnection;
import com.liulishuo.filedownloader.download.CustomComponentHolder;
import com.liulishuo.filedownloader.exception.FileDownloadGiveUpRetryException;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import com.liulishuo.filedownloader.stream.FileDownloadOutputStream;
import com.taobao.weex.el.parse.Operators;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileDownloadUtils {

    /* renamed from: a  reason: collision with root package name */
    private static int f6471a = 65536;
    private static long b = 2000;
    private static String c = null;
    private static Boolean d = null;
    private static final String e = "filedownloader";
    private static final String f = ".old_file_converted";
    private static Boolean g = null;
    private static final Pattern h = Pattern.compile("attachment;\\s*filename\\s*=\\s*\"([^\"]*)\"");
    private static final String i = "FileDownloader";

    public static boolean a(String str) {
        return true;
    }

    public static void a(int i2) throws IllegalAccessException {
        if (a(FileDownloadHelper.a())) {
            f6471a = i2;
            return;
        }
        throw new IllegalAccessException("This value is used in the :filedownloader process, so set this value in your process is without effect. You can add 'process.non-separate=true' in 'filedownloader.properties' to share the main process to FileDownloadService. Or you can configure this value in 'filedownloader.properties' by 'download.min-progress-step'.");
    }

    public static void a(long j) throws IllegalAccessException {
        if (a(FileDownloadHelper.a())) {
            b = j;
            return;
        }
        throw new IllegalAccessException("This value is used in the :filedownloader process, so set this value in your process is without effect. You can add 'process.non-separate=true' in 'filedownloader.properties' to share the main process to FileDownloadService. Or you can configure this value in 'filedownloader.properties' by 'download.min-progress-time'.");
    }

    public static int a() {
        return f6471a;
    }

    public static long b() {
        return b;
    }

    public static String c() {
        if (!TextUtils.isEmpty(c)) {
            return c;
        }
        if (FileDownloadHelper.a().getExternalCacheDir() == null) {
            return Environment.getDownloadCacheDirectory().getAbsolutePath();
        }
        return FileDownloadHelper.a().getExternalCacheDir().getAbsolutePath();
    }

    public static String b(String str) {
        return a(c(), c(str));
    }

    public static String c(String str) {
        return f(str);
    }

    public static String a(String str, String str2) {
        if (str2 == null) {
            throw new IllegalStateException("can't generate real path, the file name is null");
        } else if (str != null) {
            return a("%s%s%s", str, File.separator, str2);
        } else {
            throw new IllegalStateException("can't generate real path, the directory is null");
        }
    }

    public static void d(String str) {
        c = str;
    }

    public static String e(String str) {
        return a("%s.temp", str);
    }

    public static int b(String str, String str2) {
        return CustomComponentHolder.a().b().a(str, str2, false);
    }

    public static int a(String str, String str2, boolean z) {
        return CustomComponentHolder.a().b().a(str, str2, z);
    }

    public static String f(String str) {
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder(digest.length * 2);
            for (byte b2 : digest) {
                byte b3 = b2 & 255;
                if (b3 < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(b3));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e2) {
            throw new RuntimeException("Huh, MD5 should be supported?", e2);
        } catch (UnsupportedEncodingException e3) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e3);
        }
    }

    public static String d() {
        return a(true);
    }

    public static String a(boolean z) {
        return a(new Throwable().getStackTrace(), z);
    }

    public static String a(StackTraceElement[] stackTraceElementArr, boolean z) {
        if (stackTraceElementArr == null || stackTraceElementArr.length < 4) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 3; i2 < stackTraceElementArr.length; i2++) {
            if (stackTraceElementArr[i2].getClassName().contains(BuildConfig.b)) {
                sb.append(Operators.ARRAY_START_STR);
                sb.append(stackTraceElementArr[i2].getClassName().substring(BuildConfig.b.length()));
                sb.append(":");
                sb.append(stackTraceElementArr[i2].getMethodName());
                if (z) {
                    sb.append(Operators.BRACKET_START_STR);
                    sb.append(stackTraceElementArr[i2].getLineNumber());
                    sb.append(")]");
                } else {
                    sb.append(Operators.ARRAY_END_STR);
                }
            }
        }
        return sb.toString();
    }

    public static boolean a(Context context) {
        if (d != null) {
            return d.booleanValue();
        }
        boolean z = false;
        if (!FileDownloadProperties.a().d) {
            int myPid = Process.myPid();
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            if (activityManager != null) {
                List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
                if (runningAppProcesses != null && !runningAppProcesses.isEmpty()) {
                    Iterator<ActivityManager.RunningAppProcessInfo> it = runningAppProcesses.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        ActivityManager.RunningAppProcessInfo next = it.next();
                        if (next.pid == myPid) {
                            z = next.processName.endsWith(":filedownloader");
                            break;
                        }
                    }
                } else {
                    FileDownloadLog.d(FileDownloadUtils.class, "The running app process info list from ActivityManager is null or empty, maybe current App is not running.", new Object[0]);
                    return false;
                }
            } else {
                FileDownloadLog.d(FileDownloadUtils.class, "fail to get the activity manager!", new Object[0]);
                return false;
            }
        } else {
            z = true;
        }
        d = Boolean.valueOf(z);
        return d.booleanValue();
    }

    public static String[] g(String str) {
        String[] split = str.split("\n");
        String[] strArr = new String[(split.length * 2)];
        for (int i2 = 0; i2 < split.length; i2++) {
            String[] split2 = split[i2].split(": ");
            int i3 = i2 * 2;
            strArr[i3] = split2[0];
            strArr[i3 + 1] = split2[1];
        }
        return strArr;
    }

    public static long h(String str) {
        StatFs statFs = new StatFs(str);
        if (Build.VERSION.SDK_INT >= 18) {
            return statFs.getAvailableBytes();
        }
        return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
    }

    public static String a(String str, Object... objArr) {
        return String.format(Locale.ENGLISH, str, objArr);
    }

    public static void b(Context context) {
        File d2 = d(context);
        try {
            d2.getParentFile().mkdirs();
            d2.createNewFile();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public static boolean c(Context context) {
        if (g == null) {
            g = Boolean.valueOf(d(context).exists());
        }
        return g.booleanValue();
    }

    public static File d(Context context) {
        return new File(context.getFilesDir().getAbsolutePath() + File.separator + "filedownloader", f);
    }

    public static String i(String str) {
        if (str == null) {
            return null;
        }
        try {
            Matcher matcher = h.matcher(str);
            if (matcher.find()) {
                return matcher.group(1);
            }
        } catch (IllegalStateException unused) {
        }
        return null;
    }

    public static String a(String str, boolean z, String str2) {
        if (str == null) {
            return null;
        }
        if (!z) {
            return str;
        }
        if (str2 == null) {
            return null;
        }
        return a(str, str2);
    }

    public static String j(String str) {
        int length = str.length();
        int i2 = 2;
        int i3 = (File.separatorChar == '\\' && length > 2 && str.charAt(1) == ':') ? 2 : 0;
        int lastIndexOf = str.lastIndexOf(File.separatorChar);
        if (lastIndexOf != -1 || i3 <= 0) {
            i2 = lastIndexOf;
        }
        if (i2 == -1 || str.charAt(length - 1) == File.separatorChar) {
            return null;
        }
        if (str.indexOf(File.separatorChar) == i2 && str.charAt(i3) == File.separatorChar) {
            return str.substring(0, i2 + 1);
        }
        return str.substring(0, i2);
    }

    public static String k(String str) {
        return "FileDownloader-" + str;
    }

    public static boolean e() {
        ConnectivityManager connectivityManager = (ConnectivityManager) FileDownloadHelper.a().getSystemService("connectivity");
        if (connectivityManager == null) {
            FileDownloadLog.d(FileDownloadUtils.class, "failed to get connectivity manager!", new Object[0]);
            return true;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null || activeNetworkInfo.getType() != 1) {
            return true;
        }
        return false;
    }

    public static boolean l(String str) {
        return FileDownloadHelper.a().checkCallingOrSelfPermission(str) == 0;
    }

    public static long m(String str) {
        if (str == null) {
            return -1;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    public static String a(int i2, FileDownloadConnection fileDownloadConnection) {
        if (fileDownloadConnection != null) {
            String a2 = fileDownloadConnection.a("Etag");
            if (FileDownloadLog.f6465a) {
                FileDownloadLog.c(FileDownloadUtils.class, "etag find %s for task(%d)", a2, Integer.valueOf(i2));
            }
            return a2;
        }
        throw new RuntimeException("connection is null when findEtag");
    }

    public static long b(int i2, FileDownloadConnection fileDownloadConnection) {
        long m = m(fileDownloadConnection.a("Content-Length"));
        String a2 = fileDownloadConnection.a("Transfer-Encoding");
        if (m >= 0) {
            return m;
        }
        if (a2 != null && a2.equals("chunked")) {
            return -1;
        }
        if (!FileDownloadProperties.a().c) {
            throw new FileDownloadGiveUpRetryException("can't know the size of the download file, and its Transfer-Encoding is not Chunked either.\nyou can ignore such exception by add http.lenient=true to the filedownloader.properties");
        } else if (!FileDownloadLog.f6465a) {
            return -1;
        } else {
            FileDownloadLog.c(FileDownloadUtils.class, "%d response header is not legal but HTTP lenient is true, so handle as the case of transfer encoding chunk", Integer.valueOf(i2));
            return -1;
        }
    }

    public static String a(FileDownloadConnection fileDownloadConnection, String str) {
        String i2 = i(fileDownloadConnection.a("Content-Disposition"));
        return TextUtils.isEmpty(i2) ? c(str) : i2;
    }

    public static FileDownloadOutputStream n(String str) throws IOException {
        if (TextUtils.isEmpty(str)) {
            throw new RuntimeException("found invalid internal destination path, empty");
        } else if (a(str)) {
            File file = new File(str);
            if (file.exists() && file.isDirectory()) {
                throw new RuntimeException(a("found invalid internal destination path[%s], & path is directory[%B]", str, Boolean.valueOf(file.isDirectory())));
            } else if (file.exists() || file.createNewFile()) {
                return CustomComponentHolder.a().a(file);
            } else {
                throw new IOException(a("create new file error  %s", file.getAbsolutePath()));
            }
        } else {
            throw new RuntimeException(a("found invalid internal destination filename %s", str));
        }
    }

    public static boolean a(int i2, FileDownloadModel fileDownloadModel) {
        return a(i2, fileDownloadModel, (Boolean) null);
    }

    public static boolean a(int i2, FileDownloadModel fileDownloadModel, Boolean bool) {
        if (fileDownloadModel == null) {
            if (FileDownloadLog.f6465a) {
                FileDownloadLog.c(FileDownloadUtils.class, "can't continue %d model == null", Integer.valueOf(i2));
            }
            return false;
        } else if (fileDownloadModel.e() != null) {
            return a(i2, fileDownloadModel, fileDownloadModel.e(), bool);
        } else {
            if (FileDownloadLog.f6465a) {
                FileDownloadLog.c(FileDownloadUtils.class, "can't continue %d temp path == null", Integer.valueOf(i2));
            }
            return false;
        }
    }

    public static boolean a(int i2, FileDownloadModel fileDownloadModel, String str, Boolean bool) {
        if (str != null) {
            File file = new File(str);
            boolean exists = file.exists();
            boolean isDirectory = file.isDirectory();
            if (exists && !isDirectory) {
                long length = file.length();
                long g2 = fileDownloadModel.g();
                if (fileDownloadModel.n() > 1 || g2 != 0) {
                    long h2 = fileDownloadModel.h();
                    if (length < g2 || (h2 != -1 && (length > h2 || g2 >= h2))) {
                        if (!FileDownloadLog.f6465a) {
                            return false;
                        }
                        FileDownloadLog.c(FileDownloadUtils.class, "can't continue %d dirty data fileLength[%d] sofar[%d] total[%d]", Integer.valueOf(i2), Long.valueOf(length), Long.valueOf(g2), Long.valueOf(h2));
                        return false;
                    } else if (bool == null || bool.booleanValue() || h2 != length) {
                        return true;
                    } else {
                        if (!FileDownloadLog.f6465a) {
                            return false;
                        }
                        FileDownloadLog.c(FileDownloadUtils.class, "can't continue %d, because of the output stream doesn't support seek, but the task has already pre-allocated, so we only can download it from the very beginning.", Integer.valueOf(i2));
                        return false;
                    }
                } else if (!FileDownloadLog.f6465a) {
                    return false;
                } else {
                    FileDownloadLog.c(FileDownloadUtils.class, "can't continue %d the downloaded-record is zero.", Integer.valueOf(i2));
                    return false;
                }
            } else if (!FileDownloadLog.f6465a) {
                return false;
            } else {
                FileDownloadLog.c(FileDownloadUtils.class, "can't continue %d file not suit, exists[%B], directory[%B]", Integer.valueOf(i2), Boolean.valueOf(exists), Boolean.valueOf(isDirectory));
                return false;
            }
        } else if (!FileDownloadLog.f6465a) {
            return false;
        } else {
            FileDownloadLog.c(FileDownloadUtils.class, "can't continue %d path = null", Integer.valueOf(i2));
            return false;
        }
    }

    public static void c(String str, String str2) {
        o(str2);
        p(str);
    }

    public static void o(String str) {
        if (str != null) {
            File file = new File(str);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    public static void p(String str) {
        if (str != null) {
            File file = new File(str);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    public static boolean a(long j, long j2) {
        return j > ((long) a()) && j2 > b();
    }
}
