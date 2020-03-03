package com.mi.global.shop.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.mi.global.shop.R;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.model.cart.List;
import com.mi.global.shop.request.MiHttpHttpsRequestHelper;
import com.mi.global.shop.request.MiProtobufRequest;
import com.mi.global.shop.util.ConnectionHelper;
import com.mi.global.shop.webview.WebViewHelper;
import com.mi.global.shop.widget.BaseWebView;
import com.mi.global.shop.widget.CommonButton;
import com.mi.log.LogUtil;
import com.mi.multimonitor.Request;
import com.mi.util.MiToast;
import com.mi.util.RequestQueueUtil;
import com.squareup.wire.Wire;
import org.json.JSONObject;

public class ShoppingCartInsuranceActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5456a = "ShoppingCartInsuranceActivity";
    private View b;
    private CommonButton c;
    private CommonButton d;
    private String e;
    private String f;
    protected BaseWebView mWebView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        if (bundle != null) {
            LogUtil.b(f5456a, "onCreate, savedInstanceState:" + bundle.toString());
        }
        super.onCreate(bundle);
        try {
            setCustomContentView(R.layout.shop_cart_insurance_activity);
            setTitle(R.string.cart_title);
            Intent intent = getIntent();
            this.e = intent.getStringExtra("cart_insurance_goodsid");
            this.f = intent.getStringExtra("cart_insurance_parentid");
            if (TextUtils.isEmpty(this.f) || TextUtils.isEmpty(this.e)) {
                LogUtil.b(f5456a, "id is empty");
                a();
                return;
            }
            this.mCartView.setVisibility(4);
            this.b = findViewById(R.id.title_bar_home);
            this.b.setVisibility(0);
            this.b.setOnClickListener(this);
            b();
        } catch (Exception e2) {
            if (e2.getMessage() == null || !e2.getMessage().contains("MissingWebViewPackageException")) {
                MiToast.a((Context) this, (CharSequence) getResources().getString(R.string.loading_error), 0);
            } else {
                MiToast.a((Context) this, (CharSequence) getResources().getString(R.string.webview_tips_uploaing), 0);
            }
            finish();
        }
    }

    private void a() {
        MiToast.a((Context) this, R.string.shop_error_network, 0);
        setResult(0);
        finish();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        if (this.mWebView != null) {
            this.mWebView.reload();
        }
        super.onResume();
    }

    private void b() {
        this.c = (CommonButton) findViewById(R.id.insurance_confirm);
        this.d = (CommonButton) findViewById(R.id.insurance_cancel);
        this.c.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.mWebView = (BaseWebView) findViewById(R.id.cart_insurance_webview);
        WebViewHelper.a((WebView) this.mWebView);
        this.mWebView.setWebViewClient(new WebViewClient());
        this.mWebView.setWebChromeClient(new WebChromeClient());
        this.mWebView.loadUrl(ConnectionHelper.at());
    }

    public void onClick(View view) {
        try {
            if (view.getId() == R.id.title_bar_home) {
                onBackPressed();
            }
            if (view == this.d) {
                setResult(0);
                finish();
            } else if (view == this.c) {
                b(this.e, this.f);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        setResult(-1);
        finish();
    }

    private String[] a(String str, String str2) {
        String[] J = ConnectionHelper.J();
        String[] strArr = new String[J.length];
        for (int i = 0; i < J.length; i++) {
            strArr[i] = ConnectionHelper.a(J[i], str, "", str2, "");
        }
        return strArr;
    }

    private void d() {
        this.c.setEnabled(false);
        this.d.setEnabled(false);
    }

    /* access modifiers changed from: private */
    public void e() {
        this.c.setEnabled(true);
        this.d.setEnabled(true);
    }

    private void b(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            d();
            if (ShopApp.n()) {
                MiProtobufRequest miProtobufRequest = new MiProtobufRequest(0, ConnectionHelper.a(ConnectionHelper.K(), str, "", str2, ""), new Response.Listener<byte[]>() {
                    /* renamed from: a */
                    public void onResponse(byte[] bArr) {
                        try {
                            List decode = List.ADAPTER.decode(bArr);
                            if (((Integer) Wire.get(decode.errno, List.DEFAULT_ERRNO)).intValue() != 0) {
                                LogUtil.b(ShoppingCartInsuranceActivity.f5456a, "error msg:" + ((String) Wire.get(decode.errmsg, "")));
                                return;
                            }
                            ShoppingCartInsuranceActivity.this.c();
                            LogUtil.b(ShoppingCartInsuranceActivity.f5456a, "Add cart Process success");
                        } catch (Exception e) {
                            e.printStackTrace();
                            LogUtil.b(ShoppingCartInsuranceActivity.f5456a, "Exception:" + e.toString());
                            ShoppingCartInsuranceActivity.this.e();
                        }
                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError volleyError) {
                        ShopApp.o();
                        VolleyLog.d(ShoppingCartInsuranceActivity.f5456a, "Error: " + volleyError.getMessage());
                        ShoppingCartInsuranceActivity.this.e();
                    }
                });
                miProtobufRequest.setTag(f5456a);
                RequestQueueUtil.a().add(miProtobufRequest);
            } else {
                MiHttpHttpsRequestHelper miHttpHttpsRequestHelper = new MiHttpHttpsRequestHelper(0, ConnectionHelper.a(a(str, str2)), new Response.Listener<JSONObject>() {
                    /* renamed from: a */
                    public void onResponse(JSONObject jSONObject) {
                        try {
                            if (jSONObject.getInt(Request.RESULT_CODE_KEY) != 0) {
                                LogUtil.b(ShoppingCartInsuranceActivity.f5456a, "errno=" + Integer.toString(jSONObject.getInt(Request.RESULT_CODE_KEY)));
                                return;
                            }
                            try {
                                ShoppingCartInsuranceActivity.this.c();
                                LogUtil.b(ShoppingCartInsuranceActivity.f5456a, "Process success");
                            } catch (Exception e) {
                                LogUtil.b(ShoppingCartInsuranceActivity.f5456a, "JSON parse error");
                                e.printStackTrace();
                                ShoppingCartInsuranceActivity.this.e();
                            }
                        } catch (Exception e2) {
                            LogUtil.b(ShoppingCartInsuranceActivity.f5456a, "JSON parse error");
                            e2.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError volleyError) {
                        LogUtil.b(ShoppingCartInsuranceActivity.f5456a, "Error: " + volleyError.getMessage());
                        ShoppingCartInsuranceActivity.this.e();
                    }
                });
                miHttpHttpsRequestHelper.a((Object) f5456a);
                miHttpHttpsRequestHelper.a((RetryPolicy) new DefaultRetryPolicy(10000, 1, 1.0f));
                miHttpHttpsRequestHelper.a();
            }
            LogUtil.b(f5456a, "added task to request quene");
        }
    }
}
