package com.mijia.app;

import android.text.TextUtils;
import com.tutk.TuTkClient;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.plugin.DeviceConstant;
import com.xiaomi.smarthome.stat.PluginStatReporter;
import org.json.JSONException;
import org.json.JSONObject;

public class Event {
    public static String A = "plg.set.v9b.l9m";
    public static String B = "plg.g83.t2b.ibo";
    public static String C = "plg.g83.p2n.d4x";
    public static String D = "plg.g83.4bw.i12";
    public static String E = "plg.g83.mgq.lik";
    public static String F = "plg.g83.q3w.x3x";
    public static String G = "plg.g83.rla.iza";
    public static String H = "plg.g83.g68.r3f";
    public static String I = "plg.g83.ig2.gtq";
    public static String J = "plg.g83.a1n.k3m";
    public static String K = "plg.g83.r1k.h7u";
    public static String L = "plg.gn9.szd.5im";
    public static String M = "plg.gn9.dr4.dsi";
    public static String N = "plg.gn9.c4r.h4u";
    public static String O = "plg.gn9.a2p.e8m";
    public static String P = "plg.gn9.2dn.bbr";
    public static String Q = "plg.gn9.tnk.omz";
    public static String R = "plg.set.j1w.a2g";
    public static String S = "soft_code";
    public static String T = "plg.i1e.0ju.ozz";
    public static String U = "plg.i1e.4rq.9sm";
    public static String V = "plg.gn9.i4v.l1t";
    public static String W = "plg.ert.8ui.lk3";
    public static String X = "plg.up1.gre.860";
    public static String Y = "plg.up1.kn0.75r";
    public static String Z = "plg.vvm.uhc.ixr";

