package com.xiaomi.payment.task;

import android.content.Context;
import com.mibi.common.base.BasePaymentTask;
import com.mibi.common.data.Connection;
import com.mibi.common.data.ConnectionFactory;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.xiaomi.payment.data.MibiConstants;
import java.io.Serializable;

public class CheckTySMSCodeTask extends BasePaymentTask<Void, Result> {

    public static class Result extends BasePaymentTask.Result implements Serializable {
        public long mBalance;
    }

    public CheckTySMSCodeTask(Context context, Session session) {
        super(context, session, Result.class);
    }

    /* access modifiers changed from: protected */
    public Connection a(SortedParameter sortedParameter) {
        String f = sortedParameter.f("processId");
        String f2 = sortedParameter.f("smsCode");
        Connection a2 = ConnectionFactory.a(MibiConstants.a(MibiConstants.bO), this.f7453a);
        SortedParameter d = a2.d();
        d.a("processId", (Object) f);
        d.a(MibiConstants.fn, (Object) f2);
        return a2;
    }
}
