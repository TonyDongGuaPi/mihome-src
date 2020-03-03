package com.sina.weibo.sdk.auth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.WeiboAppManager;
import com.sina.weibo.sdk.net.WeiboParameters;
import com.sina.weibo.sdk.sso.WeiboSsoManager;
import com.sina.weibo.sdk.utils.LogUtil;
import com.sina.weibo.sdk.utils.NetworkHelper;
import com.sina.weibo.sdk.utils.SecurityHelper;
import com.sina.weibo.sdk.utils.UIUtils;
import com.sina.weibo.sdk.utils.Utility;
import com.sina.weibo.sdk.utils.WbAuthConstants;
import com.sina.weibo.sdk.utils.WbSdkVersion;
import com.sina.weibo.sdk.web.WebRequestType;
import com.sina.weibo.sdk.web.WeiboCallbackManager;
import com.sina.weibo.sdk.web.WeiboSdkWebActivity;
import com.sina.weibo.sdk.web.param.AuthWebViewRequestParam;

public class BaseSsoHandler {
    public static final String OAUTH2_BASE_URL = "https://open.weibo.cn/oauth2/authorize?";
    private static final String TAG = "BaseSsoHandler";
    protected final int SSO_TYPE_INVALID = 3;
    protected WbAuthListener authListener;
    protected Context mAuthActivity;
    protected int ssoRequestCode = -1;
    protected int ssoRequestType = 3;

    protected enum AuthType {
        ALL,
        SsoOnly,
        WebOnly
    }

    /* access modifiers changed from: protected */
    public void fillExtraIntent(Intent intent, int i) {
    }

    public BaseSsoHandler(Activity activity) {
        this.mAuthActivity = activity;
        WeiboSsoManager.a().a(activity, WbSdk.getAuthInfo().getAppKey());
    }

    public BaseSsoHandler(Context context) {
        this.mAuthActivity = context;
        WeiboSsoManager.a().a(context, WbSdk.getAuthInfo().getAppKey());
    }

    public void authorize(WbAuthListener wbAuthListener) {
        authorize(WbAuthConstants.f8849a, wbAuthListener, AuthType.ALL);
    }

    public void authorizeClientSso(WbAuthListener wbAuthListener) {
        authorize(WbAuthConstants.f8849a, wbAuthListener, AuthType.SsoOnly);
    }

    public void authorizeWeb(WbAuthListener wbAuthListener) {
        authorize(WbAuthConstants.f8849a, wbAuthListener, AuthType.WebOnly);
    }

