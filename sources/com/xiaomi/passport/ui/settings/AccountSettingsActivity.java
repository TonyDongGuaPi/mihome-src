package com.xiaomi.passport.ui.settings;

import android.accounts.Account;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;
import com.xiaomi.accounts.AccountManager;
import com.xiaomi.accountsdk.utils.AccountLog;
import com.xiaomi.accountsdk.utils.ParcelableAttackGuardian;
import com.xiaomi.passport.ui.R;
import com.xiaomi.passport.ui.internal.util.FriendlyFragmentUtils;
import com.xiaomi.passport.utils.AuthenticatorUtil;
import com.xiaomi.passport.utils.XiaomiPassportExecutor;
import java.io.IOException;
import java.lang.ref.WeakReference;

public class AccountSettingsActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_ADD_ACCOUNT = 65536;
    private static final String TAG = "AccountSettingsActivity";
    private AccountSettingsFragment mFragment;
    /* access modifiers changed from: private */
    public AccountManagerFuture<Bundle> mRefreshTokenFuture;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!new ParcelableAttackGuardian().safeCheck(this)) {
            Toast.makeText(this, R.string.passport_unknow_error, 0).show();
            finish();
        } else if (AuthenticatorUtil.getXiaomiAccount(this) != null) {
            addSettingFragment();
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 65536) {
            if (i2 == -1) {
                AccountLog.d(TAG, "add account success");
                addSettingFragment();
                return;
            }
            AccountLog.d(TAG, "add account cancelled");
            finish();
        } else if (this.mFragment != null) {
            this.mFragment.onActivityResultOfFragment(i, i2, intent);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        Account xiaomiAccount = AuthenticatorUtil.getXiaomiAccount(this);
        if (xiaomiAccount == null) {
            finish();
            Toast.makeText(this, R.string.no_account, 0).show();
            return;
        }
        AccountManager accountManager = AccountManager.get(this);
        WeakReference weakReference = new WeakReference(this);
        if (needTriggerNewGetAuthToken((Activity) weakReference.get(), accountManager, xiaomiAccount)) {
            this.mRefreshTokenFuture = accountManager.getAuthToken(xiaomiAccount, "passportapi", (Bundle) null, (Activity) weakReference.get(), (AccountManagerCallback<Bundle>) null, (Handler) null);
            XiaomiPassportExecutor.getSingleton().execute(new Runnable() {
                public void run() {
                    try {
                        if (TextUtils.isEmpty(((Bundle) AccountSettingsActivity.this.mRefreshTokenFuture.getResult()).getString("authtoken"))) {
                            AccountSettingsActivity.this.finish();
                        }
                    } catch (OperationCanceledException e) {
                        AccountLog.w(AccountSettingsActivity.TAG, "check token invalid", e);
                        AccountSettingsActivity.this.finish();
                    } catch (IOException e2) {
                        AccountLog.w(AccountSettingsActivity.TAG, "check token invalid", e2);
                    } catch (AuthenticatorException e3) {
                        AccountLog.w(AccountSettingsActivity.TAG, "check token invalid", e3);
                    } catch (Throwable th) {
                        AccountManagerFuture unused = AccountSettingsActivity.this.mRefreshTokenFuture = null;
                        throw th;
                    }
                    AccountManagerFuture unused2 = AccountSettingsActivity.this.mRefreshTokenFuture = null;
                }
            });
        }
    }

    private boolean needTriggerNewGetAuthToken(Activity activity, AccountManager accountManager, Account account) {
        return activity != null && !activity.isFinishing() && TextUtils.isEmpty(accountManager.getPassword(account)) && this.mRefreshTokenFuture == null;
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    private void addSettingFragment() {
        this.mFragment = new AccountSettingsFragment();
        Intent intent = getIntent();
        if (!(intent == null || intent.getExtras() == null)) {
            this.mFragment.setArguments(intent.getExtras());
        }
        FriendlyFragmentUtils.addFragment(getFragmentManager(), 16908290, this.mFragment);
    }
}
