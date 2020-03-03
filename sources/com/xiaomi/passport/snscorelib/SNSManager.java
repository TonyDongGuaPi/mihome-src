package com.xiaomi.passport.snscorelib;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.xiaomi.accountsdk.account.URLs;
import com.xiaomi.accountsdk.account.data.AccountInfo;
import com.xiaomi.accountsdk.account.data.PassportInfo;
import com.xiaomi.accountsdk.account.exception.NeedNotificationException;
import com.xiaomi.accountsdk.request.AccessDeniedException;
import com.xiaomi.accountsdk.request.AuthenticationFailureException;
import com.xiaomi.accountsdk.request.CipherException;
import com.xiaomi.accountsdk.request.InvalidResponseException;
import com.xiaomi.accountsdk.request.SecureRequestForAccount;
import com.xiaomi.accountsdk.request.SimpleRequest;
import com.xiaomi.accountsdk.utils.AccountLog;
import com.xiaomi.accountsdk.utils.EasyMap;
import com.xiaomi.passport.snscorelib.internal.entity.SNSBindParameter;
import com.xiaomi.passport.snscorelib.internal.entity.SNSLoginParameter;
import com.xiaomi.passport.snscorelib.internal.exception.SNSLoginException;
import com.xiaomi.passport.snscorelib.internal.request.SNSRequest;
import com.xiaomi.passport.snscorelib.internal.utils.SNSCookieManager;
import com.xiaomi.passport.ui.internal.SnsWebLoginBaseFragment;
import com.xiaomi.passport.uicontroller.SimpleFutureTask;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SNSManager {
    private static final Integer INT_0 = 0;
    public static final int REQUEST_SNS_LOGIN = 1;
    public static final String SNS_BIND_PARAMETER = "sns_bind_parameter";
    public static final String SNS_WEB_LOGIN_URL = "sns_web_login_url";
    public static final String TAG = "SNSManager";
    public static final String URL_SNS_BIND_CANCLED = (URLs.URL_ACCOUNT_BASE + "/sns/bind/cancel");
    public static final String URL_SNS_BIND_FINISH = (URLs.URL_ACCOUNT_BASE + "/sns/bind/finish");
    private static Activity mActivity;
    private static final ExecutorService mExecutorService = Executors.newCachedThreadPool();
    static WebViewClient mWebViewClient = new WebViewClient() {
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            if (SNSManager.sSNSBindCallback != null) {
                SNSManager.sSNSBindCallback.onPageFinished();
            }
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            Log.i("liujun006", "shouldOverrideUrlLoading..url=" + str);
            String path = Uri.parse(SNSManager.URL_SNS_BIND_CANCLED).getPath();
            String path2 = Uri.parse(SNSManager.URL_SNS_BIND_FINISH).getPath();
            String path3 = Uri.parse(str).getPath();
            boolean equals = path2.equals(path3);
            boolean equals2 = path.equals(path3);
            if (equals) {
                SNSManager.sSNSBindCallback.onSNSBindFinished();
                return true;
            } else if (!equals2) {
                return false;
            } else {
                SNSManager.sSNSBindCallback.onSNSBindCancel();
                return true;
            }
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            SNSManager.showWebView();
        }
    };
    /* access modifiers changed from: private */
    public static SNSBindCallback sSNSBindCallback;
    private static WebView sWebView;
    private SimpleFutureTask<SNSBindParameter> mSNSBindParameterTask;
    private SimpleFutureTask<AccountInfo> mSnsLoginByAccessTokenTask;

    public static abstract class SNSBindByAccountCallback {
        /* access modifiers changed from: protected */
        public abstract void onImplementSNSBind(SNSBindParameter sNSBindParameter);

        /* access modifiers changed from: protected */
        public abstract void onNetWorkErrorException();

        /* access modifiers changed from: protected */
        public abstract void onSNSBindFailed(int i, String str);
    }

    public static abstract class SNSBindCallback {
        /* access modifiers changed from: protected */
        public abstract void onNetWorkErrorException();

        /* access modifiers changed from: protected */
        public abstract void onPageFinished();

        /* access modifiers changed from: protected */
        public abstract void onSNSBindCancel();

        /* access modifiers changed from: protected */
        public abstract void onSNSBindFailed(int i, String str);

        /* access modifiers changed from: protected */
        public abstract void onSNSBindFinished();
    }

    public interface SNSLoginCallback {
        void onBindLimit();

        void onNeedLoginForBind(SNSBindParameter sNSBindParameter);

        void onNeedNotificationException(String str, String str2);

        void onNetWorkErrorException();

        void onRedirectToWebLogin(SNSBindParameter sNSBindParameter);

        void onSnsLoginFailed(int i, String str);

        void onSnsLoginSucess(AccountInfo accountInfo);
    }

    public interface UnBindSNSCallback {
        void refreshAuthToken();
    }

    public SNSManager(Activity activity) {
        mActivity = activity;
    }

    public SNSManager() {
    }

    public void snsLogin(SNSLoginParameter sNSLoginParameter, SNSLoginCallback sNSLoginCallback) {
        String str = sNSLoginParameter.enToken;
        String str2 = sNSLoginParameter.token;
        String str3 = sNSLoginParameter.code;
        if (!TextUtils.isEmpty(str) || !TextUtils.isEmpty(str2)) {
            snsLoginByAccessToken(sNSLoginParameter, sNSLoginCallback);
        } else if (!TextUtils.isEmpty(str3)) {
            snsLoginByCode(sNSLoginParameter, sNSLoginCallback);
        } else {
            throw new IllegalArgumentException("code and enToken parameters is null");
        }
    }

    private SimpleFutureTask<AccountInfo> snsLoginByCode(final SNSLoginParameter sNSLoginParameter, final SNSLoginCallback sNSLoginCallback) {
        this.mSnsLoginByAccessTokenTask = new SimpleFutureTask<>(new Callable<AccountInfo>() {
            public AccountInfo call() throws Exception {
                return SNSRequest.snsLoginByCode(sNSLoginParameter);
            }
        }, new SimpleFutureTask.Callback<AccountInfo>() {
            public void call(SimpleFutureTask<AccountInfo> simpleFutureTask) {
                try {
                    sNSLoginCallback.onSnsLoginSucess((AccountInfo) simpleFutureTask.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    throw new RuntimeException("getSNSAccessTokenByCode:interrupted");
                } catch (ExecutionException e2) {
                    SNSManager.this.handleExecutionException(e2, sNSLoginCallback);
                }
            }
        });
        mExecutorService.submit(this.mSnsLoginByAccessTokenTask);
        return this.mSnsLoginByAccessTokenTask;
    }

    private SimpleFutureTask<AccountInfo> snsLoginByAccessToken(final SNSLoginParameter sNSLoginParameter, final SNSLoginCallback sNSLoginCallback) {
        this.mSnsLoginByAccessTokenTask = new SimpleFutureTask<>(new Callable<AccountInfo>() {
            public AccountInfo call() throws Exception {
                return SNSRequest.snsLoginByAccessToken(sNSLoginParameter);
            }
        }, new SimpleFutureTask.Callback<AccountInfo>() {
            public void call(SimpleFutureTask<AccountInfo> simpleFutureTask) {
                try {
                    sNSLoginCallback.onSnsLoginSucess((AccountInfo) simpleFutureTask.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    throw new RuntimeException("getAccountInfo:interrupted");
                } catch (ExecutionException e2) {
                    SNSManager.this.handleExecutionException(e2, sNSLoginCallback);
                }
            }
        });
        mExecutorService.submit(this.mSnsLoginByAccessTokenTask);
        return this.mSnsLoginByAccessTokenTask;
    }

    public boolean unBindSNS(String str, PassportInfo passportInfo, UnBindSNSCallback unBindSNSCallback) throws IOException {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("snsType is null");
        } else if (unBindSNSCallback == null) {
            throw new IllegalArgumentException("unBindSNSCallback is null");
        } else if (passportInfo != null) {
            try {
                return unBindSNS(passportInfo, str);
            } catch (AccessDeniedException e) {
                AccountLog.e(TAG, "InvalidAccessTokenRunnable error", e);
                return false;
            } catch (AuthenticationFailureException e2) {
                AccountLog.e(TAG, "InvalidAccessTokenRunnable error", e2);
                unBindSNSCallback.refreshAuthToken();
                return false;
            } catch (InvalidResponseException e3) {
                AccountLog.e(TAG, "InvalidAccessTokenRunnable error", e3);
                return false;
            } catch (CipherException e4) {
                throw new RuntimeException(e4);
            }
        } else {
            throw new IllegalArgumentException("passportInfo is null");
        }
    }

    private boolean unBindSNS(PassportInfo passportInfo, String str) throws IOException, AccessDeniedException, AuthenticationFailureException, InvalidResponseException, CipherException {
        return requestUnBindSNS(passportInfo, str);
    }

    /* access modifiers changed from: private */
    public void handleExecutionException(ExecutionException executionException, SNSLoginCallback sNSLoginCallback) {
        Throwable cause = executionException.getCause();
        if (cause instanceof SNSLoginException) {
            sNSLoginCallback.onSnsLoginFailed(((SNSLoginException) cause).getCode(), cause.getMessage());
        } else if (cause instanceof IOException) {
            sNSLoginCallback.onNetWorkErrorException();
        } else if (cause instanceof SNSRequest.NeedLoginForBindException) {
            sNSLoginCallback.onNeedLoginForBind(((SNSRequest.NeedLoginForBindException) cause).getSNSBindParameter());
        } else if (cause instanceof NeedNotificationException) {
            NeedNotificationException needNotificationException = (NeedNotificationException) cause;
            sNSLoginCallback.onNeedNotificationException(needNotificationException.getUserId(), needNotificationException.getNotificationUrl());
        } else if (cause instanceof SNSRequest.BindLimitException) {
            sNSLoginCallback.onBindLimit();
        } else if (cause instanceof SNSRequest.RedirectToWebLoginException) {
            sNSLoginCallback.onRedirectToWebLogin(((SNSRequest.RedirectToWebLoginException) cause).getSNSBindParameter());
        } else {
            throw new RuntimeException(cause);
        }
    }

    private static boolean requestUnBindSNS(PassportInfo passportInfo, String str) throws IOException, AccessDeniedException, AuthenticationFailureException, InvalidResponseException, CipherException {
        if (passportInfo == null || !TextUtils.isDigitsOnly(passportInfo.getUserId())) {
            throw new IllegalArgumentException("illegal param");
        }
        SimpleRequest.MapContent postAsMap = SecureRequestForAccount.postAsMap(SNSRequest.URL_UNBIND_SNS, new EasyMap().easyPut("snsType", str).easyPut("userId", passportInfo.getUserId()), new EasyMap().easyPut("cUserId", passportInfo.getEncryptedUserId()).easyPut("serviceToken", passportInfo.getServiceToken()), true, passportInfo.getSecurity());
        if (postAsMap != null) {
            if (INT_0.equals(postAsMap.getFromBody("code"))) {
                return true;
            }
            return false;
        }
        throw new IOException("failed to get response to delete sns accesstoken");
    }

    public static void snsBind(SNSBindParameter sNSBindParameter, AccountInfo accountInfo, WebView webView, SNSBindCallback sNSBindCallback) {
        sWebView = webView;
        sSNSBindCallback = sNSBindCallback;
        String localeString = getLocaleString(Locale.getDefault());
        WebSettings settings = webView.getSettings();
        String userAgent = getUserAgent(mActivity);
        WebSettings settings2 = webView.getSettings();
        settings2.setUserAgentString(userAgent + " AndroidSnsSDK/" + "1.0");
        settings.setJavaScriptEnabled(true);
        webView.setWebViewClient(mWebViewClient);
        HashMap hashMap = new HashMap();
        hashMap.put("userId", accountInfo.userId);
        hashMap.put("cUserId", accountInfo.encryptedUserId);
        hashMap.put("passToken", accountInfo.passToken);
        hashMap.put(SnsWebLoginBaseFragment.SNS_TOKEN_PH, sNSBindParameter.sns_token_ph);
        hashMap.put(SnsWebLoginBaseFragment.SNS_WEIXIN_OPENID, sNSBindParameter.sns_weixin_openId);
        SNSCookieManager.setupCookiesForAccountWeb(webView, hashMap);
        webView.loadUrl(sNSBindParameter.snsBindUrl + "&_locale=" + localeString);
    }

    public static String getUserAgent(Context context) {
        if (Build.VERSION.SDK_INT < 19) {
            return sWebView.getSettings().getUserAgentString();
        }
        return WebSettings.getDefaultUserAgent(context);
    }

    public static String getLocaleString(Locale locale) {
        if (locale == null) {
            return null;
        }
        String language = locale.getLanguage();
        String country = locale.getCountry();
        if (TextUtils.isEmpty(country)) {
            return language;
        }
        return String.format("%s_%s", new Object[]{language, country});
    }

    public void snsBindByAccountInfo(SNSLoginParameter sNSLoginParameter, AccountInfo accountInfo, final SNSBindByAccountCallback sNSBindByAccountCallback) {
        final String str = sNSLoginParameter.enToken;
        final String str2 = sNSLoginParameter.token;
        final String str3 = sNSLoginParameter.code;
        if (sNSBindByAccountCallback != null) {
            final SNSLoginParameter sNSLoginParameter2 = sNSLoginParameter;
            final AccountInfo accountInfo2 = accountInfo;
            this.mSNSBindParameterTask = new SimpleFutureTask<>(new Callable<SNSBindParameter>() {
                public SNSBindParameter call() throws Exception {
                    if (!TextUtils.isEmpty(str) || !TextUtils.isEmpty(str2)) {
                        return SNSRequest.getSNSBindParameterByToken(sNSLoginParameter2, accountInfo2);
                    }
                    if (!TextUtils.isEmpty(str3)) {
                        return SNSRequest.getSNSBindParameterByCode(sNSLoginParameter2, accountInfo2);
                    }
                    throw new IllegalArgumentException("code and enToken parameters is null");
                }
            }, new SimpleFutureTask.Callback<SNSBindParameter>() {
                public void call(SimpleFutureTask<SNSBindParameter> simpleFutureTask) {
                    try {
                        SNSBindParameter sNSBindParameter = (SNSBindParameter) simpleFutureTask.get();
                        if (sNSBindByAccountCallback != null) {
                            sNSBindByAccountCallback.onImplementSNSBind(sNSBindParameter);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        throw new RuntimeException("snsBindByAccountInfo:interrupted");
                    } catch (ExecutionException e2) {
                        Throwable cause = e2.getCause();
                        if (cause instanceof SNSLoginException) {
                            sNSBindByAccountCallback.onSNSBindFailed(((SNSLoginException) cause).getCode(), cause.getMessage());
                        } else if (cause instanceof IOException) {
                            sNSBindByAccountCallback.onNetWorkErrorException();
                        } else {
                            throw new RuntimeException(cause);
                        }
                    }
                }
            });
            mExecutorService.submit(this.mSNSBindParameterTask);
            return;
        }
        throw new IllegalArgumentException("snsBindByAccountCallback is null");
    }

    /* access modifiers changed from: private */
    public static void showWebView() {
        if (sWebView.getVisibility() != 0) {
            sWebView.setVisibility(0);
        }
    }
}