    private void authorize(int i, WbAuthListener wbAuthListener, AuthType authType) {
        resetIntentFillData();
        if (wbAuthListener != null) {
            this.authListener = wbAuthListener;
            if (authType == AuthType.WebOnly) {
                startWebAuth();
                return;
            }
            boolean z = false;
            if (authType == AuthType.SsoOnly) {
                z = true;
            }
            WbAppInfo a2 = WeiboAppManager.a(this.mAuthActivity).a();
            if (isWbAppInstalled() && a2 != null) {
                startClientAuth(i);
            } else if (z) {
                this.authListener.onFailure(new WbConnectErrorMessage());
            } else {
                startWebAuth();
            }
        } else {
            throw new RuntimeException("please set auth listener");
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        return;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x006f */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0073 A[Catch:{ Exception -> 0x007d }] */
    /* JADX WARNING: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void startClientAuth(int r5) {
        /*
            r4 = this;
            android.content.Context r0 = r4.mAuthActivity     // Catch:{ Exception -> 0x007d }
            com.sina.weibo.sdk.WeiboAppManager r0 = com.sina.weibo.sdk.WeiboAppManager.a(r0)     // Catch:{ Exception -> 0x007d }
            com.sina.weibo.sdk.auth.WbAppInfo r0 = r0.a()     // Catch:{ Exception -> 0x007d }
            android.content.Intent r1 = new android.content.Intent     // Catch:{ Exception -> 0x007d }
            r1.<init>()     // Catch:{ Exception -> 0x007d }
            java.lang.String r2 = r0.getPackageName()     // Catch:{ Exception -> 0x007d }
            java.lang.String r0 = r0.getAuthActivityName()     // Catch:{ Exception -> 0x007d }
            r1.setClassName(r2, r0)     // Catch:{ Exception -> 0x007d }
            com.sina.weibo.sdk.auth.AuthInfo r0 = com.sina.weibo.sdk.WbSdk.getAuthInfo()     // Catch:{ Exception -> 0x007d }
            android.os.Bundle r0 = r0.getAuthBundle()     // Catch:{ Exception -> 0x007d }
            r1.putExtras(r0)     // Catch:{ Exception -> 0x007d }
            java.lang.String r0 = "_weibo_command_type"
            r2 = 3
            r1.putExtra(r0, r2)     // Catch:{ Exception -> 0x007d }
            java.lang.String r0 = "_weibo_transaction"
            long r2 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x007d }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ Exception -> 0x007d }
            r1.putExtra(r0, r2)     // Catch:{ Exception -> 0x007d }
            java.lang.String r0 = "aid"
            android.content.Context r2 = r4.mAuthActivity     // Catch:{ Exception -> 0x007d }
            com.sina.weibo.sdk.auth.AuthInfo r3 = com.sina.weibo.sdk.WbSdk.getAuthInfo()     // Catch:{ Exception -> 0x007d }
            java.lang.String r3 = r3.getAppKey()     // Catch:{ Exception -> 0x007d }
            java.lang.String r2 = com.sina.weibo.sdk.utils.Utility.b(r2, r3)     // Catch:{ Exception -> 0x007d }
            r1.putExtra(r0, r2)     // Catch:{ Exception -> 0x007d }
            android.content.Context r0 = r4.mAuthActivity     // Catch:{ Exception -> 0x007d }
            boolean r0 = com.sina.weibo.sdk.utils.SecurityHelper.a((android.content.Context) r0, (android.content.Intent) r1)     // Catch:{ Exception -> 0x007d }
            if (r0 != 0) goto L_0x0062
            com.sina.weibo.sdk.auth.WbAuthListener r5 = r4.authListener     // Catch:{ Exception -> 0x007d }
            com.sina.weibo.sdk.auth.WbConnectErrorMessage r0 = new com.sina.weibo.sdk.auth.WbConnectErrorMessage     // Catch:{ Exception -> 0x007d }
            java.lang.String r1 = "your install weibo app is counterfeit"
            java.lang.String r2 = "8001"
            r0.<init>(r1, r2)     // Catch:{ Exception -> 0x007d }
            r5.onFailure(r0)     // Catch:{ Exception -> 0x007d }
            return
        L_0x0062:
            r4.fillExtraIntent(r1, r5)     // Catch:{ Exception -> 0x007d }
            android.content.Context r5 = r4.mAuthActivity     // Catch:{ Exception -> 0x006f }
            android.app.Activity r5 = (android.app.Activity) r5     // Catch:{ Exception -> 0x006f }
            int r0 = r4.ssoRequestCode     // Catch:{ Exception -> 0x006f }
            r5.startActivityForResult(r1, r0)     // Catch:{ Exception -> 0x006f }
            goto L_0x007d
        L_0x006f:
            com.sina.weibo.sdk.auth.WbAuthListener r5 = r4.authListener     // Catch:{ Exception -> 0x007d }
            if (r5 == 0) goto L_0x007d
            com.sina.weibo.sdk.auth.WbAuthListener r5 = r4.authListener     // Catch:{ Exception -> 0x007d }
            com.sina.weibo.sdk.auth.WbConnectErrorMessage r0 = new com.sina.weibo.sdk.auth.WbConnectErrorMessage     // Catch:{ Exception -> 0x007d }
            r0.<init>()     // Catch:{ Exception -> 0x007d }
            r5.onFailure(r0)     // Catch:{ Exception -> 0x007d }
        L_0x007d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sina.weibo.sdk.auth.BaseSsoHandler.startClientAuth(int):void");
    }

    /* access modifiers changed from: protected */
    public void resetIntentFillData() {
        this.ssoRequestCode = WbAuthConstants.f8849a;
    }

    /* access modifiers changed from: protected */
    public void startWebAuth() {
        String str;
        AuthInfo authInfo = WbSdk.getAuthInfo();
        WeiboParameters weiboParameters = new WeiboParameters(authInfo.getAppKey());
        weiboParameters.put("client_id", authInfo.getAppKey());
        weiboParameters.put("redirect_uri", authInfo.getRedirectUrl());
        weiboParameters.put("scope", authInfo.getScope());
        weiboParameters.put("response_type", "code");
        weiboParameters.put("version", WbSdkVersion.f8850a);
        weiboParameters.put("luicode", "10000360");
        Oauth2AccessToken readAccessToken = AccessTokenKeeper.readAccessToken(this.mAuthActivity);
        if (readAccessToken != null && !TextUtils.isEmpty(readAccessToken.getToken())) {
            weiboParameters.put("trans_token", readAccessToken.getToken());
            weiboParameters.put("trans_access_token", readAccessToken.getToken());
        }
        weiboParameters.put("lfid", "OP_" + authInfo.getAppKey());
        String b = Utility.b(this.mAuthActivity, authInfo.getAppKey());
        if (!TextUtils.isEmpty(b)) {
            weiboParameters.put("aid", b);
        }
        weiboParameters.put("packagename", authInfo.getPackageName());
        weiboParameters.put("key_hash", authInfo.getKeyHash());
        String str2 = OAUTH2_BASE_URL + weiboParameters.encodeUrl();
        if (!NetworkHelper.a(this.mAuthActivity)) {
            UIUtils.a(this.mAuthActivity, "Error", "Application requires permission to access the Internet");
            return;
        }
        if (this.authListener != null) {
            WeiboCallbackManager a2 = WeiboCallbackManager.a();
            String b2 = a2.b();
            a2.a(b2, this.authListener);
            str = b2;
        } else {
            str = null;
        }
        AuthWebViewRequestParam authWebViewRequestParam = new AuthWebViewRequestParam(authInfo, WebRequestType.AUTH, str, "微博登录", str2, this.mAuthActivity);
        Intent intent = new Intent(this.mAuthActivity, WeiboSdkWebActivity.class);
        Bundle bundle = new Bundle();
        authWebViewRequestParam.c(bundle);
        intent.putExtras(bundle);
        this.mAuthActivity.startActivity(intent);
    }

    public void authorizeCallBack(int i, int i2, Intent intent) {
        if (32973 != i) {
            return;
        }
        if (i2 == -1) {
            if (!SecurityHelper.a(this.mAuthActivity, WeiboAppManager.a(this.mAuthActivity).a(), intent)) {
                this.authListener.onFailure(new WbConnectErrorMessage(WbAuthConstants.l, WbAuthConstants.m));
                return;
            }
            String c = Utility.c(intent.getStringExtra("error"));
            String c2 = Utility.c(intent.getStringExtra("error_type"));
            String c3 = Utility.c(intent.getStringExtra("error_description"));
            LogUtil.a(TAG, "error: " + c + ", error_type: " + c2 + ", error_description: " + c3);
            if (TextUtils.isEmpty(c) && TextUtils.isEmpty(c2) && TextUtils.isEmpty(c3)) {
                Oauth2AccessToken parseAccessToken = Oauth2AccessToken.parseAccessToken(intent.getExtras());
                if (parseAccessToken != null && parseAccessToken.isSessionValid()) {
                    LogUtil.a(TAG, "Login Success! " + parseAccessToken.toString());
                    AccessTokenKeeper.writeAccessToken(this.mAuthActivity, parseAccessToken);
                    this.authListener.onSuccess(parseAccessToken);
                }
            } else if ("access_denied".equals(c) || "OAuthAccessDeniedException".equals(c)) {
                LogUtil.a(TAG, "Login canceled by user.");
                this.authListener.cancel();
            } else {
                LogUtil.a(TAG, "Login failed: " + c);
                this.authListener.onFailure(new WbConnectErrorMessage(c2, c3));
            }
        } else if (i2 == 0) {
            this.authListener.cancel();
        }
    }

    @Deprecated
    public boolean isWbAppInstalled() {
        return WbSdk.isWbInstall(this.mAuthActivity);
    }
}
