package com.xiaomi.payment.task.rxjava;

import com.mibi.common.account.FakeAccountLoader;
import com.mibi.common.data.PrivacyManager;
import com.mibi.common.data.Session;
import com.mibi.common.exception.PaymentException;
import com.mibi.common.rxjava.RxTask;

public class RxPrivacyUploadTask extends RxTask<Result> {

    /* renamed from: a  reason: collision with root package name */
    private Session f12431a;

    public static class Result {
    }

    public RxPrivacyUploadTask(Session session) {
        super(Result.class);
        this.f12431a = session;
    }

    /* access modifiers changed from: protected */
    public void a(Result result) throws PaymentException {
        if (!(this.f12431a.e() instanceof FakeAccountLoader)) {
            PrivacyManager.a(this.f12431a);
        }
    }
}
