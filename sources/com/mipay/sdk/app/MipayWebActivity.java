package com.mipay.sdk.app;

import android.accounts.Account;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.mipay.sdk.IMipayAccountProvider;
import com.xiaomi.jr.web.utils.UserAgentUtils;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class MipayWebActivity extends MipayHybridActivity {
    public static final int REQUEST_CODE_SELECT_IMAGE = 100000;

    /* renamed from: a  reason: collision with root package name */
    private static final String f8156a = "MipayWebActivity";
    private static final String b = "Mipay";
    private static final String c = "2.1";
    private static final int d = 500;
    /* access modifiers changed from: private */
    public a e = a.LOAD_ING;
    /* access modifiers changed from: private */
    public a f;
    private Intent g;
    private LinearLayout h;
    /* access modifiers changed from: private */
    public WebView i;
    private ProgressBar j;
    private TextView k;
    /* access modifiers changed from: private */
    public final AccountManagerCallback<Bundle> l = new AccountManagerCallback<Bundle>() {
        public void run(AccountManagerFuture<Bundle> accountManagerFuture) {
            try {
                String string = accountManagerFuture.getResult().getString("authtoken");
                if (!TextUtils.isEmpty(string)) {
                    MipayWebActivity.this.i.loadUrl(string);
                }
            } catch (Exception e) {
                Log.e(MipayWebActivity.f8156a, "load stsUrl failed", e);
            }
        }
    };

    public class MipayInterface {
        public MipayInterface() {
        }

        @JavascriptInterface
        public void finish() {
            MipayWebActivity.this.finish();
        }

        @JavascriptInterface
        public void notify(String str) {
            MipayWebActivity.this.b(str);
            MipayWebActivity.this.finish();
        }

        @JavascriptInterface
        public void setResult(String str) {
            MipayWebActivity.this.b(str);
        }
    }

    private enum a {
        LOAD_ING,
        LOGIN_ING,
        LOGIN_FINISHING,
        LOGIN_FINISHED,
        LOAD_FINISHED
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
                Log.d(f8156a, "result is not json");
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
        String str;
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
                    str = obj.toString();
                } else if (obj instanceof Boolean) {
                    bundle.putBoolean(next, ((Boolean) obj).booleanValue());
                } else if (obj instanceof String) {
                    str = (String) obj;
                }
                bundle.putString(next, str);
            }
            return bundle;
        } catch (JSONException e2) {
            Log.e(f8156a, "convertJson2Map failed", e2);
            return null;
        }
    }

    private void a() {
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        FrameLayout frameLayout = new FrameLayout(this);
        this.i = new WebView(this);
        frameLayout.addView(this.i, new FrameLayout.LayoutParams(-1, -1));
        this.h = new LinearLayout(this);
        this.h.setBackgroundColor(Constants.BACKGROUND_COLOR);
        this.h.setOrientation(1);
        this.h.setGravity(17);
        this.k = new TextView(this);
        this.k.setTextSize((float) Constants.INFO_TEXT_SIZE);
        this.k.setTextColor(Constants.INFO_TEXT_COLOR);
        this.k.setText(Constants.getString(Constants.ID_INFO_TEXT));
        this.j = new ProgressBar(this);
        this.h.addView(this.j, new LinearLayout.LayoutParams(-2, -2));
        this.h.addView(this.k, new LinearLayout.LayoutParams(-2, -2));
        frameLayout.addView(this.h, new FrameLayout.LayoutParams(-1, -1));
        setContentView(frameLayout, new ViewGroup.LayoutParams(-1, -1));
    }

    private void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            String userAgentString = this.i.getSettings().getUserAgentString();
            WebSettings settings = this.i.getSettings();
            settings.setUserAgentString(userAgentString.trim() + " " + str.trim());
        }
    }

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    private void b() {
        this.i.getSettings().setJavaScriptEnabled(true);
        this.i.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        this.i.addJavascriptInterface(new MipayInterface(), b);
        a(UserAgentUtils.f11081a);
        a("MipaySdk/2.1");
        this.i.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView webView, String str) {
                if (MipayWebActivity.this.e == a.LOGIN_FINISHING) {
                    a unused = MipayWebActivity.this.e = a.LOGIN_FINISHED;
                    MipayWebActivity.this.i.postDelayed(new Runnable() {
                        public void run() {
                            MipayWebActivity.this.d();
                        }
                    }, 500);
                } else if (MipayWebActivity.this.e == a.LOAD_ING) {
                    MipayWebActivity.this.d();
                    a unused2 = MipayWebActivity.this.e = a.LOAD_FINISHED;
                }
            }

            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                if (MipayWebActivity.this.e == a.LOAD_ING) {
                    MipayWebActivity.this.c();
                } else if (MipayWebActivity.this.e == a.LOGIN_ING) {
                    a unused = MipayWebActivity.this.e = a.LOGIN_FINISHING;
                }
            }

            public void onReceivedLoginRequest(WebView webView, String str, String str2, String str3) {
                IMipayAccountProvider iMipayAccountProvider;
                if (TextUtils.equals(str, "com.xiaomi") && (iMipayAccountProvider = AccountProviderHolder.get()) != null) {
                    Account account = null;
                    Account[] accountsByType = iMipayAccountProvider.getAccountsByType("com.xiaomi");
                    if (accountsByType.length != 0) {
                        account = accountsByType[0];
                    }
                    Account account2 = account;
                    if (account2 != null) {
                        a unused = MipayWebActivity.this.e = a.LOGIN_ING;
                        MipayWebActivity.this.c();
                        iMipayAccountProvider.getAuthToken(account2, "weblogin:" + str3, (Bundle) null, true, (AccountManagerCallback<Bundle>) MipayWebActivity.this.l, (Handler) null);
                    }
                }
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (!UrlResolver.a(str)) {
                    return false;
                }
                Context context = webView.getContext();
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                intent.addCategory("android.intent.category.BROWSABLE");
                ResolveInfo a2 = UrlResolver.a(context, intent);
                if (a2 == null || a2.activityInfo == null) {
                    return false;
                }
                context.startActivity(intent);
                return true;
            }
        });
        this.i.setWebChromeClient(new WebChromeClient() {
            public boolean onJsAlert(WebView webView, String str, String str2, final JsResult jsResult) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MipayWebActivity.this, 3);
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

            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
                a unused = MipayWebActivity.this.f = new a(MipayWebActivity.this);
                MipayWebActivity.this.f.a(valueCallback, fileChooserParams);
                return true;
            }
        });
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0045  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(java.lang.String r6) {
        /*
            r5 = this;
            java.lang.String r0 = "user canceled"
            r1 = 2
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0027 }
            r2.<init>(r6)     // Catch:{ JSONException -> 0x0027 }
            java.lang.String r6 = "code"
            int r6 = r2.getInt(r6)     // Catch:{ JSONException -> 0x0027 }
            java.lang.String r1 = "message"
            java.lang.String r1 = r2.optString(r1)     // Catch:{ JSONException -> 0x0021 }
            java.lang.String r0 = "result"
            java.lang.String r0 = r2.optString(r0)     // Catch:{ JSONException -> 0x001c }
            goto L_0x0036
        L_0x001c:
            r0 = move-exception
            r4 = r0
            r0 = r6
            r6 = r4
            goto L_0x002a
        L_0x0021:
            r1 = move-exception
            r4 = r0
            r0 = r6
            r6 = r1
            r1 = r4
            goto L_0x002a
        L_0x0027:
            r6 = move-exception
            r1 = r0
            r0 = 2
        L_0x002a:
            java.lang.String r2 = "MipayWebActivity"
            java.lang.String r3 = "setResult failed"
            android.util.Log.e(r2, r3, r6)
            r6 = 0
            r4 = r0
            r0 = r6
            r6 = r4
        L_0x0036:
            android.content.Intent r0 = r5.a(r6, r1, r0)
            r5.g = r0
            if (r6 != 0) goto L_0x0045
            r6 = -1
        L_0x003f:
            android.content.Intent r0 = r5.g
            r5.setResult(r6, r0)
            goto L_0x0047
        L_0x0045:
            r6 = 0
            goto L_0x003f
        L_0x0047:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mipay.sdk.app.MipayWebActivity.b(java.lang.String):void");
    }

    /* access modifiers changed from: private */
    public void c() {
        this.h.setVisibility(0);
    }

    /* access modifiers changed from: private */
    public void d() {
        this.h.setVisibility(8);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i2 == 100000) {
            this.f.a(i3, intent);
        }
    }

    public void onBackPressed() {
        if (this.g == null) {
            setResult(0, a(2, "user canceled", (String) null));
        }
        finish();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a();
        b();
        a(this.i);
        String stringExtra = getIntent().getStringExtra(Constants.KEY_URL);
        if (!TextUtils.isEmpty(stringExtra)) {
            this.i.loadUrl(stringExtra);
            return;
        }
        setResult(0, a(7, "url is empty", (String) null));
        finish();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.f != null) {
            this.f = null;
        }
        this.i.removeAllViews();
        this.i.destroy();
    }
}
