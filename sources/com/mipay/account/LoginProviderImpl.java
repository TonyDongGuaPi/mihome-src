package com.mipay.account;

import android.accounts.AccountsException;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import com.mibi.common.account.ILoginProvider;
import com.xiaomi.accountsdk.account.exception.IllegalDeviceException;
import com.xiaomi.accountsdk.account.exception.InvalidCredentialException;
import com.xiaomi.accountsdk.account.exception.InvalidUserNameException;
import com.xiaomi.accountsdk.request.AccessDeniedException;
import com.xiaomi.accountsdk.request.AuthenticationFailureException;
import com.xiaomi.accountsdk.request.InvalidResponseException;
import com.xiaomi.passport.LocalFeatures.LocalFeaturesManagerCallback;
import com.xiaomi.passport.accountmanager.MiAccountManager;
import java.io.IOException;

public class LoginProviderImpl implements ILoginProvider {
    private static final String KEY_STS_URL = "sts_url";
    private MiAccountManager mAccountManager;

    public LoginProviderImpl(Context context) {
        this.mAccountManager = MiAccountManager.get(context);
    }

    public String getStsUrl(Activity activity, String str, String str2, String str3) throws AccountsException, IOException {
        try {
            return ((Bundle) this.mAccountManager.getLocalFeatures().getStsUrl(str, str2, str3, (Bundle) null, activity, (LocalFeaturesManagerCallback<Bundle>) null, (Handler) null).getResult()).getString("sts_url");
        } catch (OperationCanceledException e) {
            throw e;
        } catch (AuthenticatorException e2) {
            throw e2;
        } catch (IOException e3) {
            throw e3;
        } catch (InvalidCredentialException e4) {
            throw new AccountsException(e4);
        } catch (InvalidUserNameException e5) {
            throw new AccountsException(e5);
        } catch (AccessDeniedException e6) {
            throw new AccountsException(e6);
        } catch (InvalidResponseException e7) {
            throw new AccountsException(e7);
        } catch (IllegalDeviceException e8) {
            throw new AccountsException(e8);
        } catch (AuthenticationFailureException e9) {
            throw new AccountsException(e9);
        }
    }
}
