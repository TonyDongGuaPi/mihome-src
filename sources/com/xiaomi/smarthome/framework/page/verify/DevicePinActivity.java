package com.xiaomi.smarthome.framework.page.verify;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.bluetooth.XmBluetoothManager;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.core.server.internal.util.LtmkEncryptUtil;
import com.xiaomi.smarthome.device.BluetoothStateHelper;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.bluetooth.BLEDeviceManager;
import com.xiaomi.smarthome.device.bluetooth.MiioBtSearchResponse;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.page.verify.view.PinInputView;
import com.xiaomi.smarthome.framework.page.verify.view.PinSoftKeyboard;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleResponse;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;

public abstract class DevicePinActivity extends BaseActivity implements PinSoftKeyboard.OnPinSoftKeyboardClickListener {
    public static final int RESULT_FAIL = -10;
    public static final String RESULT_FAIL_INTENT_ERRORMSG = "xiaomi.sm.fail_msg";

    /* renamed from: a  reason: collision with root package name */
    private XQProgressDialog f1541a;
    private ImageView b;
    private View c;
    private ImageView d;
    /* access modifiers changed from: private */
    public View e;
    private TextView f;
    private TextView g;
    private TextView h;
    private TextView i;
    private TextView j;
    /* access modifiers changed from: private */
    public int k;
    private MLAlertDialog l;
    protected String mCustomDesc;
    protected String mDesc;
    protected String mDesc2;
    protected Device mDevice;
    protected String mDeviceId;
    protected String mErrorMsg;
    protected String mTitle;
    protected VerifyManager mVerifyManager;
    @BindView(2131434051)
    TextView vDesc;
    @BindView(2131434050)
    PinInputView vPinInputView;
    @BindView(2131434052)
    PinSoftKeyboard vPinSoftKeyboard;
    @BindView(2131434053)
    TextView vSubHint;
    @BindView(2131430975)
    TextView vTitle;

    /* access modifiers changed from: protected */
    public abstract void initStringResource();

    /* access modifiers changed from: protected */
    public void initVerify() {
    }

    public boolean invokeOnPinCodeInputFinishWhileOverInput() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean isCustomDesc() {
        return true;
    }

    /* access modifiers changed from: protected */
    public abstract void onPinCodeInputFinish(String str);

    /* access modifiers changed from: protected */
    public abstract void onRetryClick();

