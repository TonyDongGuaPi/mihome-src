package com.xiaomi.smarthome.camera.activity.setting;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import com.mijia.model.property.CameraPropertyBase;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.activity.CameraBaseActivity;
import com.xiaomi.smarthome.camera.view.widget.SettingsItemView;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;

public class InfraredSettingActivity extends CameraBaseActivity implements SettingsItemView.OnSelectedListener {
    /* access modifiers changed from: private */
    public int MSG_PROGRESS = 7001;
    private SettingsItemView mInfraredAuto;
    private SettingsItemView mInfraredClose;
    private SettingsItemView mInfraredOpen;
    /* access modifiers changed from: private */
    public int mLastType = 0;
    private XQProgressDialog mXQProgressDialog;

    public void doCreate(Bundle bundle) {
        super.doCreate(bundle);
        setContentView(R.layout.camera_activity_setting_infrared);
        findViewById(R.id.title_bar_more).setVisibility(8);
        ((TextView) findViewById(R.id.title_bar_title)).setText(R.string.setttings_infared);
        findViewById(R.id.title_bar_return).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                InfraredSettingActivity.this.finish();
            }
        });
        initView();
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        if (message.what == this.MSG_PROGRESS) {
            showProgress();
        }
    }

    private void initView() {
        this.mInfraredAuto = (SettingsItemView) findViewById(R.id.infrared_auto);
        this.mInfraredClose = (SettingsItemView) findViewById(R.id.infrared_close);
        this.mInfraredOpen = (SettingsItemView) findViewById(R.id.infrared_open);
        this.mInfraredAuto.setOnSelectedListener(this);
        this.mInfraredClose.setOnSelectedListener(this);
        this.mInfraredOpen.setOnSelectedListener(this);
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

    private void showProgress() {
        if (this.mXQProgressDialog == null) {
            this.mXQProgressDialog = new XQProgressDialog(activity());
        }
        this.mXQProgressDialog.show();
    }

    /* access modifiers changed from: private */
    public void hideProgress() {
        if (this.mXQProgressDialog != null && this.mXQProgressDialog.isShowing()) {
            this.mXQProgressDialog.dismiss();
        }
    }

    private void changeType(final int i) {
        initType(i);
        this.mHandler.sendEmptyMessageDelayed(this.MSG_PROGRESS, 500);
        this.mCameraDevice.f().a(CameraPropertyBase.n, (Object) Integer.valueOf(i), (Callback<Void>) new Callback<Void>() {
            public void onSuccess(Void voidR) {
                if (!InfraredSettingActivity.this.isFinishing()) {
                    int unused = InfraredSettingActivity.this.mLastType = i;
                    InfraredSettingActivity.this.initType(i);
                    InfraredSettingActivity.this.mHandler.removeMessages(InfraredSettingActivity.this.MSG_PROGRESS);
                    InfraredSettingActivity.this.hideProgress();
                }
            }

            public void onFailure(int i, String str) {
                if (!InfraredSettingActivity.this.isFinishing()) {
                    InfraredSettingActivity.this.initType(InfraredSettingActivity.this.mLastType);
                    InfraredSettingActivity.this.mHandler.removeMessages(InfraredSettingActivity.this.MSG_PROGRESS);
                    ToastUtil.a((Context) InfraredSettingActivity.this.activity(), (int) R.string.set_failed);
                    InfraredSettingActivity.this.hideProgress();
                }
            }
        });
    }

    public void onSelected(View view) {
        switch (view.getId()) {
            case R.id.infrared_auto:
                changeType(0);
                return;
            case R.id.infrared_close:
                changeType(1);
                return;
            case R.id.infrared_open:
                changeType(2);
                return;
            default:
                return;
        }
    }

    public void onDestroy() {
        super.onDestroy();
        this.mHandler.removeMessages(this.MSG_PROGRESS);
        hideProgress();
    }
}
