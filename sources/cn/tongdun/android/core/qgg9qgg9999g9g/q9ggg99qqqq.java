package cn.tongdun.android.core.qgg9qgg9999g9g;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.net.TrafficStats;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Debug;
import android.os.Environment;
import android.os.Looper;
import android.os.Process;
import android.os.StatFs;
import android.os.SystemClock;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import cn.com.fmsh.tsm.business.constants.Constants;
import cn.tongdun.android.core.O0o0o0o0o;
import cn.tongdun.android.core.q9gqqq99999qq.qgg99qqg9gq;
import cn.tongdun.android.core.q9q99gq99gggqg9qqqgg.g9q9q9g9;
import cn.tongdun.android.core.q9q99gq99gggqg9qqqgg.gqgqgqq9gq9q9q9;
import cn.tongdun.android.core.q9q99gq99gggqg9qqqgg.q9gqqq99999qq;
import cn.tongdun.android.core.q9q99gq99gggqg9qqqgg.q9q99gq99gggqg9qqqgg;
import cn.tongdun.android.core.q9q99gq99gggqg9qqqgg.qgggqg999gg9qqggq;
import cn.tongdun.android.core.q9q99gq99gggqg9qqqgg.qq9gq9g9g99;
import cn.tongdun.android.core.q9q99gq99gggqg9qqqgg.qq9q9ggg;
import cn.tongdun.android.core.q9q99gq99gggqg9qqqgg.qqg9qgqqqgqqgg9qqqqq;
import cn.tongdun.android.core.q9q99gq99gggqg9qqqgg.qqq9gg9gqq9qgg99q;
import cn.tongdun.android.core.qqq9gg9gqq9qgg99q.gqg9qq9gqq9q9q;
import cn.tongdun.android.core.qqq9gg9gqq9qgg99q.qgg9qgg9999g9g;
import cn.tongdun.android.shell.common.HelperJNI;
import cn.tongdun.android.shell.utils.BoxUtil;
import cn.tongdun.android.shell.utils.LogUtil;
import com.alipay.mobile.common.logging.api.LogCategory;
import com.amap.location.common.model.AmapLoc;
import com.xiaomi.accountsdk.service.DeviceInfoResult;
import com.xiaomi.verificationsdk.internal.Constants;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeSet;
import org.json.JSONException;
import org.json.JSONObject;

public class q9ggg99qqqq {
    public static int g999gqq9ggqgqq = 0;
    public static final String g9q9q9g9 = gqg9qq9gqq9q9q("094770071f3513270b", 52);
    public static final String gqg9qq9gqq9q9q = gqg9qq9gqq9q9q("093f707f", 76);
    private static final String gqgqgqq9gq9q9q9 = gqg9qq9gqq9q9q("270e", 125);
    public static final String q9gqqq99999qq = gqg9qq9gqq9q9q("1e1e651f492950", 122);
    public static String q9q99gq99gggqg9qqqgg = null;
    public static final String q9qq99qg9qqgqg9gqgg9 = gqg9qq9gqq9q9q("182561", 91);
    private static final String qgg99qqg9gq = gqg9qq9gqq9q9q("18096b1f76197b533c5426452d5f77107507680169410542104201550a5316581f57165517", 117);
    public static final String qgg9qgg9999g9g = gqg9qq9gqq9q9q("0d596f07", 43);
    private static final String[] qq9q9ggg = {gqg9qq9gqq9q9q("567a257022612a236926626b216e2a6f165d1a501a53166f2d6e2a79384116551e4d451059", 85), gqg9qq9gqq9q9q("5621252b79756d7d75575e514d534b", 14), gqg9qq9gqq9q9q("5646254c225d2a1f671460553e4136596e046c19731a", 105), gqg9qq9gqq9q9q("566f2e7c77206b2c632272792c6d247535", 87), gqg9qq9gqq9q9q("56152e06775863507b7a54634d76", 45), gqg9qq9gqq9q9q("56582e4b77176b1b6315724e3a4c315b2c", 96), gqg9qq9gqq9q9q("566f2e7c77206b2c632272793f7a2d6c2a6f25651e5d1c560b4b", 87), gqg9qq9gqq9q9q("565b2551791a7809670361153d49374330523810740371593d4a380f27433446275230466e1a7e18731d74144c285a2f7716630177", 116)};
    public static final String qqq9gg9gqq9qgg99q = gqg9qq9gqq9q9q("0d556f0b00390c2b14", 39);

    static String gqg9qq9gqq9q9q() {
        return gqg9qq9gqq9q9q(qgg9qgg9999g9g.gqg9qq9gqq9q9q, qgg9qgg9999g9g.qgg9qgg9999g9g);
    }

    static String gqg9qq9gqq9q9q(Context context) {
        return gqg9qq9gqq9q9q(qq9q9ggg.gqg9qq9gqq9q9q(context), Integer.valueOf(qq9q9ggg.gqg9qq9gqq9q9q()));
    }

    static String qgg9qgg9999g9g() {
        int i = Build.VERSION.SDK_INT;
        String str = Build.VERSION.RELEASE;
        String str2 = Build.MODEL;
        if (str2 != null && str2.length() > 30) {
            str2 = str2.substring(0, 30);
        }
        String str3 = Build.PRODUCT;
        String str4 = Build.BRAND;
        if (str4 != null && str4.length() > 30) {
            str4 = str4.substring(0, 30);
        }
        String str5 = Build.SERIAL;
        String str6 = Build.DISPLAY;
        String str7 = Build.HOST;
        String str8 = Build.DEVICE;
        String str9 = Build.HARDWARE;
        if (str9 != null && str9.length() > 30) {
            str9 = str9.substring(0, 30);
        }
        return gqg9qq9gqq9q9q(Integer.valueOf(i), str, str2, str3, str4, str5, str6, str7, str8, str9, Build.TAGS, Build.FINGERPRINT);
    }

