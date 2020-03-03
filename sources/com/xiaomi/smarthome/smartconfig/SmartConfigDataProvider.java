package com.xiaomi.smarthome.smartconfig;

import android.net.wifi.ScanResult;
import com.xiaomi.smarthome.framework.api.RouterRemoteApi;
import java.util.concurrent.ConcurrentHashMap;

public class SmartConfigDataProvider {
    public static final String A = "camera_bind_key_expire_time";
    public static final String B = "ap_connect_failed_times";
    public static final String C = "scan_qr_model";
    public static final String D = "scan_qr_code";
    public static final String E = "restore_wifi";
    public static final String F = "start_time";
    public static final String G = "bind_device_token";
    public static final String H = "bind_device_sn";
    public static final String I = "bind_device_timestamp";
    public static final String J = "bind_device_did";
    public static final String K = "bind_device_key";
    public static final String L = "check_bind_key_did";
    public static final String M = "enter_scan_qrcode_normal";
    public static final String N = "from_miui";
    public static final String O = "combo_ble_mac";
    public static final String P = "combo_ble_key";
    public static final String Q = "combo_strategy";
    public static final String R = "camera_process";
    public static final String S = "gateway_did";
    public static final String T = "zigbee_nfc";
    public static final String U = "camera_qrcode_oob";
    public static final String V = "wifi_send_ways";
    public static final int W = 1;
    public static final int X = 2;
    public static final int Y = 3;
    public static final int Z = 4;

    /* renamed from: a  reason: collision with root package name */
    public static final String f22323a = "mi_router_in_device_list";
    public static final String aa = "connect_result";
    public static final String ab = "key_strategy_index";
    public static final String ac = "key_time_start";
    public static final String ad = "connect_from";
    public static final int ae = 1;
    public static final int af = 2;
    public static final int ag = 3;
    public static final int ah = 4;
    public static final int ai = 0;
    public static final int aj = 5;
    public static final int ak = 6;
    public static final int al = 7;
    private static SmartConfigDataProvider am = null;
    public static final String b = "mi_router_info";
    public static final String c = "selected_ap";
    public static final String d = "selected_ap_ssid";
    public static final String e = "selected_ap_passwd";
    public static final String f = "selected_ap_capabilities";
    public static final String g = "selected_ap_level";
    public static final String h = "device_ap";
    public static final String i = "device_ap_bssid";
    public static final String j = "length";
    public static final String k = "sign";
    public static final String l = "ticket";
    public static final String m = "device_model";
    public static final String n = "target_bssid";
    public static final String o = "target_passwd";
    public static final String p = "miui_class";
    public static final String q = "wifi_ssid";
    public static final String r = "aiot_wifi";
    public static final String s = "gmt_offset";
    public static final String t = "gmt_tz";
    public static final String u = "user_qr_code";
    public static final String v = "send_passwd_success";
    public static final String w = "connected_device";
    public static final String x = "find_device_time";
    public static final String y = "goto_error_page";
    public static final String z = "camera_bind_key";
    private ConcurrentHashMap<String, Object> an = new ConcurrentHashMap<>();

    public static SmartConfigDataProvider a() {
        if (am == null) {
            am = new SmartConfigDataProvider();
        }
        return am;
    }

    private SmartConfigDataProvider() {
    }

    public Object a(String str) {
        if (str == null || !this.an.containsKey(str)) {
            return null;
        }
        return this.an.get(str);
    }

    public Object a(String str, Object obj) {
        return (str == null || !this.an.containsKey(str)) ? obj : this.an.get(str);
    }

    public void b(String str, Object obj) {
        if (str != null && obj != null) {
            this.an.put(str, obj);
        }
    }

    public void b(String str) {
        this.an.remove(str);
    }

    public String b() {
        if (a(b) != null) {
            return ((RouterRemoteApi.WifiInfo) a(b)).c;
        }
        if (a(c) != null) {
            return ((ScanResult) a(c)).SSID;
        }
        return null;
    }

    public String c() {
        if (a(b) != null) {
            return ((RouterRemoteApi.WifiInfo) a(b)).d;
        }
        if (a(c) != null) {
            return ((ScanResult) a(c)).BSSID;
        }
        return null;
    }

    public String d() {
        if (a(b) != null) {
            return ((RouterRemoteApi.WifiInfo) a(b)).e;
        }
        return (String) a(e);
    }

    public String e() {
        if (a(b) == null) {
            Object a2 = a(c);
            if (a2 == null) {
                return "";
            }
            return ((ScanResult) a2).capabilities;
        } else if (((RouterRemoteApi.WifiInfo) a(b)).g.equalsIgnoreCase("psk2")) {
            return "[WPA2-PSK-CCMP]";
        } else {
            return ((RouterRemoteApi.WifiInfo) a(b)).g.equalsIgnoreCase("mixed-psk") ? "[WPA-PSK-CCMP+TKIP]" : "";
        }
    }

    public void f() {
        this.an.clear();
    }
}
