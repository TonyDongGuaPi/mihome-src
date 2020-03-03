package com.xiaomi.smarthome.miio.page.devicetag;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.xiaomi.smarthome.SmartHomeMainActivity;

public class DeviceTagDrawerAdapter extends DeviceTagAdapter {
    public DeviceTagDrawerAdapter(Activity activity, String str) {
        super(activity, str);
        b();
    }

    /* access modifiers changed from: protected */
    public void b() {
        this.b = DeviceTagGroupManager.a().b();
    }

    public void a(String str) {
        Intent intent = new Intent(SmartHomeMainActivity.ACTION_CLOSE_DRAWER);
        if (!TextUtils.isEmpty(str)) {
            intent.putExtra(SmartHomeMainActivity.PARAM_NEXT_ACTION, str);
        }
        LocalBroadcastManager.getInstance(this.f19795a).sendBroadcast(intent);
    }
}