    static String gqg9qq9gqq9q9q(Context context, TelephonyManager telephonyManager) {
        if (!gqgqgqq9gq9q9q9.gqg9qq9gqq9q9q(context, "android.permission.READ_PHONE_STATE") && ((String) qq9q9ggg.gqg9qq9gqq9q9q(context, gqg9qq9gqq9q9q("0d443d19251e24151e39192c0c3d", 39), (Object) "")) == "") {
            try {
                Thread.sleep(500);
                qq9q9ggg.gqg9qq9gqq9q9q(context, gqg9qq9gqq9q9q("0d743d29252e24251e09191c0c0d", 23), gqg9qq9gqq9q9q("48", 115));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (!gqgqgqq9gq9q9q9.gqg9qq9gqq9q9q(context, "android.permission.READ_PHONE_STATE")) {
            return null;
        }
        String deviceSoftwareVersion = telephonyManager.getDeviceSoftwareVersion();
        Map gqg9qq9gqq9q9q2 = qq9g9qqqq9qqgg9q9.gqg9qq9gqq9q9q(context, telephonyManager);
        return gqg9qq9gqq9q9q(gqg9qq9gqq9q9q2.get(qq9g9qqqq9qqgg9q9.qgg9qgg9999g9g[1]), gqg9qq9gqq9q9q2.get(qq9g9qqqq9qqgg9q9.qgg9qgg9999g9g[2]), gqg9qq9gqq9q9q2.get(qq9g9qqqq9qqgg9q9.qgg9qgg9999g9g[3]), gqg9qq9gqq9q9q2.get(qq9g9qqqq9qqgg9q9.qgg9qgg9999g9g[4]), gqg9qq9gqq9q9q2.get(qq9g9qqqq9qqgg9q9.qgg9qgg9999g9g[5]), gqg9qq9gqq9q9q2.get(qq9g9qqqq9qqgg9q9.qgg9qgg9999g9g[6]), gqg9qq9gqq9q9q2.get(qq9g9qqqq9qqgg9q9.qgg9qgg9999g9g[7]), gqg9qq9gqq9q9q2.get(qq9g9qqqq9qqgg9q9.qgg9qgg9999g9g[8]), gqg9qq9gqq9q9q2.get(qq9g9qqqq9qqgg9q9.qgg9qgg9999g9g[9]), gqg9qq9gqq9q9q2.get(qq9g9qqqq9qqgg9q9.qgg9qgg9999g9g[10]), gqg9qq9gqq9q9q2.get(qq9g9qqqq9qqgg9q9.qgg9qgg9999g9g[11]), gqg9qq9gqq9q9q2.get(qq9g9qqqq9qqgg9q9.qgg9qgg9999g9g[12]), deviceSoftwareVersion);
    }

    static String gqg9qq9gqq9q9q(Context context, WifiManager wifiManager) {
        int i;
        int i2;
        String str = "";
        String str2 = "";
        String str3 = "";
        String str4 = "";
        if (!gqgqgqq9gq9q9q9.gqg9qq9gqq9q9q(context, "android.permission.ACCESS_WIFI_STATE") || !wifiManager.isWifiEnabled()) {
            i2 = 0;
            i = 0;
        } else {
            WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            DhcpInfo dhcpInfo = wifiManager.getDhcpInfo();
            str = qgggqg999gg9qqggq.gqg9qq9gqq9q9q(connectionInfo.getIpAddress());
            if (gqgqgqq9gq9q9q9.gqg9qq9gqq9q9q(23)) {
                str2 = connectionInfo.getMacAddress();
            } else {
                str2 = g9g9g99.gqg9qq9gqq9q9q(str);
            }
            str3 = connectionInfo.getSSID();
            str4 = connectionInfo.getBSSID();
            i2 = dhcpInfo.gateway;
            i = dhcpInfo.netmask;
        }
        if (qgggqg999gg9qqggq.gqg9qq9gqq9q9q(str2)) {
            String gqg9qq9gqq9q9q2 = gqg9qq9gqq9q9q("56332539797576786478383933286870737d7c23636d666d707a667a", 28);
            String gqg9qq9gqq9q9q3 = gqg9qq9gqq9q9q("564b2541790d7600640038413350681a790621196f1c6f0a781c78", 100);
            str2 = qqq9gg9gqq9qgg99q.gqg9qq9gqq9q9q(gqg9qq9gqq9q9q2);
            if (qgggqg999gg9qqggq.gqg9qq9gqq9q9q(str2)) {
                str2 = qqq9gg9gqq9qgg99q.gqg9qq9gqq9q9q(gqg9qq9gqq9q9q3);
            }
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = q9gqqq99999qq.gqg9qq9gqq9q9q();
        }
        return gqg9qq9gqq9q9q(str, str2, str3, str4, Integer.valueOf(i2), Integer.valueOf(i));
    }

    static String qgg9qgg9999g9g(Context context) {
        String str;
        int i;
        if (gqgqgqq9gq9q9q9.qgg9qgg9999g9g(14)) {
            str = System.getProperty(gqg9qq9gqq9q9q("1113681736493454235512720e75", 124));
            String property = System.getProperty(gqg9qq9gqq9q9q("112b682f3671346c236d0a521754", 68));
            if (property == null || "".equals(property)) {
                property = gqg9qq9gqq9q9q("544c", 35);
            }
            i = Integer.parseInt(property);
        } else {
            String host = Proxy.getHost(context);
            i = Proxy.getPort(context);
            str = host;
        }
        if (qgggqg999gg9qqggq.gqg9qq9gqq9q9q(str)) {
            return "";
        }
        return str + gqg9qq9gqq9q9q("43", 47) + i;
    }

    static String gqg9qq9gqq9q9q(Context context, ConnectivityManager connectivityManager, WifiManager wifiManager) {
        return g9g9g99.gqg9qq9gqq9q9q(context, connectivityManager, wifiManager);
    }

    static cn.tongdun.android.core.q9gqqq99999qq.qgggqg999gg9qqggq qgg9qgg9999g9g(Context context, WifiManager wifiManager) {
        if (gqg9qq9gqq9q9q.q9q99gq99gggqg9qqqgg) {
            return null;
        }
        if (!gqgqgqq9gq9q9q9.gqg9qq9gqq9q9q(context, "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION")) {
            return null;
        }
        try {
            List<ScanResult> scanResults = wifiManager.getScanResults();
            int size = scanResults.size();
            if (scanResults == null || size == 0) {
                return new cn.tongdun.android.core.q9gqqq99999qq.qgggqg999gg9qqggq();
            }
            if (size > 10) {
                size = 10;
            }
            cn.tongdun.android.core.q9gqqq99999qq.qgggqg999gg9qqggq qgggqg999gg9qqggq = new cn.tongdun.android.core.q9gqqq99999qq.qgggqg999gg9qqggq();
            for (int i = 0; i < size; i++) {
                qgggqg999gg9qqggq.gqg9qq9gqq9q9q(scanResults.get(i).BSSID, scanResults.get(i).SSID, scanResults.get(i).level);
            }
            return qgggqg999gg9qqggq;
        } catch (Exception unused) {
            return null;
        }
    }

    static String q9qq99qg9qqgqg9gqgg9(Context context) {
        String str;
        String str2 = "";
        String str3 = "";
        String str4 = "";
        Map gqg9qq9gqq9q9q2 = g9g9g99.gqg9qq9gqq9q9q(context, gqg9qq9gqq9q9q, qgg9qgg9999g9g);
        if (gqg9qq9gqq9q9q2 != null && !gqg9qq9gqq9q9q2.isEmpty()) {
            str3 = (String) gqg9qq9gqq9q9q2.get(gqg9qq9gqq9q9q);
            str4 = (String) gqg9qq9gqq9q9q2.get(qgg9qgg9999g9g);
        }
        if (!(str3 == null && str4 == null)) {
            if (qgggqg999gg9qqggq.gqg9qq9gqq9q9q(str3)) {
                str3 = "";
            }
            if (qgggqg999gg9qqggq.gqg9qq9gqq9q9q(str3)) {
                str2 = qgggqg999gg9qqggq.gqg9qq9gqq9q9q(str4) ? "" : str4;
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(str3);
                if (qgggqg999gg9qqggq.gqg9qq9gqq9q9q(str4)) {
                    str = "";
                } else {
                    str = gqg9qq9gqq9q9q("55", 103) + str4;
                }
                sb.append(str);
                str2 = sb.toString();
            }
        }
        String str5 = "";
        if (!qgggqg999gg9qqggq.gqg9qq9gqq9q9q(str2)) {
            String str6 = (String) gqg9qq9gqq9q9q2.get(g9q9q9g9);
            String str7 = (String) gqg9qq9gqq9q9q2.get(qqq9gg9gqq9qgg99q);
            if (qgggqg999gg9qqggq.gqg9qq9gqq9q9q(str6)) {
                str6 = "";
            }
            if (qgggqg999gg9qqggq.gqg9qq9gqq9q9q(str7)) {
                str7 = "";
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str6);
            if (!qgggqg999gg9qqggq.gqg9qq9gqq9q9q(str6)) {
                if (qgggqg999gg9qqggq.gqg9qq9gqq9q9q(str7)) {
                    str7 = "";
                } else {
                    str7 = gqg9qq9gqq9q9q("55", 24) + str7;
                }
            }
            sb2.append(str7);
            str5 = sb2.toString();
        }
        return gqg9qq9gqq9q9q(str2, str5);
    }

    static String q9gqqq99999qq(Context context) {
        Map gqg9qq9gqq9q9q2 = g9g9g99.gqg9qq9gqq9q9q(context, q9qq99qg9qqgqg9gqgg9);
        return (gqg9qq9gqq9q9q2 == null || gqg9qq9gqq9q9q2.isEmpty()) ? "" : (String) gqg9qq9gqq9q9q2.get(q9qq99qg9qqgqg9gqgg9);
    }

    static String gqg9qq9gqq9q9q(Context context, ConnectivityManager connectivityManager) {
        String gqg9qq9gqq9q9q2;
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        String str = "";
        String str2 = "";
        if (activeNetworkInfo != null) {
            if (activeNetworkInfo.getType() == 0) {
                Map gqg9qq9gqq9q9q3 = g9g9g99.gqg9qq9gqq9q9q(context, q9gqqq99999qq);
                if (gqg9qq9gqq9q9q3 != null && !gqg9qq9gqq9q9q3.isEmpty()) {
                    str2 = (String) gqg9qq9gqq9q9q3.get(q9gqqq99999qq);
                }
                switch (activeNetworkInfo.getSubtype()) {
                    case 1:
                    case 2:
                    case 4:
                    case 11:
                    case 16:
                    case 17:
                        gqg9qq9gqq9q9q2 = gqg9qq9gqq9q9q("4b6b", 109);
                        break;
                    case 3:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                    case 14:
                    case 15:
                        gqg9qq9gqq9q9q2 = gqg9qq9gqq9q9q("4a5a", 93);
                        break;
                    case 13:
                        gqg9qq9gqq9q9q2 = gqg9qq9gqq9q9q("4d40", 64);
                        break;
                    case 18:
                        gqg9qq9gqq9q9q2 = gqg9qq9gqq9q9q("3052525f5d", 63);
                        break;
                    default:
                        gqg9qq9gqq9q9q2 = gqg9qq9gqq9q9q("2c715074516c48", 25);
                        break;
                }
                str = gqg9qq9gqq9q9q2;
            } else {
                str = activeNetworkInfo.getTypeName();
            }
        }
        return gqg9qq9gqq9q9q(str2, str);
    }

    static String q9qq99qg9qqgqg9gqgg9() {
        return gqg9qq9gqq9q9q(Long.valueOf(System.currentTimeMillis()), Long.valueOf(SystemClock.elapsedRealtime()), Long.valueOf(SystemClock.uptimeMillis()));
    }

    static boolean q9gqqq99999qq() {
        String[] strArr = {gqg9qq9gqq9q9q("565d29", 114), gqg9qq9gqq9q9q("5626297c647763363f30", 9), gqg9qq9gqq9q9q("56703e7b393a653c", 95), gqg9qq9gqq9q9q("564d2a583f167c1570177d542a4e2149601566", 117), gqg9qq9gqq9q9q("56262a333f7d7c7e707c7d3f303437756b73", 30), gqg9qq9gqq9q9q("56572a423f0c7c0f700d7d4e2148", 111), gqg9qq9gqq9q9q("5605250f221e2a5c7d467641371d31", 42), gqg9qq9gqq9q9q("5624252e223f2a7d677660373c31", 11), gqg9qq9gqq9q9q("564d254722562a1476033d54275f201e7c18", 98), gqg9qq9gqq9q9q("5651255b224a2a0867036042294521403e523951730d75", 126)};
        for (String file : strArr) {
            File file2 = new File(file);
            if (file2.exists() && !file2.isDirectory() && file2.canExecute()) {
                return true;
            }
        }
        return false;
    }

    static String g9q9q9g9(Context context) {
        String[] strArr = {gqg9qq9gqq9q9q("1a0a61492e452a0260137a0f7c0a680f7d1e6a42315e", 117), gqg9qq9gqq9q9q("1a2661652e692a2e68237c387133743b653d78", 89), gqg9qq9gqq9q9q("1a59611a2e162a51775b605667477d5b7d4d6a4975437355", 38), gqg9qq9gqq9q9q("1a1a61592d573447224037423f5a621c651e6d", 101), gqg9qq9gqq9q9q("1a3661752d7b346b226c376e3f76622f7c2876236e277b287e21762d79227f2068", 73), gqg9qq9gqq9q9q("1a7161322d3c342c222b37293f31626c747d7460736967", 14), gqg9qq9gqq9q9q("1a6d612e2d203430223737353f2d62616c787c6e7b7b7973616c626060757d737c6d7e70676f6a6e7d", 18), gqg9qq9gqq9q9q("1a28616b2d653475227237703f6862277327703161317c367522", 87), gqg9qq9gqq9q9q("1a7561362d383428222f372d3f356279737e6177627f6368", 10), gqg9qq9gqq9q9q("1a6761242d2a343a223d373f3f27624b534c646066606a6f605b42464e405840445d", 24), gqg9qq9gqq9q9q("1a4a61092d073417221037123f0a62572315", 53), gqg9qq9gqq9q9q("1a2361602d6e347e2279377b3f63623e742f752a", 92), gqg9qq9gqq9q9q("1a346177247d2a622f272f26307c2d74226e3f66307b266c227f35", 75), gqg9qq9gqq9q9q("1a766135242e2e2773617e68627d756c736769767e", 9), gqg9qq9gqq9q9q("1a736130243a2a252f602f61303b2d3322293f213026342e", 12), gqg9qq9gqq9q9q("1a48610b2e0424123914345e7e5f6d4c644f7b52735969724564546449634077", 55), gqg9qq9gqq9q9q("1a7361302e3f2429392f34657e646d7764747b69736269", 12), gqg9qq9gqq9q9q("1a1661552e5a244c394a340079167f11641365106912641b33", 105), gqg9qq9gqq9q9q("1a3d617e2a63336f2e732738683762217f27726d3d7c2466", 66), gqg9qq9gqq9q9q("1a0f614c284e2359375b2c462a476a1a6c1f79087e0e6819", 112), gqg9qq9gqq9q9q("1a7461372835232237202c3d2a3c6a7165747c667168627e767c6b6b6a", 11), gqg9qq9gqq9q9q("1a0161423a54770277007e5d3b5327482a463950", 126), gqg9qq9gqq9q9q("1a7761343a22777477767e", 8), gqg9qq9gqq9q9q("1a6b61282d2634367f7062646d6d3b6b222424262f3c", 20), gqg9qq9gqq9q9q("1a4f610c2d0234127f5462406d493b4f220d2f1934143f1928", 48), gqg9qq9gqq9q9q("1a4c610f2d0134117f5762436d4a3b4c220b2603", 51), gqg9qq9gqq9q9q("1a69612a222e283f35262a3d2c676575797878", 22), gqg9qq9gqq9q9q("1a3e617d2279286835712a6a2c306f3c7c257234", 65), gqg9qq9gqq9q9q("1a5c611f221b280a35132a082c526f5a67427b4673", 35), gqg9qq9gqq9q9q("1a2b236c3e773e74212979323a7037642c69276430", 85), gqg9qq9gqq9q9q("1a7423333e283e2b2176796d3a33382e2f2f", 10), gqg9qq9gqq9q9q("1a1e23593e423e41211c79073a5a2653344c3a50215d2f4e39", 96), gqg9qq9gqq9q9q("1a4523023e193e1a21476e507c59704a694478", 59), gqg9qq9gqq9q9q("1a6261213c2b216146654e", 29), gqg9qq9gqq9q9q("1a3161722d7923702267742865283b753c6e21796a3f66", 78), gqg9qq9gqq9q9q("1a4e610d2d06230f2218745a794e6243694e7e", 49), gqg9qq9gqq9q9q("1a68612b2d202329223e747165713b2f23282223", 23), gqg9qq9gqq9q9q("1a1261512d5a23532244740b650b3b4b3a5c314a264e39443f", 109), gqg9qq9gqq9q9q("1a0b61482e4724513957341d741c631466127d", 116), gqg9qq9gqq9q9q("1a676124272d2f29233f3530756e70686b", 24), gqg9qq9gqq9q9q("141026562f5e2b526414691d750e730569147e", 107), gqg9qq9gqq9q9q("145f26192f112b1d64526b587d457b487e5a7e406447", 36), gqg9qq9gqq9q9q("1a3e617d38633f6929743f237d2e6935643e6929", 65), gqg9qq9gqq9q9q("1a566115380b3f01291c3f4b624d6758705f764961", 41), gqg9qq9gqq9q9q("1a616122383c3f36292b3f7c7d71696a64616976", 30), gqg9qq9gqq9q9q("1a0e614d38533f5929443f13781770", 113), gqg9qq9gqq9q9q("1a0f614c2e43245539533419731e690f7e13711e33543e4e3d52304a670771066a1c6b46204e3857354228442904620c7a497b", 112), gqg9qq9gqq9q9q("1a6e612d2e222434393234787f7067696a7c71612d3c333e37302621302637", 17), gqg9qq9gqq9q9q("1a4461072805231237102c0d2a0c6a45684e7f53", 59), gqg9qq9gqq9q9q("1a066145244f2a502f152f14304e2d46225c3f543053345b", 121), gqg9qq9gqq9q9q("1a7d613e3b23332c2931213e61796571", 2), gqg9qq9gqq9q9q("1a116152224a3a5261106c0477097c046b", 110), gqg9qq9gqq9q9q("1325682d733c7e7e31713b6726612b713a6625", 93), gqg9qq9gqq9q9q("146226202f7d3932363820252628233a23203927", 25), gqg9qq9gqq9q9q("1a726131372a2436292e7e7865776863736e78636f", 13), gqg9qq9gqq9q9q("1a2e616d2e6224622f6839357b386f2362286f3f", 81), gqg9qq9gqq9q9q("161b7a523347244331512556394b63046c0e71593e483e553c543b492054", 117), gqg9qq9gqq9q9q("160c7a453350245431462541395c63136c19714e364a3e", 98)};
        ArrayList arrayList = new ArrayList();
        PackageManager packageManager = context.getPackageManager();
        for (int i = 0; i < strArr.length; i++) {
            try {
                packageManager.getInstallerPackageName(strArr[i]);
                arrayList.add(Integer.valueOf(i));
            } catch (IllegalArgumentException unused) {
            }
        }
        if (arrayList.isEmpty()) {
            return "";
        }
        if (arrayList.size() <= 10) {
            return arrayList.toString();
        }
        g999gqq9ggqgqq |= 1;
        return "";
    }

    static String qqq9gg9gqq9qgg99q(Context context) {
        return context.getFilesDir().getAbsolutePath();
    }

    static int q9q99gq99gggqg9qqqgg(Context context) {
        int q9qq99qg9qqgqg9gqgg92 = g9g9g99.q9qq99qg9qqgqg9gqgg9();
        if ((g999gqq9ggqgqq & 1) == 1) {
            return q9qq99qg9qqgqg9gqgg92 | 0;
        }
        int q9gqqq99999qq2 = g9g9g99.q9gqqq99999qq(context);
        return q9gqqq99999qq2 | 0 | g9g9g99.gqg9qq9gqq9q9q() | g9g9g99.qgg9qgg9999g9g() | q9qq99qg9qqgqg9gqgg92;
    }

    static String g9q9q9g9() {
        return O0o0o0o0o.o0o0oO;
    }

    static String qqq9gg9gqq9qgg99q() {
        return O0o0o0o0o.o0o0ooOo0;
    }

    static String q9q99gq99gggqg9qqqgg() {
        return O0o0o0o0o.o0o0oO0;
    }

    static String g999gqq9ggqgqq() {
        HelperJNI.exprot(5, 1);
        return O0o0o0o0o.o0o0oooo;
    }

    static String gqgqgqq9gq9q9q9() {
        HelperJNI.exprot(6, 1);
        return O0o0o0o0o.o0ooo;
    }

    static String qgg99qqg9gq() throws Throwable {
        String str;
        Field field;
        boolean z;
        Method method;
        Class<?> cls;
        Object[] objArr;
        try {
            Class<?> loadClass = ClassLoader.getSystemClassLoader().loadClass(gqg9qq9gqq9q9q("1d172f4b3246261e691163077e01734b25433a5f2c5e66284e3752215307631c6e1f6c", 101));
            Field[] declaredFields = loadClass.getDeclaredFields();
            int length = declaredFields.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    field = null;
                    break;
                }
                Field field2 = declaredFields[i];
                if (gqg9qq9gqq9q9q("0a6854685066514f795e65596e7e4c734c7d4f7f4767", 32).equals(field2.getName())) {
                    field = loadClass.getDeclaredField(gqg9qq9gqq9q9q("0a7b547b5075515c794d654a6e6d4c604c6e4f6c4774", 51));
                    break;
                } else if (gqg9qq9gqq9q9q("1156685266534f7b5e67596c7e4e734e7d4d7f4567", 34).equals(field2.getName())) {
                    field = loadClass.getDeclaredField(gqg9qq9gqq9q9q("1102680666074f2f5e3359387e1a731a7d197f1167", 118));
                    z = true;
                    break;
                } else {
                    i++;
                }
            }
            z = false;
            if (field != null) {
                field.setAccessible(true);
            }
            Map map = (Map) field.get((Object) null);
            if (!z) {
                cls = ClassLoader.getSystemClassLoader().loadClass(gqg9qq9gqq9q9q("1d4a2f16321b2643694c635a7e5c7316251e3a022c0366754e6a527c535a63416e426c030b2f142622071b22003f11092d142b052a321c23", 56));
                method = cls.getDeclaredMethod(gqg9qq9gqq9q9q("1e3776104b1f5a1c411b5a", 70), new Class[0]);
            } else {
                cls = null;
                method = null;
            }
            qgg99qqg9gq qgg99qqg9gq2 = new qgg99qqg9gq();
            qgg99qqg9gq2.gqg9qq9gqq9q9q();
            for (Map.Entry entry : map.entrySet()) {
                Object value = entry.getValue();
                int gqg9qq9gqq9q9q2 = qgg99qqg9gq.gqg9qq9gqq9q9q(((Member) entry.getKey()).toString());
                if (gqg9qq9gqq9q9q2 != -1) {
                    if (cls == null || !cls.isInstance(value)) {
                        objArr = TreeSet.class.isInstance(value) ? ((TreeSet) value).toArray() : null;
                    } else {
                        objArr = (Object[]) method.invoke(value, new Object[0]);
                    }
                    for (Object obj : objArr) {
                        obj.getClass().getName();
                        String obj2 = obj.getClass().getClassLoader().toString();
                        String str2 = "\"";
                        if (obj2.split(str2).length > 1) {
                            String str3 = obj2.split(str2)[1];
                            String str4 = str3.split(gqg9qq9gqq9q9q("56", 105))[str3.split(gqg9qq9gqq9q9q("56", 89)).length - 1];
                            qgg99qqg9gq2.gqg9qq9gqq9q9q(str3, gqg9qq9gqq9q9q2);
                        }
                    }
                }
            }
            str = qgg99qqg9gq2.toString();
        } catch (Exception unused) {
            str = "";
        }
        if (str == null || "".equals(str)) {
            qgg99qqg9gq.qgg9qgg9999g9g();
        }
        return str;
    }

