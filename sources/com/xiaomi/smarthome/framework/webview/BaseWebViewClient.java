package com.xiaomi.smarthome.framework.webview;

import android.os.Build;
import android.text.TextUtils;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.sina.weibo.sdk.constant.WBConstants;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.library.commonapi.SystemApi;
import org.json.JSONException;
import org.json.JSONObject;

public class BaseWebViewClient extends WebViewClient {

    /* renamed from: a  reason: collision with root package name */
    private static final String f17843a = "shscheme://";

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (str.startsWith(f17843a)) {
            try {
                JSONObject jSONObject = new JSONObject(str.replace(f17843a, ""));
                String optString = jSONObject.optString("type");
                String optString2 = jSONObject.optString(WBConstants.F);
                if (!TextUtils.isEmpty(optString) && optString.equals("getNetworkType")) {
                    int k = SystemApi.a().k(SHApplication.getAppContext());
                    if (TextUtils.isEmpty(optString2)) {
                        return true;
                    }
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("networkType", k);
                    a(webView, a(optString2, jSONObject2).toString());
                    return true;
                }
            } catch (Exception | JSONException unused) {
            }
        }
        return super.shouldOverrideUrlLoading(webView, str);
    }

    /* access modifiers changed from: package-private */
    public void a(WebView webView, String str) {
        b(webView, "if(SHBridge&&SHBridge.notify){SHBridge.notify(" + str + ");}");
    }

    /* access modifiers changed from: package-private */
    public JSONObject a(String str, JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put(WBConstants.F, str);
            jSONObject2.put("body", jSONObject);
        } catch (JSONException unused) {
        }
        return jSONObject2;
    }

    private void b(WebView webView, String str) {
        if (Build.VERSION.SDK_INT >= 19) {
            webView.evaluateJavascript(str, (ValueCallback) null);
            return;
        }
        webView.loadUrl("javascript:" + str);
    }
}
