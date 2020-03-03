package com.xiaomi.payment.task.rxjava;

import android.content.Context;
import android.text.TextUtils;
import com.mibi.common.account.FakeAccountLoader;
import com.mibi.common.data.CommonConstants;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.exception.PaymentException;
import com.mibi.common.exception.ResultException;
import com.mibi.common.rxjava.RxBasePaymentTask;
import com.xiaomi.payment.data.EntryData;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.task.rxjava.RxBaseQueryTask.Result;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class RxBaseQueryTask<TaskResult extends Result> extends RxBasePaymentTask<TaskResult> {
    private long c;

    public static class Result implements Serializable {
        public long mBalance;
        public String mChargeStatus;
        public String mErrorDesc;
        public String mHint;
        public EntryData mHintEntryData;
        public long mRechargeFee;
        public boolean mRechargeSuccess = false;
    }

    public RxBaseQueryTask(Context context, Session session, Class<TaskResult> cls) {
        super(context, session, cls);
    }

    public void a(SortedParameter sortedParameter) {
        super.a(sortedParameter);
        this.c = sortedParameter.d(MibiConstants.gl);
    }

    /* access modifiers changed from: protected */
    public void a(JSONObject jSONObject, TaskResult taskresult) throws PaymentException {
        taskresult.mErrorDesc = jSONObject.optString("errDesc");
        taskresult.mBalance = jSONObject.optLong("balance", -1);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void c(JSONObject jSONObject, TaskResult taskresult) throws PaymentException {
        try {
            taskresult.mChargeStatus = jSONObject.getString(MibiConstants.dF);
            taskresult.mRechargeFee = jSONObject.getLong(MibiConstants.dq);
            taskresult.mHint = jSONObject.optString(MibiConstants.ew);
            JSONObject optJSONObject = jSONObject.optJSONObject("entry");
            if (optJSONObject != null) {
                taskresult.mHintEntryData = new EntryData();
                taskresult.mHintEntryData.parseEntryData(optJSONObject);
            }
            if (!TextUtils.equals(taskresult.mChargeStatus, "TRADE_SUCCESS") && !TextUtils.equals(taskresult.mChargeStatus, CommonConstants.Mgc.ab) && !TextUtils.equals(taskresult.mChargeStatus, "TRADE_CLOSED") && !TextUtils.equals(taskresult.mChargeStatus, CommonConstants.Mgc.ae)) {
                throw new ResultException("result has error");
            } else if (TextUtils.equals(taskresult.mChargeStatus, "TRADE_SUCCESS")) {
                if (!(this.f7587a.e() instanceof FakeAccountLoader) && taskresult.mBalance < 0) {
                    throw new ResultException("result has error");
                } else if (taskresult.mRechargeFee > 0) {
                    taskresult.mRechargeSuccess = true;
                } else {
                    throw new ResultException("result has error");
                }
            } else if (taskresult.mRechargeFee <= 0) {
                taskresult.mRechargeFee = this.c;
            }
        } catch (JSONException e) {
            throw new ResultException((Throwable) e);
        }
    }
}
