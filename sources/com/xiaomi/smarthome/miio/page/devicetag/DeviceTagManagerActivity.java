package com.xiaomi.smarthome.miio.page.devicetag;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.HomeRoomRecommendActivity;

public class DeviceTagManagerActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    private View f19819a;
    private TextView b;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_device_tag_manager);
        this.b = (TextView) findViewById(R.id.module_a_3_right_text_btn);
        this.b.setText(R.string.tag_add_title);
        this.b.setOnClickListener(this);
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.tag_manager);
        this.f19819a = findViewById(R.id.module_a_3_return_btn);
        this.f19819a.setOnClickListener(this);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
    }

    public void onClick(View view) {
        if (view == this.f19819a) {
            finish();
        } else if (view == this.b) {
            startActivity(new Intent(this, HomeRoomRecommendActivity.class));
        }
    }
}
