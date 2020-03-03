package cn.fraudmetrix.octopus.aspirit.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import cn.fraudmetrix.octopus.aspirit.bean.e;
import com.alibaba.fastjson.JSON;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.UUID;

public class k {
    public static e a(Context context) {
        e eVar = null;
        if (context == null) {
            return null;
        }
        e eVar2 = new e(context);
        String str = (String) eVar2.b(OctopusConstants.l, "");
        if (!"".equals(str)) {
            try {
                e eVar3 = (e) JSON.parseObject(str, e.class);
                if (TextUtils.isEmpty(eVar3.device_id)) {
                    eVar3.device_id = UUID.randomUUID().toString();
                }
                eVar = eVar3;
            } catch (Exception unused) {
            }
        }
        if (eVar == null) {
            try {
                e eVar4 = new e();
                try {
                    eVar4.device_id = UUID.randomUUID().toString();
                    eVar4.os_version = Build.VERSION.RELEASE;
                    eVar4.os_type = "android";
                    eVar4.device_brand = Build.BOARD + "  " + Build.MANUFACTURER;
                    eVar4.device_type = Build.MODEL;
                    eVar4.device_id = c(context);
                    eVar4.network = b(context);
                    eVar4.cpu_usage = a() + "";
                    eVar = eVar4;
                } catch (Exception e) {
                    e = e;
                    eVar = eVar4;
                    e.printStackTrace();
                    return eVar;
                }
            } catch (Exception e2) {
                e = e2;
            }
        }
        eVar2.a(OctopusConstants.l, JSON.toJSONString(eVar));
        return eVar;
    }

    private static String a() {
        String readLine;
        new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("top -n 1").getInputStream()));
            do {
                readLine = bufferedReader.readLine();
                if (readLine == null) {
                    return null;
                }
            } while (readLine.trim().length() < 1);
            return readLine;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean a(Context context, String str) {
        try {
            List<PackageInfo> installedPackages = context.getPackageManager().getInstalledPackages(0);
            for (int i = 0; i < installedPackages.size(); i++) {
                if (installedPackages.get(i).packageName.equalsIgnoreCase(str)) {
                    return true;
                }
            }
        } catch (Exception unused) {
        }
        return false;
    }

    public static String b(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return "nono_connect";
        }
        int type = activeNetworkInfo.getType();
        if (type == 1) {
            return "wifi";
        }
        if (type != 0) {
            return "nono_connect";
        }
        if (Build.VERSION.SDK_INT >= 23 && ActivityCompat.checkSelfPermission(context, "android.permission.READ_PHONE_STATE") != 0) {
            return "移动网络";
        }
        int subtype = activeNetworkInfo.getSubtype();
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (subtype == 13 && !telephonyManager.isNetworkRoaming()) {
            return "4G";
        }
        if (subtype == 3 || subtype == 8 || (subtype == 5 && !telephonyManager.isNetworkRoaming())) {
            return "3G";
        }
        if (!(subtype == 1 || subtype == 2 || subtype != 4)) {
            boolean isNetworkRoaming = telephonyManager.isNetworkRoaming();
        }
        return "2G";
    }

    private static String c(Context context) {
        if (Build.VERSION.SDK_INT >= 23 && ActivityCompat.checkSelfPermission(context, "android.permission.READ_PHONE_STATE") != 0) {
            return UUID.randomUUID().toString();
        }
        String deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        return deviceId == null ? UUID.randomUUID().toString() : deviceId;
    }
}
