package com.alipay.deviceid.module.x;

import android.content.Context;
import com.alipay.sdk.sys.a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class d {
    private static d b;

    /* renamed from: a  reason: collision with root package name */
    public Map<String, String> f925a = null;

    public static d a() {
        if (b == null) {
            synchronized (d.class) {
                if (b == null) {
                    b = new d();
                }
            }
        }
        return b;
    }

    public final String a(Context context) {
        String str;
        b(context);
        Map<String, String> map = this.f925a;
        if (map == null) {
            str = null;
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            ArrayList arrayList = new ArrayList(map.keySet());
            Collections.sort(arrayList);
            int i = 0;
            while (i < arrayList.size()) {
                String str2 = (String) arrayList.get(i);
                String str3 = map.get(str2);
                if (str3 == null) {
                    str3 = "";
                }
                StringBuilder sb = new StringBuilder();
                sb.append(i == 0 ? "" : a.b);
                sb.append(str2);
                sb.append("=");
                sb.append(str3);
                stringBuffer.append(sb.toString());
                i++;
            }
            str = stringBuffer.toString();
        }
        return h.a(str);
    }

    public final void b(Context context) {
        this.f925a = new TreeMap();
        Map<String, String> map = this.f925a;
        HashMap hashMap = new HashMap();
        hashMap.put("AC4", bz.b(context));
        map.putAll(hashMap);
        Map<String, String> map2 = this.f925a;
        l.a();
        HashMap hashMap2 = new HashMap();
        hashMap2.put("AE1", l.b());
        StringBuilder sb = new StringBuilder();
        sb.append(l.c() ? "1" : "0");
        hashMap2.put("AE2", sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append(l.a(context) ? "1" : "0");
        hashMap2.put("AE3", sb2.toString());
        hashMap2.put("AE4", l.d());
        hashMap2.put("AE5", l.e());
        hashMap2.put("AE6", l.f());
        hashMap2.put("AE7", l.g());
        hashMap2.put("AE8", l.h());
        hashMap2.put("AE9", l.i());
        hashMap2.put("AE10", l.j());
        hashMap2.put("AE11", l.k());
        hashMap2.put("AE12", l.l());
        hashMap2.put("AE13", l.m());
        hashMap2.put("AE14", l.n());
        hashMap2.put("AE15", l.a("ro.kernel.qemu", "0"));
        map2.putAll(hashMap2);
        Map<String, String> map3 = this.f925a;
        k a2 = k.a();
        HashMap hashMap3 = new HashMap();
        bx a3 = bw.a(context);
        String a4 = k.a(context);
        String b2 = k.b(context);
        String l = k.l(context);
        String o = k.o(context);
        String n = k.n(context);
        boolean z = true;
        if (a3 != null && e.a(e.c(a3.f900a), a4) && e.a(e.c(a3.b), b2) && e.a(e.c(a3.c), l) && e.a(e.c(a3.d), o) && e.a(e.c(a3.e), n)) {
            z = false;
        }
        if (a3 != null) {
            if (e.a(a4)) {
                a4 = e.c(a3.f900a);
            }
            if (e.a(b2)) {
                b2 = e.c(a3.b);
            }
            if (e.a(l)) {
                l = e.c(a3.c);
            }
            if (e.a(o)) {
                o = e.c(a3.d);
            }
            if (e.a(n)) {
                n = e.c(a3.e);
            }
        }
        if (z) {
            bw.a(context, new bx(a4, b2, l, o, n));
        } else {
            Context context2 = context;
        }
        hashMap3.put("AD1", a4);
        hashMap3.put("AD2", b2);
        hashMap3.put("AD3", k.g(context));
        hashMap3.put("AD4", "");
        hashMap3.put("AD5", k.i(context));
        hashMap3.put("AD6", k.j(context));
        hashMap3.put("AD7", k.k(context));
        hashMap3.put("AD8", l);
        hashMap3.put("AD9", k.m(context));
        hashMap3.put("AD10", n);
        hashMap3.put("AD11", k.e());
        hashMap3.put("AD12", a2.g());
        hashMap3.put("AD13", k.h());
        hashMap3.put("AD14", k.j());
        hashMap3.put("AD15", k.k());
        hashMap3.put("AD16", k.l());
        hashMap3.put("AD17", "");
        hashMap3.put("AD18", o);
        hashMap3.put("AD20", k.m());
        hashMap3.put("AD21", "");
        hashMap3.put("AD22", "");
        hashMap3.put("AD23", k.o());
        hashMap3.put("AD24", e.f(k.h(context)));
        hashMap3.put("AD25", "");
        hashMap3.put("AD26", k.f(context));
        hashMap3.put("AD27", k.t());
        hashMap3.put("AD28", k.v());
        hashMap3.put("AD29", k.x());
        hashMap3.put("AD30", k.u());
        hashMap3.put("AD31", k.w());
        StringBuilder sb3 = new StringBuilder();
        sb3.append(k.r() - (k.r() % 1000));
        hashMap3.put("AD32", sb3.toString());
        hashMap3.put("AD34", k.s(context));
        hashMap3.put("AD37", k.q());
        hashMap3.put("AD38", k.p());
        hashMap3.put("AD39", k.c(context));
        map3.putAll(hashMap3);
        this.f925a.putAll(c.a(context));
    }
}