    static String g999gqq9ggqgqq(Context context) {
        return gqg9qq9gqq9q9q(g9g9g99.gqg9qq9gqq9q9q(context), g9g9g99.qgg9qgg9999g9g(context), g9g9g99.q9qq99qg9qqgqg9gqgg9(context));
    }

    static Integer qq9q9ggg() {
        HelperJNI.exprot(8, 0);
        int parseInt = O0o0o0o0o.oooo0ooo != null ? Integer.parseInt(O0o0o0o0o.oooo0ooo) : 0;
        if (Debug.isDebuggerConnected()) {
            parseInt |= 32;
        }
        if (gqg9qq9gqq9q9q.g999gqq9ggqgqq && parseInt != 0) {
            HelperJNI.exprot(9, 0);
            int myPid = Process.myPid();
            LogUtil.err(gqg9qq9gqq9q9q("3d40435751575340626c636c686a7f7b7e3f7e747c717c3d2c3f31333725370d", 18) + myPid + gqg9qq9gqq9q9q(AmapLoc.w, 116));
            Process.killProcess(myPid);
            System.exit(0);
        }
        return Integer.valueOf(parseInt);
    }

    static String gqgqgqq9gq9q9q9(Context context) {
        Intent registerReceiver = context.registerReceiver((BroadcastReceiver) null, new IntentFilter(qgg99qqg9gq));
        int intExtra = registerReceiver.getIntExtra(gqg9qq9gqq9q9q("0a7d6668676e", 9), 0);
        int intExtra2 = registerReceiver.getIntExtra(gqg9qq9gqq9q9q("15487f5b76", 50), 0);
        int intExtra3 = registerReceiver.getIntExtra(gqg9qq9gqq9q9q("0d617c7c696b7a7e7b796c", 3), 0);
        String gqg9qq9gqq9q9q2 = gqg9qq9gqq9q9q("0c307035712d68", 88);
        switch (intExtra) {
            case 1:
                gqg9qq9gqq9q9q2 = gqg9qq9gqq9q9q("0c277022713a68", 79);
                break;
            case 2:
                gqg9qq9gqq9q9q2 = gqg9qq9gqq9q9q("1a2b6a387f36783f", 83);
                break;
            case 3:
                gqg9qq9gqq9q9q2 = gqg9qq9gqq9q9q("1d4e7e5e75576642684561", 48);
                break;
            case 4:
                gqg9qq9gqq9q9q2 = gqg9qq9gqq9q9q("17627536363d3f2e2a202d29", 16);
                break;
            case 5:
                gqg9qq9gqq9q9q2 = gqg9qq9gqq9q9q("1f037f03", 99);
                break;
        }
        return gqg9qq9gqq9q9q(Integer.valueOf(intExtra2), gqg9qq9gqq9q9q2, Integer.valueOf(intExtra3));
    }

