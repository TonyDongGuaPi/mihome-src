package com.mibi.common.decorator;

import android.accounts.Account;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import com.mibi.common.account.AccountLoader;
import com.mibi.common.account.AccountRegistry;
import com.mibi.common.account.AccountUtils;
import java.io.IOException;

public class LoginHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7551a = "LoginHelper";

    private interface LoginCallback {
        void a(int i, String str);

        void a(Account account);
    }

    public interface LoginWithResult {
        void a(int i, String str);

        void a(AccountLoader accountLoader);
    }

    private LoginHelper() {
    }

    public static void a(Activity activity, LoginWithResult loginWithResult) {
        a(activity, false, loginWithResult);
    }

    public static void a(Activity activity, boolean z, LoginWithResult loginWithResult) {
        if (loginWithResult == null) {
            throw new IllegalArgumentException("login callback not be null");
        } else if (z) {
            loginWithResult.a(AccountUtils.b());
        } else {
            b(activity, loginWithResult);
        }
    }

    private static void b(Activity activity, final LoginWithResult loginWithResult) {
        Account b = AccountUtils.b(activity);
        if (b != null) {
            loginWithResult.a(AccountUtils.a(b));
            return;
        }
        AccountRegistry.a().addAccount("com.xiaomi", (String) null, (String[]) null, (Bundle) null, activity, new AddAccountCallback(activity) {
            public void a(Account account) {
                loginWithResult.a(AccountUtils.a(account));
            }

            public void a(int i, String str) {
                loginWithResult.a(i, str);
            }
        }, (Handler) null);
    }

    private static abstract class AddAccountCallback implements AccountManagerCallback<Bundle>, LoginCallback {

        /* renamed from: a  reason: collision with root package name */
        private Context f7553a;

        public AddAccountCallback(Context context) {
            this.f7553a = context;
        }

        public void run(AccountManagerFuture<Bundle> accountManagerFuture) {
            if (accountManagerFuture.isDone()) {
                try {
                    if (accountManagerFuture.getResult() == null) {
                        Log.d(LoginHelper.f7551a, "loginSystem failed : authentication failed");
                        a(5, "authentication failed");
                        return;
                    }
                    Account b = AccountUtils.b(this.f7553a);
                    if (b == null) {
                        Log.d(LoginHelper.f7551a, "loginSystem failed : authentication failed");
                        a(5, "authentication failed");
                        return;
                    }
                    a(b);
                } catch (OperationCanceledException e) {
                    Log.d(LoginHelper.f7551a, "loginSystem failed : user canceled " + e);
                    a(0, e.getMessage());
                } catch (AuthenticatorException e2) {
                    Log.d(LoginHelper.f7551a, "loginSystem failed : authenticator exception " + e2);
                    a(5, e2.getMessage());
                } catch (IOException e3) {
                    Log.d(LoginHelper.f7551a, "loginSystem failed : io exception " + e3);
                    a(3, e3.getMessage());
                }
            }
        }
    }
}
