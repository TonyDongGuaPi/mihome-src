package a.a.a;

import android.content.Context;
import android.content.res.Resources;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.ColorRes;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.accountsdk.service.DeviceInfoResult;
import com.xiaomi.jr.mipay.common.MipayConstants;
import com.xiaomi.youpin.PackageUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class d {

    public class a implements FileFilter {
        public boolean accept(File file) {
            return Pattern.matches("cpu[0-9]", file.getName());
        }
    }

    public static int a(Context context, @ColorRes int i) {
        return Build.VERSION.SDK_INT >= 23 ? context.getResources().getColor(i, (Resources.Theme) null) : context.getResources().getColor(i);
    }

    public static long a() {
        return a("/storage") + 0 + a("/cache") + a("/system") + Environment.getDataDirectory().getTotalSpace();
    }

    public static long a(String str) {
        try {
            return new File(str).getTotalSpace();
        } catch (Exception unused) {
            return 0;
        }
    }

    public static String a(TelephonyManager telephonyManager, String str, int i) {
        return telephonyManager.getClass().getMethod(str, new Class[]{Integer.TYPE}).invoke(telephonyManager, new Object[]{Integer.valueOf(i)}).toString();
    }

    public static String a(Collection<String> collection) {
        return (collection == null || collection.isEmpty()) ? "" : a((String[]) collection.toArray(new String[collection.size()]));
    }

    public static String a(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(strArr[0]);
        for (int i = 1; i < strArr.length; i++) {
            sb.append("|");
            sb.append(strArr[i]);
        }
        return sb.toString();
    }

    public static Collection<String> a(Context context, String[] strArr) {
        HashSet hashSet = new HashSet();
        if (strArr != null) {
            Collections.addAll(hashSet, strArr);
        }
        hashSet.addAll(a(context));
        return hashSet;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:21|22|(3:24|(2:26|27)|38)(0)) */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:28|29|(2:(2:32|33)|39)(0)) */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x005a, code lost:
        if (r0.size() == 1) goto L_0x005c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x005c, code lost:
        r2 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x005d, code lost:
        if (r2 < 10) goto L_0x005f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x005f, code lost:
        r0.add(a(r1, "getDeviceIdGemini", r2));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0068, code lost:
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x006f, code lost:
        if (r0.size() == 1) goto L_0x0071;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0071, code lost:
        if (r5 < 10) goto L_0x0073;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0073, code lost:
        r0.add(a(r1, "getDeviceIdDs", r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x007c, code lost:
        r5 = r5 + 1;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0056 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:28:0x006b */
    @android.annotation.SuppressLint({"HardwareIds"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.List<java.lang.String> a(android.content.Context r5) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.String r1 = "phone"
            java.lang.Object r1 = r5.getSystemService(r1)
            android.telephony.TelephonyManager r1 = (android.telephony.TelephonyManager) r1
            int r2 = android.os.Build.VERSION.SDK_INT
            r3 = 23
            if (r2 < r3) goto L_0x0029
            int r5 = r1.getPhoneCount()     // Catch:{ Exception -> 0x0024 }
            r2 = 0
        L_0x0018:
            if (r2 >= r5) goto L_0x007f
            java.lang.String r3 = r1.getDeviceId(r2)     // Catch:{ Exception -> 0x0024 }
            r0.add(r3)     // Catch:{ Exception -> 0x0024 }
            int r2 = r2 + 1
            goto L_0x0018
        L_0x0024:
            r5 = move-exception
            r5.printStackTrace()
            goto L_0x007f
        L_0x0029:
            java.lang.String r2 = r1.getDeviceId()     // Catch:{ Exception -> 0x0024 }
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ Exception -> 0x0024 }
            if (r3 != 0) goto L_0x0037
            r0.add(r2)     // Catch:{ Exception -> 0x0024 }
            goto L_0x0044
        L_0x0037:
            android.content.ContentResolver r5 = r5.getContentResolver()     // Catch:{ Exception -> 0x0024 }
            java.lang.String r2 = "android_id"
            java.lang.String r5 = android.provider.Settings.Secure.getString(r5, r2)     // Catch:{ Exception -> 0x0024 }
            r0.add(r5)     // Catch:{ Exception -> 0x0024 }
        L_0x0044:
            r5 = 1
            r2 = 1
        L_0x0046:
            r3 = 10
            if (r2 >= r3) goto L_0x007f
            java.lang.String r4 = "getDeviceId"
            java.lang.String r4 = a(r1, r4, r2)     // Catch:{ Exception -> 0x0056 }
            r0.add(r4)     // Catch:{ Exception -> 0x0056 }
            int r2 = r2 + 1
            goto L_0x0046
        L_0x0056:
            int r2 = r0.size()     // Catch:{ Exception -> 0x006b }
            if (r2 != r5) goto L_0x007f
            r2 = 1
        L_0x005d:
            if (r2 >= r3) goto L_0x007f
            java.lang.String r4 = "getDeviceIdGemini"
            java.lang.String r4 = a(r1, r4, r2)     // Catch:{ Exception -> 0x006b }
            r0.add(r4)     // Catch:{ Exception -> 0x006b }
            int r2 = r2 + 1
            goto L_0x005d
        L_0x006b:
            int r2 = r0.size()     // Catch:{ Exception -> 0x007f }
            if (r2 != r5) goto L_0x007f
        L_0x0071:
            if (r5 >= r3) goto L_0x007f
            java.lang.String r2 = "getDeviceIdDs"
            java.lang.String r2 = a(r1, r2, r5)     // Catch:{ Exception -> 0x007f }
            r0.add(r2)     // Catch:{ Exception -> 0x007f }
            int r5 = r5 + 1
            goto L_0x0071
        L_0x007f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: a.a.a.d.a(android.content.Context):java.util.List");
    }

    public static void a(String str, String str2) {
        Log.d(str, "log: " + str2);
    }

    public static String b() {
        String e = e();
        if (!TextUtils.isEmpty(e)) {
            return e;
        }
        String d = d();
        if (!TextUtils.isEmpty(d)) {
            return d;
        }
        String c = c();
        return !TextUtils.isEmpty(c) ? c : "NA";
    }

    public static String b(Context context) {
        String g = g();
        if (!TextUtils.isEmpty(g)) {
            return g;
        }
        String c = c(context);
        if (!TextUtils.isEmpty(c)) {
            return c;
        }
        String d = d(context);
        if (!TextUtils.isEmpty(d)) {
            return d;
        }
        String f = f();
        return !TextUtils.isEmpty(f) ? f : "NA";
    }

    public static String b(String str) {
        File file = new File("/proc/cpuinfo");
        if (!file.exists()) {
            return "NA";
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    String[] split = readLine.split(":");
                    if (split.length > 1 && split[0].trim().equalsIgnoreCase(str.trim())) {
                        return split[1].trim();
                    }
                } else {
                    bufferedReader.close();
                    return "NA";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "NA";
        }
    }

    public static String c() {
        String str = Build.TAGS;
        return str != null && str.contains("test-keys") ? "test-keys" : "";
    }

    public static String c(Context context) {
        return TextUtils.isEmpty(Settings.Secure.getString(context.getContentResolver(), DeviceInfoResult.BUNDLE_KEY_ANDROID_ID)) ? "Device Id empty" : Build.PRODUCT.equals("google_sdk") ? "google_sdk" : Build.PRODUCT.equals("sdk") ? "sdk" : "";
    }

    public static String d() {
        for (String str : new String[]{"/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su"}) {
            if (new File(str).exists()) {
                return str;
            }
        }
        return "";
    }

    public static String d(Context context) {
        return ((SensorManager) context.getSystemService("sensor")).getSensorList(-1).isEmpty() ? "Sensor Manager not found" : "NA";
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0062 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String e() {
        /*
            r0 = 3
            java.lang.String[][] r0 = new java.lang.String[r0][]
            r1 = 2
            java.lang.String[] r2 = new java.lang.String[r1]
            java.lang.String r3 = "/system/xbin/which"
            r4 = 0
            r2[r4] = r3
            java.lang.String r3 = "su"
            r5 = 1
            r2[r5] = r3
            r0[r4] = r2
            java.lang.String[] r2 = new java.lang.String[r1]
            java.lang.String r3 = "/system/bin/which"
            r2[r4] = r3
            java.lang.String r3 = " su"
            r2[r5] = r3
            r0[r5] = r2
            java.lang.String[] r2 = new java.lang.String[r1]
            java.lang.String r3 = "which"
            r2[r4] = r3
            java.lang.String r3 = "su"
            r2[r5] = r3
            r0[r1] = r2
            int r1 = r0.length
            r2 = 0
        L_0x002f:
            if (r4 >= r1) goto L_0x0066
            r3 = r0[r4]
            java.lang.Runtime r5 = java.lang.Runtime.getRuntime()     // Catch:{ all -> 0x005c }
            java.lang.Process r5 = r5.exec(r3)     // Catch:{ all -> 0x005c }
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ all -> 0x005d }
            java.io.InputStreamReader r6 = new java.io.InputStreamReader     // Catch:{ all -> 0x005d }
            java.io.InputStream r7 = r5.getInputStream()     // Catch:{ all -> 0x005d }
            r6.<init>(r7)     // Catch:{ all -> 0x005d }
            r2.<init>(r6)     // Catch:{ all -> 0x005d }
            java.lang.String r2 = r2.readLine()     // Catch:{ all -> 0x005d }
            if (r2 == 0) goto L_0x0059
            java.lang.String r2 = a((java.lang.String[]) r3)     // Catch:{ all -> 0x005d }
            if (r5 == 0) goto L_0x0058
            r5.destroy()
        L_0x0058:
            return r2
        L_0x0059:
            if (r5 == 0) goto L_0x0062
            goto L_0x005f
        L_0x005c:
            r5 = r2
        L_0x005d:
            if (r5 == 0) goto L_0x0062
        L_0x005f:
            r5.destroy()
        L_0x0062:
            r2 = r5
            int r4 = r4 + 1
            goto L_0x002f
        L_0x0066:
            java.lang.String r0 = ""
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: a.a.a.d.e():java.lang.String");
    }

    public static String f() {
        String property = System.getProperty("os.version");
        return property != null && property.contains(PackageUtils.c) ? PackageUtils.c : "";
    }

    public static String g() {
        File file = new File(Environment.getExternalStorageDirectory().toString() + File.separatorChar + "windows" + File.separatorChar + "BstSharedFolder");
        return file.exists() ? file.getAbsolutePath() : "";
    }

    public static double h() {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile("/proc/meminfo", "r");
            Matcher matcher = Pattern.compile("(\\d+)").matcher(randomAccessFile.readLine());
            String str = "";
            while (matcher.find()) {
                str = matcher.group(1);
            }
            randomAccessFile.close();
            return Double.parseDouble(str);
        } catch (IOException e) {
            e.printStackTrace();
            return 0.0d;
        }
    }

    public static String i() {
        return b(MipayConstants.S);
    }

    public static int j() {
        try {
            return Runtime.getRuntime().availableProcessors();
        } catch (Throwable unused) {
            return 0;
        }
    }

    public static int k() {
        File file = new File("/proc/cpuinfo");
        if (!file.exists()) {
            return 0;
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            int i = 0;
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    String[] split = readLine.split(":");
                    if (split.length > 1 && split[0].trim().equals("processor")) {
                        try {
                            Integer.parseInt(split[1].trim());
                            i++;
                        } catch (Throwable unused) {
                        }
                    }
                } else {
                    bufferedReader.close();
                    return i;
                }
            }
        } catch (Throwable unused2) {
            return 0;
        }
    }

    public static int l() {
        try {
            return new File("/sys/devices/system/cpu/").listFiles(new a()).length;
        } catch (Throwable unused) {
            return 0;
        }
    }
}
