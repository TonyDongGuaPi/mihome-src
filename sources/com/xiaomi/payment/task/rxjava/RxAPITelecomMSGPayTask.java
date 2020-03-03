package com.xiaomi.payment.task.rxjava;

import android.content.Context;
import com.mibi.common.data.Session;
import com.mibi.common.data.Utils;
import com.mibi.common.exception.PaymentException;
import com.mibi.common.exception.ResultException;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.task.rxjava.RxMessagePayTask;
import org.json.JSONException;
import org.json.JSONObject;

public class RxAPITelecomMSGPayTask extends RxMessagePayTask<Result> {

    public static class Result extends RxMessagePayTask.Result {

        /* renamed from: a  reason: collision with root package name */
        public String f12416a;
        public String b;
    }

    public RxAPITelecomMSGPayTask(Context context, Session session) {
        super(context, session, Result.class);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void c(JSONObject jSONObject, Result result) throws PaymentException {
        super.c(jSONObject, result);
        try {
            result.f12416a = result.d.getString(MibiConstants.fo);
            result.b = result.d.getString(MibiConstants.fp);
            if (!Utils.a(result.f12416a, result.b)) {
                throw new ResultException("result has error");
            }
        } catch (JSONException e) {
            throw new ResultException((Throwable) e);
        }
    }
}
