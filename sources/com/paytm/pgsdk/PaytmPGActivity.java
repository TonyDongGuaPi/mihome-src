package com.paytm.pgsdk;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.mi.blockcanary.internal.BlockInfo;
import com.taobao.weex.ui.module.WXModalUIModule;
import com.xiaomi.zxing.integration.android.IntentIntegrator;
import java.util.Iterator;
import org.json.JSONObject;

public class PaytmPGActivity extends Activity {
    private static final String d = "CHECKSUMHASH";
    private static final String e = "Client authentication failed. Please try again later.";
    private static final String f = "Client authentication failed due to server error. Please try again later.";
    private static final String g = "Some error occured while initializing UI of Payment Gateway Activity";
    private static final String h = "Some UI error occured in WebView of Payment Gateway Activity";
    private static final String i = "payt_STATUS";
    private static final String j = "1";
    private static final String k = "Transaction Cancelled.";
    private static final String l = "Transaction not Cancelled.";
    private static final String m = "Transaction cancelled by user.";
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public volatile PaytmWebView f8539a;
    private volatile AuthenticatorTask b;
    /* access modifiers changed from: private */
    public volatile Bundle c;
    protected volatile ProgressBar mProgress;
    /* access modifiers changed from: private */
    public Dialog n;
    /* access modifiers changed from: private */
    public boolean o;
    /* access modifiers changed from: private */
    public boolean p;
    private boolean q;
    private boolean r;

