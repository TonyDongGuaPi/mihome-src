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
import com.mijia.camera.CameraPlayerEx;
import com.mijia.camera.CameraProperties;
import com.mijia.camera.Utils.BitmapUtils;
import com.mijia.camera.Utils.Util;
import com.mijia.debug.SDKLog;
import com.mijia.model.local.LocalFileManager;
import com.mijia.model.sdcard.SdcardManagerEx;
import com.mijia.model.sdcard.TimeItem;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.IMISSListener;
import com.xiaomi.smarthome.camera.XmVideoViewGl;
import com.xiaomi.smarthome.camera.activity.CameraPlayerBaseActivity;
import com.xiaomi.smarthome.camera.activity.local.LocalPicReviewActivity;
import com.xiaomi.smarthome.camera.activity.sdcard.SDCardTimeLinePlayerExActivity;
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
import java.util.List;

public class SDCardTimeLinePlayerExActivity extends CameraPlayerBaseActivity {
    private static final String TAG = "SDCardTimeLinePlayerExActivity";
    private int UPDATE_DURATION = 500;
    private CenterDrawableCheckBox cdcToggleAudio;
    private ImageView cdcTogglePlay;
    boolean isFirst = true;
    private boolean isForeground;
    private boolean isInEnding = false;
    private boolean isOnFileEnd;
    boolean isVideoPlaying = true;
    private ImageView ivCameraShot;
    private ImageView ivFullScreen;
    private LinearLayout llFuncPopup;
    /* access modifiers changed from: private */
    public LinearLayout llVideoViewBottomCtrl;
    BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (SDCardTimeLinePlayerExActivity.this.mCameraDevice.d().f().equals(intent.getAction()) && !SDCardTimeLinePlayerExActivity.this.isFinishing()) {
                SDCardTimeLinePlayerExActivity.this.mTimeLineControlView.setTimeItems(SDCardTimeLinePlayerExActivity.this.mCameraDevice.d().i());
                SDCardTimeLinePlayerExActivity.this.mHandler.post(new Runnable() {
                    public final void run() {
                        SDCardTimeLinePlayerExActivity.AnonymousClass1.lambda$onReceive$0(SDCardTimeLinePlayerExActivity.AnonymousClass1.this);
                    }
                });
            }
        }

        public static /* synthetic */ void lambda$onReceive$0(AnonymousClass1 r1) {
            if (SDCardTimeLinePlayerExActivity.this.isFirst) {
                SDCardTimeLinePlayerExActivity.this.startPlay();
            }
        }
    };
    private SdcardManagerEx mCameraSdcardFileManager;
    private int mDuration;
    private int mEndTime;
    private FrameLayout mFLTitleBar;
    private boolean mIsSetPlayTime;
    private boolean mIsSetTime = false;
    private int mLastOffsetTime = 0;
    private long mLastSetPlayTime;
    private long mLastSetTime = 0;
    private long mLastUpdateTime = 0;
    private LocalBroadcastManager mLocalBroadcastManager;
    private boolean mNeedSetTime = false;
    private View mPlayerHint1;
    private View mPlayerHint2;
    private View mSeeAllVieo;
    public boolean mSeekBarTouched = false;
    private int mSelectTime;
    private int mStartTime;
    private TimeLineControlView.TimeLineCallback mTimeCallBack = new TimeLineControlView.TimeLineCallback() {
        public void onUpdateTime(long j) {
            SDCardTimeLinePlayerExActivity.this.mTimeUpdateView.setText(TimeUtils.a(j));
            if (SDCardTimeLinePlayerExActivity.this.mNeedSpeed) {
                SDCardTimeLinePlayerExActivity.this.mHandler.removeMessages(4000);
                boolean unused = SDCardTimeLinePlayerExActivity.this.mNeedSpeed = false;
            }
            if (SDCardTimeLinePlayerExActivity.this.mTimeUpdateView.getVisibility() != 0) {
                SDCardTimeLinePlayerExActivity.this.mTimeUpdateView.setVisibility(0);
            }
        }

        public void onSelectTime(long j) {
            if (j != 0) {
                SDCardTimeLinePlayerExActivity.this.mHandler.removeMessages(2);
            }
            SDCardTimeLinePlayerExActivity.this.startPlayVideoWithCheckConnection(j, true);
            SDCardTimeLinePlayerExActivity.this.mTimeUpdateView.setVisibility(8);
        }

        public void onPlayLive() {
            SDCardTimeLinePlayerExActivity.this.toSdFilesEnd();
            SDCardTimeLinePlayerExActivity.this.mTimeUpdateView.setVisibility(8);
        }

        public void onCancel() {
            if (SDCardTimeLinePlayerExActivity.this.mTimeUpdateView.getVisibility() == 0) {
                SDCardTimeLinePlayerExActivity.this.mTimeUpdateView.setVisibility(8);
            }
        }
    };
    private TimeItem mTimeItem;
    /* access modifiers changed from: private */
    public TimeLineWithDatePickView mTimeLineControlView;
    /* access modifiers changed from: private */
    public TextView mTimeUpdateView;
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
        initView();
        if (this.mCameraDevice == null || this.mCameraDevice.n()) {
            this.mCameraSdcardFileManager = this.mCameraDevice.d();
            Event.e();
            this.mNetworkMonitor.register(this);
            return;
        }
        ToastUtil.a((Context) this, (int) R.string.action_fail);
        finish();
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
            if (this.mCameraPlayerEx == null || !this.mCameraPlayerEx.g()) {
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
                SDCardTimeLinePlayerExActivity.this.startActivity(new Intent(SDCardTimeLinePlayerExActivity.this, SDCardActivity.class));
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
        if (this.mNetworkMonitor != null) {
            this.mNetworkMonitor.unregister();
        }
    }

    private void initView() {
        this.ivCameraShot = (ImageView) findViewById(R.id.ivCameraShot);
        this.ivCameraShot.setOnClickListener(this);
        this.llVideoViewBottomCtrl = (LinearLayout) findViewById(R.id.llVideoViewBottomCtrl);
        this.tvsMultiSpeed = (TextViewS) findViewById(R.id.tvsMultiSpeed);
        this.tvsMultiSpeed.setOnClickListener(this);
        if (this.mCameraDevice.o()) {
            this.tvsMultiSpeed.setVisibility(8);
        }
        this.cdcToggleAudio = (CenterDrawableCheckBox) findViewById(R.id.cdcToggleAudio);
        this.cdcToggleAudio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                SDKLog.b(SDCardTimeLinePlayerExActivity.TAG, "cdcToggleAudio isChecked:" + z);
                if (SDCardTimeLinePlayerExActivity.this.mCameraPlayerEx == null) {
                    return;
                }
                if (z) {
                    SDCardTimeLinePlayerExActivity.this.mCameraPlayerEx.b(false);
                } else {
                    SDCardTimeLinePlayerExActivity.this.mCameraPlayerEx.b(true);
                }
            }
        });
        this.cdcTogglePlay = (ImageView) findViewById(R.id.cdcTogglePlay);
        this.cdcTogglePlay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SDCardTimeLinePlayerExActivity.this.switchPlayBtnStatus();
            }
        });
        findViewById(R.id.togglePlay).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SDCardTimeLinePlayerExActivity.this.switchPlayBtnStatus();
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
            this.mErrorRetryView = LayoutInflater.from(this).inflate(R.layout.camera_v4_error_retry, this.mVideoLayout, false);
        } else {
            this.mErrorRetryView = LayoutInflater.from(this).inflate(R.layout.camera_error_retry, this.mVideoLayout, false);
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
                SDCardTimeLinePlayerExActivity.this.refreshUI();
            }
        });
        initTimeSelectView();
        initOtherView();
        initForFirstEnter();
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public void onPause() {
        if (this.mCameraPlayerEx != null) {
            this.mCameraPlayerEx.j();
            this.mCameraPlayerEx.w();
            this.mCameraPlayerEx = null;
        }
        if (this.mVideoView != null) {
            this.mVideoView.releaseOnlySelf();
            this.mVideoView = null;
        }
        try {
            this.mLocalBroadcastManager.unregisterReceiver(this.mBroadcastReceiver);
            this.mCameraDevice.d().b();
        } catch (Throwable unused) {
        }
        super.onPause();
        this.isForeground = false;
    }

    /* access modifiers changed from: protected */
    public void doResume() {
        if ((this.mUserPause && !this.isVideoPlaying) || !this.isForeground) {
            return;
        }
        if (getEndTimeOfLastVideo() == 0) {
            pauseCamera();
        } else {
            startPlayVideoWithCheckConnection(this.mTimeLineControlView.getSelectTime(), false);
        }
    }

    /* access modifiers changed from: protected */
    public void refreshUI() {
        if (this.mCameraPlayerEx == null || this.mCameraPlayerEx.u()) {
            this.cdcToggleAudio.setChecked(false);
        } else {
            this.cdcToggleAudio.setChecked(true);
        }
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        int i = message.what;
        if (i != 2) {
            if (i == 4000) {
                this.mNeedSpeed = false;
                this.mTimeUpdateView.setVisibility(8);
            }
        } else if (this.mCameraPlayerEx != null) {
            long e = this.mCameraPlayerEx.e();
            if (this.mLastUpdateTime == e || this.mSeekBarTouched || !this.mCameraPlayerEx.g()) {
                this.mHandler.removeMessages(2);
                this.mHandler.sendEmptyMessageDelayed(2, (long) this.UPDATE_DURATION);
                return;
            }
            if (e >= 0) {
                setIsConnected();
            }
            if (getEndTimeOfLastVideo() == 0 || getEndTimeOfLastVideo() - (e * 1000) >= ((long) this.UPDATE_DURATION)) {
                this.mLastUpdateTime = e;
                int i2 = (int) (e - (this.mTimeItem.f8098a / 1000));
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
                    this.mTimeLineControlView.updatePlayTime(e * 1000, false);
                } else if (Math.abs(((long) this.mSelectTime) - e) < 10 || System.currentTimeMillis() - this.mLastSetPlayTime > 10000) {
                    this.mIsSetPlayTime = false;
                    this.mTimeLineControlView.updatePlayTime(e * 1000, false);
                    hideLoading();
                    SDKLog.b(TAG, "update " + Math.abs(((long) this.mSelectTime) - e) + "  " + ((System.currentTimeMillis() - this.mLastSetPlayTime) / 1000));
                }
            } else if (!this.isInEnding) {
                toSdFilesEnd();
            }
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
        if (TextUtils.isEmpty(this.mCameraDevice.getModel()) || TextUtils.isEmpty(this.mCameraDevice.getDid())) {
            hideLoading();
            showError(getString(R.string.camera_play_error2));
            return;
        }
        if (this.mCameraPlayerEx == null) {
            this.mCameraPlayerEx = new CameraPlayerEx(this, this.mCameraDevice, this, this.mVideoView);
            this.mCameraPlayerEx.a();
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
            startPlayVideoWithCheckConnection(this.mTimeLineControlView.getSelectTime(), false);
        } else if (this.mCameraDevice.d().i() != null && this.mCameraDevice.d().i().size() != 0) {
            if (this.isFirst) {
                showHideViews();
            }
            this.isFirst = false;
            TimeItem timeItem = this.mCameraSdcardFileManager.i().get(this.mCameraSdcardFileManager.i().size() - 1);
            if (timeItem != null) {
                startPlayVideoWithCheckConnection(timeItem.f8098a, false);
            }
        }
    }

    public void onResume() {
        this.mVideoView = XmPluginHostApi.instance().createVideoViewOnFront(activity(), this.mVideoLayout, true, 1);
        this.mVideoView.initial();
        this.mVideoView.setVideoViewListener(new XmVideoViewGl.IVideoViewListener() {
            public void onVideoViewClick() {
                if (SDCardTimeLinePlayerExActivity.this.llVideoViewBottomCtrl.getTranslationY() > 0.0f) {
                    ObjectAnimator ofFloat = ObjectAnimator.ofFloat(SDCardTimeLinePlayerExActivity.this.llVideoViewBottomCtrl, "translationY", new float[]{(float) SDCardTimeLinePlayerExActivity.this.llVideoViewBottomCtrl.getHeight(), 0.0f});
                    ofFloat.setDuration(200);
                    ofFloat.start();
                    return;
                }
                ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(SDCardTimeLinePlayerExActivity.this.llVideoViewBottomCtrl, "translationY", new float[]{0.0f, (float) SDCardTimeLinePlayerExActivity.this.llVideoViewBottomCtrl.getHeight()});
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
        this.mTimeLineControlView.setTimeItems(this.mCameraDevice.d().i());
        super.onResume();
        showLoading();
        if (this.mCameraPlayerEx != null) {
            this.mCameraPlayerEx.b(true);
            TextViewS textViewS = this.tvsMultiSpeed;
            textViewS.setText("" + this.mCameraPlayerEx.v() + "X");
        } else {
            this.tvsMultiSpeed.setText("1X");
        }
        if (!this.isFirst) {
            startPlay();
        }
        this.mHandler.postDelayed(new Runnable() {
            public final void run() {
                SDCardTimeLinePlayerExActivity.lambda$onResume$0(SDCardTimeLinePlayerExActivity.this);
            }
        }, 1000);
        this.isForeground = true;
    }

    public static /* synthetic */ void lambda$onResume$0(SDCardTimeLinePlayerExActivity sDCardTimeLinePlayerExActivity) {
        sDCardTimeLinePlayerExActivity.mCameraDevice.d().j();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(sDCardTimeLinePlayerExActivity.mCameraDevice.d().f());
        sDCardTimeLinePlayerExActivity.mLocalBroadcastManager.registerReceiver(sDCardTimeLinePlayerExActivity.mBroadcastReceiver, intentFilter);
        sDCardTimeLinePlayerExActivity.mCameraDevice.d().a(40000);
    }

    /* access modifiers changed from: private */
    public void startPlayVideoWithCheckConnection(final long j, final boolean z) {
        if (this.mCameraPlayerEx != null) {
            if (this.mCameraPlayerEx.l()) {
                startMissPlayback(j, z);
                return;
            }
            showLoading();
            this.mCameraPlayerEx.a((IMISSListener) new IMISSListener() {
                public void onProgress(int i) {
                }

                public void onSuccess(String str, Object obj) {
                    if (!SDCardTimeLinePlayerExActivity.this.isFinishing()) {
                        SDCardTimeLinePlayerExActivity.this.runOnUiThread(new Runnable() {
                            public final void run() {
                                SDCardTimeLinePlayerExActivity.this.hideLoading();
                            }
                        });
                        if (SDCardTimeLinePlayerExActivity.this.mCameraPlayerEx != null && SDCardTimeLinePlayerExActivity.this.mCameraPlayerEx.l()) {
                            SDCardTimeLinePlayerExActivity.this.startMissPlayback(j, z);
                        }
                    }
                }

                public void onFailed(int i, String str) {
                    if (!SDCardTimeLinePlayerExActivity.this.isFinishing()) {
                        SDCardTimeLinePlayerExActivity.this.runOnUiThread(new Runnable() {
                            public final void run() {
                                SDCardTimeLinePlayerExActivity.this.hideLoading();
                            }
                        });
                        LogUtil.b(SDCardTimeLinePlayerExActivity.TAG, "SDCardTimeLinePlayerExActivity startStreamPlay onFailed:" + i + " " + str);
                        SDCardTimeLinePlayerExActivity.this.showError(SDCardTimeLinePlayerExActivity.this.getString(R.string.video_play_failed));
                    }
                }
            });
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

    /* access modifiers changed from: private */
    public void startMissPlayback(long j, boolean z) {
        setPlayTime(j, z, new IMISSListener() {
            public void onProgress(int i) {
            }

            public void onSuccess(String str, Object obj) {
                if (!SDCardTimeLinePlayerExActivity.this.isFinishing()) {
                    SDCardTimeLinePlayerExActivity.this.runOnUiThread(new Runnable() {
                        public final void run() {
                            SDCardTimeLinePlayerExActivity.this.hideLoading();
                        }
                    });
                }
            }

            public void onFailed(int i, String str) {
                if (!SDCardTimeLinePlayerExActivity.this.isFinishing()) {
                    SDCardTimeLinePlayerExActivity.this.runOnUiThread(new Runnable() {
                        public final void run() {
                            SDCardTimeLinePlayerExActivity.this.hideLoading();
                        }
                    });
                    LogUtil.b(SDCardTimeLinePlayerExActivity.TAG, "SDCardTimeLinePlayerExActivity playbackStream onFailed:" + i + " " + str);
                    SDCardTimeLinePlayerExActivity.this.showError(SDCardTimeLinePlayerExActivity.this.getString(R.string.video_play_failed));
                    SDCardTimeLinePlayerExActivity.this.mHandler.removeMessages(2);
                }
            }
        });
        if (!this.mCameraPlayerEx.u()) {
            this.mCameraPlayerEx.b(this.mCameraPlayerEx.u());
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x00c3 A[Catch:{ Exception -> 0x0120 }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00c4 A[Catch:{ Exception -> 0x0120 }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00da A[Catch:{ Exception -> 0x0120 }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00e5 A[Catch:{ Exception -> 0x0120 }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00f6 A[Catch:{ Exception -> 0x0120 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onServerCmd(int r6, byte[] r7) {
        /*
            r5 = this;
            boolean r0 = r5.isFinishing()
            if (r0 == 0) goto L_0x0007
            return
        L_0x0007:
            java.lang.String r0 = "SDCardTimeLinePlayerExActivity"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "server cmd:"
            r1.append(r2)
            r1.append(r6)
            java.lang.String r2 = " "
            r1.append(r2)
            java.lang.String r2 = new java.lang.String
            r2.<init>(r7)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.xiaomi.smarthome.framework.log.LogUtil.a((java.lang.String) r0, (java.lang.String) r1)
            r0 = 270(0x10e, float:3.78E-43)
            if (r6 != r0) goto L_0x012a
            java.lang.String r6 = "SDCardTimeLinePlayerExActivity"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "onServerCmd thread "
            r0.append(r1)
            java.lang.Thread r1 = java.lang.Thread.currentThread()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.mijia.debug.SDKLog.d(r6, r0)
            java.lang.String r6 = new java.lang.String     // Catch:{ Exception -> 0x0120 }
            r6.<init>(r7)     // Catch:{ Exception -> 0x0120 }
            org.json.JSONObject r7 = new org.json.JSONObject     // Catch:{ Exception -> 0x0120 }
            r7.<init>(r6)     // Catch:{ Exception -> 0x0120 }
            java.lang.String r0 = "id"
            r1 = -1
            int r0 = r7.optInt(r0, r1)     // Catch:{ Exception -> 0x0120 }
            java.lang.String r2 = "SDCardTimeLinePlayerExActivity"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0120 }
            r3.<init>()     // Catch:{ Exception -> 0x0120 }
            java.lang.String r4 = "id "
            r3.append(r4)     // Catch:{ Exception -> 0x0120 }
            r3.append(r6)     // Catch:{ Exception -> 0x0120 }
            java.lang.String r6 = r3.toString()     // Catch:{ Exception -> 0x0120 }
            com.mijia.debug.SDKLog.d(r2, r6)     // Catch:{ Exception -> 0x0120 }
            int r6 = r5.mStartTime     // Catch:{ Exception -> 0x0120 }
            if (r0 != r6) goto L_0x012a
            java.lang.String r6 = "status"
            r0 = 0
            java.lang.String r6 = r7.optString(r6, r0)     // Catch:{ Exception -> 0x0120 }
            if (r6 == 0) goto L_0x012a
            int r0 = r6.hashCode()     // Catch:{ Exception -> 0x0120 }
            r2 = -1897432978(0xffffffff8ee7786e, float:-5.7061866E-30)
            r3 = 0
            r4 = 2
            if (r0 == r2) goto L_0x00b5
            r2 = -1309954170(0xffffffffb1ebaf86, float:-6.859355E-9)
            if (r0 == r2) goto L_0x00ab
            r2 = -1107950030(0xffffffffbdf60632, float:-0.120129004)
            if (r0 == r2) goto L_0x00a1
            r2 = 250180107(0xee9720b, float:5.7548754E-30)
            if (r0 == r2) goto L_0x0097
            goto L_0x00bf
        L_0x0097:
            java.lang.String r0 = "filenotfound"
            boolean r0 = r6.equals(r0)     // Catch:{ Exception -> 0x0120 }
            if (r0 == 0) goto L_0x00bf
            r0 = 1
            goto L_0x00c0
        L_0x00a1:
            java.lang.String r0 = "readerror"
            boolean r0 = r6.equals(r0)     // Catch:{ Exception -> 0x0120 }
            if (r0 == 0) goto L_0x00bf
            r0 = 3
            goto L_0x00c0
        L_0x00ab:
            java.lang.String r0 = "filefound"
            boolean r0 = r6.equals(r0)     // Catch:{ Exception -> 0x0120 }
            if (r0 == 0) goto L_0x00bf
            r0 = 0
            goto L_0x00c0
        L_0x00b5:
            java.lang.String r0 = "endoffile"
            boolean r0 = r6.equals(r0)     // Catch:{ Exception -> 0x0120 }
            if (r0 == 0) goto L_0x00bf
            r0 = 2
            goto L_0x00c0
        L_0x00bf:
            r0 = -1
        L_0x00c0:
            switch(r0) {
                case 0: goto L_0x00f6;
                case 1: goto L_0x00e5;
                case 2: goto L_0x00da;
                case 3: goto L_0x00c4;
                default: goto L_0x00c3;
            }     // Catch:{ Exception -> 0x0120 }
        L_0x00c3:
            goto L_0x0109
        L_0x00c4:
            r5.hideLoading()     // Catch:{ Exception -> 0x0120 }
            r5.pauseCamera()     // Catch:{ Exception -> 0x0120 }
            r0 = 2131498391(0x7f0c1597, float:1.8620402E38)
            java.lang.String r0 = r5.getString(r0)     // Catch:{ Exception -> 0x0120 }
            r5.showError(r0)     // Catch:{ Exception -> 0x0120 }
            java.lang.String r0 = "starttime"
            r7.optInt(r0)     // Catch:{ Exception -> 0x0120 }
            goto L_0x0109
        L_0x00da:
            android.os.Handler r7 = r5.mHandler     // Catch:{ Exception -> 0x0120 }
            r7.removeMessages(r4)     // Catch:{ Exception -> 0x0120 }
            r5.isInEnding = r3     // Catch:{ Exception -> 0x0120 }
            r5.pauseCamera()     // Catch:{ Exception -> 0x0120 }
            goto L_0x0109
        L_0x00e5:
            r5.hideLoading()     // Catch:{ Exception -> 0x0120 }
            r5.pauseCamera()     // Catch:{ Exception -> 0x0120 }
            r7 = 2131493771(0x7f0c038b, float:1.8611032E38)
            java.lang.String r7 = r5.getString(r7)     // Catch:{ Exception -> 0x0120 }
            r5.showError(r7)     // Catch:{ Exception -> 0x0120 }
            goto L_0x0109
        L_0x00f6:
            java.lang.String r0 = "duration"
            int r7 = r7.optInt(r0, r1)     // Catch:{ Exception -> 0x0120 }
            if (r7 <= 0) goto L_0x0109
            int r0 = r5.mDuration     // Catch:{ Exception -> 0x0120 }
            if (r0 == r7) goto L_0x0109
            r5.mDuration = r7     // Catch:{ Exception -> 0x0120 }
            int r0 = r5.mStartTime     // Catch:{ Exception -> 0x0120 }
            int r0 = r0 + r7
            r5.mEndTime = r0     // Catch:{ Exception -> 0x0120 }
        L_0x0109:
            java.lang.String r7 = "SDCardTimeLinePlayerExActivity"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0120 }
            r0.<init>()     // Catch:{ Exception -> 0x0120 }
            java.lang.String r1 = " onServerCmd status "
            r0.append(r1)     // Catch:{ Exception -> 0x0120 }
            r0.append(r6)     // Catch:{ Exception -> 0x0120 }
            java.lang.String r6 = r0.toString()     // Catch:{ Exception -> 0x0120 }
            com.mijia.debug.SDKLog.d(r7, r6)     // Catch:{ Exception -> 0x0120 }
            goto L_0x012a
        L_0x0120:
            r6 = move-exception
            java.lang.String r7 = "CameraPlay"
            java.lang.String r6 = r6.toString()
            com.mijia.debug.SDKLog.d(r7, r6)
        L_0x012a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.camera.activity.sdcard.SDCardTimeLinePlayerExActivity.onServerCmd(int, byte[]):void");
    }

    public void onDisConnected() {
        this.mNeedSetTime = true;
    }

    public void onDisconnectedWithCode(int i) {
        this.mNeedSetTime = true;
    }

    public void onBackPressed() {
        if (this.mFullScreen) {
            runOnUiThread(new Runnable() {
                public final void run() {
                    SDCardTimeLinePlayerExActivity.this.setOrientation(false);
                }
            });
        } else {
            super.onBackPressed();
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
        if (this.mCameraPlayerEx == null || !this.mCameraPlayerEx.g()) {
            ToastUtil.a((Context) activity(), (int) R.string.sd_card_video_not_playing);
            return;
        }
        int v = this.mCameraPlayerEx.v();
        SDKLog.b(TAG, "speed:" + v);
        int i = 16;
        if (v == 1) {
            i = 4;
        } else if (v != 4) {
            i = 1;
        }
        this.mCameraPlayerEx.b(i);
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

    public void onConnected() {
        super.onConnected();
        if (this.mNeedSetTime) {
            if (!this.mUserPause) {
                startPlayVideo();
            }
            this.mNeedSetTime = false;
            SDKLog.b(TAG, "SDCardPlayer reconnect");
        }
    }

    private void snapShot() {
        if (!this.mCameraDevice.f().c()) {
            ToastUtil.a((Context) activity(), (int) R.string.power_off);
        } else if (this.mCameraDevice == null || this.mCameraDevice.f() == null || !this.mCameraDevice.f().c()) {
            ToastUtil.a((Context) activity(), (int) R.string.power_off);
        } else if (this.mCameraPlayerEx == null || !this.mCameraPlayerEx.l()) {
            ToastUtil.a((Context) activity(), (int) R.string.snap_failed_paused);
        } else {
            this.mVideoView.snap(new XmVideoViewGl.PhotoSnapCallback() {
                public final void onSnap(Bitmap bitmap) {
                    SDCardTimeLinePlayerExActivity.this.runOnUiThread(new Runnable(bitmap) {
                        private final /* synthetic */ Bitmap f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void run() {
                            SDCardTimeLinePlayerExActivity.this.onSnapShot(this.f$1);
                        }
                    });
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void pauseCamera() {
        super.pauseCamera();
        changePlayBtnBg(false);
        this.isVideoPlaying = false;
    }

    private void dismissSnapshotPopupRunnable(long j) {
        this.mHandler.postDelayed(new Runnable() {
            public final void run() {
                SDCardTimeLinePlayerExActivity.lambda$dismissSnapshotPopupRunnable$4(SDCardTimeLinePlayerExActivity.this);
            }
        }, j);
    }

    public static /* synthetic */ void lambda$dismissSnapshotPopupRunnable$4(SDCardTimeLinePlayerExActivity sDCardTimeLinePlayerExActivity) {
        if (sDCardTimeLinePlayerExActivity.llFuncPopup.getVisibility() != 8) {
            sDCardTimeLinePlayerExActivity.llFuncPopup.startAnimation(AnimationUtils.loadAnimation(sDCardTimeLinePlayerExActivity.activity(), R.anim.anim_snap_shot_out));
            sDCardTimeLinePlayerExActivity.llFuncPopup.setVisibility(8);
        }
    }

    /* access modifiers changed from: package-private */
    public void onSnapShot(Bitmap bitmap) {
        String a2 = FileUtil.a(false, this.mCameraDevice.getDid());
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
            runOnUiThread(new Runnable(a2, Bitmap.createScaledBitmap(bitmap, 200, (bitmap.getHeight() * 200) / bitmap.getWidth(), false)) {
                private final /* synthetic */ String f$1;
                private final /* synthetic */ Bitmap f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    SDCardTimeLinePlayerExActivity.lambda$onSnapShot$6(SDCardTimeLinePlayerExActivity.this, this.f$1, this.f$2);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$onSnapShot$6(SDCardTimeLinePlayerExActivity sDCardTimeLinePlayerExActivity, String str, Bitmap bitmap) {
        if (new File(str).exists()) {
            ImageView imageView = (ImageView) sDCardTimeLinePlayerExActivity.findViewById(R.id.ivShotPic);
            if (sDCardTimeLinePlayerExActivity.llFuncPopup.getVisibility() == 0) {
                sDCardTimeLinePlayerExActivity.llFuncPopup.setVisibility(8);
            }
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) sDCardTimeLinePlayerExActivity.llFuncPopup.getLayoutParams();
            layoutParams.bottomMargin = sDCardTimeLinePlayerExActivity.llVideoViewBottomCtrl.getHeight() + Util.a((Context) sDCardTimeLinePlayerExActivity.activity(), 6.0f);
            sDCardTimeLinePlayerExActivity.llFuncPopup.setLayoutParams(layoutParams);
            sDCardTimeLinePlayerExActivity.llFuncPopup.startAnimation(AnimationUtils.loadAnimation(sDCardTimeLinePlayerExActivity.activity(), R.anim.anim_snap_shot_in));
            sDCardTimeLinePlayerExActivity.llFuncPopup.setVisibility(0);
            sDCardTimeLinePlayerExActivity.dismissSnapshotPopupRunnable(3000);
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }
            ContentValues contentValues = new ContentValues(4);
            contentValues.put("datetaken", Long.valueOf(System.currentTimeMillis()));
            contentValues.put(Downloads._DATA, str);
            contentValues.put("mime_type", "image/jpeg");
            try {
                if (!Build.MODEL.equals("HM 1SC")) {
                    sDCardTimeLinePlayerExActivity.activity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                }
                SDKLog.b(TAG, "snap success");
            } catch (Throwable unused) {
            }
            LocalFileManager.LocalFile b = sDCardTimeLinePlayerExActivity.mCameraDevice.b().b(str);
            if (b != null) {
                imageView.setOnClickListener(new View.OnClickListener(b) {
                    private final /* synthetic */ LocalFileManager.LocalFile f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void onClick(View view) {
                        SDCardTimeLinePlayerExActivity.lambda$null$5(SDCardTimeLinePlayerExActivity.this, this.f$1, view);
                    }
                });
            }
        }
    }

    public static /* synthetic */ void lambda$null$5(SDCardTimeLinePlayerExActivity sDCardTimeLinePlayerExActivity, LocalFileManager.LocalFile localFile, View view) {
        sDCardTimeLinePlayerExActivity.dismissSnapshotPopupRunnable(0);
        Intent intent = new Intent(sDCardTimeLinePlayerExActivity.activity(), LocalPicReviewActivity.class);
        intent.putExtra("file_path", localFile.d);
        sDCardTimeLinePlayerExActivity.startActivity(intent);
    }

    public void setPlayTime(long j, boolean z, IMISSListener iMISSListener) {
        int i;
        TimeItem timeItem;
        long j2 = j;
        TimeItem a2 = this.mCameraDevice.d().a(j2);
        if (a2 != null) {
            SDKLog.d(TAG, "last set time before " + TimeUtils.b(j));
            int i2 = (int) (a2.f8098a / 1000);
            i = a2.f8098a < j2 ? ((int) (j2 - a2.f8098a)) / 1000 : 0;
            StringBuilder sb = new StringBuilder();
            sb.append("last set time after  ");
            long j3 = (long) (i * 1000);
            sb.append(TimeUtils.b(a2.f8098a + j3));
            SDKLog.d(TAG, sb.toString());
            this.mIsSetPlayTime = true;
            this.mSelectTime = i2 + i;
            this.mTimeLineControlView.updatePlayTime(a2.f8098a + j3, false);
        } else {
            SDKLog.d(TAG, "last set time alive");
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
        setPlayWithOffset(i, z, iMISSListener);
    }

    private void setPlayWithOffset(int i, boolean z, IMISSListener iMISSListener) {
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
        if (this.mCameraPlayerEx != null && (!this.mCameraPlayerEx.k() || !z)) {
            changePlayBtnBg(true);
            if (!this.mCameraPlayerEx.g()) {
                this.mCameraPlayerEx.a(true, (IMISSListener) null);
            }
            this.mCameraPlayerEx.b(this.mStartTime, i, 0, iMISSListener);
            this.isVideoPlaying = true;
            changePlayBtnBg(true);
            if (!(this.mErrorRetryView == null || this.mErrorRetryView.getVisibility() == 8)) {
                this.mErrorRetryView.setVisibility(8);
            }
        }
        if (this.mCameraPlayerEx != null && !this.mCameraPlayerEx.k()) {
            showLoading();
        }
        this.mHandler.removeMessages(2);
        this.mHandler.sendEmptyMessageDelayed(2, 3000);
        SDKLog.d(TAG, "SDCardPlayer PlayTime " + this.mStartTime + " offset " + i + " end " + this.mEndTime);
    }

    /* access modifiers changed from: protected */
    public void onPlayError() {
        super.onPlayError();
        this.isVideoPlaying = false;
        changePlayBtnBg(false);
    }
}
