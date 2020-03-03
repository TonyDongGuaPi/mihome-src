package com.xiaomi.payment.channel.presenter;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import com.mibi.common.base.Presenter;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.decorator.AutoSave;
import com.xiaomi.payment.channel.contract.PaytoolContract;
import com.xiaomi.payment.channel.model.AlipayModel;
import com.xiaomi.payment.channel.model.IPaytoolModel;
import com.xiaomi.payment.channel.model.IPaytoolTaskListener;
import com.xiaomi.payment.channel.model.MipayFQModel;
import com.xiaomi.payment.channel.model.MipayModel;
import com.xiaomi.payment.channel.model.PaypalModel;
import com.xiaomi.payment.channel.model.WXpayModel;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.recharge.PaytoolRechargeMethod;
import com.xiaomi.payment.recharge.RechargeManager;
import junit.framework.Assert;

public class PaytoolPresenter extends Presenter<PaytoolContract.View> implements PaytoolContract.Presenter {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12211a = "PaytoolPresenter";
    private static final long b = 1;
    @AutoSave.AutoSavable
    private IPaytoolModel c;
    /* access modifiers changed from: private */
    public String d;
    private double e;
    private long f;
    private long g;
    /* access modifiers changed from: private */
    public PaytoolRechargeMethod h;
    private IPaytoolTaskListener i = new IPaytoolTaskListener() {
        public void a(Activity activity, Bundle bundle) {
            bundle.putSerializable(MibiConstants.cE, PaytoolPresenter.this.h);
            bundle.putString("channel", PaytoolPresenter.this.h.mChannel);
            bundle.putString("title", PaytoolPresenter.this.h.mTitle);
            ((PaytoolContract.View) PaytoolPresenter.this.l()).a(bundle, 0);
        }

        public void a() {
            ((PaytoolContract.View) PaytoolPresenter.this.l()).Q();
        }

        public void b() {
            ((PaytoolContract.View) PaytoolPresenter.this.l()).O();
        }

        public void a(int i, String str, Throwable th) {
            ((PaytoolContract.View) PaytoolPresenter.this.l()).a(i, str, th);
        }

        public void a(long j, long j2) {
            PaytoolPresenter.this.f().m().a(PaytoolPresenter.this.d, MibiConstants.hg, (Object) Long.valueOf(j));
            ((PaytoolContract.View) PaytoolPresenter.this.l()).a(j);
        }

        public void a(PaytoolContract.Function<Fragment> function) {
            ((PaytoolContract.View) PaytoolPresenter.this.l()).a(function);
        }
    };

    public PaytoolPresenter() {
        super(PaytoolContract.View.class);
    }

    public void b(Bundle bundle) {
        super.b(bundle);
        this.h = (PaytoolRechargeMethod) n_().getSerializable(MibiConstants.cE);
        if (bundle != null) {
            if (bundle.containsKey("processId")) {
                this.d = bundle.getString("processId");
            }
            this.c = n();
            if (this.c != null) {
                this.c.b(bundle);
                this.c.a(this.i);
            }
        } else {
            this.d = n_().getString("processId");
        }
        this.e = this.h.mDiscountRate;
        this.f = this.h.mMinMibiValue;
        this.g = this.h.mMaxMibiValue;
    }

    /* access modifiers changed from: protected */
    public void c(Bundle bundle) {
        super.c(bundle);
        if (!TextUtils.isEmpty(this.d)) {
            bundle.putString("processId", this.d);
        }
        if (this.c != null) {
            this.c.a(bundle);
        }
    }

    public String[] i_() {
        this.c = n();
        if (this.c != null) {
            return this.c.d();
        }
        return null;
    }

    private IPaytoolModel n() {
        if (this.c != null) {
            return this.c;
        }
        if (TextUtils.equals(this.h.mChannel, RechargeManager.CHANNELS.ALIPAY.getChannel())) {
            return new AlipayModel(f());
        }
        if (TextUtils.equals(this.h.mChannel, RechargeManager.CHANNELS.PAYPALPAY.getChannel())) {
            return new PaypalModel(f());
        }
        if (TextUtils.equals(this.h.mChannel, RechargeManager.CHANNELS.MIPAY.getChannel())) {
            return new MipayModel(f());
        }
        if (TextUtils.equals(this.h.mChannel, RechargeManager.CHANNELS.MIPAYFQ.getChannel())) {
            return new MipayFQModel(f());
        }
        if (TextUtils.equals(this.h.mChannel, RechargeManager.CHANNELS.WXPAY.getChannel())) {
            return new WXpayModel(f());
        }
        return null;
    }

    public void a(long j) {
        if (j < this.f || j > this.g) {
            ((PaytoolContract.View) l()).N();
            return;
        }
        SortedParameter sortedParameter = new SortedParameter();
        sortedParameter.a("processId", (Object) this.d);
        sortedParameter.a(MibiConstants.cE, (Object) this.h);
        sortedParameter.a(MibiConstants.dd, (Object) Long.valueOf(j));
        sortedParameter.a(MibiConstants.dq, (Object) Long.valueOf(j * 1));
        if (TextUtils.equals(this.h.mChannel, RechargeManager.CHANNELS.ALIPAY.getChannel())) {
            sortedParameter.a(MibiConstants.dz, (Object) true);
        } else if (TextUtils.equals(this.h.mChannel, RechargeManager.CHANNELS.PAYPALPAY.getChannel())) {
            sortedParameter.a(MibiConstants.dz, (Object) true);
        } else if (TextUtils.equals(this.h.mChannel, RechargeManager.CHANNELS.MIPAY.getChannel()) || TextUtils.equals(this.h.mChannel, RechargeManager.CHANNELS.MIPAYFQ.getChannel())) {
            sortedParameter.a("channel", (Object) this.h.mChannel);
        } else {
            TextUtils.equals(this.h.mChannel, RechargeManager.CHANNELS.WXPAY.getChannel());
        }
        Assert.assertNotNull(this.c);
        this.c.a(sortedParameter, this.i);
    }

    public void a(int i2, int i3, Bundle bundle) {
        super.a(i2, i3, bundle);
        this.c = n();
        this.c.a(this.i);
        this.c.a(i2, i3, bundle);
    }

    public void j_() {
        if (this.c != null) {
            this.c.e();
        }
    }

    public long b(long j) {
        if (this.e <= 0.0d) {
            this.e = 1.0d;
        }
        double d2 = (double) j;
        Double.isNaN(d2);
        return j - Double.valueOf(d2 * (1.0d - this.e)).longValue();
    }

    /* access modifiers changed from: protected */
    public void E_() {
        super.E_();
        if (this.c != null) {
            this.c.f();
        }
    }
}
