package com.amap.location.common.model;

import android.location.Location;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import com.google.android.exoplayer2.C;
import com.mi.global.shop.model.Tags;
import miuipub.reflect.Field;
import org.json.JSONObject;

public class AmapLoc {
    @Deprecated
    public static final String A = "6";
    public static final String B = "8";
    public static final String C = "9";
    public static String D = null;

    /* renamed from: a  reason: collision with root package name */
    public static final int f4583a = 0;
    private static final int ao = 1;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final String f = "new";
    public static final String g = "mem";
    public static final String h = "wifioff";
    public static final String i = "wifioffv3";
    public static final String j = "file";
    public static final int k = -1;
    public static final int l = 0;
    public static final int m = 1;
    public static final String n = "-5";
    public static final String o = "-4";
    public static final String p = "-3";
    public static final String q = "-2";
    public static final String r = "-1";
    public static final String s = "0";
    public static final String t = "1";
    public static final String u = "14";
    public static final String v = "2";
    public static final String w = "24";
    public static final String x = "3";
    public static final String y = "4";
    public static final String z = "5";
    private String E = "";
    private double F = 0.0d;
    private double G = 0.0d;
    private double H = 0.0d;
    private float I = 0.0f;
    private float J = 0.0f;
    private float K = 0.0f;
    private long L = 0;
    private String M = "new";
    private String N = "";
    private String O = "";
    private String P = "";
    private String Q = "";
    private String R = "";
    private String S = "";
    private String T = "";
    private String U = "";
    private String V = "";
    private String W = "";
    private String X = "";
    private String Y = "";
    private String Z = "";
    private String aa = "";
    private String ab = null;
    private String ac = "";
    private String ad = "";
    private boolean ae = false;
    private int af = -1;
    private String ag = "";
    private JSONObject ah = null;
    private float ai = 0.0f;
    private byte[] aj = null;
    private int ak;
    private int al = -1;
    private String am = "";
    private boolean an;

    public AmapLoc() {
    }

    public AmapLoc(AmapLoc amapLoc) {
        if (amapLoc != null) {
            try {
                this.E = amapLoc.E;
                this.F = amapLoc.F;
                this.G = amapLoc.G;
                this.H = amapLoc.H;
                this.I = amapLoc.I;
                this.J = amapLoc.J;
                this.K = amapLoc.K;
                this.L = amapLoc.L;
                this.M = amapLoc.M;
                this.N = amapLoc.N;
                this.O = amapLoc.O;
                this.P = amapLoc.P;
                this.Q = amapLoc.Q;
                this.R = amapLoc.R;
                this.S = amapLoc.S;
                this.T = amapLoc.T;
                this.U = amapLoc.U;
                this.V = amapLoc.V;
                this.W = amapLoc.W;
                this.X = amapLoc.X;
                this.Y = amapLoc.Y;
                this.Z = amapLoc.Z;
                this.aa = amapLoc.aa;
                this.ab = amapLoc.ab;
                this.ac = amapLoc.ac;
                this.ad = amapLoc.ad;
                this.ae = amapLoc.ae;
                this.af = amapLoc.af;
                this.ag = amapLoc.ag;
                this.ah = amapLoc.ah;
                this.al = amapLoc.al;
                this.am = amapLoc.am;
                this.an = amapLoc.an;
            } catch (Exception unused) {
            }
        }
    }

