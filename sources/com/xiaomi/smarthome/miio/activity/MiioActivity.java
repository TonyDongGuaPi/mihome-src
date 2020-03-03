package com.xiaomi.smarthome.miio.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.widget.Toast;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.MiioDeviceV2;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.webview.CommonDeviceH5WebPage;
import com.xiaomi.smarthome.miio.page.CurtainPage;
import com.xiaomi.smarthome.miio.page.MiioPageV2;

public class MiioActivity extends BaseActivity {
    public static final int REQUEST_CODE_MAIN_ACTIVITY = 1;

    /* access modifiers changed from: protected */
    public boolean useActivityAsStat() {
        return false;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.miio_activity_container);
        String stringExtra = getIntent().getStringExtra(MiioPageV2.e);
        if (TextUtils.isEmpty(stringExtra)) {
            finish();
            return;
        }
        MiioPageV2 newMiioPageV2 = newMiioPageV2(SmartHomeDeviceManager.a().b(stringExtra));
        if (newMiioPageV2 == null) {
            Toast.makeText(this, R.string.miio_unknown, 0).show();
            finish();
            return;
        }
        newMiioPageV2.setArguments(getIntent().getExtras());
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.page_container, newMiioPageV2, (String) null);
        beginTransaction.commitAllowingStateLoss();
    }

    public static MiioPageV2 newMiioPageV2(Device device) {
        MiioPageV2 miioPageV2 = null;
        if (device == null) {
            return null;
        }
        if (DeviceFactory.e(device.model, "xiaomi.curtain.v1")) {
            miioPageV2 = new CurtainPage();
        }
        if (miioPageV2 == null) {
            miioPageV2 = new CommonDeviceH5WebPage();
        }
        if (miioPageV2 != null && (device instanceof MiioDeviceV2)) {
            miioPageV2.a((MiioDeviceV2) device);
        }
        return miioPageV2;
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1 && i2 == -1 && intent != null && intent.getIntExtra("result_data", 0) == 1) {
            finish();
        }
    }
}
