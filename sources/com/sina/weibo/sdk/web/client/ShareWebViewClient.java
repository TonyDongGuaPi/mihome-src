package com.sina.weibo.sdk.web.client;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.utils.LogUtil;
import com.sina.weibo.sdk.utils.WbUtils;
import com.sina.weibo.sdk.web.WebViewRequestCallback;
import com.sina.weibo.sdk.web.WeiboCallbackManager;
import com.sina.weibo.sdk.web.WeiboSdkWebActivity;
import com.sina.weibo.sdk.web.param.BaseWebViewRequestParam;

public class ShareWebViewClient extends BaseWebViewClient {
    private static final String c = "0";
    private static final String d = "code";
    private static final String e = "msg";
    private Activity f;
    private boolean g = false;

    public ShareWebViewClient(Activity activity, WebViewRequestCallback webViewRequestCallback, BaseWebViewRequestParam baseWebViewRequestParam) {
        super(webViewRequestCallback, baseWebViewRequestParam);
        this.f = activity;
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
        if (this.b != null) {
            this.b.onPageStartedCallBack(webView, str, bitmap);
        }
    }

    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        if (this.b != null) {
            this.b.onPageFinishedCallBack(webView, str);
        }
    }

    public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
        super.onReceivedError(webView, webResourceRequest, webResourceError);
        if (this.b != null) {
            this.b.onReceivedErrorCallBack(webView, webResourceError.getErrorCode(), webResourceError.getDescription().toString(), webResourceRequest.getUrl().toString());
        }
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        super.onReceivedError(webView, i, str, str2);
        if (this.b != null) {
            this.b.onReceivedErrorCallBack(webView, i, str, str2);
        }
    }

    @TargetApi(24)
    public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
        return a(webResourceRequest.getUrl().toString());
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (this.b != null) {
            this.b.shouldOverrideUrlLoadingCallBack(webView, str);
        }
        return a(str);
    }

    private boolean a(String str) {
        if (!str.startsWith(WeiboSdkWebActivity.BROWSER_CLOSE_SCHEME)) {
            return false;
        }
        Bundle a2 = WbUtils.a(str);
        if (this.f8861a.d() != null && !TextUtils.isEmpty(this.f8861a.d().getCallback())) {
            String callback = this.f8861a.d().getCallback();
            WeiboCallbackManager a3 = WeiboCallbackManager.a();
            if (a3.a(callback) != null && !a2.isEmpty()) {
                a3.b(callback);
            }
        }
        String string = a2.getString("code");
        String string2 = a2.getString("msg");
        if (TextUtils.isEmpty(string)) {
            a(this.f);
        } else if (!"0".equals(string)) {
            b(this.f, string2);
        } else {
            b(this.f);
        }
        if (this.b == null) {
            return true;
        }
        this.b.closePage();
        return true;
    }

    private void a(Activity activity, int i, String str) {
        LogUtil.b("Share", "WebActivity.sendSdkResponse,errCode:" + i + ",errMsg:" + str);
        Bundle extras = activity.getIntent().getExtras();
        if (extras != null && !this.g) {
            Intent intent = new Intent(WBConstants.r);
            String string = extras.getString("packageName");
            intent.setFlags(131072);
            intent.setPackage(string);
            intent.putExtras(extras);
            intent.putExtra(WBConstants.Base.b, activity.getPackageName());
            intent.putExtra(WBConstants.Response.f8824a, i);
            intent.putExtra(WBConstants.Response.b, str);
            try {
                activity.startActivityForResult(intent, WBConstants.s);
            } catch (ActivityNotFoundException unused) {
            }
            this.g = true;
        }
    }

    public void a(Activity activity) {
        a(activity, 1, "send cancel!!!");
    }

    public void b(Activity activity) {
        a(activity, 0, "send ok!!!");
    }

    public void b(Activity activity, String str) {
        a(activity, 2, str);
    }

    public void a(Activity activity, String str) {
        super.a(activity, str);
        b(activity, str);
    }

    public void a() {
        super.a();
        a(this.f);
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
