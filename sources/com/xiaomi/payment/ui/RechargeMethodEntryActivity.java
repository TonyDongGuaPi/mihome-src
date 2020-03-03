package com.xiaomi.payment.ui;

import android.os.Bundle;
import com.mibi.common.base.BaseActivity;
import com.xiaomi.payment.PaymentApp;

public class RechargeMethodEntryActivity extends BaseActivity {
    /* access modifiers changed from: protected */
    public void doPreCreate(Bundle bundle) {
        super.doPreCreate(bundle);
        PaymentApp.a(getApplication());
    }
}
