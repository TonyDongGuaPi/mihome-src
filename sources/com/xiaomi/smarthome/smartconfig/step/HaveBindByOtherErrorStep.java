package com.xiaomi.smarthome.smartconfig.step;

import android.content.Context;
import android.os.Message;
import android.view.View;
import butterknife.BindView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.smartconfig.step.SmartConfigStep;

public class HaveBindByOtherErrorStep extends SmartConfigStep {
    @BindView(2131431272)
    View mOkBtn;

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
        a(context, R.layout.smart_config_bind_by_other);
        this.mOkBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                HaveBindByOtherErrorStep.this.d_(false);
            }
        });
    }
}
