package com.mi.global.bbs.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.mi.global.bbs.R;
import com.mi.global.bbs.base.BaseActivity;

public class FeedActivity extends BaseActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCustomContentView(R.layout.bbs_activity_feed);
        hideToolBar();
    }

    public static void jump(Context context) {
        context.startActivity(new Intent(context, FeedActivity.class));
    }
}
