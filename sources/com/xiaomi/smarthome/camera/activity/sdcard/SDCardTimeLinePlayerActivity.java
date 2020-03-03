package com.xiaomi.smarthome.camera.activity.sdcard;

import android.animation.ObjectAnimator;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.Utils.FileUtil;
import com.Utils.NetworkUtil;
import com.Utils.TimeUtils;
import com.mijia.app.Event;
import com.mijia.camera.CameraPlayer;
import com.mijia.camera.CameraProperties;
import com.mijia.camera.Utils.BitmapUtils;
import com.mijia.camera.Utils.Util;
import com.mijia.debug.SDKLog;
import com.mijia.debug.Tag;
import com.mijia.model.local.LocalFileManager;
import com.mijia.model.sdcard.DownloadSdCardManager;
import com.mijia.model.sdcard.SdcardManager;
import com.mijia.model.sdcard.TimeItem;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.XmVideoViewGl;
import com.xiaomi.smarthome.camera.activity.CameraPlayerBaseActivity;
import com.xiaomi.smarthome.camera.activity.local.LocalPicReviewActivity;
import com.xiaomi.smarthome.camera.view.TextViewS;
import com.xiaomi.smarthome.camera.view.timeline.TimeLineControlView;
import com.xiaomi.smarthome.camera.view.timeline.TimeLineWithDatePickView;
import com.xiaomi.smarthome.camera.view.widget.CenterDrawableCheckBox;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.download.Downloads;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SDCardTimeLinePlayerActivity extends CameraPlayerBaseActivity {
    static final int MSG_UPDATE_SEEK_PROGRESS = 1001;
    private static final String TAG = "SDCardPlayerActivity";
    private int UPDATE_DURATION = 500;
    private CenterDrawableCheckBox cdcToggleAudio;
    private ImageView cdcTogglePlay;
    boolean isFirst = true;
    private boolean isInEnding = false;
    private boolean isOnFileEnd;
    boolean isVideoPlaying = true;
    private ImageView ivCameraShot;
    private ImageView ivFullScreen;
    /* access modifiers changed from: private */
    public LinearLayout llFuncPopup;
    /* access modifiers changed from: private */
    public LinearLayout llVideoViewBottomCtrl;
    BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (SDCardTimeLinePlayerActivity.this.mCameraDevice.c().f().equals(intent.getAction()) && !SDCardTimeLinePlayerActivity.this.isFinishing()) {
                SDCardTimeLinePlayerActivity.this.mTimeLineControlView.setTimeItems(SDCardTimeLinePlayerActivity.this.mCameraDevice.c().i());
                SDCardTimeLinePlayerActivity.this.mHandler.post(new Runnable() {
                    public void run() {
                        if (SDCardTimeLinePlayerActivity.this.isFirst) {
                            SDCardTimeLinePlayerActivity.this.startPlay();
                        }
                    }
                });
            }
        }
    };
    private SdcardManager mCameraSdcardFileManager;
    private Date mDate;
    private int mDay;
    private DownloadSdCardManager mDownloadSdCardFileManager;
    private int mDuration;
    private int mEndTime;
    private FrameLayout mFLTitleBar;
    private int mHour;
    private boolean mIsSetPlayTime;
    private boolean mIsSetTime = false;
    private int mLastOffsetTime = 0;
    private long mLastSetPlayTime;
    private int mLastSetStart;
    private long mLastSetTime = 0;
    private long mLastUpdateTime = 0;
    private LocalBroadcastManager mLocalBroadcastManager;
    private boolean mNeedSetTime = false;
    private View mPlayerHint1;
    private View mPlayerHint2;
    private View mSeeAllVieo;
    public boolean mSeekBarTouched = false;
    private int mSelectTime;
    SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("mm:ss");
    private int mStartTime;
    private TimeLineControlView.TimeLineCallback mTimeCallBack = new TimeLineControlView.TimeLineCallback() {
        public void onUpdateTime(long j) {
            SDCardTimeLinePlayerActivity.this.mTimeUpdateView.setText(TimeUtils.a(j));
            if (SDCardTimeLinePlayerActivity.this.mNeedSpeed) {
                SDCardTimeLinePlayerActivity.this.mHandler.removeMessages(4000);
                boolean unused = SDCardTimeLinePlayerActivity.this.mNeedSpeed = false;
            }
            if (SDCardTimeLinePlayerActivity.this.mTimeUpdateView.getVisibility() != 0) {
                SDCardTimeLinePlayerActivity.this.mTimeUpdateView.setVisibility(0);
            }
        }

        public void onSelectTime(long j) {
            if (j != 0) {
                SDCardTimeLinePlayerActivity.this.mHandler.removeMessages(2);
            }
            SDCardTimeLinePlayerActivity.this.setPlayTime(j, true);
            SDCardTimeLinePlayerActivity.this.mTimeUpdateView.setVisibility(8);
        }

        public void onPlayLive() {
            SDCardTimeLinePlayerActivity.this.toSdFilesEnd();
            SDCardTimeLinePlayerActivity.this.mTimeUpdateView.setVisibility(8);
        }

        public void onCancel() {
            if (SDCardTimeLinePlayerActivity.this.mTimeUpdateView.getVisibility() == 0) {
                SDCardTimeLinePlayerActivity.this.mTimeUpdateView.setVisibility(8);
            }
        }
    };
    private TimeItem mTimeItem;
    /* access modifiers changed from: private */
    public TimeLineWithDatePickView mTimeLineControlView;
    /* access modifiers changed from: private */
    public TextView mTimeUpdateView;
    private SimpleDateFormat sdfHHmm;
    private SimpleDateFormat sdfMMdd;
    private ImageView title_bar_return;
    private TextViewS tvsMultiSpeed;

    /* access modifiers changed from: protected */
    public void detectSDCard() {
    }

    /* access modifiers changed from: protected */
    public void doStopRecord() {
    }

    public boolean isHistory() {
        return true;
    }

    public void doCreate(Bundle bundle) {
        super.doCreate(bundle);
        SDKLog.b(TAG, "onCreate");
        setContentView(R.layout.camera_activity_today_sdcard_player);
        this.mFLTitleBar = (FrameLayout) findViewById(R.id.title_bar);
        this.mFLTitleBar.setBackgroundResource(R.drawable.camera_shape_gradient_bg);
        this.title_bar_return = (ImageView) findViewById(R.id.title_bar_return);
        this.title_bar_return.setOnClickListener(this);
        findViewById(R.id.title_bar_more).setVisibility(8);
        this.mFLTitleBar.bringToFront();
        this.mDate = new Date();
        initView();
        this.mCameraSdcardFileManager = this.mCameraDevice.c();
        this.mDownloadSdCardFileManager = this.mCameraDevice.h();
        this.sdfMMdd = new SimpleDateFormat(getString(R.string.simple_date_format_mm_dd));
        this.sdfMMdd.setTimeZone(TimeUtils.a());
        this.sdfHHmm = new SimpleDateFormat(getString(R.string.simple_date_format_hh_mm));
        this.sdfHHmm.setTimeZone(TimeUtils.a());
        Event.e();
    }

    private void initView() {
        this.ivCameraShot = (ImageView) findViewById(R.id.ivCameraShot);
        this.ivCameraShot.setOnClickListener(this);
        this.llVideoViewBottomCtrl = (LinearLayout) findViewById(R.id.llVideoViewBottomCtrl);
        this.tvsMultiSpeed = (TextViewS) findViewById(R.id.tvsMultiSpeed);
        this.tvsMultiSpeed.setOnClickListener(this);
        this.cdcToggleAudio = (CenterDrawableCheckBox) findViewById(R.id.cdcToggleAudio);
        this.cdcToggleAudio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                SDKLog.b(SDCardTimeLinePlayerActivity.TAG, "cdcToggleAudio isChecked:" + z);
                if (SDCardTimeLinePlayerActivity.this.mCameraPlayer == null) {
                    return;
                }
                if (z) {
                    SDCardTimeLinePlayerActivity.this.mCameraPlayer.b(false);
                } else {
                    SDCardTimeLinePlayerActivity.this.mCameraPlayer.b(true);
                }
            }
        });
        this.cdcTogglePlay = (ImageView) findViewById(R.id.cdcTogglePlay);
        this.cdcTogglePlay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SDCardTimeLinePlayerActivity.this.switchPlayBtnStatus();
            }
        });
        findViewById(R.id.togglePlay).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SDCardTimeLinePlayerActivity.this.switchPlayBtnStatus();
            }
        });
        changePlayBtnBg(this.isVideoPlaying);
        this.ivFullScreen = (ImageView) findViewById(R.id.ivFullScreen);
        this.ivFullScreen.setOnClickListener(this);
        this.llFuncPopup = (LinearLayout) findViewById(R.id.llFuncPopup);
        this.llFuncPopup.setOnClickListener(this);
        this.mTimeUpdateView = (TextView) findViewById(R.id.time_container_center);
        this.mTitleView = (TextView) findViewById(R.id.title_bar_title);
        this.mTitleView.setText(R.string.item_storage);
        this.mTitleView.setTextColor(getResources().getColor(R.color.white));
        this.mVideoViewFrame = (FrameLayout) findViewById(R.id.video_frame);
        this.mVideoLayout = new FrameLayout(activity());
        this.mVideoViewFrame.addView(this.mVideoLayout, 0, new FrameLayout.LayoutParams(-1, -1));
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        this.mLoadingView = LayoutInflater.from(this).inflate(R.layout.camera_progress, (ViewGroup) null);
        this.mVideoLayout.addView(this.mLoadingView, layoutParams);
        this.mLoadingView.setVisibility(8);
        this.mLoadingProgress = (TextView) this.mLoadingView.findViewById(R.id.loading_progress);
        this.mLoadingTitle = (TextView) this.mLoadingView.findViewById(R.id.loading_title);
        this.mLoadingImageView = (ImageView) this.mLoadingView.findViewById(R.id.loading_anim);
        this.mLoadingAnimation = (AnimationDrawable) getResources().getDrawable(R.drawable.camera_anim_camera_loading);
        this.mLoadingImageView.setImageDrawable(this.mLoadingAnimation);
        if (this.isV4) {
            this.mErrorRetryView = LayoutInflater.from(this).inflate(R.layout.camera_v4_error_retry, (ViewGroup) null);
        } else {
            this.mErrorRetryView = LayoutInflater.from(this).inflate(R.layout.camera_error_retry, (ViewGroup) null);
        }
        this.mVideoLayout.addView(this.mErrorRetryView, layoutParams);
        this.mErrorRetryView.setVisibility(8);
        this.mRetryView = this.mErrorRetryView.findViewById(R.id.retry_btn);
        this.mErrorInfoView = (TextView) this.mErrorRetryView.findViewById(R.id.error_info);
        if (this.isV4) {
            this.mPowerOffView = LayoutInflater.from(this).inflate(R.layout.camera_closed_v4, (ViewGroup) null);
        } else {
            this.mPowerOffView = LayoutInflater.from(this).inflate(R.layout.camera_closed, (ViewGroup) null);
        }
        this.mVideoLayout.addView(this.mPowerOffView, layoutParams);
        this.mPowerOffView.setVisibility(8);
        this.mPauseView = findViewById(R.id.pause_view);
        this.mCameraDevice.g().a(this.mPropertyChangeListener);
        this.mCameraDevice.g().a();
        findViewById(R.id.title_bar_return).setOnClickListener(this);
        this.mPauseView.setOnClickListener(this);
        this.mCameraDevice.f().a(CameraProperties.c, (Callback<Void>) new Callback<Void>() {
            public void onFailure(int i, String str) {
            }

            public void onSuccess(Void voidR) {
                SDCardTimeLinePlayerActivity.this.refreshUI();
            }
        });
        initTimeSelectView();
        initOtherView();
        initForFirstEnter();
    }

    private void initForFirstEnter() {
        this.mPlayerHint1.setVisibility(8);
        this.mPlayerHint2.setVisibility(8);
        this.mSeeAllVieo.setVisibility(8);
        this.mTimeLineControlView.setVisibility(8);
        this.llVideoViewBottomCtrl.setVisibility(8);
    }

    private void showHideViews() {
        this.mPlayerHint1.setVisibility(8);
        this.mPlayerHint2.setVisibility(8);
        this.mSeeAllVieo.setVisibility(0);
        this.mTimeLineControlView.setVisibility(0);
        this.llVideoViewBottomCtrl.setVisibility(0);
    }

    /* access modifiers changed from: private */
    public void switchPlayBtnStatus() {
        if (this.isVideoPlaying) {
            this.isVideoPlaying = false;
            pauseCamera();
            this.mUserPause = true;
        } else {
            this.isVideoPlaying = true;
            if (this.mCameraPlayer == null || !this.mCameraPlayer.d()) {
                this.mUserPause = false;
                startPlay();
            } else {
                return;
            }
        }
        changePlayBtnBg(this.isVideoPlaying);
    }

    private void changePlayBtnBg(boolean z) {
        if (z) {
            this.cdcTogglePlay.setImageResource(R.drawable.camera_icon_pause02_nor);
        } else {
            this.cdcTogglePlay.setImageResource(R.drawable.camera_icon_play_nor);
        }
    }

    private void initOtherView() {
        this.mSeeAllVieo = findViewById(R.id.see_all_video);
        this.mPlayerHint1 = findViewById(R.id.play_hint_1);
        this.mPlayerHint2 = findViewById(R.id.play_hint_2);
        this.mSeeAllVieo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SDCardTimeLinePlayerActivity.this.startActivity(new Intent(SDCardTimeLinePlayerActivity.this, SDCardActivity.class));
            }
        });
    }

    private void initTimeSelectView() {
        this.mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
        this.mTimeLineControlView = (TimeLineWithDatePickView) findViewById(R.id.time_line_date_pick);
        this.mTimeLineControlView.synCurrentTime(System.currentTimeMillis());
        this.mTimeLineControlView.setTimeLineCallback(this.mTimeCallBack);
    }

    public void onDestroy() {
        super.onDestroy();
        Event.f();
    }

    public void onResume() {
        this.mVideoView = XmPluginHostApi.instance().createVideoViewOnFront(activity(), this.mVideoLayout, true, 1);
        this.mVideoView.initial();
        this.mVideoView.setVideoViewListener(new XmVideoViewGl.IVideoViewListener() {
            public void onVideoViewClick() {
                if (SDCardTimeLinePlayerActivity.this.llVideoViewBottomCtrl.getTranslationY() > 0.0f) {
                    ObjectAnimator ofFloat = ObjectAnimator.ofFloat(SDCardTimeLinePlayerActivity.this.llVideoViewBottomCtrl, "translationY", new float[]{(float) SDCardTimeLinePlayerActivity.this.llVideoViewBottomCtrl.getHeight(), 0.0f});
                    ofFloat.setDuration(200);
                    ofFloat.start();
                    return;
                }
                ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(SDCardTimeLinePlayerActivity.this.llVideoViewBottomCtrl, "translationY", new float[]{0.0f, (float) SDCardTimeLinePlayerActivity.this.llVideoViewBottomCtrl.getHeight()});
                ofFloat2.setDuration(200);
                ofFloat2.start();
            }
        });
        if (getRequestedOrientation() != 1) {
            this.mFullScreen = true;
            this.mVideoViewFrame.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
            if (this.mVideoView != null) {
                this.mVideoView.setIsFull(true);
            }
            this.mTimeLineControlView.setBackgroundColor(getResources().getColor(R.color.land_timeline_bg));
            this.mSeeAllVieo.setVisibility(8);
            this.mTitleView.setVisibility(8);
        }
        this.mNetworkMonitor.register(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(this.mCameraDevice.c().f());
        this.mLocalBroadcastManager.registerReceiver(this.mBroadcastReceiver, intentFilter);
        this.mCameraDevice.c().j();
        this.mCameraDevice.c().a(40000);
        this.mTimeLineControlView.setTimeItems(this.mCameraDevice.c().i());
        super.onResume();
        showLoading();
        if (this.mCameraPlayer != null) {
            this.mCameraPlayer.b(true);
            TextViewS textViewS = this.tvsMultiSpeed;
            textViewS.setText("" + this.mCameraPlayer.q() + "X");
        } else {
            this.tvsMultiSpeed.setText("1X");
        }
        if (!this.isFirst) {
            startPlay();
        }
    }

    public void onPause() {
        if (this.mCameraPlayer != null) {
            this.mCameraPlayer.r();
            this.mCameraPlayer = null;
        }
        if (this.mVideoView != null) {
            this.mVideoView.releaseOnlySelf();
            this.mVideoView = null;
        }
        if (this.mNetworkMonitor != null) {
            this.mNetworkMonitor.unregister();
        }
        this.mLocalBroadcastManager.unregisterReceiver(this.mBroadcastReceiver);
        this.mCameraDevice.d().b();
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void doResume() {
        if (this.mUserPause && !this.isVideoPlaying) {
            return;
        }
        if (getEndTimeOfLastVideo() == 0) {
            pauseCamera();
        } else {
            setPlayTime(this.mTimeLineControlView.getSelectTime(), false);
        }
    }

    /* access modifiers changed from: protected */
    public void refreshUI() {
        if (this.mCameraPlayer == null || this.mCameraPlayer.p()) {
            this.cdcToggleAudio.setChecked(false);
        } else {
            this.cdcToggleAudio.setChecked(true);
        }
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        int i = message.what;
        if (i == 2) {
            StringBuilder sb = new StringBuilder();
            sb.append("MSG_UPDATE_PLAY_TIME:");
            sb.append(this.mCameraPlayer == null ? "null" : "not null");
            LogUtil.a(TAG, sb.toString());
            if (this.mCameraPlayer != null) {
                long b = this.mCameraPlayer.b();
                LogUtil.a(TAG, "current play timestamp:" + b);
                if (this.mLastUpdateTime == b || this.mSeekBarTouched || !this.mCameraPlayer.d()) {
                    this.mHandler.removeMessages(2);
                    this.mHandler.sendEmptyMessageDelayed(2, (long) this.UPDATE_DURATION);
                    return;
                }
                if (b >= 0) {
                    setIsConnected();
                }
                if (getEndTimeOfLastVideo() == 0 || getEndTimeOfLastVideo() - (b * 1000) >= ((long) this.UPDATE_DURATION)) {
                    this.mLastUpdateTime = b;
                    int i2 = (int) (b - (this.mTimeItem.f8098a / 1000));
                    if (this.mIsSetTime) {
                        if (Math.abs(this.mLastOffsetTime - i2) <= 3 || System.currentTimeMillis() - this.mLastSetTime > 6000) {
                            this.mIsSetTime = false;
                            hideLoading();
                        } else {
                            this.mHandler.removeMessages(2);
                            this.mHandler.sendEmptyMessageDelayed(2, (long) this.UPDATE_DURATION);
                            return;
                        }
                    }
                    this.mHandler.removeMessages(2);
                    this.mHandler.sendEmptyMessageDelayed(2, (long) this.UPDATE_DURATION);
                    if (this.mTimeLineControlView.getIsPress()) {
                        return;
                    }
                    if (!this.mIsSetPlayTime) {
                        this.mTimeLineControlView.updatePlayTime(b * 1000, false);
                    } else if (Math.abs(((long) this.mSelectTime) - b) < 10 || System.currentTimeMillis() - this.mLastSetPlayTime > 10000) {
                        this.mIsSetPlayTime = false;
                        this.mTimeLineControlView.updatePlayTime(b * 1000, false);
                        hideLoading();
                        SDKLog.b(Tag.b, "update " + Math.abs(((long) this.mSelectTime) - b) + "  " + ((System.currentTimeMillis() - this.mLastSetPlayTime) / 1000));
                    }
                } else if (!this.isInEnding) {
                    toSdFilesEnd();
                }
            }
        } else if (i == 4000) {
            this.mNeedSpeed = false;
            this.mTimeUpdateView.setVisibility(8);
        }
    }

    /* access modifiers changed from: private */
    public void toSdFilesEnd() {
        List<TimeItem> i = this.mCameraSdcardFileManager.i();
        if (i == null || i.size() <= 0) {
            initOtherView();
            return;
        }
        this.mTimeLineControlView.updatePlayTime(getEndTimeOfLastVideo(), false);
        pauseCamera();
        this.isOnFileEnd = true;
    }

    private long getEndTimeOfLastVideo() {
        List<TimeItem> i = this.mCameraSdcardFileManager.i();
        if (i == null || i.size() == 0) {
            return 0;
        }
        return i.get(i.size() - 1).c;
    }

    /* access modifiers changed from: protected */
    public void startPlay() {
        if (TextUtils.isEmpty(this.mCameraDevice.r()) || TextUtils.isEmpty(this.mCameraDevice.w())) {
            hideLoading();
            showError(getString(R.string.camera_play_error2));
            return;
        }
        if (this.mCameraPlayer == null) {
            this.mCameraPlayer = new CameraPlayer(this, this.mCameraDevice, this, this.mVideoView);
            this.mCameraPlayer.a();
        }
        if (!NetworkUtil.c(activity()) || this.mAllowMobileNetwork) {
            startPlayVideo();
        } else {
            pauseCamera();
        }
    }

    /* access modifiers changed from: protected */
    public void resumeCamera() {
        this.mPauseView.setVisibility(8);
        if (NetworkUtil.c(activity())) {
            this.mAllowMobileNetwork = true;
        }
        refreshUI();
        this.mHandler.removeMessages(2);
        this.mHandler.sendEmptyMessageDelayed(2, (long) this.UPDATE_DURATION);
        this.isVideoPlaying = true;
        changePlayBtnBg(true);
        startPlay();
    }

    private void startPlayVideo() {
        if (!this.isFirst && this.mTimeLineControlView.getSelectTime() < getEndTimeOfLastVideo()) {
            setPlayTime(this.mTimeLineControlView.getSelectTime(), false);
        } else if (this.mCameraDevice.c().i() != null && this.mCameraDevice.c().i().size() != 0) {
            if (this.isFirst) {
                showHideViews();
            }
            this.isFirst = false;
            TimeItem timeItem = this.mCameraSdcardFileManager.i().get(this.mCameraSdcardFileManager.i().size() - 1);
            if (timeItem != null) {
                setPlayTime(timeItem.f8098a, false);
            }
        }
    }

    public void onClick(View view) {
        doOnClick(view);
        int id = view.getId();
        if (id == R.id.ivCameraShot) {
            snapShot();
        } else if (id != R.id.ivFullScreen) {
            if (id == R.id.tvsMultiSpeed) {
                toggleSpeed();
            }
        } else if (1 == getRequestedOrientation()) {
            setOrientation(true);
        } else {
            setOrientation(false);
        }
    }

    public void onBackPressed() {
        if (this.mFullScreen) {
            runOnUiThread(new Runnable() {
                public void run() {
                    SDCardTimeLinePlayerActivity.this.setOrientation(false);
                }
            });
        } else {
            super.onBackPressed();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x00a1 A[Catch:{ Exception -> 0x00fe }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00a2 A[Catch:{ Exception -> 0x00fe }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00b8 A[Catch:{ Exception -> 0x00fe }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00c3 A[Catch:{ Exception -> 0x00fe }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00d4 A[Catch:{ Exception -> 0x00fe }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onServerCmd(int r6, byte[] r7) {
        /*
            r5 = this;
            boolean r0 = r5.isFinishing()
            if (r0 == 0) goto L_0x0007
            return
        L_0x0007:
            r0 = 61442(0xf002, float:8.6099E-41)
            if (r6 != r0) goto L_0x0108
            java.lang.String r6 = "CameraPlay"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "onServerCmd thread "
            r0.append(r1)
            java.lang.Thread r1 = java.lang.Thread.currentThread()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.mijia.debug.SDKLog.d(r6, r0)
            java.lang.String r6 = new java.lang.String     // Catch:{ Exception -> 0x00fe }
            r6.<init>(r7)     // Catch:{ Exception -> 0x00fe }
            org.json.JSONObject r7 = new org.json.JSONObject     // Catch:{ Exception -> 0x00fe }
            r7.<init>(r6)     // Catch:{ Exception -> 0x00fe }
            java.lang.String r0 = "id"
            r1 = -1
            int r0 = r7.optInt(r0, r1)     // Catch:{ Exception -> 0x00fe }
            java.lang.String r2 = "CameraPlay"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00fe }
            r3.<init>()     // Catch:{ Exception -> 0x00fe }
            java.lang.String r4 = "id "
            r3.append(r4)     // Catch:{ Exception -> 0x00fe }
            r3.append(r6)     // Catch:{ Exception -> 0x00fe }
            java.lang.String r6 = r3.toString()     // Catch:{ Exception -> 0x00fe }
            com.mijia.debug.SDKLog.d(r2, r6)     // Catch:{ Exception -> 0x00fe }
            int r6 = r5.mStartTime     // Catch:{ Exception -> 0x00fe }
            if (r0 != r6) goto L_0x0108
            java.lang.String r6 = "status"
            r0 = 0
            java.lang.String r6 = r7.optString(r6, r0)     // Catch:{ Exception -> 0x00fe }
            if (r6 == 0) goto L_0x0108
            int r0 = r6.hashCode()     // Catch:{ Exception -> 0x00fe }
            r2 = -1897432978(0xffffffff8ee7786e, float:-5.7061866E-30)
            r3 = 0
            r4 = 2
            if (r0 == r2) goto L_0x0093
            r2 = -1309954170(0xffffffffb1ebaf86, float:-6.859355E-9)
            if (r0 == r2) goto L_0x0089
            r2 = -1107950030(0xffffffffbdf60632, float:-0.120129004)
            if (r0 == r2) goto L_0x007f
            r2 = 250180107(0xee9720b, float:5.7548754E-30)
            if (r0 == r2) goto L_0x0075
            goto L_0x009d
        L_0x0075:
            java.lang.String r0 = "filenotfound"
            boolean r0 = r6.equals(r0)     // Catch:{ Exception -> 0x00fe }
            if (r0 == 0) goto L_0x009d
            r0 = 1
            goto L_0x009e
        L_0x007f:
            java.lang.String r0 = "readerror"
            boolean r0 = r6.equals(r0)     // Catch:{ Exception -> 0x00fe }
            if (r0 == 0) goto L_0x009d
            r0 = 3
            goto L_0x009e
        L_0x0089:
            java.lang.String r0 = "filefound"
            boolean r0 = r6.equals(r0)     // Catch:{ Exception -> 0x00fe }
            if (r0 == 0) goto L_0x009d
            r0 = 0
            goto L_0x009e
        L_0x0093:
            java.lang.String r0 = "endoffile"
            boolean r0 = r6.equals(r0)     // Catch:{ Exception -> 0x00fe }
            if (r0 == 0) goto L_0x009d
            r0 = 2
            goto L_0x009e
        L_0x009d:
            r0 = -1
        L_0x009e:
            switch(r0) {
                case 0: goto L_0x00d4;
                case 1: goto L_0x00c3;
                case 2: goto L_0x00b8;
                case 3: goto L_0x00a2;
                default: goto L_0x00a1;
            }     // Catch:{ Exception -> 0x00fe }
        L_0x00a1:
            goto L_0x00e7
        L_0x00a2:
            r5.hideLoading()     // Catch:{ Exception -> 0x00fe }
            r5.pauseCamera()     // Catch:{ Exception -> 0x00fe }
            r0 = 2131498391(0x7f0c1597, float:1.8620402E38)
            java.lang.String r0 = r5.getString(r0)     // Catch:{ Exception -> 0x00fe }
            r5.showError(r0)     // Catch:{ Exception -> 0x00fe }
            java.lang.String r0 = "starttime"
            r7.optInt(r0)     // Catch:{ Exception -> 0x00fe }
            goto L_0x00e7
        L_0x00b8:
            android.os.Handler r7 = r5.mHandler     // Catch:{ Exception -> 0x00fe }
            r7.removeMessages(r4)     // Catch:{ Exception -> 0x00fe }
            r5.isInEnding = r3     // Catch:{ Exception -> 0x00fe }
            r5.pauseCamera()     // Catch:{ Exception -> 0x00fe }
            goto L_0x00e7
        L_0x00c3:
            r5.hideLoading()     // Catch:{ Exception -> 0x00fe }
            r5.pauseCamera()     // Catch:{ Exception -> 0x00fe }
            r7 = 2131493771(0x7f0c038b, float:1.8611032E38)
            java.lang.String r7 = r5.getString(r7)     // Catch:{ Exception -> 0x00fe }
            r5.showError(r7)     // Catch:{ Exception -> 0x00fe }
            goto L_0x00e7
        L_0x00d4:
            java.lang.String r0 = "duration"
            int r7 = r7.optInt(r0, r1)     // Catch:{ Exception -> 0x00fe }
            if (r7 <= 0) goto L_0x00e7
            int r0 = r5.mDuration     // Catch:{ Exception -> 0x00fe }
            if (r0 == r7) goto L_0x00e7
            r5.mDuration = r7     // Catch:{ Exception -> 0x00fe }
            int r0 = r5.mStartTime     // Catch:{ Exception -> 0x00fe }
            int r0 = r0 + r7
            r5.mEndTime = r0     // Catch:{ Exception -> 0x00fe }
        L_0x00e7:
            java.lang.String r7 = "CameraPlay"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00fe }
            r0.<init>()     // Catch:{ Exception -> 0x00fe }
            java.lang.String r1 = " onServerCmd status "
            r0.append(r1)     // Catch:{ Exception -> 0x00fe }
            r0.append(r6)     // Catch:{ Exception -> 0x00fe }
            java.lang.String r6 = r0.toString()     // Catch:{ Exception -> 0x00fe }
            com.mijia.debug.SDKLog.d(r7, r6)     // Catch:{ Exception -> 0x00fe }
            goto L_0x0108
        L_0x00fe:
            r6 = move-exception
            java.lang.String r7 = "CameraPlay"
            java.lang.String r6 = r6.toString()
            com.mijia.debug.SDKLog.d(r7, r6)
        L_0x0108:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.camera.activity.sdcard.SDCardTimeLinePlayerActivity.onServerCmd(int, byte[]):void");
    }

    public void onDisConnected() {
        this.mNeedSetTime = true;
        this.mTimeLineControlView.setTimeItems(new ArrayList());
    }

    public void onDisconnectedWithCode(int i) {
        this.mNeedSetTime = true;
        this.mTimeLineControlView.setTimeItems(new ArrayList());
    }

    public void onConnected() {
        super.onConnected();
        if (this.mNeedSetTime) {
            if (this.isVideoPlaying) {
                startPlayVideo();
            }
            this.mNeedSetTime = false;
            SDKLog.d(Tag.b, "SDCardPlayer reconnect");
        }
    }

    /* access modifiers changed from: protected */
    public void setOrientation(boolean z) {
        if (!this.mCameraDevice.f().c()) {
            ToastUtil.a((Context) activity(), (int) R.string.power_off);
        } else if (z) {
            setRequestedOrientation(0);
        } else {
            setRequestedOrientation(1);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        setWindow(configuration);
        if (configuration.orientation != 1) {
            this.mFullScreen = true;
            this.mVideoViewFrame.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
            if (this.mVideoView != null) {
                this.mVideoView.setIsFull(true);
            }
            this.mTimeLineControlView.setBackgroundColor(getResources().getColor(R.color.land_timeline_bg));
            this.mSeeAllVieo.setVisibility(8);
            this.mTitleView.setVisibility(8);
            return;
        }
        this.mFullScreen = false;
        this.mVideoViewFrame.setLayoutParams(new RelativeLayout.LayoutParams(-1, Util.a((Context) activity(), 350.0f)));
        if (this.mVideoView != null) {
            this.mVideoView.setIsFull(false);
        }
        this.mTimeLineControlView.setBackgroundColor(getResources().getColor(R.color.camera_white_bg));
        this.mSeeAllVieo.setVisibility(0);
        this.mTitleView.setVisibility(0);
    }

    private void toggleSpeed() {
        if (this.mCameraPlayer == null || !this.mCameraPlayer.d()) {
            ToastUtil.a((Context) activity(), (int) R.string.sd_card_video_not_playing);
            return;
        }
        int q = this.mCameraPlayer.q();
        SDKLog.b(TAG, "speed:" + q);
        int i = 16;
        if (q == 1) {
            i = 4;
        } else if (q != 4) {
            i = 1;
        }
        this.mCameraPlayer.d(i);
        if (i == 1) {
            this.cdcToggleAudio.setEnabled(true);
        } else {
            if (this.cdcToggleAudio.isChecked()) {
                this.cdcToggleAudio.performClick();
            }
            this.cdcToggleAudio.setEnabled(false);
        }
        TextViewS textViewS = this.tvsMultiSpeed;
        textViewS.setText("" + i + "X");
    }

    private void snapShot() {
        if (!this.mCameraDevice.f().c()) {
            ToastUtil.a((Context) activity(), (int) R.string.power_off);
        } else if (this.mCameraDevice == null || this.mCameraDevice.f() == null || !this.mCameraDevice.f().c()) {
            ToastUtil.a((Context) activity(), (int) R.string.power_off);
        } else if (this.mCameraPlayer == null || !this.mCameraPlayer.i()) {
            ToastUtil.a((Context) activity(), (int) R.string.snap_failed_paused);
        } else {
            this.mVideoView.snap(new XmVideoViewGl.PhotoSnapCallback() {
                public void onSnap(final Bitmap bitmap) {
                    SDCardTimeLinePlayerActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            SDCardTimeLinePlayerActivity.this.onSnapShot(bitmap);
                        }
                    });
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void dismissSnapshotPopupRunnable(long j) {
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                if (SDCardTimeLinePlayerActivity.this.llFuncPopup.getVisibility() != 8) {
                    SDCardTimeLinePlayerActivity.this.llFuncPopup.startAnimation(AnimationUtils.loadAnimation(SDCardTimeLinePlayerActivity.this.activity(), R.anim.anim_snap_shot_out));
                    SDCardTimeLinePlayerActivity.this.llFuncPopup.setVisibility(8);
                }
            }
        }, j);
    }

    /* access modifiers changed from: protected */
    public void pauseCamera() {
        super.pauseCamera();
        changePlayBtnBg(false);
        this.isVideoPlaying = false;
    }

    /* access modifiers changed from: package-private */
    public void onSnapShot(Bitmap bitmap) {
        final String a2 = FileUtil.a(false, this.mCameraDevice.getDid());
        if (a2 != null && bitmap != null) {
            if (!CoreApi.a().D()) {
                bitmap = BitmapUtils.a(bitmap, activity());
            }
            if (bitmap != null) {
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(a2);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                    fileOutputStream.close();
                } catch (IOException unused) {
                    return;
                }
            }
            final Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, 200, (bitmap.getHeight() * 200) / bitmap.getWidth(), false);
            runOnUiThread(new Runnable() {
                public void run() {
                    if (new File(a2).exists()) {
                        ImageView imageView = (ImageView) SDCardTimeLinePlayerActivity.this.findViewById(R.id.ivShotPic);
                        if (SDCardTimeLinePlayerActivity.this.llFuncPopup.getVisibility() == 0) {
                            SDCardTimeLinePlayerActivity.this.llFuncPopup.setVisibility(8);
                        }
                        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) SDCardTimeLinePlayerActivity.this.llFuncPopup.getLayoutParams();
                        layoutParams.bottomMargin = SDCardTimeLinePlayerActivity.this.llVideoViewBottomCtrl.getHeight() + Util.a((Context) SDCardTimeLinePlayerActivity.this.activity(), 6.0f);
                        SDCardTimeLinePlayerActivity.this.llFuncPopup.setLayoutParams(layoutParams);
                        SDCardTimeLinePlayerActivity.this.llFuncPopup.startAnimation(AnimationUtils.loadAnimation(SDCardTimeLinePlayerActivity.this.activity(), R.anim.anim_snap_shot_in));
                        SDCardTimeLinePlayerActivity.this.llFuncPopup.setVisibility(0);
                        SDCardTimeLinePlayerActivity.this.dismissSnapshotPopupRunnable(3000);
                        if (createScaledBitmap != null) {
                            imageView.setImageBitmap(createScaledBitmap);
                        }
                        ContentValues contentValues = new ContentValues(4);
                        contentValues.put("datetaken", Long.valueOf(System.currentTimeMillis()));
                        contentValues.put(Downloads._DATA, a2);
                        contentValues.put("mime_type", "image/jpeg");
                        try {
                            if (!Build.MODEL.equals("HM 1SC")) {
                                SDCardTimeLinePlayerActivity.this.activity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                            }
                            SDKLog.b(SDCardTimeLinePlayerActivity.TAG, "snap success");
                        } catch (Throwable unused) {
                        }
                        final LocalFileManager.LocalFile b = SDCardTimeLinePlayerActivity.this.mCameraDevice.b().b(a2);
                        if (b != null) {
                            imageView.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    SDCardTimeLinePlayerActivity.this.dismissSnapshotPopupRunnable(0);
                                    Intent intent = new Intent(SDCardTimeLinePlayerActivity.this.activity(), LocalPicReviewActivity.class);
                                    intent.putExtra("file_path", b.d);
                                    SDCardTimeLinePlayerActivity.this.startActivity(intent);
                                }
                            });
                        }
                    }
                }
            });
        }
    }

    public void setPlayTime(long j, boolean z) {
        int i;
        TimeItem timeItem;
        long j2 = j;
        TimeItem a2 = this.mCameraDevice.c().a(j2);
        if (a2 != null) {
            SDKLog.d(Tag.b, "last set time before " + TimeUtils.b(j));
            int i2 = (int) (a2.f8098a / 1000);
            i = a2.f8098a < j2 ? ((int) (j2 - a2.f8098a)) / 1000 : 0;
            StringBuilder sb = new StringBuilder();
            sb.append("last set time after  ");
            long j3 = (long) (i * 1000);
            sb.append(TimeUtils.b(a2.f8098a + j3));
            SDKLog.d(Tag.b, sb.toString());
            this.mIsSetPlayTime = true;
            this.mSelectTime = i2 + i;
            this.mLastSetStart = i2;
            this.mTimeLineControlView.updatePlayTime(a2.f8098a + j3, false);
        } else {
            SDKLog.d(Tag.b, "last set time alive");
            this.mHandler.removeMessages(2);
            this.mIsSetPlayTime = true;
            if (this.mCameraSdcardFileManager.i() == null || this.mCameraSdcardFileManager.i().size() == 0) {
                initForFirstEnter();
                return;
            } else if (j2 != 0 && (timeItem = this.mCameraSdcardFileManager.i().get(this.mCameraSdcardFileManager.i().size() - 1)) != null) {
                if (j2 >= timeItem.c) {
                    toSdFilesEnd();
                    return;
                }
                i = 0;
            } else {
                return;
            }
        }
        if (!this.mUserPause) {
            hidError();
            showLoading("");
        }
        this.mLastSetPlayTime = System.currentTimeMillis();
        this.mHandler.removeMessages(2);
        if (a2 == null) {
            pauseCamera();
            return;
        }
        this.mStartTime = (int) (a2.f8098a / 1000);
        this.mEndTime = (int) (a2.c / 1000);
        this.mDuration = ((int) a2.b) / 1000;
        this.mLastUpdateTime = 0;
        this.mTimeItem = a2;
        setPlayWithOffset(i, z);
    }

    private void setPlayWithOffset(int i, boolean z) {
        if (i == this.mDuration) {
            i = this.mDuration - 2;
        }
        if (i < 0) {
            i = 0;
        }
        this.mLastSetTime = System.currentTimeMillis();
        this.mIsSetTime = true;
        this.mLastOffsetTime = i;
        this.mHandler.removeMessages(2);
        hidError();
        if (this.mPauseView.getVisibility() == 0) {
            this.mPauseView.setVisibility(8);
        }
        this.mUserPause = false;
        if (this.isOnFileEnd) {
            this.isOnFileEnd = false;
            z = false;
        }
        if (this.mCameraPlayer != null && (!this.mCameraPlayer.h() || !z)) {
            this.mCameraPlayer.a(this.mStartTime, i, 0);
            this.isVideoPlaying = true;
            changePlayBtnBg(true);
        }
        if (this.mCameraPlayer != null && !this.mCameraPlayer.h()) {
            showLoading();
        }
        this.mHandler.removeMessages(2);
        this.mHandler.sendEmptyMessageDelayed(2, 3000);
        SDKLog.d(Tag.b, "SDCardPlayer PlayTime " + this.mStartTime + " offset " + i + " end " + this.mEndTime);
    }

    /* access modifiers changed from: protected */
    public void onPlayError() {
        super.onPlayError();
        this.isVideoPlaying = false;
        changePlayBtnBg(false);
    }
}
