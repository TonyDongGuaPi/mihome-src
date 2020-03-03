package com.xiaomi.payment.deduct.presenter;

import android.app.Fragment;
import android.os.Bundle;
import com.mibi.common.base.Presenter;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.deduct.DeductManager;
import com.xiaomi.payment.deduct.contract.DoDeductContract;
import com.xiaomi.payment.deduct.model.AlipayDeductModel;
import com.xiaomi.payment.deduct.model.DeductModel;
import com.xiaomi.payment.deduct.model.MipayDeductModel;
import com.xiaomi.payment.deduct.model.WxpayDeductModel;

public class DoDeductPresenter extends Presenter<DoDeductContract.View> implements DoDeductContract.Presenter {

    /* renamed from: a  reason: collision with root package name */
    private DeductModel f12285a;
    private String b;
    private String c;

    public DoDeductPresenter() {
        super(DoDeductContract.View.class);
    }

    /* access modifiers changed from: protected */
    public void b(Bundle bundle) {
        super.b(bundle);
        this.c = n_().getString("processId");
        this.b = n_().getString(MibiConstants.gA);
        this.f12285a = n();
        if (bundle == null) {
            this.f12285a.d();
        }
    }

    private DeductModel n() {
        DeductModel deductModel;
        if (DeductManager.CHANNELS.MIPAY.getChannel().equals(this.b)) {
            deductModel = new MipayDeductModel(f(), this.c);
        } else if (DeductManager.CHANNELS.ALIPAY.getChannel().equals(this.b)) {
            deductModel = new AlipayDeductModel(f(), this.c);
        } else if (DeductManager.CHANNELS.WXPAY.getChannel().equals(this.b)) {
            deductModel = new WxpayDeductModel(f(), this.c);
        } else {
            throw new IllegalStateException("mChannelName:" + this.b);
        }
        deductModel.a((DeductModel.IDeductListener) new DeductListener());
        return deductModel;
    }

    public void a(int i, int i2, Bundle bundle) {
        super.a(i, i2, bundle);
        this.f12285a.a(i, i2, bundle);
    }

    public void g() {
        if (f().m().a(this.c, DeductModel.f12262a, false)) {
            Bundle bundle = new Bundle();
            bundle.putString(MibiConstants.gA, DeductManager.CHANNELS.ALIPAY.getChannel());
            ((DoDeductContract.View) l()).a(200, "go deduct query", bundle);
            f().m().a(this.c, DeductModel.f12262a);
        }
    }

    private class DeductListener implements DeductModel.IDeductListener {
        private DeductListener() {
        }

        public void a(int i, String str) {
            ((DoDeductContract.View) DoDeductPresenter.this.l()).a(i, str, (Bundle) null);
        }

        public void a(Bundle bundle) {
            ((DoDeductContract.View) DoDeductPresenter.this.l()).a(200, "go deduct query", bundle);
        }

        public void a(DoDeductContract.Function<Fragment> function) {
            ((DoDeductContract.View) DoDeductPresenter.this.l()).a(function);
        }
    }
}
