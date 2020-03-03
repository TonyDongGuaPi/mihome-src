package com.xiaomi.payment.ui;

import android.os.Bundle;
import com.mibi.common.base.BaseActivity;
import com.xiaomi.payment.data.MibiCodeConstants;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.pay.CheckPayOrderFragment;

public class PaymentActivity extends BaseActivity {
    /* access modifiers changed from: protected */
    public void handleCreate(Bundle bundle) {
        super.handleCreate(bundle);
        if (bundle == null) {
            a();
        }
    }

    private void a() {
        boolean booleanExtra = getIntent().getBooleanExtra("payment_is_no_account", false);
        String stringExtra = getIntent().getStringExtra(MibiConstants.cX);
        boolean booleanExtra2 = getIntent().getBooleanExtra(MibiConstants.eD, true);
        boolean booleanExtra3 = getIntent().getBooleanExtra(MibiConstants.eE, true);
        boolean booleanExtra4 = getIntent().getBooleanExtra(MibiConstants.eH, true);
        Bundle bundle = new Bundle();
        bundle.putBoolean(MibiConstants.f12225dk, getIntent().getBooleanExtra(MibiConstants.f12225dk, false));
        bundle.putString("url", getIntent().getStringExtra("url"));
        bundle.putString(MibiConstants.cT, getIntent().getStringExtra(MibiConstants.cT));
        bundle.putBoolean("payment_is_no_account", booleanExtra);
        bundle.putString(MibiConstants.cX, stringExtra);
        bundle.putBoolean(MibiConstants.eD, booleanExtra2);
        bundle.putBoolean(MibiConstants.eE, booleanExtra3);
        bundle.putBoolean(MibiConstants.eH, booleanExtra4);
        initFragment(CheckPayOrderFragment.class, bundle, MibiCodeConstants.d);
    }
}
