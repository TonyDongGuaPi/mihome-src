package com.xiaomi.pluginhost;

import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;

public class PluginHostTransparentActivity extends PluginHostActivity {
    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (!getIntent().getBooleanExtra("hasOverridePendingTransition", false)) {
            overridePendingTransition(0, 0);
        }
    }

    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

    /* access modifiers changed from: protected */
    public void handleActivityInfo() {
        Resources.Theme theme = super.getTheme();
        this.mTheme = this.mLoadedInfo.resources.newTheme();
        this.mTheme.setTo(theme);
        int i = Build.VERSION.SDK_INT;
        try {
            this.mTheme.applyStyle(16973840, true);
        } catch (Exception unused) {
        }
    }
}
