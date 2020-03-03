package com.autonavi.httpdns;

import android.content.Context;
import com.loc.dc;
import com.loc.dd;
import java.util.ArrayList;

public class HttpDnsManager {

    /* renamed from: a  reason: collision with root package name */
    dd f4785a = null;
    private ArrayList<String> b = new ArrayList<>(12);

    public HttpDnsManager(Context context) {
        this.f4785a = dc.a(context, "154081");
        this.b.add("apilocatesrc.amap.com");
        this.f4785a.a((ArrayList) this.b);
        this.f4785a.a();
    }

    public String a(String str) {
        return this.f4785a.a(str);
    }

    public String[] b(String str) {
        if (!this.b.contains(str)) {
            this.b.add(str);
            this.f4785a.a((ArrayList) this.b);
        }
        return this.f4785a.b(str);
    }
}
