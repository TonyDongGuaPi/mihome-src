package com.xiaomi.payment.ui.fragment.query;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mibi.common.base.IPresenter;
import com.mibi.common.base.StepFragment;
import com.mibi.common.component.ProgressButton;
import com.mibi.common.ui.animation.PopupAnimatorFactory;
import com.mibi.common.ui.fragment.BaseProcessFragment;
import com.xiaomi.payment.data.MibiCodeConstants;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.ui.fragment.query.contract.QueryContract;
import com.xiaomi.payment.ui.fragment.query.presenter.PayQueryPresenter;

public class PayWaitQueryFragment extends BaseProcessFragment implements QueryContract.View {
    private ProgressButton v;
    private int[] w = {1, 10, 10, 10, 5, 5, 5, 5};

    public void a(int i, String str, Throwable th) {
    }

    public void e(Bundle bundle) {
    }

    public View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.mibi_payment_progress, viewGroup, false);
        this.v = (ProgressButton) inflate.findViewById(R.id.button_progress);
        return inflate;
    }

    public IPresenter onCreatePresenter() {
        return new PayQueryPresenter();
    }

    public void c(Bundle bundle) {
        super.c(bundle);
        d(false);
        b(false);
        R();
        ((PayQueryPresenter) H_()).a(this.w);
    }

    private void R() {
        this.v.setText(R.string.mibi_btn_query);
        this.v.setEnabled(false);
    }

    public void a(long j) {
        this.v.setText(getString(R.string.mibi_btn_count_down_auto, new Object[]{Long.valueOf(j)}));
    }

    public void b(long j) {
        this.v.setText(getString(R.string.mibi_btn_count_down_auto, new Object[]{Long.valueOf(j)}));
    }

    public void Q() {
        this.v.setText(R.string.mibi_btn_query);
    }

    public void a(int i, boolean z) {
        if (z) {
            this.v.startProgress();
        } else {
            this.v.stopProgress();
        }
    }

    public void a(long j, String str, int i) {
        this.v.setText(R.string.mibi_btn_query);
        this.v.setEnabled(false);
        b(false);
    }

    public void N() {
        b(true);
        L();
    }

    public void O() {
        b(true);
    }

    public void f(Bundle bundle) {
        b(bundle.getInt("resultCode"), bundle);
        a(MibiCodeConstants.b, false);
    }

    public void g(Bundle bundle) {
        a_(PayResultFragment.class, bundle);
    }

    /* access modifiers changed from: protected */
    public StepFragment.IAnimatorFactory I() {
        return new PopupAnimatorFactory();
    }
}
