package com.xiaomi.passport.ui.settings;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;
import com.xiaomi.accounts.AccountManager;
import com.xiaomi.accountsdk.account.data.MiuiActivatorInfo;
import com.xiaomi.accountsdk.account.exception.InvalidPhoneNumException;
import com.xiaomi.accountsdk.account.exception.InvalidVerifyCodeException;
import com.xiaomi.accountsdk.account.exception.NeedCaptchaException;
import com.xiaomi.accountsdk.account.exception.NeedVerificationException;
import com.xiaomi.accountsdk.account.exception.ReachLimitException;
import com.xiaomi.accountsdk.account.exception.UserRestrictedException;
import com.xiaomi.accountsdk.request.AccessDeniedException;
import com.xiaomi.accountsdk.request.AuthenticationFailureException;
import com.xiaomi.accountsdk.request.CipherException;
import com.xiaomi.accountsdk.request.InvalidResponseException;
import com.xiaomi.accountsdk.utils.AccountLog;
import com.xiaomi.accountsdk.utils.ParcelableAttackGuardian;
import com.xiaomi.passport.data.XMPassportInfo;
import com.xiaomi.passport.ui.R;
import com.xiaomi.passport.ui.internal.Constants;
import com.xiaomi.passport.ui.internal.util.FriendlyFragmentUtils;
import com.xiaomi.passport.ui.settings.SimpleAsyncTask;
import com.xiaomi.passport.utils.AuthenticatorUtil;
import com.xiaomi.passport.utils.XiaomiPassportExecutor;
import java.io.IOException;

public class BindPhoneActivity extends AppCompatActivity {
    private static final String TAG = "BindPhoneActivity";
    String captchaUrl;
    /* access modifiers changed from: private */
    public Account mAccount;
    private InputBindedPhoneFragment mInputBoundPhoneFragment;
    private SimpleAsyncTask<Integer> mModifySafeTask;
    private SimpleAsyncTask<Integer> mSendModifySafeTicketTask;

    public interface ModifyPhoneCallback {
        void onError(int i);

        void onNeedSMSCode(String str);

        void onSuccess();
    }

    public interface SendTicketCallback {
        void onError(int i);

        void onNeedCaptchaCode(String str);

