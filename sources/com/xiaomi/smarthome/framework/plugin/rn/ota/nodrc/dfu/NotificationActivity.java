package com.xiaomi.smarthome.framework.plugin.rn.ota.nodrc.dfu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.bridge.ReactContext;
import com.google.android.exoplayer2.C;

public class NotificationActivity extends Activity {

    /* renamed from: a  reason: collision with root package name */
    private ReactInstanceManager f17490a;

    private ReactContext a() {
        this.f17490a = ((ReactApplication) getApplication()).getReactNativeHost().getReactInstanceManager();
        return this.f17490a.getCurrentReactContext();
    }

    public Class getMainActivityClass(ReactContext reactContext) {
        try {
            return Class.forName(reactContext.getPackageManager().getLaunchIntentForPackage(reactContext.getPackageName()).getComponent().getClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (isTaskRoot()) {
            Intent intent = new Intent(this, getMainActivityClass(a()));
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
            intent.putExtras(getIntent().getExtras());
            startActivity(intent);
        }
        finish();
    }
}
