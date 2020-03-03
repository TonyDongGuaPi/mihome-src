package com.xiaomi.smarthome.smartconfig.step;

import android.content.Context;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.smartconfig.step.SmartConfigStep;

public class XiaoxunErrorStep extends SmartConfigStep {
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

    public SmartConfigStep.Step f() {
        return SmartConfigStep.Step.STEP_BLE_SEND_ERROR;
    }

    public void a(Context context) {
        a(context, R.layout.smart_config_base_left_right_ui);
        this.mIcon.setImageResource(R.drawable.config_failed_disconnect);
        this.mMainTitle.setText(R.string.xiaoxun_error);
        this.mSubMainTitle.setText("");
        this.mLeftBtn.setText(R.string.ok_button);
        this.mLeftBtn.setBackgroundResource(R.drawable.common_button);
        this.mLeftBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BluetoothLog.c(String.format("Cancel Button Pressed", new Object[0]));
                XiaoxunErrorStep.this.d_(false);
            }
        });
        this.mRightBtn.setText(R.string.retry);
        this.mRightBtn.setVisibility(8);
        this.mRightBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                XiaoxunErrorStep.this.D();
            }
        });
    }

    public void c() {
        BluetoothLog.c(String.format("%s.onResumeStep", new Object[]{getClass().getSimpleName()}));
    }

    public void d() {
        BluetoothLog.c(String.format("%s.onPauseStep", new Object[]{getClass().getSimpleName()}));
    }

    public void e() {
        BluetoothLog.c(String.format("%s.onFinishStep", new Object[]{getClass().getSimpleName()}));
    }

    public boolean a() {
        BluetoothLog.c(String.format("%s.onBackPressed", new Object[]{getClass().getSimpleName()}));
        return super.a();
    }
}
