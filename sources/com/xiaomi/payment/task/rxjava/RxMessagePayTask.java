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
import com.xiaomi.payment.task.rxjava.RxMessagePayTask.Result;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class RxMessagePayTask<TaskResult extends Result> extends RxBasePaymentTask<TaskResult> {

    public static class Result {
        public String c;
        JSONObject d;
    }

    public RxMessagePayTask(Context context, Session session, Class<TaskResult> cls) {
        super(context, session, cls);
    }

    /* access modifiers changed from: protected */
    public Connection b(SortedParameter sortedParameter) {
        String f = sortedParameter.f("processId");
        Connection a2 = ConnectionFactory.a(MibiConstants.a(MibiConstants.bM), this.f7587a);
        SortedParameter d = a2.d();
        d.a("processId", (Object) f);
        d.a(MibiConstants.dq, sortedParameter.a(MibiConstants.dq));
        d.a(MibiConstants.eY, sortedParameter.a(MibiConstants.eY));
        d.a(MibiConstants.eZ, sortedParameter.a(MibiConstants.eZ));
        return a2;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void c(JSONObject jSONObject, TaskResult taskresult) throws PaymentException {
        try {
            taskresult.c = jSONObject.getString(MibiConstants.dr);
            String string = jSONObject.getString("url");
            if (TextUtils.isEmpty(taskresult.c) || TextUtils.isEmpty(string)) {
                throw new ResultException("result has error");
            }
            try {
                Connection a2 = ConnectionFactory.a(this.b, string, false);
                a2.a(true);
                taskresult.d = a2.e();
            } catch (Exception e) {
                throw new ResultException((Throwable) e);
            }
        } catch (JSONException e2) {
            throw new ResultException((Throwable) e2);
        }
    }
}
