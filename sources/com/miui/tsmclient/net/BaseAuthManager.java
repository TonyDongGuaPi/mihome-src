package com.miui.tsmclient.net;

import android.content.Context;
import com.miui.tsmclient.account.AccountInfo;
import com.miui.tsmclient.account.AccountManagerFactory;
import com.miui.tsmclient.account.IAccountManager;
import com.miui.tsmclient.common.net.host.AuthHost;
import com.miui.tsmclient.common.net.host.Host;
import com.miui.tsmclient.util.LogUtils;
import com.miui.tsmclient.util.NetworkUtil;
import com.xiaomi.accountsdk.request.AccessDeniedException;
import com.xiaomi.accountsdk.request.AuthenticationFailureException;
import com.xiaomi.accountsdk.request.CipherException;
import com.xiaomi.accountsdk.request.InvalidResponseException;
import java.io.IOException;

public class BaseAuthManager {
    protected IAccountManager mAccountManager = AccountManagerFactory.createAccountManager();
    private IAuthClient mAuthClient = AuthClientFactory.createAuthClient();
    protected Host mHost;

    public BaseAuthManager() {
        initHost();
    }

    /* access modifiers changed from: protected */
    public Object sendRequest(Context context, AccountInfo accountInfo, AuthRequest authRequest) throws AuthApiException {
        if (NetworkUtil.isConnected(context)) {
            try {
                return this.mAuthClient.sendPostRequest(accountInfo, authRequest);
            } catch (AccessDeniedException e) {
                LogUtils.e(authRequest.getClass().getName() + " send failed with an AccessDeniedException! So return null!", e);
                throw new AuthApiException(7);
            } catch (AuthenticationFailureException e2) {
                LogUtils.e(authRequest.getClass().getName() + " send failed with an AuthenticationFailureException! Now retry!", e2);
                return retry(context, accountInfo, authRequest, true);
            } catch (InvalidResponseException | IOException e3) {
                LogUtils.e(authRequest.getClass().getName() + " send failed!", e3);
                throw new AuthApiException(2);
            } catch (CipherException e4) {
                LogUtils.e(authRequest.getClass().getName() + " send failed with a CipherException!", e4);
                throw new AuthApiException(7);
            }
        } else {
            throw new AuthApiException(2);
        }
    }

    /* access modifiers changed from: protected */
    public Object sendGetRequest(Context context, AccountInfo accountInfo, AuthRequest authRequest) throws AuthApiException {
        if (NetworkUtil.isConnected(context)) {
            try {
                return this.mAuthClient.sendGetRequest(accountInfo, authRequest);
            } catch (AccessDeniedException e) {
                LogUtils.e(authRequest.getClass().getName() + " send failed with an AccessDeniedException!So return null!", e);
                throw new AuthApiException(7);
            } catch (AuthenticationFailureException e2) {
                LogUtils.e(authRequest.getClass().getName() + " send failed with an AuthenticationFailureException!Now retry", e2);
                return retry(context, accountInfo, authRequest, false);
            } catch (InvalidResponseException | IOException e3) {
                LogUtils.e(authRequest.getClass().getName() + " send failed! So return null!", e3);
                throw new AuthApiException(2);
            } catch (CipherException e4) {
                LogUtils.e(authRequest.getClass().getName() + " send failed with a CipherException!So return null!", e4);
                throw new AuthApiException(7);
            }
        } else {
            throw new AuthApiException(2);
        }
    }

    /* access modifiers changed from: protected */
    public void initHost() {
        this.mHost = new AuthHost();
    }

    private Object retry(Context context, AccountInfo accountInfo, AuthRequest authRequest, boolean z) throws AuthApiException {
        this.mAccountManager.resetAccount(context, accountInfo.getAuthToken());
        AccountInfo loadAccountInfo = this.mAccountManager.loadAccountInfo(context, this.mHost.getServiceId());
        if (!z) {
            return this.mAuthClient.sendGetRequest(loadAccountInfo, authRequest);
        }
        try {
            return this.mAuthClient.sendPostRequest(loadAccountInfo, authRequest);
        } catch (IOException unused) {
            throw new AuthApiException(2);
        } catch (Exception e) {
            LogUtils.e("An AuthenticationFailureException happened, so retry, but still failed!", e);
            throw new AuthApiException(7);
        }
    }
}
