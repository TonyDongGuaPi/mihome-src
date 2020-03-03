package com.xiaomi.payment.task;

import android.content.Context;
import com.mibi.common.account.FakeAccountLoader;
import com.mibi.common.base.BasePaymentTask;
import com.mibi.common.data.Connection;
import com.mibi.common.data.ConnectionFactory;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.exception.PaymentException;
import com.xiaomi.payment.data.MibiConstants;

public class UploadAnalyticsTask extends BasePaymentTask<Void, BasePaymentTask.Result> {
    public UploadAnalyticsTask(Context context, Session session) {
        super(context, session, BasePaymentTask.Result.class, true);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void b(SortedParameter sortedParameter, BasePaymentTask.Result result) throws PaymentException {
        if (!(this.f7453a.e() instanceof FakeAccountLoader)) {
            super.b(sortedParameter, result);
        }
    }

    /* access modifiers changed from: protected */
    public Connection a(SortedParameter sortedParameter) {
        Connection a2 = ConnectionFactory.a(MibiConstants.a(MibiConstants.bX), this.f7453a);
        a2.d().a(sortedParameter);
        return a2;
    }
}
