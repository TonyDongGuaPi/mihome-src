package com.xiaomi.payment.ui.fragment.query.presenter;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.mibi.common.base.Presenter;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.rxjava.RxBaseErrorHandleTaskListener;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.deduct.DeductManager;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.task.rxjava.RxDeductQueryTask;
import com.xiaomi.payment.ui.fragment.query.AutoQuerier;
import com.xiaomi.payment.ui.fragment.query.contract.DeductQueryContract;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DeductQueryPresenter extends Presenter<DeductQueryContract.View> implements DeductQueryContract.Presenter {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static final int[] f12499a = {0, 1, 1, 2, 3, 5, 7};
    private String b;
    /* access modifiers changed from: private */
    public String c;
    /* access modifiers changed from: private */
    public AutoQuerier d;
    private RxDeductQueryTask e;
    /* access modifiers changed from: private */
    public AutoQuerier.AutoQuerierCallback f = new AutoQuerier.AutoQuerierCallback() {
        public void a(long j) {
        }

        public void b(long j) {
        }

        public void a() {
            DeductQueryPresenter.this.o();
        }
    };

    public DeductQueryPresenter() {
        super(DeductQueryContract.View.class);
    }

    /* access modifiers changed from: protected */
    public void b(Bundle bundle) {
        super.b(bundle);
        this.b = n_().getString("processId");
        this.c = n_().getString(MibiConstants.gA);
        this.e = new RxDeductQueryTask(e(), f());
        o();
    }

    /* access modifiers changed from: private */
    public void o() {
        if (this.b == null || this.c == null) {
            throw new IllegalArgumentException("mProcessId or mDeductChannel should not be null here");
        }
        SortedParameter sortedParameter = new SortedParameter();
        sortedParameter.a("processId", (Object) this.b);
        sortedParameter.a(MibiConstants.gA, (Object) this.c);
        this.e.a(sortedParameter);
        Observable.create(this.e).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new DeductQueryTaskListener(e()));
    }

    private class DeductQueryTaskListener extends RxBaseErrorHandleTaskListener<RxDeductQueryTask.Result> {
        public DeductQueryTaskListener(Context context) {
            super(context);
        }

        /* access modifiers changed from: protected */
        public void a(int i, String str, Throwable th) {
            ((DeductQueryContract.View) DeductQueryPresenter.this.l()).e(str);
        }

        /* access modifiers changed from: protected */
        public void a(RxDeductQueryTask.Result result) {
            switch (result.mStatus) {
                case 0:
                    ((DeductQueryContract.View) DeductQueryPresenter.this.l()).e(DeductQueryPresenter.this.e().getResources().getString(R.string.mibi_deduct_query_error));
                    return;
                case 1:
                    Bundle bundle = new Bundle();
                    if (TextUtils.equals(DeductQueryPresenter.this.c, DeductManager.CHANNELS.MIPAY.getChannel())) {
                        bundle.putString(MibiConstants.gB, DeductQueryPresenter.this.e().getResources().getString(R.string.mibi_paytool_mipay));
                    } else if (TextUtils.equals(DeductQueryPresenter.this.c, DeductManager.CHANNELS.ALIPAY.getChannel())) {
                        bundle.putString(MibiConstants.gB, DeductQueryPresenter.this.e().getResources().getString(R.string.mibi_paytool_alipay));
                    }
                    bundle.putBoolean(MibiConstants.gK, true);
                    ((DeductQueryContract.View) DeductQueryPresenter.this.l()).e(bundle);
                    return;
                case 2:
                    if (DeductQueryPresenter.this.d == null) {
                        AutoQuerier unused = DeductQueryPresenter.this.d = new AutoQuerier(DeductQueryPresenter.f12499a, DeductQueryPresenter.this.f);
                    }
                    if (DeductQueryPresenter.this.d.a()) {
                        DeductQueryPresenter.this.d.c();
                        return;
                    } else {
                        ((DeductQueryContract.View) DeductQueryPresenter.this.l()).e(DeductQueryPresenter.this.e().getResources().getString(R.string.mibi_deduct_query_error));
                        return;
                    }
                default:
                    return;
            }
        }
    }
}
