package com.xiaomi.payment.pay.model;

import android.content.Context;
import com.mibi.common.base.Model;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.rxjava.RxBaseErrorHandleTaskListener;
import com.xiaomi.payment.task.rxjava.RxUploadOrderGiftcardSettingTask;
import junit.framework.Assert;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

public class UploadOrderGiftcardModel extends Model {

    /* renamed from: a  reason: collision with root package name */
    private RxUploadOrderGiftcardSettingTask f12384a;
    /* access modifiers changed from: private */
    public OnUploadOrderGiftcardListener b;

    public interface OnUploadOrderGiftcardListener {
        void a();

        void a(int i, String str);

        void b();
    }

    public UploadOrderGiftcardModel(Session session) {
        super(session);
        if (this.f12384a == null) {
            this.f12384a = new RxUploadOrderGiftcardSettingTask(l_(), c());
        }
    }

    public void a(SortedParameter sortedParameter, OnUploadOrderGiftcardListener onUploadOrderGiftcardListener) {
        Assert.assertNotNull(sortedParameter);
        Assert.assertNotNull(onUploadOrderGiftcardListener);
        this.b = onUploadOrderGiftcardListener;
        this.f12384a.a(sortedParameter);
        Observable.create(this.f12384a).observeOn(AndroidSchedulers.mainThread()).doOnSubscribe(new Action0() {
            public void call() {
                UploadOrderGiftcardModel.this.b.a();
            }
        }).subscribeOn(Schedulers.io()).subscribe(new UploadOrderGiftTaskListener(l_()));
    }

    private class UploadOrderGiftTaskListener extends RxBaseErrorHandleTaskListener<RxUploadOrderGiftcardSettingTask.Result> {
        public UploadOrderGiftTaskListener(Context context) {
            super(context);
        }

        /* access modifiers changed from: protected */
        public void a(int i, String str, Throwable th) {
            UploadOrderGiftcardModel.this.b.a(i, str);
        }

        /* access modifiers changed from: protected */
        public void a(RxUploadOrderGiftcardSettingTask.Result result) {
            UploadOrderGiftcardModel.this.b.b();
        }
    }
}
