package com.xiaomi.smarthome.camera.activity.setting;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.mijia.app.Event;
import com.mijia.camera.CameraProperties;
import com.mijia.camera.Utils.Util;
import com.mijia.camera.nas.NASInfo;
import com.mijia.debug.SDKLog;
import com.mijia.debug.Tag;
import com.mijia.model.property.CameraPropertyBase;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.activity.CameraBaseActivity;
import com.xiaomi.smarthome.camera.activity.nas.NASDiscoveryActivity;
import com.xiaomi.smarthome.camera.activity.nas.NASInfoActivity;
import com.xiaomi.smarthome.camera.v4.activity.setting.NoMemoryCardActivity;
import com.xiaomi.smarthome.camera.view.widget.SettingsItemView;
import com.xiaomi.smarthome.camera.view.widget.SettingsItemViewMultiLine;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;

public class FileManagerSettingActivity extends CameraBaseActivity {
    private final int SD_CARD_STATUS = 1001;
    CameraProperties mCameraProperties;
    private String mRecordStatus = "";
    SettingsItemViewMultiLine mSDCardItem;
    /* access modifiers changed from: private */
    public int mSdcardStatus;
    /* access modifiers changed from: private */
    public int mSdcardStatusFromNet;
    SettingsItemView mSmbBackupItem;
    /* access modifiers changed from: private */
    public XQProgressDialog mXQProgressDialog;

    public void doCreate(Bundle bundle) {
        super.doCreate(bundle);
        setContentView(R.layout.camera_file_setting_activity);
        this.mCameraProperties = (CameraProperties) this.mCameraDevice.f();
        initView();
        refreshUI();
    }

