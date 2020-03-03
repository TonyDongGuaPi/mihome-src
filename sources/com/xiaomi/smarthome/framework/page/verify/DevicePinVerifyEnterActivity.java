package com.xiaomi.smarthome.framework.page.verify;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.server.internal.util.LtmkEncryptUtil;
import com.xiaomi.smarthome.frame.plugin.runtime.activity.LockSecurePinUtil;

public class DevicePinVerifyEnterActivity extends DevicePinVerifyActivity {

    /* renamed from: a  reason: collision with root package name */
    private static final int f1543a = 10000;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        boolean booleanExtra = getIntent().getBooleanExtra("verfy_pincode_first", false);
        if (this.mVerifyManager != null && booleanExtra) {
            if (this.mDevice == null || !LtmkEncryptUtil.a(this.mDevice.model, this.mDevice.version)) {
                if (this.mVerifyManager.b(this.mDeviceId)) {
                    DeviceVerifyHelper.d(this, this.mDeviceId, this.mCustomDesc, 10000);
                }
            } else if (!TextUtils.isEmpty(LockSecurePinUtil.getPropLtmk(this.mDevice.mac)) && !TextUtils.isEmpty(LockSecurePinUtil.getPropPincode(this.mDevice.mac)) && this.mVerifyManager.b(this.mDeviceId)) {
                DeviceVerifyHelper.d(this, this.mDeviceId, this.mCustomDesc, 10000);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void initStringResource() {
        if (LtmkEncryptUtil.a(this.mDevice.model, this.mDevice.version)) {
            this.mTitle = getString(R.string.ble_secure_pin_verify_title);
        } else {
            this.mTitle = getString(R.string.device_more_verify_enter_title);
        }
        if (LtmkEncryptUtil.a(this.mDevice.model, this.mDevice.version)) {
            this.mDesc = getString(R.string.ble_secure_pin_open_title);
            if (TextUtils.isEmpty(LockSecurePinUtil.getPropLtmk(this.mDevice.mac)) || TextUtils.isEmpty(LockSecurePinUtil.getPropPincode(this.mDevice.mac))) {
                this.vSubHint.setVisibility(0);
                this.vSubHint.setText(R.string.ble_secure_pin_open_exception_tips);
            }
        } else {
            this.mDesc = getString(R.string.device_more_verify_enter_device_desc);
        }
        this.mErrorMsg = getString(R.string.device_more_verify_pin_error);
    }

    /* access modifiers changed from: protected */
    public void onVerifySuccess(String str) {
        setResult(-1);
        finish();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 10000) {
            return;
        }
        if (intent == null || !intent.getBooleanExtra("back_for_pincode_verify", false)) {
            setResult(i2, intent);
            finish();
        }
    }
}
