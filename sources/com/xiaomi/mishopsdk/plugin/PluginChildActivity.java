package com.xiaomi.mishopsdk.plugin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import com.xiaomi.mishopsdk.util.Constants;

public class PluginChildActivity extends BasePluginActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mIsThemeInstalled = true;
        installPluginFragment();
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        installPluginFragment();
    }

    /* access modifiers changed from: protected */
    public void installPluginFragment() {
        if (this.mPluginInfo == null) {
            finish();
            return;
        }
        String fragmentClass = getFragmentClass();
        if (TextUtils.isEmpty(fragmentClass)) {
            finish();
            return;
        }
        try {
            Fragment fragment = (Fragment) getClassLoader().loadClass(fragmentClass).newInstance();
            Bundle extras = getIntent().getExtras();
            extras.putSerializable(Constants.Plugin.ARGUMENT_PLUGININFO, this.mPluginInfo);
            fragment.setArguments(extras);
            getSupportFragmentManager().beginTransaction().replace(16908300, fragment).commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
