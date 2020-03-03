package com.xiaomi.payment.pay.presenter;

import android.os.Bundle;
import android.text.TextUtils;
import com.mibi.common.base.Presenter;
import com.mibi.common.data.MemoryStorage;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.decorator.AutoSave;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.pay.contract.PaymentOrderInfoContract;
import com.xiaomi.payment.pay.model.CheckAuthModel;
import com.xiaomi.payment.pay.model.DoPayModel;
import com.xiaomi.payment.pay.model.UploadOrderGiftcardModel;
import com.xiaomi.payment.recharge.RechargeType;
import com.xiaomi.payment.task.rxjava.RxCheckPaymentTask;
import com.xiaomi.payment.task.rxjava.RxRechargeTypeTask;
import java.util.ArrayList;
import junit.framework.Assert;

public class PaymentOrderInfoPresenter extends Presenter<PaymentOrderInfoContract.View> implements PaymentOrderInfoContract.Presenter {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12396a = "PaymentOrderInfoPresenter";
    private CheckAuthModel b;
    private UploadOrderGiftcardModel c;
    private DoPayModel d;
    private long e;
    private long f;
    private long g;
    private long h;
    private RxCheckPaymentTask.Result i;
    private ArrayList<RxCheckPaymentTask.Result.DiscountGiftCard> j;
    private RxRechargeTypeTask.Result k;
    @AutoSave.AutoSavable
    private String l;

    public PaymentOrderInfoPresenter() {
        super(PaymentOrderInfoContract.View.class);
    }

    public void b(Bundle bundle) {
        super.b(bundle);
        this.b = new CheckAuthModel(f());
        n();
    }

    private void n() {
        Bundle n_ = n_();
        Assert.assertNotNull(n_);
        this.l = n_.getString("processId");
        this.i = (RxCheckPaymentTask.Result) n_.getSerializable(MibiConstants.cW);
        this.g = this.i.mOrderPrice;
        this.h = this.i.mBalance;
        this.e = this.i.mGiftcardValue;
        this.f = this.i.mPartnerGiftcardValue;
        this.j = this.i.mDiscountGiftCards;
        this.k = (RxRechargeTypeTask.Result) n_.getSerializable(MibiConstants.cF);
    }

    public void b(boolean z, boolean z2, int i2, boolean z3) {
        SortedParameter sortedParameter = new SortedParameter();
        sortedParameter.a("processId", (Object) this.l);
        final boolean z4 = z;
        final boolean z5 = z2;
        final int i3 = i2;
        final boolean z6 = z3;
        this.b.a(sortedParameter, new CheckAuthModel.OnCheckAuthListener() {
            public void a() {
                ((PaymentOrderInfoContract.View) PaymentOrderInfoPresenter.this.l()).O();
            }

            public void b() {
                ((PaymentOrderInfoContract.View) PaymentOrderInfoPresenter.this.l()).Q();
            }

            public void a(Bundle bundle) {
                ((PaymentOrderInfoContract.View) PaymentOrderInfoPresenter.this.l()).e(bundle);
            }

            public void b(Bundle bundle) {
                ((PaymentOrderInfoContract.View) PaymentOrderInfoPresenter.this.l()).f(bundle);
            }

            public void c() {
                ((PaymentOrderInfoContract.View) PaymentOrderInfoPresenter.this.l()).N();
            }

            public void a(int i, String str, Throwable th) {
                ((PaymentOrderInfoContract.View) PaymentOrderInfoPresenter.this.l()).a(i, str, th);
            }

            public void d() {
                PaymentOrderInfoPresenter.this.a(z4, z5, i3, z6);
            }
        });
    }

    public void a(boolean z, boolean z2, int i2, boolean z3) {
        SortedParameter sortedParameter = new SortedParameter();
        sortedParameter.a("processId", (Object) this.l);
        sortedParameter.a(MibiConstants.eE, (Object) Boolean.valueOf(z));
        sortedParameter.a(MibiConstants.eG, (Object) Boolean.valueOf(z2));
        if (!o() || i2 < 0) {
            sortedParameter.a(MibiConstants.gS, (Object) 0);
        } else {
            sortedParameter.a(MibiConstants.gS, (Object) Long.valueOf(this.j.get(i2).mGiftCardId));
        }
        if (e(z, z2, i2, z3)) {
            a(sortedParameter);
        } else {
            a(sortedParameter, z, z2, i2, z3);
        }
    }

    private boolean o() {
        return this.j != null && this.j.size() > 0;
    }

    private boolean e(boolean z, boolean z2, int i2, boolean z3) {
        return c(z, z2, i2, z3) >= this.g;
    }

    public long c(boolean z, boolean z2, int i2, boolean z3) {
        long j2 = 0;
        if (z) {
            j2 = 0 + this.e;
        }
        if (z2) {
            j2 += this.f;
        }
        if (z3) {
            j2 += this.h;
        }
        return i2 != -1 ? j2 + this.j.get(i2).mGiftCardValue : j2;
    }

    public long d(boolean z, boolean z2, int i2, boolean z3) {
        return this.g - c(z, z2, i2, z3);
    }

    private void a(SortedParameter sortedParameter) {
        this.d = new DoPayModel(f());
        this.d.a(this.g, sortedParameter, new DoPayModel.IDoPayListener() {
            public void a() {
            }

            public void a(int i, String str, String str2) {
                ((PaymentOrderInfoContract.View) PaymentOrderInfoPresenter.this.l()).a(i, str, PaymentOrderInfoPresenter.this.a(i, str, str2));
            }

            public void a(Bundle bundle) {
                ((PaymentOrderInfoContract.View) PaymentOrderInfoPresenter.this.l()).g(bundle);
            }
        });
    }

    private void a(SortedParameter sortedParameter, boolean z, boolean z2, int i2, boolean z3) {
        this.c = new UploadOrderGiftcardModel(f());
        final boolean z4 = z;
        final boolean z5 = z2;
        final int i3 = i2;
        final boolean z6 = z3;
        this.c.a(sortedParameter, new UploadOrderGiftcardModel.OnUploadOrderGiftcardListener() {
            public void a() {
            }

            public void a(int i, String str) {
                ((PaymentOrderInfoContract.View) PaymentOrderInfoPresenter.this.l()).a(i, str, (Bundle) null);
            }

            public void b() {
                PaymentOrderInfoPresenter.this.f(z4, z5, i3, z6);
            }
        });
    }

    /* access modifiers changed from: private */
    public void f(boolean z, boolean z2, int i2, boolean z3) {
        if (this.k != null) {
            RechargeType rechargeType = this.k.mLastChargeType;
            if (rechargeType == null) {
                rechargeType = this.k.mRechargeTypes.get(0);
            }
            long d2 = d(z, z2, i2, z3);
            MemoryStorage m = f().m();
            m.a(this.l, MibiConstants.cZ, (Object) true);
            m.a(this.l, "price", (Object) Long.valueOf(d2));
            Bundle n_ = n_();
            n_.putString("processId", this.l);
            n_.putSerializable(MibiConstants.cG, rechargeType);
            ((PaymentOrderInfoContract.View) l()).h(n_);
            return;
        }
        throw new IllegalArgumentException("mPayTypeInfo should not be null when in goRechargeAndPay()!");
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
