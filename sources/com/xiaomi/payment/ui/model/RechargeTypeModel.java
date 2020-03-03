package com.xiaomi.payment.ui.model;

import android.content.Context;
import com.mibi.common.base.Model;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.rxjava.RxBaseErrorHandleTaskListener;
import com.xiaomi.payment.task.rxjava.RxRechargeTypeTask;
import junit.framework.Assert;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RechargeTypeModel extends Model {

    /* renamed from: a  reason: collision with root package name */
    private RxRechargeTypeTask f12536a;
    /* access modifiers changed from: private */
    public OnRechargeTypeListener b;

    public interface OnRechargeTypeListener {
        void a(int i, String str, Throwable th);

        void a(RxRechargeTypeTask.Result result);
    }

    public RechargeTypeModel(Session session) {
        super(session);
        if (this.f12536a == null) {
            this.f12536a = new RxRechargeTypeTask(l_(), c());
        }
    }

    public void a(SortedParameter sortedParameter, OnRechargeTypeListener onRechargeTypeListener) {
        Assert.assertNotNull(onRechargeTypeListener);
        this.b = onRechargeTypeListener;
        SortedParameter sortedParameter2 = new SortedParameter();
        sortedParameter2.a(sortedParameter);
        this.f12536a.a(sortedParameter2);
        Observable.create(this.f12536a).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new RechargeTypeTaskListener(l_()));
    }

    private class RechargeTypeTaskListener extends RxBaseErrorHandleTaskListener<RxRechargeTypeTask.Result> {
        public RechargeTypeTaskListener(Context context) {
            super(context);
        }

        /* access modifiers changed from: protected */
        public void a(int i, String str, Throwable th) {
            RechargeTypeModel.this.b.a(i, str, th);
        }

        /* access modifiers changed from: protected */
        public void a(RxRechargeTypeTask.Result result) {
            RechargeTypeModel.this.b.a(result);
        }
    }
}
