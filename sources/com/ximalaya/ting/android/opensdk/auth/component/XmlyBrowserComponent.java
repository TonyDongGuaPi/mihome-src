package com.ximalaya.ting.android.opensdk.auth.component;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.taobao.weex.el.parse.Operators;
import com.ximalaya.ting.android.opensdk.auth.call.IXmSimpleLoginCallBack;
import com.ximalaya.ting.android.opensdk.auth.call.IXmlyAuthListener;
import com.ximalaya.ting.android.opensdk.auth.constants.XmlyConstants;
import com.ximalaya.ting.android.opensdk.auth.exception.XmlyAuthException;
import com.ximalaya.ting.android.opensdk.auth.exception.XmlyException;
import com.ximalaya.ting.android.opensdk.auth.model.XmlyAuth2AccessToken;
import com.ximalaya.ting.android.opensdk.auth.model.XmlyAuthInfo;
import com.ximalaya.ting.android.opensdk.auth.model.XmlyErrorInfo;
import com.ximalaya.ting.android.opensdk.auth.utils.AccessTokenKeeper;
import com.ximalaya.ting.android.opensdk.auth.utils.Logger;
import com.ximalaya.ting.android.opensdk.auth.utils.c;
import com.ximalaya.ting.android.opensdk.auth.utils.d;
import com.ximalaya.ting.android.opensdk.auth.utils.g;
import com.ximalaya.ting.android.opensdk.auth.utils.h;
import com.ximalaya.ting.android.opensdk.auth.view.LoadingBar;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

public class XmlyBrowserComponent extends Activity implements b {
    public static final String BROWSER_CLOSE_SCHEME = "ximalaya://browser/close";
    public static final int COLOR_BROWSER_LOAD_ERROR_BACKGROUND = -657931;
    public static final int COLOR_BROWSER_LOAD_ERROR_RETRY = -6710887;
    public static final int COLOR_BROWSER_TITLE_BAR_DIVIDER = -1513240;
    public static final int COLOR_BROWSER_TITLE_BAR_LEFT_BUTTON_NORMAL = -498622;
    public static final int COLOR_BROWSER_TITLE_BAR_LEFT_BUTTON_PRESSED = -6710887;
    public static final int COLOR_BROWSER_TITLE_BAR_TITLE = -13421773;
    public static final String OBTAIN_AUTH_TYPE_CODE = "code";
    public static final String OBTAIN_AUTH_TYPE_TOKEN = "token";
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static final String f1808a = "XmlyBrowserComponent";
    private static final String b = "UTF-8";
    private static final int c = 15;
    private static final String d = "https://api.ximalaya.com/oauth2/v2/authorize?";
    public static IXmSimpleLoginCallBack mSimpleLogin;
    private ImageView e;
    private ImageView f;
    private Button g;
    private TextView h;
    /* access modifiers changed from: private */
    public WebView i;
    /* access modifiers changed from: private */
    public LoadingBar j;
    private LinearLayout k;
    private f l;
    /* access modifiers changed from: private */
    public d m;
    /* access modifiers changed from: private */
    public XmlyAuthInfo n;
    /* access modifiers changed from: private */
    public String o = "";
    /* access modifiers changed from: private */
    public IXmlyAuthListener p;
    /* access modifiers changed from: private */
    public String q;
    /* access modifiers changed from: private */
    public String r;
    /* access modifiers changed from: private */
    public boolean s;
    /* access modifiers changed from: private */
    public String t = "";
    /* access modifiers changed from: private */
    public boolean u;
    private String v = "";

