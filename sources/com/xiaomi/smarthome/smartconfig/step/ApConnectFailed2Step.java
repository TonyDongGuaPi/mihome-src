package com.xiaomi.smarthome.smartconfig.step;

import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import com.taobao.weex.common.Constants;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.ResetDevicePage;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider;
import com.xiaomi.smarthome.smartconfig.SmartConfigMainActivity;
import com.xiaomi.smarthome.smartconfig.step.SmartConfigStep;

public class ApConnectFailed2Step extends SmartConfigStep {
    @BindView(2131430969)
    View mReturnBtn;
    @BindView(2131431178)
    Button mSettingWifiBtn;
    @BindView(2131432919)
    View mTitleBar;
    @BindView(2131430975)
    TextView mTitlebarTv;
    @BindView(2131433310)
    TextView mTvFailHint;

    public void a(Message message) {
    }

    public void c() {
    }

    public void d() {
    }

    public void e() {
    }

    public SmartConfigStep.Step f() {
        return SmartConfigStep.Step.STEP_CONNECT_AP_ERROR2;
    }

    public boolean a() {
        d_(false);
        return true;
    }

    public void a(Context context) {
        a(context, R.layout.smart_config_connect_ap_failed2_ui);
        TitleBarUtil.a(this.mTitleBar);
        this.mReturnBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ApConnectFailed2Step.this.a();
            }
        });
        final String str = (String) SmartConfigDataProvider.a().a("device_model");
        String string = this.mTvFailHint.getResources().getString(R.string.kuailian_connect_fail_reason_content);
        if (str == null || !DeviceFactory.c(str)) {
            this.mTvFailHint.setText(string);
        } else {
            this.mTvFailHint.setText(string.substring(0, string.lastIndexOf("\n")));
        }
        this.mTitlebarTv.setText(this.af.getString(R.string.kuailian_connect_device));
        this.mSettingWifiBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (TextUtils.isEmpty(str)) {
                    ApConnectFailed2Step.this.d_(false);
                    return;
                }
                Intent intent = new Intent(ApConnectFailed2Step.this.af, ResetDevicePage.class);
                intent.putExtra("model", str);
                ((SmartConfigMainActivity) ApConnectFailed2Step.this.af).startActivityForResult(intent, 1);
            }
        });
    }

    public void a(int i, int i2, Intent intent) {
        if (intent == null) {
            d_(true);
        } else {
            d_(intent.getBooleanExtra(Constants.Event.FINISH, true));
        }
    }
}
