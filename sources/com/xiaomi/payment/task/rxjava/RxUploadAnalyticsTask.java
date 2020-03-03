package com.xiaomi.payment.task.rxjava;

import android.content.Context;
import com.mibi.common.account.FakeAccountLoader;
import com.mibi.common.data.Connection;
import com.mibi.common.data.ConnectionFactory;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.rxjava.RxBasePaymentTask;
import com.xiaomi.payment.data.MibiConstants;
import rx.Subscriber;

public class RxUploadAnalyticsTask extends RxBasePaymentTask<Result> {

    public static class Result {
    }

    public RxUploadAnalyticsTask(Context context, Session session) {
        super(context, session, Result.class);
    }

    /* renamed from: a */
    public void call(Subscriber<? super Result> subscriber) {
        if (this.f7587a.e() instanceof FakeAccountLoader) {
            subscriber.onCompleted();
        } else {
            super.call(subscriber);
        }
    }

    /* access modifiers changed from: protected */
    public Connection b(SortedParameter sortedParameter) {
        Connection a2 = ConnectionFactory.a(MibiConstants.a(MibiConstants.bX), this.f7587a);
        a2.d().a(sortedParameter);
        return a2;
    }
}
