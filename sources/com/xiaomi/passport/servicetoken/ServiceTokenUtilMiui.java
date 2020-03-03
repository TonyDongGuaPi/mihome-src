package com.xiaomi.passport.servicetoken;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.xiaomi.accountsdk.futureservice.ClientFuture;
import com.xiaomi.accountsdk.futureservice.ServerServiceConnector;
import com.xiaomi.accountsdk.futureservice.SimpleClientFuture;
import com.xiaomi.accountsdk.utils.AccountLog;
import com.xiaomi.accountsdk.utils.MiuiOsBuildReflection;
import com.xiaomi.accountsdk.utils.MiuiVersionDev;
import com.xiaomi.accountsdk.utils.MiuiVersionStable;
import com.xiaomi.passport.IPassportServiceTokenService;
import com.xiaomi.passport.servicetoken.ServiceTokenResult;
import com.xiaomi.passport.servicetoken.data.XmAccountVisibility;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;

final class ServiceTokenUtilMiui extends ServiceTokenUtilImplBase {
    private static final String TAG = "ServiceTokenUtilMiui";
    private static volatile AtomicBoolean miuiServiceTokenServiceAvailability = new AtomicBoolean(true);
    private static volatile Boolean xiaomiAccountAppSlhPhAvailability = null;

    ServiceTokenUtilMiui() {
    }

    public ServiceTokenResult getServiceTokenImpl(Context context, String str) {
        if (str != null && str.startsWith("weblogin:") && MiuiCompatUtil.hasWebLoginCompatIssue()) {
            return getAMServiceTokenUtil().getServiceTokenImpl(context, str);
        }
        if (miuiServiceTokenServiceAvailability.get()) {
            ServiceTokenFuture serviceTokenFuture = new ServiceTokenFuture((ClientFuture.ClientCallback<ServiceTokenResult>) null);
            final String str2 = str;
            final Context context2 = context;
            new ServiceTokenServiceConnector(context, serviceTokenFuture) {
                /* access modifiers changed from: protected */
                public ServiceTokenResult callServiceWork() throws RemoteException {
                    return ServiceTokenUIErrorHandler.blockingHandleIntentError(context2, ((IPassportServiceTokenService) getIService()).getServiceToken(str2));
                }
            }.bind();
            if (checkBindServiceSuccess(serviceTokenFuture)) {
                return serviceTokenFuture.get();
            }
            miuiServiceTokenServiceAvailability.set(false);
        }
        return getAMServiceTokenUtil().getServiceTokenImpl(context, str);
    }

    public ServiceTokenResult invalidateServiceTokenImpl(Context context, final ServiceTokenResult serviceTokenResult) {
        if (miuiServiceTokenServiceAvailability.get()) {
            ServiceTokenFuture serviceTokenFuture = new ServiceTokenFuture((ClientFuture.ClientCallback<ServiceTokenResult>) null);
            new ServiceTokenServiceConnector(context, serviceTokenFuture) {
                /* access modifiers changed from: protected */
                public ServiceTokenResult callServiceWork() throws RemoteException {
                    ServiceTokenResult serviceTokenResult;
                    if (serviceTokenResult == null || !MiuiCompatUtil.hasServiceTokenResultParcelCompatIssue()) {
                        serviceTokenResult = serviceTokenResult;
                    } else {
                        serviceTokenResult = ServiceTokenResult.Builder.copyFrom(serviceTokenResult).useV1Parcel(true).build();
                    }
                    return ((IPassportServiceTokenService) getIService()).invalidateServiceToken(serviceTokenResult);
                }
            }.bind();
            if (checkBindServiceSuccess(serviceTokenFuture)) {
                return serviceTokenFuture.get();
            }
            miuiServiceTokenServiceAvailability.set(false);
        }
        return getAMServiceTokenUtil().invalidateServiceTokenImpl(context, serviceTokenResult);
    }

    public boolean doesXiaomiAccountAppSupportServiceTokenUIResponse(Context context) {
        if (!miuiServiceTokenServiceAvailability.get()) {
            return false;
        }
        SimpleClientFuture simpleClientFuture = new SimpleClientFuture();
        new ServiceTokenServiceConnectorBase<Boolean>(context, simpleClientFuture) {
            /* access modifiers changed from: protected */
            public Boolean callServiceWork() throws RemoteException {
                return Boolean.valueOf(((IPassportServiceTokenService) getIService()).supportServiceTokenUIResponse());
            }
        }.bind();
        try {
            return ((Boolean) simpleClientFuture.get()).booleanValue();
        } catch (InterruptedException e) {
            AccountLog.w(TAG, "", e);
            return false;
        } catch (ExecutionException e2) {
            AccountLog.w(TAG, "", e2);
            return false;
        }
    }

    public boolean fastCheckSlhPhCompatibility(Context context) {
        if (!miuiServiceTokenServiceAvailability.get()) {
            return false;
        }
        synchronized (ServiceTokenUtilMiui.class) {
            if (xiaomiAccountAppSlhPhAvailability != null) {
                boolean booleanValue = xiaomiAccountAppSlhPhAvailability.booleanValue();
                return booleanValue;
            }
            SimpleClientFuture simpleClientFuture = new SimpleClientFuture();
            new ServiceTokenServiceConnectorBase<Boolean>(context, simpleClientFuture) {
                /* access modifiers changed from: protected */
                public Boolean callServiceWork() throws RemoteException {
                    return Boolean.valueOf(((IPassportServiceTokenService) getIService()).fastCheckSlhPhCompatibility());
                }
            }.bind();
            try {
                Boolean bool = (Boolean) simpleClientFuture.get();
                synchronized (ServiceTokenUtilMiui.class) {
                    xiaomiAccountAppSlhPhAvailability = bool;
                }
                return bool.booleanValue();
            } catch (InterruptedException e) {
                AccountLog.w(TAG, "", e);
                return false;
            } catch (ExecutionException e2) {
                AccountLog.w(TAG, "", e2);
                return false;
            }
        }
    }

