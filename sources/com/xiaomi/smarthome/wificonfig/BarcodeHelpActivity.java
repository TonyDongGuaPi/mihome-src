package com.xiaomi.smarthome.wificonfig;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.server.internal.util.LocaleUtil;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.smartconfig.SmartConfigMainActivity;
import java.util.Locale;

public class BarcodeHelpActivity extends BaseActivity {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public Button f22825a;
    private CheckBox b;
    private WebView c;
    /* access modifiers changed from: private */
    public String d;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        String str;
        String str2;
        super.onCreate(bundle);
        setContentView(R.layout.barcode_help_activity);
        this.f22825a = (Button) findViewById(R.id.next_step);
        this.b = (CheckBox) findViewById(R.id.confirm);
        this.c = (WebView) findViewById(R.id.webview);
        this.f22825a.setEnabled(false);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BarcodeHelpActivity.this.finish();
            }
        });
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.qr_help_title);
        this.d = getIntent().getStringExtra("model");
        Locale I = CoreApi.a().I();
        if (I == null) {
            I = Locale.getDefault();
        }
        if (!TextUtils.isEmpty(this.d)) {
            if (CoreApi.a().D()) {
                ServerBean F = CoreApi.a().F();
                StringBuilder sb = new StringBuilder();
                sb.append("https://");
                if (F != null) {
                    str2 = F.f1530a + ".";
                } else {
                    str2 = "";
                }
                sb.append(str2);
                sb.append("home.mi.com/views/deviceReset.html?model=");
                sb.append(this.d);
                sb.append("&locale=");
                sb.append(LocaleUtil.a(I));
                str = sb.toString();
            } else {
                str = "https://home.mi.com/views/deviceReset.html?model=" + this.d + "&locale=" + LocaleUtil.a(I);
            }
            this.c.loadUrl(str);
        }
        this.b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                BarcodeHelpActivity.this.f22825a.setEnabled(z);
            }
        });
        this.f22825a.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(BarcodeHelpActivity.this.getContext(), SmartConfigMainActivity.class);
                intent.putExtra("strategy_id", 5);
                intent.putExtra("model", BarcodeHelpActivity.this.d);
                BarcodeHelpActivity.this.startActivityForResult(intent, 100);
            }
        });
    }

    public void onBackPressed() {
        if (this.c == null || !this.c.canGoBack()) {
            super.onBackPressed();
        } else {
            this.c.goBack();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.c != null) {
            this.c.onResume();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (this.c != null) {
            this.c.onPause();
        }
    }
}
