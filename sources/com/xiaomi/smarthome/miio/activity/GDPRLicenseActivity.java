package com.xiaomi.smarthome.miio.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.unionpay.tsmservice.data.Constant;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.server.internal.util.LocaleUtil;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.framework.webview.SmartHomeWebActivity;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.miio.LanguageUtil;
import com.xiaomi.smarthome.miio.Miio;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.setting.ServerRouteUtil;
import java.util.Locale;

public class GDPRLicenseActivity extends FragmentActivity {
    public static final String KEY_MODEL = "key_model";

    /* renamed from: a  reason: collision with root package name */
    private static final int f11745a = 1;
    private static final int b = 2;
    private String c;

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        DisplayUtils.e(this);
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT != 26 || !DisplayUtils.d(this)) {
            setRequestedOrientation(1);
        }
        TitleBarUtil.a((Activity) this);
        overridePendingTransition(R.anim.activity_slide_in_right, R.anim.activity_slide_out_left);
        setContentView(R.layout.activity_license_and_privacy);
        this.c = getIntent().getStringExtra("key_model");
        if (TextUtils.isEmpty(this.c)) {
            Miio.g("GDPRLicenseActivity model is null");
            finish();
            return;
        }
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GDPRLicenseActivity.this.finish();
            }
        });
        findViewById(R.id.cancel_license).setVisibility(8);
        findViewById(R.id.bottom_divider).setVisibility(8);
        findViewById(R.id.list_space2).setVisibility(8);
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(getString(R.string.device_more_activity_license_privacy));
        findViewById(R.id.license).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GDPRLicenseActivity.this.a(1, GDPRLicenseActivity.this.getString(R.string.device_more_activity_license));
            }
        });
        findViewById(R.id.privacy).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GDPRLicenseActivity.this.a(2, GDPRLicenseActivity.this.getString(R.string.device_more_activity_privacy));
            }
        });
    }

    public void onContentChanged() {
        super.onContentChanged();
        TitleBarUtil.c(this);
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context) {
        super.attachBaseContext(LanguageUtil.a(context));
    }

    private String a(int i) {
        String str;
        Uri.Builder buildUpon = Uri.parse(ServerRouteUtil.b(this) + "/app_page/argument.html?").buildUpon();
        buildUpon.appendQueryParameter("type", String.valueOf(i));
        buildUpon.appendQueryParameter("model", this.c);
        ServerBean F = CoreApi.a().F();
        if (F == null) {
            str = "";
        } else {
            str = F.b;
        }
        buildUpon.appendQueryParameter(Constant.KEY_COUNTRY_CODE, str);
        Locale I = CoreApi.a().I();
        if (I == null) {
            I = Locale.getDefault();
        }
        buildUpon.appendQueryParameter("locale", LocaleUtil.a(I).toLowerCase());
        return buildUpon.toString();
    }

    /* access modifiers changed from: private */
    public void a(int i, String str) {
        Intent intent = new Intent(this, SmartHomeWebActivity.class);
        String a2 = a(i);
        intent.putExtra("title", str);
        intent.putExtra("url", a2);
        startActivity(intent);
    }
}
