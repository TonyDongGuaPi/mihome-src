package com.unionpay;

import android.content.Context;

final class q implements ac {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ UPPayWapActivity f9812a;

    q(UPPayWapActivity uPPayWapActivity) {
        this.f9812a = uPPayWapActivity;
    }

    public final void a(String str, ad adVar) {
        String a2 = UPPayAssistEx.a((Context) this.f9812a, true);
        if (adVar != null) {
            adVar.a(UPPayWapActivity.b("0", "success", a2));
        }
    }
}
