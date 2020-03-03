package com.xiaomi.smarthome.framework.page.verify;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.server.internal.util.LtmkEncryptUtil;
import com.xiaomi.smarthome.device.bluetooth.BleCacheUtils;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.log.BluetoothMyLogger;
import com.xiaomi.smarthome.framework.page.verify.callback.PinOptCallback;

public class DevicePinMotifyChangeActivity extends DevicePinMotifyActivity {

    /* renamed from: a  reason: collision with root package name */
    private String f17043a;

    static void start(Activity activity, String str, String str2, String str3, int i) {
        Intent intent = new Intent(activity, DevicePinMotifyChangeActivity.class);
        DeviceVerifyHelper.a(intent, str);
        DeviceVerifyHelper.b(intent, str3);
        DeviceVerifyHelper.c(intent, str2);
        activity.startActivityForResult(intent, i);
    }

    /* access modifiers changed from: protected */
    public void initStringResource() {
        if (LtmkEncryptUtil.a(this.mDevice.model, this.mDevice.version)) {
            this.mTitle = getString(R.string.ble_secure_change_pin_code);
        } else {
            this.mTitle = getString(R.string.device_more_change_pin_title);
        }
        this.mDesc = getString(R.string.device_more_add_pin_desc1);
        this.mDesc2 = getString(R.string.device_more_add_pin_desc2);
        this.mErrorMsg = getString(R.string.device_more_add_pin_error);
    }

    /* access modifiers changed from: protected */
    public void parseCustomIntent(Intent intent) {
        super.parseCustomIntent(intent);
        this.f17043a = DeviceVerifyHelper.c(intent);
    }

    /* access modifiers changed from: protected */
    public void motifyPin(final String str) {
        if (LtmkEncryptUtil.a(this.mDevice.model, this.mDevice.version)) {
            int t = BleCacheUtils.t(this.mDevice.mac);
            String b = LtmkEncryptUtil.b(BleCacheUtils.s(this.mDevice.mac), BleCacheUtils.r(this.mDevice.mac), t);
            if (TextUtils.isEmpty(b)) {
                BluetoothMyLogger.e("change secure pin failed because decryptLtmk is empty");
                onMotifyFail("");
                return;
            }
            final String a2 = LtmkEncryptUtil.a(str, b, t);
            if (TextUtils.isEmpty(a2)) {
                BluetoothMyLogger.e("change secure pin failed because encryptLtmk is empty");
                onMotifyFail("");
                return;
            }
            DevicePinApi.a().d(SHApplication.getAppContext(), this.mDevice.did, a2, new AsyncCallback<Void, Error>() {
                /* renamed from: a */
                public void onSuccess(Void voidR) {
                    BleCacheUtils.j(DevicePinMotifyChangeActivity.this.mDevice.mac, str);
                    BleCacheUtils.i(DevicePinMotifyChangeActivity.this.mDevice.mac, a2);
                    DevicePinMotifyChangeActivity.this.onMotifySuccess();
                }

                public void onFailure(Error error) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("changeSecurePinLtmk failed, error = ");
                    sb.append(error == null ? null : error.toString());
                    BluetoothMyLogger.e(sb.toString());
                    DevicePinMotifyChangeActivity.this.hideLoadingDialog();
                    DevicePinMotifyChangeActivity.this.showRetryDialog();
                }
            });
            return;
        }
        this.mVerifyManager.a(this.mDevice.model, this.mDevice.version, this.mDeviceId, str, this.f17043a, new PinOptCallback() {
            public void a() {
                DevicePinMotifyChangeActivity.this.onMotifySuccess();
            }

            public void b() {
                DevicePinMotifyChangeActivity.this.onMotifyFail("");
            }

            public void a(String str) {
                c();
            }

            public void c() {
                DevicePinMotifyChangeActivity.this.hideLoadingDialog();
                DevicePinMotifyChangeActivity.this.showRetryDialog();
            }
        });
    }
}
