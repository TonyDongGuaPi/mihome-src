package com.xiaomi.smarthome.miio.page.smartlife;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.miio.page.smartlife.view.WaterWaveView;

public class SmartLifeDevicesChangedActivity extends BaseActivity {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public WaterWaveView f19921a;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_smart_life_devices_change_completed);
        TitleBarUtil.b((Activity) this);
        a();
    }

    private void a() {
        ((TextView) findViewById(R.id.module_a_3_return_transparent_title)).setText(R.string.smart_life_title_device_selected);
        this.f19921a = (WaterWaveView) findViewById(R.id.water_wave_view);
        this.f19921a.setOmegaByProgress(100);
        this.f19921a.setHeightOffsetByProgress(82);
        this.f19921a.setMoveSpeed(13.0f);
        this.f19921a.setWaveHeight(18.0d);
        findViewById(R.id.module_a_3_return_transparent_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SmartLifeDevicesChangedActivity.this.finish();
            }
        });
        findViewById(R.id.check_detail).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SmartLifeDevicesChangedActivity.this.startActivity(new Intent(SmartLifeDevicesChangedActivity.this, SmartLifeDevicesListActivity.class));
                SmartLifeDevicesChangedActivity.this.overridePendingTransition(R.anim.activity_smart_life_slide_in_right, R.anim.activity_smart_life_fade_out_left_scale);
                SmartLifeDevicesChangedActivity.this.finish();
            }
        });
        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (!SmartLifeDevicesChangedActivity.this.isFinishing()) {
                    if (Build.VERSION.SDK_INT < 17 || !SmartLifeDevicesChangedActivity.this.isDestroyed()) {
                        SmartLifeDevicesChangedActivity.this.f19921a.cancelAnim();
                        SmartLifeDevicesChangedActivity.this.startActivity(new Intent(SmartLifeDevicesChangedActivity.this, SmartLifeDevicesListActivity.class));
                        SmartLifeDevicesChangedActivity.this.overridePendingTransition(R.anim.activity_smart_life_slide_in_right, R.anim.activity_smart_life_fade_out_left_scale);
                        SmartLifeDevicesChangedActivity.this.finish();
                    }
                }
            }
        }, 3000);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }
}
