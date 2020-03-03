package com.unionpay.mobile.android.widgets;

import android.content.Context;
import com.unionpay.mobile.android.widgets.ba;
import org.json.JSONObject;

public abstract class z extends ba implements ba.a {
    public z(Context context, JSONObject jSONObject, String str) {
        super(context, jSONObject, str);
    }

    public final /* bridge */ /* synthetic */ boolean a(String str) {
        return super.a(str);
    }

    public /* bridge */ /* synthetic */ boolean f() {
        return super.f();
    }

    public String h() {
        StringBuffer stringBuffer = new StringBuffer();
        if (!(n() == null || a() == null)) {
            stringBuffer.append("\"");
            stringBuffer.append(n());
            stringBuffer.append("\":");
            stringBuffer.append("\"");
            stringBuffer.append(a());
            stringBuffer.append("\"");
        }
        return stringBuffer.toString();
    }

    public final /* bridge */ /* synthetic */ String i() {
        return super.i();
    }
}
