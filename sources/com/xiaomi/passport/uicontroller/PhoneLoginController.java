package com.xiaomi.passport.uicontroller;

import android.text.TextUtils;
import com.xiaomi.accountsdk.account.XMPassport;
import com.xiaomi.accountsdk.account.XMPassportSettings;
import com.xiaomi.accountsdk.account.data.AccountInfo;
import com.xiaomi.accountsdk.account.data.CheckRegPhoneParams;
import com.xiaomi.accountsdk.account.data.PassportInfo;
import com.xiaomi.accountsdk.account.data.PasswordLoginParams;
import com.xiaomi.accountsdk.account.data.PhoneTicketLoginParams;
import com.xiaomi.accountsdk.account.data.PhoneTokenRegisterParams;
import com.xiaomi.accountsdk.account.data.QueryPhoneInfoParams;
import com.xiaomi.accountsdk.account.data.RegisterUserInfo;
import com.xiaomi.accountsdk.account.data.SendPhoneTicketParams;
import com.xiaomi.accountsdk.account.data.SetPasswordParams;
import com.xiaomi.accountsdk.account.data.Step2LoginParams;
import com.xiaomi.accountsdk.account.exception.AccountException;
import com.xiaomi.accountsdk.account.exception.HttpException;
import com.xiaomi.accountsdk.account.exception.InvalidCredentialException;
import com.xiaomi.accountsdk.account.exception.InvalidParameterException;
import com.xiaomi.accountsdk.account.exception.InvalidPhoneNumException;
import com.xiaomi.accountsdk.account.exception.InvalidPhoneOrTicketException;
import com.xiaomi.accountsdk.account.exception.InvalidUserNameException;
import com.xiaomi.accountsdk.account.exception.InvalidVerifyCodeException;
import com.xiaomi.accountsdk.account.exception.NeedCaptchaException;
import com.xiaomi.accountsdk.account.exception.NeedNotificationException;
import com.xiaomi.accountsdk.account.exception.NeedVerificationException;
import com.xiaomi.accountsdk.account.exception.PassportIOException;
import com.xiaomi.accountsdk.account.exception.ReachLimitException;
import com.xiaomi.accountsdk.account.exception.TokenExpiredException;
import com.xiaomi.accountsdk.account.exception.UserRestrictedException;
import com.xiaomi.accountsdk.request.AccessDeniedException;
import com.xiaomi.accountsdk.request.AuthenticationFailureException;
import com.xiaomi.accountsdk.request.InvalidResponseException;
import com.xiaomi.accountsdk.utils.AccountLog;
import com.xiaomi.passport.PassportUserEnvironment;
import com.xiaomi.passport.data.LoginPreference;
import com.xiaomi.passport.uicontroller.SimpleFutureTask;
import com.xiaomi.passport.utils.LoginPreferenceConfig;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PhoneLoginController {
    private static final String TAG = "PhoneLoginController";
    private static final ExecutorService mExecutorService = Executors.newCachedThreadPool();
    /* access modifiers changed from: private */
    public PhoneUserInfoHelper mPhoneUserInfoHelper = new PhoneUserInfoHelper();

    public enum ErrorCode {
        NONE,
        ERROR_UNKNOWN,
        ERROR_AUTH_FAIL,
        ERROR_NETWORK,
        ERROR_SERVER,
        ERROR_ACCESS_DENIED,
        ERROR_INVALID_PARAM,
        ERROR_USER_ACTION_OVER_LIMIT,
        ERROR_PASSWORD,
        ERROR_NON_EXIST_USER,
        ERROR_NO_PHONE
    }

    public interface PasswordLoginCallback {
        void onLoginByStep2(Step2LoginParams step2LoginParams);

        void onLoginFailed(ErrorCode errorCode, String str, boolean z);

        void onLoginSuccess(AccountInfo accountInfo);

        void onNeedCaptchaCode(boolean z, String str);

        void onNeedNotification(String str, String str2);
    }

    public interface PhoneLoginConfigCallback {
        void onError(ErrorCode errorCode, String str);

        void onPhoneNumInvalid();

        void onSuccess(LoginPreference loginPreference);
    }

    public interface PhoneRegisterCallback {
        void onRegisterFailed(ErrorCode errorCode, String str);

        void onRegisterReachLimit();

        void onRegisterSuccess(AccountInfo accountInfo);

        void onTokenExpired();
    }

    public interface PhoneUserInfoCallback {
        void onNotRecycledRegisteredPhone(RegisterUserInfo registerUserInfo);

        void onPhoneNumInvalid();

        void onProbablyRecycleRegisteredPhone(RegisterUserInfo registerUserInfo);

        void onQueryFailed(ErrorCode errorCode, String str);

        void onRecycledOrNotRegisteredPhone(RegisterUserInfo registerUserInfo);

        void onTicketOrTokenInvalid();
    }

    public interface SendPhoneTicketCallback {
        void onActivatorTokenExpired();

        void onNeedCaptchaCode(String str);

        void onPhoneNumInvalid();

        void onSMSReachLimit();

        void onSentFailed(ErrorCode errorCode, String str);

        void onSentSuccess(int i);
    }

    public interface SendSetPwdTicketCallback {
        void onSMSReachLimit();

        void onSentFailed(ErrorCode errorCode, String str);

        void onSentSuccess(String str);
    }

    public interface SetPasswordCallback {
        void onHasPassword();

        void onNeedTicketOrTicketInvalid();

        void onPassTokenInvalid();

        void onSetFailed(ErrorCode errorCode, String str);

        void onSetSuccess(String str);
    }

    public interface TicketLoginCallback {
        void onLoginFailed(ErrorCode errorCode, String str, boolean z);

        void onLoginSuccess(AccountInfo accountInfo);

        void onNeedNotification(String str, String str2);

        void onPhoneNumInvalid();

        void onTicketOrTokenInvalid();
    }

    public void setPhoneUserInfoHelper(PhoneUserInfoHelper phoneUserInfoHelper) {
        this.mPhoneUserInfoHelper = phoneUserInfoHelper;
    }

    public SimpleFutureTask<Integer> sendPhoneTicket(final SendPhoneTicketParams sendPhoneTicketParams, final SendPhoneTicketCallback sendPhoneTicketCallback) {
        if (sendPhoneTicketCallback != null) {
            SimpleFutureTask<Integer> simpleFutureTask = new SimpleFutureTask<>(new Callable<Integer>() {
                public Integer call() throws Exception {
                    return Integer.valueOf(XMPassport.sendPhoneLoginTicket(sendPhoneTicketParams));
                }
            }, new SimpleFutureTask.Callback<Integer>() {
                public void call(SimpleFutureTask<Integer> simpleFutureTask) {
                    try {
                        sendPhoneTicketCallback.onSentSuccess(((Integer) simpleFutureTask.get()).intValue());
                    } catch (InterruptedException e) {
                        AccountLog.e(PhoneLoginController.TAG, "sendPhoneLoginTicket", e);
                        sendPhoneTicketCallback.onSentFailed(ErrorCode.ERROR_UNKNOWN, e.getMessage());
                    } catch (ExecutionException e2) {
                        AccountLog.e(PhoneLoginController.TAG, "sendPhoneLoginTicket", e2);
                        Throwable cause = e2.getCause();
                        if (cause instanceof NeedCaptchaException) {
                            sendPhoneTicketCallback.onNeedCaptchaCode(((NeedCaptchaException) cause).getCaptchaUrl());
                        } else if (cause instanceof TokenExpiredException) {
                            sendPhoneTicketCallback.onActivatorTokenExpired();
                        } else if (cause instanceof ReachLimitException) {
                            sendPhoneTicketCallback.onSMSReachLimit();
                        } else if (cause instanceof InvalidPhoneNumException) {
                            sendPhoneTicketCallback.onPhoneNumInvalid();
                        } else {
                            sendPhoneTicketCallback.onSentFailed(PhoneLoginController.convertErrorCode(cause), e2.getMessage());
                        }
                    }
                }
            });
            mExecutorService.submit(simpleFutureTask);
            return simpleFutureTask;
        }
        throw new IllegalArgumentException("should implements login callback");
    }

    public SimpleFutureTask<RegisterUserInfo> queryPhoneUserInfo(final QueryPhoneInfoParams queryPhoneInfoParams, final PhoneUserInfoCallback phoneUserInfoCallback) {
        if (phoneUserInfoCallback != null) {
            SimpleFutureTask<RegisterUserInfo> simpleFutureTask = new SimpleFutureTask<>(new Callable<RegisterUserInfo>() {
                public RegisterUserInfo call() throws Exception {
                    return PhoneLoginController.this.mPhoneUserInfoHelper.query(queryPhoneInfoParams);
                }
            }, new SimpleFutureTask.Callback<RegisterUserInfo>() {
                public void call(SimpleFutureTask<RegisterUserInfo> simpleFutureTask) {
                    try {
                        RegisterUserInfo registerUserInfo = (RegisterUserInfo) simpleFutureTask.get();
                        RegisterUserInfo.RegisterStatus registerStatus = registerUserInfo.status;
                        if (registerStatus == RegisterUserInfo.RegisterStatus.STATUS_NOT_REGISTERED) {
                            phoneUserInfoCallback.onRecycledOrNotRegisteredPhone(registerUserInfo);
                        } else if (registerStatus == RegisterUserInfo.RegisterStatus.STATUS_REGISTERED_NOT_RECYCLED) {
                            phoneUserInfoCallback.onNotRecycledRegisteredPhone(registerUserInfo);
                        } else {
                            phoneUserInfoCallback.onProbablyRecycleRegisteredPhone(registerUserInfo);
                        }
                    } catch (InterruptedException e) {
                        AccountLog.e(PhoneLoginController.TAG, "query user phone info", e);
                        phoneUserInfoCallback.onQueryFailed(ErrorCode.ERROR_UNKNOWN, e.getMessage());
                    } catch (ExecutionException e2) {
                        AccountLog.e(PhoneLoginController.TAG, "query user phone info", e2);
                        Throwable cause = e2.getCause();
                        if (cause instanceof InvalidVerifyCodeException) {
                            phoneUserInfoCallback.onTicketOrTokenInvalid();
                        } else if (cause instanceof InvalidPhoneNumException) {
                            phoneUserInfoCallback.onPhoneNumInvalid();
                        } else {
                            phoneUserInfoCallback.onQueryFailed(PhoneLoginController.convertErrorCode(cause), e2.getMessage());
                        }
                    }
                }
            });
            mExecutorService.submit(simpleFutureTask);
            return simpleFutureTask;
        }
        throw new IllegalArgumentException("should implements phone user info callback");
    }

    public SimpleFutureTask<AccountInfo> ticketLogin(final PhoneTicketLoginParams phoneTicketLoginParams, final TicketLoginCallback ticketLoginCallback) {
        if (ticketLoginCallback != null) {
            SimpleFutureTask<AccountInfo> simpleFutureTask = new SimpleFutureTask<>(new Callable<AccountInfo>() {
                public AccountInfo call() throws Exception {
                    PhoneTicketLoginParams phoneTicketLoginParams;
                    if (phoneTicketLoginParams.hashedEnvFactors == null) {
                        phoneTicketLoginParams = PhoneTicketLoginParams.copyFrom(phoneTicketLoginParams).hashedEnvFactors(PassportUserEnvironment.Holder.getInstance().getEnvInfoArray(XMPassportSettings.getApplicationContext())).build();
                    } else {
                        phoneTicketLoginParams = phoneTicketLoginParams;
                    }
                    return XMPassport.loginByPhone(phoneTicketLoginParams);
                }
            }, new SimpleFutureTask.Callback<AccountInfo>() {
                public void call(SimpleFutureTask<AccountInfo> simpleFutureTask) {
                    try {
                        ticketLoginCallback.onLoginSuccess((AccountInfo) simpleFutureTask.get());
                    } catch (InterruptedException e) {
                        AccountLog.e(PhoneLoginController.TAG, "loginByPhoneTicket", e);
                        ticketLoginCallback.onLoginFailed(ErrorCode.ERROR_UNKNOWN, e.getMessage(), false);
                    } catch (ExecutionException e2) {
                        AccountLog.e(PhoneLoginController.TAG, "loginByPhoneTicket", e2);
                        Throwable cause = e2.getCause();
                        if (cause instanceof NeedNotificationException) {
                            ticketLoginCallback.onNeedNotification(phoneTicketLoginParams.serviceId, ((NeedNotificationException) cause).getNotificationUrl());
                        } else if (cause instanceof InvalidPhoneNumException) {
                            ticketLoginCallback.onPhoneNumInvalid();
                        } else if (cause instanceof InvalidVerifyCodeException) {
                            ticketLoginCallback.onTicketOrTokenInvalid();
                        } else {
                            ticketLoginCallback.onLoginFailed(PhoneLoginController.convertErrorCode(cause), e2.getMessage(), PhoneLoginController.this.checkIsStsUrlRequestError(cause));
                        }
                    }
                }
            });
            mExecutorService.submit(simpleFutureTask);
            return simpleFutureTask;
        }
        throw new IllegalArgumentException("should implements login callback");
    }

    public SimpleFutureTask<AccountInfo> passwordLogin(final PasswordLoginParams passwordLoginParams, final PasswordLoginCallback passwordLoginCallback) {
        if (passwordLoginCallback != null) {
            SimpleFutureTask<AccountInfo> simpleFutureTask = new SimpleFutureTask<>(new Callable<AccountInfo>() {
                public AccountInfo call() throws Exception {
                    return XMPassport.loginByPassword(passwordLoginParams);
                }
            }, new SimpleFutureTask.Callback<AccountInfo>() {
                public void call(SimpleFutureTask<AccountInfo> simpleFutureTask) {
                    try {
                        passwordLoginCallback.onLoginSuccess((AccountInfo) simpleFutureTask.get());
                    } catch (InterruptedException e) {
                        AccountLog.e(PhoneLoginController.TAG, "passwordLogin", e);
                        passwordLoginCallback.onLoginFailed(ErrorCode.ERROR_UNKNOWN, e.getMessage(), false);
                    } catch (ExecutionException e2) {
                        AccountLog.e(PhoneLoginController.TAG, "passwordLogin", e2);
                        Throwable cause = e2.getCause();
                        if (cause instanceof NeedNotificationException) {
                            passwordLoginCallback.onNeedNotification(passwordLoginParams.serviceId, ((NeedNotificationException) cause).getNotificationUrl());
                        } else if (cause instanceof NeedVerificationException) {
                            NeedVerificationException needVerificationException = (NeedVerificationException) cause;
                            passwordLoginCallback.onLoginByStep2(new Step2LoginParams.Builder().setUserId(needVerificationException.getUserId()).setMetaLoginData(needVerificationException.getMetaLoginData()).setServiceId(passwordLoginParams.serviceId).setStep1Token(needVerificationException.getStep1Token()).build());
                        } else if (cause instanceof NeedCaptchaException) {
                            passwordLoginCallback.onNeedCaptchaCode(false, ((NeedCaptchaException) cause).getCaptchaUrl());
                        } else if (cause instanceof InvalidCredentialException) {
                            InvalidCredentialException invalidCredentialException = (InvalidCredentialException) cause;
                            if (!TextUtils.isEmpty(invalidCredentialException.getCaptchaUrl())) {
                                passwordLoginCallback.onNeedCaptchaCode(true, invalidCredentialException.getCaptchaUrl());
                            } else {
                                passwordLoginCallback.onLoginFailed(ErrorCode.ERROR_PASSWORD, e2.getMessage(), false);
                            }
                        } else {
                            passwordLoginCallback.onLoginFailed(PhoneLoginController.convertErrorCode(cause), e2.getMessage(), PhoneLoginController.this.checkIsStsUrlRequestError(cause));
                        }
                    }
                }
            });
            mExecutorService.submit(simpleFutureTask);
            return simpleFutureTask;
        }
        throw new IllegalArgumentException("should implements login callback");
    }

    public SimpleFutureTask<AccountInfo> register(final PhoneTokenRegisterParams phoneTokenRegisterParams, final PhoneRegisterCallback phoneRegisterCallback) {
        if (phoneRegisterCallback != null) {
            SimpleFutureTask<AccountInfo> simpleFutureTask = new SimpleFutureTask<>(new Callable<AccountInfo>() {
                public AccountInfo call() throws Exception {
                    return XMPassport.regByPhoneWithToken(phoneTokenRegisterParams);
                }
            }, new SimpleFutureTask.Callback<AccountInfo>() {
                public void call(SimpleFutureTask<AccountInfo> simpleFutureTask) {
                    try {
                        phoneRegisterCallback.onRegisterSuccess((AccountInfo) simpleFutureTask.get());
                    } catch (InterruptedException e) {
                        AccountLog.e(PhoneLoginController.TAG, "registerByPhone", e);
                        phoneRegisterCallback.onRegisterFailed(ErrorCode.ERROR_UNKNOWN, e.getMessage());
                    } catch (ExecutionException e2) {
                        AccountLog.e(PhoneLoginController.TAG, "registerByPhone", e2);
                        Throwable cause = e2.getCause();
                        if (cause instanceof UserRestrictedException) {
                            phoneRegisterCallback.onRegisterReachLimit();
                        } else if (cause instanceof TokenExpiredException) {
                            phoneRegisterCallback.onTokenExpired();
                        } else if (cause instanceof ReachLimitException) {
                            phoneRegisterCallback.onRegisterFailed(ErrorCode.ERROR_USER_ACTION_OVER_LIMIT, e2.getMessage());
                        } else {
                            phoneRegisterCallback.onRegisterFailed(PhoneLoginController.convertErrorCode(cause), e2.getMessage());
                        }
                    }
                }
            });
            mExecutorService.submit(simpleFutureTask);
            return simpleFutureTask;
        }
        throw new IllegalArgumentException("should implements register callback");
    }

    public SimpleFutureTask<String> sendSetPasswordTicket(final PassportInfo passportInfo, final String str, final SendSetPwdTicketCallback sendSetPwdTicketCallback) {
        if (sendSetPwdTicketCallback != null) {
            SimpleFutureTask<String> simpleFutureTask = new SimpleFutureTask<>(new Callable<String>() {
                public String call() throws Exception {
                    return XMPassport.sendSetPasswordTicket(passportInfo, str);
                }
            }, new SimpleFutureTask.Callback<String>() {
                public void call(SimpleFutureTask<String> simpleFutureTask) {
                    try {
                        sendSetPwdTicketCallback.onSentSuccess((String) simpleFutureTask.get());
                    } catch (InterruptedException e) {
                        AccountLog.e(PhoneLoginController.TAG, "sendSetPasswordTicket", e);
                        sendSetPwdTicketCallback.onSentFailed(ErrorCode.ERROR_UNKNOWN, e.getMessage());
                    } catch (ExecutionException e2) {
                        AccountLog.e(PhoneLoginController.TAG, "sendSetPasswordTicket", e2);
                        Throwable cause = e2.getCause();
                        if (cause instanceof ReachLimitException) {
                            sendSetPwdTicketCallback.onSMSReachLimit();
                        } else if (cause instanceof InvalidPhoneNumException) {
                            sendSetPwdTicketCallback.onSentFailed(ErrorCode.ERROR_NO_PHONE, e2.getMessage());
                        } else {
                            sendSetPwdTicketCallback.onSentFailed(PhoneLoginController.convertErrorCode(cause), e2.getMessage());
                        }
                    }
                }
            });
            mExecutorService.submit(simpleFutureTask);
            return simpleFutureTask;
        }
        throw new IllegalArgumentException("should implement send set pwd ticket callback");
    }

    public SimpleFutureTask<String> setPassword(final SetPasswordParams setPasswordParams, final SetPasswordCallback setPasswordCallback) {
        if (setPasswordCallback != null) {
            SimpleFutureTask<String> simpleFutureTask = new SimpleFutureTask<>(new Callable<String>() {
                public String call() throws Exception {
                    return XMPassport.setPassword(setPasswordParams);
                }
            }, new SimpleFutureTask.Callback<String>() {
                public void call(SimpleFutureTask<String> simpleFutureTask) {
                    try {
                        setPasswordCallback.onSetSuccess((String) simpleFutureTask.get());
                    } catch (InterruptedException e) {
                        AccountLog.e(PhoneLoginController.TAG, "setPassword", e);
                        setPasswordCallback.onSetFailed(ErrorCode.ERROR_UNKNOWN, e.getMessage());
                    } catch (ExecutionException e2) {
                        AccountLog.e(PhoneLoginController.TAG, "setPassword", e2);
                        Throwable cause = e2.getCause();
                        if (cause instanceof InvalidVerifyCodeException) {
                            setPasswordCallback.onNeedTicketOrTicketInvalid();
                        } else if (cause instanceof InvalidCredentialException) {
                            setPasswordCallback.onPassTokenInvalid();
                        } else if (cause instanceof UserRestrictedException) {
                            setPasswordCallback.onHasPassword();
                        } else {
                            setPasswordCallback.onSetFailed(PhoneLoginController.convertErrorCode(cause), e2.getMessage());
                        }
                    }
                }
            });
            mExecutorService.submit(simpleFutureTask);
            return simpleFutureTask;
        }
        throw new IllegalArgumentException("should implement set password callback");
    }

    public static SimpleFutureTask<LoginPreference> getPhoneLoginConfigOnLine(final String str, final String str2, final PhoneLoginConfigCallback phoneLoginConfigCallback) {
        AnonymousClass15 r4;
        if (phoneLoginConfigCallback == null) {
            r4 = null;
        } else {
            r4 = new SimpleFutureTask.Callback<LoginPreference>() {
                public void call(SimpleFutureTask<LoginPreference> simpleFutureTask) {
                    try {
                        phoneLoginConfigCallback.onSuccess((LoginPreference) simpleFutureTask.get());
                    } catch (InterruptedException e) {
                        AccountLog.e(PhoneLoginController.TAG, "getPhoneLoginConfigOnLine", e);
                        phoneLoginConfigCallback.onError(ErrorCode.ERROR_UNKNOWN, e.getMessage());
                    } catch (ExecutionException e2) {
                        AccountLog.e(PhoneLoginController.TAG, "getPhoneLoginConfigOnLine", e2);
                        Throwable cause = e2.getCause();
                        if (cause instanceof InvalidPhoneNumException) {
                            phoneLoginConfigCallback.onPhoneNumInvalid();
                            return;
                        }
                        phoneLoginConfigCallback.onError(PhoneLoginController.convertErrorCode(cause), e2.getMessage());
                    }
                }
            };
        }
        SimpleFutureTask<LoginPreference> simpleFutureTask = new SimpleFutureTask<>(new Callable<LoginPreference>() {
            public LoginPreference call() throws Exception {
                return LoginPreferenceConfig.getPhoneLoginConfigOnLine(str, str2);
            }
        }, r4);
        mExecutorService.submit(simpleFutureTask);
        return simpleFutureTask;
    }

    /* access modifiers changed from: private */
    public boolean checkIsStsUrlRequestError(Throwable th) {
        if (th instanceof AccountException) {
            return ((AccountException) th).isStsUrlRequestError;
        }
        if (th instanceof HttpException) {
            return ((HttpException) th).isStsUrlRequestError;
        }
        if (th instanceof PassportIOException) {
            return ((PassportIOException) th).isStsUrlRequestError;
        }
        return false;
    }

    public static ErrorCode convertErrorCode(Throwable th) {
        if (th instanceof InvalidResponseException) {
            return ErrorCode.ERROR_SERVER;
        }
        if (th instanceof IOException) {
            return ErrorCode.ERROR_NETWORK;
        }
        if (th instanceof AuthenticationFailureException) {
            return ErrorCode.ERROR_AUTH_FAIL;
        }
        if (th instanceof AccessDeniedException) {
            return ErrorCode.ERROR_ACCESS_DENIED;
        }
        if (th instanceof InvalidParameterException) {
            return ErrorCode.ERROR_INVALID_PARAM;
        }
        if (th instanceof InvalidUserNameException) {
            return ErrorCode.ERROR_NON_EXIST_USER;
        }
        if (th instanceof InvalidCredentialException) {
            return ErrorCode.ERROR_PASSWORD;
        }
        return ErrorCode.ERROR_UNKNOWN;
    }

    public static class PhoneUserInfoHelper {
        public RegisterUserInfo query(QueryPhoneInfoParams queryPhoneInfoParams) throws Exception {
            return XMPassport.queryPhoneUserInfo(queryPhoneInfoParams);
        }

        public RegisterUserInfo checkRegisterPhone(CheckRegPhoneParams checkRegPhoneParams) throws IOException, AccessDeniedException, InvalidPhoneOrTicketException, AuthenticationFailureException, InvalidResponseException, UserRestrictedException {
            return XMPassport.checkRegisterPhone(checkRegPhoneParams);
        }
    }
}
