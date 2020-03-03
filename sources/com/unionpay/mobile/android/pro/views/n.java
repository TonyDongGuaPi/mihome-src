package com.unionpay.mobile.android.pro.views;

import android.text.TextUtils;
import android.view.View;
import com.unionpay.mobile.android.utils.j;
import org.json.JSONObject;

final class n implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ k f9693a;

    n(k kVar) {
        this.f9693a = kVar;
    }

    public final void onClick(View view) {
        JSONObject jSONObject = (JSONObject) view.getTag();
        String a2 = j.a(jSONObject, "errMsg");
        if (a2 == null || TextUtils.isEmpty(a2)) {
            String a3 = j.a(jSONObject, "action");
            String a4 = j.a(jSONObject, "value");
            k kVar = this.f9693a;
            k.a(kVar, a3, a4 + ",\"carrier_tp\":\"7\"");
            return;
        }
        this.f9693a.a(a2);
    }
}
