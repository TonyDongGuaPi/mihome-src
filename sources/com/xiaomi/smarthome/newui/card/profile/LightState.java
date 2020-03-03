package com.xiaomi.smarthome.newui.card.profile;

import com.libra.Color;
import com.mijia.model.property.CameraPropertyBase;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.newui.card.AdvancedFlow;
import java.util.Arrays;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class LightState {

    /* renamed from: a  reason: collision with root package name */
    public static final String f20543a = "on";
    public static final String b = "off";
    public static final int c = 1;
    public static final int d = 2;
    public static final int e = 3;
    public static final int f = 1;
    public static final int g = 2;
    public static final int h = 4;
    public static final int i = 5;
    private static final String j = "LightState";
    private List<String[]> A;
    private int B;
    private int C;
    private boolean D;
    private int E;
    private int F;
    private String k;
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private boolean q;
    private boolean r;
    private boolean s;
    private boolean t;
    private String u;
    private String v;
    private int w;
    private int[] x;
    private AdvancedFlow y;
    private int z;

    public static LightState a() {
        return new LightState("off", 2, 100, 4000, -65536, false);
    }

    public LightState(String str, int i2, int i3, int i4, int i5, int i6, int i7, boolean z2) {
        this.t = false;
        if (!str.equals("off") && !str.equals("on")) {
            str = "off";
        }
        if (!(i2 == 2 || i2 == 1 || i2 == 3)) {
            i2 = 2;
        }
        i3 = (i3 < 0 || i3 > 100) ? 100 : i3;
        i4 = (i4 < 1700 || i4 > 6500) ? 4000 : i4;
        i6 = (i6 < 0 || i6 > 359) ? 0 : i6;
        i7 = (i7 < 0 || i7 > 100) ? 0 : i7;
        this.k = str;
        this.l = i2;
        this.m = i3;
        this.o = i4;
        this.p = i5;
        this.B = i6;
        this.C = i7;
        this.q = z2;
    }

    public LightState(String str, int i2, int i3, int i4, int i5, boolean z2) {
        this(str, i2, i3, i4, i5, 0, 0, z2);
    }

    public LightState(String str) {
        this.t = false;
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.k = jSONObject.getString(CameraPropertyBase.l);
            this.l = jSONObject.getInt("model");
            this.m = jSONObject.getInt("bright");
            this.o = jSONObject.getInt("ct");
            this.p = jSONObject.getInt("rgb");
            this.q = jSONObject.getBoolean("flowing");
            this.B = jSONObject.getInt("hue");
            this.C = jSONObject.getInt("sat");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public void a(String str) {
        this.k = str;
    }

    public void a(int i2) {
        this.l = i2;
    }

    public void b(int i2) {
        this.m = i2;
    }

    public void c(int i2) {
        this.n = i2;
    }

    public void d(int i2) {
        this.o = i2;
    }

    public void e(int i2) {
        this.B = i2;
    }

    public void f(int i2) {
        this.C = i2;
    }

    public void g(int i2) {
        this.p = i2;
    }

    public void h(int i2) {
        this.E = i2;
    }

    public int b() {
        return this.E;
    }

    public void i(int i2) {
        this.F = i2;
    }

    public int c() {
        return this.F;
    }

    public void a(int[] iArr) {
        this.x = iArr;
    }

    public int[] d() {
        if (this.x == null) {
            this.x = new int[]{-65536, Color.g, -256, Color.h};
        }
        return this.x;
    }

    public boolean e() {
        return this.r;
    }

    public boolean f() {
        return this.s;
    }

    public void a(boolean z2) {
        this.r = z2;
    }

    public void b(boolean z2) {
        this.s = z2;
    }

    public String g() {
        return this.u;
    }

    public void b(String str) {
        this.u = str;
    }

    public String h() {
        return this.v;
    }

    public void c(String str) {
        this.v = str;
    }

    public int i() {
        return this.w;
    }

    public void j(int i2) {
        this.w = i2;
    }

    public boolean j() {
        return this.D;
    }

    public void c(boolean z2) {
        this.D = z2;
    }

    public boolean k() {
        return !"on".equalsIgnoreCase(this.k);
    }

    public int l() {
        if (this.l == 3) {
            return 1;
        }
        return this.l;
    }

    public int m() {
        return this.m;
    }

    public int n() {
        return this.n;
    }

    public int o() {
        return this.o;
    }

    public int p() {
        if (this.l != 3) {
            float[] fArr = new float[3];
            android.graphics.Color.colorToHSV(this.p, fArr);
            fArr[2] = YelightColorUtils.b(this.m);
            return android.graphics.Color.HSVToColor(fArr);
        }
        return android.graphics.Color.HSVToColor(new float[]{(float) this.B, ((float) this.C) / 100.0f, YelightColorUtils.b(this.m)});
    }

    public boolean q() {
        return this.q;
    }

    public void d(boolean z2) {
        this.q = z2;
    }

    public boolean equals(Object obj) {
        LightState lightState = (LightState) obj;
        if (l() != lightState.l() || Math.abs(m() - lightState.m()) > 3 || Math.abs(o() - lightState.o()) > 3) {
            return false;
        }
        if ((k() && !lightState.k()) || (!k() && lightState.k())) {
            return false;
        }
        if ((q() && !lightState.q()) || (!q() && lightState.q())) {
            return false;
        }
        if ((q() && this.y != null && !this.y.equals(lightState.s())) || e() != lightState.e()) {
            return false;
        }
        if (this.p == 0 || p() == lightState.p()) {
            return true;
        }
        return false;
    }

    public static LightState a(LightState lightState, LightState lightState2) {
        int m2;
        String str = (!lightState.k() || !lightState2.k()) ? "on" : "off";
        int l2 = lightState.l() == lightState2.l() ? lightState.l() : 2;
        boolean z2 = lightState.q() && lightState2.q();
        if (lightState.m() < lightState2.m()) {
            m2 = lightState.m();
        } else {
            m2 = lightState2.m();
        }
        int i2 = m2;
        int o2 = lightState.o();
        int p2 = lightState.p();
        int t2 = lightState.t();
        LightState lightState3 = new LightState(str, l2, i2, o2, p2, z2);
        lightState3.k(t2);
        return lightState3;
    }

    public boolean r() {
        return this.t;
    }

    public void e(boolean z2) {
        this.t = z2;
    }

    public void a(AdvancedFlow advancedFlow) {
        this.y = advancedFlow;
    }

    public AdvancedFlow s() {
        return this.y;
    }

    public int t() {
        return this.z;
    }

    public void k(int i2) {
        this.z = i2;
    }

    public String toString() {
        return "LightState{mPower='" + this.k + Operators.SINGLE_QUOTE + ", mMode=" + this.l + ", mBright=" + this.m + ", mNLBright=" + this.n + ", mCt=" + this.o + ", mRgb=" + this.p + ", mIsFlowing=" + this.q + ", bNightMode=" + this.r + ", bEnableNightTime=" + this.s + ", bMiBandSleepEnabled=" + this.t + ", mNightLightStart='" + this.u + Operators.SINGLE_QUOTE + ", mNightLightEnd='" + this.v + Operators.SINGLE_QUOTE + ", mNightLightDelay=" + this.w + ", mColors=" + Arrays.toString(this.x) + ", mFlow=" + this.y + ", mDelayTime=" + this.z + ", mHue=" + this.B + ", mSat=" + this.C + ", isPdoEnable=" + this.D + ", mWorkTime=" + this.E + ", mRestTime=" + this.F + Operators.BLOCK_END;
    }

    public void a(List<String[]> list) {
        this.A = list;
    }

    public List<String[]> u() {
        return this.A;
    }
}
