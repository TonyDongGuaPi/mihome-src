package com.ximalaya.ting.android.xmpayordersdk;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ximalaya.ting.android.opensdk.auth.utils.c;
import com.ximalaya.ting.android.opensdk.auth.utils.d;
import com.ximalaya.ting.android.opensdk.auth.utils.g;
import com.ximalaya.ting.android.opensdk.auth.view.LoadingBar;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.model.pay.PayOderStatue;
import com.ximalaya.ting.android.opensdk.util.SharedPreferencesUtil;
import com.ximalaya.ting.android.xmpayordersdk.PayOrderManager;
import java.util.ArrayList;
import java.util.HashMap;

public class XmPayOrderActivity extends Activity {
    public static final String CODE = "openPaySdkCode";
    public static final int COLOR_BROWSER_LOAD_ERROR_BACKGROUND = -657931;
    public static final int COLOR_BROWSER_LOAD_ERROR_RETRY = -6710887;
    public static final int COLOR_BROWSER_TITLE_BAR_DIVIDER = -1513240;
    public static final int COLOR_BROWSER_TITLE_BAR_LEFT_BUTTON_NORMAL = -498622;
    public static final int COLOR_BROWSER_TITLE_BAR_LEFT_BUTTON_PRESSED = -6710887;
    public static final int COLOR_BROWSER_TITLE_BAR_TITLE = -13421773;
    public static final String ORDER_NUM = "ORDER_NUM";
    public static final String ORDER_URL = "ORDER_URL";
    public static final String PARAMS_ERROR_DESC = "params_error_desc";
    public static final String PARMAS_ERROR_CODE = "params_error_code";
    public static final String PAY_ORDER_NO = "PAY_ORDER_NO";
    public static final String TAG = "XmPayOrderActivity";

