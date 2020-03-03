package com.xiaomi.payment.deduct.model;

import android.content.Context;
import com.mibi.common.base.Model;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.rxjava.RxBaseErrorHandleTaskListener;
import com.xiaomi.payment.task.rxjava.RxCheckDeductTask;
import junit.framework.Assert;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CheckDeductOrderModel extends Model {

    /* renamed from: a  reason: collision with root package name */
    private RxCheckDeductTask f12258a;
    /* access modifiers changed from: private */
    public OnCheckDeductOrderListener b;

    public interface OnCheckDeductOrderListener {
        void a(int i, String str, Throwable th);

        void a(RxCheckDeductTask.Result result);
    }

    public CheckDeductOrderModel(Session session) {
        super(session);
        if (this.f12258a == null) {
            this.f12258a = new RxCheckDeductTask(l_(), c());
        }
    }

    public void a(String str, String str2, OnCheckDeductOrderListener onCheckDeductOrderListener) {
        Assert.assertNotNull(str);
        Assert.assertNotNull(str2);
        Assert.assertNotNull(onCheckDeductOrderListener);
        this.b = onCheckDeductOrderListener;
        CheckDeductOrderTaskListener checkDeductOrderTaskListener = new CheckDeductOrderTaskListener(l_());
        SortedParameter sortedParameter = new SortedParameter();
        sortedParameter.a("deductSignOrder", (Object) str2);
        sortedParameter.a("processId", (Object) str);
        this.f12258a.a(sortedParameter);
        Observable.create(this.f12258a).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(checkDeductOrderTaskListener);
    }

    private class CheckDeductOrderTaskListener extends RxBaseErrorHandleTaskListener<RxCheckDeductTask.Result> {
        private CheckDeductOrderTaskListener(Context context) {
            super(context);
        }

        /* access modifiers changed from: protected */
        public void a(int i, String str, Throwable th) {
            CheckDeductOrderModel.this.b.a(i, str, th);
        }

        /* access modifiers changed from: protected */
        public void a(RxCheckDeductTask.Result result) {
            CheckDeductOrderModel.this.b.a(result);
        }
    }
}