    static String qgg99qqg9gq(Context context) {
        int i;
        String str = "";
        TimeZone timeZone = TimeZone.getDefault();
        if (timeZone != null) {
            str = gqg9qq9gqq9q9q("1d0163314e3e4e65", 101) + (timeZone.getDSTSavings() / 60000) + gqg9qq9gqq9q9q("551d26160f3f0f2a193b50", 37) + (timeZone.getRawOffset() / 60000);
        }
        String language = Locale.getDefault().getLanguage();
        try {
            i = Settings.System.getInt(context.getContentResolver(), Constants.w);
        } catch (Settings.SettingNotFoundException unused) {
            i = 0;
        }
        if (i < 0) {
            i = -1;
        } else if (i > 255) {
            i = 256;
        }
        return gqg9qq9gqq9q9q(str, language, Integer.valueOf(i));
    }

    static String gqg9qq9gqq9q9q(Context context, SensorManager sensorManager, WindowManager windowManager) {
        StringBuilder sb = new StringBuilder();
        new Date().getTime();
        for (Sensor next : sensorManager.getSensorList(-1)) {
            if (sb.length() > 0) {
                sb.append(gqg9qq9gqq9q9q("55", 105));
            }
            sb.append(String.valueOf(next.getType()));
        }
        String sb2 = sb.toString();
        Display defaultDisplay = windowManager.getDefaultDisplay();
        String str = null;
        Resources resources = context.getResources();
        if (gqg9qq9gqq9q9q(context, defaultDisplay)) {
            int dimensionPixelSize = resources.getDimensionPixelSize(resources.getIdentifier(gqg9qq9gqq9q9q("175c7943774562586459556456777b40764c784364", 32), gqg9qq9gqq9q9q("1d33603b6b", 77), gqg9qq9gqq9q9q("18286b3e76387b", 84)));
            if (dimensionPixelSize > 0) {
                str = defaultDisplay.getWidth() + gqg9qq9gqq9q9q("01", 33) + (defaultDisplay.getHeight() + dimensionPixelSize);
            }
        } else {
            str = defaultDisplay.getWidth() + gqg9qq9gqq9q9q("01", 30) + defaultDisplay.getHeight();
        }
        return gqg9qq9gqq9q9q(sb2, str, g9g9g99.q9gqqq99999qq(), g9g9g99.g9q9q9g9(context), Settings.Secure.getString(context.getContentResolver(), DeviceInfoResult.BUNDLE_KEY_ANDROID_ID), g9g9g99.g9q9q9g9(), qqq9gg9gqq9qgg99q.gqg9qq9gqq9q9q(gqg9qq9gqq9q9q("563d2d20216c787f6f7e757874", 17)));
    }

