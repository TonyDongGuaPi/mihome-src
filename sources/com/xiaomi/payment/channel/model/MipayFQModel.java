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
import com.mipay.sdk.MipayFactory;
import com.xiaomi.payment.channel.contract.PaytoolContract;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.mipay.AccountProvider;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.task.rxjava.RxMipayFQTask;
import junit.framework.Assert;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MipayFQModel extends Model implements IPaytoolModel {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12184a = "MipayFQModel";
    private static final long b = 1;
    private long c;
    private RxMipayFQTask d;
    /* access modifiers changed from: private */
    public IPaytoolTaskListener e;

    public String[] d() {
        return null;
    }

    public void e() {
    }

    public void f() {
    }

    public MipayFQModel(Session session) {
        super(session);
        if (this.d == null) {
            this.d = new RxMipayFQTask(l_(), c());
        }
    }

    public void a(IPaytoolTaskListener iPaytoolTaskListener) {
        this.e = iPaytoolTaskListener;
    }

    public void a(SortedParameter sortedParameter, IPaytoolTaskListener iPaytoolTaskListener) {
        Assert.assertNotNull(sortedParameter);
        Assert.assertNotNull(iPaytoolTaskListener);
        this.e = iPaytoolTaskListener;
        this.c = sortedParameter.d(MibiConstants.dd);
        if (sortedParameter == null) {
            sortedParameter = new SortedParameter();
        }
        this.d.a(sortedParameter);
        MipayFQTaskListener mipayFQTaskListener = new MipayFQTaskListener(l_());
        mipayFQTaskListener.a().a((ExceptionDispatcher.ExceptionHandler) new ServerErrorCodeExceptionHandler(l_()) {
            /* access modifiers changed from: protected */
            public boolean e() {
                MipayFQModel.this.e.a();
                return true;
            }
        });
        Observable.create(this.d).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread()).subscribe(mipayFQTaskListener);
    }

    private class MipayFQTaskListener extends RxBaseErrorHandleTaskListener<RxMipayFQTask.Result> {
        public MipayFQTaskListener(Context context) {
            super(context);
        }

        /* access modifiers changed from: protected */
        public void a(int i, String str, Throwable th) {
            MipayFQModel.this.e.a(i, str, th);
        }

        /* access modifiers changed from: protected */
        public void a(RxMipayFQTask.Result result) {
            MipayFQModel.this.a(result.f12427a);
        }
    }

    /* access modifiers changed from: private */
    public void a(final String str) {
        this.e.a(new PaytoolContract.Function<Fragment>() {
            public void a(Fragment fragment) {
                Assert.assertNotNull(fragment);
                AccountProvider accountProvider = new AccountProvider(fragment.getActivity());
                Bundle bundle = new Bundle();
                bundle.putBoolean("skipSuccess", true);
                MipayFactory.get(fragment.getActivity(), accountProvider).pay(fragment, str, bundle);
            }
        });
    }

    public void a(int i, int i2, Bundle bundle) {
        if (i == 424) {
            if (bundle != null) {
                int i3 = bundle.getInt("code", 2);
                if (i3 == 0) {
                    this.e.a(this.c, this.c * 1);
                } else if (i3 == 2) {
                    this.e.b();
                } else {
                    this.e.a(11, l_().getResources().getString(R.string.mibi_mipay_error), (Throwable) null);
                }
            } else {
                this.e.a(11, l_().getResources().getString(R.string.mibi_mipay_error), (Throwable) null);
            }
            MipayFactory.release();
        }
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
