package com.xiaomi.smarthome.lite;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.adobe.xmp.XMPConst;
import com.taobao.weex.bridge.WXBridgeManager;
import com.xiaomi.smarthome.StatCommon;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.audiorecord.ToastUtil;
import com.xiaomi.smarthome.core.entity.statistic.StatType;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.MiTVDevice;
import com.xiaomi.smarthome.device.MiioDeviceV2;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthomedevice.R;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LiteDevice extends LiteDeviceAbstract {
    public static final int f = 4000;
    private static String[] m = {"小蚁智能摄像机", "小米插座基础版", "小米空气净化器", "小米多功能网关", "米家小白智能摄像机", "小米智能插座", "小米智能插线板", "青米智能插排", "小米WiFi放大器", "Yeelight白光灯", "Yeelight彩光灯", "Yeelight 白光灯", "Yeelight 彩光灯", "飞利浦智睿台灯", "小米万能遥控器", "小米网络收音机", "米兔智能故事机", "米家压力HI电饭煲", "米家恒温水壶", "智米直流变频落地扇", "90分智能金属旅行箱", "美的智能空调", "奥克斯智能空调", "金兴智能空调", "小米净水器", "素士声波电动牙刷", "iHealth血压计", "小米温湿度传感器", "小米人体传感器", "小米门窗传感器"};
    private static String[] n = {"小米", "米家", "米兔", "小蚁", "青米", "飞利浦", "Yeelight", "智米", "90分", "美的", "奥克斯", "金兴", "iHealth", "素士"};

    /* renamed from: a  reason: collision with root package name */
    public Device f19353a;
    public String b;
    public LiteComConfig c;
    public boolean d = false;
    public boolean e = false;
    private long l = 0;

    public enum DeviceStat {
        Unknown,
        ValueOperation,
        Value,
        Operation,
        Entrance,
        Offline
    }

    public LiteDevice(String str, Device device, String str2) {
        this.k = a(str, 4);
        this.f19353a = device;
        this.b = str2;
        this.c = i();
        this.d = true;
        if (this.c == null) {
            this.e = false;
        } else if (this.c.f == null || this.c.f.size() <= 0) {
            this.e = false;
        } else {
            this.e = true;
        }
    }

    public long a() {
        return this.l;
    }

    private static String a(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (str.length() <= i) {
            return str.trim();
        }
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i3 < m.length) {
                if (str.startsWith(m[i3])) {
                    while (true) {
                        if (i2 >= n.length) {
                            break;
                        }
                        String str2 = n[i2];
                        if (str.length() > str2.length() && str.startsWith(str2)) {
                            str = str.substring(str2.length());
                            break;
                        }
                        i2++;
                    }
                } else {
                    i3++;
                }
            } else {
                break;
            }
        }
        return str.trim();
    }

    public int hashCode() {
        return this.b.hashCode();
    }

    public int b() {
        return this.d ? 0 : 2;
    }

    public static boolean a(String str) {
        return TextUtils.isEmpty(str) || str.equals("null") || str.equals(XMPConst.ai);
    }

    private LiteOptConfig a(LiteComConfig liteComConfig) {
        Map<String, String> d2 = LiteUIConfigManager.a().d(this.f19353a.did);
        if (d2 == null || liteComConfig == null || liteComConfig.f == null || liteComConfig.f.size() <= 0) {
            return null;
        }
        for (int i = 0; i < liteComConfig.f.size(); i++) {
            LiteOptConfig liteOptConfig = liteComConfig.f.get(i);
            if (!TextUtils.isEmpty(liteOptConfig.f19363a) && !TextUtils.isEmpty(liteOptConfig.b) && d2.containsKey(liteOptConfig.f19363a) && liteOptConfig.b.equals(d2.get(liteOptConfig.f19363a))) {
                return liteOptConfig;
            }
        }
        return null;
    }

    public boolean c() {
        return this.c != null && !this.c.h.equals("0");
    }

    private LiteComConfig i() {
        Device device = this.f19353a;
        if (device == null) {
            return null;
        }
        LiteUIConfig e2 = device instanceof MiTVDevice ? LiteUIConfigManager.a().e(((MiTVDevice) device).a()) : null;
        if (e2 == null || e2.c == null || e2.c.size() <= 0) {
            e2 = LiteUIConfigManager.a().e(device.model);
        }
        if (e2 == null || e2.c == null || e2.c.size() <= 0) {
            return null;
        }
        if (e2.c.size() == 1) {
            return e2.c.get(0);
        }
        String f2 = LiteUIConfigManager.a().f(this.b);
        if (TextUtils.isEmpty(f2)) {
            return null;
        }
        for (int i = 0; i < e2.c.size(); i++) {
            LiteComConfig liteComConfig = e2.c.get(i);
            if (f2.equals(liteComConfig.f19352a)) {
                return liteComConfig;
            }
        }
        return null;
    }

    public String d() {
        LiteComConfig liteComConfig = this.c;
        if (liteComConfig == null) {
            return null;
        }
        if (this.f19353a != null && !this.f19353a.isOnline) {
            return liteComConfig.g;
        }
        if (liteComConfig.f == null || liteComConfig.f.size() <= 0) {
            return liteComConfig.e;
        }
        LiteOptConfig a2 = a(this.c);
        if (a2 == null) {
            return liteComConfig.e;
        }
        return a2.e;
    }

    public String e() {
        Map<String, String> d2;
        if (this.c == null || this.c.c == null || (d2 = LiteUIConfigManager.a().d(this.f19353a.did)) == null) {
            return null;
        }
        if (this.f19353a != null && !TextUtils.isEmpty(this.f19353a.model) && this.f19353a.model.startsWith("lumi.sensor_magnet.")) {
            try {
                return new Date(Long.valueOf(new JSONObject(d2.get("event.open")).optString("timestamp")).longValue() * 1000).compareTo(new Date(Long.valueOf(new JSONObject(d2.get("event.close")).optString("timestamp")).longValue() * 1000)) > 0 ? "Open" : "Close";
            } catch (Exception unused) {
            }
        }
        String str = d2.get(this.c.c);
        if (!this.c.c.startsWith(Device.EVENT_PREFIX)) {
            return str;
        }
        try {
            return new SimpleDateFormat("HH:mm").format(new Date(Long.valueOf(new JSONObject(str).optString("timestamp")).longValue() * 1000));
        } catch (Exception unused2) {
            return "";
        }
    }

    public void f() {
        if (!this.e || this.f19353a == null || !(this.f19353a instanceof MiioDeviceV2)) {
            return;
        }
        if (!LiteUIConfigManager.a().b()) {
            LiteUIConfigManager.a().a((String) null);
            LiteUIConfigManager.a().c();
            return;
        }
        final MiioDeviceV2 miioDeviceV2 = (MiioDeviceV2) this.f19353a;
        final LiteOptConfig a2 = a(this.c);
        if (a2 == null) {
            ToastUtil.a((Context) CommonApplication.getApplication(), CommonApplication.getApplication().getString(R.string.property_is_null_tips), 1);
        } else if (!TextUtils.isEmpty(a2.c)) {
            this.l = System.currentTimeMillis();
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("id", (int) ((Math.random() * 100000.0d) + 100000.0d));
                jSONObject.put("method", a2.c);
                JSONArray jSONArray = new JSONArray();
                if (a2.d != null && a2.d.length > 0) {
                    for (String put : a2.d) {
                        jSONArray.put(put);
                    }
                }
                jSONObject.put("params", jSONArray);
            } catch (JSONException unused) {
            }
            miioDeviceV2.a(jSONObject, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    boolean a2 = LiteUIConfigManager.a().a(LiteDevice.this.f19353a, a2);
                    if (!LiteUIConfigManager.c(LiteDevice.this.f19353a, a2) && !LiteUIConfigManager.c(LiteDevice.this.f19353a, a2) && !a2) {
                        CommonApplication.getGlobalWorkerHandler().postDelayed(new Runnable() {
                            public void run() {
                                LiteUIConfigManager.a().a(miioDeviceV2.did, a2);
                            }
                        }, 1000);
                    }
                }

                public void onFailure(Error error) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("doOperation fail =");
                    Object obj = error;
                    if (error != null) {
                        obj = error.a() + "," + error.b();
                    }
                    sb.append(obj);
                    Log.e("LiteDevice", sb.toString());
                }
            });
            JSONObject jSONObject2 = new JSONObject();
            try {
                if (this.f19353a != null) {
                    jSONObject2.put("model", this.f19353a.model);
                    jSONObject2.put("isOnline", this.f19353a.isOnline);
                }
                if (!(this.c == null || this.c.f19352a == null)) {
                    jSONObject2.put(WXBridgeManager.COMPONENT, this.c.f19352a);
                }
                jSONObject2.put("rpc", jSONObject);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            CoreApi.a().a(StatType.EVENT, StatCommon.f13530a, "DoOperation", jSONObject2.toString(), false);
        }
    }

    public String g() {
        LiteOptConfig a2;
        if (this.e && this.f19353a != null && (this.f19353a instanceof MiioDeviceV2) && this.f19353a.isOnline && this.c != null && (a2 = a(this.c)) != null && !TextUtils.isEmpty(a2.g)) {
            return a2.g;
        }
        return null;
    }

    public DeviceStat h() {
        if (this.f19353a == null) {
            return DeviceStat.Unknown;
        }
        if (!this.f19353a.isOnline) {
            return DeviceStat.Offline;
        }
        if (this.c == null) {
            return DeviceStat.Entrance;
        }
        boolean z = true;
        boolean z2 = !a(e());
        String g = g();
        if (TextUtils.isEmpty(g) || (!g.equals("blue") && !g.equals("gray"))) {
            z = false;
        }
        if (z2 && z) {
            return DeviceStat.ValueOperation;
        }
        if (z2) {
            return DeviceStat.Value;
        }
        if (z) {
            return DeviceStat.Operation;
        }
        return DeviceStat.Entrance;
    }
}
