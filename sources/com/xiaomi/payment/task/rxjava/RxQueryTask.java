package com.xiaomi.payment.task.rxjava;

import android.content.Context;
import com.mibi.common.data.Connection;
import com.mibi.common.data.ConnectionFactory;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.data.Utils;
import com.mibi.common.exception.PaymentException;
import com.xiaomi.payment.data.EntryData;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.task.rxjava.RxBaseQueryTask;
import org.json.JSONObject;

public class RxQueryTask extends RxBaseQueryTask<Result> {

    public static class Result extends RxBaseQueryTask.Result {
        public EntryData mBannerEntryData;
        public String mBannerPicUrl;
        public boolean mHasBanner;
    }

    public RxQueryTask(Context context, Session session) {
        super(context, session, Result.class);
    }

    /* access modifiers changed from: protected */
    public Connection b(SortedParameter sortedParameter) {
        String f = sortedParameter.f("processId");
        Connection a2 = ConnectionFactory.a(MibiConstants.a(MibiConstants.bQ), this.f7587a);
        a2.d().a("processId", (Object) f);
        return a2;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void c(JSONObject jSONObject, Result result) throws PaymentException {
        super.c(jSONObject, result);
        if (result.mRechargeSuccess) {
            result.mBannerPicUrl = jSONObject.optString(MibiConstants.ey);
            JSONObject optJSONObject = jSONObject.optJSONObject("entry");
            if (optJSONObject != null) {
                result.mBannerEntryData = new EntryData();
                result.mBannerEntryData.parseEntryData(optJSONObject);
            }
            if (!Utils.a(result.mBannerPicUrl) || result.mBannerEntryData == null) {
                result.mHasBanner = false;
            } else {
                result.mHasBanner = true;
            }
        }
    }
}
