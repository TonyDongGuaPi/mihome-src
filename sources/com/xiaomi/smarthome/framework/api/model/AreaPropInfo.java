package com.xiaomi.smarthome.framework.api.model;

import com.mi.global.shop.model.Tags;
import com.xiaomi.smarthome.library.common.util.StringUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AreaPropInfo {

    /* renamed from: a  reason: collision with root package name */
    public static final String f16447a = "pm2.5";
    public static final String b = "aqi";
    public static final String c = "aqi_out";
    public static final String d = "aqi_count";
    public static final String e = "tds_in";
    public static final String f = "tds_out";
    public static final String g = "tds_count";
    public static final String h = "weather";
    public static final String i = "prop.humidity";
    public static final String j = "sd";
    public static final String k = "prop.humidity.cnt";
    public static final String l = "temp_range";
    public static final String m = "temp_high";
    public static final String n = "temperature";
    public static final String o = "temp_low";
    public static final String p = "show_items";
    public String A;
    public String B;
    public String C;
    public String D;
    public String E;
    public Map<String, Boolean> F = new HashMap();
    public Forecast G;
    public WeatherIndex H;
    public WeatherRealTime I;
    public String q;
    public String r;
    public String s;
    public String t;
    public String u;
    public String v;
    public String w;
    public String x;
    public String y;
    public String z;

    private String c(String str) {
        return str;
    }

    public static final Float a(String str) {
        if (str == null) {
            return null;
        }
        String replaceAll = str.replaceAll("[^0-9,\\.]", "");
        if (replaceAll.isEmpty()) {
            return null;
        }
        try {
            return Float.valueOf(Float.parseFloat(replaceAll));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static final String b(String str) {
        return a(str, "-");
    }

    public static final String a(String str, String str2) {
        Float a2 = a(str);
        if (a2 == null) {
            return str2;
        }
        return ((int) Math.ceil((double) a2.floatValue())) + "";
    }

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(f16447a, this.q);
            jSONObject.put("aqi", this.r);
            jSONObject.put(c, this.s);
            jSONObject.put(d, this.t);
            jSONObject.put(e, this.u);
            jSONObject.put(f, this.v);
            jSONObject.put(g, this.w);
            jSONObject.put(h, this.x);
            jSONObject.put(l, this.B);
            jSONObject.put(m, this.C);
            jSONObject.put(n, this.E);
            jSONObject.put(o, this.D);
            jSONObject.put("prop.humidity", this.y);
            jSONObject.put(j, this.z);
            jSONObject.put("prop.humidity.cnt", this.A);
            jSONObject.put(p, b());
            if (this.G != null) {
                jSONObject.put("forecast", this.G.a());
            }
            if (this.H != null) {
                jSONObject.put("index", this.H.a());
            }
            if (this.I != null) {
                jSONObject.put("realtime", this.I.a());
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    private JSONObject b() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (String next : this.F.keySet()) {
            jSONObject.put(next, this.F.get(next));
        }
        return jSONObject;
    }

    public void a(JSONObject jSONObject) {
        this.q = c(jSONObject.optString(f16447a));
        this.r = c(jSONObject.optString("aqi"));
        this.s = c(jSONObject.optString(c));
        this.t = c(jSONObject.optString(d));
        this.u = c(jSONObject.optString(e));
        this.v = c(jSONObject.optString(f));
        this.w = c(jSONObject.optString(g));
        this.x = c(jSONObject.optString(h));
        this.B = c(jSONObject.optString(l));
        this.C = c(jSONObject.optString(m));
        this.E = c(jSONObject.optString(n));
        this.D = c(jSONObject.optString(o));
        this.y = b(jSONObject.optString("prop.humidity"));
        this.z = b(jSONObject.optString(j));
        this.A = b(jSONObject.optString("prop.humidity.cnt"));
        b(jSONObject.optJSONObject(p));
        this.G = Forecast.a(jSONObject.optJSONObject("forecast"));
        this.H = WeatherIndex.a(jSONObject.optJSONArray("index"));
        this.I = WeatherRealTime.a(jSONObject.optJSONObject("realtime"));
    }

    private void a(AreaPropInfo areaPropInfo) {
        int indexOf;
        if (!StringUtil.c(areaPropInfo.B) && (indexOf = areaPropInfo.B.indexOf(8451)) > 0 && indexOf < areaPropInfo.B.length()) {
            areaPropInfo.C = areaPropInfo.B.substring(0, indexOf);
            areaPropInfo.D = areaPropInfo.B.substring(indexOf + 2, areaPropInfo.B.length() - 1);
        }
    }

    public void b(JSONObject jSONObject) {
        if (jSONObject != null) {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                this.F.put(next, Boolean.valueOf(jSONObject.optBoolean(next)));
            }
        }
    }

    public static class Forecast {
        public static final String A = "img_single";
        public static final String B = "img_title1";
        public static final String C = "img_title2";
        public static final String D = "img_title3";
        public static final String E = "img_title4";
        public static final String F = "img_title5";
        public static final String G = "img_title6";
        public static final String H = "img_title7";
        public static final String I = "img_title8";
        public static final String J = "img_title9";
        public static final String K = "img_title10";
        public static final String L = "img_title11";
        public static final String M = "img_title12";
        public static final String N = "img_title_single";
        public static final String O = "index";
        public static final String P = "index48";
        public static final String Q = "index48_d";
        public static final String R = "index48_uv";
        public static final String S = "index_ag";
        public static final String T = "index_cl";
        public static final String U = "index_co";
        public static final String V = "index_d";
        public static final String W = "index_ls";
        public static final String X = "index_tr";
        public static final String Y = "index_uv";
        public static final String Z = "index_xc";

        /* renamed from: a  reason: collision with root package name */
        public static final String f16448a = "city";
        public static final String aA = "wind2";
        public static final String aB = "wind3";
        public static final String aC = "wind4";
        public static final String aD = "wind5";
        public static final String aE = "wind6";
        public static final String aa = "st1";
        public static final String ab = "st2";
        public static final String ac = "st3";
        public static final String ad = "st4";
        public static final String ae = "st5";
        public static final String af = "st6";
        public static final String ag = "temp1";
        public static final String ah = "temp2";
        public static final String ai = "temp3";
        public static final String aj = "temp4";
        public static final String ak = "temp5";
        public static final String al = "temp6";
        public static final String am = "tempF1";
        public static final String an = "tempF2";
        public static final String ao = "tempF3";
        public static final String ap = "tempF4";
        public static final String aq = "tempF5";
        public static final String ar = "tempF6";
        public static final String as = "weather1";
        public static final String at = "weather2";
        public static final String au = "weather3";
        public static final String av = "weather4";
        public static final String aw = "weather5";
        public static final String ax = "weather6";
        public static final String ay = "week";
        public static final String az = "wind1";
        public static final String b = "city_en";
        public static final String c = "cityid";
        public static final String d = "date";
        public static final String e = "date_y";
        public static final String f = "fchh";
        public static final String g = "fl1";
        public static final String h = "fl2";
        public static final String i = "fl3";
        public static final String j = "fl4";
        public static final String k = "fl5";
        public static final String l = "fl6";
        public static final String m = "fx1";
        public static final String n = "fx2";
        public static final String o = "img1";
        public static final String p = "img2";
        public static final String q = "img3";
        public static final String r = "img4";
        public static final String s = "img5";
        public static final String t = "img6";
        public static final String u = "img7";
        public static final String v = "img8";
        public static final String w = "img9";
        public static final String x = "img10";
        public static final String y = "img11";
        public static final String z = "img12";
        public String aF;
        public String aG;
        public String aH;
        public String aI;
        public String aJ;
        public String aK;
        public String aL;
        public String aM;
        public String aN;
        public String aO;
        public String aP;
        public String aQ;
        public String aR;
        public String aS;
        public String aT;
        public String aU;
        public String aV;
        public String aW;
        public String aX;
        public String aY;
        public String aZ;
        public String bA;
        public String bB;
        public String bC;
        public String bD;
        public String bE;
        public String bF;
        public String bG;
        public String bH;
        public String bI;
        public String bJ;
        public String bK;
        public String bL;
        public String bM;
        public String bN;
        public String bO;
        public String bP;
        public String bQ;
        public String bR;
        public String bS;
        public String bT;
        public String bU;
        public String bV;
        public String bW;
        public String bX;
        public String bY;
        public String bZ;
        public String ba;
        public String bb;
        public String bc;
        public String bd;
        public String be;
        public String bf;
        public String bg;
        public String bh;
        public String bi;
        public String bj;
        public String bk;
        public String bl;
        public String bm;
        public String bn;
        public String bo;
        public String bp;
        public String bq;
        public String br;
        public String bs;
        public String bt;
        public String bu;
        public String bv;
        public String bw;
        public String bx;
        public String by;
        public String bz;
        public String ca;
        public String cb;
        public String cc;
        public String cd;
        public String ce;
        public String cf;
        public String cg;
        public String ch;
        public String ci;
        public String cj;

        public static Forecast a(JSONObject jSONObject) {
            Forecast forecast = new Forecast();
            if (jSONObject == null) {
                return forecast;
            }
            forecast.aF = jSONObject.optString("city");
            forecast.aG = jSONObject.optString(b);
            forecast.aH = jSONObject.optString(c);
            forecast.aI = jSONObject.optString("date");
            forecast.aJ = jSONObject.optString(e);
            forecast.aK = jSONObject.optString(f);
            forecast.aL = jSONObject.optString(g);
            forecast.aM = jSONObject.optString(h);
            forecast.aN = jSONObject.optString(i);
            forecast.aO = jSONObject.optString(j);
            forecast.aP = jSONObject.optString(k);
            forecast.aQ = jSONObject.optString(l);
            forecast.aR = jSONObject.optString(m);
            forecast.aS = jSONObject.optString(n);
            forecast.aT = jSONObject.optString(o);
            forecast.aU = jSONObject.optString(p);
            forecast.aV = jSONObject.optString(q);
            forecast.aW = jSONObject.optString(r);
            forecast.aX = jSONObject.optString(s);
            forecast.aY = jSONObject.optString(t);
            forecast.aZ = jSONObject.optString(u);
            forecast.ba = jSONObject.optString(v);
            forecast.bb = jSONObject.optString(w);
            forecast.bc = jSONObject.optString(x);
            forecast.bd = jSONObject.optString(y);
            forecast.be = jSONObject.optString(z);
            forecast.bf = jSONObject.optString(A);
            forecast.bg = jSONObject.optString(B);
            forecast.bh = jSONObject.optString(C);
            forecast.bi = jSONObject.optString(D);
            forecast.bj = jSONObject.optString(E);
            forecast.bk = jSONObject.optString(F);
            forecast.bl = jSONObject.optString(G);
            forecast.bm = jSONObject.optString(H);
            forecast.bn = jSONObject.optString(I);
            forecast.bo = jSONObject.optString(J);
            forecast.bp = jSONObject.optString(K);
            forecast.bq = jSONObject.optString(L);
            forecast.br = jSONObject.optString(M);
            forecast.bs = jSONObject.optString(N);
            forecast.bt = jSONObject.optString("index");
            forecast.bu = jSONObject.optString(P);
            forecast.bv = jSONObject.optString(Q);
            forecast.bw = jSONObject.optString(R);
            forecast.bx = jSONObject.optString(S);
            forecast.by = jSONObject.optString(T);
            forecast.bz = jSONObject.optString(U);
            forecast.bA = jSONObject.optString(V);
            forecast.bB = jSONObject.optString(W);
            forecast.bC = jSONObject.optString(X);
            forecast.bD = jSONObject.optString(Y);
            forecast.bE = jSONObject.optString(Z);
            forecast.bF = jSONObject.optString(aa);
            forecast.bG = jSONObject.optString(ab);
            forecast.bH = jSONObject.optString(ac);
            forecast.bI = jSONObject.optString(ad);
            forecast.bJ = jSONObject.optString(ae);
            forecast.bK = jSONObject.optString(af);
            forecast.bL = jSONObject.optString(ag);
            forecast.bM = jSONObject.optString(ah);
            forecast.bN = jSONObject.optString(ai);
            forecast.bO = jSONObject.optString(aj);
            forecast.bP = jSONObject.optString(ak);
            forecast.bQ = jSONObject.optString(al);
            forecast.bR = jSONObject.optString(am);
            forecast.bS = jSONObject.optString(an);
            forecast.bT = jSONObject.optString(ao);
            forecast.bU = jSONObject.optString(ap);
            forecast.bV = jSONObject.optString(aq);
            forecast.bW = jSONObject.optString(ar);
            forecast.bX = jSONObject.optString(as);
            forecast.bY = jSONObject.optString(at);
            forecast.bZ = jSONObject.optString(au);
            forecast.ca = jSONObject.optString(av);
            forecast.cb = jSONObject.optString(aw);
            forecast.cc = jSONObject.optString(ax);
            forecast.cd = jSONObject.optString(ay);
            forecast.ce = jSONObject.optString(az);
            forecast.cf = jSONObject.optString(aA);
            forecast.cg = jSONObject.optString(aB);
            forecast.ch = jSONObject.optString(aC);
            forecast.ci = jSONObject.optString(aD);
            forecast.cj = jSONObject.optString(aE);
            return forecast;
        }

        public JSONObject a() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("city", this.aF);
                jSONObject.put(b, this.aG);
                jSONObject.put(c, this.aH);
                jSONObject.put("date", this.aI);
                jSONObject.put(e, this.aJ);
                jSONObject.put(f, this.aK);
                jSONObject.put(g, this.aL);
                jSONObject.put(h, this.aM);
                jSONObject.put(i, this.aN);
                jSONObject.put(j, this.aO);
                jSONObject.put(k, this.aP);
                jSONObject.put(l, this.aQ);
                jSONObject.put(m, this.aR);
                jSONObject.put(n, this.aS);
                jSONObject.put(o, this.aT);
                jSONObject.put(p, this.aU);
                jSONObject.put(q, this.aV);
                jSONObject.put(r, this.aW);
                jSONObject.put(s, this.aX);
                jSONObject.put(t, this.aY);
                jSONObject.put(u, this.aZ);
                jSONObject.put(v, this.ba);
                jSONObject.put(w, this.bb);
                jSONObject.put(x, this.bc);
                jSONObject.put(y, this.bd);
                jSONObject.put(z, this.be);
                jSONObject.put(A, this.bf);
                jSONObject.put(B, this.bg);
                jSONObject.put(C, this.bh);
                jSONObject.put(D, this.bi);
                jSONObject.put(E, this.bj);
                jSONObject.put(F, this.bk);
                jSONObject.put(G, this.bl);
                jSONObject.put(H, this.bm);
                jSONObject.put(I, this.bn);
                jSONObject.put(J, this.bo);
                jSONObject.put(K, this.bp);
                jSONObject.put(L, this.bq);
                jSONObject.put(M, this.br);
                jSONObject.put(N, this.bs);
                jSONObject.put("index", this.bt);
                jSONObject.put(P, this.bu);
                jSONObject.put(Q, this.bv);
                jSONObject.put(R, this.bw);
                jSONObject.put(S, this.bx);
                jSONObject.put(T, this.by);
                jSONObject.put(U, this.bz);
                jSONObject.put(V, this.bA);
                jSONObject.put(W, this.bB);
                jSONObject.put(X, this.bC);
                jSONObject.put(Y, this.bD);
                jSONObject.put(Z, this.bE);
                jSONObject.put(aa, this.bF);
                jSONObject.put(ab, this.bG);
                jSONObject.put(ac, this.bH);
                jSONObject.put(ad, this.bI);
                jSONObject.put(ae, this.bJ);
                jSONObject.put(af, this.bK);
                jSONObject.put(ag, this.bL);
                jSONObject.put(ah, this.bM);
                jSONObject.put(ai, this.bN);
                jSONObject.put(aj, this.bO);
                jSONObject.put(ak, this.bP);
                jSONObject.put(al, this.bQ);
                jSONObject.put(am, this.bR);
                jSONObject.put(an, this.bS);
                jSONObject.put(ao, this.bT);
                jSONObject.put(ap, this.bU);
                jSONObject.put(aq, this.bV);
                jSONObject.put(ar, this.bW);
                jSONObject.put(as, this.bX);
                jSONObject.put(at, this.bY);
                jSONObject.put(au, this.bZ);
                jSONObject.put(av, this.ca);
                jSONObject.put(aw, this.cb);
                jSONObject.put(ax, this.cc);
                jSONObject.put(ay, this.cd);
                jSONObject.put(az, this.ce);
                jSONObject.put(aA, this.cf);
                jSONObject.put(aB, this.cg);
                jSONObject.put(aC, this.ch);
                jSONObject.put(aD, this.ci);
                jSONObject.put(aE, this.cj);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }
    }

    public static class WeatherIndex {

        /* renamed from: a  reason: collision with root package name */
        public List<WeatherIndexItem> f16449a = new ArrayList();

        public static WeatherIndex a(JSONArray jSONArray) {
            WeatherIndex weatherIndex = new WeatherIndex();
            if (jSONArray == null) {
                return weatherIndex;
            }
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    if (jSONObject != null) {
                        weatherIndex.f16449a.add(WeatherIndexItem.a(jSONObject));
                    }
                } catch (JSONException unused) {
                }
            }
            return weatherIndex;
        }

        public JSONArray a() {
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < this.f16449a.size(); i++) {
                if (this.f16449a.get(i) != null) {
                    jSONArray.put(this.f16449a.get(i).a());
                }
            }
            return jSONArray;
        }

        public static class WeatherIndexItem {

            /* renamed from: a  reason: collision with root package name */
            public String f16450a;
            public String b;
            public String c;
            public String d;
            public String e;

            public static WeatherIndexItem a(JSONObject jSONObject) {
                WeatherIndexItem weatherIndexItem = new WeatherIndexItem();
                if (jSONObject == null) {
                    return weatherIndexItem;
                }
                weatherIndexItem.f16450a = jSONObject.optString("code");
                weatherIndexItem.b = jSONObject.optString(Tags.MiPhoneDetails.DETAILS);
                weatherIndexItem.c = jSONObject.optString("index");
                weatherIndexItem.d = jSONObject.optString("name");
                weatherIndexItem.e = jSONObject.optString("otherName");
                return weatherIndexItem;
            }

            public JSONObject a() {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("code", this.f16450a);
                    jSONObject.put(Tags.MiPhoneDetails.DETAILS, this.b);
                    jSONObject.put("index", this.c);
                    jSONObject.put("name", this.d);
                    jSONObject.put("otherName", this.e);
                } catch (JSONException unused) {
                }
                return jSONObject;
            }
        }
    }

    public static class WeatherRealTime {

        /* renamed from: a  reason: collision with root package name */
        public String f16451a;
        public String b;
        public String c;
        public String d;
        public String e;
        public String f;
        public String g;
        public String h;
        public String i;
        public String j;
        public String k;

        public static WeatherRealTime a(JSONObject jSONObject) {
            WeatherRealTime weatherRealTime = new WeatherRealTime();
            if (jSONObject == null) {
                return weatherRealTime;
            }
            weatherRealTime.f16451a = jSONObject.optString("SD");
            weatherRealTime.b = jSONObject.optString("WD");
            weatherRealTime.c = jSONObject.optString("WS");
            weatherRealTime.d = jSONObject.optString("WSE");
            weatherRealTime.e = jSONObject.optString("city");
            weatherRealTime.f = jSONObject.optString(Forecast.c);
            weatherRealTime.g = jSONObject.optString("isRadar");
            weatherRealTime.h = jSONObject.optString("radar");
            weatherRealTime.i = jSONObject.optString("temp");
            weatherRealTime.j = jSONObject.optString("time");
            weatherRealTime.k = jSONObject.optString(AreaPropInfo.h);
            return weatherRealTime;
        }

        public JSONObject a() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("SD", this.f16451a);
                jSONObject.put("WD", this.b);
                jSONObject.put("WS", this.c);
                jSONObject.put("WSE", this.d);
                jSONObject.put("city", this.e);
                jSONObject.put(Forecast.c, this.f);
                jSONObject.put("isRadar", this.g);
                jSONObject.put("radar", this.h);
                jSONObject.put("temp", this.i);
                jSONObject.put("time", this.j);
                jSONObject.put(AreaPropInfo.h, this.k);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }
    }
}
