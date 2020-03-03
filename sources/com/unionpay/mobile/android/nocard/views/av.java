package com.unionpay.mobile.android.nocard.views;

import android.text.TextUtils;
import android.view.View;
import com.unionpay.mobile.android.utils.j;
import org.json.JSONObject;

final class av implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ at f9603a;

    av(at atVar) {
        this.f9603a = atVar;
    }

    public final void onClick(View view) {
        JSONObject jSONObject = (JSONObject) view.getTag();
        String a2 = j.a(jSONObject, "errMsg");
        if (a2 == null || TextUtils.isEmpty(a2)) {
            at.a(this.f9603a, j.a(jSONObject, "action"), j.a(jSONObject, "value"));
            return;
        }
        this.f9603a.a(a2);
    }
}
