package com.xiaomi.payment.task.rxjava;

import android.content.Context;
import android.text.TextUtils;
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

public class RxHomePageHeaderTask extends RxBasePaymentTask<Result> {
    private static final String c = "FROZE";

    public static class Result {

        /* renamed from: a  reason: collision with root package name */
        public long f12424a;
        public long b;
        public int c;
        public boolean d;
    }

    public RxHomePageHeaderTask(Context context, Session session) {
        super(context, session, Result.class);
    }

    /* access modifiers changed from: protected */
    public Connection b(SortedParameter sortedParameter) {
        Connection a2 = ConnectionFactory.a(MibiConstants.a(MibiConstants.bR), this.f7587a);
        a2.a(true);
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
            result.f12424a = jSONObject.getLong("balance");
            result.b = jSONObject.getLong("giftcardValue");
            result.c = jSONObject.getInt(MibiConstants.eA);
            result.d = TextUtils.equals(c, jSONObject.optString("status"));
            if (result.b > 0 && result.f12424a >= result.b) {
                result.f12424a -= result.b;
            }
        } catch (JSONException e) {
            throw new ResultException((Throwable) e);
        }
    }
}
