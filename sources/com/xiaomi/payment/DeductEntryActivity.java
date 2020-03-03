package com.xiaomi.payment;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import com.mibi.common.data.MistatisticUtils;
import com.xiaomi.payment.ui.DeductActivity;

public class DeductEntryActivity extends BaseEntryActivity {
    public static final int REQUEST_CODE = 1;

    private int a(int i) {
        switch (i) {
            case 200:
                return -1;
            case 201:
                return 17;
            case 202:
                return 0;
            default:
                return 1;
        }
    }

    /* access modifiers changed from: protected */
    public String getEntryName() {
        return "DeductEntry";
    }

    /* access modifiers changed from: protected */
    public void doEntrySuccess() {
        String stringExtra = getIntent().getStringExtra("deductSignOrder");
        if (TextUtils.isEmpty(stringExtra)) {
            setResult(17);
            finish();
            return;
        }
        boolean booleanExtra = getIntent().getBooleanExtra("payment_skip_view", false);
        String stringExtra2 = getIntent().getStringExtra("deduct_channel");
        Intent intent = new Intent(this, DeductActivity.class);
        intent.putExtra("deductSignOrder", stringExtra);
        intent.putExtra("payment_skip_view", booleanExtra);
        intent.putExtra("deduct_channel", stringExtra2);
        startActivityForResult(intent, 1);
        MistatisticUtils.a((Activity) this, getEntryName());
        MistatisticUtils.b((Activity) this, getEntryName());
    }

    /* access modifiers changed from: protected */
    public void doEntryFailed(int i, String str) {
        returnError(i, str);
        finish();
    }

    /* access modifiers changed from: protected */
    public void doActivityResult(int i, int i2, Intent intent) {
        super.doActivityResult(i, i2, intent);
        if (i == 1) {
            setResult(a(i2));
        }
        finish();
    }
}
