package com.xiaomi.smarthome.smartconfig.step;

import android.content.Context;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.smartconfig.step.SmartConfigStep;

public class ApDeviceUserErrorStep extends SmartConfigStep {
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
        this.mIcon.setImageResource(R.drawable.config_failed_timeout);
        this.mMainTitle.setText(R.string.smart_config_xiaofang_error_user_error_main_title);
        this.mSubMainTitle.setText(R.string.smart_config_xiaofang_error_user_error_sub_title);
        this.mLeftBtn.setText(R.string.cancel);
        this.mLeftBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ApDeviceUserErrorStep.this.d_(true);
            }
        });
        this.mRightBtn.setText(R.string.retry);
        this.mRightBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ApDeviceUserErrorStep.this.D();
            }
        });
    }

    public boolean a() {
        return super.a();
    }
}