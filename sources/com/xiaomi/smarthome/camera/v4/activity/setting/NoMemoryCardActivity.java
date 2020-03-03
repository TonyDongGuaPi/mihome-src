package com.xiaomi.smarthome.camera.v4.activity.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.mi.global.shop.model.Tags;
import com.mijia.model.alarm.AlarmNetUtils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.activity.CameraBaseActivity;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoIntroActivity;

public class NoMemoryCardActivity extends CameraBaseActivity {
    private TextView tvBuy;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_no_memory_card);
        ((TextView) findViewById(R.id.title_bar_title)).setText(R.string.storage_sdcard_file_manager);
        findViewById(R.id.title_bar_return).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                NoMemoryCardActivity.this.finish();
            }
        });
        findViewById(R.id.title_bar_more).setVisibility(8);
        this.tvBuy = (TextView) findViewById(R.id.tv_buy);
        if (this.isV4) {
            if (CoreApi.a().D() || !AlarmNetUtils.b() || !AlarmNetUtils.e() || AlarmNetUtils.d() || this.mCameraDevice.isShared()) {
                this.tvBuy.setVisibility(8);
            } else {
                this.tvBuy.setVisibility(0);
                this.tvBuy.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        NoMemoryCardActivity.this.startActivity(new Intent(NoMemoryCardActivity.this, CloudVideoIntroActivity.class));
                    }
                });
            }
        } else if (CoreApi.a().D() || !AlarmNetUtils.e() || this.mCameraDevice.e().f() || this.mCameraDevice.isShared()) {
            this.tvBuy.setVisibility(8);
        } else {
            this.tvBuy.setVisibility(0);
            this.tvBuy.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    NoMemoryCardActivity.this.startActivity(new Intent(NoMemoryCardActivity.this, CloudVideoIntroActivity.class));
                }
            });
        }
        if (this.mCameraDevice != null && this.mCameraDevice.o()) {
            findViewById(R.id.tab_row_4gb).setVisibility(0);
            findViewById(R.id.tab_row_8gb).setVisibility(0);
            ((TextView) findViewById(R.id.storage_16_low)).setText(R.string.storage_time_4_days);
            ((TextView) findViewById(R.id.storage_16_high)).setText(R.string.storage_time_2_days);
            ((TextView) findViewById(R.id.storage_32_low)).setText(R.string.storage_time_8_days);
            ((TextView) findViewById(R.id.storage_32_high)).setText(R.string.storage_time_4_days);
            ((TextView) findViewById(R.id.camera_tips_1)).setText(((TextView) findViewById(R.id.camera_tips_1)).getText().toString().replace(Tags.Phone.M22S_PHONE, "4"));
            ((TextView) findViewById(R.id.camera_tips_1)).setText(((TextView) findViewById(R.id.camera_tips_1)).getText().toString().replace(Tags.Phone.MRED_PHONE, Tags.Phone.M2A_PHONE));
            ((TextView) findViewById(R.id.high_quality)).setText(R.string.quality_fhd);
            ((TextView) findViewById(R.id.low_quality)).setText(R.string.quality_low);
            findViewById(R.id.gb_64_tab_tow).setVisibility(8);
        }
    }

    public void onResume() {
        super.onResume();
    }
}
