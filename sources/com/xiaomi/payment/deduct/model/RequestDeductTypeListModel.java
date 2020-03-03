package com.xiaomi.payment.deduct.model;

import android.content.Context;
import com.mibi.common.base.Model;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.rxjava.RxBaseErrorHandleTaskListener;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.task.rxjava.RxDeductTypeTask;
import junit.framework.Assert;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RequestDeductTypeListModel extends Model {

    /* renamed from: a  reason: collision with root package name */
    private RxDeductTypeTask f12266a;
    /* access modifiers changed from: private */
    public OnRequestDeductTypeListListener b;

    public interface OnRequestDeductTypeListListener {
        void a(int i, String str, Throwable th);

        void a(RxDeductTypeTask.Result result);
    }

    public RequestDeductTypeListModel(Session session) {
        super(session);
        if (this.f12266a == null) {
            this.f12266a = new RxDeductTypeTask(l_(), c());
        }
    }

    public void a(String str, String str2, OnRequestDeductTypeListListener onRequestDeductTypeListListener) {
        Assert.assertNotNull(str);
        Assert.assertNotNull(str2);
        Assert.assertNotNull(onRequestDeductTypeListListener);
        this.b = onRequestDeductTypeListListener;
        DeductTypeListListener deductTypeListListener = new DeductTypeListListener(l_());
        SortedParameter sortedParameter = new SortedParameter();
        sortedParameter.a("processId", (Object) str);
        sortedParameter.a(MibiConstants.gp, (Object) str2);
        this.f12266a.a(sortedParameter);
        Observable.create(this.f12266a).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(deductTypeListListener);
    }

    private class DeductTypeListListener extends RxBaseErrorHandleTaskListener<RxDeductTypeTask.Result> {
        private DeductTypeListListener(Context context) {
            super(context);
        }

        /* access modifiers changed from: protected */
        public void a(int i, String str, Throwable th) {
            RequestDeductTypeListModel.this.b.a(i, str, th);
        }

        /* access modifiers changed from: protected */
        public void a(RxDeductTypeTask.Result result) {
            RequestDeductTypeListModel.this.b.a(result);
        }
    }
}
