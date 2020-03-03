package com.xiaomi.payment.task.rxjava;

import android.content.Context;
import android.text.TextUtils;
import com.mibi.common.data.Connection;
import com.mibi.common.data.ConnectionFactory;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.exception.PaymentException;
import com.mibi.common.exception.ResultException;
import com.mibi.common.rxjava.RxBasePaymentTask;
import com.xiaomi.payment.data.MibiConstants;
import org.json.JSONException;
import org.json.JSONObject;

public class RxPrepaidTask extends RxBasePaymentTask<Result> {

    public static class Result {

        /* renamed from: a  reason: collision with root package name */
        public String f12430a;
    }

    public RxPrepaidTask(Context context, Session session) {
        super(context, session, Result.class);
    }

    /* access modifiers changed from: protected */
    public Connection b(SortedParameter sortedParameter) {
        String f = sortedParameter.f("processId");
        String f2 = sortedParameter.f("channel");
        String f3 = sortedParameter.f("carrier");
        String f4 = sortedParameter.f(MibiConstants.dD);
        String f5 = sortedParameter.f(MibiConstants.dE);
        long d = sortedParameter.d(MibiConstants.dq);
        Connection a2 = ConnectionFactory.a(MibiConstants.a(MibiConstants.bP), this.f7587a);
        SortedParameter d2 = a2.d();
        d2.a("processId", (Object) f);
        d2.a("channel", (Object) f2);
        d2.a("carrier", (Object) f3);
        d2.a(MibiConstants.dD, (Object) f4);
        d2.a(MibiConstants.dE, (Object) f5);
        d2.a(MibiConstants.dq, (Object) Long.valueOf(d));
        return a2;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void c(JSONObject jSONObject, Result result) throws PaymentException {
        try {
            String string = jSONObject.getString(MibiConstants.dr);
            if (!TextUtils.isEmpty(string)) {
                result.f12430a = string;
                return;
            }
            throw new ResultException("result has error");
        } catch (JSONException e) {
            throw new ResultException((Throwable) e);
        }
    }
}
