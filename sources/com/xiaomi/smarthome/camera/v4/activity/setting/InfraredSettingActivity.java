package com.xiaomi.smarthome.camera.v4.activity.setting;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.mijia.app.Event;
import com.mijia.debug.SDKLog;
import com.mijia.model.property.CameraPropertyBase;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.activity.CameraBaseActivity;
import com.xiaomi.smarthome.camera.view.widget.SettingsItemView;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.library.common.util.ToastUtil;

public class InfraredSettingActivity extends CameraBaseActivity implements SettingsItemView.OnSelectedListener {
    private static final String TAG = "InfraredSettingActivity";
    private SettingsItemView mInfraredAuto;
    private SettingsItemView mInfraredClose;
    private SettingsItemView mInfraredOpen;
    /* access modifiers changed from: private */
    public int mLastType = 0;
    private SettingsItemView sivGlimmerSetting;

    public void doCreate(Bundle bundle) {
        super.doCreate(bundle);
        setContentView(R.layout.activity_setting_infrared);
        findViewById(R.id.title_bar_more).setVisibility(8);
        ((TextView) findViewById(R.id.title_bar_title)).setText(R.string.infrared_func_setting);
        findViewById(R.id.title_bar_return).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                InfraredSettingActivity.this.finish();
            }
        });
        initView();
    }

    public void onResume() {
        super.onResume();
        updateProperties();
    }

    private void initView() {
        this.mInfraredAuto = (SettingsItemView) findViewById(R.id.infrared_auto);
        this.mInfraredClose = (SettingsItemView) findViewById(R.id.infrared_close);
        this.mInfraredOpen = (SettingsItemView) findViewById(R.id.infrared_open);
        this.sivGlimmerSetting = (SettingsItemView) findViewById(R.id.sivGlimmerSetting);
        this.mInfraredAuto.setOnSelectedListener(this);
        this.mInfraredClose.setOnSelectedListener(this);
        this.mInfraredOpen.setOnSelectedListener(this);
        this.sivGlimmerSetting.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Event.a(Event.w);
                InfraredSettingActivity.this.changeGlimmerColor(z);
            }
        });
        this.mLastType = this.mCameraDevice.f().a(CameraPropertyBase.n);
        initType(this.mLastType);
    }

    /* access modifiers changed from: private */
    public void initType(int i) {
        this.mInfraredAuto.setSelect(false);
        this.mInfraredClose.setSelect(false);
        this.mInfraredOpen.setSelect(false);
        switch (i) {
            case 0:
                this.mInfraredAuto.setSelect(true);
                return;
            case 1:
                this.mInfraredClose.setSelect(true);
                return;
            case 2:
                this.mInfraredOpen.setSelect(true);
                return;
            default:
                return;
        }
    }

    private void updateProperties() {
        this.mCameraDevice.f().a(new String[]{CameraPropertyBase.n, CameraPropertyBase.o}, (Callback<Void>) new Callback<Void>() {
            public void onSuccess(Void voidR) {
                if (!InfraredSettingActivity.this.isFinishing()) {
                    SDKLog.b(InfraredSettingActivity.TAG, "updateProperty onSuccess");
                    InfraredSettingActivity.this.updateGlimmerColor();
                }
            }

            public void onFailure(int i, String str) {
                if (!InfraredSettingActivity.this.isFinishing()) {
                    SDKLog.b(InfraredSettingActivity.TAG, "updateProperty onFailure");
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void updateGlimmerColor() {
        this.sivGlimmerSetting.setChecked(this.mCameraDevice.f().a(CameraPropertyBase.o, false));
    }

    private void changeType(final int i) {
        initType(i);
        this.mCameraDevice.f().a(CameraPropertyBase.n, (Object) Integer.valueOf(i), (Callback<Void>) new Callback<Void>() {
            public void onSuccess(Void voidR) {
                if (!InfraredSettingActivity.this.isFinishing()) {
                    int unused = InfraredSettingActivity.this.mLastType = i;
                    InfraredSettingActivity.this.initType(i);
                }
            }

            public void onFailure(int i, String str) {
                if (!InfraredSettingActivity.this.isFinishing()) {
                    InfraredSettingActivity.this.initType(InfraredSettingActivity.this.mLastType);
                    ToastUtil.a((Context) InfraredSettingActivity.this, (int) R.string.set_failed);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void changeGlimmerColor(boolean z) {
        if (this.mCameraDevice != null && this.mCameraDevice.f() != null) {
            this.mCameraDevice.f().a(CameraPropertyBase.o, z, (Callback<Void>) new Callback<Void>() {
                public void onSuccess(Void voidR) {
                    if (!InfraredSettingActivity.this.isFinishing()) {
                        SDKLog.b(InfraredSettingActivity.TAG, "onSuccess");
                        InfraredSettingActivity.this.updateGlimmerColor();
                    }
                }

                public void onFailure(int i, String str) {
                    if (!InfraredSettingActivity.this.isFinishing()) {
                        SDKLog.b(InfraredSettingActivity.TAG, "onFailure");
                        InfraredSettingActivity.this.updateGlimmerColor();
                    }
                }
            });
        }
    }

    public void onSelected(View view) {
        switch (view.getId()) {
            case R.id.infrared_auto:
                Event.a(Event.x, "type", (Object) 1);
                changeType(0);
                return;
            case R.id.infrared_close:
                Event.a(Event.x, "type", (Object) 2);
                changeType(1);
                return;
            case R.id.infrared_open:
                Event.a(Event.x, "type", (Object) 3);
                changeType(2);
                return;
            default:
                return;
        }
    }
}
