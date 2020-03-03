package com.xiaomi.smarthome.smartconfig.step;

import android.content.Context;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider;
import com.xiaomi.smarthome.smartconfig.step.SmartConfigStep;

public class DeviceInfoStep extends SmartConfigStep {
    @BindView(2131432516)
    SimpleDraweeView mDeviceIcon;
    @BindView(2131432518)
    TextView mDeviceInfo;
    @BindView(2131432517)
    TextView mDeviceInfoSubTitle;
    @BindView(2131431178)
    Button mNextBtn;
    @BindView(2131430969)
    View mReturnBtn;
    @BindView(2131430975)
    TextView mTitle;
    @BindView(2131432919)
    View mTitleBar;

    public void a(Message message) {
    }

    public void c() {
    }

    public void d() {
    }

    public void e() {
    }

    public SmartConfigStep.Step f() {
        return SmartConfigStep.Step.STEP_DEVICE_INFO;
    }

    public void a(Context context) {
        String str;
        a(context, R.layout.smart_config_device_info_ui);
        if (!TitleBarUtil.f11582a) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mTitleBar.getLayoutParams();
            this.mTitleBar.setPadding(0, 0, 0, 0);
            layoutParams.height = DisplayUtils.a(45.0f);
            this.mTitleBar.setLayoutParams(layoutParams);
        }
        this.mReturnBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DeviceInfoStep.this.d_(false);
            }
        });
        String str2 = (String) SmartConfigDataProvider.a().a("device_model");
        if (str2 == null) {
            this.mDeviceInfo.setText(R.string.terminal_feedback);
            DeviceFactory.a("", this.mDeviceIcon, (int) R.drawable.kuailian_common_icon);
            return;
        }
        Device k = DeviceFactory.k(str2);
        if (k != null) {
            str = k.name;
        } else {
            str = SHApplication.getAppContext().getString(R.string.other_device);
        }
        this.mTitle.setText(k.name);
        this.mDeviceInfo.setText(String.format(context.getString(R.string.kuailian_main_title_3), new Object[]{str}));
        this.mDeviceInfoSubTitle.setText(R.string.kuailian_sub_main_title_1);
        DeviceFactory.a(str2, this.mDeviceIcon, (int) R.drawable.kuailian_common_icon);
        this.mNextBtn.setText(R.string.next_button);
        this.mNextBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (((Boolean) SmartConfigDataProvider.a().a(SmartConfigDataProvider.f22323a, false)).booleanValue()) {
                    DeviceInfoStep.this.a(SmartConfigStep.Step.STEP_GET_ROUTER_INFO);
                } else {
                    DeviceInfoStep.this.a(SmartConfigStep.Step.STEP_CHOOSE_WIFI);
                }
            }
        });
    }

    public boolean a() {
        d_(false);
        return true;
    }
}
