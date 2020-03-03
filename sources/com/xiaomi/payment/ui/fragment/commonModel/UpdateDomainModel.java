package com.xiaomi.payment.ui.fragment.commonModel;

import android.content.Context;
import com.mibi.common.base.Model;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.rxjava.RxBaseErrorHandleTaskListener;
import com.xiaomi.payment.task.rxjava.RxUpdateDomainTask;
import junit.framework.Assert;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UpdateDomainModel extends Model {

    /* renamed from: a  reason: collision with root package name */
    private RxUpdateDomainTask f12484a;
    /* access modifiers changed from: private */
    public OnUpdateDomainListener b;

    public interface OnUpdateDomainListener {
        void a(int i, String str, Throwable th);

        void a(RxUpdateDomainTask.Result result);
    }

    public UpdateDomainModel(Session session) {
        super(session);
        if (this.f12484a == null) {
            this.f12484a = new RxUpdateDomainTask(l_(), c());
        }
    }

    public void a(SortedParameter sortedParameter, OnUpdateDomainListener onUpdateDomainListener) {
        Assert.assertNotNull(onUpdateDomainListener);
        Assert.assertNotNull(sortedParameter);
        this.b = onUpdateDomainListener;
        this.f12484a.a(sortedParameter);
        Observable.create(this.f12484a).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new UpdateDomainTaskListener(l_()));
    }

    private class UpdateDomainTaskListener extends RxBaseErrorHandleTaskListener<RxUpdateDomainTask.Result> {
        public UpdateDomainTaskListener(Context context) {
            super(context);
        }

        /* access modifiers changed from: protected */
        public void a(int i, String str, Throwable th) {
            UpdateDomainModel.this.b.a(i, str, th);
        }

        /* access modifiers changed from: protected */
        public void a(RxUpdateDomainTask.Result result) {
            UpdateDomainModel.this.b.a(result);
        }
    }
}
