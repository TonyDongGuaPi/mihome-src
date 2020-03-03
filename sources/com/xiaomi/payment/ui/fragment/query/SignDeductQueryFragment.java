package com.xiaomi.payment.ui.fragment.query;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.mibi.common.base.IPresenter;
import com.mibi.common.ui.fragment.BaseProcessFragment;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.ui.fragment.query.contract.SignDeductQueryContract;
import com.xiaomi.payment.ui.fragment.query.presenter.SignDeductQueryPresenter;
import java.lang.ref.WeakReference;

public class SignDeductQueryFragment extends BaseProcessFragment implements SignDeductQueryContract.View {
    private static final String v = "SignDeductQueryFragment";

    public IPresenter onCreatePresenter() {
        return new SignDeductQueryPresenter();
    }

    public void c(Bundle bundle) {
        super.c(bundle);
        d(false);
    }

    public void k() {
        super.k();
        h(true);
    }

    public void l() {
        super.l();
        h(false);
    }

    private void h(boolean z) {
        if (z) {
            a(getString(R.string.mibi_progress_querying), true, new OnCancelListenter());
        } else {
            M();
        }
    }

    public void a(String str, Bundle bundle) {
        Toast.makeText(getActivity(), str, 0).show();
        c(204, bundle);
    }

    public void e(Bundle bundle) {
        Log.i(v, "showSuccess");
        c(203, bundle);
    }

    /* access modifiers changed from: private */
    public void c(int i, Bundle bundle) {
        Log.i(v, "returnResult");
        b(i, bundle);
        E();
    }

    private static class OnCancelListenter implements DialogInterface.OnCancelListener {

        /* renamed from: a  reason: collision with root package name */
        private WeakReference<SignDeductQueryFragment> f12498a;

        private OnCancelListenter(SignDeductQueryFragment signDeductQueryFragment) {
            this.f12498a = new WeakReference<>(signDeductQueryFragment);
        }

        public void onCancel(DialogInterface dialogInterface) {
            SignDeductQueryFragment signDeductQueryFragment = (SignDeductQueryFragment) this.f12498a.get();
            if (signDeductQueryFragment != null) {
                signDeductQueryFragment.c(205, (Bundle) null);
            }
        }
    }
}
