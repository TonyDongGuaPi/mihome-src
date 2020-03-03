package com.xiaomi.smarthome.wificonfig;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Build;
import com.miui.whetstone.WhetstoneResult;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.library.common.util.ListUtils;
import com.xiaomi.smarthomedevice.R;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

public class WifiSettingUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final int f22961a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    private static boolean f = false;
    private static boolean g = false;

    public static class KuailianScanResult {

        /* renamed from: a  reason: collision with root package name */
        public ScanResult f22964a;
        public boolean b;
        public boolean c;
        public String d;
    }

    enum PskType {
        UNKNOWN,
        WPA,
        WPA2,
        WPA_WPA2
    }

    enum TKIPType {
        TKIP_CCMP,
        TKIP,
        CCMP,
        NONE
    }

    public static int a(int i, int i2) {
        if (i <= -100) {
            return 0;
        }
        if (i >= -55) {
            return 100;
        }
        return (int) ((((float) (i - -100)) * ((float) 100)) / ((float) 45));
    }

    public static void a(WifiConfiguration wifiConfiguration, String str, String str2, String str3) {
        int a2 = a(str3);
        wifiConfiguration.allowedAuthAlgorithms.clear();
        wifiConfiguration.allowedGroupCiphers.clear();
        wifiConfiguration.allowedKeyManagement.clear();
        wifiConfiguration.allowedPairwiseCiphers.clear();
        wifiConfiguration.allowedProtocols.clear();
        wifiConfiguration.SSID = c(str);
        switch (a2) {
            case 0:
                wifiConfiguration.allowedKeyManagement.set(0);
                return;
            case 1:
                wifiConfiguration.hiddenSSID = true;
                String[] strArr = wifiConfiguration.wepKeys;
                strArr[0] = "\"" + str2 + "\"";
                wifiConfiguration.allowedAuthAlgorithms.set(1);
                wifiConfiguration.allowedGroupCiphers.set(3);
                wifiConfiguration.allowedGroupCiphers.set(2);
                wifiConfiguration.allowedGroupCiphers.set(0);
                wifiConfiguration.allowedGroupCiphers.set(1);
                wifiConfiguration.allowedKeyManagement.set(0);
                wifiConfiguration.wepTxKeyIndex = 0;
                break;
            case 2:
                break;
            default:
                return;
        }
        wifiConfiguration.preSharedKey = "\"" + str2 + "\"";
        wifiConfiguration.allowedKeyManagement.set(1);
        TKIPType b2 = b(str3);
        if (b2 == TKIPType.TKIP_CCMP) {
            wifiConfiguration.allowedGroupCiphers.set(3);
            wifiConfiguration.allowedGroupCiphers.set(2);
            wifiConfiguration.allowedPairwiseCiphers.set(2);
            wifiConfiguration.allowedPairwiseCiphers.set(1);
        } else if (b2 == TKIPType.TKIP) {
            wifiConfiguration.allowedGroupCiphers.set(2);
            wifiConfiguration.allowedPairwiseCiphers.set(1);
        } else if (b2 == TKIPType.CCMP) {
            wifiConfiguration.allowedGroupCiphers.set(3);
            wifiConfiguration.allowedPairwiseCiphers.set(2);
        }
        wifiConfiguration.status = 0;
    }

    public static boolean a() {
        if (Build.VERSION.SDK_INT >= 21) {
            for (String equals : Build.SUPPORTED_ABIS) {
                if (equals.equals("x86_64")) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void a(WifiConfiguration wifiConfiguration, ScanResult scanResult, String str) {
        int a2 = a(scanResult);
        wifiConfiguration.SSID = c(scanResult.SSID);
        switch (a2) {
            case 0:
                wifiConfiguration.allowedKeyManagement.set(0);
                return;
            case 1:
                wifiConfiguration.hiddenSSID = true;
                String[] strArr = wifiConfiguration.wepKeys;
                strArr[0] = "\"" + str + "\"";
                wifiConfiguration.allowedAuthAlgorithms.set(1);
                wifiConfiguration.allowedGroupCiphers.set(3);
                wifiConfiguration.allowedGroupCiphers.set(2);
                wifiConfiguration.allowedGroupCiphers.set(0);
                wifiConfiguration.allowedGroupCiphers.set(1);
                wifiConfiguration.allowedKeyManagement.set(0);
                wifiConfiguration.wepTxKeyIndex = 0;
                break;
            case 2:
                break;
            default:
                return;
        }
        wifiConfiguration.preSharedKey = "\"" + str + "\"";
        wifiConfiguration.allowedKeyManagement.set(1);
        TKIPType b2 = b(scanResult);
        if (b2 == TKIPType.TKIP_CCMP) {
            wifiConfiguration.allowedGroupCiphers.set(3);
            wifiConfiguration.allowedGroupCiphers.set(2);
            wifiConfiguration.allowedPairwiseCiphers.set(2);
            wifiConfiguration.allowedPairwiseCiphers.set(1);
        } else if (b2 == TKIPType.TKIP) {
            wifiConfiguration.allowedGroupCiphers.set(2);
            wifiConfiguration.allowedPairwiseCiphers.set(1);
        } else if (b2 == TKIPType.CCMP) {
            wifiConfiguration.allowedGroupCiphers.set(3);
            wifiConfiguration.allowedPairwiseCiphers.set(2);
        }
        wifiConfiguration.status = 0;
    }

    public static int a(ScanResult scanResult) {
        if (scanResult.capabilities.contains("WEP")) {
            return 1;
        }
        if (scanResult.capabilities.contains("PSK")) {
            return 2;
        }
        if (scanResult.capabilities.contains("EAP")) {
            return 3;
        }
        return scanResult.capabilities.contains("WAPI") ? 4 : 0;
    }

    public static int a(String str) {
        if (str.equalsIgnoreCase("psk2") || str.equalsIgnoreCase("mixed-psk")) {
            return 2;
        }
        if (str.contains("WEP")) {
            return 1;
        }
        if (str.contains("PSK")) {
            return 2;
        }
        if (str.contains("EAP")) {
            return 3;
        }
        return str.contains("WAPI") ? 4 : 0;
    }

    public static TKIPType b(ScanResult scanResult) {
        boolean contains = scanResult.capabilities.contains("TKIP");
        boolean contains2 = scanResult.capabilities.contains("CCMP");
        if (contains && contains2) {
            return TKIPType.TKIP_CCMP;
        }
        if (contains) {
            return TKIPType.TKIP;
        }
        if (contains2) {
            return TKIPType.CCMP;
        }
        return TKIPType.NONE;
    }

    public static TKIPType b(String str) {
        if (str.equalsIgnoreCase("psk2")) {
            return TKIPType.CCMP;
        }
        return TKIPType.TKIP_CCMP;
    }

    public static PskType c(ScanResult scanResult) {
        boolean contains = scanResult.capabilities.contains("WPA-PSK");
        boolean contains2 = scanResult.capabilities.contains("WPA2-PSK");
        if (contains2 && contains) {
            return PskType.WPA_WPA2;
        }
        if (contains2) {
            return PskType.WPA2;
        }
        if (contains) {
            return PskType.WPA;
        }
        return PskType.UNKNOWN;
    }

    public static String c(String str) {
        return "\"" + str + "\"";
    }

    public static boolean a(String str, String str2) {
        if (str == null || str2 == null) {
            return false;
        }
        if (str.startsWith("\"")) {
            str = str.substring(1, str.length() - 1);
        }
        if (str2.startsWith("\"")) {
            str2 = str2.substring(1, str2.length() - 1);
        }
        return str.equalsIgnoreCase(str2);
    }

    public static String a(Context context, KuailianScanResult kuailianScanResult) {
        String str = "";
        int a2 = a(kuailianScanResult.f22964a);
        if (kuailianScanResult.b && a2 != 0) {
            str = str + context.getString(R.string.kuailian_save_passwd) + "，";
        }
        if (a2 == 0) {
            return str + context.getString(R.string.kuailian_no_passwd);
        } else if (a2 == 2) {
            PskType c2 = c(kuailianScanResult.f22964a);
            if (c2 == PskType.WPA) {
                return str + String.format(context.getString(R.string.kuailian_wifi_security_type), new Object[]{"WPA"});
            } else if (c2 == PskType.WPA2) {
                return str + String.format(context.getString(R.string.kuailian_wifi_security_type), new Object[]{"WPA2"});
            } else if (c2 != PskType.WPA_WPA2) {
                return str;
            } else {
                return str + String.format(context.getString(R.string.kuailian_wifi_security_type), new Object[]{"WPA_WPA2"});
            }
        } else if (a2 == 1) {
            return str + String.format(context.getString(R.string.kuailian_wifi_security_type), new Object[]{"WEP"});
        } else if (a2 == 3) {
            return str + String.format(context.getString(R.string.kuailian_wifi_security_type), new Object[]{"802.1X"});
        } else if (a2 != 4) {
            return str;
        } else {
            return str + String.format(context.getString(R.string.kuailian_wifi_security_type), new Object[]{"WAPI"});
        }
    }

    public static int a(int i) {
        if (i > 80) {
            return R.drawable.wifi_strength_4;
        }
        if (i > 60) {
            return R.drawable.wifi_strength_3;
        }
        if (i > 40) {
            return R.drawable.wifi_strength_2;
        }
        if (i > 20) {
            return R.drawable.wifi_strength_1;
        }
        return R.drawable.wifi_strength_0;
    }

    public static int a(WifiConfiguration wifiConfiguration) {
        return wifiConfiguration.allowedKeyManagement.get(1) ? 2 : 0;
    }

    public static boolean d(ScanResult scanResult) {
        try {
            Field declaredField = Class.forName(ScanResult.class.getName()).getDeclaredField("isXiaomiRouter");
            if (declaredField != null) {
                return declaredField.getBoolean(scanResult);
            }
            return false;
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException unused) {
            return false;
        }
    }

    public static String a(WhetstoneResult whetstoneResult) {
        try {
            Field declaredField = Class.forName(WhetstoneResult.class.getName()).getDeclaredField("mResult");
            if (declaredField != null) {
                return (String) declaredField.get(whetstoneResult);
            }
            return null;
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException unused) {
            return null;
        }
    }

    public static Class b() {
        try {
            Class<?> cls = Class.forName("com.miui.whetstone.WhetstoneManager");
            if (((Integer) cls.getDeclaredMethod("getSmartConfigLevel", new Class[0]).invoke((Object) null, new Object[0])).intValue() == 2) {
                return cls;
            }
            return null;
        } catch (Exception unused) {
        }
    }

    public static boolean d(String str) {
        byte[] bytes = str.getBytes();
        for (byte b2 : bytes) {
            if ((b2 & 128) != 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean a(WifiManager wifiManager, String str) {
        WifiConfiguration wifiConfiguration;
        List<WifiConfiguration> configuredNetworks = wifiManager.getConfiguredNetworks();
        if (configuredNetworks == null) {
            return false;
        }
        Iterator<WifiConfiguration> it = configuredNetworks.iterator();
        while (true) {
            if (!it.hasNext()) {
                wifiConfiguration = null;
                break;
            }
            wifiConfiguration = it.next();
            if (a(wifiConfiguration.SSID, str)) {
                break;
            }
        }
        if (wifiConfiguration != null) {
            return true;
        }
        return false;
    }

    public static void b(final WifiManager wifiManager, final String str) {
        LogUtilGrey.a("WifiState", "disconnectAp " + str);
        CommonApplication.getGlobalWorkerHandler().post(new Runnable() {
            public void run() {
                List<WifiConfiguration> configuredNetworks = wifiManager.getConfiguredNetworks();
                if (configuredNetworks != null) {
                    WifiConfiguration wifiConfiguration = null;
                    Iterator<WifiConfiguration> it = configuredNetworks.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        WifiConfiguration next = it.next();
                        if (next.SSID != null && next.SSID.equals(WifiSettingUtils.c(str))) {
                            wifiConfiguration = next;
                            break;
                        }
                    }
                    if (wifiConfiguration != null) {
                        wifiManager.disableNetwork(wifiConfiguration.networkId);
                        wifiManager.disconnect();
                        wifiManager.removeNetwork(wifiConfiguration.networkId);
                    }
                }
            }
        });
    }

    public static void a(WifiManager wifiManager, String str, String str2, String str3, String str4) {
        LogUtilGrey.a("WifiState", "connectToAP " + str + " bssid:" + str3 + " security:" + str4);
        final WifiManager wifiManager2 = wifiManager;
        final String str5 = str;
        final String str6 = str4;
        final String str7 = str2;
        final String str8 = str3;
        CommonApplication.getGlobalWorkerHandler().post(new Runnable() {
            public void run() {
                if (wifiManager2.isWifiEnabled()) {
                    List<WifiConfiguration> configuredNetworks = wifiManager2.getConfiguredNetworks();
                    WifiConfiguration wifiConfiguration = null;
                    if (!ListUtils.a(configuredNetworks)) {
                        Iterator<WifiConfiguration> it = configuredNetworks.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            WifiConfiguration next = it.next();
                            if (next.SSID != null && next.SSID.equals(WifiSettingUtils.c(str5)) && WifiSettingUtils.a(next) == WifiSettingUtils.a(str6)) {
                                wifiConfiguration = next;
                                break;
                            }
                        }
                    }
                    boolean z = false;
                    Iterator<ScanResult> it2 = wifiManager2.getScanResults().iterator();
                    while (true) {
                        if (it2.hasNext()) {
                            if (it2.next().SSID.equalsIgnoreCase(str5)) {
                                z = true;
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    LogUtilGrey.a("WifiState", "isContain " + str5 + " - " + z);
                    if (wifiConfiguration != null) {
                        try {
                            wifiManager2.disconnect();
                            wifiManager2.enableNetwork(wifiConfiguration.networkId, true);
                            wifiManager2.reconnect();
                        } catch (SecurityException unused) {
                        }
                    } else {
                        WifiConfiguration wifiConfiguration2 = new WifiConfiguration();
                        WifiSettingUtils.a(wifiConfiguration2, str5, str7, str6);
                        wifiConfiguration2.BSSID = str8;
                        int addNetwork = wifiManager2.addNetwork(wifiConfiguration2);
                        wifiManager2.disconnect();
                        wifiManager2.enableNetwork(addNetwork, true);
                        wifiManager2.reconnect();
                    }
                }
            }
        });
    }
}
