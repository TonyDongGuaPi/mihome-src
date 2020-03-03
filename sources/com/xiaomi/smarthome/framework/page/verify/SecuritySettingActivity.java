package com.xiaomi.smarthome.framework.page.verify;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.server.internal.util.LtmkEncryptUtil;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.DeviceApi;
import com.xiaomi.smarthome.device.bluetooth.BleCacheUtils;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.log.MyLog;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.page.verify.callback.GetCipherCallback;
import com.xiaomi.smarthome.framework.page.verify.callback.OnFingerPrintVerifyCallback;
import com.xiaomi.smarthome.framework.page.verify.view.FingerPrintOpenVerifyDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.SwitchButton;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import javax.crypto.Cipher;
import org.json.JSONObject;

public class SecuritySettingActivity extends BaseActivity implements GetCipherCallback {

    /* renamed from: a  reason: collision with root package name */
    private static final String f17062a = "extra_device_did";
    private static final String b = "xiaomi.smarthome.custom_hint";
    private static final int c = 7000;
    private static final int d = 7001;
    private static final int e = 7002;
    private static final int f = 7003;
    private static final int g = 7004;
    /* access modifiers changed from: private */
    public Device h;
    /* access modifiers changed from: private */
    public String i;
    private String j;
    /* access modifiers changed from: private */
    public FingerPrintOpenVerifyDialog k;
    /* access modifiers changed from: private */
    public VerifyManager l;
    /* access modifiers changed from: private */
    public String m;
    private boolean n;
    @BindView(2131434043)
    View vChangeSecurePin;
    @BindView(2131434046)
    View vFingerPrintSetting;
    @BindView(2131434047)
    SwitchButton vFingerPrintSwitch;
    @BindView(2131434048)
    View vFingerPrintTitle;
    @BindView(2131434041)
    View vPasswordChange;
    @BindView(2131434042)
    TextView vPasswordChangeTitle;
    @BindView(2131434045)
    View vPasswordClose;
    @BindView(2131434049)
    View vPasswordOpen;
    @BindView(2131434055)
    SwitchButton vSecurePinSwitch;
    @BindView(2131434054)
    View vShowSecurePin;
    @BindView(2131430975)
    TextView vTitle;

    public static void startActivity(Activity activity, String str, String str2) {
        Intent intent = new Intent(activity, SecuritySettingActivity.class);
        intent.putExtra("extra_device_did", str);
        intent.putExtra(b, str2);
        activity.startActivity(intent);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        TitleBarUtil.a((Activity) this);
        setContentView(R.layout.activity_security_setting);
        ButterKnife.bind((Activity) this);
        a(getIntent());
        this.h = SmartHomeDeviceManager.a().b(this.i);
        if (this.h == null) {
            MyLog.f(this.i + " device is null!");
            finish();
            return;
        }
        if (LtmkEncryptUtil.a(this.h.model, this.h.version)) {
            this.vTitle.setText(getString(R.string.ble_secure_pin_device_more_title));
        } else {
            this.vTitle.setText(getString(R.string.device_more_security_title));
        }
        this.l = VerifyManager.a((Context) this);
        b();
    }

