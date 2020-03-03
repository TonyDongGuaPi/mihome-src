package com.xiaomi.smarthome.core.entity.plugin;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.core.server.internal.plugin.entity.DeviceResult;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.framework.page.DeviceMoreActivity;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PluginDeviceInfo implements Parcelable {
    public static final int A = 11;
    public static final int B = 12;
    public static final int C = 13;
    public static final Parcelable.Creator<PluginDeviceInfo> CREATOR = new Parcelable.Creator<PluginDeviceInfo>() {
        /* renamed from: a */
        public PluginDeviceInfo createFromParcel(Parcel parcel) {
            return new PluginDeviceInfo(parcel);
        }

        /* renamed from: a */
        public PluginDeviceInfo[] newArray(int i) {
            return new PluginDeviceInfo[i];
        }
    };
    public static final int D = 14;
    public static final int E = 15;
    public static final int F = 16;
    public static final int H = 0;
    public static final int I = 1;
    public static final int J = 2;

    /* renamed from: a  reason: collision with root package name */
    public static final int f13987a = 1;
    private static final int ao = 16;
    public static final int b = 2;
    public static final int c = 4;
    public static final int d = 8;
    public static final int e = 16;
    public static final int f = 0;
    public static final int g = 1;
    public static final int h = 2;
    public static final int s = 0;
    public static final int t = 1;
    public static final int u = 2;
    public static final int v = 3;
    public static final int w = 4;
    public static final int x = 5;
    public static final int y = 6;
    public static final int z = 7;
    public List<Integer> G;
    public boolean K;
    public List<String> L;
    public int M;
    public int N;
    public int O;
    private String P;
    private int Q;
    private int R;
    private String S;
    private String T;
    private String U;
    private String V;
    private String W;
    private String X;
    private boolean Y;
    private String Z;
    private String aa;
    private int ab;
    private String ac;
    private int ad;
    private String ae;
    private String af;
    private int ag;
    private int ah;
    private int ai;
    private boolean aj;
    private int ak;
    private String al;
    private int am;
    private int an;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public int n;
    public String o;
    public int p;
    public int q;
    public List<Integer> r;

    public int describeContents() {
        return 0;
    }

    public boolean a() {
        return this.p <= 16;
    }

    public PluginDeviceInfo() {
    }

    protected PluginDeviceInfo(Parcel parcel) {
        this.P = parcel.readString();
        this.Q = parcel.readInt();
        this.R = parcel.readInt();
        this.S = parcel.readString();
        this.T = parcel.readString();
        this.U = parcel.readString();
        this.V = parcel.readString();
        this.W = parcel.readString();
        this.X = parcel.readString();
        boolean z2 = false;
        this.Y = parcel.readByte() != 0;
        this.Z = parcel.readString();
        this.aa = parcel.readString();
        this.ab = parcel.readInt();
        this.ac = parcel.readString();
        this.ad = parcel.readInt();
        this.ae = parcel.readString();
        this.af = parcel.readString();
        this.ag = parcel.readInt();
        this.ah = parcel.readInt();
        this.ai = parcel.readInt();
        this.aj = parcel.readByte() != 0;
        this.ak = parcel.readInt();
        this.al = parcel.readString();
        this.am = parcel.readInt();
        this.i = parcel.readInt();
        this.j = parcel.readInt();
        this.k = parcel.readInt();
        this.p = parcel.readInt();
        this.q = parcel.readInt();
        this.r = parcel.readArrayList(ClassLoader.getSystemClassLoader());
        this.l = parcel.readInt();
        this.m = parcel.readInt();
        this.G = parcel.readArrayList(ClassLoader.getSystemClassLoader());
        this.K = parcel.readInt() != 0 ? true : z2;
        this.an = parcel.readInt();
        this.L = parcel.readArrayList(ClassLoader.getSystemClassLoader());
        this.M = parcel.readInt();
        this.N = parcel.readInt();
        this.n = parcel.readInt();
        this.o = parcel.readString();
        this.O = parcel.readInt();
    }

    public static PluginDeviceInfo a(String str, String str2) {
        PluginDeviceInfo pluginDeviceInfo = new PluginDeviceInfo();
        try {
            JSONObject jSONObject = new JSONObject(str2);
            pluginDeviceInfo.P = str;
            pluginDeviceInfo.Q = jSONObject.optInt("product_id");
            pluginDeviceInfo.R = jSONObject.optInt("min_app_version");
            pluginDeviceInfo.S = jSONObject.optString("name");
            pluginDeviceInfo.T = jSONObject.optString("icon_on");
            pluginDeviceInfo.U = jSONObject.optString("icon_off");
            pluginDeviceInfo.V = jSONObject.optString("icon_offline");
            pluginDeviceInfo.W = jSONObject.optString("icon_b_pair");
            pluginDeviceInfo.X = jSONObject.optString("icon_real");
            pluginDeviceInfo.Y = jSONObject.optBoolean("bind_confirm");
            pluginDeviceInfo.Z = jSONObject.optString("model_regex");
            try {
                pluginDeviceInfo.Z = new JSONObject(pluginDeviceInfo.Z).optString(DeviceTagInterface.e);
            } catch (JSONException unused) {
            }
            pluginDeviceInfo.aa = jSONObject.optString("desc");
            pluginDeviceInfo.ab = jSONObject.optInt("pid");
            pluginDeviceInfo.ac = jSONObject.optString("smart_config_passwd");
            pluginDeviceInfo.ad = jSONObject.optInt("status");
            pluginDeviceInfo.ae = jSONObject.optString("brand_name");
            pluginDeviceInfo.af = jSONObject.optString("cate_name");
            pluginDeviceInfo.ag = jSONObject.optInt("bt_bind_style");
            pluginDeviceInfo.ah = jSONObject.optInt("ct_offline_enter");
            pluginDeviceInfo.ai = jSONObject.optInt("permission_control");
            pluginDeviceInfo.aj = jSONObject.optBoolean("wexin_share");
            pluginDeviceInfo.ak = jSONObject.optInt("bt_match");
            pluginDeviceInfo.al = jSONObject.optString("bt_rssi");
            pluginDeviceInfo.am = jSONObject.optInt("sc_failed");
            pluginDeviceInfo.i = jSONObject.optInt(DeviceMoreActivity.MENU_ITEM_NAME_VOICE_CONTROL);
            pluginDeviceInfo.j = jSONObject.optInt("voice_ctrl_ed");
            pluginDeviceInfo.k = jSONObject.optInt("op_history");
            pluginDeviceInfo.p = jSONObject.optInt("sc_type");
            pluginDeviceInfo.q = jSONObject.optInt("sc_status");
            JSONArray optJSONArray = jSONObject.optJSONArray("sc_type_more");
            pluginDeviceInfo.r = new ArrayList();
            if (optJSONArray != null) {
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    pluginDeviceInfo.r.add(Integer.valueOf(optJSONArray.optInt(i2)));
                }
            }
            pluginDeviceInfo.l = jSONObject.optInt("bt_is_secure");
            pluginDeviceInfo.m = jSONObject.optInt("bt_gateway");
            JSONArray optJSONArray2 = jSONObject.optJSONArray("sc_type_more_v2");
            pluginDeviceInfo.G = new ArrayList();
            if (optJSONArray2 != null) {
                for (int i3 = 0; i3 < optJSONArray2.length(); i3++) {
                    pluginDeviceInfo.G.add(Integer.valueOf(optJSONArray2.optInt(i3)));
                }
            }
            pluginDeviceInfo.K = jSONObject.optBoolean("fiveG_wifi");
            pluginDeviceInfo.an = jSONObject.optInt("subcategory_id");
            pluginDeviceInfo.L = new ArrayList();
            JSONArray optJSONArray3 = jSONObject.optJSONArray("relations");
            if (optJSONArray3 != null) {
                for (int i4 = 0; i4 < optJSONArray3.length(); i4++) {
                    pluginDeviceInfo.L.add(optJSONArray3.optString(i4));
                }
            }
            pluginDeviceInfo.M = jSONObject.optInt("rank");
            pluginDeviceInfo.N = jSONObject.optInt("inherit_id");
            pluginDeviceInfo.n = jSONObject.optInt("mesh_gateway");
            pluginDeviceInfo.o = jSONObject.optString("member_cnt");
            pluginDeviceInfo.O = DeviceResult.a(pluginDeviceInfo.m, pluginDeviceInfo.n, jSONObject.optInt("infrared_gateway"), jSONObject.optInt("zigbee_gateway"));
            return pluginDeviceInfo;
        } catch (JSONException unused2) {
            return null;
        }
    }

    public synchronized String b() {
        String str;
        str = "";
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("product_id", this.Q);
            jSONObject.put("min_app_version", this.R);
            jSONObject.put("name", this.S);
            jSONObject.put("icon_on", this.T);
            jSONObject.put("icon_off", this.U);
            jSONObject.put("icon_offline", this.V);
            jSONObject.put("icon_b_pair", this.W);
            jSONObject.put("icon_real", this.X);
            jSONObject.put("bind_confirm", this.Y);
            jSONObject.put("model_regex", this.Z);
            jSONObject.put("desc", this.aa);
            jSONObject.put("pid", this.ab);
            jSONObject.put("smart_config_passwd", this.ac);
            jSONObject.put("status", this.ad);
            jSONObject.put("brand_name", this.ae);
            jSONObject.put("cate_name", this.af);
            jSONObject.put("bt_bind_style", this.ag);
            jSONObject.put("ct_offline_enter", this.ah);
            jSONObject.put("permission_control", this.ai);
            jSONObject.put("wexin_share", this.aj);
            jSONObject.put("bt_match", this.ak);
            jSONObject.put("bt_rssi", this.al);
            jSONObject.put("sc_failed", this.am);
            jSONObject.put(DeviceMoreActivity.MENU_ITEM_NAME_VOICE_CONTROL, this.i);
            jSONObject.put("voice_ctrl_ed", this.j);
            jSONObject.put("op_history", this.k);
            jSONObject.put("sc_type", this.p);
            jSONObject.put("sc_status", this.q);
            JSONArray jSONArray = new JSONArray();
            if (this.r != null) {
                for (Integer intValue : this.r) {
                    jSONArray.put(intValue.intValue());
                }
                jSONObject.put("sc_type_more", jSONArray);
            }
            jSONObject.put("bt_is_secure", this.l);
            jSONObject.put("bt_gateway", this.m);
            JSONArray jSONArray2 = new JSONArray();
            if (this.G != null) {
                for (Integer intValue2 : this.G) {
                    jSONArray2.put(intValue2.intValue());
                }
                jSONObject.put("sc_type_more_v2", jSONArray2);
            }
            jSONObject.put("fiveG_wifi", this.K);
            jSONObject.put("subcategory_id", this.an);
            JSONArray jSONArray3 = new JSONArray();
            if (this.L != null) {
                for (String put : this.L) {
                    jSONArray3.put(put);
                }
                jSONObject.put("relations", jSONArray3);
            }
            jSONObject.put("rank", this.M);
            jSONObject.put("inherit_id", this.N);
            jSONObject.put("mesh_gateway", this.n);
            jSONObject.put("member_cnt", this.o);
            jSONObject.put("infrared_gateway", t(16) ? 1 : 0);
            if (t(4)) {
                jSONObject.put("zigbee_gateway", 2);
            } else if (t(8)) {
                jSONObject.put("zigbee_gateway", 4);
            } else {
                jSONObject.put("zigbee_gateway", 0);
            }
            str = jSONObject.toString();
        } catch (JSONException unused) {
        }
        return str;
    }

    public synchronized void a(boolean z2) {
        this.aj = z2;
    }

    public synchronized String c() {
        if (TextUtils.isEmpty(this.P)) {
            return "";
        }
        return this.P;
    }

    public synchronized void a(String str) {
        this.P = str;
    }

    public synchronized int d() {
        return this.Q;
    }

    public synchronized void a(int i2) {
        this.Q = i2;
    }

    public synchronized int e() {
        return this.p;
    }

    public synchronized void b(int i2) {
        this.p = i2;
    }

    public synchronized int f() {
        return this.q;
    }

    public synchronized void c(int i2) {
        this.q = i2;
    }

    public synchronized List<Integer> g() {
        return this.r;
    }

    public synchronized void a(List<Integer> list) {
        this.r = new ArrayList();
        this.r.addAll(list);
    }

    public synchronized boolean h() {
        return this.K;
    }

    public synchronized void b(boolean z2) {
        this.K = z2;
    }

    public synchronized List<Integer> i() {
        return this.G;
    }

    public synchronized void b(List<Integer> list) {
        this.G = list;
    }

    public synchronized int j() {
        return this.R;
    }

    public synchronized void d(int i2) {
        this.R = i2;
    }

    public synchronized String k() {
        if (TextUtils.isEmpty(this.S)) {
            return "";
        }
        return this.S;
    }

    public synchronized void b(String str) {
        this.S = str;
    }

    public synchronized String l() {
        if (TextUtils.isEmpty(this.T)) {
            return "";
        }
        return this.T;
    }

    public synchronized void c(String str) {
        this.T = str;
    }

    public synchronized String m() {
        if (TextUtils.isEmpty(this.U)) {
            return "";
        }
        return this.U;
    }

    public synchronized void d(String str) {
        this.U = str;
    }

    public synchronized String n() {
        if (TextUtils.isEmpty(this.V)) {
            return "";
        }
        return this.V;
    }

    public synchronized void e(String str) {
        this.V = str;
    }

    public synchronized String o() {
        if (TextUtils.isEmpty(this.W)) {
            return "";
        }
        return this.W;
    }

    public synchronized void f(String str) {
        this.W = str;
    }

    public synchronized String p() {
        if (TextUtils.isEmpty(this.X)) {
            return "";
        }
        return this.X;
    }

    public synchronized void g(String str) {
        this.X = str;
    }

    public synchronized boolean q() {
        return this.Y;
    }

    public synchronized void c(boolean z2) {
        this.Y = z2;
    }

    public synchronized String r() {
        return this.Z;
    }

    public synchronized void h(String str) {
        this.Z = str;
    }

    public synchronized String s() {
        return this.aa;
    }

    public synchronized void i(String str) {
        this.aa = str;
    }

    public synchronized int t() {
        return this.ab;
    }

    public synchronized void e(int i2) {
        this.ab = i2;
    }

    public synchronized String u() {
        return this.ac;
    }

    public synchronized void j(String str) {
        this.ac = str;
    }

    public synchronized PluginDeviceInfoStatus v() {
        return PluginDeviceInfoStatus.valueOf(this.ad);
    }

    public synchronized void f(int i2) {
        this.ad = i2;
    }

    public synchronized String w() {
        return this.ae;
    }

    public synchronized void k(String str) {
        this.ae = str;
    }

    public synchronized String x() {
        return this.af;
    }

    public synchronized void l(String str) {
        this.af = str;
    }

    public synchronized int y() {
        return this.ag;
    }

    public synchronized void g(int i2) {
        this.ag = i2;
    }

    public synchronized int z() {
        return this.ah;
    }

    public synchronized void h(int i2) {
        this.ah = i2;
    }

    public synchronized int A() {
        return this.ak;
    }

    public synchronized void i(int i2) {
        this.ak = i2;
    }

    public synchronized String B() {
        return this.al;
    }

    public synchronized void m(String str) {
        this.al = str;
    }

    public synchronized int C() {
        return this.am;
    }

    public synchronized void j(int i2) {
        this.i = i2;
    }

    public synchronized int D() {
        return this.i;
    }

    public synchronized void k(int i2) {
        this.j = i2;
    }

    public synchronized int E() {
        return this.j;
    }

    public synchronized void l(int i2) {
        this.k = i2;
    }

    public synchronized int F() {
        return this.k;
    }

    public synchronized void m(int i2) {
        this.am = i2;
    }

    public synchronized int G() {
        return this.ai;
    }

    public synchronized void n(int i2) {
        this.ai = i2;
    }

    public synchronized boolean H() {
        return this.aj;
    }

    public synchronized void o(int i2) {
        this.l = i2;
    }

    public synchronized int I() {
        return this.l;
    }

    public synchronized void p(int i2) {
        this.m = i2;
    }

    public synchronized int J() {
        return this.m;
    }

    public int K() {
        return this.an;
    }

    public void q(int i2) {
        this.an = i2;
    }

    public synchronized void c(List<String> list) {
        this.L = list;
    }

    public synchronized List<String> L() {
        return this.L;
    }

    public int M() {
        return this.M;
    }

    public void r(int i2) {
        this.M = i2;
    }

    public int N() {
        return this.N;
    }

    public void s(int i2) {
        this.N = i2;
    }

    public boolean t(int i2) {
        return (i2 & this.O) != 0;
    }

    public void u(int i2) {
        this.O = i2;
    }

    public synchronized void v(int i2) {
        this.n = i2;
    }

    public synchronized int O() {
        return this.n;
    }

    public void n(String str) {
        this.o = str;
    }

    public String P() {
        return this.o;
    }

    public synchronized String Q() {
        return "PluginDeviceInfo[" + "model:" + this.P + " " + "productId:" + this.Q + " " + "minAppVersion:" + this.R + " " + "name:" + this.S + " " + "iconOn:" + this.T + " " + "iconOff:" + this.U + " " + "iconOffline:" + this.V + " " + "bindConfirm:" + this.Y + " " + "modelRegex:" + this.Z + " " + "brandName:" + this.ae + " " + "categoryName:" + this.af + " " + Operators.ARRAY_END_STR;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.P);
        parcel.writeInt(this.Q);
        parcel.writeInt(this.R);
        parcel.writeString(this.S);
        parcel.writeString(this.T);
        parcel.writeString(this.U);
        parcel.writeString(this.V);
        parcel.writeString(this.W);
        parcel.writeString(this.X);
        parcel.writeByte(this.Y ? (byte) 1 : 0);
        parcel.writeString(this.Z);
        parcel.writeString(this.aa);
        parcel.writeInt(this.ab);
        parcel.writeString(this.ac);
        parcel.writeInt(this.ad);
        parcel.writeString(this.ae);
        parcel.writeString(this.af);
        parcel.writeInt(this.ag);
        parcel.writeInt(this.ah);
        parcel.writeInt(this.ai);
        parcel.writeByte(this.aj ? (byte) 1 : 0);
        parcel.writeInt(this.ak);
        parcel.writeString(this.al);
        parcel.writeInt(this.am);
        parcel.writeInt(this.i);
        parcel.writeInt(this.j);
        parcel.writeInt(this.k);
        parcel.writeInt(this.p);
        parcel.writeInt(this.q);
        parcel.writeList(this.r);
        parcel.writeInt(this.l);
        parcel.writeInt(this.m);
        parcel.writeList(this.G);
        parcel.writeByte(this.K ? (byte) 1 : 0);
        parcel.writeInt(this.an);
        parcel.writeList(this.L);
        parcel.writeInt(this.M);
        parcel.writeInt(this.N);
        parcel.writeInt(this.n);
        parcel.writeString(this.o);
        parcel.writeInt(this.O);
    }
}
