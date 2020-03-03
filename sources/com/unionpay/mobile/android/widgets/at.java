package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.text.InputFilter;
import com.taobao.weex.common.Constants;
import com.unionpay.mobile.android.utils.j;
import org.json.JSONObject;

public final class at extends aa {
    private int c = 0;

    public at(Context context, int i, JSONObject jSONObject, String str) {
        super(context, i, jSONObject, str);
        String a2 = j.a(jSONObject, Constants.Name.MAX_LENGTH);
        this.c = (a2 == null || a2.length() <= 0) ? 23 : Integer.getInteger(a2).intValue();
        this.b.a((InputFilter) new InputFilter.LengthFilter(this.c));
    }

    public final boolean b() {
        return this.i || this.c >= a().length();
    }

    /* access modifiers changed from: protected */
    public final String d() {
        return "_input_text";
    }
}
