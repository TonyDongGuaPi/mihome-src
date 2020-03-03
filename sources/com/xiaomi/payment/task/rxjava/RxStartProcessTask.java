package com.xiaomi.payment.task.rxjava;

import android.content.Context;
import android.text.TextUtils;
import com.mibi.common.account.FakeAccountLoader;
import com.mibi.common.data.Client;
import com.mibi.common.data.CommonConstants;
import com.mibi.common.data.Connection;
import com.mibi.common.data.ConnectionFactory;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.exception.NoPrivacyRightException;
import com.mibi.common.exception.PaymentException;
import com.mibi.common.exception.ResultException;
import com.mibi.common.rxjava.RxBasePaymentTask;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.platform.R;
import org.json.JSONException;
import org.json.JSONObject;

public class RxStartProcessTask extends RxBasePaymentTask<Result> {

    public static class Result {

        /* renamed from: a  reason: collision with root package name */
        public String f12434a;
    }

    public RxStartProcessTask(Context context, Session session) {
        super(context, session, Result.class);
    }

    /* access modifiers changed from: protected */
    public Connection b(SortedParameter sortedParameter) {
        if (!(this.f7587a.e() instanceof FakeAccountLoader)) {
            return ConnectionFactory.a(MibiConstants.a(MibiConstants.by), this.f7587a);
        }
        Connection a2 = ConnectionFactory.a(this.b, MibiConstants.a(MibiConstants.bZ));
        a2.d().a("imei", (Object) Client.A().i());
        return a2;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void c(JSONObject jSONObject, Result result) throws PaymentException {
        boolean optBoolean = jSONObject.optBoolean(CommonConstants.bm, false);
        boolean optBoolean2 = jSONObject.optBoolean(CommonConstants.bn, false);
        if (optBoolean || optBoolean2) {
            throw new NoPrivacyRightException(this.b.getResources().getString(R.string.mibi_error_privacy_summary));
        }
        try {
            String string = jSONObject.getString("processId");
            if (!TextUtils.isEmpty(string)) {
                result.f12434a = string;
                return;
            }
            throw new ResultException("processId is empty!");
        } catch (JSONException e) {
            throw new ResultException((Throwable) e);
        }
    }
}
