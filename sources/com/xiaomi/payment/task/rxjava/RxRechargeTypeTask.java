package com.xiaomi.payment.task.rxjava;

import android.content.Context;
import android.text.TextUtils;
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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RxRechargeTypeTask extends RxBasePaymentTask<Result> {

    public static class Result implements Serializable {
        public String mDirectPayDiscount;
        public RechargeType mLastChargeType;
        public HashMap<String, RechargeType> mRechargeMethodTypeMap = new HashMap<>();
        public ArrayList<RechargeType> mRechargeTypes = new ArrayList<>();
        public boolean mShowAll;
    }

    public RxRechargeTypeTask(Context context, Session session) {
        super(context, session, Result.class);
    }

    /* access modifiers changed from: protected */
    public Connection b(SortedParameter sortedParameter) {
        String f = sortedParameter.f("processId");
        long d = sortedParameter.d(MibiConstants.dq);
        String f2 = sortedParameter.f(MibiConstants.gp);
        boolean a2 = sortedParameter.a(MibiConstants.gw, false);
        Connection a3 = ConnectionFactory.a(MibiConstants.a(MibiConstants.bW), this.f7587a);
        SortedParameter d2 = a3.d();
        d2.a("processId", (Object) f);
        d2.a(MibiConstants.gw, (Object) Boolean.valueOf(a2));
        if (d > 0) {
            d2.a(MibiConstants.dq, (Object) Long.valueOf(d));
        }
        d2.a("platform", (Object) Client.q());
        d2.a("package", (Object) Client.F().e());
        d2.a(MibiConstants.gp, (Object) f2);
        d2.a("os", (Object) Client.p());
        d2.a("miuiVersion", (Object) Client.m());
        d2.a("miuiUiVersionCode", (Object) Integer.valueOf(Client.x()));
        d2.a(MibiConstants.ev, (Object) Integer.valueOf(Client.y()));
        d2.a(MibiConstants.ez, (Object) Client.A().a());
        a3.a(true);
        return a3;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void c(JSONObject jSONObject, Result result) throws PaymentException {
        try {
            b(jSONObject, result);
        } catch (JSONException e) {
            throw new ResultException((Throwable) e);
        }
    }

    private void b(JSONObject jSONObject, Result result) throws JSONException, ResultException {
        result.mShowAll = jSONObject.optBoolean(MibiConstants.fR, false);
        result.mDirectPayDiscount = jSONObject.optString(MibiConstants.gs);
        String optString = jSONObject.optString(MibiConstants.gr, "");
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
                if (TextUtils.equals(optString, rechargeType.mType)) {
                    result.mLastChargeType = rechargeType;
                }
                result.mRechargeTypes.add(rechargeType);
            }
            return;
        }
        throw new ResultException("recharge methods should not be empty");
    }
}
