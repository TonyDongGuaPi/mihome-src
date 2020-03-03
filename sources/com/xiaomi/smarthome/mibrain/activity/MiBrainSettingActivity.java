package com.xiaomi.smarthome.mibrain.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CompoundButton;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.SwitchButton;
import com.xiaomi.smarthome.mibrain.MiBrainManager;
import java.util.HashMap;

public class MiBrainSettingActivity extends BaseActivity {
    @BindView(2131430863)
    SwitchButton mSwitchBtn;
    @BindView(2131433968)
    WebView mWebView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_mi_brain_setting);
        ButterKnife.bind((Activity) this);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MiBrainSettingActivity.this.finish();
            }
        });
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.audio_controller);
        this.mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                webView.loadUrl(str);
                return true;
            }
        });
        this.mWebView.loadUrl("https://home.mi.com/redirect/voiceControl");
        this.mSwitchBtn.setChecked(MiBrainManager.a().f());
        this.mSwitchBtn.setOnPerformCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                MiBrainSettingActivity.this.a(z);
                MiBrainSettingActivity.this.mSwitchBtn.setChecked(z);
                StatHelper.j(z);
                new HashMap().put("checked", Boolean.valueOf(z));
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(boolean z) {
        MiBrainManager.a().a(z, (AsyncCallback) new AsyncCallback() {
            public void onSuccess(Object obj) {
                ToastUtil.a((int) R.string.mi_brain_setting_success);
            }

            public void onFailure(Error error) {
                ToastUtil.a((int) R.string.mi_brain_setting_fail);
                MiBrainSettingActivity.this.mSwitchBtn.setChecked(MiBrainManager.a().f());
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
