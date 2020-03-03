package cn.fraudmetrix.octopus.aspirit.c;

import android.text.TextUtils;
import cn.fraudmetrix.octopus.aspirit.b.f;
import cn.fraudmetrix.octopus.aspirit.bean.g;
import cn.fraudmetrix.octopus.aspirit.main.OctopusManager;
import cn.fraudmetrix.octopus.aspirit.utils.OctopusConstants;
import cn.fraudmetrix.octopus.aspirit.utils.e;
import cn.fraudmetrix.octopus.aspirit.utils.i;
import cn.fraudmetrix.octopus.aspirit.utils.j;
import cn.fraudmetrix.octopus.aspirit.utils.k;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.annotation.JSMethod;
import com.unionpay.tsmservice.data.ResultCode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class c implements cn.fraudmetrix.octopus.aspirit.b.a {

    /* renamed from: a  reason: collision with root package name */
    private static c f629a = new c();
    private Map<String, a> b = new HashMap();
    private List<a> c = new ArrayList();
    private g d;
    private boolean e = false;

    public class a {
        private String b;
        private String c;
        private Date d;
        private Date e;
        private String f;
        private String g;

        public a() {
        }

        public String a() {
            return this.b;
        }

        public void a(String str) {
            this.b = str;
        }

        public Date b() {
            return this.d;
        }

        public void b(String str) {
            this.c = str;
        }

        public String c() {
            return this.f;
        }

        public void c(String str) {
            this.f = str;
        }

        public String d() {
            return this.g;
        }

        public void d(String str) {
            this.g = str;
        }

        public Date e() {
            return this.e;
        }

        public void f() {
            this.d = new Date();
        }

        public void g() {
            this.e = new Date();
        }
    }

    public static class b {
        public static synchronized void a() {
            synchronized (b.class) {
                final e eVar = new e(OctopusManager.b().c());
                for (final Map.Entry next : eVar.a().entrySet()) {
                    if (((String) next.getKey()).endsWith("_octopus_intent_data")) {
                        String str = (String) eVar.b((String) next.getKey(), "");
                        if (!TextUtils.isEmpty(str)) {
                            f.a.a().a(i.p, str, (cn.fraudmetrix.octopus.aspirit.b.b<String, ?>) new cn.fraudmetrix.octopus.aspirit.a.b() {
                                /* renamed from: a */
                                public void b(JSONObject jSONObject) {
                                    eVar.a((String) next.getKey());
                                }

                                public void a(Throwable th) {
                                }
                            });
                        }
                    }
                }
            }
        }
    }

    public static c d() {
        return f629a;
    }

    public void a() {
        this.d = new g();
        this.e = true;
    }

    public synchronized void a(cn.fraudmetrix.octopus.aspirit.bean.a.a aVar) {
        List<a> g = g();
        for (Map.Entry<String, a> value : this.b.entrySet()) {
            g.add(value.getValue());
        }
        for (a next : g) {
            cn.fraudmetrix.octopus.aspirit.bean.a.a aVar2 = new cn.fraudmetrix.octopus.aspirit.bean.a.a();
            aVar2.url = next.a();
            aVar2.code = next.c() != null ? next.c() : ResultCode.ERROR_INTERFACE_GET_APP_DETAIL;
            aVar2.message = next.d();
            if (next.e() != null) {
                aVar2.cost_time = (next.e().getTime() - next.b().getTime()) + "";
            }
            c().crawled_info.add(aVar2);
        }
        if (aVar != null) {
            c().crawled_info.add(aVar);
        }
        c().device_info = k.a(OctopusManager.b().c());
        if (!(c().crawled_info.size() <= 0 || c().crawled_info == null || c().user_info == null)) {
            new e(OctopusManager.b().c()).a(j.a() + JSMethod.NOT_SET + OctopusConstants.d, JSON.toJSONString(this.d));
        }
        c().crawled_info.clear();
        d().f();
    }

    public void a(String str, String str2) {
        if (!TextUtils.isEmpty(str) && this.e) {
            synchronized (this) {
                a aVar = new a();
                aVar.a(str);
                aVar.b(str2);
                aVar.c("10000");
                this.b.put(str, aVar);
                aVar.f();
            }
        }
    }

    public void a(String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str) && this.e) {
            synchronized (this) {
                if (this.b.containsKey(str)) {
                    a remove = this.b.remove(str);
                    remove.c(str2);
                    remove.d(str3);
                    remove.g();
                    this.c.add(remove);
                }
            }
        }
    }

    public void b() {
        this.e = false;
    }

    public g c() {
        if (this.d.channel_info == null) {
            this.d.channel_info = new cn.fraudmetrix.octopus.aspirit.bean.a();
            this.d.partner_code = OctopusManager.b().d();
            this.d.sdk_version = OctopusManager.b().h();
            this.d.crawled_info = new ArrayList<>();
            this.d.user_info = new cn.fraudmetrix.octopus.aspirit.bean.j();
        }
        return this.d;
    }

    public void e() {
        a((cn.fraudmetrix.octopus.aspirit.bean.a.a) null);
    }

    public synchronized void f() {
        this.b.clear();
        this.c.clear();
    }

    public List<a> g() {
        return this.c;
    }
}
