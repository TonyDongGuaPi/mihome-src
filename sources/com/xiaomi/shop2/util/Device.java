package com.xiaomi.shop2.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.mishopsdk.ShopApp;
import com.xiaomi.mishopsdk.util.AndroidUtil;
import com.xiaomi.mishopsdk.util.Coder;
import com.xiaomi.mishopsdk.util.NetworkUtil;
import com.xiaomi.mishopsdk.util.PreferenceUtil;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Locale;

public class Device {
    public static String BOARD = "";
    public static String BRAND = "";
    public static String BUILD_TYPE = "";
    public static String CARRIER = "";
    public static String CHANNEL_ID = "";
    public static String COUNTRY = "";
    public static String DEVICE = "";
    public static int DISPLAY_DENSITY = 0;
    public static int DISPLAY_HEIGHT = 0;
    public static String DISPLAY_RESOLUTION = null;
    public static int DISPLAY_WIDTH = 0;
    public static String HARDWARE = "";
    public static String IMEI = "";
    public static boolean IS_MIUI = false;
    public static boolean IS_NEW_USER = false;
    public static boolean IS_SYSTEM_SHOP = false;
    private static final String KEY_INSTALL_TIME = "installTime";
    public static String LANGUAGE = "";
    public static String MAC_ADDRESS = "";
    public static String MANUFACTURER = "";
    public static final int MIN_SHOP_SDK_VERSION = AndroidUtil.getMetaDataInt("minShopSdkVersionCode");
    public static final int MISHOP_SDK_VERSION = AndroidUtil.getMetaDataInt("mishopSdkVersionCode");
    public static final String MISHOP_SDK_VERSION_STRING = AndroidUtil.getMetaDataString("mishopSdkVersionName");
    public static String MODEL = "";
    private static final long NEW_USER_TIME = 604800000;
    public static String PACKAGE = "";
    public static String PRODUCT = "";
    public static String RELEASE = "";
    public static int SDK_VERSION = 0;
    public static String SERIAL_NO = "";
    public static String SIM_SERIAL_NO = "";
    public static String SN = "";
    public static String SYSTEM_VERSION = "";
    private static final String TAG = "Device";
    public static String UUID = "";

    public static boolean isInstalled(String str) {
        return false;
    }

    public static void init(Context context) {
        acquireScreenAttr(context);
        acquireSystemInfo(context);
        acquireShopInfo(context);
        acquireUserInfo(context);
        acquireIdentity(context);
        acquireIsNewUser(context);
    }

    public static String getClientInfoHash() {
        return Coder.encodeMD5(getFullInfo());
    }

    public static String getFullInfo() {
        return DISPLAY_RESOLUTION + DISPLAY_WIDTH + DISPLAY_HEIGHT + DISPLAY_DENSITY + MODEL + DEVICE + PRODUCT + BOARD + HARDWARE + MANUFACTURER + BRAND + BUILD_TYPE + SDK_VERSION + SYSTEM_VERSION + RELEASE + IS_MIUI + MISHOP_SDK_VERSION + MISHOP_SDK_VERSION_STRING + IS_SYSTEM_SHOP + COUNTRY + LANGUAGE + CARRIER + UUID + IMEI + CHANNEL_ID;
    }

    private static void acquireScreenAttr(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        if (i2 > i) {
            DISPLAY_WIDTH = i;
            DISPLAY_HEIGHT = i2;
        } else {
            DISPLAY_WIDTH = i2;
            DISPLAY_HEIGHT = i;
        }
        DISPLAY_RESOLUTION = DISPLAY_HEIGHT + "*" + DISPLAY_WIDTH;
        DISPLAY_DENSITY = displayMetrics.densityDpi;
    }

    private static void acquireSystemInfo(Context context) {
        MODEL = Build.MODEL;
        DEVICE = Build.DEVICE;
        PRODUCT = Build.PRODUCT;
        BOARD = Build.BOARD;
        HARDWARE = Build.HARDWARE;
        MANUFACTURER = Build.MANUFACTURER;
        BRAND = Build.BRAND;
        BUILD_TYPE = Build.TYPE;
        SN = Build.SERIAL;
        RELEASE = Build.VERSION.RELEASE;
        SYSTEM_VERSION = Build.VERSION.INCREMENTAL;
        SDK_VERSION = Build.VERSION.SDK_INT;
        IS_MIUI = isMiui(context);
        MAC_ADDRESS = NetworkUtil.getMacAddress();
        SERIAL_NO = getSerialNo();
    }

    private static String getSerialNo() {
        Class<?> cls;
        try {
            cls = Class.forName("android.os.SystemProperties");
        } catch (Exception unused) {
            cls = null;
        }
        if (cls == null) {
            return null;
        }
        try {
            return (String) cls.getMethod("get", new Class[]{String.class}).invoke(cls, new Object[]{"permanent.hw.custom.serialno"});
        } catch (Exception unused2) {
            return null;
        }
    }

