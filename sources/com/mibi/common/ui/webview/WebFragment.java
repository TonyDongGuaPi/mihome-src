package com.mibi.common.ui.webview;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.mibi.common.R;
import com.mibi.common.base.BaseFragment;
import com.mibi.common.data.Client;
import com.mibi.common.data.CommonConstants;
import com.mibi.common.data.Utils;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.el.parse.Operators;
import miuipub.webkit.WebViewClient;

public class WebFragment extends BaseFragment {
    private static final String y = "userId";
    private static final int z = 1;
    private TextView A;
    private Button B;
    /* access modifiers changed from: private */
    public LinearLayout C;
    private boolean D;
    private boolean E;
    private final Handler F = new Handler() {
        public void handleMessage(Message message) {
            if (message.what == 1 && WebFragment.this.u != null) {
                WebFragment.this.u.setVisibility(0);
                WebFragment.this.C.setVisibility(8);
            }
        }
    };
    protected ViewGroup t;
    protected WebView u;
    protected String v;
    protected String w;
    protected boolean x;

    /* access modifiers changed from: protected */
    public void a(WebView webView, String str, String str2, String str3) {
    }

    /* access modifiers changed from: protected */
    public boolean a(WebView webView, String str) {
        return false;
    }

    public void a(Bundle bundle) {
        super.a(bundle);
        f(5);
    }

    public View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.mibi_webview, (ViewGroup) null);
        this.t = (ViewGroup) inflate.findViewById(R.id.web_container);
        this.u = (WebView) inflate.findViewById(R.id.webview);
        this.A = (TextView) inflate.findViewById(R.id.error);
        this.B = (Button) inflate.findViewById(R.id.button_retry);
        this.C = (LinearLayout) inflate.findViewById(R.id.network_error);
        return inflate;
    }

    public void c(Bundle bundle) {
        super.c(bundle);
        if (!TextUtils.isEmpty(this.w)) {
            a((CharSequence) this.w);
        }
        d("Mibi/" + Client.F().a() + " (MiuiDeepLink;)");
        d("lg/" + Client.a() + JSMethod.NOT_SET + Client.b());
        Q();
        this.B.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WebFragment.this.O();
            }
        });
        N();
    }

    /* access modifiers changed from: protected */
    public void b_(Bundle bundle) {
        super.b_(bundle);
        if (bundle != null) {
            String string = bundle.getString("webUrl");
            if (!TextUtils.isEmpty(string)) {
                this.v = string;
            }
            this.w = bundle.getString("webTitle");
        }
    }

    private void Q() {
        WebSettings settings = this.u.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setDefaultTextEncodingName("utf-8");
        if (CommonConstants.b) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        this.u.setWebViewClient(new WebViewClient(0, 2) {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (WebFragment.this.a(webView, str)) {
                    return true;
                }
                return super.shouldOverrideUrlLoading(webView, str);
            }

            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                super.onPageStarted(webView, str, bitmap);
                WebFragment.this.a(webView, str, bitmap);
            }

            public void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
                WebFragment.this.b(webView, str);
            }

            public void onReceivedLoginRequest(WebView webView, String str, String str2, String str3) {
                super.onReceivedLoginRequest(webView, str, str2, str3);
                WebFragment.this.a(webView, str, str2, str3);
            }

            public void onReceivedError(WebView webView, int i, String str, String str2) {
                super.onReceivedError(webView, i, str, str2);
                WebFragment.this.a(webView, i, str, str2);
            }
        });
        this.u.setWebChromeClient(new WebChromeClient() {
            public boolean onJsAlert(WebView webView, String str, String str2, final JsResult jsResult) {
                AlertDialog.Builder builder = new AlertDialog.Builder(WebFragment.this.getActivity());
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
                WebFragment.this.E();
            }
        });
        this.u.setLayerType(1, (Paint) null);
        this.u.requestFocus();
    }

    /* access modifiers changed from: protected */
    public void a(WebView webView, String str, Bitmap bitmap) {
        K();
    }

    /* access modifiers changed from: protected */
    public void b(WebView webView, String str) {
        if (!this.D) {
            if (this.E || this.x) {
                this.F.sendEmptyMessageDelayed(1, 200);
                this.E = false;
            }
            this.w = this.u.getTitle();
            a((CharSequence) this.w);
        }
        this.x = false;
        L();
    }

    /* access modifiers changed from: protected */
    public void K() {
        T().e(true);
    }

    /* access modifiers changed from: protected */
    public void L() {
        T().e(false);
    }

    /* access modifiers changed from: protected */
    public void a(WebView webView, int i, String str, String str2) {
        if (!Utils.a((Context) this.f7451a)) {
            b(getString(R.string.mibi_error_network_summary), true);
            return;
        }
        b(getString(R.string.mibi_error_web_summary) + Operators.ARRAY_START_STR + i + Operators.ARRAY_END_STR, false);
    }

    public boolean a(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return false;
        }
        E();
        return true;
    }

    public void y() {
        if (this.u.canGoBack()) {
            this.x = true;
            this.D = false;
            this.u.goBack();
            return;
        }
        super.y();
    }

    public void o() {
        this.u.setWebViewClient((android.webkit.WebViewClient) null);
        this.u.setWebChromeClient((WebChromeClient) null);
        this.u = null;
        super.o();
    }

    /* access modifiers changed from: protected */
    public boolean M() {
        return this.x;
    }

    /* access modifiers changed from: protected */
    public void N() {
        this.D = false;
        this.C.setVisibility(8);
        this.u.loadUrl(Uri.parse(this.v).buildUpon().appendQueryParameter("userId", this.b.f()).build().toString());
    }

    /* access modifiers changed from: protected */
    public void O() {
        this.D = false;
        this.E = true;
        this.C.setVisibility(8);
        this.u.reload();
    }

    /* access modifiers changed from: protected */
    public void b(String str, boolean z2) {
        this.D = true;
        this.A.setText(str);
        int i = 8;
        this.u.setVisibility(8);
        this.C.setVisibility(0);
        Button button = this.B;
        if (z2) {
            i = 0;
        }
        button.setVisibility(i);
    }

    /* access modifiers changed from: protected */
    public void d(String str) {
        if (!TextUtils.isEmpty(str)) {
            String userAgentString = this.u.getSettings().getUserAgentString();
            WebSettings settings = this.u.getSettings();
            settings.setUserAgentString(userAgentString.trim() + " " + str.trim());
        }
    }
}
