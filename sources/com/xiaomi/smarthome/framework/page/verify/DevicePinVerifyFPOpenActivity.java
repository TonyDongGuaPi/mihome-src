package com.xiaomi.smarthome.framework.page.verify;

import android.content.Intent;
import com.xiaomi.smarthome.R;

public class DevicePinVerifyFPOpenActivity extends DevicePinVerifyActivity {
    /* access modifiers changed from: protected */
    public void initStringResource() {
        this.mTitle = getString(R.string.device_more_security_fingerprint_main);
        this.mDesc = getString(R.string.device_more_verify_finger_print);
        this.mErrorMsg = getString(R.string.device_more_verify_pin_error);
    }

    /* access modifiers changed from: protected */
    public void onVerifySuccess(String str) {
        Intent intent = new Intent();
        DeviceVerifyHelper.d(intent, str);
        setResult(-1, intent);
        finish();
    }
}
