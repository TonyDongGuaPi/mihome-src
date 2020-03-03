package com.xiaomi.payment.channel;

import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.mibi.common.base.IPresenter;
import com.mibi.common.base.StepFragment;
import com.mibi.common.data.MemoryStorage;
import com.mibi.common.data.MistatisticUtils;
import com.mibi.common.data.Utils;
import com.mibi.common.ui.PadDialogActivity;
import com.mibi.common.ui.PhoneCommonActivity;
import com.mibi.common.ui.fragment.BaseProcessFragment;
import com.xiaomi.payment.channel.contract.PaytoolContract;
import com.xiaomi.payment.channel.presenter.PaytoolPresenter;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.recharge.PaytoolRechargeMethod;

public class PaytoolTransitFragment extends BaseProcessFragment implements PaytoolContract.View {
    private static final String v = "PaytoolTransitFragment";
    private boolean A;
    private long w = 0;
    private PaytoolRechargeMethod x;
    private long y;
    private long z;

    public void a(int i, boolean z2) {
    }

    public void c(Bundle bundle) {
        super.c(bundle);
        d(false);
        a(((PaytoolPresenter) H_()).i_());
    }

    /* access modifiers changed from: protected */
    public void b_(Bundle bundle) {
        super.b_(bundle);
        this.w = bundle.getLong(MibiConstants.dd);
        this.x = (PaytoolRechargeMethod) bundle.getSerializable(MibiConstants.cE);
        this.y = this.x.mMinMibiValue;
        this.z = this.x.mMaxMibiValue;
    }

    /* access modifiers changed from: protected */
    public void a(MemoryStorage memoryStorage) {
        super.a(memoryStorage);
        if (!TextUtils.isEmpty(this.t)) {
            this.A = memoryStorage.d(this.t, MibiConstants.cZ);
        }
    }

    /* access modifiers changed from: protected */
    public void A() {
        super.A();
        ((PaytoolPresenter) H_()).a(this.w);
    }

    /* access modifiers changed from: protected */
    public void B() {
        super.B();
        E();
    }

    public IPresenter onCreatePresenter() {
        return new PaytoolPresenter();
    }

    public void k() {
        super.k();
        ((PaytoolPresenter) H_()).j_();
        MistatisticUtils.a((Fragment) this, this.A ? "Pay:" : "Recharge:");
    }

    public void l() {
        super.l();
        MistatisticUtils.b((Fragment) this, this.A ? "Pay:" : "Recharge:");
    }

    public void N() {
        Toast.makeText(getActivity(), getString(R.string.mibi_pay_grid_error_denomination, new Object[]{Utils.a(this.y), Utils.a(this.z)}), 0).show();
        E();
    }

    public void Q() {
        L();
    }

    public void a(Bundle bundle, int i) {
        Class cls;
        if (Utils.b()) {
            cls = PadDialogActivity.class;
        } else {
            cls = PhoneCommonActivity.class;
        }
        a(PaytoolWebFragment.class, bundle, i, (String) null, cls);
    }

    public void a(int i, String str, Throwable th) {
        ChannelUtils.a((StepFragment) this, i, str);
        E();
    }

    public void O() {
        c(0);
        E();
    }

    public void a(PaytoolContract.Function<Fragment> function) {
        function.a(this);
    }

    public void a(long j) {
        p().m().a(this.t, MibiConstants.hg, (Object) Long.valueOf(j));
        Bundle bundle = new Bundle();
        bundle.putString("title", this.x.mTitle);
        bundle.putBoolean(MibiConstants.hi, true);
        ChannelUtils.a((StepFragment) this, bundle, this.A);
    }

    public void a(int i, int i2, Bundle bundle) {
        Log.v(v, this + ".onFragmentResult, requestCode = " + i + ",resultCode = " + i2);
        super.a(i, i2, bundle);
        if (i == 0) {
            b(i2, bundle);
        }
        E();
    }
}
