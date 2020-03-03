package com.xiaomi.payment.ui;

import android.os.Bundle;
import android.view.WindowManager;
import com.mibi.common.base.BaseActivity;
import com.mibi.common.data.Client;
import com.xiaomi.payment.PaymentApp;

public class PhoneRechargeDialogActivity extends BaseActivity {
    /* access modifiers changed from: protected */
    public void handleCreate(Bundle bundle) {
        super.handleCreate(bundle);
        PaymentApp.a(getApplication());
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        Client.DisplayInfo z = Client.z();
        attributes.width = Math.min(z.a(), z.b());
        attributes.height = -2;
    }

    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }
}
