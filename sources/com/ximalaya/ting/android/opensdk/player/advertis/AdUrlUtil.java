package com.ximalaya.ting.android.opensdk.player.advertis;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.text.TextUtils;
import com.miui.tsmclient.net.TSMAuthContants;
import com.miui.tsmclient.util.Constants;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.httputil.BaseCall;
import com.ximalaya.ting.android.opensdk.httputil.IHttpCallBack;
import com.ximalaya.ting.android.opensdk.httputil.XimalayaException;
import com.ximalaya.ting.android.opensdk.util.Logger;
import com.ximalaya.ting.android.opensdk.util.SharedPreferencesUtil;
import com.ximalaya.ting.android.player.MD5;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.Request;

public class AdUrlUtil {
    public static final int d = -1;
    public static final int e = 0;
    public static final int f = 1;
    public static String g = null;
    private static final String h = "ad_first_open_time";
    private static volatile AdUrlUtil i;

    /* renamed from: a  reason: collision with root package name */
    final Pattern f2147a = Pattern.compile("\\{\\w*\\}");
    final Pattern b = Pattern.compile("\\[\\w*\\]");
    final Pattern c = Pattern.compile("__\\w*__");
    private Map<String, String> j = new ConcurrentHashMap();
    private Context k;

    public interface Callback {
        void a(String str);
    }

    private AdUrlUtil(Context context) {
        if (context != null) {
            this.k = context.getApplicationContext();
            a();
        }
    }

    public static AdUrlUtil a(Context context) {
        if (i == null) {
            synchronized (AdUrlUtil.class) {
                if (i == null) {
                    i = new AdUrlUtil(context);
                }
            }
        }
        return i;
    }

    public void a() {
        this.j.put(TSMAuthContants.PARAM_OS, "0");
        if (this.k != null) {
            try {
                this.j.put("IMEI", MD5.a(OpenSdkUtils.c(this.k)));
            } catch (Exception e2) {
                this.j.put("IMEI", "");
                e2.printStackTrace();
            }
            String str = null;
            try {
                str = OpenSdkUtils.d(this.k);
            } catch (XimalayaException e3) {
                e3.printStackTrace();
            }
            if (!TextUtils.isEmpty(str)) {
                this.j.put("MAC1", MD5.a(str));
                this.j.put("MAC", MD5.a(str.replaceAll(":", "")));
            }
            this.j.put("ANDROIDID", MD5.a(OpenSdkUtils.a(this.k)));
            this.j.put("ANDROIDID1", OpenSdkUtils.a(this.k));
            try {
                Map<String, String> map = this.j;
                CommonRequest.a();
                map.put("UA", CommonRequest.w());
            } catch (Exception e4) {
                e4.printStackTrace();
            }
            this.j.put("OSVS", CommonRequest.a().r());
            this.j.put("TERM", Build.MODEL);
            this.j.put("APPID", "0");
            try {
                String encode = URLEncoder.encode(b(), "UTF-8");
                this.j.put("APPNAME", encode);
                this.j.put(Constants.ENTRY_TYPE_APP, encode);
                this.j.put("ANAME", encode);
            } catch (UnsupportedEncodingException e5) {
                e5.printStackTrace();
            }
            this.j.put("FIRSTOPENTIME", c());
        }
    }

