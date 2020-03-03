package com.xiaomi.payment.task;

import android.content.Context;
import com.mibi.common.base.BasePaymentTask;
import com.mibi.common.data.Connection;
import com.mibi.common.data.ConnectionFactory;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.xiaomi.payment.data.MibiConstants;

public class VerifyRegetSmsCodeTask extends BasePaymentTask<Void, BasePaymentTask.Result> {
    public VerifyRegetSmsCodeTask(Context context, Session session) {
        super(context, session, BasePaymentTask.Result.class);
    }

    /* access modifiers changed from: protected */
    public Connection a(SortedParameter sortedParameter) {
        String f = sortedParameter.f("processId");
        Connection a2 = ConnectionFactory.a(MibiConstants.a(MibiConstants.bF), this.f7453a);
        a2.d().a("processId", (Object) f);
        return a2;
    }
}
