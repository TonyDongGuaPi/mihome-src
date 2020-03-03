package com.xiaomi.payment.deduct;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.mibi.common.base.BaseFragment;
import com.mibi.common.base.IPresenter;
import com.mibi.common.data.PrivacyManager;
import com.mibi.common.data.Utils;
import com.mibi.common.ui.TranslucentActivity;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.data.MibiPrivacyUtils;
import com.xiaomi.payment.deduct.contract.CheckSignDeductOrderContract;
import com.xiaomi.payment.deduct.presenter.CheckSignDeductOrderPresenter;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.ui.PadFixedWidthActivity;
import com.xiaomi.payment.ui.PhonePaymentCommonActivity;
import miuipub.app.ProgressDialog;

public class CheckSignDeductOrderFragment extends BaseFragment implements CheckSignDeductOrderContract.View {
    private ProgressDialog t;

    public IPresenter onCreatePresenter() {
        return new CheckSignDeductOrderPresenter();
    }

    public void c(Bundle bundle) {
        super.c(bundle);
        g(true);
    }

    private void g(boolean z) {
        if (z) {
            if (isAdded()) {
                if (this.t == null) {
                    this.t = new ProgressDialog(getActivity());
                    this.t.a((CharSequence) getString(R.string.mibi_progress_creating));
                    this.t.setCanceledOnTouchOutside(false);
                    this.t.setCancelable(true);
                    this.t.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        public void onCancel(DialogInterface dialogInterface) {
                            CheckSignDeductOrderFragment.this.a(0, "cancelled by user");
                            CheckSignDeductOrderFragment.this.E();
                        }
                    });
                }
                this.t.show();
            }
        } else if (this.t != null) {
            this.t.dismiss();
        }
    }

    public void a(int i, String str) {
        if (i == 204 && !TextUtils.isEmpty(str)) {
            Toast.makeText(getActivity(), str, 0).show();
        }
        Bundle bundle = new Bundle();
        bundle.putString(MibiConstants.db, str);
        b(i, bundle);
        g(false);
        E();
    }

    public void e(Bundle bundle) {
        a(SignDeductTypeListFragment.class, bundle, 1, (String) null, Utils.b() ? PadFixedWidthActivity.class : PhonePaymentCommonActivity.class);
    }

    public void b(final int i, final String str) {
        MibiPrivacyUtils.a(getActivity(), new MibiPrivacyUtils.PrivacyCallBack() {
            public void a() {
                PrivacyManager.a(CheckSignDeductOrderFragment.this.p(), false);
                CheckSignDeductOrderFragment.this.a(i, str);
            }
        });
    }

    public void f(Bundle bundle) {
        a(DoSignDeductFragment.class, bundle, 1, (String) null, TranslucentActivity.class);
    }

    public void o() {
        super.o();
        if (this.t != null) {
            this.t.cancel();
        }
    }
}
