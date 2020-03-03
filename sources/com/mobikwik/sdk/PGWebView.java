package com.mobikwik.sdk;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.mobikwik.sdk.lib.Constants;
import com.mobikwik.sdk.lib.SDKErrorCodes;
import com.mobikwik.sdk.lib.payapi.PaymentAPI;
import com.mobikwik.sdk.lib.utils.Utils;
import com.mobikwik.sdk.ui.data.b;
import com.xiaomi.zxing.integration.android.IntentIntegrator;
import java.util.HashMap;

public class PGWebView extends Activity implements PaymentAPI.Callback {

    /* renamed from: a  reason: collision with root package name */
    final Activity f8355a = this;
    public String b;
    WebView c;
    PaymentAPI d;
    String e;
    String f;
    boolean g;
    /* access modifiers changed from: private */
    public ProgressDialog h;
    /* access modifiers changed from: private */
    public boolean i = false;
    /* access modifiers changed from: private */
    public Runnable j;

    class a {
        private Context b;

        a(Context context) {
            this.b = context;
        }

        @JavascriptInterface
        public void checkStatusForMK(String str) {
            PGWebView.this.b = str;
        }

        @JavascriptInterface
        public void checkStatusForMerchant(String str, String str2, String str3, String str4) {
            String str5;
            PGWebView pGWebView;
            Utils.print("PGWebView.MyJavaScriptInterface.checkStatusForMerchant() " + str + " " + str3);
            Double valueOf = Double.valueOf(Double.parseDouble(str2));
            Double valueOf2 = Double.valueOf(Double.parseDouble(PGWebView.this.e));
            if (!"0".equals(str) || !valueOf.equals(valueOf2)) {
                pGWebView = PGWebView.this;
                str5 = Constants.FAILURE;
            } else {
                pGWebView = PGWebView.this;
                str5 = "SUCCESS";
            }
            pGWebView.b = str5;
            Intent intent = new Intent(PGWebView.this.getIntent());
            intent.setClass(PGWebView.this.getBaseContext(), MobikwikSDK.class);
            intent.putExtra(Constants.PAYMENT_STATUS_MSG, str4);
            if (PGWebView.this.b.trim().equalsIgnoreCase("SUCCESS")) {
                intent.putExtra(Constants.TXN_STATUS, "SUCCESS");
                PGWebView pGWebView2 = PGWebView.this;
                pGWebView2.a("Payment Successful!", "Payment Done of Rs. " + str2, intent);
                return;
            }
            intent.putExtra(Constants.TXN_STATUS, Constants.FAILURE);
            PGWebView pGWebView3 = PGWebView.this;
            pGWebView3.a("Payment Failed", "Error is: " + str4, intent);
        }

        @JavascriptInterface
        public void showResponse(String str) {
            Intent intent = new Intent();
            if (PGWebView.this.b.trim().equalsIgnoreCase("SUCCESS")) {
                intent.putExtra(Constants.SENDER_TO_WALLET, PGWebView.this.getIntent().getIntExtra(Constants.SENDER_TO_WALLET, 0));
                intent.putExtra(Constants.STATUS_CODE, "0");
                intent.putExtra(Constants.STATUS_MSG, "Payment Successful");
                intent.putExtra(Constants.WALLET_BALANCE, str);
                PGWebView.this.a(intent, "0", (String) null);
                return;
            }
            PGWebView.this.a((Intent) null, "1", str);
        }
    }

    private void a() {
        WebView webView = this.c;
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new a(this), "ResponseViewer");
        if (b.b((Context) this).f().isAllowMixedContent() && Build.VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(0);
        }
        webView.setWebChromeClient(new d(this));
        webView.setWebViewClient(new e(this, webView));
    }

    /* access modifiers changed from: private */
    public void a(Intent intent, String str, String str2) {
        runOnUiThread(new i(this, intent, str, str2));
    }

    private void a(String str, String str2) {
        this.c.requestFocus(130);
        this.c.postUrl(str, str2.getBytes());
    }

    /* access modifiers changed from: private */
    public void a(String str, String str2, Intent intent) {
        if (this.h != null && this.h.isShowing()) {
            this.h.dismiss();
        }
        a((Intent) null, "SUCCESS".equalsIgnoreCase(intent.getStringExtra(Constants.TXN_STATUS)) ? "0" : "1", intent.getStringExtra(Constants.PAYMENT_STATUS_MSG));
    }

    private void a(String str, String str2, WebView webView, HashMap hashMap) {
        new c(this, str, str2, hashMap, webView.getSettings().getUserAgentString(), webView).execute(new Void[0]);
    }

    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false).setIcon(17301543).setTitle(Constants.QUIT_PAYMENT_FLOW_TITLE).setMessage(Constants.QUIT_PAYMENT_FLOW_DESC).setPositiveButton(IntentIntegrator.d, new h(this)).setNegativeButton(IntentIntegrator.e, new g(this));
        builder.create().show();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().addFlags(128);
        this.c = new WebView(this);
        setContentView(this.c);
        a();
        this.d = (PaymentAPI) getIntent().getSerializableExtra("KEY_API");
        this.e = getIntent().getStringExtra("KEY_PG_AMOUNT");
        this.f = getIntent().getStringExtra("KEY_RESPONSE_URL");
        this.g = getIntent().getBooleanExtra("KEY_IS_WALLET", false);
        if (this.d == null || this.f == null) {
            a((Intent) null, SDKErrorCodes.UNEXPECTED_ERROR.getErrorCode(), SDKErrorCodes.UNEXPECTED_ERROR.getErrorDescription());
            return;
        }
        if (this.h == null) {
            this.h = ProgressDialog.show(this, "", "Processing your payment request...", true);
        }
        this.d.startAPI(this, this);
    }

    public void onError(String str, String str2) {
        a((Intent) null, str, str2);
    }

    public void onPause() {
        super.onPause();
        this.i = false;
    }

    public void onPaymentCompleted(String str) {
        Intent intent = new Intent(getIntent());
        intent.setClass(getBaseContext(), MobikwikSDK.class);
        intent.putExtra(Constants.SENDER_TO_WALLET, getIntent().getIntExtra(Constants.SENDER_TO_WALLET, 0));
        intent.putExtra(Constants.TXN_STATUS, "SUCCESS");
        intent.putExtra(Constants.PAYMENT_STATUS_MSG, "Payment Successful");
        intent.putExtra(Constants.WALLET_BALANCE, str);
        a(intent, "0", "Payment Successful");
    }

    public void onResume() {
        super.onResume();
        this.i = true;
        if (this.j != null) {
            this.j.run();
        }
    }

    public void onSuccess(String str, String str2, HashMap hashMap) {
        Utils.print("postUrl = " + str);
        Utils.print("postData = " + str2);
        if (hashMap == null || hashMap.size() == 0) {
            a(str, str2);
        } else {
            a(str, str2, this.c, hashMap);
        }
    }
}
