package com.unionpay.mobile.android.pro.views;

import android.content.Context;
import com.unionpay.mobile.android.model.e;
import com.unionpay.mobile.android.pro.pboc.engine.b;
import com.unionpay.uppay.PayActivity;

public final class x extends y {
    public x(Context context, e eVar) {
        super(context, eVar);
    }

    public final b s() {
        Object a2 = ((PayActivity) this.d).a(b.class.toString());
        if (a2 != null) {
            return (b) a2;
        }
        return null;
    }
}
