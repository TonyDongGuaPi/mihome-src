package com.sina.weibo.sdk.share;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.WeiboAppManager;
import com.sina.weibo.sdk.api.StoryMessage;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAppInfo;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.utils.FileUtils;
import com.sina.weibo.sdk.utils.LogUtil;
import com.sina.weibo.sdk.utils.MD5;
import com.sina.weibo.sdk.utils.Utility;
import com.sina.weibo.sdk.utils.WbSdkVersion;
import com.sina.weibo.sdk.web.WebRequestType;
import com.sina.weibo.sdk.web.param.ShareWebViewRequestParam;

public class WbShareHandler {
    public static final int WB_SHARE_REQUEST = 1;
    private Activity context;
    private boolean hasRegister = false;
    private int progressColor = -1;
    private int progressId = -1;

    public boolean supportMulti() {
        return false;
    }

    public WbShareHandler(Activity activity) {
        this.context = activity;
    }

    public boolean registerApp() {
        sendBroadcast(this.context, WBConstants.o, WbSdk.getAuthInfo().getAppKey(), (String) null, (Bundle) null);
        this.hasRegister = true;
        return true;
    }

    private void sendBroadcast(Context context2, String str, String str2, String str3, Bundle bundle) {
        Intent intent = new Intent(str);
        String packageName = context2.getPackageName();
        intent.putExtra(WBConstants.Base.f8821a, WbSdkVersion.f8850a);
        intent.putExtra(WBConstants.Base.b, packageName);
        intent.putExtra(WBConstants.Base.c, str2);
        intent.putExtra(WBConstants.SDK.f8825a, WBConstants.z);
        intent.putExtra(WBConstants.x, MD5.a(Utility.a(context2, packageName)));
        if (!TextUtils.isEmpty(str3)) {
            intent.setPackage(str3);
        }
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context2.sendBroadcast(intent, WBConstants.p);
    }

    public void shareMessage(WeiboMultiMessage weiboMultiMessage, boolean z) {
        if (!this.hasRegister) {
            throw new RuntimeException("please call WbShareHandler.registerApp(),before use share function");
        } else if (!WbSdk.isWbInstall(this.context) && z) {
        } else {
            if (z) {
                startClientShare(weiboMultiMessage);
                return;
            }
            WbAppInfo a2 = WeiboAppManager.a(this.context).a();
            if (!WbSdk.isWbInstall(this.context) || a2 == null || a2.getSupportVersion() <= 10000) {
                startWebShare(weiboMultiMessage);
            } else {
                startClientShare(weiboMultiMessage);
            }
        }
    }

    public void shareToStory(StoryMessage storyMessage) {
        Uri imageUri = storyMessage.getImageUri();
        Uri videoUri = storyMessage.getVideoUri();
        if ((imageUri == null || !FileUtils.a(this.context, imageUri)) && (videoUri == null || !FileUtils.b(this.context, videoUri))) {
            throw new IllegalStateException("File only can be Image or Video. ");
        }
        Intent intent = new Intent();
        intent.putExtra(WBConstants.Msg.k, storyMessage);
        intent.putExtra(WBConstants.H, this.context.getClass().getName());
        intent.putExtra(WBConstants.D, WeiboAppManager.a(this.context).a().getPackageName());
        intent.putExtra(WBConstants.K, this.progressColor);
        intent.putExtra(WBConstants.L, this.progressId);
        intent.putExtra(WBConstants.G, 0);
        intent.setClass(this.context, WbShareToStoryActivity.class);
        this.context.startActivityForResult(intent, 1);
    }

    private void startClientShare(WeiboMultiMessage weiboMultiMessage) {
        Bundle bundle = new Bundle();
        bundle.putInt(WBConstants.v, 1);
        bundle.putString(WBConstants.w, String.valueOf(System.currentTimeMillis()));
        bundle.putLong(WBConstants.F, 0);
        bundle.putAll(weiboMultiMessage.toBundle(bundle));
        Intent intent = new Intent();
        intent.setClass(this.context, WbShareTransActivity.class);
        intent.putExtra(WBConstants.G, 0);
        intent.putExtra(WBConstants.H, this.context.getClass().getName());
        intent.putExtra(WBConstants.K, this.progressColor);
        intent.putExtra(WBConstants.L, this.progressId);
        intent.putExtras(bundle);
        try {
            this.context.startActivityForResult(intent, 1);
        } catch (Exception e) {
            LogUtil.e("weibo sdk error ", e.toString());
        }
    }

    private void startWebShare(WeiboMultiMessage weiboMultiMessage) {
        LogUtil.b("Share", "startWebShare");
        Intent intent = new Intent(this.context, WbShareTransActivity.class);
        String packageName = this.context.getPackageName();
        ShareWebViewRequestParam shareWebViewRequestParam = new ShareWebViewRequestParam(WbSdk.getAuthInfo(), WebRequestType.SHARE, "", 1, "微博分享", (String) null, this.context);
        shareWebViewRequestParam.a((Context) this.context);
        shareWebViewRequestParam.c("");
        shareWebViewRequestParam.d(packageName);
        Oauth2AccessToken readAccessToken = AccessTokenKeeper.readAccessToken(this.context);
        if (readAccessToken != null && !TextUtils.isEmpty(readAccessToken.getToken())) {
            shareWebViewRequestParam.b(readAccessToken.getToken());
        }
        shareWebViewRequestParam.a(weiboMultiMessage);
        Bundle bundle = new Bundle();
        shareWebViewRequestParam.c(bundle);
        intent.putExtras(bundle);
        intent.putExtra(WBConstants.G, 0);
        intent.putExtra(WBConstants.H, this.context.getClass().getName());
        intent.putExtra(WBConstants.E, WBConstants.q);
        intent.putExtra(WBConstants.I, "com.sina.weibo.sdk.web.WeiboSdkWebActivity");
        this.context.startActivityForResult(intent, 1);
    }

    @Deprecated
    public boolean isWbAppInstalled() {
        return WbSdk.isWbInstall(this.context);
    }

    public void doResultIntent(Intent intent, WbShareCallback wbShareCallback) {
        Bundle extras;
        if (wbShareCallback != null && intent != null && (extras = intent.getExtras()) != null) {
            switch (extras.getInt(WBConstants.Response.f8824a, -1)) {
                case 0:
                    wbShareCallback.onWbShareSuccess();
                    return;
                case 1:
                    wbShareCallback.onWbShareCancel();
                    return;
                case 2:
                    wbShareCallback.onWbShareFail();
                    return;
                default:
                    return;
            }
        }
    }

    public void setProgressColor(int i) {
        this.progressColor = i;
    }

    public void setProgressId(int i) {
        this.progressId = i;
    }
}