    /* access modifiers changed from: protected */
    public synchronized void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (a()) {
            b();
        } else {
            finish();
            PaytmPaymentTransactionCallback paytmPaymentTransactionCallback = PaytmPGService.a().e;
            if (paytmPaymentTransactionCallback != null) {
                paytmPaymentTransactionCallback.a(g);
            }
        }
    }

    private synchronized boolean a() {
        try {
            if (getIntent() != null) {
                this.q = getIntent().getBooleanExtra("HIDE_HEADER", false);
                this.r = getIntent().getBooleanExtra("SEND_ALL_CHECKSUM_RESPONSE_PARAMETERS_TO_PG_SERVER", false);
            }
            PaytmUtility.a("Hide Header " + this.q);
            PaytmUtility.a("Initializing the UI of Transaction Page...");
            RelativeLayout relativeLayout = new RelativeLayout(this);
            RelativeLayout relativeLayout2 = new RelativeLayout(this);
            relativeLayout2.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
            relativeLayout2.setId(1);
            relativeLayout2.setBackgroundColor(Color.parseColor("#bdbdbd"));
            Button button = new Button(this, (AttributeSet) null, 16842825);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(15);
            layoutParams.leftMargin = (int) (getResources().getDisplayMetrics().density * 5.0f);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    PaytmUtility.a("User pressed back button which is present in Header Bar.");
                    PaytmPGActivity.this.c();
                }
            });
            button.setLayoutParams(layoutParams);
            button.setText(WXModalUIModule.CANCEL);
            TextView textView = new TextView(this);
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams2.addRule(13);
            textView.setLayoutParams(layoutParams2);
            textView.setTextColor(-16777216);
            textView.setText("Paytm Payments");
            relativeLayout2.addView(button);
            relativeLayout2.addView(textView);
            RelativeLayout relativeLayout3 = new RelativeLayout(this);
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams3.addRule(3, relativeLayout2.getId());
            relativeLayout3.setLayoutParams(layoutParams3);
            this.f8539a = new PaytmWebView(this, this.c);
            this.f8539a.setVisibility(8);
            this.f8539a.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
            this.mProgress = new ProgressBar(this, (AttributeSet) null, 16842873);
            RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams4.addRule(13);
            this.mProgress.setLayoutParams(layoutParams4);
            relativeLayout3.addView(this.f8539a);
            relativeLayout3.addView(this.mProgress);
            relativeLayout.addView(relativeLayout2);
            relativeLayout.addView(relativeLayout3);
            if (this.q) {
                relativeLayout2.setVisibility(8);
            }
            requestWindowFeature(1);
            setContentView(relativeLayout);
            PaytmUtility.a("Initialized UI of Transaction Page.");
        } catch (Exception e2) {
            PaytmUtility.a("Some exception occurred while initializing UI.");
            PaytmUtility.a(e2);
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public synchronized void onDestroy() {
        super.onDestroy();
        try {
            if (this.b != null) {
                this.b.cancel(true);
            }
            PaytmPGService.a().d();
        } catch (Exception e2) {
            PaytmPGService.a().d();
            PaytmUtility.a("Some exception occurred while destroying the PaytmPGActivity.");
            PaytmUtility.a(e2);
        }
        return;
    }

    private class AuthenticatorTask extends AsyncTask<String, Void, String> {
        private AuthenticatorTask() {
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Removed duplicated region for block: B:27:0x0116 A[SYNTHETIC, Splitter:B:27:0x0116] */
        /* renamed from: a */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public synchronized java.lang.String doInBackground(java.lang.String... r7) {
            /*
                r6 = this;
                monitor-enter(r6)
                java.lang.String r0 = ""
                r1 = 0
                java.net.URL r2 = new java.net.URL     // Catch:{ Exception -> 0x0108 }
                r3 = 0
                r7 = r7[r3]     // Catch:{ Exception -> 0x0108 }
                r2.<init>(r7)     // Catch:{ Exception -> 0x0108 }
                java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0108 }
                r7.<init>()     // Catch:{ Exception -> 0x0108 }
                java.lang.String r3 = "URL is "
                r7.append(r3)     // Catch:{ Exception -> 0x0108 }
                java.lang.String r3 = r2.toString()     // Catch:{ Exception -> 0x0108 }
                r7.append(r3)     // Catch:{ Exception -> 0x0108 }
                java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x0108 }
                com.paytm.pgsdk.PaytmUtility.a((java.lang.String) r7)     // Catch:{ Exception -> 0x0108 }
                java.net.URLConnection r7 = r2.openConnection()     // Catch:{ Exception -> 0x0108 }
                java.lang.String r1 = "New Connection is created."
                com.paytm.pgsdk.PaytmUtility.a((java.lang.String) r1)     // Catch:{ Exception -> 0x0106 }
                java.lang.String r1 = r2.toString()     // Catch:{ Exception -> 0x0106 }
                boolean r1 = android.webkit.URLUtil.isHttpsUrl(r1)     // Catch:{ Exception -> 0x0106 }
                if (r1 == 0) goto L_0x0059
                java.lang.String r1 = "Https url"
                com.paytm.pgsdk.PaytmUtility.a((java.lang.String) r1)     // Catch:{ Exception -> 0x0106 }
                java.lang.String r1 = "Setting SSLSocketFactory to connection..."
                com.paytm.pgsdk.PaytmUtility.a((java.lang.String) r1)     // Catch:{ Exception -> 0x0106 }
                r1 = r7
                javax.net.ssl.HttpsURLConnection r1 = (javax.net.ssl.HttpsURLConnection) r1     // Catch:{ Exception -> 0x0106 }
                com.paytm.pgsdk.PaytmSSLSocketFactory r2 = new com.paytm.pgsdk.PaytmSSLSocketFactory     // Catch:{ Exception -> 0x0106 }
                com.paytm.pgsdk.PaytmPGActivity r3 = com.paytm.pgsdk.PaytmPGActivity.this     // Catch:{ Exception -> 0x0106 }
                com.paytm.pgsdk.PaytmPGService r4 = com.paytm.pgsdk.PaytmPGService.a()     // Catch:{ Exception -> 0x0106 }
                com.paytm.pgsdk.PaytmClientCertificate r4 = r4.b     // Catch:{ Exception -> 0x0106 }
                r2.<init>(r3, r4)     // Catch:{ Exception -> 0x0106 }
                r1.setSSLSocketFactory(r2)     // Catch:{ Exception -> 0x0106 }
                java.lang.String r1 = "SSLSocketFactory is set to connection."
                com.paytm.pgsdk.PaytmUtility.a((java.lang.String) r1)     // Catch:{ Exception -> 0x0106 }
            L_0x0059:
                r1 = 1
                r7.setDoOutput(r1)     // Catch:{ Exception -> 0x0106 }
                r1 = r7
                java.net.HttpURLConnection r1 = (java.net.HttpURLConnection) r1     // Catch:{ Exception -> 0x0106 }
                java.lang.String r2 = "POST"
                r1.setRequestMethod(r2)     // Catch:{ Exception -> 0x0106 }
                com.paytm.pgsdk.PaytmPGActivity r1 = com.paytm.pgsdk.PaytmPGActivity.this     // Catch:{ Exception -> 0x0106 }
                android.os.Bundle r1 = r1.c     // Catch:{ Exception -> 0x0106 }
                java.lang.String r1 = com.paytm.pgsdk.PaytmUtility.a((android.os.Bundle) r1)     // Catch:{ Exception -> 0x0106 }
                if (r1 == 0) goto L_0x0114
                int r2 = r1.length()     // Catch:{ Exception -> 0x0106 }
                if (r2 <= 0) goto L_0x0114
                java.lang.String r2 = "Getting the output stream to post"
                com.paytm.pgsdk.PaytmUtility.a((java.lang.String) r2)     // Catch:{ Exception -> 0x0106 }
                java.io.PrintWriter r2 = new java.io.PrintWriter     // Catch:{ Exception -> 0x0106 }
                java.io.OutputStream r3 = r7.getOutputStream()     // Catch:{ Exception -> 0x0106 }
                r2.<init>(r3)     // Catch:{ Exception -> 0x0106 }
                java.lang.String r3 = "posting......"
                com.paytm.pgsdk.PaytmUtility.a((java.lang.String) r3)     // Catch:{ Exception -> 0x0106 }
                r2.print(r1)     // Catch:{ Exception -> 0x0106 }
                r2.close()     // Catch:{ Exception -> 0x0106 }
                java.lang.String r1 = "posted parameters and closing output stream"
                com.paytm.pgsdk.PaytmUtility.a((java.lang.String) r1)     // Catch:{ Exception -> 0x0106 }
                r1 = r7
                java.net.HttpURLConnection r1 = (java.net.HttpURLConnection) r1     // Catch:{ Exception -> 0x0106 }
                int r1 = r1.getResponseCode()     // Catch:{ Exception -> 0x0106 }
                r2 = r7
                java.net.HttpURLConnection r2 = (java.net.HttpURLConnection) r2     // Catch:{ Exception -> 0x0106 }
                java.lang.String r2 = r2.getResponseMessage()     // Catch:{ Exception -> 0x0106 }
                java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0106 }
                r3.<init>()     // Catch:{ Exception -> 0x0106 }
                java.lang.String r4 = "Response code is "
                r3.append(r4)     // Catch:{ Exception -> 0x0106 }
                r3.append(r1)     // Catch:{ Exception -> 0x0106 }
                java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x0106 }
                com.paytm.pgsdk.PaytmUtility.a((java.lang.String) r3)     // Catch:{ Exception -> 0x0106 }
                java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0106 }
                r3.<init>()     // Catch:{ Exception -> 0x0106 }
                java.lang.String r4 = "Response Message is "
                r3.append(r4)     // Catch:{ Exception -> 0x0106 }
                r3.append(r2)     // Catch:{ Exception -> 0x0106 }
                java.lang.String r2 = r3.toString()     // Catch:{ Exception -> 0x0106 }
                com.paytm.pgsdk.PaytmUtility.a((java.lang.String) r2)     // Catch:{ Exception -> 0x0106 }
                r2 = 200(0xc8, float:2.8E-43)
                if (r1 != r2) goto L_0x0114
                java.lang.String r1 = "Getting the input stream to read response"
                com.paytm.pgsdk.PaytmUtility.a((java.lang.String) r1)     // Catch:{ Exception -> 0x0106 }
                java.util.Scanner r1 = new java.util.Scanner     // Catch:{ Exception -> 0x0106 }
                java.io.InputStream r2 = r7.getInputStream()     // Catch:{ Exception -> 0x0106 }
                r1.<init>(r2)     // Catch:{ Exception -> 0x0106 }
                java.lang.String r2 = "reading......"
                com.paytm.pgsdk.PaytmUtility.a((java.lang.String) r2)     // Catch:{ Exception -> 0x0106 }
            L_0x00e2:
                boolean r2 = r1.hasNextLine()     // Catch:{ Exception -> 0x0106 }
                if (r2 == 0) goto L_0x00fd
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0106 }
                r2.<init>()     // Catch:{ Exception -> 0x0106 }
                r2.append(r0)     // Catch:{ Exception -> 0x0106 }
                java.lang.String r3 = r1.nextLine()     // Catch:{ Exception -> 0x0106 }
                r2.append(r3)     // Catch:{ Exception -> 0x0106 }
                java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x0106 }
                r0 = r2
                goto L_0x00e2
            L_0x00fd:
                r1.close()     // Catch:{ Exception -> 0x0106 }
                java.lang.String r1 = "read response and closing input stream"
                com.paytm.pgsdk.PaytmUtility.a((java.lang.String) r1)     // Catch:{ Exception -> 0x0106 }
                goto L_0x0114
            L_0x0106:
                r1 = move-exception
                goto L_0x010c
            L_0x0108:
                r7 = move-exception
                r5 = r1
                r1 = r7
                r7 = r5
            L_0x010c:
                java.lang.String r2 = "Some exception occurred while making client authentication."
                com.paytm.pgsdk.PaytmUtility.a((java.lang.String) r2)     // Catch:{ all -> 0x0129 }
                com.paytm.pgsdk.PaytmUtility.a((java.lang.Exception) r1)     // Catch:{ all -> 0x0129 }
            L_0x0114:
                if (r7 == 0) goto L_0x011e
                java.net.HttpURLConnection r7 = (java.net.HttpURLConnection) r7     // Catch:{ Exception -> 0x011c }
                r7.disconnect()     // Catch:{ Exception -> 0x011c }
                goto L_0x011e
            L_0x011c:
                r7 = move-exception
                goto L_0x0124
            L_0x011e:
                java.lang.String r7 = "connection is disconnected"
                com.paytm.pgsdk.PaytmUtility.a((java.lang.String) r7)     // Catch:{ Exception -> 0x011c }
                goto L_0x0127
            L_0x0124:
                com.paytm.pgsdk.PaytmUtility.a((java.lang.Exception) r7)     // Catch:{ all -> 0x0129 }
            L_0x0127:
                monitor-exit(r6)
                return r0
            L_0x0129:
                r7 = move-exception
                monitor-exit(r6)
                throw r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.paytm.pgsdk.PaytmPGActivity.AuthenticatorTask.doInBackground(java.lang.String[]):java.lang.String");
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public synchronized void onPostExecute(String str) {
            if (str != null) {
                try {
                    if (!str.equalsIgnoreCase("")) {
                        PaytmUtility.a("Response is " + str);
                        if (PaytmPGActivity.this.a(str)) {
                            boolean unused = PaytmPGActivity.this.p = true;
                            PaytmPGActivity.this.f8539a.setVisibility(0);
                            PaytmPGActivity.this.f8539a.postUrl(PaytmPGService.a().d, PaytmUtility.b(PaytmPGActivity.this.c).getBytes());
                            PaytmPGActivity.this.f8539a.requestFocus(130);
                        } else {
                            PaytmPGActivity.this.finish();
                            PaytmPaymentTransactionCallback paytmPaymentTransactionCallback = PaytmPGService.a().e;
                            if (paytmPaymentTransactionCallback != null) {
                                paytmPaymentTransactionCallback.b(PaytmPGActivity.e);
                            }
                        }
                    }
                } catch (Exception e) {
                    PaytmPGActivity.this.finish();
                    PaytmPaymentTransactionCallback paytmPaymentTransactionCallback2 = PaytmPGService.a().e;
                    if (paytmPaymentTransactionCallback2 != null) {
                        paytmPaymentTransactionCallback2.a(PaytmPGActivity.h);
                    }
                    PaytmUtility.a("Some exception occurred while posting data to PG Server.");
                    PaytmUtility.a(e);
                } catch (Throwable th) {
                    throw th;
                }
            }
            PaytmPGActivity.this.finish();
            PaytmPaymentTransactionCallback paytmPaymentTransactionCallback3 = PaytmPGService.a().e;
            if (paytmPaymentTransactionCallback3 != null) {
                paytmPaymentTransactionCallback3.b(PaytmPGActivity.f);
            }
        }
    }

    /* access modifiers changed from: private */
    public synchronized boolean a(String str) {
        boolean z;
        z = false;
        try {
            PaytmUtility.a("Parsing JSON");
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            PaytmUtility.a("Appending Key Value pairs");
            PaytmUtility.a("Send All Checksum Response Parameters to PG " + this.r);
            while (keys.hasNext()) {
                String next = keys.next();
                String string = jSONObject.getString(next);
                String trim = next.trim();
                PaytmUtility.a(trim + BlockInfo.c + string);
                if (trim.equals(d)) {
                    this.c.putString(trim, string);
                } else if (this.r) {
                    this.c.putString(trim, string);
                }
                if (trim.equals(i) && string.equals("1")) {
                    z = true;
                }
            }
        } catch (Exception e2) {
            PaytmUtility.a("Some exception occurred while extracting the checksum from CAS Response.");
            PaytmUtility.a(e2);
        }
        return z;
    }

    private synchronized void b() {
        PaytmUtility.a("Starting the Process...");
        if (!(getIntent() == null || getIntent().getBundleExtra("Parameters") == null)) {
            this.c = getIntent().getBundleExtra("Parameters");
            if (this.c != null && this.c.size() > 0) {
                PaytmUtility.a("Starting the Client Authentication...");
                this.b = new AuthenticatorTask();
                if (PaytmPGService.a() != null) {
                    this.f8539a.setVisibility(0);
                    this.f8539a.postUrl(PaytmPGService.a().d, PaytmUtility.b(this.c).getBytes());
                    this.f8539a.requestFocus(130);
                }
            }
        }
    }

    public synchronized boolean onKeyDown(int i2, KeyEvent keyEvent) {
        PaytmUtility.a("User pressed key and key code is " + i2);
        if (i2 == 4) {
            PaytmUtility.a("User pressed hard key back button");
            c();
        }
        return super.onKeyDown(i2, keyEvent);
    }

    private synchronized void b(final String str) {
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    boolean unused = PaytmPGActivity.this.o = false;
                    PaytmUtility.a(str);
                    Toast.makeText(PaytmPGActivity.this, str, 0).show();
                    PaytmUtility.a("User cancelled " + PaytmPGActivity.this.c);
                    PaytmPGService.a().e.a(PaytmPGActivity.m, PaytmPGActivity.this.c);
                    PaytmUtility.a("user cancellation");
                    PaytmPGActivity.this.finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public synchronized void c() {
        if (!this.o) {
            PaytmUtility.a("Displaying Confirmation Dialog");
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Cancel Transaction");
            builder.setMessage("Are you sure you want to cancel transaction");
            builder.setPositiveButton(IntentIntegrator.d, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    PaytmPGActivity.this.onBackPressed();
                }
            });
            builder.setNegativeButton(IntentIntegrator.e, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    PaytmPGActivity.this.n.dismiss();
                }
            });
            this.n = builder.create();
            this.n.show();
        }
    }

    public void onBackPressed() {
        PaytmPGService.a().e.b();
        super.onBackPressed();
    }
}
