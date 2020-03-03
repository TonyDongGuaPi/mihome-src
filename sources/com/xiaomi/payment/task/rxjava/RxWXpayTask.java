package com.xiaomi.payment.task.rxjava;

import android.content.Context;
import android.util.Log;
import com.mibi.common.account.FakeAccountLoader;
import com.mibi.common.data.Connection;
import com.mibi.common.data.ConnectionFactory;
import com.mibi.common.data.Session;
import com.mibi.common.data.Utils;
import com.mibi.common.exception.PaymentException;
import com.mibi.common.exception.ResultException;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.task.rxjava.RxPaytoolTask;
import org.json.JSONException;
import org.json.JSONObject;

public class RxWXpayTask extends RxPaytoolTask<Result> {
    private static final String c = "RxWXPayTask";

    public static class Result extends RxPaytoolTask.Result {

        /* renamed from: a  reason: collision with root package name */
        public String f12439a;
        public String d;
        public String e;
        public String f;
        public String g;
        public String h;
        public String i;
    }

    public RxWXpayTask(Context context, Session session) {
        super(context, session, Result.class);
    }

    /* access modifiers changed from: protected */
    public String c() {
        if (this.f7587a.e() instanceof FakeAccountLoader) {
            return MibiConstants.a(MibiConstants.cd);
        }
        return MibiConstants.a(MibiConstants.bJ);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void c(JSONObject jSONObject, Result result) throws PaymentException {
        super.c(jSONObject, result);
        try {
            Connection a2 = ConnectionFactory.a(this.b, result.c, false);
            a2.a(true);
            b(a2.e(), result);
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
                result.f12439a = string;
                result.d = string4;
                result.e = string5;
                result.f = string3;
                result.g = string2;
                result.h = string7;
                result.i = string6;
                return;
            }
            throw new ResultException("RxWXPayTask result has error");
        } catch (JSONException e) {
            Log.d(c, "RxWXPayTask order json exception", e);
        } catch (ResultException e2) {
            Log.d(c, "RxWXPayTask order content exception", e2);
        }
    }
}
