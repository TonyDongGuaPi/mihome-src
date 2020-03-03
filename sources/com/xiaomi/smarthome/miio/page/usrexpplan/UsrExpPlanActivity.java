package com.xiaomi.smarthome.miio.page.usrexpplan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import com.xiaomi.smarthome.OpenExternalBrowserCompat;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.page.DeviceMoreActivity;
import com.xiaomi.smarthome.httpserver.NanoHTTPD;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.util.ViewUtils;
import com.xiaomi.smarthome.miio.LanguageUtil;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.miio.activity.UserLicense;
import java.net.URI;

public class UsrExpPlanActivity extends FragmentActivity {
    private static final String b = "usr_exp_plan.html";

    /* renamed from: a  reason: collision with root package name */
    private WebView f19952a;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        DisplayUtils.e(this);
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT != 26 || !DisplayUtils.d(this)) {
            setRequestedOrientation(1);
        }
        TitleBarUtil.a((Activity) this);
        setContentView(R.layout.activity_usr_exp_plan_layout);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                UsrExpPlanActivity.this.finish();
            }
        });
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.usr_exp_plan);
        getWindow().addFlags(524288);
        this.f19952a = (WebView) findViewById(R.id.usr_exp_plan_webview);
        Intent intent = getIntent();
        if (intent != null) {
            String stringExtra = intent.getStringExtra(DeviceMoreActivity.ARGS_LICENSE_URI);
            String stringExtra2 = intent.getStringExtra(DeviceMoreActivity.ARGS_USR_EXP_PLAN_RN_URI);
            try {
                Spanned spanned = (Spanned) getIntent().getCharSequenceExtra(DeviceMoreActivity.ARGS_USR_EXP_PLAN_CONTENT);
                if (!TextUtils.isEmpty(stringExtra2)) {
                    URI create = URI.create(stringExtra2);
                    String scheme = create.getScheme();
                    if (!"http".equals(scheme)) {
                        if (!"https".equals(scheme)) {
                            if ("file".equals(scheme)) {
                                stringExtra2 = create.getPath();
                            }
                            String a2 = a(stringExtra2);
                            if (a2 != null) {
                                findViewById(R.id.usr_exp_plan_scrollview).setVisibility(8);
                                this.f19952a.loadDataWithBaseURL((String) null, a2, NanoHTTPD.c, "charset=utf-8", (String) null);
                                return;
                            }
                            return;
                        }
                    }
                    findViewById(R.id.usr_exp_plan_scrollview).setVisibility(8);
                    this.f19952a.loadUrl(stringExtra2);
                } else if (!TextUtils.isEmpty(stringExtra)) {
                    String a3 = a(stringExtra);
                    if (a3 != null) {
                        findViewById(R.id.user_license).setVisibility(8);
                        this.f19952a.loadDataWithBaseURL((String) null, a3, NanoHTTPD.c, "charset=utf-8", (String) null);
                    }
                } else if (!TextUtils.isEmpty(spanned)) {
                    this.f19952a.setVisibility(8);
                    findViewById(R.id.usr_exp_plan_scrollview).setVisibility(0);
                    TextView textView = (TextView) findViewById(R.id.usr_exp_plan_info);
                    textView.setText(spanned);
                    textView.setMovementMethod(LinkMovementMethod.getInstance());
                } else {
                    OpenExternalBrowserCompat.a(this, UserLicense.getUrlByTpye(UserLicense.TYPE_USER_EXPPLANN));
                    finish();
                }
            } catch (Exception e) {
                e.printStackTrace();
                OpenExternalBrowserCompat.a(this, UserLicense.getUrlByTpye(UserLicense.TYPE_USER_EXPPLANN));
                finish();
            }
        } else {
            OpenExternalBrowserCompat.a(this, UserLicense.getUrlByTpye(UserLicense.TYPE_USER_EXPPLANN));
            finish();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0034 A[Catch:{ Exception -> 0x0049, all -> 0x0047 }, LOOP:0: B:11:0x002e->B:14:0x0034, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x003e A[SYNTHETIC, Splitter:B:17:0x003e] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0038 A[EDGE_INSN: B:38:0x0038->B:15:0x0038 ?: BREAK  , SYNTHETIC] */
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
            java.lang.String r2 = r5.readLine()     // Catch:{ Exception -> 0x0049 }
            if (r2 == 0) goto L_0x0038
            r0.append(r2)     // Catch:{ Exception -> 0x0049 }
            goto L_0x002e
        L_0x0038:
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0049 }
            if (r5 == 0) goto L_0x0046
            r5.close()     // Catch:{ IOException -> 0x0042 }
            goto L_0x0046
        L_0x0042:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0046:
            return r0
        L_0x0047:
            r0 = move-exception
            goto L_0x0058
        L_0x0049:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x0047 }
            if (r5 == 0) goto L_0x0057
            r5.close()     // Catch:{ IOException -> 0x0053 }
            goto L_0x0057
        L_0x0053:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0057:
            return r1
        L_0x0058:
            if (r5 == 0) goto L_0x0062
            r5.close()     // Catch:{ IOException -> 0x005e }
            goto L_0x0062
        L_0x005e:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0062:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.miio.page.usrexpplan.UsrExpPlanActivity.a(java.lang.String):java.lang.String");
    }

    public boolean isValid() {
        if (isFinishing()) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 17 || !isDestroyed()) {
            return true;
        }
        return false;
    }

    public void onContentChanged() {
        super.onContentChanged();
        TitleBarUtil.c(this);
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context) {
        super.attachBaseContext(LanguageUtil.a(context));
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        try {
            ViewUtils.b((Activity) this);
            ViewUtils.a((Activity) this);
            super.onDestroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.f19952a != null) {
            this.f19952a.onResume();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (this.f19952a != null) {
            this.f19952a.onPause();
        }
    }
}
