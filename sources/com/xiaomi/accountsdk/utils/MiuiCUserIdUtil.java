package com.xiaomi.accountsdk.utils;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import com.xiaomi.accountsdk.account.IXiaomiAccountService;
import com.xiaomi.accountsdk.futureservice.ServerServiceConnector;
import com.xiaomi.accountsdk.futureservice.SimpleClientFuture;
import com.xiaomi.passport.servicetoken.ServiceTokenResult;
import com.xiaomi.passport.servicetoken.ServiceTokenUtilFacade;
import java.util.concurrent.ExecutionException;

public final class MiuiCUserIdUtil {
    private static final String ACTION_NAME = "android.intent.action.BIND_XIAOMI_ACCOUNT_SERVICE";
    private static final String ACTION_NEW_NAME = "com.xiaomi.account.action.BIND_XIAOMI_ACCOUNT_SERVICE";
    private static final String PACKAGE_NAME = "com.xiaomi.account";
    private static final String TAG = "MiuiCUserIdUtil";
    /* access modifiers changed from: private */
    public final Account mAccount;
    private final Context mContext;

    public MiuiCUserIdUtil(Context context, Account account) {
        if (context != null) {
            this.mContext = context.getApplicationContext();
            this.mAccount = account;
            return;
        }
        throw new IllegalArgumentException("context == null");
    }

    public final String getCUserId() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            try {
                return getFromXiaomiAccountService();
            } catch (SecurityException unused) {
                return getFromServiceTokenUtil();
            }
        } else {
            throw new IllegalStateException("MiuiCUserIdUtil#getCUserId() should NOT be called on main thread!");
        }
    }

    private String getFromServiceTokenUtil() {
        ServiceTokenResult serviceTokenResult = ServiceTokenUtilFacade.getInstance().buildMiuiServiceTokenUtil().getServiceToken(this.mContext, "passportapi").get();
        if (serviceTokenResult != null) {
            return serviceTokenResult.cUserId;
        }
        return null;
    }

    private String getFromXiaomiAccountService() {
        String str = this.mContext.getPackageManager().resolveService(new Intent(ACTION_NEW_NAME), 0) == null ? "android.intent.action.BIND_XIAOMI_ACCOUNT_SERVICE" : ACTION_NEW_NAME;
        SimpleClientFuture simpleClientFuture = new SimpleClientFuture();
        new ServerServiceConnector<IXiaomiAccountService, String, String>(this.mContext, str, "com.xiaomi.account", simpleClientFuture) {
            /* access modifiers changed from: protected */
            public String callServiceWork() throws RemoteException {
                return ((IXiaomiAccountService) getIService()).getEncryptedUserId(MiuiCUserIdUtil.this.mAccount);
            }

            /* access modifiers changed from: protected */
            public IXiaomiAccountService binderToServiceType(IBinder iBinder) {
                return IXiaomiAccountService.Stub.asInterface(iBinder);
            }
        }.bind();
        try {
            return (String) simpleClientFuture.get();
        } catch (InterruptedException e) {
            AccountLog.e(TAG, "getCUserId", e);
            return null;
        } catch (ExecutionException e2) {
            AccountLog.e(TAG, "getCUserId", e2);
            return null;
        }
    }
}
