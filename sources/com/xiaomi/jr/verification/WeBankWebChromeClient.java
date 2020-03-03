package com.xiaomi.jr.verification;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.xiaomi.jr.permission.PermissionManager;
import com.xiaomi.jr.permission.Request;
import com.xiaomi.jr.verification.sdk.WBH5FaceVerifySDK;
import com.xiaomi.jr.web.WebFragment;
import com.xiaomi.jr.web.webkit.WebChromeClient;

public class WeBankWebChromeClient extends WebChromeClient {
    private static final String[] b = {"android.permission.CAMERA", "android.permission.ACCESS_WIFI_STATE", "android.permission.ACCESS_NETWORK_STATE", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.RECORD_AUDIO"};

    public WeBankWebChromeClient(WebFragment webFragment) {
        super(webFragment);
    }

    public void openFileChooser(ValueCallback<Uri> valueCallback, String str, String str2) {
        if ("video/webank".equals(str)) {
            WBH5FaceVerifySDK.a().a(valueCallback, str, this.f11083a);
        }
    }

    @TargetApi(21)
    public boolean onShowFileChooser(final WebView webView, final ValueCallback<Uri[]> valueCallback, final WebChromeClient.FileChooserParams fileChooserParams) {
        if (!"video/webank".equals(fileChooserParams.getAcceptTypes()[0]) && !webView.getUrl().startsWith("https://ida.webank.com/")) {
            return false;
        }
        PermissionManager.a((Context) this.f11083a.getActivity(), b, (Request.Callback) new Request.Callback() {
            public /* synthetic */ void b() {
                Request.Callback.CC.$default$b(this);
            }

            public void a() {
                WBH5FaceVerifySDK.a().a(webView, valueCallback, WeBankWebChromeClient.this.f11083a, fileChooserParams);
            }

            public void a(String str) {
                valueCallback.onReceiveValue((Object) null);
            }
        });
        return true;
    }
}
