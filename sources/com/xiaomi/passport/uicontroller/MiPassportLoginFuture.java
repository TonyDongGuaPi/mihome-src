package com.xiaomi.passport.uicontroller;

import android.os.Build;
import android.os.RemoteException;
import com.xiaomi.accountsdk.account.data.AccountInfo;
import com.xiaomi.accountsdk.account.data.MiLoginResult;
import com.xiaomi.accountsdk.account.data.NotificationAuthResult;
import com.xiaomi.accountsdk.account.exception.IllegalDeviceException;
import com.xiaomi.accountsdk.account.exception.InvalidCredentialException;
import com.xiaomi.accountsdk.account.exception.InvalidStep2codeException;
import com.xiaomi.accountsdk.account.exception.InvalidUserNameException;
import com.xiaomi.accountsdk.account.exception.NeedCaptchaException;
import com.xiaomi.accountsdk.account.exception.NeedNotificationException;
import com.xiaomi.accountsdk.account.exception.NeedVerificationException;
import com.xiaomi.accountsdk.account.exception.PassportIOException;
import com.xiaomi.accountsdk.futureservice.ClientFuture;
import com.xiaomi.accountsdk.request.AccessDeniedException;
import com.xiaomi.accountsdk.request.InvalidResponseException;
import com.xiaomi.passport.uicontroller.UIControllerFuture;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import javax.net.ssl.SSLException;

public abstract class MiPassportLoginFuture extends UIControllerFuture<MiLoginResult, AccountInfo> {
    public MiPassportLoginFuture(UIControllerFuture.UICallback<AccountInfo> uICallback) {
        super(uICallback);
    }

    /* access modifiers changed from: protected */
    public AccountInfo convertModelDataToUIData(MiLoginResult miLoginResult) throws InvalidResponseException, NeedNotificationException, NeedVerificationException, InvalidStep2codeException, IOException, InvalidCredentialException, NeedCaptchaException, InvalidUserNameException, AccessDeniedException, IllegalDeviceException, RemoteException {
        if (miLoginResult != null) {
            switch (miLoginResult.resultCode) {
                case 0:
                    return miLoginResult.accountInfo;
                case 1:
                    throw new NeedCaptchaException(miLoginResult.captchaUrl);
                case 2:
                    throw new NeedVerificationException(miLoginResult.metaLoginData, miLoginResult.step1Token, miLoginResult.userId);
                case 3:
                    throw new NeedNotificationException(miLoginResult.userId, miLoginResult.notificationUrl);
                case 4:
                    throw new InvalidCredentialException(miLoginResult.hasPwd).metaLoginData(miLoginResult.metaLoginData).captchaUrl(miLoginResult.captchaUrl);
                case 5:
                    if (miLoginResult.isStsCallbackError) {
                        PassportIOException passportIOException = new PassportIOException(0, "http exception");
                        passportIOException.stsUrlRequestError(miLoginResult.serviceId);
                        throw passportIOException;
                    }
                    throw new IOException("network error");
                case 6:
                    InvalidResponseException invalidResponseException = new InvalidResponseException("server error");
                    if (miLoginResult.isStsCallbackError) {
                        invalidResponseException.stsUrlRequestError(miLoginResult.serviceId);
                    }
                    throw invalidResponseException;
                case 7:
                    AccessDeniedException accessDeniedException = new AccessDeniedException(403, "access denied");
                    if (miLoginResult.isStsCallbackError) {
                        accessDeniedException.stsUrlRequestError(miLoginResult.serviceId);
                    }
                    throw accessDeniedException;
                case 8:
                    throw new InvalidUserNameException();
                case 9:
                    throw new IllegalDeviceException("device id should not be null");
                case 10:
                    throw new SSLException("time or network error");
                case 11:
                    throw new InvalidStep2codeException();
                case 13:
                    if (Build.VERSION.SDK_INT < 15) {
                        throw new RemoteException();
                    }
                    throw new RemoteException("Service side fatal error");
                default:
                    throw new IllegalStateException("this should not be happen");
            }
        } else {
            throw new InvalidResponseException("result is null");
        }
    }