    public static void closeBrowser(Activity activity, String str) {
        e a2 = e.a(activity.getApplicationContext());
        if (!TextUtils.isEmpty(str)) {
            a2.b(str);
            activity.finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        if (!a(getIntent())) {
            finish();
        } else {
            a();
            d();
            a(this.t);
        }
        a();
        d();
        a(this.t);
    }

    private void a() {
        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        relativeLayout.setBackgroundColor(-1);
        LinearLayout linearLayout = new LinearLayout(this);
        int c2 = c();
        linearLayout.setId(c2);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        linearLayout.setOrientation(1);
        RelativeLayout relativeLayout2 = new RelativeLayout(this);
        relativeLayout2.setLayoutParams(new LinearLayout.LayoutParams(-1, d.a((Context) this, 50)));
        this.e = new ImageView(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(9);
        layoutParams.addRule(15);
        layoutParams.leftMargin = d.a((Context) this, 10);
        layoutParams.rightMargin = d.a((Context) this, 10);
        this.e.setLayoutParams(layoutParams);
        this.e.setClickable(true);
        this.e.setImageDrawable(d.a((Context) this, "xmly_auth_sdk_back.png"));
        this.f = new ImageView(this);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(9);
        layoutParams2.addRule(15);
        layoutParams2.leftMargin = d.a((Context) this, 45);
        layoutParams2.rightMargin = d.a((Context) this, 10);
        this.f.setLayoutParams(layoutParams2);
        this.f.setClickable(true);
        this.f.setImageDrawable(d.a((Context) this, "xmly_auth_sdk_close.png"));
        this.f.setVisibility(8);
        this.h = new TextView(this);
        this.h.setTextSize(2, 18.0f);
        this.h.setTextColor(-13421773);
        this.h.setEllipsize(TextUtils.TruncateAt.END);
        this.h.setSingleLine(true);
        this.h.setGravity(17);
        this.h.setMaxWidth(d.a((Context) this, 160));
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.addRule(13);
        this.h.setLayoutParams(layoutParams3);
        View view = new View(this);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, d.a((Context) this, 1));
        layoutParams4.addRule(12);
        view.setLayoutParams(layoutParams4);
        view.setBackgroundColor(-1513240);
        relativeLayout2.addView(this.e);
        relativeLayout2.addView(this.f);
        relativeLayout2.addView(this.h);
        relativeLayout2.addView(view);
        this.j = new LoadingBar(this);
        this.j.setBackgroundColor(0);
        this.j.a(0);
        this.j.setLayoutParams(new LinearLayout.LayoutParams(-1, d.a((Context) this, 3)));
        linearLayout.addView(relativeLayout2);
        linearLayout.addView(this.j);
        this.i = new WebView(this);
        this.i.setBackgroundColor(-1);
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams5.addRule(3, c2);
        this.i.setLayoutParams(layoutParams5);
        this.k = new LinearLayout(this);
        RelativeLayout.LayoutParams layoutParams6 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams6.addRule(3, c2);
        this.k.setLayoutParams(layoutParams6);
        this.k.setVisibility(8);
        this.k.setGravity(17);
        this.k.setOrientation(1);
        this.k.setBackgroundColor(-657931);
        ImageView imageView = new ImageView(this);
        imageView.setImageDrawable(d.a((Context) this, "xmly_auth_sdk_empty_failed.png"));
        LinearLayout.LayoutParams layoutParams7 = new LinearLayout.LayoutParams(-2, -2);
        int a2 = d.a((Context) this, 8);
        layoutParams7.bottomMargin = a2;
        layoutParams7.rightMargin = a2;
        layoutParams7.topMargin = a2;
        layoutParams7.leftMargin = a2;
        imageView.setLayoutParams(layoutParams7);
        this.k.addView(imageView);
        this.g = new Button(this);
        this.g.setGravity(17);
        this.g.setTextColor(-6710887);
        this.g.setTextSize(2, 16.0f);
        this.g.setText("重新加载");
        this.g.setBackgroundDrawable(d.a((Context) this, "xmly_auth_sdk_common_button_alpha.9.png", "xmly_auth_sdk_common_button_alpha_highlighted.9.png"));
        LinearLayout.LayoutParams layoutParams8 = new LinearLayout.LayoutParams(d.a((Context) this, 142), d.a((Context) this, 46));
        layoutParams8.topMargin = d.a((Context) this, 10);
        this.g.setLayoutParams(layoutParams8);
        this.g.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                XmlyBrowserComponent.this.a(XmlyBrowserComponent.this.t);
                boolean unused = XmlyBrowserComponent.this.u = false;
            }
        });
        this.k.addView(this.g);
        relativeLayout.addView(linearLayout);
        relativeLayout.addView(this.i);
        relativeLayout.addView(this.k);
        setContentView(relativeLayout);
        b();
        if (c.b(this)) {
            this.i.setVisibility(0);
            this.k.setVisibility(8);
            return;
        }
        this.i.setVisibility(8);
        this.k.setVisibility(0);
    }

    private void b() {
        this.h.setText(this.v);
        this.e.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                if (XmlyBrowserComponent.this.i == null || !XmlyBrowserComponent.this.i.canGoBack()) {
                    if (XmlyBrowserComponent.this.m != null) {
                        XmlyBrowserComponent.this.m.a((Activity) XmlyBrowserComponent.this);
                    }
                    XmlyBrowserComponent.this.finish();
                    return;
                }
                XmlyBrowserComponent.this.i.goBack();
            }
        });
        this.f.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                if (XmlyBrowserComponent.this.m != null) {
                    XmlyBrowserComponent.this.m.a((Activity) XmlyBrowserComponent.this);
                }
                XmlyBrowserComponent.this.finish();
            }
        });
    }

    private int c() {
        if (Build.VERSION.SDK_INT < 17) {
            return g.a();
        }
        return View.generateViewId();
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void d() {
        this.i.getSettings().setJavaScriptEnabled(true);
        this.i.getSettings().setDomStorageEnabled(true);
        this.i.addJavascriptInterface(new a(), "jscall");
        this.i.getSettings().setSavePassword(false);
        this.i.setWebViewClient(this.l);
        this.i.setWebChromeClient(new b(this, (byte) 0));
        this.i.requestFocus();
        this.i.setScrollBarStyle(0);
        if (Build.VERSION.SDK_INT >= 21) {
            this.i.getSettings().setMixedContentMode(0);
        }
        if (Build.VERSION.SDK_INT >= 11) {
            this.i.removeJavascriptInterface("searchBoxJavaBridge_");
        } else {
            a(this.i);
        }
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        this.i.loadUrl(str);
    }

    private boolean a(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            this.n = XmlyAuthInfo.a(this, extras.getBundle(c.f1820a));
        }
        if (this.n == null) {
            this.n = new XmlyAuthInfo(this, "", "", "");
        } else {
            this.o = this.n.g();
        }
        this.m = a(extras);
        if (this.m != null) {
            this.t = this.m.e();
            this.v = this.m.f();
        } else {
            String string = extras.getString(d.f);
            String string2 = extras.getString(d.g);
            if (!TextUtils.isEmpty(string) && string.startsWith("http")) {
                this.t = string;
                this.v = string2;
            }
        }
        if (TextUtils.isEmpty(this.t)) {
            return false;
        }
        String str = f1808a;
        Logger.a(str, "LOAD URL : " + this.t);
        return true;
    }

    private d a(Bundle bundle) {
        c cVar = new c(this);
        cVar.c(bundle);
        this.p = cVar.b();
        this.q = cVar.c();
        a(cVar);
        return cVar;
    }

    private void a(c cVar) {
        this.l = new a(this, cVar);
        this.l.a(this);
    }

    private void a(WebView webView) {
        if (Build.VERSION.SDK_INT < 11) {
            try {
                webView.getClass().getDeclaredMethod("removeJavascriptInterface", new Class[0]).invoke("searchBoxJavaBridge_", new Object[0]);
            } catch (Exception e2) {
                Logger.c(f1808a, e2.toString());
            }
        }
    }

    public void onPageStartedCallBack(WebView webView, String str, Bitmap bitmap) {
        String str2 = f1808a;
        Logger.a(str2, "onPageStarted URL: " + str);
        this.t = str;
        if (!b(str)) {
            this.r = "";
        }
    }

    public boolean shouldOverrideUrlLoadingCallBack(WebView webView, String str) {
        String str2 = f1808a;
        Logger.b(str2, "shouldOverrideUrlLoading URL: " + str);
        return false;
    }

    public void onPageFinishedCallBack(WebView webView, String str) {
        if (!(webView == null || this.f == null)) {
            if (webView.canGoBack()) {
                this.f.setVisibility(0);
            } else {
                this.f.setVisibility(8);
            }
        }
        String str2 = f1808a;
        Logger.a(str2, "onPageFinished URL: " + str);
        if (this.u) {
            e();
        } else {
            this.u = false;
            f();
        }
        this.i.loadUrl("javascript:window.jscall.handleErrorInfo(document.getElementsByTagName('body')[0].innerText);");
    }

    public void onReceivedErrorCallBack(WebView webView, int i2, String str, String str2) {
        String str3 = f1808a;
        Logger.a(str3, "onReceivedError: errorCode = " + i2 + ", description = " + str + ", failingUrl = " + str2);
        a(webView, i2, str, str2);
    }

    public void onReceivedSslErrorCallBack(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        Logger.a(f1808a, "onReceivedSslErrorCallBack.........");
    }

    private class b extends WebChromeClient {
        /* synthetic */ b(XmlyBrowserComponent xmlyBrowserComponent, byte b) {
            this();
        }

        private b() {
        }

        public final void onProgressChanged(WebView webView, int i) {
            XmlyBrowserComponent.this.j.setVisibility(0);
            XmlyBrowserComponent.this.j.a(i * 100);
            if (i == 100) {
                boolean unused = XmlyBrowserComponent.this.s = false;
                XmlyBrowserComponent.this.refreshAllViews();
            } else if (!XmlyBrowserComponent.this.s) {
                boolean unused2 = XmlyBrowserComponent.this.s = true;
                XmlyBrowserComponent.this.refreshAllViews();
            }
        }

        public final void onReceivedTitle(WebView webView, String str) {
            if (!XmlyBrowserComponent.this.b(XmlyBrowserComponent.this.t)) {
                String unused = XmlyBrowserComponent.this.r = str;
                XmlyBrowserComponent.this.i();
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return "xmly".equalsIgnoreCase(Uri.parse(str).getAuthority());
    }

    /* access modifiers changed from: protected */
    public void refreshAllViews() {
        if (this.s) {
            g();
        } else {
            h();
        }
    }

    private void a(WebView webView, int i2, String str, String str2) {
        if (!str2.startsWith("xmly")) {
            this.u = true;
            e();
        }
    }

    private void e() {
        this.k.setVisibility(0);
        this.i.setVisibility(8);
    }

    private void f() {
        this.k.setVisibility(8);
        this.i.setVisibility(0);
    }

    /* access modifiers changed from: private */
    public void g() {
        this.h.setText("加载中....");
        this.j.setVisibility(0);
    }

    private void h() {
        i();
        this.j.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void i() {
        String str = "";
        if (!TextUtils.isEmpty(this.r)) {
            str = this.r;
        } else if (!TextUtils.isEmpty(this.v)) {
            str = this.v;
        }
        this.h.setText(str);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        if (i2 != 4) {
            return super.onKeyUp(i2, keyEvent);
        }
        if (this.m != null) {
            this.m.a((Activity) this);
        }
        finish();
        return true;
    }

    final class a {
        a() {
        }

        @JavascriptInterface
        public final void handleErrorInfo(String str) {
            XmlyErrorInfo d;
            if (!TextUtils.isEmpty(str) && (d = XmlyErrorInfo.d(str.trim())) != null) {
                if (XmlyBrowserComponent.this.p != null) {
                    XmlyBrowserComponent.this.p.a((XmlyException) new XmlyAuthException(d.a(), d.b(), d.c()));
                }
                Logger.a(XmlyBrowserComponent.f1808a, "Failed to receive access token by Web");
                XmlyBrowserComponent.closeBrowser(XmlyBrowserComponent.this, XmlyBrowserComponent.this.q);
            }
        }

        @JavascriptInterface
        public final void authorize(String str) {
            if (XmlyBrowserComponent.this.j.getVisibility() != 0) {
                XmlyBrowserComponent.this.runOnUiThread(new Runnable() {
                    public final void run() {
                        XmlyBrowserComponent.this.g();
                        XmlyBrowserComponent.this.j.a(99);
                    }
                });
                String str2 = "";
                String str3 = "";
                if (!TextUtils.isEmpty(str)) {
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        String optString = jSONObject.optString(XmlyConstants.n);
                        try {
                            str3 = jSONObject.optString("scope");
                            str2 = optString;
                        } catch (JSONException e) {
                            JSONException jSONException = e;
                            str2 = optString;
                            e = jSONException;
                            e.printStackTrace();
                            XmlyBrowserComponent.this.a(str2, str3);
                        }
                    } catch (JSONException e2) {
                        e = e2;
                        e.printStackTrace();
                        XmlyBrowserComponent.this.a(str2, str3);
                    }
                }
                XmlyBrowserComponent.this.a(str2, str3);
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(final String str, String str2) {
        if (mSimpleLogin != null) {
            mSimpleLogin.a(new HashMap<String, String>() {
                {
                    put("sso_code", str);
                    put("redirect_uri", XmlyBrowserComponent.this.n.b());
                }
            }, new IDataCallBack<String>() {
                public final /* synthetic */ void a(Object obj) {
                    XmlyBrowserComponent.this.b(h.a((String) obj));
                }

                private void a(String str) {
                    XmlyBrowserComponent.this.b(h.a(str));
                }

                public final void a(int i, String str) {
                    PrintStream printStream = System.out;
                    printStream.println("XmlyBrowserComponent.onError  " + i + "   " + str);
                }
            });
            return;
        }
        OkHttpClient build = new OkHttpClient().newBuilder().connectTimeout(15, TimeUnit.SECONDS).followRedirects(false).followSslRedirects(false).build();
        FormBody formBody = null;
        try {
            FormBody.Builder builder = new FormBody.Builder();
            if (!TextUtils.isEmpty(this.n.a())) {
                builder.add("client_id", this.n.a());
            }
            builder.add("response_type", "code");
            if (!TextUtils.isEmpty(this.n.b())) {
                builder.addEncoded("redirect_uri", URLEncoder.encode(this.n.b(), "UTF-8"));
            }
            if (!TextUtils.isEmpty(str)) {
                builder.add("sso_code", str);
            }
            if (!TextUtils.isEmpty(this.n.c())) {
                builder.add("device_id", this.n.c());
            }
            builder.add("client_os_type", "2");
            if (!TextUtils.isEmpty(this.n.d())) {
                builder.add("pack_id", this.n.d());
            }
            if (!TextUtils.isEmpty(this.n.e())) {
                builder.add("state", this.n.e());
            }
            if (!TextUtils.isEmpty(str2)) {
                builder.add("scope", str2);
            }
            formBody = builder.build();
        } catch (Exception e2) {
            e2.printStackTrace();
            str = "";
            str2 = "";
        }
        if (formBody != null) {
            String str3 = f1808a;
            Logger.a(str3, "authorize, request: method = post, url = https://api.ximalaya.com/oauth2/v2/authorize?, body:client_id = " + this.n.a() + ", response_type = code, redirect_uri = " + this.n.b() + ", sso_code = " + str + ", device_id = " + this.n.c() + ", client_os_type = " + this.n.f() + ", pack_id = " + this.n.d() + ", state = " + this.n.e() + ", scope = " + str2);
            build.newCall(new Request.Builder().url(d).post(formBody).build()).enqueue(new Callback() {
                public final void onFailure(Call call, IOException iOException) {
                    XmlyBrowserComponent.this.b(iOException.getMessage(), (String) null);
                    iOException.printStackTrace();
                    String access$1200 = XmlyBrowserComponent.f1808a;
                    Logger.a(access$1200, "authorize, request failed, error message = " + iOException.getMessage());
                }

                public final void onResponse(Call call, Response response) throws IOException {
                    int code = response.code();
                    String string = response.body().string();
                    String str = "";
                    if (code == 302) {
                        str = response.headers().get("Location");
                        if (!TextUtils.isEmpty(str)) {
                            if (TextUtils.isEmpty(XmlyBrowserComponent.this.o) || !XmlyBrowserComponent.this.o.equalsIgnoreCase("code")) {
                                XmlyBrowserComponent.this.c(str);
                            } else {
                                XmlyBrowserComponent.this.b(h.a(str));
                            }
                        }
                    } else {
                        XmlyBrowserComponent.this.b(String.valueOf(code), string);
                    }
                    String access$1200 = XmlyBrowserComponent.f1808a;
                    Logger.a(access$1200, "authorize, request success, status code = " + code + ", body = " + string + ", redirectUrl" + str);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void b(Bundle bundle) {
        if (this.p != null) {
            this.p.a(bundle);
        }
        closeBrowser(this, this.q);
    }

    /* access modifiers changed from: private */
    public void c(final String str) {
        String str2 = f1808a;
        Logger.a(str2, "redirectRequestAccessTokenInfo, request: method = get, url = " + str);
        new OkHttpClient().newBuilder().connectTimeout(15, TimeUnit.SECONDS).followRedirects(false).followSslRedirects(false).build().newCall(new Request.Builder().url(str).build()).enqueue(new Callback() {
            public final void onFailure(Call call, IOException iOException) {
                XmlyBrowserComponent.this.b(iOException.getMessage(), (String) null);
                iOException.printStackTrace();
                String access$1200 = XmlyBrowserComponent.f1808a;
                Logger.a(access$1200, "redirectRequestAccessTokenInfo, request failed, error message = " + iOException.getMessage());
            }

            public final void onResponse(Call call, Response response) throws IOException {
                int code = response.code();
                String string = response.body().string();
                if (!response.isSuccessful()) {
                    XmlyBrowserComponent.this.b(String.valueOf(code), string);
                } else if (!TextUtils.isEmpty(string)) {
                    try {
                        int optInt = new JSONObject(string).optInt("expires_in", 0);
                        XmlyAuth2AccessToken a2 = XmlyAuth2AccessToken.a(string);
                        if (a2 == null || !a2.a()) {
                            XmlyBrowserComponent.this.b(String.valueOf(code), string);
                        } else {
                            String access$1200 = XmlyBrowserComponent.f1808a;
                            Logger.a(access$1200, "Login Success! " + a2.toString());
                            AccessTokenKeeper.b(XmlyBrowserComponent.this);
                            AccessTokenKeeper.a(XmlyBrowserComponent.this, a2);
                            a2.a((long) optInt);
                            if (XmlyBrowserComponent.this.p != null) {
                                XmlyBrowserComponent.this.p.a(a2.b());
                            }
                            XmlyBrowserComponent.closeBrowser(XmlyBrowserComponent.this, XmlyBrowserComponent.this.q);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        XmlyBrowserComponent.this.b(e.getMessage(), (String) null);
                    }
                }
                String access$12002 = XmlyBrowserComponent.f1808a;
                Logger.a(access$12002, "redirectRequestAccessTokenInfo, request success, status code = " + code + ", body = " + string + ", redirectUrl" + str);
            }
        });
    }

    /* access modifiers changed from: private */
    public void b(String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            XmlyErrorInfo d2 = str2.indexOf(Operators.BLOCK_START_STR) >= 0 ? XmlyErrorInfo.d(str2) : null;
            if (d2 != null) {
                this.p.a((XmlyException) new XmlyAuthException(d2.a(), d2.b(), d2.c()));
            } else {
                this.p.a((XmlyException) new XmlyAuthException(str, str2, (String) null));
            }
        } else {
            this.p.a((XmlyException) new XmlyAuthException(str, str2, (String) null));
        }
        Logger.a(f1808a, "Failed to receive access token by Web");
        closeBrowser(this, this.q);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        CookieSyncManager.createInstance(this);
        CookieManager.getInstance().removeAllCookie();
        CookieSyncManager.getInstance().sync();
        super.onDestroy();
    }
}
