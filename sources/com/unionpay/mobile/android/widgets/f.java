package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.text.InputFilter;
import org.json.JSONObject;

public final class f extends aa {
    public f(Context context, int i, JSONObject jSONObject, String str) {
        super(context, i, jSONObject, str);
        this.b.a((InputFilter) new InputFilter.LengthFilter(32));
    }

    public final boolean b() {
        return a().length() != 0 && a().length() <= 32;
    }

    /* access modifiers changed from: protected */
    public final String d() {
        return "_input_certId";
    }
}
