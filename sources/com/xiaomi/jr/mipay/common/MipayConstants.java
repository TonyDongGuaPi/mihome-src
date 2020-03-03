package com.xiaomi.jr.mipay.common;

import com.xiaomi.jr.account.XiaomiService;
import com.xiaomi.jr.account.XiaomiServices;
import com.xiaomi.jr.mipay.common.http.HostManager;

public class MipayConstants {
    public static final String A = "simCountryIso";
    public static final String B = "iccid";
    public static final String C = "displayResolution";
    public static final String D = "displayDensity";
    public static final String E = "screenSize";
    public static final String F = "apkChannel";
    public static final String G = "romChannel";
    public static final String H = "platform";
    public static final String I = "package";
    public static final String J = "apkSign";
    public static final String K = "version";
    public static final String L = "versionCode";
    public static final String M = "imei";
    public static final String N = "imsi";
    public static final String O = "mac";
    public static final String P = "androidId";
    public static final String Q = "networkType";
    public static final String R = "networkMeter";
    public static final String S = "hardware";
    public static final String T = "buildDisplay";
    public static final String U = "buildTags";
    public static final String V = "buildHost";
    public static final String W = "networkOperator";
    public static final String X = "networkOperatorName";
    public static final String Y = "cellLocation";
    public static final String Z = "countryIso";

    /* renamed from: a  reason: collision with root package name */
    public static String f10939a = null;
    public static final String aa = "phoneType";
    public static final String ab = "deviceSoftwareVersion";
    public static final String ac = "deviceConfig";
    public static final String ad = "bluetoothMac";
    public static final String ae = "wifiGateway";
    public static final String af = "wifiSSID";
    public static final String ag = "wifiBSSID";
    public static final String ah = "phoneNumber";
    public static final String ai = "sensorList";
    public static final String aj = "cpuType";
    public static final String ak = "cpuSpeed";
    public static final String al = "cpuHardware";
    public static final String am = "cpuSerial";
    public static final String an = "elapsedRealtime";
    public static final String ao = "upTime";
    public static final String ap = "bootTime";
    public static final String aq = "totalStorage";
    public static final String ar = "location";
    public static final String as = "xiaomiDeviceToken";
    public static final String b = "userId";
    public static final String c = "session";
    public static final String d = "deviceId";
    public static final String e = "processType";
    public static final String f = "processId";
    public static final String g = "title";
    public static final String h = "url";
    public static final String i = "uuid";
    public static final String j = "model";
    public static final String k = "device";
    public static final String l = "product";
    public static final String m = "manufacturer";
    public static final String n = "brand";
    public static final String o = "buildType";
    public static final String p = "sdk";
    public static final String q = "systemVersion";
    public static final String r = "systemRelease";
    public static final String s = "os";
    public static final String t = "miuiVersion";
    public static final String u = "miuiUiVersion";
    public static final String v = "miuiUiVersionCode";
    public static final String w = "co";
    public static final String x = "la";
    public static final String y = "carrier";
    public static final String z = "simOperatorName";

    public interface Mgc {

        /* renamed from: a  reason: collision with root package name */
        public static final int f10940a = 200;
        public static final int b = 201;
        public static final int c = 1000000;
        public static final int d = 1999999;
        public static final int e = 2000000;
        public static final int f = 2000001;
        public static final int g = 2000002;
        public static final int h = 2000003;
        public static final int i = 2000004;
        public static final int j = 2010002;
        public static final int k = 2010003;
        public static final int l = 2010008;
        public static final int m = 2010009;
        public static final int n = 2999999;
        public static final int o = 3000000;
        public static final int p = 3000001;
        public static final int q = 3000002;
        public static final int r = 3000004;
        public static final int s = 3999999;
        public static final int t = 100000000;
        public static final int u = 199999999;
        public static final int v = 200000000;
        public static final int w = 299999999;
        public static final int x = 300000000;
        public static final int y = 399999999;
    }

    static {
        XiaomiService a2 = XiaomiServices.a(HostManager.f10942a);
        f10939a = a2 != null ? a2.b : "mipay";
    }
}
