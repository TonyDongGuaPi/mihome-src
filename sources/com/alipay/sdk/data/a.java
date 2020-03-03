package com.alipay.sdk.data;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.sys.b;
import com.alipay.sdk.util.c;
import com.alipay.sdk.util.j;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class a {

    /* renamed from: a  reason: collision with root package name */
    public static final int f1104a = 3500;
    public static final String b = "https://h5.m.taobao.com/mlapp/olist.html";
    public static final int c = 10;
    public static final boolean d = true;
    public static final boolean e = true;
    public static final int f = 1000;
    public static final int g = 20000;
    public static final String h = "alipay_cashier_dynamic_config";
    public static final String i = "timeout";
    public static final String j = "st_sdk_config";
    public static final String k = "tbreturl";
    public static final String l = "launchAppSwitch";
    public static final String m = "configQueryInterval";
    public static final String n = "scheme_pay";
    public static final String o = "intercept_batch";
    private static a w;
    public boolean p = false;
    private int q = f1104a;
    private String r = b;
    private int s = 10;
    private boolean t = true;
    private boolean u = true;
    private List<C0029a> v = null;

    public int a() {
        if (this.q < 1000 || this.q > 20000) {
            c.b("", "DynamicConfig::getJumpTimeout(default) >3500");
            return f1104a;
        }
        c.b("", "DynamicConfig::getJumpTimeout >" + this.q);
        return this.q;
    }

    public boolean b() {
        return this.t;
    }

    public boolean c() {
        return this.u;
    }

    public String d() {
        return this.r;
    }

    public int e() {
        return this.s;
    }

    public List<C0029a> f() {
        return this.v;
    }

    public void a(boolean z) {
        this.p = z;
    }

    public static a g() {
        if (w == null) {
            w = new a();
            w.h();
        }
        return w;
    }

    private void h() {
        a(j.b(b.a().b(), h, (String) null));
    }

    private void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                this.q = jSONObject.optInt("timeout", f1104a);
                this.r = jSONObject.optString(k, b).trim();
                this.s = jSONObject.optInt(m, 10);
                this.v = C0029a.a(jSONObject.optJSONArray(l));
                this.t = jSONObject.optBoolean(n, true);
                this.u = jSONObject.optBoolean(o, true);
            } catch (Throwable th) {
                c.a(th);
            }
        }
    }

    /* access modifiers changed from: private */
    public void i() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("timeout", a());
            jSONObject.put(k, d());
            jSONObject.put(m, e());
            jSONObject.put(l, C0029a.a(f()));
            jSONObject.put(n, b());
            jSONObject.put(o, c());
            j.a(b.a().b(), h, jSONObject.toString());
        } catch (Exception e2) {
            c.a(e2);
        }
    }

    /* access modifiers changed from: private */
    public void b(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject optJSONObject = new JSONObject(str).optJSONObject(j);
                if (optJSONObject != null) {
                    this.q = optJSONObject.optInt("timeout", f1104a);
                    this.r = optJSONObject.optString(k, b).trim();
                    this.s = optJSONObject.optInt(m, 10);
                    this.v = C0029a.a(optJSONObject.optJSONArray(l));
                    this.t = optJSONObject.optBoolean(n, true);
                    this.u = optJSONObject.optBoolean(o, true);
                    return;
                }
                c.d("msp", "config is null");
            } catch (Throwable th) {
                c.a(th);
            }
        }
    }

    public void a(Context context) {
        new Thread(new b(this, context)).start();
    }

    /* renamed from: com.alipay.sdk.data.a$a  reason: collision with other inner class name */
    public static final class C0029a {

        /* renamed from: a  reason: collision with root package name */
        public final String f1105a;
        public final int b;
        public final String c;

        public C0029a(String str, int i, String str2) {
            this.f1105a = str;
            this.b = i;
            this.c = str2;
        }

        public static C0029a a(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            return new C0029a(jSONObject.optString("pn"), jSONObject.optInt("v", 0), jSONObject.optString("pk"));
        }

        public static List<C0029a> a(JSONArray jSONArray) {
            if (jSONArray == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                C0029a a2 = a(jSONArray.optJSONObject(i));
                if (a2 != null) {
                    arrayList.add(a2);
                }
            }
            return arrayList;
        }

        public static JSONObject a(C0029a aVar) {
            if (aVar == null) {
                return null;
            }
            try {
                return new JSONObject().put("pn", aVar.f1105a).put("v", aVar.b).put("pk", aVar.c);
            } catch (JSONException e) {
                c.a(e);
                return null;
            }
        }

        public static JSONArray a(List<C0029a> list) {
            if (list == null) {
                return null;
            }
            JSONArray jSONArray = new JSONArray();
            for (C0029a a2 : list) {
                jSONArray.put(a(a2));
            }
            return jSONArray;
        }

        public String toString() {
            return String.valueOf(a(this));
        }
    }
}
