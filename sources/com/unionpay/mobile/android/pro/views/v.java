package com.unionpay.mobile.android.pro.views;

import android.content.Context;
import android.os.Handler;
import com.unionpay.mobile.android.hce.c;
import com.unionpay.mobile.android.hce.f;
import com.unionpay.mobile.android.model.b;
import com.unionpay.mobile.android.model.d;
import com.unionpay.mobile.android.model.e;
import com.unionpay.mobile.android.nocard.views.ao;
import com.unionpay.uppay.PayActivity;
import java.util.Iterator;

public final class v extends ao {
    public v(Context context, e eVar) {
        super(context, eVar);
    }

    /* access modifiers changed from: protected */
    public final void a(Handler handler) {
        Object a2 = ((PayActivity) this.d).a(f.class.toString());
        if (a2 != null) {
            ((f) a2).a(handler);
        }
    }

    /* access modifiers changed from: protected */
    public final void d(String str, String str2) {
        if (b.bn) {
            a(this.f9608a.ap, false);
            return;
        }
        Object a2 = ((PayActivity) this.d).a(com.unionpay.mobile.android.pro.pboc.engine.b.class.toString());
        if (a2 != null) {
            ((com.unionpay.mobile.android.pro.pboc.engine.b) a2).a(new Handler(new w(this)), str, str2);
        }
    }

    /* access modifiers changed from: protected */
    public final boolean t() {
        return true;
    }

    /* access modifiers changed from: protected */
    public final void u() {
        if (b.bb != null) {
            Iterator<d> it = b.bb.iterator();
            while (it.hasNext()) {
                try {
                    this.d.unbindService(((c) it.next()).h());
                } catch (IllegalArgumentException unused) {
                }
            }
        }
    }
}
