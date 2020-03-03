package com.unionpay.mobile.android.pro.views;

import android.text.TextUtils;
import android.view.View;
import com.unionpay.mobile.android.utils.j;
import org.json.JSONObject;

final class aa implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ y f9677a;

    aa(y yVar) {
        this.f9677a = yVar;
    }

    public final void onClick(View view) {
        JSONObject jSONObject = (JSONObject) view.getTag();
        String a2 = j.a(jSONObject, "errMsg");
        if (a2 == null || TextUtils.isEmpty(a2)) {
            String a3 = j.a(jSONObject, "action");
            String a4 = j.a(jSONObject, "value");
            String str = this.f9677a.f9608a.br ? "10" : "2";
            y yVar = this.f9677a;
            y.a(yVar, a3, a4 + ",\"carrier_tp\":" + str);
            return;
        }
        this.f9677a.a(a2);
    }
}
