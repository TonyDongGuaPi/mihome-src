package com.xiaomi.jr.mipay.codepay.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.xiaomi.jr.common.utils.UrlUtils;
import com.xiaomi.jr.http.MifiHttpCallback;
import com.xiaomi.jr.http.model.MiFiResponse;
import com.xiaomi.jr.mipay.codepay.CodePayManager;
import com.xiaomi.jr.mipay.codepay.api.ApiManager;
import com.xiaomi.jr.mipay.codepay.ui.CodePayWebActivity;
import com.xiaomi.jr.mipay.codepay.util.MifiConstants;
import java.net.URLEncoder;
import org.json.JSONObject;

public class BindCardActivity extends CodePayWebActivity {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10913a = "codepay://bindcard_result";
    private static final String b = (MifiConstants.f10936a + "loan/loanh5/installment.html#/bindcard/result");
    private boolean c;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        showLoading(true);
        a();
        this.mWebView.setWebViewClient(new CodePayWebActivity.CodePayWebViewClient() {
            /* access modifiers changed from: protected */
            public boolean a(String str) {
                if (!str.toLowerCase().startsWith(BindCardActivity.f10913a)) {
                    return false;
                }
                BindCardActivity.this.a(str);
                return true;
            }
        });
    }

    private void a() {
        String str = null;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("requester", CodePayManager.c());
            String jSONObject2 = jSONObject.toString();
            try {
                str = URLEncoder.encode(jSONObject2, "UTF-8");
            } catch (Exception e) {
                String str2 = jSONObject2;
                e = e;
                str = str2;
                e.printStackTrace();
                ApiManager.b().a(f10913a, 1, str).enqueue(new MifiHttpCallback<MiFiResponse<String>>(this) {
                    public void a(MiFiResponse<String> miFiResponse) {
                        BindCardActivity bindCardActivity = BindCardActivity.this;
                        bindCardActivity.mUrl = BindCardActivity.this.mUrl + "?" + miFiResponse.d();
                        BindCardActivity.this.mWebView.loadUrl(BindCardActivity.this.mUrl);
                    }

                    public void a(int i, String str, MiFiResponse<String> miFiResponse, Throwable th) {
                        super.a(i, str, miFiResponse, th);
                        BindCardActivity.this.finish();
                    }
                });
            }
        } catch (Exception e2) {
            e = e2;
            e.printStackTrace();
            ApiManager.b().a(f10913a, 1, str).enqueue(new MifiHttpCallback<MiFiResponse<String>>(this) {
                public void a(MiFiResponse<String> miFiResponse) {
                    BindCardActivity bindCardActivity = BindCardActivity.this;
                    bindCardActivity.mUrl = BindCardActivity.this.mUrl + "?" + miFiResponse.d();
                    BindCardActivity.this.mWebView.loadUrl(BindCardActivity.this.mUrl);
                }

                public void a(int i, String str, MiFiResponse<String> miFiResponse, Throwable th) {
                    super.a(i, str, miFiResponse, th);
                    BindCardActivity.this.finish();
                }
            });
        }
        ApiManager.b().a(f10913a, 1, str).enqueue(new MifiHttpCallback<MiFiResponse<String>>(this) {
            public void a(MiFiResponse<String> miFiResponse) {
                BindCardActivity bindCardActivity = BindCardActivity.this;
                bindCardActivity.mUrl = BindCardActivity.this.mUrl + "?" + miFiResponse.d();
                BindCardActivity.this.mWebView.loadUrl(BindCardActivity.this.mUrl);
            }

            public void a(int i, String str, MiFiResponse<String> miFiResponse, Throwable th) {
                super.a(i, str, miFiResponse, th);
                BindCardActivity.this.finish();
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        int i;
        boolean z = false;
        try {
            i = Integer.parseInt(Uri.parse(str).getQueryParameter("bindStatus"));
        } catch (Exception unused) {
            i = 0;
        }
        String str2 = b;
        if (i == 1) {
            z = true;
        }
        this.mWebView.loadUrl(UrlUtils.a(str2, "success", String.valueOf(z)));
        this.c = true;
        Intent intent = new Intent();
        intent.putExtra("bind_success", i);
        setResult(-1, intent);
    }

    public void onBackPressed() {
        if (this.c) {
            finish();
        } else {
            super.onBackPressed();
        }
    }
}