    public AmapLoc(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                a(jSONObject.getString("provider"));
                a(jSONObject.getDouble(Tags.Nearby.LON));
                b(jSONObject.getDouble(Tags.Nearby.LAT));
                if (jSONObject.has("altitude")) {
                    c(jSONObject.getDouble("altitude"));
                }
                b((float) jSONObject.getLong("accuracy"));
                c((float) jSONObject.getLong("speed"));
                d((float) jSONObject.getLong("bearing"));
                d(jSONObject.getString("type"));
                f(jSONObject.getString("retype"));
                g(jSONObject.getString("rdesc"));
                h(jSONObject.getString("citycode"));
                i(jSONObject.getString("desc"));
                j(jSONObject.getString("adcode"));
                k(jSONObject.getString("country"));
                l(jSONObject.getString("province"));
                m(jSONObject.getString("city"));
                o(jSONObject.getString("road"));
                p(jSONObject.getString("street"));
                q(jSONObject.getString("number"));
                r(jSONObject.getString("aoiname"));
                s(jSONObject.getString("poiname"));
                if (jSONObject.has(C.CENC_TYPE_cens)) {
                    t(jSONObject.getString(C.CENC_TYPE_cens));
                }
                if (jSONObject.has("poiid")) {
                    u(jSONObject.getString("poiid"));
                }
                if (jSONObject.has(Tags.Kuwan.COMMENT_FLOOR)) {
                    v(jSONObject.getString(Tags.Kuwan.COMMENT_FLOOR));
                }
                if (jSONObject.has("coord")) {
                    w(jSONObject.getString("coord"));
                }
                if (jSONObject.has("mcell")) {
                    x(jSONObject.getString("mcell"));
                }
                if (jSONObject.has("time")) {
                    a(jSONObject.getLong("time"));
                }
                if (jSONObject.has("district")) {
                    n(jSONObject.getString("district"));
                }
                if (jSONObject.has("scenarioConfidence")) {
                    a(jSONObject.optInt("scenarioConfidence"));
                }
                if (jSONObject.has("resubtype")) {
                    e(jSONObject.optString("resubtype"));
                }
                if (jSONObject.has("isLast")) {
                    a(jSONObject.optBoolean("isLast"));
                }
            } catch (Exception unused) {
            }
        }
    }

    public static int a(AmapLoc amapLoc) {
        if (amapLoc != null) {
            if (amapLoc.n()) {
                return 2;
            }
            if ("new".equals(amapLoc.k())) {
                return 1;
            }
            if (g.equals(amapLoc.k())) {
                return 3;
            }
            if ("file".equals(amapLoc.k()) || h.equals(amapLoc.k())) {
                return 4;
            }
        }
        return 0;
    }

    public static boolean b(Location location) {
        if (location == null) {
            return false;
        }
        try {
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();
            boolean equals = "gps".equals(location.getProvider());
            boolean hasAccuracy = location.hasAccuracy();
            if (longitude == 0.0d && latitude == 0.0d) {
                return false;
            }
            if (longitude > 180.0d) {
                return false;
            }
            if (latitude > 90.0d) {
                return false;
            }
            if (longitude < -180.0d) {
                return false;
            }
            if (latitude < -90.0d) {
                return false;
            }
            if (equals && hasAccuracy && location.getAccuracy() < -1.0E-8f) {
                return false;
            }
            if (equals || !hasAccuracy || location.getAccuracy() > 0.0f) {
                return !Double.isNaN(latitude) && !Double.isNaN(longitude);
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    private void z(String str) {
        this.I = Float.parseFloat(str);
    }

    public String A() {
        return this.Z;
    }

    public String B() {
        return this.aa;
    }

    public String C() {
        return this.ab;
    }

    public String D() {
        return this.ac;
    }

    public String E() {
        return this.ad;
    }

    public int F() {
        return this.af;
    }

    public String G() {
        return this.ag;
    }

    public AmapLoc H() {
        String G2 = G();
        if (TextUtils.isEmpty(G2)) {
            return null;
        }
        String[] split = G2.split(",");
        if (split.length != 3) {
            return null;
        }
        AmapLoc amapLoc = new AmapLoc();
        amapLoc.a(b());
        amapLoc.b(split[0]);
        amapLoc.c(split[1]);
        amapLoc.b(Float.parseFloat(split[2]));
        amapLoc.h(q());
        amapLoc.j(s());
        amapLoc.k(t());
        amapLoc.l(u());
        amapLoc.m(v());
        amapLoc.n(w());
        amapLoc.a(j());
        amapLoc.d(k());
        amapLoc.w(String.valueOf(F()));
        if (!amapLoc.R()) {
            return null;
        }
        return amapLoc;
    }

    public boolean I() {
        return this.ae;
    }

    public JSONObject J() {
        return this.ah;
    }

    public boolean K() {
        return this.H > 0.0d;
    }

    public boolean L() {
        return this.I > 0.0f;
    }

    public boolean M() {
        return this.K > 0.0f;
    }

    public boolean N() {
        return this.J > 0.0f;
    }

    public byte[] O() {
        return this.aj;
    }

    public void P() {
        this.aj = null;
    }

    public boolean Q() {
        return this.ak == 1;
    }

    public boolean R() {
        if (o().equals("8") || o().equals("5") || o().equals("6")) {
            return false;
        }
        double c2 = c();
        double d2 = d();
        return !(c2 == 0.0d && d2 == 0.0d && ((double) g()) == 0.0d) && c2 <= 180.0d && d2 <= 90.0d && c2 >= -180.0d && d2 >= -90.0d;
    }

    public String S() {
        return d(1);
    }

    public Location a() {
        Location location = new Location(b());
        location.setTime(this.L);
        if (Build.VERSION.SDK_INT >= 17) {
            location.setElapsedRealtimeNanos(this.L);
        }
        location.setLatitude(this.G);
        location.setLongitude(this.F);
        location.setAltitude(this.H);
        location.setSpeed(this.J);
        location.setBearing(this.K);
        location.setAccuracy(this.I);
        return location;
    }

    public void a(double d2) {
        if (d2 > 180.0d || d2 < -180.0d) {
            this.F = 0.0d;
            this.ae = true;
            return;
        }
        double round = (double) Math.round(d2 * 1000000.0d);
        Double.isNaN(round);
        this.F = round / 1000000.0d;
    }

    public void a(float f2) {
        this.ai = f2;
    }

    public void a(int i2) {
        this.al = i2;
    }

    public void a(long j2) {
        this.L = j2;
    }

    public void a(Location location) {
        if (b(location)) {
            this.G = location.getLatitude();
            this.F = location.getLongitude();
            this.H = location.getAltitude();
            this.J = location.getSpeed();
            this.K = location.getBearing();
            this.I = location.getAccuracy();
        }
    }

    public void a(String str) {
        this.E = str;
    }

    public void a(JSONObject jSONObject) {
        this.ah = jSONObject;
    }

    public void a(boolean z2) {
        this.an = z2;
    }

    public String b() {
        return this.E;
    }

    public void b(double d2) {
        if (d2 > 90.0d || d2 < -90.0d) {
            this.G = 0.0d;
            this.ae = true;
            return;
        }
        double round = (double) Math.round(d2 * 1000000.0d);
        Double.isNaN(round);
        this.G = round / 1000000.0d;
    }

    public void b(float f2) {
        z(String.valueOf(Math.round(f2)));
    }

    public void b(int i2) {
        w(String.valueOf(i2));
    }

    public void b(String str) {
        a(Double.parseDouble(str));
    }

    public void b(boolean z2) {
        this.ae = z2;
    }

    public double c() {
        return this.F;
    }

    public void c(double d2) {
        this.H = d2;
    }

    public void c(float f2) {
        this.J = f2 > 100.0f ? 0.0f : (f2 * 10.0f) / 10.0f;
    }

    public void c(int i2) {
        this.ak = i2;
    }

    public void c(String str) {
        b(Double.parseDouble(str));
    }

    public double d() {
        return this.G;
    }

    public String d(int i2) {
        JSONObject e2 = e(i2);
        if (e2 == null) {
            return null;
        }
        return e2.toString();
    }

    public void d(float f2) {
        this.K = (f2 * 10.0f) / 10.0f;
    }

    public void d(String str) {
        this.M = str;
    }

    public double e() {
        return this.H;
    }

    public JSONObject e(int i2) {
        try {
            JSONObject jSONObject = new JSONObject();
            switch (i2) {
                case 1:
                    jSONObject.put("altitude", this.H);
                    jSONObject.put("speed", (double) this.J);
                    jSONObject.put("bearing", (double) this.K);
                    jSONObject.put("retype", this.N);
                    jSONObject.put("rdesc", this.O);
                    jSONObject.put("citycode", this.P);
                    jSONObject.put("desc", this.Q);
                    jSONObject.put("adcode", this.R);
                    jSONObject.put("country", this.S);
                    jSONObject.put("province", this.T);
                    jSONObject.put("city", this.U);
                    jSONObject.put("district", this.V);
                    jSONObject.put("road", this.W);
                    jSONObject.put("street", this.X);
                    jSONObject.put("number", this.Y);
                    jSONObject.put("aoiname", this.Z);
                    jSONObject.put("poiname", this.aa);
                    jSONObject.put(C.CENC_TYPE_cens, this.ab);
                    jSONObject.put("poiid", this.ac);
                    jSONObject.put(Tags.Kuwan.COMMENT_FLOOR, this.ad);
                    jSONObject.put("coord", this.af);
                    jSONObject.put("mcell", this.ag);
                    jSONObject.put("scenarioConfidence", this.al);
                    jSONObject.put("resubtype", this.am);
                    jSONObject.put("isLast", this.an);
                    if (this.ah != null && jSONObject.has("offpct")) {
                        jSONObject.put("offpct", this.ah.getString("offpct"));
                        break;
                    }
                case 2:
                    break;
                case 3:
                    break;
                default:
                    return jSONObject;
            }
            jSONObject.put("time", this.L);
            jSONObject.put("provider", this.E);
            jSONObject.put(Tags.Nearby.LON, this.F);
            jSONObject.put(Tags.Nearby.LAT, this.G);
            jSONObject.put("accuracy", (double) this.I);
            jSONObject.put("type", this.M);
            return jSONObject;
        } catch (Exception unused) {
            return null;
        }
    }

    public void e(String str) {
        this.am = str;
    }

    public float f() {
        return this.ai;
    }

    public void f(String str) {
        this.N = str;
    }

    public float g() {
        return this.I;
    }

    public void g(String str) {
        this.O = str;
    }

    public float h() {
        return this.J;
    }

    public void h(String str) {
        this.P = str;
    }

    public float i() {
        return this.K;
    }

    public void i(String str) {
        this.Q = str;
    }

    public long j() {
        return this.L;
    }

    public void j(String str) {
        this.R = str;
    }

    public String k() {
        return this.M;
    }

    public void k(String str) {
        this.S = str;
    }

    public int l() {
        return this.al;
    }

    public void l(String str) {
        this.T = str;
    }

    public String m() {
        return this.am;
    }

    public void m(String str) {
        this.U = str;
    }

    public void n(String str) {
        this.V = str;
    }

    public boolean n() {
        return this.an;
    }

    public String o() {
        return this.N;
    }

    public void o(String str) {
        this.W = str;
    }

    public String p() {
        return this.O;
    }

    public void p(String str) {
        this.X = str;
    }

    public String q() {
        return this.P;
    }

    public void q(String str) {
        this.Y = str;
    }

    public String r() {
        return this.Q;
    }

    public void r(String str) {
        this.Z = str;
    }

    public String s() {
        return this.R;
    }

    public void s(String str) {
        this.aa = str;
    }

    public String t() {
        return this.S;
    }

    public void t(String str) {
        if (!TextUtils.isEmpty(str)) {
            String[] split = str.split("\\*");
            int length = split.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    break;
                }
                String str2 = split[i2];
                if (!TextUtils.isEmpty(str2)) {
                    String[] split2 = str2.split(",");
                    a(Double.parseDouble(split2[0]));
                    b(Double.parseDouble(split2[1]));
                    b((float) Integer.parseInt(split2[2]));
                    break;
                }
                i2++;
            }
            this.ab = str;
        }
    }

    public String u() {
        return this.T;
    }

    public void u(String str) {
        this.ac = str;
    }

    public String v() {
        return this.U;
    }

    public void v(String str) {
        if (!TextUtils.isEmpty(str)) {
            str = str.replace(Field.g, "");
            try {
                Integer.parseInt(str);
            } catch (Exception unused) {
                str = null;
            }
        }
        this.ad = str;
    }

    public String w() {
        return this.V;
    }

    public void w(String str) {
        int i2;
        if (!TextUtils.isEmpty(str)) {
            if (this.E.equals("gps")) {
                this.af = 0;
                return;
            } else if (str.equals("0")) {
                this.af = 0;
                return;
            } else if (str.equals("1")) {
                i2 = 1;
                this.af = i2;
            }
        }
        i2 = -1;
        this.af = i2;
    }

    public String x() {
        return this.W;
    }

    public void x(String str) {
        this.ag = str;
    }

    public String y() {
        return this.X;
    }

    public void y(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                this.aj = Base64.decode(str, 0);
            }
        } catch (Exception unused) {
        }
    }

    public String z() {
        return this.Y;
    }
}
