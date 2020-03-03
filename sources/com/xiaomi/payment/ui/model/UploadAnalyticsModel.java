package com.xiaomi.payment.ui.model;

import com.mibi.common.base.Model;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.xiaomi.payment.task.rxjava.RxUploadAnalyticsTask;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class UploadAnalyticsModel extends Model {

    /* renamed from: a  reason: collision with root package name */
    private RxUploadAnalyticsTask f12541a;

    public UploadAnalyticsModel(Session session) {
        super(session);
        if (this.f12541a == null) {
            this.f12541a = new RxUploadAnalyticsTask(l_(), c());
        }
    }

    public void a(SortedParameter sortedParameter) {
        SortedParameter sortedParameter2 = new SortedParameter();
        sortedParameter2.a(sortedParameter);
        this.f12541a.a(sortedParameter2);
        Observable.create(this.f12541a).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Action1<RxUploadAnalyticsTask.Result>() {
            /* renamed from: a */
            public void call(RxUploadAnalyticsTask.Result result) {
            }
        }, (Action1<Throwable>) new Action1<Throwable>() {
            /* renamed from: a */
            public void call(Throwable th) {
            }
        });
    }
}
