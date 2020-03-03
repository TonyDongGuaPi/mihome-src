package com.xiaomi.smarthome.framework.webview;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.page.BaseActivity;

public class SmartHomeWebActivity extends BaseActivity {
    public static final String ARGS_KEY_TITLE = "title";
    public static final String ARGS_KEY_URL = "url";
    String mTitle;
    TextView mTitleView;
    String mUrl;
    SmartHomeWebView mWebView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.smarthome_web_activity);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SmartHomeWebActivity.this.finish();
            }
        });
        this.mTitleView = (TextView) findViewById(R.id.module_a_3_return_title);
        findViewById(R.id.module_a_3_return_more_more_btn).setVisibility(8);
        this.mWebView = (SmartHomeWebView) findViewById(R.id.webview);
        this.mUrl = getIntent().getStringExtra("url");
        this.mTitle = getIntent().getStringExtra("title");
        if (!TextUtils.isEmpty(this.mTitle)) {
            this.mTitleView.setText(this.mTitle);
        }
        if (TextUtils.isEmpty(this.mUrl)) {
            finish();
        } else {
            this.mWebView.loadUrl(this.mUrl);
        }
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
