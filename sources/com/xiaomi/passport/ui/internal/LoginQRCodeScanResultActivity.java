package com.xiaomi.passport.ui.internal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.xiaomi.accountsdk.utils.ParcelableAttackGuardian;
import com.xiaomi.passport.ui.internal.util.FriendlyFragmentUtils;

public class LoginQRCodeScanResultActivity extends AppCompatActivity {
    private ScanCodeLoginFragment mFragment;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!new ParcelableAttackGuardian().safeCheck(this)) {
            finish();
            return;
        }
        this.mFragment = new ScanCodeLoginFragment();
        this.mFragment.setArguments(getIntent().getExtras());
        FriendlyFragmentUtils.addFragment(getFragmentManager(), 16908290, this.mFragment);
    }

    public void onBackPressed() {
        if (this.mFragment == null || !this.mFragment.onBackPressed()) {
            try {
                super.onBackPressed();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
    }

    public void finish() {
        if (!isFinishing()) {
            this.mFragment.checkScanCodeSuccess();
        }
        super.finish();
    }
}
