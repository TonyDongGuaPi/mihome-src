package com.xiaomi.payment.task.rxjava;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.mibi.common.data.Client;
import com.mibi.common.data.Connection;
import com.mibi.common.data.ConnectionFactory;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.exception.PaymentException;
import com.mibi.common.exception.ResultException;
import com.mibi.common.rxjava.RxBasePaymentTask;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.recharge.Recharge;
import com.xiaomi.payment.recharge.RechargeManager;
import com.xiaomi.payment.recharge.RechargeType;
import com.xiaomi.payment.task.rxjava.RxRechargeTypeTask;
import java.io.Serializable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RxStartNoAccountPaymentTask extends RxBasePaymentTask<Result> {
    private static final String c = "RxStartNoAccountPaymentTask";
    private String d;

    public static class Result extends RxRechargeTypeTask.Result implements Serializable {
        public String mOrderDesc;
        public long mOrderFee;
        public String mResult;
        public int mResultErrorCode;
        public String mResultErrorDesc;
        public String mTradeId;
    }

    public RxStartNoAccountPaymentTask(Context context, Session session) {
        super(context, session, Result.class);
    }

    /* access modifiers changed from: protected */
    public Connection b(SortedParameter sortedParameter) {
        String f = sortedParameter.f("processId");
        String f2 = sortedParameter.f(MibiConstants.gp);
        this.d = sortedParameter.f("order");
        Connection a2 = ConnectionFactory.a(this.b, MibiConstants.a(MibiConstants.ca));
        SortedParameter d2 = a2.d();
        d2.a("processId", (Object) f);
        d2.a("imei", (Object) Client.A().i());
        d2.a(MibiConstants.gp, (Object) f2);
        d2.a("order", (Object) this.d);
        d2.a("platform", (Object) Client.q());
        d2.a("package", (Object) Client.F().e());
        d2.a(MibiConstants.gp, (Object) f2);
        d2.a("os", (Object) Client.p());
        d2.a("miuiVersion", (Object) Client.m());
        d2.a("miuiUiVersionCode", (Object) Integer.valueOf(Client.x()));
        d2.a(MibiConstants.ev, (Object) Integer.valueOf(Client.y()));
        d2.a(MibiConstants.ez, (Object) Client.A().a());
        a2.a(false);
        return a2;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public boolean b(JSONObject jSONObject, Result result) throws PaymentException {
        JSONObject optJSONObject = jSONObject.optJSONObject("result");
        if (optJSONObject == null) {
            return false;
        }
        result.mResult = optJSONObject.toString();
        try {
            result.mResultErrorCode = jSONObject.getInt("errcode");
            result.mResultErrorDesc = jSONObject.optString("errDesc");
            return true;
        } catch (JSONException e) {
            throw new ResultException("error code not exists", e);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void c(JSONObject jSONObject, Result result) throws PaymentException {
        try {
            c(jSONObject, result);
            a(this.d, result);
        } catch (JSONException e) {
            throw new ResultException((Throwable) e);
        }
    }

    private void c(JSONObject jSONObject, Result result) throws JSONException, ResultException {
        JSONArray jSONArray = jSONObject.getJSONArray(MibiConstants.fS);
        if (jSONArray.length() > 0) {
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                RechargeType rechargeType = new RechargeType();
                JSONArray jSONArray2 = jSONObject2.getJSONArray("data");
                for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                    JSONObject jSONObject3 = jSONArray2.getJSONObject(i2);
                    String string = jSONObject3.getString("channel");
                    Recharge a2 = RechargeManager.a().a(string);
                    if (a2 != null) {
                        rechargeType.mRechargeMethods.add(a2.b().a(string, jSONObject3));
                        result.mRechargeMethodTypeMap.put(string, rechargeType);
                    }
                }
                rechargeType.mType = jSONObject2.getString("type");
                rechargeType.mIcon = jSONObject2.getString(MibiConstants.fU);
                rechargeType.mTitle = jSONObject2.getString("title");
                rechargeType.mTitleHint = jSONObject2.optString(MibiConstants.fW);
                rechargeType.mFavorite = jSONObject2.optBoolean(MibiConstants.fX, false);
                rechargeType.mCurrencyUnit = jSONObject2.getString(MibiConstants.fQ);
                result.mRechargeTypes.add(rechargeType);
            }
            return;
        }
        throw new ResultException("recharge methods should not be empty");
    }

    private void a(String str, Result result) throws ResultException {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                String string = jSONObject.getString(MibiConstants.dR);
                long j = jSONObject.getLong(MibiConstants.dQ);
                String string2 = jSONObject.getString("orderId");
                result.mOrderDesc = string;
                result.mOrderFee = j;
                result.mTradeId = string2;
            } catch (JSONException e) {
                Log.d(c, "noAccount order content error!");
                throw new ResultException((Throwable) e);
            }
        }
    }
}
