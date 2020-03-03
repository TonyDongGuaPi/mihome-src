package com.xiaomi.push.service;

import android.util.Base64;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.al;
import com.xiaomi.push.ay;
import com.xiaomi.push.df;
import com.xiaomi.push.el;
import com.xiaomi.push.service.bb;
import com.xiaomi.push.t;
import java.util.List;

class bc extends al.b {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ bb f12900a;

    /* renamed from: a  reason: collision with other field name */
    boolean f318a = false;

    bc(bb bbVar) {
        this.f12900a = bbVar;
    }

    public void b() {
        try {
            el.a b = el.a.b(Base64.decode(df.a(t.a(), "http://resolver.msg.xiaomi.net/psc/?t=a", (List<ay>) null), 10));
            if (b != null) {
                el.a unused = this.f12900a.f316a = b;
                this.f318a = true;
                bb.a(this.f12900a);
            }
        } catch (Exception e) {
            b.a("fetch config failure: " + e.getMessage());
        }
    }

    public void c() {
        bb.a[] aVarArr;
        al.b unused = this.f12900a.f315a = null;
        if (this.f318a) {
            synchronized (this.f12900a) {
                aVarArr = (bb.a[]) bb.a(this.f12900a).toArray(new bb.a[bb.a(this.f12900a).size()]);
            }
            for (bb.a a2 : aVarArr) {
                a2.a(bb.a(this.f12900a));
            }
        }
    }
}
