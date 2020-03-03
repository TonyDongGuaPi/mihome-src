package com.xiaomi.payment.task;

import android.content.Context;
import com.mibi.common.base.BasePaymentTask;
import com.mibi.common.data.Connection;
import com.mibi.common.data.ConnectionFactory;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.xiaomi.payment.data.MibiConstants;

public class BillRecordDeleteTask extends BasePaymentTask<Void, BasePaymentTask.Result> {
    public BillRecordDeleteTask(Context context, Session session) {
        super(context, session, BasePaymentTask.Result.class);
    }

    /* access modifiers changed from: protected */
    public Connection a(SortedParameter sortedParameter) {
        String f = sortedParameter.f(MibiConstants.dH);
        String f2 = sortedParameter.f(MibiConstants.dI);
        Connection a2 = ConnectionFactory.a(MibiConstants.a(MibiConstants.bT), this.f7453a);
        SortedParameter d = a2.d();
        d.a(MibiConstants.dH, (Object) f);
        d.a(MibiConstants.dI, (Object) f2);
        return a2;
    }
}
