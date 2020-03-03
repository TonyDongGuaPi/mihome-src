package com.paytm.pgsdk;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.mi.blockcanary.internal.BlockInfo;
import java.util.Iterator;
import org.json.JSONObject;

@TargetApi(21)
public class PaytmWebView extends WebView {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8551a = "HTMLOUT";
    private static final String b = "javascript:window.HTMLOUT.processResponse(document.getElementById('response').value);";
    private static final String c = "Y";
    private static final String d = "01";
    private static final String g = "/CAS/Response";
    /* access modifiers changed from: private */
    public final PaytmPGActivity e;
    /* access modifiers changed from: private */
    public volatile boolean f;

    public PaytmWebView(Context context, Bundle bundle) {
        super(context);
        this.e = (PaytmPGActivity) context;
        setWebViewClient(new PaytmWebViewClient());
        setWebChromeClient(new WebChromeClient() {
            public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
                PaytmUtility.a("JavaScript Alert " + str);
                return super.onJsAlert(webView, str, str2, jsResult);
            }
        });
        getSettings().setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= 21) {
            getSettings().setMixedContentMode(0);
        }
        addJavascriptInterface(new PaytmJavaScriptInterface(), f8551a);
    }

    private class PaytmWebViewClient extends WebViewClient {
        private PaytmWebViewClient() {
        }

        public synchronized void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            PaytmUtility.a("Page started loading " + str);
            PaytmWebView.this.a();
        }

        public synchronized void onPageFinished(WebView webView, String str) {
            try {
                super.onPageFinished(webView, str);
                PaytmUtility.a("Page finished loading " + str);
                PaytmWebView.this.b();
                if (str.equalsIgnoreCase(PaytmPGService.a().f8545a.a().get("CALLBACK_URL").toString())) {
                    PaytmUtility.a("Merchant specific Callback Url is finished loading. Extract data now. ");
                    boolean unused = PaytmWebView.this.f = true;
                    PaytmWebView.this.loadUrl(PaytmWebView.b);
                } else if (str.endsWith(PaytmWebView.g)) {
                    PaytmUtility.a("CAS Callback Url is finished loading. Extract data now. ");
                    PaytmWebView.this.loadUrl(PaytmWebView.b);
                }
            } catch (Exception e) {
                PaytmUtility.a(e);
            }
            return;
        }

        public synchronized void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            PaytmUtility.a("Error occured while loading url " + str2);
            PaytmUtility.a("Error code is " + i + "Description is " + str);
            if (i == -6) {
                ((Activity) PaytmWebView.this.getContext()).finish();
                PaytmPaymentTransactionCallback paytmPaymentTransactionCallback = PaytmPGService.a().e;
                if (paytmPaymentTransactionCallback != null) {
                    paytmPaymentTransactionCallback.a(i, str, str2);
                }
            }
        }

        public synchronized void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            PaytmUtility.a("SSL Error occured " + sslError.toString());
            PaytmUtility.a("SSL Handler is " + sslErrorHandler);
            if (sslErrorHandler != null) {
                sslErrorHandler.cancel();
            }
        }
    }

    /* access modifiers changed from: private */
    public synchronized void a() {
        try {
            ((Activity) getContext()).runOnUiThread(new Runnable() {
                public void run() {
                    PaytmWebView.this.e.mProgress.setVisibility(0);
                    PaytmUtility.a("Progress dialog started");
                }
            });
        } catch (Exception e2) {
            PaytmUtility.a(e2);
        }
        return;
    }

    /* access modifiers changed from: private */
    public synchronized void b() {
        try {
            ((Activity) getContext()).runOnUiThread(new Runnable() {
                public void run() {
                    PaytmWebView.this.e.mProgress.setVisibility(8);
                    PaytmUtility.a("Progress dialog ended");
                }
            });
        } catch (Exception e2) {
            PaytmUtility.a(e2);
        }
        return;
    }

    private class PaytmJavaScriptInterface {
        private PaytmJavaScriptInterface() {
        }

        @JavascriptInterface
        public synchronized void processResponse(String str) {
            try {
                PaytmUtility.a("Merchant Response is " + str);
                a(PaytmWebView.this.a(str));
            } catch (Exception e) {
                PaytmUtility.a(e);
            }
            return;
        }

        private synchronized void a(final Bundle bundle) {
            try {
                ((Activity) PaytmWebView.this.getContext()).runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            ((Activity) PaytmWebView.this.getContext()).finish();
                            PaytmPGService.a().e.a(bundle);
                        } catch (Exception e) {
                            PaytmUtility.a(e);
                        }
                    }
                });
            } catch (Exception e) {
                PaytmUtility.a(e);
            }
            return;
        }
    }

    /* access modifiers changed from: private */
    public synchronized Bundle a(String str) {
        Bundle bundle;
        PaytmUtility.a("Parsing the Merchant Response");
        bundle = new Bundle();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.length() > 0) {
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    String string = jSONObject.getString(next);
                    PaytmUtility.a(next + BlockInfo.c + string);
                    bundle.putString(next, string);
                }
            }
        } catch (Exception e2) {
            PaytmUtility.a("Error while parsing the Merchant Response");
            PaytmUtility.a(e2);
        }
        return bundle;
    }

    private synchronized boolean a(Bundle bundle) {
        boolean z;
        z = false;
        if (bundle != null) {
            try {
                if (bundle.size() > 0 && bundle.containsKey(PaytmConstants.l) && bundle.getString(PaytmConstants.l).equalsIgnoreCase(c)) {
                    z = true;
                }
            } catch (Exception e2) {
                PaytmUtility.a(e2);
            }
        }
        return z;
    }
}
