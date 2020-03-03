package com.unionpay;

import android.content.Context;
import com.unionpay.utils.UPUtils;

final class s implements ac {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ UPPayWapActivity f9814a;

    s(UPPayWapActivity uPPayWapActivity) {
        this.f9814a = uPPayWapActivity;
    }

    public final void a(String str, ad adVar) {
        String a2 = UPUtils.a((Context) this.f9814a, str);
        if (adVar != null) {
            adVar.a(UPPayWapActivity.b("0", "success", a2));
        }
    }
}
