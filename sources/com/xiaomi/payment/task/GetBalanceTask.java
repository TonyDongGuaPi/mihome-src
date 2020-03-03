package com.xiaomi.payment.task;

import android.content.Context;
import android.text.TextUtils;
import com.mibi.common.base.BasePaymentTask;
import com.mibi.common.data.Connection;
import com.mibi.common.data.ConnectionFactory;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.exception.PaymentException;
import com.mibi.common.exception.ResultException;
import com.xiaomi.payment.data.MibiConstants;
import org.json.JSONException;
import org.json.JSONObject;

public class GetBalanceTask extends BasePaymentTask<Void, Result> {

    public static class Result extends BasePaymentTask.Result {
        public long mBalance;
    }

    public GetBalanceTask(Context context, Session session) {
        super(context, session, Result.class);
    }

    /* access modifiers changed from: protected */
    public Connection a(SortedParameter sortedParameter) {
        String f = sortedParameter.f(MibiConstants.dP);
        String f2 = sortedParameter.f(MibiConstants.dO);
        Connection a2 = ConnectionFactory.a(MibiConstants.a(MibiConstants.bz), this.f7453a);
        SortedParameter d = a2.d();
        if (!TextUtils.isEmpty(f)) {
            d.a(MibiConstants.dP, (Object) f);
        }
        if (!TextUtils.isEmpty(f2)) {
            d.a(MibiConstants.dO, (Object) f2);
        }
        return a2;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void c(JSONObject jSONObject, Result result) throws PaymentException {
        try {
            long j = jSONObject.getLong("balance");
            if (j >= 0) {
                result.mBalance = j;
                return;
            }
            throw new ResultException("balance has error");
        } catch (JSONException e) {
            throw new ResultException((Throwable) e);
        }
    }
}
