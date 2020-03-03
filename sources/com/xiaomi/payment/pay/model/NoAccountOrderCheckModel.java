package com.xiaomi.payment.pay.model;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.mibi.common.base.Model;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.exception.rxjava.ExceptionDispatcher;
import com.mibi.common.exception.rxjava.ServerErrorCodeExceptionHandler;
import com.mibi.common.rxjava.RxBaseErrorHandleTaskListener;
import com.mibi.common.ui.TranslucentActivity;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.pay.model.IOrderCheckModel;
import com.xiaomi.payment.pay.noAccount.NoAccountPaymentOrderFragment;
import com.xiaomi.payment.pay.noAccount.autoPay.NoAccountAutoPayFragment;
import com.xiaomi.payment.task.rxjava.RxStartNoAccountPaymentTask;
import junit.framework.Assert;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NoAccountOrderCheckModel extends Model implements IOrderCheckModel {

    /* renamed from: a  reason: collision with root package name */
    private RxStartNoAccountPaymentTask f12381a;
    /* access modifiers changed from: private */
    public IOrderCheckModel.OnCheckAuthListener b;
    private String c;
    private String d;

    public NoAccountOrderCheckModel(Session session) {
        super(session);
        if (this.f12381a == null) {
            this.f12381a = new RxStartNoAccountPaymentTask(l_(), c());
        }
    }

    public void a(SortedParameter sortedParameter, IOrderCheckModel.OnCheckAuthListener onCheckAuthListener) {
        Assert.assertNotNull(sortedParameter);
        Assert.assertNotNull(onCheckAuthListener);
        this.c = sortedParameter.f("processId");
        this.d = sortedParameter.f(MibiConstants.cX);
        this.b = onCheckAuthListener;
        this.f12381a.a(sortedParameter);
        NoAccountCheckPaymentTaskListener noAccountCheckPaymentTaskListener = new NoAccountCheckPaymentTaskListener(l_());
        noAccountCheckPaymentTaskListener.a().a((ExceptionDispatcher.ExceptionHandler) new ServerErrorCodeExceptionHandler(l_()) {
            /* access modifiers changed from: protected */
            public boolean a(int i, String str, Object obj) {
                int i2;
                if (i == 1986) {
                    i2 = 7;
                } else if (i != 1991) {
                    return false;
                } else {
                    i2 = 13;
                }
                Bundle bundle = null;
                RxStartNoAccountPaymentTask.Result result = (RxStartNoAccountPaymentTask.Result) obj;
                if (result.mResult != null) {
                    bundle = new Bundle();
                    bundle.putString("payment_payment_result", result.mResult);
                }
                NoAccountOrderCheckModel.this.b.a(i2, str, bundle);
                return true;
            }
        });
        Observable.create(this.f12381a).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(noAccountCheckPaymentTaskListener);
    }

    private class NoAccountCheckPaymentTaskListener extends RxBaseErrorHandleTaskListener<RxStartNoAccountPaymentTask.Result> {
        public NoAccountCheckPaymentTaskListener(Context context) {
            super(context);
        }

        /* access modifiers changed from: protected */
        public void a(int i, String str, Throwable th) {
            NoAccountOrderCheckModel.this.b.a(i, str, new Bundle());
        }

        /* access modifiers changed from: protected */
        public void a(RxStartNoAccountPaymentTask.Result result) {
            if (result.mResult != null) {
                Bundle bundle = new Bundle();
                bundle.putString("payment_payment_result", result.mResult);
                NoAccountOrderCheckModel.this.b.a(result.mResultErrorCode, result.mResultErrorDesc, bundle);
                return;
            }
            NoAccountOrderCheckModel.this.a(result);
        }
    }

    /* access modifiers changed from: private */
    public void a(RxStartNoAccountPaymentTask.Result result) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(MibiConstants.cF, result);
        bundle.putString("processId", this.c);
        bundle.putLong("price", result.mOrderFee);
        bundle.putString(MibiConstants.cX, this.d);
        bundle.putSerializable("fragment", TextUtils.isEmpty(this.d) ? NoAccountPaymentOrderFragment.class : NoAccountAutoPayFragment.class);
        bundle.putSerializable("activity", TranslucentActivity.class);
        this.b.a(bundle);
    }
}
