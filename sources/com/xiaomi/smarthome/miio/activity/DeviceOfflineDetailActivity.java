package com.xiaomi.smarthome.miio.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import com.google.android.exoplayer2.C;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.statistic.StatHelper;
import com.xiaomi.smarthome.core.server.internal.util.LocaleUtil;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.device.choosedevice.ResetPageRoute;
import com.xiaomi.smarthome.feedback.FeedbackActivity;
import com.xiaomi.smarthome.frame.baseui.BaseActivity;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.runtime.util.DeviceCategory;
import com.xiaomi.smarthome.framework.webview.SmartHomeWebView;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.setting.ServerRouteUtil;
import java.util.Locale;

public class DeviceOfflineDetailActivity extends BaseActivity {
    public static final String ARGS_KEY_DID = "did";
    public static final String EXTRA_DEVICE_TYPE = "arg_device_type";
    public static final String EXTRA_MODEL = "extra_model";
    public static final String FAQ_PATH_BASE = "/offlineGuide.html?model=";
    private static final int e = 0;
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public String f11741a;
    /* access modifiers changed from: private */
    public String b;
    /* access modifiers changed from: private */
    public SmartHomeWebView c;
    private XQProgressDialog d;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        TitleBarUtil.a((Activity) this);
        setContentView(R.layout.activity_device_offline_detail);
        this.f11741a = getIntent().getStringExtra("extra_model");
        this.b = getIntent().getStringExtra("did");
        a();
    }

    public void onContentChanged() {
        super.onContentChanged();
        TitleBarUtil.c(this);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.d != null) {
            this.d.dismiss();
        }
    }

    private void a() {
        c();
        this.c = (SmartHomeWebView) findViewById(R.id.wv_common_problem);
        this.c.loadUrl(b());
        this.c.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (!str.contains("home.mi.com/views/deviceReset.html?model=")) {
                    return super.shouldOverrideUrlLoading(webView, str);
                }
                Device b = SmartHomeDeviceManager.a().b(DeviceOfflineDetailActivity.this.b);
                if (b == null || DeviceCategory.fromPid(b.pid) != DeviceCategory.Bluetooth) {
                    Intent intent = new Intent(DeviceOfflineDetailActivity.this, ResetPageRoute.class);
                    intent.putExtra("extra_model", DeviceOfflineDetailActivity.this.f11741a);
                    intent.addFlags(C.ENCODING_PCM_MU_LAW);
                    DeviceOfflineDetailActivity.this.startActivity(intent);
                    return true;
                }
                XmPluginHostApi.instance().visualSecureBind(b.did);
                StatHelper.i(b.newDeviceStat());
                return true;
            }
        });
        findViewById(R.id.feedback_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(DeviceOfflineDetailActivity.this, FeedbackActivity.class);
                intent.putExtra("extra_device_model", DeviceOfflineDetailActivity.this.f11741a);
                intent.putExtra("extra_device_did", DeviceOfflineDetailActivity.this.b);
                DeviceOfflineDetailActivity.this.startActivity(intent);
            }
        });
    }

    private String b() {
        Locale I = CoreApi.a().I();
        if (I == null) {
            I = Locale.getDefault();
        }
        String stringExtra = getIntent().getStringExtra(EXTRA_DEVICE_TYPE);
        String buildUrl = buildUrl(FAQ_PATH_BASE + this.f11741a + "&locale=" + LocaleUtil.a(I));
        return buildUrl + "&WDC=" + stringExtra;
    }

    public String buildUrl(String str) {
        return ServerRouteUtil.b(SHApplication.getAppContext()) + str;
    }

    public void onBackPressed() {
        if (this.c == null || !this.c.canGoBack()) {
            super.onBackPressed();
        } else {
            this.c.goBack();
        }
    }

    private void c() {
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.device_offline_page_title);
        View findViewById = findViewById(R.id.module_a_3_return_btn);
        if (findViewById != null) {
            findViewById.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (DeviceOfflineDetailActivity.this.c == null || !DeviceOfflineDetailActivity.this.c.canGoBack()) {
                        DeviceOfflineDetailActivity.this.finish();
                    } else {
                        DeviceOfflineDetailActivity.this.c.goBack();
                    }
                }
            });
        }
    }
}
