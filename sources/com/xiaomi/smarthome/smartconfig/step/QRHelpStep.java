package com.xiaomi.smarthome.smartconfig.step;

import android.content.Context;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import butterknife.BindView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.smartconfig.step.SmartConfigStep;

public class QRHelpStep extends SmartConfigStep {
    @BindView(2131431272)
    Button mOkButton;

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
        a(context, R.layout.activity_camera_gen_barcode_help);
        this.mOkButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                QRHelpStep.this.D();
            }
        });
    }
}
