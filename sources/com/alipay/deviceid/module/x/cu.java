package com.alipay.deviceid.module.x;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;

public final class cu extends ch {

    /* renamed from: a  reason: collision with root package name */
    private List<ch> f922a;

    public cu() {
        this((byte) 0);
    }

    private cu(byte b) {
        this.f922a = new ArrayList();
    }

    public final void a(ch chVar) {
        this.f922a.add(chVar);
    }

    public final synchronized void a(String str, boolean z, double d) {
        ch chVar = this.f922a.get(this.f922a.size() - 1);
        if (chVar instanceof cr) {
            ((cr) chVar).b();
        } else if (chVar instanceof cs) {
            ((cs) chVar).a(str);
        } else if (chVar instanceof ct) {
            ((ct) chVar).b(z);
        } else if (chVar instanceof cw) {
            ((cw) chVar).a(d);
        }
        this.f922a.set(this.f922a.size() - 1, chVar);
    }

    public final /* synthetic */ Object a() {
        JSONArray jSONArray = new JSONArray();
        if (this.f922a.size() > 15) {
            this.f922a = this.f922a.subList(this.f922a.size() - 15, this.f922a.size());
        }
        for (ch next : this.f922a) {
            if (next != null && (next instanceof ch)) {
                jSONArray.put(next.a());
            }
        }
        return jSONArray;
    }
}
