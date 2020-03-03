package com.xiaomi.payment.deduct.model;

import android.content.Context;
import com.mibi.common.base.Model;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.rxjava.RxBaseErrorHandleTaskListener;
import com.xiaomi.payment.task.rxjava.RxCheckSignDeductTask;
import junit.framework.Assert;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CheckSignDeductOrderModel extends Model {

    /* renamed from: a  reason: collision with root package name */
    private RxCheckSignDeductTask f12260a;
    /* access modifiers changed from: private */
    public OnCheckSignDeductOrderListener b;

    public interface OnCheckSignDeductOrderListener {
        void a(int i, String str, Throwable th);

        void a(RxCheckSignDeductTask.Result result);
    }

    public CheckSignDeductOrderModel(Session session) {
        super(session);
        if (this.f12260a == null) {
            this.f12260a = new RxCheckSignDeductTask(l_(), c());
        }
    }

    public void a(String str, String str2, OnCheckSignDeductOrderListener onCheckSignDeductOrderListener) {
        Assert.assertNotNull(str);
        Assert.assertNotNull(str2);
        Assert.assertNotNull(onCheckSignDeductOrderListener);
        this.b = onCheckSignDeductOrderListener;
        CheckSignDeductOrderTaskListener checkSignDeductOrderTaskListener = new CheckSignDeductOrderTaskListener(l_());
        SortedParameter sortedParameter = new SortedParameter();
        sortedParameter.a("deductSignOrder", (Object) str2);
        sortedParameter.a("processId", (Object) str);
        this.f12260a.a(sortedParameter);
        Observable.create(this.f12260a).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(checkSignDeductOrderTaskListener);
    }

    private class CheckSignDeductOrderTaskListener extends RxBaseErrorHandleTaskListener<RxCheckSignDeductTask.Result> {
        private CheckSignDeductOrderTaskListener(Context context) {
            super(context);
        }

        /* access modifiers changed from: protected */
        public void a(int i, String str, Throwable th) {
            CheckSignDeductOrderModel.this.b.a(i, str, th);
        }

        /* access modifiers changed from: protected */
        public void a(RxCheckSignDeductTask.Result result) {
            CheckSignDeductOrderModel.this.b.a(result);
        }
    }
}
