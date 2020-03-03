package com.xiaomi.payment.pay.presenter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.mibi.common.base.Presenter;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.decorator.AutoSave;
import com.mibi.common.exception.rxjava.AccountExceptionHandler;
import com.mibi.common.exception.rxjava.ExceptionDispatcher;
import com.mibi.common.rxjava.RxBaseErrorHandleTaskListener;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.pay.contract.PaymentCheckPasswordContract;
import com.xiaomi.payment.pay.model.CheckAuthModel;
import com.xiaomi.payment.task.rxjava.RxLoginTask;
import junit.framework.Assert;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PaymentCheckPasswordPresenter extends Presenter<PaymentCheckPasswordContract.View> implements PaymentCheckPasswordContract.Presenter {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12392a = "ProgressPresenterDel";
    private RxLoginTask b;
    /* access modifiers changed from: private */
    public CheckAuthModel c;
    /* access modifiers changed from: private */
    @AutoSave.AutoSavable
    public String d;

    public PaymentCheckPasswordPresenter() {
        super(PaymentCheckPasswordContract.View.class);
    }

    public void b(Bundle bundle) {
        super.b(bundle);
        n();
    }

    private void n() {
        Bundle n_ = n_();
        Assert.assertNotNull(n_);
        this.d = n_.getString("processId");
    }

    public void a(Activity activity, String str, String str2) {
        SortedParameter sortedParameter = new SortedParameter();
        sortedParameter.a("processId", (Object) this.d);
        sortedParameter.a("userName", (Object) str);
        sortedParameter.a("password", (Object) str2);
        this.b = new RxLoginTask(activity);
        this.b.a(sortedParameter);
        LoginTaskListener loginTaskListener = new LoginTaskListener(e());
        loginTaskListener.a().a((ExceptionDispatcher.ExceptionHandler) new AccountExceptionHandler(e()) {
            public boolean a(Throwable th, Bundle bundle, ExceptionDispatcher exceptionDispatcher) {
                super.a(th, bundle, exceptionDispatcher);
                ((PaymentCheckPasswordContract.View) PaymentCheckPasswordPresenter.this.l()).a(bundle.getInt("errcode"), bundle.getString("errDesc"));
                return true;
            }
        });
        Observable.create(this.b).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(loginTaskListener);
    }

    private class LoginTaskListener extends RxBaseErrorHandleTaskListener<RxLoginTask.Result> {
        public LoginTaskListener(Context context) {
            super(context);
        }

        /* access modifiers changed from: protected */
        public void a(int i, String str, Throwable th) {
            ((PaymentCheckPasswordContract.View) PaymentCheckPasswordPresenter.this.l()).a(i, str, (Throwable) null);
        }

        /* access modifiers changed from: protected */
        public void a(RxLoginTask.Result result) {
            if (result.e == 0) {
                SortedParameter sortedParameter = new SortedParameter();
                sortedParameter.a("processId", (Object) PaymentCheckPasswordPresenter.this.d);
                if (!TextUtils.isEmpty(result.d)) {
                    sortedParameter.a(MibiConstants.du, (Object) result.d);
                }
                CheckAuthModel unused = PaymentCheckPasswordPresenter.this.c = new CheckAuthModel(PaymentCheckPasswordPresenter.this.f());
                PaymentCheckPasswordPresenter.this.c.a(sortedParameter, new CheckAuthModel.OnCheckAuthListener() {
                    public void a() {
                        ((PaymentCheckPasswordContract.View) PaymentCheckPasswordPresenter.this.l()).R();
                    }

                    public void b() {
                        ((PaymentCheckPasswordContract.View) PaymentCheckPasswordPresenter.this.l()).S();
                    }

                    public void a(Bundle bundle) {
                        ((PaymentCheckPasswordContract.View) PaymentCheckPasswordPresenter.this.l()).e(bundle);
                    }

                    public void b(Bundle bundle) {
                        ((PaymentCheckPasswordContract.View) PaymentCheckPasswordPresenter.this.l()).e(bundle);
                    }

                    public void c() {
                        ((PaymentCheckPasswordContract.View) PaymentCheckPasswordPresenter.this.l()).Q();
                    }

                    public void a(int i, String str, Throwable th) {
                        ((PaymentCheckPasswordContract.View) PaymentCheckPasswordPresenter.this.l()).a(i, str, th);
                    }

                    public void d() {
                        ((PaymentCheckPasswordContract.View) PaymentCheckPasswordPresenter.this.l()).N();
                    }
                });
                return;
            }
            ((PaymentCheckPasswordContract.View) PaymentCheckPasswordPresenter.this.l()).O();
        }
    }
}
