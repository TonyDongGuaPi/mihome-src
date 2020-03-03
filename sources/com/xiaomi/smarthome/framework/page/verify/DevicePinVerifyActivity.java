package com.xiaomi.smarthome.framework.page.verify;

import android.os.Bundle;
import android.text.TextUtils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.server.internal.util.LtmkEncryptUtil;
import com.xiaomi.smarthome.device.api.DeviceApi;
import com.xiaomi.smarthome.device.bluetooth.BLEDeviceManager;
import com.xiaomi.smarthome.device.bluetooth.BleCacheUtils;
import com.xiaomi.smarthome.device.bluetooth.MiioBtSearchResponse;
import com.xiaomi.smarthome.device.bluetooth.utils.BluetoothHelper;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.page.verify.callback.PinOptCallback;
import com.xiaomi.smarthome.library.bluetooth.IBleSecureConnectResponse;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import org.json.JSONObject;

public abstract class DevicePinVerifyActivity extends DevicePinActivity {

    /* renamed from: a  reason: collision with root package name */
    private static final int f1542a = 300000;
    private static final int b = 5;
    private long c;
    private int d;
    protected DeviceLockCache mLockedCahce;

    public boolean invokeOnPinCodeInputFinishWhileOverInput() {
        return false;
    }

    /* access modifiers changed from: protected */
    public abstract void onVerifySuccess(String str);

    /* access modifiers changed from: protected */
    public void initVerify() {
        this.mLockedCahce = DeviceLockCache.a();
        if (this.mLockedCahce.b(this.mDeviceId)) {
            ToastUtil.a((CharSequence) getString(R.string.device_more_verify_locked));
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onPinCodeInputFinish(final String str) {
        showLoadingDialog();
        if (LtmkEncryptUtil.a(this.mDevice.model, this.mDevice.version)) {
            DeviceApi.getEncryptLtmk(this, this.mDevice.did, new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    final int i;
                    String str = "";
                    if (jSONObject != null) {
                        str = jSONObject.optString("key");
                        i = jSONObject.optInt("encrypt_type");
                    } else {
                        i = 0;
                    }
                    if (TextUtils.isEmpty(str) || i == 0) {
                        DevicePinVerifyActivity.this.hideLoadingDialog();
                        DevicePinVerifyActivity.this.showRetryDialog();
                        return;
                    }
                    String r = BleCacheUtils.r(DevicePinVerifyActivity.this.mDevice.mac);
                    String s = BleCacheUtils.s(DevicePinVerifyActivity.this.mDevice.mac);
                    if (!TextUtils.equals(r, str) || TextUtils.isEmpty(s)) {
                        DevicePinVerifyActivity.this.hideLoadingDialog();
                        BleCacheUtils.i(DevicePinVerifyActivity.this.mDevice.mac, str);
                        DevicePinVerifyActivity.this.showSecurePinConnectLayout();
                        BluetoothHelper.a(DevicePinVerifyActivity.this.mDevice.mac, str, str, i, (IBleSecureConnectResponse) new IBleSecureConnectResponse() {
                            public void a(int i, Bundle bundle) {
                            }

                            public void b(int i, Bundle bundle) {
                            }

                            public void c(int i, Bundle bundle) {
                            }

                            public void d(int i, Bundle bundle) {
                                DevicePinVerifyActivity.this.hideSecurePinConnectLayout();
                                if (i == 0) {
                                    BleCacheUtils.j(DevicePinVerifyActivity.this.mDevice.mac, str);
                                    BleCacheUtils.c(DevicePinVerifyActivity.this.mDevice.mac, i);
                                    DevicePinVerifyActivity.this.onVerifySuccess(str);
                                    return;
                                }
                                DevicePinVerifyActivity.this.showSecurePinFailedLayout(i);
                                BLEDeviceManager.a((MiioBtSearchResponse) null);
                            }
                        });
                    } else if (TextUtils.equals(str, s)) {
                        DevicePinVerifyActivity.this.hideLoadingDialog();
                        DevicePinVerifyActivity.this.onVerifySuccess(str);
                    } else {
                        DevicePinVerifyActivity.this.hideLoadingDialog();
                        DevicePinVerifyActivity.this.onVerifyFail();
                    }
                }

                public void onFailure(Error error) {
                    DevicePinVerifyActivity.this.hideLoadingDialog();
                    DevicePinVerifyActivity.this.showRetryDialog();
                }
            });
            return;
        }
        this.mVerifyManager.b(this.mDevice.model, this.mDevice.version, this.mDeviceId, str, new PinOptCallback() {
            public void a() {
                DevicePinVerifyActivity.this.hideLoadingDialog();
                DevicePinVerifyActivity.this.onVerifySuccess(str);
            }

            public void b() {
                DevicePinVerifyActivity.this.hideLoadingDialog();
                DevicePinVerifyActivity.this.onVerifyFail();
            }

            public void a(String str) {
                c();
            }

            public void c() {
                DevicePinVerifyActivity.this.hideLoadingDialog();
                DevicePinVerifyActivity.this.showRetryDialog();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onRetryClick() {
        onPinCodeInputFinish(this.vPinInputView.getPinCode());
    }

    /* access modifiers changed from: protected */
    public void onVerifyFail() {
        this.vPinInputView.showError();
        this.vDesc.setText(this.mErrorMsg);
        if (this.c == 0 || System.currentTimeMillis() - this.c < 300000) {
            this.d++;
        } else {
            this.d = 1;
        }
        this.c = System.currentTimeMillis();
        if (this.d >= 5) {
            this.mLockedCahce.a(this.mDeviceId);
            ToastUtil.a((CharSequence) getString(R.string.device_more_verify_locked));
            finish();
        }
    }
}
