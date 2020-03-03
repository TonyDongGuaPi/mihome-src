package com.xiaomi.smarthome.framework.page.verify;

import android.annotation.TargetApi;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.text.TextUtils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.server.internal.util.LtmkEncryptUtil;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.DeviceApi;
import com.xiaomi.smarthome.device.bluetooth.BleCacheUtils;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.page.verify.callback.GetCipherCallback;
import com.xiaomi.smarthome.framework.page.verify.callback.OnFingerPrintVerifyCallback;
import com.xiaomi.smarthome.framework.page.verify.callback.PinOptCallback;
import com.xiaomi.smarthome.framework.page.verify.view.FingerPrintOpenVerifyDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import javax.crypto.Cipher;
import org.json.JSONObject;

@TargetApi(23)
public class FingerPrintVerifyActivity extends BaseActivity implements GetCipherCallback {

    /* renamed from: a  reason: collision with root package name */
    private static final int f17054a = 7004;
    private static final int b = 7005;
    /* access modifiers changed from: private */
    public FingerPrintOpenVerifyDialog c;
    /* access modifiers changed from: private */
    public String d;
    private String e;
    /* access modifiers changed from: private */
    public VerifyManager f;
    /* access modifiers changed from: private */
    public Device g;
    /* access modifiers changed from: private */
    public Cipher h;
    private String i;
    private boolean j;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().getDecorView().setBackgroundColor(Color.parseColor("#b1b5b9"));
        Intent intent = getIntent();
        this.d = DeviceVerifyHelper.a(intent);
        this.e = DeviceVerifyHelper.b(intent);
        this.g = SmartHomeDeviceManager.a().b(this.d);
        if (TextUtils.isEmpty(this.d) || this.g == null) {
            ToastUtil.a((CharSequence) "设备id不能为空!");
            finish();
        }
        this.f = VerifyManager.a((Context) this);
        a();
    }

    private void a() {
        this.f.b(this.d, this);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.j = true;
        a(this.i);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        if (this.c != null) {
            this.c.a();
        }
        this.j = false;
        super.onPause();
    }

    public void onGetCipherSuccess(Cipher cipher) {
        this.c = new FingerPrintOpenVerifyDialog(this, new FingerprintManager.CryptoObject(cipher), new OnFingerPrintVerifyCallback() {
            public void a(Cipher cipher) {
                final String a2 = FingerPrintVerifyActivity.this.f.a(FingerPrintVerifyActivity.this.d, cipher);
                if (LtmkEncryptUtil.a(FingerPrintVerifyActivity.this.g.model, FingerPrintVerifyActivity.this.g.version)) {
                    DeviceApi.getEncryptLtmk(FingerPrintVerifyActivity.this, FingerPrintVerifyActivity.this.g.did, new AsyncCallback<JSONObject, Error>() {
                        /* renamed from: a */
                        public void onSuccess(JSONObject jSONObject) {
                            int i;
                            String str = "";
                            if (jSONObject != null) {
                                str = jSONObject.optString("key");
                                i = jSONObject.optInt("encrypt_type");
                            } else {
                                i = 0;
                            }
                            if (TextUtils.isEmpty(str) || i == 0) {
                                ToastUtil.a((CharSequence) FingerPrintVerifyActivity.this.getString(R.string.device_more_verify_fail));
                                Intent intent = new Intent();
                                intent.putExtra("back_for_pincode_verify", true);
                                FingerPrintVerifyActivity.this.setResult(0, intent);
                                FingerPrintVerifyActivity.this.finish();
                                return;
                            }
                            String r = BleCacheUtils.r(FingerPrintVerifyActivity.this.g.mac);
                            String s = BleCacheUtils.s(FingerPrintVerifyActivity.this.g.mac);
                            if (!TextUtils.equals(r, str) || TextUtils.isEmpty(s)) {
                                ToastUtil.a((CharSequence) FingerPrintVerifyActivity.this.getString(R.string.device_more_verify_fail));
                                Intent intent2 = new Intent();
                                intent2.putExtra("back_for_pincode_verify", true);
                                FingerPrintVerifyActivity.this.setResult(0, intent2);
                                FingerPrintVerifyActivity.this.finish();
                            } else if (TextUtils.equals(a2, s)) {
                                FingerPrintVerifyActivity.this.setResult(-1);
                                FingerPrintVerifyActivity.this.finish();
                            } else {
                                ToastUtil.a((CharSequence) FingerPrintVerifyActivity.this.getString(R.string.device_more_verify_fail));
                                Intent intent3 = new Intent();
                                intent3.putExtra("back_for_pincode_verify", true);
                                FingerPrintVerifyActivity.this.setResult(0, intent3);
                                FingerPrintVerifyActivity.this.finish();
                            }
                        }

                        public void onFailure(Error error) {
                            ToastUtil.a((CharSequence) FingerPrintVerifyActivity.this.getString(R.string.device_more_security_network_error));
                        }
                    });
                } else {
                    FingerPrintVerifyActivity.this.f.b(FingerPrintVerifyActivity.this.g.model, FingerPrintVerifyActivity.this.g.version, FingerPrintVerifyActivity.this.d, a2, new PinOptCallback() {
                        public void a() {
                            FingerPrintVerifyActivity.this.setResult(-1);
                            FingerPrintVerifyActivity.this.finish();
                        }

                        public void b() {
                            ToastUtil.a((CharSequence) FingerPrintVerifyActivity.this.getString(R.string.device_more_verify_fail));
                            Intent intent = new Intent();
                            intent.putExtra("back_for_pincode_verify", true);
                            FingerPrintVerifyActivity.this.setResult(0, intent);
                            FingerPrintVerifyActivity.this.finish();
                        }

                        public void a(String str) {
                            ToastUtil.a((CharSequence) FingerPrintVerifyActivity.this.getString(R.string.device_more_security_network_error));
                        }

                        public void c() {
                            ToastUtil.a((CharSequence) FingerPrintVerifyActivity.this.getString(R.string.device_more_security_network_error));
                        }
                    });
                }
            }

            public void a(CharSequence charSequence) {
                FingerPrintVerifyActivity.this.setResult(0);
                FingerPrintVerifyActivity.this.finish();
            }

            public void b(CharSequence charSequence) {
                ToastUtil.a((CharSequence) FingerPrintVerifyActivity.this.getString(R.string.device_more_verify_locked));
                Intent intent = new Intent();
                intent.putExtra("back_for_pincode_verify", true);
                FingerPrintVerifyActivity.this.setResult(0, intent);
                FingerPrintVerifyActivity.this.finish();
            }

            public void a() {
                Intent intent = new Intent();
                intent.putExtra("back_for_pincode_verify", true);
                FingerPrintVerifyActivity.this.setResult(0, intent);
                FingerPrintVerifyActivity.this.finish();
            }
        }, true);
        this.c.a((DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent();
                intent.putExtra("back_for_pincode_verify", true);
                FingerPrintVerifyActivity.this.setResult(0, intent);
                FingerPrintVerifyActivity.this.finish();
            }
        });
        a(getString(R.string.device_more_fingerprint_title));
    }

    public void onGetResetCipherSuccess(Cipher cipher) {
        this.c = new FingerPrintOpenVerifyDialog(this, new FingerprintManager.CryptoObject(cipher), new OnFingerPrintVerifyCallback() {
            public void a(Cipher cipher) {
                Cipher unused = FingerPrintVerifyActivity.this.h = cipher;
                DeviceVerifyHelper.c(FingerPrintVerifyActivity.this, FingerPrintVerifyActivity.this.d, 7004);
                FingerPrintVerifyActivity.this.c.b();
            }

            public void a(CharSequence charSequence) {
                FingerPrintVerifyActivity.this.setResult(0);
                FingerPrintVerifyActivity.this.finish();
            }

            public void b(CharSequence charSequence) {
                ToastUtil.a((CharSequence) FingerPrintVerifyActivity.this.getString(R.string.device_more_verify_locked));
                Intent intent = new Intent();
                intent.putExtra("back_for_pincode_verify", true);
                FingerPrintVerifyActivity.this.setResult(0, intent);
                FingerPrintVerifyActivity.this.finish();
            }

            public void a() {
                Intent intent = new Intent();
                intent.putExtra("back_for_pincode_verify", true);
                FingerPrintVerifyActivity.this.setResult(0, intent);
                FingerPrintVerifyActivity.this.finish();
            }
        }, true);
        this.c.a((DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent();
                intent.putExtra("back_for_pincode_verify", true);
                FingerPrintVerifyActivity.this.setResult(0, intent);
                FingerPrintVerifyActivity.this.finish();
            }
        });
        a(getString(R.string.device_more_verify_key_permanently_invalidated_title));
    }

    private void a(String str) {
        this.i = str;
        if (this.c != null && this.j) {
            this.c.a(this.i);
        }
    }

    public void onGetCipherError(int i2, String str) {
        if (i2 == DeviceVerifyConfigCache.d) {
            Intent createConfirmDeviceCredentialIntent = ((KeyguardManager) getSystemService("keyguard")).createConfirmDeviceCredentialIntent("Hey there!", "Please...");
            if (createConfirmDeviceCredentialIntent != null) {
                startActivityForResult(createConfirmDeviceCredentialIntent, 7005);
                return;
            }
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("back_for_pincode_verify", true);
        setResult(0, intent);
        finish();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i2 == 7004) {
            if (i3 == -1 && this.h != null) {
                this.f.a(this.d, DeviceVerifyHelper.d(intent), this.h);
                setResult(-1);
                finish();
            }
        } else if (i2 == 7005) {
            a();
        }
    }
}
