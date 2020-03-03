package com.xiaomi.payment.pay.presenter;

import android.os.Bundle;
import com.mibi.common.base.Presenter;
import com.mibi.common.data.PrivacyManager;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.decorator.AutoSave;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.data.MibiPrivacyUtils;
import com.xiaomi.payment.pay.contract.CheckPayOrderContract;
import com.xiaomi.payment.pay.model.AccountOrderCheckModel;
import com.xiaomi.payment.pay.model.IOrderCheckModel;
import com.xiaomi.payment.pay.model.NoAccountOrderCheckModel;
import com.xiaomi.payment.pay.noAccount.NoAccountPayManager;
import com.xiaomi.payment.task.rxjava.RxCheckPaymentTask;
import com.xiaomi.payment.task.rxjava.RxPrivacyUploadTask;
import com.xiaomi.payment.task.rxjava.RxStartProcessTask;
import com.xiaomi.payment.task.rxjava.RxUpdateDomainTask;
import com.xiaomi.payment.ui.fragment.commonModel.UpdateDomainModel;
import com.xiaomi.payment.ui.model.StartProcessModel;
import java.io.Serializable;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CheckPayOrderPresenter extends Presenter<CheckPayOrderContract.View> implements CheckPayOrderContract.Presenter {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12388a = "CheckPayOrderPresenter";
    private Boolean b;
    private Boolean c;
    private String d;
    private String e;
    /* access modifiers changed from: private */
    @AutoSave.AutoSavable
    public String f;
    @AutoSave.AutoSavable
    private String g;
    private UpdateDomainModel h;
    private StartProcessModel i;
    private IOrderCheckModel j;
    private boolean k;
    private boolean l;
    private boolean m;

    public CheckPayOrderPresenter() {
        super(CheckPayOrderContract.View.class);
    }

    public void b(Bundle bundle) {
        super.b(bundle);
        this.h = new UpdateDomainModel(f());
        this.i = new StartProcessModel(f());
        Bundle n_ = n_();
        this.c = Boolean.valueOf(n_.getBoolean(MibiConstants.f12225dk, false));
        this.b = Boolean.valueOf(n_.getBoolean("payment_is_no_account", false));
        this.g = n_.getString(MibiConstants.cX, "");
        this.k = n_.getBoolean(MibiConstants.eD, true);
        this.l = n_.getBoolean(MibiConstants.eE, true);
        this.m = n_.getBoolean(MibiConstants.eG, true);
        if (this.c.booleanValue()) {
            this.d = n_.getString("url");
        } else {
            this.e = n_.getString(MibiConstants.cT);
        }
    }

    public void g() {
        o();
    }

    private void o() {
        SortedParameter sortedParameter = new SortedParameter();
        sortedParameter.a(MibiConstants.cT, (Object) this.e);
        sortedParameter.a(MibiConstants.cV, (Object) this.d);
        this.h.a(sortedParameter, new UpdateDomainModel.OnUpdateDomainListener() {
            public void a(int i, String str, Throwable th) {
                ((CheckPayOrderContract.View) CheckPayOrderPresenter.this.l()).a(i, str, th);
            }

            public void a(RxUpdateDomainTask.Result result) {
                CheckPayOrderPresenter.this.p();
            }
        });
    }

    /* access modifiers changed from: private */
    public void p() {
        this.i.a((StartProcessModel.OnProcessStartListener) new StartProcessModel.OnProcessStartListener() {
            public void a(int i, String str, Throwable th) {
                ((CheckPayOrderContract.View) CheckPayOrderPresenter.this.l()).a(i, str, th);
            }

            public void a(RxStartProcessTask.Result result) {
                String unused = CheckPayOrderPresenter.this.f = result.f12434a;
                CheckPayOrderPresenter.this.n();
            }

            public void a(int i, String str) {
                ((CheckPayOrderContract.View) CheckPayOrderPresenter.this.l()).a(i, str);
            }
        });
    }

    public void n() {
        SortedParameter sortedParameter = new SortedParameter();
        sortedParameter.a("processId", (Object) this.f);
        sortedParameter.a(MibiConstants.cX, (Object) this.g);
        sortedParameter.a(MibiConstants.eD, (Object) Boolean.valueOf(this.k));
        sortedParameter.a(MibiConstants.eE, (Object) Boolean.valueOf(this.l));
        sortedParameter.a(MibiConstants.eH, (Object) Boolean.valueOf(this.m));
        if (this.b.booleanValue()) {
            sortedParameter.a(MibiConstants.gp, (Object) NoAccountPayManager.a(e()));
            sortedParameter.a("order", (Object) this.e);
            this.j = new NoAccountOrderCheckModel(f());
        } else {
            sortedParameter.a("processId", (Object) this.f);
            sortedParameter.a(MibiConstants.f12225dk, (Object) this.c);
            sortedParameter.a("order", (Object) this.e);
            sortedParameter.a("url", (Object) this.d);
            this.j = new AccountOrderCheckModel(f());
        }
        this.j.a(sortedParameter, new IOrderCheckModel.OnCheckAuthListener() {
            public void a(int i, String str, Bundle bundle) {
                ((CheckPayOrderContract.View) CheckPayOrderPresenter.this.l()).a(i, str, bundle);
            }

            public void a(Bundle bundle) {
                Serializable serializable = bundle.getSerializable(MibiConstants.cW);
                if (serializable != null && (serializable instanceof RxCheckPaymentTask.Result)) {
                    CheckPayOrderPresenter.this.a(((RxCheckPaymentTask.Result) serializable).mServerMarketType);
                }
                ((CheckPayOrderContract.View) CheckPayOrderPresenter.this.l()).a(CheckPayOrderPresenter.this.f, bundle);
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        PrivacyManager.a(f(), str, MibiPrivacyUtils.c);
        Observable.create(new RxPrivacyUploadTask(f())).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe();
    }
}
