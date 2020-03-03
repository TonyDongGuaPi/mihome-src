package com.xiaomi.smarthome.framework.page;

import android.os.Bundle;
import android.view.View;
import com.xiaomi.smarthome.R;

public class DevelopingActivity extends BaseActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.developing_activity);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DevelopingActivity.this.finish();
            }
        });
    }
}
