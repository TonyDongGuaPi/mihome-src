package com.taobao.weex.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.ui.component.WXWeb;
import com.taobao.weex.ui.view.IWebView;
import com.taobao.weex.utils.WXLogUtils;
import com.xiaomi.smarthome.httpserver.NanoHTTPD;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXWebView implements IWebView {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    private static final String BRIDGE_NAME = "__WEEX_WEB_VIEW_BRIDGE";
    private static final boolean DOWNGRADE_JS_INTERFACE;
    private static final int POST_MESSAGE = 1;
    private static final int SDK_VERSION = Build.VERSION.SDK_INT;
    private Context mContext;
    private Handler mMessageHandler;
    private IWebView.OnErrorListener mOnErrorListener;
    private IWebView.OnMessageListener mOnMessageListener;
    private IWebView.OnPageListener mOnPageListener;
    private String mOrigin;
    private ProgressBar mProgressBar;
    private boolean mShowLoading = true;
    private WebView mWebView;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(6956002820440275937L, "com/taobao/weex/ui/view/WXWebView", 95);
        $jacocoData = a2;
        return a2;
    }

    static /* synthetic */ IWebView.OnPageListener access$100(WXWebView wXWebView) {
        boolean[] $jacocoInit = $jacocoInit();
        IWebView.OnPageListener onPageListener = wXWebView.mOnPageListener;
        $jacocoInit[84] = true;
        return onPageListener;
    }

    static /* synthetic */ IWebView.OnMessageListener access$200(WXWebView wXWebView) {
        boolean[] $jacocoInit = $jacocoInit();
        IWebView.OnMessageListener onMessageListener = wXWebView.mOnMessageListener;
        $jacocoInit[85] = true;
        return onMessageListener;
    }

    static /* synthetic */ boolean access$300() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = DOWNGRADE_JS_INTERFACE;
        $jacocoInit[86] = true;
        return z;
    }

    static /* synthetic */ void access$400(WXWebView wXWebView, String str) {
        boolean[] $jacocoInit = $jacocoInit();
        wXWebView.evaluateJS(str);
        $jacocoInit[87] = true;
    }

    static /* synthetic */ IWebView.OnErrorListener access$500(WXWebView wXWebView) {
        boolean[] $jacocoInit = $jacocoInit();
        IWebView.OnErrorListener onErrorListener = wXWebView.mOnErrorListener;
        $jacocoInit[88] = true;
        return onErrorListener;
    }

    static /* synthetic */ void access$600(WXWebView wXWebView, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        wXWebView.showWebView(z);
        $jacocoInit[89] = true;
    }

    static /* synthetic */ void access$700(WXWebView wXWebView, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        wXWebView.showProgressBar(z);
        $jacocoInit[90] = true;
    }

    static /* synthetic */ void access$800(WXWebView wXWebView, String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        wXWebView.onMessage(str, str2);
        $jacocoInit[91] = true;
    }

    static {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (SDK_VERSION < 17) {
            $jacocoInit[92] = true;
            z = true;
        } else {
            z = false;
            $jacocoInit[93] = true;
        }
        DOWNGRADE_JS_INTERFACE = z;
        $jacocoInit[94] = true;
    }

    public WXWebView(Context context, String str) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mContext = context;
        this.mOrigin = str;
        $jacocoInit[0] = true;
    }

    public View getView() {
        boolean[] $jacocoInit = $jacocoInit();
        FrameLayout frameLayout = new FrameLayout(this.mContext);
        $jacocoInit[1] = true;
        frameLayout.setBackgroundColor(-1);
        $jacocoInit[2] = true;
        this.mWebView = new WebView(this.mContext);
        $jacocoInit[3] = true;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.gravity = 17;
        $jacocoInit[4] = true;
        this.mWebView.setLayoutParams(layoutParams);
        $jacocoInit[5] = true;
        frameLayout.addView(this.mWebView);
        $jacocoInit[6] = true;
        initWebView(this.mWebView);
        $jacocoInit[7] = true;
        this.mProgressBar = new ProgressBar(this.mContext);
        $jacocoInit[8] = true;
        showProgressBar(false);
        $jacocoInit[9] = true;
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-2, -2);
        $jacocoInit[10] = true;
        this.mProgressBar.setLayoutParams(layoutParams2);
        layoutParams2.gravity = 17;
        $jacocoInit[11] = true;
        frameLayout.addView(this.mProgressBar);
        $jacocoInit[12] = true;
        this.mMessageHandler = new MessageHandler(this, (AnonymousClass1) null);
        $jacocoInit[13] = true;
        return frameLayout;
    }

    public void destroy() {
        boolean[] $jacocoInit = $jacocoInit();
        if (getWebView() == null) {
            $jacocoInit[14] = true;
        } else {
            $jacocoInit[15] = true;
            getWebView().removeAllViews();
            $jacocoInit[16] = true;
            getWebView().destroy();
            this.mWebView = null;
            $jacocoInit[17] = true;
        }
        $jacocoInit[18] = true;
    }

    public void loadUrl(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (getWebView() == null) {
            $jacocoInit[19] = true;
            return;
        }
        getWebView().loadUrl(str);
        $jacocoInit[20] = true;
    }

    public void loadDataWithBaseURL(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (getWebView() == null) {
            $jacocoInit[21] = true;
            return;
        }
        getWebView().loadDataWithBaseURL(this.mOrigin, str, NanoHTTPD.c, "utf-8", (String) null);
        $jacocoInit[22] = true;
    }

    public void reload() {
        boolean[] $jacocoInit = $jacocoInit();
        if (getWebView() == null) {
            $jacocoInit[23] = true;
            return;
        }
        getWebView().reload();
        $jacocoInit[24] = true;
    }

    public void goBack() {
        boolean[] $jacocoInit = $jacocoInit();
        if (getWebView() == null) {
            $jacocoInit[25] = true;
            return;
        }
        getWebView().goBack();
        $jacocoInit[26] = true;
    }

    public void goForward() {
        boolean[] $jacocoInit = $jacocoInit();
        if (getWebView() == null) {
            $jacocoInit[27] = true;
            return;
        }
        getWebView().goForward();
        $jacocoInit[28] = true;
    }

    public void postMessage(Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        if (getWebView() != null) {
            $jacocoInit[29] = true;
            try {
                JSONObject jSONObject = new JSONObject();
                $jacocoInit[31] = true;
                jSONObject.put("type", (Object) "message");
                $jacocoInit[32] = true;
                jSONObject.put("data", obj);
                $jacocoInit[33] = true;
                jSONObject.put("origin", (Object) this.mOrigin);
                $jacocoInit[34] = true;
                StringBuilder sb = new StringBuilder();
                sb.append("javascript:(function () {var initData = ");
                $jacocoInit[35] = true;
                sb.append(jSONObject.toString());
                sb.append(";try {var event = new MessageEvent('message', initData);window.dispatchEvent(event);} catch (e) {}})();");
                String sb2 = sb.toString();
                $jacocoInit[36] = true;
                evaluateJS(sb2);
                $jacocoInit[39] = true;
            } catch (JSONException e) {
                $jacocoInit[37] = true;
                RuntimeException runtimeException = new RuntimeException(e);
                $jacocoInit[38] = true;
                throw runtimeException;
            }
        } else {
            $jacocoInit[30] = true;
        }
    }

    public void setShowLoading(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mShowLoading = z;
        $jacocoInit[40] = true;
    }

    public void setOnErrorListener(IWebView.OnErrorListener onErrorListener) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mOnErrorListener = onErrorListener;
        $jacocoInit[41] = true;
    }

    public void setOnPageListener(IWebView.OnPageListener onPageListener) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mOnPageListener = onPageListener;
        $jacocoInit[42] = true;
    }

    public void setOnMessageListener(IWebView.OnMessageListener onMessageListener) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mOnMessageListener = onMessageListener;
        $jacocoInit[43] = true;
    }

    private void showProgressBar(boolean z) {
        int i;
        boolean[] $jacocoInit = $jacocoInit();
        if (!this.mShowLoading) {
            $jacocoInit[44] = true;
        } else {
            $jacocoInit[45] = true;
            ProgressBar progressBar = this.mProgressBar;
            if (z) {
                i = 0;
                $jacocoInit[46] = true;
            } else {
                i = 8;
                $jacocoInit[47] = true;
            }
            progressBar.setVisibility(i);
            $jacocoInit[48] = true;
        }
        $jacocoInit[49] = true;
    }

    private void showWebView(boolean z) {
        int i;
        boolean[] $jacocoInit = $jacocoInit();
        WebView webView = this.mWebView;
        if (z) {
            i = 0;
            $jacocoInit[50] = true;
        } else {
            i = 4;
            $jacocoInit[51] = true;
        }
        webView.setVisibility(i);
        $jacocoInit[52] = true;
    }

    @Nullable
    private WebView getWebView() {
        boolean[] $jacocoInit = $jacocoInit();
        WebView webView = this.mWebView;
        $jacocoInit[53] = true;
        return webView;
    }

    private void initWebView(WebView webView) {
        boolean[] $jacocoInit = $jacocoInit();
        WebSettings settings = webView.getSettings();
        $jacocoInit[54] = true;
        settings.setJavaScriptEnabled(true);
        $jacocoInit[55] = true;
        settings.setAppCacheEnabled(true);
        $jacocoInit[56] = true;
        settings.setUseWideViewPort(true);
        $jacocoInit[57] = true;
        settings.setDomStorageEnabled(true);
        $jacocoInit[58] = true;
        settings.setSupportZoom(false);
        $jacocoInit[59] = true;
        settings.setBuiltInZoomControls(false);
        $jacocoInit[60] = true;
        settings.setAllowFileAccess(false);
        $jacocoInit[61] = true;
        webView.setWebViewClient(new WebViewClient(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXWebView this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-5255771912024186832L, "com/taobao/weex/ui/view/WXWebView$1", 37);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r3;
                $jacocoInit[0] = true;
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                boolean[] $jacocoInit = $jacocoInit();
                webView.loadUrl(str);
                $jacocoInit[1] = true;
                WXLogUtils.v("tag", "onPageOverride " + str);
                $jacocoInit[2] = true;
                return true;
            }

            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                boolean[] $jacocoInit = $jacocoInit();
                super.onPageStarted(webView, str, bitmap);
                $jacocoInit[3] = true;
                WXLogUtils.v("tag", "onPageStarted " + str);
                $jacocoInit[4] = true;
                if (WXWebView.access$100(this.this$0) == null) {
                    $jacocoInit[5] = true;
                } else {
                    $jacocoInit[6] = true;
                    WXWebView.access$100(this.this$0).onPageStart(str);
                    $jacocoInit[7] = true;
                }
                $jacocoInit[8] = true;
            }

            public void onPageFinished(WebView webView, String str) {
                String str2;
                boolean[] $jacocoInit = $jacocoInit();
                super.onPageFinished(webView, str);
                $jacocoInit[9] = true;
                WXLogUtils.v("tag", "onPageFinished " + str);
                $jacocoInit[10] = true;
                if (WXWebView.access$100(this.this$0) == null) {
                    $jacocoInit[11] = true;
                } else {
                    $jacocoInit[12] = true;
                    WXWebView.access$100(this.this$0).onPageFinish(str, webView.canGoBack(), webView.canGoForward());
                    $jacocoInit[13] = true;
                }
                if (WXWebView.access$200(this.this$0) == null) {
                    $jacocoInit[14] = true;
                } else {
                    $jacocoInit[15] = true;
                    WXWebView wXWebView = this.this$0;
                    StringBuilder sb = new StringBuilder();
                    sb.append("javascript:(window.postMessage = function(message, targetOrigin) {if (message == null || !targetOrigin) return;");
                    $jacocoInit[16] = true;
                    if (WXWebView.access$300()) {
                        str2 = "prompt('__WEEX_WEB_VIEW_BRIDGE://postMessage?message=' + JSON.stringify(message) + '&targetOrigin=' + targetOrigin)";
                        $jacocoInit[17] = true;
                    } else {
                        str2 = "__WEEX_WEB_VIEW_BRIDGE.postMessage(JSON.stringify(message), targetOrigin);";
                        $jacocoInit[18] = true;
                    }
                    sb.append(str2);
                    sb.append("})");
                    String sb2 = sb.toString();
                    $jacocoInit[19] = true;
                    WXWebView.access$400(wXWebView, sb2);
                    $jacocoInit[20] = true;
                }
                $jacocoInit[21] = true;
            }

            public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                boolean[] $jacocoInit = $jacocoInit();
                super.onReceivedError(webView, webResourceRequest, webResourceError);
                $jacocoInit[22] = true;
                if (WXWebView.access$500(this.this$0) == null) {
                    $jacocoInit[23] = true;
                } else {
                    $jacocoInit[24] = true;
                    WXWebView.access$500(this.this$0).onError("error", "page error");
                    $jacocoInit[25] = true;
                }
                $jacocoInit[26] = true;
            }

            public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
                boolean[] $jacocoInit = $jacocoInit();
                super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
                $jacocoInit[27] = true;
                if (WXWebView.access$500(this.this$0) == null) {
                    $jacocoInit[28] = true;
                } else {
                    $jacocoInit[29] = true;
                    WXWebView.access$500(this.this$0).onError("error", "http error");
                    $jacocoInit[30] = true;
                }
                $jacocoInit[31] = true;
            }

            public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
                boolean[] $jacocoInit = $jacocoInit();
                super.onReceivedSslError(webView, sslErrorHandler, sslError);
                $jacocoInit[32] = true;
                if (WXWebView.access$500(this.this$0) == null) {
                    $jacocoInit[33] = true;
                } else {
                    $jacocoInit[34] = true;
                    WXWebView.access$500(this.this$0).onError("error", "ssl error");
                    $jacocoInit[35] = true;
                }
                $jacocoInit[36] = true;
            }
        });
        $jacocoInit[62] = true;
        webView.setWebChromeClient(new WebChromeClient(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXWebView this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-2486894487086128300L, "com/taobao/weex/ui/view/WXWebView$2", 25);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r3;
                $jacocoInit[0] = true;
            }

            public void onProgressChanged(WebView webView, int i) {
                boolean z;
                boolean[] $jacocoInit = $jacocoInit();
                super.onProgressChanged(webView, i);
                $jacocoInit[1] = true;
                WXWebView wXWebView = this.this$0;
                boolean z2 = false;
                if (i == 100) {
                    $jacocoInit[2] = true;
                    z = true;
                } else {
                    $jacocoInit[3] = true;
                    z = false;
                }
                WXWebView.access$600(wXWebView, z);
                $jacocoInit[4] = true;
                WXWebView wXWebView2 = this.this$0;
                if (i != 100) {
                    $jacocoInit[5] = true;
                    z2 = true;
                } else {
                    $jacocoInit[6] = true;
                }
                WXWebView.access$700(wXWebView2, z2);
                $jacocoInit[7] = true;
                WXLogUtils.v("tag", "onPageProgressChanged " + i);
                $jacocoInit[8] = true;
            }

            public void onReceivedTitle(WebView webView, String str) {
                boolean[] $jacocoInit = $jacocoInit();
                super.onReceivedTitle(webView, str);
                $jacocoInit[9] = true;
                if (WXWebView.access$100(this.this$0) == null) {
                    $jacocoInit[10] = true;
                } else {
                    $jacocoInit[11] = true;
                    WXWebView.access$100(this.this$0).onReceivedTitle(webView.getTitle());
                    $jacocoInit[12] = true;
                }
                $jacocoInit[13] = true;
            }

            public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
                boolean[] $jacocoInit = $jacocoInit();
                Uri parse = Uri.parse(str2);
                $jacocoInit[14] = true;
                String scheme = parse.getScheme();
                $jacocoInit[15] = true;
                if (TextUtils.equals(scheme, WXWebView.BRIDGE_NAME)) {
                    $jacocoInit[16] = true;
                    if (TextUtils.equals(parse.getAuthority(), WXWeb.POST_MESSAGE)) {
                        $jacocoInit[17] = true;
                        String queryParameter = parse.getQueryParameter("message");
                        $jacocoInit[18] = true;
                        String queryParameter2 = parse.getQueryParameter("targetOrigin");
                        $jacocoInit[19] = true;
                        WXWebView.access$800(this.this$0, queryParameter, queryParameter2);
                        $jacocoInit[20] = true;
                        jsPromptResult.confirm("success");
                        $jacocoInit[21] = true;
                    } else {
                        jsPromptResult.confirm("fail");
                        $jacocoInit[22] = true;
                    }
                    $jacocoInit[23] = true;
                    return true;
                }
                boolean onJsPrompt = super.onJsPrompt(webView, str, str2, str3, jsPromptResult);
                $jacocoInit[24] = true;
                return onJsPrompt;
            }
        });
        if (DOWNGRADE_JS_INTERFACE) {
            $jacocoInit[63] = true;
        } else {
            $jacocoInit[64] = true;
            webView.addJavascriptInterface(new Object(this) {
                private static transient /* synthetic */ boolean[] $jacocoData;
                final /* synthetic */ WXWebView this$0;

                private static /* synthetic */ boolean[] $jacocoInit() {
                    boolean[] zArr = $jacocoData;
                    if (zArr != null) {
                        return zArr;
                    }
                    boolean[] a2 = Offline.a(4669575429749244782L, "com/taobao/weex/ui/view/WXWebView$3", 2);
                    $jacocoData = a2;
                    return a2;
                }

                {
                    boolean[] $jacocoInit = $jacocoInit();
                    this.this$0 = r3;
                    $jacocoInit[0] = true;
                }

                @JavascriptInterface
                public void postMessage(String str, String str2) {
                    boolean[] $jacocoInit = $jacocoInit();
                    WXWebView.access$800(this.this$0, str, str2);
                    $jacocoInit[1] = true;
                }
            }, BRIDGE_NAME);
            $jacocoInit[65] = true;
        }
        $jacocoInit[66] = true;
    }

    private void onMessage(String str, String str2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (str == null) {
            $jacocoInit[67] = true;
        } else if (str2 == null) {
            $jacocoInit[68] = true;
        } else if (this.mOnMessageListener == null) {
            $jacocoInit[69] = true;
        } else {
            try {
                $jacocoInit[70] = true;
                HashMap hashMap = new HashMap();
                $jacocoInit[71] = true;
                hashMap.put("data", JSON.parse(str));
                $jacocoInit[72] = true;
                hashMap.put("origin", str2);
                $jacocoInit[73] = true;
                hashMap.put("type", "message");
                $jacocoInit[74] = true;
                Message message = new Message();
                message.what = 1;
                message.obj = hashMap;
                $jacocoInit[75] = true;
                this.mMessageHandler.sendMessage(message);
                $jacocoInit[76] = true;
            } catch (JSONException e) {
                $jacocoInit[77] = true;
                RuntimeException runtimeException = new RuntimeException(e);
                $jacocoInit[78] = true;
                throw runtimeException;
            }
        }
        $jacocoInit[79] = true;
    }

    private void evaluateJS(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (SDK_VERSION < 19) {
            $jacocoInit[80] = true;
            this.mWebView.loadUrl(str);
            $jacocoInit[81] = true;
        } else {
            this.mWebView.evaluateJavascript(str, (ValueCallback) null);
            $jacocoInit[82] = true;
        }
        $jacocoInit[83] = true;
    }

    private static class MessageHandler extends Handler {
        private static transient /* synthetic */ boolean[] $jacocoData;
        private final WeakReference<WXWebView> mWv;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(-2319113482813152570L, "com/taobao/weex/ui/view/WXWebView$MessageHandler", 9);
            $jacocoData = a2;
            return a2;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ MessageHandler(WXWebView wXWebView, AnonymousClass1 r3) {
            this(wXWebView);
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[8] = true;
        }

        private MessageHandler(WXWebView wXWebView) {
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[0] = true;
            this.mWv = new WeakReference<>(wXWebView);
            $jacocoInit[1] = true;
        }

        public void handleMessage(Message message) {
            boolean[] $jacocoInit = $jacocoInit();
            super.handleMessage(message);
            if (message.what != 1) {
                $jacocoInit[2] = true;
            } else if (this.mWv.get() == null) {
                $jacocoInit[3] = true;
            } else if (WXWebView.access$200((WXWebView) this.mWv.get()) == null) {
                $jacocoInit[4] = true;
            } else {
                $jacocoInit[5] = true;
                WXWebView.access$200((WXWebView) this.mWv.get()).onMessage((Map) message.obj);
                $jacocoInit[6] = true;
            }
            $jacocoInit[7] = true;
        }
    }
}
