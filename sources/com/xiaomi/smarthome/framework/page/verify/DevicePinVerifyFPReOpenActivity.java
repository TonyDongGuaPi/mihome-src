package com.xiaomi.smarthome.framework.page.verify;

import android.content.Intent;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.server.internal.util.LtmkEncryptUtil;

public class DevicePinVerifyFPReOpenActivity extends DevicePinVerifyActivity {
    /* access modifiers changed from: protected */
    public void initStringResource() {
        if (LtmkEncryptUtil.a(this.mDevice.model, this.mDevice.version)) {
            this.mTitle = getString(R.string.ble_secure_pin_verify_title);
        } else {
            this.mTitle = getString(R.string.device_more_verify_enter_title);
        }
        this.mDesc = getString(R.string.device_more_verify_finger_print);
        this.mErrorMsg = getString(R.string.device_more_verify_pin_error);
        this.vSubHint.setVisibility(0);
        this.vSubHint.setText(R.string.device_more_verify_fingerprint_sub_hint);
    }

    /* access modifiers changed from: protected */
    public void onVerifySuccess(String str) {
        Intent intent = new Intent();
        DeviceVerifyHelper.d(intent, str);
        setResult(-1, intent);
        finish();
    }
}
