package com.xiaomi.payment.pay.autoPay;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.mibi.common.base.IPresenter;
import com.mibi.common.base.StepFragment;
import com.mibi.common.ui.TranslucentActivity;
import com.mibi.common.ui.fragment.BaseProcessFragment;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.payment.data.MibiCodeConstants;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.pay.PaymenPayListFragment;
import com.xiaomi.payment.pay.PaymentCheckPasswordFragment;
import com.xiaomi.payment.pay.PaymentVerifySMSCodeFragment;
import com.xiaomi.payment.pay.RechargeAndPayTransitFragment;
import com.xiaomi.payment.pay.autoPay.AutoPayContract;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.recharge.RechargeType;
import com.xiaomi.payment.ui.fragment.query.PayQuickQueryFragment;
import com.xiaomi.payment.ui.fragment.query.PayResultFragment;
import com.xiaomi.payment.ui.fragment.query.PayWaitQueryFragment;
import junit.framework.Assert;

public class AutoPayFragment extends BaseProcessFragment implements AutoPayContract.View {
    public static final int v = 2;
    public static final int w = 3;
    public static final int x = 4;

    public void a(int i, boolean z) {
    }

    public void a(Bundle bundle) {
        super.a(bundle);
        d(false);
    }

    public IPresenter onCreatePresenter() {
        return new AutoPayPresenter();
    }

    public void c(Bundle bundle) {
        super.c(bundle);
        b(MibiCodeConstants.b);
        ((AutoPayPresenter) H_()).g();
    }

    public void F_() {
        L();
    }

    public void G_() {
        Toast.makeText(getActivity(), getResources().getString(R.string.mibi_error_frozen_summary), 0);
        E();
    }

    public void g(Bundle bundle) {
        a((Class<? extends StepFragment>) PaymenPayListFragment.class, bundle, 2, (String) null);
    }

    public void c() {
        a((Class<? extends StepFragment>) PaymentCheckPasswordFragment.class, (Bundle) null, 4, (String) null);
    }

    public void b(Bundle bundle) {
        a((Class<? extends StepFragment>) PaymentVerifySMSCodeFragment.class, bundle, 4, (String) null);
    }

    public void a_(Bundle bundle) {
        Assert.assertNotNull(bundle);
        e(bundle.getString(MibiConstants.dw));
    }

    public void e(Bundle bundle) {
        a((Class<? extends StepFragment>) PayResultFragment.class, bundle, 3, (String) null);
    }

    public void f(Bundle bundle) {
        a(RechargeAndPayTransitFragment.class, bundle, 3, (String) null, TranslucentActivity.class);
        b(getString(R.string.mibi_progress_creating), false);
    }

    public void a(int i, String str, Throwable th) {
        a(i, str, (Bundle) null);
    }

    public void a(int i, String str, Bundle bundle) {
        Toast.makeText(getActivity(), str, 0).show();
        b(1101, bundle);
        M();
        E();
    }

    private void e(final String str) {
        a(getString(R.string.mibi_title_bind_phone), getString(R.string.mibi_summary_bind_phone), getString(R.string.mibi_button_bind_phone), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                String c = MibiConstants.c(MibiConstants.bt);
                if (!TextUtils.isEmpty(str)) {
                    c = str;
                }
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse(c));
                intent.addFlags(Constants.CALLIGRAPHY_TAG_PRICE);
                intent.addFlags(8388608);
                AutoPayFragment.this.startActivity(intent);
            }
        });
    }

    public void a(int i, int i2, Intent intent) {
        String autoPayFragment = toString();
        Log.v(autoPayFragment, this + ".doActivityResult, requestCode = " + i + ",resultCode = " + i2);
        super.a(i, i2, intent);
        b(i, i2, intent != null ? intent.getExtras() : null);
    }

    public void a(int i, int i2, Bundle bundle) {
        String autoPayFragment = toString();
        Log.v(autoPayFragment, this + ".doFragmentResult, requestCode = " + i + ",resultCode = " + i2);
        super.a(i, i2, bundle);
        b(i, i2, bundle);
    }

    public void a(int i, Bundle bundle) {
        String autoPayFragment = toString();
        Log.v(autoPayFragment, this + ".doJumpBackResult, resultCode = " + i);
        super.a(i, bundle);
        b(3, i, bundle);
    }

    /* access modifiers changed from: protected */
    public void b(int i, int i2, Bundle bundle) {
        M();
        if (i == 2) {
            if (bundle == null) {
                E();
                return;
            }
            ((AutoPayPresenter) H_()).a((RechargeType) bundle.getSerializable(MibiConstants.cG));
            ((AutoPayPresenter) H_()).n();
        } else if (i == 4) {
            if (i2 == -1) {
                ((AutoPayPresenter) H_()).h();
            } else if (i2 == 1104) {
                int i3 = bundle.getInt(MibiConstants.da);
                if (i3 == 9) {
                    Toast.makeText(getActivity(), getResources().getString(R.string.mibi_error_frozen_summary), 0);
                } else if (i3 == 1985) {
                    a((Class<? extends StepFragment>) PaymentCheckPasswordFragment.class, (Bundle) null, 4, (String) null);
                } else if (i3 == 7001) {
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("processId", this.t);
                    bundle2.putString(MibiConstants.dl, bundle.getString(MibiConstants.dl));
                    a((Class<? extends StepFragment>) PaymentVerifySMSCodeFragment.class, bundle2, 4, (String) null);
                } else if (i3 == 7002) {
                    e(bundle.getString(MibiConstants.dw));
                }
            } else {
                c(0);
                E();
            }
        } else if (i != 3) {
        } else {
            if (i2 == 1103) {
                boolean z = true;
                if (bundle != null) {
                    z = bundle.getBoolean(MibiConstants.hi, true);
                }
                if (z) {
                    a((Class<? extends StepFragment>) PayQuickQueryFragment.class, (Bundle) null, 0, (String) null);
                } else {
                    a((Class<? extends StepFragment>) PayWaitQueryFragment.class, (Bundle) null, 0, (String) null);
                }
            } else if (i2 == 0) {
                c(0);
                E();
            } else if (i2 == 1005) {
                Toast.makeText(getActivity(), getString(R.string.mibi_error_recharge_process_fail), 0).show();
                b(1101, bundle);
                E();
            } else if (i2 == 1001) {
                Toast.makeText(getActivity(), getString(R.string.mibi_error_recharge_result_fail), 0).show();
                b(1101, bundle);
                E();
            } else if (i2 == 1002) {
                b(1101, bundle);
                E();
            } else if (i2 == 1003) {
                Toast.makeText(getActivity(), getString(R.string.mibi_error_recharge_success_pay_fail), 0).show();
                c(1102);
                E();
            } else if (i2 == 1004 || i2 == 1100) {
                b(1100, bundle);
                E();
            } else {
                if (bundle != null) {
                    int i4 = bundle.getInt(MibiConstants.da);
                    Toast.makeText(getActivity(), getString(R.string.mibi_progress_error_pay_title) + i4, 0).show();
                }
                b(1101, bundle);
                E();
            }
        }
    }

    public void o() {
        M();
        super.o();
    }
}