    /* access modifiers changed from: protected */
    public XmAccountVisibility canAccessAccountImpl(final Context context) {
        Account xiaomiAccount = new AMUtilImpl(new AMKeys()).getXiaomiAccount(context);
        if (xiaomiAccount != null) {
            return new XmAccountVisibility.Builder(XmAccountVisibility.ErrorCode.ERROR_NONE, (String) null).accountVisible(true, xiaomiAccount).build();
        }
        SimpleClientFuture simpleClientFuture = new SimpleClientFuture();
        new ServiceTokenServiceConnectorBase<XmAccountVisibility>(simpleClientFuture, context) {
            /* access modifiers changed from: protected */
            public XmAccountVisibility callServiceWork() throws RemoteException {
                if (((IPassportServiceTokenService) getIService()).supportAccessAccount()) {
                    return ((IPassportServiceTokenService) getIService()).setAccountVisible(context.getPackageName());
                }
                if (Build.VERSION.SDK_INT < 26) {
                    return new XmAccountVisibility.Builder(XmAccountVisibility.ErrorCode.ERROR_PRE_ANDROID_O, (String) null).build();
                }
                return new XmAccountVisibility.Builder(XmAccountVisibility.ErrorCode.ERROR_NOT_SUPPORT, (String) null).newChooseAccountIntent(AccountManager.newChooseAccountIntent((Account) null, (List) null, new String[]{"com.xiaomi"}, (String) null, (String) null, (String[]) null, (Bundle) null)).build();
            }
        }.bind();
        try {
            return (XmAccountVisibility) simpleClientFuture.get();
        } catch (InterruptedException e) {
            AccountLog.e(TAG, "setSystemAccountVisible", e);
            return new XmAccountVisibility.Builder(XmAccountVisibility.ErrorCode.ERROR_CANCELLED, (String) null).build();
        } catch (ExecutionException e2) {
            AccountLog.e(TAG, "setSystemAccountVisible", e2);
            return new XmAccountVisibility.Builder(XmAccountVisibility.ErrorCode.ERROR_EXECUTION, e2.getMessage()).build();
        }
    }

    private ServiceTokenUtilAM getAMServiceTokenUtil() {
        return new ServiceTokenUtilAM(new AMUtilImpl(new AMKeys()));
    }

    private boolean checkBindServiceSuccess(ServiceTokenFuture serviceTokenFuture) {
        return !serviceTokenFuture.isDone() || serviceTokenFuture.get().errorCode != ServiceTokenResult.ErrorCode.ERROR_REMOTE_EXCEPTION;
    }

    private static abstract class ServiceTokenServiceConnector extends ServiceTokenServiceConnectorBase<ServiceTokenResult> {
        protected ServiceTokenServiceConnector(Context context, ServiceTokenFuture serviceTokenFuture) {
            super(context, serviceTokenFuture);
        }
    }

    private static abstract class ServiceTokenServiceConnectorBase<T> extends ServerServiceConnector<IPassportServiceTokenService, T, T> {
        private static final String ACTION_SERVICE_TOKEN_OP = "com.xiaomi.account.action.SERVICE_TOKEN_OP";
        private static final String PACKAGE_NAME_XIAOMI_ACCOUNT = "com.xiaomi.account";

        protected ServiceTokenServiceConnectorBase(Context context, ClientFuture<T, T> clientFuture) {
            super(context, "com.xiaomi.account.action.SERVICE_TOKEN_OP", "com.xiaomi.account", clientFuture);
        }

        /* access modifiers changed from: protected */
        public final IPassportServiceTokenService binderToServiceType(IBinder iBinder) {
            return IPassportServiceTokenService.Stub.asInterface(iBinder);
        }
    }

    private static class MiuiCompatUtil {
        private static volatile Boolean cachedParcelIssueCheckResult;
        private static volatile Boolean cachedWebLoginIssueCheckResult;

        private MiuiCompatUtil() {
        }

        static boolean hasServiceTokenResultParcelCompatIssue() {
            if (cachedParcelIssueCheckResult != null) {
                return cachedParcelIssueCheckResult.booleanValue();
            }
            boolean z = true;
            if ((!MiuiOsBuildReflection.isStable(false) || !MiuiVersionStable.earlyThan(new MiuiVersionStable(8, 0), false)) && (!MiuiOsBuildReflection.isDevButNotAlpha(false) || !MiuiVersionDev.earlyThan(new MiuiVersionDev(6, 7, 1), false))) {
                z = false;
            }
            if (cachedParcelIssueCheckResult == null) {
                cachedParcelIssueCheckResult = new Boolean(z);
            }
            return cachedParcelIssueCheckResult.booleanValue();
        }

        static boolean hasWebLoginCompatIssue() {
            if (cachedWebLoginIssueCheckResult != null) {
                return cachedWebLoginIssueCheckResult.booleanValue();
            }
            boolean z = true;
            if ((!MiuiOsBuildReflection.isStable(false) || !MiuiVersionStable.earlyThan(new MiuiVersionStable(8, 2), false)) && (!MiuiOsBuildReflection.isDevButNotAlpha(false) || !MiuiVersionDev.earlyThan(new MiuiVersionDev(6, 11, 25), false))) {
                z = false;
            }
            if (cachedWebLoginIssueCheckResult == null) {
                cachedWebLoginIssueCheckResult = new Boolean(z);
            }
            return cachedWebLoginIssueCheckResult.booleanValue();
        }
    }
}
