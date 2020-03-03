package com.xiaomi.smarthome.splashads.model;

import android.text.TextUtils;
import com.taobao.weex.common.Constants;
import com.xiaomi.market.sdk.Constants;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.smarthome.framework.log.LogUtil;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MiuiAdsInfo {

    /* renamed from: a  reason: collision with root package name */
    public static final int f22742a = 600000;
    private String A;
    private String B;
    private String C;
    private String D;
    private String E;
    private String F;
    private long G;
    private long H;
    private long I;
    private int J;
    private int K;
    private int L;
    private int M;
    private int N;
    private String O;
    private int P;
    private JSONObject Q;
    private String R;
    private String S;
    private String T;
    public long b;
    public String c;
    public String d;
    public String e;
    public List<ADAssetsInfo> f = new ArrayList();
    public String g;
    public int h;
    public int i;
    public String j;
    public String k;
    public String l;
    public String m;
    public long n;
    public long o;
    public long p;
    public List<String> q = new ArrayList();
    public List<String> r = new ArrayList();
    private List<String> s = new ArrayList();
    private String t;
    private List<String> u = new ArrayList();
    private String v;
    private String w;
    private String x;
    private String y;
    private String z;

    public static MiuiAdsInfo a(JSONObject jSONObject) {
        return new MiuiAdsInfo(jSONObject);
    }

    private MiuiAdsInfo(JSONObject jSONObject) {
        this.v = jSONObject.toString();
        c(jSONObject);
    }

    public String a() {
        return this.T;
    }

    public static List<MiuiAdsInfo> a(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (jSONArray == null || jSONArray.length() == 0) {
            return arrayList;
        }
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            arrayList.add(a(jSONArray.optJSONObject(i2)));
        }
        return arrayList;
    }

    public String b() {
        return this.S;
    }

    public String c() {
        return this.R;
    }

    private void c(JSONObject jSONObject) {
        try {
            this.b = jSONObject.optLong("id");
            this.c = jSONObject.optString("title");
            this.d = jSONObject.optString(MibiConstants.ee);
            this.e = jSONObject.optString("brand");
            JSONArray optJSONArray = jSONObject.optJSONArray("assets");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    this.f.add(new ADAssetsInfo().a(optJSONArray.optJSONObject(i2)));
                }
            }
            this.g = jSONObject.optString("adMark");
            this.h = jSONObject.optInt("adType");
            this.i = jSONObject.optInt("targetType");
            this.j = jSONObject.optString("deeplink");
            this.k = jSONObject.optString("landingPageUrl");
            this.l = jSONObject.optString("actionUrl");
            this.m = jSONObject.optString(MibiConstants.fU);
            this.w = jSONObject.optString("packageName");
            JSONObject optJSONObject = jSONObject.optJSONObject("adControl");
            if (optJSONObject != null) {
                this.n = optJSONObject.optLong("duration", -1);
                this.o = optJSONObject.optLong("startTimeInMills", -1);
                this.p = optJSONObject.optLong("endTimeInMills", -1);
            }
            this.q.clear();
            this.q.addAll(b(jSONObject.optJSONArray("viewMonitorUrls")));
            this.r.clear();
            this.r.addAll(b(jSONObject.optJSONArray("clickMonitorUrls")));
            this.A = jSONObject.optString("videoUrl");
            this.x = jSONObject.optString("adInfoPassback");
            this.E = jSONObject.optString("template");
            this.y = jSONObject.optString("source");
            this.z = jSONObject.optString("ex");
            this.H = (long) jSONObject.optInt(MibiConstants.eP);
            this.Q = jSONObject.optJSONObject("sdkAdDetail");
            this.u.clear();
            this.u.addAll(b(jSONObject.optJSONArray("downloadMonitorUrls")));
            this.s.clear();
            this.s.addAll(b(jSONObject.optJSONArray("imgUrls")));
            this.T = jSONObject.optString("dspShowName");
            this.R = jSONObject.optString("cuscontrl");
            this.S = jSONObject.optString("cuslandingpageUrl");
            this.k = jSONObject.optString("landingPageUrl");
            this.G = jSONObject.optLong(Constants.S);
            this.I = jSONObject.optLong("allDownloadNum");
            this.J = jSONObject.optInt("width");
            this.K = jSONObject.optInt("height");
            this.L = jSONObject.optInt("sequence");
            this.P = jSONObject.optInt("adStyle");
            this.O = jSONObject.optString("tagId");
            this.N = jSONObject.optInt("materialType");
        } catch (Exception e2) {
            LogUtil.a("Ad", e2.getMessage());
        }
    }

    private List<String> b(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (jSONArray != null) {
            int length = jSONArray.length();
            for (int i2 = 0; i2 < length; i2++) {
                String optString = jSONArray.optString(i2);
                if (!TextUtils.isEmpty(optString) && optString.startsWith("http")) {
                    arrayList.add(optString);
                }
            }
        }
        return arrayList;
    }

    public String d() {
        return this.j;
    }

    public String e() {
        return this.E;
    }

    public String f() {
        return this.w;
    }

    public void a(String str) {
        this.w = str;
    }

    public long g() {
        return this.G;
    }

    public long h() {
        return this.I;
    }

    public int i() {
        return this.J;
    }

    public int j() {
        return this.K;
    }

    public int k() {
        return this.L;
    }

    public List<String> l() {
        return this.s;
    }

    public String m() {
        return this.k;
    }

    public List<String> n() {
        return this.q;
    }

    public String o() {
        return this.t;
    }

    public void b(String str) {
        this.t = str;
    }

    public List<String> p() {
        return this.r;
    }

    public List<String> q() {
        return this.u;
    }

    public String r() {
        return this.c;
    }

    public String s() {
        return this.d;
    }

    public int t() {
        return this.i;
    }

    public int u() {
        return this.N;
    }

    public double v() {
        if (this.K == 0) {
            return 0.0d;
        }
        return (double) (this.J / this.K);
    }

    public boolean w() {
        return this.i == 1;
    }

    public boolean x() {
        return this.i == 6;
    }

    public boolean y() {
        return this.i == 2 || this.i == 6;
    }

    public boolean z() {
        if (this.Q != null) {
            return !this.Q.optBoolean("isPopUpDownload");
        }
        return true;
    }

    public boolean A() {
        if (this.Q != null) {
            return this.Q.optBoolean("isPopUpDownload");
        }
        return false;
    }

    public long B() {
        if (this.Q != null) {
            return this.Q.optLong("reqInterval", 0);
        }
        return 0;
    }

    public boolean C() {
        return this.P == 20;
    }

    public boolean D() {
        return this.P == 21;
    }

    public boolean E() {
        return this.P == 40;
    }

    public boolean F() {
        return this.P == 42;
    }

    public boolean G() {
        return this.N == 2;
    }

    public boolean H() {
        return this.N == 1;
    }

    public boolean I() {
        return this.N == 3;
    }

    public String J() {
        return this.z;
    }

    public String K() {
        return this.x;
    }

    public String L() {
        return this.D;
    }

    public String M() {
        return this.F;
    }

    public String N() {
        return this.l;
    }

    public String O() {
        return this.y;
    }

    public String P() {
        return this.A;
    }

    public String Q() {
        return this.B;
    }

    public void c(String str) {
        this.B = str;
    }

    public int R() {
        return this.P;
    }

    public String S() {
        return this.O;
    }

    public void a(int i2) {
        this.P = i2;
    }

    public long T() {
        return this.n;
    }

    public int U() {
        return this.M;
    }

    public long V() {
        if (this.H <= 0) {
            return 600000;
        }
        return this.H;
    }

    public String W() {
        return this.v;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|(1:8)|9|(1:13)|14|(1:16)|17|20) */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0062, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0063, code lost:
        r1.printStackTrace();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0013 */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0053 A[Catch:{ Exception -> 0x0062 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.json.JSONObject X() {
        /*
            r4 = this;
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            java.lang.String r1 = "id"
            long r2 = r4.b     // Catch:{ Exception -> 0x0013 }
            r0.put(r1, r2)     // Catch:{ Exception -> 0x0013 }
            java.lang.String r1 = "value"
            java.lang.String r2 = r4.v     // Catch:{ Exception -> 0x0013 }
            r0.put(r1, r2)     // Catch:{ Exception -> 0x0013 }
        L_0x0013:
            java.lang.String r1 = r4.B     // Catch:{ Exception -> 0x0062 }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Exception -> 0x0062 }
            if (r1 != 0) goto L_0x002f
            java.io.File r1 = new java.io.File     // Catch:{ Exception -> 0x0062 }
            java.lang.String r2 = r4.B     // Catch:{ Exception -> 0x0062 }
            r1.<init>(r2)     // Catch:{ Exception -> 0x0062 }
            boolean r1 = r1.exists()     // Catch:{ Exception -> 0x0062 }
            if (r1 == 0) goto L_0x002f
            java.lang.String r1 = "videoLocalPath"
            java.lang.String r2 = r4.B     // Catch:{ Exception -> 0x0062 }
            r0.put(r1, r2)     // Catch:{ Exception -> 0x0062 }
        L_0x002f:
            java.lang.String r1 = r4.C     // Catch:{ Exception -> 0x0062 }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Exception -> 0x0062 }
            if (r1 != 0) goto L_0x004b
            java.io.File r1 = new java.io.File     // Catch:{ Exception -> 0x0062 }
            java.lang.String r2 = r4.C     // Catch:{ Exception -> 0x0062 }
            r1.<init>(r2)     // Catch:{ Exception -> 0x0062 }
            boolean r1 = r1.exists()     // Catch:{ Exception -> 0x0062 }
            if (r1 == 0) goto L_0x004b
            java.lang.String r1 = "imgLocalPath"
            java.lang.String r2 = r4.C     // Catch:{ Exception -> 0x0062 }
            r0.put(r1, r2)     // Catch:{ Exception -> 0x0062 }
        L_0x004b:
            java.lang.String r1 = r4.t     // Catch:{ Exception -> 0x0062 }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Exception -> 0x0062 }
            if (r1 != 0) goto L_0x005a
            java.lang.String r1 = "localLandingPagePath"
            java.lang.String r2 = r4.t     // Catch:{ Exception -> 0x0062 }
            r0.put(r1, r2)     // Catch:{ Exception -> 0x0062 }
        L_0x005a:
            java.lang.String r1 = "adStyle"
            int r2 = r4.P     // Catch:{ Exception -> 0x0062 }
            r0.put(r1, r2)     // Catch:{ Exception -> 0x0062 }
            goto L_0x0066
        L_0x0062:
            r1 = move-exception
            r1.printStackTrace()
        L_0x0066:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.splashads.model.MiuiAdsInfo.X():org.json.JSONObject");
    }

    public MiuiAdsInfo b(JSONObject jSONObject) {
        try {
            this.B = jSONObject.optString("videoLocalPath");
            this.C = jSONObject.optString("imgLocalPath");
            this.t = jSONObject.optString("localLandingPagePath");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return this;
    }

    public String Y() {
        return X().toString();
    }

    public String toString() {
        return "Ad id:" + Z();
    }

    public long Z() {
        return this.b;
    }

    public void a(long j2) {
        this.b = j2;
    }

    public static String a(List<MiuiAdsInfo> list) {
        if (list == null || list.size() == 0) {
            return "";
        }
        try {
            JSONArray jSONArray = new JSONArray();
            for (int i2 = 0; i2 < list.size(); i2++) {
                jSONArray.put(new JSONObject(list.get(i2).W()));
            }
            return jSONArray.toString();
        } catch (JSONException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public class ADAssetsInfo {

        /* renamed from: a  reason: collision with root package name */
        public String f22743a;
        public String b;
        public int c;

        public ADAssetsInfo() {
        }

        public ADAssetsInfo a(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            this.f22743a = jSONObject.optString("url");
            this.c = jSONObject.optInt("materialType");
            this.b = jSONObject.optString(Constants.CodeCache.BANNER_DIGEST);
            return this;
        }
    }
}
