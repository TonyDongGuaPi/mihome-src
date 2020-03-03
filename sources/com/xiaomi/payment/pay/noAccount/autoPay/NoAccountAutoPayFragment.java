package com.xiaomi.payment.pay.noAccount.autoPay;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.mibi.common.base.StepFragment;
import com.mibi.common.data.MemoryStorage;
import com.mibi.common.decorator.AutoSave;
import com.mibi.common.ui.TranslucentActivity;
import com.mibi.common.ui.fragment.BaseProcessFragment;
import com.xiaomi.payment.data.MibiCodeConstants;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.pay.RechargeAndPayTransitFragment;
import com.xiaomi.payment.pay.data.AutoPayUtils;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.recharge.RechargeType;
import com.xiaomi.payment.task.rxjava.RxRechargeTypeTask;
import com.xiaomi.payment.ui.fragment.query.PayQuickQueryFragment;
import com.xiaomi.payment.ui.fragment.query.PayWaitQueryFragment;

public class NoAccountAutoPayFragment extends BaseProcessFragment {
    public static final int v = 0;
    public static final int w = 1100;
    public static final int x = 1101;
    private static final int y = 0;
    @AutoSave.AutoSavable
    private String A;
    private RechargeType B;
    private RxRechargeTypeTask.Result C;
    private long z;

    public void a(Bundle bundle) {
        super.a(bundle);
        d(false);
    }

    /* access modifiers changed from: protected */
    public void b_(Bundle bundle) {
        super.b_(bundle);
        this.C = (RxRechargeTypeTask.Result) bundle.getSerializable(MibiConstants.cF);
        this.z = bundle.getLong("price");
        this.A = bundle.getString(MibiConstants.cX);
        if (this.C != null) {
            this.B = AutoPayUtils.a(this.C.mRechargeTypes, this.A);
            if (this.B == null) {
                this.B = AutoPayUtils.a(this.C.mRechargeTypes, AutoPayUtils.b);
            }
        }
    }

    public void c(Bundle bundle) {
        super.c(bundle);
        b(MibiCodeConstants.b);
        N();
    }

    private void N() {
        if (this.C != null) {
            MemoryStorage m = p().m();
            m.a(this.t, MibiConstants.cZ, (Object) true);
            m.a(this.t, "price", (Object) Long.valueOf(this.z));
            Bundle arguments = getArguments();
            arguments.putString("processId", this.t);
            arguments.putSerializable(MibiConstants.cG, this.B);
            a(RechargeAndPayTransitFragment.class, arguments, 0, (String) null, TranslucentActivity.class);
            return;
        }
        throw new IllegalArgumentException("mPayTypeInfo should not be null when in goRechargeAndPay()!");
    }

    public void a(int i, int i2, Intent intent) {
        String noAccountAutoPayFragment = toString();
        Log.v(noAccountAutoPayFragment, this + ".doActivityResult, requestCode = " + i + ",resultCode = " + i2);
        super.a(i, i2, intent);
        b(i, i2, intent != null ? intent.getExtras() : null);
    }

    public void a(int i, int i2, Bundle bundle) {
        String noAccountAutoPayFragment = toString();
        Log.v(noAccountAutoPayFragment, this + ".doFragmentResult, requestCode = " + i + ",resultCode = " + i2);
        super.a(i, i2, bundle);
        b(i, i2, bundle);
    }

    public void a(int i, Bundle bundle) {
        String noAccountAutoPayFragment = toString();
        Log.v(noAccountAutoPayFragment, this + ".doJumpBackResult, resultCode = " + i);
        super.a(i, bundle);
        b(0, i, bundle);
    }

    /* access modifiers changed from: protected */
    public void b(int i, int i2, Bundle bundle) {
        if (i2 == 1103) {
            this.b.m().a(this.t, "price", (Object) Long.valueOf(this.z));
            boolean z2 = true;
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
