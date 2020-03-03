package com.xiaomi.payment.task.rxjava;

import android.content.Context;
import android.util.Log;
import com.mibi.common.account.FakeAccountLoader;
import com.mibi.common.data.Client;
import com.mibi.common.data.Connection;
import com.mibi.common.data.ConnectionFactory;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.data.Utils;
import com.mibi.common.exception.PaymentException;
import com.mibi.common.exception.ResultException;
import com.mibi.common.rxjava.RxBasePaymentTask;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.deduct.DeductManager;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class RxDoWxpaySignDeductTask extends RxBasePaymentTask<Result> {
    private static final String c = "RxDoWxpaySignDeduct";

    public static class Result implements Serializable {
        public String mAppId;
        public String mNonceStr;
        public String mPackageValue;
        public String mPartnerId;
        public String mPrepayId;
        public String mSign;
        public String mTimeStamp;
        public String mUrl;
    }

    public RxDoWxpaySignDeductTask(Context context, Session session) {
        super(context, session, Result.class);
    }

    /* access modifiers changed from: protected */
    public Connection b(SortedParameter sortedParameter) {
        Connection connection;
        String f = sortedParameter.f("processId");
        if (this.f7587a.e() instanceof FakeAccountLoader) {
            connection = ConnectionFactory.a(this.b, MibiConstants.a(MibiConstants.cw));
        } else {
            connection = ConnectionFactory.a(MibiConstants.a(MibiConstants.cv), this.f7587a);
        }
        SortedParameter d = connection.d();
        d.a("processId", (Object) f);
        d.a(MibiConstants.gA, (Object) DeductManager.CHANNELS.WXPAY.getChannel());
        d.a("imei", (Object) Client.A().i());
        return connection;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void c(JSONObject jSONObject, Result result) throws PaymentException {
        super.c(jSONObject, result);
        try {
            result.mUrl = jSONObject.getString("url");
            Connection a2 = ConnectionFactory.a(this.b, result.mUrl, false);
            a2.a(true);
            b(a2.e(), result);
        } catch (JSONException unused) {
            throw new ResultException("wxpay sign deduct result error");
        } catch (Exception e) {
            throw new ResultException((Throwable) e);
        }
    }

    private void b(JSONObject jSONObject, Result result) {
        try {
            String string = jSONObject.getString("appid");
            String string2 = jSONObject.getString(MibiConstants.fu);
            String string3 = jSONObject.getString("package");
            String string4 = jSONObject.getString(MibiConstants.fr);
            String string5 = jSONObject.getString(MibiConstants.fs);
            String string6 = jSONObject.getString("sign");
            String string7 = jSONObject.getString("timestamp");
            if (Utils.a(string, string2, string3, string4, string5, string6, string7)) {
                result.mAppId = string;
                result.mPartnerId = string4;
                result.mPrepayId = string5;
                result.mPackageValue = string3;
                result.mNonceStr = string2;
                result.mTimeStamp = string7;
                result.mSign = string6;
                return;
            }
            throw new ResultException("RxWXPayTask result has error");
        } catch (JSONException e) {
            Log.d(c, "RxDoWxpaySignDeductTask order json exception", e);
        } catch (ResultException e2) {
            Log.d(c, "RxDoWxpaySignDeductTask order content exception", e2);
        }
    }
}
