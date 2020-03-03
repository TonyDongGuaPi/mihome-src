package com.xiaomi.passport.ui.settings;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.xiaomi.accounts.AccountManager;
import com.xiaomi.accountsdk.account.data.AccountInfo;
import com.xiaomi.accountsdk.account.data.IdentityAuthReason;
import com.xiaomi.accountsdk.account.data.NotificationAuthResult;
import com.xiaomi.accountsdk.account.exception.InvalidCredentialException;
import com.xiaomi.accountsdk.account.exception.InvalidParameterException;
import com.xiaomi.accountsdk.request.AccessDeniedException;
import com.xiaomi.accountsdk.request.AuthenticationFailureException;
import com.xiaomi.accountsdk.request.CipherException;
import com.xiaomi.accountsdk.request.InvalidResponseException;
import com.xiaomi.accountsdk.utils.AccountLog;
import com.xiaomi.passport.data.XMPassportInfo;
import com.xiaomi.passport.ui.R;
import com.xiaomi.passport.ui.internal.Constants;
import com.xiaomi.passport.ui.internal.PassportUI;
import com.xiaomi.passport.ui.internal.ProgressHolder;
import com.xiaomi.passport.ui.settings.GetIdentityAuthUrlTask;
import com.xiaomi.passport.ui.settings.SimpleAsyncTask;
import com.xiaomi.passport.ui.settings.widget.PasswordView;
import com.xiaomi.passport.utils.AuthenticatorUtil;
import com.xiaomi.passport.utils.XiaomiPassportExecutor;
import java.io.IOException;

