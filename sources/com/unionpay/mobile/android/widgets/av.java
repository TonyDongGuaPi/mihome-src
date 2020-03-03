package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.text.InputFilter;
import java.util.Calendar;
import org.json.JSONObject;

public final class av extends aa {
    public av(Context context, int i, JSONObject jSONObject, String str) {
        super(context, i, jSONObject, str);
        this.b.a((InputFilter) new InputFilter.LengthFilter(4));
        this.b.a(2);
    }

    public final String a() {
        return this.b.b().trim();
    }

    public final boolean b() {
        String a2 = a();
        if (4 == a2.length()) {
            int parseInt = Integer.parseInt(a2.substring(0, 2));
            int parseInt2 = Integer.parseInt(a2.substring(2));
            int i = Calendar.getInstance().get(1) % 100;
            if (parseInt > 0 && parseInt <= 12 && (parseInt2 > i || (parseInt2 == i && parseInt >= Calendar.getInstance().get(2) + 1))) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public final String d() {
        return "_select_availdata";
    }
}
