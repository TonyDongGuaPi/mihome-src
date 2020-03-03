package cn.fraudmetrix.octopus.aspirit.c;

import android.text.TextUtils;
import cn.fraudmetrix.octopus.aspirit.bean.d;
import cn.fraudmetrix.octopus.aspirit.c.b;
import cn.fraudmetrix.octopus.aspirit.g.c;
import cn.fraudmetrix.octopus.aspirit.g.e;
import cn.fraudmetrix.octopus.aspirit.g.f;
import cn.fraudmetrix.octopus.aspirit.g.g;
import cn.fraudmetrix.octopus.aspirit.main.OctopusManager;
import cn.fraudmetrix.octopus.aspirit.utils.OctopusConstants;
import cn.fraudmetrix.octopus.aspirit.utils.h;
import cn.fraudmetrix.octopus.aspirit.utils.j;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class a implements b {

    /* renamed from: a  reason: collision with root package name */
    private List<e> f626a = new ArrayList();
    private Map<String, e> b;
    /* access modifiers changed from: private */
    public b.a c;
    private int d = 0;
    private int e = 75;
    private e.a f = new e.a() {
        public void a(f fVar) {
            if (a.this.k() && a.this.g() == fVar && AnonymousClass2.f628a[fVar.j().ordinal()] == 4) {
                a.this.c.a(a.this.j());
            }
        }
    };

    private void a(e eVar) {
        if (eVar != null && k()) {
            eVar.f();
            c(eVar.b());
            this.c.a(this.d, g());
        }
    }

    private void b(e eVar) {
        if (eVar != null) {
            eVar.a(this.f);
            this.f626a.add(eVar);
        }
    }

    private boolean b(cn.fraudmetrix.octopus.aspirit.bean.b bVar) {
        b("005012".equals(bVar.client_channel_code) ? e(bVar) : d(bVar));
        c(bVar);
        return this.f626a.size() > 0;
    }

    private void c(cn.fraudmetrix.octopus.aspirit.bean.b bVar) {
        if (bVar != null && bVar.crawledUrls != null) {
            for (d next : bVar.crawledUrls) {
                b((e) new cn.fraudmetrix.octopus.aspirit.g.d(next.name, next.url, (String) null, next.JsContentL1, bVar.wait_seconds));
            }
        }
    }

    private void c(String str) {
        this.b.put(str, g());
    }

    private e d(cn.fraudmetrix.octopus.aspirit.bean.b bVar) {
        return new c(bVar.city_name, bVar.login_url, bVar.success_url, bVar.user_agent, bVar.login_js, bVar.wait_seconds);
    }

    private void d() {
        if (k()) {
            this.c.b(this.d, g());
            f();
        }
    }

    private e e(cn.fraudmetrix.octopus.aspirit.bean.b bVar) {
        return new g(bVar.city_name, bVar.login_url, bVar.success_url, bVar.user_agent, bVar.login_js, bVar.wait_seconds);
    }

    private void e() {
        if (k() && this.c.c(this.d, g())) {
            f();
        }
    }

    private void f() {
        this.c.a(j());
        if (i()) {
            if (this.c.a((f) this.f626a.get(this.d + 1))) {
                a(h());
            }
        } else if (k()) {
            this.c.a((List<? extends f>) this.f626a);
        }
    }

    private boolean f(cn.fraudmetrix.octopus.aspirit.bean.b bVar) {
        StringBuilder sb = new StringBuilder();
        OctopusManager b2 = OctopusManager.b();
        sb.append(bVar.login_url + "?box_token=" + bVar.box_token);
        try {
            sb.append("&cb=" + URLEncoder.encode(OctopusConstants.C, "utf-8"));
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        if (b2.f644a != null && !j.d(b2.f644a.passbackarams)) {
            sb.append("&passback_params=" + b2.f644a.passbackarams);
        }
        bVar.login_url = sb.toString();
        this.f626a.add(d(bVar));
        return this.f626a.size() > 0;
    }

    /* access modifiers changed from: private */
    public e g() {
        if (this.f626a == null || this.f626a.size() <= this.d) {
            return null;
        }
        return this.f626a.get(this.d);
    }

    private e h() {
        if (!i()) {
            return null;
        }
        this.d++;
        return g();
    }

    private boolean i() {
        return this.f626a != null && this.f626a.size() > this.d + 1;
    }

    /* access modifiers changed from: private */
    public int j() {
        int i;
        int nextInt = new Random().nextInt(5);
        int size = this.e / this.f626a.size();
        if (g() != null) {
            f.a j = g().j();
            if (j == f.a.GatherData) {
                i = (this.d * size) + ((size / 4) * 3);
                return i + nextInt;
            } else if (j == f.a.LoginCompleteLoad) {
                return (size / 2) + nextInt;
            }
        }
        i = (this.d + 1) * size;
        return i + nextInt;
    }

    /* access modifiers changed from: private */
    public boolean k() {
        return this.c != null;
    }

    public void a() {
        if (this.f626a.size() > 0) {
            this.d = 0;
            a(g());
        }
    }

    public void a(int i, String str, String str2) {
        if (this.b.get(str2) != null) {
            e eVar = this.b.get(str2);
            if (eVar.j() == f.a.Complete) {
                return;
            }
            if (eVar.i()) {
                e();
            } else {
                a(g());
            }
        }
    }

    public void a(b.a aVar) {
        this.c = aVar;
    }

    public void a(String str) {
        e g = g();
        if (this.b.get(str) != null) {
            g = this.b.get(str);
        }
        if (g != null) {
            f.a d2 = g.d(str);
            if (this.d > 0) {
                this.c.a(j());
            }
            switch (d2) {
                case InjectionJs:
                    if (k()) {
                        this.c.d(this.d, g);
                        return;
                    }
                    return;
                case Error:
                    h.c("onTaskUrlLoadComplete  Error name:" + g().a() + " url:" + str);
                    e();
                    return;
                case Complete:
                    h.a("onTaskUrlLoadComplete Complete name:" + g().a());
                    d();
                    return;
                default:
                    return;
            }
        }
    }

    public void a(String str, int i) {
        if (this.b.get(str) != null) {
            e eVar = this.b.get(str);
            if (eVar.a(str, i) == f.a.InjectionJs && k()) {
                this.c.d(this.d, eVar);
            }
        }
    }

    public void a(String str, String str2) {
        e g = g();
        g.a(str);
        g.b(str2);
        a(g());
    }

    public boolean a(cn.fraudmetrix.octopus.aspirit.bean.b bVar) {
        if (bVar == null) {
            return false;
        }
        this.e += new Random().nextInt(10);
        if (!TextUtils.isEmpty(bVar.login_url)) {
            this.f626a = new ArrayList();
            this.b = new HashMap();
            this.d = 0;
            switch (bVar.source_type) {
                case 1:
                    return b(bVar);
                case 2:
                    return f(bVar);
            }
        }
        return false;
    }

    public void b() {
        if (k()) {
            if (this.f626a.size() > 0 && g() != null) {
                this.c.e(this.d, g());
            }
            this.c.a();
        }
    }

    public void b(String str, String str2) {
        g().a(str, str2);
    }

    public boolean b(String str) {
        boolean e2 = g().e(str);
        if (!e2) {
            c(str);
        }
        return e2;
    }

    public void c() {
        if (g() != null) {
            g().g();
        }
        d();
    }
}
