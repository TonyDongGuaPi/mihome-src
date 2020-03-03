package com.ximalaya.ting.android.opensdk.model.advertis;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Base64;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.taobao.weex.el.parse.Operators;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Advertis implements Parcelable {
    public static final int A = 11;
    public static final int B = 0;
    public static final int C = 1;
    public static final Parcelable.Creator<Advertis> CREATOR = new Parcelable.Creator<Advertis>() {
        /* renamed from: a */
        public Advertis[] newArray(int i) {
            return new Advertis[i];
        }

        /* renamed from: a */
        public Advertis createFromParcel(Parcel parcel) {
            Advertis advertis = new Advertis();
            advertis.a(parcel);
            return advertis;
        }
    };
    public static final int D = 9;
    public static final int E = 1;
    public static final int F = 2;
    public static final int G = 5;
    public static final int H = 6;
    public static final int I = 9;
    public static final int J = 10;
    public static final int K = 8;
    public static final int L = 12;
    public static final int M = 13;
    public static final int N = 14;
    public static final int O = 15;
    public static final int P = 16;
    public static final int Q = 17;
    public static final int R = 18;
    public static final int S = 19;
    public static final String T = "duringPlay";
    public static final String U = "isDisplayedInScreen";
    public static final String V = "playLeft";
    public static final String W = "playRight";
    public static final String X = "playDown";
    public static final String Y = "textArea";
    public static final Type Z = new TypeToken<Advertis>() {
    }.getType();

    /* renamed from: a  reason: collision with root package name */
    public static final int f2009a = 0;
    public static Set<String> aa = new HashSet();
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 0;
    public static final int e = 1;
    public static final int f = 2;
    public static final int g = 0;
    public static final int h = 1;
    public static final int i = 2;
    public static final int j = 3;
    public static final int k = 4;
    public static final int l = 5;
    public static final int m = 6;
    public static final int n = 7;
    public static final int o = 8;
    public static final int p = 0;
    public static final int q = 1;
    public static final int r = 2;
    public static final int s = 3;
    public static final int t = 4;
    public static final int u = 5;
    public static final int v = 6;
    public static final int w = 7;
    public static final int x = 8;
    public static final int y = 9;
    public static final int z = 10;
    private int aA;
    private List<String> aB;
    private List<String> aC;
    private String aD;
    private String aE;
    private String aF;
    private long aG;
    private boolean aH;
    private long aI;
    private String aJ;
    private boolean aK;
    private int aL;
    private List<String> aM;
    private int aN;
    private int aO;
    private String aP;
    private int aQ;
    private int aR;
    private int aS;
    private String aT;
    private long aU;
    private long aV;
    private String aW;
    private List<String> aX;
    private List<String> aY;
    private List<String> aZ;
    @SerializedName(alternate = {"adId"}, value = "adid")
    private int ab;
    private String ac;
    private int ad;
    @SerializedName("link")
    private String ae;
    @SerializedName(alternate = {"cover"}, value = "image")
    private String af;
    @SerializedName("logo")
    private String ag;
    private String ah;
    private String ai;
    private int aj = 100;
    private int ak;
    private int al;
    private int am;
    private int an;
    private String ao;
    private String ap;
    private long aq;
    private long ar;
    private long as;
    private boolean at = true;
    @SerializedName(alternate = {"shareFlag"}, value = "isShareFlag")
    private boolean au;
    private AdShareDataForOpenSDK av;
    private boolean aw;
    @SerializedName(alternate = {"adType"}, value = "adtype")
    private int ax;
    private boolean ay;
    private String az;
    private List<String> ba;
    private List<String> bb;
    private String bc;
    private String bd;
    private String be;
    private boolean bf;
    private String bg;
    private Map<String, String> bh;
    private int bi;
    private boolean bj = true;
    private String bk;
    private String bl;
    private int bm;
    private String bn;
    private boolean bo;
    private String bp;
    private boolean bq;

    public int describeContents() {
        return 0;
    }

    public String a() {
        if (this.aE == null) {
            return "";
        }
        return this.aE;
    }

    public void a(String str) {
        this.aE = str;
    }

    public String b() {
        if (this.aF == null) {
            return "";
        }
        return this.aF;
    }

    public void b(String str) {
        this.aF = str;
    }

    public int c() {
        return this.aO;
    }

    public void a(int i2) {
        this.aO = i2;
    }

    public boolean d() {
        return this.bq;
    }

    public void a(boolean z2) {
        this.bq = z2;
    }

    public boolean e() {
        return this.at;
    }

    public void b(boolean z2) {
        this.at = z2;
    }

    public int f() {
        return this.ab;
    }

    public void b(int i2) {
        this.ab = i2;
    }

    public String g() {
        return this.ac;
    }

    public void c(String str) {
        this.ac = str;
    }

    public int h() {
        return this.ad;
    }

    public void c(int i2) {
        this.ad = i2;
    }

    public String i() {
        return this.ae;
    }

    public void d(String str) {
        this.ae = str;
    }

    public String j() {
        return w(this.af);
    }

    public void e(String str) {
        this.af = str;
    }

    public String k() {
        return w(this.ag);
    }

    public void f(String str) {
        this.ag = str;
    }

    public String l() {
        return w(this.ah);
    }

    public void g(String str) {
        this.ah = str;
    }

    public String m() {
        return this.ai;
    }

    public void h(String str) {
        this.ai = str;
    }

    public boolean n() {
        return this.at;
    }

    public void c(boolean z2) {
        this.at = z2;
    }

    public String o() {
        return this.aP;
    }

    public void i(String str) {
        this.aP = str;
    }

    public int p() {
        return this.aQ;
    }

    public void d(int i2) {
        this.aQ = i2;
    }

    public int q() {
        return this.aR;
    }

    public void e(int i2) {
        this.aR = i2;
    }

    public int r() {
        return this.aS;
    }

    public void f(int i2) {
        this.aS = i2;
    }

    public String s() {
        return this.aT;
    }

    public void j(String str) {
        this.aT = str;
    }

    public long t() {
        return this.aU;
    }

    public void a(long j2) {
        this.aU = j2;
    }

    public long u() {
        return this.aV;
    }

    public void b(long j2) {
        this.aV = j2;
    }

    public String v() {
        return this.aW;
    }

    public void k(String str) {
        this.aW = str;
    }

    public List<String> w() {
        return this.aX;
    }

    public void a(List<String> list) {
        this.aX = list;
    }

    public List<String> x() {
        return this.aY;
    }

    public void b(List<String> list) {
        this.aY = list;
    }

    public List<String> y() {
        return this.aZ;
    }

    public void c(List<String> list) {
        this.aZ = list;
    }

    public List<String> z() {
        return this.ba;
    }

    public void d(List<String> list) {
        this.ba = list;
    }

    public List<String> A() {
        return this.bb;
    }

    public void e(List<String> list) {
        this.bb = list;
    }

    public String B() {
        return w(this.bc);
    }

    public void l(String str) {
        this.bc = str;
    }

    public String C() {
        return w(this.bd);
    }

    public void m(String str) {
        this.bd = str;
    }

    public String D() {
        return w(this.be);
    }

    public void n(String str) {
        this.be = str;
    }

    public boolean E() {
        return this.bf;
    }

    public void d(boolean z2) {
        this.bf = z2;
    }

    public String F() {
        return this.bg;
    }

    public void o(String str) {
        this.bg = str;
    }

    public Map<String, String> G() {
        return this.bh;
    }

    public void a(Map<String, String> map) {
        this.bh = map;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.ab);
        parcel.writeString(this.ac);
        parcel.writeInt(this.ad);
        parcel.writeString(this.ae);
        parcel.writeString(this.af);
        parcel.writeString(this.ag);
        parcel.writeString(this.ah);
        parcel.writeString(this.ai);
        parcel.writeInt(this.ak);
        parcel.writeInt(this.al);
        parcel.writeInt(this.am);
        parcel.writeInt(this.an);
        parcel.writeString(this.ao);
        parcel.writeString(this.ap);
        parcel.writeLong(this.aq);
        parcel.writeParcelable(this.av, i2);
        parcel.writeInt(this.au ? 1 : 0);
        parcel.writeInt(this.aw ? 1 : 0);
        parcel.writeInt(this.ax);
        parcel.writeInt(this.ay ? 1 : 0);
        parcel.writeString(this.az);
        parcel.writeStringList(this.aC);
        parcel.writeStringList(this.aB);
        parcel.writeString(this.aE);
        parcel.writeString(this.aF);
        parcel.writeLong(this.aG);
        parcel.writeInt(this.aH ? 1 : 0);
        parcel.writeLong(this.aI);
        parcel.writeString(this.aJ);
        parcel.writeString(this.aP);
        parcel.writeInt(this.aK ? 1 : 0);
        parcel.writeStringList(this.ba);
        parcel.writeStringList(this.bb);
        parcel.writeLong(this.ar);
        parcel.writeLong(this.as);
        parcel.writeString(this.bk);
        parcel.writeString(this.bl);
        parcel.writeInt(this.bm);
        parcel.writeInt(this.aL);
        parcel.writeStringList(this.aM);
        parcel.writeString(this.bn);
        parcel.writeInt(this.bo ? 1 : 0);
        parcel.writeString(this.bp);
    }

    public void a(Parcel parcel) {
        b(parcel.readInt());
        c(parcel.readString());
        c(parcel.readInt());
        d(parcel.readString());
        e(parcel.readString());
        f(parcel.readString());
        g(parcel.readString());
        h(parcel.readString());
        h(parcel.readInt());
        i(parcel.readInt());
        j(parcel.readInt());
        k(parcel.readInt());
        p(parcel.readString());
        q(parcel.readString());
        c(parcel.readLong());
        a((AdShareDataForOpenSDK) parcel.readParcelable(AdShareDataForOpenSDK.class.getClassLoader()));
        boolean z2 = false;
        e(parcel.readInt() == 1);
        f(parcel.readInt() == 1);
        l(parcel.readInt());
        g(parcel.readInt() == 1);
        r(parcel.readString());
        ArrayList arrayList = new ArrayList();
        parcel.readStringList(arrayList);
        g((List<String>) arrayList);
        ArrayList arrayList2 = new ArrayList();
        parcel.readStringList(arrayList2);
        f((List<String>) arrayList2);
        a(parcel.readString());
        b(parcel.readString());
        d(parcel.readLong());
        h(parcel.readInt() == 1);
        e(parcel.readLong());
        t(parcel.readString());
        i(parcel.readString());
        i(parcel.readInt() == 1);
        ArrayList arrayList3 = new ArrayList();
        parcel.readStringList(arrayList3);
        d((List<String>) arrayList3);
        ArrayList arrayList4 = new ArrayList();
        parcel.readStringList(arrayList4);
        e((List<String>) arrayList4);
        f(parcel.readLong());
        g(parcel.readLong());
        u(parcel.readString());
        v(parcel.readString());
        o(parcel.readInt());
        p(parcel.readInt());
        ArrayList arrayList5 = new ArrayList();
        parcel.readStringList(arrayList5);
        h((List<String>) arrayList5);
        x(parcel.readString());
        if (parcel.readInt() == 1) {
            z2 = true;
        }
        k(z2);
        y(parcel.readString());
    }

    public String toString() {
        return "Advertis [adid=" + this.ab + ", name=" + this.ac + ", clickType=" + this.ad + ", linkUrl=" + this.ae + ", imageUrl=" + this.af + ", soundType=" + this.an + ", logoUrl=" + this.ag + ", soundUrl=" + this.ah + ", thirdStatUrl=" + this.ai + Operators.ARRAY_END_STR;
    }

    public int H() {
        return this.aj;
    }

    public void g(int i2) {
        this.aj = i2;
    }

    public int I() {
        return this.ak;
    }

    public void h(int i2) {
        this.ak = i2;
    }

    public int J() {
        return this.al;
    }

    public void i(int i2) {
        this.al = i2;
    }

    public int K() {
        return this.am;
    }

    public void j(int i2) {
        this.am = i2;
    }

    public int L() {
        return this.an;
    }

    public void k(int i2) {
        this.an = i2;
    }

    public String M() {
        return this.ao;
    }

    public void p(String str) {
        this.ao = str;
    }

    public String N() {
        return this.ap;
    }

    public void q(String str) {
        this.ap = str;
    }

    public long O() {
        return this.aq;
    }

    public void c(long j2) {
        this.aq = j2;
    }

    public AdShareDataForOpenSDK P() {
        return this.av;
    }

    public void a(AdShareDataForOpenSDK adShareDataForOpenSDK) {
        this.av = adShareDataForOpenSDK;
    }

    public boolean Q() {
        return this.au;
    }

    public void e(boolean z2) {
        this.au = z2;
    }

    public boolean R() {
        return this.aw;
    }

    public void f(boolean z2) {
        this.aw = z2;
    }

    public int S() {
        return this.ax;
    }

    public void l(int i2) {
        this.ax = i2;
    }

    public boolean T() {
        return this.ay;
    }

    public void g(boolean z2) {
        this.ay = z2;
    }

    public Advertis() {
    }

    public Advertis(Advertis advertis) {
        this.ab = advertis.ab;
        this.ac = advertis.ac;
        this.ad = advertis.ad;
        this.ae = advertis.ae;
        this.af = advertis.af;
        this.ag = advertis.ag;
        this.ah = advertis.ah;
        this.ai = advertis.ai;
        this.aj = advertis.aj;
        this.ak = advertis.ak;
        this.al = advertis.al;
        this.am = advertis.am;
        this.an = advertis.an;
        this.ao = advertis.ao;
        this.ap = advertis.ap;
        this.aq = advertis.aq;
        this.at = advertis.at;
        this.au = advertis.au;
        this.av = advertis.av;
        this.aw = advertis.aw;
        this.ax = advertis.ax;
        this.ay = advertis.ay;
        this.aO = advertis.aO;
        this.bq = advertis.bq;
        this.aC = advertis.aC;
        this.aB = advertis.aB;
        this.az = advertis.az;
        this.aE = advertis.aE;
        this.aF = advertis.aF;
        this.aI = advertis.aI;
        this.aP = advertis.aP;
        this.aK = advertis.aK;
        this.ar = advertis.ar;
        this.as = advertis.as;
        this.bk = advertis.bk;
        this.bl = advertis.bl;
        this.bm = advertis.bm;
        this.aL = advertis.aL;
        this.bo = advertis.bo;
        this.bn = advertis.bn;
        this.bp = advertis.bp;
    }

    public String U() {
        return this.az;
    }

    public void r(String str) {
        this.az = str;
    }

    public List<String> V() {
        return this.aB;
    }

    public void f(List<String> list) {
        this.aB = list;
    }

    public List<String> W() {
        return this.aC;
    }

    public void g(List<String> list) {
        this.aC = list;
    }

    public String X() {
        return this.aD;
    }

    public void s(String str) {
        this.aD = str;
    }

    public static List<Advertis> a(int i2, JSONObject jSONObject) {
        ArrayList arrayList = new ArrayList();
        if (i2 == 5) {
            return b(jSONObject);
        }
        return i2 == 6 ? a(jSONObject) : arrayList;
    }

    private static List<Advertis> a(JSONObject jSONObject) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONObject optJSONObject = jSONObject.optJSONObject(jSONObject.names().optString(0));
            String optString = optJSONObject.optString("imgurl", "");
            String optString2 = optJSONObject.optString("clickurl", "");
            String optString3 = optJSONObject.optString("displaytitle", "");
            String optString4 = optJSONObject.optString("displaytext", "");
            JSONArray optJSONArray = optJSONObject.optJSONArray("imgtracking");
            JSONArray optJSONArray2 = optJSONObject.optJSONArray("thclkurl");
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            if (optJSONArray != null) {
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    arrayList3.add(optJSONArray.optString(i2));
                }
            }
            if (optJSONArray2 != null) {
                for (int i3 = 0; i3 < optJSONArray2.length(); i3++) {
                    arrayList2.add(optJSONArray2.optString(i3));
                }
            }
            Advertis advertis = new Advertis();
            advertis.k(6);
            advertis.c(1);
            advertis.e(optString);
            advertis.i(optString4);
            advertis.r(optString2);
            advertis.c(optString3);
            advertis.a((List<String>) arrayList2);
            advertis.b((List<String>) arrayList3);
            arrayList.add(advertis);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return arrayList;
    }

    private static List<Advertis> b(JSONObject jSONObject) {
        ArrayList arrayList = new ArrayList();
        try {
            if (jSONObject.optInt("ret") == 0) {
                JSONArray optJSONArray = jSONObject.optJSONArray("data");
                Gson gson = new Gson();
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    int optInt = optJSONArray.optJSONObject(i2).optInt("adtype");
                    long optLong = optJSONArray.optJSONObject(i2).optLong("endAt");
                    int optInt2 = optJSONArray.optJSONObject(i2).optInt("loadingShowTime");
                    if (optInt == 5) {
                        a(arrayList, optJSONArray, i2, optInt, optLong, optInt2);
                    } else {
                        Advertis a2 = a(optJSONArray.getString(i2), gson);
                        if (a2 != null) {
                            arrayList.add(a2);
                        }
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return arrayList;
    }

    private static void a(List<Advertis> list, JSONArray jSONArray, int i2, int i3, long j2, int i4) throws JSONException {
        long j3 = (long) jSONArray.getJSONObject(i2).getInt("adId");
        JSONArray optJSONArray = jSONArray.optJSONObject(i2).optJSONObject("inmobiData").optJSONArray("ads");
        for (int i5 = 0; i5 < optJSONArray.length(); i5++) {
            String optString = optJSONArray.optJSONObject(i5).optString("pubContent", "");
            optJSONArray.optJSONObject(i5).optString("landingPage", "");
            JSONObject optJSONObject = optJSONArray.optJSONObject(i5).optJSONObject("eventTracking");
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            if (optJSONObject != null) {
                JSONArray optJSONArray2 = optJSONObject.optJSONObject("8").optJSONArray("urls");
                JSONArray optJSONArray3 = optJSONObject.optJSONObject("18").optJSONArray("urls");
                JSONArray optJSONArray4 = optJSONObject.optJSONObject("120").optJSONArray("urls");
                if (optJSONArray2 != null) {
                    for (int i6 = 0; i6 < optJSONArray2.length(); i6++) {
                        arrayList.add(optJSONArray2.optString(i6));
                    }
                }
                if (optJSONArray3 != null) {
                    for (int i7 = 0; i7 < optJSONArray3.length(); i7++) {
                        arrayList2.add(optJSONArray3.optString(i7));
                    }
                }
                if (optJSONArray4 != null) {
                    for (int i8 = 0; i8 < optJSONArray4.length(); i8++) {
                        arrayList3.add(optJSONArray4.optString(i8));
                    }
                }
            }
            JSONObject jSONObject = new JSONObject(new String(Base64.decode(optString, 0)));
            jSONObject.optJSONObject("icon").optString("url", "");
            String optString2 = jSONObject.optJSONObject("screenshots").optString("url", "");
            String optString3 = jSONObject.optString("description", "");
            String optString4 = jSONObject.optString("title", "");
            String optString5 = jSONObject.optString("landingURL", "");
            Advertis advertis = new Advertis();
            advertis.b((int) j3);
            advertis.l(i3);
            advertis.e(optString2);
            advertis.i(optString3);
            advertis.r(optString5);
            advertis.d(i4);
            advertis.c(optString4);
            advertis.b(j2);
            advertis.a((List<String>) arrayList);
            advertis.b((List<String>) arrayList2);
            advertis.c((List<String>) arrayList3);
            List<Advertis> list2 = list;
            list.add(advertis);
        }
    }

    private static Advertis a(String str, Gson gson) {
        if (str == null) {
            return null;
        }
        try {
            return (Advertis) gson.fromJson(str, Z);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public int Y() {
        return this.bi;
    }

    public void m(int i2) {
        this.bi = i2;
    }

    public int Z() {
        return this.aA;
    }

    public void n(int i2) {
        this.aA = i2;
    }

    public long aa() {
        return this.aG;
    }

    public void d(long j2) {
        this.aG = j2;
    }

    public boolean ab() {
        return this.aH;
    }

    public void h(boolean z2) {
        this.aH = z2;
    }

    public long ac() {
        return this.aI;
    }

    public void e(long j2) {
        this.aI = j2;
    }

    public String ad() {
        return this.aJ;
    }

    public void t(String str) {
        this.aJ = str;
    }

    public boolean ae() {
        return this.aK;
    }

    public void i(boolean z2) {
        this.aK = z2;
    }

    public boolean af() {
        return this.bj;
    }

    public void j(boolean z2) {
        this.bj = z2;
    }

    public long ag() {
        return this.ar;
    }

    public void f(long j2) {
        this.ar = j2;
    }

    public long ah() {
        return this.as;
    }

    public void g(long j2) {
        this.as = j2;
    }

    public String ai() {
        return this.bk;
    }

    public void u(String str) {
        this.bk = str;
    }

    public String aj() {
        return this.bl;
    }

    public void v(String str) {
        this.bl = str;
    }

    public int ak() {
        return this.bm;
    }

    public void o(int i2) {
        this.bm = i2;
    }

    public int al() {
        return this.aL;
    }

    public void p(int i2) {
        this.aL = i2;
    }

    public List<String> am() {
        return this.aM;
    }

    public void h(List<String> list) {
        this.aM = list;
    }

    public static String w(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        String host = Uri.parse(str).getHost();
        if (!"fdfs.xmcdn.com".equals(host) && !"fdfs.ximalaya.com".equals(host)) {
            aa.add(host);
        }
        return str;
    }

    public String an() {
        return this.bn;
    }

    public void x(String str) {
        this.bn = str;
    }

    public boolean ao() {
        return this.bo;
    }

    public void k(boolean z2) {
        this.bo = z2;
    }

    public String ap() {
        return this.bp;
    }

    public void y(String str) {
        this.bp = str;
    }

    public int aq() {
        return this.aN;
    }

    public void q(int i2) {
        this.aN = i2;
    }
}
