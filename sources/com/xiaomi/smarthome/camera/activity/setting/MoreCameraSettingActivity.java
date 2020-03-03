package com.xiaomi.smarthome.camera.activity.setting;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.Utils.Util;
import com.mijia.app.Event;
import com.mijia.camera.CameraProperties;
import com.mijia.debug.SDKLog;
import com.mijia.debug.Tag;
import com.mijia.model.local.LocalSettings;
import com.mijia.model.property.CameraPropertyBase;
import com.taobao.weex.common.Constants;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.activity.CameraBaseActivity;
import com.xiaomi.smarthome.camera.activity.timelapse.TimeLapsePhotographyActivity;
import com.xiaomi.smarthome.camera.view.widget.SettingsItemView;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.plugin.DeviceConstant;

public class MoreCameraSettingActivity extends CameraBaseActivity implements View.OnClickListener {
    CameraProperties mCameraProperties;
    private boolean mIsGpu = false;
    SettingsItemView mLightItem;
    SettingsItemView mOnlyWifi;
    private TextView mSubTitle;
    SettingsItemView smartPictureFrame;
    private TextView tvReset;

    /* access modifiers changed from: private */
    public void showCameraInfo() {
    }

    public void doCreate(Bundle bundle) {
        super.doCreate(bundle);
        setContentView(R.layout.camera_more_camera_activity);
        this.mCameraProperties = (CameraProperties) this.mCameraDevice.f();
        Intent intent = getIntent();
        if (intent != null) {
            this.mIsGpu = intent.getBooleanExtra("gpu", false);
        }
        initView();
        refreshUI();
        Event.a(Event.k);
        if (this.mCameraDevice != null && this.mCameraDevice.f() != null) {
            this.mCameraDevice.f().a(DeviceConstant.MIJIA_CAMERA_V3_UPGRADE.equals(this.mCameraDevice.getModel()) ? CameraProperties.d : CameraProperties.c, (Callback<Void>) new Callback<Void>() {
                public void onFailure(int i, String str) {
                }

                public void onSuccess(Void voidR) {
                    if (!MoreCameraSettingActivity.this.isFinishing()) {
                        MoreCameraSettingActivity.this.refreshUI();
                    }
                }
            });
        }
    }

