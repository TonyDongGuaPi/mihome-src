package com.ximalaya.ting.android.opensdk.player.advertis;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import com.coloros.mcssdk.c.a;
import com.taobao.weex.common.Constants;
import com.xiaomi.accountsdk.service.DeviceInfoResult;
import com.ximalaya.ting.android.opensdk.httputil.XimalayaException;
import com.ximalaya.ting.android.opensdk.util.MyAsyncTask;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class OpenSdkUtils {

    /* renamed from: a  reason: collision with root package name */
    public static String f2163a = null;
    public static final int b = 0;
    public static final int c = 1;
    public static final int d = 2;
    public static final int e = 3;
    public static final int f = -1;
    public static final int g = 0;
    public static final int h = 1;
    /* access modifiers changed from: private */
    public static String i = null;
    private static String j = "";
    private static String k = "1tyt1zuKMloXu/prwDTm5Q==";
    private static Cipher l = null;
    private static String m = "";
    private static String n;

    public static String a(Context context) {
        if (context == null) {
            return null;
        }
        if (!TextUtils.isEmpty(f2163a)) {
            return f2163a;
        }
        try {
            f2163a = Settings.Secure.getString(context.getContentResolver(), DeviceInfoResult.BUNDLE_KEY_ANDROID_ID);
            return f2163a;
        } catch (NullPointerException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String b(Context context) {
        try {
            return c(context);
        } catch (Exception unused) {
            return Constants.Name.UNDEFINED;
        }
    }

    public static String c(final Context context) throws Exception {
        AnonymousClass1 r0 = new Runnable() {
            public void run() {
                try {
                    String unused = OpenSdkUtils.i = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
                } catch (Exception unused2) {
                    String unused3 = OpenSdkUtils.i = null;
                }
            }
        };
        if (!TextUtils.isEmpty(i)) {
            MyAsyncTask.a(r0, true);
            return i;
        }
        r0.run();
        if (!TextUtils.isEmpty(i)) {
            return i;
        }
        throw new Exception("未获取到imei");
    }

    @SuppressLint({"WifiManagerLeak"})
    public static String d(Context context) throws XimalayaException {
        if (TextUtils.isEmpty(j)) {
            j = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
        }
        if (!TextUtils.isEmpty(j)) {
            return j;
        }
        throw new XimalayaException(1006, "get mac address error");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int e(android.content.Context r6) {
        /*
            java.lang.String r0 = "phone"
            java.lang.Object r6 = r6.getSystemService(r0)     // Catch:{ Exception -> 0x000d }
            android.telephony.TelephonyManager r6 = (android.telephony.TelephonyManager) r6     // Catch:{ Exception -> 0x000d }
            java.lang.String r6 = r6.getSimOperator()     // Catch:{ Exception -> 0x000d }
            goto L_0x0012
        L_0x000d:
            r6 = move-exception
            r6.printStackTrace()
            r6 = 0
        L_0x0012:
            r0 = 3
            if (r6 == 0) goto L_0x0065
            r1 = -1
            int r2 = r6.hashCode()
            r3 = 2
            r4 = 1
            r5 = 0
            switch(r2) {
                case 49679470: goto L_0x0053;
                case 49679471: goto L_0x0049;
                case 49679472: goto L_0x003f;
                case 49679473: goto L_0x0035;
                case 49679474: goto L_0x0020;
                case 49679475: goto L_0x002b;
                case 49679476: goto L_0x0020;
                case 49679477: goto L_0x0021;
                default: goto L_0x0020;
            }
        L_0x0020:
            goto L_0x005d
        L_0x0021:
            java.lang.String r2 = "46007"
            boolean r6 = r6.equals(r2)
            if (r6 == 0) goto L_0x005d
            r6 = 2
            goto L_0x005e
        L_0x002b:
            java.lang.String r2 = "46005"
            boolean r6 = r6.equals(r2)
            if (r6 == 0) goto L_0x005d
            r6 = 5
            goto L_0x005e
        L_0x0035:
            java.lang.String r2 = "46003"
            boolean r6 = r6.equals(r2)
            if (r6 == 0) goto L_0x005d
            r6 = 4
            goto L_0x005e
        L_0x003f:
            java.lang.String r2 = "46002"
            boolean r6 = r6.equals(r2)
            if (r6 == 0) goto L_0x005d
            r6 = 1
            goto L_0x005e
        L_0x0049:
            java.lang.String r2 = "46001"
            boolean r6 = r6.equals(r2)
            if (r6 == 0) goto L_0x005d
            r6 = 3
            goto L_0x005e
        L_0x0053:
            java.lang.String r2 = "46000"
            boolean r6 = r6.equals(r2)
            if (r6 == 0) goto L_0x005d
            r6 = 0
            goto L_0x005e
        L_0x005d:
            r6 = -1
        L_0x005e:
            switch(r6) {
                case 0: goto L_0x0064;
                case 1: goto L_0x0064;
                case 2: goto L_0x0064;
                case 3: goto L_0x0063;
                case 4: goto L_0x0062;
                case 5: goto L_0x0062;
                default: goto L_0x0061;
            }
        L_0x0061:
            return r0
        L_0x0062:
            return r3
        L_0x0063:
            return r4
        L_0x0064:
            return r5
        L_0x0065:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.opensdk.player.advertis.OpenSdkUtils.e(android.content.Context):int");
    }

    public static <T> boolean a(Collection<T> collection) {
        return collection == null || collection.isEmpty();
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) throws Exception {
        if (l == null) {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, a.b);
            l = Cipher.getInstance("AES/ECB/PKCS5Padding");
            l.init(2, secretKeySpec);
        }
        return l.doFinal(bArr);
    }

    public static String a(String str) throws Exception {
        return new String(a(Base64.decode(str, 0), Base64.decode(k, 0)));
    }

    public static String f(Context context) {
        String[] split;
        String g2 = g(context);
        if (TextUtils.isEmpty(g2) || (split = g2.split("\\.")) == null || split.length <= 3) {
            return g2;
        }
        StringBuilder sb = null;
        for (int i2 = 0; i2 < 3; i2++) {
            if (sb == null) {
                sb = new StringBuilder();
                sb.append(split[i2]);
            } else {
                sb.append(".");
                sb.append(split[i2]);
            }
        }
        return sb != null ? sb.toString() : g2;
    }

    public static String g(Context context) {
        PackageInfo packageInfo;
        if (!TextUtils.isEmpty(m)) {
            return m;
        }
        if (context == null) {
            return m;
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            if (!(packageManager == null || (packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0)) == null)) {
                m = packageInfo.versionName;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            m = "";
        }
        return m;
    }

    public static String h(Context context) {
        ApplicationInfo applicationInfo;
        PackageManager packageManager;
        if (context == null) {
            return null;
        }
        try {
            packageManager = context.getPackageManager();
            try {
                applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
            } catch (PackageManager.NameNotFoundException unused) {
                applicationInfo = null;
                return (String) packageManager.getApplicationLabel(applicationInfo);
            }
        } catch (PackageManager.NameNotFoundException unused2) {
            packageManager = null;
            applicationInfo = null;
            return (String) packageManager.getApplicationLabel(applicationInfo);
        }
        return (String) packageManager.getApplicationLabel(applicationInfo);
    }

    public static String i(Context context) {
        if (!TextUtils.isEmpty(n)) {
            return n;
        }
        int j2 = j(context);
        if (j2 == 0) {
            n = a();
        } else if (j2 == 1) {
            n = k(context);
        }
        if (TextUtils.isEmpty(n)) {
            return "192.168.1.1";
        }
        return n;
    }

    public static int j(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo networkInfo;
        if (context == null) {
            return -1;
        }
        try {
            connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        } catch (Exception e2) {
            e2.printStackTrace();
            connectivityManager = null;
        }
        if (connectivityManager == null) {
            return -1;
        }
        try {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        } catch (Exception unused) {
            networkInfo = null;
        }
        if (networkInfo == null || !networkInfo.isAvailable()) {
            return -1;
        }
        return networkInfo.getType() == 1 ? 1 : 0;
    }

    private static String a() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (true) {
                    if (inetAddresses.hasMoreElements()) {
                        InetAddress nextElement = inetAddresses.nextElement();
                        if (!nextElement.isLoopbackAddress() && b(nextElement.getHostAddress())) {
                            return nextElement.getHostAddress();
                        }
                    }
                }
            }
            return null;
        } catch (SocketException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static boolean b(String str) {
        if (TextUtils.isEmpty(str) || str.length() < 7 || str.length() > 15) {
            return false;
        }
        return Pattern.compile("^((\\d|\\d\\d|[0-1]\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d|\\d\\d|[0-1]\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d|\\d\\d|[0-1]\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d|\\d\\d|[0-1]\\d\\d|2[0-4]\\d|25[0-5]))$").matcher(str).find();
    }

    private static String k(Context context) {
        WifiInfo wifiInfo;
        WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
        if (wifiManager == null) {
            return "";
        }
        try {
            wifiInfo = wifiManager.getConnectionInfo();
        } catch (Throwable th) {
            th.printStackTrace();
            wifiInfo = null;
        }
        if (wifiInfo == null) {
            return "";
        }
        return a(wifiInfo.getIpAddress());
    }

    private static String a(int i2) {
        return (i2 & 255) + "." + ((i2 >> 8) & 255) + "." + ((i2 >> 16) & 255) + "." + ((i2 >> 24) & 255);
    }
}
