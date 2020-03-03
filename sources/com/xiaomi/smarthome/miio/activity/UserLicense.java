package com.xiaomi.smarthome.miio.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.MailTo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.server.internal.globaldynamicsetting.GlobalDynamicSettingManager;
import com.xiaomi.smarthome.core.server.internal.util.LocaleUtil;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.util.ViewUtils;
import com.xiaomi.smarthome.miio.LanguageUtil;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import java.util.Locale;

public class UserLicense extends FragmentActivity {
    public static String TYPE_USER_EXPPLANN = "userExpPlan";
    public static String TYPE_USER_LICENSE = "userLicense";
    public static String TYPE_USER_PRIVACY = "userPrivacy";
    public static String baseUrl = "https://g.home.mi.com/views/user-terms.html?";
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public boolean f11806a = false;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        DisplayUtils.e(this);
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT != 26 || !DisplayUtils.d(this)) {
            setRequestedOrientation(1);
        }
        TitleBarUtil.a((Activity) this);
        setContentView(R.layout.user_license);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                UserLicense.this.finish();
            }
        });
        TextView textView = (TextView) findViewById(R.id.module_a_3_return_title);
        TextView textView2 = (TextView) findViewById(R.id.user_license_info);
        WebView webView = (WebView) findViewById(R.id.user_license_info_web);
        if (getIntent() != null) {
            String stringExtra = getIntent().getStringExtra("type");
            textView.setText(getIntent().getStringExtra("title"));
            this.f11806a = getIntent().getBooleanExtra("call_system_browser", false);
            MibiConstants.bw.equals(stringExtra);
        }
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                try {
                    if (TextUtils.isEmpty(str) || !str.startsWith("mailto:")) {
                        if (UserLicense.this.f11806a) {
                            try {
                                UserLicense.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            webView.loadUrl(str);
                        }
                        return true;
                    }
                    if (UserLicense.this.isValid()) {
                        MailTo parse = MailTo.parse(str);
                        UserLicense.this.startActivity(UserLicense.this.a(parse.getTo(), parse.getSubject(), parse.getBody(), parse.getCc()));
                        webView.reload();
                        return true;
                    }
                    return true;
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
        getWindow().addFlags(524288);
    }

    /* access modifiers changed from: private */
    public Intent a(String str, String str2, String str3, String str4) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.EMAIL", new String[]{str});
        intent.putExtra("android.intent.extra.TEXT", str3);
        intent.putExtra("android.intent.extra.SUBJECT", str2);
        intent.putExtra("android.intent.extra.CC", str4);
        intent.setType("message/rfc822");
        return intent;
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

    public static String getUrlByTpye(String str) {
        Locale g = GlobalDynamicSettingManager.a().g();
        if (g == null) {
            g = Locale.getDefault();
        }
        String b = LocaleUtil.b(g);
        return baseUrl + "locale=" + b + "&type=" + str;
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        ViewUtils.a((Activity) this);
        super.onDestroy();
    }
}
