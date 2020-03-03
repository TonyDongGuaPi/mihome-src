package com.xiaomi.smarthome.smartconfig.step;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.umeng.analytics.MobclickAgent;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider;
import com.xiaomi.smarthome.smartconfig.step.SmartConfigStep;

public class ApDevicePasswdChooseStep extends SmartConfigStep {

    /* renamed from: a  reason: collision with root package name */
    private WifiManager f22422a;
    private ScanResult b;
    @BindView(2131429685)
    ImageView mDeviceIcon;
    @BindView(2131430975)
    TextView mDeviceInfo;
    @BindView(2131431178)
    Button mNextBtn;
    @BindView(2131434005)
    EditText mPasswordEditor;
    @BindView(2131434006)
    EditText mPasswordEditorAbove;
    @BindView(2131434007)
    TextView mPasswordToggle;
    @BindView(2131430969)
    View mReturnBtn;
    @BindView(2131432919)
    View mTitleBar;
    @BindView(2131430719)
    TextView mWifiChooser;
    @BindView(2131434010)
    View mWifiPassContainer;

    public void a(Message message) {
    }

    public void c() {
    }

    public void d() {
    }

    public void e() {
    }

    public SmartConfigStep.Step f() {
        return null;
    }

    public void a(Context context) {
        MobclickAgent.a(this.af, "midr.cardvr.v1", "start_input_ap_password");
        SmartConfigDataProvider.a().b(SmartConfigDataProvider.F, Long.valueOf(System.currentTimeMillis()));
        a(context, R.layout.smart_config_ap_passwd_choose_step);
        TitleBarUtil.a(this.mTitleBar);
        this.f22422a = (WifiManager) context.getSystemService("wifi");
        this.mNextBtn.setText(R.string.next_button);
        this.mDeviceInfo.setText(String.format(context.getString(R.string.kuailian_main_title_2), new Object[0]));
        this.mReturnBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ApDevicePasswdChooseStep.this.d_(false);
            }
        });
        this.mDeviceIcon.setImageResource(R.drawable.kuailian_wifi_icon);
        this.b = (ScanResult) SmartConfigDataProvider.a().a(SmartConfigDataProvider.h, (Object) null);
        if (this.b == null) {
            d_(false);
            return;
        }
        this.mWifiChooser.setText(this.b.SSID);
        this.mPasswordEditor.setVisibility(0);
        this.mPasswordEditorAbove.setVisibility(8);
        this.mPasswordToggle.setEnabled(true);
        this.mNextBtn.setEnabled(false);
        SmartConfigDataProvider.a().b(SmartConfigDataProvider.p);
        this.mNextBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                InputMethodManager inputMethodManager = (InputMethodManager) ApDevicePasswdChooseStep.this.af.getSystemService("input_method");
                if (inputMethodManager.isActive() && (ApDevicePasswdChooseStep.this.af instanceof Activity) && ((Activity) ApDevicePasswdChooseStep.this.af).getCurrentFocus() != null) {
                    inputMethodManager.hideSoftInputFromWindow(((Activity) ApDevicePasswdChooseStep.this.af).getCurrentFocus().getWindowToken(), 2);
                }
                SmartConfigDataProvider.a().b(SmartConfigDataProvider.e, ApDevicePasswdChooseStep.this.mPasswordEditor.getText().toString());
                ApDevicePasswdChooseStep.this.D();
            }
        });
        this.mPasswordEditor.setText("");
        this.mPasswordEditor.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                ApDevicePasswdChooseStep.this.b();
            }
        });
        this.mPasswordToggle.setEnabled(true);
        this.mPasswordToggle.setSelected(true);
        this.mPasswordToggle.setText(R.string.smart_config_hide_passwd);
        this.mPasswordToggle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int selectionStart = ApDevicePasswdChooseStep.this.mPasswordEditor.getSelectionStart();
                if (!ApDevicePasswdChooseStep.this.mPasswordToggle.isSelected()) {
                    ApDevicePasswdChooseStep.this.mPasswordEditor.setInputType(144);
                    ApDevicePasswdChooseStep.this.mPasswordToggle.setSelected(true);
                    ApDevicePasswdChooseStep.this.mPasswordToggle.setText(R.string.smart_config_hide_passwd);
                } else {
                    ApDevicePasswdChooseStep.this.mPasswordEditor.setInputType(129);
                    ApDevicePasswdChooseStep.this.mPasswordToggle.setSelected(false);
                    ApDevicePasswdChooseStep.this.mPasswordToggle.setText(R.string.smart_config_show_passwd);
                }
                ApDevicePasswdChooseStep.this.mPasswordEditor.setSelection(selectionStart);
            }
        });
    }

    /* access modifiers changed from: private */
    public void b() {
        if (this.mWifiPassContainer.getVisibility() == 8 || this.mPasswordEditorAbove.getVisibility() == 0 || !TextUtils.isEmpty(this.mPasswordEditor.getText().toString())) {
            this.mNextBtn.setEnabled(true);
        } else {
            this.mNextBtn.setEnabled(false);
        }
    }

    public boolean a() {
        d_(false);
        return true;
    }
}