public class ChangePasswordActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_PASSPORT_IDENTITY = 16;
    private static final String TAG = "ChangePasswordActivity";
    /* access modifiers changed from: private */
    public Account mAccount;
    private View mChangeButton;
    private SimpleAsyncTask<SimpleAsyncTask.ResultType> mChangePwdTask;
    private PasswordView mConfirmPwdView;
    private View mContentView;
    private TextView mErrorView;
    /* access modifiers changed from: private */
    public GetIdentityAuthUrlTask mGetIdentityUrlTask;
    /* access modifiers changed from: private */
    public IdentityAuthReason mIdentityAuthReason;
    private PasswordView mPwdView;
    final TextWatcher mTextWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            ChangePasswordActivity.this.updateErrorMsgView(false, -1);
        }
    };

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.change_pwd_layout);
        this.mContentView = findViewById(16908290);
        this.mChangeButton = findViewById(R.id.change_pwd_btn);
        this.mChangeButton.setOnClickListener(this);
        this.mErrorView = (TextView) findViewById(R.id.error_status);
        this.mPwdView = (PasswordView) findViewById(R.id.input_new_pwd_view);
        this.mPwdView.addTextChangedListener(this.mTextWatcher);
        this.mConfirmPwdView = (PasswordView) findViewById(R.id.confirm_pwd_view);
        this.mConfirmPwdView.addTextChangedListener(this.mTextWatcher);
        this.mAccount = AuthenticatorUtil.getXiaomiAccount(getApplicationContext());
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ChangePasswordActivity.this.onBackPressed();
            }
        });
        setContentVisibility(false);
        if (this.mAccount == null) {
            finish();
        } else {
            changePasswordAuth();
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem == null || menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        onBackPressed();
        return true;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.mAccount = AuthenticatorUtil.getXiaomiAccount(getApplicationContext());
        if (this.mAccount == null) {
            finish();
        }
    }

    public void onBackPressed() {
        sendChangePasswordResultByLocalBroadcast(false, 0);
        super.onBackPressed();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        if (this.mChangePwdTask != null) {
            this.mChangePwdTask.cancel(true);
            this.mChangePwdTask = null;
        }
        if (this.mGetIdentityUrlTask != null) {
            this.mGetIdentityUrlTask.cancel(true);
            this.mGetIdentityUrlTask = null;
        }
        super.onDestroy();
    }

    public void onClick(View view) {
        if (view == this.mChangeButton) {
            changePassword(checkNewPwd());
        }
    }

    private String checkNewPwd() {
        String password = this.mPwdView.getPassword();
        if (TextUtils.isEmpty(password)) {
            return null;
        }
        String password2 = this.mConfirmPwdView.getPassword();
        if (TextUtils.isEmpty(password2)) {
            return null;
        }
        if (TextUtils.equals(password, password2)) {
            return password;
        }
        this.mConfirmPwdView.setError(getString(R.string.inconsistent_pwd));
        return null;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        AccountLog.d(TAG, "onActivityResult() requestCode:" + i + " resultCode:" + i2);
        if (i2 != -1) {
            sendChangePasswordResultByLocalBroadcast(false, i2);
            setResult(i2);
            finish();
        }
        if (i == 16 && i2 == -1) {
            NotificationAuthResult notificationAuthResult = (NotificationAuthResult) intent.getParcelableExtra("notification_auth_end");
            if (notificationAuthResult != null) {
                AccountManager.get(this).setUserData(this.mAccount, Constants.PASSPORT_IDENTITY_AUTH_TOKEN, notificationAuthResult.serviceToken);
                onPassIdentityAuth(this.mIdentityAuthReason);
            } else {
                return;
            }
        }
        super.onActivityResult(i, i2, intent);
    }

    /* access modifiers changed from: private */
    public void sendChangePasswordResultByLocalBroadcast(boolean z, int i) {
        Intent intent = new Intent(PassportUI.ACTION_LOCAL_ACCOUNT_CHANGE_PASSWORD);
        intent.putExtra("result", z);
        intent.putExtra("result_code", i);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    private void changePasswordAuth() {
        doIdentityAuth(IdentityAuthReason.CHANGE_PASSWORD);
    }

    private void doIdentityAuth(IdentityAuthReason identityAuthReason) {
        if (this.mGetIdentityUrlTask == null) {
            this.mIdentityAuthReason = identityAuthReason;
            String userData = AccountManager.get(this).getUserData(this.mAccount, Constants.PASSPORT_IDENTITY_AUTH_TOKEN);
            final ProgressHolder progressHolder = new ProgressHolder();
            progressHolder.showProgress(this);
            this.mGetIdentityUrlTask = new GetIdentityAuthUrlTask(this, userData, identityAuthReason, new GetIdentityAuthUrlTask.GetIdentityAuthUrlCallback() {
                public void onFail(int i) {
                    GetIdentityAuthUrlTask unused = ChangePasswordActivity.this.mGetIdentityUrlTask = null;
                    Toast.makeText(ChangePasswordActivity.this, i, 1).show();
                    progressHolder.dismissProgress();
                }

                public void onNeedNotification(String str) {
                    GetIdentityAuthUrlTask unused = ChangePasswordActivity.this.mGetIdentityUrlTask = null;
                    Intent newNotificationIntent = AuthenticatorUtil.newNotificationIntent(ChangePasswordActivity.this, (Parcelable) null, str, "passportapi", true, (Bundle) null);
                    newNotificationIntent.putExtra("userId", ChangePasswordActivity.this.mAccount.name);
                    newNotificationIntent.putExtra("passToken", AuthenticatorUtil.getPassToken(ChangePasswordActivity.this.getApplicationContext(), ChangePasswordActivity.this.mAccount));
                    ChangePasswordActivity.this.startActivityForResult(newNotificationIntent, 16);
                    ChangePasswordActivity.this.overridePendingTransition(0, 0);
                    progressHolder.dismissProgress();
                }

                public void onSuccess() {
                    GetIdentityAuthUrlTask unused = ChangePasswordActivity.this.mGetIdentityUrlTask = null;
                    ChangePasswordActivity.this.onPassIdentityAuth(ChangePasswordActivity.this.mIdentityAuthReason);
                    progressHolder.dismissProgress();
                }
            });
            this.mGetIdentityUrlTask.executeOnExecutor(XiaomiPassportExecutor.getSingleton(), new Void[0]);
        }
    }

    /* renamed from: com.xiaomi.passport.ui.settings.ChangePasswordActivity$6  reason: invalid class name */
    static /* synthetic */ class AnonymousClass6 {
        static final /* synthetic */ int[] $SwitchMap$com$xiaomi$accountsdk$account$data$IdentityAuthReason = new int[IdentityAuthReason.values().length];

        static {
            try {
                $SwitchMap$com$xiaomi$accountsdk$account$data$IdentityAuthReason[IdentityAuthReason.CHANGE_PASSWORD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    /* access modifiers changed from: private */
    public void onPassIdentityAuth(IdentityAuthReason identityAuthReason) {
        if (identityAuthReason != null && AnonymousClass6.$SwitchMap$com$xiaomi$accountsdk$account$data$IdentityAuthReason[identityAuthReason.ordinal()] == 1) {
            setContentVisibility(true);
        }
    }

    private void setContentVisibility(boolean z) {
        if (this.mContentView != null) {
            this.mContentView.setVisibility(z ? 0 : 8);
        }
    }

    private void changePassword(final String str) {
        if (TextUtils.isEmpty(str)) {
            AccountLog.w(TAG, "no valid newPwd");
            return;
        }
        final Context applicationContext = getApplicationContext();
        this.mChangePwdTask = new SimpleAsyncTask.Builder().setProgressDialogMessage(getFragmentManager(), getString(R.string.just_a_second)).setDoInBackgroundRunnable(new SimpleAsyncTask.DoInBackgroundRunnable<SimpleAsyncTask.ResultType>() {
            public SimpleAsyncTask.ResultType run() {
                XMPassportInfo build = XMPassportInfo.build(applicationContext, "passportapi");
                SimpleAsyncTask.ResultType resultType = SimpleAsyncTask.ResultType.ERROR_UNKNOWN;
                try {
                    AuthenticatorUtil.addOrUpdateAccountManager(applicationContext, new AccountInfo.Builder().userId(build.getUserId()).passToken(CloudHelper.changePassword(build, AuthenticatorUtil.getPassToken(applicationContext, ChangePasswordActivity.this.mAccount), str, AccountManager.get(applicationContext).getUserData(ChangePasswordActivity.this.mAccount, Constants.PASSPORT_IDENTITY_AUTH_TOKEN), "passportapi")).hasPwd(true).build());
                    return SimpleAsyncTask.ResultType.SUCCESS;
                } catch (AccessDeniedException e) {
                    AccountLog.e(ChangePasswordActivity.TAG, "changePassword", e);
                    return SimpleAsyncTask.ResultType.ERROR_ACCESS_DENIED;
                } catch (AuthenticationFailureException e2) {
                    AccountLog.e(ChangePasswordActivity.TAG, "changePassword", e2);
                    return SimpleAsyncTask.ResultType.ERROR_AUTH_FAIL;
                } catch (IOException e3) {
                    AccountLog.e(ChangePasswordActivity.TAG, "changePassword", e3);
                    return SimpleAsyncTask.ResultType.ERROR_NETWORK;
                } catch (InvalidCredentialException e4) {
                    AccountLog.e(ChangePasswordActivity.TAG, "changePassword", e4);
                    return SimpleAsyncTask.ResultType.ERROR_AUTH_FAIL;
                } catch (InvalidParameterException e5) {
                    AccountLog.e(ChangePasswordActivity.TAG, "changePassword", e5);
                    return SimpleAsyncTask.ResultType.ERROR_SAME_NEW_AND_OLD_PASS;
                } catch (CipherException | InvalidResponseException e6) {
                    AccountLog.e(ChangePasswordActivity.TAG, "changePassword", e6);
                    return SimpleAsyncTask.ResultType.ERROR_SERVER;
                }
            }
        }).setOnPostExecuteRunnable(new SimpleAsyncTask.OnPostExecuteRunnable<SimpleAsyncTask.ResultType>() {
            public void run(SimpleAsyncTask.ResultType resultType) {
                if (resultType.success()) {
                    Toast.makeText(applicationContext, R.string.set_success, 1).show();
                    ChangePasswordActivity.this.setResult(-1);
                    ChangePasswordActivity.this.sendChangePasswordResultByLocalBroadcast(true, -1);
                    ChangePasswordActivity.this.finish();
                    return;
                }
                ChangePasswordActivity.this.updateErrorMsgView(true, resultType.getErrorMessageResId());
            }
        }).build();
        this.mChangePwdTask.executeOnExecutor(XiaomiPassportExecutor.getSingleton(), new Void[0]);
    }

    /* access modifiers changed from: private */
    public void updateErrorMsgView(boolean z, int i) {
        this.mErrorView.setVisibility(z ? 0 : 8);
        if (i != -1) {
            this.mErrorView.setText(i);
        }
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, ChangePasswordActivity.class);
    }
}
