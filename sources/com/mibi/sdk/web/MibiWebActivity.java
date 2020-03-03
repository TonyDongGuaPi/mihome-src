package com.mibi.sdk.web;

import android.accounts.Account;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.mibi.sdk.IMibiAccountProvider;
import com.mibi.sdk.account.AccountProviderHolder;
import com.mibi.sdk.common.Utils;
import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.jr.web.utils.UserAgentUtils;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class MibiWebActivity extends Activity {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7603a = "MibiWebActivity";
    private static final String b = "MIBIPay";
    private static final String c = "0.2";
    private static final int d = 500;
    private static int g = Color.parseColor("#f0f0f0");
    private static int h = Color.parseColor("#bababa");
    private static int i = 11;
    private final String e = "webUrl";
    private final String f = "com.xiaomi";
    /* access modifiers changed from: private */
    public LoginState j = LoginState.LOAD_ING;
    /* access modifiers changed from: private */
    public boolean k;
    private Intent l;
    private LinearLayout m;
    /* access modifiers changed from: private */
    public WebView n;
    private TextView o;
    /* access modifiers changed from: private */
    public final AccountManagerCallback<Bundle> p = new AccountManagerCallback<Bundle>() {
        public void run(AccountManagerFuture<Bundle> accountManagerFuture) {
            try {
                String string = accountManagerFuture.getResult().getString("authtoken");
                if (!TextUtils.isEmpty(string)) {
                    MibiWebActivity.this.n.loadUrl(string);
                }
            } catch (Exception e) {
                Log.e(MibiWebActivity.f7603a, "load stsUrl failed", e);
            }
        }
    };

    private enum LoginState {
        LOAD_ING,
        LOGIN_ING,
        LOGIN_FINISHING,
        LOGIN_FINISHED,
        LOAD_FINISHED
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a();
        b();
        Intent intent = getIntent();
        if (intent != null) {
            String stringExtra = intent.getStringExtra("webUrl");
            if (!TextUtils.isEmpty(stringExtra)) {
                this.n.loadUrl(stringExtra);
                return;
            }
            setResult(2, a(2, "url is empty", (String) null));
            finish();
        }
    }

    private void a() {
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.setBackgroundColor(g);
        this.n = new WebView(this);
        frameLayout.addView(this.n, new FrameLayout.LayoutParams(-1, -1));
        this.m = new LinearLayout(this);
        this.m.setOrientation(1);
        this.m.setGravity(17);
        TextView textView = new TextView(this);
        textView.setTextSize((float) i);
        textView.setTextColor(h);
        textView.setText("Loading...");
        this.m.addView(new ProgressBar(this), new LinearLayout.LayoutParams(-2, -2));
        this.m.addView(textView, new LinearLayout.LayoutParams(-2, -2));
        frameLayout.addView(this.m, new FrameLayout.LayoutParams(-1, -1));
        setContentView(frameLayout, new ViewGroup.LayoutParams(-1, -1));
    }

    /* access modifiers changed from: protected */
    public void appendUA(String str) {
        if (!TextUtils.isEmpty(str)) {
            String userAgentString = this.n.getSettings().getUserAgentString();
            WebSettings settings = this.n.getSettings();
            settings.setUserAgentString(userAgentString.trim() + " " + str.trim());
        }
    }

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    private void b() {
        if (Utils.f7602a) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        WebSettings settings = this.n.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setDefaultTextEncodingName("utf-8");
        this.n.addJavascriptInterface(new MibiInterface(), b);
        appendUA(UserAgentUtils.f11081a);
        appendUA("MibiSdk/0.2");
        appendUA("lg/" + Utils.a() + JSMethod.NOT_SET + Utils.b());
        this.n.setWebViewClient(new WebViewClient() {
            public void onReceivedLoginRequest(WebView webView, String str, String str2, String str3) {
                if (!TextUtils.equals(str, "com.xiaomi")) {
                    return;
                }
                if (MibiWebActivity.this.k) {
                    MibiWebActivity.this.finish();
                    return;
                }
                IMibiAccountProvider a2 = AccountProviderHolder.a();
                if (a2 != null) {
                    LoginState unused = MibiWebActivity.this.j = LoginState.LOGIN_ING;
                    Account[] a3 = a2.a(str);
                    if (a3.length != 0) {
                        MibiWebActivity.this.c();
                        a2.a(a3[0], "weblogin:" + str3, (Bundle) null, (Activity) MibiWebActivity.this, (AccountManagerCallback<Bundle>) MibiWebActivity.this.p, (Handler) null);
                    }
                }
            }

            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                if (MibiWebActivity.this.j == LoginState.LOAD_ING) {
                    MibiWebActivity.this.c();
                } else if (MibiWebActivity.this.j == LoginState.LOGIN_ING) {
                    LoginState unused = MibiWebActivity.this.j = LoginState.LOGIN_FINISHING;
                }
            }

            public void onPageFinished(WebView webView, String str) {
                if (MibiWebActivity.this.j == LoginState.LOGIN_FINISHING) {
                    LoginState unused = MibiWebActivity.this.j = LoginState.LOGIN_FINISHED;
                    MibiWebActivity.this.n.postDelayed(new Runnable() {
                        public void run() {
                            MibiWebActivity.this.d();
                        }
                    }, 500);
                } else if (MibiWebActivity.this.j == LoginState.LOAD_ING) {
                    MibiWebActivity.this.d();
                    LoginState unused2 = MibiWebActivity.this.j = LoginState.LOAD_FINISHED;
                    boolean unused3 = MibiWebActivity.this.k = false;
                }
            }
        });
        this.n.setWebChromeClient(new WebChromeClient() {
            public boolean onJsAlert(WebView webView, String str, String str2, final JsResult jsResult) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MibiWebActivity.this);
                builder.setMessage(str2);
                builder.setPositiveButton(17039370, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        jsResult.confirm();
                    }
                });
                builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialogInterface) {
                        jsResult.cancel();
                    }
                });
                builder.show();
                return true;
            }

            public void onCloseWindow(WebView webView) {
                super.onCloseWindow(webView);
                MibiWebActivity.this.finish();
            }
        });
    }

    public class MibiInterface {
        public MibiInterface() {
        }

        @JavascriptInterface
        public void setResult(String str) {
            MibiWebActivity.this.a(str);
        }

        @JavascriptInterface
        public void finish() {
            MibiWebActivity.this.finish();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        this.n.removeAllViews();
        this.n.destroy();
    }

    public void onBackPressed() {
        if (this.l == null) {
            this.l = a(0, "user canceled", (String) null);
            setResult(0, this.l);
        }
        if (this.n.canGoBack()) {
            this.n.goBack();
            this.k = true;
            return;
        }
        super.onBackPressed();
        AccountProviderHolder.a((IMibiAccountProvider) null);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0036  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r7) {
        /*
            r6 = this;
            java.lang.String r0 = "user canceled"
            r1 = 0
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0026 }
            r2.<init>(r7)     // Catch:{ JSONException -> 0x0026 }
            java.lang.String r7 = "code"
            int r7 = r2.getInt(r7)     // Catch:{ JSONException -> 0x0026 }
            java.lang.String r3 = "message"
            java.lang.String r3 = r2.optString(r3)     // Catch:{ JSONException -> 0x0021 }
            java.lang.String r0 = "result"
            java.lang.String r0 = r2.optString(r0)     // Catch:{ JSONException -> 0x001c }
            goto L_0x0034
        L_0x001c:
            r0 = move-exception
            r5 = r0
            r0 = r7
            r7 = r5
            goto L_0x0029
        L_0x0021:
            r2 = move-exception
            r3 = r0
            r0 = r7
            r7 = r2
            goto L_0x0029
        L_0x0026:
            r7 = move-exception
            r3 = r0
            r0 = 0
        L_0x0029:
            java.lang.String r2 = "MibiWebActivity"
            java.lang.String r4 = "setResult failed"
            android.util.Log.e(r2, r4, r7)
            r7 = 0
            r5 = r0
            r0 = r7
            r7 = r5
        L_0x0034:
            if (r7 != 0) goto L_0x0037
            r1 = -1
        L_0x0037:
            android.content.Intent r7 = r6.a(r1, r3, r0)
            r6.l = r7
            android.content.Intent r7 = r6.l
            r6.setResult(r1, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mibi.sdk.web.MibiWebActivity.a(java.lang.String):void");
    }

    private Intent a(int i2, String str, String str2) {
        Intent intent = new Intent();
        intent.putExtra("code", i2);
        if (!TextUtils.isEmpty(str)) {
            intent.putExtra("message", str);
        }
        if (!TextUtils.isEmpty(str2)) {
            try {
                intent.putExtra("result", a(new JSONObject(str2)));
            } catch (JSONException unused) {
                Log.d(f7603a, "result is not json");
                intent.putExtra("result", str2);
            }
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("code", i2);
            if (!TextUtils.isEmpty(str)) {
                jSONObject.put("message", str);
            }
            if (!TextUtils.isEmpty(str2)) {
                jSONObject.put("result", str2);
            }
        } catch (JSONException unused2) {
        }
        intent.putExtra("fullResult", jSONObject.toString());
        return intent;
    }

    private Bundle a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        try {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                Object obj = jSONObject.get(next);
                if (obj instanceof Integer) {
                    bundle.putString(next, obj.toString());
                } else if (obj instanceof Boolean) {
                    bundle.putBoolean(next, ((Boolean) obj).booleanValue());
                } else if (obj instanceof String) {
                    bundle.putString(next, (String) obj);
                }
            }
            return bundle;
        } catch (JSONException e2) {
            Log.e(f7603a, "convertJson2Map failed", e2);
            return null;
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        this.m.setVisibility(0);
    }

    /* access modifiers changed from: private */
    public void d() {
        this.m.setVisibility(8);
    }
}
