package com.alipay.biometrics.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import com.alipay.android.phone.mobilecommon.a.a.a;

public class ProtocalDialog extends Dialog {
    private String cacelButtonText;
    /* access modifiers changed from: private */
    public ClickListenerInterface clickListenerInterface;
    private String confirmButtonText;
    private Context context;
    private boolean showCloseButton;
    private boolean showProtocol;
    private String subTitle;
    private String title;

    public interface ClickListenerInterface {
        void doClosed();
    }

    public ProtocalDialog(Context context2) {
        super(context2, a.e.bio_custom_dialog_style);
        this.context = context2;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().requestFeature(1);
        getWindow().addFlags(1024);
        hideBottomUIMenu();
        setContentView(LayoutInflater.from(this.context).inflate(a.d.bio_protocal_dialog, (ViewGroup) null));
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        getWindow().setLayout(-1, -1);
        WebView webView = (WebView) findViewById(a.c.title);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/html/agreement.html");
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                webView.loadUrl(str);
                return super.shouldOverrideUrlLoading(webView, str);
            }
        });
        ((Button) findViewById(a.c.btn_x)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ProtocalDialog.this.clickListenerInterface.doClosed();
            }
        });
    }

    public void setOnClickListener(ClickListenerInterface clickListenerInterface2) {
        this.clickListenerInterface = clickListenerInterface2;
    }

    /* access modifiers changed from: protected */
    public void hideBottomUIMenu() {
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) {
            getWindow().getDecorView().setSystemUiVisibility(8);
        } else if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(4102);
        }
    }
}
