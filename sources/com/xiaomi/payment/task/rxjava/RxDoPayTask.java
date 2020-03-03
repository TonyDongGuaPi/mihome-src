package com.xiaomi.payment.task.rxjava;

import android.content.Context;
import android.text.TextUtils;
import com.mibi.common.data.Connection;
import com.mibi.common.data.ConnectionFactory;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.exception.PaymentException;
import com.mibi.common.exception.ResultException;
import com.xiaomi.payment.data.EntryData;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.task.rxjava.RxBaseCheckAuthAndPayTask;
import org.json.JSONException;
import org.json.JSONObject;

public class RxDoPayTask extends RxBaseCheckAuthAndPayTask<Result> {

    public static class Result extends RxBaseCheckAuthAndPayTask.Result {
        public EntryData mEntryData;
        public String mImageUrl;
        public String mResult;
    }

    public RxDoPayTask(Context context, Session session) {
        super(context, session, Result.class);
    }

    /* access modifiers changed from: protected */
    public Connection b(SortedParameter sortedParameter) {
        String f = sortedParameter.f("processId");
        boolean b = sortedParameter.b(MibiConstants.eE);
        boolean b2 = sortedParameter.b(MibiConstants.eG);
        int a2 = sortedParameter.a(MibiConstants.gS, 0);
        Connection a3 = ConnectionFactory.a(MibiConstants.a(MibiConstants.bD), this.f7587a);
        SortedParameter d = a3.d();
        d.a("processId", (Object) f);
        d.a(MibiConstants.eE, (Object) Boolean.valueOf(b));
        d.a(MibiConstants.eG, (Object) Boolean.valueOf(b2));
        d.a(MibiConstants.gS, (Object) Integer.valueOf(a2));
        return a3;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public boolean b(JSONObject jSONObject, Result result) throws PaymentException {
        super.b(jSONObject, (Object) result);
        JSONObject optJSONObject = jSONObject.optJSONObject("result");
        if (optJSONObject == null) {
            return false;
        }
        result.mResult = optJSONObject.toString();
        return true;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void c(JSONObject jSONObject, Result result) throws PaymentException {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("result");
            result.mImageUrl = jSONObject.optString(MibiConstants.ey);
            JSONObject optJSONObject = jSONObject.optJSONObject("entry");
            if (optJSONObject != null) {
                EntryData entryData = new EntryData();
                entryData.parseEntryData(optJSONObject);
                result.mEntryData = entryData;
            }
            result.mResult = jSONObject2.toString();
            if (TextUtils.isEmpty(result.mResult)) {
                throw new ResultException("result has error");
            }
        } catch (JSONException e) {
            throw new ResultException((Throwable) e);
        }
    }
}
