package com.xiaomi.payment.task.rxjava;

import android.content.Context;
import android.text.TextUtils;
import com.mibi.common.account.FakeAccountLoader;
import com.mibi.common.data.Session;
import com.mibi.common.exception.PaymentException;
import com.mibi.common.exception.ResultException;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.task.rxjava.RxPaytoolTask;
import org.json.JSONException;
import org.json.JSONObject;

public class RxTenpayTask extends RxPaytoolTask<Result> {

    public static class Result extends RxPaytoolTask.Result {

        /* renamed from: a  reason: collision with root package name */
        public String f12435a;
    }

    public RxTenpayTask(Context context, Session session) {
        super(context, session, Result.class);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void c(JSONObject jSONObject, Result result) throws PaymentException {
        super.c(jSONObject, result);
        try {
            String string = jSONObject.getString(MibiConstants.dA);
            if (!TextUtils.isEmpty(string)) {
                result.f12435a = string;
                return;
            }
            throw new ResultException("result has error");
        } catch (JSONException e) {
            throw new ResultException((Throwable) e);
        }
    }

    /* access modifiers changed from: protected */
    public String c() {
        if (this.f7587a.e() instanceof FakeAccountLoader) {
            return MibiConstants.a(MibiConstants.ce);
        }
        return MibiConstants.a(MibiConstants.bK);
    }
}