        void onSuccess();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!new ParcelableAttackGuardian().safeCheck(this)) {
            finish();
            return;
        }
        this.mAccount = AuthenticatorUtil.getXiaomiAccount(this);
        if (this.mAccount == null) {
            AccountLog.i(TAG, "no xiaomi account");
            finish();
            return;
        }
        this.mInputBoundPhoneFragment = new InputBindedPhoneFragment();
        this.mInputBoundPhoneFragment.setArguments(getIntent().getExtras());
        FriendlyFragmentUtils.addFragment(getFragmentManager(), 16908290, this.mInputBoundPhoneFragment);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        if (this.mModifySafeTask != null) {
            this.mModifySafeTask.cancel(true);
            this.mModifySafeTask = null;
        }
        if (this.mSendModifySafeTicketTask != null) {
            this.mSendModifySafeTicketTask.cancel(true);
            this.mSendModifySafeTicketTask = null;
        }
        super.onDestroy();
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (this.mInputBoundPhoneFragment != null) {
            this.mInputBoundPhoneFragment.onRequestPermissionsResult(i, strArr, iArr);
        }
    }

    public void modifySafePhone(final String str, MiuiActivatorInfo miuiActivatorInfo, String str2, final ModifyPhoneCallback modifyPhoneCallback) {
        if (this.mModifySafeTask == null || !this.mModifySafeTask.isRunning()) {
            final Context applicationContext = getApplicationContext();
            final Context context = applicationContext;
            final String str3 = str;
            final String str4 = str2;
            final MiuiActivatorInfo miuiActivatorInfo2 = miuiActivatorInfo;
            this.mModifySafeTask = new SimpleAsyncTask.Builder().setProgressDialogMessage(getFragmentManager(), getString(R.string.just_a_second)).setDoInBackgroundRunnable(new SimpleAsyncTask.DoInBackgroundRunnable<Integer>() {
                public Integer run() {
                    XMPassportInfo build = XMPassportInfo.build(context, "passportapi");
                    if (build == null) {
                        AccountLog.w(BindPhoneActivity.TAG, "null passportInfo");
                        return null;
                    }
                    String userData = AccountManager.get(context).getUserData(BindPhoneActivity.this.mAccount, "acc_user_phone");
                    int i = 5;
                    String userData2 = AccountManager.get(context).getUserData(BindPhoneActivity.this.mAccount, Constants.PASSPORT_IDENTITY_AUTH_TOKEN);
                    int i2 = 0;
                    while (i2 < 2) {
                        try {
                            CloudHelper.modifySafePhone(build, str3, str4, miuiActivatorInfo2, !TextUtils.isEmpty(userData), userData2, "passportapi");
                            return 0;
                        } catch (IOException e) {
                            AccountLog.e(BindPhoneActivity.TAG, "modifySafePhone", e);
                            i = 2;
                        } catch (AuthenticationFailureException e2) {
                            AccountLog.e(BindPhoneActivity.TAG, "modifySafePhone", e2);
                            build.refreshAuthToken(context);
                            i = 1;
                            i2++;
                        } catch (AccessDeniedException e3) {
                            AccountLog.e(BindPhoneActivity.TAG, "modifySafePhone", e3);
                            i = 4;
                        } catch (InvalidResponseException e4) {
                            AccountLog.e(BindPhoneActivity.TAG, "modifySafePhone", e4);
                            i = 3;
                            return Integer.valueOf(i);
                        } catch (CipherException e5) {
                            AccountLog.e(BindPhoneActivity.TAG, "modifySafePhone", e5);
                            i = 3;
                            return Integer.valueOf(i);
                        } catch (InvalidVerifyCodeException e6) {
                            AccountLog.e(BindPhoneActivity.TAG, "modifySafePhone", e6);
                            i = 7;
                            i2++;
                        } catch (InvalidPhoneNumException e7) {
                            AccountLog.e(BindPhoneActivity.TAG, "modifySafePhone", e7);
                            i = 9;
                        } catch (UserRestrictedException e8) {
                            AccountLog.e(BindPhoneActivity.TAG, "modifySafePhone", e8);
                            i = 11;
                        } catch (NeedVerificationException e9) {
                            AccountLog.e(BindPhoneActivity.TAG, "modifySafePhone", e9);
                            i = 15;
                            i2++;
                        }
                    }
                    return Integer.valueOf(i);
                }
            }).setOnPostExecuteRunnable(new SimpleAsyncTask.OnPostExecuteRunnable<Integer>() {
                public void run(Integer num) {
                    if (num == null) {
                        AccountLog.i(BindPhoneActivity.TAG, "modifySafePhone result is null");
                        return;
                    }
                    AsyncTaskResult asyncTaskResult = new AsyncTaskResult(num.intValue());
                    if (asyncTaskResult.getExceptionType() == 15) {
                        modifyPhoneCallback.onNeedSMSCode(str);
                    } else if (asyncTaskResult.hasException()) {
                        modifyPhoneCallback.onError(asyncTaskResult.getExceptionReason());
                    } else {
                        Intent intent = new Intent();
                        intent.putExtra("acc_user_phone", str);
                        BindPhoneActivity.this.setResult(-1, intent);
                        AccountManager.get(applicationContext).setUserData(BindPhoneActivity.this.mAccount, "acc_user_phone", str);
                        Toast.makeText(applicationContext, R.string.set_success, 1).show();
                        BindPhoneActivity.this.finish();
                    }
                }
            }).build();
            this.mModifySafeTask.executeOnExecutor(XiaomiPassportExecutor.getSingleton(), new Void[0]);
            return;
        }
        AccountLog.d(TAG, "modify safe phone task id running");
    }

    public void sendModifySafePhoneTicket(String str, String str2, String str3, final SendTicketCallback sendTicketCallback) {
        if (this.mSendModifySafeTicketTask == null || !this.mSendModifySafeTicketTask.isRunning()) {
            final Context applicationContext = getApplicationContext();
            final Context context = applicationContext;
            final String str4 = str;
            final String str5 = str2;
            final String str6 = str3;
            this.mSendModifySafeTicketTask = new SimpleAsyncTask.Builder().setProgressDialogMessage(getFragmentManager(), getString(R.string.passport_sending_vcode)).setDoInBackgroundRunnable(new SimpleAsyncTask.DoInBackgroundRunnable<Integer>() {
                public Integer run() {
                    XMPassportInfo build = XMPassportInfo.build(context, "passportapi");
                    if (build == null) {
                        AccountLog.w(BindPhoneActivity.TAG, "null passportInfo");
                        return null;
                    }
                    int i = 0;
                    int i2 = 5;
                    while (i < 2) {
                        try {
                            CloudHelper.sendModifySafePhoneTicket(build, str4, str5, str6, "passportapi");
                            return 0;
                        } catch (IOException e) {
                            AccountLog.e(BindPhoneActivity.TAG, "sendModifySafePhoneTicket", e);
                            i2 = 2;
                        } catch (AuthenticationFailureException e2) {
                            AccountLog.e(BindPhoneActivity.TAG, "sendModifySafePhoneTicket", e2);
                            build.refreshAuthToken(context);
                            i++;
                            i2 = 1;
                        } catch (AccessDeniedException e3) {
                            AccountLog.e(BindPhoneActivity.TAG, "sendModifySafePhoneTicket", e3);
                            i2 = 4;
                        } catch (InvalidResponseException e4) {
                            AccountLog.e(BindPhoneActivity.TAG, "sendModifySafePhoneTicket", e4);
                            i2 = 3;
                            return Integer.valueOf(i2);
                        } catch (CipherException e5) {
                            AccountLog.e(BindPhoneActivity.TAG, "sendModifySafePhoneTicket", e5);
                            i2 = 3;
                            return Integer.valueOf(i2);
                        } catch (InvalidPhoneNumException e6) {
                            AccountLog.e(BindPhoneActivity.TAG, "sendModifySafePhoneTicket", e6);
                            i2 = 9;
                        } catch (ReachLimitException e7) {
                            AccountLog.e(BindPhoneActivity.TAG, "sendModifySafePhoneTicket", e7);
                            i2 = 10;
                        } catch (NeedCaptchaException e8) {
                            AccountLog.e(BindPhoneActivity.TAG, "sendModifySafePhoneTicket", e8);
                            i2 = 12;
                            BindPhoneActivity.this.captchaUrl = e8.getCaptchaUrl();
                        }
                    }
                    return Integer.valueOf(i2);
                }
            }).setOnPostExecuteRunnable(new SimpleAsyncTask.OnPostExecuteRunnable<Integer>() {
                public void run(Integer num) {
                    if (num == null) {
                        AccountLog.i(BindPhoneActivity.TAG, "send ticket result is null");
                        return;
                    }
                    AsyncTaskResult asyncTaskResult = new AsyncTaskResult(num.intValue());
                    if (asyncTaskResult.getExceptionType() == 12) {
                        sendTicketCallback.onNeedCaptchaCode(BindPhoneActivity.this.captchaUrl);
                    } else if (asyncTaskResult.hasException()) {
                        sendTicketCallback.onError(asyncTaskResult.getExceptionReason());
                    } else {
                        Toast.makeText(applicationContext, R.string.sms_send_success, 1).show();
                        sendTicketCallback.onSuccess();
                    }
                }
            }).build();
            this.mSendModifySafeTicketTask.executeOnExecutor(XiaomiPassportExecutor.getSingleton(), new Void[0]);
            return;
        }
        AccountLog.d(TAG, "send modify phone ticket task is running");
    }
}
