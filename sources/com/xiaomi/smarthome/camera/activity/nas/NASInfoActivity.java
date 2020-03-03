package com.xiaomi.smarthome.camera.activity.nas;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.mijia.app.AppCode;
import com.mijia.app.Event;
import com.mijia.camera.Utils.Util;
import com.mijia.camera.nas.NASInfo;
import com.mijia.debug.SDKLog;
import com.mijia.debug.Tag;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.activity.CameraBaseActivity;
import com.xiaomi.smarthome.camera.view.widget.SettingsItemView;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import java.util.Calendar;

public class NASInfoActivity extends CameraBaseActivity implements View.OnClickListener {
    private static final String TAG = "NASInfoActivity";
    private int REQUEST_NAS_DIR = 1002;
    private int REQUEST_NAS_EDIT = 1001;
    ImageView ivCameraStoreIcon;
    TextView latestLogInfoTv;
    /* access modifiers changed from: private */
    public int mCycleTime = 0;
    SettingsItemView mCycleTimeItem;
    /* access modifiers changed from: private */
    public int mLastCycleTime = 0;
    /* access modifiers changed from: private */
    public int mLastSyncInterval = 0;
    /* access modifiers changed from: private */
    public NASInfo mNASInfo = null;
    private SettingsItemView mNASName;
    SettingsItemView mSivVideoStorage;
    /* access modifiers changed from: private */
    public int mSyncInterval = 0;
    SettingsItemView mUpdateTimeIntervalItem;
    /* access modifiers changed from: private */
    public XQProgressDialog mXQProgressDialog;
    TextView pauseResumeTv;
    XQProgressDialog progressDialog;
    ImageView statusIconIv;
    ImageView titleBarReturn;
    TextView transferStatusTv;
    /* access modifiers changed from: private */
    public int type;

    public void doCreate(Bundle bundle) {
        super.doCreate(bundle);
        setContentView(R.layout.camera_activity_device_nas_info);
        findViewById(R.id.title_bar_more).setVisibility(8);
        initView();
        initProgressDialog();
        if (this.mCameraDevice.l().a() != null) {
            this.mNASInfo = this.mCameraDevice.l().a();
            updateView();
            return;
        }
        loadNASServerInfo();
    }

    public void onResume() {
        super.onResume();
        loadNASServerInfo();
    }

