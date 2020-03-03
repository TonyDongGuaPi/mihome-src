package com.unionpay.mobile.android.nocard.views;

import android.view.View;
import com.unionpay.mobile.android.nocard.views.bd;
import com.unionpay.mobile.android.utils.o;

final class bg implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ int f9615a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ bd.a d;

    bg(bd.a aVar, int i, String str, String str2) {
        this.d = aVar;
        this.f9615a = i;
        this.b = str;
        this.c = str2;
    }

    public final void onClick(View view) {
        String[] strArr = o.i;
        Object[] objArr = {Integer.valueOf(this.f9615a), this.b};
        bd.this.a("", this.c);
    }
}
