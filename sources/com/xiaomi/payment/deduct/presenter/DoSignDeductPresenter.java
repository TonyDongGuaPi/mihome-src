package com.xiaomi.payment.deduct.presenter;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import com.mibi.common.base.Presenter;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.deduct.DeductManager;
import com.xiaomi.payment.deduct.contract.DoSignDeductContract;
import com.xiaomi.payment.deduct.model.AlipaySignDeductModel;
import com.xiaomi.payment.deduct.model.SignDeductModel;
import com.xiaomi.payment.deduct.model.WxpaySignDeductModel;

public class DoSignDeductPresenter extends Presenter<DoSignDeductContract.View> implements DoSignDeductContract.Presenter {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12287a = "DoSignDeductPresenter";
    private SignDeductModel b;
    private String c;
    private String d;

    public DoSignDeductPresenter() {
        super(DoSignDeductContract.View.class);
    }

    /* access modifiers changed from: protected */
    public void b(Bundle bundle) {
        super.b(bundle);
        if (bundle == null) {
            this.d = n_().getString("processId");
        } else {
            this.d = bundle.getString("processId");
        }
        this.c = n_().getString(MibiConstants.gA);
        this.b = n();
        if (bundle == null) {
            this.b.d();
        }
    }

    private SignDeductModel n() {
        SignDeductModel signDeductModel;
        if (DeductManager.CHANNELS.WXPAY.getChannel().equals(this.c)) {
            signDeductModel = new WxpaySignDeductModel(f(), this.d);
        } else if (DeductManager.CHANNELS.ALIPAY.getChannel().equals(this.c)) {
            signDeductModel = new AlipaySignDeductModel(f(), this.d);
        } else {
            throw new IllegalStateException("mChannelName:" + this.c);
        }
        signDeductModel.a((SignDeductModel.IDeductListener) new SignDeductListener());
        return signDeductModel;
    }

    public void a(int i, int i2, Bundle bundle) {
        super.a(i, i2, bundle);
        Log.d(f12287a, "handleResult requestCode : " + i + " ; resultCode : " + i2);
        this.b.a(i, i2, bundle);
        if (i != 200) {
            return;
        }
        if (i2 == 205 || i2 == 204) {
            ((DoSignDeductContract.View) l()).a(205, "cancel by user", bundle);
        } else if (i2 == 203) {
            ((DoSignDeductContract.View) l()).a(203, "sign deduct success", bundle);
        }
    }

    public void g() {
        if (f().m().a(this.d, SignDeductModel.f12268a, false) && this.b != null) {
            if (this.b instanceof WxpaySignDeductModel) {
                ((WxpaySignDeductModel) this.b).k_();
            } else if (this.b instanceof AlipaySignDeductModel) {
                this.b.d();
            }
        }
    }

    public String h() {
        return this.d;
    }

    /* access modifiers changed from: protected */
    public void c(Bundle bundle) {
        super.c(bundle);
        bundle.putString("processId", this.d);
    }

    /* access modifiers changed from: protected */
    public void E_() {
        super.E_();
        if (this.b instanceof WxpaySignDeductModel) {
            ((WxpaySignDeductModel) this.b).b();
        }
    }

    private class SignDeductListener implements SignDeductModel.IDeductListener {
        private SignDeductListener() {
        }

        public void a(int i, String str) {
            Log.d(DoSignDeductPresenter.f12287a, "handleDeductError");
            ((DoSignDeductContract.View) DoSignDeductPresenter.this.l()).a(i, str, (Bundle) null);
        }

        public void a(Bundle bundle) {
            Log.d(DoSignDeductPresenter.f12287a, "requestQueryResult");
            ((DoSignDeductContract.View) DoSignDeductPresenter.this.l()).d(bundle.getString(MibiConstants.gA));
        }

        public void a(DoSignDeductContract.Function<Fragment> function) {
            ((DoSignDeductContract.View) DoSignDeductPresenter.this.l()).a(function);
        }
    }
}