    private static void acquireShopInfo(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            PACKAGE = packageInfo.packageName;
            boolean z = true;
            if ((packageInfo.applicationInfo.flags & 1) == 0) {
                z = false;
            }
            IS_SYSTEM_SHOP = z;
        } catch (PackageManager.NameNotFoundException unused) {
            IS_SYSTEM_SHOP = false;
        }
    }

    private static void acquireUserInfo(Context context) {
        COUNTRY = Locale.getDefault().getCountry();
        LANGUAGE = Locale.getDefault().getLanguage();
        CARRIER = ((TelephonyManager) context.getSystemService("phone")).getSimOperator();
    }

    private static void acquireIdentity(Context context) {
        if (context != null) {
            IMEI = getRealIMEI(context);
            SIM_SERIAL_NO = getSimSerialNo(context);
            String macAddress = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
            StringBuffer stringBuffer = new StringBuffer();
            if (!TextUtils.isEmpty(IMEI)) {
                stringBuffer.append(IMEI);
            }
            if (!TextUtils.isEmpty(macAddress)) {
                stringBuffer.append(JSMethod.NOT_SET);
                stringBuffer.append(macAddress);
            }
            UUID = Coder.encodeMD5(stringBuffer.toString());
            CHANNEL_ID = DeviceUtil.getMetaData("XM_SHOP_CHANNAL_ID");
            CHANNEL_ID = TextUtils.isEmpty(CHANNEL_ID) ? "1.1.1" : CHANNEL_ID;
        }
    }

    private static String getRealIMEI(Context context) {
        String str;
        String str2;
        Exception e;
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        try {
            str = telephonyManager.getDeviceId();
            try {
                if (!TextUtils.isEmpty(str) && str.startsWith("86")) {
                    return str;
                }
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                Method declaredMethod = telephonyManager.getClass().getDeclaredMethod("getImei", new Class[]{Integer.TYPE});
                declaredMethod.setAccessible(true);
                str2 = (String) declaredMethod.invoke(telephonyManager, new Object[]{0});
                return str2;
            }
        } catch (Exception e3) {
            Exception exc = e3;
            str = "000000000000000";
            e = exc;
            e.printStackTrace();
            Method declaredMethod2 = telephonyManager.getClass().getDeclaredMethod("getImei", new Class[]{Integer.TYPE});
            declaredMethod2.setAccessible(true);
            str2 = (String) declaredMethod2.invoke(telephonyManager, new Object[]{0});
            return str2;
        }
        try {
            Method declaredMethod22 = telephonyManager.getClass().getDeclaredMethod("getImei", new Class[]{Integer.TYPE});
            declaredMethod22.setAccessible(true);
            str2 = (String) declaredMethod22.invoke(telephonyManager, new Object[]{0});
            if (!TextUtils.isEmpty(str2) && str2.startsWith("86")) {
                return str2;
            }
        } catch (Exception e4) {
            e4.printStackTrace();
        }
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                for (int i = 0; i < telephonyManager.getPhoneCount(); i++) {
                    String deviceId = telephonyManager.getDeviceId(i);
                    if (!TextUtils.isEmpty(deviceId) && deviceId.startsWith("86")) {
                        return deviceId;
                    }
                }
            }
        } catch (Exception e5) {
            e5.printStackTrace();
        }
        return TextUtils.isEmpty(str) ? "000000000000000" : str;
    }

    private static String getSimSerialNo(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getSimSerialNumber();
        } catch (Exception e) {
            e.printStackTrace();
            return "000000000000000";
        }
    }

    private static void acquireIsNewUser(Context context) {
        long longPref = PreferenceUtil.getLongPref(context, KEY_INSTALL_TIME, 0);
        long currentTimeMillis = System.currentTimeMillis();
        if (longPref > 0) {
            long j = currentTimeMillis - longPref;
            if (j >= 0) {
                if (j < 604800000) {
                    IS_NEW_USER = true;
                    return;
                } else {
                    IS_NEW_USER = false;
                    return;
                }
            }
        }
        IS_NEW_USER = true;
        PreferenceUtil.setLongPref(context, KEY_INSTALL_TIME, Long.valueOf(currentTimeMillis));
    }

    private static boolean isMiui(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.miui.cloudservice", 0);
            if (packageInfo == null || (packageInfo.applicationInfo.flags & 1) == 0) {
                return false;
            }
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getMiuiVersion() {
        return MiuiUtils.getMiuiVersion();
    }

    public static String getLocalIpAddress() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (true) {
                    if (inetAddresses.hasMoreElements()) {
                        InetAddress nextElement = inetAddresses.nextElement();
                        if (!nextElement.isLoopbackAddress()) {
                            return nextElement.getHostAddress();
                        }
                    }
                }
            }
            return null;
        } catch (SocketException unused) {
            return null;
        }
    }

    public static boolean isSDCardUnavailable() {
        return Environment.getExternalStorageState().equals("removed");
    }

    public static boolean isSDCardBusy() {
        return !Environment.getExternalStorageState().equals("mounted");
    }

    public static boolean isSDCardFull() {
        return getSDCardAvailableBytes() <= 102400;
    }

    public static boolean isSDCardUseful() {
        return !isSDCardBusy() && !isSDCardFull() && !isSDCardUnavailable();
    }

    public static long getSDCardAvailableBytes() {
        if (isSDCardBusy()) {
            return 0;
        }
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return ((long) statFs.getBlockSize()) * (((long) statFs.getAvailableBlocks()) - 4);
    }

    public static String getSDCardPath() {
        if (!isSDCardAvailable()) {
            return null;
        }
        return Environment.getExternalStorageDirectory().getPath();
    }

    public static boolean isSDCardAvailable() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static boolean isAppInstalled(String str) {
        for (PackageInfo next : ShopApp.instance.getPackageManager().getInstalledPackages(0)) {
            if (str != null && str.equals(next.packageName)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isIntentAvailable(Intent intent) {
        return ShopApp.instance.getPackageManager().queryIntentActivities(intent, 65536).size() > 0;
    }
}
