package com.tencent.tinker.loader.shareutil;

import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Process;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ShareTinkerInternals {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1362a = "Tinker.TinkerInternals";
    private static final boolean b = d(System.getProperty("java.vm.version"));
    private static final boolean c = f();
    private static final String d = ":patch";
    private static Boolean e = null;
    private static Boolean f = false;
    private static String g = null;
    private static String h = null;
    private static String i = null;

    public static boolean a(int i2) {
        return (i2 & 1) != 0;
    }

    public static boolean b(int i2) {
        return (i2 & 2) != 0;
    }

    public static boolean c(int i2) {
        return (i2 & 4) != 0;
    }

    public static boolean d(int i2) {
        return (i2 & 8) != 0;
    }

    public static String e(int i2) {
        switch (i2) {
            case 1:
                return "patch_file";
            case 2:
                return "patch_info";
            case 3:
                return ShareConstants.q;
            case 4:
                return "dex_opt";
            case 5:
                return ShareConstants.o;
            case 6:
                return "resource";
            default:
                return "unknown";
        }
    }

    public static boolean f(int i2) {
        return i2 != 0;
    }

    public static boolean g(int i2) {
        return i2 == 15;
    }

    public static boolean a() {
        return b || Build.VERSION.SDK_INT >= 21;
    }

    public static boolean b() {
        return c && Build.VERSION.SDK_INT < 24;
    }

    public static boolean c() {
        if (f != null) {
            return f.booleanValue();
        }
        f = false;
        try {
            Method declaredMethod = ClassLoader.getSystemClassLoader().getParent().loadClass("com.huawei.ark.app.ArkApplicationInfo").getDeclaredMethod("isRunningInArk", new Class[0]);
            declaredMethod.setAccessible(true);
            f = (Boolean) declaredMethod.invoke((Object) null, new Object[0]);
        } catch (ClassNotFoundException unused) {
            Log.i(f1362a, "class not found exception");
        } catch (NoSuchMethodException unused2) {
            Log.i(f1362a, "no such method exception");
        } catch (SecurityException unused3) {
            Log.i(f1362a, "security exception");
        } catch (IllegalAccessException unused4) {
            Log.i(f1362a, "illegal access exception");
        } catch (InvocationTargetException unused5) {
            Log.i(f1362a, "invocation target exception");
        } catch (IllegalArgumentException unused6) {
            Log.i(f1362a, "illegal argument exception");
        }
        return f.booleanValue();
    }

    public static boolean d() {
        return Build.VERSION.SDK_INT > 25;
    }

    public static String e() throws Exception {
        if (i != null) {
            return i;
        }
        i = (String) Class.forName("dalvik.system.VMRuntime").getDeclaredMethod("getCurrentInstructionSet", new Class[0]).invoke((Object) null, new Object[0]);
        Log.d(f1362a, "getCurrentInstructionSet:" + i);
        return i;
    }

    public static boolean a(String str) {
        String str2 = Build.FINGERPRINT;
        if (str == null || str.equals("") || str2 == null || str2.equals("")) {
            Log.d(f1362a, "fingerprint empty:" + str + ",current:" + str2);
            return false;
        } else if (str.equals(str2)) {
            Log.d(f1362a, "same fingerprint:" + str2);
            return false;
        } else {
            Log.d(f1362a, "system OTA,fingerprint not equal:" + str + "," + str2);
            return true;
        }
    }

    public static ShareDexDiffPatchInfo a(ShareDexDiffPatchInfo shareDexDiffPatchInfo, int i2) {
        String str;
        if (!shareDexDiffPatchInfo.f1358a.startsWith(ShareConstants.A)) {
            return null;
        }
        if (i2 != 1) {
            str = "classes" + i2 + ShareConstants.w;
        } else {
            str = ShareConstants.X;
        }
        return new ShareDexDiffPatchInfo(str, shareDexDiffPatchInfo.g, shareDexDiffPatchInfo.b, shareDexDiffPatchInfo.c, shareDexDiffPatchInfo.f, shareDexDiffPatchInfo.d, shareDexDiffPatchInfo.e, shareDexDiffPatchInfo.h);
    }

    public static boolean b(String str) {
        return str == null || str.length() <= 0;
    }

    public static int a(Context context, int i2, File file, ShareSecurityCheck shareSecurityCheck) {
        int a2 = a(context, file, shareSecurityCheck);
        return a2 == 0 ? a(shareSecurityCheck, i2) : a2;
    }

    public static int a(Context context, File file, ShareSecurityCheck shareSecurityCheck) {
        if (!shareSecurityCheck.a(file)) {
            return -1;
        }
        String a2 = a(context);
        if (a2 == null) {
            return -5;
        }
        HashMap<String, String> b2 = shareSecurityCheck.b();
        if (b2 == null) {
            return -2;
        }
        String str = b2.get(ShareConstants.f);
        if (str == null) {
            return -6;
        }
        if (a2.equals(str)) {
            return 0;
        }
        Log.e(f1362a, "tinkerId is not equal, base is " + a2 + ", but patch is " + str);
        return -7;
    }

    public static int a(ShareSecurityCheck shareSecurityCheck, int i2) {
        if (g(i2)) {
            return 0;
        }
        HashMap<String, String> a2 = shareSecurityCheck.a();
        if (!a(i2) && a2.containsKey(ShareConstants.p)) {
            return -9;
        }
        if (!b(i2) && a2.containsKey(ShareConstants.n)) {
            return -9;
        }
        if (c(i2) || !a2.containsKey(ShareConstants.I)) {
            return 0;
        }
        return -9;
    }

    public static Properties a(File file) {
        ZipFile zipFile;
        InputStream inputStream;
        if (file == null || !file.isFile() || file.length() == 0) {
            Log.e(f1362a, "patchFile is illegal");
            return null;
        }
        try {
            zipFile = new ZipFile(file);
            try {
                ZipEntry entry = zipFile.getEntry(ShareConstants.m);
                if (entry == null) {
                    Log.e(f1362a, "patch meta entry not found");
                    SharePatchFileUtil.a(zipFile);
                    return null;
                }
                try {
                    inputStream = zipFile.getInputStream(entry);
                    try {
                        Properties properties = new Properties();
                        properties.load(inputStream);
                        SharePatchFileUtil.a((Object) inputStream);
                        SharePatchFileUtil.a(zipFile);
                        return properties;
                    } catch (Throwable th) {
                        th = th;
                        SharePatchFileUtil.a((Object) inputStream);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    inputStream = null;
                    SharePatchFileUtil.a((Object) inputStream);
                    throw th;
                }
            } catch (IOException e2) {
                e = e2;
                try {
                    Log.e(f1362a, "fastGetPatchPackageMeta exception:" + e.getMessage());
                    SharePatchFileUtil.a(zipFile);
                    return null;
                } catch (Throwable th3) {
                    th = th3;
                    SharePatchFileUtil.a(zipFile);
                    throw th;
                }
            }
        } catch (IOException e3) {
            e = e3;
            zipFile = null;
            Log.e(f1362a, "fastGetPatchPackageMeta exception:" + e.getMessage());
            SharePatchFileUtil.a(zipFile);
            return null;
        } catch (Throwable th4) {
            th = th4;
            zipFile = null;
            SharePatchFileUtil.a(zipFile);
            throw th;
        }
    }

    public static String a(Context context) {
        if (h != null) {
            return h;
        }
        try {
            Object obj = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.get(ShareConstants.f);
            if (obj != null) {
                h = String.valueOf(obj);
            } else {
                h = null;
            }
            return h;
        } catch (Exception e2) {
            Log.e(f1362a, "getManifestTinkerID exception:" + e2.getMessage());
            return null;
        }
    }

    public static void b(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(ShareConstants.ae, 4);
        sharedPreferences.edit().putBoolean(j(context), false).commit();
    }

    public static boolean c(Context context) {
        if (context == null) {
            return false;
        }
        return context.getSharedPreferences(ShareConstants.ae, 4).getBoolean(j(context), true);
    }

    private static String j(Context context) {
        String a2 = a(context);
        if (b(a2)) {
            a2 = "@@";
        }
        return "tinker_enable_1.9.14_" + a2;
    }

    public static int d(Context context) {
        String str = ShareConstants.ag + i(context);
        int i2 = context.getSharedPreferences(str, 0).getInt("safe_mode_count_1.9.14", 0);
        Log.w(f1362a, "getSafeModeCount: preferName:" + str + " count:" + i2);
        return i2;
    }

    public static void a(Context context, int i2) {
        String str = ShareConstants.ag + i(context);
        context.getSharedPreferences(str, 0).edit().putInt("safe_mode_count_1.9.14", i2).commit();
        Log.w(f1362a, "setSafeModeCount: preferName:" + str + " count:" + i2);
    }

    public static boolean e(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        String str = applicationInfo != null ? applicationInfo.processName : null;
        if (b(str)) {
            str = context.getPackageName();
        }
        String i2 = i(context);
        if (i2 == null || i2.length() == 0) {
            i2 = "";
        }
        return str.equals(i2);
    }

    public static boolean f(Context context) {
        if (e != null) {
            return e.booleanValue();
        }
        e = Boolean.valueOf(i(context).endsWith(d));
        return e.booleanValue();
    }

    public static String a(Context context, String str) {
        if (str.equals(ShareConstants.v)) {
            return e(context) ? "odex" : ShareConstants.u;
        }
        return str;
    }

    public static void g(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager != null && (runningAppProcesses = activityManager.getRunningAppProcesses()) != null) {
            for (ActivityManager.RunningAppProcessInfo next : runningAppProcesses) {
                if (next.uid == Process.myUid() && next.pid != Process.myPid()) {
                    Process.killProcess(next.pid);
                }
            }
        }
    }

    public static void h(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager != null && (runningAppProcesses = activityManager.getRunningAppProcesses()) != null) {
            for (ActivityManager.RunningAppProcessInfo next : runningAppProcesses) {
                if (next.uid == Process.myUid() && !next.processName.equals(context.getPackageName())) {
                    Process.killProcess(next.pid);
                }
            }
        }
    }

    public static String i(Context context) {
        if (g != null) {
            return g;
        }
        g = k(context);
        return g;
    }

    /* JADX WARNING: Removed duplicated region for block: B:52:0x00bb A[SYNTHETIC, Splitter:B:52:0x00bb] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00c3 A[SYNTHETIC, Splitter:B:56:0x00c3] */
    /* JADX WARNING: Removed duplicated region for block: B:67:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String k(android.content.Context r6) {
        /*
            int r0 = android.os.Process.myPid()
            if (r6 == 0) goto L_0x00c7
            if (r0 > 0) goto L_0x000a
            goto L_0x00c7
        L_0x000a:
            java.lang.String r1 = "activity"
            java.lang.Object r6 = r6.getSystemService(r1)
            android.app.ActivityManager r6 = (android.app.ActivityManager) r6
            r1 = 0
            if (r6 == 0) goto L_0x0051
            java.util.List r6 = r6.getRunningAppProcesses()     // Catch:{ Exception -> 0x0036 }
            if (r6 == 0) goto L_0x0051
            java.util.Iterator r6 = r6.iterator()     // Catch:{ Exception -> 0x0036 }
        L_0x001f:
            boolean r2 = r6.hasNext()     // Catch:{ Exception -> 0x0036 }
            if (r2 == 0) goto L_0x0030
            java.lang.Object r2 = r6.next()     // Catch:{ Exception -> 0x0036 }
            android.app.ActivityManager$RunningAppProcessInfo r2 = (android.app.ActivityManager.RunningAppProcessInfo) r2     // Catch:{ Exception -> 0x0036 }
            int r3 = r2.pid     // Catch:{ Exception -> 0x0036 }
            if (r3 != r0) goto L_0x001f
            goto L_0x0031
        L_0x0030:
            r2 = r1
        L_0x0031:
            if (r2 == 0) goto L_0x0051
            java.lang.String r6 = r2.processName     // Catch:{ Exception -> 0x0036 }
            return r6
        L_0x0036:
            r6 = move-exception
            java.lang.String r2 = "Tinker.TinkerInternals"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "getProcessNameInternal exception:"
            r3.append(r4)
            java.lang.String r6 = r6.getMessage()
            r3.append(r6)
            java.lang.String r6 = r3.toString()
            android.util.Log.e(r2, r6)
        L_0x0051:
            r6 = 128(0x80, float:1.794E-43)
            byte[] r2 = new byte[r6]
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ Exception -> 0x009e }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x009e }
            r4.<init>()     // Catch:{ Exception -> 0x009e }
            java.lang.String r5 = "/proc/"
            r4.append(r5)     // Catch:{ Exception -> 0x009e }
            r4.append(r0)     // Catch:{ Exception -> 0x009e }
            java.lang.String r0 = "/cmdline"
            r4.append(r0)     // Catch:{ Exception -> 0x009e }
            java.lang.String r0 = r4.toString()     // Catch:{ Exception -> 0x009e }
            r3.<init>(r0)     // Catch:{ Exception -> 0x009e }
            int r0 = r3.read(r2)     // Catch:{ Exception -> 0x0099, all -> 0x0096 }
            if (r0 <= 0) goto L_0x0092
            r1 = 0
            r4 = 0
        L_0x0078:
            if (r4 >= r0) goto L_0x0089
            byte r5 = r2[r4]     // Catch:{ Exception -> 0x0099, all -> 0x0096 }
            r5 = r5 & 255(0xff, float:3.57E-43)
            if (r5 > r6) goto L_0x0088
            byte r5 = r2[r4]     // Catch:{ Exception -> 0x0099, all -> 0x0096 }
            if (r5 > 0) goto L_0x0085
            goto L_0x0088
        L_0x0085:
            int r4 = r4 + 1
            goto L_0x0078
        L_0x0088:
            r0 = r4
        L_0x0089:
            java.lang.String r6 = new java.lang.String     // Catch:{ Exception -> 0x0099, all -> 0x0096 }
            r6.<init>(r2, r1, r0)     // Catch:{ Exception -> 0x0099, all -> 0x0096 }
            r3.close()     // Catch:{ Exception -> 0x0091 }
        L_0x0091:
            return r6
        L_0x0092:
            r3.close()     // Catch:{ Exception -> 0x00be }
            goto L_0x00be
        L_0x0096:
            r6 = move-exception
            r1 = r3
            goto L_0x00c1
        L_0x0099:
            r6 = move-exception
            r1 = r3
            goto L_0x009f
        L_0x009c:
            r6 = move-exception
            goto L_0x00c1
        L_0x009e:
            r6 = move-exception
        L_0x009f:
            java.lang.String r0 = "Tinker.TinkerInternals"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x009c }
            r2.<init>()     // Catch:{ all -> 0x009c }
            java.lang.String r3 = "getProcessNameInternal exception:"
            r2.append(r3)     // Catch:{ all -> 0x009c }
            java.lang.String r6 = r6.getMessage()     // Catch:{ all -> 0x009c }
            r2.append(r6)     // Catch:{ all -> 0x009c }
            java.lang.String r6 = r2.toString()     // Catch:{ all -> 0x009c }
            android.util.Log.e(r0, r6)     // Catch:{ all -> 0x009c }
            if (r1 == 0) goto L_0x00be
            r1.close()     // Catch:{ Exception -> 0x00be }
        L_0x00be:
            java.lang.String r6 = ""
            return r6
        L_0x00c1:
            if (r1 == 0) goto L_0x00c6
            r1.close()     // Catch:{ Exception -> 0x00c6 }
        L_0x00c6:
            throw r6
        L_0x00c7:
            java.lang.String r6 = ""
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.tinker.loader.shareutil.ShareTinkerInternals.k(android.content.Context):java.lang.String");
    }

    private static boolean d(String str) {
        if (str == null) {
            return false;
        }
        Matcher matcher = Pattern.compile("(\\d+)\\.(\\d+)(\\.\\d+)?").matcher(str);
        if (!matcher.matches()) {
            return false;
        }
        try {
            int parseInt = Integer.parseInt(matcher.group(1));
            int parseInt2 = Integer.parseInt(matcher.group(2));
            if (parseInt > 2 || (parseInt == 2 && parseInt2 >= 1)) {
                return true;
            }
            return false;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    private static boolean f() {
        try {
            Method declaredMethod = Class.forName("android.os.SystemProperties").getDeclaredMethod("get", new Class[]{String.class});
            String str = (String) declaredMethod.invoke((Object) null, new Object[]{"dalvik.vm.usejit"});
            return !b(str) && b((String) declaredMethod.invoke((Object) null, new Object[]{"dalvik.vm.usejitprofiles"})) && str.equals("true");
        } catch (Throwable th) {
            Log.e(f1362a, "isVmJitInternal ex:" + th);
        }
    }

    public static String a(Throwable th) {
        if (th == null) {
            return "";
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        while (true) {
            try {
                Throwable cause = th.getCause();
                if (cause == null) {
                    th.printStackTrace(printStream);
                    return c(byteArrayOutputStream.toString());
                }
                th = cause;
            } finally {
                SharePatchFileUtil.a((Object) printStream);
            }
        }
    }

    public static String c(String str) {
        char[] charArray;
        boolean z;
        if (str == null || (charArray = str.toCharArray()) == null) {
            return null;
        }
        int i2 = 0;
        while (true) {
            if (i2 >= charArray.length) {
                z = false;
                break;
            } else if (charArray[i2] > 127) {
                charArray[i2] = 0;
                z = true;
                break;
            } else {
                i2++;
            }
        }
        return z ? new String(charArray, 0, i2) : str;
    }
}
