package com.xiaomi.payment.task.rxjava;

import android.content.Context;
import com.mibi.common.data.Connection;
import com.mibi.common.data.ConnectionFactory;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.exception.PaymentException;
import com.mibi.common.rxjava.RxBasePaymentTask;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.deduct.DeductManager;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class RxDoMipayDeductTask extends RxBasePaymentTask<Result> {

    public static class Result implements Serializable {
        public String mCancelMerchant;
        public String mGoodsName;
        public String mMerchant;
        public String mRequestData;
    }

    public RxDoMipayDeductTask(Context context, Session session) {
        super(context, session, Result.class);
    }

    /* access modifiers changed from: protected */
    public Connection b(SortedParameter sortedParameter) {
        String f = sortedParameter.f("processId");
        Connection a2 = ConnectionFactory.a(MibiConstants.a(MibiConstants.ct), this.f7587a);
        SortedParameter d = a2.d();
        d.a(MibiConstants.gA, (Object) DeductManager.CHANNELS.MIPAY.getChannel());
        d.a("processId", (Object) f);
        return a2;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void c(JSONObject jSONObject, Result result) throws PaymentException {
        super.c(jSONObject, result);
        result.mRequestData = jSONObject.optString("requestData");
        try {
            result.mGoodsName = jSONObject.getString(MibiConstants.gL);
            result.mMerchant = jSONObject.getString("merchantName");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        result.mCancelMerchant = jSONObject.optString(MibiConstants.gE, "");
    }
}
