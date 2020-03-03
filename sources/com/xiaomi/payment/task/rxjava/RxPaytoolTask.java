package com.xiaomi.payment.task.rxjava;

import android.content.Context;
import android.text.TextUtils;
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
import com.xiaomi.payment.task.rxjava.RxPaytoolTask.Result;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class RxPaytoolTask<R extends Result> extends RxBasePaymentTask<R> {

    public static class Result {
        public String b;
        public String c;
    }

    /* access modifiers changed from: protected */
    public abstract String c();

    public RxPaytoolTask(Context context, Session session, Class<R> cls) {
        super(context, session, cls);
    }

    /* access modifiers changed from: protected */
    public Connection b(SortedParameter sortedParameter) {
        String f = sortedParameter.f("processId");
        long d = sortedParameter.d(MibiConstants.dq);
        Connection a2 = ConnectionFactory.a(this.b, this.f7587a, c());
        SortedParameter d2 = a2.d();
        d2.a("processId", (Object) f);
        if (this.f7587a.e() instanceof FakeAccountLoader) {
            d2.a("imei", (Object) Client.A().i());
        } else {
            d2.a(MibiConstants.dq, (Object) Long.valueOf(d));
        }
        return a2;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void c(JSONObject jSONObject, R r) throws PaymentException {
        try {
            String string = jSONObject.getString(MibiConstants.dr);
            String string2 = jSONObject.getString("url");
            if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2)) {
                throw new ResultException("result has error");
            }
            r.b = string;
            r.c = string2;
        } catch (JSONException e) {
            throw new ResultException((Throwable) e);
        }
    }
}
