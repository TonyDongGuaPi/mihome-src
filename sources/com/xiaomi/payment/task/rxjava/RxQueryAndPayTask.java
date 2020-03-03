package com.xiaomi.payment.task.rxjava;

import android.content.Context;
import android.text.TextUtils;
import com.mibi.common.account.FakeAccountLoader;
import com.mibi.common.data.Client;
import com.mibi.common.data.Connection;
import com.mibi.common.data.ConnectionFactory;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.exception.PaymentException;
import com.mibi.common.exception.ResultException;
import com.xiaomi.payment.data.EntryData;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.task.rxjava.RxBaseQueryTask;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class RxQueryAndPayTask extends RxBaseQueryTask<Result> {

    public static class Result extends RxBaseQueryTask.Result implements Serializable {
        public EntryData mEntryData;
        public String mImageUrl;
        public long mPayFee;
        public int mPayStatus;
        public String mResult;
    }

    public RxQueryAndPayTask(Context context, Session session) {
        super(context, session, Result.class);
    }

    /* access modifiers changed from: protected */
    public Connection b(SortedParameter sortedParameter) {
        String f = sortedParameter.f("processId");
        if (this.f7587a.e() instanceof FakeAccountLoader) {
            Connection a2 = ConnectionFactory.a(this.b, MibiConstants.a(MibiConstants.cg));
            SortedParameter d = a2.d();
            d.a("processId", (Object) f);
            d.a("imei", (Object) Client.A().i());
            return a2;
        }
        Connection a3 = ConnectionFactory.a(MibiConstants.a(MibiConstants.bQ), this.f7587a);
        a3.d().a("processId", (Object) f);
        return a3;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void c(JSONObject jSONObject, Result result) throws PaymentException {
        super.c(jSONObject, result);
        if (result.mRechargeSuccess) {
            try {
                int i = jSONObject.getInt(MibiConstants.en);
                result.mPayStatus = i;
                if (i == 200) {
                    try {
                        JSONObject jSONObject2 = jSONObject.getJSONObject("result");
                        long j = jSONObject.getLong("payFee");
                        result.mResult = jSONObject2.toString();
                        result.mPayFee = j;
                        if (TextUtils.isEmpty(result.mResult)) {
                            throw new ResultException("result has error");
                        } else if (result.mPayFee >= 0) {
                            result.mImageUrl = jSONObject.optString(MibiConstants.ey);
                            JSONObject optJSONObject = jSONObject.optJSONObject("entry");
                            if (optJSONObject != null) {
                                EntryData entryData = new EntryData();
                                entryData.parseEntryData(optJSONObject);
                                result.mEntryData = entryData;
                            }
                        } else {
                            throw new ResultException("result has error");
                        }
                    } catch (JSONException e) {
                        throw new ResultException((Throwable) e);
                    }
                } else {
                    JSONObject optJSONObject2 = jSONObject.optJSONObject("result");
                    long optLong = jSONObject.optLong("payFee", -1);
                    if (optJSONObject2 != null) {
                        result.mResult = optJSONObject2.toString();
                    }
                    if (optLong >= 0) {
                        result.mPayFee = optLong;
                    }
                }
            } catch (JSONException e2) {
                throw new ResultException((Throwable) e2);
            }
        }
    }
}
