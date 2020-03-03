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

public class PasswdSendFailedStep extends SmartConfigStep {
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
        STAT.c.g(this.aj, 0);
        this.mIcon.setImageResource(R.drawable.config_failed_disconnect);
        this.mMainTitle.setText(R.string.smart_config_passwd_failed);
        this.mSubMainTitle.setText(R.string.smart_config_passwd_failed_sub_title);
        this.mLeftBtn.setText(R.string.cancel);
        this.mLeftBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                STAT.c.g(PasswdSendFailedStep.this.aj, PasswdSendFailedStep.this.ai);
                STAT.d.e("adddevice_failtm_cancel.AP", PasswdSendFailedStep.this.aj);
                PasswdSendFailedStep.this.d_(true);
            }
        });
        this.mRightBtn.setText(R.string.retry);
        this.mRightBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                STAT.c.g(PasswdSendFailedStep.this.aj, PasswdSendFailedStep.this.ai);
                STAT.d.e("adddevice_failtm_retry.AP", PasswdSendFailedStep.this.aj);
                SmartConfigDataProvider.a().b(SmartConfigDataProvider.v, false);
                PasswdSendFailedStep.this.D();
            }
        });
        StatHelper.B();
    }
}
