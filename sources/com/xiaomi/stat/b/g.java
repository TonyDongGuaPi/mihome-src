package com.xiaomi.stat.b;

import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.text.TextUtils;
import com.mi.global.bbs.utils.ConnectionHelper;
import com.xiaomi.stat.ab;
import com.xiaomi.stat.ak;
import com.xiaomi.stat.b;
import com.xiaomi.stat.d.h;
import com.xiaomi.stat.d.k;
import com.xiaomi.stat.d.m;
import com.xiaomi.stat.d.r;
import java.util.HashMap;
import org.json.JSONObject;

public class g {

    /* renamed from: a  reason: collision with root package name */
    private static final String f23029a = "RDM";
    private static final Object b = new Object();
    private static final String c = "CN";
    private static final String d = "INTL";
    private static final String e = "IN";
    private static final String f = "data.mistat.xiaomi.com";
    private static final String g = "data.mistat.intl.xiaomi.com";
    private static final String h = "data.mistat.india.xiaomi.com";
    private static final String i = "region-url";
    private static final String j = "/map_domain";
    private static final String k = "region";
    private static HashMap<String, String> l = new HashMap<>();
    private static g r;
    private String m = c;
    private String n = f;
    private String o = null;
    private String p;
    private h q;

    static {
        l.put(c, f);
        l.put(d, g);
        l.put("IN", h);
    }

    private g() {
        a(ak.a());
    }

    public static g a() {
        if (r == null) {
            synchronized (b) {
                if (r == null) {
                    r = new g();
                }
            }
        }
        return r;
    }

    /* access modifiers changed from: protected */
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cannot clone instance of this class");
    }

    private static void e() {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            k.d(f23029a, "can not init in main thread!", (Throwable) null);
        }
    }

    public void a(Context context) {
        e();
        this.q = new h();
        this.p = context.getFilesDir().getPath();
        if (!b.e()) {
            this.m = c;
            this.n = f;
        } else {
            String g2 = m.g();
            k.b(f23029a, "[SystemRegion]:" + g2);
            String a2 = ab.a().a("region", (String) null);
            if (!TextUtils.isEmpty(g2)) {
                this.m = g2;
            }
            if (!TextUtils.isEmpty(a2)) {
                this.m = a2;
            }
            f();
        }
        k.b(f23029a, "[file-dir]:" + this.p + "\n[CurrentRegion]:" + this.m + "\n[domain]:" + this.n);
    }

    private void f() {
        l = this.q.a(l, (HashMap<String, String>) (HashMap) h.a(this.p.concat(j)));
        String str = l.get(this.m);
        if (!TextUtils.isEmpty(str)) {
            this.n = str;
        } else if (b.e()) {
            this.m = d;
            this.n = l.get(this.m);
        }
    }

    /* access modifiers changed from: package-private */
    public String b() {
        return b("get_all_config");
    }

    public String c() {
        return b("mistats/v3");
    }

    public String d() {
        return b("key_get");
    }

    private String b(String str) {
        String str2;
        int i2 = ak.a().getApplicationInfo().targetSdkVersion;
        if (b.e()) {
            str2 = "https://";
        } else {
            str2 = (Build.VERSION.SDK_INT < 28 || i2 < 28) ? ConnectionHelper.HTTP_PREFIX : "https://";
        }
        return str2.concat(this.n).concat("/").concat(str);
    }

    public void a(String str) {
        d(str);
        c(str);
    }

    public void a(boolean z) {
        if (z) {
            this.m = d;
            this.n = g;
            String str = TextUtils.isEmpty(this.o) ? this.m : this.o;
            if (!TextUtils.isEmpty(str)) {
                String str2 = l.get(str);
                if (!TextUtils.isEmpty(str2)) {
                    this.m = str;
                    this.n = str2;
                    return;
                }
                return;
            }
            return;
        }
        this.m = c;
        this.n = f;
    }

    private boolean c(String str) {
        boolean z;
        if (l.keySet().contains(str)) {
            this.m = str;
            this.n = l.get(this.m);
            z = true;
        } else {
            this.m = d;
            this.n = l.get(this.m);
            k.d(f23029a, "unknown region,set to unknown(singapore)'s domain");
            z = false;
        }
        ab.a().b("region", str);
        return z;
    }

    private void d(String str) {
        this.o = str;
    }

    public void a(JSONObject jSONObject) {
        HashMap<String, String> a2 = this.q.a(i, jSONObject);
        String str = TextUtils.isEmpty(this.o) ? this.m : this.o;
        if (a2 != null) {
            l = this.q.a(l, a2);
            if (!TextUtils.isEmpty(str)) {
                String str2 = l.get(str);
                if (!TextUtils.isEmpty(str2)) {
                    this.m = str;
                    this.n = str2;
                }
            } else if (b.e()) {
                this.m = d;
                this.n = l.get(this.m);
            }
            h.a(l, this.p.concat(j));
        }
    }

    public boolean b(Context context) {
        return r.b(ab.a().a("key_update_time", 0)) || !b.e();
    }
}
