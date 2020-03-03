package com.xiaomi.payment.task.rxjava;

import android.content.Context;
import com.mibi.common.data.Session;
import com.mibi.common.exception.PaymentException;
import com.mibi.common.exception.ResultException;
import com.mibi.common.rxjava.RxBasePaymentTask;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.task.rxjava.RxBaseCheckAuthAndPayTask.Result;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class RxBaseCheckAuthAndPayTask<TaskResult extends Result> extends RxBasePaymentTask<TaskResult> {

    public static class Result implements Serializable {
        public long mBalance;
        public String mBindPhoneUrl;
        public int mErrorCode;
        public String mErrorDesc;
        public long mGiftcardValue;
        public long mPartnerGiftcardValue;
        public String mPhoneNum;
        public boolean mUseGiftcard;
        public boolean mUsePartnerGiftcard;
    }

    public RxBaseCheckAuthAndPayTask(Context context, Session session, Class<TaskResult> cls) {
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
    public boolean b(JSONObject jSONObject, Result result) throws PaymentException {
        if (result.mErrorCode == 7002) {
            result.mBindPhoneUrl = jSONObject.optString(MibiConstants.dw);
            return true;
        } else if (result.mErrorCode != 7001) {
            return false;
        } else {
            try {
                result.mPhoneNum = jSONObject.getString(MibiConstants.dx);
                return true;
            } catch (JSONException e) {
                throw new ResultException((Throwable) e);
            }
        }
    }
}
