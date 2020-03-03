package com.unionpay.mobile.android.nocard.views;

import android.text.TextUtils;
import android.view.View;
import com.unionpay.mobile.android.utils.j;
import org.json.JSONObject;

final class p implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ o f9630a;

    p(o oVar) {
        this.f9630a = oVar;
    }

    public final void onClick(View view) {
        JSONObject jSONObject = (JSONObject) view.getTag();
        String a2 = j.a(jSONObject, "errMsg");
        if (a2 == null || TextUtils.isEmpty(a2)) {
            o.a(this.f9630a, j.a(jSONObject, "action"), j.a(jSONObject, "value"));
            return;
        }
        this.f9630a.a(a2);
    }
}
