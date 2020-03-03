package com.xiaomi.smarthome.camera.v4.activity.setting;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.Utils.FileUtil;
import com.libra.Color;
import com.mijia.app.Event;
import com.mijia.debug.SDKLog;
import com.mijia.debug.Tag;
import com.mijia.model.property.CameraPropertyBase;
import com.mijia.model.sdcard.SDCardInfo;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.activity.CameraBaseActivity;
import com.xiaomi.smarthome.camera.v4.utils.Util;
import com.xiaomi.smarthome.camera.view.widget.SettingsItemView;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;

public class SDCardStatusActivity extends CameraBaseActivity implements View.OnClickListener {
    /* access modifiers changed from: private */
    public int CHECK_DURATION = 8000;
    /* access modifiers changed from: private */
    public int SD_FORMAT_CHECK = 2001;
    /* access modifiers changed from: private */
    public boolean isFormating = false;
    private boolean isResume = false;
    TextView mFreeSize;
    View mInfoView;
    View mLoadView;
    TextView mOtherSize;
    /* access modifiers changed from: private */
    public int mStatus = 0;
    View mStatusLayout;
    TextView mStatusView;
    TextView mTotalView;
    TextView mVideoSize;
    MLAlertDialog.Builder mlAlertDialog;
    XQProgressDialog progressDialog;
    View sdcardFree;
    View sdcardOther;
    View sdcardVideo;
    /* access modifiers changed from: private */
    public SettingsItemView sdcard_format;

    public void doCreate(Bundle bundle) {
        super.doCreate(bundle);
        setContentView(R.layout.activity_sdcard_status);
        initView();
    }

    private void initView() {
        this.sdcardVideo = findViewById(R.id.sdcard_video);
        this.sdcardOther = findViewById(R.id.sdcard_other);
        this.sdcardFree = findViewById(R.id.sdcard_free);
        this.mOtherSize = (TextView) findViewById(R.id.sdcard_other_size);
        this.mFreeSize = (TextView) findViewById(R.id.sdcard_free_size);
        this.mVideoSize = (TextView) findViewById(R.id.sdcard_video_size);
        this.mTotalView = (TextView) findViewById(R.id.sdcard_total);
        this.mStatusView = (TextView) findViewById(R.id.sdcard_status);
        this.mInfoView = findViewById(R.id.sdcard_info);
        this.mLoadView = findViewById(R.id.loading_layout);
        this.mStatusLayout = findViewById(R.id.sdcard_status_layout);
        ((AnimationDrawable) ((ImageView) findViewById(R.id.loading_img)).getDrawable()).start();
        this.sdcard_format = (SettingsItemView) findViewById(R.id.sdcard_format);
        this.sdcard_format.setOnClickListener(this);
        findViewById(R.id.sdcard_out).setOnClickListener(this);
        findViewById(R.id.title_bar_return).setOnClickListener(this);
        findViewById(R.id.title_bar_more).setVisibility(8);
        ((TextView) findViewById(R.id.title_bar_title)).setText(R.string.setttings_sdcard_title);
        this.mStatus = this.mCameraDevice.f().a(CameraPropertyBase.k);
        this.mStatusView.setText(getString(R.string.sdcard_status, new Object[]{Util.getSdCardStatus(getResources(), this.mStatus)}));
        if (this.mStatus == 5 || this.mStatus == 1 || this.mStatus == 4) {
            this.sdcard_format.setEnabled(false);
            this.sdcard_format.setTitleColor(Color.c);
        } else {
            this.sdcard_format.setEnabled(true);
            this.sdcard_format.setTitleColor(android.graphics.Color.parseColor("#e94f4f"));
        }
        this.mInfoView.setVisibility(8);
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
            this.mLoadView.setVisibility(0);
        }
        this.mCameraDevice.c().b((Callback<SDCardInfo>) new Callback<SDCardInfo>() {
            public void onSuccess(SDCardInfo sDCardInfo) {
                SDCardStatusActivity.this.mStatusLayout.setVisibility(0);
                if (!SDCardStatusActivity.this.isFinishing()) {
                    SDCardStatusActivity.this.parseInfo(sDCardInfo);
                    if (sDCardInfo.b < 100000) {
                        SDCardStatusActivity.this.initAlertDlg();
                        SDCardStatusActivity.this.mlAlertDialog.a((int) R.string.sdcard_title);
                        SDCardStatusActivity.this.mlAlertDialog.b((int) R.string.sdcard_error_full);
                        SDCardStatusActivity.this.mlAlertDialog.b((int) R.string.sdcard_tip_cancel, (DialogInterface.OnClickListener) null);
                        SDCardStatusActivity.this.mlAlertDialog.a((int) R.string.sdcard_tip_ok, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                SDCardStatusActivity.this.doFormat();
                            }
                        });
                        SDCardStatusActivity.this.mlAlertDialog.d();
                    }
                    SDCardStatusActivity.this.mInfoView.setVisibility(0);
                    SDCardStatusActivity.this.mLoadView.setVisibility(8);
                }
            }

