package com.xiaomi.smarthome.framework.page.verify;

import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.page.verify.callback.PinOptCallback;

public class DevicePinMotifyOpenActivity extends DevicePinMotifyActivity {
    /* access modifiers changed from: protected */
    public void initStringResource() {
        this.mTitle = getString(R.string.device_more_add_pin_title);
        this.mDesc = getString(R.string.device_more_add_pin_desc1);
        this.mDesc2 = getString(R.string.device_more_add_pin_desc2);
        this.mErrorMsg = getString(R.string.device_more_add_pin_error);
    }

    /* access modifiers changed from: protected */
    public void motifyPin(String str) {
        this.mVerifyManager.a(this.mDevice.model, this.mDevice.version, this.mDeviceId, str, new PinOptCallback() {
            public void b() {
            }

            public void a() {
                DevicePinMotifyOpenActivity.this.onMotifySuccess();
            }

            public void a(String str) {
                c();
            }

            public void c() {
                DevicePinMotifyOpenActivity.this.hideLoadingDialog();
                DevicePinMotifyOpenActivity.this.showRetryDialog();
            }
        });
    }
}