    /* renamed from: a  reason: collision with root package name */
    public static String f7858a = "plg.tbm.a8j.ti4";
    public static String aA = "8fq.a57.2w9.3yg";
    public static String aB = "8fq.a57.2w9.br3";
    public static String aC = "8fq.a57.2w9.89h";
    public static String aD = "8fq.a57.2w9.3p6";
    public static String aE = "8fq.a57.2w9.2di";
    public static String aF = "8fq.a57.2w9.0s8";
    public static String aG = "8fq.a57.2w9.ao1";
    public static String aH = "8fq.a57.2w9.2gb";
    public static String aI = "8fq.a57.2w9.9qd";
    public static String aJ = "8fq.a57.2w9.3hf";
    public static String aK = "8fq.a57.2w9.n3x";
    public static String aL = "8fq.a57.2w9.j69";
    public static String aM = "8fq.a57.2w9.59k";
    public static String aN = "8fq.a57.2w9.5ye";
    public static String aO = "8fq.a57.2w9.6ml";
    public static String aP = "8fq.a57.2w9.ku5";
    public static String aQ = "8fq.a57.2w9.0xp";
    public static String aR = "8fq.a57.2w9.pm2";
    public static String aS = "8fq.a57.2w9.4d1";
    public static String aT = "8fq.a57.2w9.2ti";
    public static String aU = "8fq.a57.2w9.9s1";
    public static String aV = "8fq.a57.2w9.o0k";
    public static String aW = "8fq.a57.2w9.o8d";
    public static String aX = "8fq.a57.2w9.ho1";
    public static String aY = "8fq.a57.2w9.9vg";
    public static String aZ = "8fq.a57.2w9.u3f";
    public static String aa = "plg.vvm.hbn.4ps";
    public static String ab = "plg.xcf.07y.8aq";
    public static String ac = "plg.xcf.uiq.3ye";
    public static String ad = "plg.gl6.e78.x9t";
    public static String ae = "plg.gl6.t3u.dzx";
    public static String af = "plg.4gw.ycy.pa4";
    public static String ag = "plg.4gw.4fn.53o";
    public static String ah = "plg.tbm.zkk.mxy";
    public static String ai = "plg.tbm.xu9.pyr";
    public static String aj = "plg.l09.p2p.7dw";
    public static String ak = "plg.l09.37g.bs6";
    public static String al = "plg.tbm.jz4.fgj";
    public static String am = "8fq.a57.2w9.iw4";
    public static String an = "8fq.a57.2w9.1zy";
    public static String ao = "8fq.a57.2w9.61a";
    public static String ap = "8fq.a57.2w9.h5w";
    public static String aq = "8fq.a57.2w9.61l";
    public static String ar = "8fq.a57.2w9.8hk";
    public static String as = "8fq.a57.2w9.b8a";
    public static String at = "8fq.a57.2w9.o83";
    public static String au = "8fq.a57.2w9.c0f";
    public static String av = "8fq.a57.2w9.67f";
    public static String aw = "8fq.a57.2w9.4pc";
    public static String ax = "8fq.a57.2w9.75d";
    public static String ay = "8fq.a57.2w9.v3y";
    public static String az = "8fq.a57.2w9.0u9";
    public static String b = "plg.5hi.c8u.xtf";
    public static String bA = "u5v.u1o.4k5.5e7";
    public static String bB = "u5v.u1o.4k5.4ot";
    public static String bC = "u5v.u1o.4k5.s4x";
    public static String bD = "u5v.u1o.4k5.2yb";
    public static String bE = "u5v.u1o.4k5.h3i";
    public static final String bF = "u5v.u1o.4k5.g2i";
    public static final String bG = "u5v.u1o.4k5.9yf";
    public static final String bH = "u5v.u1o.4k5.ax0";
    public static final String bI = "u5v.u1o.4k5.xs8";
    public static final String bJ = "u5v.u1o.4k5.em4";
    public static final String bK = "u5v.u1o.4k5.69e";
    public static final String bL = "u5v.u1o.4k5.qf1";
    public static final String bM = "plg.gn9.m4l.f5v";
    public static final String bN = "plg.gn9.dr4.dsi";
    public static final String bO = "plg.gn9.c4r.h4u";
    public static final String bP = "plg.gn9.2dn.bbr";
    public static final String bQ = "plg.gn9.tnk.omz";
    public static final String bR = "plg.gn9.i4v.l1t";
    public static final String bS = "plg.gn9.qc4.tdm";
    public static final String bT = "plg.gn9.t3o.e0c";
    public static final String bU = "u5v.u1o.4k5.1m3";
    public static final String bV = "type";
    public static final String bW = "type";
    public static final String bX = "time";
    public static final String bY = "num";
    public static long bZ = -1;
    public static String ba = "8fq.a57.2w9.wp1";
    public static String bb = "8fq.a57.2w9.eg1";
    public static String bc = "8fq.a57.2w9.73w";
    public static String bd = "8fq.a57.2w9.8dv";
    public static String be = "8fq.a57.2w9.8z4";
    public static String bf = "8fq.a57.2w9.5f6";
    public static String bg = "8fq.a57.2w9.km7";
    public static String bh = "8fq.a57.2w9.2va";
    public static String bi = "8fq.a57.2w9.2e6";
    public static String bj = "8fq.a57.2w9.56k";
    public static String bk = "8fq.a57.2w9.8fy";
    public static String bl = "8fq.a57.2w9.6fg";
    public static String bm = "8fq.a57.2w9.ng4";
    public static String bn = "8fq.a57.2w9.w2f";
    public static String bo = "8fq.a57.2w9.t83";
    public static String bp = "8fq.a57.2w9.ce1";
    public static String bq = "8fq.a57.2w9.w8e";
    public static String br = "8fq.a57.2w9.6n1";
    public static String bs = "8fq.a57.2w9.i95";
    public static String bt = "8fq.a57.2w9.72z";
    public static String bu = "8fq.a57.2w9.4de";
    public static String bv = "8fq.a57.2w9.jd1";
    public static String bw = "8fq.a57.2w9.zs5";
    public static String bx = "8fq.a57.2w9.j8q";
    public static String by = "u5v.u1o.4k5.z5l";
    public static String bz = "info";
    public static String c = "plg.tbm.21l.xrl";
    public static long ca = -1;
    public static long cb = -1;
    public static long cc = -1;
    public static long cd = -1;
    public static long ce = -1;
    public static long cf = -1;
    public static long cg = -1;
    public static volatile boolean ch = false;
    private static final String ci = "plg.r02.yia.ecq";
    private static long cj = 0;
    private static long ck = 0;
    private static long cl = 0;
    private static long cm = 0;