    public static boolean gqg9qq9gqq9q9q(Context context, Display display) {
        if (Build.VERSION.SDK_INT >= 17) {
            Point point = new Point();
            Point point2 = new Point();
            display.getSize(point);
            display.getRealSize(point2);
            if (point2.y != point.y) {
                return true;
            }
            return false;
        }
        return !ViewConfiguration.get(context).hasPermanentMenuKey() && !KeyCharacterMap.deviceHasKey(4);
    }

    static String qgggqg999gg9qqggq() {
        String[] strArr = {gqg9qq9gqq9q9q("29134d1f4b094b1556", 66), gqg9qq9gqq9q9q("310b5b1d480b5b1c", 81)};
        ArrayList arrayList = new ArrayList();
        Collections.addAll(arrayList, strArr);
        Map gqg9qq9gqq9q9q2 = qqq9gg9gqq9qgg99q.gqg9qq9gqq9q9q(gqg9qq9gqq9q9q("56492d5421186d0b68176f1f66", 101), arrayList, gqg9qq9gqq9q9q("43", 48));
        String gqg9qq9gqq9q9q3 = qqq9gg9gqq9qgg99q.gqg9qq9gqq9q9q(gqg9qq9gqq9q9q("565d2557791c780f670561133d4f3745305438167405715f3d4c380927453440275430406e0c7d09610e69075935552c7215660272", 114));
        if (gqg9qq9gqq9q9q2.isEmpty() || gqg9qq9gqq9q9q2.size() != 2) {
            return gqg9qq9gqq9q9q(gqg9qq9gqq9q9q3, null, null);
        }
        return gqg9qq9gqq9q9q(gqg9qq9gqq9q9q3, gqg9qq9gqq9q9q2.get(strArr[0]), gqg9qq9gqq9q9q2.get(strArr[1]));
    }

