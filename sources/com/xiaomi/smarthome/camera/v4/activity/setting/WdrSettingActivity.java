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
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;

public class WdrSettingActivity extends CameraBaseActivity {
    SettingsItemView mWdrItem;
    /* access modifiers changed from: private */
    public XQProgressDialog progressDialog;

    public void doCreate(Bundle bundle) {
        super.doCreate(bundle);
        setContentView(R.layout.activity_setting_wdr);
        findViewById(R.id.title_bar_more).setVisibility(8);
        this.mWdrItem = (SettingsItemView) findViewById(R.id.wdr_setting);
        findViewById(R.id.title_bar_return).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WdrSettingActivity.this.onBackPressed();
            }
        });
        TextView textView = (TextView) findViewById(R.id.title_bar_title);
        textView.setText(R.string.setting_wdr);
        textView.setMaxLines(2);
        this.mWdrItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Event.a(Event.z);
                WdrSettingActivity.this.mWdrItem.setSwitchEnable(false);
                if (WdrSettingActivity.this.progressDialog == null) {
                    XQProgressDialog unused = WdrSettingActivity.this.progressDialog = new XQProgressDialog(WdrSettingActivity.this);
                }
                WdrSettingActivity.this.progressDialog.show();
                WdrSettingActivity.this.mCameraDevice.f().a(CameraPropertyBase.m, z, (Callback<Void>) new Callback<Void>() {
                    public void onSuccess(Void voidR) {
                        if (!WdrSettingActivity.this.isFinishing()) {
                            if (WdrSettingActivity.this.progressDialog != null) {
                                WdrSettingActivity.this.progressDialog.dismiss();
                            }
                            WdrSettingActivity.this.refreshUI();
                            WdrSettingActivity.this.mWdrItem.setSwitchEnable(true);
                        }
                    }

                    public void onFailure(int i, String str) {
                        if (!WdrSettingActivity.this.isFinishing()) {
                            if (WdrSettingActivity.this.progressDialog != null) {
                                WdrSettingActivity.this.progressDialog.dismiss();
                            }
                            WdrSettingActivity.this.refreshUI();
                            WdrSettingActivity.this.mWdrItem.setSwitchEnable(true);
                        }
                    }
                });
            }
        });
        refreshUI();
    }

    /* access modifiers changed from: private */
    public void refreshUI() {
        this.mWdrItem.setChecked(this.mCameraDevice.f().b(CameraPropertyBase.m));
    }
}
