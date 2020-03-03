package com.xiaomi.payment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.mibi.common.data.MistatisticUtils;
import com.mibi.common.ui.TranslucentActivity;
import com.xiaomi.payment.deduct.CheckSignDeductOrderFragment;

public class SignDeductEntryActivity extends BaseEntryActivity {
    public static final int REQUEST_CODE = 1;

    private int a(int i) {
        switch (i) {
            case 203:
                return -1;
            case 204:
                return 17;
            case 205:
                return 0;
            default:
                return 1;
        }
    }

    /* access modifiers changed from: protected */
    public String getEntryName() {
        return "SignDeductEntry";
    }

    /* access modifiers changed from: protected */
    public void doEntrySuccess() {
        String stringExtra = getIntent().getStringExtra("deductSignOrder");
        if (TextUtils.isEmpty(stringExtra)) {
            setResult(17);
            finish();
            return;
        }
        String stringExtra2 = getIntent().getStringExtra("sign_deduct_channel");
        Bundle bundle = new Bundle();
        bundle.putString("deductSignOrder", stringExtra);
        bundle.putString("sign_deduct_channel", stringExtra2);
        Intent intent = new Intent(this, TranslucentActivity.class);
        intent.putExtra("fragment", CheckSignDeductOrderFragment.class.getName());
        intent.putExtra("payment_fragment_arguments", bundle);
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