    /* renamed from: a  reason: collision with root package name */
    private TextView f2389a;
    private Button b;
    private TextView c;
    private WebView d;
    /* access modifiers changed from: private */
    public LoadingBar e;
    private LinearLayout f;
    /* access modifiers changed from: private */
    public boolean g;
    /* access modifiers changed from: private */
    public boolean h;
    /* access modifiers changed from: private */
    public String i;
    /* access modifiers changed from: private */
    public String j = "";
    private String k;
    private String l;
    /* access modifiers changed from: private */
    public String m;
    /* access modifiers changed from: private */
    public IXmPayOrderListener n;

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        PayOrderManager.b = this;
        if (PayOrderManager.c != null) {
            PayOrderManager.a aVar = PayOrderManager.c;
        }
        requestWindowFeature(1);
        this.j = getIntent().getStringExtra(ORDER_URL);
        this.l = getIntent().getStringExtra(ORDER_NUM);
        this.m = getIntent().getStringExtra(PAY_ORDER_NO);
        ArrayList<String> k2 = SharedPreferencesUtil.a(this).k(PAY_ORDER_NO);
        if (k2 == null) {
            k2 = new ArrayList<>();
        }
        k2.add(this.m);
        SharedPreferencesUtil.a(this).a(DTransferConstants.aU, k2);
        this.n = XmPayOrderCallBackManager.a().a(this.l);
        if (TextUtils.isEmpty(this.j) || TextUtils.isEmpty(this.l) || this.n == null) {
            finish();
            return;
        }
        this.k = "确认订单";
        a();
        e();
        a(this.j);
    }

    private void a() {
        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        relativeLayout.setBackgroundColor(-1);
        LinearLayout linearLayout = new LinearLayout(this);
        int d2 = d();
        linearLayout.setId(d2);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        linearLayout.setOrientation(1);
        RelativeLayout relativeLayout2 = new RelativeLayout(this);
        relativeLayout2.setLayoutParams(new LinearLayout.LayoutParams(-1, d.a((Context) this, 50)));
        this.f2389a = new TextView(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(9);
        layoutParams.addRule(15);
        layoutParams.leftMargin = d.a((Context) this, 10);
        layoutParams.rightMargin = d.a((Context) this, 10);
        this.f2389a.setLayoutParams(layoutParams);
        this.f2389a.setClickable(true);
        this.f2389a.setText("关闭");
        this.f2389a.setTextSize(2, 17.0f);
        this.f2389a.setTextColor(d.a());
        this.c = new TextView(this);
        this.c.setTextSize(2, 18.0f);
        this.c.setTextColor(-13421773);
        this.c.setEllipsize(TextUtils.TruncateAt.END);
        this.c.setSingleLine(true);
        this.c.setGravity(17);
        this.c.setMaxWidth(d.a((Context) this, 160));
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(13);
        this.c.setLayoutParams(layoutParams2);
        View view = new View(this);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, d.a((Context) this, 1));
        layoutParams3.addRule(12);
        view.setLayoutParams(layoutParams3);
        view.setBackgroundColor(-1513240);
        relativeLayout2.addView(this.f2389a);
        relativeLayout2.addView(this.c);
        relativeLayout2.addView(view);
        this.e = new LoadingBar(this);
        this.e.setBackgroundColor(0);
        this.e.a(0);
        this.e.setLayoutParams(new LinearLayout.LayoutParams(-1, d.a((Context) this, 3)));
        linearLayout.addView(relativeLayout2);
        linearLayout.addView(this.e);
        this.d = new WebView(this);
        this.d.setBackgroundColor(-1);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams4.addRule(3, d2);
        this.d.setLayoutParams(layoutParams4);
        this.f = new LinearLayout(this);
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams5.addRule(3, d2);
        this.f.setLayoutParams(layoutParams5);
        this.f.setVisibility(8);
        this.f.setGravity(17);
        this.f.setOrientation(1);
        this.f.setBackgroundColor(-657931);
        ImageView imageView = new ImageView(this);
        imageView.setImageDrawable(d.a((Context) this, "xmly_auth_sdk_empty_failed.png"));
        LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(-2, -2);
        int a2 = d.a((Context) this, 8);
        layoutParams6.bottomMargin = a2;
        layoutParams6.rightMargin = a2;
        layoutParams6.topMargin = a2;
        layoutParams6.leftMargin = a2;
        imageView.setLayoutParams(layoutParams6);
        this.f.addView(imageView);
        this.b = new Button(this);
        this.b.setGravity(17);
        this.b.setTextColor(-6710887);
        this.b.setTextSize(2, 16.0f);
        this.b.setText("重新加载");
        this.b.setBackgroundDrawable(d.a((Context) this, "xmly_auth_sdk_common_button_alpha.9.png", "xmly_auth_sdk_common_button_alpha_highlighted.9.png"));
        LinearLayout.LayoutParams layoutParams7 = new LinearLayout.LayoutParams(d.a((Context) this, 142), d.a((Context) this, 46));
        layoutParams7.topMargin = d.a((Context) this, 10);
        this.b.setLayoutParams(layoutParams7);
        this.b.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                XmPayOrderActivity.this.a(XmPayOrderActivity.this.j);
                boolean unused = XmPayOrderActivity.this.g = false;
            }
        });
        this.f.addView(this.b);
        relativeLayout.addView(linearLayout);
        relativeLayout.addView(this.d);
        relativeLayout.addView(this.f);
        setContentView(relativeLayout);
        b();
        if (c.b(this)) {
            this.d.setVisibility(0);
            this.f.setVisibility(8);
            return;
        }
        this.d.setVisibility(8);
        this.f.setVisibility(0);
    }

    private void b() {
        this.c.setText(this.k);
        this.f2389a.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                XmPayOrderActivity.this.c();
            }
        });
    }

    /* access modifiers changed from: private */
    public void c() {
        new AlertDialog.Builder(this).setMessage("确定取消购买?").setNegativeButton("确定", new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                if (XmPayOrderActivity.this.n != null) {
                    XmPayOrderActivity.this.n.a(PayFinishModel.b(2));
                }
                HashMap hashMap = new HashMap();
                hashMap.put("xima_order_no", XmPayOrderActivity.this.m);
                CommonRequest.aZ(hashMap, new IDataCallBack<PayOderStatue>() {
                    public final void a(int i, String str) {
                    }

                    private void a() {
                        PayOrderManager.a(XmPayOrderActivity.this, XmPayOrderActivity.this.m);
                    }

                    public final /* synthetic */ void a(@Nullable Object obj) {
                        PayOrderManager.a(XmPayOrderActivity.this, XmPayOrderActivity.this.m);
                    }
                });
                XmPayOrderActivity.this.finish();
            }
        }).setNeutralButton("取消", (DialogInterface.OnClickListener) null).create().show();
    }

    private int d() {
        if (Build.VERSION.SDK_INT < 17) {
            return g.a();
        }
        return View.generateViewId();
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void e() {
        this.d.getSettings().setJavaScriptEnabled(true);
        this.d.getSettings().setSavePassword(false);
        this.d.setWebViewClient(new a(this, (byte) 0));
        this.d.setWebChromeClient(new b(this, (byte) 0));
        this.d.requestFocus();
        this.d.setScrollBarStyle(0);
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        this.d.loadUrl(str);
    }

    private class a extends WebViewClient {
        private a() {
        }

        /* synthetic */ a(XmPayOrderActivity xmPayOrderActivity, byte b) {
            this();
        }

        public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
            int i;
            if (!TextUtils.isEmpty(str)) {
                Uri parse = Uri.parse(str);
                if (parse.getQueryParameterNames().contains(XmPayOrderActivity.CODE)) {
                    try {
                        i = Integer.valueOf(parse.getQueryParameter(XmPayOrderActivity.CODE)).intValue();
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        i = 500;
                    }
                    if (i == 200) {
                        PayOrderManager.a(XmPayOrderActivity.this, XmPayOrderActivity.this.m);
                    }
                    XmPayOrderActivity.this.n.a(PayFinishModel.b(i));
                    XmPayOrderActivity.this.finish();
                    return false;
                }
                try {
                    if (str.startsWith("alipays://platformapi")) {
                        Intent intent = new Intent("android.intent.action.VIEW");
                        intent.setData(Uri.parse(str));
                        XmPayOrderActivity.this.startActivity(intent);
                        return true;
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return true;
                }
            }
            return super.shouldOverrideUrlLoading(webView, str);
        }

        public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            String unused = XmPayOrderActivity.this.j = str;
            if (!XmPayOrderActivity.this.b(str)) {
                String unused2 = XmPayOrderActivity.this.i = "";
            }
        }

        public final void onPageFinished(WebView webView, String str) {
            if (XmPayOrderActivity.this.g) {
                XmPayOrderActivity.this.f();
            } else {
                boolean unused = XmPayOrderActivity.this.g = false;
                XmPayOrderActivity.this.g();
            }
            super.onPageFinished(webView, str);
        }

        public final void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            XmPayOrderActivity.this.a(webView, i, str, str2);
        }
    }

    private class b extends WebChromeClient {
        /* synthetic */ b(XmPayOrderActivity xmPayOrderActivity, byte b) {
            this();
        }

        private b() {
        }

        public final void onProgressChanged(WebView webView, int i) {
            XmPayOrderActivity.this.e.setVisibility(0);
            XmPayOrderActivity.this.e.a(i * 100);
            if (i == 100) {
                boolean unused = XmPayOrderActivity.this.h = false;
                XmPayOrderActivity.this.refreshAllViews();
            } else if (!XmPayOrderActivity.this.h) {
                boolean unused2 = XmPayOrderActivity.this.h = true;
                XmPayOrderActivity.this.refreshAllViews();
            }
        }

        public final void onReceivedTitle(WebView webView, String str) {
            if (!XmPayOrderActivity.this.b(XmPayOrderActivity.this.j)) {
                String unused = XmPayOrderActivity.this.i = str;
                XmPayOrderActivity.this.j();
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
        if (this.h) {
            h();
        } else {
            i();
        }
    }

    /* access modifiers changed from: private */
    public void a(WebView webView, int i2, String str, String str2) {
        if (!str2.startsWith("xmly")) {
            this.g = true;
            f();
        }
    }

    /* access modifiers changed from: private */
    public void f() {
        this.f.setVisibility(0);
        this.d.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void g() {
        this.f.setVisibility(8);
        this.d.setVisibility(0);
    }

    private void h() {
        this.c.setText("加载中....");
        this.e.setVisibility(0);
    }

    private void i() {
        j();
        this.e.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void j() {
        String str = "";
        if (!TextUtils.isEmpty(this.i)) {
            str = this.i;
        } else if (!TextUtils.isEmpty(this.k)) {
            str = this.k;
        }
        this.c.setText(str);
    }

    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        if (i2 != 4) {
            return super.onKeyUp(i2, keyEvent);
        }
        if (this.d == null || !this.d.canGoBack()) {
            c();
            return true;
        }
        this.d.goBack();
        return true;
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        PayOrderManager.b = null;
        if (PayOrderManager.c != null) {
            PayOrderManager.a aVar = PayOrderManager.c;
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (PayOrderManager.c != null) {
            PayOrderManager.a aVar = PayOrderManager.c;
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (PayOrderManager.c != null) {
            PayOrderManager.a aVar = PayOrderManager.c;
        }
    }

    /* access modifiers changed from: protected */
    public void onRestart() {
        super.onRestart();
        if (PayOrderManager.c != null) {
            PayOrderManager.a aVar = PayOrderManager.c;
        }
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        if (PayOrderManager.c != null) {
            PayOrderManager.a aVar = PayOrderManager.c;
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        if (PayOrderManager.c != null) {
            PayOrderManager.a aVar = PayOrderManager.c;
        }
    }
}
