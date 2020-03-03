package com.unionpay.mobile.android.nocard.views;

import android.view.View;
import org.json.JSONObject;

final class e implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ JSONObject f9621a;
    final /* synthetic */ b b;

    e(b bVar, JSONObject jSONObject) {
        this.b = bVar;
        this.f9621a = jSONObject;
    }

    public final void onClick(View view) {
        this.b.i();
        this.b.b(this.b.f9608a.aJ, this.f9621a);
    }
}
