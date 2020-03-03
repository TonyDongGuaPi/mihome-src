package com.xiaomi.payment.deduct;

import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import com.mibi.common.base.BaseFragment;
import com.mibi.common.base.IPresenter;
import com.mibi.common.base.StepFragment;
import com.mibi.common.decorator.AutoSave;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.deduct.DeductManager;
import com.xiaomi.payment.deduct.contract.DoSignDeductContract;
import com.xiaomi.payment.deduct.presenter.DoSignDeductPresenter;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.ui.fragment.query.SignDeductQueryFragment;
import junit.framework.Assert;
import miuipub.app.ProgressDialog;

public class DoSignDeductFragment extends BaseFragment implements AutoSave, DoSignDeductContract.View {
    private static final String t = "DoSignDeductFragment";
    private ProgressDialog u;
    @AutoSave.AutoSavable
    private String v;

    public IPresenter onCreatePresenter() {
        return new DoSignDeductPresenter();
    }

    /* access modifiers changed from: protected */
    public void b_(Bundle bundle) {
        super.b_(bundle);
        this.v = bundle.getString(MibiConstants.gA);
        Assert.assertNotNull(this.v);
    }

    public void c(Bundle bundle) {
        super.c(bundle);
        d(false);
        g(true);
    }

    private void g(boolean z) {
        if (z) {
            if (isAdded()) {
                if (this.u == null) {
                    this.u = new ProgressDialog(getActivity());
                    this.u.a((CharSequence) K());
                    this.u.setCanceledOnTouchOutside(false);
                    this.u.setCancelable(true);
                    this.u.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        public void onCancel(DialogInterface dialogInterface) {
                            DoSignDeductFragment.this.a(0, "cancelled by user", (Bundle) null);
                            DoSignDeductFragment.this.E();
                        }
                    });
                }
                this.u.show();
            }
        } else if (this.u != null) {
            this.u.dismiss();
        }
    }

    public void k() {
        super.k();
        ((DoSignDeductPresenter) H_()).g();
    }

    public void a(int i, String str, Bundle bundle) {
        Log.d(t, "returnResult errorCode : " + i);
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putString(MibiConstants.db, str);
        b(i, bundle);
        g(false);
        E();
    }

    public void d(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("processId", ((DoSignDeductPresenter) H_()).h());
        bundle.putString(MibiConstants.gA, str);
        a((Class<? extends StepFragment>) SignDeductQueryFragment.class, bundle, 200, (String) null);
    }

    public void a(DoSignDeductContract.Function<Fragment> function) {
        function.a(this);
    }

    private String K() {
        if (DeductManager.CHANNELS.MIPAY.getChannel().equals(this.v)) {
            return getString(R.string.mibi_progress_deduct_creating, new Object[]{getString(R.string.mibi_paytool_mipay)});
        } else if (DeductManager.CHANNELS.ALIPAY.getChannel().equals(this.v)) {
            return getString(R.string.mibi_progress_deduct_creating, new Object[]{getString(R.string.mibi_paytool_alipay)});
        } else if (DeductManager.CHANNELS.WXPAY.getChannel().equals(this.v)) {
            return getString(R.string.mibi_progress_deduct_creating, new Object[]{getString(R.string.mibi_paytool_weixin)});
        } else {
            throw new IllegalStateException("channelName is " + this.v);
        }
    }

    public void o() {
        super.o();
        if (this.u != null) {
            this.u.cancel();
        }
    }
}
