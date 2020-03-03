package com.xiaomi.smarthome.device.bluetooth.connect.single;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.bluetooth.BleCacheUtils;
import com.xiaomi.smarthome.frame.log.BluetoothMyLogger;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.page.verify.view.PinInputView;
import com.xiaomi.smarthome.framework.page.verify.view.PinSoftKeyboard;

public class BleSecurePinActivity extends BaseActivity implements PinSoftKeyboard.OnPinSoftKeyboardClickListener {
    public static final String KEY_MAC = "key_mac";

    /* renamed from: a  reason: collision with root package name */
    private String f15212a = "";
    private String b;
    @BindView(2131428162)
    View mCancelView;
    @BindView(2131431751)
    CheckBox mCheckBox;
    @BindView(2131431752)
    View mCheckBoxContainer;
    @BindView(2131430836)
    TextView mMessageView;
    @BindView(2131434050)
    PinInputView vPinInputView;
    @BindView(2131434052)
    PinSoftKeyboard vPinSoftKeyboard;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.ble_secure_pin_code_layout);
        ButterKnife.bind((Activity) this);
        this.vPinSoftKeyboard.setClickListener(this);
        this.mCancelView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BleSecurePinActivity.this.finish();
            }
        });
        this.b = getIntent().getStringExtra(KEY_MAC);
        if (TextUtils.isEmpty(this.b)) {
            BluetoothMyLogger.c("BleSecurePinActivity device mac is null");
            finish();
        }
    }

    public void onNumberClick(int i) {
        final String add = this.vPinInputView.add(i);
        if (add.length() >= this.vPinInputView.getPincodeNumber()) {
            new Handler(getMainLooper()).postDelayed(new Runnable() {
                public void run() {
                    BleSecurePinActivity.this.a(add);
                }
            }, 100);
        }
    }

    public void onBackClick() {
        finish();
    }

    public void onDeleteClick() {
        this.vPinInputView.delete();
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        if (TextUtils.isEmpty(this.f15212a)) {
            this.f15212a = str;
            this.mCheckBoxContainer.setVisibility(4);
            this.mMessageView.setText(R.string.ble_secure_pin_setting_second_tips);
            this.vPinInputView.reset();
        } else if (!TextUtils.equals(this.f15212a, str)) {
            Toast.makeText(this, R.string.device_more_add_pin_error, 0).show();
            this.f15212a = "";
            this.mCheckBoxContainer.setVisibility(0);
            this.mMessageView.setText(R.string.ble_secure_pin_setting_first_tips);
            this.vPinInputView.reset();
        } else if (!isFinishing()) {
            BleCacheUtils.c(this.b, 1);
            BleCacheUtils.a(this.b, this.mCheckBox.isChecked());
            BleCacheUtils.j(this.b, this.f15212a);
            setResult(-1);
            finish();
        }
    }
}
