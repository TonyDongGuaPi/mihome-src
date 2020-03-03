package com.sina.weibo.sdk.share;

import android.content.Intent;
import android.os.Bundle;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.utils.LogUtil;

public class WbShareResultActivity extends BaseActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.b("Share", "startShareResultActivity");
        Intent intent = getIntent();
        if (intent.getIntExtra(WBConstants.G, -1) == 0) {
            finish();
            return;
        }
        if (WBConstants.r.equals(intent.getAction())) {
            intent.setClass(this, WbShareTransActivity.class);
        } else {
            intent.setClass(this, WbShareToStoryActivity.class);
        }
        startActivity(intent);
        finish();
    }
}
