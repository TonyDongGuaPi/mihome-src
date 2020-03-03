package com.xiaomi.smarthome.framework.page.verify;

import android.content.Intent;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.server.internal.util.LtmkEncryptUtil;

public class DevicePinVerifyChangeActivity extends DevicePinVerifyActivity {

    /* renamed from: a  reason: collision with root package name */
    private static final int f17050a = 300;

    /* access modifiers changed from: protected */
    public boolean isCustomDesc() {
        return false;
    }

    /* access modifiers changed from: protected */
    public void initStringResource() {
        if (LtmkEncryptUtil.a(this.mDevice.model, this.mDevice.version)) {
            this.mTitle = getString(R.string.ble_secure_change_pin_code);
        } else {
            this.mTitle = getString(R.string.device_more_change_pin_title);
        }
        this.mDesc = getString(R.string.device_more_verify_pin_desc);
        this.mErrorMsg = getString(R.string.device_more_verify_pin_error);
    }

    /* access modifiers changed from: protected */
    public void onVerifySuccess(String str) {
        DevicePinMotifyChangeActivity.start(this, this.mDeviceId, str, this.mCustomDesc, 300);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 300) {
            return;
        }
        if (i2 != -10) {
            switch (i2) {
                case -1:
                    setResult(-1);
                    finish();
                    return;
                case 0:
                    setResult(0);
                    finish();
                    return;
                default:
                    return;
            }
        } else {
            Intent intent2 = new Intent();
            intent2.putExtra(DevicePinActivity.RESULT_FAIL_INTENT_ERRORMSG, intent.getStringExtra(DevicePinActivity.RESULT_FAIL_INTENT_ERRORMSG));
            setResult(-10, intent2);
            finish();
        }
    }
}
