package com.xiaomi.payment.ui.fragment.query.presenter;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.mibi.common.base.Presenter;
import com.mibi.common.data.CommonConstants;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.exception.rxjava.ExceptionDispatcher;
import com.mibi.common.exception.rxjava.NetworkExceptionHandler;
import com.mibi.common.exception.rxjava.ServerErrorCodeExceptionHandler;
import com.mibi.common.rxjava.RxBaseErrorHandleTaskListener;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.task.rxjava.RxQueryTask;
import com.xiaomi.payment.ui.fragment.query.AutoQuerier;
import com.xiaomi.payment.ui.fragment.query.RechargeResultFragment;
import com.xiaomi.payment.ui.fragment.query.contract.QueryContract;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

public class RechargeQueryPresenter extends Presenter<QueryContract.View> implements QueryContract.Presenter {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12508a = "RechargeQueryPresenter";
    private String b;
    private AutoQuerier c;
    private long d;
    private RxQueryTask e;
    private AutoQuerier.AutoQuerierCallback f = new AutoQuerier.AutoQuerierCallback() {
        public void a(long j) {
            ((QueryContract.View) RechargeQueryPresenter.this.l()).a(j);
        }

        public void b(long j) {
            ((QueryContract.View) RechargeQueryPresenter.this.l()).b(j);
        }

        public void a() {
            ((QueryContract.View) RechargeQueryPresenter.this.l()).Q();
            RechargeQueryPresenter.this.n();
        }
    };

    public RechargeQueryPresenter() {
        super(QueryContract.View.class);
    }

    public void b(Bundle bundle) {
        super.b(bundle);
        this.b = n_().getString("processId");
        this.d = f().m().a(this.b, MibiConstants.hg, 0);
        if (this.d <= 0) {
            throw new IllegalArgumentException("mRechargeMibi should be greater than 0");
        } else if (this.e == null) {
            this.e = new RxQueryTask(e(), f());
        }
    }

    public void a(int[] iArr) {
        if (this.c == null) {
            this.c = new AutoQuerier(iArr, this.f);
            this.c.c();
        }
    }

    /* access modifiers changed from: private */
    public void n() {
        SortedParameter sortedParameter = new SortedParameter();
        sortedParameter.a("processId", (Object) this.b);
        sortedParameter.a(MibiConstants.gl, (Object) Long.valueOf(this.d));
        this.e.a(sortedParameter);
        RechargeQueryTaskListener rechargeQueryTaskListener = new RechargeQueryTaskListener(e());
        rechargeQueryTaskListener.a().a((ExceptionDispatcher.ExceptionHandler) new QueryNetworkExcptionTaskListener(e()));
        rechargeQueryTaskListener.a().a((ExceptionDispatcher.ExceptionHandler) new QueryServerErrorExcptionTaskListener(e()));
        Observable.create(this.e).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).doOnSubscribe(new Action0() {
            public void call() {
                ((QueryContract.View) RechargeQueryPresenter.this.l()).a(1, true);
            }
        }).doOnUnsubscribe(new Action0() {
            public void call() {
                ((QueryContract.View) RechargeQueryPresenter.this.l()).a(1, false);
            }
        }).subscribe(rechargeQueryTaskListener);
    }

    private class QueryNetworkExcptionTaskListener extends NetworkExceptionHandler {
        public QueryNetworkExcptionTaskListener(Context context) {
            super(context);
        }

        public boolean a(Throwable th, Bundle bundle, ExceptionDispatcher exceptionDispatcher) {
            RechargeQueryPresenter.this.a(bundle.getInt("errcode"), bundle.getString("errDesc"));
            return true;
        }
    }

    private class QueryServerErrorExcptionTaskListener extends ServerErrorCodeExceptionHandler {
        public QueryServerErrorExcptionTaskListener(Context context) {
            super(context);
        }

        /* access modifiers changed from: protected */
        public boolean e() {
            ((QueryContract.View) RechargeQueryPresenter.this.l()).N();
            return true;
        }

        /* access modifiers changed from: protected */
        public boolean a(int i, String str, Object obj) {
            RechargeQueryPresenter.this.a(i, str, (RxQueryTask.Result) obj);
            return true;
        }
    }

    private class RechargeQueryTaskListener extends RxBaseErrorHandleTaskListener<RxQueryTask.Result> {
        public RechargeQueryTaskListener(Context context) {
            super(context);
        }

        /* access modifiers changed from: protected */
        public void a(int i, String str, Throwable th) {
            RechargeQueryPresenter.this.a(i, str, (RxQueryTask.Result) null);
        }

        /* access modifiers changed from: protected */
        public void a(RxQueryTask.Result result) {
            if (TextUtils.equals(result.mChargeStatus, "TRADE_SUCCESS")) {
                RechargeQueryPresenter.this.a(result);
            } else if (TextUtils.equals(result.mChargeStatus, CommonConstants.Mgc.ab)) {
                RechargeQueryPresenter.this.b(result);
            } else if (TextUtils.equals(result.mChargeStatus, "TRADE_CLOSED")) {
                RechargeQueryPresenter.this.d(result);
            } else if (TextUtils.equals(result.mChargeStatus, CommonConstants.Mgc.ae)) {
                RechargeQueryPresenter.this.c(result);
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(RxQueryTask.Result result) {
        Bundle bundle = new Bundle();
        bundle.putInt("status", 1);
        bundle.putLong(MibiConstants.dh, result.mRechargeFee);
        bundle.putLong("balance", result.mBalance);
        bundle.putString(MibiConstants.ew, result.mHint);
        bundle.putSerializable(MibiConstants.di, result.mHintEntryData);
        bundle.putBoolean(MibiConstants.ex, result.mHasBanner);
        if (result.mHasBanner) {
            bundle.putString(MibiConstants.ey, result.mBannerPicUrl);
            bundle.putSerializable(MibiConstants.cJ, result.mBannerEntryData);
        }
        bundle.putSerializable(MibiConstants.cR, RechargeResultFragment.class);
        ((QueryContract.View) l()).g(bundle);
        ((QueryContract.View) l()).e(bundle);
    }

    /* access modifiers changed from: private */
    public void b(RxQueryTask.Result result) {
        long j = result.mRechargeFee;
        if (j <= 0) {
            j = this.d;
        }
        if (this.c.a()) {
            ((QueryContract.View) l()).a(j, e().getResources().getString(R.string.mibi_progress_warning_waiting), this.c.b());
            this.c.c();
            return;
        }
        ((QueryContract.View) l()).O();
    }

    /* access modifiers changed from: private */
    public void c(RxQueryTask.Result result) {
        a(1, result.mErrorDesc, result);
    }

    /* access modifiers changed from: private */
    public void d(RxQueryTask.Result result) {
        ((QueryContract.View) l()).O();
    }

    /* access modifiers changed from: private */
    public void a(int i, String str) {
        if (this.c.a()) {
            ((QueryContract.View) l()).a(this.d, str, this.c.b());
            this.c.c();
            return;
        }
        a(i, str, new RxQueryTask.Result());
    }

    /* access modifiers changed from: private */
    public void a(int i, String str, RxQueryTask.Result result) {
        Bundle bundle = new Bundle();
        bundle.putInt("status", 2);
        bundle.putInt(MibiConstants.da, i);
        bundle.putString(MibiConstants.db, str);
        if (result != null) {
            bundle.putString(MibiConstants.ew, result.mHint);
            bundle.putSerializable(MibiConstants.di, result.mHintEntryData);
        }
        bundle.putSerializable(MibiConstants.cR, RechargeResultFragment.class);
        ((QueryContract.View) l()).g(bundle);
    }
}
