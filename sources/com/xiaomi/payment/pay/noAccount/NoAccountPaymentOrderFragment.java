package com.xiaomi.payment.pay.noAccount;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.mibi.common.base.StepFragment;
import com.mibi.common.data.MemoryStorage;
import com.mibi.common.data.Utils;
import com.mibi.common.ui.TranslucentActivity;
import com.mibi.common.ui.fragment.BaseProcessFragment;
import com.xiaomi.payment.data.MibiCodeConstants;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.pay.PaymenPayListFragment;
import com.xiaomi.payment.pay.RechargeAndPayTransitFragment;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.recharge.RechargeType;
import com.xiaomi.payment.task.rxjava.RxRechargeTypeTask;
import com.xiaomi.payment.ui.PadFixedWidthActivity;
import com.xiaomi.payment.ui.PhonePaymentCommonActivity;
import com.xiaomi.payment.ui.fragment.query.PayQuickQueryFragment;
import com.xiaomi.payment.ui.fragment.query.PayWaitQueryFragment;

public class NoAccountPaymentOrderFragment extends BaseProcessFragment {
    public static final int v = 0;
    public static final int w = 1100;
    public static final int x = 1101;
    private static final int y = 1;
    private static final int z = 0;
    private long A;
    private RechargeType B;
    private RxRechargeTypeTask.Result C;

    public void a(Bundle bundle) {
        super.a(bundle);
        d(false);
    }

    /* access modifiers changed from: protected */
    public void b_(Bundle bundle) {
        super.b_(bundle);
        this.C = (RxRechargeTypeTask.Result) bundle.getSerializable(MibiConstants.cF);
        this.A = bundle.getLong("price");
    }

    public void c(Bundle bundle) {
        super.c(bundle);
        b(MibiCodeConstants.b);
        N();
    }

    private void N() {
        if (this.C != null) {
            if (this.B == null) {
                this.B = this.C.mLastChargeType;
            }
            if (this.B == null) {
                this.B = this.C.mRechargeTypes.get(0);
            }
            Bundle bundle = new Bundle();
            bundle.putSerializable(MibiConstants.he, this.C.mRechargeTypes);
            bundle.putSerializable(MibiConstants.hf, this.B);
            a(PaymenPayListFragment.class, bundle, 1, (String) null, Utils.b() ? PadFixedWidthActivity.class : PhonePaymentCommonActivity.class);
            return;
        }
        throw new IllegalArgumentException("default pay type should not be null");
    }

    private void O() {
        if (this.C != null) {
            RechargeType rechargeType = this.C.mLastChargeType;
            if (rechargeType != null) {
                MemoryStorage m = p().m();
                m.a(this.t, MibiConstants.cZ, (Object) true);
                m.a(this.t, "price", (Object) Long.valueOf(this.A));
                Bundle arguments = getArguments();
                arguments.putString("processId", this.t);
                arguments.putSerializable(MibiConstants.cG, rechargeType);
                a(RechargeAndPayTransitFragment.class, arguments, 0, (String) null, TranslucentActivity.class);
                return;
            }
            throw new IllegalArgumentException("typeChosen pay type could not be null, its default value is Mipay");
        }
        throw new IllegalArgumentException("mPayTypeInfo should not be null when in goRechargeAndPay()!");
    }

    public void a(int i, int i2, Intent intent) {
        String noAccountPaymentOrderFragment = toString();
        Log.v(noAccountPaymentOrderFragment, this + ".doActivityResult, requestCode = " + i + ",resultCode = " + i2);
        super.a(i, i2, intent);
        b(i, i2, intent != null ? intent.getExtras() : null);
    }

    public void a(int i, int i2, Bundle bundle) {
        String noAccountPaymentOrderFragment = toString();
        Log.v(noAccountPaymentOrderFragment, this + ".doFragmentResult, requestCode = " + i + ",resultCode = " + i2);
        super.a(i, i2, bundle);
        b(i, i2, bundle);
    }

    public void a(int i, Bundle bundle) {
        String noAccountPaymentOrderFragment = toString();
        Log.v(noAccountPaymentOrderFragment, this + ".doJumpBackResult, resultCode = " + i);
        super.a(i, bundle);
        b(0, i, bundle);
    }

    /* access modifiers changed from: protected */
    public void b(int i, int i2, Bundle bundle) {
        boolean z2 = true;
        if (i == 1) {
            if (bundle == null) {
                E();
                return;
            }
            this.B = (RechargeType) bundle.getSerializable(MibiConstants.cG);
            this.C.mLastChargeType = this.B;
            O();
        } else if (i2 == 1103) {
            this.b.m().a(this.t, "price", (Object) Long.valueOf(this.A));
            if (bundle != null) {
                z2 = bundle.getBoolean(MibiConstants.hi, true);
            }
            if (z2) {
                a((Class<? extends StepFragment>) PayQuickQueryFragment.class, (Bundle) null, 0, (String) null);
            } else {
                a((Class<? extends StepFragment>) PayWaitQueryFragment.class, (Bundle) null, 0, (String) null);
            }
        } else {
            if (i2 == 0) {
                c(0);
            } else if (i2 == 1005 || i2 == 1001 || i2 == 1000 || i2 == 1002 || i2 == 1003) {
                Toast.makeText(getActivity(), getString(R.string.mibi_progress_error_pay_title) + ":" + i2, 0).show();
                b(1101, bundle);
            } else if (i2 == 1004) {
                b(1100, bundle);
            }
            E();
        }
    }
}
