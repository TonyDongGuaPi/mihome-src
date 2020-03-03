package com.mibi.common.ui;

import android.os.Bundle;
import android.view.WindowManager;
import com.mibi.common.base.BaseActivity;
import com.mibi.common.data.Client;

public class PhoneDialogActivity extends BaseActivity {
    /* access modifiers changed from: protected */
    public void handleCreate(Bundle bundle) {
        super.handleCreate(bundle);
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
