package com.xiaomi.passport.ui.internal;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import com.xiaomi.accountsdk.account.AccountIntent;
import com.xiaomi.accountsdk.utils.ParcelableAttackGuardian;
import com.xiaomi.passport.ui.settings.ChangePasswordActivity;
import com.xiaomi.passport.utils.AuthenticatorUtil;

public class QuickLoginActivity extends BaseActivity {
    private static final int REQUEST_CODE_SET_PWD = 1;
    private static final String TAG_FRM_QUICK_LOGIN = "quick_login";

    /* access modifiers changed from: protected */
    public boolean needTranslucentStatusBar() {
        return false;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!new ParcelableAttackGuardian().safeCheck(this)) {
            finish();
        } else if (!getIntent().getBooleanExtra(AccountIntent.EXTRA_HAS_PASSWORD, true)) {
            Intent intent = new Intent(this, ChangePasswordActivity.class);
            intent.putExtras(getIntent());
            intent.setPackage(getPackageName());
            startActivityForResult(intent, 1);
        } else {
            FragmentManager fragmentManager = getFragmentManager();
            if (((QuickLoginFragment) fragmentManager.findFragmentByTag(TAG_FRM_QUICK_LOGIN)) == null) {
                QuickLoginFragment quickLoginFragment = new QuickLoginFragment();
                quickLoginFragment.setArguments(getIntent().getExtras());
                fragmentManager.beginTransaction().setTransition(4099).replace(16908290, quickLoginFragment, TAG_FRM_QUICK_LOGIN).commit();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1) {
            setResult(i2);
            finish();
        }
    }

    public void finish() {
        AuthenticatorUtil.handleAccountAuthenticatorResponse(getIntent().getParcelableExtra("accountAuthenticatorResponse"), (Bundle) null);
        super.finish();
    }
}
