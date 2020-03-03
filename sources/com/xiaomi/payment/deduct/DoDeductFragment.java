package com.xiaomi.payment.deduct;

import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import com.mibi.common.base.BaseFragment;
import com.mibi.common.base.IPresenter;
import com.mibi.common.decorator.AutoSave;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.deduct.DeductManager;
import com.xiaomi.payment.deduct.contract.DoDeductContract;
import com.xiaomi.payment.deduct.presenter.DoDeductPresenter;
import com.xiaomi.payment.platform.R;
import junit.framework.Assert;
import miuipub.app.ProgressDialog;

public class DoDeductFragment extends BaseFragment implements AutoSave, DoDeductContract.View {
    private ProgressDialog t;
    @AutoSave.AutoSavable
    private String u;

    public IPresenter onCreatePresenter() {
        return new DoDeductPresenter();
    }

    /* access modifiers changed from: protected */
    public void b_(Bundle bundle) {
        super.b_(bundle);
        this.u = bundle.getString(MibiConstants.gA);
        Assert.assertNotNull(this.u);
    }

    public void c(Bundle bundle) {
        super.c(bundle);
        d(false);
        g(true);
    }

    private void g(boolean z) {
        if (z) {
            if (isAdded()) {
                if (this.t == null) {
                    this.t = new ProgressDialog(getActivity());
                    this.t.a((CharSequence) K());
                    this.t.setCanceledOnTouchOutside(false);
                    this.t.setCancelable(true);
                    this.t.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        public void onCancel(DialogInterface dialogInterface) {
                            DoDeductFragment.this.a(0, "cancelled by user", (Bundle) null);
                            DoDeductFragment.this.E();
                        }
                    });
                }
                this.t.show();
            }
        } else if (this.t != null) {
            this.t.dismiss();
        }
    }

    public void k() {
        super.k();
        ((DoDeductPresenter) H_()).g();
    }

    public void a(int i, String str, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putString(MibiConstants.db, str);
        b(i, bundle);
        g(false);
        E();
    }

    public void a(DoDeductContract.Function<Fragment> function) {
        function.a(this);
    }

    private String K() {
        if (DeductManager.CHANNELS.MIPAY.getChannel().equals(this.u)) {
            return getString(R.string.mibi_progress_deduct_creating, new Object[]{getString(R.string.mibi_paytool_mipay)});
        } else if (DeductManager.CHANNELS.ALIPAY.getChannel().equals(this.u)) {
            return getString(R.string.mibi_progress_deduct_creating, new Object[]{getString(R.string.mibi_paytool_alipay)});
        } else if (DeductManager.CHANNELS.WXPAY.getChannel().equals(this.u)) {
            return getString(R.string.mibi_progress_deduct_creating, new Object[]{getString(R.string.mibi_paytool_weixin)});
        } else {
            throw new IllegalStateException("channelName is " + this.u);
        }
    }

    public void o() {
        super.o();
        if (this.t != null) {
            this.t.cancel();
        }
    }
}
