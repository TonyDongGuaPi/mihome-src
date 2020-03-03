package com.xiaomi.smarthome.smartconfig;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.server.internal.util.LocaleUtil;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import java.util.Locale;

public class XiaofangResetConnection extends BaseActivity {
    @BindView(2131431178)
    Button mButton;
    @BindView(2131428307)
    CheckBox mCheck;
    @BindView(2131430969)
    View mReturnBtn;
    @BindView(2131430975)
    TextView mTitle;
    @BindView(2131430280)
    WebView mWebView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        String str;
        String str2;
        super.onCreate(bundle);
        setContentView(R.layout.smart_config_xiaofang_reset);
        ButterKnife.bind((Activity) this);
        this.mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                webView.loadUrl(str);
                return true;
            }
        });
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
            sb.append("isa.camera.isc5");
            sb.append("&locale=");
            sb.append(LocaleUtil.a(I));
            str = sb.toString();
        } else {
            str = "https://home.mi.com/views/deviceReset.html?model=" + "isa.camera.isc5" + "&locale=" + LocaleUtil.a(I);
        }
        this.mWebView.loadUrl(str);
        this.mCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                XiaofangResetConnection.this.mButton.setEnabled(z);
            }
        });
        this.mButton.setEnabled(false);
        this.mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                XiaofangResetConnection.this.startActivity(new Intent(XiaofangResetConnection.this, XiaofangChooseConnection.class));
                XiaofangResetConnection.this.finish();
            }
        });
        this.mReturnBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                XiaofangResetConnection.this.finish();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.mWebView != null) {
            this.mWebView.onResume();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (this.mWebView != null) {
            this.mWebView.onPause();
        }
    }
}