    static String gqg9qq9gqq9q9q(Context context, ActivityManager activityManager) {
        long j;
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        long j2 = memoryInfo.availMem;
        if (gqgqgqq9gq9q9q9.qgg9qgg9999g9g(16)) {
            j = memoryInfo.totalMem;
        } else {
            ArrayList arrayList = new ArrayList();
            arrayList.add(gqg9qq9gqq9q9q("340845317e2a6b27", 83));
            Map gqg9qq9gqq9q9q2 = qqq9gg9gqq9qgg99q.gqg9qq9gqq9q9q(gqg9qq9gqq9q9q("56772d6a2126632e6b2a6c2265", 91), arrayList, gqg9qq9gqq9q9q("43", 36));
            j = !gqg9qq9gqq9q9q2.isEmpty() ? (gqg9qq9gqq9q9q2.get("MemTotal") == null || "".equals(gqg9qq9gqq9q9q2.get("MemTotal"))) ? -1 : Long.parseLong(((String) gqg9qq9gqq9q9q2.get("MemTotal")).replaceAll("\\D+", "")) * 1024 : 0;
        }
        return gqg9qq9gqq9q9q(Long.valueOf(j), Long.valueOf(j2));
    }

    static String qq9gq9g9g99() {
        long j;
        long j2;
        long j3;
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        if (gqgqgqq9gq9q9q9.gqg9qq9gqq9q9q(18)) {
            j2 = (long) statFs.getBlockSize();
            j = (long) statFs.getAvailableBlocks();
            j3 = (long) statFs.getBlockCount();
        } else {
            j2 = statFs.getBlockSizeLong();
            j = statFs.getAvailableBlocksLong();
            j3 = statFs.getBlockCountLong();
        }
        return gqg9qq9gqq9q9q(Long.valueOf(j3 * j2), Long.valueOf(j * j2));
    }

    private static String gqg9qq9gqq9q9q(Object... objArr) {
        int length = objArr.length;
        if (length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            String str = objArr[i] == null ? "" : objArr[i];
            if (i == 0) {
                sb.append(str);
            } else {
                sb.append(gqgqgqq9gq9q9q9);
                sb.append(str);
            }
        }
        return sb.toString();
    }

    public static String qq9q9ggg(Context context) {
        return qqg9qgqqqgqqgg9qqqqq.gqg9qq9gqq9q9q().gqg9qq9gqq9q9q(context.getPackageManager());
    }