    @TargetApi(23)
    private void a() {
        if (this.k != null) {
            this.k.b();
        }
        this.l.a(this.i, (GetCipherCallback) this);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.n = true;
        if (this.k != null) {
            this.k.a(getString(R.string.device_more_fingerprint_title));
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        if (this.k != null) {
            this.k.a();
        }
        this.n = false;
        super.onPause();
    }

    /* access modifiers changed from: private */
    public void b() {
        this.vFingerPrintSwitch.setOnTouchEnable(false);
        this.vSecurePinSwitch.setOnTouchEnable(false);
        c();
    }

    private void c() {
        if (!LtmkEncryptUtil.a(this.h.model, this.h.version)) {
            showPinCloseStyle();
            if (this.h.isSetPinCode == 1) {
                showPinOpenStyle();
                d();
                return;
            }
            showPinCloseStyle();
        } else if (BleCacheUtils.u(this.h.mac)) {
            showSecurePinOpenStyle();
            d();
        } else {
            showSecurePinCloseStyle();
        }
    }

    private void d() {
        if (!this.l.a()) {
            showFingerPrintUnSupport();
        } else if (this.l.b(this.i)) {
            showFingerPrintSupportOpen();
        } else {
            showFingerPrintSupportClose();
        }
    }

    private void a(Intent intent) {
        this.i = intent.getStringExtra("extra_device_did");
        this.j = intent.getStringExtra(b);
        if (TextUtils.isEmpty(this.i)) {
            ToastUtil.a((CharSequence) "设备id不能为空");
            finish();
        }
    }

    public void showPinCloseStyle() {
        this.vPasswordOpen.setVisibility(0);
        this.vPasswordClose.setVisibility(8);
        this.vPasswordChange.setVisibility(0);
        this.vPasswordChange.setClickable(false);
        this.vPasswordChangeTitle.setTextColor(ContextCompat.getColor(this, R.color.black_50_transparent));
        this.vFingerPrintTitle.setVisibility(8);
        this.vFingerPrintSetting.setVisibility(8);
    }

    public void showPinOpenStyle() {
        this.vPasswordOpen.setVisibility(8);
        this.vPasswordClose.setVisibility(0);
        this.vPasswordChange.setVisibility(0);
        this.vPasswordChange.setClickable(true);
        this.vPasswordChangeTitle.setTextColor(ContextCompat.getColor(this, R.color.black_80_transparent));
    }

    public void showSecurePinCloseStyle() {
        this.vPasswordOpen.setVisibility(8);
        this.vPasswordClose.setVisibility(8);
        this.vPasswordChange.setVisibility(8);
        this.vFingerPrintTitle.setVisibility(8);
        this.vFingerPrintSetting.setVisibility(8);
        this.vShowSecurePin.setVisibility(0);
        this.vSecurePinSwitch.setChecked(false);
        this.vChangeSecurePin.setVisibility(0);
    }

    public void showSecurePinOpenStyle() {
        this.vPasswordOpen.setVisibility(8);
        this.vPasswordClose.setVisibility(8);
        this.vPasswordChange.setVisibility(8);
        this.vShowSecurePin.setVisibility(0);
        this.vSecurePinSwitch.setChecked(true);
        this.vChangeSecurePin.setVisibility(0);
    }

    public void showFingerPrintSupportOpen() {
        this.vFingerPrintTitle.setVisibility(0);
        this.vFingerPrintSetting.setVisibility(0);
        this.vFingerPrintSwitch.setChecked(true);
    }

    public void showFingerPrintSupportClose() {
        this.vFingerPrintTitle.setVisibility(0);
        this.vFingerPrintSetting.setVisibility(0);
        this.vFingerPrintSwitch.setChecked(false);
    }

    public void showFingerPrintUnSupport() {
        this.vFingerPrintTitle.setVisibility(8);
        this.vFingerPrintSetting.setVisibility(8);
    }

    @OnClick({2131430969})
    public void onClickBack() {
        finish();
    }

    @OnClick({2131434049})
    public void onClickPasswordOpen() {
        DeviceVerifyHelper.a(this, this.i, this.j, 7000);
    }

    @OnClick({2131434045})
    public void onClickPasswordClose() {
        DeviceVerifyHelper.a(this, this.i, 7001);
    }

    @OnClick({2131434041})
    public void onClickPasswordChange() {
        DeviceVerifyHelper.b(this, this.i, this.j, 7002);
    }

    @OnClick({2131434046, 2131434047})
    public void onClickFingerPrintSetting() {
        if (this.vFingerPrintSwitch.isChecked()) {
            this.l.a(this.i);
            this.vFingerPrintSwitch.setChecked(false);
            return;
        }
        this.m = this.l.a(this.i, 10000);
        if (!TextUtils.isEmpty(this.m)) {
            a();
        } else {
            DeviceVerifyHelper.b(this, this.i, 7003);
        }
    }

    @OnClick({2131434054, 2131434055})
    public void onClickSecurePin() {
        if (this.vSecurePinSwitch.isChecked()) {
            a(false);
        } else {
            a(true);
        }
    }

    @OnClick({2131434043})
    public void onClickChangeSecurePin() {
        DeviceVerifyHelper.b(this, this.i, this.j, 7002);
    }

    private void a(final boolean z) {
        DeviceApi.setSecurePinSwitch(this.i, z, new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                SecuritySettingActivity.this.vSecurePinSwitch.setChecked(z);
                BleCacheUtils.a(SecuritySettingActivity.this.h.mac, z);
                SecuritySettingActivity.this.b();
            }

            public void onFailure(Error error) {
                Toast.makeText(SecuritySettingActivity.this, R.string.toast_lock_switch_failed, 0).show();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        switch (i2) {
            case 7000:
                if (i3 == -1) {
                    this.h.isSetPinCode = 1;
                    SmartHomeDeviceManager.a().a(this.h);
                    b();
                    SmartHomeDeviceManager.a().p();
                    return;
                }
                return;
            case 7001:
                if (i3 == -1) {
                    this.h.isSetPinCode = 0;
                    SmartHomeDeviceManager.a().a(this.h);
                    b();
                    SmartHomeDeviceManager.a().p();
                    return;
                }
                return;
            case 7002:
                if (i3 == -1) {
                    b();
                    return;
                }
                return;
            case 7003:
                if (i3 == -1) {
                    this.m = DeviceVerifyHelper.d(intent);
                    if (!TextUtils.isEmpty(this.m)) {
                        a();
                        return;
                    }
                    return;
                }
                return;
            case 7004:
                if (i3 == -1) {
                    a();
                    return;
                }
                return;
            default:
                return;
        }
    }

    @TargetApi(23)
    public void onGetCipherSuccess(Cipher cipher) {
        this.k = new FingerPrintOpenVerifyDialog(this, new FingerprintManager.CryptoObject(cipher), new OnFingerPrintVerifyCallback() {
            public void a(Cipher cipher) {
                SecuritySettingActivity.this.k.b();
                if (!TextUtils.isEmpty(SecuritySettingActivity.this.m)) {
                    SecuritySettingActivity.this.l.a(SecuritySettingActivity.this.i, SecuritySettingActivity.this.m, cipher);
                    SecuritySettingActivity.this.vFingerPrintSwitch.setChecked(true);
                }
            }

            public void a(CharSequence charSequence) {
                SecuritySettingActivity.this.k.b();
            }

            public void b(CharSequence charSequence) {
                ToastUtil.a(charSequence);
                SecuritySettingActivity.this.k.b();
            }

            public void a() {
                SecuritySettingActivity.this.k.b();
            }
        }, false);
        if (this.n) {
            this.k.a(getString(R.string.device_more_fingerprint_title));
        }
    }

    public void onGetResetCipherSuccess(Cipher cipher) {
        onGetCipherSuccess(cipher);
    }

    @RequiresApi(api = 21)
    public void onGetCipherError(int i2, String str) {
        if (i2 == DeviceVerifyConfigCache.d) {
            Intent createConfirmDeviceCredentialIntent = ((KeyguardManager) getSystemService("keyguard")).createConfirmDeviceCredentialIntent("", "");
            if (createConfirmDeviceCredentialIntent != null) {
                startActivityForResult(createConfirmDeviceCredentialIntent, 7004);
                return;
            }
            return;
        }
        ToastUtil.a((CharSequence) getString(R.string.device_more_fingerprint_open_fail));
    }
}
