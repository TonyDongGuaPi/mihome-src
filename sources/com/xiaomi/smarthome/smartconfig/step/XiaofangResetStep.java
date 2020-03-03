package com.xiaomi.smarthome.smartconfig.step;

import android.content.Context;
import android.os.Message;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import butterknife.BindView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.server.internal.util.LocaleUtil;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.framework.webview.SmartHomeWebView;
import com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider;
import com.xiaomi.smarthome.smartconfig.step.SmartConfigStep;
import java.util.Locale;

public class XiaofangResetStep extends SmartConfigStep {
    @BindView(2131431178)
    Button mButton;
    @BindView(2131428307)
    CheckBox mCheck;
    @BindView(2131430969)
    View mReturnBtn;
    @BindView(2131430975)
    TextView mTitle;
    @BindView(2131430280)
    SmartHomeWebView mWebView;

    public void a(Message message) {
    }

    public void c() {
    }

    public void d() {
    }

    public void e() {
    }

    public SmartConfigStep.Step f() {
        return null;
    }

    public void a(Context context) {
        String str;
        String str2;
        a(context, R.layout.smart_config_xiaofang_reset);
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
        String str3 = (String) SmartConfigDataProvider.a().a("device_model");
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
            sb.append(str3);
            sb.append("&locale=");
            sb.append(LocaleUtil.a(I));
            str = sb.toString();
        } else {
            str = "https://home.mi.com/views/deviceReset.html?model=" + str3 + "&locale=" + LocaleUtil.a(I);
        }
        this.mWebView.loadUrl(str);
        this.mCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                XiaofangResetStep.this.mButton.setEnabled(z);
            }
        });
        this.mButton.setEnabled(false);
        this.mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                XiaofangResetStep.this.a(SmartConfigStep.Step.STEP_CHOOSE_WIFI);
            }
        });
        this.mReturnBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                XiaofangResetStep.this.d_(false);
            }
        });
    }
}
