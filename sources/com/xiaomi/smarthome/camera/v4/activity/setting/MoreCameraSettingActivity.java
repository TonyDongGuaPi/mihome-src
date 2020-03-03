package com.xiaomi.smarthome.camera.v4.activity.setting;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.Utils.Util;
import com.mijia.app.Event;
import com.mijia.camera.CameraProperties;
import com.mijia.debug.SDKLog;
import com.mijia.debug.Tag;
import com.mijia.model.alarm.AlarmNetUtils;
import com.mijia.model.local.LocalSettings;
import com.taobao.weex.common.Constants;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.activity.CameraBaseActivity;
import com.xiaomi.smarthome.camera.view.widget.SettingsItemView;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;

public class MoreCameraSettingActivity extends CameraBaseActivity implements View.OnClickListener {
    CameraProperties mCameraProperties;
    SettingsItemView mLightItem;
    SettingsItemView mOnlyWifi;
    private TextView tvReset;

    public void doCreate(Bundle bundle) {
        super.doCreate(bundle);
        setContentView(R.layout.more_camera_activity);
        this.mCameraProperties = (CameraProperties) this.mCameraDevice.f();
        initView();
        refreshUI();
        Event.a(Event.k);
    }

    private void initView() {
        ((TextView) findViewById(R.id.title_bar_title)).setText(R.string.more_camera_setting);
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
        findViewById(R.id.settings_device_calibration).setOnClickListener(this);
        findViewById(R.id.settings_float_window).setOnClickListener(this);
        if (!(!CoreApi.a().D() && (AlarmNetUtils.e() || !"chuangmi.camera.ipc009".equals(this.mDeviceStat.model)))) {
            findViewById(R.id.settings_device_calibration).setVisibility(8);
            findViewById(R.id.settings_float_window).setVisibility(8);
            findViewById(R.id.divider_line).setVisibility(8);
        }
    }

    /* access modifiers changed from: private */
    public void refreshUI() {
        this.mLightItem.setChecked(this.mCameraProperties.a("light", true));
        this.mOnlyWifi.setChecked(this.mCameraDevice.e().b());
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.settings_device_calibration:
                Event.a(Event.f);
                Event.a(Event.aG);
                Intent intent = new Intent();
                intent.putExtra(Constants.Event.FINISH, true);
                intent.putExtra("start_calibration", true);
                setResult(-1, intent);
                finish();
                return;
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
                        Intent intent2 = new Intent();
                        intent2.putExtra(Constants.Event.FINISH, true);
                        intent2.putExtra("open_float_window", true);
                        setResult(-1, intent2);
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
            case R.id.settings_view:
                Event.a(Event.q);
                startActivity(new Intent(this, ViewSettingActivity.class));
                return;
            case R.id.title_bar_return:
                finish();
                return;
            case R.id.tvReset:
                MLAlertDialog.Builder builder = new MLAlertDialog.Builder(this);
                builder.a((int) R.string.setting_restart);
                builder.b((int) R.string.setting_restart_tip);
                builder.b((int) R.string.cancel, (DialogInterface.OnClickListener) null);
                builder.a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        final XQProgressDialog xQProgressDialog = new XQProgressDialog(MoreCameraSettingActivity.this);
                        xQProgressDialog.setMessage(MoreCameraSettingActivity.this.getString(R.string.alarm_select_set_ing));
                        xQProgressDialog.setCancelable(true);
                        xQProgressDialog.show();
                        Event.a(Event.R);
                        MoreCameraSettingActivity.this.mCameraDevice.d(new Callback<Void>() {
                            public void onSuccess(Void voidR) {
                                xQProgressDialog.dismiss();
                                MoreCameraSettingActivity.this.mCameraDevice.g().a(LocalSettings.c);
                                ToastUtil.a((Context) MoreCameraSettingActivity.this, (int) R.string.settings_set_success);
                            }

                            public void onFailure(int i, String str) {
                                xQProgressDialog.dismiss();
                                ToastUtil.a((Context) MoreCameraSettingActivity.this, (int) R.string.set_failed);
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
}
