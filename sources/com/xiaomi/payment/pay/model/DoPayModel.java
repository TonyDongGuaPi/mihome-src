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
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.task.rxjava.RxDoPayTask;
import junit.framework.Assert;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

public class DoPayModel extends Model {

    /* renamed from: a  reason: collision with root package name */
    private RxDoPayTask f12377a;
    /* access modifiers changed from: private */
    public String b;
    /* access modifiers changed from: private */
    public long c;
    /* access modifiers changed from: private */
    public IDoPayListener d;

    public interface IDoPayListener {
        void a();

        void a(int i, String str, String str2);

        void a(Bundle bundle);
    }

    public DoPayModel(Session session) {
        super(session);
        if (this.f12377a == null) {
            this.f12377a = new RxDoPayTask(l_(), c());
        }
    }

    public void a(long j, SortedParameter sortedParameter, IDoPayListener iDoPayListener) {
        Assert.assertNotNull(sortedParameter);
        Assert.assertNotNull(iDoPayListener);
        this.c = j;
        this.d = iDoPayListener;
        this.b = sortedParameter.f("processId");
        this.f12377a.a(sortedParameter);
        DoPayTaskListener doPayTaskListener = new DoPayTaskListener(l_());
        doPayTaskListener.a().a((ExceptionDispatcher.ExceptionHandler) new DoPayTaskServerErrorCodeExceptionHandler(l_()));
        Observable.create(this.f12377a).observeOn(AndroidSchedulers.mainThread()).doOnSubscribe(new Action0() {
            public void call() {
                DoPayModel.this.d.a();
            }
        }).subscribeOn(Schedulers.io()).subscribe(doPayTaskListener);
    }

    private class DoPayTaskListener extends RxBaseErrorHandleTaskListener<RxDoPayTask.Result> {
        public DoPayTaskListener(Context context) {
            super(context);
        }

        /* access modifiers changed from: protected */
        public void a(int i, String str, Throwable th) {
            DoPayModel.this.d.a(i, str, (String) null);
        }

        /* access modifiers changed from: protected */
        public void a(RxDoPayTask.Result result) {
            DoPayModel.this.c().m().a(DoPayModel.this.b, "price", (Object) Long.valueOf(DoPayModel.this.c));
            Bundle bundle = new Bundle();
            bundle.putInt("status", 1);
            bundle.putString(MibiConstants.ey, result.mImageUrl);
            bundle.putSerializable(MibiConstants.dt, result.mEntryData);
            bundle.putString("result", result.mResult);
            bundle.putInt("resultCode", 1100);
            DoPayModel.this.d.a(bundle);
        }
    }

    private class DoPayTaskServerErrorCodeExceptionHandler extends ServerErrorCodeExceptionHandler {
        private Context b;

        public DoPayTaskServerErrorCodeExceptionHandler(Context context) {
            super(context);
            this.b = context;
        }

        /* access modifiers changed from: protected */
        public boolean a(int i, String str, Object obj) {
            String str2 = str + " : " + i;
            String str3 = "";
            if (i == 2001) {
                str2 = this.b.getString(R.string.mibi_error_mili_summary);
            } else if (i == 1986) {
                i = 7;
                str3 = ((RxDoPayTask.Result) obj).mResult;
            } else if (i == 1990) {
                i = 8;
                str3 = ((RxDoPayTask.Result) obj).mResult;
            } else if (i != 1991) {
                return false;
            } else {
                i = 13;
                str3 = ((RxDoPayTask.Result) obj).mResult;
            }
            DoPayModel.this.d.a(i, str2, str3);
            return true;
        }
    }
}
