package com.xiaomi.smarthome.device;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.server.internal.util.LocaleUtil;
import com.xiaomi.smarthome.device.bluetooth.connect.single.BleBindActivityV2;
import com.xiaomi.smarthome.frame.baseui.BaseActivity;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.smartconfig.SmartConfigMainActivity;
import java.util.Locale;

public class ResetDevicePageScan extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private SimpleDraweeView f14948a;
    private View b;
    private String c;
    private Button d;
    private WebView e;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        String str;
        String str2;
        super.onCreate(bundle);
        setContentView(R.layout.choose_device_failed_page);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ResetDevicePageScan.this.onBackPressed();
            }
        });
        if (getIntent() != null) {
            this.c = getIntent().getStringExtra("model");
        }
        this.f14948a = (SimpleDraweeView) findViewById(R.id.kuailian_common_icon);
        this.b = findViewById(R.id.kuailian_reset_container);
        this.d = (Button) findViewById(R.id.next_btn);
        this.e = (WebView) findViewById(R.id.kuailian_reset_web_view);
        this.e.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                webView.loadUrl(str);
                return true;
            }
        });
        if (this.c != null) {
            this.b.setVisibility(0);
            this.d.setText(R.string.smart_config_reconnect);
            DeviceFactory.a(this.c, this.f14948a, (int) R.drawable.more_icon);
            if (CoreApi.a().c(this.c)) {
                ((TextView) findViewById(R.id.module_a_3_return_title)).setText(CoreApi.a().d(this.c).p());
            }
            this.d.setVisibility(0);
            Locale I = CoreApi.a().I();
            if (I == null) {
                I = Locale.getDefault();
            }
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
                sb.append(this.c);
                sb.append("&locale=");
                sb.append(LocaleUtil.a(I));
                str = sb.toString();
            } else {
                str = "https://home.mi.com/views/deviceReset.html?model=" + this.c + "&locale=" + LocaleUtil.a(I);
            }
            this.e.loadUrl(str);
            this.d.setText(R.string.kuailian_reset_text);
            this.d.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(ResetDevicePageScan.this, SmartConfigMainActivity.class);
                    intent.putExtra("strategy_id", 5);
                    if (ResetDevicePageScan.this.getIntent() != null && ResetDevicePageScan.this.getIntent().hasExtra(BleBindActivityV2.KEY_QRCODE_OOB)) {
                        intent.putExtra(BleBindActivityV2.KEY_QRCODE_OOB, ResetDevicePageScan.this.getIntent().getStringExtra(BleBindActivityV2.KEY_QRCODE_OOB));
                    }
                    ResetDevicePageScan.this.startActivity(intent);
                    ResetDevicePageScan.this.finish();
                }
            });
        }
    }
}
