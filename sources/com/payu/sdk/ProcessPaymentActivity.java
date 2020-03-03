package com.payu.sdk;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.alipay.sdk.sys.a;
import com.mi.global.shop.R;
import com.mi.global.shop.buy.payu.PayU;
import com.mi.global.shop.util.MiShopStatInterface;
import com.payu.custombrowser.Bank;
import com.payu.custombrowser.PayUWebChromeClient;
import com.payu.custombrowser.PayUWebViewClient;
import com.payu.custombrowser.util.CBConstant;
import com.xiaomi.smarthome.framework.plugin.rn.nativemodule.viewshot.ViewShot;
import org.apache.http.util.EncodingUtils;

@SuppressLint({"ValidFragment"})
public class ProcessPaymentActivity extends FragmentActivity {
    boolean cancelTransaction = false;
    private BroadcastReceiver mReceiver = null;
    private WebView webView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        String str = null;
        if (bundle != null) {
            super.onCreate((Bundle) null);
            finish();
        } else {
            super.onCreate(bundle);
        }
        setContentView(R.layout.shop_activity_process_payment);
        this.webView = (WebView) findViewById(R.id.webview);
        this.webView.getSettings().setCacheMode(2);
        try {
            Class.forName("com.payu.custombrowser.Bank");
            BankFragment bankFragment = new BankFragment();
            bankFragment.setParent(findViewById(R.id.parent));
            bankFragment.setTransOverlay(findViewById(R.id.trans_overlay));
            Bundle bundle2 = new Bundle();
            bundle2.putInt(CBConstant.WEBVIEW, R.id.webview);
            bundle2.putInt(CBConstant.TRANS_LAYOUT, R.id.trans_overlay);
            bundle2.putInt(CBConstant.MAIN_LAYOUT, R.id.r_layout);
            String[] split = getIntent().getExtras().getString("postData").split(a.b);
            int length = split.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                String str2 = split[i];
                if (str2.contains("txnid")) {
                    str = str2.split("=")[1];
                    break;
                }
                i++;
            }
            if (str == null) {
                str = String.valueOf(System.currentTimeMillis());
            }
            bundle2.putString("txnid", str);
            if (getIntent().getExtras().containsKey("showCustom")) {
                bundle2.putBoolean("showCustom", getIntent().getBooleanExtra("showCustom", false));
            }
            bundle2.putBoolean("showCustom", true);
            bankFragment.setArguments(bundle2);
            findViewById(R.id.parent).bringToFront();
            try {
                getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.cb_face_out).add(R.id.parent, (Fragment) bankFragment).commit();
            } catch (Exception e) {
                e.printStackTrace();
                finish();
            }
            this.webView.setWebChromeClient(new PayUWebChromeClient(bankFragment));
            this.webView.setWebViewClient(new PayUWebViewClient(bankFragment, PayU.f6891a));
        } catch (ClassNotFoundException unused) {
            this.webView.getSettings().setSupportMultipleWindows(true);
            this.webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            this.webView.addJavascriptInterface(new Object() {
                @JavascriptInterface
                public void onSuccess() {
                    onSuccess("");
                }

                @JavascriptInterface
                public void onSuccess(final String str) {
                    ProcessPaymentActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            Intent intent = new Intent();
                            intent.putExtra("result", str);
                            ProcessPaymentActivity.this.setResult(-1, intent);
                            ProcessPaymentActivity.this.finish();
                        }
                    });
                }

                @JavascriptInterface
                public void onFailure() {
                    onFailure("");
                }

                @JavascriptInterface
                public void onFailure(final String str) {
                    ProcessPaymentActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            Intent intent = new Intent();
                            intent.putExtra("result", str);
                            ProcessPaymentActivity.this.setResult(0, intent);
                            ProcessPaymentActivity.this.finish();
                        }
                    });
                }
            }, "PayU");
            this.webView.setWebChromeClient(new WebChromeClient() {
            });
            this.webView.setWebViewClient(new WebViewClient());
        }
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.getSettings().setDomStorageEnabled(true);
        this.webView.postUrl(Constants.PAYMENT_URL, EncodingUtils.getBytes(getIntent().getExtras().getString("postData"), ViewShot.Results.BASE_64));
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onBackPressed() {
        /*
            r4 = this;
            boolean r0 = r4.cancelTransaction
            r1 = 0
            if (r0 == 0) goto L_0x001a
            r4.cancelTransaction = r1
            android.content.Intent r0 = new android.content.Intent
            r0.<init>()
            java.lang.String r2 = "result"
            java.lang.String r3 = "Transaction canceled due to back pressed!"
            r0.putExtra(r2, r3)
            r4.setResult(r1, r0)
            super.onBackPressed()
            return
        L_0x001a:
            android.content.pm.PackageManager r0 = r4.getPackageManager()     // Catch:{ NameNotFoundException -> 0x003c }
            java.lang.String r2 = r4.getPackageName()     // Catch:{ NameNotFoundException -> 0x003c }
            r3 = 128(0x80, float:1.794E-43)
            android.content.pm.ApplicationInfo r0 = r0.getApplicationInfo(r2, r3)     // Catch:{ NameNotFoundException -> 0x003c }
            android.os.Bundle r0 = r0.metaData     // Catch:{ NameNotFoundException -> 0x003c }
            java.lang.String r2 = "payu_disable_back"
            boolean r2 = r0.containsKey(r2)     // Catch:{ NameNotFoundException -> 0x003c }
            if (r2 == 0) goto L_0x0040
            java.lang.String r2 = "payu_disable_back"
            boolean r0 = r0.getBoolean(r2)     // Catch:{ NameNotFoundException -> 0x003c }
            if (r0 == 0) goto L_0x0040
            r0 = 1
            goto L_0x0041
        L_0x003c:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0040:
            r0 = 0
        L_0x0041:
            if (r0 != 0) goto L_0x0067
            android.app.AlertDialog$Builder r0 = new android.app.AlertDialog$Builder
            r0.<init>(r4)
            r0.setCancelable(r1)
            java.lang.String r1 = "Do you really want to cancel the transaction ?"
            r0.setMessage(r1)
            java.lang.String r1 = "Ok"
            com.payu.sdk.ProcessPaymentActivity$3 r2 = new com.payu.sdk.ProcessPaymentActivity$3
            r2.<init>()
            r0.setPositiveButton(r1, r2)
            java.lang.String r1 = "Cancel"
            com.payu.sdk.ProcessPaymentActivity$4 r2 = new com.payu.sdk.ProcessPaymentActivity$4
            r2.<init>()
            r0.setNegativeButton(r1, r2)
            r0.show()
        L_0x0067:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.payu.sdk.ProcessPaymentActivity.onBackPressed():void");
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MiShopStatInterface.a((Context) this, getClass().getSimpleName());
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        MiShopStatInterface.b();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.webView.clearCache(true);
        this.webView.clearHistory();
        this.webView.destroy();
    }

    public static class BankFragment extends Bank {
        private BroadcastReceiver mReceiver;
        private View parent;
        private View transOverlay;

        public void setParent(View view) {
            this.parent = view;
        }

        public void setTransOverlay(View view) {
            this.transOverlay = view;
        }

        public void registerBroadcast(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
            this.mReceiver = broadcastReceiver;
            getContext().registerReceiver(broadcastReceiver, intentFilter);
        }

        public void unregisterBroadcast(BroadcastReceiver broadcastReceiver) {
            if (this.mReceiver != null) {
                getContext().unregisterReceiver(this.mReceiver);
                this.mReceiver = null;
            }
        }

        public void onHelpUnavailable() {
            this.parent.setVisibility(8);
            this.transOverlay.setVisibility(8);
        }

        public void onBankError() {
            this.parent.setVisibility(8);
            this.transOverlay.setVisibility(8);
        }

        public void onHelpAvailable() {
            this.parent.setVisibility(0);
        }
    }
}
