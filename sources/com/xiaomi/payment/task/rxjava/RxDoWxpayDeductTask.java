package com.xiaomi.payment.task.rxjava;

import android.content.Context;
import com.mibi.common.account.FakeAccountLoader;
import com.mibi.common.data.Client;
import com.mibi.common.data.Connection;
import com.mibi.common.data.ConnectionFactory;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.exception.PaymentException;
import com.mibi.common.exception.ResultException;
import com.mibi.common.rxjava.RxBasePaymentTask;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.deduct.DeductManager;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class RxDoWxpayDeductTask extends RxBasePaymentTask<Result> {

    public static class Result implements Serializable {
        public String mAppId;
        public String mUrl;
    }

    public RxDoWxpayDeductTask(Context context, Session session) {
        super(context, session, Result.class);
    }

    /* access modifiers changed from: protected */
    public Connection b(SortedParameter sortedParameter) {
        Connection connection;
        String f = sortedParameter.f("processId");
        if (this.f7587a.e() instanceof FakeAccountLoader) {
            connection = ConnectionFactory.a(this.b, MibiConstants.a(MibiConstants.cq));
        } else {
            connection = ConnectionFactory.a(MibiConstants.a(MibiConstants.cp), this.f7587a);
        }
        SortedParameter d = connection.d();
        d.a("processId", (Object) f);
        d.a(MibiConstants.gA, (Object) DeductManager.CHANNELS.WXPAY.getChannel());
        d.a("imei", (Object) Client.A().i());
        return connection;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void c(JSONObject jSONObject, Result result) throws PaymentException {
        super.c(jSONObject, result);
        try {
            result.mUrl = jSONObject.getString("url");
            result.mAppId = jSONObject.getString("appid");
        } catch (JSONException unused) {
            throw new ResultException("wxpay deduct result error");
        }
    }
}
