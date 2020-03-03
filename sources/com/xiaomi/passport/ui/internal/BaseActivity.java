package com.xiaomi.passport.ui.internal;

import android.accounts.Account;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.xiaomi.accountsdk.account.XMPassportSettings;
import com.xiaomi.accountsdk.account.data.AccountInfo;
import com.xiaomi.passport.utils.AccountHelper;
import com.xiaomi.passport.utils.AuthenticatorUtil;

public class BaseActivity extends Activity implements OnBackNotifier {
    OnBackListener mOnBackListener;

    /* access modifiers changed from: protected */
    public boolean needTranslucentStatusBar() {
        return true;
    }

    public void onBackPressed() {
        if (this.mOnBackListener == null || !this.mOnBackListener.onBackPressed()) {
            try {
                super.onBackPressed();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        onBackPressed();
        return true;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        XMPassportSettings.ensureApplicationContext(getApplication());
        isTranslucentStatusBar();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    public void setContentView(int i) {
        setContentView(LayoutInflater.from(this).inflate(i, (ViewGroup) null));
    }

    public void setContentView(View view) {
        if (isTranslucentStatusBar()) {
            view.setFitsSystemWindows(true);
        }
        super.setContentView(view);
    }

    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        if (isTranslucentStatusBar()) {
            view.setFitsSystemWindows(true);
        }
        super.setContentView(view, layoutParams);
    }

    public boolean isTranslucentStatusBar() {
        return needTranslucentStatusBar();
    }

    /* access modifiers changed from: protected */
    public boolean finishIfAccountExist() {
        Account xiaomiAccount = AuthenticatorUtil.getXiaomiAccount(this);
        if (xiaomiAccount == null) {
            return false;
        }
        finishOnResult(-1, new AccountInfo.Builder().userId(xiaomiAccount.name).build());
        return true;
    }

    public void setOnBackListener(OnBackListener onBackListener) {
        this.mOnBackListener = onBackListener;
    }

    public OnBackListener getOnBackListener() {
        return this.mOnBackListener;
    }

    /* access modifiers changed from: protected */
    public void finishOnResult(int i) {
        finishOnResult(i, (AccountInfo) null);
    }

    /* access modifiers changed from: protected */
    public void finishOnResult(int i, AccountInfo accountInfo) {
        AuthenticatorUtil.handleAccountAuthenticatorResponse(getIntent().getParcelableExtra("accountAuthenticatorResponse"), AccountHelper.getAccountAuthenticatorResponseResult(i, accountInfo, getIntent().getBooleanExtra("need_retry_on_authenticator_response_result", false)));
        setResult(i);
        finish();
    }
}
