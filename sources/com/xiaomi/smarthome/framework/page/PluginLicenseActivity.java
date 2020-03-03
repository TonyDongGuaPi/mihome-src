package com.xiaomi.smarthome.framework.page;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import com.mijia.camera.Utils.Util;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.webview.SmartHomeWebView;
import com.xiaomi.smarthome.httpserver.NanoHTTPD;
import java.net.URI;

public class PluginLicenseActivity extends BaseActivity {
    public static final String LICENSE_CONTENT = "license_content";
    public static final String LICENSE_HTML_CONTENT = "license_html_content";
    public static final String LICENSE_HTML_CONTENT_RES = "license_html_content_res";
    public static final String LICENSE_URI = "license_uri";
    public static final String TITLE = "title";

    /* renamed from: a  reason: collision with root package name */
    private SmartHomeWebView f16906a;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        overridePendingTransition(R.anim.activity_slide_in_right, R.anim.activity_slide_out_left);
        setContentView(R.layout.activity_plugin_license);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PluginLicenseActivity.this.finish();
            }
        });
        this.f16906a = (SmartHomeWebView) findViewById(R.id.user_license_info_web);
        initWebView();
        TextView textView = (TextView) findViewById(R.id.user_license_info);
        String stringExtra = getIntent().getStringExtra("title");
        Spanned spanned = (Spanned) getIntent().getCharSequenceExtra(LICENSE_CONTENT);
        String stringExtra2 = getIntent().getStringExtra(LICENSE_URI);
        String stringExtra3 = getIntent().getStringExtra(LICENSE_HTML_CONTENT);
        int intExtra = getIntent().getIntExtra(LICENSE_HTML_CONTENT_RES, -1);
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(stringExtra);
        if (!TextUtils.isEmpty(stringExtra3)) {
            findViewById(R.id.user_license).setVisibility(8);
            this.f16906a.loadDataWithBaseURL((String) null, stringExtra3, NanoHTTPD.c, "charset=utf-8", (String) null);
        } else if (stringExtra2 != null) {
            URI create = URI.create(stringExtra2);
            String scheme = create.getScheme();
            if ("http".equals(scheme) || "https".equals(scheme)) {
                findViewById(R.id.user_license).setVisibility(8);
                this.f16906a.loadUrl(stringExtra2);
                return;
            }
            if ("file".equals(scheme)) {
                stringExtra2 = create.getPath();
            }
            String a2 = a(stringExtra2);
            if (a2 != null) {
                findViewById(R.id.user_license).setVisibility(8);
                this.f16906a.loadDataWithBaseURL((String) null, a2, NanoHTTPD.c, "charset=utf-8", (String) null);
            }
        } else if (spanned != null) {
            this.f16906a.setVisibility(8);
            textView.setText(spanned);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        } else if (intExtra > 0) {
            this.f16906a.setVisibility(0);
            findViewById(R.id.user_license).setVisibility(8);
            this.f16906a.loadDataWithBaseURL((String) null, Util.f(getResources(), intExtra), NanoHTTPD.c, "charset=utf-8", (String) null);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0034 A[Catch:{ Exception -> 0x004e, all -> 0x004c }, LOOP:0: B:11:0x002e->B:14:0x0034, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0043 A[SYNTHETIC, Splitter:B:17:0x0043] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x003d A[EDGE_INSN: B:38:0x003d->B:15:0x003d ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String a(java.lang.String r5) {
        /*
            r4 = this;
            java.io.File r0 = new java.io.File
            r0.<init>(r5)
            boolean r5 = r0.exists()
            r1 = 0
            if (r5 != 0) goto L_0x000d
            return r1
        L_0x000d:
            java.io.BufferedReader r5 = new java.io.BufferedReader     // Catch:{ UnsupportedEncodingException -> 0x0024, FileNotFoundException -> 0x001f }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ UnsupportedEncodingException -> 0x0024, FileNotFoundException -> 0x001f }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ UnsupportedEncodingException -> 0x0024, FileNotFoundException -> 0x001f }
            r3.<init>(r0)     // Catch:{ UnsupportedEncodingException -> 0x0024, FileNotFoundException -> 0x001f }
            java.lang.String r0 = "UTF-8"
            r2.<init>(r3, r0)     // Catch:{ UnsupportedEncodingException -> 0x0024, FileNotFoundException -> 0x001f }
            r5.<init>(r2)     // Catch:{ UnsupportedEncodingException -> 0x0024, FileNotFoundException -> 0x001f }
            goto L_0x0029
        L_0x001f:
            r5 = move-exception
            r5.printStackTrace()
            goto L_0x0028
        L_0x0024:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0028:
            r5 = r1
        L_0x0029:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
        L_0x002e:
            java.lang.String r2 = r5.readLine()     // Catch:{ Exception -> 0x004e }
            if (r2 == 0) goto L_0x003d
            r0.append(r2)     // Catch:{ Exception -> 0x004e }
            java.lang.String r2 = "\r\n"
            r0.append(r2)     // Catch:{ Exception -> 0x004e }
            goto L_0x002e
        L_0x003d:
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x004e }
            if (r5 == 0) goto L_0x004b
            r5.close()     // Catch:{ IOException -> 0x0047 }
            goto L_0x004b
        L_0x0047:
            r5 = move-exception
            r5.printStackTrace()
        L_0x004b:
            return r0
        L_0x004c:
            r0 = move-exception
            goto L_0x005d
        L_0x004e:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x004c }
            if (r5 == 0) goto L_0x005c
            r5.close()     // Catch:{ IOException -> 0x0058 }
            goto L_0x005c
        L_0x0058:
            r5 = move-exception
            r5.printStackTrace()
        L_0x005c:
            return r1
        L_0x005d:
            if (r5 == 0) goto L_0x0067
            r5.close()     // Catch:{ IOException -> 0x0063 }
            goto L_0x0067
        L_0x0063:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0067:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.page.PluginLicenseActivity.a(java.lang.String):java.lang.String");
    }

    /* access modifiers changed from: package-private */
    public void initWebView() {
        this.f16906a.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                if (intent.resolveActivity(PluginLicenseActivity.this.getPackageManager()) == null) {
                    return true;
                }
                PluginLicenseActivity.this.startActivity(intent);
                return true;
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.f16906a != null) {
            this.f16906a.onResume();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (this.f16906a != null) {
            this.f16906a.onPause();
        }
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_slide_in_left, R.anim.activity_slide_out_right);
    }
}
