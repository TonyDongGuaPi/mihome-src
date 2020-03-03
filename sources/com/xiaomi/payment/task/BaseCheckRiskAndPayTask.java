package com.xiaomi.payment.task;

import android.content.Context;
import com.mibi.common.base.BasePaymentTask;
import com.mibi.common.data.Session;
import com.mibi.common.exception.PaymentException;
import com.mibi.common.exception.ResultException;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.task.BaseCheckRiskAndPayTask.Result;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class BaseCheckRiskAndPayTask<TaskResult extends Result> extends BasePaymentTask<Void, TaskResult> {

    public static class Result extends BasePaymentTask.Result implements Serializable {
        public long mBalance;
        public String mBindPhoneUrl;
        public long mGiftcardValue;
        public long mPartnerGiftcardValue;
        public String mPhoneNum;
        public boolean mUseGiftcard;
        public boolean mUsePartnerGiftcard;
    }

    public BaseCheckRiskAndPayTask(Context context, Session session, Class<TaskResult> cls) {
        super(context, session, cls);
    }

    /* access modifiers changed from: protected */
    public void a(JSONObject jSONObject, Result result) {
        long optLong = jSONObject.optLong("balance", -1);
        long optLong2 = jSONObject.optLong("giftcardValue", -1);
        boolean optBoolean = jSONObject.optBoolean(MibiConstants.eE, true);
        long optLong3 = jSONObject.optLong(MibiConstants.eJ, -1);
        boolean optBoolean2 = jSONObject.optBoolean(MibiConstants.eG, true);
        result.mBalance = optLong;
        result.mGiftcardValue = optLong2;
        result.mUseGiftcard = optBoolean;
        result.mPartnerGiftcardValue = optLong3;
        result.mUsePartnerGiftcard = optBoolean2;
    }

    /* access modifiers changed from: protected */
    public void b(JSONObject jSONObject, Result result) throws PaymentException {
        if (result.mErrorCode == 7002) {
            result.mBindPhoneUrl = jSONObject.optString(MibiConstants.dw);
        } else if (result.mErrorCode == 7001) {
            try {
                result.mPhoneNum = jSONObject.getString(MibiConstants.dx);
            } catch (JSONException e) {
                throw new ResultException((Throwable) e);
            }
        }
    }
}