    private void initView() {
        this.mSDCardItem = (SettingsItemViewMultiLine) findViewById(R.id.settings_sdcard);
        this.mSDCardItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Event.a(Event.bO);
                if (FileManagerSettingActivity.this.mSdcardStatus == 4) {
                    ToastUtil.a((Context) FileManagerSettingActivity.this.activity(), (CharSequence) FileManagerSettingActivity.this.getString(R.string.camera_storage_sdcard_out_tips));
                } else if (FileManagerSettingActivity.this.mSdcardStatus == 2) {
                    FileManagerSettingActivity.this.startActivity(new Intent(FileManagerSettingActivity.this.activity(), NoMemoryCardActivity.class));
                } else if (FileManagerSettingActivity.this.mSdcardStatus == 5) {
                    ToastUtil.a((Context) FileManagerSettingActivity.this.activity(), (CharSequence) FileManagerSettingActivity.this.getString(R.string.camera_storage_sdcard_formating_tips));
                } else {
                    FileManagerSettingActivity.this.startActivityForResult(new Intent(FileManagerSettingActivity.this.activity(), SDCardStatusActivityNew.class), 1001);
                }
            }
        });
        this.mSmbBackupItem = (SettingsItemView) findViewById(R.id.settings_smb_backup);
        if (this.mCameraDevice.o()) {
            this.mSmbBackupItem.setVisibility(8);
            findViewById(R.id.backup_title).setVisibility(8);
        }
        this.mSmbBackupItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Event.a(Event.O);
                int access$100 = FileManagerSettingActivity.this.mSdcardStatusFromNet;
                if (access$100 == 1 || access$100 == 5) {
                    MLAlertDialog.Builder builder = new MLAlertDialog.Builder(FileManagerSettingActivity.this.activity());
                    builder.a((int) R.string.nas_no_sdcard_hint);
                    builder.a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    builder.b().show();
                } else if (FileManagerSettingActivity.this.mCameraDevice != null) {
                    FileManagerSettingActivity.this.startNAS();
                } else {
                    ToastUtil.a((Context) FileManagerSettingActivity.this.activity(), (int) R.string.retrieve_data_fail);
                }
            }
        });
        findViewById(R.id.title_bar_return).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FileManagerSettingActivity.this.finish();
            }
        });
        findViewById(R.id.title_bar_more).setVisibility(8);
        ((TextView) findViewById(R.id.title_bar_title)).setText(R.string.more_store_setting);
        updateSdcardStatus();
    }

    /* access modifiers changed from: private */
    public void refreshUI() {
        int i = this.mSdcardStatusFromNet;
        int b = Util.b(getResources(), i, "stop".equalsIgnoreCase(this.mCameraProperties.d()));
        String string = getString(R.string.camera_storage_normal_status);
        switch (b) {
            case 1:
                string = getString(R.string.camera_storage_normal_status);
                break;
            case 2:
                string = getString(R.string.sd_card_status1);
                break;
            case 3:
                string = getString(R.string.camera_storage_unnormal_status);
                break;
            case 4:
                string = getString(R.string.camera_storage_sdcard_out);
                break;
            case 5:
                string = getString(R.string.sd_card_status4);
                break;
            case 6:
                string = getString(R.string.camera_storage_pause);
                break;
        }
        SDKLog.e(Tag.d, "sdcarad original status" + i + " ;display status:" + string);
        this.mSDCardItem.setInfo(string);
        this.mSdcardStatus = b;
        this.mSmbBackupItem.setEnabled(false);
        this.mSDCardItem.setEnabled(true);
        if (b == 1) {
            this.mSmbBackupItem.setEnabled(true);
        } else if (b == 6 && i != 1 && i != 5 && i != 3) {
            this.mSmbBackupItem.setEnabled(true);
        }
    }

    private void initProgressDialog() {
        this.mXQProgressDialog = new XQProgressDialog(activity());
        this.mXQProgressDialog.setMessage(getString(R.string.smb_waiting));
        this.mXQProgressDialog.setCancelable(true);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1001 && i2 == -1) {
            refreshUI();
            this.mHandler.postDelayed(new Runnable() {
                public void run() {
                    FileManagerSettingActivity.this.updateSdcardStatus();
                }
            }, 300);
            return;
        }
        super.onActivityResult(i, i2, intent);
    }

    /* access modifiers changed from: private */
    public void updateSdcardStatus() {
        this.mSdcardStatusFromNet = this.mCameraProperties.a(CameraPropertyBase.k);
        this.mCameraProperties.a(new String[]{CameraPropertyBase.h, CameraPropertyBase.k}, (Callback<Void>) new Callback<Void>() {
            public void onSuccess(Void voidR) {
                if (!FileManagerSettingActivity.this.isFinishing()) {
                    int unused = FileManagerSettingActivity.this.mSdcardStatusFromNet = FileManagerSettingActivity.this.mCameraProperties.a(CameraPropertyBase.k);
                    FileManagerSettingActivity.this.refreshUI();
                }
            }

            public void onFailure(int i, String str) {
                if (!FileManagerSettingActivity.this.isFinishing()) {
                    FileManagerSettingActivity.this.refreshUI();
                }
            }
        });
        refreshUI();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    /* access modifiers changed from: private */
    public void startNAS() {
        if (this.mXQProgressDialog == null) {
            initProgressDialog();
        }
        this.mXQProgressDialog.show();
        this.mCameraDevice.l().b(new Callback<NASInfo>() {
            public void onSuccess(NASInfo nASInfo) {
                if (!FileManagerSettingActivity.this.isFinishing()) {
                    FileManagerSettingActivity.this.mXQProgressDialog.dismiss();
                    if (nASInfo == null || nASInfo.f() == 0) {
                        Event.a(Event.bT, "type", (Object) 2);
                        FileManagerSettingActivity.this.startActivity(new Intent(FileManagerSettingActivity.this.activity(), NASDiscoveryActivity.class));
                        return;
                    }
                    Event.a(Event.bT, "type", (Object) 1);
                    FileManagerSettingActivity.this.startActivity(new Intent(FileManagerSettingActivity.this.activity(), NASInfoActivity.class));
                }
            }

            public void onFailure(int i, String str) {
                if (!FileManagerSettingActivity.this.isFinishing()) {
                    FileManagerSettingActivity.this.mXQProgressDialog.dismiss();
                    ToastUtil.a((Context) FileManagerSettingActivity.this.activity(), (int) R.string.retrieve_data_fail);
                }
            }
        });
    }
}
