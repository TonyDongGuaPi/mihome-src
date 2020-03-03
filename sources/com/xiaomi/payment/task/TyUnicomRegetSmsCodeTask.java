package com.xiaomi.payment.task;

import android.content.Context;
import android.text.TextUtils;
import com.mibi.common.base.BasePaymentTask;
import com.mibi.common.data.Connection;
import com.mibi.common.data.ConnectionFactory;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.exception.PaymentException;
import com.mibi.common.exception.ResultException;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.recharge.RechargeManager;
import org.json.JSONException;
import org.json.JSONObject;

public class TyUnicomRegetSmsCodeTask extends BasePaymentTask<Void, Result> {

    public static class Result extends BasePaymentTask.Result {
        public String mChargeOrderId;
    }

    public TyUnicomRegetSmsCodeTask(Context context, Session session) {
        super(context, session, Result.class);
    }

    /* access modifiers changed from: protected */
    public Connection a(SortedParameter sortedParameter) {
        String f = sortedParameter.f("processId");
        Connection a2 = ConnectionFactory.a(MibiConstants.a(MibiConstants.bN), this.f7453a);
        SortedParameter d = a2.d();
        d.a("processId", (Object) f);
        d.a(MibiConstants.eZ, (Object) (String) sortedParameter.a(MibiConstants.eZ));
        d.a(MibiConstants.dq, (Object) (Long) sortedParameter.a(MibiConstants.dq));
        d.a(MibiConstants.fm, (Object) (String) sortedParameter.a(MibiConstants.fm));
        d.a(MibiConstants.eY, (Object) RechargeManager.CHANNELS.TYUNICOMMSGPAY.getChannel());
        return a2;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void c(JSONObject jSONObject, Result result) throws PaymentException {
        try {
            result.mChargeOrderId = jSONObject.getString(MibiConstants.dr);
            if (TextUtils.isEmpty(result.mChargeOrderId)) {
                throw new ResultException("result has error");
            }
        } catch (JSONException e) {
            throw new ResultException((Throwable) e);
        }
    }
}
