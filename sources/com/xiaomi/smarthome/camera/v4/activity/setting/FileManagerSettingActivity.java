package com.xiaomi.smarthome.camera.v4.activity.setting;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.mijia.app.Event;
import com.mijia.camera.CameraProperties;
import com.mijia.camera.nas.NASInfo;
import com.mijia.model.property.CameraPropertyBase;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.activity.CameraBaseActivity;
import com.xiaomi.smarthome.camera.activity.nas.NASDiscoveryActivity;
import com.xiaomi.smarthome.camera.activity.nas.NASInfoActivity;
import com.xiaomi.smarthome.camera.view.widget.SettingsItemView;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;

public class FileManagerSettingActivity extends CameraBaseActivity {
    private final int SD_CARD_STATUS = 1001;
    CameraProperties mCameraProperties;
    SettingsItemView mRecordModel;
    /* access modifiers changed from: private */
    public String mRecordStatus = "";
    SettingsItemView mSDCardItem;
    View mSDCardItemCopy;
    SettingsItemView mSmbBackupItem;
    /* access modifiers changed from: private */
    public XQProgressDialog mXQProgressDialog;

    public void doCreate(Bundle bundle) {
        super.doCreate(bundle);
        setContentView(R.layout.file_setting_activity);
        this.mCameraProperties = (CameraProperties) this.mCameraDevice.f();
        initView();
        refreshUI();
        Event.a(Event.L);
    }