    static String gqg9qq9gqq9q9q(Context context, LocationManager locationManager) {
        Location lastKnownLocation;
        Location lastKnownLocation2;
        Location lastKnownLocation3;
        List<String> allProviders;
        if (gqg9qq9gqq9q9q.q9q99gq99gggqg9qqqgg) {
            return "";
        }
        q9qqgq9qgqg9q[] q9qqgq9qgqg9qArr = {new q9qqgq9qgqg9q((qq99gqggggq) null), new q9qqgq9qgqg9q((qq99gqggggq) null)};
        Looper.prepare();
        if (gqgqgqq9gq9q9q9.gqg9qq9gqq9q9q(context, "android.permission.ACCESS_FINE_LOCATION") && (allProviders = locationManager.getAllProviders()) != null) {
            if (allProviders.contains("gps")) {
                locationManager.requestLocationUpdates("gps", 1000, 1000.0f, q9qqgq9qgqg9qArr[0]);
            }
            if (allProviders.contains(LogCategory.CATEGORY_NETWORK)) {
                locationManager.requestLocationUpdates(LogCategory.CATEGORY_NETWORK, 1000, 1.0f, q9qqgq9qgqg9qArr[1]);
            }
        }
        if (!gqgqgqq9gq9q9q9.gqg9qq9gqq9q9q(context, "android.permission.ACCESS_FINE_LOCATION")) {
            return "";
        }
        JSONObject jSONObject = new JSONObject();
        if (locationManager.isProviderEnabled("gps") && (lastKnownLocation3 = locationManager.getLastKnownLocation("gps")) != null) {
            try {
                jSONObject.put(gqg9qq9gqq9q9q("0d2f7d3a", 81), gqg9qq9gqq9q9q("1e1764", 115));
                jSONObject.put(gqg9qq9gqq9q9q("153479", 74), lastKnownLocation3.getLatitude());
                jSONObject.put(gqg9qq9gqq9q9q("15476d4e", 55), lastKnownLocation3.getLongitude());
                jSONObject.put(gqg9qq9gqq9q9q("183761", 70), (double) lastKnownLocation3.getAccuracy());
            } catch (JSONException unused) {
            }
        }
        if (jSONObject.length() == 0 && locationManager.isProviderEnabled(LogCategory.CATEGORY_NETWORK) && (lastKnownLocation2 = locationManager.getLastKnownLocation(LogCategory.CATEGORY_NETWORK)) != null) {
            try {
                jSONObject.put(gqg9qq9gqq9q9q("0d117d04", 111), gqg9qq9gqq9q9q("17207f23673e7e", 88));
                jSONObject.put(gqg9qq9gqq9q9q("153b79", 69), lastKnownLocation2.getLatitude());
                jSONObject.put(gqg9qq9gqq9q9q("15556d5c", 37), lastKnownLocation2.getLongitude());
                jSONObject.put(gqg9qq9gqq9q9q("185d61", 44), (double) lastKnownLocation2.getAccuracy());
            } catch (JSONException unused2) {
            }
        }
        if (jSONObject.length() == 0 && locationManager.isProviderEnabled("passive") && (lastKnownLocation = locationManager.getLastKnownLocation("passive")) != null) {
            try {
                jSONObject.put(gqg9qq9gqq9q9q("0d717d64", 15), gqg9qq9gqq9q9q("0926622678396b", 68));
                jSONObject.put(gqg9qq9gqq9q9q("151279", 108), lastKnownLocation.getLatitude());
                jSONObject.put(gqg9qq9gqq9q9q("15536d5a", 35), lastKnownLocation.getLongitude());
                jSONObject.put(gqg9qq9gqq9q9q("181e61", 111), (double) lastKnownLocation.getAccuracy());
            } catch (JSONException unused3) {
            }
        }
        locationManager.removeUpdates(q9qqgq9qgqg9qArr[0]);
        locationManager.removeUpdates(q9qqgq9qgqg9qArr[1]);
        if (jSONObject.length() == 0) {
            return "";
        }
        return jSONObject.toString();
    }

    static String qgggqg999gg9qqggq(Context context) {
        return g9g9g99.qqq9gg9gqq9qgg99q(context);
    }

