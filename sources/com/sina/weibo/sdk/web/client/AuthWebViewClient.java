package com.sina.weibo.sdk.web.client;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.utils.LogUtil;
import com.sina.weibo.sdk.utils.Utility;
import com.sina.weibo.sdk.web.WebViewRequestCallback;
import com.sina.weibo.sdk.web.WeiboCallbackManager;
import com.sina.weibo.sdk.web.WeiboSdkWebActivity;
import com.sina.weibo.sdk.web.param.BaseWebViewRequestParam;

public class AuthWebViewClient extends BaseWebViewClient {
    private static final String c = "AuthWebViewClient";
    private Context d;
    private boolean e = false;

    public AuthWebViewClient(WebViewRequestCallback webViewRequestCallback, Context context, BaseWebViewRequestParam baseWebViewRequestParam) {
        super(webViewRequestCallback, baseWebViewRequestParam);
        this.d = context;
    }

    @TargetApi(24)
    public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
        LogUtil.a(c, "shouldOverrideUrlLoading,request.getUrl()");
        return a(webView, webResourceRequest.getUrl().toString());
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        LogUtil.a(c, "shouldOverrideUrlLoading,url");
        return a(webView, str);
    }

    private boolean a(WebView webView, String str) {
        if (str.startsWith("sms:")) {
            try {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.putExtra("address", str.replace("sms:", ""));
                intent.setType("vnd.android-dir/mms-sms");
                this.d.startActivity(intent);
                return true;
            } catch (Exception unused) {
                return false;
            }
        } else if (str.startsWith(WeiboSdkWebActivity.BROWSER_CLOSE_SCHEME)) {
            if (this.f8861a.d() != null && !TextUtils.isEmpty(this.f8861a.d().getCallback())) {
                String callback = this.f8861a.d().getCallback();
                WeiboCallbackManager a2 = WeiboCallbackManager.a();
                if (a2.a(callback) != null) {
                    a2.a(callback).cancel();
                }
                a2.b(callback);
            }
            return true;
        } else if (!a(str) || TextUtils.isEmpty(Utility.a(str).getString("access_token"))) {
            return false;
        } else {
            return true;
        }
    }

    private boolean a(String str) {
        Uri parse = Uri.parse(this.f8861a.d().getAuthInfo().getRedirectUrl());
        Uri parse2 = Uri.parse(str);
        String host = parse.getHost();
        return !TextUtils.isEmpty(host) && host.equals(parse2.getHost());
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        LogUtil.a(c, "onPageStarted:");
        if (this.b != null) {
            this.b.onPageStartedCallBack(webView, str, bitmap);
        }
        super.onPageStarted(webView, str, bitmap);
    }

    public void onPageFinished(WebView webView, String str) {
        LogUtil.a(c, "onPageFinished:");
        super.onPageFinished(webView, str);
        if (this.b != null) {
            this.b.onPageFinishedCallBack(webView, str);
        }
        if (a(str) && !this.e) {
            this.e = true;
            b(str);
            webView.stopLoading();
            if (this.b != null) {
                this.b.closePage();
            }
        }
    }

    @TargetApi(24)
    public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
        super.onReceivedError(webView, webResourceRequest, webResourceError);
        LogUtil.a(c, "onReceivedError");
        if (this.b != null) {
            this.b.onReceivedErrorCallBack(webView, webResourceError.getErrorCode(), webResourceError.getDescription().toString(), webResourceRequest.getUrl().toString());
        }
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        super.onReceivedError(webView, i, str, str2);
        LogUtil.a(c, "onReceivedError");
        if (this.b != null) {
            this.b.onReceivedErrorCallBack(webView, i, str, str2);
        }
    }

    private void b(String str) {
        WbAuthListener wbAuthListener;
        Bundle a2 = Utility.a(str);
        String string = a2.getString("error");
        String string2 = a2.getString("error_code");
        String string3 = a2.getString("error_description");
        if (this.f8861a.d() == null || TextUtils.isEmpty(this.f8861a.d().getCallback())) {
            wbAuthListener = null;
        } else {
            String callback = this.f8861a.d().getCallback();
            WeiboCallbackManager a3 = WeiboCallbackManager.a();
            wbAuthListener = a3.a(callback);
            a3.b(callback);
        }
        if (string == null && string2 == null) {
            if (wbAuthListener != null) {
                Oauth2AccessToken parseAccessToken = Oauth2AccessToken.parseAccessToken(a2);
                AccessTokenKeeper.writeAccessToken(this.d, parseAccessToken);
                wbAuthListener.onSuccess(parseAccessToken);
            }
        } else if (wbAuthListener != null) {
            wbAuthListener.onFailure(new WbConnectErrorMessage(string2, string3));
        }
    }

    public void a() {
        super.a();
        if (this.f8861a.d() != null && !TextUtils.isEmpty(this.f8861a.d().getCallback())) {
            String callback = this.f8861a.d().getCallback();
            WeiboCallbackManager a2 = WeiboCallbackManager.a();
            if (a2.a(callback) != null) {
                a2.a(callback).cancel();
            }
            a2.b(callback);
        }
    }

    public boolean b() {
        a();
        if (this.b == null) {
            return true;
        }
        this.b.closePage();
        return true;
    }
}
