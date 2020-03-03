package com.xiaomi.smarthome.wificonfig;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.webview.SmartHomeWebView;
import com.xiaomi.smarthome.setting.ServerRouteUtil;

public class CameraBarcodeHelpActivity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private static final String f22881a = "/views/faqDetail.html?question=";
    private View b;
    private View c;
    private Button d;
    private Button e;
    private SmartHomeWebView f;
    private String g;
    private String h;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_camera_gen_barcode_help);
        this.b = findViewById(R.id.original_help);
        this.c = findViewById(R.id.camera_faq);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CameraBarcodeHelpActivity.this.finish();
            }
        });
        this.h = getIntent().getStringExtra("model");
        this.g = getIntent().getStringExtra("url");
        if (!TextUtils.isEmpty(this.h)) {
            a();
        }
        findViewById(R.id.ok_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CameraBarcodeHelpActivity.this.finish();
            }
        });
    }

    private void a() {
        String str;
        this.b.setVisibility(8);
        this.c.setVisibility(0);
        this.d = (Button) findViewById(R.id.retry);
        this.e = (Button) findViewById(R.id.use_other);
        this.f = (SmartHomeWebView) findViewById(R.id.webview);
        SmartHomeWebView smartHomeWebView = this.f;
        if (this.g == null) {
            str = f22881a + getString(R.string.param_camera_nothing_heard);
        } else {
            str = this.g;
        }
        smartHomeWebView.loadUrl(buildUrl(str));
        this.d.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CameraBarcodeHelpActivity.this.finish();
            }
        });
        this.e.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CameraBarcodeHelpActivity.this.finish();
            }
        });
    }

    public String buildUrl(String str) {
        return ServerRouteUtil.b(SHApplication.getAppContext()) + str;
    }

    public void onBackPressed() {
        if (this.f == null || !this.f.canGoBack()) {
            super.onBackPressed();
        } else {
            this.f.goBack();
        }
    }
}
