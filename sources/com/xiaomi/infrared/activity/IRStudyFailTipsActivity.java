package com.xiaomi.infrared.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.xiaomi.infrared.InifraredContants;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.page.BaseActivity;

public class IRStudyFailTipsActivity extends BaseActivity implements View.OnClickListener {
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.ir_go_matching) {
            a();
        } else if (id == R.id.ir_report) {
            b();
        } else if (id == R.id.module_a_3_return_btn) {
            finish();
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_ir_study_controller_air_conditioner);
        View findViewById = findViewById(R.id.module_a_3_return_btn);
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.ir_report_title);
        findViewById(R.id.module_a_3_return_more_more_btn).setVisibility(8);
        findViewById.setOnClickListener(this);
        ((Button) findViewById(R.id.ir_report)).setOnClickListener(this);
        ((Button) findViewById(R.id.ir_go_matching)).setOnClickListener(this);
    }

    private void a() {
        finish();
    }

    private void b() {
        finish();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (intent != null && intent.getBooleanExtra(InifraredContants.IntentParams.d, false)) {
            setResult(-1, intent);
            finish();
        }
    }
}
