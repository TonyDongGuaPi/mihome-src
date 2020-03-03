package com.xiaomi.payment.task.rxjava;

import android.content.Context;
import android.util.Log;
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
import java.io.Serializable;
import org.json.JSONObject;

public class RxDoAlipaySignDeductTask extends RxBasePaymentTask<Result> {
    private static final String c = "RxDoAlipaySignDeduct";

    public static class Result implements Serializable {
        public String mUrl;
    }

    public RxDoAlipaySignDeductTask(Context context, Session session) {
        super(context, session, Result.class);
    }

    /* access modifiers changed from: protected */
    public Connection b(SortedParameter sortedParameter) {
        Connection connection;
        String f = sortedParameter.f("processId");
        if (this.f7587a.e() instanceof FakeAccountLoader) {
            connection = ConnectionFactory.a(this.b, MibiConstants.a(MibiConstants.co));
        } else {
            connection = ConnectionFactory.a(MibiConstants.a(MibiConstants.cm), this.f7587a);
        }
        SortedParameter d = connection.d();
        d.a("processId", (Object) f);
        d.a(MibiConstants.gA, (Object) DeductManager.CHANNELS.ALIPAY.getChannel());
        d.a("imei", (Object) Client.A().i());
        return connection;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void c(JSONObject jSONObject, Result result) throws PaymentException {
        super.c(jSONObject, result);
        try {
            result.mUrl = jSONObject.optString("url");
            Connection a2 = ConnectionFactory.a(this.b, result.mUrl, false);
            a2.a(true);
            JSONObject e = a2.e();
            try {
                int optInt = e.optInt("errcode");
                if (optInt == 200) {
                    result.mUrl = e.optString("url");
                    return;
                }
                Log.e(c, "get alipay url error : " + optInt);
                throw new Exception();
            } catch (Exception e2) {
                Log.e(c, "parse alipay redirect url error");
                throw new ResultException((Throwable) e2);
            }
        } catch (Exception e3) {
            Log.e(c, "parse alipay url error ");
            throw new ResultException((Throwable) e3);
        }
    }
}
