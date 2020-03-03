package com.xiaomi.passport.ui.internal.util;

import android.app.Activity;
import android.app.FragmentManager;
import com.xiaomi.accountsdk.account.data.AccountInfo;
import com.xiaomi.accountsdk.account.data.PasswordLoginParams;
import com.xiaomi.accountsdk.account.data.PhoneTicketLoginParams;
import com.xiaomi.accountsdk.account.data.PhoneTokenRegisterParams;
import com.xiaomi.accountsdk.account.data.QueryPhoneInfoParams;
import com.xiaomi.accountsdk.account.data.RegisterUserInfo;
import com.xiaomi.accountsdk.account.data.SendPhoneTicketParams;
import com.xiaomi.accountsdk.account.data.Step2LoginParams;
import com.xiaomi.accountsdk.account.exception.InvalidVerifyCodeException;
import com.xiaomi.accountsdk.utils.AccountLog;
import com.xiaomi.passport.ui.R;
import com.xiaomi.passport.ui.internal.SimpleDialogFragment;
import com.xiaomi.passport.uicontroller.PhoneLoginController;
import com.xiaomi.passport.uicontroller.SimpleFutureTask;
import com.xiaomi.passport.utils.AccountHelper;
import com.xiaomi.passport.utils.AsyncTestMarker;
import com.xiaomi.passport.utils.AuthenticatorUtil;
import com.xiaomi.passport.utils.XiaomiPassportExecutor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class LoginUIController {
    private static final String TAG = "LoginUIController";
    /* access modifiers changed from: private */
    public final Activity mActivity;
    private PhoneLoginController mPhoneLoginController;
    /* access modifiers changed from: private */
    public PhoneLoginController.PhoneUserInfoHelper mPhoneUserInfoHelper;
    private SimpleDialogFragment mProgressDialog;
    private Map<UIControllerType, FutureTask> mUIControllerFutures = new HashMap();

    public interface OnLoginSuccessRunnable {
        void run(AccountInfo accountInfo);
    }

    public interface PasswordLoginCallback {
        void onLoginByStep2(Step2LoginParams step2LoginParams);

        void onLoginFailed(int i);

        void onLoginSuccess(AccountInfo accountInfo);

        void onNeedCaptchaCode(boolean z, String str);

        void onNeedNotification(String str, String str2);
    }

    public interface QueryDualPhoneUserInfoCallback {
        void onFailed(int i);

        void onSuccess(List<RegisterUserInfo> list);

        void onTokenExpired();
    }

    public interface Step2LoginCallback {
        void onInvalidStep2Code(int i);

        void onLoginFailed(int i);

        void onLoginSuccess(AccountInfo accountInfo);

        void onPwdError();
    }

    private enum UIControllerType {
        PASSWORD_LOGIN,
        PHONE_LOGIN,
        PHONE_REGISTER,
        SEND_PHONE_TICKET,
        ADD_OR_UPDATE_ACCOUNT_MANAGER,
        QUERY_PHONE_USER_INFO
    }

    public LoginUIController(Activity activity) {
        this.mActivity = activity;
        this.mPhoneLoginController = new PhoneLoginController();
        this.mPhoneUserInfoHelper = new PhoneLoginController.PhoneUserInfoHelper() {
            public RegisterUserInfo query(QueryPhoneInfoParams queryPhoneInfoParams) throws Exception {
                return InNetDateHelper.updateRegisterStatusIfNeed(LoginUIController.this.mActivity.getApplicationContext(), LoginUIController.this.mActivity.getFragmentManager(), super.query(queryPhoneInfoParams), queryPhoneInfoParams);
            }
        };
        this.mPhoneLoginController.setPhoneUserInfoHelper(this.mPhoneUserInfoHelper);
    }

    public void cancel() {
        clearLoginFutureTasks();
    }

    public void loginByPassword(final PasswordLoginParams passwordLoginParams, final PasswordLoginCallback passwordLoginCallback) {
        if (isFutureTaskRunning(UIControllerType.PASSWORD_LOGIN)) {
            AccountLog.d(TAG, "password login has not finished");
        } else if (passwordLoginCallback == null) {
            throw new IllegalArgumentException("should implements login callback");
        } else if (this.mActivity == null || this.mActivity.isFinishing()) {
            AccountLog.i(TAG, "activity non exist");
        } else {
            showLoginLoadingDialog(this.mActivity.getFragmentManager(), this.mActivity.getString(R.string.passport_checking_account));
            AsyncTestMarker.increment();
            SimpleFutureTask simpleFutureTask = new SimpleFutureTask(new Callable<AccountInfo>() {
                public AccountInfo call() throws Exception {
                    return AccountHelper.loginByPassword(passwordLoginParams);
                }
            }, new SimpleFutureTask.Callback<AccountInfo>() {
                /* JADX WARNING: Code restructure failed: missing block: B:52:0x012d, code lost:
                    r5.onLoginFailed(r5);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:57:?, code lost:
                    return;
                 */
                /* JADX WARNING: Removed duplicated region for block: B:59:? A[RETURN, SYNTHETIC] */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void call(com.xiaomi.passport.uicontroller.SimpleFutureTask<com.xiaomi.accountsdk.account.data.AccountInfo> r5) {
                    /*
                        r4 = this;
                        com.xiaomi.passport.ui.internal.util.LoginUIController r0 = com.xiaomi.passport.ui.internal.util.LoginUIController.this
                        android.app.Activity r0 = r0.mActivity
                        if (r0 == 0) goto L_0x0139
                        com.xiaomi.passport.ui.internal.util.LoginUIController r0 = com.xiaomi.passport.ui.internal.util.LoginUIController.this
                        android.app.Activity r0 = r0.mActivity
                        boolean r0 = r0.isFinishing()
                        if (r0 == 0) goto L_0x0016
                        goto L_0x0139
                    L_0x0016:
                        r0 = -1
                        java.lang.Object r5 = r5.get()     // Catch:{ InterruptedException -> 0x011c, ExecutionException -> 0x002c }
                        com.xiaomi.accountsdk.account.data.AccountInfo r5 = (com.xiaomi.accountsdk.account.data.AccountInfo) r5     // Catch:{ InterruptedException -> 0x011c, ExecutionException -> 0x002c }
                        com.xiaomi.passport.ui.internal.util.LoginUIController$PasswordLoginCallback r1 = r5     // Catch:{ InterruptedException -> 0x011c, ExecutionException -> 0x002c }
                        r1.onLoginSuccess(r5)     // Catch:{ InterruptedException -> 0x011c, ExecutionException -> 0x002c }
                        com.xiaomi.passport.ui.internal.util.LoginUIController r5 = com.xiaomi.passport.ui.internal.util.LoginUIController.this
                        r5.dismissLoginLoadingDialog()
                        goto L_0x0132
                    L_0x0029:
                        r5 = move-exception
                        goto L_0x0133
                    L_0x002c:
                        r5 = move-exception
                        java.lang.String r1 = "LoginUIController"
                        java.lang.String r2 = "execution error"
                        com.xiaomi.accountsdk.utils.AccountLog.e(r1, r2, r5)     // Catch:{ all -> 0x0029 }
                        java.lang.Throwable r1 = r5.getCause()     // Catch:{ all -> 0x0029 }
                        boolean r2 = r1 instanceof com.xiaomi.accountsdk.account.exception.NeedNotificationException     // Catch:{ all -> 0x0029 }
                        if (r2 == 0) goto L_0x0053
                        java.lang.String r2 = "LoginUIController"
                        java.lang.String r3 = "need notification"
                        com.xiaomi.accountsdk.utils.AccountLog.e(r2, r3, r5)     // Catch:{ all -> 0x0029 }
                        com.xiaomi.passport.ui.internal.util.LoginUIController$PasswordLoginCallback r5 = r5     // Catch:{ all -> 0x0029 }
                        com.xiaomi.accountsdk.account.data.PasswordLoginParams r2 = r4     // Catch:{ all -> 0x0029 }
                        java.lang.String r2 = r2.serviceId     // Catch:{ all -> 0x0029 }
                        com.xiaomi.accountsdk.account.exception.NeedNotificationException r1 = (com.xiaomi.accountsdk.account.exception.NeedNotificationException) r1     // Catch:{ all -> 0x0029 }
                        java.lang.String r1 = r1.getNotificationUrl()     // Catch:{ all -> 0x0029 }
                        r5.onNeedNotification(r2, r1)     // Catch:{ all -> 0x0029 }
                        goto L_0x00a6
                    L_0x0053:
                        boolean r2 = r1 instanceof com.xiaomi.accountsdk.account.exception.NeedVerificationException     // Catch:{ all -> 0x0029 }
                        if (r2 == 0) goto L_0x008f
                        java.lang.String r2 = "LoginUIController"
                        java.lang.String r3 = "need step2 login"
                        com.xiaomi.accountsdk.utils.AccountLog.e(r2, r3, r5)     // Catch:{ all -> 0x0029 }
                        com.xiaomi.accountsdk.account.exception.NeedVerificationException r1 = (com.xiaomi.accountsdk.account.exception.NeedVerificationException) r1     // Catch:{ all -> 0x0029 }
                        com.xiaomi.accountsdk.account.data.Step2LoginParams$Builder r5 = new com.xiaomi.accountsdk.account.data.Step2LoginParams$Builder     // Catch:{ all -> 0x0029 }
                        r5.<init>()     // Catch:{ all -> 0x0029 }
                        java.lang.String r2 = r1.getUserId()     // Catch:{ all -> 0x0029 }
                        com.xiaomi.accountsdk.account.data.Step2LoginParams$Builder r5 = r5.setUserId(r2)     // Catch:{ all -> 0x0029 }
                        com.xiaomi.accountsdk.account.data.MetaLoginData r2 = r1.getMetaLoginData()     // Catch:{ all -> 0x0029 }
                        com.xiaomi.accountsdk.account.data.Step2LoginParams$Builder r5 = r5.setMetaLoginData(r2)     // Catch:{ all -> 0x0029 }
                        com.xiaomi.accountsdk.account.data.PasswordLoginParams r2 = r4     // Catch:{ all -> 0x0029 }
                        java.lang.String r2 = r2.serviceId     // Catch:{ all -> 0x0029 }
                        com.xiaomi.accountsdk.account.data.Step2LoginParams$Builder r5 = r5.setServiceId(r2)     // Catch:{ all -> 0x0029 }
                        java.lang.String r1 = r1.getStep1Token()     // Catch:{ all -> 0x0029 }
                        com.xiaomi.accountsdk.account.data.Step2LoginParams$Builder r5 = r5.setStep1Token(r1)     // Catch:{ all -> 0x0029 }
                        com.xiaomi.accountsdk.account.data.Step2LoginParams r5 = r5.build()     // Catch:{ all -> 0x0029 }
                        com.xiaomi.passport.ui.internal.util.LoginUIController$PasswordLoginCallback r1 = r5     // Catch:{ all -> 0x0029 }
                        r1.onLoginByStep2(r5)     // Catch:{ all -> 0x0029 }
                        goto L_0x00a6
                    L_0x008f:
                        boolean r2 = r1 instanceof com.xiaomi.accountsdk.account.exception.NeedCaptchaException     // Catch:{ all -> 0x0029 }
                        if (r2 == 0) goto L_0x00a9
                        java.lang.String r5 = "LoginUIController"
                        java.lang.String r2 = "need captcha"
                        com.xiaomi.accountsdk.utils.AccountLog.e(r5, r2)     // Catch:{ all -> 0x0029 }
                        com.xiaomi.passport.ui.internal.util.LoginUIController$PasswordLoginCallback r5 = r5     // Catch:{ all -> 0x0029 }
                        r2 = 0
                        com.xiaomi.accountsdk.account.exception.NeedCaptchaException r1 = (com.xiaomi.accountsdk.account.exception.NeedCaptchaException) r1     // Catch:{ all -> 0x0029 }
                        java.lang.String r1 = r1.getCaptchaUrl()     // Catch:{ all -> 0x0029 }
                        r5.onNeedCaptchaCode(r2, r1)     // Catch:{ all -> 0x0029 }
                    L_0x00a6:
                        r5 = -1
                        goto L_0x0114
                    L_0x00a9:
                        boolean r2 = r1 instanceof com.xiaomi.accountsdk.account.exception.InvalidCredentialException     // Catch:{ all -> 0x0029 }
                        if (r2 == 0) goto L_0x00da
                        java.lang.String r2 = "LoginUIController"
                        java.lang.String r3 = "wrong password"
                        com.xiaomi.accountsdk.utils.AccountLog.e(r2, r3, r5)     // Catch:{ all -> 0x0029 }
                        r5 = r1
                        com.xiaomi.accountsdk.account.exception.InvalidCredentialException r5 = (com.xiaomi.accountsdk.account.exception.InvalidCredentialException) r5     // Catch:{ all -> 0x0029 }
                        java.lang.String r2 = r5.getCaptchaUrl()     // Catch:{ all -> 0x0029 }
                        boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x0029 }
                        if (r2 != 0) goto L_0x00cc
                        com.xiaomi.passport.ui.internal.util.LoginUIController$PasswordLoginCallback r1 = r5     // Catch:{ all -> 0x0029 }
                        r2 = 1
                        java.lang.String r5 = r5.getCaptchaUrl()     // Catch:{ all -> 0x0029 }
                        r1.onNeedCaptchaCode(r2, r5)     // Catch:{ all -> 0x0029 }
                        goto L_0x00a6
                    L_0x00cc:
                        com.xiaomi.accountsdk.account.exception.InvalidCredentialException r1 = (com.xiaomi.accountsdk.account.exception.InvalidCredentialException) r1     // Catch:{ all -> 0x0029 }
                        boolean r5 = r1.getHasPwd()     // Catch:{ all -> 0x0029 }
                        if (r5 == 0) goto L_0x00d7
                        int r5 = com.xiaomi.passport.ui.R.string.passport_bad_authentication     // Catch:{ all -> 0x0029 }
                        goto L_0x0114
                    L_0x00d7:
                        int r5 = com.xiaomi.passport.ui.R.string.passport_error_no_password_user     // Catch:{ all -> 0x0029 }
                        goto L_0x0114
                    L_0x00da:
                        boolean r2 = r1 instanceof java.io.IOException     // Catch:{ all -> 0x0029 }
                        if (r2 == 0) goto L_0x00e8
                        java.lang.String r1 = "LoginUIController"
                        java.lang.String r2 = "network error"
                        com.xiaomi.accountsdk.utils.AccountLog.e(r1, r2, r5)     // Catch:{ all -> 0x0029 }
                        int r5 = com.xiaomi.passport.ui.R.string.passport_error_network     // Catch:{ all -> 0x0029 }
                        goto L_0x0114
                    L_0x00e8:
                        boolean r2 = r1 instanceof com.xiaomi.accountsdk.account.exception.InvalidUserNameException     // Catch:{ all -> 0x0029 }
                        if (r2 == 0) goto L_0x00f6
                        java.lang.String r1 = "LoginUIController"
                        java.lang.String r2 = "nonExist user name"
                        com.xiaomi.accountsdk.utils.AccountLog.e(r1, r2, r5)     // Catch:{ all -> 0x0029 }
                        int r5 = com.xiaomi.passport.ui.R.string.passport_error_user_name     // Catch:{ all -> 0x0029 }
                        goto L_0x0114
                    L_0x00f6:
                        boolean r2 = r1 instanceof com.xiaomi.accountsdk.request.AccessDeniedException     // Catch:{ all -> 0x0029 }
                        if (r2 == 0) goto L_0x0104
                        java.lang.String r1 = "LoginUIController"
                        java.lang.String r2 = "access denied"
                        com.xiaomi.accountsdk.utils.AccountLog.e(r1, r2, r5)     // Catch:{ all -> 0x0029 }
                        int r5 = com.xiaomi.passport.ui.R.string.passport_access_denied     // Catch:{ all -> 0x0029 }
                        goto L_0x0114
                    L_0x0104:
                        boolean r1 = r1 instanceof com.xiaomi.accountsdk.request.InvalidResponseException     // Catch:{ all -> 0x0029 }
                        if (r1 == 0) goto L_0x0112
                        java.lang.String r1 = "LoginUIController"
                        java.lang.String r2 = "invalid response"
                        com.xiaomi.accountsdk.utils.AccountLog.e(r1, r2, r5)     // Catch:{ all -> 0x0029 }
                        int r5 = com.xiaomi.passport.ui.R.string.passport_error_server     // Catch:{ all -> 0x0029 }
                        goto L_0x0114
                    L_0x0112:
                        int r5 = com.xiaomi.passport.ui.R.string.passport_error_unknown     // Catch:{ all -> 0x0029 }
                    L_0x0114:
                        com.xiaomi.passport.ui.internal.util.LoginUIController r1 = com.xiaomi.passport.ui.internal.util.LoginUIController.this
                        r1.dismissLoginLoadingDialog()
                        if (r5 == r0) goto L_0x0132
                        goto L_0x012d
                    L_0x011c:
                        r5 = move-exception
                        java.lang.String r1 = "LoginUIController"
                        java.lang.String r2 = "interrupted"
                        com.xiaomi.accountsdk.utils.AccountLog.e(r1, r2, r5)     // Catch:{ all -> 0x0029 }
                        int r5 = com.xiaomi.passport.ui.R.string.passport_error_unknown     // Catch:{ all -> 0x0029 }
                        com.xiaomi.passport.ui.internal.util.LoginUIController r1 = com.xiaomi.passport.ui.internal.util.LoginUIController.this
                        r1.dismissLoginLoadingDialog()
                        if (r5 == r0) goto L_0x0132
                    L_0x012d:
                        com.xiaomi.passport.ui.internal.util.LoginUIController$PasswordLoginCallback r0 = r5
                        r0.onLoginFailed(r5)
                    L_0x0132:
                        return
                    L_0x0133:
                        com.xiaomi.passport.ui.internal.util.LoginUIController r0 = com.xiaomi.passport.ui.internal.util.LoginUIController.this
                        r0.dismissLoginLoadingDialog()
                        throw r5
                    L_0x0139:
                        java.lang.String r5 = "LoginUIController"
                        java.lang.String r0 = "activity not alive"
                        com.xiaomi.accountsdk.utils.AccountLog.d(r5, r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.passport.ui.internal.util.LoginUIController.AnonymousClass2.call(com.xiaomi.passport.uicontroller.SimpleFutureTask):void");
                }
            });
            XiaomiPassportExecutor.getSingleton().submit(simpleFutureTask);
            this.mUIControllerFutures.put(UIControllerType.PASSWORD_LOGIN, simpleFutureTask);
        }
    }

    public void loginByStep2(final Step2LoginParams step2LoginParams, final Step2LoginCallback step2LoginCallback) {
        if (isFutureTaskRunning(UIControllerType.PASSWORD_LOGIN)) {
            AccountLog.d(TAG, "password login has not finished");
        } else if (step2LoginCallback == null) {
            throw new IllegalArgumentException("should implements login callback");
        } else if (this.mActivity == null || this.mActivity.isFinishing()) {
            AccountLog.i(TAG, "activity non exist");
        } else {
            showLoginLoadingDialog(this.mActivity.getFragmentManager(), this.mActivity.getString(R.string.passport_checking_account));
            AsyncTestMarker.increment();
            SimpleFutureTask simpleFutureTask = new SimpleFutureTask(new Callable<AccountInfo>() {
                public AccountInfo call() throws Exception {
                    return AccountHelper.getServiceTokenByStep2(step2LoginParams.userId, step2LoginParams.step2code, step2LoginParams.metaLoginData, step2LoginParams.trust, step2LoginParams.step1Token, step2LoginParams.serviceId);
                }
            }, new SimpleFutureTask.Callback<AccountInfo>() {
                /* JADX WARNING: Code restructure failed: missing block: B:40:0x00b0, code lost:
                    r5.onLoginFailed(r4);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
                    return;
                 */
                /* JADX WARNING: Removed duplicated region for block: B:47:? A[RETURN, SYNTHETIC] */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void call(com.xiaomi.passport.uicontroller.SimpleFutureTask<com.xiaomi.accountsdk.account.data.AccountInfo> r4) {
                    /*
                        r3 = this;
                        com.xiaomi.passport.ui.internal.util.LoginUIController r0 = com.xiaomi.passport.ui.internal.util.LoginUIController.this
                        android.app.Activity r0 = r0.mActivity
                        if (r0 == 0) goto L_0x00bc
                        com.xiaomi.passport.ui.internal.util.LoginUIController r0 = com.xiaomi.passport.ui.internal.util.LoginUIController.this
                        android.app.Activity r0 = r0.mActivity
                        boolean r0 = r0.isFinishing()
                        if (r0 == 0) goto L_0x0016
                        goto L_0x00bc
                    L_0x0016:
                        r0 = -1
                        java.lang.Object r4 = r4.get()     // Catch:{ InterruptedException -> 0x009f, ExecutionException -> 0x002c }
                        com.xiaomi.accountsdk.account.data.AccountInfo r4 = (com.xiaomi.accountsdk.account.data.AccountInfo) r4     // Catch:{ InterruptedException -> 0x009f, ExecutionException -> 0x002c }
                        com.xiaomi.passport.ui.internal.util.LoginUIController$Step2LoginCallback r1 = r5     // Catch:{ InterruptedException -> 0x009f, ExecutionException -> 0x002c }
                        r1.onLoginSuccess(r4)     // Catch:{ InterruptedException -> 0x009f, ExecutionException -> 0x002c }
                        com.xiaomi.passport.ui.internal.util.LoginUIController r4 = com.xiaomi.passport.ui.internal.util.LoginUIController.this
                        r4.dismissLoginLoadingDialog()
                        goto L_0x00b5
                    L_0x0029:
                        r4 = move-exception
                        goto L_0x00b6
                    L_0x002c:
                        r4 = move-exception
                        java.lang.String r1 = "LoginUIController"
                        java.lang.String r2 = "execution error"
                        com.xiaomi.accountsdk.utils.AccountLog.e(r1, r2, r4)     // Catch:{ all -> 0x0029 }
                        java.lang.Throwable r1 = r4.getCause()     // Catch:{ all -> 0x0029 }
                        boolean r2 = r1 instanceof com.xiaomi.accountsdk.account.exception.InvalidStep2codeException     // Catch:{ all -> 0x0029 }
                        if (r2 == 0) goto L_0x004b
                        java.lang.String r1 = "LoginUIController"
                        java.lang.String r2 = "wrong step2 code"
                        com.xiaomi.accountsdk.utils.AccountLog.e(r1, r2, r4)     // Catch:{ all -> 0x0029 }
                        com.xiaomi.passport.ui.internal.util.LoginUIController$Step2LoginCallback r4 = r5     // Catch:{ all -> 0x0029 }
                        int r1 = com.xiaomi.passport.ui.R.string.passport_wrong_vcode     // Catch:{ all -> 0x0029 }
                        r4.onInvalidStep2Code(r1)     // Catch:{ all -> 0x0029 }
                        goto L_0x005b
                    L_0x004b:
                        boolean r2 = r1 instanceof com.xiaomi.accountsdk.account.exception.InvalidCredentialException     // Catch:{ all -> 0x0029 }
                        if (r2 == 0) goto L_0x005d
                        java.lang.String r1 = "LoginUIController"
                        java.lang.String r2 = "wrong password"
                        com.xiaomi.accountsdk.utils.AccountLog.e(r1, r2, r4)     // Catch:{ all -> 0x0029 }
                        com.xiaomi.passport.ui.internal.util.LoginUIController$Step2LoginCallback r4 = r5     // Catch:{ all -> 0x0029 }
                        r4.onPwdError()     // Catch:{ all -> 0x0029 }
                    L_0x005b:
                        r4 = -1
                        goto L_0x0097
                    L_0x005d:
                        boolean r2 = r1 instanceof java.io.IOException     // Catch:{ all -> 0x0029 }
                        if (r2 == 0) goto L_0x006b
                        java.lang.String r1 = "LoginUIController"
                        java.lang.String r2 = "network error"
                        com.xiaomi.accountsdk.utils.AccountLog.e(r1, r2, r4)     // Catch:{ all -> 0x0029 }
                        int r4 = com.xiaomi.passport.ui.R.string.passport_error_network     // Catch:{ all -> 0x0029 }
                        goto L_0x0097
                    L_0x006b:
                        boolean r2 = r1 instanceof com.xiaomi.accountsdk.account.exception.InvalidUserNameException     // Catch:{ all -> 0x0029 }
                        if (r2 == 0) goto L_0x0079
                        java.lang.String r1 = "LoginUIController"
                        java.lang.String r2 = "nonExist user name"
                        com.xiaomi.accountsdk.utils.AccountLog.e(r1, r2, r4)     // Catch:{ all -> 0x0029 }
                        int r4 = com.xiaomi.passport.ui.R.string.passport_error_user_name     // Catch:{ all -> 0x0029 }
                        goto L_0x0097
                    L_0x0079:
                        boolean r2 = r1 instanceof com.xiaomi.accountsdk.request.AccessDeniedException     // Catch:{ all -> 0x0029 }
                        if (r2 == 0) goto L_0x0087
                        java.lang.String r1 = "LoginUIController"
                        java.lang.String r2 = "access denied"
                        com.xiaomi.accountsdk.utils.AccountLog.e(r1, r2, r4)     // Catch:{ all -> 0x0029 }
                        int r4 = com.xiaomi.passport.ui.R.string.passport_access_denied     // Catch:{ all -> 0x0029 }
                        goto L_0x0097
                    L_0x0087:
                        boolean r1 = r1 instanceof com.xiaomi.accountsdk.request.InvalidResponseException     // Catch:{ all -> 0x0029 }
                        if (r1 == 0) goto L_0x0095
                        java.lang.String r1 = "LoginUIController"
                        java.lang.String r2 = "invalid response"
                        com.xiaomi.accountsdk.utils.AccountLog.e(r1, r2, r4)     // Catch:{ all -> 0x0029 }
                        int r4 = com.xiaomi.passport.ui.R.string.passport_error_server     // Catch:{ all -> 0x0029 }
                        goto L_0x0097
                    L_0x0095:
                        int r4 = com.xiaomi.passport.ui.R.string.passport_error_unknown     // Catch:{ all -> 0x0029 }
                    L_0x0097:
                        com.xiaomi.passport.ui.internal.util.LoginUIController r1 = com.xiaomi.passport.ui.internal.util.LoginUIController.this
                        r1.dismissLoginLoadingDialog()
                        if (r4 == r0) goto L_0x00b5
                        goto L_0x00b0
                    L_0x009f:
                        r4 = move-exception
                        java.lang.String r1 = "LoginUIController"
                        java.lang.String r2 = "interrupted"
                        com.xiaomi.accountsdk.utils.AccountLog.e(r1, r2, r4)     // Catch:{ all -> 0x0029 }
                        int r4 = com.xiaomi.passport.ui.R.string.passport_error_unknown     // Catch:{ all -> 0x0029 }
                        com.xiaomi.passport.ui.internal.util.LoginUIController r1 = com.xiaomi.passport.ui.internal.util.LoginUIController.this
                        r1.dismissLoginLoadingDialog()
                        if (r4 == r0) goto L_0x00b5
                    L_0x00b0:
                        com.xiaomi.passport.ui.internal.util.LoginUIController$Step2LoginCallback r0 = r5
                        r0.onLoginFailed(r4)
                    L_0x00b5:
                        return
                    L_0x00b6:
                        com.xiaomi.passport.ui.internal.util.LoginUIController r0 = com.xiaomi.passport.ui.internal.util.LoginUIController.this
                        r0.dismissLoginLoadingDialog()
                        throw r4
                    L_0x00bc:
                        java.lang.String r4 = "LoginUIController"
                        java.lang.String r0 = "activity not alive"
                        com.xiaomi.accountsdk.utils.AccountLog.d(r4, r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.passport.ui.internal.util.LoginUIController.AnonymousClass4.call(com.xiaomi.passport.uicontroller.SimpleFutureTask):void");
                }
            });
            XiaomiPassportExecutor.getSingleton().submit(simpleFutureTask);
            this.mUIControllerFutures.put(UIControllerType.PASSWORD_LOGIN, simpleFutureTask);
        }
    }

    public void sendPhoneLoginTicket(SendPhoneTicketParams sendPhoneTicketParams, final PhoneLoginController.SendPhoneTicketCallback sendPhoneTicketCallback) {
        if (isFutureTaskRunning(UIControllerType.SEND_PHONE_TICKET)) {
            AccountLog.d(TAG, "send phone ticket task has not finished");
        } else if (sendPhoneTicketCallback == null) {
            throw new IllegalArgumentException("should implements login callback");
        } else if (this.mActivity == null || this.mActivity.isFinishing()) {
            AccountLog.i(TAG, "activity non exist");
        } else {
            showLoginLoadingDialog(this.mActivity.getFragmentManager(), this.mActivity.getString(R.string.passport_sending_vcode));
            this.mUIControllerFutures.put(UIControllerType.SEND_PHONE_TICKET, this.mPhoneLoginController.sendPhoneTicket(sendPhoneTicketParams, new PhoneLoginController.SendPhoneTicketCallback() {
                public void onSentSuccess(int i) {
                    LoginUIController.this.dismissLoginLoadingDialog();
                    sendPhoneTicketCallback.onSentSuccess(i);
                }

                public void onNeedCaptchaCode(String str) {
                    LoginUIController.this.dismissLoginLoadingDialog();
                    sendPhoneTicketCallback.onNeedCaptchaCode(str);
                }

                public void onActivatorTokenExpired() {
                    LoginUIController.this.dismissLoginLoadingDialog();
                    sendPhoneTicketCallback.onActivatorTokenExpired();
                }

                public void onSMSReachLimit() {
                    LoginUIController.this.dismissLoginLoadingDialog();
                    sendPhoneTicketCallback.onSMSReachLimit();
                }

                public void onPhoneNumInvalid() {
                    LoginUIController.this.dismissLoginLoadingDialog();
                    sendPhoneTicketCallback.onPhoneNumInvalid();
                }

                public void onSentFailed(PhoneLoginController.ErrorCode errorCode, String str) {
                    LoginUIController.this.dismissLoginLoadingDialog();
                    sendPhoneTicketCallback.onSentFailed(errorCode, str);
                }
            }));
        }
    }

    public void queryPhoneUserInfo(QueryPhoneInfoParams queryPhoneInfoParams, final PhoneLoginController.PhoneUserInfoCallback phoneUserInfoCallback, boolean z) {
        if (isFutureTaskRunning(UIControllerType.QUERY_PHONE_USER_INFO)) {
            AccountLog.d(TAG, "send phone ticket task has not finished");
        } else if (phoneUserInfoCallback == null) {
            throw new IllegalArgumentException("should implements phone user info callback");
        } else if (this.mActivity == null || this.mActivity.isFinishing()) {
            AccountLog.i(TAG, "activity non exist");
        } else {
            if (z) {
                showLoginLoadingDialog(this.mActivity.getFragmentManager(), this.mActivity.getString(R.string.passport_querying_phone_info));
            }
            this.mUIControllerFutures.put(UIControllerType.QUERY_PHONE_USER_INFO, this.mPhoneLoginController.queryPhoneUserInfo(queryPhoneInfoParams, new PhoneLoginController.PhoneUserInfoCallback() {
                public void onRecycledOrNotRegisteredPhone(RegisterUserInfo registerUserInfo) {
                    LoginUIController.this.dismissLoginLoadingDialog();
                    phoneUserInfoCallback.onRecycledOrNotRegisteredPhone(registerUserInfo);
                }

                public void onNotRecycledRegisteredPhone(RegisterUserInfo registerUserInfo) {
                    LoginUIController.this.dismissLoginLoadingDialog();
                    phoneUserInfoCallback.onNotRecycledRegisteredPhone(registerUserInfo);
                }

                public void onProbablyRecycleRegisteredPhone(RegisterUserInfo registerUserInfo) {
                    LoginUIController.this.dismissLoginLoadingDialog();
                    phoneUserInfoCallback.onProbablyRecycleRegisteredPhone(registerUserInfo);
                }

                public void onPhoneNumInvalid() {
                    LoginUIController.this.dismissLoginLoadingDialog();
                    phoneUserInfoCallback.onPhoneNumInvalid();
                }

                public void onTicketOrTokenInvalid() {
                    LoginUIController.this.dismissLoginLoadingDialog();
                    phoneUserInfoCallback.onTicketOrTokenInvalid();
                }

                public void onQueryFailed(PhoneLoginController.ErrorCode errorCode, String str) {
                    LoginUIController.this.dismissLoginLoadingDialog();
                    phoneUserInfoCallback.onQueryFailed(errorCode, str);
                }
            }));
        }
    }

    public void queryDualPhoneUserInfo(final QueryPhoneInfoParams queryPhoneInfoParams, final QueryPhoneInfoParams queryPhoneInfoParams2, final QueryDualPhoneUserInfoCallback queryDualPhoneUserInfoCallback, boolean z) {
        if (isFutureTaskRunning(UIControllerType.QUERY_PHONE_USER_INFO)) {
            AccountLog.d(TAG, "send phone ticket task has not finished");
        } else if (this.mActivity == null || this.mActivity.isFinishing()) {
            AccountLog.i(TAG, "activity non exist");
        } else {
            if (z) {
                showLoginLoadingDialog(this.mActivity.getFragmentManager(), this.mActivity.getString(R.string.passport_querying_phone_info));
            }
            SimpleFutureTask simpleFutureTask = new SimpleFutureTask(new Callable<List<RegisterUserInfo>>() {
                public List<RegisterUserInfo> call() throws Exception {
                    boolean z;
                    ArrayList arrayList = new ArrayList();
                    try {
                        arrayList.add(LoginUIController.this.mPhoneUserInfoHelper.query(queryPhoneInfoParams));
                        z = false;
                    } catch (InvalidVerifyCodeException e) {
                        AccountLog.d(LoginUIController.TAG, "phone1", e);
                        throw e;
                    } catch (Exception e2) {
                        AccountLog.w(LoginUIController.TAG, "phone1", e2);
                        z = true;
                    }
                    try {
                        arrayList.add(LoginUIController.this.mPhoneUserInfoHelper.query(queryPhoneInfoParams2));
                    } catch (InvalidVerifyCodeException e3) {
                        AccountLog.d(LoginUIController.TAG, "phone2", e3);
                        throw e3;
                    } catch (Exception e4) {
                        AccountLog.w(LoginUIController.TAG, "phone2", e4);
                        if (z) {
                            throw e4;
                        }
                    }
                    return arrayList;
                }
            }, new SimpleFutureTask.Callback<List<RegisterUserInfo>>() {
                public void call(SimpleFutureTask<List<RegisterUserInfo>> simpleFutureTask) {
                    try {
                        queryDualPhoneUserInfoCallback.onSuccess((List) simpleFutureTask.get());
                    } catch (InterruptedException e) {
                        AccountLog.e(LoginUIController.TAG, "query user phone info", e);
                        queryDualPhoneUserInfoCallback.onFailed(ErrorInfo.ERROR_UNKNOWN.errorMessageId);
                    } catch (ExecutionException e2) {
                        AccountLog.e(LoginUIController.TAG, "query user phone info", e2);
                        Throwable cause = e2.getCause();
                        if (cause instanceof InvalidVerifyCodeException) {
                            queryDualPhoneUserInfoCallback.onTokenExpired();
                        } else {
                            queryDualPhoneUserInfoCallback.onFailed(ErrorInfo.getMsgIdGivenException(cause));
                        }
                    } catch (Throwable th) {
                        LoginUIController.this.dismissLoginLoadingDialog();
                        throw th;
                    }
                    LoginUIController.this.dismissLoginLoadingDialog();
                }
            });
            XiaomiPassportExecutor.getSingleton().submit(simpleFutureTask);
            this.mUIControllerFutures.put(UIControllerType.QUERY_PHONE_USER_INFO, simpleFutureTask);
        }
    }

    public void loginByPhone(PhoneTicketLoginParams phoneTicketLoginParams, final PhoneLoginController.TicketLoginCallback ticketLoginCallback) {
        if (isFutureTaskRunning(UIControllerType.PHONE_LOGIN)) {
            AccountLog.d(TAG, "phone ticket login task has not finished");
        } else if (ticketLoginCallback == null) {
            throw new IllegalArgumentException("should implements login callback");
        } else if (this.mActivity == null || this.mActivity.isFinishing()) {
            AccountLog.i(TAG, "activity non exist");
        } else {
            showLoginLoadingDialog(this.mActivity.getFragmentManager(), this.mActivity.getString(R.string.passport_checking_account));
            this.mUIControllerFutures.put(UIControllerType.PHONE_LOGIN, this.mPhoneLoginController.ticketLogin(phoneTicketLoginParams, new PhoneLoginController.TicketLoginCallback() {
                public void onLoginSuccess(AccountInfo accountInfo) {
                    LoginUIController.this.dismissLoginLoadingDialog();
                    if (AuthenticatorUtil.addOrUpdateAccountManager(LoginUIController.this.mActivity, accountInfo)) {
                        ticketLoginCallback.onLoginSuccess(accountInfo);
                        return;
                    }
                    AccountLog.i(LoginUIController.TAG, "loginByPhone: fail to add account manager");
                    ticketLoginCallback.onLoginFailed(PhoneLoginController.ErrorCode.ERROR_UNKNOWN, "fail to add account manager", false);
                }

                public void onNeedNotification(String str, String str2) {
                    LoginUIController.this.dismissLoginLoadingDialog();
                    ticketLoginCallback.onNeedNotification(str, str2);
                }

                public void onPhoneNumInvalid() {
                    LoginUIController.this.dismissLoginLoadingDialog();
                    AccountLog.i(LoginUIController.TAG, "loginByPhone:invalid phone num");
                    ticketLoginCallback.onPhoneNumInvalid();
                }

                public void onTicketOrTokenInvalid() {
                    LoginUIController.this.dismissLoginLoadingDialog();
                    AccountLog.i(LoginUIController.TAG, "loginByPhone:token expired");
                    ticketLoginCallback.onTicketOrTokenInvalid();
                }

                public void onLoginFailed(PhoneLoginController.ErrorCode errorCode, String str, boolean z) {
                    LoginUIController.this.dismissLoginLoadingDialog();
                    AccountLog.i(LoginUIController.TAG, "loginByPhone: " + str);
                    ticketLoginCallback.onLoginFailed(errorCode, str, z);
                }
            }));
        }
    }

    public void registerByPhone(PhoneTokenRegisterParams phoneTokenRegisterParams, final PhoneLoginController.PhoneRegisterCallback phoneRegisterCallback) {
        if (isFutureTaskRunning(UIControllerType.PHONE_REGISTER)) {
            AccountLog.d(TAG, "send phone ticket task has not finished");
        } else if (phoneRegisterCallback == null) {
            throw new IllegalArgumentException("should implements login callback");
        } else if (this.mActivity == null || this.mActivity.isFinishing()) {
            AccountLog.i(TAG, "activity non exist");
        } else {
            showLoginLoadingDialog(this.mActivity.getFragmentManager(), this.mActivity.getString(R.string.passport_reging));
            this.mUIControllerFutures.put(UIControllerType.PHONE_REGISTER, this.mPhoneLoginController.register(phoneTokenRegisterParams, new PhoneLoginController.PhoneRegisterCallback() {
                public void onRegisterSuccess(AccountInfo accountInfo) {
                    LoginUIController.this.dismissLoginLoadingDialog();
                    phoneRegisterCallback.onRegisterSuccess(accountInfo);
                }

                public void onRegisterReachLimit() {
                    LoginUIController.this.dismissLoginLoadingDialog();
                    AccountLog.i(LoginUIController.TAG, "registerByPhone: reach register limit");
                    phoneRegisterCallback.onRegisterReachLimit();
                }

                public void onTokenExpired() {
                    LoginUIController.this.dismissLoginLoadingDialog();
                    AccountLog.i(LoginUIController.TAG, "registerByPhone: token expired");
                    phoneRegisterCallback.onTokenExpired();
                }

                public void onRegisterFailed(PhoneLoginController.ErrorCode errorCode, String str) {
                    LoginUIController.this.dismissLoginLoadingDialog();
                    AccountLog.i(LoginUIController.TAG, "registerByPhone: " + str);
                    phoneRegisterCallback.onRegisterFailed(errorCode, str);
                }
            }));
        }
    }

    private void showLoginLoadingDialog(FragmentManager fragmentManager, String str) {
        if (this.mProgressDialog != null) {
            dismissLoginLoadingDialog();
        }
        this.mProgressDialog = new SimpleDialogFragment.AlertDialogFragmentBuilder(2).setMessage(str).create();
        this.mProgressDialog.showAllowingStateLoss(fragmentManager, TAG);
    }

    /* access modifiers changed from: private */
    public void dismissLoginLoadingDialog() {
        if (this.mProgressDialog != null && this.mProgressDialog.getActivity() != null && !this.mProgressDialog.getActivity().isFinishing()) {
            this.mProgressDialog.dismissAllowingStateLoss();
            this.mProgressDialog = null;
        }
    }

    private boolean isFutureTaskRunning(UIControllerType uIControllerType) {
        FutureTask futureTask = this.mUIControllerFutures.get(uIControllerType);
        return futureTask != null && !futureTask.isDone();
    }

    private void clearLoginFutureTasks() {
        for (UIControllerType uIControllerType : this.mUIControllerFutures.keySet()) {
            FutureTask futureTask = this.mUIControllerFutures.get(uIControllerType);
            if (futureTask != null && !futureTask.isDone()) {
                futureTask.cancel(true);
            }
        }
        this.mUIControllerFutures.clear();
    }
}
