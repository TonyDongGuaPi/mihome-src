package com.unionpay.mobile.android.widgets;

import android.view.View;

final class aq implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ap f9776a;

    aq(ap apVar) {
        this.f9776a = apVar;
    }

    public final void onClick(View view) {
        if (this.f9776a.c != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.f9776a.s());
            sb.append("_click_get_msg");
            this.f9776a.c.a(this.f9776a);
        }
    }
}
