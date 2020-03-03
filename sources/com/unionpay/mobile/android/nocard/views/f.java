package com.unionpay.mobile.android.nocard.views;

import android.view.View;
import org.json.JSONObject;

final class f implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ JSONObject f9622a;
    final /* synthetic */ b b;

    f(b bVar, JSONObject jSONObject) {
        this.b = bVar;
        this.f9622a = jSONObject;
    }

    public final void onClick(View view) {
        this.b.i();
        this.b.b(this.b.f9608a.aL, this.f9622a);
    }
}
