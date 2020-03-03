package com.xiaomi.payment.channel.model;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import com.mibi.common.base.Model;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.exception.rxjava.ExceptionDispatcher;
import com.mibi.common.exception.rxjava.ServerErrorCodeExceptionHandler;
import com.mibi.common.rxjava.RxBaseErrorHandleTaskListener;
import com.xiaomi.payment.channel.contract.PaytoolContract;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.task.rxjava.RxPaypalTask;
import junit.framework.Assert;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PaypalModel extends Model implements IPaytoolModel {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12192a = "PaypalModel";
    private static final long b = 1;
    private long c;
    private RxPaypalTask d;
    /* access modifiers changed from: private */
    public IPaytoolTaskListener e;

    public void e() {
    }

    public void f() {
    }

    public PaypalModel(Session session) {
        super(session);
        if (this.d == null) {
            this.d = new RxPaypalTask(l_(), c());
        }
    }

    public String[] d() {
        return new String[0];
    }

    public void a(IPaytoolTaskListener iPaytoolTaskListener) {
        this.e = iPaytoolTaskListener;
    }

    public void a(SortedParameter sortedParameter, IPaytoolTaskListener iPaytoolTaskListener) {
        Assert.assertNotNull(sortedParameter);
        Assert.assertNotNull(iPaytoolTaskListener);
        this.e = iPaytoolTaskListener;
        this.c = sortedParameter.d(MibiConstants.dd);
        this.d.a(sortedParameter);
        PaypalTaskListener paypalTaskListener = new PaypalTaskListener(l_());
        paypalTaskListener.a().a((ExceptionDispatcher.ExceptionHandler) new ServerErrorCodeExceptionHandler(l_()) {
            /* access modifiers changed from: protected */
            public boolean e() {
                PaypalModel.this.e.a();
                return true;
            }
        });
        Observable.create(this.d).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread()).subscribe(paypalTaskListener);
    }

    private class PaypalTaskListener extends RxBaseErrorHandleTaskListener<RxPaypalTask.Result> {
        public PaypalTaskListener(Context context) {
            super(context);
        }

        /* access modifiers changed from: protected */
        public void a(int i, String str, Throwable th) {
            PaypalModel.this.e.a(i, str, th);
        }

        /* access modifiers changed from: protected */
        public void a(RxPaypalTask.Result result) {
            PaypalModel.this.a(result);
        }
    }

    /* access modifiers changed from: private */
    public void a(RxPaypalTask.Result result) {
        final Bundle bundle = new Bundle();
        bundle.putString("url", result.c);
        bundle.putString(MibiConstants.dA, result.f12429a);
        bundle.putLong(MibiConstants.dd, this.c);
        bundle.putLong(MibiConstants.f12224de, this.c * 1);
        this.e.a(new PaytoolContract.Function<Fragment>() {
            public void a(Fragment fragment) {
                Assert.assertNotNull(fragment);
                PaypalModel.this.e.a(fragment.getActivity(), bundle);
            }
        });
    }

    public void a(int i, int i2, Bundle bundle) {
        if (i != 0) {
            return;
        }
        if (i2 == -1) {
            this.e.a(bundle.getLong(MibiConstants.dd, 0), bundle.getLong(MibiConstants.f12224de, 0));
            return;
        }
        this.e.a(11, l_().getString(R.string.mibi_paytool_error_msp, new Object[]{"Paypal"}), (Throwable) null);
    }

    public void a(Bundle bundle) {
        if (this.c > 0) {
            bundle.putLong(MibiConstants.dd, this.c);
        }
    }

    public void b(Bundle bundle) {
        long j = bundle.getLong(MibiConstants.dd, 0);
        if (j > 0) {
            this.c = j;
        }
    }
}