    /* access modifiers changed from: protected */
    public void parseCustomIntent(Intent intent) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a(getIntent());
        if (this.mDevice != null) {
            if (LtmkEncryptUtil.a(this.mDevice.model, this.mDevice.version)) {
                setContentView(R.layout.activity_device_lock_secure_pin);
                a();
            } else {
                setContentView(R.layout.activity_device_pin);
            }
            ButterKnife.bind((Activity) this);
            this.mVerifyManager = VerifyManager.a((Context) this);
            initStringResource();
            this.vTitle.setText(this.mTitle);
            this.vDesc.setText(this.mDesc);
            this.vPinSoftKeyboard.setClickListener(this);
            initVerify();
        }
    }

    private void a() {
        this.b = (ImageView) findViewById(R.id.ble_secure_pin_faq);
        this.c = findViewById(R.id.ble_secure_pin_connect_layout);
        this.d = (ImageView) findViewById(R.id.connect_loading);
        this.e = findViewById(R.id.ble_secure_pin_failed_layout);
        this.f = (TextView) findViewById(R.id.pin_failed_title);
        this.g = (TextView) findViewById(R.id.pin_failed_text_1);
        this.h = (TextView) findViewById(R.id.pin_failed_text_2);
        this.i = (TextView) findViewById(R.id.pin_failed_text_3);
        this.j = (TextView) findViewById(R.id.failed_retry_btn);
        this.b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                new MLAlertDialog.Builder(DevicePinActivity.this).a((int) R.string.ble_secure_pin_code).b((int) R.string.ble_secure_pin_code_tips).a((int) R.string.reassure_ok, (DialogInterface.OnClickListener) null).b().show();
            }
        });
        this.j.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (DevicePinActivity.this.k == -6 || DevicePinActivity.this.k == -5) {
                    DevicePinActivity.this.d();
                    return;
                }
                DevicePinActivity.this.e.setVisibility(8);
                DevicePinActivity.this.vPinInputView.reset();
            }
        });
    }

    private void a(Intent intent) {
        this.mDeviceId = DeviceVerifyHelper.a(intent);
        this.mCustomDesc = DeviceVerifyHelper.b(intent);
        this.mDevice = SmartHomeDeviceManager.a().b(this.mDeviceId);
        if (TextUtils.isEmpty(this.mDeviceId) || this.mDevice == null) {
            ToastUtil.a((CharSequence) "deviceId不能为空");
            setResult(0);
            finish();
            return;
        }
        if (isCustomDesc() && !TextUtils.isEmpty(this.mCustomDesc)) {
            this.mDesc = this.mCustomDesc;
        }
        parseCustomIntent(intent);
    }

    /* access modifiers changed from: protected */
    public void showSecurePinConnectLayout() {
        if (this.c != null) {
            this.c.setVisibility(0);
            if (this.d != null) {
                Animation loadAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_infinite);
                loadAnimation.setDuration(1000);
                this.d.startAnimation(loadAnimation);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void hideSecurePinConnectLayout() {
        if (this.d != null) {
            this.d.clearAnimation();
        }
        if (this.c != null) {
            this.c.setVisibility(8);
        }
    }

    /* access modifiers changed from: protected */
    public void showSecurePinFailedLayout(int i2) {
        if (this.e != null) {
            this.k = i2;
            this.e.setVisibility(0);
            if (i2 == -6 || i2 == -5) {
                this.f.setText(R.string.ble_new_connect_step_failed);
                this.g.setText(R.string.ble_new_connect_failed_title);
                this.h.setVisibility(4);
                this.i.setVisibility(4);
                this.j.setText(R.string.ble_new_reopen_and_retry);
                return;
            }
            this.f.setText(R.string.ble_secure_pin_verify_failed_title);
            this.g.setText(R.string.ble_secure_pin_verify_failed_message_1);
            this.h.setVisibility(0);
            this.i.setVisibility(0);
            this.h.setText(R.string.ble_secure_pin_verify_failed_message_2);
            this.i.setText(R.string.ble_secure_pin_verify_failed_message_3);
            this.j.setText(R.string.ble_new_reopen_and_retry_btn);
        }
    }

    @OnClick({2131430969})
    public void onClickBack() {
        setResult(0);
        finish();
    }

    public void onNumberClick(int i2) {
        String pinCode = this.vPinInputView.getPinCode();
        if (invokeOnPinCodeInputFinishWhileOverInput() || TextUtils.isEmpty(pinCode) || pinCode.length() < this.vPinInputView.getPincodeNumber()) {
            final String add = this.vPinInputView.add(i2);
            if (add.length() >= this.vPinInputView.getPincodeNumber()) {
                new Handler(getMainLooper()).postDelayed(new Runnable() {
                    public void run() {
                        DevicePinActivity.this.onPinCodeInputFinish(add);
                    }
                }, 100);
                return;
            }
            return;
        }
        LogUtil.c("DevicePinActivity", "onNumberClick: pin code is over input, max length:" + this.vPinInputView.getPincodeNumber());
    }

    public void onBackClick() {
        setResult(0);
        finish();
    }

    public void onDeleteClick() {
        this.vPinInputView.delete();
    }

    /* access modifiers changed from: protected */
    public void showLoadingDialog() {
        if (isValid()) {
            this.f1541a = new XQProgressDialog(this);
            this.f1541a.setMessage(getString(R.string.device_more_security_loading_operation));
            this.f1541a.show();
        }
    }

    /* access modifiers changed from: protected */
    public void hideLoadingDialog() {
        if (this.f1541a != null) {
            this.f1541a.dismiss();
        }
    }

    /* access modifiers changed from: protected */
    public void showRetryDialog() {
        if (isValid()) {
            new MLAlertDialog.Builder(this).a((CharSequence) getString(R.string.device_more_security_network_error)).a((CharSequence) getString(R.string.retry), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    DevicePinActivity.this.onRetryClick();
                }
            }).b((CharSequence) getString(R.string.cancel), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    DevicePinActivity.this.setResult(0);
                    DevicePinActivity.this.finish();
                }
            }).b().show();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.c != null && this.c.getVisibility() == 0) {
            XmBluetoothManager.getInstance().disconnect(this.mDevice.mac);
        }
    }

    private void b() {
        if (this.l == null) {
            this.l = new XQProgressDialog(this);
            this.l.setMessage(getString(R.string.reopening_bluetooth));
            this.l.setCancelable(false);
        }
        if (!this.l.isShowing()) {
            this.l.show();
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        if (this.l != null && this.l.isShowing()) {
            this.l.dismiss();
        }
    }

    /* access modifiers changed from: private */
    public void d() {
        b();
        if (BluetoothUtils.b()) {
            BluetoothStateHelper.b(new BleResponse() {
                public void a(int i, Object obj) {
                    BluetoothStateHelper.a((BleResponse) new BleResponse() {
                        public void a(int i, Object obj) {
                            BLEDeviceManager.a((MiioBtSearchResponse) null);
                            DevicePinActivity.this.mHandler.postDelayed(new Runnable() {
                                public void run() {
                                    DevicePinActivity.this.c();
                                    DevicePinActivity.this.e.setVisibility(8);
                                    DevicePinActivity.this.onRetryClick();
                                }
                            }, 5000);
                        }
                    });
                }
            });
        } else {
            BluetoothStateHelper.a((BleResponse) new BleResponse() {
                public void a(int i, Object obj) {
                    BLEDeviceManager.a((MiioBtSearchResponse) null);
                    DevicePinActivity.this.mHandler.postDelayed(new Runnable() {
                        public void run() {
                            DevicePinActivity.this.c();
                            DevicePinActivity.this.e.setVisibility(8);
                            DevicePinActivity.this.onRetryClick();
                        }
                    }, 5000);
                }
            });
        }
    }
}
