package com.xiaomi.smarthome.framework.plugin.web;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.webkit.ConsoleMessage;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.xiaomi.router.miio.miioplugin.IPluginRequest;
import com.xiaomi.router.miio.miioplugin.PluginServiceManager;
import com.xiaomi.smarthome.framework.plugin.web.command.BaseCommand;
import com.xiaomi.smarthome.framework.plugin.web.command.CommandFactory;
import com.xiaomi.smarthome.library.common.util.PreferenceUtils;
import com.xiaomi.smarthome.miio.Miio;
import com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoListActivity;
import java.net.URLDecoder;
import java.util.HashSet;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"SetJavaScriptEnabled"})
public class PluginWebView extends WebView {
    static final int _MSG_CHANGE_PAGE = 201;
    public static final int _MSG_NOTIFY_JSRUNTIME = 200;
    static final int _MSG_WEB_PAGE_READY = 203;
    static final int _MSG_WIDGET = 202;

    /* renamed from: a  reason: collision with root package name */
    private static final String f17643a = "shpluginscheme://";
    private static final String b = "_MESSAGE_SEMAPHORE_";
    private static final String c = "_MESSAGE_QUEUE_";
    private static final String d = "_MESSAGE_SPLIT_";
    private static final String e = "sh_webplugin_ConsoleLogExpire";
    private static final long f = 30;
    private static final String g = "sh_webplugin_ConsoleLogStatus";
    private static final int h = 0;
    private static final int i = 1;
    private static final int j = 2;
    private static final String o = "entranceParam";
    private static final String p = "consoleLog";
    private static final String q = "url";
    /* access modifiers changed from: private */
    public static String r = "consoleLog";
    private static final int t = 204;
    private static final int u = 205;
    private static final int v = 206;
    private static final int w = 207;
    /* access modifiers changed from: private */
    public String k;
    private String l;
    private String m = "file:///android_asset/plugin/SHPluginBridge.js";
    long mConsoleLogCheckExpireTime;
    long mConsoleLogCheckStartTime;
    Context mContext;
    /* access modifiers changed from: private */
    public IPluginRequest n;
    private HashSet<PluginWebViewListener> s = new HashSet<>();
    /* access modifiers changed from: private */
    @SuppressLint({"HandlerLeak"})
    public Handler x = new Handler() {
        public void handleMessage(Message message) {
            int i = message.what;
            if (i != 200) {
                switch (i) {
                    case 204:
                        if (PreferenceUtils.c(PluginWebView.this.mContext, PluginWebView.g, 0) != 2) {
                            PreferenceUtils.a(PluginWebView.this.mContext, PluginWebView.g, 1);
                            PreferenceUtils.a(PluginWebView.this.mContext, PluginWebView.e, PluginWebView.this.mConsoleLogCheckStartTime + CloudVideoListActivity.THIRTY_DAYS_MILLIS);
                            String unused = PluginWebView.r = PluginWebView.p;
                            ((CheckConsoleLogCallback) message.obj).a();
                            return;
                        }
                        return;
                    case 205:
                        if (PreferenceUtils.c(PluginWebView.this.mContext, PluginWebView.g, 0) != 1) {
                            PreferenceUtils.a(PluginWebView.this.mContext, PluginWebView.g, 2);
                            PreferenceUtils.a(PluginWebView.this.mContext, PluginWebView.e, PluginWebView.this.mConsoleLogCheckStartTime + CloudVideoListActivity.THIRTY_DAYS_MILLIS);
                            String unused2 = PluginWebView.r = "url";
                            ((CheckConsoleLogCallback) message.obj).b();
                            return;
                        }
                        return;
                    case 206:
                        String unused3 = PluginWebView.r = PluginWebView.p;
                        ((CheckConsoleLogCallback) message.obj).a();
                        return;
                    case 207:
                        String unused4 = PluginWebView.r = "url";
                        ((CheckConsoleLogCallback) message.obj).b();
                        return;
                    default:
                        return;
                }
            } else {
                PluginWebView.this.c((String) message.obj);
            }
        }
    };
    private WebChromeClient y = new WebChromeClient() {
        public void onReceivedTitle(WebView webView, String str) {
        }

        @SuppressLint({"NewApi"})
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            String message = consoleMessage.message();
            Miio.g(message);
            PluginWebView.this.a(message);
            return super.onConsoleMessage(consoleMessage);
        }
    };
    private WebViewClient z = new WebViewClient() {
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (str.startsWith(PluginWebView.f17643a)) {
                String replace = str.replace(PluginWebView.f17643a, "");
                if (replace.startsWith(PluginWebView.b)) {
                    PluginWebView pluginWebView = PluginWebView.this;
                    pluginWebView.a(webView, "SHPlugin.Bridge.getMessage(\"" + PluginWebView.r + "\");");
                    return true;
                } else if (replace.startsWith(PluginWebView.c)) {
                    try {
                        PluginWebView.this.a(URLDecoder.decode(replace.replace(PluginWebView.c, ""), "UTF-8"));
                    } catch (Exception unused) {
                    }
                    return true;
                }
            }
            return super.shouldOverrideUrlLoading(webView, str);
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
        }

        public void onPageFinished(WebView webView, String str) {
            PluginWebView.this.loadPluginBridge();
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
        }
    };

    private interface CheckConsoleLogCallback {
        void a();

        void b();
    }

    public PluginWebView(Context context) {
        super(context);
        this.mContext = context;
        a();
        initServiceRequest();
    }

    public PluginWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        a();
        initServiceRequest();
    }

    public PluginWebView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mContext = context;
        a();
        initServiceRequest();
    }

    private void a() {
        clearCache(true);
        setWebContentsDebuggingEnabled(true);
        getSettings().setAllowFileAccess(true);
        getSettings().setJavaScriptEnabled(true);
        getSettings().setDomStorageEnabled(true);
        setWebChromeClient(this.y);
        setWebViewClient(this.z);
    }

    /* access modifiers changed from: package-private */
    public void initServiceRequest() {
        PluginServiceManager.a().a((PluginServiceManager.BindServiceListener) new PluginServiceManager.BindServiceListener() {
            public void onBindService(IPluginRequest iPluginRequest) {
                IPluginRequest unused = PluginWebView.this.n = PluginServiceManager.a().b();
            }
        });
        this.n = PluginServiceManager.a().b();
    }

    private void a(final CheckConsoleLogCallback checkConsoleLogCallback) {
        this.mConsoleLogCheckStartTime = System.currentTimeMillis();
        this.mConsoleLogCheckExpireTime = PreferenceUtils.b(this.mContext, e, 0);
        int c2 = PreferenceUtils.c(this.mContext, g, 0);
        if (this.mConsoleLogCheckStartTime > this.mConsoleLogCheckExpireTime || c2 == 0) {
            PreferenceUtils.c(this.mContext, g);
            WebView webView = new WebView(this.mContext);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebChromeClient(new WebChromeClient() {
                public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                    PluginWebView.this.x.obtainMessage(204, checkConsoleLogCallback).sendToTarget();
                    return super.onConsoleMessage(consoleMessage);
                }
            });
            a(webView, "console.log('testConsoleLog')");
            this.x.sendMessageDelayed(this.x.obtainMessage(205, checkConsoleLogCallback), 10000);
        } else if (c2 == 1) {
            this.x.obtainMessage(206, checkConsoleLogCallback).sendToTarget();
        } else if (c2 == 2) {
            this.x.obtainMessage(207, checkConsoleLogCallback).sendToTarget();
        }
    }

    public void loadPluginBridge() {
        a(this, "var bridgeElement=document.createElement('script');bridgeElement.src='" + this.m + "';bridgeElement.onload=function(){var mainElement=document.createElement('script');mainElement.src='main.js';mainElement.onload=function(){SHPlugin.Api.notify('PluginBridgeReady');};document.head.appendChild(mainElement);};document.head.appendChild(bridgeElement);");
    }

    public void launch(String str, String str2) {
        this.l = str2;
        this.k = str;
        a((CheckConsoleLogCallback) new CheckConsoleLogCallback() {
            public void a() {
                PluginWebView.this.loadUrl(PluginWebView.this.k);
            }

            public void b() {
                PluginWebView.this.loadUrl(PluginWebView.this.k);
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            if (str.contains(d)) {
                String[] split = str.split(d);
                for (String b2 : split) {
                    b(b2);
                }
                return;
            }
            b(str);
        }
    }

    private void b(String str) {
        BaseCommand a2;
        if (!TextUtils.isEmpty(str) && (a2 = CommandFactory.a(BaseCommand.a(str))) != null) {
            a2.a(this, str, this.x, this.n);
            a2.c();
        }
    }

    public void sendEntranceParam(String str) {
        if (str != null) {
            this.l = str;
        }
        try {
            sendMessageToJSRuntime(o, new JSONObject(this.l));
        } catch (JSONException unused) {
        }
    }

    public void sendMessageToJSRuntime(String str, JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        try {
            jSONObject2.put(BaseCommand.f17651a, str);
            jSONObject2.put("param", jSONObject);
        } catch (JSONException unused) {
        }
        c(jSONObject2.toString());
    }

    /* access modifiers changed from: private */
    public void c(String str) {
        a(this, "SHPlugin.Bridge.notify(" + str + ");");
    }

    /* access modifiers changed from: private */
    public void a(WebView webView, String str) {
        if (Build.VERSION.SDK_INT >= 19) {
            webView.evaluateJavascript(str, (ValueCallback) null);
            return;
        }
        webView.loadUrl("javascript:" + str);
    }

    private void a(Message message) {
        Iterator<PluginWebViewListener> it = this.s.iterator();
        while (it.hasNext()) {
            it.next().a(message);
        }
    }

    public void registerListener(PluginWebViewListener pluginWebViewListener) {
        this.s.add(pluginWebViewListener);
    }

    public void unregisterListener(PluginWebViewListener pluginWebViewListener) {
        this.s.remove(pluginWebViewListener);
    }
}
