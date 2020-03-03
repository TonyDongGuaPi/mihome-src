package com.xiaomi.smarthome.camera.activity.setting;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.Utils.FileUtil;
import com.mijia.camera.Utils.Util;
import com.mijia.debug.SDKLog;
import com.mijia.debug.Tag;
import com.mijia.model.property.CameraPropertyBase;
import com.mijia.model.sdcard.SDCardInfo;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.activity.CameraBaseActivity;
import com.xiaomi.smarthome.camera.view.widget.SettingsItemView;
import com.xiaomi.smarthome.camera.view.widget.XmLoadingDialog;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import java.util.ArrayList;
import java.util.List;

public class SDCardStatusActivityNew extends CameraBaseActivity {
    /* access modifiers changed from: private */
    public int CHECK_DURATION = 8000;
    /* access modifiers changed from: private */
    public int SD_FORMAT_CHECK = 2001;
    private boolean isResume = false;
    /* access modifiers changed from: private */
    public boolean isStopRecord;
    /* access modifiers changed from: private */
    public CameraPropertyBase mCameraProperties;
    MLAlertDialog mFormatErrorDialog;
    /* access modifiers changed from: private */
    public SettingsItemView mFormatSdcard;
    private TextView mLeftSpace;
    private TextView mLeftSpaceNum;
    /* access modifiers changed from: private */
    public View mLoadingView;
    private SettingsItemView mPushOutSdcard;
    /* access modifiers changed from: private */
    public String mRecordStatus;
    private SettingsItemView mRecordingMode;
    private TextView mSdcardStatus;
    private ImageView mSdcardStatusBg;
    private TextView mSdcardStatusHint;
    /* access modifiers changed from: private */
    public int mStatus = -1;
    private SettingsItemView mStorageSwitch;
    private TextView mTitleView;
    private TextView mTotalSpace;
    private View mbackView;
    MLAlertDialog.Builder mlAlertDialog;
    AlertDialog progressDialog;
    /* access modifiers changed from: private */
    public int selectedModeIndex;

    public void doCreate(Bundle bundle) {
        super.doCreate(bundle);
        setContentView(R.layout.camera_activity_sdcard_status_new);
        initView();
        initData();
    }

