package com.xiaomi.smarthome.core.server.internal.device;

import android.text.TextUtils;
import com.xiaomi.iot.spec.api.Constants;
import com.xiaomi.smarthome.core.entity.device.Device;
import com.xiaomi.smarthome.core.entity.device.Location;
import com.xiaomi.smarthome.core.entity.device.MiTVDevice;
import com.xiaomi.smarthome.core.entity.device.RouterDevice;
import com.xiaomi.smarthome.core.entity.device.WatchBandDevice;
import com.xiaomi.smarthome.core.server.CoreService;
import com.xiaomi.smarthome.library.common.network.WifiUtil;
import java.net.InetAddress;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import javax.jmdns.ServiceInfo;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceFactory {
    public static final String A = "onemore.soundbox.sm001";
    public static final String B = "aer.mask.smartbug";
    public static final String C = "ninebot.scooter.v1";
    public static final String D = "ninebot.scooter.v2";
    public static final String E = "midea.aircondition.v1";
    public static final String F = "chuangmi.wifi.v1";
    public static final Map<String, Class<?>> G = new HashMap();

    /* renamed from: a  reason: collision with root package name */
    public static final int f14503a = 0;
    public static final int b = 1;
    public static final String c = "xiaomi.tv.v1";
    public static final String d = "xiaomi.tv.b1";
    public static final String e = "xiaomi.tv.i1";
    public static final String f = "xiaomi.tv.h1";
    public static final String g = "xiaomi.tvbox.v1";
    public static final String h = "xiaomi.tvbox.b1";
    public static final String i = "xiaomi.tvbox.i1";
    public static final String j = "xiaomi.split_tv.b1";
    public static final String k = "xiaomi.split_tv.v1";
    public static final String l = "fengmi.projector.fm15";
    public static final String m = "inovel.projector.me2";
    public static final String n = "zimi.projector.v1";
    public static final String o = "fengmi.projector.fm154k";
    public static final String p = "fengmi.projector.fm05";
    public static final String[] q = {"xiaomi.watch.band1", "xiaomi.watch.band1S", "xiaomi.watch.band1A", "xiaomi.watch.band2", "hmpace.scales.mibfs", "hmpace.scales.miscale2", "trios.bleshoes.v1", "hmpace.bracelet.v4", com.xiaomi.smarthome.device.DeviceFactory.aP};
    public static final String r = "yeelink.light.ble1";
    public static final String s = "xiaomi.mikey.v1";
    public static final String t = "roidmi.btfm.v1";
    public static final String u = "roidmi.btfm.m1";
    public static final String v = "ninebot.balscooter.v1";
    public static final String w = "xiaomi.ble.v1";
    public static final String x = "zimi.powerbank.v1";
    public static final String y = "haiii.pettag.v1";
    public static final String z = "runmi.suitcase.v1";

    static {
        G.put("xiaomi.tv.v1", MiTVDevice.class);
        G.put("xiaomi.tv.b1", MiTVDevice.class);
        G.put("xiaomi.tv.i1", MiTVDevice.class);
        G.put("xiaomi.tv.h1", MiTVDevice.class);
        G.put("xiaomi.tvbox.v1", MiTVDevice.class);
        G.put("xiaomi.tvbox.b1", MiTVDevice.class);
        G.put("xiaomi.tvbox.i1", MiTVDevice.class);
        G.put("xiaomi.split_tv.b1", MiTVDevice.class);
        G.put("xiaomi.split_tv.v1", MiTVDevice.class);
        G.put("fengmi.projector.fm15", MiTVDevice.class);
        G.put("inovel.projector.me2", MiTVDevice.class);
        G.put("zimi.projector.v1", MiTVDevice.class);
        G.put("fengmi.projector.fm154k", MiTVDevice.class);
        G.put("fengmi.projector.fm05", MiTVDevice.class);
        for (String put : q) {
            G.put(put, WatchBandDevice.class);
        }
    }

    public static String a(String str) {
        if (str == null) {
            return "";
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.reset();
            instance.update(str.getBytes());
            return a(instance.digest());
        } catch (Exception unused) {
            return "";
        }
    }

    private static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b2 : bArr) {
            String hexString = Integer.toHexString(b2 & 255);
            while (hexString.length() < 2) {
                hexString = "0" + hexString;
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    public static Class<?> b(String str) {
        Class<?> cls = G.get(str);
        return (cls != null || !str.startsWith("xiaomi.router")) ? cls : RouterDevice.class;
    }

    public static Device a(JSONObject jSONObject) {
        Device e2 = e(jSONObject.optString("model"));
        e2.a(jSONObject);
        return e2;
    }

    public static Device a(DeviceRecord deviceRecord) {
        Device e2 = e(deviceRecord.model);
        e2.a(deviceRecord.did);
        e2.b(deviceRecord.model);
        e2.c(deviceRecord.name);
        e2.d(deviceRecord.mac);
        boolean z2 = false;
        e2.a(deviceRecord.isOnline == 1);
        e2.c(deviceRecord.pid);
        e2.d(deviceRecord.permitLevel);
        e2.e(deviceRecord.resetFlag);
        e2.f(deviceRecord.rssi);
        e2.e(deviceRecord.token);
        e2.f(deviceRecord.localIP);
        e2.a(deviceRecord.longitude);
        e2.b(deviceRecord.latitude);
        e2.g(deviceRecord.ssid);
        e2.h(deviceRecord.bssid);
        e2.g(deviceRecord.showMode);
        e2.i(deviceRecord.desc);
        e2.j(deviceRecord.parentId);
        e2.k(deviceRecord.parentModel);
        e2.l(deviceRecord.ownerName);
        e2.m(deviceRecord.ownerId);
        e2.n(deviceRecord.propInfo);
        e2.o(deviceRecord.methodInfo);
        e2.p(deviceRecord.extraInfo);
        e2.q(deviceRecord.eventInfo);
        e2.a(Location.values()[deviceRecord.location]);
        e2.r(deviceRecord.version);
        e2.b(deviceRecord.canUseNotBind == 1);
        if (deviceRecord.canAuth == 1) {
            z2 = true;
        }
        e2.c(z2);
        e2.u(deviceRecord.specUrn);
        e2.a((byte) deviceRecord.voiceCtrl);
        e2.i();
        return e2;
    }

    public static DeviceRecord a(String str, Device device) {
        DeviceRecord deviceRecord = new DeviceRecord();
        deviceRecord.userId = str;
        deviceRecord.did = device.k();
        deviceRecord.model = device.l();
        deviceRecord.name = device.m();
        deviceRecord.mac = device.n();
        deviceRecord.isOnline = device.o() ? 1 : 0;
        deviceRecord.pid = device.f();
        deviceRecord.permitLevel = device.p();
        deviceRecord.resetFlag = device.q();
        deviceRecord.rssi = device.r();
        deviceRecord.token = "";
        deviceRecord.localIP = device.t();
        deviceRecord.longitude = device.u();
        deviceRecord.latitude = device.v();
        deviceRecord.ssid = device.w();
        deviceRecord.bssid = device.x();
        deviceRecord.showMode = device.y();
        deviceRecord.desc = device.z();
        deviceRecord.parentId = device.A();
        deviceRecord.parentModel = device.B();
        deviceRecord.ownerName = device.C();
        deviceRecord.ownerId = device.D();
        deviceRecord.propInfo = device.E();
        deviceRecord.methodInfo = device.F();
        deviceRecord.extraInfo = device.j();
        deviceRecord.eventInfo = device.H();
        deviceRecord.location = device.I().ordinal();
        deviceRecord.version = device.J();
        deviceRecord.canUseNotBind = device.K() ? 1 : 0;
        deviceRecord.canAuth = device.L() ? 1 : 0;
        deviceRecord.descNew = device.O();
        deviceRecord.descTimeJString = device.N();
        deviceRecord.specUrn = device.P();
        deviceRecord.voiceCtrl = device.Q();
        return deviceRecord;
    }

    public static MiTVDevice a(ServiceInfo serviceInfo) {
        int i2;
        MiTVDevice miTVDevice = new MiTVDevice();
        miTVDevice.d(serviceInfo.d("mac"));
        miTVDevice.h(serviceInfo.d("mac"));
        String b2 = WifiUtil.b(CoreService.getAppContext());
        if (!TextUtils.isEmpty(b2)) {
            miTVDevice.h(b2.toUpperCase());
        }
        String c2 = WifiUtil.c(CoreService.getAppContext());
        if (!TextUtils.isEmpty(c2)) {
            miTVDevice.g(c2);
        }
        miTVDevice.c(serviceInfo.d());
        String d2 = serviceInfo.d(Constants.Device.e);
        if (!TextUtils.isEmpty(miTVDevice.n()) && !TextUtils.isEmpty(d2)) {
            miTVDevice.a("mitv." + a(miTVDevice.n()) + ":" + serviceInfo.d(Constants.Device.e));
        }
        InetAddress j2 = serviceInfo.j();
        if (j2 != null) {
            miTVDevice.f(j2.getHostAddress());
        }
        miTVDevice.a(true);
        miTVDevice.e(1);
        miTVDevice.a(Location.LOCAL);
        miTVDevice.c(2);
        miTVDevice.f(true);
        miTVDevice.c(false);
        miTVDevice.e(true);
        miTVDevice.b(true);
        miTVDevice.a(true);
        try {
            i2 = Integer.valueOf(serviceInfo.d("platform_id")).intValue();
        } catch (Exception unused) {
            i2 = 0;
        }
        if (i2 < 600) {
            miTVDevice.b("xiaomi.tvbox.v1");
        } else {
            miTVDevice.b("xiaomi.tv.v1");
        }
        if (i2 > 0) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("platform", i2);
            } catch (JSONException unused2) {
            }
            miTVDevice.p(jSONObject.toString());
        }
        return miTVDevice;
    }

    public static Device a(String str, String str2) {
        String c2 = c(str);
        Device e2 = e(c2);
        e2.a(d(str));
        int indexOf = str.indexOf("_miio");
        if (indexOf > 0) {
            e2.b(str.substring(0, indexOf).replace('-', '.'));
        } else {
            e2.b(c2);
        }
        e2.c(0);
        e2.a(true);
        e2.U();
        e2.e(1);
        e2.f(str2);
        e2.a(Location.LOCAL);
        return e2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0014  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.xiaomi.smarthome.core.entity.device.Device e(java.lang.String r0) {
        /*
            java.lang.Class r0 = b(r0)
            if (r0 == 0) goto L_0x0011
            java.lang.Object r0 = r0.newInstance()     // Catch:{ IllegalAccessException | InstantiationException -> 0x0011, Exception -> 0x000d }
            com.xiaomi.smarthome.core.entity.device.Device r0 = (com.xiaomi.smarthome.core.entity.device.Device) r0     // Catch:{ IllegalAccessException | InstantiationException -> 0x0011, Exception -> 0x000d }
            goto L_0x0012
        L_0x000d:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0011:
            r0 = 0
        L_0x0012:
            if (r0 != 0) goto L_0x0019
            com.xiaomi.smarthome.core.entity.device.MiioDevice r0 = new com.xiaomi.smarthome.core.entity.device.MiioDevice
            r0.<init>()
        L_0x0019:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.internal.device.DeviceFactory.e(java.lang.String):com.xiaomi.smarthome.core.entity.device.Device");
    }

    public static String c(String str) {
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
        return (str.contains("midea_AC") || str.contains("midea_ac")) ? "midea.aircondition.v1" : str;
    }

    public static String d(String str) {
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
        int indexOf4 = str.indexOf("midea_ac_");
        return indexOf4 >= 0 ? str.substring(indexOf4 + 9) : str;
    }
}
