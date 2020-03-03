package com.xiaomi.smarthome.framework.page.verify;

import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.page.verify.callback.PinOptCallback;

public class DevicePinVerifyCloseActivity extends DevicePinVerifyActivity {
    /* access modifiers changed from: protected */
    public void onPinCodeInputFinish(String str) {
        showLoadingDialog();
        this.mVerifyManager.a(this.mDeviceId, str, (PinOptCallback) new PinOptCallback() {
            public void a() {
                DevicePinVerifyCloseActivity.this.hideLoadingDialog();
                DevicePinVerifyCloseActivity.this.setResult(-1);
                DevicePinVerifyCloseActivity.this.finish();
            }

            public void b() {
                DevicePinVerifyCloseActivity.this.hideLoadingDialog();
                DevicePinVerifyCloseActivity.this.onVerifyFail();
            }

            public void a(String str) {
                c();
            }

            public void c() {
                DevicePinVerifyCloseActivity.this.hideLoadingDialog();
                DevicePinVerifyCloseActivity.this.showRetryDialog();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void initStringResource() {
        this.mTitle = getString(R.string.device_more_close_pin_title);
        this.mDesc = getString(R.string.device_more_verify_pin_desc);
        this.mErrorMsg = getString(R.string.device_more_verify_pin_error);
    }

    /* access modifiers changed from: protected */
    public void onVerifySuccess(String str) {
        setResult(-1);
        finish();
    }
}