    private void initData() {
        this.mCameraProperties = this.mCameraDevice.f();
        refreshUI();
        this.mCameraProperties.a(new String[]{CameraPropertyBase.k, CameraPropertyBase.h}, (Callback<Void>) new Callback<Void>() {
            public void onSuccess(Void voidR) {
                if (!SDCardStatusActivityNew.this.isFinishing()) {
                    SDCardStatusActivityNew.this.refreshUI();
                }
            }

            public void onFailure(int i, String str) {
                if (!SDCardStatusActivityNew.this.isFinishing()) {
                    SDCardStatusActivityNew.this.refreshUI();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void refreshUI() {
        String d = this.mCameraProperties.d();
        if ("stop".equalsIgnoreCase(d)) {
            this.isStopRecord = true;
        } else {
            this.isStopRecord = false;
        }
        SDKLog.e(Tag.d, "sdcarad status display status:" + Util.a(getResources(), this.mStatus, this.isStopRecord));
        updateSdcardStatus(Util.a(getResources(), this.mStatus, this.isStopRecord));
        char c = 65535;
        int hashCode = d.hashCode();
        if (hashCode != 109935) {
            if (hashCode == 3540994 && d.equals("stop")) {
                c = 0;
            }
        } else if (d.equals("off")) {
            c = 1;
        }
        switch (c) {
            case 0:
                this.mRecordingMode.setVisibility(8);
                this.mStorageSwitch.setChecked(false);
                return;
            case 1:
                this.mRecordingMode.setVisibility(0);
                this.mRecordingMode.setInfo(getString(R.string.setting_record_model_always));
                this.mStorageSwitch.setChecked(true);
                return;
            default:
                this.mRecordingMode.setVisibility(0);
                this.mRecordingMode.setInfo(getString(R.string.setting_record_model_move));
                this.mStorageSwitch.setChecked(true);
                return;
        }
    }

    private void initView() {
        this.mSdcardStatus = (TextView) findViewById(R.id.tvSdcardStatus);
        this.mSdcardStatusBg = (ImageView) findViewById(R.id.ivSdcardStatusBg);
        this.mLeftSpace = (TextView) findViewById(R.id.tvLeftSpace);
        this.mLeftSpaceNum = (TextView) findViewById(R.id.tvLeftSpaceNum);
        this.mTotalSpace = (TextView) findViewById(R.id.tvTotalSpace);
        this.mLoadingView = findViewById(R.id.loading_layout);
        this.mStorageSwitch = (SettingsItemView) findViewById(R.id.settings_storage_switch);
        this.mRecordingMode = (SettingsItemView) findViewById(R.id.settings_record_mode);
        this.mFormatSdcard = (SettingsItemView) findViewById(R.id.settings_storage_format);
        this.mFormatSdcard.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                SDCardStatusActivityNew.this.doFormat();
            }
        });
        this.mPushOutSdcard = (SettingsItemView) findViewById(R.id.settings_storage_out);
        this.mPushOutSdcard.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                SDCardStatusActivityNew.this.doPushOut();
            }
        });
        this.mbackView = findViewById(R.id.title_bar_return);
        this.mTitleView = (TextView) findViewById(R.id.title_bar_title);
        this.mTitleView.setText(R.string.more_store_setting);
        this.mbackView.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                SDCardStatusActivityNew.this.finish();
            }
        });
        this.mSdcardStatusHint = (TextView) findViewById(R.id.tvSdcardStatusHint);
        this.mSdcardStatusHint.setVisibility(8);
        findViewById(R.id.storage_status_container).setVisibility(8);
        this.mStorageSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    boolean unused = SDCardStatusActivityNew.this.isStopRecord = false;
                    if (!"off".equalsIgnoreCase(SDCardStatusActivityNew.this.mCameraProperties.d())) {
                        SDCardStatusActivityNew.this.mCameraProperties.a(CameraPropertyBase.h, (Object) "off", (Callback<Void>) new Callback<Void>() {
                            public void onSuccess(Void voidR) {
                                if (!SDCardStatusActivityNew.this.isFinishing()) {
                                    Toast.makeText(SDCardStatusActivityNew.this.activity(), R.string.settings_set_success, 0).show();
                                    SDCardStatusActivityNew.this.refreshUI();
                                }
                            }

                            public void onFailure(int i, String str) {
                                if (!SDCardStatusActivityNew.this.isFinishing()) {
                                    Toast.makeText(SDCardStatusActivityNew.this.activity(), R.string.set_failed, 0).show();
                                }
                            }
                        });
                    }
                } else if (!"stop".equalsIgnoreCase(SDCardStatusActivityNew.this.mCameraProperties.d())) {
                    SDCardStatusActivityNew.this.mCameraProperties.a(CameraPropertyBase.h, (Object) "stop", (Callback<Void>) new Callback<Void>() {
                        public void onSuccess(Void voidR) {
                            if (!SDCardStatusActivityNew.this.isFinishing()) {
                                Toast.makeText(SDCardStatusActivityNew.this.activity(), R.string.settings_set_success, 0).show();
                                SDCardStatusActivityNew.this.refreshUI();
                            }
                        }

                        public void onFailure(int i, String str) {
                            if (!SDCardStatusActivityNew.this.isFinishing()) {
                                Toast.makeText(SDCardStatusActivityNew.this.activity(), R.string.set_failed, 0).show();
                            }
                        }
                    });
                }
            }
        });
        this.mRecordingMode.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SDCardStatusActivityNew.this.changeRecordMode();
            }
        });
    }

    /* access modifiers changed from: private */
    public void changeRecordMode() {
        RecordModeAdapter recordModeAdapter = new RecordModeAdapter();
        this.mRecordStatus = this.mCameraProperties.d();
        if (this.mRecordStatus != null) {
            if ("on".equalsIgnoreCase(this.mRecordStatus)) {
                recordModeAdapter.setSelected(0);
                this.selectedModeIndex = 0;
            } else {
                recordModeAdapter.setSelected(1);
                this.selectedModeIndex = 1;
            }
            new MLAlertDialog.Builder(this).a((CharSequence) getString(R.string.setting_record_model)).a((ListAdapter) recordModeAdapter, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (i != SDCardStatusActivityNew.this.selectedModeIndex) {
                        if (i == 0) {
                            String unused = SDCardStatusActivityNew.this.mRecordStatus = "on";
                        } else {
                            String unused2 = SDCardStatusActivityNew.this.mRecordStatus = "off";
                        }
                        if (!SDCardStatusActivityNew.this.mRecordStatus.equals(SDCardStatusActivityNew.this.mCameraProperties.d())) {
                            SDCardStatusActivityNew.this.mCameraProperties.a(CameraPropertyBase.h, (Object) SDCardStatusActivityNew.this.mRecordStatus, (Callback<Void>) new Callback<Void>() {
                                public void onSuccess(Void voidR) {
                                    if (!SDCardStatusActivityNew.this.isFinishing()) {
                                        Toast.makeText(SDCardStatusActivityNew.this.activity(), R.string.settings_set_success, 0).show();
                                        SDCardStatusActivityNew.this.refreshUI();
                                    }
                                }

                                public void onFailure(int i, String str) {
                                    if (!SDCardStatusActivityNew.this.isFinishing()) {
                                        Toast.makeText(SDCardStatusActivityNew.this.activity(), R.string.set_failed, 0).show();
                                    }
                                }
                            });
                        }
                    }
                }
            }).b().show();
        }
    }

    public void onResume() {
        super.onResume();
        if (this.isResume) {
            loadData(false);
        } else {
            loadData(true);
        }
        this.isResume = true;
    }

    public void handleMessage(Message message) {
        if (message.what == this.SD_FORMAT_CHECK) {
            checkFormat();
        }
    }

    /* access modifiers changed from: private */
    public void loadData(boolean z) {
        if (z) {
            this.mLoadingView.setVisibility(0);
        }
        AnonymousClass5 r0 = new Callback<SDCardInfo>() {
            public void onSuccess(SDCardInfo sDCardInfo) {
                if (!SDCardStatusActivityNew.this.isFinishing()) {
                    if (SDCardStatusActivityNew.this.progressDialog != null && SDCardStatusActivityNew.this.progressDialog.isShowing()) {
                        SDCardStatusActivityNew.this.progressDialog.dismiss();
                    }
                    SDCardStatusActivityNew.this.mLoadingView.setVisibility(8);
                    SDCardStatusActivityNew.this.findViewById(R.id.storage_status_container).setVisibility(0);
                    SDKLog.e(Tag.d, "sdcarad status success status:" + sDCardInfo.e);
                    SDCardStatusActivityNew.this.parseInfo(sDCardInfo);
                }
            }

            public void onFailure(int i, String str) {
                if (!SDCardStatusActivityNew.this.isFinishing()) {
                    SDKLog.e(Tag.d, "sdcarad status fail " + i + " : " + str);
                    SDCardStatusActivityNew.this.initAlertDlg();
                    SDCardStatusActivityNew.this.mLoadingView.setVisibility(8);
                    SDCardStatusActivityNew.this.getString(R.string.sdcard_error);
                    SDCardStatusActivityNew.this.parseInfo((SDCardInfo) null);
                    if (i == -2003) {
                        ToastUtil.a((Context) SDCardStatusActivityNew.this.activity(), (CharSequence) SDCardStatusActivityNew.this.getString(R.string.sdcard_no));
                        int unused = SDCardStatusActivityNew.this.mStatus = 1;
                        SDCardStatusActivityNew.this.refreshUI();
                    } else if (i == -2000) {
                        SDCardStatusActivityNew.this.mHandler.removeMessages(SDCardStatusActivityNew.this.SD_FORMAT_CHECK);
                        SDCardStatusActivityNew.this.mHandler.sendEmptyMessageDelayed(SDCardStatusActivityNew.this.SD_FORMAT_CHECK, (long) SDCardStatusActivityNew.this.CHECK_DURATION);
                        ToastUtil.a((Context) SDCardStatusActivityNew.this.activity(), (CharSequence) SDCardStatusActivityNew.this.getString(R.string.sdcard_formating));
                        int unused2 = SDCardStatusActivityNew.this.mStatus = 4;
                        SDCardStatusActivityNew.this.refreshUI();
                    } else if (i == -2002) {
                        int unused3 = SDCardStatusActivityNew.this.mStatus = 3;
                        SDCardStatusActivityNew.this.refreshUI();
                    } else if (i == -2005) {
                        SDCardStatusActivityNew.this.mlAlertDialog.a((int) R.string.sdcard_tip);
                        SDCardStatusActivityNew.this.mlAlertDialog.b((int) R.string.sdcard_error_out);
                        SDCardStatusActivityNew.this.mlAlertDialog.b((int) R.string.know_button, (DialogInterface.OnClickListener) null);
                        SDCardStatusActivityNew.this.mlAlertDialog.d();
                        int unused4 = SDCardStatusActivityNew.this.mStatus = 5;
                        SDCardStatusActivityNew.this.refreshUI();
                    } else {
                        int unused5 = SDCardStatusActivityNew.this.mStatus = 3;
                        SDCardStatusActivityNew.this.refreshUI();
                        SDCardStatusActivityNew.this.mlAlertDialog.a((int) R.string.sdcard_tip);
                        SDCardStatusActivityNew.this.mlAlertDialog.b((int) R.string.sdcard_error_tip);
                        SDCardStatusActivityNew.this.mlAlertDialog.a((int) R.string.common_retry, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                SDCardStatusActivityNew.this.loadData(true);
                            }
                        });
                        SDCardStatusActivityNew.this.mlAlertDialog.b((int) R.string.cancel, (DialogInterface.OnClickListener) null);
                        SDCardStatusActivityNew.this.mlAlertDialog.d();
                    }
                }
            }
        };
        if (this.mCameraDevice.n()) {
            this.mCameraDevice.c().b((Callback<SDCardInfo>) r0, z);
        } else {
            this.mCameraDevice.d().b((Callback<SDCardInfo>) r0, z);
        }
    }

    /* access modifiers changed from: private */
    public void parseInfo(SDCardInfo sDCardInfo) {
        if (sDCardInfo == null) {
            this.mLeftSpace.setText("");
            this.mTotalSpace.setText("");
            this.mLeftSpaceNum.setText("");
            return;
        }
        if (!(sDCardInfo.e == -1 || sDCardInfo.e == this.mStatus)) {
            this.mStatus = sDCardInfo.e;
            updateSdcardStatus(Util.a(getResources(), this.mStatus, this.isStopRecord));
            this.mCameraDevice.f().a(CameraPropertyBase.k, (Object) Integer.valueOf(this.mStatus));
        }
        if (sDCardInfo.b <= 0 || sDCardInfo.f8074a <= 0) {
            this.mLeftSpace.setText("");
            this.mTotalSpace.setText("");
            this.mLeftSpaceNum.setText("");
            return;
        }
        this.mLeftSpace.setText(activity().getString(R.string.camera_storage_left_space));
        this.mLeftSpaceNum.setText(FileUtil.a(sDCardInfo.b));
        this.mTotalSpace.setText(activity().getString(R.string.camera_storage_total_space, new Object[]{FileUtil.a(sDCardInfo.f8074a)}));
    }

    private void updateSdcardStatus(int i) {
        findViewById(R.id.storage_status_container).setVisibility(0);
        this.mSdcardStatusHint.setVisibility(4);
        if (i == 1) {
            this.mSdcardStatus.setText(getString(R.string.camera_storage_normal));
            this.mSdcardStatusBg.setImageResource(R.drawable.camera_sdcard_status_normal);
        } else if (i == 2) {
            this.mSdcardStatus.setText(getString(R.string.camera_storage_unnormal));
            this.mSdcardStatusBg.setImageResource(R.drawable.camera_sdcard_status_unnormal);
            this.mSdcardStatusHint.setVisibility(0);
        } else if (i == 3) {
            this.mSdcardStatus.setText(getString(R.string.camera_storage_pause));
            this.mSdcardStatusBg.setImageResource(R.drawable.camera_sdcard_status_stopped);
        }
    }

    private void checkFormat() {
        this.mHandler.removeMessages(this.SD_FORMAT_CHECK);
        AnonymousClass6 r0 = new Callback<SDCardInfo>() {
            public void onSuccess(SDCardInfo sDCardInfo) {
                if (!SDCardStatusActivityNew.this.isFinishing()) {
                    SDCardStatusActivityNew.this.disProgressDlg();
                    SDCardStatusActivityNew.this.parseInfo(sDCardInfo);
                    ToastUtil.a((Context) SDCardStatusActivityNew.this.activity(), (int) R.string.sdcard_format_success);
                }
            }

            public void onFailure(int i, String str) {
                if (!SDCardStatusActivityNew.this.isFinishing()) {
                    if (i == -2000) {
                        SDCardStatusActivityNew.this.mHandler.sendEmptyMessageDelayed(SDCardStatusActivityNew.this.SD_FORMAT_CHECK, (long) SDCardStatusActivityNew.this.CHECK_DURATION);
                        return;
                    }
                    SDCardStatusActivityNew.this.disProgressDlg();
                    SDKLog.e(Tag.d, "check format error " + i);
                    SDCardStatusActivityNew.this.mFormatSdcard.setEnabled(true);
                    SDCardStatusActivityNew.this.mFormatSdcard.setTitleColor(Color.parseColor("#e94f4f"));
                    SDCardStatusActivityNew.this.showFormatErrorDialog();
                }
            }
        };
        if (this.mCameraDevice.n()) {
            this.mCameraDevice.d().b((Callback<SDCardInfo>) r0, true);
        } else {
            this.mCameraDevice.c().b((Callback<SDCardInfo>) r0, true);
        }
    }

    /* access modifiers changed from: private */
    public void doFormat() {
        initAlertDlg();
        this.mlAlertDialog.a((int) R.string.sdcard_format);
        this.mlAlertDialog.b((int) R.string.sdcard_format_tip);
        this.mlAlertDialog.a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                SDCardStatusActivityNew.this.initProgressDlg(SDCardStatusActivityNew.this.getString(R.string.sdcard_formating));
                if (SDCardStatusActivityNew.this.mCameraDevice.n()) {
                    SDCardStatusActivityNew.this.mCameraDevice.d().b((Callback<Object>) new Callback<Object>() {
                        public void onSuccess(Object obj) {
                            if (!SDCardStatusActivityNew.this.isFinishing()) {
                                SDCardStatusActivityNew.this.mHandler.removeMessages(SDCardStatusActivityNew.this.SD_FORMAT_CHECK);
                                SDCardStatusActivityNew.this.mHandler.sendEmptyMessageDelayed(SDCardStatusActivityNew.this.SD_FORMAT_CHECK, (long) SDCardStatusActivityNew.this.CHECK_DURATION);
                                SDCardStatusActivityNew.this.mCameraDevice.d().h();
                            }
                        }

                        public void onFailure(int i, String str) {
                            if (!SDCardStatusActivityNew.this.isFinishing()) {
                                if (i == -2000) {
                                    SDCardStatusActivityNew.this.mHandler.removeMessages(SDCardStatusActivityNew.this.SD_FORMAT_CHECK);
                                    SDCardStatusActivityNew.this.mHandler.sendEmptyMessageDelayed(SDCardStatusActivityNew.this.SD_FORMAT_CHECK, (long) SDCardStatusActivityNew.this.CHECK_DURATION);
                                    ToastUtil.a((Context) SDCardStatusActivityNew.this.activity(), (int) R.string.sdcard_formating);
                                    return;
                                }
                                if (i == -2003) {
                                    ToastUtil.a((Context) SDCardStatusActivityNew.this.activity(), (int) R.string.sdcard_no);
                                } else {
                                    SDKLog.e(Tag.d, "format error " + i + " info ");
                                    SDCardStatusActivityNew.this.mFormatSdcard.setEnabled(true);
                                    SDCardStatusActivityNew.this.mFormatSdcard.setTitleColor(Color.parseColor("#e94f4f"));
                                    SDCardStatusActivityNew.this.showFormatErrorDialog();
                                }
                                SDCardStatusActivityNew.this.disProgressDlg();
                            }
                        }
                    });
                } else {
                    SDCardStatusActivityNew.this.mCameraDevice.c().b((Callback<Object>) new Callback<Object>() {
                        public void onSuccess(Object obj) {
                            if (!SDCardStatusActivityNew.this.isFinishing()) {
                                SDCardStatusActivityNew.this.mHandler.removeMessages(SDCardStatusActivityNew.this.SD_FORMAT_CHECK);
                                SDCardStatusActivityNew.this.mHandler.sendEmptyMessageDelayed(SDCardStatusActivityNew.this.SD_FORMAT_CHECK, (long) SDCardStatusActivityNew.this.CHECK_DURATION);
                            }
                        }

                        public void onFailure(int i, String str) {
                            if (!SDCardStatusActivityNew.this.isFinishing()) {
                                if (i == -2000) {
                                    SDCardStatusActivityNew.this.mHandler.removeMessages(SDCardStatusActivityNew.this.SD_FORMAT_CHECK);
                                    SDCardStatusActivityNew.this.mHandler.sendEmptyMessageDelayed(SDCardStatusActivityNew.this.SD_FORMAT_CHECK, (long) SDCardStatusActivityNew.this.CHECK_DURATION);
                                    ToastUtil.a((Context) SDCardStatusActivityNew.this.activity(), (int) R.string.sdcard_formating);
                                    return;
                                }
                                if (i == -2003) {
                                    ToastUtil.a((Context) SDCardStatusActivityNew.this.activity(), (int) R.string.sdcard_no);
                                } else {
                                    SDKLog.e(Tag.d, "format error " + i + " info ");
                                    SDCardStatusActivityNew.this.mFormatSdcard.setEnabled(true);
                                    SDCardStatusActivityNew.this.mFormatSdcard.setTitleColor(Color.parseColor("#e94f4f"));
                                    SDCardStatusActivityNew.this.showFormatErrorDialog();
                                }
                                SDCardStatusActivityNew.this.disProgressDlg();
                            }
                        }
                    });
                }
            }
        });
        this.mlAlertDialog.b((int) R.string.cancel, (DialogInterface.OnClickListener) null);
        this.mlAlertDialog.d();
    }

    /* access modifiers changed from: private */
    public void showFormatErrorDialog() {
        if (this.mFormatErrorDialog == null) {
            this.mFormatErrorDialog = new MLAlertDialog.Builder(this).b((int) R.string.camer_sdcard_format_error).a((int) R.string.confirm, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    SDCardStatusActivityNew.this.mFormatErrorDialog.dismiss();
                }
            }).b();
        }
        if (!this.mFormatErrorDialog.isShowing()) {
            this.mFormatErrorDialog.show();
        }
    }

    public void finish() {
        setResult(-1);
        super.finish();
    }

    /* access modifiers changed from: private */
    public void doPushOut() {
        if (this.mStatus == 5) {
            ToastUtil.a((Context) activity(), (int) R.string.sdcard_out_already);
            return;
        }
        initAlertDlg();
        this.mlAlertDialog.a((int) R.string.sdcard_out);
        this.mlAlertDialog.b((int) R.string.sdcard_out_tips);
        this.mlAlertDialog.b((int) R.string.cancel, (DialogInterface.OnClickListener) null);
        this.mlAlertDialog.a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                SDCardStatusActivityNew.this.initProgressDlg(SDCardStatusActivityNew.this.getString(R.string.sdcard_out));
                SDCardStatusActivityNew.this.progressDialog.show();
                if (SDCardStatusActivityNew.this.mCameraDevice.n()) {
                    SDCardStatusActivityNew.this.mCameraDevice.d().c((Callback<Object>) new Callback<Object>() {
                        public void onSuccess(Object obj) {
                            if (!SDCardStatusActivityNew.this.isFinishing()) {
                                SDCardStatusActivityNew.this.disProgressDlg();
                                ToastUtil.a((Context) SDCardStatusActivityNew.this.activity(), (int) R.string.sdcard_out_success);
                                SDCardStatusActivityNew.this.mCameraDevice.f().a(CameraPropertyBase.k, (Object) 5);
                                SDCardStatusActivityNew.this.mFormatSdcard.setEnabled(false);
                                SDCardStatusActivityNew.this.mFormatSdcard.setTitleColor(com.libra.Color.c);
                                SDCardStatusActivityNew.this.finish();
                            }
                        }

                        public void onFailure(int i, String str) {
                            if (!SDCardStatusActivityNew.this.isFinishing()) {
                                ToastUtil.a((Context) SDCardStatusActivityNew.this.activity(), (int) R.string.sdcard_out_fail);
                                SDCardStatusActivityNew.this.disProgressDlg();
                            }
                        }
                    });
                } else {
                    SDCardStatusActivityNew.this.mCameraDevice.c().c((Callback<Object>) new Callback<Object>() {
                        public void onSuccess(Object obj) {
                            if (!SDCardStatusActivityNew.this.isFinishing()) {
                                SDCardStatusActivityNew.this.disProgressDlg();
                                ToastUtil.a((Context) SDCardStatusActivityNew.this.activity(), (int) R.string.sdcard_out_success);
                                SDCardStatusActivityNew.this.mCameraDevice.f().a(CameraPropertyBase.k, (Object) 5);
                                SDCardStatusActivityNew.this.mFormatSdcard.setEnabled(false);
                                SDCardStatusActivityNew.this.mFormatSdcard.setTitleColor(com.libra.Color.c);
                                SDCardStatusActivityNew.this.finish();
                            }
                        }

                        public void onFailure(int i, String str) {
                            if (!SDCardStatusActivityNew.this.isFinishing()) {
                                ToastUtil.a((Context) SDCardStatusActivityNew.this.activity(), (int) R.string.sdcard_out_fail);
                                SDCardStatusActivityNew.this.disProgressDlg();
                            }
                        }
                    });
                }
            }
        });
        this.mlAlertDialog.d();
    }

    /* access modifiers changed from: private */
    public void initProgressDlg(String str) {
        if (this.progressDialog != null && this.progressDialog.isShowing()) {
            this.progressDialog.dismiss();
        }
        this.progressDialog = XmLoadingDialog.show(activity(), str);
    }

    /* access modifiers changed from: private */
    public void initAlertDlg() {
        if (this.mlAlertDialog == null) {
            this.mlAlertDialog = new MLAlertDialog.Builder(this);
        }
    }

    /* access modifiers changed from: private */
    public void disProgressDlg() {
        if (this.progressDialog != null && this.progressDialog.isShowing()) {
            this.progressDialog.dismiss();
        }
    }

    private class RecordModeAdapter extends BaseAdapter {
        List<RecordModeEntity> datas;
        int selectedIndex;

        public long getItemId(int i) {
            return (long) i;
        }

        public RecordModeAdapter() {
            this.datas = null;
            this.selectedIndex = 0;
            this.datas = new ArrayList();
            this.datas.add(new RecordModeEntity(SDCardStatusActivityNew.this.getString(R.string.setting_record_model_move), SDCardStatusActivityNew.this.getString(R.string.setting_record_model_move_title)));
            this.datas.add(new RecordModeEntity(SDCardStatusActivityNew.this.getString(R.string.setting_record_model_always), SDCardStatusActivityNew.this.getString(R.string.setting_record_model_always_title)));
        }

        public void setSelected(int i) {
            if (i < this.datas.size()) {
                this.selectedIndex = i;
                notifyDataSetChanged();
            }
        }

        public int getCount() {
            return this.datas.size();
        }

        public RecordModeEntity getItem(int i) {
            return this.datas.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            RecordModeViewHolder recordModeViewHolder;
            int i2 = 0;
            if (view == null) {
                view = LayoutInflater.from(SDCardStatusActivityNew.this.getContext()).inflate(R.layout.camera_file_setting_record_mode_item, viewGroup, false);
                recordModeViewHolder = new RecordModeViewHolder();
                recordModeViewHolder.mRecordModeSelectedIcon = (ImageView) view.findViewById(R.id.ivSelectIcon);
                recordModeViewHolder.mRecordModeTitle = (TextView) view.findViewById(R.id.tvRecordModeTitle);
                recordModeViewHolder.mRecordModeDesc = (TextView) view.findViewById(R.id.tvRecordModeDesc);
                view.setTag(recordModeViewHolder);
            } else {
                recordModeViewHolder = (RecordModeViewHolder) view.getTag();
            }
            recordModeViewHolder.mRecordModeTitle.setText(getItem(i).title);
            recordModeViewHolder.mRecordModeDesc.setText(getItem(i).desc);
            ImageView imageView = recordModeViewHolder.mRecordModeSelectedIcon;
            if (this.selectedIndex != i) {
                i2 = 4;
            }
            imageView.setVisibility(i2);
            recordModeViewHolder.mRecordModeTitle.setTextColor(Color.parseColor(this.selectedIndex == i ? "#FF32BAC0" : "#FF000000"));
            return view;
        }

        class RecordModeEntity {
            String desc;
            String title;

            public RecordModeEntity(String str, String str2) {
                this.title = str;
                this.desc = str2;
            }
        }

        class RecordModeViewHolder {
            TextView mRecordModeDesc;
            ImageView mRecordModeSelectedIcon;
            TextView mRecordModeTitle;

            RecordModeViewHolder() {
            }
        }
    }
}
