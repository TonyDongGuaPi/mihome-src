package com.xiaomi.smarthome.camera.v4.activity.setting;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.mijia.app.Event;
import com.mijia.model.property.CameraPropertyBase;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.activity.CameraBaseActivity;
import com.xiaomi.smarthome.camera.view.widget.SettingsItemView;
import com.xiaomi.smarthome.constants.AppConstants;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.frame.FrameManager;

public class SleepSettingActivity extends CameraBaseActivity {
    private SettingsItemView mSleepAutoTimer;
    /* access modifiers changed from: private */
    public SettingsItemView mSleepStatus;

    public void doCreate(Bundle bundle) {
        super.doCreate(bundle);
        setContentView(R.layout.activity_setting_sleep);
        ((TextView) findViewById(R.id.title_bar_title)).setText(R.string.setting_sleep_setting);
        findViewById(R.id.title_bar_return).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SleepSettingActivity.this.finish();
            }
        });
        findViewById(R.id.title_bar_more).setVisibility(8);
        initView();
    }

    public void onResume() {
        super.onResume();
    }

    private void initView() {
        this.mSleepStatus = (SettingsItemView) findViewById(R.id.sleep_status);
        this.mSleepStatus.setChecked(!this.mCameraDevice.f().c());
        this.mSleepStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Event.a(Event.o);
                SleepSettingActivity.this.mSleepStatus.setSwitchEnable(false);
                SleepSettingActivity.this.mCameraDevice.f().a(CameraPropertyBase.l, !z, (Callback<Void>) new Callback<Void>() {
                    public void onSuccess(Void voidR) {
                        if (!SleepSettingActivity.this.isFinishing()) {
                            SleepSettingActivity.this.mSleepStatus.setSwitchEnable(true);
                        }
                    }

                    public void onFailure(int i, String str) {
                        if (!SleepSettingActivity.this.isFinishing()) {
                            SleepSettingActivity.this.mSleepStatus.setChecked(!SleepSettingActivity.this.mSleepStatus.isChecked());
                            SleepSettingActivity.this.mSleepStatus.setSwitchEnable(true);
                        }
                    }
                });
            }
        });
        this.mSleepAutoTimer = (SettingsItemView) findViewById(R.id.sleep_auto_timer);
        this.mSleepAutoTimer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Event.a(Event.p);
                if (XmPluginHostApi.instance().getApiLevel() < 58) {
                    FrameManager.b().k().startSetTimerList(SleepSettingActivity.this, SleepSettingActivity.this.mDeviceStat.did, AppConstants.B, "off", AppConstants.B, "on", SleepSettingActivity.this.mDeviceStat.did, SleepSettingActivity.this.getString(R.string.auto_sleep), SleepSettingActivity.this.getString(R.string.auto_sleep_title));
                } else {
                    FrameManager.b().k().startSetTimerListV2(SleepSettingActivity.this, SleepSettingActivity.this.mDeviceStat.did, AppConstants.B, "off", AppConstants.B, "on", SleepSettingActivity.this.mDeviceStat.did, SleepSettingActivity.this.getString(R.string.auto_sleep), SleepSettingActivity.this.getString(R.string.auto_sleep_title), true, SleepSettingActivity.this.getString(R.string.sleep_auto_on_time), SleepSettingActivity.this.getString(R.string.sleep_auto_off_time), SleepSettingActivity.this.getString(R.string.sleep_auto_item_title));
                }
            }
        });
    }
}
