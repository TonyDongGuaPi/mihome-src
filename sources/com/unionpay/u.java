package com.unionpay;

final class u implements ac {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ UPPayWapActivity f9842a;

    u(UPPayWapActivity uPPayWapActivity) {
        this.f9842a = uPPayWapActivity;
    }

    public final void a(String str, ad adVar) {
        UPPayWapActivity.a(this.f9842a, Boolean.parseBoolean(str));
        if (adVar != null) {
            adVar.a(UPPayWapActivity.b("0", "success", (String) null));
        }
    }
}
