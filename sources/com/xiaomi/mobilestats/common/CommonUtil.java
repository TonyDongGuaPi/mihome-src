package com.xiaomi.mobilestats.common;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import com.taobao.weex.adapter.URIAdapter;
import com.taobao.weex.ui.view.gesture.WXGestureType;
import com.xiaomi.mobilestats.data.ProtoMsg;
import com.xiaomi.mobilestats.data.SendStrategyEnum;
import com.xiaomi.mobilestats.object.GSMCell;
import com.xiaomi.mobilestats.object.LatitudeAndLongitude;
import com.xiaomi.smarthome.device.BleDevice;
import java.util.Date;
import java.util.List;

public class CommonUtil {
    private static int a(Context context, String str, int i) {
        Bundle f = f(context);
        if (f != null) {
            return f.getInt(str, i);
        }
        return 0;
    }

    private static String a(Context context, String str) {
        String str2 = "";
        Bundle f = f(context);
        if (f != null) {
            str2 = f.getString(str);
        }
        if (TextUtils.isEmpty(str2)) {
            printLog("XMAgent", "Could not read " + str + " meta-data from AndroidManifest.xml.");
        }
        return str2;
    }

    private static String a(String str) {
        if (StrUtil.isEmpty(str)) {
            return "";
        }
        char charAt = str.charAt(0);
        if (Character.isUpperCase(charAt)) {
            return str;
        }
        return Character.toUpperCase(charAt) + str.substring(1);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0003, code lost:
        r1 = r2.getPackageManager();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean checkPermissions(android.content.Context r2, java.lang.String r3) {
        /*
            r0 = 0
            if (r2 == 0) goto L_0x0014
            android.content.pm.PackageManager r1 = r2.getPackageManager()
            if (r1 == 0) goto L_0x0014
            java.lang.String r2 = r2.getPackageName()
            int r2 = r1.checkPermission(r3, r2)
            if (r2 != 0) goto L_0x0014
            r0 = 1
        L_0x0014:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mobilestats.common.CommonUtil.checkPermissions(android.content.Context, java.lang.String):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:1:0x0002, code lost:
        r0 = r2.getPackageManager();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean checkPhoneState(android.content.Context r2) {
        /*
            if (r2 == 0) goto L_0x0016
            android.content.pm.PackageManager r0 = r2.getPackageManager()
            if (r0 == 0) goto L_0x0016
            java.lang.String r1 = "android.permission.READ_PHONE_STATE"
            java.lang.String r2 = r2.getPackageName()
            int r2 = r0.checkPermission(r1, r2)
            if (r2 == 0) goto L_0x0016
            r2 = 0
            return r2
        L_0x0016:
            r2 = 1
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mobilestats.common.CommonUtil.checkPhoneState(android.content.Context):boolean");
    }

    public static boolean currentNoteworkTypeIsWIFI(Context context) {
        if (context == null) {
            return false;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().getType() == 1;
    }

    private static Bundle f(Context context) {
        if (context == null) {
            return null;
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                return null;
            }
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo == null) {
                return null;
            }
            return applicationInfo.metaData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static boolean g(Context context) {
        if (context == null) {
            return false;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.miui.cloudservice", 0);
            return (packageInfo == null || (packageInfo.applicationInfo.flags & 1) == 0) ? false : true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getActivityName(Context context) {
        if (context == null) {
            return "";
        }
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager == null || !checkPermissions(context, "android.permission.GET_TASKS")) {
            printLog("no permission", "android.permission.GET_TASKS");
            return "";
        }
        ComponentName componentName = activityManager.getRunningTasks(1).get(0).topActivity;
        return componentName != null ? componentName.getClassName() : "";
    }

    public static String getAppChannel(Context context) {
        return a(context, "XM_APPCHANNEL");
    }

    public static String getAppKey(Context context) {
        return a(context, "XM_APPKEY");
    }

    public static GSMCell getCellInfo(Context context) {
        if (context == null) {
            return null;
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager == null) {
                return null;
            }
            GSMCell gSMCell = new GSMCell();
            String networkOperator = telephonyManager.getNetworkOperator();
            if (StrUtil.isEmpty(networkOperator)) {
                networkOperator = "";
            }
            gSMCell.MCCMNC = networkOperator;
            GsmCellLocation gsmCellLocation = (GsmCellLocation) telephonyManager.getCellLocation();
            if (gsmCellLocation != null) {
                gSMCell.LAC = gsmCellLocation.getLac();
                gSMCell.CID = gsmCellLocation.getCid();
            }
            return gSMCell;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getClientId(Context context) {
        return a(context, "CLIENT_ID");
    }

    public static String getDeviceID(Context context) {
        TelephonyManager telephonyManager;
        String str = "";
        if (context != null) {
            if (checkPermissions(context, "android.permission.READ_PHONE_STATE") && checkPhoneState(context) && (telephonyManager = (TelephonyManager) context.getSystemService("phone")) != null) {
                str = telephonyManager.getDeviceId();
            }
            if (!TextUtils.isEmpty(str)) {
                return StrUtil.md5(str);
            }
        }
        return str;
    }

    public static String getDeviceName() {
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        if (StrUtil.isEmpty(str)) {
            str = "";
        }
        if (StrUtil.isEmpty(str2)) {
            str2 = "";
        }
        if (!StrUtil.isEmpty(str2) && !StrUtil.isEmpty(str) && str2.startsWith(str)) {
            return a(str2);
        }
        return a(str) + " " + str2;
    }

    @SuppressLint({"NewApi"})
    public static String getLargeMemoryClass(Context context) {
        if (context == null) {
            return "";
        }
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager == null) {
            return "Unkown";
        }
        return activityManager.getLargeMemoryClass() + "";
    }

    public static LatitudeAndLongitude getLatitudeAndLongitude(Context context) {
        List<String> allProviders;
        String str;
        LatitudeAndLongitude latitudeAndLongitude = new LatitudeAndLongitude();
        if (context != null) {
            try {
                LocationManager locationManager = (LocationManager) context.getSystemService("location");
                if (!(locationManager == null || (allProviders = locationManager.getAllProviders()) == null || allProviders.size() <= 0)) {
                    for (String lastKnownLocation : allProviders) {
                        Location lastKnownLocation2 = locationManager.getLastKnownLocation(lastKnownLocation);
                        if (lastKnownLocation2 != null) {
                            latitudeAndLongitude.latitude = lastKnownLocation2.getLatitude() + "";
                            str = lastKnownLocation2.getLongitude() + "";
                        } else {
                            latitudeAndLongitude.latitude = "";
                            str = "";
                        }
                        latitudeAndLongitude.longitude = str;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return latitudeAndLongitude;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000e, code lost:
        r2 = r2.getConnectionInfo();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getMacAddress(android.content.Context r2) {
        /*
            java.lang.String r0 = ""
            if (r2 == 0) goto L_0x001e
            java.lang.String r1 = "wifi"
            java.lang.Object r2 = r2.getSystemService(r1)     // Catch:{ Exception -> 0x001a }
            android.net.wifi.WifiManager r2 = (android.net.wifi.WifiManager) r2     // Catch:{ Exception -> 0x001a }
            if (r2 == 0) goto L_0x001e
            android.net.wifi.WifiInfo r2 = r2.getConnectionInfo()     // Catch:{ Exception -> 0x001a }
            if (r2 == 0) goto L_0x001e
            java.lang.String r2 = r2.getMacAddress()     // Catch:{ Exception -> 0x001a }
            r0 = r2
            goto L_0x001e
        L_0x001a:
            r2 = move-exception
            r2.printStackTrace()
        L_0x001e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mobilestats.common.CommonUtil.getMacAddress(android.content.Context):java.lang.String");
    }

    public static String getMemoryClass(Context context) {
        if (context == null) {
            return "";
        }
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager == null) {
            return "Unkown";
        }
        return activityManager.getMemoryClass() + "";
    }

    public static int getMishopSdkVersionCode(Context context) {
        return a(context, "mishopSdkVersionCode", 0);
    }

    public static String getMishopSdkVersionName(Context context) {
        return a(context, "mishopSdkVersionName");
    }

    public static String getMiuiVerson(Context context) {
        StringBuilder sb;
        String str;
        if (g(context)) {
            sb = new StringBuilder();
            str = "MIUI ";
        } else {
            sb = new StringBuilder();
            sb.append(Build.MANUFACTURER);
            str = " ";
        }
        sb.append(str);
        sb.append(Build.VERSION.INCREMENTAL);
        return sb.toString();
    }

    public static String getNetworkType(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        if (activeNetworkInfo == null) {
            return "unknown";
        }
        if (activeNetworkInfo.getType() == 1) {
            return "wifi";
        }
        if (activeNetworkInfo.getType() != 0) {
            return activeNetworkInfo.getType() == 7 ? BleDevice.f14751a : URIAdapter.OTHERS;
        }
        switch (activeNetworkInfo.getSubtype()) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return "2g";
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
                return "3g";
            case 13:
                return "4g";
            default:
                return "unknown";
        }
    }

    public static ProtoMsg.StatsMsg.NetworkType getNetworkType2G3GWifi(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo;
        if (context == null || (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
            return ProtoMsg.StatsMsg.NetworkType.NETWORK_UNKNOWN;
        }
        if (activeNetworkInfo.getType() == 1) {
            return ProtoMsg.StatsMsg.NetworkType.NETWORK_WIFI;
        }
        if (activeNetworkInfo.getType() != 0) {
            return activeNetworkInfo.getType() == 7 ? ProtoMsg.StatsMsg.NetworkType.NETWORK_BLUETOOTH : ProtoMsg.StatsMsg.NetworkType.NETWORK_OTHERS;
        }
        switch (activeNetworkInfo.getSubtype()) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return ProtoMsg.StatsMsg.NetworkType.NETWORK_2G;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
                return ProtoMsg.StatsMsg.NetworkType.NETWORK_3G;
            case 13:
                return ProtoMsg.StatsMsg.NetworkType.NETWORK_4G;
            default:
                return ProtoMsg.StatsMsg.NetworkType.NETWORK_UNKNOWN;
        }
    }

    public static String getNetworkTypeDetail(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo;
        if (context == null || (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
            return "Unkown";
        }
        if (activeNetworkInfo.getType() == 1) {
            return "Wifi";
        }
        if (activeNetworkInfo.getType() != 0) {
            return "Unkown";
        }
        switch (activeNetworkInfo.getSubtype()) {
            case 1:
                return "GPRS";
            case 2:
                return "EDGE";
            case 3:
                return "UMTS";
            case 4:
                return "CDMA";
            case 5:
                return "EVDO_0";
            case 6:
                return "EVDO_A";
            case 7:
                return "1xRTT";
            case 8:
                return "HSDPA";
            case 9:
                return "HSUPA";
            case 10:
                return "HSPA";
            case 11:
                return "IDEN";
            case 12:
                return "EHRPD";
            case 13:
                return "LTE";
            case 14:
                return "EHRPD";
            case 15:
                return "HSPAP";
            default:
                return "Unkown";
        }
    }

    @SuppressLint({"DefaultLocale"})
    public static String getNetworkTypeWIFIOther(Context context) {
        ConnectivityManager connectivityManager;
        String str = "UNKNOWN";
        if (context == null || (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) == null) {
            return str;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            str = activeNetworkInfo.getTypeName().toLowerCase();
        }
        return (StrUtil.isEmpty(str) || !str.equals("wifi")) ? connectivityManager.getNetworkInfo(0).getExtraInfo() : str;
    }

    public static String getOsVersion(Context context) {
        return (context == null || !checkPhoneState(context)) ? "" : Build.VERSION.RELEASE;
    }

    public static String getPackageName(Context context) {
        if (context == null) {
            return "";
        }
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager == null || !checkPermissions(context, "android.permission.GET_TASKS")) {
            printLog("no permission", "android.permission.GET_TASKS");
            return "";
        }
        ComponentName componentName = activityManager.getRunningTasks(1).get(0).topActivity;
        return componentName != null ? componentName.getPackageName() : "";
    }

    public static String getPhoneType(Context context) {
        TelephonyManager telephonyManager;
        if (context == null || (telephonyManager = (TelephonyManager) context.getSystemService("phone")) == null) {
            return "";
        }
        return telephonyManager.getPhoneType() + "";
    }

    public static String getResolution(Context context) {
        Display defaultDisplay;
        if (context == null) {
            return "";
        }
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (!(windowManager == null || (defaultDisplay = windowManager.getDefaultDisplay()) == null)) {
            defaultDisplay.getMetrics(displayMetrics);
        }
        return displayMetrics.widthPixels + "x" + displayMetrics.heightPixels;
    }

    public static String getSdkVersion(Context context) {
        if (context != null && !checkPhoneState(context)) {
            return Build.VERSION.RELEASE;
        }
        printLog("android_osVersion", "OsVerson get failed");
        return "";
    }

    public static SendStrategyEnum getSendStragegy(Context context) {
        if (context != null) {
            String packageName = context.getPackageName();
            SharedPreferences sharedPreferences = context.getSharedPreferences("xm_agent_online_setting_" + packageName, 0);
            if (sharedPreferences != null) {
                return SendStrategyEnum.values()[sharedPreferences.getInt("xm_local_report_policy", 0)];
            }
        }
        return SendStrategyEnum.REAL_TIME;
    }

    public static String getSimOperator(Context context) {
        if (context == null) {
            return "UNKNOWN";
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager == null) {
                return "UNKNOWN";
            }
            return TextUtils.isEmpty(telephonyManager.getSimOperator()) ? "UNKNOWN" : telephonyManager.getSimOperator();
        } catch (Exception e) {
            e.printStackTrace();
            return "UNKNOWN";
        }
    }

    public static String getTime() {
        return Long.toString(new Date().getTime());
    }

    public static String getUserIdentifier(Context context) {
        if (context == null) {
            return "";
        }
        String packageName = context.getPackageName();
        SharedPreferences sharedPreferences = context.getSharedPreferences("xm_agent_online_setting_" + packageName, 0);
        return sharedPreferences != null ? sharedPreferences.getString(WXGestureType.GestureInfo.POINTER_ID, "") : "";
    }

    public static int getVersionCode(Context context) {
        PackageInfo packageInfo;
        if (context == null) {
            return -1;
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null || (packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0)) == null) {
                return -1;
            }
            return packageInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000a, code lost:
        r3 = r1.getPackageInfo(r3.getPackageName(), 0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getVersionName(android.content.Context r3) {
        /*
            java.lang.String r0 = ""
            if (r3 == 0) goto L_0x001d
            android.content.pm.PackageManager r1 = r3.getPackageManager()     // Catch:{ Exception -> 0x0019 }
            if (r1 == 0) goto L_0x001d
            java.lang.String r3 = r3.getPackageName()     // Catch:{ Exception -> 0x0019 }
            r2 = 0
            android.content.pm.PackageInfo r3 = r1.getPackageInfo(r3, r2)     // Catch:{ Exception -> 0x0019 }
            if (r3 == 0) goto L_0x001d
            java.lang.String r3 = r3.versionName     // Catch:{ Exception -> 0x0019 }
            r0 = r3
            goto L_0x001d
        L_0x0019:
            r3 = move-exception
            r3.printStackTrace()
        L_0x001d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mobilestats.common.CommonUtil.getVersionName(android.content.Context):java.lang.String");
    }

    public static boolean isHasBluetooth() {
        return BluetoothAdapter.getDefaultAdapter() != null;
    }

    public static boolean isHasGps(Context context) {
        return (context == null || ((LocationManager) context.getSystemService("location")) == null) ? false : true;
    }

    public static boolean isHaveGravity(Context context) {
        return (context == null || ((SensorManager) context.getSystemService("sensor")) == null) ? false : true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0015, code lost:
        r2 = (r2 = (android.net.ConnectivityManager) r2.getSystemService("connectivity")).getActiveNetworkInfo();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isNetworkAvailable(android.content.Context r2) {
        /*
            r0 = 0
            if (r2 == 0) goto L_0x0023
            java.lang.String r1 = "android.permission.INTERNET"
            boolean r1 = checkPermissions(r2, r1)
            if (r1 == 0) goto L_0x0023
            java.lang.String r1 = "connectivity"
            java.lang.Object r2 = r2.getSystemService(r1)
            android.net.ConnectivityManager r2 = (android.net.ConnectivityManager) r2
            if (r2 == 0) goto L_0x0023
            android.net.NetworkInfo r2 = r2.getActiveNetworkInfo()
            if (r2 == 0) goto L_0x0023
            boolean r2 = r2.isAvailable()
            if (r2 == 0) goto L_0x0023
            r2 = 1
            return r2
        L_0x0023:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mobilestats.common.CommonUtil.isNetworkAvailable(android.content.Context):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0015, code lost:
        r2 = (r2 = (android.net.ConnectivityManager) r2.getSystemService("connectivity")).getActiveNetworkInfo();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isNetworkTypeWifi(android.content.Context r2) {
        /*
            r0 = 0
            if (r2 == 0) goto L_0x002f
            java.lang.String r1 = "android.permission.INTERNET"
            boolean r1 = checkPermissions(r2, r1)
            if (r1 == 0) goto L_0x002f
            java.lang.String r1 = "connectivity"
            java.lang.Object r2 = r2.getSystemService(r1)
            android.net.ConnectivityManager r2 = (android.net.ConnectivityManager) r2
            if (r2 == 0) goto L_0x002f
            android.net.NetworkInfo r2 = r2.getActiveNetworkInfo()
            if (r2 == 0) goto L_0x002f
            boolean r1 = r2.isAvailable()
            if (r1 == 0) goto L_0x002f
            java.lang.String r2 = r2.getTypeName()
            java.lang.String r1 = "WIFI"
            boolean r2 = r2.equals(r1)
            if (r2 == 0) goto L_0x002f
            r2 = 1
            return r2
        L_0x002f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mobilestats.common.CommonUtil.isNetworkTypeWifi(android.content.Context):boolean");
    }

    public static boolean isWiFiActive(Context context) {
        Context applicationContext;
        ConnectivityManager connectivityManager;
        NetworkInfo[] allNetworkInfo;
        if (!(context == null || !checkPermissions(context, "android.permission.ACCESS_WIFI_STATE") || (applicationContext = context.getApplicationContext()) == null || (connectivityManager = (ConnectivityManager) applicationContext.getSystemService("connectivity")) == null || (allNetworkInfo = connectivityManager.getAllNetworkInfo()) == null || allNetworkInfo.length <= 0)) {
            for (int i = 0; i < allNetworkInfo.length; i++) {
                if (allNetworkInfo[i].getTypeName().equals("WIFI") && allNetworkInfo[i].isConnected()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void printLog(String str, String str2) {
        if (HostManager.DEBUG_MODE) {
            Log.d(str, str2);
        }
    }
}
