package com.mipay.ucashier.task;

import android.content.Context;
import com.mipay.common.data.e;
import com.mipay.common.data.h;
import com.mipay.common.exception.f;
import com.mipay.common.exception.g;
import com.mipay.ucashier.data.UCashierConstants;
import com.mipay.ucashier.task.BaseUCashierTask;
import org.json.JSONException;
import org.json.JSONObject;

public class PayTradeTask extends BaseUCashierTask<Void, Result> {

    public static class Result extends BaseUCashierTask.Result {
        public String mPayInfo;
    }

    public PayTradeTask(Context context) {
        super(context, Result.class);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void c(JSONObject jSONObject, Result result) throws f {
        try {
            result.mPayInfo = jSONObject.getString(UCashierConstants.KEY_PAY_INFO);
        } catch (JSONException e) {
            throw new g((Throwable) e);
        }
    }

    /* access modifiers changed from: protected */
    public e b(h hVar) {
        String f = hVar.f("tradeId");
        int e = hVar.e("payType");
        String f2 = hVar.f("deviceId");
        e a2 = com.mipay.common.data.f.a(this.f8187a, UCashierConstants.getUrl(UCashierConstants.URL_PAY_TRADE));
        e.b d = a2.d();
        d.a("tradeId", (Object) f);
        d.a("payType", (Object) Integer.valueOf(e));
        d.a("deviceId", (Object) f2);
        return a2;
    }
}
