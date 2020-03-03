package com.xiaomi.smarthome.device.bluetooth.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.stat.STAT;

public class BleErrorActivity extends BaseActivity {
    public static final String ERROR_FROM = "error.from";
    public static final int FROM_BLE_MATCH = 1;

    /* renamed from: a  reason: collision with root package name */
    private Button f15220a;
    private Button b;
    private TextView c;
    private TextView d;
    private int e;
    /* access modifiers changed from: private */
    public String f;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.ble_error);
        Intent intent = getIntent();
        this.e = intent.getIntExtra(ERROR_FROM, 0);
        this.f = intent.getStringExtra("model");
        this.f15220a = (Button) findViewById(R.id.retry);
        this.b = (Button) findViewById(R.id.cancel);
        this.c = (TextView) findViewById(R.id.line1);
        this.d = (TextView) findViewById(R.id.line2);
        this.f15220a.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                STAT.d.bb(BleErrorActivity.this.f);
                BleErrorActivity.this.b();
            }
        });
        this.b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                STAT.d.ba(BleErrorActivity.this.f);
                BleErrorActivity.this.finish();
            }
        });
        a();
        STAT.c.l(this.f);
    }

    private void a() {
        if (this.e == 1) {
            this.c.setText(R.string.ble_rssi_match_failed_line1);
            this.d.setText(R.string.ble_rssi_match_failed_line2);
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        if (this.e == 1) {
            Intent intent = new Intent();
            intent.putExtras(getIntent());
            intent.setClass(this, BleMatchActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
