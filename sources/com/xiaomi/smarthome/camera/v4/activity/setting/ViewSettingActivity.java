package com.xiaomi.smarthome.camera.v4.activity.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.mijia.app.Event;
import com.mijia.camera.CameraProperties;
import com.mijia.model.property.CameraPropertyBase;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.activity.CameraBaseActivity;
import com.xiaomi.smarthome.camera.view.widget.SettingsItemView;
import com.xiaomi.smarthome.device.api.BaseDevice;
import com.xiaomi.smarthome.device.api.Callback;

public class ViewSettingActivity extends CameraBaseActivity implements View.OnClickListener {
    CameraProperties mCameraProperties;
    SettingsItemView mCorrectLensDistortItem;
    BaseDevice.StateChangedListener mListener = new BaseDevice.StateChangedListener() {
        public void onStateChanged(BaseDevice baseDevice) {
            ViewSettingActivity.this.refreshUI();
        }
    };
    SettingsItemView mRoteItem;
    SettingsItemView mWatermarkItem;
    SettingsItemView mWdrItem;

    public void doCreate(Bundle bundle) {
        super.doCreate(bundle);
        setContentView(R.layout.view_setting_acitivity);
        this.mCameraProperties = (CameraProperties) this.mCameraDevice.f();
        initView();
        this.mCameraDevice.addStateChangedListener(this.mListener);
    }

    private void initView() {
        ((TextView) findViewById(R.id.title_bar_title)).setText(R.string.setting_view);
        findViewById(R.id.title_bar_more).setVisibility(8);
        this.mWatermarkItem = (SettingsItemView) findViewById(R.id.watermark);
        this.mCorrectLensDistortItem = (SettingsItemView) findViewById(R.id.correct_lens_distort);
        this.mRoteItem = (SettingsItemView) findViewById(R.id.settings_rote);
        this.mWdrItem = (SettingsItemView) findViewById(R.id.settings_wdr);
        this.mWatermarkItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ViewSettingActivity.this.mWatermarkItem.setSwitchEnable(false);
                Event.a(Event.s);
                ViewSettingActivity.this.mCameraProperties.a("watermark", z, (Callback<Void>) new Callback<Void>() {
                    public void onSuccess(Void voidR) {
                        if (!ViewSettingActivity.this.isFinishing()) {
                            ViewSettingActivity.this.refreshUI();
                            ViewSettingActivity.this.mWatermarkItem.setSwitchEnable(true);
                        }
                    }

                    public void onFailure(int i, String str) {
                        if (!ViewSettingActivity.this.isFinishing()) {
                            ViewSettingActivity.this.refreshUI();
                            ViewSettingActivity.this.mWatermarkItem.setSwitchEnable(true);
                        }
                    }
                });
            }
        });
        this.mCorrectLensDistortItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Event.a(Event.t);
                ViewSettingActivity.this.mCameraDevice.e().a(z);
            }
        });
        this.mRoteItem.setOnClickListener(this);
        this.mWdrItem.setOnClickListener(this);
        findViewById(R.id.title_bar_return).setOnClickListener(this);
    }

    /* access modifiers changed from: private */
    public void refreshUI() {
        this.mWatermarkItem.setChecked(this.mCameraProperties.b("watermark"));
        this.mCorrectLensDistortItem.setChecked(this.mCameraDevice.e().c());
        this.mWdrItem.setInfo(getString(this.mCameraProperties.b(CameraPropertyBase.m) ? R.string.setting_open_status : R.string.setting_close_status));
    }

    public void onResume() {
        super.onResume();
        refreshUI();
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.settings_rote) {
            Event.a(Event.A);
            startActivity(new Intent(this, RoteSettingActivity.class));
        } else if (id == R.id.settings_wdr) {
            Event.a(Event.y);
            startActivity(new Intent(this, WdrSettingActivity.class));
        } else if (id == R.id.title_bar_return) {
            finish();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mCameraDevice != null) {
            this.mCameraDevice.removeStateChangedListener(this.mListener);
        }
    }
}