    private void initView() {
        TextView textView = (TextView) findViewById(R.id.title_bar_title);
        textView.setText(R.string.more_camera_setting);
        this.mSubTitle = (TextView) findViewById(R.id.sub_title_bar_title);
        textView.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                MoreCameraSettingActivity.this.showCameraInfo();
                return true;
            }
        });
        findViewById(R.id.title_bar_more).setVisibility(8);
        this.mLightItem = (SettingsItemView) findViewById(R.id.settings_light);
        this.mOnlyWifi = (SettingsItemView) findViewById(R.id.only_wifi);
        this.tvReset = (TextView) findViewById(R.id.tvReset);
        this.mLightItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                MoreCameraSettingActivity.this.mLightItem.setSwitchEnable(false);
                Event.a(Event.r);
                MoreCameraSettingActivity.this.mCameraProperties.a("light", z, (Callback<Void>) new Callback<Void>() {
                    public void onSuccess(Void voidR) {
                        if (!MoreCameraSettingActivity.this.isFinishing()) {
                            MoreCameraSettingActivity.this.refreshUI();
                            MoreCameraSettingActivity.this.mLightItem.setSwitchEnable(true);
                        }
                    }

                    public void onFailure(int i, String str) {
                        if (!MoreCameraSettingActivity.this.isFinishing()) {
                            MoreCameraSettingActivity.this.refreshUI();
                            MoreCameraSettingActivity.this.mLightItem.setSwitchEnable(true);
                        }
                    }
                });
            }
        });
        this.mOnlyWifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Event.a(Event.u);
                MoreCameraSettingActivity.this.mCameraDevice.e().b(z);
                MoreCameraSettingActivity.this.mCameraDevice.g().a(LocalSettings.b);
            }
        });
        this.tvReset.setOnClickListener(this);
        findViewById(R.id.settings_infrared).setOnClickListener(this);
        findViewById(R.id.settings_view).setOnClickListener(this);
        findViewById(R.id.settings_power).setOnClickListener(this);
        findViewById(R.id.title_bar_return).setOnClickListener(this);
        this.smartPictureFrame = (SettingsItemView) findViewById(R.id.smart_picture_frame);
        if (TextUtils.isEmpty(this.mCameraDevice.getModel()) || !this.mCameraDevice.getModel().equals(DeviceConstant.MIJIA_CAMERA_V3_UPGRADE)) {
            this.smartPictureFrame.setVisibility(8);
        } else {
            this.smartPictureFrame.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    MoreCameraSettingActivity.this.smartPictureFrame.setSwitchEnable(false);
                    MoreCameraSettingActivity.this.mCameraProperties.a(CameraPropertyBase.u, z, (Callback<Void>) new Callback<Void>() {
                        public void onSuccess(Void voidR) {
                            if (!MoreCameraSettingActivity.this.isFinishing()) {
                                MoreCameraSettingActivity.this.refreshUI();
                                MoreCameraSettingActivity.this.smartPictureFrame.setSwitchEnable(true);
                            }
                        }

                        public void onFailure(int i, String str) {
                            if (!MoreCameraSettingActivity.this.isFinishing()) {
                                MoreCameraSettingActivity.this.refreshUI();
                                MoreCameraSettingActivity.this.smartPictureFrame.setSwitchEnable(true);
                            }
                        }
                    });
                }
            });
        }
        if (this.mCameraDevice.o()) {
            findViewById(R.id.settings_float_window).setVisibility(0);
            findViewById(R.id.settings_timelapse).setVisibility(0);
            findViewById(R.id.settings_float_window).setOnClickListener(this);
            findViewById(R.id.settings_timelapse).setOnClickListener(this);
            return;
        }
        findViewById(R.id.settings_float_window).setVisibility(8);
        findViewById(R.id.settings_timelapse).setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void refreshUI() {
        this.mLightItem.setChecked(this.mCameraProperties.a("light", true));
        this.mOnlyWifi.setChecked(this.mCameraDevice.e().b());
        if (this.mCameraDevice != null && !TextUtils.isEmpty(this.mCameraDevice.getModel()) && this.mCameraDevice.getModel().equals(DeviceConstant.MIJIA_CAMERA_V3_UPGRADE)) {
            this.smartPictureFrame.setChecked(this.mCameraProperties.a(CameraPropertyBase.u, true));
        }
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.settings_float_window:
                if (!this.mCameraDevice.f().c()) {
                    ToastUtil.a((Context) this, (int) R.string.power_off);
                    return;
                } else if (XmPluginHostApi.instance().getApiLevel() < 57) {
                    SDKLog.e(Tag.b, "pip not 36");
                    ToastUtil.a((Context) this, (CharSequence) getResources().getString(R.string.api_tip_title));
                    return;
                } else {
                    Event.a(Event.m);
                    Event.a(Event.aH);
                    if (Util.a((Context) this)) {
                        XmPluginHostApi.instance().openCameraFloatingWindow(this.mCameraDevice.getDid());
                        Intent intent = new Intent();
                        intent.putExtra(Constants.Event.FINISH, true);
                        intent.putExtra("open_float_window", true);
                        setResult(-1, intent);
                        finish();
                        return;
                    }
                    ToastUtil.a((Context) this, (CharSequence) getResources().getString(R.string.float_tip));
                    return;
                }
            case R.id.settings_infrared:
                Event.a(Event.v);
                startActivity(new Intent(this, InfraredSettingActivity.class));
                return;
            case R.id.settings_power:
                Event.a(Event.n);
                startActivity(new Intent(this, SleepSettingActivity.class));
                return;
            case R.id.settings_timelapse:
                startActivity(new Intent(this, TimeLapsePhotographyActivity.class));
                return;
            case R.id.settings_view:
                startActivity(new Intent(this, ViewSettingActivity.class));
                return;
            case R.id.title_bar_return:
                finish();
                return;
            case R.id.tvReset:
                if (!this.mCameraDevice.isOwner()) {
                    ToastUtil.a((Context) activity(), (int) R.string.auth_fail);
                    return;
                }
                MLAlertDialog.Builder builder = new MLAlertDialog.Builder(this);
                builder.a((int) R.string.setting_restart);
                builder.b((int) R.string.setting_restart_tip);
                builder.b((int) R.string.cancel, (DialogInterface.OnClickListener) null);
                builder.a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        final XQProgressDialog xQProgressDialog = new XQProgressDialog(MoreCameraSettingActivity.this.activity());
                        xQProgressDialog.setMessage(MoreCameraSettingActivity.this.getString(R.string.alarm_select_set_ing));
                        xQProgressDialog.setCancelable(true);
                        xQProgressDialog.show();
                        Event.a(Event.R);
                        MoreCameraSettingActivity.this.mCameraDevice.d(new Callback<Void>() {
                            public void onSuccess(Void voidR) {
                                xQProgressDialog.dismiss();
                                MoreCameraSettingActivity.this.mCameraDevice.g().a(LocalSettings.c);
                                ToastUtil.a((Context) MoreCameraSettingActivity.this.activity(), (int) R.string.settings_set_success);
                            }

                            public void onFailure(int i, String str) {
                                xQProgressDialog.dismiss();
                                ToastUtil.a((Context) MoreCameraSettingActivity.this.activity(), (int) R.string.set_failed);
                            }
                        });
                    }
                });
                builder.d();
                return;
            default:
                return;
        }
    }

    public void onBackPressed() {
        if (this.mSubTitle.getVisibility() == 0) {
            this.mSubTitle.setVisibility(8);
        } else {
            super.onBackPressed();
        }
    }
}
