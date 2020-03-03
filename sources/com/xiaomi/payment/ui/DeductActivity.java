package com.xiaomi.payment.ui;

import android.os.Bundle;
import com.mibi.common.base.BaseActivity;
import com.xiaomi.payment.data.MibiCodeConstants;
import com.xiaomi.payment.deduct.CheckDeductOrderFragment;

public class DeductActivity extends BaseActivity {
    /* access modifiers changed from: protected */
    public void handleCreate(Bundle bundle) {
        super.handleCreate(bundle);
        if (bundle == null) {
            String stringExtra = getIntent().getStringExtra("deductSignOrder");
            boolean booleanExtra = getIntent().getBooleanExtra("payment_skip_view", false);
            String stringExtra2 = getIntent().getStringExtra("deduct_channel");
            Bundle bundle2 = new Bundle();
            bundle2.putString("deductSignOrder", stringExtra);
            bundle2.putBoolean("payment_skip_view", booleanExtra);
            bundle2.putString("deduct_channel", stringExtra2);
            initFragment(CheckDeductOrderFragment.class, bundle2, MibiCodeConstants.d);
        }
    }
}
