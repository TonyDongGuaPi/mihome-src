package com.xiaomi.payment.ui.fragment.query.presenter;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.mibi.common.base.Presenter;
import com.mibi.common.data.CommonConstants;
import com.mibi.common.data.MemoryStorage;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.exception.rxjava.ExceptionDispatcher;
import com.mibi.common.exception.rxjava.NetworkExceptionHandler;
import com.mibi.common.rxjava.RxBaseErrorHandleTaskListener;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.task.rxjava.RxQueryAndPayTask;
import com.xiaomi.payment.ui.fragment.query.AutoQuerier;
import com.xiaomi.payment.ui.fragment.query.contract.QueryContract;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

public class PayQueryPresenter extends Presenter<QueryContract.View> implements QueryContract.Presenter {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12502a = "PayQueryPresenter";
    private String b;
    private AutoQuerier c;
    private RxQueryAndPayTask d;
    private long e;
    private AutoQuerier.AutoQuerierCallback f = new AutoQuerier.AutoQuerierCallback() {
        public void a(long j) {
            ((QueryContract.View) PayQueryPresenter.this.l()).a(j);
        }

        public void b(long j) {
            ((QueryContract.View) PayQueryPresenter.this.l()).b(j);
        }

        public void a() {
            ((QueryContract.View) PayQueryPresenter.this.l()).Q();
            PayQueryPresenter.this.n();
        }
    };

    public PayQueryPresenter() {
        super(QueryContract.View.class);
    }

    public void b(Bundle bundle) {
        super.b(bundle);
        MemoryStorage m = f().m();
        this.b = n_().getString("processId");
        this.e = m.a(this.b, "price", 0);
        if (this.e <= 0) {
            throw new IllegalArgumentException("mPayMibi should be greater than 0");
        } else if (this.d == null) {
            this.d = new RxQueryAndPayTask(e(), f());
        }
    }

    public void a(int[] iArr) {
        if (this.c == null) {
            this.c = new AutoQuerier(iArr, this.f);
            this.c.c();
        }
    }

