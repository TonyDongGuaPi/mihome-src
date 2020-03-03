package com.unionpay;

import android.content.Context;
import com.unionpay.utils.UPUtils;
import java.util.Iterator;
import org.json.JSONObject;

final class r implements ac {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ UPPayWapActivity f9813a;

    r(UPPayWapActivity uPPayWapActivity) {
        this.f9813a = uPPayWapActivity;
    }

    public final void a(String str, ad adVar) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                UPUtils.a((Context) this.f9813a, jSONObject.getString(next), next);
            }
            if (adVar != null) {
                adVar.a(UPPayWapActivity.b("0", "success", (String) null));
            }
        } catch (Exception e) {
            if (adVar != null) {
                adVar.a(UPPayWapActivity.b("1", e.getMessage(), (String) null));
            }
        }
    }
}
