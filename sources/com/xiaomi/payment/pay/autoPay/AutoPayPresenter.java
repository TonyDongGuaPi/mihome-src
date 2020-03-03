package com.xiaomi.payment.pay.autoPay;

import android.os.Bundle;
import android.text.TextUtils;
import com.mibi.common.base.Presenter;
import com.mibi.common.data.MemoryStorage;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.decorator.AutoSave;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.pay.autoPay.AutoPayContract;
import com.xiaomi.payment.pay.data.AutoPayUtils;
import com.xiaomi.payment.pay.model.CheckAuthModel;
import com.xiaomi.payment.pay.model.DoPayModel;
import com.xiaomi.payment.recharge.RechargeType;
import com.xiaomi.payment.task.rxjava.RxRechargeTypeTask;
import junit.framework.Assert;

public class AutoPayPresenter extends Presenter<AutoPayContract.View> implements AutoPayContract.Presenter {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12365a = "AutoPayPresenter";
    private CheckAuthModel b;
    private DoPayModel c;
    private long d;
    private long e;
    private RxRechargeTypeTask.Result f;
    @AutoSave.AutoSavable
    private String g;
    @AutoSave.AutoSavable
    private RechargeType h;
    private String i;

    public AutoPayPresenter() {
        super(AutoPayContract.View.class);
    }

    public void b(Bundle bundle) {
        super.b(bundle);
        this.b = new CheckAuthModel(f());
        p();
    }

    private void p() {
        Bundle n_ = n_();
        Assert.assertNotNull(n_);
        this.g = n_.getString("processId");
        this.d = n_.getLong(MibiConstants.dh, 0);
        this.e = n_.getLong(MibiConstants.dQ, 0);
        this.f = (RxRechargeTypeTask.Result) n_.getSerializable(MibiConstants.cF);
        this.i = n_.getString(MibiConstants.cX);
        if (!TextUtils.isEmpty(this.i) && this.f != null) {
            this.h = AutoPayUtils.a(this.f.mRechargeTypes, this.i);
            if (this.h == null) {
                this.i = AutoPayUtils.f12368a;
            }
        }
    }

    public void g() {
        if (TextUtils.equals(AutoPayUtils.f12368a, this.i)) {
            o();
        } else {
            n();
        }
    }

    public void a(RechargeType rechargeType) {
        this.h = rechargeType;
    }

    public void n() {
        SortedParameter sortedParameter = new SortedParameter();
        sortedParameter.a("processId", (Object) this.g);
        this.b.a(sortedParameter, new CheckAuthModel.OnCheckAuthListener() {
            public void a() {
                ((AutoPayContract.View) AutoPayPresenter.this.l()).G_();
            }

            public void b() {
                ((AutoPayContract.View) AutoPayPresenter.this.l()).c();
            }

            public void a(Bundle bundle) {
                ((AutoPayContract.View) AutoPayPresenter.this.l()).b(bundle);
            }

            public void b(Bundle bundle) {
                ((AutoPayContract.View) AutoPayPresenter.this.l()).a_(bundle);
            }

            public void c() {
                ((AutoPayContract.View) AutoPayPresenter.this.l()).F_();
            }

            public void a(int i, String str, Throwable th) {
                ((AutoPayContract.View) AutoPayPresenter.this.l()).a(i, str, th);
            }

            public void d() {
                AutoPayPresenter.this.h();
            }
        });
    }

    public void h() {
        SortedParameter sortedParameter = new SortedParameter();
        sortedParameter.a("processId", (Object) this.g);
        sortedParameter.a(MibiConstants.eE, (Object) true);
        sortedParameter.a(MibiConstants.eG, (Object) true);
        sortedParameter.a(MibiConstants.gS, (Object) 0);
        if (this.d == 0) {
            a(sortedParameter);
        } else if (this.d > 0) {
            q();
        } else {
            ((AutoPayContract.View) l()).a(-1, "recharge price should not be letter than 0", (Bundle) null);
        }
    }

    public void o() {
        Bundle bundle = new Bundle();
        bundle.putSerializable(MibiConstants.he, this.f.mRechargeTypes);
        bundle.putSerializable(MibiConstants.hf, r());
        ((AutoPayContract.View) l()).g(bundle);
    }

    private void a(SortedParameter sortedParameter) {
        this.c = new DoPayModel(f());
        this.c.a(this.e, sortedParameter, new DoPayModel.IDoPayListener() {
            public void a() {
            }

            public void a(int i, String str, String str2) {
                ((AutoPayContract.View) AutoPayPresenter.this.l()).a(i, str, AutoPayPresenter.this.a(i, str, str2));
            }

            public void a(Bundle bundle) {
                ((AutoPayContract.View) AutoPayPresenter.this.l()).e(bundle);
            }
        });
    }

    private void q() {
        MemoryStorage m = f().m();
        m.a(this.g, MibiConstants.cZ, (Object) true);
        m.a(this.g, "price", (Object) Long.valueOf(this.d));
        Bundle n_ = n_();
        n_.putString("processId", this.g);
        n_.putSerializable(MibiConstants.cG, r());
        ((AutoPayContract.View) l()).f(n_);
    }

    private RechargeType r() {
        if (this.f == null) {
            throw new IllegalArgumentException("mPayTypeInfo should not be null when in goRechargeAndPay()!");
        } else if (this.h != null) {
            return this.h;
        } else {
            RechargeType rechargeType = this.f.mLastChargeType;
            return rechargeType == null ? this.f.mRechargeTypes.get(0) : rechargeType;
        }
    }

    /* access modifiers changed from: private */
    public Bundle a(int i2, String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putInt(MibiConstants.da, i2);
        bundle.putString(MibiConstants.db, str);
        if (!TextUtils.isEmpty(str2)) {
            bundle.putString("result", str2);
        }
        return bundle;
    }
}