    public void interpretExecutionException(ExecutionException executionException) throws InvalidResponseException, NeedNotificationException, NeedVerificationException, InvalidStep2codeException, IOException, InvalidCredentialException, NeedCaptchaException, InvalidUserNameException, AccessDeniedException, IllegalDeviceException, RemoteException {
        Throwable cause = executionException.getCause();
        if (cause instanceof NeedNotificationException) {
            throw ((NeedNotificationException) cause);
        } else if (cause instanceof NeedVerificationException) {
            throw ((NeedVerificationException) cause);
        } else if (cause instanceof InvalidStep2codeException) {
            throw ((InvalidStep2codeException) cause);
        } else if (cause instanceof IOException) {
            throw ((IOException) cause);
        } else if (cause instanceof InvalidCredentialException) {
            throw ((InvalidCredentialException) cause);
        } else if (cause instanceof NeedCaptchaException) {
            throw ((NeedCaptchaException) cause);
        } else if (cause instanceof InvalidUserNameException) {
            throw ((InvalidUserNameException) cause);
        } else if (cause instanceof AccessDeniedException) {
            throw ((AccessDeniedException) cause);
        } else if (cause instanceof InvalidResponseException) {
            throw ((InvalidResponseException) cause);
        } else if (cause instanceof SSLException) {
            throw ((SSLException) cause);
        } else if (cause instanceof IllegalDeviceException) {
            throw ((IllegalDeviceException) cause);
        } else if (cause instanceof IllegalStateException) {
            throw ((IllegalStateException) cause);
        } else if (cause instanceof RemoteException) {
            throw ((RemoteException) cause);
        } else {
            throw new IllegalStateException("unknown exception met: " + cause.getMessage());
        }
    }

    public static final class PasswordLoginFuture extends MiPassportLoginFuture {
        public PasswordLoginFuture(UIControllerFuture.UICallback<AccountInfo> uICallback) {
            super(uICallback);
        }

        /* access modifiers changed from: protected */
        public AccountInfo convertModelDataToUIData(MiLoginResult miLoginResult) throws InvalidResponseException, NeedNotificationException, NeedVerificationException, IOException, InvalidCredentialException, NeedCaptchaException, InvalidUserNameException, AccessDeniedException, IllegalDeviceException, RemoteException {
            try {
                return MiPassportLoginFuture.super.convertModelDataToUIData(miLoginResult);
            } catch (InvalidStep2codeException unused) {
                throw new IllegalStateException("this should not be happen");
            }
        }

        public void interpretExecutionException(ExecutionException executionException) throws InvalidResponseException, NeedNotificationException, NeedVerificationException, IOException, InvalidCredentialException, NeedCaptchaException, InvalidUserNameException, AccessDeniedException, IllegalDeviceException, RemoteException {
            try {
                MiPassportLoginFuture.super.interpretExecutionException(executionException);
            } catch (InvalidStep2codeException unused) {
                throw new IllegalStateException("this should not be happen");
            }
        }
    }

    public static final class NotificationLoginFuture extends MiPassportLoginFuture {
        public NotificationLoginFuture(UIControllerFuture.UICallback<AccountInfo> uICallback) {
            super(uICallback);
        }

        /* access modifiers changed from: protected */
        public AccountInfo convertModelDataToUIData(MiLoginResult miLoginResult) throws InvalidResponseException, NeedNotificationException, IOException, InvalidCredentialException, InvalidUserNameException, AccessDeniedException, IllegalDeviceException, RemoteException {
            try {
                return MiPassportLoginFuture.super.convertModelDataToUIData(miLoginResult);
            } catch (InvalidStep2codeException unused) {
                throw new IllegalStateException("this should not be happen");
            } catch (NeedVerificationException unused2) {
                throw new IllegalStateException("this should not be happen");
            } catch (NeedCaptchaException unused3) {
                throw new IllegalStateException("this should not be happen");
            }
        }

        public void interpretExecutionException(ExecutionException executionException) throws InvalidResponseException, NeedNotificationException, IOException, InvalidCredentialException, InvalidUserNameException, AccessDeniedException, IllegalDeviceException, RemoteException {
            try {
                MiPassportLoginFuture.super.interpretExecutionException(executionException);
            } catch (InvalidStep2codeException unused) {
                throw new IllegalStateException("this should not be happen");
            } catch (NeedVerificationException unused2) {
                throw new IllegalStateException("this should not be happen");
            } catch (NeedCaptchaException unused3) {
                throw new IllegalStateException("this should not be happen");
            }
        }
    }

    public static final class Step2LoginFuture extends MiPassportLoginFuture {
        public Step2LoginFuture(UIControllerFuture.UICallback<AccountInfo> uICallback) {
            super(uICallback);
        }

