package com.xiaomi.payment.task.rxjava;

import android.content.Context;
import com.mibi.common.data.Session;
import com.mibi.common.data.Utils;
import com.mibi.common.exception.PaymentException;
import com.mibi.common.exception.ResultException;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.task.rxjava.RxMessagePayTask;
import org.json.JSONException;
import org.json.JSONObject;

public class RxUmMsgPayTask extends RxMessagePayTask<Result> {

    public static class Result extends RxMessagePayTask.Result {

        /* renamed from: a  reason: collision with root package name */
        public String f12436a;
        public String b;
        public String e;
        public String f;
        public String g;
        public String h;
        public String i;
        public String j;
        public String k;
        public String l;
        public String m;
        public String n;
        public String o;
    }

    public RxUmMsgPayTask(Context context, Session session) {
        super(context, session, Result.class);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void c(JSONObject jSONObject, Result result) throws PaymentException {
        super.c(jSONObject, result);
        try {
            result.f12436a = result.d.getString(MibiConstants.fx);
            result.b = result.d.getString(MibiConstants.fy);
            result.e = result.d.getString(MibiConstants.fz);
            result.f = result.d.getString("orderid");
            result.g = result.d.getString(MibiConstants.fB);
            result.h = result.d.getString("amount");
            result.i = result.d.getString(MibiConstants.fD);
            result.j = result.d.getString(MibiConstants.fE);
            result.k = result.d.getString(MibiConstants.fF);
            result.l = result.d.getString(MibiConstants.fG);
            result.m = result.d.getString("appid");
            result.n = result.d.getString(MibiConstants.fI);
            result.o = result.d.getString(MibiConstants.fJ);
            if (!Utils.a(result.f12436a, result.b, result.e, result.f, result.g, result.h, result.i, result.j, result.k, result.l, result.m, result.n, result.o)) {
                throw new ResultException("result has error");
            }
        } catch (JSONException e) {
            throw new ResultException((Throwable) e);
        }
    }
}
