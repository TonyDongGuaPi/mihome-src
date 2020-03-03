package com.unionpay.mobile.android.pro.views;

import android.text.TextUtils;
import android.view.View;
import com.unionpay.mobile.android.utils.j;
import org.json.JSONObject;

final class c implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ a f9685a;

    c(a aVar) {
        this.f9685a = aVar;
    }

    public final void onClick(View view) {
        JSONObject jSONObject = (JSONObject) view.getTag();
        String a2 = j.a(jSONObject, "errMsg");
        if (a2 == null || TextUtils.isEmpty(a2)) {
            String a3 = j.a(jSONObject, "action");
            String a4 = j.a(jSONObject, "value");
            a aVar = this.f9685a;
            a.a(aVar, a3, a4 + ",\"carrier_tp\":\"9\"");
            return;
        }
        this.f9685a.a(a2);
    }
}