        /* access modifiers changed from: protected */
        public AccountInfo convertModelDataToUIData(MiLoginResult miLoginResult) throws InvalidResponseException, IOException, InvalidCredentialException, InvalidUserNameException, AccessDeniedException, IllegalDeviceException, InvalidStep2codeException, RemoteException {
            try {
                return MiPassportLoginFuture.super.convertModelDataToUIData(miLoginResult);
            } catch (NeedNotificationException unused) {
                throw new IllegalStateException("this should not be happen");
            } catch (NeedVerificationException unused2) {
                throw new IllegalStateException("this should not be happen");
            } catch (NeedCaptchaException unused3) {
                throw new IllegalStateException("this should not be happen");
            }
        }

        public void interpretExecutionException(ExecutionException executionException) throws InvalidResponseException, IOException, InvalidCredentialException, InvalidUserNameException, AccessDeniedException, IllegalDeviceException, InvalidStep2codeException, RemoteException {
            try {
                MiPassportLoginFuture.super.interpretExecutionException(executionException);
            } catch (NeedNotificationException unused) {
                throw new IllegalStateException("this should not be happen");
            } catch (NeedVerificationException unused2) {
                throw new IllegalStateException("this should not be happen");
            } catch (NeedCaptchaException unused3) {
                throw new IllegalStateException("this should not be happen");
            }
        }
    }

    public static class NotificationAuthFuture extends UIControllerFuture<NotificationAuthResult, NotificationAuthResult> {
        /* access modifiers changed from: protected */
        public NotificationAuthResult convertModelDataToUIData(NotificationAuthResult notificationAuthResult) {
            return notificationAuthResult;
        }

        public NotificationAuthFuture(UIControllerFuture.UICallback<NotificationAuthResult> uICallback) {
            super(uICallback);
        }

        public void interpretExecutionException(ExecutionException executionException) throws Exception {
            if (executionException.getCause() instanceof RemoteException) {
                throw ((RemoteException) executionException.getCause());
            }
            throw new IllegalStateException("unknown exception met: " + executionException.getCause().getMessage());
        }
    }

    public static final class AddOrUpdateAccountFuture extends UIControllerFuture<Void, Void> {
        /* access modifiers changed from: protected */
        public Void convertModelDataToUIData(Void voidR) {
            return null;
        }

        public AddOrUpdateAccountFuture(UIControllerFuture.UICallback<Void> uICallback) {
            super(uICallback);
        }

        public void interpretExecutionException(ExecutionException executionException) throws RemoteException {
            Throwable cause = executionException.getCause();
            if (cause instanceof RemoteException) {
                throw ((RemoteException) cause);
            }
            throw new IllegalStateException("unknown exception met: " + executionException.getCause().getMessage());
        }
    }

    public static abstract class PasswordLoginUICallback implements UIControllerFuture.UICallback {
        /* access modifiers changed from: protected */
        public abstract void call(PasswordLoginFuture passwordLoginFuture);

        public final void call(ClientFuture clientFuture) {
            call((PasswordLoginFuture) clientFuture);
        }
    }

    public static abstract class NotificationLoginUICallback implements UIControllerFuture.UICallback {
        /* access modifiers changed from: protected */
        public abstract void call(NotificationLoginFuture notificationLoginFuture);

        public final void call(ClientFuture clientFuture) {
            call((NotificationLoginFuture) clientFuture);
        }
    }

    public static abstract class Step2LoginUICallback implements UIControllerFuture.UICallback {
        /* access modifiers changed from: protected */
        public abstract void call(Step2LoginFuture step2LoginFuture);

        public final void call(ClientFuture clientFuture) {
            call((Step2LoginFuture) clientFuture);
        }
    }

    public static abstract class NotificationAuthUICallback implements UIControllerFuture.UICallback {
        /* access modifiers changed from: protected */
        public abstract void call(NotificationAuthFuture notificationAuthFuture);

        public final void call(ClientFuture clientFuture) {
            call((NotificationAuthFuture) clientFuture);
        }
    }

    public static abstract class AddOrUpdateUICallback implements UIControllerFuture.UICallback {
        /* access modifiers changed from: protected */
        public abstract void call(AddOrUpdateAccountFuture addOrUpdateAccountFuture);

        public final void call(ClientFuture clientFuture) {
            call((AddOrUpdateAccountFuture) clientFuture);
        }
    }
}
