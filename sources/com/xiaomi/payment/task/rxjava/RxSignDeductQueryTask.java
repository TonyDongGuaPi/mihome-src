package com.xiaomi.payment.task.rxjava;

import android.content.Context;
import com.mibi.common.account.FakeAccountLoader;
import com.mibi.common.data.Client;
import com.mibi.common.data.Connection;
import com.mibi.common.data.ConnectionFactory;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.exception.PaymentException;
import com.mibi.common.rxjava.RxBasePaymentTask;
import com.xiaomi.payment.data.MibiConstants;
import java.io.Serializable;
import org.json.JSONObject;

public class RxSignDeductQueryTask extends RxBasePaymentTask<Result> {

    public static class Result implements Serializable {
        public String mChargeStatus;
        public int mDeductSignStatus;
        public String mMarketDeductId;
        public String mOrderId;
        public int mPayStatus;
        public String mStatus;
    }

    public RxSignDeductQueryTask(Context context, Session session) {
        super(context, session, Result.class);
    }

    /* access modifiers changed from: protected */
    public Connection b(SortedParameter sortedParameter) {
        Connection connection;
        String f = sortedParameter.f("processId");
        if (this.f7587a.e() instanceof FakeAccountLoader) {
            connection = ConnectionFactory.a(this.b, MibiConstants.a(MibiConstants.cA));
        } else {
            connection = ConnectionFactory.a(MibiConstants.a(MibiConstants.cz), this.f7587a);
        }
        SortedParameter d = connection.d();
        d.a("processId", (Object) f);
        d.a("imei", (Object) Client.A().i());
        return connection;
    }

    /* access modifiers changed from: protected */
    public void a(JSONObject jSONObject, Result result) throws PaymentException {
        super.a(jSONObject, result);
        result.mStatus = jSONObject.optString("status");
        result.mMarketDeductId = jSONObject.optString(MibiConstants.hl);
        result.mOrderId = jSONObject.optString("orderId");
        result.mChargeStatus = jSONObject.optString(MibiConstants.dF);
        result.mPayStatus = jSONObject.optInt(MibiConstants.en);
        result.mDeductSignStatus = jSONObject.optInt(MibiConstants.hm);
    }
}
