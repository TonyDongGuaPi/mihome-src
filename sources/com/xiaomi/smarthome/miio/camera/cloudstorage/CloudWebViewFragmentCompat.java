package com.xiaomi.smarthome.miio.camera.cloudstorage;

import android.os.Bundle;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.operation.js_sdk.CommonWebViewFragment;
import com.xiaomi.smarthome.operation.js_sdk.base.CommonWebView;

public class CloudWebViewFragmentCompat extends CommonWebViewFragment {
    private static final String ARG_DID = "arg_did";
    private static final String TAG = "CloudWebViewFragmentCom";

    public static CloudWebViewFragmentCompat newInstance(String str, String str2, String str3) {
        Bundle bundle = new Bundle();
        bundle.putString("arg_url", str);
        bundle.putString("arg_title", str2);
        bundle.putBoolean("arg_use_title_bar", true);
        bundle.putString("arg_did", str3);
        CloudWebViewFragmentCompat cloudWebViewFragmentCompat = new CloudWebViewFragmentCompat();
        cloudWebViewFragmentCompat.setArguments(bundle);
        return cloudWebViewFragmentCompat;
    }

    /* access modifiers changed from: protected */
    public void init() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            String string = arguments.getString("arg_did");
            CommonWebView webView = getWebView();
            if (webView == null) {
                LogUtil.a(TAG, "onViewCreated: webView is null on webView#setDid");
                return;
            }
            webView.setDid(string);
        }
        super.init();
    }
}