    /* renamed from: cn  reason: collision with root package name */
    private static long f7859cn = 0;
    private static long co = 0;
    private static final String cp = "click";
    private static final String cq = "result";
    private static JSONObject cr = null;
    private static String cs = "";
    public static String d = "plg.i1e.mnd.ve3";
    public static String e = "plg.tbm.3e3.muq";
    public static String f = "plg.5hi.r1t.9jq";
    public static String g = "plg.tbm.oto.ktr";
    public static String h = "plg.tbm.imb.quc";
    public static String i = "plg.tbm.sao.mfs";
    public static String j = "plg.e56.7ic.8c4";
    public static String k = "plg.set.of2.7bn";
    public static String l = "plg.5hi.hsx.2q7";
    public static String m = "plg.5hi.7ya.e5q";
    public static String n = "plg.set.fiv.m2r";
    public static String o = "plg.set.j2m.zzr";
    public static String p = "plg.set.r4u.ldt";
    public static String q = "plg.set.x7b.r2x";
    public static String r = "plg.set.e59.ma7";
    public static String s = "plg.set.3gh.d5v";
    public static String t = "plg.set.x7b.qe6";
    public static String u = "plg.set.v80.mas";
    public static String v = "plg.set.o3k.g2g";
    public static String w = "plg.set.o3k.g8w";
    public static String x = "plg.set.vu2.o8y";
    public static String y = "plg.set.piu.mhk";
    public static String z = "plg.set.pec.err";

    public static void a() {
        cj = System.currentTimeMillis();
    }

    public static void b() {
        if (cj > 0) {
            a(by, "time", (Object) Integer.valueOf((int) ((System.currentTimeMillis() - cj) / 1000)));
            cj = 0;
        }
    }

    public static void c() {
        ck = System.currentTimeMillis();
    }

    public static void d() {
        if (ck > 0) {
            a(bA, "time", (Object) Integer.valueOf((int) ((System.currentTimeMillis() - ck) / 1000)));
            ck = 0;
        }
    }

    public static void e() {
        cl = System.currentTimeMillis();
    }

    public static void f() {
        if (cl > 0) {
            a(bB, "time", (Object) Integer.valueOf((int) ((System.currentTimeMillis() - cl) / 1000)));
            cl = 0;
        }
    }

    public static void g() {
        cm = System.currentTimeMillis();
    }

    public static void h() {
        if (cm > 0) {
            a(bC, "time", (Object) Integer.valueOf((int) ((System.currentTimeMillis() - cm) / 1000)));
            cm = 0;
        }
    }

    public static void i() {
        f7859cn = System.currentTimeMillis();
    }

    public static void j() {
        if (f7859cn > 0) {
            a(bD, "time", (Object) Integer.valueOf((int) ((System.currentTimeMillis() - f7859cn) / 1000)));
            f7859cn = 0;
        }
    }

    public static void k() {
        co = System.currentTimeMillis();
    }

    public static void l() {
        if (co > 0) {
            a(bE, "time", (Object) Integer.valueOf((int) ((System.currentTimeMillis() - co) / 1000)));
            co = 0;
        }
    }

