package com.xiaomi.smarthome.smartconfig.step;

import android.content.Context;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.smartconfig.step.SmartConfigStep;

public class ScanQRBindFailStep extends SmartConfigStep {
    @BindView(2131427933)
    Button mConfim;
    @BindView(2131432516)
    ImageView mIcon;
    @BindView(2131432518)
    TextView mMainTitle;
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
        return null;
    }

    public void a(Context context) {
        a(context, R.layout.smart_config_scan_qr_bind_failed_ui);
        this.mIcon.setImageResource(R.drawable.config_failed_disconnect);
        this.mMainTitle.setText(R.string.smart_config_faile_qr_bind);
        this.mSubMainTitle.setText(R.string.smart_config_faile_qr_bind_detail);
        this.mConfim.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ScanQRBindFailStep.this.d_(false);
            }
        });
        StatHelper.C();
    }
}
