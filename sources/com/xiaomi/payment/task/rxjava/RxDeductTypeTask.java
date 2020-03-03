package com.xiaomi.payment.task.rxjava;

import android.content.Context;
import com.mibi.common.account.FakeAccountLoader;
import com.mibi.common.data.Client;
import com.mibi.common.data.Connection;
import com.mibi.common.data.ConnectionFactory;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.exception.PaymentException;
import com.mibi.common.exception.ResultException;
import com.mibi.common.rxjava.RxBasePaymentTask;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.deduct.DeductManager;
import com.xiaomi.payment.recharge.Recharge;
import com.xiaomi.payment.recharge.RechargeType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RxDeductTypeTask extends RxBasePaymentTask<Result> {

    public static class Result implements Serializable {
        /* access modifiers changed from: private */
        public HashMap<String, RechargeType> mRechargeMethodTypeMap = new HashMap<>();
        public ArrayList<RechargeType> mRechargeTypes = new ArrayList<>();
    }

    public RxDeductTypeTask(Context context, Session session) {
        super(context, session, Result.class);
    }

    /* access modifiers changed from: protected */
    public Connection b(SortedParameter sortedParameter) {
        Connection connection;
        String f = sortedParameter.f("processId");
        String f2 = sortedParameter.f(MibiConstants.gp);
        if (this.f7587a.e() instanceof FakeAccountLoader) {
            connection = ConnectionFactory.a(this.b, MibiConstants.a(MibiConstants.ci));
        } else {
            connection = ConnectionFactory.a(MibiConstants.a(MibiConstants.ch), this.f7587a);
        }
        SortedParameter d = connection.d();
        d.a("processId", (Object) f);
        d.a("package", (Object) Client.F().e());
        d.a(MibiConstants.gp, (Object) f2);
        d.a(MibiConstants.gx, (Object) true);
        d.a("platform", (Object) Client.q());
        d.a("os", (Object) Client.p());
        d.a("miuiVersion", (Object) Client.m());
        d.a("miuiUiVersionCode", (Object) Integer.valueOf(Client.x()));
        d.a(MibiConstants.ev, (Object) Integer.valueOf(Client.y()));
        d.a(MibiConstants.ez, (Object) Client.A().a());
        connection.a(true);
        return connection;
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
        JSONArray jSONArray = jSONObject.getJSONArray(MibiConstants.fS);
        if (jSONArray.length() > 0) {
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                RechargeType rechargeType = new RechargeType();
                JSONArray jSONArray2 = jSONObject2.getJSONArray("data");
                for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                    JSONObject jSONObject3 = jSONArray2.getJSONObject(i2);
                    String string = jSONObject3.getString("channel");
                    jSONObject3.getString("title");
                    Recharge a2 = DeductManager.a().a(string);
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
}
