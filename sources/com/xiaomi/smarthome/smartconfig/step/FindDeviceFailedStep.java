package com.xiaomi.smarthome.smartconfig.step;

import android.content.Context;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider;
import com.xiaomi.smarthome.smartconfig.step.SmartConfigStep;
import com.xiaomi.smarthome.stat.STAT;

public class FindDeviceFailedStep extends SmartConfigStep {
    @BindView(2131432516)
    ImageView mIcon;
    @BindView(2131430408)
    TextView mLeftBtn;
    @BindView(2131432518)
    TextView mMainTitle;
    @BindView(2131431897)
    TextView mRightBtn;
    @BindView(2131432517)
    TextView mSubMainTitle;

    public void a(Message message) {
    }

    public void c() {
    }

    public void d() {
    }

    public void e() {
    }

    public SmartConfigStep.Step f() {
        return SmartConfigStep.Step.STEP_SEND_ROUTER_INFO_ERROR;
    }

    public void a(Context context) {
        a(context, R.layout.smart_config_base_left_right_ui);
        y();
        STAT.c.f(this.aj, 0);
        this.mIcon.setImageResource(R.drawable.config_failed_timeout);
        this.mMainTitle.setText(R.string.smart_config_connect_timeout_first);
        this.mSubMainTitle.setText(R.string.smart_config_find_new_device_error);
        this.mLeftBtn.setText(R.string.cancel);
        this.mLeftBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                STAT.c.f(FindDeviceFailedStep.this.aj, FindDeviceFailedStep.this.ai);
                STAT.d.e("adddevice_failovertime_cancel.AP", FindDeviceFailedStep.this.aj);
                SmartConfigDataProvider.a().b(SmartConfigDataProvider.E, true);
                FindDeviceFailedStep.this.d_(true);
            }
        });
        this.mRightBtn.setText(R.string.retry);
        this.mRightBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                STAT.c.f(FindDeviceFailedStep.this.aj, FindDeviceFailedStep.this.ai);
                STAT.d.e("adddevice_failovertime_retry.AP", FindDeviceFailedStep.this.aj);
                FindDeviceFailedStep.this.D();
            }
        });
        StatHelper.A();
    }

    public boolean a() {
        SmartConfigDataProvider.a().b(SmartConfigDataProvider.E, true);
        return super.a();
    }
}