    public static void a(String str, String str2) {
        if (TextUtils.isEmpty(str2) || (!str2.equals("chuangmi.camera.ipc009") && !str2.equals("chuangmi.camera.ipc019") && !str2.equals(DeviceConstant.CHUANGMI_CAMERA_021))) {
            cs = "plugin." + str2 + "." + str;
        } else {
            cs = "plugin.853.0";
        }
        try {
            cr = new JSONObject();
            cr.put("did", str);
            cr.put("version", 0);
            cr.put("model", str2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        ch = XmPluginHostApi.instance().isUsrExpPlanEnabled(str);
    }

    public static void m() {
        cr = null;
    }

    public static void a(String str) {
        if (cr != null && ch) {
            PluginStatReporter.a(cs, "click", str, "", cr);
        }
    }

    public static void a(String str, String str2, Object obj) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(str2, obj);
            if (cr != null && ch) {
                PluginStatReporter.a(cs, "result", str, jSONObject.toString(), cr);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public static void n() {
        a(X);
        bZ = System.currentTimeMillis();
    }

    public static void o() {
        a(Z);
        ca = System.currentTimeMillis();
    }

    public static void p() {
        a(af);
        cd = System.currentTimeMillis();
    }

    public static void q() {
        if (bZ > 0) {
            a(Y, "time", (Object) Long.valueOf(System.currentTimeMillis() - bZ));
            bZ = -1;
        } else if (ca > 0) {
            a(aa, "time", (Object) Long.valueOf(System.currentTimeMillis() - ca));
            ca = -1;
        } else if (cd > 0) {
            a(ag, "time", (Object) Long.valueOf(System.currentTimeMillis() - cd));
            cd = -1;
        }
    }

    public static void r() {
        a(ab);
        cb = System.currentTimeMillis();
    }

    public static void s() {
        a(ac, "time", (Object) Long.valueOf(System.currentTimeMillis() - cb));
        cb = -1;
    }

    public static void t() {
        a(ad);
        cc = System.currentTimeMillis();
    }

    public static void u() {
        a(ae, "time", (Object) Long.valueOf(System.currentTimeMillis() - cc));
        cc = -1;
    }

    public static void v() {
        a(e);
        ce = System.currentTimeMillis();
    }

    public static void w() {
        a(ah, "time", (Object) Long.valueOf(System.currentTimeMillis() - ce));
        ce = -1;
    }

    public static void x() {
        a(i);
        cf = System.currentTimeMillis();
    }

    public static void y() {
        a(ai, "time", (Object) Long.valueOf(System.currentTimeMillis() - cf));
        cf = -1;
    }

    public static void a(int i2) {
        a(aj, "type", (Object) Integer.valueOf(i2));
        cg = System.currentTimeMillis();
    }

    public static void z() {
        a(ak, "time", (Object) Long.valueOf(System.currentTimeMillis() - cg));
        cg = -1;
    }

    public static void a(int i2, int i3, long j2) {
        a(i2, i3, j2, "");
    }

    public static void a(int i2, int i3, long j2, String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("connect_status", i2);
            jSONObject.put("connect_type", str);
            jSONObject.put("retry", i3);
            jSONObject.put("time", j2);
            jSONObject.put("s1", TuTkClient.mConnect_Iotc);
            jSONObject.put("s2", TuTkClient.mConnect_Client);
            jSONObject.put("s3", TuTkClient.mConnect_Start);
            jSONObject.put("s4", TuTkClient.mConnect_Public_Key);
            TuTkClient.mConnect_Iotc = 0;
            TuTkClient.mConnect_Client = 0;
            TuTkClient.mConnect_Start = 0;
            TuTkClient.mConnect_Public_Key = 0;
            jSONObject.put("timestamp", System.currentTimeMillis());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        if (cr != null && ch) {
            PluginStatReporter.a(cs, "result", ci, jSONObject.toString(), cr);
        }
        if (i3 > 0 && i2 != -1) {
            JSONObject jSONObject2 = new JSONObject();
            int i4 = 1;
            if (i2 != 1) {
                i4 = 0;
            }
            try {
                jSONObject2.put("result", i4);
                jSONObject2.put("timestamp", System.currentTimeMillis());
                if (cr != null && ch) {
                    PluginStatReporter.a(cs, "result", W, jSONObject2.toString(), cr);
                }
            } catch (JSONException e3) {
                e3.printStackTrace();
            }
        }
    }
}
