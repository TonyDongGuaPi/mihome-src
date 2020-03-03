package com.unionpay;

import android.content.Context;
import com.unionpay.utils.UPUtils;

final class t implements ac {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ UPPayWapActivity f9815a;

    t(UPPayWapActivity uPPayWapActivity) {
        this.f9815a = uPPayWapActivity;
    }

    public final void a(String str, ad adVar) {
        UPUtils.b((Context) this.f9815a, str);
        if (adVar != null) {
            adVar.a(UPPayWapActivity.b("0", "success", (String) null));
        }
    }
}
