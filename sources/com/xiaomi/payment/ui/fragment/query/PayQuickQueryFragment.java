package com.xiaomi.payment.ui.fragment.query;

import android.os.Bundle;
import com.mibi.common.base.IPresenter;
import com.mibi.common.base.StepFragment;
import com.mibi.common.ui.animation.PopupAnimatorFactory;
import com.mibi.common.ui.fragment.BaseProcessFragment;
import com.xiaomi.payment.data.MibiCodeConstants;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.ui.fragment.query.contract.QueryContract;
import com.xiaomi.payment.ui.fragment.query.presenter.PayQueryPresenter;

public class PayQuickQueryFragment extends BaseProcessFragment implements QueryContract.View {
    private int[] v = {0, 1, 1, 2, 3, 5, 7, 10};

    public void N() {
    }

    public void a(int i, String str, Throwable th) {
    }

    public void a(int i, boolean z) {
    }

    public void b(long j) {
    }

    public void e(Bundle bundle) {
    }

    public void c(Bundle bundle) {
        super.c(bundle);
        d(false);
        ((PayQueryPresenter) H_()).a(this.v);
    }

    public IPresenter onCreatePresenter() {
        return new PayQueryPresenter();
    }

    public void a(long j, String str, int i) {
        b(false);
    }

    public void O() {
        b(true);
    }

    public void f(Bundle bundle) {
        b(bundle.getInt("resultCode"), bundle);
        a(MibiCodeConstants.b, false);
    }

    public void a(long j) {
        b(getString(R.string.mibi_progress_querying), false);
    }

    public void Q() {
        M();
    }

    public void g(Bundle bundle) {
        a_(PayResultFragment.class, bundle);
    }

    /* access modifiers changed from: protected */
    public StepFragment.IAnimatorFactory I() {
        return new PopupAnimatorFactory();
    }
}
