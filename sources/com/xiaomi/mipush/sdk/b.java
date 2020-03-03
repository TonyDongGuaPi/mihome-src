package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.brentvatne.react.ReactVideoViewManager;
import com.miui.tsmclient.net.TSMAuthContants;
import com.xiaomi.push.g;
import com.xiaomi.push.i;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class b {
    private static volatile b b;

    /* renamed from: a  reason: collision with root package name */
    String f11544a;
    private Context c;
    private a d;
    private Map<String, a> e;

    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public String f11545a;
        public String b;
        public String c;
        public String d;
        public String e;
        public String f;
        public String g;
        public String h;
        public boolean i = true;
        public boolean j = false;
        public int k = 1;
        private Context l;

        public a(Context context) {
            this.l = context;
        }

        public static a a(Context context, String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                a aVar = new a(context);
                aVar.f11545a = jSONObject.getString("appId");
                aVar.b = jSONObject.getString("appToken");
                aVar.c = jSONObject.getString("regId");
                aVar.d = jSONObject.getString("regSec");
                aVar.f = jSONObject.getString("devId");
                aVar.e = jSONObject.getString("vName");
                aVar.i = jSONObject.getBoolean(TSMAuthContants.PARAM_VALID);
                aVar.j = jSONObject.getBoolean(ReactVideoViewManager.PROP_PAUSED);
                aVar.k = jSONObject.getInt("envType");
                aVar.g = jSONObject.getString("regResource");
                return aVar;
            } catch (Throwable th) {
                com.xiaomi.channel.commonutils.logger.b.a(th);
                return null;
            }
        }

        public static String a(a aVar) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("appId", aVar.f11545a);
                jSONObject.put("appToken", aVar.b);
                jSONObject.put("regId", aVar.c);
                jSONObject.put("regSec", aVar.d);
                jSONObject.put("devId", aVar.f);
                jSONObject.put("vName", aVar.e);
                jSONObject.put(TSMAuthContants.PARAM_VALID, aVar.i);
                jSONObject.put(ReactVideoViewManager.PROP_PAUSED, aVar.j);
                jSONObject.put("envType", aVar.k);
                jSONObject.put("regResource", aVar.g);
                return jSONObject.toString();
            } catch (Throwable th) {
                com.xiaomi.channel.commonutils.logger.b.a(th);
                return null;
            }
        }

        private String d() {
            return g.a(this.l, this.l.getPackageName());
        }

        public void a(int i2) {
            this.k = i2;
        }

        public void a(String str, String str2) {
            this.c = str;
            this.d = str2;
            this.f = i.l(this.l);
            this.e = d();
            this.i = true;
        }

        public void a(String str, String str2, String str3) {
            this.f11545a = str;
            this.b = str2;
            this.g = str3;
            SharedPreferences.Editor edit = b.b(this.l).edit();
            edit.putString("appId", this.f11545a);
            edit.putString("appToken", str2);
            edit.putString("regResource", str3);
            edit.commit();
        }

        public void a(boolean z) {
            this.j = z;
        }

        public boolean a() {
            return b(this.f11545a, this.b);
        }

        public void b() {
            b.b(this.l).edit().clear().commit();
            this.f11545a = null;
            this.b = null;
            this.c = null;
            this.d = null;
            this.f = null;
            this.e = null;
            this.i = false;
            this.j = false;
            this.h = null;
            this.k = 1;
        }

        public void b(String str, String str2, String str3) {
            this.c = str;
            this.d = str2;
            this.f = i.l(this.l);
            this.e = d();
            this.i = true;
            this.h = str3;
            SharedPreferences.Editor edit = b.b(this.l).edit();
            edit.putString("regId", str);
            edit.putString("regSec", str2);
            edit.putString("devId", this.f);
            edit.putString("vName", d());
            edit.putBoolean(TSMAuthContants.PARAM_VALID, true);
            edit.putString("appRegion", str3);
            edit.commit();
        }

        public boolean b(String str, String str2) {
            return TextUtils.equals(this.f11545a, str) && TextUtils.equals(this.b, str2) && !TextUtils.isEmpty(this.c) && !TextUtils.isEmpty(this.d) && (TextUtils.equals(this.f, i.l(this.l)) || TextUtils.equals(this.f, i.k(this.l)));
        }

        public void c() {
            this.i = false;
            b.b(this.l).edit().putBoolean(TSMAuthContants.PARAM_VALID, this.i).commit();
        }

        public void c(String str, String str2, String str3) {
            this.f11545a = str;
            this.b = str2;
            this.g = str3;
        }
    }

    private b(Context context) {
        this.c = context;
        o();
    }

    public static b a(Context context) {
        if (b == null) {
            synchronized (b.class) {
                if (b == null) {
                    b = new b(context);
                }
            }
        }
        return b;
    }

    public static SharedPreferences b(Context context) {
        return context.getSharedPreferences("mipush", 0);
    }

    private void o() {
        this.d = new a(this.c);
        this.e = new HashMap();
        SharedPreferences b2 = b(this.c);
        this.d.f11545a = b2.getString("appId", (String) null);
        this.d.b = b2.getString("appToken", (String) null);
        this.d.c = b2.getString("regId", (String) null);
        this.d.d = b2.getString("regSec", (String) null);
        this.d.f = b2.getString("devId", (String) null);
        if (!TextUtils.isEmpty(this.d.f) && i.a(this.d.f)) {
            this.d.f = i.l(this.c);
            b2.edit().putString("devId", this.d.f).commit();
        }
        this.d.e = b2.getString("vName", (String) null);
        this.d.i = b2.getBoolean(TSMAuthContants.PARAM_VALID, true);
        this.d.j = b2.getBoolean(ReactVideoViewManager.PROP_PAUSED, false);
        this.d.k = b2.getInt("envType", 1);
        this.d.g = b2.getString("regResource", (String) null);
        this.d.h = b2.getString("appRegion", (String) null);
    }

    public void a(int i) {
        this.d.a(i);
        b(this.c).edit().putInt("envType", i).commit();
    }

    public void a(String str) {
        SharedPreferences.Editor edit = b(this.c).edit();
        edit.putString("vName", str);
        edit.commit();
        this.d.e = str;
    }

    public void a(String str, a aVar) {
        this.e.put(str, aVar);
        String a2 = a.a(aVar);
        b(this.c).edit().putString("hybrid_app_info_" + str, a2).commit();
    }

    public void a(String str, String str2, String str3) {
        this.d.a(str, str2, str3);
    }

    public void a(boolean z) {
        this.d.a(z);
        b(this.c).edit().putBoolean(ReactVideoViewManager.PROP_PAUSED, z).commit();
    }

    public boolean a() {
        return !TextUtils.equals(g.a(this.c, this.c.getPackageName()), this.d.e);
    }

    public boolean a(String str, String str2) {
        return this.d.b(str, str2);
    }

    public a b(String str) {
        if (this.e.containsKey(str)) {
            return this.e.get(str);
        }
        String str2 = "hybrid_app_info_" + str;
        SharedPreferences b2 = b(this.c);
        if (!b2.contains(str2)) {
            return null;
        }
        a a2 = a.a(this.c, b2.getString(str2, ""));
        this.e.put(str2, a2);
        return a2;
    }

    public void b(String str, String str2, String str3) {
        this.d.b(str, str2, str3);
    }

    public boolean b() {
        if (this.d.a()) {
            return true;
        }
        com.xiaomi.channel.commonutils.logger.b.a("Don't send message before initialization succeeded!");
        return false;
    }

    public String c() {
        return this.d.f11545a;
    }

    public void c(String str) {
        this.e.remove(str);
        b(this.c).edit().remove("hybrid_app_info_" + str).commit();
    }

    public boolean c(String str, String str2, String str3) {
        a b2 = b(str3);
        return b2 != null && TextUtils.equals(str, b2.f11545a) && TextUtils.equals(str2, b2.b);
    }

    public String d() {
        return this.d.b;
    }

    public String e() {
        return this.d.c;
    }

    public String f() {
        return this.d.d;
    }

    public String g() {
        return this.d.g;
    }

    public String h() {
        return this.d.h;
    }

    public void i() {
        this.d.b();
    }

    public boolean j() {
        return this.d.a();
    }

    public void k() {
        this.d.c();
    }

    public boolean l() {
        return this.d.j;
    }

    public int m() {
        return this.d.k;
    }

    public boolean n() {
        return !this.d.i;
    }
}
