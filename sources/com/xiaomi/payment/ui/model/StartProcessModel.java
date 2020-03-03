package com.xiaomi.payment.ui.model;

import android.content.Context;
import com.mibi.common.base.Model;
import com.mibi.common.data.Session;
import com.mibi.common.exception.rxjava.ExceptionDispatcher;
import com.mibi.common.exception.rxjava.NoPrivacyRightExceptionHandler;
import com.mibi.common.rxjava.RxBaseErrorHandleTaskListener;
import com.xiaomi.payment.task.rxjava.RxStartProcessTask;
import junit.framework.Assert;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class StartProcessModel extends Model {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public OnProcessStartListener f12538a;

    public interface OnProcessStartListener {
        void a(int i, String str);

        void a(int i, String str, Throwable th);

        void a(RxStartProcessTask.Result result);
    }

    public StartProcessModel(Session session) {
        super(session);
    }

    public void a(OnProcessStartListener onProcessStartListener) {
        Assert.assertNotNull(onProcessStartListener);
        this.f12538a = onProcessStartListener;
        ProcessTaskListener processTaskListener = new ProcessTaskListener(l_());
        processTaskListener.a().a((ExceptionDispatcher.ExceptionHandler) new NoPrivacyRightExceptionHandler(l_()) {
            public boolean a(int i, String str) {
                StartProcessModel.this.f12538a.a(i, str);
                return true;
            }
        });
        Observable.create(new RxStartProcessTask(l_(), c())).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(processTaskListener);
    }

    private class ProcessTaskListener extends RxBaseErrorHandleTaskListener<RxStartProcessTask.Result> {
        public ProcessTaskListener(Context context) {
            super(context);
        }

        /* access modifiers changed from: protected */
        public void a(int i, String str, Throwable th) {
            StartProcessModel.this.f12538a.a(i, str, th);
        }

        /* access modifiers changed from: protected */
        public void a(RxStartProcessTask.Result result) {
            StartProcessModel.this.f12538a.a(result);
        }
    }
}
