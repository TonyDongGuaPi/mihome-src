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

public class RxCheckSignDeductTask extends RxBasePaymentTask<Result> {

    public static class Result implements Serializable {
        public String mDeductChannel;
        public String mExtraInfo;
        public String mOrderDesc;
    }

    public RxCheckSignDeductTask(Context context, Session session) {
        super(context, session, Result.class);
    }

    /* access modifiers changed from: protected */
    public Connection b(SortedParameter sortedParameter) {
        SortedParameter sortedParameter2;
        Connection connection;
        String f = sortedParameter.f("processId");
        String f2 = sortedParameter.f("deductSignOrder");
        if (this.f7587a.e() instanceof FakeAccountLoader) {
            connection = ConnectionFactory.a(this.b, MibiConstants.a(MibiConstants.cy));
            sortedParameter2 = connection.d();
            sortedParameter2.a("imei", (Object) Client.A().i());
        } else {
            connection = ConnectionFactory.a(MibiConstants.a(MibiConstants.cx), this.f7587a);
            sortedParameter2 = connection.d();
        }
        sortedParameter2.a(MibiConstants.gH, (Object) f2);
        sortedParameter2.a("processId", (Object) f);
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
