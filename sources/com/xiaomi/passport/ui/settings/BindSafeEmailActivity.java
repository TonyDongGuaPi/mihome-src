package com.xiaomi.passport.ui.settings;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.xiaomi.accountsdk.utils.AccountLog;
import com.xiaomi.accountsdk.utils.ParcelableAttackGuardian;
import com.xiaomi.passport.ui.internal.Constants;
import com.xiaomi.passport.ui.internal.util.FriendlyFragmentUtils;
import com.xiaomi.passport.utils.AuthenticatorUtil;

public class BindSafeEmailActivity extends AppCompatActivity {
    private static final String TAG = "BindSafeEmailActivity";

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!new ParcelableAttackGuardian().safeCheck(this)) {
            finish();
        } else if (getIntent().getBooleanExtra(Constants.EXTRA_HAS_UNACTIVATED_EMAIL, false)) {
            FriendlyFragmentUtils.addFragment(getFragmentManager(), 16908290, UnactivatedEmailFragment.getUnactivatedEmailFragment(getIntent().getStringExtra(Constants.SP_UNACTIVATED_EMAIL_ADDRESS)));
        } else {
            InputBindedEmailFragment inputBindedEmailFragment = new InputBindedEmailFragment();
            inputBindedEmailFragment.setArguments(getIntent().getExtras());
            FriendlyFragmentUtils.addFragment(getFragmentManager(), 16908290, inputBindedEmailFragment);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (AuthenticatorUtil.getXiaomiAccount(this) == null) {
            AccountLog.i(TAG, "no xiaomi account");
            finish();
        }
    }
}
