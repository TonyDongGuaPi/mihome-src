package com.xiaomi.payment.pay.model;

import android.content.Context;
import android.os.Bundle;
import com.mibi.common.base.Model;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.exception.rxjava.ExceptionDispatcher;
import com.mibi.common.exception.rxjava.ServerErrorCodeExceptionHandler;
import com.mibi.common.rxjava.RxBaseErrorHandleTaskListener;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.task.rxjava.RxBaseCheckAuthAndPayTask;
import com.xiaomi.payment.task.rxjava.RxCheckAuthTask;
import junit.framework.Assert;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CheckAuthModel extends Model {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public String f12374a;
    private RxCheckAuthTask b;
    /* access modifiers changed from: private */
    public OnCheckAuthListener c;

    public interface OnCheckAuthListener {
        void a();

        void a(int i, String str, Throwable th);

        void a(Bundle bundle);

        void b();

        void b(Bundle bundle);

        void c();

        void d();
    }

    public CheckAuthModel(Session session) {
        super(session);
        if (this.b == null) {
            this.b = new RxCheckAuthTask(l_(), c());
        }
    }

    public void a(SortedParameter sortedParameter, OnCheckAuthListener onCheckAuthListener) {
        Assert.assertNotNull(sortedParameter);
        Assert.assertNotNull(onCheckAuthListener);
        this.f12374a = sortedParameter.f("processId");
        this.c = onCheckAuthListener;
        this.b.a(sortedParameter);
        CheckAuthTaskListener checkAuthTaskListener = new CheckAuthTaskListener(l_());
        checkAuthTaskListener.a().a((ExceptionDispatcher.ExceptionHandler) new CheckAuthTaskServerErrorCodeExceptionHandler(l_()));
        Observable.create(this.b).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(checkAuthTaskListener);
    }

    private class CheckAuthTaskListener extends RxBaseErrorHandleTaskListener<RxBaseCheckAuthAndPayTask.Result> {
        public CheckAuthTaskListener(Context context) {
            super(context);
        }

        /* access modifiers changed from: protected */
        public void a(int i, String str, Throwable th) {
            CheckAuthModel.this.c.a(i, str, th);
        }

        /* access modifiers changed from: protected */
        public void a(RxBaseCheckAuthAndPayTask.Result result) {
            CheckAuthModel.this.c.d();
        }
    }

    private class CheckAuthTaskServerErrorCodeExceptionHandler extends ServerErrorCodeExceptionHandler {
        public CheckAuthTaskServerErrorCodeExceptionHandler(Context context) {
            super(context);
        }

        /* access modifiers changed from: protected */
        public boolean a(int i, String str, Object obj) {
            if (i == 1985) {
                CheckAuthModel.this.c.b();
                return true;
            } else if (i == 7001) {
                Bundle bundle = new Bundle();
                bundle.putString("processId", CheckAuthModel.this.f12374a);
                bundle.putString(MibiConstants.dl, ((RxBaseCheckAuthAndPayTask.Result) obj).mPhoneNum);
                bundle.putInt(MibiConstants.da, 7001);
                CheckAuthModel.this.c.a(bundle);
                return true;
            } else if (i != 7002) {
                return false;
            } else {
                Bundle bundle2 = new Bundle();
                bundle2.putString("processId", CheckAuthModel.this.f12374a);
                bundle2.putString(MibiConstants.dw, ((RxBaseCheckAuthAndPayTask.Result) obj).mBindPhoneUrl);
                bundle2.putInt(MibiConstants.da, 7002);
                CheckAuthModel.this.c.b(bundle2);
                return true;
            }
        }

        /* access modifiers changed from: protected */
        public boolean c() {
            CheckAuthModel.this.c.a();
            return true;
        }

        /* access modifiers changed from: protected */
        public boolean e() {
            CheckAuthModel.this.c.c();
            return true;
        }
    }
}
