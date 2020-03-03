package com.xiaomi.smarthome.camera.activity.alarm2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.FileDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.mijia.app.Event;
import com.mijia.model.alarmcloud.AlarmVideo;
import com.mijia.model.alarmcloud.FaceInfoMeta;
import com.p2p.audio.AudioFoucsTool;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.activity.CameraBaseActivity;
import com.xiaomi.smarthome.camera.activity.alarm.AlarmSettingV2Activity;
import com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmEventListAdapter;
import com.xiaomi.smarthome.camera.activity.alarm2.util.TopSmoothScroller;
import com.xiaomi.smarthome.camera.view.widget.FeedbackDialog;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoDownloadActivity;
import com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoDownloadManager;
import com.xiaomi.smarthome.miio.camera.cloudstorage.exopackage.MJExoPlayerViewP;
import com.xiaomi.smarthome.miio.camera.cloudstorage.model.CloudVideoDownloadInfo;
import com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoNetUtils;
import com.xiaomi.smarthome.miio.camera.face.FaceManager;
import com.xiaomi.smarthome.miio.camera.face.util.FaceUtils;
import com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class AlarmVideoPlayerActivity extends CameraBaseActivity implements View.OnClickListener, AlarmEventListAdapter.OnItemClickLister {
    public static final int PLAYER_STATE_IDLE = 0;
    public static final int PLAYER_STATE_PAUSE = 1;
    public static final int PLAYER_STATE_PLAY = 2;
    private static final String TAG = "AlarmVideoPlayerActivity";
    /* access modifiers changed from: private */
    public CheckBox cbIsMute;
    /* access modifiers changed from: private */
    public CheckBox cbIsMuteLand;
    /* access modifiers changed from: private */
    public CheckBox cbTogglePlay;
    /* access modifiers changed from: private */
    public CheckBox cbTogglePlayLand;
    private MJExoPlayerViewP exoVideoView;
    /* access modifiers changed from: private */
    public String fileId;
    private FrameLayout flControlContainer;
    private FrameLayout flTitleBar;
    private FrameLayout flVideoContainer;
    /* access modifiers changed from: private */
    public Runnable getCurrentPosRunnable = new Runnable() {
        public void run() {
            if (AlarmVideoPlayerActivity.this.simpleExoPlayer != null) {
                final long currentPosition = AlarmVideoPlayerActivity.this.simpleExoPlayer.getCurrentPosition();
                AlarmVideoPlayerActivity.this.setProgressTime(currentPosition);
                if (AlarmVideoPlayerActivity.this.simpleExoPlayer.getPlayWhenReady()) {
                    AlarmVideoPlayerActivity.this.cbTogglePlay.setChecked(false);
                } else {
                    AlarmVideoPlayerActivity.this.cbTogglePlay.setChecked(true);
                }
                AlarmVideoPlayerActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        AlarmVideoPlayerActivity.this.simpleExoPlayer.getDuration();
                        AlarmVideoPlayerActivity.this.setStartTime(currentPosition);
                        AlarmVideoPlayerActivity.this.mAlarmEventListAdapter.parseProgress(currentPosition);
                    }
                });
                AlarmVideoPlayerActivity.this.mHandler.postDelayed(AlarmVideoPlayerActivity.this.getCurrentPosRunnable, 500);
            }
        }
    };
    /* access modifiers changed from: private */
    public boolean isAlarm;
    private ImageView ivDeleteCurrentVideo;
    private ImageView ivFullScreen;
    private ImageView ivFullScreenLand;
    private View ivVideoBack;
    private ImageView ivVideoDownload;
    private ImageView ivVideoLoading;
    /* access modifiers changed from: private */
    public ImageView ivVideoViewCover;
    private View llTopRightCtrl;
    /* access modifiers changed from: private */
    public AlarmEventListAdapter mAlarmEventListAdapter;
    CloudVideoDownloadManager.ICloudVideoDownloadListener mCloudVideoDownloadListener = new CloudVideoDownloadManager.ICloudVideoDownloadListener() {
        public void onInfo(CloudVideoDownloadInfo cloudVideoDownloadInfo, int i) {
        }

        public void onProgress(CloudVideoDownloadInfo cloudVideoDownloadInfo, int i) {
        }

        public void onSpeed(CloudVideoDownloadInfo cloudVideoDownloadInfo, int i) {
        }

        public void onStart(CloudVideoDownloadInfo cloudVideoDownloadInfo) {
        }

        public void onStop(CloudVideoDownloadInfo cloudVideoDownloadInfo, int i) {
        }

        public void onFinish(CloudVideoDownloadInfo cloudVideoDownloadInfo) {
            LogUtil.a(AlarmVideoPlayerActivity.TAG, "onFinish " + cloudVideoDownloadInfo.m3u8LocalPath);
            if (cloudVideoDownloadInfo.fileId.equals(AlarmVideoPlayerActivity.this.fileId)) {
                ToastUtil.a((int) R.string.save_success);
            }
        }

        public void onCancelled(CloudVideoDownloadInfo cloudVideoDownloadInfo) {
            ToastUtil.a((int) R.string.save_fail);
        }

        public void onError(CloudVideoDownloadInfo cloudVideoDownloadInfo, int i) {
            ToastUtil.a((int) R.string.save_fail);
        }
    };
    /* access modifiers changed from: private */
    public long mCreateTime;
    /* access modifiers changed from: private */
    public RecyclerView mRecyclerView;
    private SimpleDateFormat mTimeFormat;
    /* access modifiers changed from: private */
    public boolean needRefresh;
    private int offset;
    private RelativeLayout rlVideoViewBottomCtrl;
    private View rlVideoViewBottomCtrlLand;
    private SeekBar sbProgress;
    private SeekBar sbProgressLand;
    /* access modifiers changed from: private */
    public SimpleExoPlayer simpleExoPlayer;
    /* access modifiers changed from: private */
    public double startDuration;
    private int storedState = 0;
    private ImageView title_bar_more;
    /* access modifiers changed from: private */
    public TextView tvDownloadHint;
    private TextView tvFeedback;
    private TextView tvVideoEnd;
    private TextView tvVideoEndLand;
    private TextView tvVideoInfo;
    private TextView tvVideoStart;
    private TextView tvVideoStartLand;
    private TextView tvVideoTitle;

    public void doCreate(Bundle bundle) {
        Event.a(Event.aK);
        super.doCreate(bundle);
        setContentView(R.layout.camera_activity_alarm_video_player);
        Intent intent = getIntent();
        this.fileId = intent.getStringExtra("fileId");
        this.isAlarm = intent.getBooleanExtra("isAlarm", false);
        this.mCreateTime = intent.getLongExtra("createTime", 0);
        this.offset = intent.getIntExtra("offset", 0);
        this.startDuration = intent.getDoubleExtra("startDuration", 0.0d);
        if (!getIntent().getBooleanExtra("pincod", false)) {
            enableVerifyPincode();
        }
        LogUtil.a(TAG, "fileId = " + this.fileId + " createTime = " + this.mCreateTime + " startDuration = " + this.startDuration + " isAlarm = " + this.isAlarm);
        initView();
        initPlayer();
        initRecyclerView();
        getVideoUrl();
        getEventList();
        findViewById(R.id.ivVideoShare).setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void checkHasFace(ArrayList<AlarmVideo> arrayList) {
        boolean z;
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i2 >= arrayList.size()) {
                z = false;
                break;
            } else if (arrayList.get(i2).fileIdMetaResult != null) {
                z = true;
                break;
            } else {
                i2++;
            }
        }
        TextView textView = this.tvFeedback;
        if (!z) {
            i = 8;
        }
        textView.setVisibility(i);
    }

    private void initView() {
        this.flTitleBar = (FrameLayout) findViewById(R.id.title_bar);
        ((TextView) findViewById(R.id.title_bar_title)).setText(getString(R.string.housekeeping));
        this.title_bar_more = (ImageView) findViewById(R.id.title_bar_more);
        this.title_bar_more.setImageResource(R.drawable.house_keeping_setting);
        this.title_bar_more.setOnClickListener(this);
        int i = 0;
        this.title_bar_more.setVisibility(0);
        if (this.mCameraDevice.isReadOnlyShared()) {
            this.title_bar_more.setVisibility(8);
        }
        findViewById(R.id.title_bar_return).setOnClickListener(this);
        this.rlVideoViewBottomCtrl = (RelativeLayout) findViewById(R.id.rlVideoViewBottomCtrl);
        this.rlVideoViewBottomCtrlLand = findViewById(R.id.rlVideoViewBottomCtrlLand);
        this.llTopRightCtrl = findViewById(R.id.llTopRightCtrl);
        if (this.mCameraDevice.isReadOnlyShared()) {
            this.llTopRightCtrl.setVisibility(8);
        }
        this.ivVideoLoading = (ImageView) findViewById(R.id.ivVideoLoading);
        this.tvVideoInfo = (TextView) findViewById(R.id.tvVideoInfo);
        this.tvVideoInfo.setOnClickListener(this);
        this.ivDeleteCurrentVideo = (ImageView) findViewById(R.id.ivDeleteCurrentVideo);
        this.ivDeleteCurrentVideo.setOnClickListener(this);
        this.ivVideoDownload = (ImageView) findViewById(R.id.ivVideoDownload);
        this.ivVideoDownload.setVisibility(0);
        this.ivVideoDownload.setOnClickListener(this);
        this.ivVideoViewCover = (ImageView) findViewById(R.id.ivVideoViewCover);
        this.tvFeedback = (TextView) findViewById(R.id.tv_feedback);
        this.tvFeedback.getPaint().setFlags(8);
        this.tvFeedback.getPaint().setAntiAlias(true);
        this.tvFeedback.setOnClickListener(this);
        TextView textView = this.tvFeedback;
        if (this.mCameraDevice.isShared() || this.mCameraDevice.isReadOnlyShared()) {
            i = 8;
        }
        textView.setVisibility(i);
        this.tvDownloadHint = (TextView) findViewById(R.id.tvDownloadHint);
        this.tvDownloadHint.setOnClickListener(this);
        this.mTimeFormat = new SimpleDateFormat("mm:ss");
        this.mTimeFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        this.ivVideoBack = findViewById(R.id.ivVideoBack);
        this.ivVideoBack.setOnClickListener(this);
        this.tvVideoTitle = (TextView) findViewById(R.id.tvVideoTitle);
        this.tvVideoTitle.setText(getString(R.string.housekeeping));
        this.flVideoContainer = (FrameLayout) findViewById(R.id.fl_video_container);
        this.flControlContainer = (FrameLayout) findViewById(R.id.fl_control_container);
        this.mHandler.post(new Runnable() {
            public void run() {
                AlarmVideoPlayerActivity.this.toggleVideoCtrlViewTranslation();
            }
        });
    }

    private void initPlayer() {
        this.exoVideoView = (MJExoPlayerViewP) findViewById(R.id.exoVideoView);
        this.exoVideoView.setPlayerViewListener(new MJExoPlayerViewP.PlayerViewListener() {
            public void onVideoSizeChanged(int i, int i2, int i3, float f) {
                AlarmVideoPlayerActivity.this.getRequestedOrientation();
            }

            public void onRenderedFirstFrame() {
                if (AlarmVideoPlayerActivity.this.simpleExoPlayer != null) {
                    AlarmVideoPlayerActivity.this.setEndTime(AlarmVideoPlayerActivity.this.simpleExoPlayer.getDuration());
                }
                AlarmVideoPlayerActivity.this.mHandler.post(AlarmVideoPlayerActivity.this.getCurrentPosRunnable);
                AlarmVideoPlayerActivity.this.hideVideoLoading();
            }
        });
        this.simpleExoPlayer = ExoPlayerFactory.newSimpleInstance((Context) this, (TrackSelector) new DefaultTrackSelector((TrackSelection.Factory) new AdaptiveTrackSelection.Factory(new DefaultBandwidthMeter())));
        this.exoVideoView.setPlayer(this.simpleExoPlayer);
        if (!(this.exoVideoView == null || this.exoVideoView.getVideoSurfaceView() == null)) {
            this.exoVideoView.getVideoSurfaceView().setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    AlarmVideoPlayerActivity.this.toggleVideoCtrlViewTranslation();
                }
            });
        }
        if (this.simpleExoPlayer != null) {
            this.simpleExoPlayer.addListener(new Player.EventListener() {
                public void onTimelineChanged(Timeline timeline, Object obj, int i) {
                    LogUtil.a(AlarmVideoPlayerActivity.TAG, "onTimelineChanged:" + i);
                }

                public void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
                    LogUtil.a(AlarmVideoPlayerActivity.TAG, "onTracksChanged");
                }

                public void onLoadingChanged(boolean z) {
                    if (z) {
                        AlarmVideoPlayerActivity.this.showVideoLoading(false);
                    } else {
                        AlarmVideoPlayerActivity.this.hideVideoLoading();
                    }
                    LogUtil.a(AlarmVideoPlayerActivity.TAG, "onLoadingChanged:" + z);
                }

                public void onPlayerStateChanged(boolean z, int i) {
                    switch (i) {
                        case 1:
                            AlarmVideoPlayerActivity.this.hideVideoLoading();
                            break;
                        case 2:
                            AlarmVideoPlayerActivity.this.showVideoLoading(false);
                            break;
                        case 3:
                            AlarmVideoPlayerActivity.this.hideVideoLoading();
                            break;
                        case 4:
                            AlarmVideoPlayerActivity.this.hideVideoLoading();
                            AlarmVideoPlayerActivity.this.simpleExoPlayer.seekTo(0);
                            AlarmVideoPlayerActivity.this.onCompletion();
                            AlarmVideoPlayerActivity.this.mHandler.removeCallbacks(AlarmVideoPlayerActivity.this.getCurrentPosRunnable);
                            break;
                    }
                    LogUtil.a(AlarmVideoPlayerActivity.TAG, "onPlayerStateChanged:" + z + " playbackState:" + i);
                }

                public void onRepeatModeChanged(int i) {
                    LogUtil.a(AlarmVideoPlayerActivity.TAG, "onRepeatModeChanged:" + i);
                }

                public void onShuffleModeEnabledChanged(boolean z) {
                    LogUtil.a(AlarmVideoPlayerActivity.TAG, "onShuffleModeEnabledChanged:" + z);
                }

                public void onPlayerError(ExoPlaybackException exoPlaybackException) {
                    AlarmVideoPlayerActivity.this.hideVideoLoading();
                    if (exoPlaybackException != null) {
                        LogUtil.a(AlarmVideoPlayerActivity.TAG, "onPlayerError:" + exoPlaybackException.getLocalizedMessage() + "" + exoPlaybackException.type + " error:" + exoPlaybackException.toString());
                    }
                }

                public void onPositionDiscontinuity(int i) {
                    LogUtil.a(AlarmVideoPlayerActivity.TAG, "onPositionDiscontinuity:" + i);
                }

                public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
                    LogUtil.a(AlarmVideoPlayerActivity.TAG, "onPlaybackParametersChanged:" + playbackParameters.speed);
                }

                public void onSeekProcessed() {
                    LogUtil.a(AlarmVideoPlayerActivity.TAG, "onSeekProcessed");
                }
            });
        } else {
            LogUtil.b(TAG, "simpleExoPlayer null");
        }
        this.cbTogglePlay = (CheckBox) findViewById(R.id.cbTogglePlay);
        this.cbTogglePlayLand = (CheckBox) findViewById(R.id.cbTogglePlayLand);
        this.cbTogglePlay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    AlarmVideoPlayerActivity.this.simpleExoPlayer.setPlayWhenReady(false);
                    AlarmVideoPlayerActivity.this.mHandler.removeCallbacks(AlarmVideoPlayerActivity.this.getCurrentPosRunnable);
                } else {
                    AlarmVideoPlayerActivity.this.simpleExoPlayer.setPlayWhenReady(true);
                    AlarmVideoPlayerActivity.this.ivVideoViewCover.setVisibility(8);
                    AlarmVideoPlayerActivity.this.mHandler.post(AlarmVideoPlayerActivity.this.getCurrentPosRunnable);
                }
                if (z != AlarmVideoPlayerActivity.this.cbTogglePlayLand.isChecked()) {
                    AlarmVideoPlayerActivity.this.cbTogglePlayLand.setChecked(z);
                }
            }
        });
        this.cbTogglePlayLand.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    AlarmVideoPlayerActivity.this.simpleExoPlayer.setPlayWhenReady(false);
                    AlarmVideoPlayerActivity.this.mHandler.removeCallbacks(AlarmVideoPlayerActivity.this.getCurrentPosRunnable);
                } else {
                    AlarmVideoPlayerActivity.this.simpleExoPlayer.setPlayWhenReady(true);
                    AlarmVideoPlayerActivity.this.ivVideoViewCover.setVisibility(8);
                    AlarmVideoPlayerActivity.this.mHandler.post(AlarmVideoPlayerActivity.this.getCurrentPosRunnable);
                }
                if (z != AlarmVideoPlayerActivity.this.cbTogglePlay.isChecked()) {
                    AlarmVideoPlayerActivity.this.cbTogglePlay.setChecked(z);
                }
            }
        });
        this.cbIsMute = (CheckBox) findViewById(R.id.cbIsMute);
        this.cbIsMuteLand = (CheckBox) findViewById(R.id.cbIsMuteLand);
        this.cbIsMute.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Event.a(Event.aL);
                if (z) {
                    AlarmVideoPlayerActivity.this.simpleExoPlayer.setVolume(0.0f);
                    AudioFoucsTool.b(AlarmVideoPlayerActivity.this, "audioFoucs");
                } else {
                    AlarmVideoPlayerActivity.this.simpleExoPlayer.setVolume(1.0f);
                    AudioFoucsTool.a(AlarmVideoPlayerActivity.this, "audioFoucs");
                }
                if (z != AlarmVideoPlayerActivity.this.cbIsMuteLand.isChecked()) {
                    AlarmVideoPlayerActivity.this.cbIsMuteLand.setChecked(z);
                }
            }
        });
        this.cbIsMuteLand.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Event.a(Event.aL);
                if (z) {
                    AlarmVideoPlayerActivity.this.simpleExoPlayer.setVolume(0.0f);
                } else {
                    AlarmVideoPlayerActivity.this.simpleExoPlayer.setVolume(1.0f);
                }
                if (z != AlarmVideoPlayerActivity.this.cbIsMute.isChecked()) {
                    AlarmVideoPlayerActivity.this.cbIsMute.setChecked(z);
                }
            }
        });
        this.cbIsMute.setChecked(true);
        this.cbIsMuteLand.setChecked(true);
        this.sbProgress = (SeekBar) this.rlVideoViewBottomCtrl.findViewById(R.id.sbProgress);
        this.sbProgressLand = (SeekBar) findViewById(R.id.sbProgressLand);
        this.sbProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                AlarmVideoPlayerActivity.this.mHandler.removeCallbacks(AlarmVideoPlayerActivity.this.getCurrentPosRunnable);
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                AlarmVideoPlayerActivity.this.seekTo(seekBar.getProgress());
                AlarmVideoPlayerActivity.this.mHandler.postDelayed(AlarmVideoPlayerActivity.this.getCurrentPosRunnable, 500);
            }
        });
        this.sbProgressLand.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                AlarmVideoPlayerActivity.this.mHandler.removeCallbacks(AlarmVideoPlayerActivity.this.getCurrentPosRunnable);
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                AlarmVideoPlayerActivity.this.seekTo(seekBar.getProgress());
                AlarmVideoPlayerActivity.this.mHandler.postDelayed(AlarmVideoPlayerActivity.this.getCurrentPosRunnable, 500);
            }
        });
        this.ivFullScreen = (ImageView) findViewById(R.id.ivFullScreen);
        this.ivFullScreenLand = (ImageView) findViewById(R.id.ivFullScreenLand);
        this.ivFullScreen.setOnClickListener(this);
        this.ivFullScreenLand.setOnClickListener(this);
        this.tvVideoStart = (TextView) findViewById(R.id.tvVideoStart);
        this.tvVideoStartLand = (TextView) findViewById(R.id.tvVideoStartLand);
        this.tvVideoEnd = (TextView) findViewById(R.id.tvVideoEnd);
        this.tvVideoEndLand = (TextView) findViewById(R.id.tvVideoEndLand);
    }

    private void initRecyclerView() {
        this.mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.mAlarmEventListAdapter = new AlarmEventListAdapter(this);
        this.mRecyclerView.setAdapter(this.mAlarmEventListAdapter);
        this.mAlarmEventListAdapter.setOnItemClickLister(this);
        this.mAlarmEventListAdapter.setShareUser(this.mCameraDevice.isShared() || this.mCameraDevice.isReadOnlyShared());
    }

    /* access modifiers changed from: private */
    public void toggleVideoCtrlViewTranslation() {
        if (!this.mCameraDevice.isReadOnlyShared()) {
            if (this.llTopRightCtrl.getTranslationY() >= 0.0f) {
                this.llTopRightCtrl.setTranslationY((float) (-this.llTopRightCtrl.getHeight()));
                if (getRequestedOrientation() != 1) {
                    this.rlVideoViewBottomCtrlLand.setTranslationY((float) this.rlVideoViewBottomCtrlLand.getHeight());
                    return;
                }
                return;
            }
            this.llTopRightCtrl.setTranslationY(0.0f);
            if (getRequestedOrientation() != 1) {
                this.rlVideoViewBottomCtrlLand.setTranslationY(0.0f);
            }
        }
    }

    /* access modifiers changed from: private */
    public void seekTo(int i) {
        if (i >= 0 && this.simpleExoPlayer != null && this.simpleExoPlayer.getDuration() > 0) {
            this.simpleExoPlayer.seekTo((long) ((int) ((((long) i) * this.simpleExoPlayer.getDuration()) / 100)));
        }
    }

    /* access modifiers changed from: private */
    public void setProgressTime(long j) {
        long duration = this.simpleExoPlayer.getDuration();
        if (j >= 0 && duration > 0) {
            int ceil = (int) Math.ceil((double) ((100 * j) / duration));
            if (ceil >= 100 || j / 1000 >= duration / 1000) {
                this.sbProgress.setProgress(100);
                this.sbProgressLand.setProgress(100);
                return;
            }
            this.sbProgress.setProgress(ceil);
            this.sbProgressLand.setProgress(ceil);
        }
    }

    /* access modifiers changed from: private */
    public void setStartTime(long j) {
        this.tvVideoStart.setText(this.mTimeFormat.format(Long.valueOf(j)));
        this.tvVideoStartLand.setText(this.mTimeFormat.format(Long.valueOf(j)));
    }

    /* access modifiers changed from: private */
    public void setEndTime(long j) {
        this.tvVideoEnd.setText(this.mTimeFormat.format(Long.valueOf(j)));
        this.tvVideoEndLand.setText(this.mTimeFormat.format(Long.valueOf(j)));
    }

    public void onCompletion() {
        this.mHandler.removeCallbacks(this.getCurrentPosRunnable);
        if (this.sbProgress != null && this.sbProgress.getProgress() < 100) {
            this.sbProgress.setProgress(100);
            this.sbProgressLand.setProgress(100);
        }
        this.cbTogglePlay.setChecked(true);
    }

    /* access modifiers changed from: private */
    public void showVideoLoading(boolean z) {
        if (this.ivVideoLoading != null) {
            this.ivVideoLoading.setVisibility(0);
            Drawable drawable = this.ivVideoLoading.getDrawable();
            if (drawable instanceof AnimationDrawable) {
                ((AnimationDrawable) drawable).start();
            }
            if (z) {
                this.mHandler.postDelayed(new Runnable() {
                    public void run() {
                        AlarmVideoPlayerActivity.this.hideVideoLoading();
                    }
                }, 5000);
            }
        }
    }

    /* access modifiers changed from: private */
    public void hideVideoLoading() {
        if (this.ivVideoLoading != null) {
            Drawable drawable = this.ivVideoLoading.getDrawable();
            if (drawable instanceof AnimationDrawable) {
                ((AnimationDrawable) drawable).stop();
            }
            this.ivVideoLoading.setVisibility(8);
        }
    }

    private void getVideoUrl() {
        String videoFileUrl = CloudVideoNetUtils.getInstance().getVideoFileUrl(this.mCameraDevice.getDid(), this.mCameraDevice.getModel(), this.fileId, this.isAlarm);
        LogUtil.a(TAG, "videoUrl = " + videoFileUrl);
        HashMap hashMap = new HashMap();
        MiServiceTokenInfo tokenInfo = CloudVideoNetUtils.getInstance().getTokenInfo();
        if (tokenInfo != null) {
            hashMap.put("Cookie", "yetAnotherServiceToken=" + tokenInfo.c);
            this.simpleExoPlayer.prepare(buildMediaSource(this, videoFileUrl, hashMap));
            this.simpleExoPlayer.setPlayWhenReady(true);
            if (this.offset > 0) {
                this.simpleExoPlayer.seekTo((long) (this.startDuration * 1000.0d));
            }
            if (this.cbIsMute.isChecked()) {
                this.simpleExoPlayer.setVolume(0.0f);
            } else {
                this.simpleExoPlayer.setVolume(1.0f);
            }
        }
    }

    /* access modifiers changed from: private */
    public void getEventList() {
        this.mCameraDevice.a().getEventListByFileId(this.fileId, this.isAlarm, new Callback<ArrayList<AlarmVideo>>() {
            public void onFailure(int i, String str) {
            }

            public void onSuccess(ArrayList<AlarmVideo> arrayList) {
                Collections.sort(arrayList, new Comparator<AlarmVideo>() {
                    public int compare(AlarmVideo alarmVideo, AlarmVideo alarmVideo2) {
                        if (alarmVideo.createTime > alarmVideo2.createTime) {
                            return 1;
                        }
                        return alarmVideo.createTime < alarmVideo2.createTime ? -1 : 0;
                    }
                });
                AlarmVideoPlayerActivity.this.checkHasFace(arrayList);
                if (arrayList != null && arrayList.size() > 0) {
                    long unused = AlarmVideoPlayerActivity.this.mCreateTime = arrayList.get(0).createTime;
                    AlarmVideoPlayerActivity.this.mAlarmEventListAdapter.setData(arrayList);
                    if (AlarmVideoPlayerActivity.this.startDuration > 0.0d) {
                        AlarmVideoPlayerActivity.this.mAlarmEventListAdapter.parseProgress((long) (AlarmVideoPlayerActivity.this.startDuration * 1000.0d));
                    }
                }
            }
        });
    }

    private void downloadHint() {
        Event.a(Event.aM);
        if (CloudVideoNetUtils.getInstance().isWifiConnected(this)) {
            doDownload();
            showDownloadActivityHint();
            return;
        }
        new MLAlertDialog.Builder(this).b((CharSequence) getString(R.string.cs_non_wifi_environment)).a((int) R.string.cs_go_on, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                AlarmVideoPlayerActivity.this.doDownload();
                AlarmVideoPlayerActivity.this.showDownloadActivityHint();
            }
        }).a((DialogInterface.OnCancelListener) new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
            }
        }).b((int) R.string.cs_cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).d();
    }

    /* access modifiers changed from: private */
    public void showDownloadActivityHint() {
        this.tvDownloadHint.setVisibility(0);
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                if (AlarmVideoPlayerActivity.this.tvDownloadHint != null) {
                    AlarmVideoPlayerActivity.this.tvDownloadHint.setVisibility(8);
                }
            }
        }, 3000);
    }

    /* access modifiers changed from: private */
    public void doDownload() {
        long duration = this.simpleExoPlayer.getDuration();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Device b = SmartHomeDeviceManager.a().b(this.mCameraDevice.getDid());
        if (b != null) {
            ArrayList arrayList = new ArrayList();
            CloudVideoDownloadInfo cloudVideoDownloadInfo = new CloudVideoDownloadInfo();
            cloudVideoDownloadInfo.uid = b.userId;
            cloudVideoDownloadInfo.did = this.mCameraDevice.getDid();
            cloudVideoDownloadInfo.title = this.mCameraDevice.getName();
            cloudVideoDownloadInfo.videoUrl = CloudVideoNetUtils.getInstance().getVideoFileUrl(this.mCameraDevice.getDid(), this.mCameraDevice.getModel(), this.fileId, this.isAlarm);
            cloudVideoDownloadInfo.fileId = this.fileId;
            cloudVideoDownloadInfo.mp4FilePath = null;
            cloudVideoDownloadInfo.m3u8FilePath = null;
            cloudVideoDownloadInfo.status = 4;
            cloudVideoDownloadInfo.createTime = System.currentTimeMillis();
            cloudVideoDownloadInfo.startTime = this.mCreateTime;
            cloudVideoDownloadInfo.endTime = this.mCreateTime + duration;
            cloudVideoDownloadInfo.duration = duration / 1000;
            cloudVideoDownloadInfo.createTimePretty = simpleDateFormat.format(Long.valueOf(cloudVideoDownloadInfo.createTime));
            cloudVideoDownloadInfo.startTimePretty = simpleDateFormat.format(Long.valueOf(cloudVideoDownloadInfo.startTime));
            cloudVideoDownloadInfo.endTimePretty = simpleDateFormat.format(Long.valueOf(cloudVideoDownloadInfo.endTime));
            cloudVideoDownloadInfo.size = 0;
            cloudVideoDownloadInfo.progress = 0;
            arrayList.add(cloudVideoDownloadInfo);
            CloudVideoDownloadManager.getInstance().insertRecords(arrayList);
            CloudVideoDownloadManager.getInstance().addListener(this.mCloudVideoDownloadListener);
            CloudVideoDownloadManager.getInstance().pullDownloadFromList(getContext(), b.userId, this.mCameraDevice.getDid());
        }
    }

    private void showDeleteVideoDialog() {
        new MLAlertDialog.Builder(this).b((CharSequence) getString(R.string.cs_delete_video)).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                AlarmVideoPlayerActivity.this.mCameraDevice.a().deleteFiles(new Callback() {
                    public void onFailure(int i, String str) {
                    }

                    public void onSuccess(Object obj) {
                        AlarmVideoPlayerActivity.this.setResult(-1);
                        AlarmVideoPlayerActivity.this.finish();
                    }
                }, AlarmVideoPlayerActivity.this.isAlarm, AlarmVideoPlayerActivity.this.fileId);
            }
        }).a((DialogInterface.OnCancelListener) new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
            }
        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).d();
    }

    private MediaSource buildMediaSource(Context context, String str, Map<String, String> map) {
        Uri parse = Uri.parse(str);
        if (TextUtils.isEmpty(parse.getScheme()) || (!parse.getScheme().equals("http") && !parse.getScheme().equals("https"))) {
            return buildMediaSourceLocal(parse);
        }
        return buildMediaSourceHTTP(context, parse, map);
    }

    private HlsMediaSource buildMediaSourceHTTP(Context context, Uri uri, Map<String, String> map) {
        DefaultHttpDataSourceFactory defaultHttpDataSourceFactory = new DefaultHttpDataSourceFactory(Util.getUserAgent(context, "SmartHome;Android"), new DefaultBandwidthMeter());
        if (map != null && map.size() > 0) {
            for (Map.Entry next : map.entrySet()) {
                defaultHttpDataSourceFactory.setDefaultRequestProperty("" + ((String) next.getKey()), "" + ((String) next.getValue()));
            }
        }
        return new HlsMediaSource.Factory((DataSource.Factory) defaultHttpDataSourceFactory).createMediaSource(uri);
    }

    private MediaSource buildMediaSourceLocal(Uri uri) {
        return new HlsMediaSource.Factory((DataSource.Factory) new FileDataSourceFactory()).createMediaSource(uri);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        LogUtil.a(TAG, "orientation:" + configuration.orientation);
        if (configuration.orientation == 1) {
            getWindow().clearFlags(1024);
            this.flTitleBar.setVisibility(0);
            this.ivFullScreen.setImageResource(R.drawable.camera_icon_alarm_player_fullscreen);
            this.ivFullScreenLand.setImageResource(R.drawable.camera_icon_alarm_player_fullscreen);
            if (this.flVideoContainer != null) {
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.exoVideoView.getLayoutParams());
                layoutParams.height = DisplayUtils.a(210.0f);
                this.flVideoContainer.setLayoutParams(layoutParams);
                this.flVideoContainer.requestLayout();
            }
            this.tvVideoTitle.setVisibility(8);
            this.ivVideoBack.setVisibility(8);
            this.rlVideoViewBottomCtrlLand.setVisibility(8);
            this.rlVideoViewBottomCtrl.setVisibility(0);
            return;
        }
        getWindow().setFlags(1024, 1024);
        this.flTitleBar.setVisibility(8);
        this.ivFullScreen.setImageResource(R.drawable.camera_icon_alarm_player_exit_fullscreen);
        this.ivFullScreenLand.setImageResource(R.drawable.camera_icon_alarm_player_exit_fullscreen);
        if (this.flVideoContainer != null) {
            this.flVideoContainer.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
            this.flVideoContainer.requestLayout();
        }
        this.rlVideoViewBottomCtrlLand.setVisibility(0);
        this.rlVideoViewBottomCtrl.setVisibility(8);
        this.tvVideoTitle.setVisibility(0);
        this.ivVideoBack.setVisibility(0);
    }

    public void onBackPressed() {
        if (getRequestedOrientation() != 1) {
            setRequestedOrientation(1);
            return;
        }
        if (this.needRefresh) {
            setResult(-1);
        }
        super.onBackPressed();
    }

    private void showFeedbackDialog() {
        Event.a(Event.bK);
        final FeedbackDialog feedbackDialog = new FeedbackDialog(this);
        feedbackDialog.setTitle(R.string.face_match_error);
        feedbackDialog.setOnClickListener(new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                feedbackDialog.dismiss();
                AlarmVideoPlayerActivity.this.mCameraDevice.a().feedBack(AlarmVideoPlayerActivity.this.fileId, true, new Callback() {
                    public void onSuccess(Object obj) {
                        ToastUtil.a((int) R.string.thanks_feedback);
                    }

                    public void onFailure(int i, String str) {
                        ToastUtil.a((int) R.string.action_fail);
                    }
                });
            }
        });
        feedbackDialog.setModel(this.mCameraDevice.getModel());
        feedbackDialog.show();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivDeleteCurrentVideo:
                showDeleteVideoDialog();
                Event.a(Event.bI);
                return;
            case R.id.ivFullScreen:
            case R.id.ivFullScreenLand:
                Event.a(Event.aN);
                if (getRequestedOrientation() == 1) {
                    setRequestedOrientation(0);
                    return;
                } else {
                    setRequestedOrientation(1);
                    return;
                }
            case R.id.ivVideoBack:
                onBackPressed();
                return;
            case R.id.ivVideoDownload:
                downloadHint();
                return;
            case R.id.title_bar_more:
                startActivity(new Intent(this, AlarmSettingV2Activity.class));
                Event.a(Event.aR);
                return;
            case R.id.title_bar_return:
                finish();
                return;
            case R.id.tvDownloadHint:
                this.tvDownloadHint.setVisibility(8);
                Intent intent = new Intent(this, CloudVideoDownloadActivity.class);
                intent.putExtra("did", this.mCameraDevice.getDid());
                intent.putExtra("title", this.mCameraDevice.getName());
                Device b = SmartHomeDeviceManager.a().b(this.mCameraDevice.getDid());
                if (b != null) {
                    intent.putExtra("uid", b.userId);
                    startActivity(intent);
                    return;
                }
                return;
            case R.id.tv_feedback:
                showFeedbackDialog();
                return;
            default:
                return;
        }
    }

    public void onItemClick(AlarmVideo alarmVideo, int i) {
        this.simpleExoPlayer.seekTo((long) (alarmVideo.startDuration * 1000.0d));
    }

    public void onBtnClick(AlarmVideo alarmVideo, int i, boolean z) {
        FaceInfoMeta faceInfoMeta = alarmVideo.fileIdMetaResult.faceInfoMetas.get(0);
        Event.a(Event.bJ);
        if (z) {
            FaceUtils.processMarkFace(this, faceInfoMeta.faceId, FaceManager.getInstance(this.mCameraDevice), new FaceManager.IFaceCallback() {
                public void onSuccess(Object obj, Object obj2) {
                    ToastUtil.a((int) R.string.action_success);
                    AlarmVideoPlayerActivity.this.getEventList();
                    boolean unused = AlarmVideoPlayerActivity.this.needRefresh = true;
                }

                public void onFailure(int i, String str) {
                    ToastUtil.a((int) R.string.action_fail);
                }
            });
        } else {
            FaceUtils.processReplaceFace(getContext(), faceInfoMeta.faceId, faceInfoMeta.figureId, faceInfoMeta.figureName, FaceManager.getInstance(this.mCameraDevice), new FaceManager.IFaceCallback<Object>() {
                public void onSuccess(Object obj, Object obj2) {
                    ToastUtil.a((int) R.string.action_success);
                    AlarmVideoPlayerActivity.this.getEventList();
                    boolean unused = AlarmVideoPlayerActivity.this.needRefresh = true;
                }

                public void onFailure(int i, String str) {
                    ToastUtil.a((int) R.string.action_fail);
                }
            });
        }
    }

    public void onResume() {
        super.onResume();
        this.ivVideoViewCover.setVisibility(8);
        if (this.simpleExoPlayer != null && this.simpleExoPlayer.getCurrentPosition() > 0 && this.storedState == 2) {
            this.simpleExoPlayer.setPlayWhenReady(true);
            if (this.cbIsMute.isChecked()) {
                this.simpleExoPlayer.setVolume(0.0f);
            } else {
                this.simpleExoPlayer.setVolume(1.0f);
            }
            this.mHandler.post(this.getCurrentPosRunnable);
        }
    }

    public void onPause() {
        super.onPause();
        if (this.simpleExoPlayer != null) {
            if (this.simpleExoPlayer.getPlayWhenReady()) {
                this.storedState = 2;
            } else {
                this.storedState = 1;
            }
            this.simpleExoPlayer.setPlayWhenReady(false);
        }
        this.mHandler.removeCallbacks(this.getCurrentPosRunnable);
    }

    public void smoothScrollToPosition(final int i) {
        this.mHandler.post(new Runnable() {
            public void run() {
                TopSmoothScroller topSmoothScroller = new TopSmoothScroller(AlarmVideoPlayerActivity.this.getContext());
                topSmoothScroller.setTargetPosition(i);
                if (AlarmVideoPlayerActivity.this.mRecyclerView.getLayoutManager() != null) {
                    AlarmVideoPlayerActivity.this.mRecyclerView.getLayoutManager().startSmoothScroll(topSmoothScroller);
                }
            }
        });
    }
}
