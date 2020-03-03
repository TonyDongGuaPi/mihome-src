package com.unionpay.mobile.android.pro.views;

import android.content.Context;
import com.unionpay.mobile.android.hce.f;
import com.unionpay.mobile.android.pro.pboc.engine.b;
import com.unionpay.uppay.PayActivity;

public final class j extends h {
    public j(Context context) {
        super(context);
    }

    public final void a(int i, int i2, String str, String str2, int i3, String str3) {
        Object a2 = ((PayActivity) this.d).a(f.class.toString());
        if (a2 != null) {
            f fVar = (f) a2;
            fVar.a();
            fVar.a(i);
            fVar.b(i2);
            fVar.a(str);
            fVar.b(str2);
            fVar.c(str3);
            fVar.c(i3);
            fVar.c();
        }
    }

    public final boolean s() {
        return true;
    }

    public final boolean t() {
        return true;
    }

    public final b y() {
        Object a2 = ((PayActivity) this.d).a(b.class.toString());
        if (a2 != null) {
            return (b) a2;
        }
        return null;
    }
}
