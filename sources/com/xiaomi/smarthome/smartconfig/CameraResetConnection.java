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
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.stat.STAT;
import java.util.Locale;

public class CameraResetConnection extends BaseActivity {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public String f22239a;
    @BindView(2131431178)
    Button mButton;
    @BindView(2131428307)
    CheckBox mCheck;
    @BindView(2131430969)
    View mReturnBtn;
    @BindView(2131430975)
    TextView mTitle;
    @BindView(2131432620)
    TextView mTvDesc;
    @BindView(2131430280)
    WebView mWebView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        String str;
        String str2;
        super.onCreate(bundle);
        setContentView(R.layout.smart_config_camera_reset);
        ButterKnife.bind((Activity) this);
        this.mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                webView.loadUrl(str);
                return true;
            }
        });
        this.mTitle.setText(R.string.camera_connect_guide);
        if (this.mTvDesc != null) {
            this.mTvDesc.setText(R.string.camera_step12_desc);
        }
        Locale I = CoreApi.a().I();
        if (I == null) {
            I = Locale.getDefault();
        }
        this.f22239a = getIntent().getStringExtra("model");
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
            sb.append(this.f22239a);
            sb.append("&locale=");
            sb.append(I.toString());
            str = sb.toString();
        } else {
            str = "https://home.mi.com/views/deviceReset.html?model=" + this.f22239a + "&locale=" + I.toString();
        }
        this.mWebView.loadUrl(str);
        this.mCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                CameraResetConnection.this.mButton.setEnabled(z);
                if (z) {
                    STAT.d.aL(CameraResetConnection.this.f22239a);
                }
            }
        });
        this.mButton.setEnabled(false);
        this.mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                STAT.d.aM(CameraResetConnection.this.f22239a);
                Intent intent = new Intent(CameraResetConnection.this, CameraApChooseConnection.class);
                intent.putExtra("model", CameraResetConnection.this.f22239a);
                CameraResetConnection.this.startActivity(intent);
                CameraResetConnection.this.finish();
            }
        });
        this.mReturnBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CameraResetConnection.this.finish();
            }
        });
        STAT.c.d(this.f22239a);
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
