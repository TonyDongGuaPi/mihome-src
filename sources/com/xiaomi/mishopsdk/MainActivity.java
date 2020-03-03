package com.xiaomi.mishopsdk;

import android.content.Intent;
import android.os.Bundle;
import com.xiaomi.mishopsdk.plugin.PluginRootActivity;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.mishopsdk.util.Log;

public class MainActivity extends PluginRootActivity {
    private static final String TAG = "MainActivity";

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        if (bundle == null) {
            initIntentWhenBundleNull();
        }
        super.onCreate(bundle);
    }

    private void initIntentWhenBundleNull() {
        Intent intent = getIntent();
        if (intent == null) {
            Log.e(TAG, "getIntent return null, initIntentWhenBundleNull return.");
        } else if (!"100".equals(intent.getStringExtra(Constants.Plugin.ARGUMENT_PLUGINID))) {
            intent.putExtra(Constants.Plugin.ARGUMENT_PLUGINID, "100");
            Log.d(TAG, "did not find homepage pluginId, initIntentWhenBundleNull set it.");
        }
    }
}