    public String b() {
        PackageManager packageManager;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = this.k.getPackageManager();
            try {
                applicationInfo = packageManager.getApplicationInfo(this.k.getPackageName(), 0);
            } catch (PackageManager.NameNotFoundException unused) {
            }
        } catch (PackageManager.NameNotFoundException unused2) {
            packageManager = null;
        }
        return (String) packageManager.getApplicationLabel(applicationInfo);
    }

    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            d();
            BaseCall.a().a(new Request.Builder().url(a(str, false, false)).build(), (IHttpCallBack) null);
        }
    }

    public String b(String str) {
        try {
            Uri parse = Uri.parse(str);
            if (parse == null) {
                return str;
            }
            Set<String> queryParameterNames = parse.getQueryParameterNames();
            if (queryParameterNames == null || queryParameterNames.size() <= 0) {
                return a(str, false, false);
            }
            Uri.Builder buildUpon = Uri.parse(a(parse.buildUpon().clearQuery().build().toString(), false, false)).buildUpon();
            for (String next : queryParameterNames) {
                buildUpon.appendQueryParameter(next, a(parse.getQueryParameter(next), false, false));
            }
            return buildUpon.build().toString();
        } catch (Exception e2) {
            Logger.a(e2);
            return null;
        }
    }

    public String a(String str, boolean z, boolean z2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (z) {
            try {
                str = URLDecoder.decode(str, "utf-8");
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
        String replace = a(a(a(str, this.f2147a, 1), this.b, 1), this.c, 2).replace(" ", "");
        if (!z2) {
            return replace;
        }
        try {
            return URLEncoder.encode(replace, "utf-8");
        } catch (UnsupportedEncodingException e4) {
            e4.printStackTrace();
            return replace;
        } catch (Exception e5) {
            e5.printStackTrace();
            return replace;
        }
    }

    private String a(String str, Pattern pattern, int i2) {
        StringBuffer stringBuffer = new StringBuffer();
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            String group = matcher.group();
            if (group != null) {
                if (group.length() > i2 && i2 < group.length() - i2) {
                    String upperCase = group.substring(i2, group.length() - i2).toUpperCase();
                    String str2 = this.j.get(upperCase);
                    if (!TextUtils.isEmpty(str2)) {
                        group = str2;
                    } else if ("IMEI".equals(upperCase)) {
                        group = "";
                    }
                }
                matcher.appendReplacement(stringBuffer, group);
            }
        }
        return matcher.appendTail(stringBuffer).toString();
    }

    private String c() {
        SharedPreferencesUtil a2 = SharedPreferencesUtil.a(this.k);
        String c2 = a2.c(h);
        if (!TextUtils.isEmpty(c2)) {
            return c2;
        }
        String format = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());
        a2.a(h, format);
        return format;
    }

    private void d() {
        long currentTimeMillis = System.currentTimeMillis();
        String format = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date(currentTimeMillis));
        Map<String, String> map = this.j;
        map.put("TS", "" + currentTimeMillis);
        Map<String, String> map2 = this.j;
        map2.put("timestamp", "" + currentTimeMillis);
        Map<String, String> map3 = this.j;
        map3.put("clicktime", "" + currentTimeMillis);
        this.j.put("CLICKTIME", format);
        this.j.put("IP", b(this.k));
    }

    public static String b(Context context) {
        if (!TextUtils.isEmpty(g)) {
            return g;
        }
        int c2 = c(context);
        if (c2 == 0) {
            g = e();
        } else if (c2 == 1) {
            g = d(context);
        }
        if (TextUtils.isEmpty(g)) {
            return "192.168.1.1";
        }
        return g;
    }

    private static int c(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo networkInfo;
        if (context == null) {
            return -1;
        }
        try {
            connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        } catch (Exception unused) {
            connectivityManager = null;
        }
        if (connectivityManager == null) {
            return -1;
        }
        try {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        } catch (Exception unused2) {
            networkInfo = null;
        }
        if (networkInfo == null || !networkInfo.isAvailable()) {
            return -1;
        }
        return networkInfo.getType() == 1 ? 1 : 0;
    }

    private static String e() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (true) {
                    if (inetAddresses.hasMoreElements()) {
                        InetAddress nextElement = inetAddresses.nextElement();
                        if (!nextElement.isLoopbackAddress() && c(nextElement.getHostAddress())) {
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

    private static String d(Context context) {
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

    private static boolean c(String str) {
        if (TextUtils.isEmpty(str) || str.length() < 7 || str.length() > 15) {
            return false;
        }
        return Pattern.compile("^((\\d|\\d\\d|[0-1]\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d|\\d\\d|[0-1]\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d|\\d\\d|[0-1]\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d|\\d\\d|[0-1]\\d\\d|2[0-4]\\d|25[0-5]))$").matcher(str).find();
    }
}
