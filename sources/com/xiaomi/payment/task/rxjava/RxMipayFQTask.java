package com.xiaomi.payment.task.rxjava;

import android.content.Context;
import android.text.TextUtils;
import com.mibi.common.account.FakeAccountLoader;
import com.mibi.common.data.Connection;
import com.mibi.common.data.ConnectionFactory;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.exception.PaymentException;
import com.mibi.common.exception.ResultException;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.task.rxjava.RxPaytoolTask;
import org.json.JSONObject;

public class RxMipayFQTask extends RxPaytoolTask<Result> {

    public static class Result extends RxPaytoolTask.Result {

        /* renamed from: a  reason: collision with root package name */
        public String f12427a;
    }

    public RxMipayFQTask(Context context, Session session) {
        super(context, session, Result.class);
    }

    /* access modifiers changed from: protected */
    public String c() {
        if (this.f7587a.e() instanceof FakeAccountLoader) {
            return MibiConstants.a(MibiConstants.cb);
        }
        return MibiConstants.a(MibiConstants.bH);
    }

    /* access modifiers changed from: protected */
    public Connection b(SortedParameter sortedParameter) {
        String f = sortedParameter.f("channel");
        Connection b = super.b(sortedParameter);
        b.d().a("channel", (Object) f);
        return b;
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
                result.f12427a = f;
                return;
            }
            throw new ResultException("result has error");
        } catch (Exception e) {
            throw new ResultException((Throwable) e);
        }
    }
}
