package com.mibi.common.ui;

import com.mibi.common.base.BaseActivity;

public class TranslucentActivity extends BaseActivity {
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }
}
