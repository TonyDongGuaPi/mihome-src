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

public class RxVoucherTask extends RxBasePaymentTask<Result> {

    public static class Result {

        /* renamed from: a  reason: collision with root package name */
        public String f12438a;
        public String b;
        public long c;
    }

    public RxVoucherTask(Context context, Session session) {
        super(context, session, Result.class);
    }

    /* access modifiers changed from: protected */
    public Connection b(SortedParameter sortedParameter) {
        String f = sortedParameter.f("processId");
        String f2 = sortedParameter.f(MibiConstants.ek);
        Connection a2 = ConnectionFactory.a(MibiConstants.a(MibiConstants.bU), this.f7587a);
        SortedParameter d = a2.d();
        d.a("processId", (Object) f);
        d.a(MibiConstants.ek, (Object) f2);
        return a2;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void c(JSONObject jSONObject, Result result) throws PaymentException {
        try {
            String string = jSONObject.getString(MibiConstants.dr);
            long j = jSONObject.getLong(MibiConstants.el);
            if (!TextUtils.isEmpty(string)) {
                result.b = string;
                result.c = j;
                return;
            }
            throw new ResultException("result has error");
        } catch (JSONException e) {
            throw new ResultException((Throwable) e);
        }
    }
}
