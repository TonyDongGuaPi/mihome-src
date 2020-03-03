package com.xiaomi.smarthome.camera.activity.timelapse;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.Utils.FileUtil;
import com.Utils.NetworkUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.mijia.app.Event;
import com.mijia.camera.CameraPlayerEx;
import com.mijia.camera.Utils.BitmapUtils;
import com.mijia.camera.Utils.Util;
import com.mijia.debug.SDKLog;
import com.mijia.model.sdcard.SDCardInfo;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.IMISSListener;
import com.xiaomi.smarthome.camera.IStreamCmdMessageListener;
import com.xiaomi.smarthome.camera.XmVideoViewGl;
import com.xiaomi.smarthome.camera.activity.CameraPlayerBaseActivity;
import com.xiaomi.smarthome.camera.activity.CommandTreatment;
import com.xiaomi.smarthome.camera.activity.local.AlbumActivity;
import com.xiaomi.smarthome.camera.activity.local.TimelapsePhotographPlayActivity;
import com.xiaomi.smarthome.camera.activity.timelapse.ScheduldShootDialog;
import com.xiaomi.smarthome.camera.activity.timelapse.ScheduldShootTimeDialog;
import com.xiaomi.smarthome.camera.activity.timelapse.ShootModeDialog;
import com.xiaomi.smarthome.camera.view.MultiStateTextView;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.Parser;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TimeLapsePhotographyActivity extends CameraPlayerBaseActivity implements IStreamCmdMessageListener {
    /* access modifiers changed from: private */
    public static final String TAG = "TimeLapsePhotographyActivity";
    /* access modifiers changed from: private */
    public static int TIMELAPSE_PHOTOGRAPHY = 1;
    private Calendar currentCalendar;
    /* access modifiers changed from: private */
    public int currentHour;
    private int currentHourCalendar;
    private int currentMinCalendar;
    public TimelapseTask currentTimelapseTask;
    private int diffHour;
    private int diffMin;
    private String endTimeHour;
    private String endTimeMin;
    /* access modifiers changed from: private */
    public String errorText;
    /* access modifiers changed from: private */
    public FrameLayout fl_timelaps_demo;
    private int intervalToTakePicture;
    /* access modifiers changed from: private */
    public boolean isCancleTimeLapse = false;
    private boolean isClickAlbum = false;
    /* access modifiers changed from: private */
    public boolean isManualShootFinish = false;
    /* access modifiers changed from: private */
    public boolean isMemorycardExists = false;
    /* access modifiers changed from: private */
    public boolean isScheduledShoot = false;
    private boolean isShootTask;
    private boolean isStartPlay = false;
    /* access modifiers changed from: private */
    public boolean isStartShoot = false;
    private boolean isStopAndSave = false;
    private boolean isTomorrow = false;
    /* access modifiers changed from: private */
    public ImageView iv_shoot_start;
    private ImageView iv_timelaps_demo;
    private SDCardInfo mInfo;
    /* access modifiers changed from: private */
    public LinearLayout mLLFuncPopup;
    private TimeLapseTaskManager mTimeLapseTaskManager;
    private MultiStateTextView mTvScheduledShoot;
    /* access modifiers changed from: private */
    public MultiStateTextView mTvShootingMode;
    /* access modifiers changed from: private */
    public boolean mVideoPlaying;
    private TimelapseTask newTimelapseTask;
    private int scheduledHour;
    private int scheduledMine;
    private Calendar scheduledShootCalendar;
    /* access modifiers changed from: private */
    public int shootDurSecond = 18000;
    private ShootModeDialog shootModeDialog;
    private String startTimeDay;
    private String startTimeHour;
    private String startTimeMin;
    private ImageView title_bar_return;
    /* access modifiers changed from: private */
    public TextView tv_mode_hour;
    /* access modifiers changed from: private */
    public TextView tv_timelapse_start;

    /* access modifiers changed from: protected */
    public void detectSDCard() {
    }

    public boolean isHistory() {
        return false;
    }

    public void doCreate(Bundle bundle) {
        super.doCreate(bundle);
        setContentView(R.layout.camera_activity_timelapse_photography);
        this.scheduledShootCalendar = Calendar.getInstance();
        this.currentCalendar = Calendar.getInstance();
        initView();
        initVideoView();
        initMulti();
        loadData(false);
        this.mTimeLapseTaskManager = new TimeLapseTaskManager(this.mCameraDevice, this);
        this.iv_timelaps_demo = (ImageView) findViewById(R.id.iv_timelaps_demo);
        this.fl_timelaps_demo = (FrameLayout) findViewById(R.id.fl_timelaps_demo);
        this.iv_timelaps_demo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            }
        });
        judgeDownloadTimeLapseDemo();
    }

    /* access modifiers changed from: protected */
    public void startPlay() {
        if (this.mCameraPlayerEx == null) {
            this.mCameraPlayerEx = new CameraPlayerEx(this, this.mCameraDevice, this, this.mVideoView);
        }
        if (!NetworkUtil.c(activity()) || this.mAllowMobileNetwork) {
            SDKLog.e(TAG, "startPlay 开始连接摄像头");
            String str = TAG;
            SDKLog.e(str, "mCameraPlayer 22222 =" + this.mCameraPlayerEx.l());
            if (this.mPauseView != null && this.mPauseView.getVisibility() == 0) {
                this.mPauseView.setVisibility(8);
            }
            if (this.mCameraPlayerEx != null) {
                this.mCameraPlayerEx.w();
                this.mCameraPlayerEx = null;
            }
            if (this.mCameraPlayerEx == null) {
                this.mCameraPlayerEx = new CameraPlayerEx(this, this.mCameraDevice, this, this.mVideoView);
            }
            if (!this.mCameraPlayerEx.l()) {
                if (this.mCameraPlayerEx != null) {
                    this.mCameraPlayerEx.a((IMISSListener) new IMISSListener() {
                        public void onProgress(int i) {
                        }

                        public void onSuccess(String str, Object obj) {
                            if (TimeLapsePhotographyActivity.this.mCameraPlayerEx.u()) {
                                TimeLapsePhotographyActivity.this.toggleRemoteAV(true, false);
                            } else {
                                TimeLapsePhotographyActivity.this.toggleRemoteAV(true, true);
                            }
                            boolean unused = TimeLapsePhotographyActivity.this.mVideoPlaying = true;
                        }

                        public void onFailed(int i, String str) {
                            boolean unused = TimeLapsePhotographyActivity.this.mVideoPlaying = false;
                        }
                    });
                }
                String str2 = TAG;
                SDKLog.e(str2, "mCameraPlayer =" + this.mCameraPlayerEx.l());
                if (this.mCameraPlayerEx != null) {
                    SDKLog.e(TAG, "连接摄像头成功,开始发送查询当前延时摄影状态信息");
                    this.mTimeLapseTaskManager.getTimeLapseTask(this);
                }
            } else if (this.mCameraPlayerEx != null) {
                if (this.mCameraPlayerEx.u()) {
                    toggleRemoteAV(true, false);
                } else {
                    toggleRemoteAV(true, true);
                }
                SDKLog.e(TAG, "连接摄像头成功,开始发送查询当前延时摄影状态信息");
                this.mTimeLapseTaskManager.getTimeLapseTask(this);
            }
            this.isStartPlay = true;
        } else {
            pauseCamera();
        }
        if (this.mCameraPlayerEx != null) {
            SDKLog.e(TAG, "onStop 里设置静音");
            this.mCameraPlayerEx.b(true);
        }
    }

    /* access modifiers changed from: protected */
    public void refreshUI() {
        if (this.mCameraDevice.f().c()) {
            if (this.mPowerOffView.getVisibility() == 0) {
                this.mPowerOffView.setVisibility(8);
                startPlay();
            }
        } else if (this.mPowerOffView.getVisibility() == 8) {
            this.mPowerOffView.setVisibility(0);
        }
    }

    /* access modifiers changed from: protected */
    public void doResume() {
        this.mPauseView.setVisibility(8);
        if (!this.mCameraPlayerEx.l()) {
            showLastLoading();
            this.mCameraPlayerEx.a(0, false);
            return;
        }
        setPlayTime();
    }

    /* access modifiers changed from: protected */
    public void resumeCamera() {
        String str = TAG;
        SDKLog.e(str, "resumeCamera.....isStartPlay=" + this.isStartPlay);
        if (this.mPauseView != null) {
            this.mPauseView.setVisibility(8);
        }
        if (NetworkUtil.c(activity())) {
            this.mAllowMobileNetwork = true;
        }
        if (!this.isStartPlay) {
            hidError();
            showLoading("");
            if (!(this.mCameraPlayerEx == null || this.mCameraDevice == null || !this.mCameraDevice.f().c())) {
                this.mCameraPlayerEx.a((IMISSListener) new IMISSListener() {
                    public void onProgress(int i) {
                    }

                    public void onSuccess(String str, Object obj) {
                        if (TimeLapsePhotographyActivity.this.mCameraPlayerEx.u()) {
                            TimeLapsePhotographyActivity.this.toggleRemoteAV(true, false);
                        } else {
                            TimeLapsePhotographyActivity.this.toggleRemoteAV(true, true);
                        }
                        boolean unused = TimeLapsePhotographyActivity.this.mVideoPlaying = true;
                    }

                    public void onFailed(int i, String str) {
                        boolean unused = TimeLapsePhotographyActivity.this.mVideoPlaying = false;
                    }
                });
            }
        } else {
            setPlayTime();
        }
        refreshUI();
    }

    public void setPlayTime() {
        if (!this.mUserPause) {
            hidError();
            showLoading("");
        }
        if (this.mCameraPlayerEx != null && this.mCameraDevice != null && this.mCameraDevice.f().c()) {
            this.mCameraPlayerEx.a((IMISSListener) new IMISSListener() {
                public void onProgress(int i) {
                }

                public void onSuccess(String str, Object obj) {
                    if (TimeLapsePhotographyActivity.this.mCameraPlayerEx.u()) {
                        TimeLapsePhotographyActivity.this.toggleRemoteAV(true, false);
                    } else {
                        TimeLapsePhotographyActivity.this.toggleRemoteAV(true, true);
                    }
                    boolean unused = TimeLapsePhotographyActivity.this.mVideoPlaying = true;
                }

                public void onFailed(int i, String str) {
                    boolean unused = TimeLapsePhotographyActivity.this.mVideoPlaying = false;
                }
            });
        }
    }

    private void initView() {
        ((TextView) findViewById(R.id.title_bar_title)).setText(getString(R.string.timelapse_photography));
        findViewById(R.id.tv_album).setOnClickListener(this);
        this.tv_timelapse_start = (TextView) findViewById(R.id.tv_timelapse_start);
        this.mTvShootingMode = (MultiStateTextView) findViewById(R.id.tv_shooting_mode);
        this.mTvScheduledShoot = (MultiStateTextView) findViewById(R.id.tv_scheduled_shoot);
        this.mTvScheduledShoot.setOnClickListener(this);
        this.iv_shoot_start = (ImageView) findViewById(R.id.iv_shoot_start);
        this.iv_shoot_start.setOnClickListener(this);
        this.mLLFuncPopup = (LinearLayout) findViewById(R.id.llFuncPopup);
        this.title_bar_return = (ImageView) findViewById(R.id.title_bar_return);
        this.title_bar_return.setImageResource(R.drawable.icon_timelapse_back);
        this.title_bar_return.setOnClickListener(this);
        this.mPauseView = findViewById(R.id.pause_view);
        this.tv_mode_hour = (TextView) findViewById(R.id.tv_mode_hour);
        this.currentHourCalendar = this.currentCalendar.get(11);
        this.currentMinCalendar = this.currentCalendar.get(12);
        if (this.currentHourCalendar < 10) {
            this.startTimeHour = "0" + this.currentHourCalendar;
        } else {
            this.startTimeHour = String.valueOf(this.currentHourCalendar);
        }
        if (this.currentMinCalendar < 10) {
            this.startTimeMin = "0" + this.currentMinCalendar;
        } else {
            this.startTimeMin = String.valueOf(this.currentMinCalendar);
        }
        this.mPauseView = findViewById(R.id.pause_view);
        this.mPauseView.setOnClickListener(this);
        refreshState(false);
        this.iv_shoot_start.setEnabled(true);
        this.iv_shoot_start.setAlpha(1.0f);
    }

    private void initMulti() {
        this.mTvShootingMode.addState(new MultiStateTextView.StateItem(R.string.timelapse_shoot_one_hour, R.drawable.icon_timelapse_shoot_interval, new View.OnClickListener() {
            public void onClick(View view) {
                SDKLog.e(TimeLapsePhotographyActivity.TAG, "点击了拍摄1小时");
                TimeLapsePhotographyActivity.this.showDialogType(0);
            }
        }));
        this.mTvShootingMode.addState(new MultiStateTextView.StateItem(R.string.timelapse_shoot_three_hour, R.drawable.icon_timelapse_shoot_interval, new View.OnClickListener() {
            public void onClick(View view) {
                SDKLog.e(TimeLapsePhotographyActivity.TAG, "点击了拍摄3小时");
                TimeLapsePhotographyActivity.this.showDialogType(1);
            }
        }));
        this.mTvShootingMode.addState(new MultiStateTextView.StateItem(R.string.timelapse_shoot_fives_hour, R.drawable.icon_timelapse_shoot_interval, new View.OnClickListener() {
            public void onClick(View view) {
                SDKLog.e(TimeLapsePhotographyActivity.TAG, "点击了拍摄5小时");
                TimeLapsePhotographyActivity.this.showDialogType(2);
            }
        }));
    }

    /* access modifiers changed from: private */
    public void showDialogType(int i) {
        this.shootModeDialog = new ShootModeDialog.Builder(activity(), true).setTitle(getString(R.string.timelapse_shooting_time)).setType(i).setShootOneHourListener(new ShootModeDialog.ShootOneHourListener() {
            public void onConfirm(ShootModeDialog shootModeDialog) {
                shootModeDialog.dismiss();
                int unused = TimeLapsePhotographyActivity.this.shootDurSecond = 3600;
                TimeLapsePhotographyActivity.this.calcuInterval(1);
                int unused2 = TimeLapsePhotographyActivity.this.currentHour = 1;
                TimeLapsePhotographyActivity.this.mTvShootingMode.setCurrentState(0);
                TimeLapsePhotographyActivity.this.tv_mode_hour.setText(TimeLapsePhotographyActivity.this.getText(R.string.timelapse_shoot_one_hour_desc));
                TimeLapsePhotographyActivity.this.setTimeLapseMode(0);
            }
        }).setShootThreeHourListenerr(new ShootModeDialog.ShootThreeHourListener() {
            public void onConfirm(ShootModeDialog shootModeDialog) {
                shootModeDialog.dismiss();
                int unused = TimeLapsePhotographyActivity.this.shootDurSecond = 10800;
                TimeLapsePhotographyActivity.this.calcuInterval(3);
                int unused2 = TimeLapsePhotographyActivity.this.currentHour = 3;
                TimeLapsePhotographyActivity.this.mTvShootingMode.setCurrentState(1);
                TimeLapsePhotographyActivity.this.tv_mode_hour.setText(TimeLapsePhotographyActivity.this.getText(R.string.timelapse_shoot_three_hour_desc));
                TimeLapsePhotographyActivity.this.setTimeLapseMode(1);
            }
        }).setShootFivesHourListener(new ShootModeDialog.ShootFivesHourListener() {
            public void onConfirm(ShootModeDialog shootModeDialog) {
                shootModeDialog.dismiss();
                int unused = TimeLapsePhotographyActivity.this.shootDurSecond = 18000;
                TimeLapsePhotographyActivity.this.calcuInterval(5);
                int unused2 = TimeLapsePhotographyActivity.this.currentHour = 5;
                TimeLapsePhotographyActivity.this.mTvShootingMode.setCurrentState(2);
                TimeLapsePhotographyActivity.this.tv_mode_hour.setText(TimeLapsePhotographyActivity.this.getText(R.string.timelapse_shoot_fives_hour_desc));
                TimeLapsePhotographyActivity.this.setTimeLapseMode(2);
            }
        }).build();
        this.shootModeDialog.show();
    }

    public void onClick(View view) {
        if (!doOnClick(view)) {
            int id = view.getId();
            if (id == R.id.iv_shoot_start) {
                Event.a(Event.bv);
                if (!this.isMemorycardExists) {
                    ToastUtil.a((Context) activity(), (CharSequence) this.errorText);
                    return;
                }
                this.fl_timelaps_demo.setVisibility(8);
                this.isStartShoot = !this.isStartShoot;
                this.isCancleTimeLapse = false;
                if (this.isStartShoot) {
                    SDKLog.e(TAG, "开始拍摄");
                    this.iv_shoot_start.setImageResource(R.drawable.icon_timelapse_shoot_finish);
                    refreshState(false);
                    createTimelapseTask();
                    return;
                }
                String str = TAG;
                SDKLog.e(str, "结束拍摄 isScheduledShoot=" + this.isScheduledShoot);
                if (this.isScheduledShoot) {
                    scheduldShootDialog(getString(R.string.scheduled_timelapse_desc));
                } else {
                    scheduldShootDialog(getString(R.string.scheduled_timelapse_stop));
                }
            } else if (id == R.id.title_bar_return) {
                finish();
            } else if (id == R.id.tv_album) {
                this.isClickAlbum = true;
                startActivity(new Intent(this, AlbumActivity.class));
            } else if (id == R.id.tv_scheduled_shoot) {
                new ScheduldShootTimeDialog.Builder(activity(), true, this.startTimeHour, this.startTimeMin, this.startTimeDay).setTitle(getString(R.string.timelapse_scheduled_shoot_time)).setConfirmListener(new ScheduldShootTimeDialog.ConfirmListener() {
                    public void onConfirm(ScheduldShootTimeDialog scheduldShootTimeDialog, String str, String str2, String str3) {
                        String access$500 = TimeLapsePhotographyActivity.TAG;
                        SDKLog.e(access$500, "hourTime===" + str + ",minTime===" + str2 + ",day===" + str3);
                        TimeLapsePhotographyActivity.this.scheduledTimeLapsePhotography(str, str2, str3);
                        TimeLapsePhotographyActivity.this.fl_timelaps_demo.setVisibility(8);
                    }
                }).build().show();
            }
        }
    }

    /* access modifiers changed from: private */
    public void refreshState(boolean z) {
        if (z) {
            this.mTvShootingMode.setAlpha(1.0f);
            this.mTvShootingMode.setEnabled(true);
            this.mTvScheduledShoot.setAlpha(1.0f);
            this.mTvScheduledShoot.setEnabled(true);
            this.tv_mode_hour.setAlpha(1.0f);
            this.tv_mode_hour.setEnabled(true);
            return;
        }
        this.mTvShootingMode.setAlpha(0.3f);
        this.mTvShootingMode.setEnabled(false);
        this.mTvScheduledShoot.setAlpha(0.3f);
        this.mTvScheduledShoot.setEnabled(false);
        this.tv_mode_hour.setAlpha(0.3f);
        this.tv_mode_hour.setEnabled(false);
    }

    public void onServerCmd(int i, byte[] bArr) {
        String str = TAG;
        SDKLog.e(str, "onServerCmd  type=" + i + ",data=" + bArr.length);
        new CommandTreatment(this.mHandler, this.mCameraDevice).processData(bArr);
    }

    private void initVideoView() {
        this.mVideoViewFrame = (FrameLayout) findViewById(R.id.video_frame);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        this.mVideoLayout = new FrameLayout(activity());
        this.mVideoViewFrame.addView(this.mVideoLayout, 0, layoutParams);
        this.mVideoView = XmPluginHostApi.instance().createVideoView(activity(), this.mVideoLayout, true, 2);
        this.mLoadingView = LayoutInflater.from(this).inflate(R.layout.camera_progress, (ViewGroup) null);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-1, -1);
        this.mVideoLayout.addView(this.mLoadingView, layoutParams2);
        this.mLoadingView.setVisibility(8);
        this.mLoadingProgress = (TextView) this.mLoadingView.findViewById(R.id.loading_progress);
        this.mLoadingTitle = (TextView) this.mLoadingView.findViewById(R.id.loading_title);
        this.mLoadingImageView = (ImageView) this.mLoadingView.findViewById(R.id.loading_anim);
        this.mLoadingAnimation = (AnimationDrawable) getResources().getDrawable(R.drawable.camera_anim_camera_loading);
        this.mLoadingImageView.setImageDrawable(this.mLoadingAnimation);
        this.mErrorRetryView = LayoutInflater.from(this).inflate(R.layout.camera_error_retry, (ViewGroup) null);
        this.mVideoLayout.addView(this.mErrorRetryView, layoutParams2);
        this.mErrorRetryView.setVisibility(8);
        this.mRetryView = this.mErrorRetryView.findViewById(R.id.retry_btn);
        this.mErrorInfoView = (TextView) this.mErrorRetryView.findViewById(R.id.error_info);
        this.mPowerOffView = LayoutInflater.from(this).inflate(R.layout.camera_closed, (ViewGroup) null);
        this.mVideoLayout.addView(this.mPowerOffView, layoutParams2);
        this.mPowerOffView.setVisibility(8);
        this.mVideoView.setDistort(0.27083334f, 0.04411765f);
        this.mVideoView.setTouch(false);
        this.mVideoView.setMiniScale(true);
        this.mVideoView.initial();
        showLoading();
    }

    public void onDisConnected() {
        super.onDisConnected();
        SDKLog.e(TAG, "onDisConnected  ");
    }

    public void onDisconnectedWithCode(int i) {
        String str = TAG;
        SDKLog.e(str, "onDisconnectedWithCode code=" + i);
    }

    /* access modifiers changed from: protected */
    public void pauseCamera() {
        super.pauseCamera();
    }

    public void onBackPressed() {
        this.isTimeLapsePhotography = true;
        String str = TAG;
        SDKLog.e(str, "onBackPressed isTimeLapsePhotography=" + this.isTimeLapsePhotography);
        super.onBackPressed();
    }

    private void timeLapseFinishAnimation() {
        if (this.mCameraDevice == null || this.mCameraDevice.f() == null || !this.mCameraDevice.f().c()) {
            ToastUtil.a((Context) activity(), (int) R.string.power_off);
        } else if (this.mCameraPlayerEx == null || !this.mCameraPlayerEx.l()) {
            ToastUtil.a((Context) activity(), (int) R.string.snap_failed_paused);
        } else {
            this.mVideoView.snap(new XmVideoViewGl.PhotoSnapCallback() {
                public void onSnap(final Bitmap bitmap) {
                    TimeLapsePhotographyActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            TimeLapsePhotographyActivity.this.onSnapShot(bitmap);
                        }
                    });
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void onSnapShot(Bitmap bitmap) {
        final String c = FileUtil.c(this.mCameraDevice.getDid(), this.currentTimelapseTask.getTaskID(), this.currentTimelapseTask.getTaskName());
        String str = TAG;
        SDKLog.e(str, "延时摄影拍摄完成截图nSnapShot ===" + c);
        this.fl_timelaps_demo.setVisibility(0);
        if (c != null && bitmap != null) {
            Bitmap a2 = BitmapUtils.a(bitmap, activity());
            if (a2 != null) {
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(c);
                    a2.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                    fileOutputStream.close();
                } catch (IOException unused) {
                    return;
                }
            }
            final Bitmap createScaledBitmap = Bitmap.createScaledBitmap(a2, 200, (a2.getHeight() * 200) / a2.getWidth(), false);
            runOnUiThread(new Runnable() {
                public void run() {
                    if (new File(c).exists()) {
                        ImageView imageView = (ImageView) TimeLapsePhotographyActivity.this.findViewById(R.id.ivShotPic);
                        if (TimeLapsePhotographyActivity.this.mLLFuncPopup.getVisibility() == 0) {
                            TimeLapsePhotographyActivity.this.mLLFuncPopup.setVisibility(8);
                        }
                        TimeLapsePhotographyActivity.this.mLLFuncPopup.setLayoutParams((FrameLayout.LayoutParams) TimeLapsePhotographyActivity.this.mLLFuncPopup.getLayoutParams());
                        TimeLapsePhotographyActivity.this.mLLFuncPopup.startAnimation(AnimationUtils.loadAnimation(TimeLapsePhotographyActivity.this.activity(), R.anim.anim_snap_shot_in));
                        TimeLapsePhotographyActivity.this.mLLFuncPopup.setVisibility(0);
                        TimeLapsePhotographyActivity.this.dismissSnapshotPopupRunnable(3000);
                        if (createScaledBitmap != null) {
                            imageView.setImageBitmap(createScaledBitmap);
                        }
                        imageView.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                TimeLapsePhotographyActivity.this.dismissSnapshotPopupRunnable(0);
                                Intent intent = new Intent(TimeLapsePhotographyActivity.this, TimelapsePhotographPlayActivity.class);
                                File videoFile = TimeLapsePhotographyActivity.this.currentTimelapseTask.getVideoFile("mp4");
                                if (videoFile != null) {
                                    intent.putExtra("file_path", videoFile.getAbsolutePath());
                                    String access$500 = TimeLapsePhotographyActivity.TAG;
                                    SDKLog.e(access$500, "onRecyclerClick localFileData.file_path=" + videoFile.getAbsolutePath());
                                }
                                intent.putExtra("file_realtime", TimeLapsePhotographyActivity.this.currentTimelapseTask.getRealStartTimeInSec());
                                intent.putExtra("video_path", TimeLapsePhotographyActivity.this.currentTimelapseTask.getVideoPath(TimeLapsePhotographyActivity.this.mCameraDevice));
                                intent.putExtra("file_time", TimeLapsePhotographyActivity.this.currentTimelapseTask.getStartTimestampInUTCSeconds());
                                Timelapse.timeLapseList.add(TimeLapsePhotographyActivity.this.currentTimelapseTask);
                                TimeLapsePhotographyActivity.this.startActivityForResult(intent, TimeLapsePhotographyActivity.TIMELAPSE_PHOTOGRAPHY);
                            }
                        });
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void dismissSnapshotPopupRunnable(long j) {
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                if (TimeLapsePhotographyActivity.this.mLLFuncPopup != null && TimeLapsePhotographyActivity.this.mLLFuncPopup.getVisibility() != 8) {
                    TimeLapsePhotographyActivity.this.mLLFuncPopup.startAnimation(AnimationUtils.loadAnimation(TimeLapsePhotographyActivity.this.activity(), R.anim.anim_snap_shot_out));
                    TimeLapsePhotographyActivity.this.mLLFuncPopup.setVisibility(8);
                }
            }
        }, j);
    }

    private void loadData(boolean z) {
        SDKLog.e(TAG, "loadData  获取sd卡信息");
        this.mCameraDevice.d().b((Callback<SDCardInfo>) new Callback<SDCardInfo>() {
            public void onSuccess(SDCardInfo sDCardInfo) {
                boolean unused = TimeLapsePhotographyActivity.this.isMemorycardExists = true;
                TimeLapsePhotographyActivity.this.parseInfo(sDCardInfo);
            }

            public void onFailure(int i, String str) {
                String access$500 = TimeLapsePhotographyActivity.TAG;
                SDKLog.e(access$500, "sdcarad status fail " + i + " : " + str);
                boolean unused = TimeLapsePhotographyActivity.this.isMemorycardExists = false;
                String unused2 = TimeLapsePhotographyActivity.this.errorText = TimeLapsePhotographyActivity.this.getString(R.string.sdcard_error);
                if (i == -2003) {
                    String unused3 = TimeLapsePhotographyActivity.this.errorText = TimeLapsePhotographyActivity.this.getString(R.string.sdcard_no);
                } else if (i == -2000) {
                    String unused4 = TimeLapsePhotographyActivity.this.errorText = TimeLapsePhotographyActivity.this.getString(R.string.sdcard_formating);
                } else if (i == -2002) {
                    String unused5 = TimeLapsePhotographyActivity.this.errorText = TimeLapsePhotographyActivity.this.getString(R.string.sdcard_error_unkonw);
                } else if (i == -2005) {
                    String unused6 = TimeLapsePhotographyActivity.this.errorText = TimeLapsePhotographyActivity.this.getString(R.string.sdcard_error_out);
                } else if (i == -1) {
                    String unused7 = TimeLapsePhotographyActivity.this.errorText = TimeLapsePhotographyActivity.this.getString(R.string.sdcard_error);
                }
                TimeLapsePhotographyActivity.this.refreshState(false);
            }
        }, z);
    }

    /* access modifiers changed from: private */
    public void parseInfo(SDCardInfo sDCardInfo) {
        String str = TAG;
        SDKLog.e(str, "parseInfo info ===" + sDCardInfo);
        if (sDCardInfo == null) {
            ToastUtil.a((Context) activity(), (CharSequence) getString(R.string.sdcard_error));
            return;
        }
        if (!this.isShootTask) {
            refreshState(true);
        }
        this.mInfo = sDCardInfo;
        String a2 = FileUtil.a(sDCardInfo.b);
        String a3 = FileUtil.a(sDCardInfo.c);
        String a4 = FileUtil.a(sDCardInfo.d);
        String str2 = TAG;
        SDKLog.e(str2, "info freeSize =" + sDCardInfo.b + ",info videoSize=" + sDCardInfo.c + ",info otherSize=" + sDCardInfo.d + ",info.mStatus=" + sDCardInfo.e);
        String str3 = TAG;
        SDKLog.e(str3, "freeSize =" + a2 + ",videoSize=" + a3 + ",otherSize=" + a4 + ",info.mStatus=" + sDCardInfo.e);
    }

    private void createTimelapseTask() {
        if (this.mInfo.e == 1) {
            ToastUtil.a((Context) activity(), (CharSequence) getString(R.string.no_memory_card_found));
        } else if (this.mInfo.e == 4) {
            ToastUtil.a((Context) activity(), (CharSequence) getString(R.string.sdcard_formating));
        } else if (this.mInfo.e == 5) {
            ToastUtil.a((Context) activity(), (CharSequence) getString(R.string.sdcard_no));
        } else if (this.mInfo.e == 3) {
            ToastUtil.a((Context) activity(), (CharSequence) getString(R.string.sdcard_error_timelapse));
        } else if (this.mInfo.b < 3145728) {
            ToastUtil.a((Context) activity(), (CharSequence) getString(R.string.sd_no_space));
        } else {
            String str = TAG;
            SDKLog.e(str, "mac= " + this.mCameraDevice.getMac());
            int c = Util.c();
            long currentTimeMillis = (System.currentTimeMillis() / 1000) + ((long) (c * 60));
            this.newTimelapseTask = new TimelapseTask(this.mCameraDevice, currentTimeMillis, currentTimeMillis, currentTimeMillis + ((long) this.shootDurSecond), this.intervalToTakePicture, c);
            SDKLog.e("AlbumActivity", "newTimelapseTask mac= " + this.newTimelapseTask.getCameraMac());
            SDKLog.e("AlbumActivity", "newTimelapseTask taskId= " + this.newTimelapseTask.getTaskID());
            SDKLog.e("AlbumActivity", "newTimelapseTask startTimestampInSeconds= " + this.newTimelapseTask.getStartTimestampInSeconds());
            SDKLog.e("AlbumActivity", "newTimelapseTask endTimestampInSeconds= " + this.newTimelapseTask.getEndTimestampInSeconds());
            SDKLog.e("AlbumActivity", "newTimelapseTask intervalToTakePicture= " + this.newTimelapseTask.getIntervalToTakePicture());
            SDKLog.e("AlbumActivity", "newTimelapseTask timezoneInMunite= " + this.newTimelapseTask.getTimezoneInMinutes());
            this.isStopAndSave = false;
            this.mTimeLapseTaskManager.setTimeLapseTask(this.newTimelapseTask, this);
        }
    }

    private void scheduledCreateTimelapseTask() {
        long timeInMillis;
        String str = TAG;
        SDKLog.e(str, "mac= " + this.mCameraDevice.getMac());
        int c = Util.c();
        String str2 = TAG;
        SDKLog.e(str2, "isTomorrow =" + this.isTomorrow + ",开始时间 scheduledShootCalendar.getTimeInMillis()/1000=" + (this.scheduledShootCalendar.getTimeInMillis() / 1000));
        if (this.isTomorrow) {
            timeInMillis = (this.scheduledShootCalendar.getTimeInMillis() / 1000) + ((long) (c * 60)) + 86400;
        } else {
            timeInMillis = (this.scheduledShootCalendar.getTimeInMillis() / 1000) + ((long) (c * 60));
        }
        long j = timeInMillis;
        this.newTimelapseTask = new TimelapseTask(this.mCameraDevice, j, j, j + ((long) this.shootDurSecond), this.intervalToTakePicture, c);
        String str3 = TAG;
        SDKLog.e(str3, "scheduledCreateTimelapseTask newTimelapseTask mac= " + this.newTimelapseTask.getCameraMac());
        String str4 = TAG;
        SDKLog.e(str4, "scheduledCreateTimelapseTask newTimelapseTask taskId= " + this.newTimelapseTask.getTaskID());
        String str5 = TAG;
        SDKLog.e(str5, "scheduledCreateTimelapseTask newTimelapseTask startTimestampInSeconds= " + this.newTimelapseTask.getStartTimestampInSeconds());
        String str6 = TAG;
        SDKLog.e(str6, "scheduledCreateTimelapseTask newTimelapseTask endTimestampInSeconds= " + this.newTimelapseTask.getEndTimestampInSeconds());
        String str7 = TAG;
        SDKLog.e(str7, "scheduledCreateTimelapseTask newTimelapseTask intervalToTakePicture= " + this.newTimelapseTask.getIntervalToTakePicture());
        String str8 = TAG;
        SDKLog.e(str8, "scheduledCreateTimelapseTask newTimelapseTask timezoneInMunite= " + this.newTimelapseTask.getTimezoneInMinutes());
        this.isStopAndSave = false;
        this.mTimeLapseTaskManager.setTimeLapseTask(this.newTimelapseTask, this);
    }

    /* access modifiers changed from: private */
    public void closeTimelapseTask() {
        this.isStopAndSave = true;
        this.mTimeLapseTaskManager.closeCurrentTimeLapseTask(this);
    }

    /* access modifiers changed from: private */
    public void calcuInterval(int i) {
        this.intervalToTakePicture = ((i * 60) * 60) / 200;
        String str = TAG;
        SDKLog.e(str, "拍摄间隔 intervalToTakePicture=" + this.intervalToTakePicture);
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        int i = message.what;
        if (i == 100) {
            SDKLog.b(TAG, "延时摄影样片下载成功2222");
            judgeDownloadTimeLapseDemo();
        } else if (i != 30000) {
            switch (i) {
                case CommandTreatment.SET_TIMELAPSE_RESULT:
                    int i2 = message.arg1;
                    int i3 = message.arg2;
                    String str = TAG;
                    SDKLog.e(str, "设置延时摄影的结果taskId=" + i2 + ",result=" + i3 + ",isManualShootFinish=" + this.isManualShootFinish);
                    if (i3 == 1) {
                        if (this.isManualShootFinish) {
                            timeLapseFinishAnimation();
                            this.tv_timelapse_start.setText(getString(R.string.timelapse_start));
                            this.isManualShootFinish = false;
                        } else {
                            this.currentTimelapseTask = this.newTimelapseTask;
                            refreshState();
                            createStartTimeLapse();
                            if (this.isCancleTimeLapse) {
                                this.isScheduledShoot = false;
                                this.isCancleTimeLapse = false;
                            }
                        }
                        if (this.isStopAndSave) {
                            SDKLog.e(TAG, "停止延时摄影并保存");
                            refreshState(true);
                            this.fl_timelaps_demo.setVisibility(0);
                            try {
                                this.newTimelapseTask.createLocalFolder(true, true, this.mCameraDevice);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            try {
                                this.newTimelapseTask.reWriteEndtime(System.currentTimeMillis() / 1000);
                                return;
                            } catch (Exception e2) {
                                e2.printStackTrace();
                                return;
                            }
                        } else {
                            SDKLog.e(TAG, "创建新的延时摄影");
                            try {
                                this.newTimelapseTask.createLocalFolder(true, true, this.mCameraDevice);
                                return;
                            } catch (Exception e3) {
                                e3.printStackTrace();
                                return;
                            }
                        }
                    } else if (i3 == 3) {
                        this.fl_timelaps_demo.setVisibility(0);
                        ToastUtil.a((Context) activity(), (CharSequence) getString(R.string.sdcard_available));
                        this.isManualShootFinish = false;
                        this.iv_shoot_start.setImageResource(R.drawable.icon_timelapse_shoot_finish);
                        refreshState(false);
                        return;
                    } else if (i3 == 2) {
                        this.fl_timelaps_demo.setVisibility(0);
                        ToastUtil.a((Context) activity(), (CharSequence) getString(R.string.create_timelapse_photography_failed));
                        this.isManualShootFinish = false;
                        this.iv_shoot_start.setImageResource(R.drawable.icon_timelapse_shoot_finish);
                        refreshState(false);
                        return;
                    } else {
                        return;
                    }
                case CommandTreatment.RES_TIMELAPSE_STATUS:
                    String str2 = TAG;
                    SDKLog.e(str2, "获取当前延时摄影的状态=" + message.arg1);
                    if (message.arg1 == 1) {
                        this.currentTimelapseTask = TimelapseTask.getFromProtocol(this.mCameraDevice, (byte[]) message.obj);
                        String str3 = TAG;
                        SDKLog.e(str3, "newTimelapseTask=" + this.currentTimelapseTask);
                        int taskID = this.currentTimelapseTask.getTaskID();
                        String str4 = TAG;
                        SDKLog.e(str4, "taskid = " + taskID);
                        String str5 = TAG;
                        SDKLog.e(str5, "StartTimestampInSeconds = " + this.currentTimelapseTask.getStartTimestampInSeconds());
                        String str6 = TAG;
                        SDKLog.e(str6, "EndTimestampInSeconds = " + this.currentTimelapseTask.getEndTimestampInSeconds());
                        String str7 = TAG;
                        SDKLog.e(str7, "IntervalToTakePicture = " + this.currentTimelapseTask.getIntervalToTakePicture());
                        String str8 = TAG;
                        SDKLog.e(str8, "TimezoneInMinutes = " + this.currentTimelapseTask.getTimezoneInMinutes());
                        String str9 = TAG;
                        SDKLog.e(str9, "currentSecond = " + currentSecond());
                        if (taskID > 0) {
                            this.isShootTask = true;
                            if (this.currentTimelapseTask.getStartTimestampInSeconds() <= currentSecond()) {
                                SDKLog.e(TAG, "已经开始了任务");
                                setTimelapseIsRecordingUI();
                            } else {
                                SDKLog.e(TAG, "延时任务将要拍摄");
                                setTimelapseIsNotRecording();
                                refreshState(false);
                            }
                            this.fl_timelaps_demo.setVisibility(8);
                            return;
                        }
                        this.isShootTask = false;
                        this.fl_timelaps_demo.setVisibility(0);
                        if (NetworkUtil.c(activity())) {
                            SDKLog.e(TAG, "用的流量");
                            this.fl_timelaps_demo.setVisibility(8);
                            return;
                        }
                        SDKLog.e(TAG, "用的wifi");
                        this.fl_timelaps_demo.setVisibility(0);
                        return;
                    }
                    return;
                default:
                    return;
            }
        } else {
            SDKLog.e(TAG, "保存图片成功");
        }
    }

    private void refreshState() {
        String str = TAG;
        SDKLog.e(str, "refreshState  将于什么时候停止 拍摄currentHour=" + this.currentHour);
        if (!this.isScheduledShoot) {
            TextView textView = this.tv_timelapse_start;
            textView.setText(getString(R.string.pre_desc) + this.currentHour + getString(R.string.hour) + getString(R.string.finish_desc));
        }
    }

    private int hourToInt(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        if (!str.startsWith("0") || str.length() <= 1) {
            return Integer.parseInt(str);
        }
        String substring = str.substring(1);
        String str2 = TAG;
        SDKLog.e(str2, "newHour=" + substring);
        return Integer.parseInt(substring);
    }

    private int minToInt(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        if (!str.startsWith("0") || str.length() <= 1) {
            return Integer.parseInt(str);
        }
        String substring = str.substring(1);
        String str2 = TAG;
        SDKLog.e(str2, "newMin=" + substring);
        return Integer.parseInt(str);
    }

    private void scheduldShootDialog(String str) {
        new ScheduldShootDialog.Builder(activity(), true).setTitle(str).setConfirmListener(new ScheduldShootDialog.ConfirmListener() {
            public void onConfirm(ScheduldShootDialog scheduldShootDialog) {
                String access$500 = TimeLapsePhotographyActivity.TAG;
                SDKLog.e(access$500, "scheduldShootDialog===" + TimeLapsePhotographyActivity.this.isScheduledShoot);
                if (TimeLapsePhotographyActivity.this.isScheduledShoot) {
                    TimeLapsePhotographyActivity.this.closeTimelapseTask();
                    TimeLapsePhotographyActivity.this.tv_timelapse_start.setText(TimeLapsePhotographyActivity.this.getString(R.string.timelapse_start));
                    boolean unused = TimeLapsePhotographyActivity.this.isManualShootFinish = false;
                    boolean unused2 = TimeLapsePhotographyActivity.this.isCancleTimeLapse = true;
                    return;
                }
                boolean unused3 = TimeLapsePhotographyActivity.this.isManualShootFinish = true;
                TimeLapsePhotographyActivity.this.iv_shoot_start.setImageResource(R.drawable.icon_timelapse_shoot_start);
                TimeLapsePhotographyActivity.this.refreshState(true);
                TimeLapsePhotographyActivity.this.closeTimelapseTask();
            }
        }).build().show();
    }

    private void createStartTimeLapse() {
        this.mVideoView.snap(new XmVideoViewGl.PhotoSnapCallback() {
            public void onSnap(final Bitmap bitmap) {
                TimeLapsePhotographyActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        TimeLapsePhotographyActivity.this.onSnapShotTimeLapse(bitmap);
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void onSnapShotTimeLapse(Bitmap bitmap) {
        Bitmap a2;
        if (this.currentTimelapseTask != null) {
            String c = FileUtil.c(this.mCameraDevice.getDid(), this.currentTimelapseTask.getTaskID(), this.currentTimelapseTask.getTaskName());
            String str = TAG;
            SDKLog.e(str, "延时摄影创建成功截图 onSnapShotTimeLapse ===" + c);
            if (c != null && bitmap != null && (a2 = BitmapUtils.a(bitmap, activity())) != null) {
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(c);
                    a2.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                    fileOutputStream.close();
                } catch (IOException unused) {
                }
            }
        }
    }

    public int currentSecond() {
        return (int) ((System.currentTimeMillis() / 1000) + ((long) (this.currentTimelapseTask.getTimezoneInMinutes() * 60)));
    }

    private void setTimelapseIsRecordingUI() {
        this.iv_shoot_start.setImageResource(R.drawable.icon_timelapse_shoot_finish);
        refreshState(false);
        this.isStartShoot = true;
        int endTimestampInSeconds = this.currentTimelapseTask.getEndTimestampInSeconds() - currentSecond();
        int i = endTimestampInSeconds / 3600;
        int i2 = (endTimestampInSeconds - (i * 3600)) / 60;
        String str = TAG;
        SDKLog.e(str, "setTimelapseIsRecordingUI minute==" + i2 + ",hour=" + i + ",diff=" + endTimestampInSeconds);
        TextView textView = this.tv_timelapse_start;
        textView.setText(getString(R.string.pre_desc) + i + getString(R.string.hour) + i2 + getString(R.string.min_by) + getString(R.string.finish_desc));
    }

    private void setTimelapseIsNotRecording() {
        this.iv_shoot_start.setImageResource(R.drawable.icon_timelapse_shoot_start);
        int startTimestampInSeconds = this.currentTimelapseTask.getStartTimestampInSeconds() - currentSecond();
        int i = startTimestampInSeconds / 3600;
        int i2 = (startTimestampInSeconds - (i * 3600)) / 60;
        String str = TAG;
        SDKLog.e(str, "setTimelapseIsNotRecording minute==" + i2 + ",hour=" + i + ",diff=" + startTimestampInSeconds);
        this.isScheduledShoot = true;
        this.isStartShoot = true;
        TextView textView = this.tv_timelapse_start;
        textView.setText(getString(R.string.pre_desc) + i + getString(R.string.hour) + i2 + getString(R.string.min_by) + getString(R.string.after_desc));
    }

    /* access modifiers changed from: private */
    public void scheduledTimeLapsePhotography(String str, String str2, String str3) {
        this.isScheduledShoot = true;
        this.startTimeHour = str;
        this.startTimeMin = str2;
        this.startTimeDay = str3;
        this.scheduledHour = hourToInt(str);
        this.scheduledMine = minToInt(str2);
        String str4 = TAG;
        SDKLog.e(str4, "intHour=" + this.scheduledHour + ",intMin=" + this.scheduledMine);
        if (getString(R.string.sleep_auto_times_today).equals(str3)) {
            this.isTomorrow = false;
        } else {
            this.isTomorrow = true;
        }
        this.scheduledShootCalendar.set(11, this.scheduledHour);
        this.scheduledShootCalendar.set(12, this.scheduledMine);
        this.currentCalendar.setTimeInMillis(System.currentTimeMillis());
        int i = this.currentCalendar.get(11);
        int i2 = this.currentCalendar.get(12);
        String str5 = TAG;
        SDKLog.e(str5, "当前的小时 currentHour=" + i + ",当前分钟currentMinute=" + i2);
        if (this.isTomorrow) {
            int i3 = (((this.scheduledHour * 60) + this.scheduledMine) + 1440) - ((i * 60) + i2);
            String str6 = TAG;
            SDKLog.e(str6, "明天 diff==" + i3);
            this.diffHour = i3 / 60;
            this.diffMin = i3 - (this.diffHour * 60);
        } else {
            int i4 = ((this.scheduledHour * 60) + this.scheduledMine) - ((i * 60) + i2);
            String str7 = TAG;
            SDKLog.e(str7, "今天 diff==" + i4);
            this.diffHour = i4 / 60;
            this.diffMin = i4 - (this.diffHour * 60);
        }
        if (this.diffHour < 0 || this.diffMin < 0) {
            ToastUtil.a((Context) activity(), (CharSequence) getString(R.string.timelapse_shoot_tip));
            return;
        }
        TextView textView = this.tv_timelapse_start;
        textView.setText(getString(R.string.pre_desc) + this.diffHour + getString(R.string.hour) + this.diffMin + getString(R.string.min_by) + getString(R.string.after_desc));
        scheduledCreateTimelapseTask();
        this.isManualShootFinish = false;
        this.isStartShoot = true;
        refreshState(false);
    }

    public void onPause() {
        super.onPause();
    }

    public void onStop() {
        if (this.isClickAlbum) {
            this.isTimeLapsePhotography = false;
        } else {
            this.isTimeLapsePhotography = true;
        }
        this.isClickAlbum = false;
        this.mLLFuncPopup.setVisibility(8);
        super.onStop();
    }

    public void onConnected() {
        super.onConnected();
        SDKLog.e(TAG, "onConnected  。。。。");
        if (this.mPauseView != null && this.mPauseView.getVisibility() == 0) {
            this.mPauseView.setVisibility(8);
        }
        this.iv_shoot_start.setEnabled(true);
        this.iv_shoot_start.setAlpha(1.0f);
        if (this.mCameraPlayerEx != null) {
            SDKLog.e(TAG, "连接摄像头成功,开始发送查询当前延时摄影状态信息");
            this.mCameraPlayerEx.A();
            this.mCameraPlayerEx.z();
            this.mTimeLapseTaskManager.getTimeLapseTask(this);
        }
    }

    public void onVideoPlayError(int i, String str) {
        super.onVideoPlayError(i, str);
        SDKLog.e(TAG, "摄像机连接失败");
        refreshState(false);
    }

    /* access modifiers changed from: private */
    public void toggleRemoteAV(boolean z, boolean z2) {
        if (this.mCameraPlayerEx != null) {
            this.mCameraPlayerEx.a(z, (IMISSListener) null);
            this.mCameraPlayerEx.b(z2, (IMISSListener) null);
        }
    }

    public JSONArray listToArray(int i) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("mode", i);
        jSONArray.put(jSONObject);
        return jSONArray;
    }

    /* access modifiers changed from: private */
    public void setTimeLapseMode(int i) {
        String str = TAG;
        SDKLog.e(str, "setTimeLapseMode = " + i);
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("timeLapseMode", listToArray(i).toString());
            jSONObject.put("did", this.mCameraDevice.getDid());
            jSONObject.put("settings", jSONObject2);
            String str2 = TAG;
            SDKLog.e(str2, "jsonObject=" + jSONObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        XmPluginHostApi.instance().callSmartHomeApi(this.mCameraDevice.getModel(), "/device/setsetting", jSONObject, new Callback<JSONObject>() {
            public void onSuccess(JSONObject jSONObject) {
                String access$500 = TimeLapsePhotographyActivity.TAG;
                SDKLog.e(access$500, "setTimeLapseMode onSuccess=" + jSONObject.toString());
            }

            public void onFailure(int i, String str) {
                String access$500 = TimeLapsePhotographyActivity.TAG;
                SDKLog.e(access$500, "setTimeLapseMode onFailure i=" + i + ",s=" + str);
            }
        }, Parser.DEFAULT_PARSER);
    }

    private void getTimeLapseMode() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", this.mCameraDevice.getDid());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        XmPluginHostApi.instance().callSmartHomeApi(this.mCameraDevice.getModel(), "/device/getsetting", jSONObject, new Callback<JSONObject>() {
            public void onSuccess(JSONObject jSONObject) {
                String access$500 = TimeLapsePhotographyActivity.TAG;
                SDKLog.e(access$500, "getTimeLapseMode onSuccess=" + jSONObject.toString());
                if (jSONObject != null) {
                    try {
                        String str = (String) jSONObject.opt("timeLapseMode");
                        if (!TextUtils.isEmpty(str)) {
                            String access$5002 = TimeLapsePhotographyActivity.TAG;
                            SDKLog.e(access$5002, "getTimeLapseMode onSuccess json =" + str);
                            JSONArray jSONArray = new JSONArray(str);
                            String access$5003 = TimeLapsePhotographyActivity.TAG;
                            SDKLog.e(access$5003, "getTimeLapseMode onSuccess jsonArray=" + jSONArray.length());
                            if (jSONArray.length() > 0) {
                                int optInt = jSONArray.getJSONObject(0).optInt("mode");
                                String access$5004 = TimeLapsePhotographyActivity.TAG;
                                SDKLog.e(access$5004, "getTimeLapseMode onSuccess mode=" + optInt);
                                TimeLapsePhotographyActivity.this.refresh(optInt);
                                return;
                            }
                            TimeLapsePhotographyActivity.this.mTvShootingMode.setCurrentState(2);
                            TimeLapsePhotographyActivity.this.tv_mode_hour.setText(TimeLapsePhotographyActivity.this.getText(R.string.timelapse_shoot_fives_hour_desc));
                            TimeLapsePhotographyActivity.this.calcuInterval(5);
                            int unused = TimeLapsePhotographyActivity.this.currentHour = 5;
                            TimeLapsePhotographyActivity.this.setTimeLapseMode(2);
                            return;
                        }
                        TimeLapsePhotographyActivity.this.mTvShootingMode.setCurrentState(2);
                        TimeLapsePhotographyActivity.this.tv_mode_hour.setText(TimeLapsePhotographyActivity.this.getText(R.string.timelapse_shoot_fives_hour_desc));
                        TimeLapsePhotographyActivity.this.calcuInterval(5);
                        int unused2 = TimeLapsePhotographyActivity.this.currentHour = 5;
                        TimeLapsePhotographyActivity.this.setTimeLapseMode(2);
                    } catch (Exception e) {
                        e.printStackTrace();
                        SDKLog.e(TimeLapsePhotographyActivity.TAG, "获取数据异常");
                        TimeLapsePhotographyActivity.this.mTvShootingMode.setCurrentState(2);
                        TimeLapsePhotographyActivity.this.tv_mode_hour.setText(TimeLapsePhotographyActivity.this.getText(R.string.timelapse_shoot_fives_hour_desc));
                        TimeLapsePhotographyActivity.this.calcuInterval(5);
                        int unused3 = TimeLapsePhotographyActivity.this.currentHour = 5;
                        TimeLapsePhotographyActivity.this.setTimeLapseMode(2);
                    }
                } else {
                    TimeLapsePhotographyActivity.this.mTvShootingMode.setCurrentState(2);
                    TimeLapsePhotographyActivity.this.tv_mode_hour.setText(TimeLapsePhotographyActivity.this.getText(R.string.timelapse_shoot_fives_hour_desc));
                    TimeLapsePhotographyActivity.this.calcuInterval(5);
                    int unused4 = TimeLapsePhotographyActivity.this.currentHour = 5;
                    TimeLapsePhotographyActivity.this.setTimeLapseMode(2);
                }
            }

            public void onFailure(int i, String str) {
                String access$500 = TimeLapsePhotographyActivity.TAG;
                SDKLog.e(access$500, "getTimeLapseMode onFailure i=" + i + ",s=" + str);
                TimeLapsePhotographyActivity.this.mTvShootingMode.setCurrentState(2);
                TimeLapsePhotographyActivity.this.tv_mode_hour.setText(TimeLapsePhotographyActivity.this.getText(R.string.timelapse_shoot_fives_hour_desc));
                TimeLapsePhotographyActivity.this.calcuInterval(5);
                int unused = TimeLapsePhotographyActivity.this.currentHour = 5;
                TimeLapsePhotographyActivity.this.setTimeLapseMode(2);
            }
        }, Parser.DEFAULT_PARSER);
    }

    public void onResume() {
        super.onResume();
        if (this.mCameraPlayerEx != null) {
            this.mCameraPlayerEx.w();
            this.mCameraPlayerEx = null;
        }
        this.mCameraPlayerEx = new CameraPlayerEx(this, this.mCameraDevice, this, this.mVideoView);
        getTimeLapseMode();
        startPlay();
    }

    /* access modifiers changed from: private */
    public void refresh(int i) {
        String str = TAG;
        SDKLog.e(str, "refresh mode=" + i);
        switch (i) {
            case 0:
                this.shootDurSecond = 3600;
                calcuInterval(1);
                this.currentHour = 1;
                this.mTvShootingMode.setCurrentState(0);
                this.tv_mode_hour.setText(getText(R.string.timelapse_shoot_one_hour_desc));
                setTimeLapseMode(0);
                return;
            case 1:
                this.shootDurSecond = 10800;
                calcuInterval(3);
                this.currentHour = 3;
                this.mTvShootingMode.setCurrentState(1);
                this.tv_mode_hour.setText(getText(R.string.timelapse_shoot_three_hour_desc));
                setTimeLapseMode(1);
                return;
            case 2:
                this.shootDurSecond = 18000;
                calcuInterval(5);
                this.currentHour = 5;
                this.mTvShootingMode.setCurrentState(2);
                this.tv_mode_hour.setText(getText(R.string.timelapse_shoot_fives_hour_desc));
                setTimeLapseMode(2);
                return;
            default:
                return;
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == TIMELAPSE_PHOTOGRAPHY) {
            SDKLog.e(TAG, "onActivityResult ......startPlay");
            startPlay();
        }
    }

    public void onSendCmdError() {
        SDKLog.e(TAG, "onSendCmdError");
        ToastUtil.a((Context) activity(), (CharSequence) getString(R.string.operation_failed));
        this.mHandler.post(new Runnable() {
            public void run() {
                boolean unused = TimeLapsePhotographyActivity.this.isStartShoot = false;
                TimeLapsePhotographyActivity.this.refreshState(false);
                TimeLapsePhotographyActivity.this.iv_shoot_start.setImageResource(R.drawable.icon_timelapse_shoot_start);
            }
        });
    }

    private void judgeDownloadTimeLapseDemo() {
        File file = new File(getDownloadFilePath());
        String str = TAG;
        SDKLog.b(str, "judgeDownloadTimeLapseDemo file=" + file.getAbsolutePath());
        if (!file.exists()) {
            SDKLog.e(TAG, "不存在开始下载");
            DownLoadTimeLapseDemo.getInstance(activity(), this.mCameraDevice.getDid(), this.mHandler).downLoadFile();
            return;
        }
        SDKLog.e(TAG, "存在开始显示");
        Glide.a(activity()).k().a(file).b((BaseRequestOptions<?>) (RequestOptions) ((RequestOptions) ((RequestOptions) new RequestOptions().c(this.iv_timelaps_demo.getDrawable())).e(this.iv_timelaps_demo.getDrawable())).a(DiskCacheStrategy.b)).a(this.iv_timelaps_demo);
    }

    private String getDownloadFilePath() {
        String d = FileUtil.d();
        if (TextUtils.isEmpty(d)) {
            return d;
        }
        File file = new File(d);
        if (!file.exists()) {
            file.mkdirs();
        }
        return d + File.separator + "timelaps_demo.gif";
    }
}