    private void initView() {
        this.mRecordModel = (SettingsItemView) findViewById(R.id.settings_sdcard_record);
        this.mSDCardItem = (SettingsItemView) findViewById(R.id.settings_sdcard);
        this.mSDCardItemCopy = findViewById(R.id.settings_sdcard_view);
        this.mRecordModel.setOnClickListener(new View.OnClickListener() {
            /* JADX WARNING: Removed duplicated region for block: B:12:0x00d4  */
            /* JADX WARNING: Removed duplicated region for block: B:13:0x00de  */
            /* JADX WARNING: Removed duplicated region for block: B:14:0x00e8  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onClick(android.view.View r25) {
                /*
                    r24 = this;
                    r11 = r24
                    java.lang.String r0 = com.mijia.app.Event.M
                    com.mijia.app.Event.a((java.lang.String) r0)
                    java.lang.String r0 = "plg.gn9.dr4.dsi"
                    com.mijia.app.Event.a((java.lang.String) r0)
                    com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder r0 = new com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder
                    com.xiaomi.smarthome.camera.v4.activity.setting.FileManagerSettingActivity r1 = com.xiaomi.smarthome.camera.v4.activity.setting.FileManagerSettingActivity.this
                    r0.<init>(r1)
                    r1 = 2131498600(0x7f0c1668, float:1.8620826E38)
                    r0.a((int) r1)
                    com.xiaomi.smarthome.camera.v4.activity.setting.FileManagerSettingActivity r1 = com.xiaomi.smarthome.camera.v4.activity.setting.FileManagerSettingActivity.this
                    com.xiaomi.smarthome.camera.v4.activity.setting.FileManagerSettingActivity r2 = com.xiaomi.smarthome.camera.v4.activity.setting.FileManagerSettingActivity.this
                    com.mijia.camera.CameraProperties r2 = r2.mCameraProperties
                    java.lang.String r2 = r2.d()
                    java.lang.String unused = r1.mRecordStatus = r2
                    com.xiaomi.smarthome.camera.v4.activity.setting.FileManagerSettingActivity r1 = com.xiaomi.smarthome.camera.v4.activity.setting.FileManagerSettingActivity.this
                    android.view.LayoutInflater r1 = android.view.LayoutInflater.from(r1)
                    r2 = 0
                    r3 = 2130904075(0x7f03040b, float:1.7414986E38)
                    android.view.View r12 = r1.inflate(r3, r2)
                    r0.b((android.view.View) r12)
                    r1 = 2131493823(0x7f0c03bf, float:1.8611137E38)
                    r0.b((int) r1, (android.content.DialogInterface.OnClickListener) r2)
                    com.xiaomi.smarthome.camera.v4.activity.setting.FileManagerSettingActivity$1$1 r1 = new com.xiaomi.smarthome.camera.v4.activity.setting.FileManagerSettingActivity$1$1
                    r1.<init>()
                    r2 = 2131497396(0x7f0c11b4, float:1.8618384E38)
                    r0.a((int) r2, (android.content.DialogInterface.OnClickListener) r1)
                    r1 = 2131432766(0x7f0b153e, float:1.8487299E38)
                    android.view.View r1 = r12.findViewById(r1)
                    r13 = r1
                    android.widget.TextView r13 = (android.widget.TextView) r13
                    r1 = 2131432767(0x7f0b153f, float:1.84873E38)
                    android.view.View r1 = r12.findViewById(r1)
                    r14 = r1
                    android.widget.TextView r14 = (android.widget.TextView) r14
                    r1 = 2131432768(0x7f0b1540, float:1.8487303E38)
                    android.view.View r1 = r12.findViewById(r1)
                    r15 = r1
                    android.widget.TextView r15 = (android.widget.TextView) r15
                    r1 = 2131432911(0x7f0b15cf, float:1.8487593E38)
                    android.view.View r1 = r12.findViewById(r1)
                    r10 = r1
                    android.widget.TextView r10 = (android.widget.TextView) r10
                    r1 = 2131432912(0x7f0b15d0, float:1.8487595E38)
                    android.view.View r1 = r12.findViewById(r1)
                    r9 = r1
                    android.widget.TextView r9 = (android.widget.TextView) r9
                    r1 = 2131432913(0x7f0b15d1, float:1.8487597E38)
                    android.view.View r1 = r12.findViewById(r1)
                    r8 = r1
                    android.widget.TextView r8 = (android.widget.TextView) r8
                    r1 = 2131432273(0x7f0b1351, float:1.8486299E38)
                    android.view.View r1 = r12.findViewById(r1)
                    r7 = r1
                    android.widget.ImageView r7 = (android.widget.ImageView) r7
                    r1 = 2131432274(0x7f0b1352, float:1.84863E38)
                    android.view.View r1 = r12.findViewById(r1)
                    r6 = r1
                    android.widget.ImageView r6 = (android.widget.ImageView) r6
                    r1 = 2131432275(0x7f0b1353, float:1.8486303E38)
                    android.view.View r1 = r12.findViewById(r1)
                    r5 = r1
                    android.widget.ImageView r5 = (android.widget.ImageView) r5
                    com.xiaomi.smarthome.library.common.dialog.MLAlertDialog r16 = r0.b()
                    com.xiaomi.smarthome.camera.v4.activity.setting.FileManagerSettingActivity r0 = com.xiaomi.smarthome.camera.v4.activity.setting.FileManagerSettingActivity.this
                    java.lang.String r0 = r0.mRecordStatus
                    int r1 = r0.hashCode()
                    r2 = 3551(0xddf, float:4.976E-42)
                    r3 = 0
                    r4 = 1
                    if (r1 == r2) goto L_0x00c6
                    r2 = 3540994(0x360802, float:4.96199E-39)
                    if (r1 == r2) goto L_0x00bc
                    goto L_0x00d0
                L_0x00bc:
                    java.lang.String r1 = "stop"
                    boolean r0 = r0.equals(r1)
                    if (r0 == 0) goto L_0x00d0
                    r0 = 1
                    goto L_0x00d1
                L_0x00c6:
                    java.lang.String r1 = "on"
                    boolean r0 = r0.equals(r1)
                    if (r0 == 0) goto L_0x00d0
                    r0 = 0
                    goto L_0x00d1
                L_0x00d0:
                    r0 = -1
                L_0x00d1:
                    switch(r0) {
                        case 0: goto L_0x00e8;
                        case 1: goto L_0x00de;
                        default: goto L_0x00d4;
                    }
                L_0x00d4:
                    r7.setVisibility(r3)
                    r13.setSelected(r4)
                    r10.setSelected(r4)
                    goto L_0x00f1
                L_0x00de:
                    r5.setVisibility(r3)
                    r15.setSelected(r4)
                    r8.setSelected(r4)
                    goto L_0x00f1
                L_0x00e8:
                    r6.setVisibility(r3)
                    r14.setSelected(r4)
                    r9.setSelected(r4)
                L_0x00f1:
                    r0 = 2131430320(0x7f0b0bb0, float:1.8482338E38)
                    android.view.View r4 = r12.findViewById(r0)
                    com.xiaomi.smarthome.camera.v4.activity.setting.FileManagerSettingActivity$1$2 r3 = new com.xiaomi.smarthome.camera.v4.activity.setting.FileManagerSettingActivity$1$2
                    r0 = r3
                    r1 = r24
                    r2 = r5
                    r11 = r3
                    r3 = r15
                    r17 = r15
                    r15 = r4
                    r4 = r8
                    r18 = r5
                    r5 = r6
                    r19 = r6
                    r6 = r14
                    r20 = r7
                    r7 = r9
                    r21 = r8
                    r8 = r20
                    r22 = r9
                    r9 = r13
                    r23 = r10
                    r0.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10)
                    r15.setOnClickListener(r11)
                    r0 = 2131430319(0x7f0b0baf, float:1.8482336E38)
                    android.view.View r11 = r12.findViewById(r0)
                    com.xiaomi.smarthome.camera.v4.activity.setting.FileManagerSettingActivity$1$3 r15 = new com.xiaomi.smarthome.camera.v4.activity.setting.FileManagerSettingActivity$1$3
                    r0 = r15
                    r2 = r18
                    r3 = r17
                    r4 = r21
                    r5 = r20
                    r6 = r13
                    r7 = r23
                    r8 = r19
                    r9 = r14
                    r10 = r22
                    r0.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10)
                    r11.setOnClickListener(r15)
                    r0 = 2131430321(0x7f0b0bb1, float:1.848234E38)
                    android.view.View r11 = r12.findViewById(r0)
                    com.xiaomi.smarthome.camera.v4.activity.setting.FileManagerSettingActivity$1$4 r12 = new com.xiaomi.smarthome.camera.v4.activity.setting.FileManagerSettingActivity$1$4
                    r0 = r12
                    r0.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10)
                    r11.setOnClickListener(r12)
                    r16.show()
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.camera.v4.activity.setting.FileManagerSettingActivity.AnonymousClass1.onClick(android.view.View):void");
            }
        });
        this.mSDCardItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Event.a(Event.N);
                Event.a(Event.bO);
                FileManagerSettingActivity.this.startActivityForResult(new Intent(FileManagerSettingActivity.this, SDCardStatusActivity.class), 1001);
            }
        });
        this.mSDCardItemCopy.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int a2 = FileManagerSettingActivity.this.mCameraProperties.a(CameraPropertyBase.k);
                if (a2 == 1 || a2 == 5) {
                    FileManagerSettingActivity.this.startActivity(new Intent(FileManagerSettingActivity.this, NoMemoryCardActivity.class));
                }
            }
        });
        this.mSmbBackupItem = (SettingsItemView) findViewById(R.id.settings_smb_backup);
        this.mSmbBackupItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (FileManagerSettingActivity.this.mCameraProperties.a(CameraPropertyBase.k) == 1) {
                    MLAlertDialog.Builder builder = new MLAlertDialog.Builder(FileManagerSettingActivity.this);
                    builder.b((int) R.string.nas_no_sdcard_hint);
                    builder.a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    builder.b().show();
                    return;
                }
                Event.a(Event.O);
                if (FileManagerSettingActivity.this.mCameraDevice != null) {
                    FileManagerSettingActivity.this.startNAS();
                } else {
                    ToastUtil.a((Context) FileManagerSettingActivity.this, (int) R.string.retrieve_data_fail);
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
        this.mCameraProperties.a(new String[]{CameraPropertyBase.k, CameraPropertyBase.h}, (Callback<Void>) new Callback<Void>() {
            public void onSuccess(Void voidR) {
                if (!FileManagerSettingActivity.this.isFinishing()) {
                    FileManagerSettingActivity.this.refreshUI();
                }
            }

            public void onFailure(int i, String str) {
                if (!FileManagerSettingActivity.this.isFinishing()) {
                    FileManagerSettingActivity.this.refreshUI();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0087  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void refreshUI() {
        /*
            r6 = this;
            com.mijia.camera.CameraProperties r0 = r6.mCameraProperties
            java.lang.String r1 = "sdcard_status"
            int r0 = r0.a((java.lang.String) r1)
            com.xiaomi.smarthome.camera.view.widget.SettingsItemView r1 = r6.mSDCardItem
            android.content.res.Resources r2 = r6.getResources()
            java.lang.String r2 = com.xiaomi.smarthome.camera.v4.utils.Util.getSdCardStatus(r2, r0)
            r1.setInfo(r2)
            com.mijia.camera.CameraProperties r1 = r6.mCameraProperties
            java.lang.String r1 = r1.d()
            int r2 = r1.hashCode()
            r3 = 3551(0xddf, float:4.976E-42)
            r4 = 0
            r5 = 1
            if (r2 == r3) goto L_0x0035
            r3 = 3540994(0x360802, float:4.96199E-39)
            if (r2 == r3) goto L_0x002b
            goto L_0x003f
        L_0x002b:
            java.lang.String r2 = "stop"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x003f
            r1 = 0
            goto L_0x0040
        L_0x0035:
            java.lang.String r2 = "on"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x003f
            r1 = 1
            goto L_0x0040
        L_0x003f:
            r1 = -1
        L_0x0040:
            switch(r1) {
                case 0: goto L_0x005d;
                case 1: goto L_0x0050;
                default: goto L_0x0043;
            }
        L_0x0043:
            com.xiaomi.smarthome.camera.view.widget.SettingsItemView r1 = r6.mRecordModel
            r2 = 2131498594(0x7f0c1662, float:1.8620814E38)
            java.lang.String r2 = r6.getString(r2)
            r1.setInfo(r2)
            goto L_0x0069
        L_0x0050:
            com.xiaomi.smarthome.camera.view.widget.SettingsItemView r1 = r6.mRecordModel
            r2 = 2131498598(0x7f0c1666, float:1.8620822E38)
            java.lang.String r2 = r6.getString(r2)
            r1.setInfo(r2)
            goto L_0x0069
        L_0x005d:
            com.xiaomi.smarthome.camera.view.widget.SettingsItemView r1 = r6.mRecordModel
            r2 = 2131498596(0x7f0c1664, float:1.8620818E38)
            java.lang.String r2 = r6.getString(r2)
            r1.setInfo(r2)
        L_0x0069:
            if (r0 != r5) goto L_0x0087
            com.xiaomi.smarthome.camera.view.widget.SettingsItemView r0 = r6.mSDCardItem
            r0.setEnabled(r4)
            android.view.View r0 = r6.mSDCardItemCopy
            r0.setEnabled(r5)
            java.lang.String r0 = "plg.gn9.m4l.f5v"
            java.lang.String r1 = "type"
            r2 = 2
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            com.mijia.app.Event.a((java.lang.String) r0, (java.lang.String) r1, (java.lang.Object) r2)
            com.xiaomi.smarthome.camera.view.widget.SettingsItemView r0 = r6.mRecordModel
            r0.setEnabled(r4)
            goto L_0x00d4
        L_0x0087:
            r1 = 5
            r2 = 8
            if (r0 == r1) goto L_0x00b5
            r1 = 3
            if (r0 != r1) goto L_0x0090
            goto L_0x00b5
        L_0x0090:
            com.xiaomi.smarthome.camera.view.widget.SettingsItemView r0 = r6.mSDCardItem
            r0.setEnabled(r5)
            com.xiaomi.smarthome.camera.view.widget.SettingsItemView r0 = r6.mSmbBackupItem
            r0.setEnabled(r5)
            com.xiaomi.smarthome.camera.view.widget.SettingsItemView r0 = r6.mRecordModel
            r0.setEnabled(r5)
            android.view.View r0 = r6.mSDCardItemCopy
            r0.setEnabled(r4)
            android.view.View r0 = r6.mSDCardItemCopy
            r0.setVisibility(r2)
            java.lang.String r0 = "plg.gn9.m4l.f5v"
            java.lang.String r1 = "type"
            java.lang.Integer r2 = java.lang.Integer.valueOf(r5)
            com.mijia.app.Event.a((java.lang.String) r0, (java.lang.String) r1, (java.lang.Object) r2)
            goto L_0x00d4
        L_0x00b5:
            com.xiaomi.smarthome.camera.view.widget.SettingsItemView r0 = r6.mRecordModel
            r0.setEnabled(r4)
            com.xiaomi.smarthome.camera.view.widget.SettingsItemView r0 = r6.mSDCardItem
            r0.setEnabled(r5)
            android.view.View r0 = r6.mSDCardItemCopy
            r0.setEnabled(r4)
            android.view.View r0 = r6.mSDCardItemCopy
            r0.setVisibility(r2)
            java.lang.String r0 = "plg.gn9.m4l.f5v"
            java.lang.String r1 = "type"
            java.lang.Integer r2 = java.lang.Integer.valueOf(r5)
            com.mijia.app.Event.a((java.lang.String) r0, (java.lang.String) r1, (java.lang.Object) r2)
        L_0x00d4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.camera.v4.activity.setting.FileManagerSettingActivity.refreshUI():void");
    }

    private void initProgressDialog() {
        this.mXQProgressDialog = new XQProgressDialog(this);
        this.mXQProgressDialog.setMessage(getString(R.string.smb_waiting));
        this.mXQProgressDialog.setCancelable(true);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1001 && i2 == -1) {
            refreshUI();
            this.mCameraProperties.a(new String[]{CameraPropertyBase.k}, (Callback<Void>) new Callback<Void>() {
                public void onFailure(int i, String str) {
                }

                public void onSuccess(Void voidR) {
                    if (!FileManagerSettingActivity.this.isFinishing()) {
                        FileManagerSettingActivity.this.refreshUI();
                    }
                }
            });
            return;
        }
        super.onActivityResult(i, i2, intent);
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
                        FileManagerSettingActivity.this.startActivity(new Intent(FileManagerSettingActivity.this, NASDiscoveryActivity.class));
                        return;
                    }
                    FileManagerSettingActivity.this.startActivity(new Intent(FileManagerSettingActivity.this, NASInfoActivity.class));
                    Event.a(Event.bT, "type", (Object) 1);
                }
            }

            public void onFailure(int i, String str) {
                if (!FileManagerSettingActivity.this.isFinishing()) {
                    FileManagerSettingActivity.this.mXQProgressDialog.dismiss();
                    ToastUtil.a((Context) FileManagerSettingActivity.this, (int) R.string.retrieve_data_fail);
                }
            }
        });
    }
}
