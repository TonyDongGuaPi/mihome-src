package com.xiaomi.payment.ui.fragment.query;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.mibi.common.base.IPresenter;
import com.mibi.common.base.StepActivity;
import com.mibi.common.base.StepFragment;
import com.mibi.common.data.Utils;
import com.mibi.common.ui.PadDialogActivity;
import com.mibi.common.ui.PhoneCommonActivity;
import com.mibi.common.ui.fragment.BaseProcessFragment;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.ui.fragment.query.contract.DeductQueryContract;
import com.xiaomi.payment.ui.fragment.query.presenter.DeductQueryPresenter;

public class DeductQueryFragment extends BaseProcessFragment implements DeductQueryContract.View {
    private static final String v = "DeductQueryFragment";

    public IPresenter onCreatePresenter() {
        return new DeductQueryPresenter();
    }

    public void c(Bundle bundle) {
        super.c(bundle);
        d(false);
    }

    public void k() {
        super.k();
        h(true);
    }

    private void h(boolean z) {
        if (z) {
            a(getString(R.string.mibi_progress_querying), true, new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    DeductQueryFragment.this.c(201, (Bundle) null);
                }
            });
        } else {
            M();
        }
    }

    public void e(String str) {
        Toast.makeText(getActivity(), str, 0).show();
        c(201, (Bundle) null);
    }

    public void e(Bundle bundle) {
        boolean a2 = p().m().a(this.t, "payment_skip_view", false);
        Log.d(v, "skip:" + a2);
        if (!a2) {
            if (Utils.b()) {
                a((Class<? extends StepFragment>) DeductSuccessFragment.class, bundle, (String) null, (Class<? extends StepActivity>) PadDialogActivity.class);
            } else {
                a((Class<? extends StepFragment>) DeductSuccessFragment.class, bundle, (String) null, (Class<? extends StepActivity>) PhoneCommonActivity.class);
            }
        }
        c(200, bundle);
    }

    /* access modifiers changed from: private */
    public void c(int i, Bundle bundle) {
        b(i, bundle);
        E();
    }

    public void l() {
        super.l();
        h(false);
    }
}
