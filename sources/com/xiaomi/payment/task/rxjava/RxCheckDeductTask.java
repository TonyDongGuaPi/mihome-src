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

public class RxCheckDeductTask extends RxBasePaymentTask<Result> {

    public static class Result implements Serializable {
        public String mDeductChannel;
        public String mExtraInfo;
        public String mOrderDesc;
    }

    public RxCheckDeductTask(Context context, Session session) {
        super(context, session, Result.class);
    }

    /* access modifiers changed from: protected */
    public Connection b(SortedParameter sortedParameter) {
        Connection connection;
        String f = sortedParameter.f("processId");
        String f2 = sortedParameter.f("deductSignOrder");
        if (this.f7587a.e() instanceof FakeAccountLoader) {
            connection = ConnectionFactory.a(this.b, MibiConstants.a(MibiConstants.ck));
        } else {
            connection = ConnectionFactory.a(MibiConstants.a(MibiConstants.cj), this.f7587a);
        }
        SortedParameter d = connection.d();
        d.a("imei", (Object) Client.A().i());
        d.a("deductSignOrder", (Object) f2);
        d.a("processId", (Object) f);
        return connection;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void c(JSONObject jSONObject, Result result) throws PaymentException {
        super.c(jSONObject, result);
        result.mDeductChannel = jSONObject.optString(MibiConstants.gA);
        result.mOrderDesc = jSONObject.optString(MibiConstants.dR);
        result.mExtraInfo = jSONObject.optString("extraInfo");
    }
}