    private void initView() {
        this.titleBarReturn = (ImageView) findViewById(R.id.title_bar_return);
        this.statusIconIv = (ImageView) findViewById(R.id.status_icon_iv);
        this.ivCameraStoreIcon = (ImageView) findViewById(R.id.iv_camera_store_icon);
        this.ivCameraStoreIcon.setImageResource(this.isV4 ? R.drawable.camera_v4_store_icon_camera_nor : R.drawable.camera_store_icon_camera_nor);
        this.transferStatusTv = (TextView) findViewById(R.id.transfer_status_tv);
        this.latestLogInfoTv = (TextView) findViewById(R.id.latest_log_info_tv);
        this.pauseResumeTv = (TextView) findViewById(R.id.pause_storage_rl);
        this.mSivVideoStorage = (SettingsItemView) findViewById(R.id.sivVideoStorage);
        this.mCycleTimeItem = (SettingsItemView) findViewById(R.id.setting_smb_time);
        this.mUpdateTimeIntervalItem = (SettingsItemView) findViewById(R.id.setting_nas_sync_interval);
        ((TextView) findViewById(R.id.title_bar_title)).setText(R.string.setttings_route_backup_title);
        this.mSivVideoStorage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                NASInfoActivity.this.toggleNASStorageStatus();
            }
        });
        this.mCycleTimeItem.setOnClickListener(this);
        this.mUpdateTimeIntervalItem.setOnClickListener(this);
        this.pauseResumeTv.setOnClickListener(this);
        this.titleBarReturn.setOnClickListener(this);
        this.mNASName = (SettingsItemView) findViewById(R.id.modify_setting_rl);
        findViewById(R.id.delete_storage_rl).setOnClickListener(this);
        this.mNASName.setOnClickListener(this);
        findViewById(R.id.clear_uploaded_rl).setOnClickListener(this);
        findViewById(R.id.browser_uploaded_video_rl).setOnClickListener(this);
    }

    /* access modifiers changed from: private */
    public void loadNASServerInfo() {
        this.mXQProgressDialog.show();
        this.mCameraDevice.l().b(new Callback<NASInfo>() {
            public void onSuccess(NASInfo nASInfo) {
                if (!NASInfoActivity.this.isFinishing()) {
                    NASInfo unused = NASInfoActivity.this.mNASInfo = NASInfoActivity.this.mCameraDevice.l().a();
                    if (NASInfoActivity.this.mXQProgressDialog.isShowing()) {
                        NASInfoActivity.this.mXQProgressDialog.dismiss();
                    }
                    NASInfoActivity.this.updateView();
                    if (nASInfo.f() == 0) {
                        NASInfoActivity.this.startActivity(new Intent(NASInfoActivity.this, NASDiscoveryActivity.class));
                        NASInfoActivity.this.finish();
                    }
                }
            }

            public void onFailure(int i, String str) {
                if (!NASInfoActivity.this.isFinishing()) {
                    SDKLog.d(Tag.b, " loadNAS fail " + i + " : " + str);
                    if (NASInfoActivity.this.mXQProgressDialog.isShowing()) {
                        NASInfoActivity.this.mXQProgressDialog.dismiss();
                    }
                    NASInfoActivity.this.showRetryGetNASDialog();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void showRetryGetNASDialog() {
        MLAlertDialog.Builder builder = new MLAlertDialog.Builder(this);
        builder.a((int) R.string.get_smb_info_fail_title).b((int) R.string.get_smb_info_fail_title).b((int) R.string.get_smb_info_fail_neg_btn, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                NASInfoActivity.this.finish();
            }
        }).a((int) R.string.get_smb_info_fail_pos_btn, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                NASInfoActivity.this.loadNASServerInfo();
            }
        });
        builder.b().show();
    }

    public static String getReadableDateTime(long j) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        int i = instance.get(1);
        String alignedDateTimeField = getAlignedDateTimeField(instance.get(2) + 1);
        String alignedDateTimeField2 = getAlignedDateTimeField(instance.get(5));
        return (i + "/" + alignedDateTimeField + "/" + alignedDateTimeField2) + " " + (getAlignedDateTimeField(instance.get(11)) + ":" + getAlignedDateTimeField(instance.get(12)));
    }

    private static String getAlignedDateTimeField(int i) {
        StringBuilder sb;
        if (i < 10) {
            sb = new StringBuilder();
            sb.append("0");
            sb.append(i);
        } else {
            sb = new StringBuilder();
            sb.append(i);
            sb.append("");
        }
        return sb.toString();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 != -1) {
            return;
        }
        if (this.REQUEST_NAS_EDIT == i) {
            updateTransferStatus();
        } else if (this.REQUEST_NAS_DIR == i) {
            updateTransferStatus();
            TextUtils.isEmpty(this.mCameraDevice.l().a().b());
        }
    }

    /* access modifiers changed from: private */
    public void updateView() {
        if (this.mNASInfo != null) {
            if (this.mNASInfo.c() == 0) {
                this.latestLogInfoTv.setVisibility(8);
            } else {
                this.latestLogInfoTv.setVisibility(0);
                this.latestLogInfoTv.setText(getString(R.string.smb_latest_write_time, new Object[]{getReadableDateTime(this.mNASInfo.c() * 1000)}));
            }
            if (this.mNASInfo.f() == 3 || this.mNASInfo.f() == 1) {
                this.mSivVideoStorage.setChecked(true);
            } else {
                this.mSivVideoStorage.setChecked(false);
            }
            this.mLastCycleTime = this.mNASInfo.e();
            this.mCycleTimeItem.setInfo(Util.b(getResources(), this.mLastCycleTime));
            if (this.mNASInfo.a() != null) {
                this.mNASName.setInfo(this.mNASInfo.a().a());
            }
            this.mLastSyncInterval = this.mNASInfo.d();
            this.mUpdateTimeIntervalItem.setInfo(Util.c(getResources(), this.mLastSyncInterval));
            updateTransferStatus();
        }
    }

    private void updateTransferStatus() {
        if (this.mNASInfo == null) {
            return;
        }
        if (this.mNASInfo.g() != 0) {
            this.transferStatusTv.setText(R.string.smb_transfer_status_error);
            this.statusIconIv.setImageResource(R.drawable.camera_smb_animation_error);
            this.pauseResumeTv.setText(R.string.smb_transfer_op_pause);
            return;
        }
        switch (this.mNASInfo.f()) {
            case 1:
                this.transferStatusTv.setText(R.string.nas_transfer_status_ready);
                this.pauseResumeTv.setText(R.string.smb_transfer_op_pause);
                return;
            case 2:
                this.transferStatusTv.setText(R.string.smb_transfer_status_pause);
                this.statusIconIv.setImageResource(R.drawable.camera_smb_animation_pause);
                this.pauseResumeTv.setText(R.string.smb_transfer_op_resume);
                return;
            case 3:
                this.transferStatusTv.setText(R.string.smb_transfer_status_normal);
                this.pauseResumeTv.setText(R.string.smb_transfer_op_pause);
                this.statusIconIv.setImageResource(R.drawable.camera_anim_transfering);
                ((AnimationDrawable) this.statusIconIv.getDrawable()).start();
                return;
            case 4:
                this.transferStatusTv.setText(R.string.smb_transfer_status_error);
                this.statusIconIv.setImageResource(R.drawable.camera_smb_animation_error);
                this.pauseResumeTv.setText(R.string.smb_transfer_op_pause);
                return;
            default:
                return;
        }
    }

    private void initProgressDialog() {
        this.mXQProgressDialog = new XQProgressDialog(this);
        this.mXQProgressDialog.setMessage(getString(R.string.smb_waiting));
        this.mXQProgressDialog.setCancelable(true);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.clear_uploaded_rl:
                showClearStorageDirDialog();
                return;
            case R.id.delete_storage_rl:
                showDeleteNASDialog();
                return;
            case R.id.modify_setting_rl:
                Intent intent = new Intent(this, NASSettingActivity.class);
                intent.putExtra("data", this.mNASInfo);
                startActivity(intent);
                return;
            case R.id.setting_nas_sync_interval:
                showSyncIntervalDialog();
                return;
            case R.id.setting_smb_time:
                showSetTime();
                return;
            case R.id.title_bar_return:
                finish();
                return;
            default:
                return;
        }
    }

    private void showSetTime() {
        int i;
        this.mCycleTime = this.mLastCycleTime;
        MLAlertDialog.Builder builder = new MLAlertDialog.Builder(this);
        if (this.mLastCycleTime != 86400) {
            if (this.mLastCycleTime == 604800) {
                i = 0;
            } else if (this.mLastCycleTime == 2592000) {
                i = 1;
            } else if (this.mLastCycleTime != 7776000) {
                if (this.mLastCycleTime == 15552000) {
                    i = 3;
                } else if (this.mLastCycleTime == 31104000) {
                    i = 4;
                }
            }
            builder.a((CharSequence[]) new String[]{getResources().getString(R.string.nas_recycle_week), getResources().getString(R.string.nas_recycle_month), getResources().getString(R.string.nas_recycle_3_months), getResources().getString(R.string.nas_recycle_6_months), getResources().getString(R.string.nas_recycle_12_months)}, i, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    int unused = NASInfoActivity.this.type = i + 1;
                    switch (i) {
                        case 0:
                            int unused2 = NASInfoActivity.this.mCycleTime = AppCode.m;
                            return;
                        case 1:
                            int unused3 = NASInfoActivity.this.mCycleTime = AppCode.n;
                            return;
                        case 2:
                            int unused4 = NASInfoActivity.this.mCycleTime = AppCode.o;
                            return;
                        case 3:
                            int unused5 = NASInfoActivity.this.mCycleTime = AppCode.p;
                            return;
                        case 4:
                            int unused6 = NASInfoActivity.this.mCycleTime = AppCode.q;
                            return;
                        default:
                            return;
                    }
                }
            });
            builder.a((int) R.string.nas_setting_retention_time);
            builder.b((int) R.string.cancel, (DialogInterface.OnClickListener) null);
            builder.a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (NASInfoActivity.this.progressDialog == null) {
                        NASInfoActivity.this.progressDialog = new XQProgressDialog(NASInfoActivity.this);
                    }
                    NASInfoActivity.this.progressDialog.show();
                    NASInfoActivity.this.mNASInfo.b(NASInfoActivity.this.mCycleTime);
                    Event.a(Event.bU, "type", (Object) Integer.valueOf(NASInfoActivity.this.type));
                    NASInfoActivity.this.mCameraDevice.l().a(NASInfoActivity.this.mNASInfo, (Callback<Object>) new Callback<Object>() {
                        public void onSuccess(Object obj) {
                            if (!NASInfoActivity.this.isFinishing()) {
                                int unused = NASInfoActivity.this.mLastCycleTime = NASInfoActivity.this.mCycleTime;
                                NASInfoActivity.this.mCycleTimeItem.setInfo(Util.b(NASInfoActivity.this.getResources(), NASInfoActivity.this.mCycleTime));
                                NASInfoActivity.this.progressDialog.dismiss();
                            }
                        }

                        public void onFailure(int i, String str) {
                            if (!NASInfoActivity.this.isFinishing()) {
                                ToastUtil.a((Context) NASInfoActivity.this, (int) R.string.set_failed);
                                NASInfoActivity.this.progressDialog.dismiss();
                            }
                        }
                    });
                    dialogInterface.dismiss();
                }
            });
            builder.d();
        }
        i = 2;
        builder.a((CharSequence[]) new String[]{getResources().getString(R.string.nas_recycle_week), getResources().getString(R.string.nas_recycle_month), getResources().getString(R.string.nas_recycle_3_months), getResources().getString(R.string.nas_recycle_6_months), getResources().getString(R.string.nas_recycle_12_months)}, i, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                int unused = NASInfoActivity.this.type = i + 1;
                switch (i) {
                    case 0:
                        int unused2 = NASInfoActivity.this.mCycleTime = AppCode.m;
                        return;
                    case 1:
                        int unused3 = NASInfoActivity.this.mCycleTime = AppCode.n;
                        return;
                    case 2:
                        int unused4 = NASInfoActivity.this.mCycleTime = AppCode.o;
                        return;
                    case 3:
                        int unused5 = NASInfoActivity.this.mCycleTime = AppCode.p;
                        return;
                    case 4:
                        int unused6 = NASInfoActivity.this.mCycleTime = AppCode.q;
                        return;
                    default:
                        return;
                }
            }
        });
        builder.a((int) R.string.nas_setting_retention_time);
        builder.b((int) R.string.cancel, (DialogInterface.OnClickListener) null);
        builder.a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (NASInfoActivity.this.progressDialog == null) {
                    NASInfoActivity.this.progressDialog = new XQProgressDialog(NASInfoActivity.this);
                }
                NASInfoActivity.this.progressDialog.show();
                NASInfoActivity.this.mNASInfo.b(NASInfoActivity.this.mCycleTime);
                Event.a(Event.bU, "type", (Object) Integer.valueOf(NASInfoActivity.this.type));
                NASInfoActivity.this.mCameraDevice.l().a(NASInfoActivity.this.mNASInfo, (Callback<Object>) new Callback<Object>() {
                    public void onSuccess(Object obj) {
                        if (!NASInfoActivity.this.isFinishing()) {
                            int unused = NASInfoActivity.this.mLastCycleTime = NASInfoActivity.this.mCycleTime;
                            NASInfoActivity.this.mCycleTimeItem.setInfo(Util.b(NASInfoActivity.this.getResources(), NASInfoActivity.this.mCycleTime));
                            NASInfoActivity.this.progressDialog.dismiss();
                        }
                    }

                    public void onFailure(int i, String str) {
                        if (!NASInfoActivity.this.isFinishing()) {
                            ToastUtil.a((Context) NASInfoActivity.this, (int) R.string.set_failed);
                            NASInfoActivity.this.progressDialog.dismiss();
                        }
                    }
                });
                dialogInterface.dismiss();
            }
        });
        builder.d();
    }

    private void showSyncIntervalDialog() {
        int i;
        MLAlertDialog.Builder builder = new MLAlertDialog.Builder(this);
        if (this.mLastSyncInterval == 300) {
            i = 0;
        } else if (this.mLastSyncInterval == 3600) {
            i = 1;
        } else {
            i = this.mLastSyncInterval == 86400 ? 2 : 3;
        }
        builder.a((CharSequence[]) new String[]{getResources().getString(R.string.nas_sync_interval_real_time), getResources().getString(R.string.nas_sync_interval_1_hour), getResources().getString(R.string.nas_sync_interval_1_day)}, i, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case 0:
                        int unused = NASInfoActivity.this.mSyncInterval = 300;
                        return;
                    case 1:
                        int unused2 = NASInfoActivity.this.mSyncInterval = 3600;
                        return;
                    case 2:
                        int unused3 = NASInfoActivity.this.mSyncInterval = 86400;
                        return;
                    case 3:
                        int unused4 = NASInfoActivity.this.mSyncInterval = 300;
                        return;
                    default:
                        return;
                }
            }
        });
        builder.a((int) R.string.smb_setting_nas_sync_interval);
        builder.b((int) R.string.cancel, (DialogInterface.OnClickListener) null);
        builder.a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (NASInfoActivity.this.progressDialog == null) {
                    NASInfoActivity.this.progressDialog = new XQProgressDialog(NASInfoActivity.this);
                }
                NASInfoActivity.this.progressDialog.show();
                NASInfoActivity.this.mNASInfo.a(NASInfoActivity.this.mSyncInterval);
                NASInfoActivity.this.mCameraDevice.l().a(NASInfoActivity.this.mNASInfo, (Callback<Object>) new Callback<Object>() {
                    public void onSuccess(Object obj) {
                        if (!NASInfoActivity.this.isFinishing()) {
                            int unused = NASInfoActivity.this.mLastSyncInterval = NASInfoActivity.this.mSyncInterval;
                            NASInfoActivity.this.mUpdateTimeIntervalItem.setInfo(Util.c(NASInfoActivity.this.getResources(), NASInfoActivity.this.mSyncInterval));
                            NASInfoActivity.this.progressDialog.dismiss();
                        }
                    }

                    public void onFailure(int i, String str) {
                        if (!NASInfoActivity.this.isFinishing()) {
                            ToastUtil.a((Context) NASInfoActivity.this, (int) R.string.set_failed);
                            NASInfoActivity.this.progressDialog.dismiss();
                        }
                    }
                });
                dialogInterface.dismiss();
            }
        });
        builder.d();
    }

    private void showDeleteNASDialog() {
        MLAlertDialog.Builder builder = new MLAlertDialog.Builder(this);
        builder.a((int) R.string.smb_dialog_delete_smb_title).b((int) R.string.smb_dialog_delete_smb_msg).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                NASInfoActivity.this.deleteNAS();
            }
        });
        builder.b().show();
    }

    /* access modifiers changed from: private */
    public void deleteNAS() {
        this.mXQProgressDialog.show();
        this.mCameraDevice.l().c(new Callback<Object>() {
            public void onSuccess(Object obj) {
                if (!NASInfoActivity.this.isFinishing()) {
                    NASInfoActivity.this.mXQProgressDialog.dismiss();
                    ToastUtil.a((Context) NASInfoActivity.this, (int) R.string.action_success);
                    NASInfoActivity.this.finish();
                }
            }

            public void onFailure(int i, String str) {
                if (!NASInfoActivity.this.isFinishing()) {
                    NASInfoActivity.this.mXQProgressDialog.dismiss();
                    ToastUtil.a((Context) NASInfoActivity.this, (int) R.string.action_fail);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void toggleNASStorageStatus() {
        SDKLog.b(TAG, "toggleNASStorageStatus");
        this.mXQProgressDialog.show();
        this.mCameraDevice.l().b(new Callback<NASInfo>() {
            public void onSuccess(NASInfo nASInfo) {
                if (!NASInfoActivity.this.isFinishing()) {
                    NASInfo unused = NASInfoActivity.this.mNASInfo = NASInfoActivity.this.mCameraDevice.l().a();
                    final int f = nASInfo.f();
                    if (f == 2) {
                        f = 3;
                    } else if (f == 3 || f == 1) {
                        f = 2;
                    }
                    nASInfo.c(f);
                    NASInfoActivity.this.mCameraDevice.l().a(nASInfo, (Callback<Object>) new Callback<Object>() {
                        public void onSuccess(Object obj) {
                            if (!NASInfoActivity.this.isFinishing()) {
                                NASInfoActivity.this.mXQProgressDialog.dismiss();
                                NASInfoActivity.this.mNASInfo.c(f);
                                NASInfoActivity.this.updateView();
                            }
                        }

                        public void onFailure(int i, String str) {
                            if (!NASInfoActivity.this.isFinishing()) {
                                NASInfoActivity.this.mXQProgressDialog.dismiss();
                                ToastUtil.a((Context) NASInfoActivity.this, (int) R.string.action_fail);
                            }
                        }
                    });
                }
            }

            public void onFailure(int i, String str) {
                if (!NASInfoActivity.this.isFinishing()) {
                    NASInfoActivity.this.mXQProgressDialog.dismiss();
                    ToastUtil.a((Context) NASInfoActivity.this, (int) R.string.action_fail);
                }
            }
        });
    }

    private void showClearStorageDirDialog() {
        MLAlertDialog.Builder builder = new MLAlertDialog.Builder(this);
        builder.a((int) R.string.smb_dialog_clear_dir_title).b((int) R.string.smb_dialog_clear_dir_msg).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                NASInfoActivity.this.clearNASVideo();
            }
        });
        builder.b().show();
    }

    /* access modifiers changed from: private */
    public void clearNASVideo() {
        this.mXQProgressDialog.show();
        this.mCameraDevice.l().d(new Callback<Object>() {
            public void onSuccess(Object obj) {
                if (!NASInfoActivity.this.isFinishing()) {
                    NASInfoActivity.this.mXQProgressDialog.dismiss();
                    ToastUtil.a((Context) NASInfoActivity.this, (int) R.string.action_success);
                }
            }

            public void onFailure(int i, String str) {
                if (!NASInfoActivity.this.isFinishing()) {
                    NASInfoActivity.this.mXQProgressDialog.dismiss();
                    ToastUtil.a((Context) NASInfoActivity.this, (int) R.string.action_fail);
                }
            }
        });
    }

    public void finish() {
        super.finish();
        if (this.mXQProgressDialog != null && this.mXQProgressDialog.isShowing()) {
            this.mXQProgressDialog.dismiss();
        }
    }
}
