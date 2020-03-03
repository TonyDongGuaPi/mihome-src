package com.tencent.wxop.stat;

import android.app.ListActivity;

public class EasyListActivity extends ListActivity {
    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        StatService.c(this);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        StatService.b(this);
    }
}
