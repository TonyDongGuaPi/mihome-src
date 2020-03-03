package com.xiaomi.smarthome.camera.v4.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.activity.CameraBaseActivity;
import com.xiaomi.smarthome.camera.v4.utils.Util;
import java.util.Locale;

public class PrivacyPolicyActivity extends CameraBaseActivity {
    WebView webView;

    public void onCreate(Bundle bundle) {
        String str;
        super.onCreate(bundle);
        setContentView(R.layout.activity_prolicy);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        int intExtra = intent.getIntExtra("pagetype", 0);
        if (intExtra == 0) {
            finish();
            return;
        }
        TextView textView = (TextView) findViewById(R.id.title_bar_title);
        findViewById(R.id.title_bar_more).setVisibility(8);
        findViewById(R.id.title_bar_return).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PrivacyPolicyActivity.this.finish();
            }
        });
        this.webView = (WebView) findViewById(R.id.webView);
        this.webView.requestFocus();
        WebSettings settings = this.webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(2);
        settings.setAppCacheEnabled(false);
        this.webView.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                return true;
            }
        });
        if (intExtra == 3) {
            textView.setText(R.string.privacy_content);
            String str2 = "";
            Locale locale = getResources().getConfiguration().locale;
            if (locale != null) {
                str2 = locale.getLanguage();
            }
            if (!TextUtils.isEmpty(str2) && str2.equals("de")) {
                str = Util.getRawTxt2(getResources(), R.raw.camera_v4_privacy_eu);
            } else if (!TextUtils.isEmpty(str2) && str2.equals("us")) {
                str = Util.getRawTxt2(getResources(), R.raw.camera_v4_privacy_us);
            } else if (!TextUtils.isEmpty(str2) && str2.equals("sg")) {
                str = Util.getRawTxt2(getResources(), R.raw.camera_v4_privacy_eu);
            } else if (!TextUtils.isEmpty(str2) && str2.equals("tw")) {
                str = Util.getRawTxt2(getResources(), R.raw.camera_v4_privacy_eu);
            } else if (!TextUtils.isEmpty(str2) && str2.equals("hk")) {
                str = Util.getRawTxt2(getResources(), R.raw.camera_v4_privacy_eu);
            } else if (!TextUtils.isEmpty(str2) && str2.equals("in")) {
                str = Util.getRawTxt2(getResources(), R.raw.camera_v4_privacy_eu);
            } else if (TextUtils.isEmpty(str2) || !str2.equals("zh")) {
                str = Util.getRawTxt2(getResources(), R.raw.camera_v4_privacy);
            } else {
                str = Util.getRawTxt2(getResources(), R.raw.camera_v4_privacy);
            }
            this.webView.loadData(str, "text/html;charset=UTF-8", (String) null);
        }
    }
}
