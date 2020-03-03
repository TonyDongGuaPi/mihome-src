package com.xiaomi.smarthome.device;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.mi.global.bbs.utils.ConnectionHelper;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.bluetooth.XmBluetoothRecord;
import com.xiaomi.smarthome.core.entity.device.Location;
import com.xiaomi.smarthome.core.entity.device.VirtualDevice;
import com.xiaomi.smarthome.core.entity.device.WatchBandDevice;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.bluetooth.BleCacheUtils;
import com.xiaomi.smarthome.device.utils.ClientIconMap;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.family.FamilyMemberData;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.library.common.util.SystemUtils;
import com.xiaomi.smarthome.miio.camera.match.CameraDevice;
import com.xiaomi.smarthome.miio.device.CurtainDevice;
import com.xiaomi.smarthome.miio.device.PhoneDevice;
import com.xiaomi.smarthome.miio.device.PhoneIRDevice;
import com.xiaomi.smarthome.miio.device.SmartBulbDevice;
import com.xiaomi.smarthome.newui.card.ProductModel;
import com.xiaomi.smarthomedevice.R;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceFactory {
    public static final String A = "lumi.sensor_switch.v2";
    public static final String B = "xiaomi.myphone.v1";
    public static final String C = "xiaomi.phone_ir.v1";
    public static final String D = "xiaomi.phone_ir.t1";
    public static final String E = "onemore.soundbox.sm001";
    public static final String F = "xiaomi.tv.v1";
    public static final String G = "xiaomi.tv.b1";
    public static final String H = "xiaomi.tv.i1";
    public static final String I = "xiaomi.tv.h1";
    public static final String J = "xiaomi.tvbox.v1";
    public static final String K = "xiaomi.tvbox.b1";
    public static final String L = "xiaomi.tvbox.i1";
    public static final String M = "xiaomi.split_tv.b1";
    public static final String N = "xiaomi.split_tv.v1";
    public static final String O = "fengmi.projector.fm15";
    public static final String P = "inovel.projector.me2";
    public static final String Q = "zimi.projector.v1";
    public static final String R = "fengmi.projector.fm154k";
    public static final String S = "fengmi.projector.fm05";
    public static final String T = "yeelink.light.rgb1";
    public static final String U = "yeelight.rgb.v1";
    public static final String V = "midea.aircondition.v1";
    public static final String W = "aux.aircondition.v1";
    public static final String X = "ge.light.mono1";
    public static final String Y = "xiaomi.mikey.v1";
    public static final String Z = "jomoo.toilet.ao3";

    /* renamed from: a  reason: collision with root package name */
    public static final String f14817a = "none";
    public static final String aA = "xiaoxun.watch.v8";
    public static final String aB = "xiaoxun.watch.v9";
    public static final String aC = "xiaoxun.watch.v10";
    public static final String aD = "xiaoxun.watch.v11";
    public static final String aE = "xiaoxun.watch.sw710a2";
    public static final String aF = "mijia.camera.v1";
    public static final String aG = "mijia.camera.v2";
    public static final String aH = "mijia.camera.v3";
    public static final String aI = "mijia.camera.v4";
    public static final String aJ = "mijia-camera-v1";
    public static final String aK = "chuangmi.camera.v2";
    public static final String aL = "xiaomi.plc.v1";
    public static final String aM = "hmpace.watch.v1";
    public static final String aN = "viomi.fridge.v4";
    public static final String aO = "ls123.headphone.t10";
    public static final String aP = "hmpace.bracelet.v3nfc";
    public static final String[] aQ = {"xiaomi.tvbox.204", "xiaomi.tvbox.205", "xiaomi.tvbox.206", "xiaomi.tvbox.207", "xiaomi.tv.601", "xiaomi.tv.602", "xiaomi.tv.603", "xiaomi.tv.604"};
    private static Map<String, Class<?>> aR = new HashMap();
    public static final String aa = "yeelink.light.ble1";
    public static final String ab = "philips.light.mono1";
    public static final String ac = "zimi.powerbank.v1";
    public static final String ad = "lumi.sensor_ir.v1";
    public static final String ae = "chuangmi.camera.xiaobai";
    public static final String af = "chuangmi.camera.v3";
    public static final String ag = "chuangmi.camera.v4";
    public static final String ah = "chuangmi.camera.v5";
    public static final String ai = "chuangmi.camera.v6";
    public static final String aj = "chuangmi.camera.ipc009";
    public static final String ak = "chuangmi.camera.ipc019";
    public static final String al = "chuangmi.wifi.v1";
    public static final String am = "xiaomi.advertise.v1";
    public static final String an = "haiii.pettag.v1";
    public static final String ao = "xiaomi.dev.v3";
    public static final String ap = "runmi.suitcase.v1";
    public static final String aq = "soocare.toothbrush.x3";
    public static final String ar = "isa.camera.df3";
    public static final String as = "isa.camera.isc5";
    public static final String at = "isa-camera-isc5";
    public static final String au = "xiaoxun.watch.v2";
    public static final String av = "xiaoxun.watch.v3";
    public static final String aw = "xiaoxun.watch.v4";
    public static final String ax = "xiaoxun.watch.v5";
    public static final String ay = "xiaoxun.watch.v6";
    public static final String az = "xiaoxun.watch.v7";
    public static final String b = "other";
    public static final String c = "chuangmi.plug.v1";
    public static final String d = "lumi.plug.v1";
    public static final String e = "chuangmi.plug.v2";
    public static final String f = "idelan.aircondition.v1";
    public static final String g = "yunmi.waterpurifier.v2";
    public static final String h = "handring";
    public static final String i = "yunyi.camera.v1";
    public static final String j = "yunyi.camera.v2";
    public static final String k = "yunyi.camera.mj1";
    public static final String l = "fimi.camera.c1";
    public static final String m = "xiaomi.ir.v1";
    public static final String n = "chuangmi.ir.v2";
    public static final String o = "zhimi.airpurifier.v1";
    public static final String p = "zhimi.airpurifier.v2";
    public static final String q = "zhimi.airpurifier.v3";
    public static final String r = "xiaomi.curtain.v1";
    public static final String s = "xiaomi.ble.v1";
    public static final String t = "lumi.gateway.v1";
    public static final String u = "lumi.gateway.v2";
    public static final String v = "lumi.sensor_motion.v1";
    public static final String w = "lumi.sensor_magnet.v1";
    public static final String x = "lumi.sensor_switch.v1";
    public static final String y = "lumi.sensor_motion.v2";
    public static final String z = "lumi.sensor_magnet.v2";

    public enum AP_TYPE {
        AP_NONE,
        AP_MIIO,
        AP_MIAP,
        AP_MIBAP,
        AP_MIDEVICE,
        AP_MIDEA_AC,
        AP_MIDEA
    }

    public static String j(String str) {
        return str == null ? "" : str;
    }

    static {
        aR.put("yunyi.camera.v1", CameraDevice.class);
        aR.put("xiaomi.curtain.v1", CurtainDevice.class);
        aR.put("xiaomi.ble.v1", BleDevice.class);
        aR.put("xiaomi.tv.v1", MiTVDevice.class);
        aR.put("xiaomi.tv.b1", MiTVDevice.class);
        aR.put("xiaomi.tv.i1", MiTVDevice.class);
        aR.put("xiaomi.tv.h1", MiTVDevice.class);
        aR.put("xiaomi.tvbox.v1", MiTVDevice.class);
        aR.put("xiaomi.tvbox.b1", MiTVDevice.class);
        aR.put("xiaomi.tvbox.i1", MiTVDevice.class);
        aR.put("xiaomi.split_tv.b1", MiTVDevice.class);
        aR.put("xiaomi.split_tv.v1", MiTVDevice.class);
        aR.put("fengmi.projector.fm15", MiTVDevice.class);
        aR.put("inovel.projector.me2", MiTVDevice.class);
        aR.put("zimi.projector.v1", MiTVDevice.class);
        aR.put("fengmi.projector.fm154k", MiTVDevice.class);
        aR.put("fengmi.projector.fm05", MiTVDevice.class);
        aR.put("xiaomi.myphone.v1", PhoneDevice.class);
        aR.put("xiaomi.phone_ir.t1", PhoneIRDevice.class);
        aR.put("xiaomi.phone_ir.v1", PhoneIRDevice.class);
        aR.put("yeelink.light.rgb1", SmartBulbDevice.class);
        aR.put("yeelight.rgb.v1", SmartBulbDevice.class);
        for (String put : aQ) {
            aR.put(put, MiTVDevice.class);
        }
        aR.put(aP, WatchBandDevice.class);
    }

    public static boolean a(String str) {
        return "mijia.camera.v1".equals(str) || "mijia.camera.v2".equals(str);
    }

    public static boolean b(String str) {
        return "mijia.camera.v1".equalsIgnoreCase(str) || "mijia.camera.v2".equalsIgnoreCase(str);
    }

    public static boolean c(String str) {
        PluginRecord d2 = CoreApi.a().d(str);
        boolean z2 = (d2 == null || d2.c() == null || !d2.c().K) ? false : true;
        if ("mijia.camera.v1".equalsIgnoreCase(str) || str.equalsIgnoreCase("xiaomi.wifispeaker.v2") || str.equalsIgnoreCase("xiaomi.wifispeaker.v1") || str.equalsIgnoreCase(ProductModel.v) || str.equalsIgnoreCase("chuangmi.camera.v6") || str.equalsIgnoreCase("xiaomi.wifispeaker.s12") || z2) {
            return true;
        }
        return false;
    }

    public static boolean d(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (str.contains("5G") || str.contains("5g")) {
            return true;
        }
        return false;
    }

    public static Class<?> e(String str) {
        Class<?> cls = aR.get(str);
        return (cls != null || !str.startsWith("xiaomi.router") || !CoreApi.a().c(j(str))) ? cls : RouterDevice.class;
    }

    public static String a(ScanResult scanResult) {
        if (scanResult == null || TextUtils.isEmpty(scanResult.SSID)) {
            return "none";
        }
        String i2 = i(scanResult.SSID);
        return CoreApi.a().c(i2) ? i2 : "none";
    }

    public static AP_TYPE b(ScanResult scanResult) {
        String[] split = scanResult.BSSID.split("\\:");
        if (split.length < 4) {
            return AP_TYPE.AP_NONE;
        }
        split[split.length - 2] + split[split.length - 1];
        int indexOf = scanResult.SSID.indexOf("_midev");
        if (indexOf <= 0 || indexOf + 5 >= scanResult.SSID.length()) {
            return AP_TYPE.AP_NONE;
        }
        return AP_TYPE.AP_MIDEVICE;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0008, code lost:
        r0 = r2.indexOf("_midev");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String c(android.net.wifi.ScanResult r2) {
        /*
            java.lang.String r2 = r2.SSID
            boolean r0 = android.text.TextUtils.isEmpty(r2)
            if (r0 != 0) goto L_0x001f
            java.lang.String r0 = "_midev"
            int r0 = r2.indexOf(r0)
            r1 = 1
            if (r0 <= r1) goto L_0x001f
            r1 = 0
            java.lang.String r2 = r2.substring(r1, r0)
            r0 = 45
            r1 = 46
            java.lang.String r2 = r2.replace(r0, r1)
            return r2
        L_0x001f:
            java.lang.String r2 = ""
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.DeviceFactory.c(android.net.wifi.ScanResult):java.lang.String");
    }

    public static String a(String str, String str2) {
        if (str == null) {
            return null;
        }
        PluginRecord d2 = CoreApi.a().d(str);
        boolean z2 = false;
        if (d2 != null && d2.c().e() == 3) {
            z2 = true;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str.replace('.', '-'));
        sb.append(z2 ? "_mibt" : "_miap");
        sb.append(str2);
        return sb.toString();
    }

    public static AP_TYPE d(ScanResult scanResult) {
        if (scanResult == null) {
            return AP_TYPE.AP_NONE;
        }
        return b(scanResult.SSID, scanResult.BSSID);
    }

    public static AP_TYPE b(String str, String str2) {
        int i2;
        if (str == null || str2 == null) {
            return AP_TYPE.AP_NONE;
        }
        String[] split = str2.split("\\:");
        if (split.length < 4) {
            return AP_TYPE.AP_NONE;
        }
        String str3 = split[split.length - 2] + split[split.length - 1];
        int indexOf = str.indexOf("_miio");
        if (indexOf > 0 && (i2 = indexOf + 5) < str.length() && str3.equalsIgnoreCase(str.substring(i2))) {
            return AP_TYPE.AP_MIIO;
        }
        int indexOf2 = str.indexOf("_miap");
        if (indexOf2 > 0 && indexOf2 + 5 < str.length()) {
            return AP_TYPE.AP_MIAP;
        }
        int indexOf3 = str.indexOf("_mibt");
        if (indexOf3 > 0 && indexOf3 + 5 < str.length()) {
            return AP_TYPE.AP_MIBAP;
        }
        if (str.contains("midea_ac")) {
            return AP_TYPE.AP_MIDEA;
        }
        if (str.contains("midea_AC")) {
            return AP_TYPE.AP_MIDEA_AC;
        }
        return AP_TYPE.AP_NONE;
    }

    public static boolean e(ScanResult scanResult) {
        return b(scanResult) == AP_TYPE.AP_MIDEVICE;
    }

    public static boolean f(ScanResult scanResult) {
        String a2 = a(scanResult);
        return !a2.equalsIgnoreCase("other") && !a2.equalsIgnoreCase("none");
    }

    public static boolean g(ScanResult scanResult) {
        return scanResult != null && d(scanResult) == AP_TYPE.AP_MIBAP;
    }

    public static boolean a(BleDevice bleDevice) {
        if (bleDevice != null) {
            return c(bleDevice.mac, bleDevice.model);
        }
        return false;
    }

    public static boolean c(String str, String str2) {
        return !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && BleCacheUtils.f(str) == 1;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0013, code lost:
        r4 = r4.BSSID.replace(":", "");
        r2 = r4.length();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String h(android.net.wifi.ScanResult r4) {
        /*
            java.lang.String r0 = r4.SSID
            java.lang.String r0 = g((java.lang.String) r0)
            r1 = 4
            if (r0 == 0) goto L_0x000f
            int r2 = r0.length()
            if (r2 == r1) goto L_0x0028
        L_0x000f:
            java.lang.String r2 = r4.BSSID
            if (r2 == 0) goto L_0x0028
            java.lang.String r4 = r4.BSSID
            java.lang.String r2 = ":"
            java.lang.String r3 = ""
            java.lang.String r4 = r4.replace(r2, r3)
            int r2 = r4.length()
            if (r2 < r1) goto L_0x0028
            int r2 = r2 - r1
            java.lang.String r0 = r4.substring(r2)
        L_0x0028:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.DeviceFactory.h(android.net.wifi.ScanResult):java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r2 = r2.replace(":", "");
        r0 = r2.length();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String f(java.lang.String r2) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r2)
            if (r0 != 0) goto L_0x001b
            java.lang.String r0 = ":"
            java.lang.String r1 = ""
            java.lang.String r2 = r2.replace(r0, r1)
            int r0 = r2.length()
            r1 = 4
            if (r0 < r1) goto L_0x001b
            int r0 = r0 - r1
            java.lang.String r2 = r2.substring(r0)
            return r2
        L_0x001b:
            java.lang.String r2 = ""
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.DeviceFactory.f(java.lang.String):java.lang.String");
    }

    public static String g(String str) {
        int indexOf = str.indexOf("_miio");
        if (indexOf > 0) {
            return str.substring(indexOf + 5);
        }
        int indexOf2 = str.indexOf("_miap");
        if (indexOf2 > 0) {
            return str.substring(indexOf2 + 5);
        }
        int indexOf3 = str.indexOf("_mibt");
        if (indexOf3 > 0) {
            return str.substring(indexOf3 + 5);
        }
        int indexOf4 = str.indexOf("_midev");
        if (indexOf4 > 0) {
            return str.substring(indexOf4 + 6);
        }
        if (CoreApi.a().f(str) != null) {
            return "";
        }
        int indexOf5 = str.indexOf("midea_ac_");
        return indexOf5 >= 0 ? str.substring(indexOf5 + 9) : str;
    }

    public static String i(ScanResult scanResult) {
        String a2 = a(scanResult);
        if (a2.equals("none")) {
            a2 = c(scanResult);
        }
        if (CoreApi.a().c(a2)) {
            return CoreApi.a().d(a2).p() + h(scanResult);
        } else if (a2.equalsIgnoreCase("chuangmi.plug.v1")) {
            return h("chuangmi.plug.v1") + h(scanResult);
        } else if (a2.equalsIgnoreCase("chuangmi.plug.v2")) {
            return h("chuangmi.plug.v2") + h(scanResult);
        } else if (a2.equalsIgnoreCase("yunyi.camera.v1") || a2.equalsIgnoreCase("yunyi.camera.v2")) {
            return h("yunyi.camera.v1") + h(scanResult);
        } else if (a2.equalsIgnoreCase("zhimi.airpurifier.v1")) {
            return h("zhimi.airpurifier.v1") + h(scanResult);
        } else if (a2.equalsIgnoreCase("zhimi.airpurifier.v2")) {
            return h("zhimi.airpurifier.v2") + h(scanResult);
        } else if (a2.equalsIgnoreCase("zhimi.airpurifier.v3")) {
            return h("zhimi.airpurifier.v3") + h(scanResult);
        } else if (a2.equalsIgnoreCase("lumi.gateway.v1")) {
            return h("lumi.gateway.v1") + h(scanResult);
        } else if (a2.equalsIgnoreCase("lumi.gateway.v2")) {
            return h("lumi.gateway.v2") + h(scanResult);
        } else if (a2.equalsIgnoreCase("midea.aircondition.v1")) {
            return h("midea.aircondition.v1") + h(scanResult);
        } else if (!a2.equalsIgnoreCase("other")) {
            return scanResult.SSID;
        } else {
            return h("other") + h(scanResult);
        }
    }

    public static String h(String str) {
        if (str.equalsIgnoreCase("xiaomi.ir.v1")) {
            return CommonApplication.getAppContext().getString(R.string.miio_ir_name);
        }
        if (str.equalsIgnoreCase("chuangmi.plug.v1")) {
            return CommonApplication.getAppContext().getString(R.string.intelligent_plug);
        }
        if (str.equalsIgnoreCase("chuangmi.plug.v2")) {
            return CommonApplication.getAppContext().getString(R.string.intelligent_plug_simplified);
        }
        if (str.equalsIgnoreCase("zhimi.airpurifier.v1")) {
            return CommonApplication.getAppContext().getString(R.string.air_purifier);
        }
        if (str.equalsIgnoreCase("lumi.gateway.v1")) {
            return CommonApplication.getAppContext().getString(R.string.gateway_main_title);
        }
        if (str.equalsIgnoreCase("lumi.gateway.v2")) {
            return CommonApplication.getAppContext().getString(R.string.gateway_main_title);
        }
        if (str.equalsIgnoreCase("lumi.sensor_motion.v1")) {
            return CommonApplication.getAppContext().getString(R.string.gateway_motion_name);
        }
        if (str.equalsIgnoreCase("lumi.sensor_switch.v1")) {
            return CommonApplication.getAppContext().getString(R.string.gateway_switch_name);
        }
        if (str.equalsIgnoreCase("lumi.sensor_magnet.v1")) {
            return CommonApplication.getAppContext().getString(R.string.gateway_magnet_name);
        }
        if (str.equalsIgnoreCase("lumi.sensor_motion.v2")) {
            return CommonApplication.getAppContext().getString(R.string.gateway_motion_name);
        }
        if (str.equalsIgnoreCase("lumi.sensor_switch.v2")) {
            return CommonApplication.getAppContext().getString(R.string.gateway_switch_name);
        }
        if (str.equalsIgnoreCase("lumi.sensor_magnet.v2")) {
            return CommonApplication.getAppContext().getString(R.string.gateway_magnet_name);
        }
        if (str.equalsIgnoreCase("other")) {
            return CommonApplication.getAppContext().getString(R.string.other_device);
        }
        if (str.equalsIgnoreCase("xiaomi.myphone.v1")) {
            return CommonApplication.getAppContext().getString(R.string.my_phone_name);
        }
        if (str.equalsIgnoreCase("xiaomi.phone_ir.t1")) {
            return CommonApplication.getAppContext().getString(R.string.my_phone_ir_name);
        }
        if (str.equalsIgnoreCase("xiaomi.phone_ir.v1")) {
            return CommonApplication.getAppContext().getString(R.string.my_phone_ir_name);
        }
        if (str.equalsIgnoreCase("midea.aircondition.v1")) {
            return CommonApplication.getAppContext().getString(R.string.midea_aircondition);
        }
        if (str.equalsIgnoreCase("xiaomi.ble.v1")) {
            return CommonApplication.getAppContext().getString(R.string.mi_band);
        }
        if (str.equalsIgnoreCase("xiaomi.mikey.v1")) {
            return CommonApplication.getAppContext().getString(R.string.mi_key);
        }
        if (str.equalsIgnoreCase("other")) {
            return CommonApplication.getAppContext().getString(R.string.app_name2);
        }
        return CoreApi.a().c(str) ? CoreApi.a().d(str).p() : "";
    }

    public static String i(String str) {
        int indexOf = str.indexOf("_miio");
        if (indexOf > 1) {
            return str.substring(0, indexOf).replace('-', '.');
        }
        int indexOf2 = str.indexOf("_miap");
        if (indexOf2 > 1) {
            return str.substring(0, indexOf2).replace('-', '.');
        }
        int indexOf3 = str.indexOf("_mibt");
        if (indexOf3 > 1) {
            return str.substring(0, indexOf3).replace('-', '.');
        }
        int indexOf4 = str.indexOf("_midev");
        if (indexOf4 > 1) {
            return str.substring(0, indexOf4).replace('-', '.');
        }
        return (str.contains("midea_AC") || str.contains("midea_ac")) ? "midea.aircondition.v1" : str;
    }

    public static Device a(JSONObject jSONObject) {
        Device device = null;
        if (jSONObject == null) {
            return null;
        }
        String optString = jSONObject.optString("model");
        if (TextUtils.isEmpty(optString)) {
            return null;
        }
        Class<?> e2 = e(j(optString));
        if (e2 != null) {
            try {
                Device device2 = (Device) e2.newInstance();
                try {
                    device2.model = optString;
                    device2.isOnline = true;
                    device2.did = optString;
                    device2.desc = jSONObject.optString("url");
                    device2.pid = MiioVirtualDeviceSearch.f14901a;
                    a(device2);
                    return device2;
                } catch (IllegalAccessException | InstantiationException unused) {
                    device = device2;
                }
            } catch (IllegalAccessException | InstantiationException unused2) {
            }
        }
        if (!CoreApi.a().c(optString)) {
            return device;
        }
        MiioDeviceV2 miioDeviceV2 = new MiioDeviceV2();
        miioDeviceV2.model = optString;
        miioDeviceV2.did = optString;
        miioDeviceV2.desc = jSONObject.optString("url");
        miioDeviceV2.pid = MiioVirtualDeviceSearch.f14901a;
        a((Device) miioDeviceV2);
        return miioDeviceV2;
    }

    public static Device k(String str) {
        Class<?> e2 = e(j(str));
        Device device = null;
        if (e2 != null) {
            try {
                Device device2 = (Device) e2.newInstance();
                try {
                    device2.model = str;
                    device2.isOnline = true;
                    a(device2);
                    return device2;
                } catch (IllegalAccessException | InstantiationException unused) {
                    device = device2;
                }
            } catch (IllegalAccessException | InstantiationException unused2) {
            }
        }
        if (!CoreApi.a().c(str)) {
            return device;
        }
        MiioDeviceV2 miioDeviceV2 = new MiioDeviceV2();
        miioDeviceV2.model = str;
        a((Device) miioDeviceV2);
        return miioDeviceV2;
    }

    public static Device d(String str, String str2) {
        Device device;
        String i2 = i(str);
        if (CoreApi.a().c(i2)) {
            MiioDeviceV2 miioDeviceV2 = new MiioDeviceV2();
            miioDeviceV2.model = i2;
            device = miioDeviceV2;
        } else {
            Class<?> e2 = e(i2);
            if (e2 != null) {
                try {
                    device = (Device) e2.newInstance();
                } catch (IllegalAccessException | InstantiationException unused) {
                }
            }
            device = null;
        }
        if (device == null) {
            return null;
        }
        device.did = g(str);
        int indexOf = str.indexOf("_miio");
        if (indexOf > 0) {
            device.model = str.substring(0, indexOf).replace('-', '.');
        }
        device.name = null;
        device.pid = 0;
        if (device instanceof CameraDevice) {
            device.pid = 1;
        }
        device.isOnline = true;
        device.setUnbind();
        device.resetFlag = 1;
        device.token = "";
        device.ip = str2;
        device.location = Device.Location.LOCAL;
        device.propInfo = null;
        device.ownerName = null;
        a(device);
        return device;
    }

    public static Device b(JSONObject jSONObject) {
        String optString = jSONObject.optString("did");
        Device b2 = SmartHomeDeviceManager.a().b(optString);
        if (b2 == null) {
            b2 = SmartHomeDeviceManager.a().l(optString);
        }
        if (b2 == null) {
            Class<?> e2 = e(j(jSONObject.optString("model")));
            if (e2 != null) {
                try {
                    b2 = (Device) e2.newInstance();
                } catch (IllegalAccessException | InstantiationException unused) {
                }
            } else if (CoreApi.a().c(j(jSONObject.optString("model")))) {
                b2 = new MiioDeviceV2();
                b2.model = j(jSONObject.optString("model"));
            }
        }
        if (b2 == null) {
            return null;
        }
        b2.did = jSONObject.optString("did");
        b2.model = jSONObject.optString("model");
        b2.name = jSONObject.optString("name");
        b2.pid = jSONObject.optInt("pid");
        b2.permitLevel = jSONObject.optInt("permitLevel");
        b2.resetFlag = jSONObject.optInt("resetFlag");
        b2.rssi = jSONObject.optInt("rssi", 0);
        if (!b2.isBinded()) {
            b2.token = "";
        } else {
            b2.token = jSONObject.optString("token");
        }
        b2.ip = jSONObject.optString("localip");
        b2.latitude = jSONObject.optDouble("latitude");
        b2.longitude = jSONObject.optDouble("longitude");
        b2.ssid = jSONObject.optString(DeviceTagInterface.e);
        b2.bssid = jSONObject.optString("bssid");
        b2.showMode = jSONObject.optInt("show_mode");
        b2.propInfo = jSONObject.optJSONObject(XmBluetoothRecord.TYPE_PROP);
        if (!jSONObject.isNull("method")) {
            b2.method = jSONObject.optJSONArray("method");
        }
        b2.mac = jSONObject.optString("mac");
        b2.parentModel = jSONObject.optString("parent_model");
        b2.parentId = jSONObject.optString("parent_id");
        b2.isOnline = jSONObject.optBoolean("isOnline");
        b2.desc = jSONObject.optString("desc");
        b2.specUrn = jSONObject.optString("spec_type");
        b2.voiceCtrl = (byte) jSONObject.optInt("voice_ctrl");
        if (b2.pid == Device.PID_VIRTUAL_DEVICE) {
            b2.canUseNotBind = true;
            b2.isOnline = true;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject("owner");
        if (optJSONObject != null) {
            b2.ownerName = optJSONObject.optString(FamilyMemberData.d);
            b2.ownerId = optJSONObject.optString("userid");
            if (TextUtils.isEmpty(b2.ownerName)) {
                b2.ownerName = b2.ownerId;
            }
        }
        b2.parseProp();
        b2.initialLocal();
        a(b2);
        if (!jSONObject.isNull("extra")) {
            b2.parseExtra(jSONObject.optString("extra"));
            b2.extra = jSONObject.optString("extra");
        }
        if (!jSONObject.isNull("event")) {
            b2.event = jSONObject.optString("event");
            b2.parseEvent(b2.event);
        }
        if (b2.extra != null && (b2 instanceof MiioDeviceV2)) {
            try {
                JSONObject jSONObject2 = new JSONObject(b2.extra);
                if (jSONObject2.has("fw_version")) {
                    ((MiioDeviceV2) b2).D = jSONObject2.getString("fw_version");
                }
                if (jSONObject2.has("mcu_version")) {
                    ((MiioDeviceV2) b2).C = jSONObject2.getString("mcu_version");
                }
            } catch (JSONException unused2) {
            }
        }
        return b2;
    }

    public static Device a(com.xiaomi.smarthome.core.entity.device.Device device) {
        Device device2;
        if (device == null) {
            return null;
        }
        if (device instanceof VirtualDevice) {
            return b(device);
        }
        Device b2 = SmartHomeDeviceManager.a().b(device.k());
        if (b2 == null) {
            b2 = SmartHomeDeviceManager.a().l(device.k());
        }
        if (device2 == null) {
            Class<?> e2 = e(j(device.l()));
            if (e2 != null) {
                try {
                    device2 = (Device) e2.newInstance();
                } catch (IllegalAccessException | InstantiationException unused) {
                }
            } else if (CoreApi.a().c(j(device.l()))) {
                if (device instanceof WatchBandDevice) {
                    device2 = new WatchBandDevice();
                } else if (device.f() == Device.PID_BLUETOOTH) {
                    device2 = BleDevice.a(device.n());
                } else if (device.f() == Device.PID_BLE_MESH) {
                    device2 = new BleMeshDevice();
                } else {
                    device2 = new MiioDeviceV2();
                }
                device2.model = j(device.l());
            }
        }
        if (device2 == null) {
            return null;
        }
        a(device2, device);
        return device2;
    }

    public static void a(Device device, com.xiaomi.smarthome.core.entity.device.Device device2) {
        device.did = device2.k();
        device.model = device2.l();
        device.name = device2.m();
        device.pid = device2.f();
        device.permitLevel = device2.p();
        device.resetFlag = device2.q();
        device.rssi = device2.r();
        if (!device.isBinded()) {
            device.token = "";
        } else {
            device.token = device2.s();
        }
        device.ip = device2.t();
        device.latitude = device2.v();
        device.longitude = device2.u();
        device.ssid = device2.w();
        device.bssid = device2.x();
        device.showMode = device2.y();
        device.isSetPinCode = device2.M();
        device.desc = device2.z();
        device.specUrn = device2.P();
        device.voiceCtrl = device2.Q();
        if (device2.I() == Location.LOCAL) {
            device.location = Device.Location.LOCAL;
        } else {
            device.location = Device.Location.REMOTE;
        }
        if (TextUtils.isEmpty(device2.E())) {
            device.propInfo = null;
        } else {
            try {
                device.propInfo = new JSONObject(device2.E());
            } catch (JSONException unused) {
            }
        }
        if (!TextUtils.isEmpty(device2.F())) {
            try {
                device.method = new JSONArray(device2.F());
            } catch (JSONException unused2) {
            }
        }
        device.mac = device2.n();
        device.parentModel = device2.B();
        device.parentId = device2.A();
        device.isOnline = device2.o();
        if (device2.f() == Device.PID_BLUETOOTH) {
            device.isOnline = true;
        }
        if (device.pid == Device.PID_VIRTUAL_DEVICE) {
            device.canUseNotBind = true;
            device.isOnline = true;
        } else {
            device.desc = device2.z();
        }
        device.ownerName = device2.C();
        device.ownerId = device2.D();
        if (TextUtils.isEmpty(device.ownerName)) {
            device.ownerName = device.ownerId;
        }
        device.parseProp();
        a(device);
        if (!TextUtils.isEmpty(device2.G())) {
            device.parseExtra(device2.G());
            device.extra = device2.G();
        }
        if (!TextUtils.isEmpty(device2.H())) {
            device.event = device2.H();
            device.parseEvent(device.event);
        }
        device.descNew = device2.O();
        device.descTimeJString = device2.N();
        if (device.extra != null && (device instanceof MiioDeviceV2)) {
            try {
                JSONObject jSONObject = new JSONObject(device.extra);
                if (jSONObject.has("fw_version")) {
                    ((MiioDeviceV2) device).D = jSONObject.getString("fw_version");
                }
                if (jSONObject.has("mcu_version")) {
                    ((MiioDeviceV2) device).C = jSONObject.getString("mcu_version");
                }
            } catch (JSONException unused3) {
            }
        }
        device.version = device2.J();
    }

    public static Device b(com.xiaomi.smarthome.core.entity.device.Device device) {
        if (device == null || TextUtils.isEmpty(device.l()) || !CoreApi.a().c(device.l())) {
            return null;
        }
        MiioVirtualDevice miioVirtualDevice = new MiioVirtualDevice();
        miioVirtualDevice.setOwner(true);
        miioVirtualDevice.model = device.l();
        miioVirtualDevice.did = device.l();
        miioVirtualDevice.desc = ((VirtualDevice) device).b();
        miioVirtualDevice.pid = MiioVirtualDeviceSearch.f14901a;
        a((Device) miioVirtualDevice);
        return miioVirtualDevice;
    }

    public static boolean e(String str, String str2) {
        return str != null && str.equalsIgnoreCase(str2);
    }

    public static boolean a(String str, String[] strArr) {
        String l2 = l(str);
        if (l2 == null) {
            return false;
        }
        for (String l3 : strArr) {
            String l4 = l(l3);
            if (!TextUtils.isEmpty(l4) && l4.equalsIgnoreCase(l2)) {
                return true;
            }
        }
        return false;
    }

    public static String l(String str) {
        Pattern compile = Pattern.compile("[^0-9]+");
        String[] split = str.split("\\.");
        if (split.length != 3) {
            return null;
        }
        Matcher matcher = compile.matcher(split[2]);
        String str2 = "";
        if (matcher.find()) {
            str2 = matcher.group();
        }
        return split[0] + split[1] + str2;
    }

    public static String a(Device device, boolean z2) {
        if (device == null) {
            return null;
        }
        if (device instanceof MiTVDevice) {
            String m2 = m(((MiTVDevice) device).a());
            return TextUtils.isEmpty(m2) ? m(device.model) : m2;
        } else if (z2) {
            return a(device.model, device.isOnline, device.isOpen());
        } else {
            return m(device.model);
        }
    }

    public static String m(String str) {
        PluginRecord d2;
        if (str == null || !CoreApi.a().c(str) || (d2 = CoreApi.a().d(str)) == null) {
            return null;
        }
        return d2.t();
    }

    public static String a(String str, boolean z2, boolean z3) {
        if (str == null || !CoreApi.a().c(str)) {
            return null;
        }
        PluginRecord d2 = CoreApi.a().d(str);
        if (!z2) {
            return d2.s();
        }
        if (z3) {
            return d2.q();
        }
        return d2.r();
    }

    public static String n(String str) {
        PluginRecord d2;
        if (str == null || !CoreApi.a().c(str) || (d2 = CoreApi.a().d(str)) == null || d2.c() == null) {
            return null;
        }
        return d2.c().p();
    }

    public static void a(Device device) {
        PluginRecord d2 = CoreApi.a().d(device.model);
        if (d2 != null) {
            if (TextUtils.isEmpty(device.name)) {
                device.name = d2.p();
            }
        } else if (e(device.model, "xiaomi.ir.v1")) {
            if (TextUtils.isEmpty(device.name)) {
                device.name = h("xiaomi.ir.v1");
            }
        } else if (e(device.model, "chuangmi.plug.v1")) {
            if (TextUtils.isEmpty(device.name)) {
                device.name = h("chuangmi.plug.v1");
            }
        } else if (e(device.model, "chuangmi.plug.v2")) {
            if (TextUtils.isEmpty(device.name)) {
                device.name = h("chuangmi.plug.v2");
            }
        } else if (e(device.model, "zhimi.airpurifier.v1")) {
            if (TextUtils.isEmpty(device.name)) {
                device.name = h("zhimi.airpurifier.v1");
            }
        } else if (e(device.model, "zhimi.airpurifier.v2")) {
            if (TextUtils.isEmpty(device.name)) {
                device.name = h("zhimi.airpurifier.v2");
            }
        } else if (e(device.model, "zhimi.airpurifier.v3")) {
            if (TextUtils.isEmpty(device.name)) {
                device.name = h("zhimi.airpurifier.v3");
            }
        } else if (e(device.model, "lumi.gateway.v1")) {
            if (TextUtils.isEmpty(device.name)) {
                device.name = h("lumi.gateway.v1");
            }
        } else if (e(device.model, "lumi.gateway.v2")) {
            if (TextUtils.isEmpty(device.name)) {
                device.name = h("lumi.gateway.v2");
            }
        } else if (e(device.model, "lumi.sensor_motion.v1")) {
            if (TextUtils.isEmpty(device.name)) {
                device.name = h("lumi.sensor_motion.v1");
            }
        } else if (e(device.model, "lumi.sensor_switch.v1")) {
            if (TextUtils.isEmpty(device.name)) {
                device.name = h("lumi.sensor_switch.v1");
            }
        } else if (e(device.model, "lumi.sensor_magnet.v1")) {
            if (TextUtils.isEmpty(device.name)) {
                device.name = h("lumi.sensor_magnet.v1");
            }
        } else if (e(device.model, "midea.aircondition.v1")) {
            if (TextUtils.isEmpty(device.name)) {
                device.name = h("midea.aircondition.v1");
            }
        } else if (e(device.model, "chuangmi.ir.v2")) {
            if (TextUtils.isEmpty(device.name)) {
                device.name = h("chuangmi.ir.v2");
            }
        } else if (e(device.model, "xiaomi.phone_ir.t1")) {
            if (TextUtils.isEmpty(device.name)) {
                device.name = h("xiaomi.phone_ir.t1");
            }
        } else if (e(device.model, "xiaomi.phone_ir.v1") && TextUtils.isEmpty(device.name)) {
            device.name = h("xiaomi.phone_ir.v1");
        }
    }

    public static boolean f(String str, String str2) {
        String[] split = str.split("\\.");
        String[] split2 = str2.split("\\.");
        if (split.length != 3 || split2.length != 3) {
            return false;
        }
        Pattern compile = Pattern.compile("[^0-9]+");
        Matcher matcher = compile.matcher(split[2]);
        Matcher matcher2 = compile.matcher(split2[2]);
        String str3 = "";
        String str4 = "";
        if (matcher.find()) {
            str3 = matcher.group();
        }
        if (matcher2.find()) {
            str4 = matcher2.group();
        }
        if ((split[0] + split[1] + str3).equalsIgnoreCase(split2[0] + split2[1] + str4)) {
            return true;
        }
        return false;
    }

    public static int o(String str) {
        if (str == null) {
            return 0;
        }
        if (str.equalsIgnoreCase("yunyi.camera.v1") || str.equalsIgnoreCase("yunyi.camera.v2")) {
            return R.drawable.launcher_camera;
        }
        if (str.equalsIgnoreCase("chuangmi.plug.v1")) {
            return R.drawable.launcher_plug;
        }
        if (str.equalsIgnoreCase("chuangmi.plug.v2")) {
            return R.drawable.launcher_plug;
        }
        if (str.equalsIgnoreCase("yunmi.waterpurifier.v2")) {
            return R.drawable.launcher_waterpurifier;
        }
        if (str.equalsIgnoreCase("zhimi.airpurifier.v1")) {
            return R.drawable.launcher_airpurifier;
        }
        if (str.equalsIgnoreCase("zhimi.airpurifier.v2")) {
            return R.drawable.launcher_airpurifier;
        }
        if (str.equalsIgnoreCase("zhimi.airpurifier.v3")) {
            return R.drawable.launcher_airpurifier;
        }
        return str.equalsIgnoreCase("other") ? 0 : 0;
    }

    public static Device g(String str, String str2) {
        Device b2 = SmartHomeDeviceManager.a().b(str);
        if (b2 != null) {
            return b2;
        }
        Class<?> e2 = e(j(str2));
        if (e2 != null) {
            try {
                Device device = (Device) e2.newInstance();
                try {
                    device.model = j(str2);
                } catch (Exception | IllegalAccessException | InstantiationException unused) {
                }
                return device;
            } catch (Exception | IllegalAccessException | InstantiationException unused2) {
                return b2;
            }
        } else if (!CoreApi.a().c(j(str2))) {
            return b2;
        } else {
            MiioDeviceV2 miioDeviceV2 = new MiioDeviceV2();
            miioDeviceV2.model = j(str2);
            a((Device) miioDeviceV2);
            return miioDeviceV2;
        }
    }

    public static Device h(String str, String str2) {
        Class<?> e2 = e(j(str2));
        if (e2 != null) {
            try {
                return (Device) e2.newInstance();
            } catch (IllegalAccessException | InstantiationException unused) {
            }
        } else {
            if (CoreApi.a().c(j(str2))) {
                MiioDeviceV2 miioDeviceV2 = new MiioDeviceV2();
                miioDeviceV2.model = j(str2);
                return miioDeviceV2;
            }
            return null;
        }
    }

    public static void a(String str, SimpleDraweeView simpleDraweeView, int i2) {
        if (i2 == 0) {
            i2 = R.drawable.device_list_phone_no;
        }
        final String m2 = m(str);
        if (simpleDraweeView.getHierarchy() == null) {
            simpleDraweeView.setHierarchy(new GenericDraweeHierarchyBuilder(simpleDraweeView.getResources()).setFadeDuration(200).setPlaceholderImage(simpleDraweeView.getResources().getDrawable(i2)).setActualImageScaleType(ScalingUtils.ScaleType.FIT_START).setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_START).build());
        }
        if (TextUtils.isEmpty(m2) || !m2.startsWith("http")) {
            int a2 = ClientIconMap.a(str);
            if (a2 != 0) {
                simpleDraweeView.setImageURI(SystemUtils.a(a2));
            } else {
                simpleDraweeView.setImageURI(SystemUtils.a(i2));
            }
        } else {
            simpleDraweeView.setImageURI(Uri.parse(m2));
            DraweeController controller = simpleDraweeView.getController();
            if (controller != null && (controller instanceof AbstractDraweeController)) {
                ((AbstractDraweeController) controller).addControllerListener(new BaseControllerListener() {
                    public void onFailure(String str, Throwable th) {
                        Log.e("Failure", str + " decode failed");
                        Fresco.getImagePipeline().evictFromMemoryCache(Uri.parse(m2));
                        Fresco.getImagePipeline().evictFromDiskCache(Uri.parse(m2));
                    }
                });
            }
        }
    }

    public static void a(final String str, SimpleDraweeView simpleDraweeView) {
        int i2 = R.drawable.device_list_phone_no;
        if (simpleDraweeView.getHierarchy() == null) {
            simpleDraweeView.setHierarchy(new GenericDraweeHierarchyBuilder(simpleDraweeView.getResources()).setFadeDuration(0).setPlaceholderImage(simpleDraweeView.getResources().getDrawable(i2)).setActualImageScaleType(ScalingUtils.ScaleType.FIT_START).setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_START).build());
        }
        if (TextUtils.isEmpty(str) || !str.startsWith("http")) {
            simpleDraweeView.setImageURI(SystemUtils.a(i2));
            return;
        }
        simpleDraweeView.setImageURI(Uri.parse(str));
        DraweeController controller = simpleDraweeView.getController();
        if (controller != null && (controller instanceof AbstractDraweeController)) {
            ((AbstractDraweeController) controller).addControllerListener(new BaseControllerListener() {
                public void onFailure(String str, Throwable th) {
                    Log.e("Failure", str + " decode failed");
                    Fresco.getImagePipeline().evictFromMemoryCache(Uri.parse(str));
                    Fresco.getImagePipeline().evictFromDiskCache(Uri.parse(str));
                }
            });
        }
    }

    public static void a(SimpleDraweeView simpleDraweeView, final String str, int i2) {
        if (i2 == 0) {
            i2 = R.drawable.device_list_phone_no;
        }
        if (simpleDraweeView.getHierarchy() == null) {
            simpleDraweeView.setHierarchy(new GenericDraweeHierarchyBuilder(simpleDraweeView.getResources()).setFadeDuration(200).setPlaceholderImage(simpleDraweeView.getResources().getDrawable(i2)).setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER).setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_CENTER).build());
        }
        if (TextUtils.isEmpty(str) || !str.startsWith("http")) {
            simpleDraweeView.setBackgroundResource(i2);
            return;
        }
        simpleDraweeView.setImageURI(Uri.parse(str));
        DraweeController controller = simpleDraweeView.getController();
        if (controller != null && (controller instanceof AbstractDraweeController)) {
            ((AbstractDraweeController) controller).addControllerListener(new BaseControllerListener() {
                public void onFailure(String str, Throwable th) {
                    Log.e("Failure", str + " decode failed");
                    Fresco.getImagePipeline().evictFromMemoryCache(Uri.parse(str));
                    Fresco.getImagePipeline().evictFromDiskCache(Uri.parse(str));
                }
            });
        }
    }

    public static void a(Device device, SimpleDraweeView simpleDraweeView, int i2, BasePostprocessor basePostprocessor, boolean z2) {
        final Uri uri;
        if (i2 == 0) {
            i2 = R.drawable.device_list_phone_no;
        }
        String a2 = a(device, z2);
        if (simpleDraweeView.getHierarchy() == null) {
            simpleDraweeView.setHierarchy(new GenericDraweeHierarchyBuilder(simpleDraweeView.getResources()).setFadeDuration(200).setPlaceholderImage(simpleDraweeView.getResources().getDrawable(i2)).setActualImageScaleType(ScalingUtils.ScaleType.FIT_START).setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_START).build());
        }
        if (TextUtils.isEmpty(a2) || !a2.startsWith("http")) {
            int a3 = ClientIconMap.a(device.model);
            if (a3 != 0) {
                uri = SystemUtils.a(a3);
            } else {
                uri = SystemUtils.a(i2);
            }
        } else {
            uri = Uri.parse(a2);
        }
        if (basePostprocessor == null) {
            simpleDraweeView.setImageURI(uri);
            DraweeController controller = simpleDraweeView.getController();
            if (controller != null && (controller instanceof AbstractDraweeController)) {
                ((AbstractDraweeController) controller).addControllerListener(new BaseControllerListener() {
                    public void onFailure(String str, Throwable th) {
                        Log.e("Failure", str + " decode failed");
                        Fresco.getImagePipeline().evictFromMemoryCache(uri);
                        Fresco.getImagePipeline().evictFromDiskCache(uri);
                    }
                });
                return;
            }
            return;
        }
        simpleDraweeView.setController((PipelineDraweeController) ((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setImageRequest(ImageRequestBuilder.newBuilderWithSource(uri).setPostprocessor(basePostprocessor).build())).setOldController(simpleDraweeView.getController())).setControllerListener(new BaseControllerListener() {
            public void onFailure(String str, Throwable th) {
                Log.e("Failure", str + " decode failed");
                Fresco.getImagePipeline().evictFromMemoryCache(uri);
                Fresco.getImagePipeline().evictFromDiskCache(uri);
            }
        })).build());
    }

    public static void a(Context context, Device device, final Target target, int i2) {
        String a2 = a(device, true);
        if (TextUtils.isEmpty(a2) || !a2.startsWith("http")) {
            target.onBitmapLoaded(BitmapFactory.decodeResource(context.getResources(), ClientIconMap.a(device.model)), (Picasso.LoadedFrom) null);
            return;
        }
        Fresco.getImagePipeline().fetchDecodedImage(ImageRequestBuilder.newBuilderWithSource(Uri.parse(a2)).setProgressiveRenderingEnabled(true).build(), CommonApplication.getAppContext()).subscribe(new BaseBitmapDataSubscriber() {
            public void onFailureImpl(DataSource dataSource) {
            }

            public void onNewResultImpl(@Nullable final Bitmap bitmap) {
                if (bitmap != null) {
                    new Handler().post(new Runnable() {
                        public void run() {
                            target.onBitmapLoaded(bitmap, (Picasso.LoadedFrom) null);
                        }
                    });
                }
            }
        }, CallerThreadExecutor.getInstance());
    }

    public static void a(Device device, SimpleDraweeView simpleDraweeView, String str) {
        Uri uri;
        if (!TextUtils.isEmpty(str)) {
            int i2 = R.drawable.geek_icon_default;
            if (simpleDraweeView.getHierarchy() == null) {
                simpleDraweeView.setHierarchy(new GenericDraweeHierarchyBuilder(simpleDraweeView.getResources()).setFadeDuration(200).setPlaceholderImage(simpleDraweeView.getResources().getDrawable(i2)).setFailureImage(simpleDraweeView.getResources().getDrawable(i2)).setActualImageScaleType(ScalingUtils.ScaleType.FIT_START).setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_START).build());
            }
            if (TextUtils.isEmpty(str) || !str.startsWith("http")) {
                int a2 = ClientIconMap.a(device.model);
                if (a2 != 0) {
                    uri = SystemUtils.a(a2);
                } else {
                    uri = SystemUtils.a(i2);
                }
            } else {
                uri = Uri.parse(str);
            }
            simpleDraweeView.setImageURI(uri);
        }
    }

    public static void a(Device device, SimpleDraweeView simpleDraweeView) {
        a(device, simpleDraweeView, 0, (BasePostprocessor) null, true);
    }

    public static void b(String str, SimpleDraweeView simpleDraweeView) {
        a(str, simpleDraweeView, 0);
    }

    public static String p(String str) {
        String m2 = m(str);
        if (!TextUtils.isEmpty(m2)) {
            return (m2.startsWith(ConnectionHelper.HTTP_PREFIX) || m2.startsWith("https://")) ? m2 : "";
        }
        return "";
    }

    public static void c(String str, SimpleDraweeView simpleDraweeView) {
        String str2 = "";
        PluginRecord d2 = CoreApi.a().d(str);
        if (d2 != null) {
            str2 = d2.q();
        }
        if (simpleDraweeView.getHierarchy() == null) {
            simpleDraweeView.setHierarchy(new GenericDraweeHierarchyBuilder(simpleDraweeView.getResources()).setFadeDuration(200).setPlaceholderImage(simpleDraweeView.getResources().getDrawable(R.drawable.device_list_phone_no)).setActualImageScaleType(ScalingUtils.ScaleType.FIT_START).setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_START).build());
        }
        if (TextUtils.isEmpty(str2) || !str2.startsWith("http")) {
            int a2 = ClientIconMap.a(str);
            if (str.equalsIgnoreCase("xiaomi.ble.v1")) {
                a2 = R.drawable.std_ring_virtual;
            } else if (str.equalsIgnoreCase("yeelink.light.rgb1") || str.equalsIgnoreCase("yeelight.rgb.v1")) {
                a2 = R.drawable.std_yeelight_dangle;
            } else if (str.equalsIgnoreCase("xiaomi.mikey.v1")) {
                a2 = R.drawable.std_icon_mikey_virtual;
            }
            if (a2 != 0) {
                simpleDraweeView.setImageURI(SystemUtils.a(a2));
            } else {
                simpleDraweeView.setImageURI(SystemUtils.a(R.drawable.device_list_phone_no));
            }
        } else {
            simpleDraweeView.setImageURI(Uri.parse(str2));
        }
    }

    public static int q(String str) {
        int a2 = ClientIconMap.a(str);
        if (str.equalsIgnoreCase("xiaomi.ble.v1")) {
            return R.drawable.std_ring_virtual;
        }
        if (str.equalsIgnoreCase("yeelink.light.rgb1") || str.equalsIgnoreCase("yeelight.rgb.v1")) {
            return R.drawable.std_yeelight_dangle;
        }
        if (str.equalsIgnoreCase("xiaomi.mikey.v1")) {
            return R.drawable.std_icon_mikey_virtual;
        }
        return a2;
    }

    public static boolean i(String str, String str2) {
        int lastIndexOf;
        if (str == null || str2 == null || str.isEmpty() || str2.isEmpty() || (lastIndexOf = str.lastIndexOf(".")) <= 0) {
            return false;
        }
        return str2.startsWith(str.substring(0, lastIndexOf));
    }

    public static void a(String str, SimpleDraweeView simpleDraweeView, BasePostprocessor basePostprocessor, int i2) {
        if (Fresco.getDraweeControllerBuilderSupplier() != null) {
            String m2 = m(str);
            if (simpleDraweeView.getHierarchy() == null) {
                simpleDraweeView.setHierarchy(new GenericDraweeHierarchyBuilder(simpleDraweeView.getResources()).setFadeDuration(200).setPlaceholderImage(simpleDraweeView.getResources().getDrawable(R.drawable.user_default)).setActualImageScaleType(ScalingUtils.ScaleType.CENTER_INSIDE).setPlaceholderImageScaleType(ScalingUtils.ScaleType.CENTER_INSIDE).build());
            }
            if (TextUtils.isEmpty(m2) || m2.equals("null")) {
                int a2 = ClientIconMap.a(str);
                if (a2 != 0) {
                    simpleDraweeView.setImageURI(SystemUtils.a(a2));
                } else {
                    simpleDraweeView.setImageURI(SystemUtils.a(i2));
                }
            } else if (basePostprocessor == null) {
                simpleDraweeView.setImageURI(Uri.parse(m2));
            } else {
                simpleDraweeView.setController((PipelineDraweeController) ((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setImageRequest(ImageRequestBuilder.newBuilderWithSource(Uri.parse(m2)).setPostprocessor(basePostprocessor).build())).setOldController(simpleDraweeView.getController())).build());
            }
        }
    }

    public static boolean r(String str) {
        return !TextUtils.isEmpty(str) && str.startsWith("xiaoxun.watch");
    }
}
