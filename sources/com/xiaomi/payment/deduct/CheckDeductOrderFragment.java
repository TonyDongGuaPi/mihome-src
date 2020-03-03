package com.xiaomi.payment.deduct;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.mibi.common.base.BaseFragment;
import com.mibi.common.base.IPresenter;
import com.mibi.common.data.PrivacyManager;
import com.mibi.common.data.Utils;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.data.MibiPrivacyUtils;
import com.xiaomi.payment.deduct.contract.CheckDeductOrderContract;
import com.xiaomi.payment.deduct.presenter.CheckDeductOrderPresenter;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.ui.PadFixedWidthActivity;
import com.xiaomi.payment.ui.PhonePaymentCommonActivity;
import miuipub.app.ActionBar;
import miuipub.app.ProgressDialog;

public class CheckDeductOrderFragment extends BaseFragment implements CheckDeductOrderContract.View {
    private ProgressDialog t;

    public IPresenter onCreatePresenter() {
        return new CheckDeductOrderPresenter();
    }

    public void c(Bundle bundle) {
        super.c(bundle);
        g(true);
        ActionBar T = T();
        if (T != null) {
            T.hide();
        }
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
                            CheckDeductOrderFragment.this.a(0, "cancelled by user");
                            CheckDeductOrderFragment.this.E();
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
        if (i == 201 && !TextUtils.isEmpty(str)) {
            Toast.makeText(getActivity(), str, 0).show();
        }
        Bundle bundle = new Bundle();
        bundle.putString(MibiConstants.db, str);
        b(i, bundle);
        g(false);
        E();
    }

    public void e(Bundle bundle) {
        a(DeductTypeListFragment.class, bundle, 1, (String) null, Utils.b() ? PadFixedWidthActivity.class : PhonePaymentCommonActivity.class);
    }

    public void f(Bundle bundle) {
        a(AutoDeductFragment.class, bundle, 1, (String) null, Utils.b() ? PadFixedWidthActivity.class : PhonePaymentCommonActivity.class);
    }

    public void b(final int i, final String str) {
        MibiPrivacyUtils.a(getActivity(), new MibiPrivacyUtils.PrivacyCallBack() {
            public void a() {
                PrivacyManager.a(CheckDeductOrderFragment.this.p(), false);
                CheckDeductOrderFragment.this.a(i, str);
            }
        });
    }

    public void o() {
        super.o();
        if (this.t != null) {
            this.t.cancel();
        }
    }
}
