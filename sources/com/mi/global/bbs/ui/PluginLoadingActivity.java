package com.mi.global.bbs.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import com.mi.global.bbs.R;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.sina.weibo.sdk.constant.WBConstants;

public class PluginLoadingActivity extends Activity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(R.layout.bbs_activity_plugin_loading_layout);
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.height = -1;
        attributes.alpha = 0.5f;
        window.setAttributes(attributes);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                PluginLoadingActivity.this.loadTargetActivity();
            }
        }, 1000);
    }

    /* access modifiers changed from: private */
    public void loadTargetActivity() {
        if (getIntent() == null || !getIntent().hasExtra("acName")) {
            finish();
            return;
        }
        GoogleTrackerUtil.sendRecordEvent("home", "Plugin", WBConstants.H);
        String stringExtra = getIntent().getStringExtra("acName");
        Intent intent = new Intent();
        intent.setClassName(this, stringExtra);
        startActivity(intent);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        finish();
    }
}
