package com.xiaomi.payment.task.rxjava;

import android.content.Context;
import android.text.TextUtils;
import com.mibi.common.account.FakeAccountLoader;
import com.mibi.common.data.Connection;
import com.mibi.common.data.ConnectionFactory;
import com.mibi.common.data.Session;
import com.mibi.common.exception.PaymentException;
import com.mibi.common.exception.ResultException;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.task.rxjava.RxPaytoolTask;
import org.json.JSONObject;

public class RxAlipayTask extends RxPaytoolTask<Result> {

    public static class Result extends RxPaytoolTask.Result {

        /* renamed from: a  reason: collision with root package name */
        public String f12417a;
    }

    public RxAlipayTask(Context context, Session session) {
        super(context, session, Result.class);
    }

    /* access modifiers changed from: protected */
    public String c() {
        if (this.f7587a.e() instanceof FakeAccountLoader) {
            return MibiConstants.a(MibiConstants.cc);
        }
        return MibiConstants.a(MibiConstants.bI);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void c(JSONObject jSONObject, Result result) throws PaymentException {
        super.c(jSONObject, result);
        try {
            Connection a2 = ConnectionFactory.a(this.b, result.c, false);
            a2.a(true);
            String f = a2.f();
            if (!TextUtils.isEmpty(f)) {
                result.f12417a = f;
                return;
            }
            throw new ResultException("result has error");
        } catch (Exception e) {
            throw new ResultException((Throwable) e);
        }
    }
}
