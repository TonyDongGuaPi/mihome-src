package com.xiaomi.smarthome.core.server.internal.plugin.entity;

import android.text.TextUtils;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.framework.page.DeviceMoreActivity;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class DeviceResult {
    private static final String R = "https://cdn.fds.api.xiaomi.com";

    /* renamed from: a  reason: collision with root package name */
    public static final int f14691a = 2;
    public static final int b = 4;
    public int A;
    public int B;
    public int C;
    public int D;
    public String E;
    public int F;
    public int G;
    public int H;
    public List<Integer> I;
    public List<Integer> J;
    public int K;
    public List<String> L;
    public int M;
    public int N;
    public int O;
    public String P;
    public int Q;
    public String c;
    public int d;
    public int e;
    public String f;
    public String g;
    public String h;
    public String i;
    public String j;
    public String k;
    public int l;
    public ModelRegex m;
    public String n;
    public int o;
    public int p;
    public String q;
    public String r;
    public int s;
    public int t;
    public int u;
    public int v;
    public int w;
    public String x;
    public int y;
    public int z;

    public static int a(int i2, int i3, int i4, int i5) {
        int i6 = 1 == i2 ? 1 : 0;
        if (1 == i3) {
            i6 |= 2;
        }
        if (1 == i4) {
            i6 |= 16;
        }
        return 2 == i5 ? i6 | 4 : 4 == i5 ? i6 | 8 : i6;
    }

    public static DeviceResult a(JSONObject jSONObject) {
        DeviceResult deviceResult = new DeviceResult();
        deviceResult.c = jSONObject.optString("model");
        deviceResult.d = jSONObject.optInt("pd_id");
        deviceResult.e = jSONObject.optInt("min_app_version");
        deviceResult.f = jSONObject.optString("name");
        deviceResult.l = jSONObject.optInt("bind_confirm");
        JSONObject optJSONObject = jSONObject.optJSONObject("model_regex");
        if (optJSONObject != null) {
            deviceResult.m = ModelRegex.a(optJSONObject);
        }
        deviceResult.n = jSONObject.optString("desc");
        deviceResult.o = jSONObject.optInt("pid");
        deviceResult.p = jSONObject.optInt("status");
        deviceResult.q = jSONObject.optString("brand_name");
        deviceResult.r = jSONObject.optString("cate_name");
        deviceResult.s = jSONObject.optInt("bt_bind_style");
        deviceResult.t = jSONObject.optInt("ct_offline_enter");
        deviceResult.u = jSONObject.optInt("permission_control");
        deviceResult.v = jSONObject.optInt("wexin_share");
        deviceResult.w = jSONObject.optInt("bt_match");
        deviceResult.x = jSONObject.optString("bt_rssi");
        deviceResult.y = jSONObject.optInt("sc_failed");
        deviceResult.z = jSONObject.optInt(DeviceMoreActivity.MENU_ITEM_NAME_VOICE_CONTROL);
        deviceResult.A = jSONObject.optInt("voice_ctrl_ed");
        deviceResult.B = jSONObject.optInt("op_history");
        deviceResult.G = jSONObject.optInt("sc_type");
        deviceResult.H = jSONObject.optInt("sc_status");
        JSONArray optJSONArray = jSONObject.optJSONArray("sc_type_more");
        deviceResult.I = new ArrayList();
        if (optJSONArray != null) {
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                deviceResult.I.add(Integer.valueOf(optJSONArray.optInt(i2)));
            }
        }
        deviceResult.C = jSONObject.optInt("bt_is_secure");
        deviceResult.D = jSONObject.optInt("bt_gateway");
        deviceResult.g = a(jSONObject, "icon_on", R);
        deviceResult.h = a(jSONObject, "icon_off", R);
        deviceResult.i = a(jSONObject, "icon_offline", R);
        deviceResult.j = a(jSONObject, "icon_bluetooth_pairing", R);
        deviceResult.k = a(jSONObject, "icon_real", R);
        deviceResult.E = a(jSONObject, "icon_336", R);
        JSONArray optJSONArray2 = jSONObject.optJSONArray("sc_type_more_v2");
        deviceResult.J = new ArrayList();
        if (optJSONArray2 != null) {
            for (int i3 = 0; i3 < optJSONArray2.length(); i3++) {
                deviceResult.J.add(Integer.valueOf(optJSONArray2.optInt(i3)));
            }
        }
        deviceResult.K = jSONObject.optInt("fiveG_wifi");
        deviceResult.F = jSONObject.optInt("subcategory_id");
        JSONArray optJSONArray3 = jSONObject.optJSONArray("relations");
        deviceResult.L = new ArrayList();
        if (optJSONArray3 != null) {
            for (int i4 = 0; i4 < optJSONArray3.length(); i4++) {
                deviceResult.L.add(optJSONArray3.optString(i4));
            }
        }
        deviceResult.M = jSONObject.optInt("rank");
        deviceResult.N = jSONObject.optInt("inherit_id");
        deviceResult.O = jSONObject.optInt("mesh_gateway");
        deviceResult.P = jSONObject.optString("member_cnt");
        deviceResult.Q = a(deviceResult.D, deviceResult.O, jSONObject.optInt("infrared_gateway"), jSONObject.optInt("zigbee_gateway"));
        return deviceResult;
    }

    private static String a(JSONObject jSONObject, String str, String str2) {
        String optString = jSONObject.optString(str);
        if (TextUtils.isEmpty(optString) || optString.startsWith("http") || optString.startsWith("https")) {
            return optString;
        }
        return str2 + optString;
    }

    public static class ModelRegex {

        /* renamed from: a  reason: collision with root package name */
        public String f14692a;
        public String b;

        public static ModelRegex a(JSONObject jSONObject) {
            ModelRegex modelRegex = new ModelRegex();
            modelRegex.f14692a = jSONObject.optString(DeviceTagInterface.e);
            modelRegex.b = jSONObject.optString("passwd");
            return modelRegex;
        }
    }
}