    static String qqg9qgqqqgqqgg9qqqqq() {
        Object obj;
        Object obj2;
        String str = "";
        for (int i = 0; i < qq9q9ggg.length; i++) {
            if (new File(qq9q9ggg[i]).exists()) {
                if (i != 7) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(str);
                    if (str.length() > 0) {
                        obj2 = gqg9qq9gqq9q9q("55", 67) + i;
                    } else {
                        obj2 = Integer.valueOf(i);
                    }
                    sb.append(obj2);
                    str = sb.toString();
                }
            } else if (i == 7) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                if (str.length() > 0) {
                    obj = gqg9qq9gqq9q9q("55", 73) + i;
                } else {
                    obj = Integer.valueOf(i);
                }
                sb2.append(obj);
                str = sb2.toString();
            }
        }
        return str;
    }

    static Boolean qq9gq9g9g99(Context context) {
        boolean z = false;
        if (Settings.Secure.getInt(context.getContentResolver(), "mock_location", 0) != 0) {
            z = true;
        }
        return Boolean.valueOf(z);
    }

    static String gqqqqgggqq9gq9() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(gqg9qq9gqq9q9q("56432d5e2112601971423f513d", 111)));
            String str = "";
            int i = 0;
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    try {
                        String trim = readLine.trim();
                        if (trim.length() >= 63) {
                            if (!trim.toUpperCase(Locale.US).contains(gqg9qq9gqq9q9q("3051", 59))) {
                                String trim2 = trim.substring(0, 17).trim();
                                trim.substring(29, 32).trim();
                                String trim3 = trim.substring(41, 63).trim();
                                if (!trim3.contains(gqg9qq9gqq9q9q("490d3a073a0d300d3a073a0d300d3a073a", 126))) {
                                    i++;
                                    String str2 = str + trim3.replace(gqg9qq9gqq9q9q("43", 34), "") + trim2 + gqg9qq9gqq9q9q("26", 10);
                                    if (i >= 20) {
                                        str = str2;
                                        break;
                                    }
                                    str = str2;
                                }
                            }
                        }
                    } catch (Exception unused) {
                    }
                }
            }
            bufferedReader.close();
            return str.substring(0, str.length() - 1);
        } catch (Exception unused2) {
            return "";
        }
    }

    static String gqq9qggqgg9g99() {
        String gqg9qq9gqq9q9q2 = q9qgqg9.gqg9qq9gqq9q9q();
        return gqg9qq9gqq9q9q2 == null ? gqg9qq9gqq9q9q("2235", 64) : gqg9qq9gqq9q9q2;
    }

    static String q9ggg99qqqq() {
        String box7 = BoxUtil.box7();
        return (box7 == null || box7.length() <= 0) ? "" : box7.substring(0, box7.length() - 1);
    }

    static String qq99gqggggq() {
        long mobileRxBytes = TrafficStats.getMobileRxBytes();
        long mobileTxBytes = TrafficStats.getMobileTxBytes();
        long totalTxBytes = TrafficStats.getTotalTxBytes();
        long totalRxBytes = TrafficStats.getTotalRxBytes();
        return String.format(gqg9qq9gqq9q9q("5c19087549582519087549", 43), new Object[]{Long.valueOf(mobileRxBytes), Long.valueOf(mobileTxBytes), Long.valueOf(totalTxBytes), Long.valueOf(totalRxBytes)});
    }

    static Integer q9qq99qg9qqgqg9gqgg9(Context context, WifiManager wifiManager) {
        Integer num = new Integer(-1);
        String ssid = wifiManager.getConnectionInfo().getSSID();
        PackageManager packageManager = context.getPackageManager();
        if (q9gqqq99999qq.gqg9qq9gqq9q9q(packageManager, gqg9qq9gqq9q9q("1a122348325e35433203751e6408631564", 108)) != null) {
            return 1;
        }
        if (!TextUtils.isEmpty(ssid) && ssid.contains(gqg9qq9gqq9q9q("16347a3f78327b", 88))) {
            return 2;
        }
        if (q9gqqq99999qq.gqg9qq9gqq9q9q(packageManager, gqg9qq9gqq9q9q("1a49610a3b1b3016361d2c47755564447243", 54)) != null || q9gqqq99999qq.gqg9qq9gqq9q9q(packageManager, gqg9qq9gqq9q9q("1a2361603b71307c36772c2d753f642e72292874347d2662287e33733d602b", 92)) != null) {
            return 3;
        }
        if (cn.tongdun.android.core.q9q99gq99gggqg9qqqgg.gqg9qq9gqq9q9q.gqg9qq9gqq9q9q(packageManager, ssid)) {
            return 4;
        }
        if (cn.tongdun.android.core.q9q99gq99gggqg9qqqgg.qgg9qgg9999g9g.gqg9qq9gqq9q9q(packageManager)) {
            return 5;
        }
        if (qq9gq9g9g99.gqg9qq9gqq9q9q(ssid)) {
            return 6;
        }
        if (cn.tongdun.android.core.q9q99gq99gggqg9qqqgg.qgg99qqg9gq.gqg9qq9gqq9q9q(context, ssid)) {
            return 7;
        }
        if (q9q99gq99gggqg9qqqgg.gqg9qq9gqq9q9q()) {
            return 8;
        }
        return num;
    }

    static String qqg9qgqqqgqqgg9qqqqq(Context context) {
        String gqg9qq9gqq9q9q2 = g9qqggg99gqq99g9q.gqg9qq9gqq9q9q().gqg9qq9gqq9q9q(context);
        if (gqg9qq9gqq9q9q2 == null || gqg9qq9gqq9q9q2.length() > 70) {
            return gqg9qq9gqq9q9q2;
        }
        return null;
    }

    static String gqqqqgggqq9gq9(Context context) {
        String gqg9qq9gqq9q9q2 = gggqqggggqgqggg99.gqg9qq9gqq9q9q().gqg9qq9gqq9q9q(context);
        if (gqg9qq9gqq9q9q2 != null) {
            return null;
        }
        return gqg9qq9gqq9q9q2;
    }

    static String gqq9qggqgg9g99(Context context) {
        return g9q9q9g9.gqg9qq9gqq9q9q().gqg9qq9gqq9q9q(context);
    }

    static String q9ggg99qqqq(Context context) {
        return HelperJNI.fpic5(context);
    }

    static String qq99gqggggq(Context context) {
        String str = "";
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (true) {
                if (!networkInterfaces.hasMoreElements()) {
                    break;
                }
                String name = networkInterfaces.nextElement().getName();
                if (name != null && gqg9qq9gqq9q9q("0e5f7a5024", 55).equals(name)) {
                    NetworkInterface byName = NetworkInterface.getByName(name);
                    if (byName != null) {
                        Enumeration<InetAddress> inetAddresses = byName.getInetAddresses();
                        while (inetAddresses.hasMoreElements()) {
                            InetAddress nextElement = inetAddresses.nextElement();
                            if (nextElement instanceof Inet6Address) {
                                String hostAddress = nextElement.getHostAddress();
                                if (hostAddress.contains(gqg9qq9gqq9q9q("5c6b3e663138", 74))) {
                                    str = hostAddress.substring(0, hostAddress.length() - 6);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception unused) {
        }
        return str;
    }

    static Integer qgg9qgg9999g9g(Context context, LocationManager locationManager) {
        boolean z;
        boolean gqg9qq9gqq9q9q2 = gqgqgqq9gq9q9q9.gqg9qq9gqq9q9q(context, "android.permission.ACCESS_FINE_LOCATION");
        boolean z2 = false;
        Integer num = 0;
        if (gqg9qq9gqq9q9q2) {
            z2 = locationManager.isProviderEnabled("gps");
            z = locationManager.isProviderEnabled(LogCategory.CATEGORY_NETWORK);
        } else {
            z = false;
        }
        if (z2 || z) {
            num = Integer.valueOf(num.intValue() | 1);
        }
        if (Build.VERSION.SDK_INT < 23) {
            return Integer.valueOf(num.intValue() | 2);
        }
        if (gqg9qq9gqq9q9q2) {
            num = Integer.valueOf(num.intValue() | 2);
        }
        return (Build.VERSION.SDK_INT <= 28 || !gqgqgqq9gq9q9q9.gqg9qq9gqq9q9q(context, gqg9qq9gqq9q9q("18366b2076267b6c25793266367c3666306770087208741e741269116b19670c7a16611c7a0f79037b16661067", 74))) ? num : Integer.valueOf(num.intValue() | 4);
    }

    @TargetApi(14)
    static String q9qqgq9qgqg9q(Context context) {
        List<AccessibilityServiceInfo> enabledAccessibilityServiceList;
        new ArrayList();
        AccessibilityManager accessibilityManager = (AccessibilityManager) context.getSystemService("accessibility");
        PackageManager packageManager = context.getPackageManager();
        if (accessibilityManager == null || (enabledAccessibilityServiceList = accessibilityManager.getEnabledAccessibilityServiceList(-1)) == null || enabledAccessibilityServiceList.size() == 0) {
            return "";
        }
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        for (AccessibilityServiceInfo resolveInfo : enabledAccessibilityServiceList) {
            ResolveInfo resolveInfo2 = resolveInfo.getResolveInfo();
            if (!(resolveInfo2 == null || resolveInfo2.serviceInfo == null)) {
                String str = resolveInfo2.serviceInfo.packageName;
                String str2 = resolveInfo2.serviceInfo.name;
                if (!TextUtils.isEmpty(str2) && !hashSet.contains(str2)) {
                    hashSet.add(str2);
                    String str3 = "";
                    if (packageManager != null) {
                        str3 = resolveInfo2.loadLabel(packageManager).toString();
                    }
                    ArrayList arrayList2 = new ArrayList();
                    arrayList2.add(str);
                    arrayList2.add(str2);
                    arrayList2.add(str3);
                    arrayList.add(arrayList2.toString());
                }
            }
        }
        return arrayList.toString();
    }

    static String g9qqggg99gqq99g9q(Context context) {
        String string = Settings.Secure.getString(context.getContentResolver(), "default_input_method");
        return string == null ? "" : string;
    }

    public static String gqg9qq9gqq9q9q(String str, int i) {
        try {
            int length = str.length() / 2;
            char[] charArray = str.toCharArray();
            byte[] bArr = new byte[length];
            for (int i2 = 0; i2 < length; i2++) {
                int i3 = i2 * 2;
                bArr[i2] = (byte) ("0123456789abcdef".indexOf(charArray[i3 + 1]) | ("0123456789abcdef".indexOf(charArray[i3]) << 4));
            }
            byte b = (byte) (i ^ 115);
            int length2 = bArr.length;
            bArr[0] = (byte) (bArr[0] ^ Constants.TagName.ELECTRONIC_STATE);
            byte b2 = bArr[0];
            int i4 = 1;
            while (i4 < length2) {
                byte b3 = bArr[i4];
                bArr[i4] = (byte) ((b2 ^ bArr[i4]) ^ b);
                i4++;
                b2 = b3;
            }
            return new String(bArr, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