            public void onFailure(int i, String str) {
                if (!SDCardStatusActivity.this.isFinishing()) {
                    SDKLog.e(Tag.d, "sdcarad status fail " + i + " : " + str);
                    SDCardStatusActivity.this.initAlertDlg();
                    SDCardStatusActivity.this.mStatusLayout.setVisibility(8);
                    SDCardStatusActivity.this.mInfoView.setVisibility(0);
                    SDCardStatusActivity.this.mLoadView.setVisibility(8);
                    String string = SDCardStatusActivity.this.getString(R.string.sdcard_error);
                    if (i == -2003) {
                        String string2 = SDCardStatusActivity.this.getString(R.string.sdcard_no);
                        SDCardStatusActivity.this.mStatusView.setText(SDCardStatusActivity.this.getString(R.string.sdcard_status, new Object[]{string2}));
                        ToastUtil.a((Context) SDCardStatusActivity.this, (CharSequence) string2);
                    } else if (i == -2000) {
                        SDCardStatusActivity.this.mHandler.removeMessages(SDCardStatusActivity.this.SD_FORMAT_CHECK);
                        SDCardStatusActivity.this.mHandler.sendEmptyMessageDelayed(SDCardStatusActivity.this.SD_FORMAT_CHECK, (long) SDCardStatusActivity.this.CHECK_DURATION);
                        String string3 = SDCardStatusActivity.this.getString(R.string.sdcard_formating);
                        SDCardStatusActivity.this.mStatusView.setText(SDCardStatusActivity.this.getString(R.string.sdcard_status, new Object[]{string3}));
                        ToastUtil.a((Context) SDCardStatusActivity.this, (CharSequence) string3);
                    } else if (i == -2002) {
                        SDCardStatusActivity.this.mlAlertDialog.a((int) R.string.sdcard_tip);
                        SDCardStatusActivity.this.mlAlertDialog.b((int) R.string.sdcard_error_unkonw);
                        SDCardStatusActivity.this.mlAlertDialog.a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                SDCardStatusActivity.this.doFormat();
                            }
                        });
                        SDCardStatusActivity.this.mlAlertDialog.b((int) R.string.cancel, (DialogInterface.OnClickListener) null);
                        SDCardStatusActivity.this.mlAlertDialog.d();
                        int unused = SDCardStatusActivity.this.mStatus = 3;
                        SDCardStatusActivity.this.mStatusView.setText(SDCardStatusActivity.this.getString(R.string.sdcard_status, new Object[]{Util.getSdCardStatus(SDCardStatusActivity.this.getResources(), 3)}));
                    } else if (i == -2005) {
                        SDCardStatusActivity.this.mlAlertDialog.a((int) R.string.sdcard_tip);
                        SDCardStatusActivity.this.mlAlertDialog.b((int) R.string.sdcard_error_out);
                        SDCardStatusActivity.this.mlAlertDialog.b((int) R.string.know_button, (DialogInterface.OnClickListener) null);
                        SDCardStatusActivity.this.mlAlertDialog.d();
                        int unused2 = SDCardStatusActivity.this.mStatus = 5;
                        SDCardStatusActivity.this.mStatusView.setText(SDCardStatusActivity.this.getString(R.string.sdcard_status, new Object[]{Util.getSdCardStatus(SDCardStatusActivity.this.getResources(), 5)}));
                    } else {
                        if (i == -1) {
                            SDCardStatusActivity.this.mStatusView.setText(SDCardStatusActivity.this.getString(R.string.sdcard_status, new Object[]{string}));
                        }
                        SDCardStatusActivity.this.mlAlertDialog.a((int) R.string.sdcard_tip);
                        SDCardStatusActivity.this.mlAlertDialog.b((int) R.string.sdcard_error_tip);
                        SDCardStatusActivity.this.mlAlertDialog.a((int) R.string.common_retry, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                SDCardStatusActivity.this.loadData(true);
                            }
                        });
                        SDCardStatusActivity.this.mlAlertDialog.b((int) R.string.cancel, (DialogInterface.OnClickListener) null);
                        SDCardStatusActivity.this.mlAlertDialog.d();
                    }
                }
            }
        }, z);
    }

    public void onBackPressed() {
        setResult(-1);
        super.onBackPressed();
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.sdcard_format) {
            Event.a(Event.P);
            doFormat();
        } else if (id == R.id.sdcard_out) {
            Event.a(Event.Q);
            Event.a(Event.bQ);
            if (this.mStatus == 5) {
                ToastUtil.a((Context) this, (int) R.string.sdcard_out_already);
                return;
            }
            initAlertDlg();
            this.mlAlertDialog.a((int) R.string.sdcard_out);
            this.mlAlertDialog.b((int) R.string.sdcard_out_tips);
            this.mlAlertDialog.b((int) R.string.cancel, (DialogInterface.OnClickListener) null);
            this.mlAlertDialog.a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    SDCardStatusActivity.this.initProgressDlg();
                    SDCardStatusActivity.this.progressDialog.show();
                    if (SDCardStatusActivity.this.mCameraDevice.n()) {
                        SDCardStatusActivity.this.mCameraDevice.d().c((Callback<Object>) new Callback<Object>() {
                            public void onSuccess(Object obj) {
                                if (!SDCardStatusActivity.this.isFinishing()) {
                                    SDCardStatusActivity.this.disProgressDlg();
                                    ToastUtil.a((Context) SDCardStatusActivity.this, (int) R.string.sdcard_out_success);
                                    SDCardStatusActivity.this.mStatusView.setText(SDCardStatusActivity.this.getString(R.string.sdcard_status, new Object[]{Util.getSdCardStatus(SDCardStatusActivity.this.getResources(), 5)}));
                                    SDCardStatusActivity.this.mStatusLayout.setVisibility(8);
                                    SDCardStatusActivity.this.mVideoSize.setText("");
                                    SDCardStatusActivity.this.mFreeSize.setText("");
                                    SDCardStatusActivity.this.mOtherSize.setText("");
                                    SDCardStatusActivity.this.mCameraDevice.f().a(CameraPropertyBase.k, (Object) 5);
                                    SDCardStatusActivity.this.sdcard_format.setEnabled(false);
                                    SDCardStatusActivity.this.sdcard_format.setTitleColor(Color.c);
                                }
                            }

                            public void onFailure(int i, String str) {
                                if (!SDCardStatusActivity.this.isFinishing()) {
                                    ToastUtil.a((Context) SDCardStatusActivity.this, (int) R.string.sdcard_out_fail);
                                    SDCardStatusActivity.this.disProgressDlg();
                                }
                            }
                        });
                    } else {
                        SDCardStatusActivity.this.mCameraDevice.c().c((Callback<Object>) new Callback<Object>() {
                            public void onSuccess(Object obj) {
                                if (!SDCardStatusActivity.this.isFinishing()) {
                                    SDCardStatusActivity.this.disProgressDlg();
                                    ToastUtil.a((Context) SDCardStatusActivity.this, (int) R.string.sdcard_out_success);
                                    SDCardStatusActivity.this.mStatusView.setText(SDCardStatusActivity.this.getString(R.string.sdcard_status, new Object[]{Util.getSdCardStatus(SDCardStatusActivity.this.getResources(), 5)}));
                                    SDCardStatusActivity.this.mStatusLayout.setVisibility(8);
                                    SDCardStatusActivity.this.mVideoSize.setText("");
                                    SDCardStatusActivity.this.mFreeSize.setText("");
                                    SDCardStatusActivity.this.mOtherSize.setText("");
                                    SDCardStatusActivity.this.mCameraDevice.f().a(CameraPropertyBase.k, (Object) 5);
                                    SDCardStatusActivity.this.sdcard_format.setEnabled(false);
                                    SDCardStatusActivity.this.sdcard_format.setTitleColor(Color.c);
                                }
                            }

                            public void onFailure(int i, String str) {
                                if (!SDCardStatusActivity.this.isFinishing()) {
                                    ToastUtil.a((Context) SDCardStatusActivity.this, (int) R.string.sdcard_out_fail);
                                    SDCardStatusActivity.this.disProgressDlg();
                                }
                            }
                        });
                    }
                }
            });
            this.mlAlertDialog.d();
        } else if (id == R.id.title_bar_return) {
            onBackPressed();
        }
    }

    /* access modifiers changed from: private */
    public void parseInfo(SDCardInfo sDCardInfo) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.sdcardVideo.getLayoutParams();
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.sdcardOther.getLayoutParams();
        LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) this.sdcardFree.getLayoutParams();
        layoutParams.weight = (float) sDCardInfo.c;
        layoutParams3.weight = (float) sDCardInfo.b;
        layoutParams2.weight = (float) sDCardInfo.d;
        if (!(sDCardInfo.e == -1 || sDCardInfo.e == this.mStatus)) {
            this.mStatus = sDCardInfo.e;
            this.mStatusView.setText(getString(R.string.sdcard_status, new Object[]{Util.getSdCardStatus(getResources(), this.mStatus)}));
            this.mCameraDevice.f().a(CameraPropertyBase.k, (Object) Integer.valueOf(this.mStatus));
        }
        this.sdcardVideo.setLayoutParams(layoutParams);
        this.sdcardOther.setLayoutParams(layoutParams2);
        this.sdcardFree.setLayoutParams(layoutParams3);
        this.mFreeSize.setText(FileUtil.a(sDCardInfo.b));
        this.mVideoSize.setText(FileUtil.a(sDCardInfo.c));
        this.mOtherSize.setText(FileUtil.a(sDCardInfo.d));
        this.mTotalView.setText(getString(R.string.sdcard_size, new Object[]{FileUtil.a(sDCardInfo.f8074a)}));
        Event.a(Event.bS, Event.bz, (Object) FileUtil.a(sDCardInfo.f8074a));
    }

    private void checkFormat() {
        this.mHandler.removeMessages(this.SD_FORMAT_CHECK);
        this.mCameraDevice.c().b((Callback<SDCardInfo>) new Callback<SDCardInfo>() {
            public void onSuccess(SDCardInfo sDCardInfo) {
                if (!SDCardStatusActivity.this.isFinishing()) {
                    SDCardStatusActivity.this.disProgressDlg();
                    boolean unused = SDCardStatusActivity.this.isFormating = false;
                    SDCardStatusActivity.this.parseInfo(sDCardInfo);
                    SDCardStatusActivity.this.mStatusLayout.setVisibility(0);
                    ToastUtil.a((Context) SDCardStatusActivity.this, (int) R.string.sdcard_format_success);
                }
            }

            public void onFailure(int i, String str) {
                if (!SDCardStatusActivity.this.isFinishing()) {
                    if (i == -2000) {
                        SDCardStatusActivity.this.mHandler.sendEmptyMessageDelayed(SDCardStatusActivity.this.SD_FORMAT_CHECK, (long) SDCardStatusActivity.this.CHECK_DURATION);
                        return;
                    }
                    boolean unused = SDCardStatusActivity.this.isFormating = false;
                    SDCardStatusActivity.this.disProgressDlg();
                    SDKLog.e(Tag.d, "check format error " + i);
                    SDCardStatusActivity.this.sdcard_format.setEnabled(true);
                    SDCardStatusActivity.this.sdcard_format.setTitleColor(android.graphics.Color.parseColor("#e94f4f"));
                    ToastUtil.a((Context) SDCardStatusActivity.this, (int) R.string.sdcard_format_fail);
                }
            }
        }, true);
    }

    /* access modifiers changed from: private */
    public void doFormat() {
        Event.a(Event.bP);
        initAlertDlg();
        this.mlAlertDialog.a((int) R.string.sdcard_format);
        this.mlAlertDialog.b((int) R.string.sdcard_format_tip);
        this.mlAlertDialog.a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                SDCardStatusActivity.this.initProgressDlg();
                SDCardStatusActivity.this.progressDialog.show();
                if (SDCardStatusActivity.this.mCameraDevice.n()) {
                    SDCardStatusActivity.this.mCameraDevice.d().b((Callback<Object>) new Callback<Object>() {
                        public void onSuccess(Object obj) {
                            if (!SDCardStatusActivity.this.isFinishing()) {
                                boolean unused = SDCardStatusActivity.this.isFormating = true;
                                SDCardStatusActivity.this.mHandler.removeMessages(SDCardStatusActivity.this.SD_FORMAT_CHECK);
                                SDCardStatusActivity.this.mHandler.sendEmptyMessageDelayed(SDCardStatusActivity.this.SD_FORMAT_CHECK, (long) SDCardStatusActivity.this.CHECK_DURATION);
                            }
                        }

                        public void onFailure(int i, String str) {
                            if (!SDCardStatusActivity.this.isFinishing()) {
                                if (i == -2000) {
                                    SDCardStatusActivity.this.mHandler.removeMessages(SDCardStatusActivity.this.SD_FORMAT_CHECK);
                                    SDCardStatusActivity.this.mHandler.sendEmptyMessageDelayed(SDCardStatusActivity.this.SD_FORMAT_CHECK, (long) SDCardStatusActivity.this.CHECK_DURATION);
                                    ToastUtil.a((Context) SDCardStatusActivity.this, (int) R.string.sdcard_formating);
                                    return;
                                }
                                if (i == -2003) {
                                    ToastUtil.a((Context) SDCardStatusActivity.this, (int) R.string.sdcard_no);
                                } else {
                                    SDKLog.e(Tag.d, "format error " + i + " info ");
                                    SDCardStatusActivity.this.sdcard_format.setEnabled(true);
                                    SDCardStatusActivity.this.sdcard_format.setTitleColor(android.graphics.Color.parseColor("#e94f4f"));
                                    ToastUtil.a((Context) SDCardStatusActivity.this, (int) R.string.sdcard_format_fail);
                                }
                                SDCardStatusActivity.this.disProgressDlg();
                            }
                        }
                    });
                } else {
                    SDCardStatusActivity.this.mCameraDevice.c().b((Callback<Object>) new Callback<Object>() {
                        public void onSuccess(Object obj) {
                            if (!SDCardStatusActivity.this.isFinishing()) {
                                boolean unused = SDCardStatusActivity.this.isFormating = true;
                                SDCardStatusActivity.this.mHandler.removeMessages(SDCardStatusActivity.this.SD_FORMAT_CHECK);
                                SDCardStatusActivity.this.mHandler.sendEmptyMessageDelayed(SDCardStatusActivity.this.SD_FORMAT_CHECK, (long) SDCardStatusActivity.this.CHECK_DURATION);
                            }
                        }

                        public void onFailure(int i, String str) {
                            if (!SDCardStatusActivity.this.isFinishing()) {
                                if (i == -2000) {
                                    SDCardStatusActivity.this.mHandler.removeMessages(SDCardStatusActivity.this.SD_FORMAT_CHECK);
                                    SDCardStatusActivity.this.mHandler.sendEmptyMessageDelayed(SDCardStatusActivity.this.SD_FORMAT_CHECK, (long) SDCardStatusActivity.this.CHECK_DURATION);
                                    ToastUtil.a((Context) SDCardStatusActivity.this, (int) R.string.sdcard_formating);
                                    return;
                                }
                                if (i == -2003) {
                                    ToastUtil.a((Context) SDCardStatusActivity.this, (int) R.string.sdcard_no);
                                } else {
                                    SDKLog.e(Tag.d, "format error " + i + " info ");
                                    SDCardStatusActivity.this.sdcard_format.setEnabled(true);
                                    SDCardStatusActivity.this.sdcard_format.setTitleColor(android.graphics.Color.parseColor("#e94f4f"));
                                    ToastUtil.a((Context) SDCardStatusActivity.this, (int) R.string.sdcard_format_fail);
                                }
                                SDCardStatusActivity.this.disProgressDlg();
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
    public void initProgressDlg() {
        if (this.progressDialog == null) {
            this.progressDialog = new XQProgressDialog(this);
            this.progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    dialogInterface.dismiss();
                    if (!SDCardStatusActivity.this.isFormating) {
                        ToastUtil.a((Context) SDCardStatusActivity.this, (int) R.string.sdcard_error);
                        SDCardStatusActivity.this.mHandler.removeMessages(SDCardStatusActivity.this.SD_FORMAT_CHECK);
                    }
                }
            });
        }
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
}