    public void n() {
        SortedParameter sortedParameter = new SortedParameter();
        sortedParameter.a("processId", (Object) this.b);
        sortedParameter.a(MibiConstants.gl, (Object) Long.valueOf(this.e));
        this.d.a(sortedParameter);
        PayQueryTaskListener payQueryTaskListener = new PayQueryTaskListener(e());
        payQueryTaskListener.a().a((ExceptionDispatcher.ExceptionHandler) new QueryNetworkExcptionTaskListener(e()));
        Observable.create(this.d).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).doOnSubscribe(new Action0() {
            public void call() {
                ((QueryContract.View) PayQueryPresenter.this.l()).a(1, true);
            }
        }).doOnUnsubscribe(new Action0() {
            public void call() {
                ((QueryContract.View) PayQueryPresenter.this.l()).a(1, false);
            }
        }).subscribe(payQueryTaskListener);
    }

    private class PayQueryTaskListener extends RxBaseErrorHandleTaskListener<RxQueryAndPayTask.Result> {
        public PayQueryTaskListener(Context context) {
            super(context);
        }

        /* access modifiers changed from: protected */
        public void a(int i, String str, Throwable th) {
            PayQueryPresenter.this.a(i, str, (RxQueryAndPayTask.Result) null);
        }

        /* access modifiers changed from: protected */
        public void a(RxQueryAndPayTask.Result result) {
            if (TextUtils.equals(result.mChargeStatus, "TRADE_SUCCESS")) {
                PayQueryPresenter.this.d(result);
            } else if (TextUtils.equals(result.mChargeStatus, CommonConstants.Mgc.ab)) {
                PayQueryPresenter.this.a(result);
            } else if (TextUtils.equals(result.mChargeStatus, "TRADE_CLOSED")) {
                PayQueryPresenter.this.c(result);
            } else if (TextUtils.equals(result.mChargeStatus, CommonConstants.Mgc.ae)) {
                PayQueryPresenter.this.b(result);
            }
        }
    }

    private class QueryNetworkExcptionTaskListener extends NetworkExceptionHandler {
        public QueryNetworkExcptionTaskListener(Context context) {
            super(context);
        }

        public boolean a(Throwable th, Bundle bundle, ExceptionDispatcher exceptionDispatcher) {
            PayQueryPresenter.this.a(bundle.getInt("errcode"), bundle.getString("errDesc"));
            return true;
        }
    }

    /* access modifiers changed from: private */
    public void a(RxQueryAndPayTask.Result result) {
        long j = result.mRechargeFee;
        if (j <= 0) {
            j = this.e;
        }
        if (this.c.a()) {
            a(j, e().getResources().getString(R.string.mibi_progress_warning_waiting), this.c.b());
            this.c.c();
            return;
        }
        o();
    }

    /* access modifiers changed from: private */
    public void a(int i, String str) {
        if (this.c.a()) {
            ((QueryContract.View) l()).a(this.e, str, this.c.b());
            this.c.c();
            return;
        }
        a(i, str, new RxQueryAndPayTask.Result());
    }

    /* access modifiers changed from: private */
    public void b(RxQueryAndPayTask.Result result) {
        a(1, result.mErrorDesc, result);
    }

    /* access modifiers changed from: private */
    public void c(RxQueryAndPayTask.Result result) {
        o();
    }

    /* access modifiers changed from: private */
    public void d(RxQueryAndPayTask.Result result) {
        if (result.mPayStatus == 200) {
            e(result);
        } else if (result.mPayStatus == 1985) {
            a(1, e().getResources().getString(R.string.mibi_error_expired_summary), result);
        } else if (result.mPayStatus == 2001) {
            a(0, e().getResources().getString(R.string.mibi_error_mili_summary), result);
        } else if (result.mPayStatus == 1986) {
            a(7, e().getResources().getString(R.string.mibi_error_has_bought_summary), result);
        } else if (result.mPayStatus == 1990) {
            a(8, e().getResources().getString(R.string.mibi_error_user_id_mismatch_summary), result);
        } else if (result.mPayStatus == 1991) {
            a(13, e().getResources().getString(R.string.mibi_error_illegal_order_summary), result);
        } else if (result.mPayStatus == 1993) {
            a(9, e().getResources().getString(R.string.mibi_error_frozen_summary), result);
        } else {
            a(1, e().getResources().getString(R.string.mibi_error_common_summary), result);
        }
    }

    /* access modifiers changed from: private */
    public void a(int i, String str, RxQueryAndPayTask.Result result) {
        Bundle bundle = new Bundle();
        bundle.putInt(MibiConstants.da, i);
        bundle.putString(MibiConstants.db, str);
        if (result == null || !result.mRechargeSuccess) {
            bundle.putInt("resultCode", 1001);
        } else {
            bundle.putInt("resultCode", 1003);
        }
        ((QueryContract.View) l()).f(bundle);
    }

    private void e(RxQueryAndPayTask.Result result) {
        Bundle bundle = new Bundle();
        bundle.putInt("status", 1);
        bundle.putString(MibiConstants.ey, result.mImageUrl);
        bundle.putSerializable(MibiConstants.dt, result.mEntryData);
        ((QueryContract.View) l()).e(bundle);
        bundle.putString("result", result.mResult);
        bundle.putInt("resultCode", 1004);
        ((QueryContract.View) l()).g(bundle);
    }

    private void o() {
        ((QueryContract.View) l()).O();
        Bundle bundle = new Bundle();
        bundle.putInt("status", 3);
        bundle.putString("result", "");
        bundle.putInt("resultCode", 1002);
        ((QueryContract.View) l()).g(bundle);
    }

    private void a(long j, String str, int i) {
        ((QueryContract.View) l()).a(j, str, i);
    }
}
